package dc.squareup.okhttp3.internal.cache;

import dc.squareup.okio.Sink;
import java.io.IOException;

/* loaded from: classes3.dex */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
