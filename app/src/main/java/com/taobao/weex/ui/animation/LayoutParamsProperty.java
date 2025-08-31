package com.taobao.weex.ui.animation;

import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.IRenderResult;

/* loaded from: classes3.dex */
abstract class LayoutParamsProperty extends Property<View, Integer> {
    LayoutParamsProperty() {
        super(Integer.class, "layoutParams");
    }

    protected abstract Integer getProperty(ViewGroup.LayoutParams layoutParams);

    protected abstract void setProperty(ViewGroup.LayoutParams layoutParams, Integer num);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.util.Property
    public Integer get(View view) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return 0;
        }
        return getProperty(layoutParams);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Property
    public void set(View view, Integer num) {
        ViewGroup.LayoutParams layoutParams;
        WXComponent component;
        if (view == 0 || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        setProperty(layoutParams, num);
        if ((view instanceof IRenderResult) && (component = ((IRenderResult) view).getComponent()) != null) {
            component.notifyNativeSizeChanged(layoutParams.width, layoutParams.height);
        }
        view.requestLayout();
    }
}
