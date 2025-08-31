package io.dcloud.sdk.base.dcloud;

import android.content.Context;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.p.k3;
import io.dcloud.p.v4;
import io.dcloud.sdk.base.dcloud.ADHandler;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class e extends ADHandler {

    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ HashMap b;

        a(String str, HashMap map) {
            this.a = str;
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                k3.a(this.a, this.b, true);
            } catch (Exception unused) {
            }
        }
    }

    static void a(JSONArray jSONArray, String str, ADHandler.e eVar) {
        HashMap map;
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                String strOptString = jSONArray.optJSONObject(i).optString("url");
                JSONObject jSONObjectC = eVar.c();
                if (jSONObjectC != null && jSONObjectC.has("ua") && jSONObjectC.optString("ua").equalsIgnoreCase("webview")) {
                    map = new HashMap();
                    map.put(IWebview.USER_AGENT, ADHandler.a("ua-webview"));
                } else {
                    map = null;
                }
                v4.a().a(new a(strOptString, map));
            }
        }
    }

    static void d(Context context, ADHandler.e eVar, String str) {
        JSONObject jSONObjectE = eVar.e();
        if (jSONObjectE != null) {
            a(jSONObjectE.optJSONArray("clktracker"), "clktracker", eVar);
        }
        ADHandler.b(context, eVar, str);
    }

    static void a(Context context, ADHandler.e eVar, String str, String str2) {
        JSONObject jSONObjectE = eVar.e();
        if (jSONObjectE != null) {
            a(jSONObjectE.optJSONArray(str2), str2, eVar);
        }
    }
}
