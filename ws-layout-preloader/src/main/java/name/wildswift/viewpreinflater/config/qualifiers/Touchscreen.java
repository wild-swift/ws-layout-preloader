package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum Touchscreen {
    NOTOUCH(Configuration.TOUCHSCREEN_NOTOUCH),
    STYLUS(Configuration.TOUCHSCREEN_STYLUS),
    FINGER(Configuration.TOUCHSCREEN_FINGER);

    final int configurationValue;

    Touchscreen(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
