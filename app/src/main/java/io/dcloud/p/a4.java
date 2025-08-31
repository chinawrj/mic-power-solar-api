package io.dcloud.p;

import android.app.Activity;
import android.text.TextUtils;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.BaseInfo;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
class a4 {
    r a;
    private ArrayList b = new ArrayList();
    private ArrayList c;

    a4(r rVar) {
        this.a = null;
        ArrayList<String> arrayList = new ArrayList<>();
        BaseInfo.sRunningApp = arrayList;
        this.c = arrayList;
        this.a = rVar;
    }

    boolean a(a5 a5Var, ISysEventListener.SysEventType sysEventType, Object obj) {
        boolean z = a5Var == null;
        int size = this.b.size();
        int i = size - 1;
        a5 a5Var2 = null;
        boolean zOnExecute = false;
        while (true) {
            if (i < 0) {
                break;
            }
            a5 a5Var3 = (a5) this.b.get(i);
            if (!z ? a5Var3 == a5Var : z) {
                zOnExecute |= a5Var3.onExecute(sysEventType, obj);
                if (zOnExecute && !a5.a(sysEventType)) {
                    a5Var2 = a5Var3;
                    break;
                }
                a5Var2 = a5Var3;
            }
            i--;
        }
        if (zOnExecute || !sysEventType.equals(ISysEventListener.SysEventType.onKeyUp) || size <= 1 || a5Var2 == null || ((Integer) ((Object[]) obj)[0]).intValue() != 4) {
            return zOnExecute;
        }
        this.a.processEvent(IMgr.MgrType.WindowMgr, 20, a5Var2);
        return true;
    }

    a5 b(String str) {
        a5 a5Var;
        Iterator it = this.b.iterator();
        while (true) {
            if (!it.hasNext()) {
                a5Var = null;
                break;
            }
            a5Var = (a5) it.next();
            if (TextUtils.equals(a5Var.obtainAppId(), str)) {
                break;
            }
        }
        Logger.d("AppCache", "removeWebApp " + a5Var + ";mAppIdList=" + this.c);
        this.b.remove(a5Var);
        this.c.remove(str);
        return a5Var;
    }

    public a5 c() {
        ArrayList arrayList = this.b;
        if (arrayList != null && arrayList.size() == 1) {
            return (a5) this.b.get(0);
        }
        ArrayList arrayList2 = this.b;
        if (arrayList2 == null || arrayList2.size() < 1) {
            return null;
        }
        a5 a5Var = (a5) this.b.get(0);
        long j = a5Var.g1;
        for (int i = 1; i < this.b.size(); i++) {
            a5 a5Var2 = (a5) this.b.get(i);
            long j2 = a5Var2.g1;
            if (j < j2) {
                a5Var = a5Var2;
                j = j2;
            }
        }
        return a5Var;
    }

    public a5 d() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        a5 a5Var = null;
        for (int i = 0; i < this.b.size(); i++) {
            a5 a5Var2 = (a5) this.b.get(i);
            long j = a5Var2.g1;
            if (j < jCurrentTimeMillis) {
                a5Var = a5Var2;
                jCurrentTimeMillis = j;
            }
        }
        return a5Var;
    }

    protected int e() {
        return this.b.size();
    }

    protected a5 b() {
        long j = 0;
        a5 a5Var = null;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            a5 a5Var2 = (a5) this.b.get(size);
            if (a5Var2.t == 3) {
                long j2 = a5Var2.g1;
                if (j2 > j) {
                    a5Var = a5Var2;
                    j = j2;
                }
            }
        }
        return a5Var;
    }

    protected a5 a(String str) {
        int iIndexOf = this.c.indexOf(str);
        if (iIndexOf >= 0) {
            return (a5) this.b.get(iIndexOf);
        }
        return null;
    }

    void a(String str, a5 a5Var) {
        this.c.add(str);
        this.b.add(a5Var);
    }

    protected a5 a(Activity activity, a5 a5Var) {
        if (this.b.contains(a5Var)) {
            return null;
        }
        System.currentTimeMillis();
        if (this.b.size() >= BaseInfo.s_Runing_App_Count_Max) {
            return d();
        }
        return null;
    }

    void a() {
        this.b.clear();
        this.c.clear();
    }
}
