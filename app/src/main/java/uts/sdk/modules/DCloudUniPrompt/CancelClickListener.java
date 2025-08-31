package uts.sdk.modules.DCloudUniPrompt;

import android.app.Dialog;
import android.view.View;
import io.dcloud.common.util.CreateShortResultReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u001a\u0010\u0007\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/CancelClickListener;", "Landroid/view/View$OnClickListener;", "dialog", "Landroid/app/Dialog;", "style", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "(Landroid/app/Dialog;Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;)V", "host", "getHost", "()Landroid/app/Dialog;", "setHost", "(Landroid/app/Dialog;)V", "hostStyle", "getHostStyle", "()Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "setHostStyle", "(Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;)V", "onClick", "", CreateShortResultReceiver.KEY_VERSIONNAME, "Landroid/view/View;", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class CancelClickListener implements View.OnClickListener {
    private Dialog host;
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

    public CancelClickListener(Dialog dialog, ShowActionSheetOptions style) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Intrinsics.checkNotNullParameter(style, "style");
        setHost(dialog);
        setHostStyle(style);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        PromptErrorImpl promptErrorImpl = new PromptErrorImpl((Number) 1, "showActionSheet:fail cancel");
        Function1<IPromptError, Unit> fail = getHostStyle().getFail();
        if (fail != null) {
            fail.invoke(promptErrorImpl);
        }
        getHostStyle().setFail(null);
        Function1<Object, Unit> complete = getHostStyle().getComplete();
        if (complete != null) {
            complete.invoke(promptErrorImpl);
        }
        getHostStyle().setComplete(null);
        getHost().dismiss();
    }
}
