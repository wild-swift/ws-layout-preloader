package name.wildswift.viewpreinflater.config;

import android.view.View;

import java.util.Locale;

import name.wildswift.viewpreinflater.config.qualifiers.Density;
import name.wildswift.viewpreinflater.config.qualifiers.Keyboard;
import name.wildswift.viewpreinflater.config.qualifiers.KeysHidden;
import name.wildswift.viewpreinflater.config.qualifiers.LayoutDirection;
import name.wildswift.viewpreinflater.config.qualifiers.NavHidden;
import name.wildswift.viewpreinflater.config.qualifiers.Navigation;
import name.wildswift.viewpreinflater.config.qualifiers.NightMode;
import name.wildswift.viewpreinflater.config.qualifiers.Orientation;
import name.wildswift.viewpreinflater.config.qualifiers.QualifiersSpec;
import name.wildswift.viewpreinflater.config.qualifiers.ScreenLong;
import name.wildswift.viewpreinflater.config.qualifiers.ScreenSize;
import name.wildswift.viewpreinflater.config.qualifiers.Touchscreen;
import name.wildswift.viewpreinflater.config.qualifiers.UiMode;

public class ViewCacheBuilder {
    private final Class<? extends View> viewClass;
    private final ViewInflaterConfig.Builder builder;
    private QualifiersSpec.Builder qualifiers = new QualifiersSpec.Builder();
    private int count = 0;


    ViewCacheBuilder(Class<? extends View> viewClass, ViewInflaterConfig.Builder builder) {
        this.viewClass = viewClass;
        this.builder = builder;
    }

    public ViewCacheBuilder forMcc(Integer mcc) {
        qualifiers.mcc(mcc);
        return this;
    }

    public ViewCacheBuilder forMnc(Integer mnc) {
        qualifiers.mnc(mnc);
        return this;
    }

    public ViewCacheBuilder forLocale(Locale locale) {
        qualifiers.locale(locale);
        return this;
    }

    public ViewCacheBuilder forOrientation(Orientation orientation) {
        qualifiers.orientation(orientation);
        return this;
    }

    public ViewCacheBuilder forTouchscreen(Touchscreen touchscreen) {
        qualifiers.touchscreen(touchscreen);
        return this;
    }

    public ViewCacheBuilder forDensity(Density density) {
        qualifiers.density(density);
        return this;
    }

    public ViewCacheBuilder forKeyboard(Keyboard keyboard) {
        qualifiers.keyboard(keyboard);
        return this;
    }

    public ViewCacheBuilder forNavigation(Navigation navigation) {
        qualifiers.navigation(navigation);
        return this;
    }

    public ViewCacheBuilder forKeysHidden(KeysHidden keysHidden) {
        qualifiers.keysHidden(keysHidden);
        return this;
    }

    public ViewCacheBuilder forNavHidden(NavHidden navHidden) {
        qualifiers.navHidden(navHidden);
        return this;
    }

    public ViewCacheBuilder forSmallestScreenWidth(Integer smallestScreenWidth) {
        qualifiers.smallestScreenWidth(smallestScreenWidth);
        return this;
    }

    public ViewCacheBuilder forScreenWidth(Integer screenWidth) {
        qualifiers.screenWidth(screenWidth);
        return this;
    }

    public ViewCacheBuilder forScreenHeight(Integer screenHeight) {
        qualifiers.screenHeight(screenHeight);
        return this;
    }

    public ViewCacheBuilder forSdkVersion(Integer sdkVersion) {
        qualifiers.sdkVersion(sdkVersion);
        return this;
    }

    public ViewCacheBuilder forScreenSize(ScreenSize screenSize) {
        qualifiers.screenSize(screenSize);
        return this;
    }

    public ViewCacheBuilder forScreenLong(ScreenLong screenLong) {
        qualifiers.screenLong(screenLong);
        return this;
    }

    public ViewCacheBuilder forLayoutDirection(LayoutDirection layoutDirection) {
        qualifiers.layoutDirection(layoutDirection);
        return this;
    }

    public ViewCacheBuilder forUiMode(UiMode uiMode) {
        qualifiers.uiMode(uiMode);
        return this;
    }

    public ViewCacheBuilder forNightMode(NightMode nightMode) {
        qualifiers.nightMode(nightMode);
        return this;
    }

    public ViewInflaterConfig.Builder single() {
        return count(1);
    }

    public ViewInflaterConfig.Builder count(int count) {
        this.count = count;
        return builder;
    }

    CacheConfigElement build() {
        return new CacheConfigElement(
                viewClass, qualifiers.build(), count
        );
    }
    
}
