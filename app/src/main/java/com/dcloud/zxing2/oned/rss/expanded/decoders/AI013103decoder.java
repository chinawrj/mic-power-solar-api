package com.dcloud.zxing2.oned.rss.expanded.decoders;

import com.dcloud.zxing2.common.BitArray;

/* loaded from: classes.dex */
final class AI013103decoder extends AI013x0xDecoder {
    AI013103decoder(BitArray bitArray) {
        super(bitArray);
    }

    @Override // com.dcloud.zxing2.oned.rss.expanded.decoders.AI01weightDecoder
    protected void addWeightCode(StringBuilder sb, int i) {
        sb.append("(3103)");
    }

    @Override // com.dcloud.zxing2.oned.rss.expanded.decoders.AI01weightDecoder
    protected int checkWeight(int i) {
        return i;
    }
}
