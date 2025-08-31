package com.taobao.weex.ui.component;

import android.content.Context;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.refresh.circlebar.CircleProgressBar;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXUtils;
import java.lang.reflect.InvocationTargetException;

@Component(lazyload = false)
/* loaded from: classes3.dex */
public class WXLoadingIndicator extends WXComponent<CircleProgressBar> {
    private static final String ANIMATING = "animating";

    public WXLoadingIndicator(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
    }

    private void setAnimatingSp(boolean z) {
        if (z) {
            getHostView().start();
        } else {
            getHostView().stop();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void destroy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super.destroy();
        if (getHostView() != null) {
            getHostView().destory();
        }
    }

    @WXComponentProp(name = "animating")
    public void setAnimating(boolean z) {
        if (z) {
            getHostView().start();
        } else {
            getHostView().stop();
        }
    }

    @WXComponentProp(name = "color")
    public void setColor(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (str == null || str.equals("")) {
            return;
        }
        getHostView().setColorSchemeColors(WXResourceUtils.getColor(str, -65536));
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    protected boolean setProperty(String str, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        str.hashCode();
        str.hashCode();
        switch (str) {
            case "arrow":
                getHostView().setShowArrow(WXUtils.getBoolean(obj, Boolean.TRUE).booleanValue());
                return true;
            case "color":
                String string = WXUtils.getString(obj, null);
                if (string != null) {
                    setColor(string);
                }
                return true;
            case "animating":
                Boolean bool = WXUtils.getBoolean(obj, null);
                if (bool != null) {
                    setAnimating(bool.booleanValue());
                }
                return true;
            default:
                return super.setProperty(str, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.taobao.weex.ui.component.WXComponent
    public CircleProgressBar initComponentHostView(Context context) {
        return new CircleProgressBar(context);
    }
}
