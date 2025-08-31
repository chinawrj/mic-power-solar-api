package com.taobao.weex.adapter;

import com.taobao.weex.tracing.WXTracing;

/* loaded from: classes.dex */
public interface ITracingAdapter {
    void disable();

    void enable();

    void submitTracingEvent(WXTracing.TraceEvent traceEvent);
}
