package com.taobao.weex.utils;

import com.alibaba.fastjson.JSON;
import com.taobao.weex.bridge.WXJSObject;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.wson.Wson;
import com.taobao.weex.wson.WsonUtils;

/* loaded from: classes3.dex */
public class WXWsonJSONSwitch {
    private static final String TAG = "WXSwitch";
    public static boolean USE_WSON = true;
    public static final String WSON_OFF = "wson_off";

    public static final byte[] convertJSONToWsonIfUseWson(byte[] bArr) {
        if (!USE_WSON) {
            return bArr;
        }
        if (bArr == null) {
            return null;
        }
        String str = new String(bArr);
        return str.startsWith(Operators.ARRAY_START_STR) ? WsonUtils.toWson(JSON.parseArray(str)) : WsonUtils.toWson(JSON.parse(str));
    }

    public static final Object convertWXJSObjectDataToJSON(WXJSObject wXJSObject) {
        return wXJSObject.type == 4 ? JSON.parse(Wson.parse((byte[]) wXJSObject.data).toString()) : JSON.parse(wXJSObject.data.toString());
    }

    public static String fromObjectToJSONString(WXJSObject wXJSObject) {
        Object obj;
        return (wXJSObject == null || wXJSObject.type != 4 || (obj = Wson.parse((byte[]) wXJSObject.data)) == null) ? WXJsonUtils.fromObjectToJSONString(wXJSObject, false) : obj.toString();
    }

    public static final Object parseWsonOrJSON(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return USE_WSON ? Wson.parse(bArr) : JSON.parse(new String(bArr, "UTF-8"));
        } catch (Exception e) {
            WXLogUtils.e(TAG, e);
            return USE_WSON ? JSON.parse(new String(bArr)) : Wson.parse(bArr);
        }
    }

    public static final WXJSObject toWsonOrJsonWXJSObject(Object obj) {
        return obj == null ? new WXJSObject(null) : obj.getClass() == WXJSObject.class ? (WXJSObject) obj : USE_WSON ? new WXJSObject(4, Wson.toWson(obj)) : new WXJSObject(3, WXJsonUtils.fromObjectToJSONString(obj));
    }
}
