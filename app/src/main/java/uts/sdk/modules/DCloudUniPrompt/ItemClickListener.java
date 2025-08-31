package uts.sdk.modules.DCloudUniPrompt;

import android.app.Dialog;
import android.view.View;
import io.dcloud.common.util.CreateShortResultReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R\u001a\u0010\t\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/ItemClickListener;", "Landroid/view/View$OnClickListener;", "dialog", "Landroid/app/Dialog;", "style", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "index", "", "(Landroid/app/Dialog;Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;Ljava/lang/Number;)V", "host", "getHost", "()Landroid/app/Dialog;", "setHost", "(Landroid/app/Dialog;)V", "hostIndex", "getHostIndex", "()Ljava/lang/Number;", "setHostIndex", "(Ljava/lang/Number;)V", "hostStyle", "getHostStyle", "()Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "setHostStyle", "(Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;)V", "onClick", "", CreateShortResultReceiver.KEY_VERSIONNAME, "Landroid/view/View;", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ItemClickListener implements View.OnClickListener {
    private Dialog host;
    private Number hostIndex;
    private ShowActionSheetOptions hostStyle;

    public Dialog getHost() {
        return this.host;
    }

    public void setHost(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "<set-?>");
        this.host = dialog;
    }

    public ShowActionSheetOptions getHostStyle() {
        return this.hostStyle;
    }

    public void setHostStyle(ShowActionSheetOptions showActionSheetOptions) {
        Intrinsics.checkNotNullParameter(showActionSheetOptions, "<set-?>");
        this.hostStyle = showActionSheetOptions;
    }

    public Number getHostIndex() {
        return this.hostIndex;
    }

    public void setHostIndex(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.hostIndex = number;
    }

    public ItemClickListener(Dialog dialog, ShowActionSheetOptions style, Number index) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(index, "index");
        setHost(dialog);
        setHostStyle(style);
        setHostIndex(index);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        ShowActionSheetSuccess showActionSheetSuccess = new ShowActionSheetSuccess(getHostIndex());
        Function1<ShowActionSheetSuccess, Unit> success = getHostStyle().getSuccess();
        if (success != null) {
            success.invoke(showActionSheetSuccess);
        }
        getHostStyle().setSuccess(null);
        Function1<Object, Unit> complete = getHostStyle().getComplete();
        if (complete != null) {
            complete.invoke(showActionSheetSuccess);
        }
        getHostStyle().setComplete(null);
        getHostStyle().setFail(null);
        getHost().dismiss();
    }
}
