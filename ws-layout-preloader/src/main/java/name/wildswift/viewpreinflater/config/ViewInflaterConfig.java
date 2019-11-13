package name.wildswift.viewpreinflater.config;

import android.view.View;

import java.util.Map;

public class ViewInflaterConfig {
    private final Map<Class<? extends View>, Integer> cache;

    private ViewInflaterConfig(Map<Class<? extends View>, Integer> cache) {
        this.cache = cache;
    }

    /**
     * Non public api
     *
     * @return meta to cache views
     */
    public Map<Class<? extends View>, Integer> getCacheConfig() {
        return cache;
    }

    /**
     * Builder to create ViewInflaterConfig
     */
    public static class Builder {
        private final CacheConfigBuilder cacheConfigs = new CacheConfigBuilder(this);

        public CacheConfigBuilder cache() {
            return cacheConfigs;
        }

        public ViewInflaterConfig build() {
            return new ViewInflaterConfig(cacheConfigs.build());
        }
    }
}
