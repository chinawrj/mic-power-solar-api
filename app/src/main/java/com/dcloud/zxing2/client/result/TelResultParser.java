package com.dcloud.zxing2.client.result;

import com.dcloud.zxing2.Result;

/* loaded from: classes.dex */
public final class TelResultParser extends ResultParser {
    @Override // com.dcloud.zxing2.client.result.ResultParser
    public TelParsedResult parse(Result result) {
        String str;
        String massagedText = ResultParser.getMassagedText(result);
        if (!massagedText.startsWith("tel:") && !massagedText.startsWith("TEL:")) {
            return null;
        }
        if (massagedText.startsWith("TEL:")) {
            str = "tel:" + massagedText.substring(4);
        } else {
            str = massagedText;
        }
        int iIndexOf = massagedText.indexOf(63, 4);
        return new TelParsedResult(iIndexOf < 0 ? massagedText.substring(4) : massagedText.substring(4, iIndexOf), str, null);
    }
}
