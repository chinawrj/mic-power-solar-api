package io.dcloud.p;

import android.app.Activity;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.api.custom.UniAdCustomAdapter;
import io.dcloud.p.w;
import io.dcloud.sdk.core.adapter.IAdAdapter;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class w4 implements t1, w.a {
    protected DCBaseAOL j;
    protected int o;
    private j q;
    protected final DCloudAOLSlot r;
    protected final Activity s;
    private boolean a = false;
    protected volatile boolean b = false;
    private final List c = new ArrayList();
    private final AtomicInteger d = new AtomicInteger(0);
    private final b e = new b();
    protected List f = new ArrayList();
    protected List g = new ArrayList();
    protected List h = new ArrayList();
    protected Map i = new HashMap();
    protected List k = new ArrayList();
    private final List l = new ArrayList();
    private final Map m = new HashMap();
    private final List n = new ArrayList();
    protected boolean p = false;
    protected int t = Integer.MIN_VALUE;
    private String u = "";

    private static class b {
        private Integer a;
        private Integer b;

        private b() {
            this.a = 0;
            this.b = 0;
        }

        public boolean a(Integer num) {
            return (num.compareTo(this.a) >= 0) && (num.compareTo(this.b) <= 0);
        }

        public boolean b(Integer num) {
            return num.compareTo(this.b) <= 0;
        }

        public void c(Integer num) {
            if (a(num)) {
                return;
            }
            if (num.intValue() > this.b.intValue()) {
                this.b = num;
            } else if (num.intValue() < this.a.intValue()) {
                this.a = num;
            }
        }

        public String toString() {
            return String.format("[%s, %s]", this.a, this.b);
        }
    }

    public w4(DCloudAOLSlot dCloudAOLSlot, Activity activity) {
        this.r = dCloudAOLSlot;
        this.s = activity;
    }

    private void b() throws IllegalAccessException, InstantiationException {
        b3.d("ordered request list");
        n();
        if (k()) {
            p();
        }
    }

    private void n() throws IllegalAccessException, InstantiationException {
        if (this.p || this.b) {
            return;
        }
        if (this.c.size() <= 0) {
            p();
            return;
        }
        t4 t4Var = (t4) this.c.remove(0);
        DCBaseAOLLoader dCBaseAOLLoaderB = b(t4Var);
        if (dCBaseAOLLoaderB == null) {
            b3.b("load sub slot fail cfg:" + t4Var.toString());
            this.d.decrementAndGet();
            n();
            return;
        }
        dCBaseAOLLoaderB.a(this);
        this.h.add(dCBaseAOLLoaderB);
        dCBaseAOLLoaderB.a(t4Var);
        dCBaseAOLLoaderB.d(this.u);
        dCBaseAOLLoaderB.a((Map) null);
    }

    private void o() throws IllegalAccessException, InstantiationException {
        if (!d()) {
            ArrayList arrayList = new ArrayList();
            if (this.i.size() > 0) {
                if (this.l.size() <= 0) {
                    for (List list : this.i.values()) {
                        if (list != null) {
                            arrayList.addAll(list);
                        }
                        if (arrayList.size() >= this.r.getCount()) {
                            break;
                        }
                    }
                } else {
                    for (Integer num : this.l) {
                        for (DCBaseAOL dCBaseAOL : this.f) {
                            if (dCBaseAOL.u() == num.intValue()) {
                                List list2 = (List) this.i.get(dCBaseAOL);
                                if (list2 != null) {
                                    arrayList.addAll(list2);
                                }
                                if (arrayList.size() >= this.r.getCount()) {
                                    break;
                                }
                            }
                        }
                        if (a(num) > 0) {
                            break;
                        }
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                if (arrayList.size() >= this.r.getCount()) {
                    this.k.addAll(arrayList.subList(0, this.r.getCount()));
                    q();
                } else if (k()) {
                    this.k.addAll(arrayList);
                    q();
                }
            }
        }
        r();
    }

    @Override // io.dcloud.p.t1
    public void a(t4 t4Var) {
        this.e.c(Integer.valueOf(t4Var.c()));
        this.c.add(t4Var);
    }

    @Override // io.dcloud.p.t1
    public int c() {
        if (!m()) {
            DCBaseAOL dCBaseAOL = this.j;
            if (dCBaseAOL != null) {
                return dCBaseAOL.r();
            }
            return -1;
        }
        List list = this.k;
        if (list == null || list.isEmpty()) {
            return -1;
        }
        m.a(this.k);
        return ((DCBaseAOL) this.k.get(r0.size() - 1)).r();
    }

    @Override // io.dcloud.p.t1
    public boolean d() {
        return false;
    }

    @Override // io.dcloud.p.t1
    public void e() {
        if (this instanceof x) {
            return;
        }
        this.a = true;
    }

    @Override // io.dcloud.p.t1
    public int f() {
        return this.t;
    }

    @Override // io.dcloud.p.t1
    public List g() {
        return this.n;
    }

    @Override // io.dcloud.p.t1
    public void h() {
        this.p = true;
        if (this.b) {
            return;
        }
        l();
        if (this.f.isEmpty()) {
            p();
            return;
        }
        Collections.sort(this.f, new Comparator() { // from class: io.dcloud.p.w4$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return w4.a((DCBaseAOL) obj, (DCBaseAOL) obj2);
            }
        });
        if (!m()) {
            this.j = (DCBaseAOL) this.f.get(0);
            q();
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            List list = (List) this.i.get((DCBaseAOL) it.next());
            if (list != null) {
                arrayList.addAll(list);
            }
            if (arrayList.size() >= this.r.getCount()) {
                break;
            }
        }
        if (arrayList.size() <= 0) {
            p();
            return;
        }
        if (arrayList.size() > this.r.getCount()) {
            this.k.addAll(arrayList.subList(0, this.r.getCount()));
        } else {
            this.k.addAll(arrayList);
        }
        q();
    }

    @Override // io.dcloud.p.t1
    public List i() {
        if (m()) {
            return this.k;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.j);
        return arrayList;
    }

    @Override // io.dcloud.p.t1
    public boolean k() {
        return this.p || this.d.get() <= 0;
    }

    protected void l() {
        if (this.h.isEmpty()) {
            return;
        }
        this.h.removeAll(this.f);
        this.h.removeAll(this.g);
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            this.n.add(((DCBaseAOLLoader) it.next()).C());
        }
        this.h.clear();
    }

    boolean m() {
        int i = this.o;
        return i == 10 || i == 4;
    }

    protected void p() {
        if (this.b) {
            return;
        }
        b3.a("current level load fail.level:" + this.t);
        this.b = true;
        l();
        j jVar = this.q;
        if (jVar != null) {
            jVar.a(this);
        }
    }

    protected void q() {
        if (this.b) {
            return;
        }
        if (m()) {
            b3.a("current level load success.level:" + this.t + ";count:" + this.k.size());
        } else {
            b3.a("current level load success.level:" + this.t + ";slot:" + this.j.getSlotId() + ";ss:" + this.j.u());
        }
        this.b = true;
        l();
        j jVar = this.q;
        if (jVar != null) {
            jVar.b(this);
        }
    }

    protected void r() throws IllegalAccessException, InstantiationException {
        if (k()) {
            if (this.f.isEmpty()) {
                p();
            }
        } else if (this.a) {
            b();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("print all slot cfg:level:");
        sb.append(this.t);
        sb.append(";");
        for (t4 t4Var : this.c) {
            sb.append(t4Var.l());
            sb.append(":");
            sb.append(t4Var.n());
            sb.append(":show:");
            sb.append(t4Var.m());
            sb.append(",");
        }
        return sb.toString();
    }

    private void f(int i) {
        AtomicInteger atomicInteger = (AtomicInteger) this.m.get(Integer.valueOf(i));
        if (atomicInteger == null) {
            this.m.put(Integer.valueOf(i), new AtomicInteger(0));
        } else {
            atomicInteger.decrementAndGet();
        }
    }

    @Override // io.dcloud.p.t1
    public void a(j jVar) {
        this.q = jVar;
    }

    @Override // io.dcloud.p.t1
    public void e(int i) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (((t4) it.next()).c() <= i) {
                it.remove();
            }
        }
    }

    @Override // io.dcloud.p.t1
    public void a() throws IllegalAccessException, JSONException, InstantiationException {
        if (this.c.isEmpty()) {
            p();
            return;
        }
        if (this.p) {
            p();
            return;
        }
        b(Operators.PLUS);
        if (d()) {
            a(false);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (this.c.size() > 1) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (t4 t4Var : this.c) {
                this.d.incrementAndGet();
                if (t4Var.r()) {
                    try {
                        int iP = t4Var.p();
                        if (iP > 0) {
                            jSONObject.put(t4Var.n(), iP);
                        }
                        int iG = t4Var.g();
                        if (iG > 0) {
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(t4Var.l());
                            jSONArray.put(iG);
                            jSONObject2.put(t4Var.n(), jSONArray);
                        }
                    } catch (Exception unused) {
                    }
                }
                linkedHashMap.put(t4Var.n(), t4Var);
            }
            if (jSONObject.length() <= 0 && jSONObject2.length() <= 0) {
                if (this.a) {
                    b();
                    return;
                } else {
                    a(false);
                    return;
                }
            }
            List listA = m.a(this.s, new ArrayList(linkedHashMap.keySet()), this.r.getAdpid(), jSONObject, jSONObject2);
            this.c.clear();
            Iterator it = listA.iterator();
            while (it.hasNext()) {
                t4 t4Var2 = (t4) linkedHashMap.get((String) it.next());
                if (t4Var2 != null) {
                    this.c.add(t4Var2);
                }
            }
            if (this.a) {
                b();
                return;
            } else {
                a(true);
                return;
            }
        }
        this.d.incrementAndGet();
        b();
    }

    protected DCBaseAOLLoader b(t4 t4Var) throws IllegalAccessException, InstantiationException {
        j jVar;
        IAdAdapter iAdAdapterB = e.b().b(t4Var.l());
        a0 a0VarA = b0.a().a(t4Var.l());
        if (iAdAdapterB == null && !e.b().a(t4Var.l())) {
            if (a0VarA != null && a0VarA.g()) {
                try {
                    Object objNewInstance = Class.forName(a0VarA.d()).newInstance();
                    if (objNewInstance instanceof UniAdCustomAdapter) {
                        e.b().a(t4Var.l(), (UniAdCustomAdapter) objNewInstance);
                        iAdAdapterB = (UniAdCustomAdapter) objNewInstance;
                    }
                } catch (Exception unused) {
                }
            } else if (this.o == 1 && (iAdAdapterB = e.b().b("dcloud")) == null) {
                iAdAdapterB = new q0();
                e.b().a("dcloud", iAdAdapterB);
            }
        }
        if (iAdAdapterB == null || !iAdAdapterB.isSupport()) {
            return null;
        }
        if (t4Var.l().equalsIgnoreCase("dcloud") && (jVar = this.q) != null) {
            DCBaseAOLLoader dCBaseAOLLoaderB = jVar.b();
            dCBaseAOLLoaderB.setPlatform(null, "dcloud");
            return dCBaseAOLLoaderB;
        }
        DCBaseAOLLoader ad = iAdAdapterB.getAd(this.s, this.r);
        if (a0VarA == null || ad == null) {
            return null;
        }
        if (a0VarA.g()) {
            ad.a(a0VarA.a(), iAdAdapterB, a0VarA.e());
        } else {
            ad.a(a0VarA.a(), a0VarA.b());
            if (a0VarA.f() != null) {
                ad.setPlatform(a0VarA.f(), a0VarA.c());
            }
        }
        return ad;
    }

    @Override // io.dcloud.p.t1
    public void c(int i) {
        this.o = i;
    }

    @Override // io.dcloud.p.t1
    public boolean b(int i) {
        return this.e.b(Integer.valueOf(i));
    }

    private void b(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            sb.append(str);
        }
        b3.d(sb.toString());
    }

    private void a(boolean z) throws IllegalAccessException, InstantiationException {
        AtomicInteger atomicInteger;
        this.d.set(0);
        ArrayList<DCBaseAOLLoader> arrayList = new ArrayList();
        int i = 0;
        for (t4 t4Var : this.c) {
            DCBaseAOLLoader dCBaseAOLLoaderB = b(t4Var);
            if (dCBaseAOLLoaderB != null) {
                this.d.incrementAndGet();
                if (z) {
                    t4Var.a(i);
                    i++;
                }
                dCBaseAOLLoaderB.a(t4Var);
                dCBaseAOLLoaderB.a(this);
                arrayList.add(dCBaseAOLLoaderB);
                if (!this.l.contains(Integer.valueOf(t4Var.m()))) {
                    this.l.add(Integer.valueOf(t4Var.m()));
                }
                if (this.m.containsKey(Integer.valueOf(t4Var.m()))) {
                    atomicInteger = (AtomicInteger) this.m.get(Integer.valueOf(t4Var.m()));
                } else {
                    atomicInteger = new AtomicInteger(0);
                }
                atomicInteger.incrementAndGet();
                this.m.put(Integer.valueOf(t4Var.m()), atomicInteger);
            } else {
                b3.b("load sub slot fail cfg:" + t4Var.toString());
            }
        }
        b3.a("level start load.current:" + this.t + ",valid ads:" + arrayList.size());
        if (arrayList.size() > 0) {
            if (this.l.size() > 1) {
                Collections.sort(this.l);
            }
            this.h.addAll(arrayList);
            for (DCBaseAOLLoader dCBaseAOLLoader : arrayList) {
                dCBaseAOLLoader.d(this.u);
                dCBaseAOLLoader.a((Map) null);
            }
            return;
        }
        p();
    }

    @Override // io.dcloud.p.t1
    public void a(int i) {
        this.t = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int a(DCBaseAOL dCBaseAOL, DCBaseAOL dCBaseAOL2) {
        return Integer.compare(dCBaseAOL.u(), dCBaseAOL2.u());
    }

    @Override // io.dcloud.p.w.a
    public void a(DCBaseAOLLoader dCBaseAOLLoader, List list, m1 m1Var) {
        if (!this.u.equalsIgnoreCase(dCBaseAOLLoader.t()) || this.p || this.b) {
            return;
        }
        this.d.decrementAndGet();
        this.f.add(dCBaseAOLLoader);
        this.n.add(m1Var);
        f(dCBaseAOLLoader.u());
        b3.d("level ad load success!current sub slot:" + dCBaseAOLLoader.getSlotId() + ";ss;" + dCBaseAOLLoader.u() + ";type;" + dCBaseAOLLoader.getType());
        if (m()) {
            this.i.put(dCBaseAOLLoader, list);
        }
        if (m()) {
            if (this.a) {
                this.k.addAll(list);
                q();
                r();
            } else {
                o();
            }
        } else {
            if (!d()) {
                if (this.l.size() > 1 && !this.a) {
                    for (Integer num : this.l) {
                        if (dCBaseAOLLoader.u() > num.intValue()) {
                            b3.a("check ss smaller than this.ss:" + num + ";unfinished ss count:" + a(num));
                            if (a(num) > 0) {
                                break;
                            }
                        }
                    }
                    this.j = dCBaseAOLLoader;
                    q();
                } else {
                    this.j = dCBaseAOLLoader;
                    q();
                }
            }
            r();
        }
        b(Operators.SUB);
    }

    private int a(Integer num) {
        AtomicInteger atomicInteger = (AtomicInteger) this.m.get(num);
        if (atomicInteger == null) {
            return 0;
        }
        return atomicInteger.get();
    }

    @Override // io.dcloud.p.w.a
    public void a(DCBaseAOLLoader dCBaseAOLLoader, m1 m1Var) {
        if (!this.u.equalsIgnoreCase(dCBaseAOLLoader.t()) || this.p || this.b) {
            return;
        }
        this.d.decrementAndGet();
        this.g.add(dCBaseAOLLoader);
        this.n.add(m1Var);
        f(dCBaseAOLLoader.u());
        b3.b("level ad load fail.current sub slot:" + dCBaseAOLLoader.getSlotId() + ";ss:" + dCBaseAOLLoader.u() + ";type:" + dCBaseAOLLoader.getType());
        if (m()) {
            o();
        } else {
            if (this.l.size() > 1 && !this.a && !d() && this.f.size() > 0) {
                for (Integer num : this.l) {
                    if (dCBaseAOLLoader.u() <= num.intValue()) {
                        Iterator it = this.f.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            DCBaseAOL dCBaseAOL = (DCBaseAOL) it.next();
                            if (dCBaseAOL.u() == num.intValue()) {
                                b3.b("check ss large than this.slot:" + dCBaseAOL.getSlotId() + ";ss:" + dCBaseAOL.u());
                                this.j = dCBaseAOL;
                                q();
                                break;
                            }
                        }
                        if (a(num) > 0) {
                            break;
                        }
                    } else if (dCBaseAOLLoader.u() > num.intValue()) {
                        b3.b("check ss smaller than this.ss:" + num + ";unfinished ss count:" + a(num));
                        if (a(num) > 0) {
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
            r();
        }
        b(Operators.SUB);
    }

    @Override // io.dcloud.p.t1
    public void a(String str) {
        this.u = str;
    }
}
