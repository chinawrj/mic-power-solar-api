package com.taobao.weex.dom.binding;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.el.parse.Parser;
import com.taobao.weex.el.parse.Token;
import com.taobao.weex.utils.WXLogUtils;

/* loaded from: classes.dex */
public class ELUtils {
    public static final String BINDING = "@binding";
    public static final String COMPONENT_PROPS = "@componentProps";
    public static final String[] EXCLUDES_BINDING = {"clickEventParams"};
    public static final String IS_COMPONENT_ROOT = "@isComponentRoot";

    public static Object bindingBlock(Object obj) {
        if (!(obj instanceof JSONObject)) {
            if (!(obj instanceof JSONArray)) {
                if (!(obj instanceof String)) {
                    return obj;
                }
                String string = obj.toString();
                return string.startsWith(Operators.BLOCK_START_STR) ? bindingBlock(JSON.parseObject(string)) : string.startsWith(Operators.ARRAY_START_STR) ? bindingBlock(JSON.parseArray(string)) : obj;
            }
            JSONArray jSONArray = (JSONArray) obj;
            for (int i = 0; i < jSONArray.size(); i++) {
                bindingBlock(jSONArray.get(i));
            }
            return obj;
        }
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.containsKey(BINDING)) {
            Object obj2 = jSONObject.get(BINDING);
            if (!(obj2 instanceof Token)) {
                jSONObject.put(BINDING, (Object) Parser.parse(obj2.toString()));
            }
        }
        for (String str : jSONObject.keySet()) {
            if ((jSONObject.get(str) instanceof JSONObject) && ((JSONObject) jSONObject.get(str)).containsKey(BINDING)) {
                JSONObject jSONObject2 = (JSONObject) jSONObject.get(str);
                Object obj3 = jSONObject2.get(BINDING);
                if (!(obj3 instanceof Token)) {
                    jSONObject2.put(BINDING, (Object) Parser.parse(obj3.toString()));
                }
            }
        }
        return obj;
    }

    public static boolean isBinding(Object obj) {
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).containsKey(BINDING);
        }
        if (!(obj instanceof JSONArray)) {
            return (obj instanceof String) && ((String) obj).indexOf(BINDING) >= 0;
        }
        JSONArray jSONArray = (JSONArray) obj;
        for (int i = 0; i < jSONArray.size(); i++) {
            if (isBinding(jSONArray.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static Object vforBlock(Object obj) {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.containsKey(WXStatement.WX_FOR_LIST)) {
                Object obj2 = jSONObject.get(WXStatement.WX_FOR_LIST);
                if (!(obj2 instanceof Token)) {
                    jSONObject.put(WXStatement.WX_FOR_LIST, (Object) Parser.parse(obj2.toString()));
                }
            }
        } else {
            if (obj instanceof String) {
                return vforBlock(JSONObject.parseObject(obj.toString()));
            }
            if (WXEnvironment.isApkDebugable()) {
                WXLogUtils.e("weex", "weex vfor is illegal " + obj);
            }
        }
        return obj;
    }
}
