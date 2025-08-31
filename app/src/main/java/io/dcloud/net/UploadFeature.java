package io.dcloud.net;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import java.io.FileNotFoundException;

/* loaded from: classes3.dex */
public class UploadFeature implements IFeature {
    private JsUploadMgr mJsUploadMgr;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) throws NumberFormatException, FileNotFoundException {
        this.mJsUploadMgr.execute(iWebview, str, strArr);
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.mJsUploadMgr = new JsUploadMgr();
    }
}
