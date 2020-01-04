package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum NavHidden {
    NO(Configuration.NAVIGATIONHIDDEN_NO),
    YES(Configuration.NAVIGATIONHIDDEN_YES);

    final int configurationValue;

    NavHidden(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
