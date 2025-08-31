package com.taobao.weex.ui.component;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.uniapp.ui.component.AbsVContainer;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes3.dex */
public abstract class WXVContainer<T extends ViewGroup> extends AbsVContainer<T> {
    private static final String TAG = "WXVContainer";
    private WXVContainer<T>.BoxShadowHost mBoxShadowHost;

    private class BoxShadowHost extends View {
        public BoxShadowHost(Context context) {
            super(context);
        }
    }

    public WXVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, str, z, basicComponentData);
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public View getBoxShadowHost(boolean z) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (z) {
            return this.mBoxShadowHost;
        }
        T hostView = getHostView();
        if (hostView == null) {
            return null;
        }
        try {
            String componentType = getComponentType();
            if (WXBasicComponentType.DIV.equals(componentType) || WXBasicComponentType.VIEW.equals(componentType)) {
                WXLogUtils.d("BoxShadow", "Draw box-shadow with BoxShadowHost on div: " + toString());
                if (this.mBoxShadowHost == null) {
                    WXVContainer<T>.BoxShadowHost boxShadowHost = new BoxShadowHost(getContext());
                    this.mBoxShadowHost = boxShadowHost;
                    WXViewUtils.setBackGround(boxShadowHost, null, this);
                    hostView.addView(this.mBoxShadowHost);
                }
                CSSShorthand padding = getPadding();
                CSSShorthand border = getBorder();
                CSSShorthand.EDGE edge = CSSShorthand.EDGE.LEFT;
                int i = (int) (padding.get(edge) + border.get(edge));
                CSSShorthand.EDGE edge2 = CSSShorthand.EDGE.TOP;
                int i2 = (int) (padding.get(edge2) + border.get(edge2));
                CSSShorthand.EDGE edge3 = CSSShorthand.EDGE.RIGHT;
                int i3 = (int) (padding.get(edge3) + border.get(edge3));
                CSSShorthand.EDGE edge4 = CSSShorthand.EDGE.BOTTOM;
                int i4 = (int) (padding.get(edge4) + border.get(edge4));
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(hostView.getLayoutParams());
                setMarginsSupportRTL(marginLayoutParams, -i, -i2, -i3, -i4);
                this.mBoxShadowHost.setLayoutParams(marginLayoutParams);
                hostView.removeView(this.mBoxShadowHost);
                hostView.addView(this.mBoxShadowHost);
                return this.mBoxShadowHost;
            }
        } catch (Throwable th) {
            WXLogUtils.w("BoxShadow", th);
        }
        return hostView;
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    protected int getChildrenLayoutTopOffset() {
        return 0;
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public void removeBoxShadowHost() {
        T hostView = getHostView();
        if (hostView == null) {
            return;
        }
        try {
            String componentType = getComponentType();
            if (this.mBoxShadowHost != null) {
                if (WXBasicComponentType.DIV.equals(componentType) || WXBasicComponentType.VIEW.equals(componentType)) {
                    hostView.removeView(this.mBoxShadowHost);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public WXVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
    }

    public WXVContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
    }
}
