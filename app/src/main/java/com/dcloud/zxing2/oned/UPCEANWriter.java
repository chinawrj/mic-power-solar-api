package com.dcloud.zxing2.oned;

/* loaded from: classes.dex */
public abstract class UPCEANWriter extends OneDimensionalCodeWriter {
    @Override // com.dcloud.zxing2.oned.OneDimensionalCodeWriter
    public int getDefaultMargin() {
        return UPCEANReader.START_END_PATTERN.length;
    }
}
