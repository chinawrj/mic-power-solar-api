package com.taobao.weex.wson;

import com.taobao.weex.utils.WXLogUtils;

/* loaded from: classes3.dex */
public class WsonUtils {
    public static final Object parseWson(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return Wson.parse(bArr);
        } catch (Exception e) {
            WXLogUtils.e("weex wson parse error ", e);
            return null;
        }
    }

    public static final byte[] toWson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return Wson.toWson(obj);
        } catch (Exception e) {
            WXLogUtils.e("weex wson to wson error ", e);
            return null;
        }
    }
}
