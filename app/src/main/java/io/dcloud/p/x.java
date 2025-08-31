package io.dcloud.p;

import android.app.Activity;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class x extends w4 implements y {
    private int v;
    private int w;

    public x(DCloudAOLSlot dCloudAOLSlot, Activity activity) {
        super(dCloudAOLSlot, activity);
        this.v = 0;
        this.w = 0;
    }

    @Override // io.dcloud.p.w4, io.dcloud.p.t1
    public void a(int i) {
        int i2 = this.t;
        if (i < i2 || i2 < 0) {
            this.t = i;
        }
    }

    @Override // io.dcloud.p.w4, io.dcloud.p.t1
    public boolean b(int i) {
        return true;
    }

    @Override // io.dcloud.p.w4, io.dcloud.p.t1
    public int c() {
        return super.c();
    }

    @Override // io.dcloud.p.w4, io.dcloud.p.t1
    public boolean d() {
        return true;
    }

    @Override // io.dcloud.p.w4, io.dcloud.p.t1
    public void e(int i) {
    }

    @Override // io.dcloud.p.w4, io.dcloud.p.t1
    public void h() {
        this.p = true;
        if (this.b) {
            return;
        }
        l();
        if (this.f.isEmpty()) {
            p();
        } else {
            r();
        }
    }

    @Override // io.dcloud.p.y
    public void j() {
        List list = this.k;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((DCBaseAOL) it.next()).biddingSuccess(this.v, this.w);
            }
        }
        DCBaseAOL dCBaseAOL = this.j;
        if (dCBaseAOL != null) {
            dCBaseAOL.biddingSuccess(this.v, this.w);
        }
    }

    @Override // io.dcloud.p.w4
    protected void r() {
        if (k() || this.p) {
            if (!m()) {
                if (this.f.size() <= 0) {
                    p();
                    return;
                }
                m.a(this.f);
                DCBaseAOL dCBaseAOL = (DCBaseAOL) this.f.remove(0);
                this.j = dCBaseAOL;
                this.h.remove(dCBaseAOL);
                this.v = this.j.r();
                if (this.f.size() >= 1) {
                    DCBaseAOL dCBaseAOL2 = (DCBaseAOL) this.f.remove(0);
                    this.h.remove(dCBaseAOL2);
                    int iR = dCBaseAOL2.r();
                    this.w = iR;
                    dCBaseAOL2.biddingFail(this.v, iR, 2);
                    Iterator it = this.f.iterator();
                    while (it.hasNext()) {
                        ((DCBaseAOL) it.next()).biddingFail(this.v, this.w, 2);
                    }
                }
                Iterator it2 = this.g.iterator();
                while (it2.hasNext()) {
                    ((DCBaseAOLLoader) it2.next()).biddingFail(this.v, this.w, 3);
                }
                q();
                return;
            }
            if (this.i.size() <= 0) {
                p();
                return;
            }
            Iterator it3 = this.i.values().iterator();
            ArrayList arrayList = new ArrayList();
            while (it3.hasNext()) {
                arrayList.addAll((Collection) it3.next());
            }
            m.a(arrayList);
            b3.a("level load success,total ads:" + arrayList.size());
            if (arrayList.size() > this.r.getCount()) {
                List listSubList = arrayList.subList(0, this.r.getCount());
                this.k = listSubList;
                this.v = ((DCBaseAOL) listSubList.get(0)).r();
                List listSubList2 = arrayList.subList(this.r.getCount(), arrayList.size());
                this.w = ((DCBaseAOL) listSubList2.get(0)).r();
                Iterator it4 = listSubList2.iterator();
                while (it4.hasNext()) {
                    ((DCBaseAOL) it4.next()).biddingFail(this.v, this.w, 2);
                }
            } else {
                this.v = ((DCBaseAOL) arrayList.get(0)).r();
                this.k = arrayList;
            }
            q();
        }
    }

    @Override // io.dcloud.p.y
    public void d(int i) {
        DCBaseAOL dCBaseAOL = this.j;
        if (dCBaseAOL != null) {
            dCBaseAOL.biddingFail(i, this.v, 2);
        }
    }

    @Override // io.dcloud.p.w4, io.dcloud.p.w.a
    public void a(DCBaseAOLLoader dCBaseAOLLoader, List list, m1 m1Var) {
        if (this.p || this.b) {
            dCBaseAOLLoader.biddingFail(this.v, this.w, 1);
        }
        super.a(dCBaseAOLLoader, list, m1Var);
    }

    @Override // io.dcloud.p.w4, io.dcloud.p.w.a
    public void a(DCBaseAOLLoader dCBaseAOLLoader, m1 m1Var) {
        if (this.p || this.b) {
            if (m1Var.a() == -5000) {
                dCBaseAOLLoader.biddingFail(this.v, this.w, 1);
            } else {
                dCBaseAOLLoader.biddingFail(this.v, this.w, 3);
            }
        }
        super.a(dCBaseAOLLoader, m1Var);
    }
}
