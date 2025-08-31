package uts.sdk.modules.DCloudUniNetwork;

import com.facebook.imagepipeline.producers.DecodeProducer;
import io.dcloud.uts.NumberKt;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/CountingSink;", "Lokio/ForwardingSink;", "sink", "Lokio/Sink;", "total", "", "listener", "Luts/sdk/modules/DCloudUniNetwork/UploadProgressListener;", "(Lokio/Sink;Ljava/lang/Number;Luts/sdk/modules/DCloudUniNetwork/UploadProgressListener;)V", "bytesWritten", "write", "", "source", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class CountingSink extends ForwardingSink {
    private Number bytesWritten;
    private UploadProgressListener listener;
    private Number total;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CountingSink(Sink sink, Number total, UploadProgressListener listener) {
        super(sink);
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(total, "total");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.bytesWritten = (Number) 0;
        this.listener = listener;
        this.total = total;
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer source, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        super.write(source, byteCount);
        Number numberPlus = NumberKt.plus(this.bytesWritten, Long.valueOf(byteCount));
        this.bytesWritten = numberPlus;
        UploadProgressListener uploadProgressListener = this.listener;
        if (uploadProgressListener != null) {
            uploadProgressListener.onProgress(numberPlus, this.total);
        }
    }
}
