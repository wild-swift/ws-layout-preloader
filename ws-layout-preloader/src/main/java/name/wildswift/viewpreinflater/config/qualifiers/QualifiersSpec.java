package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;


@SuppressWarnings("ALL")
public class QualifiersSpec implements Comparable<QualifiersSpec> {
    private final Integer mcc;
    private final Integer mnc;
    private final Locale locale;
    private final LayoutDirection layoutDirection;
    private final Integer smallestScreenWidth;
    private final Integer screenHeight;
    private final Integer screenWidth;
    private final ScreenSize screenSize;
    private final ScreenLong screenLong;
    private final Orientation orientation;
    private final UiMode uiMode;
    private final NightMode nightMode;
    private final Density density;
    private final Touchscreen touchscreen;
    private final KeysHidden keysHidden;
    private final Keyboard keyboard;
    private final Navigation navigation;
    private final NavHidden navHidden;
    private final Integer sdkVersion;

    private QualifiersSpec(Integer mcc, Integer mnc, Locale locale, Orientation orientation, Touchscreen touchscreen, Density density, Keyboard keyboard, Navigation navigation, KeysHidden keysHidden, NavHidden navHidden, Integer screenWidth, Integer screenHeight, Integer smallestScreenWidth, Integer sdkVersion, ScreenSize screenSize, ScreenLong screenLong, LayoutDirection layoutDirection, UiMode uiMode, NightMode nightMode) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.locale = locale;
        this.orientation = orientation;
        this.touchscreen = touchscreen;
        this.density = density;
        this.keyboard = keyboard;
        this.navigation = navigation;
        this.keysHidden = keysHidden;
        this.navHidden = navHidden;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.smallestScreenWidth = smallestScreenWidth;
        this.sdkVersion = sdkVersion;
        this.screenSize = screenSize;
        this.screenLong = screenLong;
        this.layoutDirection = layoutDirection;
        this.uiMode = uiMode;
        this.nightMode = nightMode;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean accept(Resources res) {
        Configuration configuration = res.getConfiguration();
        if (this.mcc != null && configuration.mcc != this.mcc) return false;
        if (this.mnc != null && configuration.mnc != this.mnc) return false;
        if (this.locale != null) {
            // TODO need more complex compare for multilocale
            Locale configLocale = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocaleList locales = configuration.getLocales();
                if (locales.size() > 0)
                    configLocale = locales.get(0);
            } else {
                configLocale = configuration.locale;
            }
            if (configLocale != null) {
                if (this.locale.getLanguage() != null && this.locale.getLanguage().length() > 0 && (configLocale.getLanguage() == null || !configLocale.getLanguage().equals(this.locale.getLanguage()))) return false;
                if (this.locale.getCountry() != null && this.locale.getCountry().length() > 0 && (configLocale.getCountry() == null || !configLocale.getCountry().equals(this.locale.getCountry()))) return false;
                if (this.locale.getVariant() != null && this.locale.getVariant().length() > 0 && (configLocale.getVariant() == null || !configLocale.getVariant().equals(this.locale.getVariant()))) return false;
            }
        }
        if (this.orientation != null && configuration.orientation != this.orientation.configurationValue) return false;
        if (this.touchscreen != null && configuration.touchscreen != this.touchscreen.configurationValue) return false;

