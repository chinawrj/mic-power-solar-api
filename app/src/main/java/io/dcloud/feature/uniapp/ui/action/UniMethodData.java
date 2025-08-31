package io.dcloud.feature.uniapp.ui.action;

import com.alibaba.fastjson.JSONArray;

/* loaded from: classes3.dex */
public class UniMethodData {
    JSONArray args;
    String method;

    public UniMethodData(String str, JSONArray jSONArray) {
        this.method = str;
        this.args = jSONArray;
    }

    public JSONArray getArgs() {
        return this.args;
    }

    public String getMethod() {
        return this.method;
    }
}
