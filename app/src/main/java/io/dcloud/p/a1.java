package io.dcloud.p;

import android.app.Activity;
import io.dcloud.sdk.core.interfaces.AOLLoader;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.util.MainHandlerUtil;

/* loaded from: classes3.dex */
public class a1 extends i1 implements AOLLoader.DrawAOLInteractionListener {
    public a1(DCBaseAOL dCBaseAOL, Activity activity) {
        super(dCBaseAOL, activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener instanceof AOLLoader.DrawAOLInteractionListener) {
            ((AOLLoader.DrawAOLInteractionListener) feedAOLInteractionListener).onEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener instanceof AOLLoader.DrawAOLInteractionListener) {
            feedAOLInteractionListener.onShowError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener instanceof AOLLoader.DrawAOLInteractionListener) {
            ((AOLLoader.DrawAOLInteractionListener) feedAOLInteractionListener).onPause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener instanceof AOLLoader.DrawAOLInteractionListener) {
            ((AOLLoader.DrawAOLInteractionListener) feedAOLInteractionListener).onResume();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p() {
        AOLLoader.FeedAOLInteractionListener feedAOLInteractionListener = this.d;
        if (feedAOLInteractionListener instanceof AOLLoader.DrawAOLInteractionListener) {
            ((AOLLoader.DrawAOLInteractionListener) feedAOLInteractionListener).onStart();
        }
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.DrawAOLInteractionListener
    public void onEnd() {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.a1$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.l();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.DrawAOLInteractionListener
    public void onError() {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.a1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.DrawAOLInteractionListener
    public void onPause() {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.a1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.n();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.DrawAOLInteractionListener
    public void onResume() {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.a1$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.o();
            }
        });
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.DrawAOLInteractionListener
    public void onStart() {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.a1$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.p();
            }
        });
    }
}
