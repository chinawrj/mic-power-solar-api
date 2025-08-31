package io.dcloud.p;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.net.NetWork;
import io.dcloud.p.u2;
import io.dcloud.sdk.base.entry.AdData;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class u4 extends u2 {
    ExecutorService e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private JSONObject k;
    private HashMap l;
    private String m;
    private String n;

    class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException, IllegalAccessException, SecurityException, IOException, IllegalArgumentException {
            String[] strArr = new String[1];
            byte[] bArrA = k3.a(u4.this.g, this.a, u4.this.l, strArr);
            if (bArrA == null) {
                u4 u4Var = u4.this;
                StringBuilder sb = new StringBuilder("网络请求失败：");
                String str = strArr[0];
                if (str == null) {
                    str = "data null";
                }
                sb.append(str);
                u4Var.a(60003, sb.toString());
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(new String(bArrA));
                AdData adData = new AdData();
                adData.c(u4.this.b());
                jSONObject.put("appid", u4.this.f);
                jSONObject.put("adpid", u4.this.c);
                jSONObject.put("tid", u4.this.h);
                jSONObject.put("did", u4.this.m);
                jSONObject.put("adid", u4.this.n);
                adData.a(jSONObject, new C0073a(adData), true);
            } catch (JSONException e) {
                u4.this.a(60006, e.getMessage());
            }
        }

        /* renamed from: io.dcloud.p.u4$a$a, reason: collision with other inner class name */
        class C0073a implements AdData.e {
            final /* synthetic */ AdData a;

            C0073a(AdData adData) {
                this.a = adData;
            }

            @Override // io.dcloud.sdk.base.entry.AdData.e
            public void a() {
                u4.this.d = this.a;
                u4.this.a();
            }

            @Override // io.dcloud.sdk.base.entry.AdData.e
            public void a(int i, String str) {
                try {
                    for (io.dcloud.sdk.base.entry.a aVar : j4.a().b(u4.this.b()).values()) {
                        if (!aVar.b()) {
                            AdData adData = new AdData();
                            aVar.a(u4.this.b(), adData);
                            if (adData.n() && adData.m() && adData.c().equals(u4.this.f) && adData.j().equals(u4.this.h) && u4.this.d == null) {
                                u4.this.d = adData;
                                u4.this.a();
                                return;
                            }
                        } else {
                            j4.a().b(u4.this.b(), aVar.a());
                        }
                    }
                } catch (Exception unused) {
                }
                u4.this.a(i, str);
            }
        }
    }

    public u4(u2.c cVar, Context context, String str, String str2, String str3, String str4) {
        this(cVar, context, str2);
        this.i = str3;
        this.j = str4;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.m = jSONObject.optString("did");
            this.n = jSONObject.optString("adid");
            this.g = jSONObject.optString("url");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(AbsoluteConst.XML_APP);
            this.k = jSONObjectOptJSONObject;
            this.f = jSONObjectOptJSONObject.optString("app_id");
            this.h = jSONObject.optString("tid");
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(WXBasicComponentType.HEADER);
            if (jSONObjectOptJSONObject2 == null || jSONObjectOptJSONObject2.length() <= 0) {
                return;
            }
            this.l = new HashMap();
            Iterator<String> itKeys = jSONObjectOptJSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                this.l.put(next, jSONObjectOptJSONObject2.optString(next));
            }
        } catch (JSONException unused) {
        }
    }

    public void c() throws JSONException, UnsupportedEncodingException {
        if (TextUtils.isEmpty(this.g)) {
            a(60001, "广告请求地址出错");
            return;
        }
        JSONObject jSONObject = this.k;
        if (jSONObject != null && jSONObject.length() > 0) {
            if (!TextUtils.isEmpty(this.c)) {
                try {
                    this.k.put("adp_id", this.c);
                } catch (JSONException unused) {
                }
            }
            JSONObject jSONObjectA = l4.a(b());
            try {
                jSONObjectA.put(AbsoluteConst.XML_APP, this.k);
            } catch (JSONException unused2) {
            }
            String string = jSONObjectA.toString();
            if ("1".equals(this.i)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(this.j);
                    jSONObject2.optString("method");
                    String strEncodeToString = Base64.encodeToString(d.b(d5.a(string), jSONObject2.optString(IApp.ConfigProperty.CONFIG_KEY), jSONObject2.optString("iv")), 2);
                    try {
                        strEncodeToString = URLEncoder.encode(strEncodeToString, "utf-8");
                    } catch (UnsupportedEncodingException unused3) {
                    }
                    string = "edata=" + strEncodeToString;
                } catch (Exception unused4) {
                }
            } else {
                if (this.l == null) {
                    this.l = new HashMap();
                }
                if (!this.l.containsKey(NetWork.CONTENT_TYPE)) {
                    this.l.put(NetWork.CONTENT_TYPE, "application/json");
                }
            }
            this.e.execute(new a(string));
            return;
        }
        a(60002, "广告配置异常");
    }

    private u4(u2.c cVar, Context context, String str) {
        super(cVar, context, str);
        this.e = Executors.newSingleThreadExecutor();
        this.f = "";
        this.g = "";
        this.i = "";
        this.j = "";
    }
}
