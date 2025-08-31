package io.dcloud.sdk.core.v3.cp;

import io.dcloud.sdk.core.v3.cp.DCContentPage;

/* loaded from: classes3.dex */
public interface DCContentPageVideoListener {
    void onComplete(DCContentPage.ContentPageItem contentPageItem);

    void onError(DCContentPage.ContentPageItem contentPageItem);

    void onPause(DCContentPage.ContentPageItem contentPageItem);

    void onResume(DCContentPage.ContentPageItem contentPageItem);

    void onStart(DCContentPage.ContentPageItem contentPageItem);
}
