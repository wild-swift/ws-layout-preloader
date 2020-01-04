package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum Orientation {
    PORTRAIT(Configuration.ORIENTATION_PORTRAIT),
    LANDSCAPE(Configuration.ORIENTATION_LANDSCAPE),
    @Deprecated
    SQUARE(Configuration.ORIENTATION_SQUARE);

    final int configurationValue;

    Orientation(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
