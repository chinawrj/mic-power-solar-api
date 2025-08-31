package uts.sdk.modules.DCloudUniNetwork;

import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0005\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001e\u0010\u0004\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u000f"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/OnProgressDownloadResult;", "Lio/dcloud/uts/UTSObject;", "progress", "", "totalBytesWritten", "totalBytesExpectedToWrite", "(Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;)V", "getProgress", "()Ljava/lang/Number;", "setProgress", "(Ljava/lang/Number;)V", "getTotalBytesExpectedToWrite", "setTotalBytesExpectedToWrite", "getTotalBytesWritten", "setTotalBytesWritten", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class OnProgressDownloadResult extends UTSObject {

    @JsonNotNull
    private Number progress;

    @JsonNotNull
    private Number totalBytesExpectedToWrite;

    @JsonNotNull
    private Number totalBytesWritten;

    public Number getProgress() {
        return this.progress;
    }

    public void setProgress(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.progress = number;
    }

    public Number getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void setTotalBytesWritten(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.totalBytesWritten = number;
    }

    public Number getTotalBytesExpectedToWrite() {
        return this.totalBytesExpectedToWrite;
    }

    public void setTotalBytesExpectedToWrite(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.totalBytesExpectedToWrite = number;
    }

    public OnProgressDownloadResult(Number progress, Number totalBytesWritten, Number totalBytesExpectedToWrite) {
        Intrinsics.checkNotNullParameter(progress, "progress");
        Intrinsics.checkNotNullParameter(totalBytesWritten, "totalBytesWritten");
        Intrinsics.checkNotNullParameter(totalBytesExpectedToWrite, "totalBytesExpectedToWrite");
        this.progress = progress;
        this.totalBytesWritten = totalBytesWritten;
        this.totalBytesExpectedToWrite = totalBytesExpectedToWrite;
    }
}
