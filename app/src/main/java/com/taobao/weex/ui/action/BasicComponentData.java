package com.taobao.weex.ui.action;

import android.view.View;
import com.taobao.weex.dom.CSSShorthand;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.dom.WXEvent;
import com.taobao.weex.dom.WXStyle;
import com.taobao.weex.ui.component.list.template.jni.NativeRenderObjectUtils;
import io.dcloud.feature.uniapp.dom.AbsEvent;
import io.dcloud.feature.uniapp.dom.AbsStyle;
import io.dcloud.feature.uniapp.ui.action.AbsComponentData;

/* loaded from: classes3.dex */
public class BasicComponentData<T extends View> extends AbsComponentData {
    public BasicComponentData(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    @Override // io.dcloud.feature.uniapp.ui.action.AbsComponentData
    public WXAttr getAttrs() {
        return (WXAttr) super.getAttrs();
    }

    @Override // io.dcloud.feature.uniapp.ui.action.AbsComponentData
    public CSSShorthand getBorder() {
        return (CSSShorthand) super.getBorder();
    }

    @Override // io.dcloud.feature.uniapp.ui.action.AbsComponentData
    public WXEvent getEvents() {
        return (WXEvent) super.getEvents();
    }

    @Override // io.dcloud.feature.uniapp.ui.action.AbsComponentData
    public CSSShorthand getMargin() {
        return (CSSShorthand) super.getMargin();
    }

    @Override // io.dcloud.feature.uniapp.ui.action.AbsComponentData
    public CSSShorthand getPadding() {
        return (CSSShorthand) super.getPadding();
    }

    @Override // io.dcloud.feature.uniapp.ui.action.AbsComponentData
    public WXStyle getStyles() {
        return (WXStyle) super.getStyles();
    }

    @Override // io.dcloud.feature.uniapp.ui.action.AbsComponentData
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BasicComponentData mo242clone() {
        BasicComponentData basicComponentData = new BasicComponentData(this.mRef, this.mComponentType, this.mParentRef);
        basicComponentData.setBorders(getBorder().mo238clone());
        basicComponentData.setMargins(getMargin().mo238clone());
        basicComponentData.setPaddings(getPadding().mo238clone());
        WXAttr attrs = getAttrs();
        if (attrs != null) {
            basicComponentData.mAttributes = attrs.mo239clone();
        }
        AbsStyle absStyle = this.mStyles;
        if (absStyle != null) {
            basicComponentData.mStyles = absStyle.mo240clone();
        }
        AbsEvent absEvent = this.mEvents;
        if (absEvent != null) {
            basicComponentData.mEvents = absEvent.clone();
        }
        long j = this.renderObjectPr;
        if (j != 0) {
            basicComponentData.setRenderObjectPr(NativeRenderObjectUtils.nativeCopyRenderObject(j));
        }
        return basicComponentData;
    }
}
