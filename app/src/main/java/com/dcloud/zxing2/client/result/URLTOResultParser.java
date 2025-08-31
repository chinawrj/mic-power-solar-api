package com.dcloud.zxing2.client.result;

import com.dcloud.zxing2.Result;

/* loaded from: classes.dex */
public final class URLTOResultParser extends ResultParser {
    @Override // com.dcloud.zxing2.client.result.ResultParser
    public URIParsedResult parse(Result result) {
        int iIndexOf;
        String massagedText = ResultParser.getMassagedText(result);
        if ((massagedText.startsWith("urlto:") || massagedText.startsWith("URLTO:")) && (iIndexOf = massagedText.indexOf(58, 6)) >= 0) {
            return new URIParsedResult(massagedText.substring(iIndexOf + 1), iIndexOf > 6 ? massagedText.substring(6, iIndexOf) : null);
        }
        return null;
    }
}
