package uts.sdk.modules.DCloudUniChooseMedia;

import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u001b\b\u0016\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0003j\u0002`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\"\u0010\u0004\u001a\u00060\u0003j\u0002`\u00058\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001e\u0010\u0006\u001a\u00020\u00078\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011¨\u0006\""}, d2 = {"Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaTempFile;", "Lio/dcloud/uts/UTSObject;", "tempFilePath", "", "fileType", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaFileType;", AbsoluteConst.JSON_KEY_SIZE, "", "byteSize", "duration", "height", "width", "thumbTempFilePath", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/String;)V", "getByteSize", "()Ljava/lang/Number;", "setByteSize", "(Ljava/lang/Number;)V", "getDuration", "setDuration", "getFileType", "()Ljava/lang/String;", "setFileType", "(Ljava/lang/String;)V", "getHeight", "setHeight", "getSize", "setSize", "getTempFilePath", "setTempFilePath", "getThumbTempFilePath", "setThumbTempFilePath", "getWidth", "setWidth", "uni-chooseMedia_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ChooseMediaTempFile extends UTSObject {
    private Number byteSize;
    private Number duration;

    @JsonNotNull
    private String fileType;
    private Number height;

    @JsonNotNull
    private Number size;

    @JsonNotNull
    private String tempFilePath;
    private String thumbTempFilePath;
    private Number width;

    public /* synthetic */ ChooseMediaTempFile(String str, String str2, Number number, Number number2, Number number3, Number number4, Number number5, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, number, (i & 8) != 0 ? null : number2, (i & 16) != 0 ? null : number3, (i & 32) != 0 ? null : number4, (i & 64) != 0 ? null : number5, (i & 128) != 0 ? null : str3);
    }

    public String getTempFilePath() {
        return this.tempFilePath;
    }

    public void setTempFilePath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tempFilePath = str;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fileType = str;
    }

    public Number getSize() {
        return this.size;
    }

    public void setSize(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.size = number;
    }

    public Number getByteSize() {
        return this.byteSize;
    }

    public void setByteSize(Number number) {
        this.byteSize = number;
    }

    public Number getDuration() {
        return this.duration;
    }

    public void setDuration(Number number) {
        this.duration = number;
    }

    public Number getHeight() {
        return this.height;
    }

    public void setHeight(Number number) {
        this.height = number;
    }

    public Number getWidth() {
        return this.width;
    }

    public void setWidth(Number number) {
        this.width = number;
    }

    public String getThumbTempFilePath() {
        return this.thumbTempFilePath;
    }

    public void setThumbTempFilePath(String str) {
        this.thumbTempFilePath = str;
    }

    public ChooseMediaTempFile(String tempFilePath, String fileType, Number size, Number number, Number number2, Number number3, Number number4, String str) {
        Intrinsics.checkNotNullParameter(tempFilePath, "tempFilePath");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(size, "size");
        this.tempFilePath = tempFilePath;
        this.fileType = fileType;
        this.size = size;
        this.byteSize = number;
        this.duration = number2;
        this.height = number3;
        this.width = number4;
        this.thumbTempFilePath = str;
    }
}
