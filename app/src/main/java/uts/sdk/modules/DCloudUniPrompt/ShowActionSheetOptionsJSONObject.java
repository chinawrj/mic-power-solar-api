package uts.sdk.modules.DCloudUniPrompt;

import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0016X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000eR\u001c\u0010$\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\b¨\u0006'"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptionsJSONObject;", "Lio/dcloud/uts/UTSJSONObject;", "()V", "alertText", "", "getAlertText", "()Ljava/lang/String;", "setAlertText", "(Ljava/lang/String;)V", "complete", "Lio/dcloud/uts/UTSCallback;", "getComplete", "()Lio/dcloud/uts/UTSCallback;", "setComplete", "(Lio/dcloud/uts/UTSCallback;)V", Constants.Event.FAIL, "getFail", "setFail", Constants.Name.ITEM_COLOR, "getItemColor", "setItemColor", "itemList", "Lio/dcloud/uts/UTSArray;", "getItemList", "()Lio/dcloud/uts/UTSArray;", "setItemList", "(Lio/dcloud/uts/UTSArray;)V", AbsoluteConst.JSON_KEY_POPOVER, "Luts/sdk/modules/DCloudUniPrompt/Popover;", "getPopover", "()Luts/sdk/modules/DCloudUniPrompt/Popover;", "setPopover", "(Luts/sdk/modules/DCloudUniPrompt/Popover;)V", WXImage.SUCCEED, "getSuccess", "setSuccess", AbsoluteConst.JSON_KEY_TITLE, "getTitle", "setTitle", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ShowActionSheetOptionsJSONObject extends UTSJSONObject {
    private String alertText;
    private UTSCallback complete;
    private UTSCallback fail;
    private String itemColor;
    public UTSArray<String> itemList;
    private Popover popover;
    private UTSCallback success;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAlertText() {
        return this.alertText;
    }

    public void setAlertText(String str) {
        this.alertText = str;
    }

    public UTSArray<String> getItemList() {
        UTSArray<String> uTSArray = this.itemList;
        if (uTSArray != null) {
            return uTSArray;
        }
        Intrinsics.throwUninitializedPropertyAccessException("itemList");
        return null;
    }

    public void setItemList(UTSArray<String> uTSArray) {
        Intrinsics.checkNotNullParameter(uTSArray, "<set-?>");
        this.itemList = uTSArray;
    }

    public String getItemColor() {
        return this.itemColor;
    }

    public void setItemColor(String str) {
        this.itemColor = str;
    }

    public Popover getPopover() {
        return this.popover;
    }

    public void setPopover(Popover popover) {
        this.popover = popover;
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
