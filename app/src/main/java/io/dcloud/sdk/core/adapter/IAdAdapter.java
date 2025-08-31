package io.dcloud.sdk.core.adapter;

import android.app.Activity;
import io.dcloud.sdk.core.DCloudAOLManager;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.module.DCBaseAOLLoader;

/* loaded from: classes3.dex */
public interface IAdAdapter {
    DCBaseAOLLoader getAd(Activity activity, DCloudAOLSlot dCloudAOLSlot);

    String getAdapterSDKVersion();

    String getSDKVersion();

    boolean isSupport();

    void setPersonalAd(boolean z);

    void updatePrivacyConfig(DCloudAOLManager.PrivacyConfig privacyConfig);
}
