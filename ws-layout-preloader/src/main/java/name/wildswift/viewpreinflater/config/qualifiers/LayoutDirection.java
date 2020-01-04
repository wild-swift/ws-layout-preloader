package name.wildswift.viewpreinflater.config.qualifiers;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public enum LayoutDirection {
    LTR(Configuration.SCREENLAYOUT_LAYOUTDIR_LTR),
    RTL(Configuration.SCREENLAYOUT_LAYOUTDIR_RTL);
    public final int configurationValue;

    LayoutDirection(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
