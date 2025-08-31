package com.taobao.weex.ui.action;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.tracing.Stopwatch;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.feature.uniapp.dom.AbsEvent;

/* loaded from: classes3.dex */
public class GraphicActionAddEvent extends BasicGraphicAction {
    private final String mEvent;

    public GraphicActionAddEvent(WXSDKInstance wXSDKInstance, String str, Object obj) {
        super(wXSDKInstance, str);
        this.mEvent = AbsEvent.getEventName(obj);
    }

    @Override // com.taobao.weex.ui.action.IExecutable
    public void executeAction() {
        WXComponent wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getPageId(), getRef());
        if (wXComponent == null) {
            return;
        }
        Stopwatch.tick();
        if (!wXComponent.getEvents().contains(this.mEvent)) {
            wXComponent.getEvents().addEvent(this.mEvent);
        }
        wXComponent.addEvent(this.mEvent);
        Stopwatch.split("addEventToComponent");
    }
}
