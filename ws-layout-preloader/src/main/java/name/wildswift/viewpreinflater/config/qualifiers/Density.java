package name.wildswift.viewpreinflater.config.qualifiers;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.DisplayMetrics;

public enum Density {
    LOW(0, 120, DisplayMetrics.DENSITY_LOW),
    MEDIUM(121, 160, DisplayMetrics.DENSITY_MEDIUM),
    TV(213, 213, DisplayMetrics.DENSITY_TV),
    HIGH(161, 240, DisplayMetrics.DENSITY_HIGH),
    XHIGH(241, 320, DisplayMetrics.DENSITY_XHIGH),
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    XXHIGH(321, 480, DisplayMetrics.DENSITY_XXHIGH),
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    XXXHIGH(481, 640, DisplayMetrics.DENSITY_XXHIGH);

    final int minConfigurationValue;
    final int maxConfigurationValue;
    final int referenceValue;

    Density(int minConfigurationValue, int maxConfigurationValue, int referenceValue) {
        this.minConfigurationValue = minConfigurationValue;
        this.maxConfigurationValue = maxConfigurationValue;
        this.referenceValue = referenceValue;
    }
}
