package io.dcloud.p;

import android.app.Activity;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.interfaces.AOLLoader;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;
import io.dcloud.sdk.core.util.AOLErrorUtil;
import io.dcloud.sdk.core.util.MainHandlerUtil;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class y4 extends w implements AOLLoader.VAOLInteractionListener {
    protected AOLLoader.VAOLInteractionListener r;
    protected v2 s;
    protected DCBaseAOL t;
    private boolean u;
    private boolean v;
    private boolean w;

    public y4(Activity activity, int i) {
        super(activity);
        this.u = false;
        this.v = false;
        this.w = false;
        this.d = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(v2 v2Var) {
        if (v2Var != null) {
            v2Var.onError(-5021, AOLErrorUtil.getErrorMsg(-5021), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(int i, String str) {
        AOLLoader.VAOLInteractionListener vAOLInteractionListener = this.r;
        if (vAOLInteractionListener != null) {
            vAOLInteractionListener.onShowError(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m() {
        AOLLoader.VAOLInteractionListener vAOLInteractionListener = this.r;
        if (vAOLInteractionListener != null) {
            vAOLInteractionListener.onClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n() {
        AOLLoader.VAOLInteractionListener vAOLInteractionListener = this.r;
        if (vAOLInteractionListener != null) {
            vAOLInteractionListener.onClose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        AOLLoader.VAOLInteractionListener vAOLInteractionListener = this.r;
        if (vAOLInteractionListener != null) {
            vAOLInteractionListener.onShow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p() {
        AOLLoader.VAOLInteractionListener vAOLInteractionListener = this.r;
        if (vAOLInteractionListener != null) {
            vAOLInteractionListener.onSkip();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q() {
        AOLLoader.VAOLInteractionListener vAOLInteractionListener = this.r;
        if (vAOLInteractionListener != null) {
            vAOLInteractionListener.onVideoPlayEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r() {
        AOLLoader.VAOLInteractionListener vAOLInteractionListener = this.r;
        if (vAOLInteractionListener != null) {
            vAOLInteractionListener.onShowError(-5006, AOLErrorUtil.getErrorMsg(-5006));
        }
    }

    @Override // io.dcloud.p.w
    protected void a(final int i, final String str, final JSONArray jSONArray) {
        this.w = false;
        this.t = null;
        b3.b("uniAd-loadError", "code:" + i + ";message:" + str + ";detail:" + String.valueOf(jSONArray));
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c(i, str, jSONArray);
            }
        });
    }

    @Override // io.dcloud.p.w
    protected void c(final List list) {
        this.w = false;
        this.t = null;
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.d(list);
            }
        });
    }

    public String getType() {
        return this.t != null ? e.b().c(this.t.getType()) : "";
    }

    public void k() {
        DCBaseAOL dCBaseAOL = this.t;
        if (dCBaseAOL != null) {
            dCBaseAOL.destroy();
        }
    }

    public boolean l() {
        DCBaseAOL dCBaseAOL = this.t;
        return dCBaseAOL != null && dCBaseAOL.isValid();
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
    public void onClick() {
        a(a(), this.t);
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
    public void onClose() {
        this.v = false;
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.n();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
    public void onPaidGet(long j, String str, int i) {
        a(a(), this.t, j, str, i);
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
    public void onShow() {
        this.u = true;
        this.v = true;
        b(a(), this.t);
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.o();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
    public void onShowError(final int i, final String str) {
        this.v = false;
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.d(i, str);
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
    public void onSkip() {
        this.v = false;
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.p();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
    public void onVideoPlayEnd() {
        this.v = false;
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.q();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(List list) {
        if (list != null && list.size() > 0) {
            DCBaseAOL dCBaseAOL = (DCBaseAOL) list.get(0);
            this.t = dCBaseAOL;
            dCBaseAOL.a(this);
            v2 v2Var = this.s;
            if (v2Var != null) {
                v2Var.onLoaded();
                return;
            }
            return;
        }
        v2 v2Var2 = this.s;
        if (v2Var2 != null) {
            v2Var2.onError(-5005, AOLErrorUtil.getErrorMsg(-5005), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(int i, String str, JSONArray jSONArray) {
        v2 v2Var = this.s;
        if (v2Var != null) {
            v2Var.onError(i, str, jSONArray);
        }
    }

    public void a(Activity activity) {
        if (this.u) {
            MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.r();
                }
            });
            return;
        }
        DCBaseAOL dCBaseAOL = this.t;
        if (dCBaseAOL instanceof DCBaseAOLLoader) {
            ((DCBaseAOLLoader) dCBaseAOL).show(activity);
        }
    }

    public void a(AOLLoader.VAOLInteractionListener vAOLInteractionListener) {
        this.r = vAOLInteractionListener;
    }

    public void a(DCloudAOLSlot dCloudAOLSlot, final v2 v2Var) {
        if (this.v) {
            MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    y4.b(v2Var);
                }
            });
            return;
        }
        if (this.w) {
            MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y4$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    y4.a(v2Var);
                }
            });
            return;
        }
        this.u = false;
        a(dCloudAOLSlot);
        this.s = v2Var;
        this.w = true;
        z2.a().post(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(v2 v2Var) {
        if (v2Var != null) {
            v2Var.onError(-5017, AOLErrorUtil.getErrorMsg(-5017), null);
        }
    }

    protected void a(boolean z) {
        this.w = z;
    }
}
