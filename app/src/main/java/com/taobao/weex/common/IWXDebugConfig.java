package com.taobao.weex.common;

import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.bridge.WXDebugJsBridge;

/* loaded from: classes.dex */
public interface IWXDebugConfig {
    WXDebugJsBridge getWXDebugJsBridge();

    WXBridgeManager getWXJSManager();
}
