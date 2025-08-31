package io.dcloud.sdk.core.v3.dw;

import io.dcloud.sdk.core.v3.fd.DCFeedAOLListener;

/* loaded from: classes3.dex */
public interface DCDrawAOLListener extends DCFeedAOLListener {
    @Override // io.dcloud.sdk.core.v3.fd.DCFeedAOLListener
    void onClick();

    @Override // io.dcloud.sdk.core.v3.fd.DCFeedAOLListener
    void onClosed(String str);

    void onEnd();

    void onPause();

    @Override // io.dcloud.sdk.core.v3.fd.DCFeedAOLListener
    void onRenderFail();

    @Override // io.dcloud.sdk.core.v3.fd.DCFeedAOLListener
    void onRenderSuccess();

    void onResume();

    @Override // io.dcloud.sdk.core.v3.fd.DCFeedAOLListener
    void onShow();

    @Override // io.dcloud.sdk.core.v3.fd.DCFeedAOLListener
    void onShowError();

    void onStart();
}
