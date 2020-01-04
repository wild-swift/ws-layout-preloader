package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum Navigation {
    NONAV(Configuration.NAVIGATION_NONAV),
    DPAD(Configuration.NAVIGATION_DPAD),
    TRACKBALL(Configuration.NAVIGATION_TRACKBALL),
    WHEEL(Configuration.NAVIGATION_WHEEL);

    final int configurationValue;

    Navigation(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
