package com.dcloud.zxing2.oned.rss.expanded.decoders;

import com.dcloud.zxing2.common.BitArray;

/* loaded from: classes.dex */
abstract class AI01weightDecoder extends AI01decoder {
    AI01weightDecoder(BitArray bitArray) {
        super(bitArray);
    }

    protected abstract void addWeightCode(StringBuilder sb, int i);

    protected abstract int checkWeight(int i);

    protected final void encodeCompressedWeight(StringBuilder sb, int i, int i2) {
        int iExtractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(i, i2);
        addWeightCode(sb, iExtractNumericValueFromBitArray);
        int iCheckWeight = checkWeight(iExtractNumericValueFromBitArray);
        int i3 = 100000;
        for (int i4 = 0; i4 < 5; i4++) {
            if (iCheckWeight / i3 == 0) {
                sb.append('0');
            }
            i3 /= 10;
        }
        sb.append(iCheckWeight);
    }
}
