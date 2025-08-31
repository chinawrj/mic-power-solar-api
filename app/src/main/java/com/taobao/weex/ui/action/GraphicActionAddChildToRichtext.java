package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.richtext.WXRichText;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class GraphicActionAddChildToRichtext extends BasicGraphicAction {
    public GraphicActionAddChildToRichtext(WXSDKInstance wXSDKInstance, String str, String str2, String str3, String str4, HashMap<String, String> map, HashMap<String, String> map2) {
        WXRichText wXRichText;
        super(wXSDKInstance, str4);
        if (WXSDKManager.getInstance() == null || WXSDKManager.getInstance().getWXRenderManager() == null || (wXRichText = (WXRichText) WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), str4)) == null) {
            return;
        }
        wXRichText.AddChildNode(str2, str, str3, map, map2);
    }

    @Override // com.taobao.weex.ui.action.IExecutable
    public void executeAction() {
    }
}
