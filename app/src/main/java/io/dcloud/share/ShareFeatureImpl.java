package io.dcloud.share;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;

/* loaded from: classes3.dex */
public class ShareFeatureImpl implements IFeature {
    private a a;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        a aVar;
        if (str != null || (aVar = this.a) == null) {
            return;
        }
        aVar.a();
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        return this.a.a(iWebview, str, strArr);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.a = new a(absMgr, str);
    }
}
