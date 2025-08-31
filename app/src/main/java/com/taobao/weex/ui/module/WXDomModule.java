package com.taobao.weex.ui.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.action.ActionInvokeMethod;

/* loaded from: classes3.dex */
public final class WXDomModule extends WXModule {
    public static final String UPDATE_COMPONENT_DATA = "updateComponentData";
    public static final String WXDOM = "dom";
    public static final String SCROLL_TO_ELEMENT = "scrollToElement";
    public static final String ADD_RULE = "addRule";
    public static final String GET_COMPONENT_RECT = "getComponentRect";
    public static final String INVOKE_METHOD = "invokeMethod";
    public static final String GET_COMPONENT_DIRECTION = "getLayoutDirection";
    public static final String BATCH_BEGIN = "beginBatchMark";
    public static final String BATCH_END = "endBatchMark";
    public static final String[] METHODS = {SCROLL_TO_ELEMENT, ADD_RULE, GET_COMPONENT_RECT, INVOKE_METHOD, GET_COMPONENT_DIRECTION, BATCH_BEGIN, BATCH_END};

    public WXDomModule(WXSDKInstance wXSDKInstance) {
        this.mWXSDKInstance = wXSDKInstance;
        this.mUniSDKInstance = wXSDKInstance;
    }

    public void callDomMethod(JSONObject jSONObject, long... jArr) {
        if (jSONObject == null) {
            return;
        }
        callDomMethod((String) jSONObject.get("method"), (JSONArray) jSONObject.get("args"), jArr);
    }

    public void invokeMethod(String str, String str2, JSONArray jSONArray) {
        if (str == null || str2 == null) {
            return;
        }
        new ActionInvokeMethod(this.mWXSDKInstance.getInstanceId(), str, str2, jSONArray).executeAction();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object callDomMethod(java.lang.String r6, com.alibaba.fastjson.JSONArray r7, long... r8) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.module.WXDomModule.callDomMethod(java.lang.String, com.alibaba.fastjson.JSONArray, long[]):java.lang.Object");
    }
}
