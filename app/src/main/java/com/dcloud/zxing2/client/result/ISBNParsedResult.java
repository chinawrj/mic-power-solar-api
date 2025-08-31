package com.dcloud.zxing2.client.result;

/* loaded from: classes.dex */
public final class ISBNParsedResult extends ParsedResult {
    private final String isbn;

    ISBNParsedResult(String str) {
        super(ParsedResultType.ISBN);
        this.isbn = str;
    }

    @Override // com.dcloud.zxing2.client.result.ParsedResult
    public String getDisplayResult() {
        return this.isbn;
    }

    public String getISBN() {
        return this.isbn;
    }
}