        // TODO need update density selector algorithm
        if (this.density != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (configuration.densityDpi < this.density.minConfigurationValue || configuration.densityDpi > this.density.maxConfigurationValue) return false;
            } else {
                if (res.getDisplayMetrics().densityDpi < this.density.minConfigurationValue || res.getDisplayMetrics().densityDpi > this.density.maxConfigurationValue) return false;
            }
        if (this.keyboard != null && configuration.keyboard != this.keyboard.configurationValue) return false;
        if (this.navigation != null && configuration.navigation != this.navigation.configurationValue) return false;
        if (this.keysHidden != null && configuration.keyboardHidden != this.keysHidden.configurationValue) return false;
        if (this.navHidden != null && configuration.navigationHidden != this.navHidden.configurationValue) return false;
        if (this.smallestScreenWidth != null && configuration.smallestScreenWidthDp < this.screenWidth) return false;
        if (this.screenWidth != null && configuration.screenWidthDp < this.screenWidth) return false;
        if (this.screenHeight != null && configuration.screenHeightDp < this.screenHeight) return false;
        if (this.sdkVersion != null && Build.VERSION.SDK_INT < this.sdkVersion) return false;
        if (this.screenSize != null && (configuration.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) != this.screenSize.configurationValue) return false;
        if (this.screenLong != null && (configuration.screenLayout & Configuration.SCREENLAYOUT_LONG_MASK) != this.screenLong.configurationValue) return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && this.layoutDirection != null && (configuration.screenLayout & Configuration.SCREENLAYOUT_LAYOUTDIR_MASK) != this.layoutDirection.configurationValue) return false;
        if (this.uiMode != null && (configuration.uiMode & Configuration.UI_MODE_TYPE_MASK) != this.uiMode.configurationValue) return false;
        if (this.nightMode != null && (configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK) != this.nightMode.configurationValue) return false;
        return true;
    }

    @Override
    public int compareTo(QualifiersSpec that) {
        Integer result;

        result = compareValues(mcc, that.mcc); if (result != null) return result;
        result = compareValues(mnc, that.mnc); if (result != null) return result;
        result = compareLocales(locale, that.locale);  if (result != null) return result;
        result = compareValues(layoutDirection, that.layoutDirection); if (result != null) return result;
        result = compareValues(smallestScreenWidth, that.smallestScreenWidth); if (result != null) return result;
        result = compareValues(screenHeight, that.screenHeight); if (result != null) return result;
        result = compareValues(screenWidth, that.screenWidth); if (result != null) return result;
        result = compareValues(screenSize, that.screenSize); if (result != null) return result;
        result = compareValues(screenLong, that.screenLong); if (result != null) return result;
        result = compareValues(orientation, that.orientation); if (result != null) return result;
        result = compareValues(uiMode, that.uiMode); if (result != null) return result;
        result = compareValues(nightMode, that.nightMode); if (result != null) return result;
        result = compareValues(density, that.density); if (result != null) return result;
        result = compareValues(touchscreen, that.touchscreen); if (result != null) return result;
        result = compareValues(keysHidden, that.keysHidden); if (result != null) return result;
        result = compareValues(keyboard, that.keyboard); if (result != null) return result;
        result = compareValues(navigation, that.navigation); if (result != null) return result;
        result = compareValues(navHidden, that.navHidden); if (result != null) return result;
        result = compareValues(sdkVersion, that.sdkVersion); if (result != null) return result;


        return 0;
    }

    @SuppressWarnings("ConstantConditions")
    private Integer compareLocales(Locale thisValue, Locale thatValue) {
        Integer result;
        String thisLanguage = thisValue != null && thisValue.getLanguage() != null && thisValue.getLanguage().length() > 0 ? thisValue.getLanguage() : null;
        String thisCountry = thisValue != null && thisValue.getCountry() != null && thisValue.getCountry().length() > 0 ? thisValue.getCountry() : null;
        String thisVariant = thisValue != null && thisValue.getVariant() != null && thisValue.getVariant().length() > 0 ? thisValue.getVariant() : null;

        String thatLanguage = thatValue != null && thatValue.getLanguage() != null && thatValue.getLanguage().length() > 0 ? thatValue.getLanguage() : null;
        String thatCountry = thatValue != null && thatValue.getCountry() != null && thatValue.getCountry().length() > 0 ? thatValue.getCountry() : null;
        String thatVariant = thatValue != null && thatValue.getVariant() != null && thatValue.getVariant().length() > 0 ? thatValue.getVariant() : null;

        result = compareValues(thisLanguage, thatLanguage); if (result != null) return result;
        result = compareValues(thisCountry, thatCountry); if (result != null) return result;
        result = compareValues(thisVariant, thatVariant); if (result != null) return result;
        return null;
    }

    private <T extends Comparable<T>> Integer compareValues(T thisValue, T thatValue) {
        if (thisValue != null && thatValue == null) return -1;
        if (thisValue != null && !thisValue.equals(thatValue)) return thisValue.compareTo(thatValue);
        if (thisValue == null && thatValue != null) return 1;
        return null;
    }

    @SuppressWarnings("EqualsReplaceableByObjectsCall")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QualifiersSpec that = (QualifiersSpec) o;

        if (mcc != null ? !mcc.equals(that.mcc) : that.mcc != null) return false;
        if (mnc != null ? !mnc.equals(that.mnc) : that.mnc != null) return false;
        if (locale != null ? !locale.equals(that.locale) : that.locale != null) return false;
        if (orientation != that.orientation) return false;
        if (touchscreen != that.touchscreen) return false;
        if (density != that.density) return false;
        if (keyboard != that.keyboard) return false;
        if (navigation != that.navigation) return false;
        if (keysHidden != that.keysHidden) return false;
        if (navHidden != that.navHidden) return false;
        if (smallestScreenWidth != null ? !smallestScreenWidth.equals(that.smallestScreenWidth) : that.smallestScreenWidth != null) return false;
        if (screenWidth != null ? !screenWidth.equals(that.screenWidth) : that.screenWidth != null) return false;
        if (screenHeight != null ? !screenHeight.equals(that.screenHeight) : that.screenHeight != null) return false;
        if (sdkVersion != null ? !sdkVersion.equals(that.sdkVersion) : that.sdkVersion != null) return false;
        if (screenSize != that.screenSize) return false;
        if (screenLong != that.screenLong) return false;
        if (layoutDirection != that.layoutDirection) return false;
        if (uiMode != that.uiMode) return false;
        return nightMode == that.nightMode;
    }

    @Override
    public int hashCode() {
        int result = mcc != null ? mcc.hashCode() : 0;
        result = 31 * result + (mnc != null ? mnc.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        result = 31 * result + (touchscreen != null ? touchscreen.hashCode() : 0);
        result = 31 * result + (density != null ? density.hashCode() : 0);
        result = 31 * result + (keyboard != null ? keyboard.hashCode() : 0);
        result = 31 * result + (navigation != null ? navigation.hashCode() : 0);
        result = 31 * result + (keysHidden != null ? keysHidden.hashCode() : 0);
        result = 31 * result + (navHidden != null ? navHidden.hashCode() : 0);
        result = 31 * result + (smallestScreenWidth != null ? smallestScreenWidth.hashCode() : 0);
        result = 31 * result + (screenWidth != null ? screenWidth.hashCode() : 0);
        result = 31 * result + (screenHeight != null ? screenHeight.hashCode() : 0);
        result = 31 * result + (sdkVersion != null ? sdkVersion.hashCode() : 0);
        result = 31 * result + (screenSize != null ? screenSize.hashCode() : 0);
        result = 31 * result + (screenLong != null ? screenLong.hashCode() : 0);
        result = 31 * result + (layoutDirection != null ? layoutDirection.hashCode() : 0);
        result = 31 * result + (uiMode != null ? uiMode.hashCode() : 0);
        result = 31 * result + (nightMode != null ? nightMode.hashCode() : 0);
        return result;
    }

    public Context convertContext(final Context context) {
        final Configuration configuration = new Configuration(context.getResources().getConfiguration());

        if (this.mcc != null) configuration.mcc = this.mcc;
        if (this.mnc != null) configuration.mnc = this.mnc;
        if (this.orientation != null) configuration.orientation = this.orientation.configurationValue;
        if (this.touchscreen != null)  configuration.touchscreen = this.touchscreen.configurationValue;
        if (this.keyboard != null) configuration.keyboard = this.keyboard.configurationValue;
        if (this.navigation != null) configuration.navigation = this.navigation.configurationValue;
        if (this.keysHidden != null) configuration.keyboardHidden = this.keysHidden.configurationValue;
        if (this.navHidden != null) configuration.navigationHidden = this.navHidden.configurationValue;
        if (this.screenSize != null) configuration.screenLayout = (configuration.screenLayout & ~Configuration.SCREENLAYOUT_SIZE_MASK) | this.screenSize.configurationValue;
        if (this.screenLong != null) configuration.screenLayout = (configuration.screenLayout & ~Configuration.SCREENLAYOUT_LONG_MASK) | this.screenLong.configurationValue;
        if (this.uiMode != null) configuration.uiMode = (configuration.uiMode & ~Configuration.UI_MODE_TYPE_MASK) | this.uiMode.configurationValue;
        if (this.nightMode != null) configuration.uiMode = (configuration.uiMode & ~Configuration.UI_MODE_NIGHT_MASK) | this.nightMode.configurationValue;
        if (this.smallestScreenWidth != null) configuration.smallestScreenWidthDp = this.smallestScreenWidth + 1;
        if (this.screenWidth != null) configuration.screenWidthDp = this.screenWidth + 1;
        if (this.screenHeight != null) configuration.screenHeightDp = this.screenHeight + 1;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (this.locale != null) configuration.setLocale(this.locale);
            if (this.density != null) {
                configuration.densityDpi = this.density.referenceValue;
            }
            if (this.layoutDirection != null) configuration.screenLayout = (configuration.screenLayout & ~Configuration.SCREENLAYOUT_LAYOUTDIR_MASK) | this.layoutDirection.configurationValue;

            return context.createConfigurationContext(configuration);
        } else {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setTo(context.getResources().getDisplayMetrics());

            if (this.locale != null) configuration.locale = this.locale;
            if (this.density != null) {
                displayMetrics.densityDpi = this.density.referenceValue;
                displayMetrics.xdpi = this.density.referenceValue;
                displayMetrics.ydpi = this.density.referenceValue;
                displayMetrics.density = this.density.referenceValue / 160f;
                displayMetrics.scaledDensity = this.density.referenceValue * (configuration.fontScale != 0 ? configuration.fontScale : 1.0f);
            }

            return new ContextWrapper(context) {
                private final Resources resources;
                {
                    AssetManager assetManager;
                    try {
                        Constructor<AssetManager> constructor = AssetManager.class.getConstructor();
                        constructor.setAccessible(true);
                        Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
                        addAssetPath.setAccessible(true);

                        assetManager = constructor.newInstance();
                        addAssetPath.invoke(assetManager, context.getApplicationInfo().sourceDir);

                    } catch (Exception e) {
                        assetManager = super.getResources().getAssets();
                    }
                    resources = new Resources(assetManager, displayMetrics, configuration);
                }

                @Override
                public AssetManager getAssets() {
                    return resources.getAssets();
                }

                @Override
                public Resources getResources() {
                    return resources;
                }
            };
        }
    }

    public static class Builder {
        private Integer mcc = null;
        private Integer mnc = null;
        private Locale locale = null;
        private Orientation orientation = null;
        private Touchscreen touchscreen = null;
        private Density density = null;
        private Keyboard keyboard = null;
        private Navigation navigation = null;
        private KeysHidden keysHidden = null;
        private NavHidden navHidden = null;
        private Integer smallestScreenWidth = null;
        private Integer screenWidth = null;
        private Integer screenHeight = null;
        private Integer sdkVersion = null;
        private ScreenSize screenSize = null;
        private ScreenLong screenLong = null;
        private LayoutDirection layoutDirection = null;
        private UiMode uiMode = null;
        private NightMode nightMode = null;

        public Builder() {
        }

        public Builder mcc(Integer mcc) {
            this.mcc = mcc;
            return this;
        }

        public Builder mnc(Integer mnc) {
            this.mnc = mnc;
            return this;
        }

        public Builder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder orientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder touchscreen(Touchscreen touchscreen) {
            this.touchscreen = touchscreen;
            return this;
        }

        public Builder density(Density density) {
            this.density = density;
            return this;
        }

        public Builder keyboard(Keyboard keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder navigation(Navigation navigation) {
            this.navigation = navigation;
            return this;
        }

        public Builder keysHidden(KeysHidden keysHidden) {
            this.keysHidden = keysHidden;
            return this;
        }

        public Builder navHidden(NavHidden navHidden) {
            this.navHidden = navHidden;
            return this;
        }

        public Builder smallestScreenWidth(Integer smallestScreenWidth) {
            this.smallestScreenWidth = smallestScreenWidth;
            return this;
        }

        public Builder screenWidth(Integer screenWidth) {
            this.screenWidth = screenWidth;
            return this;
        }

        public Builder screenHeight(Integer screenHeight) {
            this.screenHeight = screenHeight;
            return this;
        }

        public Builder sdkVersion(Integer sdkVersion) {
            this.sdkVersion = sdkVersion;
            return this;
        }

        public Builder screenSize(ScreenSize screenSize) {
            this.screenSize = screenSize;
            return this;
        }

        public Builder screenLong(ScreenLong screenLong) {
            this.screenLong = screenLong;
            return this;
        }

        public Builder layoutDirection(LayoutDirection layoutDirection) {
            this.layoutDirection = layoutDirection;
            return this;
        }

        public Builder uiMode(UiMode uiMode) {
            this.uiMode = uiMode;
            return this;
        }

        public Builder nightMode(NightMode nightMode) {
            this.nightMode = nightMode;
            return this;
        }

        public QualifiersSpec build() {
            return new QualifiersSpec(
                    mcc, mnc, locale, orientation, touchscreen, density,
                    keyboard, navigation, keysHidden, navHidden, screenWidth, screenHeight,
                    smallestScreenWidth, sdkVersion, screenSize, screenLong, layoutDirection,
                    uiMode, nightMode
            );
        }
    }
}
