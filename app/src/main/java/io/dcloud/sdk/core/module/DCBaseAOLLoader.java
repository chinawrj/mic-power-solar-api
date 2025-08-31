package io.dcloud.sdk.core.module;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.nostra13.dcloudimageloader.core.download.BaseImageDownloader;
import io.dcloud.p.b3;
import io.dcloud.p.e;
import io.dcloud.p.m;
import io.dcloud.p.m1;
import io.dcloud.p.t4;
import io.dcloud.p.w;
import io.dcloud.p.z2;
import io.dcloud.sdk.core.adapter.IAdAdapter;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.util.Const;
import io.dcloud.sdk.core.util.MainHandlerUtil;
import io.dcloud.sdk.poly.api.Platform;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class DCBaseAOLLoader extends io.dcloud.sdk.core.module.a {
    private boolean C;
    private final Handler D;
    private final int E;
    private int F;
    private w.a G;

    private static class a implements Runnable {
        private final DCBaseAOLLoader a;
        private final int b;
        private final int c;
        private final String d;
        private final List e;

        public a(DCBaseAOLLoader dCBaseAOLLoader, List list, int i, int i2, String str) {
            this.a = dCBaseAOLLoader;
            this.b = i;
            this.c = i2;
            this.d = str;
            this.e = list;
            if (list != null) {
                b3.d("sub slot ads:" + list.size());
            }
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            m1 m1Var = new m1();
            m1Var.c(this.a.p()).e(this.a.getSlotId()).a(this.a.e).d(this.a.getType()).f(this.a.getTid()).a(this.a.w());
            if (this.a.v()) {
                m1Var.a(m.a(this.a)).b(m.b(this.a));
            }
            int i = this.b;
            if (i == 1) {
                m1Var.b(i);
                this.a.G.a(this.a, this.e, m1Var);
            } else if (i == 0) {
                m1Var.b(i);
                m1Var.a(this.c, this.d);
                this.a.G.a(this.a, m1Var);
            }
        }
    }

    private class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            DCBaseAOLLoader.this.loadFail(-5000, "timeout");
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    public DCBaseAOLLoader(DCloudAOLSlot dCloudAOLSlot, Activity activity) {
        super(dCloudAOLSlot, activity);
        this.C = false;
        this.E = 1;
        this.F = BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT;
        d(-1);
        this.D = new b(z2.a().getLooper());
    }

    private boolean A() {
        return getSlot().getType() == 10 || getSlot().getType() == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B() {
        try {
            load();
        } catch (Throwable th) {
            loadFail(-4002, th.getMessage());
        }
    }

    private boolean z() {
        if (TextUtils.isEmpty(p())) {
            return !e.b().a(getType()) || getType().equalsIgnoreCase("dcloud") || getType().equalsIgnoreCase(Const.TYPE_WM);
        }
        if (getType().equalsIgnoreCase(Const.TYPE_SGM)) {
            return !TextUtils.isEmpty(q());
        }
        return true;
    }

    public m1 C() {
        m1 m1Var = new m1();
        x();
        m1Var.c(p()).a(this.e).e(getSlotId()).d(getType()).f(getTid()).a(w());
        m1Var.b(getAdStatus());
        if (v()) {
            m1Var.a(m.a(this)).b(m.b(this));
        }
        return m1Var;
    }

    public void a(IAdAdapter iAdAdapter, JSONObject jSONObject) {
    }

    protected abstract void init(String str, String str2);

    public abstract void load();

    public final void loadFail(int i, String str) {
        if (this.C) {
            return;
        }
        c(i, str);
        this.C = true;
        this.D.removeMessages(1);
        z2.a().post(new a(this, null, 0, i, str));
    }

    public final void loadSuccess() {
        if (A()) {
            loadFail(-5001, "回调接口调用失败，应该使用loadSuccess(List obj)");
        } else {
            loadSuccess(null);
        }
    }

    protected boolean runOnMain() {
        return false;
    }

    public void setPlatform(Platform platform, String str) {
    }

    public void show(Activity activity) {
    }

    public void showIn(ViewGroup viewGroup) {
    }

    public final void a(Map map) {
        d(-1);
        if (!z()) {
            loadFail(-9999, "");
            return;
        }
        if (TextUtils.isEmpty(getSlotId())) {
            loadFail(-9999, "");
            return;
        }
        startLoadTime();
        if (runOnMain()) {
            MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.sdk.core.module.DCBaseAOLLoader$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.B();
                }
            });
        } else {
            try {
                load();
            } catch (Throwable th) {
                loadFail(-4002, th.getMessage());
            }
        }
        this.D.sendEmptyMessageDelayed(1, this.F);
    }

    public final void loadSuccess(List<? extends DCBaseAOL> list) {
        if (!A()) {
            list = null;
        }
        List<? extends DCBaseAOL> list2 = list;
        if (this.C) {
            return;
        }
        if (list2 != null) {
            for (DCBaseAOL dCBaseAOL : list2) {
                dCBaseAOL.e = this.e;
                dCBaseAOL.b(isSlotSupportBidding());
                dCBaseAOL.setBiddingECPM(r());
                dCBaseAOL.d = this.d;
                dCBaseAOL.g = this.g;
                dCBaseAOL.g(getSlotId());
                dCBaseAOL.b(p());
                dCBaseAOL.setFeedType(getFeedType());
                dCBaseAOL.d(t());
                dCBaseAOL.s = this.s;
            }
        }
        y();
        this.C = true;
        this.D.removeMessages(1);
        z2.a().post(new a(this, list2, 1, 0, null));
    }

    public void a(t4 t4Var) {
        g(t4Var.n());
        this.s = t4Var.l();
        b(t4Var.s());
        if (!isSlotSupportBidding()) {
            setBiddingECPM(t4Var.c());
        }
        this.d = t4Var.m();
        this.F = t4Var.o();
        this.e = t4Var.f();
        this.g = t4Var.b();
        setFeedType(t4Var.d());
        e(t4Var.h());
        a(t4Var.q());
        f(t4Var.k());
        c(t4Var.i());
        a(t4Var.j());
        a(t4Var.a());
        b(t4Var.e());
        b3.d("load sub slot cfg:" + t4Var.toString());
    }

    public void a(String str, String str2) {
        b(str);
        c(str2);
        init(str, str2);
    }

    public final void a(String str, IAdAdapter iAdAdapter, JSONObject jSONObject) {
        b(str);
        a(iAdAdapter, jSONObject);
    }

    public void a(w.a aVar) {
        this.G = aVar;
    }
}
