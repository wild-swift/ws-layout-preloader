package name.wildswift.viewpreinflater.config;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class CacheConfigBuilder implements ViewInflaterConfigSectionBuilder {
    private ViewInflaterConfig.Builder owner;
    private Map<Class<? extends View>, Integer> value = new HashMap<>();

    CacheConfigBuilder(ViewInflaterConfig.Builder owner) {
        this.owner = owner;
    }

    public CacheConfigBuilder view(Class<? extends View> view) {
        Integer old = value.get(view);
        if (old == null) old = 0;
        old++;
        value.put(view, old);
        return this;
    }

    public CacheConfigBuilder viewTimes(Class<? extends View> view, int count) {
        Integer old = value.get(view);
        if (old == null) old = 0;
        old+= count;
        value.put(view, old);
        return this;
    }

    @Override
    public ViewInflaterConfig.Builder and() {
        return owner;
    }

    Map<Class<? extends View>, Integer> build() {
        return value;
    }
}
