package uts.sdk.modules.DCloudUniChooseMedia;

import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\u00020\u00068\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaSuccess;", "Lio/dcloud/uts/UTSObject;", "tempFiles", "Lio/dcloud/uts/UTSArray;", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaTempFile;", "type", "", "(Lio/dcloud/uts/UTSArray;Ljava/lang/String;)V", "getTempFiles", "()Lio/dcloud/uts/UTSArray;", "setTempFiles", "(Lio/dcloud/uts/UTSArray;)V", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "uni-chooseMedia_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ChooseMediaSuccess extends UTSObject {

    @JsonNotNull
    private UTSArray<ChooseMediaTempFile> tempFiles;

    @JsonNotNull
    private String type;

    public UTSArray<ChooseMediaTempFile> getTempFiles() {
        return this.tempFiles;
    }

    public void setTempFiles(UTSArray<ChooseMediaTempFile> uTSArray) {
        Intrinsics.checkNotNullParameter(uTSArray, "<set-?>");
        this.tempFiles = uTSArray;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }

    public ChooseMediaSuccess(UTSArray<ChooseMediaTempFile> tempFiles, String type) {
        Intrinsics.checkNotNullParameter(tempFiles, "tempFiles");
        Intrinsics.checkNotNullParameter(type, "type");
        this.tempFiles = tempFiles;
        this.type = type;
    }
}
