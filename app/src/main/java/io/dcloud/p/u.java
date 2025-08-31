package io.dcloud.p;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.common.callercontext.ContextChain;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.gg.AolSplashUtil;
import io.dcloud.feature.gg.dcloud.ADHandler;
import io.dcloud.feature.gg.dcloud.GGSplashView;
import io.dcloud.feature.ui.navigator.QueryNotchTool;
import io.dcloud.p.i4;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.entry.SplashAOLConfig;
import io.dcloud.sdk.core.interfaces.AOLLoader;
import io.dcloud.sdk.core.util.AOLErrorUtil;
import io.dcloud.sdk.core.util.Const;
import io.dcloud.sdk.core.util.MainHandlerUtil;
import io.dcloud.sdk.core.v3.inters.DCIntAOL;
import io.dcloud.sdk.core.v3.inters.DCIntAOLListener;
import io.dcloud.sdk.core.v3.inters.DCIntAOLLoadListener;
import io.dcloud.sdk.core.v3.sp.DCSplashAOLLoadListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class u extends i4 {
    private int A;
    private String B;
    private boolean C;
    h4 D;
    private String E;
    private String F;
    private boolean G;
    private boolean H;
    GGSplashView I;
    long J;
    private long K;
    DCIntAOL L;
    private boolean M;
    private boolean N;
    private boolean z;

    class b implements AOLLoader.VAOLInteractionListener {
        b() {
        }

        @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
        public void onClick() {
        }

        @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
        public void onClose() {
            u.this.u();
        }

        @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
        public void onPaidGet(long j, String str, int i) {
        }

        @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
        public void onShow() {
        }

        @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
        public void onShowError(int i, String str) {
            u.this.u();
        }

        @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
        public void onSkip() {
            u.this.u();
        }

        @Override // io.dcloud.sdk.core.interfaces.AOLLoader.VAOLInteractionListener
        public void onVideoPlayEnd() {
            u.this.u();
        }
    }

    class c implements DCIntAOLListener {
        c() {
        }

        @Override // io.dcloud.sdk.core.v3.inters.DCIntAOLListener
        public void onClick() {
        }

        @Override // io.dcloud.sdk.core.v3.inters.DCIntAOLListener
        public void onClose() {
            AolSplashUtil.setShowInterstitialAd(false);
        }

        @Override // io.dcloud.sdk.core.v3.inters.DCIntAOLListener
        public void onShow() {
            AolSplashUtil.setShowInterstitialAd(true);
        }

        @Override // io.dcloud.sdk.core.v3.inters.DCIntAOLListener
        public void onShowError(int i, String str) {
            AolSplashUtil.setShowInterstitialAd(false);
        }

        @Override // io.dcloud.sdk.core.v3.inters.DCIntAOLListener
        public void onSkip() {
        }

        @Override // io.dcloud.sdk.core.v3.inters.DCIntAOLListener
        public void onVideoPlayEnd() {
        }
    }

    class d implements DCIntAOLLoadListener {
        d() {
        }

        @Override // io.dcloud.sdk.core.v3.base.DCBaseAOLLoadListener
        public void onError(int i, String str, JSONArray jSONArray) {
            u.this.M = true;
        }

        @Override // io.dcloud.sdk.core.v3.inters.DCIntAOLLoadListener
        public void onInterstitialAOLLoad() {
            u.this.M = true;
            if (!u.this.N || u.this.K <= 0 || u.this.K <= SystemClock.elapsedRealtime()) {
                return;
            }
            u uVar = u.this;
            uVar.L.show(uVar.a());
        }
    }

    public u(Activity activity) {
        super(activity, 1);
        this.z = false;
        this.A = 0;
        this.B = "";
        this.C = false;
        this.E = "";
        this.F = "";
        this.G = false;
        this.H = false;
        this.J = 0L;
        this.K = 0L;
        this.M = false;
        this.N = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        GGSplashView gGSplashView = this.I;
        if (gGSplashView != null) {
            gGSplashView.onFinishShow();
        }
    }

    public void v() {
        this.M = false;
        if (TextUtils.isEmpty(this.B) || AolSplashUtil.isShowingInterstitialAd()) {
            return;
        }
        DCloudAOLSlot dCloudAOLSlotBuild = new DCloudAOLSlot.Builder().adpid(this.B).build();
        DCIntAOL dCIntAOL = new DCIntAOL(a());
        this.L = dCIntAOL;
        dCIntAOL.setInterstitialAOLListener(new c());
        this.L.load(dCloudAOLSlotBuild, new d());
    }

    public void w() {
        if (this.M) {
            DCIntAOL dCIntAOL = this.L;
            if (dCIntAOL == null || !dCIntAOL.isValid()) {
                return;
            }
            this.L.show(a());
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int i = this.A;
        if (i <= 0) {
            i = 2500;
        }
        this.K = jElapsedRealtime + i;
        this.N = true;
    }

    @Override // io.dcloud.p.i4, io.dcloud.p.g4
    protected int d() {
        if (this.G) {
            return 3;
        }
        return super.d();
    }

    @Override // io.dcloud.p.i4
    protected void b(final RelativeLayout relativeLayout, final FrameLayout.LayoutParams layoutParams) {
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.u$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c(relativeLayout, layoutParams);
            }
        });
    }

    @Override // io.dcloud.p.y4, io.dcloud.p.w
    protected void c(List list) {
        super.c(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams) {
        v2 v2Var = this.s;
        if (v2Var instanceof h4) {
            ((h4) v2Var).redBag(relativeLayout, layoutParams);
        }
    }

    class a extends i4.b {
        final /* synthetic */ boolean d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(boolean z) {
            super();
            this.d = z;
        }

        @Override // io.dcloud.p.i4.b, io.dcloud.p.b0.b
        public void a(JSONArray jSONArray) {
            if (jSONArray == null || jSONArray.length() <= 0) {
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.optString("action", "").equals("redPackage")) {
                    u.this.b(jSONObjectOptJSONObject);
                }
            }
        }

        @Override // io.dcloud.p.i4.b, io.dcloud.p.b0.b, io.dcloud.p.b0.a
        public void a(JSONObject jSONObject) throws JSONException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (jSONObject == null || jSONObject.length() == 0) {
                u.this.a(-5001, AOLErrorUtil.getErrorMsg(-5001));
                return;
            }
            SplashAOLConfig splashAOLConfigT = u.this.t();
            h4 h4Var = u.this.D;
            if (h4Var != null) {
                try {
                    h4Var.a(jSONObject);
                    splashAOLConfigT = u.this.D.b();
                    if (!u.this.D.a()) {
                        super.a(-5000, "");
                        return;
                    }
                } catch (Exception unused) {
                }
                if (!m.d(u.this.a())) {
                    super.a(-5000, "");
                    return;
                }
            }
            if (splashAOLConfigT != null) {
                u.this.a(new DCloudAOLSlot.Builder().height(splashAOLConfigT.getHeight()).width(splashAOLConfigT.getWidth()).build());
            }
            if (this.d && io.dcloud.p.e.b().c().contains(Const.TYPE_HW)) {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("cfgs");
                ArrayList arrayList = new ArrayList();
                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                        if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.optString(ContextChain.TAG_PRODUCT).equals(Const.TYPE_HW)) {
                            arrayList.add(Integer.valueOf(i));
                        }
                    }
                    if (arrayList.size() > 0) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            jSONArrayOptJSONArray.remove(((Integer) it.next()).intValue());
                        }
                        try {
                            jSONObject.put("cfgs", jSONArrayOptJSONArray);
                        } catch (JSONException unused2) {
                        }
                    }
                }
            }
            super.a(jSONObject);
        }

        @Override // io.dcloud.p.i4.b, io.dcloud.p.b0.b, io.dcloud.p.b0.a
        public void a(int i, String str) {
            super.a(i, str);
        }
    }

    @Override // io.dcloud.p.y4, io.dcloud.p.w
    protected void a(int i, String str, JSONArray jSONArray) {
        super.a(i, str, jSONArray);
    }

    public void a(SplashAOLConfig splashAOLConfig, DCSplashAOLLoadListener dCSplashAOLLoadListener, boolean z) {
        this.I = null;
        this.G = z;
        a(new a(z));
        a(dCSplashAOLLoadListener, z);
        this.J = SystemClock.elapsedRealtime();
        this.z = false;
        this.H = false;
        super.a(splashAOLConfig, this.D);
    }

    class e implements h4 {
        final /* synthetic */ DCSplashAOLLoadListener a;
        final /* synthetic */ boolean b;

        e(DCSplashAOLLoadListener dCSplashAOLLoadListener, boolean z) {
            this.a = dCSplashAOLLoadListener;
            this.b = z;
        }

        @Override // io.dcloud.p.h4
        public void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    u.this.C = jSONObject.optInt("fs", 0) == 1;
                    u.this.E = jSONObject.optString("fr");
                    u.this.F = jSONObject.optString("frt");
                    if (jSONObject.has("cpadpid")) {
                        u.this.B = jSONObject.optString("cpadpid");
                        u.this.A = jSONObject.optInt("fwt");
                        u uVar = u.this;
                        uVar.A = uVar.A <= 0 ? 2500 : u.this.A;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // io.dcloud.p.h4
        public SplashAOLConfig b() {
            return new SplashAOLConfig.Builder().width(a((Context) u.this.c)).height(u.this.C ? a(u.this.c) : (a(u.this.c) / 20) * 17).build();
        }

        @Override // io.dcloud.p.v2
        public void onError(int i, String str, JSONArray jSONArray) {
            u.this.z = true;
            u.this.H = false;
            u.this.a(false);
            DCSplashAOLLoadListener dCSplashAOLLoadListener = this.a;
            if (dCSplashAOLLoadListener != null) {
                dCSplashAOLLoadListener.onError(i, str, jSONArray);
            }
        }

        @Override // io.dcloud.p.v2
        public void onLoaded() {
            u.this.z = true;
            u.this.H = true;
            u.this.a(false);
            DCSplashAOLLoadListener dCSplashAOLLoadListener = this.a;
            if (dCSplashAOLLoadListener != null) {
                dCSplashAOLLoadListener.onSplashAOLLoad();
            }
        }

        @Override // io.dcloud.p.h4
        public void redBag(View view, FrameLayout.LayoutParams layoutParams) {
            DCSplashAOLLoadListener dCSplashAOLLoadListener = this.a;
            if (dCSplashAOLLoadListener != null) {
                dCSplashAOLLoadListener.redBag(view, layoutParams);
            }
        }

        private int a(Activity activity) {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            int i = displayMetrics.heightPixels;
            return (PdrUtil.hasNavBar(activity) && PdrUtil.isNavigationBarShowing(activity)) ? i - PdrUtil.getNavigationBarHeight(activity) : i;
        }

        private int a(Context context) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }

        @Override // io.dcloud.p.h4
        public boolean a() {
            boolean z;
            return ADHandler.SplashAdIsEnable(u.this.a()).booleanValue() && (!(z = this.b) || (z && "1".equals(u.this.E)));
        }
    }

    @Override // io.dcloud.p.i4
    public void a(ViewGroup viewGroup) {
        a(new b());
        super.a(viewGroup);
    }

    public void a(View view) {
        GGSplashView gGSplashView = this.I;
        if (gGSplashView != null) {
            gGSplashView.onWillCloseSplash();
        }
    }

    public View a(Activity activity, String str, ICallBack iCallBack) {
        if (!this.z || !this.H) {
            return null;
        }
        if (this.I == null) {
            GGSplashView gGSplashView = new GGSplashView(activity);
            this.I = gGSplashView;
            gGSplashView.showAd(this);
        }
        if (this.C) {
            this.I.getBottomIcon().setVisibility(8);
        }
        this.I.setPullTime(this.J);
        this.I.setAppid(str);
        this.I.setCallBack(iCallBack);
        return this.I;
    }

    public void a(final Activity activity, String str, final ViewGroup viewGroup) {
        v();
        if (this.z && this.H) {
            a(activity, str, new ICallBack() { // from class: io.dcloud.p.u$$ExternalSyntheticLambda0
                @Override // io.dcloud.common.DHInterface.ICallBack
                public final Object onCallBack(int i, Object obj) {
                    return this.f$0.a(viewGroup, activity, i, obj);
                }
            });
            viewGroup.addView(this.I);
            if (BaseInfo.sGlobalFullScreen) {
                return;
            }
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags |= 1024;
            if (QueryNotchTool.hasNotchInScreen(activity) && Build.VERSION.SDK_INT >= 28) {
                attributes.layoutInDisplayCutoutMode = 1;
            }
            window.setAttributes(attributes);
            return;
        }
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object a(ViewGroup viewGroup, Activity activity, int i, Object obj) {
        viewGroup.removeView(this.I);
        if (!BaseInfo.sGlobalFullScreen) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags &= -1025;
            if (QueryNotchTool.hasNotchInScreen(activity) && Build.VERSION.SDK_INT >= 28) {
                attributes.layoutInDisplayCutoutMode = 0;
            }
            window.setAttributes(attributes);
        }
        w();
        return null;
    }

    public boolean a(long j) throws NumberFormatException {
        long j2;
        try {
            j2 = Long.parseLong(this.F);
        } catch (Exception unused) {
            j2 = 0;
        }
        if (j2 <= 0) {
            j2 = 180000;
        }
        return j + j2 < SystemClock.elapsedRealtime() && ("1".equals(this.E) || !TextUtils.isEmpty(this.B));
    }

    private void a(DCSplashAOLLoadListener dCSplashAOLLoadListener, boolean z) {
        this.D = new e(dCSplashAOLLoadListener, z);
    }
}
