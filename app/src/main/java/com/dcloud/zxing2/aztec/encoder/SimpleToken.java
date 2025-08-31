package com.dcloud.zxing2.aztec.encoder;

import com.dcloud.zxing2.common.BitArray;
import com.taobao.weex.el.parse.Operators;
import kotlin.text.Typography;

/* loaded from: classes.dex */
final class SimpleToken extends Token {
    private final short bitCount;
    private final short value;

    SimpleToken(Token token, int i, int i2) {
        super(token);
        this.value = (short) i;
        this.bitCount = (short) i2;
    }

    @Override // com.dcloud.zxing2.aztec.encoder.Token
    void appendTo(BitArray bitArray, byte[] bArr) {
        bitArray.appendBits(this.value, this.bitCount);
    }

    public String toString() {
        short s = this.value;
        int i = 1 << this.bitCount;
        return Operators.L + Integer.toBinaryString((s & (i - 1)) | i | (1 << this.bitCount)).substring(1) + Typography.greater;
    }
}
