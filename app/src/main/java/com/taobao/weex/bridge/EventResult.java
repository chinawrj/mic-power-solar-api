package com.taobao.weex.bridge;

/* loaded from: classes.dex */
public class EventResult {
    private Object result;
    private boolean success = false;

    public Object getResult() {
        return this.result;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void onCallback(Object obj) {
        this.success = true;
        this.result = obj;
    }
}
