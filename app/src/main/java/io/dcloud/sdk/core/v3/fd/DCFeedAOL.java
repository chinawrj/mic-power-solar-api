package io.dcloud.sdk.core.v3.fd;

import android.app.Activity;
import android.view.View;
import io.dcloud.p.i1;
import io.dcloud.sdk.core.interfaces.AOLLoader;
import io.dcloud.sdk.core.util.MainHandlerUtil;

/* loaded from: classes3.dex */
public class DCFeedAOL implements AOLLoader.FeedAOLInteractionListener {
    private i1 a;
    protected DCFeedAOLListener b;

    public DCFeedAOL(i1 i1Var) {
        this.a = i1Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        this.a.k();
    }

    public void destroy() {
        i1 i1Var = this.a;
        if (i1Var != null) {
            i1Var.d();
        }
    }

    public View getFeedAOLView(Activity activity) {
        i1 i1Var = this.a;
        if (i1Var != null) {
            return i1Var.a(activity);
        }
        return null;
    }

    public String getType() {
        i1 i1Var = this.a;
        return i1Var != null ? i1Var.getType() : "";
    }

    public boolean isValid() {
        i1 i1Var = this.a;
        return i1Var != null && i1Var.e();
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onClicked() {
        DCFeedAOLListener dCFeedAOLListener = this.b;
        if (dCFeedAOLListener != null) {
            dCFeedAOLListener.onClick();
        }
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onClosed(String str) {
        DCFeedAOLListener dCFeedAOLListener = this.b;
        if (dCFeedAOLListener != null) {
            dCFeedAOLListener.onClosed(str);
        }
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onPaidGet(long j, String str, int i) {
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onRenderFail() {
        DCFeedAOLListener dCFeedAOLListener = this.b;
        if (dCFeedAOLListener != null) {
            dCFeedAOLListener.onRenderFail();
        }
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onRenderSuccess() {
        DCFeedAOLListener dCFeedAOLListener = this.b;
        if (dCFeedAOLListener != null) {
            dCFeedAOLListener.onRenderSuccess();
        }
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onShow() {
        DCFeedAOLListener dCFeedAOLListener = this.b;
        if (dCFeedAOLListener != null) {
            dCFeedAOLListener.onShow();
        }
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onShowError() {
        DCFeedAOLListener dCFeedAOLListener = this.b;
        if (dCFeedAOLListener != null) {
            dCFeedAOLListener.onShowError();
        }
    }

    public void render() {
        i1 i1Var = this.a;
        if (i1Var != null) {
            i1Var.a(this);
            MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.sdk.core.v3.fd.DCFeedAOL$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.a();
                }
            });
        }
    }

    public void setFeedAOLListener(DCFeedAOLListener dCFeedAOLListener) {
        this.b = dCFeedAOLListener;
    }
}
