package io.dcloud.api.custom.type.reward;

import android.app.Activity;
import io.dcloud.api.custom.type.UniAdCustomBaseLoader;
import io.dcloud.p.v1;

/* loaded from: classes3.dex */
public abstract class UniAdCustomRewardLoader extends UniAdCustomBaseLoader {
    public void onReward() {
        v1 v1Var = this.a;
        if (v1Var instanceof v1.a) {
            ((v1.a) v1Var).c();
        }
    }

    public abstract void show(Activity activity);

    @Override // io.dcloud.api.custom.type.UniAdCustomBaseLoader
    public final void show(Object obj) {
        show((Activity) obj);
    }
}
