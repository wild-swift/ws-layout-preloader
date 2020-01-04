package name.wildswift.viewpreinflater.config;

import android.view.View;

import name.wildswift.viewpreinflater.config.qualifiers.QualifiersSpec;

public class CacheConfigElement {
    private final Class<? extends View> clazz;
    private final QualifiersSpec qualifiersSpec;
    private final int count;

    CacheConfigElement(Class<? extends View> clazz, QualifiersSpec qualifiersSpec, int count) {
        this.clazz = clazz;
        this.qualifiersSpec = qualifiersSpec;
        this.count = count;
    }

    public Class<? extends View> getClazz() {
        return clazz;
    }

    public QualifiersSpec getQualifiersSpec() {
        return qualifiersSpec;
    }

    public int getCount() {
        return count;
    }
}
