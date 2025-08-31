package io.dcloud.p;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.util.Const;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes3.dex */
public abstract class x3 {
    private boolean a = false;

    protected abstract Activity a();

    protected void a(int i, final String str, String str2, String str3, JSONArray jSONArray, long j) {
        if (jSONArray == null || jSONArray.length() == 0 || i == 14) {
            return;
        }
        this.a = true;
        if (c()) {
            final HashMap map = new HashMap();
            map.put("type", i != 1 ? i != 4 ? i != 7 ? i != 15 ? i != 9 ? i != 10 ? "" : "draw_flow" : "rewarded" : "interstitial" : "full_screen_video" : "template" : "splash");
            map.put("adpid", str);
            map.put("ord", str2);
            if (!TextUtils.isEmpty(str3)) {
                map.put("ext", str3);
            }
            map.put("rsp", jSONArray);
            map.put("tid", 60);
            map.put("lt", Long.valueOf(j));
            v4.a().a(new Runnable() { // from class: io.dcloud.p.x3$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws UnsupportedEncodingException {
                    this.f$0.a(str, map);
                }
            });
        }
    }

    protected void b(Activity activity, DCBaseAOL dCBaseAOL) {
        b3.a("on ad show");
        if (dCBaseAOL == null) {
            return;
        }
        a(activity, 40, dCBaseAOL, dCBaseAOL.t(), String.valueOf(dCBaseAOL.r()), m.b(dCBaseAOL), m.a(dCBaseAOL));
    }

    protected abstract boolean c();

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, HashMap map) throws UnsupportedEncodingException {
        c0.a(a(), r0.d().b().getAppId(), r0.d().b().getAdId(), 60, str, map);
    }

    private void a(final Context context, final int i, final DCBaseAOL dCBaseAOL, final String str, final String str2, final String str3, final String str4) throws JSONException {
        if (i == 40) {
            m.a(context, dCBaseAOL.getDCloudId(), dCBaseAOL.getType());
        }
        final HashMap map = new HashMap();
        if (!TextUtils.isEmpty(dCBaseAOL.s())) {
            map.put("ext", dCBaseAOL.s());
        }
        v4.a().a(new Runnable() { // from class: io.dcloud.p.x3$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws NoSuchMethodException, SecurityException, UnsupportedEncodingException {
                x3.a(context, dCBaseAOL, i, map, str, str2, str3, str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, DCBaseAOL dCBaseAOL, int i, HashMap map, String str, String str2, String str3, String str4) throws NoSuchMethodException, SecurityException, UnsupportedEncodingException {
        c0.a(context, dCBaseAOL.p(), dCBaseAOL.getSlotId(), dCBaseAOL.getTid(), r0.d().b().getAppId(), r0.d().b().getAdId(), i, dCBaseAOL.getDCloudId(), map, str, str2, str3, str4);
    }

    protected void a(Activity activity, DCBaseAOL dCBaseAOL) {
        b3.a("on ad click");
        if (dCBaseAOL == null) {
            return;
        }
        a(activity, 41, dCBaseAOL, null, null, m.b(dCBaseAOL), m.a(dCBaseAOL));
    }

    protected void a(final Activity activity, final DCBaseAOL dCBaseAOL, long j, final String str, final int i) {
        if (dCBaseAOL != null && Const.TYPE_GG.equals(dCBaseAOL.getType())) {
            final float f = j / 1000000.0f;
            b3.a("on ad paid");
            v4.a().a(new Runnable() { // from class: io.dcloud.p.x3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws UnsupportedEncodingException {
                    x3.a(activity, dCBaseAOL, f, str, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Activity activity, DCBaseAOL dCBaseAOL, float f, String str, int i) throws UnsupportedEncodingException {
        c0.a(activity, r0.d().b().getAppId(), dCBaseAOL.getDCloudId(), r0.d().b().getAdId(), dCBaseAOL.p(), dCBaseAOL.getSlotId(), String.format(Locale.ENGLISH, "%f", Float.valueOf(f)), str, i, dCBaseAOL.t());
    }
}
