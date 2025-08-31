package io.dcloud.invocation;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.p.w2;

/* loaded from: classes3.dex */
public class Invocation implements IFeature {
    w2 a;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.a.a(str);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        return (!SDK.isUniMPSDK() || SDK.isNJS) ? this.a.a(iWebview, str, strArr) : "";
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.a = new w2(absMgr);
    }
}
