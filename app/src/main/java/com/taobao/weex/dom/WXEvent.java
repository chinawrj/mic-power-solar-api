package com.taobao.weex.dom;

import io.dcloud.feature.uniapp.dom.AbsEvent;

/* loaded from: classes.dex */
public class WXEvent extends AbsEvent {
    @Override // io.dcloud.feature.uniapp.dom.AbsEvent, java.util.ArrayList
    public WXEvent clone() {
        WXEvent wXEvent = new WXEvent();
        wXEvent.addAll(this);
        if (getEventBindingArgs() != null) {
            wXEvent.setEventBindingArgs(getEventBindingArgs());
        }
        wXEvent.setEventBindingArgsValues(null);
        return wXEvent;
    }
}
