package io.dcloud.feature.weex.adapter.webview;

import android.view.View;
import com.taobao.weex.ui.view.IWebView;

/* loaded from: classes3.dex */
public interface IDCWebView extends IWebView {
    View getWebView();

    void setUserAgent(String str, boolean z);
}
