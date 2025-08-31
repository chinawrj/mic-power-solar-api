package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.richtext.WXRichText;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class GraphicActionUpdateRichtextStyle extends BasicGraphicAction {
    public GraphicActionUpdateRichtextStyle(WXSDKInstance wXSDKInstance, String str, HashMap<String, String> map, String str2, String str3) {
        super(wXSDKInstance, str3);
        WXRichText wXRichText = (WXRichText) WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), str3);
        if (wXRichText != null) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            wXRichText.updateChildNodeStyles(str, map2);
        }
    }

    @Override // com.taobao.weex.ui.action.IExecutable
    public void executeAction() {
    }
}
