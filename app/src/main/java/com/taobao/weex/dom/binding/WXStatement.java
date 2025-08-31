package com.taobao.weex.dom.binding;

import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;

/* loaded from: classes.dex */
public class WXStatement extends ArrayMap<String, Object> implements Cloneable {
    public static final String WX_FOR = "[[repeat]]";
    public static final String WX_FOR_INDEX = "@index";
    public static final String WX_FOR_ITEM = "@alias";
    public static final String WX_FOR_LIST = "@expression";
    public static final String WX_IF = "[[match]]";
    public static final String WX_ONCE = "[[once]]";

    public WXStatement() {
    }

    public WXStatement(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public WXStatement m241clone() {
        return new WXStatement(this);
    }
}
