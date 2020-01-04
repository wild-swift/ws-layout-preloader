package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum NightMode {
    NO(Configuration.UI_MODE_NIGHT_NO),
    YES(Configuration.UI_MODE_NIGHT_YES);
    final int configurationValue;

    NightMode(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
