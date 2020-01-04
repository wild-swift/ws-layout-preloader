package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum UiMode {
    DESK(Configuration.UI_MODE_TYPE_DESK),
    CAR(Configuration.UI_MODE_TYPE_CAR),
    TELEVISION(Configuration.UI_MODE_TYPE_TELEVISION),
    APPLIANCE(Configuration.UI_MODE_TYPE_APPLIANCE),
    WATCH(Configuration.UI_MODE_TYPE_WATCH),
    VR(Configuration.UI_MODE_TYPE_VR_HEADSET);

    final int configurationValue;

    UiMode(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
