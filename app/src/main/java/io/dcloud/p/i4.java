package io.dcloud.p;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.badge.BadgeDrawable;
import io.dcloud.p.b0;
import io.dcloud.sdk.core.adapter.IAdAdapter;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.entry.SplashAOLConfig;
import io.dcloud.sdk.core.interfaces.AOLLoader;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;
import io.dcloud.sdk.core.util.AdSizeUtil;
import io.dcloud.sdk.core.util.MainHandlerUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class i4 extends y4 {
    private SplashAOLConfig x;
    private final Queue y;

    public i4(Activity activity, int i) {
        super(activity, i);
        this.y = new ConcurrentLinkedQueue();
        a(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DCBaseAOLLoader s() {
        IAdAdapter iAdAdapterB = e.b().b("dcloud");
        if (iAdAdapterB == null) {
            return null;
        }
        DCBaseAOLLoader ad = iAdAdapterB.getAd(a(), this.b);
        this.y.add(ad);
        return ad;
    }

    @Override // io.dcloud.p.g4
    protected int d() {
        return 1;
    }

    public SplashAOLConfig t() {
        return this.x;
    }

    protected void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        String strOptString = jSONObject.optString("src");
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        Glide.with(a()).asBitmap().load(strOptString).into((RequestBuilder<Bitmap>) new a(jSONObject));
    }

    public void a(ViewGroup viewGroup) {
        DCBaseAOL dCBaseAOL = this.t;
        if (dCBaseAOL instanceof DCBaseAOLLoader) {
            ((DCBaseAOLLoader) dCBaseAOL).showIn(viewGroup);
        }
    }

    protected void b(final RelativeLayout relativeLayout, final FrameLayout.LayoutParams layoutParams) {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.i4$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(relativeLayout, layoutParams);
            }
        });
    }

    public void a(SplashAOLConfig splashAOLConfig, v2 v2Var) {
        this.x = splashAOLConfig;
        super.a(new DCloudAOLSlot.Builder().height(splashAOLConfig.getHeight()).width(splashAOLConfig.getWidth()).build(), v2Var);
    }

    @Override // io.dcloud.p.w, io.dcloud.p.j
    public DCBaseAOLLoader b() {
        if (this.y.isEmpty()) {
            return s();
        }
        return (DCBaseAOLLoader) this.y.remove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams) {
        v2 v2Var = this.s;
        if (v2Var instanceof AOLLoader.SplashAOLLoadListener) {
            ((AOLLoader.SplashAOLLoadListener) v2Var).redBag(relativeLayout, layoutParams);
        }
    }

    @Override // io.dcloud.p.w, io.dcloud.p.g4
    protected void a(t0 t0Var) {
        super.a(t0Var);
    }

    @Override // io.dcloud.p.w, io.dcloud.p.g4
    protected void a(int i, String str) {
        super.a(i, str);
    }

    protected class b extends b0.b {
        public b() {
            super("");
        }

        @Override // io.dcloud.p.b0.b
        public void a(JSONArray jSONArray) {
        }

        @Override // io.dcloud.p.b0.b
        public void a(JSONArray jSONArray, boolean z) {
            if (jSONArray == null || jSONArray.length() == 0) {
                Iterator it = i4.this.y.iterator();
                while (it.hasNext()) {
                    ((u0) ((DCBaseAOL) it.next())).a((JSONArray) null, true);
                }
                return;
            }
            int i = 0;
            if (i4.this.y.size() <= 0) {
                while (i < jSONArray.length()) {
                    u0 u0Var = (u0) i4.this.s();
                    if (u0Var != null) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(jSONArray.opt(i));
                        u0Var.a(jSONArray2, true);
                        i4.this.y.add(u0Var);
                    }
                    i++;
                }
                return;
            }
            for (DCBaseAOL dCBaseAOL : i4.this.y) {
                if (jSONArray.length() > i) {
                    JSONArray jSONArray3 = new JSONArray();
                    jSONArray3.put(jSONArray.opt(i));
                    ((u0) dCBaseAOL).a(jSONArray3, true);
                } else {
                    ((u0) dCBaseAOL).a((JSONArray) null, true);
                }
                i++;
            }
            if (jSONArray.length() > i) {
                for (int i2 = i; i2 < jSONArray.length(); i2++) {
                    u0 u0Var2 = (u0) i4.this.s();
                    if (u0Var2 != null) {
                        JSONArray jSONArray4 = new JSONArray();
                        jSONArray4.put(jSONArray.opt(i));
                        u0Var2.a(jSONArray4, true);
                        i4.this.y.add(u0Var2);
                    }
                }
            }
        }

        @Override // io.dcloud.p.b0.b, io.dcloud.p.b0.a
        public void a(JSONObject jSONObject) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            super.a(jSONObject);
            try {
                JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
                jSONObject2.remove("cfgs");
                i4.this.s.getClass().getDeclaredMethod("getConfig", JSONObject.class).invoke(i4.this.s, jSONObject2);
            } catch (Exception unused) {
            }
            i4.this.a(jSONObject);
        }

        @Override // io.dcloud.p.b0.b, io.dcloud.p.b0.a
        public void a(int i, String str) {
            super.a(i, str);
            i4.this.b(i, str);
        }
    }

    class a extends CustomTarget {
        final /* synthetic */ JSONObject a;

        a(JSONObject jSONObject) {
            this.a = jSONObject;
        }

        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(Bitmap bitmap, Transition transition) {
            if (this.a.optJSONObject("pos") == null) {
                return;
            }
            final RelativeLayout relativeLayout = new RelativeLayout(i4.this.a());
            ImageView imageView = new ImageView(i4.this.a());
            int iPxFromDp = AdSizeUtil.pxFromDp(r12.optInt("width", -2), i4.this.a().getResources().getDisplayMetrics());
            int iPxFromDp2 = AdSizeUtil.pxFromDp(r12.optInt("height", -2), i4.this.a().getResources().getDisplayMetrics());
            int iPxFromDp3 = AdSizeUtil.pxFromDp(r12.optInt("left", -1), i4.this.a().getResources().getDisplayMetrics());
            int iPxFromDp4 = AdSizeUtil.pxFromDp(r12.optInt("right", -1), i4.this.a().getResources().getDisplayMetrics());
            int iPxFromDp5 = AdSizeUtil.pxFromDp(r12.optInt("top", -1), i4.this.a().getResources().getDisplayMetrics());
            int iPxFromDp6 = AdSizeUtil.pxFromDp(r12.optInt("bottom", -1), i4.this.a().getResources().getDisplayMetrics());
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            relativeLayout.addView(imageView, new ViewGroup.LayoutParams(iPxFromDp, iPxFromDp2));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            if (iPxFromDp4 >= 0) {
                layoutParams.rightMargin = iPxFromDp4;
            } else if (iPxFromDp3 >= 0) {
                layoutParams.leftMargin = iPxFromDp3;
            }
            if (iPxFromDp6 >= 0) {
                layoutParams.bottomMargin = iPxFromDp6;
            } else if (iPxFromDp5 >= 0) {
                layoutParams.topMargin = iPxFromDp5;
            }
            if (iPxFromDp3 >= 0) {
                if (iPxFromDp5 >= 0) {
                    layoutParams.gravity = BadgeDrawable.TOP_START;
                }
                if (iPxFromDp6 >= 0) {
                    layoutParams.gravity = BadgeDrawable.BOTTOM_START;
                }
            }
            if (iPxFromDp4 >= 0) {
                if (iPxFromDp5 >= 0) {
                    layoutParams.gravity = BadgeDrawable.TOP_END;
                }
                if (iPxFromDp6 >= 0) {
                    layoutParams.gravity = BadgeDrawable.BOTTOM_END;
                }
            }
            final JSONObject jSONObject = this.a;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.p.i4$a$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.a(relativeLayout, jSONObject, view);
                }
            });
            i4.this.b(relativeLayout, layoutParams);
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(RelativeLayout relativeLayout, final JSONObject jSONObject, View view) {
            if (relativeLayout.getParent() != null) {
                ((ViewGroup) relativeLayout.getParent()).removeView(relativeLayout);
            }
            String strOptString = jSONObject.optString("click_action", "");
            if (strOptString.equals("browser")) {
                c.c(i4.this.a(), jSONObject.optString("url"));
            } else if (strOptString.equals("url")) {
                c.e(i4.this.a(), jSONObject.optString("url"));
            }
            final String appId = r0.d().b().getAppId();
            final String adId = r0.d().b().getAdId();
            v4.a().a(new Runnable() { // from class: io.dcloud.p.i4$a$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.a(appId, jSONObject, adId);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(String str, JSONObject jSONObject, String str2) {
            c0.a(i4.this.a(), str, jSONObject.optString("tid"), str2, 10, "");
        }
    }
}
