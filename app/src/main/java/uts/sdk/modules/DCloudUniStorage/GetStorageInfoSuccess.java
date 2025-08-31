package uts.sdk.modules.DCloudUniStorage;

import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0004\n\u0002\b\r\b\u0016\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bR\u001e\u0010\u0005\u001a\u00020\u00068\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0007\u001a\u00020\u00068\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u0013"}, d2 = {"Luts/sdk/modules/DCloudUniStorage/GetStorageInfoSuccess;", "Lio/dcloud/uts/UTSObject;", "keys", "Lio/dcloud/uts/UTSArray;", "", "currentSize", "", "limitSize", "(Lio/dcloud/uts/UTSArray;Ljava/lang/Number;Ljava/lang/Number;)V", "getCurrentSize", "()Ljava/lang/Number;", "setCurrentSize", "(Ljava/lang/Number;)V", "getKeys", "()Lio/dcloud/uts/UTSArray;", "setKeys", "(Lio/dcloud/uts/UTSArray;)V", "getLimitSize", "setLimitSize", "uni-storage_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class GetStorageInfoSuccess extends UTSObject {

    @JsonNotNull
    private Number currentSize;

    @JsonNotNull
    private UTSArray<String> keys;

    @JsonNotNull
    private Number limitSize;

    public UTSArray<String> getKeys() {
        return this.keys;
    }

    public void setKeys(UTSArray<String> uTSArray) {
        Intrinsics.checkNotNullParameter(uTSArray, "<set-?>");
        this.keys = uTSArray;
    }

    public Number getCurrentSize() {
        return this.currentSize;
    }

    public void setCurrentSize(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.currentSize = number;
    }

    public Number getLimitSize() {
        return this.limitSize;
    }

    public void setLimitSize(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.limitSize = number;
    }

    public GetStorageInfoSuccess(UTSArray<String> keys, Number currentSize, Number limitSize) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Intrinsics.checkNotNullParameter(currentSize, "currentSize");
        Intrinsics.checkNotNullParameter(limitSize, "limitSize");
        this.keys = keys;
        this.currentSize = currentSize;
        this.limitSize = limitSize;
    }
}
