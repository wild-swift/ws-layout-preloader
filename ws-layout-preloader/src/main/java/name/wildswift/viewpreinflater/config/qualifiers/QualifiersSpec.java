package name.wildswift.viewpreinflater.config.qualifiers;

public class QualifiersSpec {
    private final Integer mcc;
    private final Integer mnc;
    private final String language;
    private final String country;
    private final Orientation orientation;
    private final Touchscreen touchscreen;
    private final Density density;
    private final Keyboard keyboard;
    private final Navigation navigation;
    private final KeysHidden keysHidden;
    private final NavHidden navHidden;
    private final Integer screenWidth;
    private final Integer screenHeight;
    private final Integer sdkVersion;
    private final ScreenSize screenSize;
    private final ScreenLong screenLong;
    private final LayoutDirection layoutDirection;
    private final UiMode uiMode;
    private final NightMode nightMode;

    private QualifiersSpec(Integer mcc, Integer mnc, String language, String country, Orientation orientation, Touchscreen touchscreen, Density density, Keyboard keyboard, Navigation navigation, KeysHidden keysHidden, NavHidden navHidden, Integer screenWidth, Integer screenHeight, Integer sdkVersion, ScreenSize screenSize, ScreenLong screenLong, LayoutDirection layoutDirection, UiMode uiMode, NightMode nightMode) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.language = language;
        this.country = country;
        this.orientation = orientation;
        this.touchscreen = touchscreen;
        this.density = density;
        this.keyboard = keyboard;
        this.navigation = navigation;
        this.keysHidden = keysHidden;
        this.navHidden = navHidden;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.sdkVersion = sdkVersion;
        this.screenSize = screenSize;
        this.screenLong = screenLong;
        this.layoutDirection = layoutDirection;
        this.uiMode = uiMode;
        this.nightMode = nightMode;
    }


    public static class Builder {
        private Integer mcc = null;
        private Integer mnc = null;
        private String language = null;
        private String country = null;
        private Orientation orientation = null;
        private Touchscreen touchscreen = null;
        private Density density = null;
        private Keyboard keyboard = null;
        private Navigation navigation = null;
        private KeysHidden keysHidden = null;
        private NavHidden navHidden = null;
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

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
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
                    mcc, mnc, language, country, orientation,
                    touchscreen, density, keyboard, navigation, keysHidden,
                    navHidden, screenWidth, screenHeight, sdkVersion, screenSize,
                    screenLong, layoutDirection, uiMode, nightMode
            );
        }
    }
}
