package name.wildswift.viewpreinflater.config.qualifiers;

public enum Density {
    LOW(0, 120),
    MEDIUM(121, 160),
    TV(213, 213),
    HIGH(161, 240),
    XHIGH(241, 320),
    XXHIGH(321, 480),
    XXXHIGH(481, 640);

    final int minConfigurationValue;
    final int maxConfigurationValue;

    Density(int minConfigurationValue, int maxConfigurationValue) {
        this.minConfigurationValue = minConfigurationValue;
        this.maxConfigurationValue = maxConfigurationValue;
    }
}
