package dc.squareup.okhttp3.internal.http;

import dc.squareup.okhttp3.MediaType;
import dc.squareup.okhttp3.ResponseBody;
import dc.squareup.okio.BufferedSource;

/* loaded from: classes3.dex */
public final class RealResponseBody extends ResponseBody {
    private final long contentLength;
    private final String contentTypeString;
    private final BufferedSource source;

    public RealResponseBody(String str, long j, BufferedSource bufferedSource) {
        this.contentTypeString = str;
        this.contentLength = j;
        this.source = bufferedSource;
    }

    @Override // dc.squareup.okhttp3.ResponseBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override // dc.squareup.okhttp3.ResponseBody
    public MediaType contentType() {
        String str = this.contentTypeString;
        if (str != null) {
            return MediaType.parse(str);
        }
        return null;
    }

    @Override // dc.squareup.okhttp3.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}
