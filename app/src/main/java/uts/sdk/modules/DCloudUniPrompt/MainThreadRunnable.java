package uts.sdk.modules.DCloudUniPrompt;

import android.widget.PopupWindow;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\n"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/MainThreadRunnable;", "Ljava/lang/Runnable;", "pop", "Landroid/widget/PopupWindow;", "(Landroid/widget/PopupWindow;)V", "getPop", "()Landroid/widget/PopupWindow;", "setPop", "run", "", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class MainThreadRunnable implements Runnable {
    private PopupWindow pop;

    public PopupWindow getPop() {
        return this.pop;
    }

    public void setPop(PopupWindow popupWindow) {
        Intrinsics.checkNotNullParameter(popupWindow, "<set-?>");
        this.pop = popupWindow;
    }

    public MainThreadRunnable(PopupWindow pop) {
        Intrinsics.checkNotNullParameter(pop, "pop");
        setPop(pop);
    }

    @Override // java.lang.Runnable
    public void run() {
        getPop().dismiss();
    }
}
