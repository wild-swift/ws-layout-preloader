package name.wildswift.viewpreinflater.config.qualifiers;

import android.content.res.Configuration;

public enum Keyboard {
    NOKEYS(Configuration.KEYBOARD_NOKEYS),
    QWERTY(Configuration.KEYBOARD_QWERTY),
    K12KEY(Configuration.KEYBOARD_12KEY);

    final int configurationValue;

    Keyboard(int configurationValue) {
        this.configurationValue = configurationValue;
    }
}
