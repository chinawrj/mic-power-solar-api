package uts.sdk.modules.DCloudUniPrompt;

import android.view.MotionEvent;
import android.view.View;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import io.dcloud.common.util.CreateShortResultReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\r"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/TouchInterceptorListener;", "Landroid/view/View$OnTouchListener;", BindingXConstants.STATE_INTERCEPTOR, "", "(Z)V", "getInterceptor", "()Z", "setInterceptor", "onTouch", CreateShortResultReceiver.KEY_VERSIONNAME, "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class TouchInterceptorListener implements View.OnTouchListener {
    private boolean interceptor;

    public boolean getInterceptor() {
        return this.interceptor;
    }

    public void setInterceptor(boolean z) {
        this.interceptor = z;
    }

    public TouchInterceptorListener(boolean z) {
        setInterceptor(z);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(event, "event");
        return getInterceptor();
    }
}
