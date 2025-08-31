package io.dcloud.p;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.taobao.weex.performance.WXInstanceApm;
import io.dcloud.p.m;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;
import io.dcloud.sdk.core.util.AOLErrorUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes3.dex */
public abstract class w extends g4 implements j {
    private final List f;
    private final List g;
    private t1 h;
    private boolean i;
    private final Handler j;
    private final int k;
    private final int l;
    private long m;
    private String n;
    private boolean o;
    private t0 p;
    private Set q;

    public interface a {
        void a(DCBaseAOLLoader dCBaseAOLLoader, m1 m1Var);

        void a(DCBaseAOLLoader dCBaseAOLLoader, List list, m1 m1Var);
    }

    private class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            w.this.j();
        }
    }

    public w(Activity activity) {
        super(activity);
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.i = false;
        this.k = 3;
        this.l = 18000;
        this.m = 0L;
        this.n = "";
        this.o = false;
        this.q = new HashSet();
        this.j = new b(z2.a().getLooper());
    }

    private void f() {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.m;
        ArrayList<m1> arrayList = new ArrayList();
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((t1) it.next()).g());
        }
        JSONArray jSONArray = new JSONArray();
        if (arrayList.size() > 0) {
            JSONArray jSONArray2 = new JSONArray();
            for (m1 m1Var : arrayList) {
                if (m1Var.e()) {
                    if (this.q.contains(m1Var.c())) {
                        m1Var.f();
                    }
                    if (m1Var.d() == 0) {
                        jSONArray.put(m1Var.b());
                    }
                    jSONArray2.put(m1Var.g());
                }
            }
            b3.b("collection data:load time:" + jElapsedRealtime);
            a(this.d, this.b.getAdpid(), this.p.f() ? "1" : WXInstanceApm.VALUE_ERROR_CODE_DEFAULT, this.b.getEI(), jSONArray2, jElapsedRealtime);
        }
        a(jSONArray);
    }

    private void h() {
        this.b.setRID(this.n);
        long jD = this.p.d() - (SystemClock.elapsedRealtime() - this.m);
        if (jD <= 0) {
            c(-5005, AOLErrorUtil.getErrorMsg(-5018));
            return;
        }
        this.j.removeMessages(3);
        this.j.sendEmptyMessageDelayed(3, jD);
        if (this.d != this.p.e()) {
            c(-5011, AOLErrorUtil.getErrorMsg(-5011));
            return;
        }
        List listC = this.p.c();
        if (listC == null || listC.isEmpty()) {
            c(-5019, AOLErrorUtil.getErrorMsg(-5019));
            return;
        }
        if (a(this.p, listC)) {
            return;
        }
        b3.a("level load finish.total:" + this.f.size());
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            b3.a(((t1) it.next()).toString());
        }
        i();
    }

    private void i() {
        if (this.f.size() <= 0 || this.i) {
            t1 t1Var = this.h;
            if (t1Var != null) {
                ((y) t1Var).j();
                b(this.h.i());
            }
            f();
            return;
        }
        t1 t1Var2 = (t1) this.f.remove(0);
        t1 t1Var3 = this.h;
        if (t1Var3 == null || t1Var3.c() <= 0) {
            this.g.add(t1Var2);
            t1Var2.a(this.n);
            t1Var2.a();
            return;
        }
        b3.a("is necessary to load next:" + t1Var2.b(this.h.c()) + ",next level:" + t1Var2.f());
        if (!t1Var2.b(this.h.c())) {
            i();
            return;
        }
        t1Var2.e(this.h.c());
        this.g.add(t1Var2);
        t1Var2.a(this.n);
        t1Var2.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.i = true;
        b3.b("this slot:time out");
        if (this.g.isEmpty()) {
            a(new JSONArray());
            return;
        }
        for (t1 t1Var : this.g) {
            if (!t1Var.k()) {
                t1Var.h();
            }
        }
    }

    protected abstract void a(int i, String str, JSONArray jSONArray);

    public DCBaseAOLLoader b() {
        return null;
    }

    @Override // io.dcloud.p.j
    public void b(t1 t1Var) {
        b3.a("level load success.current:" + t1Var.f() + ",is bid:" + t1Var.d());
        if (t1Var.d()) {
            this.h = t1Var;
            i();
            return;
        }
        t1 t1Var2 = this.h;
        if (t1Var2 == null || !(t1Var2 instanceof y)) {
            b(t1Var.i());
        } else if (g()) {
            m.a aVarA = m.a(this.b.getCount(), this.h.i(), t1Var.i());
            if (aVarA == null) {
                this.h = null;
                i();
                return;
            }
            for (DCBaseAOL dCBaseAOL : aVarA.d) {
                if (dCBaseAOL.isSlotSupportBidding()) {
                    dCBaseAOL.biddingSuccess(aVarA.b, aVarA.c);
                }
            }
            b(aVarA.d);
        } else if (t1Var.c() > this.h.c()) {
            ((y) this.h).d(t1Var.c());
            b(t1Var.i());
        } else {
            ((y) this.h).j();
            b(this.h.i());
        }
        f();
    }

    protected abstract void c(List list);

    @Override // io.dcloud.p.x3
    protected boolean c() {
        return 1 == this.p.b();
    }

    boolean g() {
        int i = this.d;
        return i == 10 || i == 4;
    }

    @Override // io.dcloud.p.g4, java.lang.Runnable
    public void run() throws JSONException {
        this.n = UUID.randomUUID().toString();
        this.g.clear();
        this.f.clear();
        this.q.clear();
        this.h = null;
        this.i = false;
        this.o = false;
        this.j.sendEmptyMessageDelayed(3, 18000L);
        this.m = SystemClock.elapsedRealtime();
        super.run();
    }

    private void c(int i, String str) {
        b(i, str, null);
    }

    @Override // io.dcloud.p.g4
    protected void a(t0 t0Var) {
        this.p = t0Var;
        this.b.setAdpid(t0Var.a());
        h();
    }

    @Override // io.dcloud.p.g4
    protected void a(int i, String str) {
        b(i, str, null);
    }

    private boolean a(t0 t0Var, List list) {
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        x xVar = null;
        while (it.hasNext()) {
            t4 t4Var = (t4) it.next();
            int iF = t4Var.f();
            if (t4Var.s()) {
                if (xVar == null) {
                    xVar = new x(this.b, this.c);
                    xVar.c(t0Var.e());
                    if (t0Var.f()) {
                        xVar.e();
                    }
                }
                xVar.a(iF);
                xVar.a(t4Var);
            } else {
                t1 t1Var = (t1) map.get(Integer.valueOf(iF));
                if (t1Var != null) {
                    t1Var.a(t4Var);
                } else {
                    w4 w4Var = new w4(this.b, this.c);
                    if (t0Var.f()) {
                        w4Var.e();
                    }
                    w4Var.c(t0Var.e());
                    w4Var.a(this);
                    w4Var.a(t4Var.f());
                    w4Var.a(t4Var);
                    map.put(Integer.valueOf(t4Var.f()), w4Var);
                }
            }
        }
        if (xVar != null) {
            if (map.containsKey(Integer.valueOf(xVar.f()))) {
                f3 f3Var = new f3(this.b, this.c);
                f3Var.a(xVar);
                f3Var.a((w4) map.get(Integer.valueOf(xVar.f())));
                f3Var.a(xVar.f());
                f3Var.a(this);
                f3Var.c(t0Var.e());
                if (t0Var.f()) {
                    f3Var.e();
                }
                map.put(Integer.valueOf(xVar.f()), f3Var);
            } else {
                xVar.a(this);
                map.put(Integer.valueOf(xVar.f()), xVar);
            }
        }
        this.f.addAll(map.values());
        if (this.f.size() > 1) {
            Collections.sort(this.f, new Comparator() { // from class: io.dcloud.p.w$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return w.a((t1) obj, (t1) obj2);
                }
            });
            return false;
        }
        if (map.size() == 1) {
            return false;
        }
        c(-5020, AOLErrorUtil.getErrorMsg(-5020));
        return true;
    }

    private void b(int i, String str, JSONArray jSONArray) {
        if (this.o) {
            return;
        }
        this.o = true;
        b3.b("this slot:all fail");
        this.j.removeMessages(3);
        a(i, str, jSONArray);
    }

    private void b(List list) {
        if (this.o) {
            return;
        }
        this.o = true;
        if (w0.a) {
            Iterator it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                DCBaseAOL dCBaseAOL = (DCBaseAOL) it.next();
                b3.a("success!index:" + i + ";ad:" + dCBaseAOL.toString() + ";type:" + dCBaseAOL.getType() + ",id:" + dCBaseAOL.getSlotId() + ",isbid:" + dCBaseAOL.isSlotSupportBidding() + ",bidPrice:" + dCBaseAOL.r());
                i++;
            }
        }
        this.j.removeMessages(3);
        a(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int a(t1 t1Var, t1 t1Var2) {
        return Integer.compare(t1Var.f(), t1Var2.f());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.dcloud.p.x3
    public Activity a() {
        return this.c;
    }

    @Override // io.dcloud.p.j
    public void a(t1 t1Var) {
        i();
    }

    private void a(JSONArray jSONArray) {
        if (this.i) {
            b(-5005, AOLErrorUtil.getErrorMsg(-5018), jSONArray);
        } else {
            b(-5005, AOLErrorUtil.getErrorMsg(-5005), jSONArray);
        }
    }

    private void a(List list) {
        if (list.size() > 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.q.add(((DCBaseAOL) it.next()).getSlotId());
            }
        }
        c(list);
    }
}
