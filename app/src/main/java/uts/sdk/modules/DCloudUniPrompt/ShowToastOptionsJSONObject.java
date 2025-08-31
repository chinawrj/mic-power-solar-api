package uts.sdk.modules.DCloudUniPrompt;

import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0004\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\"\u0010\u0012\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010#\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`$X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0016\"\u0004\b&\u0010\u0018R\u001c\u0010'\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\u0013X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010\u0018¨\u0006-"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/ShowToastOptionsJSONObject;", "Lio/dcloud/uts/UTSJSONObject;", "()V", "complete", "Lio/dcloud/uts/UTSCallback;", "getComplete", "()Lio/dcloud/uts/UTSCallback;", "setComplete", "(Lio/dcloud/uts/UTSCallback;)V", "duration", "", "getDuration", "()Ljava/lang/Number;", "setDuration", "(Ljava/lang/Number;)V", Constants.Event.FAIL, "getFail", "setFail", AbsoluteConst.JSON_KEY_ICON, "", "Luts/sdk/modules/DCloudUniPrompt/Icon;", "getIcon", "()Ljava/lang/String;", "setIcon", "(Ljava/lang/String;)V", "image", "getImage", "setImage", AbsoluteConst.JSON_KEY_MASK, "", "getMask", "()Ljava/lang/Boolean;", "setMask", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "position", "Luts/sdk/modules/DCloudUniPrompt/ShowToastPosition;", "getPosition", "setPosition", WXImage.SUCCEED, "getSuccess", "setSuccess", AbsoluteConst.JSON_KEY_TITLE, "getTitle", "setTitle", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ShowToastOptionsJSONObject extends UTSJSONObject {
    private UTSCallback complete;
    private Number duration;
    private UTSCallback fail;
    private String icon;
    private String image;
    private Boolean mask;
    private String position;
    private UTSCallback success;
    public String title;

    public String getTitle() {
        String str = this.title;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(AbsoluteConst.JSON_KEY_TITLE);
        return null;
    }

    public void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public Boolean getMask() {
        return this.mask;
    }

    public void setMask(Boolean bool) {
        this.mask = bool;
    }

    public Number getDuration() {
        return this.duration;
    }

    public void setDuration(Number number) {
        this.duration = number;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
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
