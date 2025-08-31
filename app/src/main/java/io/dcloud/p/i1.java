package io.dcloud.p;

import android.app.Activity;
import android.view.View;
import io.dcloud.sdk.core.interfaces.AOLLoader;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.util.MainHandlerUtil;

/* loaded from: classes3.dex */
public class i1 extends x3 implements AOLLoader.FeedAOLInteractionListener {
    private final DCBaseAOL b;
    private final Activity c;
    protected AOLLoader.FeedAOLInteractionListener d;

    public i1(DCBaseAOL dCBaseAOL, Activity activity) {
        this.b = dCBaseAOL;
        this.c = activity;
        dCBaseAOL.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str) {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener != null) {
            feedAOLInteractionListener.onClosed(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener != null) {
            feedAOLInteractionListener.onClicked();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener != null) {
            feedAOLInteractionListener.onRenderFail();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener != null) {
            feedAOLInteractionListener.onRenderSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener != null) {
            feedAOLInteractionListener.onShow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener != null) {
            feedAOLInteractionListener.onShowError();
        }
    }

    @Override // io.dcloud.p.x3
    protected boolean c() {
        return false;
    }

    public void d() {
        DCBaseAOL dCBaseAOL = this.b;
        if (dCBaseAOL != null) {
            dCBaseAOL.destroy();
        }
    }

    public boolean e() {
        DCBaseAOL dCBaseAOL = this.b;
        return dCBaseAOL != null && dCBaseAOL.isValid();
    }

    public String getType() {
        return this.b != null ? e.b().c(this.b.getType()) : "";
    }

    public void k() {
        DCBaseAOL dCBaseAOL = this.b;
        if (dCBaseAOL != null) {
            dCBaseAOL.render();
        }
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onClicked() {
        a(this.c, this.b);
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.i1$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.f();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onClosed(final String str) {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.i1$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(str);
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onPaidGet(long j, String str, int i) {
        a(a(), this.b, j, str, i);
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onRenderFail() {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.i1$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.g();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onRenderSuccess() {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.i1$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.h();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onShow() {
        b(this.c, this.b);
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.i1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.i();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.FeedAOLInteractionListener
    public void onShowError() {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.i1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.j();
            }
        });
    }

    public View a(Activity activity) {
        DCBaseAOL dCBaseAOL = this.b;
        if (dCBaseAOL != null) {
            return dCBaseAOL.getExpressAdView(activity);
        }
        return null;
    }

    @Override // io.dcloud.p.x3
    protected Activity a() {
        return this.c;
    }

    public void a(AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener) {
        this.d = feedAOLInteractionListener;
    }
}
