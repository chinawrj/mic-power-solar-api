package io.dcloud.sdk.core.interfaces;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import io.dcloud.sdk.core.DCloudAOLManager;
import io.dcloud.sdk.core.v3.cp.DCContentPage;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface AOLLoader {

    public interface ContentPageVideoListener extends VAOLInteractionListener {
        void onComplete(DCContentPage.ContentPageItem contentPageItem);

        void onError(DCContentPage.ContentPageItem contentPageItem);

        void onPause(DCContentPage.ContentPageItem contentPageItem);

        void onResume(DCContentPage.ContentPageItem contentPageItem);

        void onStart(DCContentPage.ContentPageItem contentPageItem);
    }

    public interface DrawAOLInteractionListener extends FeedAOLInteractionListener {
        void onEnd();

        void onError();

        void onPause();

        void onResume();

        void onStart();
    }

    public interface FeedAOLInteractionListener {
        void onClicked();

        void onClosed(String str);

        void onPaidGet(long j, String str, int i);

        void onRenderFail();

        void onRenderSuccess();

        void onShow();

        void onShowError();
    }

    public interface RewVAOLInteractionListener extends VAOLInteractionListener {
        void onReward(JSONObject jSONObject);
    }

    public interface SplashAOLLoadListener {
        void onError(int i, String str);

        void redBag(View view, FrameLayout.LayoutParams layoutParams);
    }

    public interface VAOLInteractionListener {
        void onClick();

        void onClose();

        void onPaidGet(long j, String str, int i);

        void onShow();

        void onShowError(int i, String str);

        void onSkip();

        void onVideoPlayEnd();
    }

    boolean getPersonalAOL(Context context);

    void setPersonalAOL(Context context, boolean z);

    void setPrivacyConfig(DCloudAOLManager.PrivacyConfig privacyConfig);

    void updatePrivacyConfig(Context context, JSONObject jSONObject);
}
