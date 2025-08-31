package io.dcloud.sdk.core.v3.fs;

/* loaded from: classes3.dex */
public interface DCFSAOLListener {
    void onClick();

    void onClose();

    void onShow();

    void onShowError(int i, String str);

    void onSkip();

    void onVideoPlayEnd();
}
