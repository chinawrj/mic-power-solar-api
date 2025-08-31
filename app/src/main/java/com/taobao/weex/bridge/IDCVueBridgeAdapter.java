package com.taobao.weex.bridge;

import com.taobao.weex.WXSDKInstance;

/* loaded from: classes.dex */
public interface IDCVueBridgeAdapter {
    void exec(WXSDKInstance wXSDKInstance, String str, String str2);

    String execSync(WXSDKInstance wXSDKInstance, String str, String str2);
}
