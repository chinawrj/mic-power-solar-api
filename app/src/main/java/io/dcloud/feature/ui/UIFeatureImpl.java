package io.dcloud.feature.ui;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.BaseFeature;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class UIFeatureImpl extends BaseFeature implements IWaiter {
    e a = null;

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.a.b(str);
    }

    @Override // io.dcloud.common.DHInterface.IWaiter
    public Object doForFeature(String str, Object obj) {
        c cVarA;
        if (!"findWebview".equals(str)) {
            return null;
        }
        String[] strArr = (String[]) obj;
        String str2 = strArr[0];
        String str3 = strArr[1];
        a aVar = (a) this.a.b.get(str2);
        if (aVar == null || (cVarA = aVar.a(str3, str3, str3)) == null) {
            return null;
        }
        return cVarA.y.obtainWebView();
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature
    public String execute(IWebview iWebview, String str, JSONArray jSONArray) {
        return AbsoluteConst.UNI_SYNC_EXEC_METHOD.equals(str) ? this.a.a(iWebview, str, jSONArray) : this.a.b(iWebview, str, jSONArray);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.a = new e(absMgr, str);
    }
}
