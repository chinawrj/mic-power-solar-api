package com.taobao.weex.appfram.clipboard;

import com.taobao.weex.bridge.JSCallback;

/* loaded from: classes.dex */
interface IWXClipboard {
    void getString(JSCallback jSCallback);

    void setString(String str);
}
