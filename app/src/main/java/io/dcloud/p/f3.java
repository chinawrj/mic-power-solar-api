package io.dcloud.p;

import android.app.Activity;
import io.dcloud.p.m;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class f3 implements t1, j, y {
    private w4 a;
    private x d;
    private int g;
    private j h;
    private int i;
    protected final DCloudAOLSlot n;
    protected final Activity o;
    private boolean b = false;
    private boolean c = false;
    private boolean e = false;
    private boolean f = false;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private final List p = new ArrayList();

    public f3(DCloudAOLSlot dCloudAOLSlot, Activity activity) {
        this.n = dCloudAOLSlot;
        this.o = activity;
    }

    private void m() {
        boolean z = this.e;
        if (z && this.b) {
            if (l()) {
                m.a aVarA = m.a(this.n.getCount(), this.d.i(), this.a.i());
                if (aVarA == null) {
                    n();
                    return;
                }
                this.k = true;
                for (DCBaseAOL dCBaseAOL : aVarA.d) {
                    if (dCBaseAOL.isSlotSupportBidding()) {
                        dCBaseAOL.biddingSuccess(aVarA.b, aVarA.c);
                    }
                }
                this.p.addAll(aVarA.d);
            } else if (this.d.c() >= this.a.c()) {
                this.j = true;
            } else {
                this.k = true;
                this.d.d(this.a.c());
            }
            o();
            return;
        }
        if (z && this.c) {
            this.j = true;
            this.p.addAll(this.d.i());
            o();
            return;
        }
        boolean z2 = this.f;
        if (z2 && this.b) {
            this.k = true;
            this.p.addAll(this.a.i());
            o();
        } else if (z2 && this.c) {
            n();
        }
    }

    @Override // io.dcloud.p.t1
    public void a(t4 t4Var) {
    }

    public void a(w4 w4Var) {
        this.a = w4Var;
    }

    @Override // io.dcloud.p.j
    public DCBaseAOLLoader b() {
        return null;
    }

    @Override // io.dcloud.p.t1
    public boolean b(int i) {
        return true;
    }

    @Override // io.dcloud.p.t1
    public int c() {
        if (l()) {
            if (this.p.isEmpty()) {
                return -1;
            }
            m.a(this.p);
            return ((DCBaseAOL) this.p.get(r0.size() - 1)).r();
        }
        if (this.j) {
            return this.d.c();
        }
        if (this.k) {
            return this.a.c();
        }
        return -1;
    }

    @Override // io.dcloud.p.t1
    public boolean d() {
        return this.j;
    }

    @Override // io.dcloud.p.t1
    public void e() {
        if (this.j) {
            this.d.e();
        }
        if (this.k) {
            this.a.e();
        }
    }

    @Override // io.dcloud.p.t1
    public void e(int i) {
    }

    @Override // io.dcloud.p.t1
    public int f() {
        return this.g;
    }

    @Override // io.dcloud.p.t1
    public List g() {
        ArrayList arrayList = new ArrayList();
        x xVar = this.d;
        if (xVar != null) {
            arrayList.addAll(xVar.g());
        }
        w4 w4Var = this.a;
        if (w4Var != null) {
            arrayList.addAll(w4Var.g());
        }
        return arrayList;
    }

    @Override // io.dcloud.p.t1
    public void h() {
        this.l = true;
        x xVar = this.d;
        if (xVar != null) {
            xVar.h();
        }
        w4 w4Var = this.a;
        if (w4Var != null) {
            w4Var.h();
        }
    }

    @Override // io.dcloud.p.t1
    public List i() {
        if (l()) {
            return this.p;
        }
        if (this.j) {
            return this.d.i();
        }
        if (this.k) {
            return this.a.i();
        }
        return null;
    }

    @Override // io.dcloud.p.y
    public void j() {
        if (d()) {
            this.d.j();
        }
    }

    @Override // io.dcloud.p.t1
    public boolean k() {
        w4 w4Var = this.a;
        return w4Var != null && this.d != null && w4Var.k() && this.d.k();
    }

    boolean l() {
        int i = this.i;
        return i == 10 || i == 4;
    }

    protected void n() {
        if (this.m) {
            return;
        }
        this.m = true;
        j jVar = this.h;
        if (jVar != null) {
            jVar.a(this);
        }
    }

    protected void o() {
        if (this.m) {
            return;
        }
        this.m = true;
        j jVar = this.h;
        if (jVar != null) {
            jVar.b(this);
        }
    }

    public String toString() {
        return "Bidding:" + this.d.toString() + ",Usual:" + this.a.toString();
    }

    public void a(x xVar) {
        this.d = xVar;
    }

    @Override // io.dcloud.p.j
    public void b(t1 t1Var) {
        if (t1Var == this.a) {
            this.b = true;
        } else if (t1Var == this.d) {
            this.e = true;
        }
        m();
    }

    @Override // io.dcloud.p.y
    public void d(int i) {
        if (d()) {
            this.d.d(i);
        }
    }

    @Override // io.dcloud.p.t1
    public void a(j jVar) {
        this.h = jVar;
    }

    @Override // io.dcloud.p.t1
    public void a() throws IllegalAccessException, JSONException, InstantiationException {
        if (this.l) {
            n();
            return;
        }
        x xVar = this.d;
        if (xVar != null) {
            xVar.a(this);
            this.d.a();
        }
        w4 w4Var = this.a;
        if (w4Var != null) {
            w4Var.a(this);
            this.a.a();
        }
    }

    @Override // io.dcloud.p.t1
    public void c(int i) {
        this.i = i;
        x xVar = this.d;
        if (xVar != null) {
            xVar.c(i);
        }
        w4 w4Var = this.a;
        if (w4Var != null) {
            w4Var.c(i);
        }
    }

    @Override // io.dcloud.p.t1
    public void a(int i) {
        this.g = i;
    }

    @Override // io.dcloud.p.t1
    public void a(String str) {
        x xVar = this.d;
        if (xVar != null) {
            xVar.a(str);
        }
        w4 w4Var = this.a;
        if (w4Var != null) {
            w4Var.a(str);
        }
    }

    @Override // io.dcloud.p.j
    public void a(t1 t1Var) {
        if (t1Var == this.a) {
            this.c = true;
        } else if (t1Var == this.d) {
            this.f = true;
        }
        m();
    }
}
