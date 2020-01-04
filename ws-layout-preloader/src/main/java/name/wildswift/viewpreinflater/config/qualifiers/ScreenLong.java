package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum ScreenLong {
    NO(Configuration.SCREENLAYOUT_LONG_NO),
    YES(Configuration.SCREENLAYOUT_LONG_YES);

    final int configurationValue;

    ScreenLong(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
