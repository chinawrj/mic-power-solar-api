package io.dcloud.feature.sensor;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;

/* loaded from: classes3.dex */
public class AccelerometerFeatureImpl implements IFeature {
    private b a;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.a.a(str);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        return this.a.a(iWebview, str, strArr);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.a = new b(absMgr.getContext());
    }
}
