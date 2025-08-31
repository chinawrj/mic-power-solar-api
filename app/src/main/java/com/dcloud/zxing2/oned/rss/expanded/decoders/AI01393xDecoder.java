package com.dcloud.zxing2.oned.rss.expanded.decoders;

import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.common.BitArray;
import com.taobao.weex.el.parse.Operators;

/* loaded from: classes.dex */
final class AI01393xDecoder extends AI01decoder {
    private static final int FIRST_THREE_DIGITS_SIZE = 10;
    private static final int HEADER_SIZE = 8;
    private static final int LAST_DIGIT_SIZE = 2;

    AI01393xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    @Override // com.dcloud.zxing2.oned.rss.expanded.decoders.AbstractExpandedDecoder
    public String parseInformation() throws FormatException, NotFoundException {
        if (getInformation().getSize() < 48) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder sb = new StringBuilder();
        encodeCompressedGtin(sb, 8);
        int iExtractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(48, 2);
        sb.append("(393");
        sb.append(iExtractNumericValueFromBitArray);
        sb.append(Operators.BRACKET_END);
        int iExtractNumericValueFromBitArray2 = getGeneralDecoder().extractNumericValueFromBitArray(50, 10);
        if (iExtractNumericValueFromBitArray2 / 100 == 0) {
            sb.append('0');
        }
        if (iExtractNumericValueFromBitArray2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(iExtractNumericValueFromBitArray2);
        sb.append(getGeneralDecoder().decodeGeneralPurposeField(60, null).getNewString());
        return sb.toString();
    }
}
