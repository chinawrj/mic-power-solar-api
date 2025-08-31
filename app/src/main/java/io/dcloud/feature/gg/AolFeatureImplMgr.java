package io.dcloud.feature.gg;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.feature.gg.dcloud.AolFeatureImpl;
import io.dcloud.p.s3;
import java.io.IOException;

/* loaded from: classes3.dex */
public class AolFeatureImplMgr implements IReflectAble, IWaiter {
    public static final /* synthetic */ int a = 0;
    static final AolFeatureImplMgr mSingleInstance = new AolFeatureImplMgr();
    String mAdType = null;

    public static IWaiter self() {
        return mSingleInstance;
    }

    public void clearAdType() {
        this.mAdType = null;
    }

    @Override // io.dcloud.common.DHInterface.IWaiter
    public Object doForFeature(String str, Object obj) throws IOException, ClassNotFoundException {
        if (TextUtils.isEmpty(this.mAdType)) {
            this.mAdType = AolSplashUtil.getPlashType();
        }
        Object objDoForFeature = AolFeatureImpl.doForFeature(str, obj);
        if (str.equals("onWillCloseSplash") || str.equals("onBack")) {
            clearAdType();
        } else if (str.equals("adc")) {
            return Boolean.valueOf(s3.a());
        }
        return objDoForFeature;
    }
}
