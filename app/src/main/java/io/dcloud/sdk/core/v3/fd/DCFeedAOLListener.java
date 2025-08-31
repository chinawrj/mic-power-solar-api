package io.dcloud.sdk.core.v3.fd;

/* loaded from: classes3.dex */
public interface DCFeedAOLListener {
    void onClick();

    void onClosed(String str);

    void onRenderFail();

    void onRenderSuccess();

    void onShow();

    void onShowError();
}
