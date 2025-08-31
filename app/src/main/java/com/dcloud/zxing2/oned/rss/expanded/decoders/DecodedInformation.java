package com.dcloud.zxing2.oned.rss.expanded.decoders;

/* loaded from: classes.dex */
final class DecodedInformation extends DecodedObject {
    private final String newString;
    private final boolean remaining;
    private final int remainingValue;

    DecodedInformation(int i, String str) {
        super(i);
        this.newString = str;
        this.remaining = false;
        this.remainingValue = 0;
    }

    String getNewString() {
        return this.newString;
    }

    int getRemainingValue() {
        return this.remainingValue;
    }

    boolean isRemaining() {
        return this.remaining;
    }

    DecodedInformation(int i, String str, int i2) {
        super(i);
        this.remaining = true;
        this.remainingValue = i2;
        this.newString = str;
    }
}
