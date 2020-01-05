package name.wildswift.viewpreinflater.config.qualifiers;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;

public enum UiMode {
    DESK(Configuration.UI_MODE_TYPE_DESK),
    CAR(Configuration.UI_MODE_TYPE_CAR),
    TELEVISION(Configuration.UI_MODE_TYPE_TELEVISION),
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    APPLIANCE(Configuration.UI_MODE_TYPE_APPLIANCE),
    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    WATCH(Configuration.UI_MODE_TYPE_WATCH),
    @TargetApi(Build.VERSION_CODES.O)
    VR(Configuration.UI_MODE_TYPE_VR_HEADSET);

    final int configurationValue;

    UiMode(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
