package name.wildswift.viewpreinflater;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import name.wildswift.viewpreinflater.config.ViewInflaterConfig;

public class ViewInflater {
    private Executor executor = Executors.newFixedThreadPool(3);
    private boolean initialized = false;
    private boolean initStarted = false;

    private Map<Class<? extends View>, View[]> cache = new HashMap<>();

    private final Object internalMutex = new Object();
    private int initCooldown = 0;

    private final Context context;

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public ViewInflater(Context context, ViewInflaterConfig config) {
        this.context = context;
        Map<Class<? extends View>, Integer> cacheConfig = config.getCacheConfig();
        Set<Map.Entry<Class<? extends View>, Integer>> entries = cacheConfig.entrySet();
        for (Map.Entry<Class<? extends View>, Integer> configEntry : entries) {
            cache.put(configEntry.getKey(), new View[configEntry.getValue()]);
            initCooldown += configEntry.getValue();
        }
    }

    public void initialize(ViewInflaterInitCallback callback) {
        if (initStarted) return;
        if (Looper.myLooper() == null) throw new IllegalStateException();
        synchronized (internalMutex) {
            if (initStarted) return;
            Handler handler = new Handler();
            Set<Map.Entry<Class<? extends View>, View[]>> entries = cache.entrySet();
            for (Map.Entry<Class<? extends View>, View[]> entry : entries) {
                View[] value = entry.getValue();
                for (int i = 0; i < value.length; i++)
                executor.execute(new InitTask(entry.getKey(), i, value, handler, callback));
            }
            initStarted = true;
        }
    }

    public <T extends View> T getView(Class<T> clazz) {
        if (!initialized) throw new IllegalStateException();
        synchronized (internalMutex) {
            View[] views = cache.get(clazz);
            if (views == null) return null;
            while (true) {
                for (int i = 0; i < views.length; i++) {
                    View result = views[i];
                    if (result != null) {
                        views[i] = null;
                        executor.execute(new LoadTask(clazz, i, views));
                        return (T) result;
                    }
                }
                try {
                    internalMutex.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    private class InitTask implements Runnable {
        private final Class<? extends View> viewClass;
        private final int arrayIndex;
        private final View[] views;
        private final Handler handler;
        private final ViewInflaterInitCallback callback;

        private InitTask(Class<? extends View> viewClass, int arrayIndex, View[] views, Handler handler, ViewInflaterInitCallback callback) {
            this.viewClass = viewClass;
            this.arrayIndex = arrayIndex;
            this.views = views;
            this.handler = handler;
            this.callback = callback;
        }

        @Override
        public void run() {
            try {
                Constructor<? extends View> constructor = viewClass.getConstructor(Context.class);
                View view = constructor.newInstance(context);
                synchronized (internalMutex) {
                    views[arrayIndex] = view;
                    initCooldown--;
                    if (initCooldown == 0) {
                        initialized = true;
                        if (callback != null) {
                            handler.post(new PerformOnInit(callback));
                        }
                    }
                }
            } catch (NoSuchMethodException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            } catch (IllegalAccessException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            } catch (InstantiationException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            } catch (InvocationTargetException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            }
        }
    }

    private class LoadTask implements Runnable {
        private final Class<? extends View> viewClass;
        private final int arrayIndex;
        private final View[] views;

        private LoadTask(Class<? extends View> viewClass, int arrayIndex, View[] views) {
            this.viewClass = viewClass;
            this.arrayIndex = arrayIndex;
            this.views = views;
        }

        @Override
        public void run() {
            try {
                Constructor<? extends View> constructor = viewClass.getConstructor(Context.class);
                View view = constructor.newInstance(context);
                synchronized (internalMutex) {
                    views[arrayIndex] = view;
                    internalMutex.notifyAll();
                }
            } catch (NoSuchMethodException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            } catch (IllegalAccessException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            } catch (InstantiationException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            } catch (InvocationTargetException e) {
                mainHandler.post(new ThrowExceptionRunnable(e));
            }
        }
    }

    private class PerformOnInit implements Runnable {
        private final ViewInflaterInitCallback callback;

        private PerformOnInit(ViewInflaterInitCallback callback) {
            this.callback = callback;
        }

        @Override
        public void run() {
            callback.onInit(ViewInflater.this);
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
