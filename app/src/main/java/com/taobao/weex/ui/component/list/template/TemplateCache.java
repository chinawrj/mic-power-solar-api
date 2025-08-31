package com.taobao.weex.ui.component.list.template;

import com.taobao.weex.ui.component.list.WXCell;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes3.dex */
class TemplateCache {
    ConcurrentLinkedQueue<WXCell> cells = new ConcurrentLinkedQueue<>();
    boolean isLoadIng = false;

    TemplateCache() {
    }
}
