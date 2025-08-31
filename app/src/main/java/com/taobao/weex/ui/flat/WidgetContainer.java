package com.taobao.weex.ui.flat;

import android.view.ViewGroup;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.flat.widget.Widget;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class WidgetContainer<T extends ViewGroup> extends WXVContainer<T> {
    protected List<Widget> widgets;

    public WidgetContainer(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
    }

    private void addFlatChild(Widget widget, int i) {
        if (this.widgets == null) {
            this.widgets = new LinkedList();
        }
        if (i >= this.widgets.size()) {
            this.widgets.add(widget);
        } else {
            this.widgets.add(i, widget);
        }
        mountFlatGUI();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003b  */
    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void createChildViewAt(int r7) {
        /*
            r6 = this;
            boolean r0 = r6.intendToBeFlatContainer()
            if (r0 == 0) goto L65
            android.util.Pair r7 = r6.rearrangeIndexAndGetChild(r7)
            java.lang.Object r0 = r7.first
            if (r0 == 0) goto L68
            com.taobao.weex.ui.component.WXComponent r0 = (com.taobao.weex.ui.component.WXComponent) r0
            com.taobao.weex.WXSDKInstance r1 = r6.getInstance()
            com.taobao.weex.ui.flat.FlatGUIContext r1 = r1.getFlatUIContext()
            com.taobao.weex.ui.flat.WidgetContainer r2 = r1.getFlatComponentAncestor(r6)
            if (r2 == 0) goto L24
            com.taobao.weex.ui.flat.widget.AndroidViewWidget r3 = r1.getAndroidViewWidget(r6)
            if (r3 == 0) goto L25
        L24:
            r2 = r6
        L25:
            r1.register(r0, r2)
            boolean r3 = r0 instanceof com.taobao.weex.ui.flat.FlatComponent
            if (r3 == 0) goto L3b
            r3 = r0
            com.taobao.weex.ui.flat.FlatComponent r3 = (com.taobao.weex.ui.flat.FlatComponent) r3
            r4 = 0
            boolean r4 = r3.promoteToView(r4)
            if (r4 != 0) goto L3b
            com.taobao.weex.ui.flat.widget.Widget r2 = r3.getOrCreateFlatWidget()
            goto L56
        L3b:
            com.taobao.weex.ui.flat.widget.AndroidViewWidget r3 = new com.taobao.weex.ui.flat.widget.AndroidViewWidget
            r3.<init>(r1)
            r1.register(r0, r3)
            r0.createView()
            android.view.View r4 = r0.getHostView()
            r3.setContentView(r4)
            android.view.View r4 = r0.getHostView()
            r5 = -1
            r2.addSubView(r4, r5)
            r2 = r3
        L56:
            r1.register(r2, r0)
            java.lang.Object r7 = r7.second
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r6.addFlatChild(r2, r7)
            goto L68
        L65:
            super.createChildViewAt(r7)
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.flat.WidgetContainer.createChildViewAt(int):void");
    }

    public boolean intendToBeFlatContainer() {
        return false;
    }

    protected abstract void mountFlatGUI();

    protected abstract void unmountFlatGUI();
}
