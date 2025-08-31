package uts.sdk.modules.DCloudUniNetwork;

import java.io.ByteArrayOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0004\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0011\u001a\u00020\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/SharedStreamBuffer;", "", "()V", "buffer", "Ljava/io/ByteArrayOutputStream;", "getBuffer", "()Ljava/io/ByteArrayOutputStream;", "setBuffer", "(Ljava/io/ByteArrayOutputStream;)V", "hasNewData", "", "getHasNewData", "()Z", "setHasNewData", "(Z)V", "isStreamEnded", "setStreamEnded", "totalBytesRead", "", "getTotalBytesRead", "()Ljava/lang/Number;", "setTotalBytesRead", "(Ljava/lang/Number;)V", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class SharedStreamBuffer {
    private boolean hasNewData;
    private boolean isStreamEnded;
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private Number totalBytesRead = (Number) 0;

    public ByteArrayOutputStream getBuffer() {
        return this.buffer;
    }

    public void setBuffer(ByteArrayOutputStream byteArrayOutputStream) {
        Intrinsics.checkNotNullParameter(byteArrayOutputStream, "<set-?>");
        this.buffer = byteArrayOutputStream;
    }

    /* renamed from: isStreamEnded, reason: from getter */
    public boolean getIsStreamEnded() {
        return this.isStreamEnded;
    }

    public void setStreamEnded(boolean z) {
        this.isStreamEnded = z;
    }

    public Number getTotalBytesRead() {
        return this.totalBytesRead;
    }

    public void setTotalBytesRead(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.totalBytesRead = number;
    }

    public boolean getHasNewData() {
        return this.hasNewData;
    }

    public void setHasNewData(boolean z) {
        this.hasNewData = z;
    }
}
