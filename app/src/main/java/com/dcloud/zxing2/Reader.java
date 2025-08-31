package com.dcloud.zxing2;

import java.util.Map;

/* loaded from: classes.dex */
public interface Reader {
    Result decode(BinaryBitmap binaryBitmap) throws ChecksumException, FormatException, NotFoundException;

    Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException;

    void reset();
}
