package io.dcloud.p;

import android.app.Activity;
import io.dcloud.sdk.core.interfaces.AOLLoader;
import io.dcloud.sdk.core.util.MainHandlerUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class y3 extends y4 implements AOLLoader.RewVAOLInteractionListener {
    public y3(Activity activity) {
        super(activity, 9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(JSONObject jSONObject) {
        AOLLoader.VAOLInteractionListener vAOLInteractionListener = this.r;
        if (vAOLInteractionListener instanceof AOLLoader.RewVAOLInteractionListener) {
            ((AOLLoader.RewVAOLInteractionListener) vAOLInteractionListener).onReward(jSONObject);
        }
    }

    @Override // io.dcloud.sdk.core.interfaces.AOLLoader.RewVAOLInteractionListener
    public void onReward(final JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!jSONObject.has("provider")) {
            try {
                jSONObject.put("provider", e.b().c(this.t.getType()));
            } catch (Exception unused) {
            }
        }
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.y3$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(jSONObject);
            }
        });
    }
}
