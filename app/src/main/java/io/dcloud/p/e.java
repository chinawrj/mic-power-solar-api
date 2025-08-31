package io.dcloud.p;

import android.util.Log;
import io.dcloud.sdk.core.adapter.IAdAdapter;
import io.dcloud.sdk.core.util.Const;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class e {
    private static volatile e d;
    private final Map a = new HashMap();
    private final Map b = new a();
    private final Map c;

    class a extends HashMap {
        a() {
            put(Const.TYPE_GG, "io.dcloud.sdk.poly.adapter.google.AdMobAdAdapter");
            put(Const.TYPE_PG, "io.dcloud.sdk.poly.adapter.pg.PGADAdapter");
        }
    }

    class b extends HashMap {
        b() {
            put(Const.TYPE_BD, "io.dcloud.sdk.poly.adapter.bd.BDAdAdapter");
            put(Const.TYPE_CSJ, "io.dcloud.sdk.poly.adapter.csj.CSJAdAdapter");
            put(Const.TYPE_GDT, "io.dcloud.sdk.poly.adapter.gdt.GDTADAdapter");
            put(Const.TYPE_SGM, "io.dcloud.sdk.poly.adapter.sgm.SGMADAdapter");
            put(Const.TYPE_KS, "io.dcloud.sdk.poly.adapter.ks.KSADAdapter");
            put(Const.TYPE_HW, "io.dcloud.sdk.poly.adapter.hw.HWAdAdapter");
            put(Const.TYPE_GM, "io.dcloud.sdk.poly.adapter.gm.DGMAdAdapter");
            putAll(e.this.b);
            put(Const.TYPE_WM, z4.class.getName());
        }
    }

    private e() {
        b bVar = new b();
        this.c = bVar;
        for (K k : bVar.keySet()) {
            IAdAdapter iAdAdapterA = a((String) this.c.get(k), k);
            if (iAdAdapterA != null) {
                this.a.put(k, iAdAdapterA);
            }
        }
    }

    public static e b() {
        if (d == null) {
            synchronized (e.class) {
                if (d == null) {
                    d = new e();
                }
            }
        }
        return d;
    }

    public String c(String str) {
        return this.b.containsKey(str) ? Const.PROVIDER_TYPE_GLOBAL : Const.PROVIDER_TYPE_CHINA;
    }

    public Map a() {
        return this.a;
    }

    public List c() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(this.a.keySet());
        } catch (Exception unused) {
        }
        if (!arrayList.contains("dcloud")) {
            arrayList.add("dcloud");
        }
        return arrayList;
    }

    private IAdAdapter a(String str, String str2) {
        try {
            IAdAdapter iAdAdapter = (IAdAdapter) Class.forName(str).newInstance();
            if (!iAdAdapter.getAdapterSDKVersion().equalsIgnoreCase("1.9.9.82448")) {
                Log.e("uni-AD", str2 + " adapter version not match");
            }
            if (iAdAdapter.getSDKVersion() != null) {
                b3.a("uniAD", "SDKVersion:" + str2 + ":" + iAdAdapter.getSDKVersion());
            }
            return iAdAdapter;
        } catch (Exception unused) {
            return null;
        }
    }

    public IAdAdapter b(String str) {
        return (IAdAdapter) this.a.get(str);
    }

    public boolean a(String str) {
        return this.c.containsKey(str);
    }

    public void a(String str, IAdAdapter iAdAdapter) {
        this.a.put(str, iAdAdapter);
    }
}
