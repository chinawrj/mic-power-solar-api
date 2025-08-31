package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;
import java.util.Map;

/* loaded from: classes3.dex */
public class GraphicActionUpdateAttr extends BasicGraphicAction {
    private WXComponent component;
    private Map<String, String> mAttrs;

    public GraphicActionUpdateAttr(WXSDKInstance wXSDKInstance, String str, Map<String, String> map) {
        Map<String, String> map2;
        super(wXSDKInstance, str);
        this.mAttrs = map;
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        this.component = wXComponent;
        if (wXComponent == null || (map2 = this.mAttrs) == null) {
            return;
        }
        wXComponent.addAttr(map2);
    }

    @Override // com.taobao.weex.ui.action.IExecutable
    public void executeAction() {
        WXComponent wXComponent = this.component;
        if (wXComponent != null) {
            wXComponent.getAttrs().mergeAttr();
            if (this.component.getHostView() != null) {
                this.component.updateAttrs(this.mAttrs);
            }
        }
    }
}
