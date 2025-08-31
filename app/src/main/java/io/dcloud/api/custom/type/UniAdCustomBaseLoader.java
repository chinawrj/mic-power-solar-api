package io.dcloud.api.custom.type;

import android.app.Activity;
import io.dcloud.api.custom.base.UniAdSlot;
import io.dcloud.api.custom.type.feed.UniAdCustomNativeAd;
import io.dcloud.p.v1;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.util.AOLErrorUtil;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class UniAdCustomBaseLoader {
    protected v1 a;

    public final void a(Activity activity, UniAdSlot uniAdSlot, v1 v1Var) {
        this.a = v1Var;
        try {
            load(activity, uniAdSlot);
        } catch (Throwable unused) {
            onLoadFail(-4002, AOLErrorUtil.getErrorMsg(-4002));
        }
    }

    public abstract void destroy();

    public int getBidType() {
        v1 v1Var = this.a;
        if (v1Var != null) {
            return v1Var.n();
        }
        return 0;
    }

    public abstract boolean isReady();

    public abstract void load(Activity activity, UniAdSlot uniAdSlot);

    public void onAdClicked() {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.a();
        }
    }

    public void onAdClosed() {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.h();
        }
    }

    public void onAdPlayEnd() {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.e();
        }
    }

    public void onAdPlayError(int i, String str) {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.a(i, str);
        }
    }

    public void onAdShow() {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.b();
        }
    }

    public void onAdSkip() {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.g();
        }
    }

    public void onBidFail(int i, int i2) {
    }

    public void onBidSuccess(int i, int i2) {
    }

    public void onLoadFail(int i, String str) {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.b(i, str);
        }
    }

    public void onLoadSuccess(List<? extends UniAdCustomNativeAd> list) {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.a(list);
        }
    }

    public void setBidPrice(int i) {
        Object obj = this.a;
        if ((obj instanceof DCBaseAOL) && ((DCBaseAOL) obj).isSlotSupportBidding()) {
            ((DCBaseAOL) this.a).setBiddingECPM(i);
        }
    }

    public abstract void show(Object obj);

    public void onLoadSuccess() {
        v1 v1Var = this.a;
        if (v1Var != null) {
            v1Var.k();
        }
    }
}
