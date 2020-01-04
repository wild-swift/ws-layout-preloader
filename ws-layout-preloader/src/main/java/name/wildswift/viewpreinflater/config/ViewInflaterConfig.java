package name.wildswift.viewpreinflater.config;

import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ViewInflaterConfig {
    private final List<CacheConfigElement> cacheRules;

    private ViewInflaterConfig(List<CacheConfigElement> cacheRules) {
        this.cacheRules = cacheRules;
    }

    /**
     * Non public api
     *
     * @return meta to cache views
     */
    public List<CacheConfigElement> getCacheConfig() {
        return cacheRules;
    }


    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to create ViewInflaterConfig
     */
    public static class Builder {
        private List<ViewCacheBuilder> cacheSubBuilders = new ArrayList<>();

        public ViewCacheBuilder view(Class<? extends View> view) {
            ViewCacheBuilder viewCacheBuilder = new ViewCacheBuilder(view, this);
            cacheSubBuilders.add(viewCacheBuilder);
            return viewCacheBuilder;
        }

        public ViewInflaterConfig build() {
            ArrayList<CacheConfigElement> cacheRules = new ArrayList<>();
            for (ViewCacheBuilder builder : cacheSubBuilders) {
                cacheRules.add(builder.build());
            }
            return new ViewInflaterConfig(cacheRules);
        }
    }
}
