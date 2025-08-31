package com.taobao.weex.dom;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.component.WXComponent;

/* loaded from: classes.dex */
public interface RenderContext {
    WXComponent getComponent(String str);

    WXSDKInstance getInstance();

    WXComponent unregisterComponent(String str);
}
