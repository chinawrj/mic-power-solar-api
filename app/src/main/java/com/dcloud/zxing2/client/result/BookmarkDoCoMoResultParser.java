package com.dcloud.zxing2.client.result;

import com.dcloud.zxing2.Result;

/* loaded from: classes.dex */
public final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser {
    @Override // com.dcloud.zxing2.client.result.ResultParser
    public URIParsedResult parse(Result result) {
        String text = result.getText();
        if (!text.startsWith("MEBKM:")) {
            return null;
        }
        String strMatchSingleDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("TITLE:", text, true);
        String[] strArrMatchDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("URL:", text, true);
        if (strArrMatchDoCoMoPrefixedField == null) {
            return null;
        }
        String str = strArrMatchDoCoMoPrefixedField[0];
        if (URIResultParser.isBasicallyValidURI(str)) {
            return new URIParsedResult(str, strMatchSingleDoCoMoPrefixedField);
        }
        return null;
    }
}
