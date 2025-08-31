package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.component.WXComponent;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes3.dex */
public class GraphicActionRenderSuccess extends BasicGraphicAction {
    public GraphicActionRenderSuccess(WXSDKInstance wXSDKInstance) {
        super(wXSDKInstance, "");
    }

    @Override // com.taobao.weex.ui.action.IExecutable
    public void executeAction() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int layoutWidth;
        int layoutHeight;
        WXSDKInstance wXSDKIntance = getWXSDKIntance();
        if (wXSDKIntance == null || wXSDKIntance.getContext() == null) {
            return;
        }
        WXComponent rootComponent = wXSDKIntance.getRootComponent();
        if (rootComponent != null) {
            layoutWidth = (int) rootComponent.getLayoutWidth();
            layoutHeight = (int) rootComponent.getLayoutHeight();
        } else {
            layoutWidth = 0;
            layoutHeight = 0;
        }
        wXSDKIntance.onRenderSuccess(layoutWidth, layoutHeight);
    }
}
