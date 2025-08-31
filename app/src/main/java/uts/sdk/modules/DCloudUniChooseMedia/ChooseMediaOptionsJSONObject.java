package uts.sdk.modules.DCloudUniChooseMedia;

import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import kotlin.Metadata;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0004\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\"X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\"\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001e\"\u0004\b'\u0010 R\u001c\u0010(\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\f\"\u0004\b*\u0010\u000e¨\u0006+"}, d2 = {"Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaOptionsJSONObject;", "Lio/dcloud/uts/UTSJSONObject;", "()V", "camera", "", "getCamera", "()Ljava/lang/String;", "setCamera", "(Ljava/lang/String;)V", "complete", "Lio/dcloud/uts/UTSCallback;", "getComplete", "()Lio/dcloud/uts/UTSCallback;", "setComplete", "(Lio/dcloud/uts/UTSCallback;)V", "count", "", "getCount", "()Ljava/lang/Number;", "setCount", "(Ljava/lang/Number;)V", Constants.Event.FAIL, "getFail", "setFail", "maxDuration", "getMaxDuration", "setMaxDuration", "mediaType", "Lio/dcloud/uts/UTSArray;", "getMediaType", "()Lio/dcloud/uts/UTSArray;", "setMediaType", "(Lio/dcloud/uts/UTSArray;)V", "pageOrientation", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaPageOrientation;", "getPageOrientation", "setPageOrientation", "sourceType", "getSourceType", "setSourceType", WXImage.SUCCEED, "getSuccess", "setSuccess", "uni-chooseMedia_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ChooseMediaOptionsJSONObject extends UTSJSONObject {
    private String camera;
    private UTSCallback complete;
    private Number count;
    private UTSCallback fail;
    private Number maxDuration;
    private UTSArray<String> mediaType;
    private String pageOrientation;
    private UTSArray<String> sourceType;
    private UTSCallback success;

    public String getPageOrientation() {
        return this.pageOrientation;
    }

    public void setPageOrientation(String str) {
        this.pageOrientation = str;
    }

    public Number getCount() {
        return this.count;
    }

    public void setCount(Number number) {
        this.count = number;
    }

    public UTSArray<String> getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(UTSArray<String> uTSArray) {
        this.mediaType = uTSArray;
    }

    public UTSArray<String> getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(UTSArray<String> uTSArray) {
        this.sourceType = uTSArray;
    }

    public Number getMaxDuration() {
        return this.maxDuration;
    }

    public void setMaxDuration(Number number) {
        this.maxDuration = number;
    }

    public String getCamera() {
        return this.camera;
    }

    public void setCamera(String str) {
        this.camera = str;
    }

    public UTSCallback getSuccess() {
        return this.success;
    }

    public void setSuccess(UTSCallback uTSCallback) {
        this.success = uTSCallback;
    }

    public UTSCallback getFail() {
        return this.fail;
    }

    public void setFail(UTSCallback uTSCallback) {
        this.fail = uTSCallback;
    }

    public UTSCallback getComplete() {
        return this.complete;
    }

    public void setComplete(UTSCallback uTSCallback) {
        this.complete = uTSCallback;
    }
}
