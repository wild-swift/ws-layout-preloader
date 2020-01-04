package name.wildswift.viewpreinflater;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import name.wildswift.viewpreinflater.config.CacheConfigElement;
import name.wildswift.viewpreinflater.config.ViewInflaterConfig;

@SuppressWarnings({"FieldCanBeLocal", "unchecked"})
public class ViewInflater {
    private ExecutorService executor = Executors.newFixedThreadPool(3);
    private boolean initialized = false;
    private boolean initStarted = false;

    private Map<Class<? extends View>, List<ViewCacheHolder>> cache = new HashMap<>();

    private final Object internalMutex = new Object();
    private AtomicInteger initCooldown = new AtomicInteger();

    private final Context context;

    public ViewInflater(Context context, ViewInflaterConfig config) {
        this.context = context;
        List<CacheConfigElement> cacheConfig = config.getCacheConfig();
        initCooldown.set(cacheConfig.size());
        for (CacheConfigElement element : cacheConfig) {
            List<ViewCacheHolder> viewCacheHolders = cache.get(element.getClazz());
            if (viewCacheHolders == null) {
                viewCacheHolders = new ArrayList<>();
                cache.put(element.getClazz(), viewCacheHolders);
            }
            viewCacheHolders.add(new ViewCacheHolder(context, element.getClazz(), element.getQualifiersSpec(), element.getCount(), executor));
        }

        Set<Class<? extends View>> cacheKeys = cache.keySet();
        for (Class<? extends View> key : cacheKeys) {
            List<ViewCacheHolder> list = new ArrayList<>(cache.get(key));
            Collections.sort(list);
            cache.put(key, Collections.unmodifiableList(list));
        }
    }

    public void initialize(final ViewInflaterInitCallback callback) {
        if (initStarted | initialized) return;
        if (Looper.myLooper() == null) throw new IllegalStateException();
        synchronized (internalMutex) {
            if (initStarted) return;

            final Handler handler = new Handler();
            Set<Map.Entry<Class<? extends View>, List<ViewCacheHolder>>> entries = cache.entrySet();

            for (Map.Entry<Class<? extends View>, List<ViewCacheHolder>> entry : entries) {
                List<ViewCacheHolder> value = entry.getValue();
                for (int i = 0; i < value.size(); i++) {
                    ViewCacheHolder viewCacheHolder = value.get(i);
                    viewCacheHolder.initialize(new LocalCacheHolderCallback(handler, callback));
                }
            }
            initStarted = true;
        }
    }

    public <T extends View> T getView(Class<T> clazz) {
        if (!initialized) throw new IllegalStateException();
        List<ViewCacheHolder> viewCacheHolders = cache.get(clazz);
        if (viewCacheHolders == null) return null;
        for (ViewCacheHolder<T> viewCache : viewCacheHolders) {
            T view = viewCache.getView(context.getResources());
            if (view != null) {
                return view;
            }
        }
        return null;
    }


    private class LocalCacheHolderCallback implements CacheHolderCallback {
        private final Handler handler;
        private final ViewInflaterInitCallback callback;

        private LocalCacheHolderCallback(Handler handler, ViewInflaterInitCallback callback) {
            this.handler = handler;
            this.callback = callback;
        }

        @Override
        public void onInit() {
            if (initCooldown.decrementAndGet() == 0) {
                initStarted = false;
                initialized = true;
                handler.post(new PerformOnInit(callback));
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
}
