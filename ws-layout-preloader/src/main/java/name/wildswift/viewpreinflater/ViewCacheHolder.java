package name.wildswift.viewpreinflater;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import name.wildswift.viewpreinflater.config.qualifiers.QualifiersSpec;

class ViewCacheHolder<T extends View> implements Comparable<ViewCacheHolder<T>>{
    private final Context context;
    private final QualifiersSpec qualifiers;
    private final Class<T> clazz;
    private final int count;
    private final ExecutorService executor;

    private final ArrayList<T> elements = new ArrayList<>();
    private final Object internalMutex = new Object();
    private volatile boolean initStarted = false;
    private volatile boolean initialized = false;
    private Handler mainHandler = new Handler(Looper.getMainLooper());


    ViewCacheHolder(Context context, Class<T> clazz, QualifiersSpec qualifiers, int count, ExecutorService executor) {
        this.context = qualifiers.convertContext(context);
        this.clazz = clazz;
        this.qualifiers = qualifiers;
        this.count = count;
        this.executor = executor;
    }


    @Override
    public int compareTo(ViewCacheHolder<T> that) {
        return qualifiers.compareTo(that.qualifiers);
    }

    void initialize(final CacheHolderCallback callback) {
        if (initStarted || initialized) return;
        if (Looper.myLooper() == null) throw new IllegalStateException();
        final Handler handler = new Handler();
        final PerformOnInit onInitTask = new PerformOnInit(callback);
        synchronized (internalMutex) {
            if (initStarted) return;
            final List<Future<Boolean>> futures;
            try {
                futures = executor.invokeAll(Collections.nCopies(count, new InitTask()));
            } catch (InterruptedException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (Future<Boolean> loadTaskResult : futures) {
                            loadTaskResult.get();
                        }
                        handler.post(onInitTask);
                        initStarted = false;
                        initialized = true;
                    } catch (Exception e) {
                        mainHandler.post(new ThrowExceptionRunnable(e));
                    }
                }
            }).start();
            initStarted = true;
        }
    }

    T getView(Resources res) {
        if (!initialized) throw new IllegalStateException();
        if (!qualifiers.accept(res)) return null;
        synchronized (internalMutex) {
            while (true) {
                if (elements.size() > 0) {
                    T result = elements.remove(elements.size() - 1);
                    executor.execute(new LoadTask());
                    return result;
                }

                try {
                    internalMutex.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    private class InitTask implements Callable<Boolean> {

        private InitTask() {
        }

        @Override
        public Boolean call() throws Exception {
            Constructor<T> constructor = clazz.getConstructor(Context.class);
            T view = constructor.newInstance(context);
            synchronized (internalMutex) {
                elements.add(view);
            }
            return Boolean.TRUE;
        }
    }

    private class LoadTask implements Runnable {
        private LoadTask() {
        }

        @Override
        public void run() {
            try {
                Constructor<T> constructor = clazz.getConstructor(Context.class);
                T view = constructor.newInstance(context);
                synchronized (internalMutex) {
                    elements.add(view);
                    internalMutex.notifyAll();
                }
            } catch (Exception e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            }
        }
    }

    private class PerformOnInit implements Runnable {
        private final CacheHolderCallback callback;

        private PerformOnInit(CacheHolderCallback callback) {
            this.callback = callback;
        }

        @Override
        public void run() {
            callback.onInit();
        }
    }

    private static class ThrowExceptionRunnable implements Runnable {
        private final Exception e;

        ThrowExceptionRunnable(Exception e) {
            this.e = e;
        }

        @Override
        public void run() {
            throw new RuntimeException(e);
        }
    }
}
