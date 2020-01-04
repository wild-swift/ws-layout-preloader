package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum ScreenSize {
    SMALL(Configuration.SCREENLAYOUT_SIZE_SMALL),
    NORMAL(Configuration.SCREENLAYOUT_SIZE_NORMAL),
    LARGE(Configuration.SCREENLAYOUT_SIZE_LARGE),
    XLARGE(Configuration.SCREENLAYOUT_SIZE_XLARGE);

    final int configurationValue;

    ScreenSize(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
