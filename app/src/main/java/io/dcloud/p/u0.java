package io.dcloud.p;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.nostra13.dcloudimageloader.core.download.BaseImageDownloader;
import io.dcloud.WebAppActivity;
import io.dcloud.p.u2;
import io.dcloud.sdk.base.dcloud.ADHandler;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;
import io.dcloud.sdk.core.util.AOLErrorUtil;
import io.dcloud.sdk.core.util.MainHandlerUtil;
import io.dcloud.sdk.poly.api.Platform;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class u0 extends DCBaseAOLLoader implements u2.c {
    private u4 H;
    private Platform I;
    private String J;
    private ADHandler.e K;
    private Handler L;
    private int M;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            u0.this.loadFail(-9999, "");
        }
    }

    class b implements n {
        b() {
        }

        @Override // io.dcloud.p.n
        public void onClicked() {
            if (u0.this.getVideoAdCallback() != null) {
                u0.this.getVideoAdCallback().onClick();
            }
        }

        @Override // io.dcloud.p.n
        public void onFinishShow() {
            if (u0.this.getVideoAdCallback() != null) {
                u0.this.getVideoAdCallback().onClose();
            }
        }

        @Override // io.dcloud.p.n
        public void onShow() {
            if (u0.this.getVideoAdCallback() != null) {
                u0.this.getVideoAdCallback().onShow();
            }
        }
    }

    class c implements ADHandler.g {
        c() {
        }

        @Override // io.dcloud.sdk.base.dcloud.ADHandler.g
        public void a() {
            if (u0.this.getAdStatus() != -1) {
                return;
            }
            ADHandler.e eVarB = ADHandler.b(u0.this.getActivity(), r0.d().b().getAppId());
            if (!eVarB.a()) {
                u0.this.loadFail(-9999, "");
                return;
            }
            u0.this.K = eVarB;
            u0.this.K.a(u0.this.getDCloudId());
            u0.this.loadSuccess();
        }

        @Override // io.dcloud.sdk.base.dcloud.ADHandler.g
        public void b() {
            if (u0.this.getAdStatus() != -1) {
                return;
            }
            ADHandler.e eVarB = ADHandler.b(u0.this.getActivity(), r0.d().b().getAppId());
            if (!eVarB.a()) {
                u0.this.loadFail(-9999, "");
                return;
            }
            u0.this.K = eVarB;
            u0.this.K.a(u0.this.getDCloudId());
            u0.this.loadSuccess();
        }
    }

    public u0(DCloudAOLSlot dCloudAOLSlot, Activity activity) {
        super(dCloudAOLSlot, activity);
        this.J = "";
        this.L = new a(Looper.getMainLooper());
        this.M = BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D() throws JSONException, UnsupportedEncodingException {
        u4 u4VarA = s0.a(this, getActivity(), this.I.getSplash(), getSlotId(), this.I.getEr(), this.I.getEc());
        this.H = u4VarA;
        u4VarA.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(ViewGroup viewGroup) {
        this.H.a(viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(ViewGroup viewGroup) {
        new io.dcloud.sdk.base.dcloud.g(getActivity(), this.K, viewGroup, new b()).b();
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public void destroy() {
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public String getTid() {
        Platform platform = this.I;
        if (platform != null) {
            return platform.getTid();
        }
        ADHandler.e eVar = this.K;
        return eVar != null ? eVar.l : "";
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public String getType() {
        Platform platform = this.I;
        return platform == null ? this.J : platform.getType();
    }

    @Override // io.dcloud.p.u2.c
    public void i() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onClose();
        }
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public void init(String str, String str2) {
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public boolean isValid() {
        return true;
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public void load() {
        if (TextUtils.isEmpty(this.J)) {
            loadFail(-9999, "");
            return;
        }
        if (this.J.equals("dcloud")) {
            b3.a("uniAd", "load base");
            this.L.sendEmptyMessageDelayed(this.M, WebAppActivity.SPLASH_SECOND);
        } else if (this.I == null) {
            loadFail(-9999, "");
        } else {
            MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.u0$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() throws JSONException, UnsupportedEncodingException {
                    this.f$0.D();
                }
            });
        }
    }

    @Override // io.dcloud.p.u2.c
    public void m() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onSkip();
        }
    }

    @Override // io.dcloud.p.u2.c
    public void o() {
        loadSuccess();
    }

    @Override // io.dcloud.p.u2.c
    public void onError(int i, String str) {
        loadFail(i, str);
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public String p() {
        Platform platform = this.I;
        return platform == null ? "" : platform.getAppid();
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public boolean runOnMain() {
        return false;
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public void setPlatform(Platform platform, String str) {
        this.I = platform;
        this.J = str;
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public void showIn(final ViewGroup viewGroup) {
        if ((this.H == null && this.K == null) || TextUtils.isEmpty(this.J)) {
            if (getVideoAdCallback() != null) {
                getVideoAdCallback().onShowError(-5008, AOLErrorUtil.getErrorMsg(-5008));
                return;
            }
            return;
        }
        if (viewGroup == null) {
            if (getVideoAdCallback() != null) {
                getVideoAdCallback().onShowError(-5014, AOLErrorUtil.getErrorMsg(-5014));
            }
        } else {
            if (this.J.equals("dcloud")) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    a(viewGroup);
                    return;
                } else {
                    MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.u0$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.a(viewGroup);
                        }
                    });
                    return;
                }
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.H.a(viewGroup);
            } else {
                MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.u0$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.b(viewGroup);
                    }
                });
            }
        }
    }

    @Override // io.dcloud.p.u2.c
    public void b() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onShow();
        }
    }

    @Override // io.dcloud.p.u2.c
    public void a() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onClick();
        }
    }

    @Override // io.dcloud.p.u2.c
    public void a(int i, String str) {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onShowError(-5100, "code" + i + ";message:" + str);
        }
    }

    public void a(JSONArray jSONArray, boolean z) {
        b3.a("uniAd-finish", String.valueOf(jSONArray) + "::::::" + z);
        if (this.L.hasMessages(this.M)) {
            this.L.removeMessages(this.M);
            if (z) {
                if (jSONArray != null && jSONArray.length() != 0) {
                    c cVar = new c();
                    boolean z2 = true;
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                        if (jSONObjectOptJSONObject != null) {
                            ADHandler.a(getActivity(), jSONObjectOptJSONObject, System.currentTimeMillis(), cVar);
                            z2 = false;
                        }
                    }
                    if (z2) {
                        loadFail(-9999, "");
                        return;
                    }
                    return;
                }
                ADHandler.e eVarB = ADHandler.b(getActivity(), r0.d().b().getAppId());
                if (eVarB.a()) {
                    this.K = eVarB;
                    eVarB.a(getDCloudId());
                    loadSuccess();
                    return;
                }
                loadFail(-9999, "");
                return;
            }
            loadFail(-9999, "");
        }
    }
}
