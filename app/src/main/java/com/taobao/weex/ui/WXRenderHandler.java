package com.taobao.weex.ui;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes3.dex */
class WXRenderHandler extends Handler {
    public WXRenderHandler() {
        super(Looper.getMainLooper());
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
    }

    public final boolean post(String str, Runnable runnable) {
        Message messageObtain = Message.obtain(this, runnable);
        messageObtain.what = str.hashCode();
        return sendMessageDelayed(messageObtain, 0L);
    }
}
