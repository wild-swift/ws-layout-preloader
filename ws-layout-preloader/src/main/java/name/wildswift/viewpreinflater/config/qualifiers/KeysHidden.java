package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum KeysHidden {
    NO(Configuration.KEYBOARDHIDDEN_NO),
    YES(Configuration.KEYBOARDHIDDEN_YES),
    /* Configuration.KEYBOARDHIDDEN_SOFT = 3 */
    SOFT(3);
    final int configurationValue;

    KeysHidden(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
