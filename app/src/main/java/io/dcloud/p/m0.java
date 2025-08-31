package io.dcloud.p;

import android.app.Activity;
import android.view.ViewGroup;
import io.dcloud.api.custom.UniAdCustomAdapter;
import io.dcloud.api.custom.base.UniAdPrivacyConfig;
import io.dcloud.api.custom.base.UniAdSlot;
import io.dcloud.api.custom.type.UniAdCustomBaseLoader;
import io.dcloud.api.custom.type.feed.UniAdCustomNativeAd;
import io.dcloud.p.v1;
import io.dcloud.sdk.core.adapter.IAdAdapter;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;
import io.dcloud.sdk.core.util.AOLErrorUtil;
import io.dcloud.sdk.core.util.AdUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class m0 extends DCBaseAOLLoader implements v1, v1.a {
    private JSONObject H;
    private UniAdCustomAdapter I;
    private UniAdCustomBaseLoader J;

    public m0(DCloudAOLSlot dCloudAOLSlot, Activity activity) {
        super(dCloudAOLSlot, activity);
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public void biddingFail(int i, int i2, int i3) {
        UniAdCustomBaseLoader uniAdCustomBaseLoader = this.J;
        if (uniAdCustomBaseLoader != null) {
            uniAdCustomBaseLoader.onBidFail(i, i3);
        }
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public void biddingSuccess(int i, int i2) {
        UniAdCustomBaseLoader uniAdCustomBaseLoader = this.J;
        if (uniAdCustomBaseLoader != null) {
            uniAdCustomBaseLoader.onBidSuccess(i, i2);
        }
    }

    @Override // io.dcloud.p.v1.a
    public void c() throws JSONException {
        if (getVideoAdCallback() instanceof y3) {
            ((y3) getVideoAdCallback()).onReward(new JSONObject());
        }
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public void destroy() {
        UniAdCustomBaseLoader uniAdCustomBaseLoader = this.J;
        if (uniAdCustomBaseLoader != null) {
            uniAdCustomBaseLoader.destroy();
        }
    }

    @Override // io.dcloud.p.v1
    public void e() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onVideoPlayEnd();
        }
    }

    @Override // io.dcloud.p.v1
    public void g() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onSkip();
        }
    }

    @Override // io.dcloud.p.v1
    public void h() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onClose();
        }
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    protected void init(String str, String str2) {
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public boolean isValid() {
        UniAdCustomBaseLoader uniAdCustomBaseLoader = this.J;
        return uniAdCustomBaseLoader != null && uniAdCustomBaseLoader.isReady();
    }

    @Override // io.dcloud.p.v1
    public void k() {
        loadSuccess();
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public void load() {
        UniAdSlot uniAdSlot = new UniAdSlot();
        uniAdSlot.setSlotId(getSlotId());
        uniAdSlot.setExtra(getSlot().getExtra());
        uniAdSlot.setUserId(getSlot().getUserId());
        uniAdSlot.setWidth(getSlot().getWidth());
        uniAdSlot.setHeight(getSlot().getHeight());
        uniAdSlot.setAdCount(getSlot().getCount());
        a(getActivity(), uniAdSlot);
    }

    @Override // io.dcloud.p.v1
    public int n() {
        return isSlotSupportBidding() ? 1 : 0;
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public int r() {
        return super.r();
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public void show(Activity activity) {
        if (isValid()) {
            this.J.show(activity);
        } else if (getVideoAdCallback() != null) {
            getVideoAdCallback().onShowError(-5008, AOLErrorUtil.getErrorMsg(-5008));
        }
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public void showIn(ViewGroup viewGroup) {
        if (isValid()) {
            this.J.show(viewGroup);
        } else if (getVideoAdCallback() != null) {
            getVideoAdCallback().onShowError(-5008, AOLErrorUtil.getErrorMsg(-5008));
        }
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public boolean v() {
        return true;
    }

    class a implements UniAdCustomAdapter.a {
        final /* synthetic */ Activity a;
        final /* synthetic */ UniAdSlot b;

        a(Activity activity, UniAdSlot uniAdSlot) {
            this.a = activity;
            this.b = uniAdSlot;
        }

        @Override // io.dcloud.api.custom.UniAdCustomAdapter.a
        public void a() {
            m0.this.J.a(this.a, this.b, m0.this);
            m0.this.I.removeInitListener(this);
        }

        @Override // io.dcloud.api.custom.UniAdCustomAdapter.a
        public void a(int i, String str) {
            m0.this.loadFail(i, str);
            m0.this.I.removeInitListener(this);
        }
    }

    public void a(UniAdCustomBaseLoader uniAdCustomBaseLoader) {
        this.J = uniAdCustomBaseLoader;
    }

    @Override // io.dcloud.p.v1
    public void b(int i, String str) {
        loadFail(i, str);
    }

    public void a(Activity activity, UniAdSlot uniAdSlot) {
        if (this.J != null) {
            UniAdCustomAdapter uniAdCustomAdapter = this.I;
            if (uniAdCustomAdapter != null) {
                if (uniAdCustomAdapter.isInitSuccess()) {
                    this.J.a(activity, uniAdSlot, this);
                    return;
                }
                this.I.addInitListener(new a(activity, uniAdSlot));
                try {
                    this.I.setPrivacyConfig(new UniAdPrivacyConfig(AdUtil.getCustomPrivacyConfig()));
                    this.I.init(getActivity(), this.H);
                    return;
                } catch (Exception unused) {
                    loadFail(-4001, AOLErrorUtil.getErrorMsg(-4001));
                    return;
                }
            }
            loadFail(-4001, AOLErrorUtil.getErrorMsg(-4001));
            return;
        }
        loadFail(-4001, AOLErrorUtil.getErrorMsg(-4001));
    }

    @Override // io.dcloud.p.v1
    public void b() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onShow();
        }
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOLLoader
    public void a(IAdAdapter iAdAdapter, JSONObject jSONObject) {
        this.I = (UniAdCustomAdapter) iAdAdapter;
        this.H = jSONObject;
    }

    @Override // io.dcloud.p.v1
    public void a(List list) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                UniAdCustomNativeAd uniAdCustomNativeAd = (UniAdCustomNativeAd) it.next();
                n0 n0Var = new n0(getSlot(), getActivity());
                n0Var.a(uniAdCustomNativeAd);
                if (isSlotSupportBidding()) {
                    n0Var.setBiddingECPM(uniAdCustomNativeAd.getBidPrice());
                }
                arrayList.add(n0Var);
            }
            loadSuccess(arrayList);
            return;
        }
        loadFail(-5004, AOLErrorUtil.getErrorMsg(200000));
    }

    @Override // io.dcloud.p.v1
    public void a() {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onClick();
        }
    }

    @Override // io.dcloud.p.v1
    public void a(int i, String str) {
        if (getVideoAdCallback() != null) {
            getVideoAdCallback().onShowError(-5100, "type:" + getType() + ";code:" + i + ";message:" + str);
        }
    }
}
