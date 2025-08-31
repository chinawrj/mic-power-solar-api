package io.dcloud.sdk.core.v3.rew;

import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface DCRewAOLListener {
    void onClick();

    void onClose();

    void onReward(JSONObject jSONObject);

    void onShow();

    void onShowError(int i, String str);

    void onSkip();

    void onVideoPlayEnd();
}
