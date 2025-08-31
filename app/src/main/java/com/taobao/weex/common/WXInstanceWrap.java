package com.taobao.weex.common;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;

/* loaded from: classes.dex */
public class WXInstanceWrap extends WXModule {
    @JSMethod
    public void error(String str, String str2, String str3) {
        WXSDKInstance parentInstance = this.mWXSDKInstance;
        if (parentInstance != null) {
            if (str3 != null && str3.contains("downgrade_to_root")) {
                while (parentInstance.getParentInstance() != null) {
                    parentInstance = parentInstance.getParentInstance();
                }
            }
            parentInstance.onRenderError(str + "|" + str2, str3);
        }
    }
}
