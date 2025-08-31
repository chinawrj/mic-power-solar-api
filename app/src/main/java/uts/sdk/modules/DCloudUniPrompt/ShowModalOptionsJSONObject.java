package uts.sdk.modules.DCloudUniPrompt;

import com.facebook.common.util.UriUtil;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import kotlin.Metadata;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0096\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010\"\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u000f\"\u0004\b$\u0010\u0011R\u001c\u0010%\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\bR\u001e\u0010(\u001a\u0004\u0018\u00010\u001cX\u0096\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b)\u0010\u001e\"\u0004\b*\u0010 R\u001c\u0010+\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u000f\"\u0004\b-\u0010\u0011R\u001c\u0010.\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\b¨\u00061"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/ShowModalOptionsJSONObject;", "Lio/dcloud/uts/UTSJSONObject;", "()V", "cancelColor", "", "getCancelColor", "()Ljava/lang/String;", "setCancelColor", "(Ljava/lang/String;)V", "cancelText", "getCancelText", "setCancelText", "complete", "Lio/dcloud/uts/UTSCallback;", "getComplete", "()Lio/dcloud/uts/UTSCallback;", "setComplete", "(Lio/dcloud/uts/UTSCallback;)V", "confirmColor", "getConfirmColor", "setConfirmColor", "confirmText", "getConfirmText", "setConfirmText", UriUtil.LOCAL_CONTENT_SCHEME, "getContent", "setContent", "editable", "", "getEditable", "()Ljava/lang/Boolean;", "setEditable", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", Constants.Event.FAIL, "getFail", "setFail", "placeholderText", "getPlaceholderText", "setPlaceholderText", "showCancel", "getShowCancel", "setShowCancel", WXImage.SUCCEED, "getSuccess", "setSuccess", AbsoluteConst.JSON_KEY_TITLE, "getTitle", "setTitle", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ShowModalOptionsJSONObject extends UTSJSONObject {
    private String cancelColor;
    private String cancelText;
    private UTSCallback complete;
    private String confirmColor;
    private String confirmText;
    private String content;
    private UTSCallback fail;
    private String placeholderText;
    private UTSCallback success;
    private String title;
    private Boolean showCancel = true;
    private Boolean editable = false;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public Boolean getShowCancel() {
        return this.showCancel;
    }

    public void setShowCancel(Boolean bool) {
        this.showCancel = bool;
    }

    public String getCancelText() {
        return this.cancelText;
    }

    public void setCancelText(String str) {
        this.cancelText = str;
    }

    public String getCancelColor() {
        return this.cancelColor;
    }

    public void setCancelColor(String str) {
        this.cancelColor = str;
    }

    public String getConfirmText() {
        return this.confirmText;
    }

    public void setConfirmText(String str) {
        this.confirmText = str;
    }

    public String getConfirmColor() {
        return this.confirmColor;
    }

    public void setConfirmColor(String str) {
        this.confirmColor = str;
    }

    public Boolean getEditable() {
        return this.editable;
    }

    public void setEditable(Boolean bool) {
        this.editable = bool;
    }

    public String getPlaceholderText() {
        return this.placeholderText;
    }

    public void setPlaceholderText(String str) {
        this.placeholderText = str;
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
