package io.dcloud.common.cs;

import io.dcloud.common.DHInterface.DAI;

/* loaded from: classes3.dex */
public class DA implements DAI {
    private static DAI mInstance;

    private native void arn(String str, Object obj);

    private native void atcn(String str, Object obj);

    public static DAI getInstance() {
        if (mInstance == null) {
            mInstance = new DA();
        }
        return mInstance;
    }

    private native void scn();

    @Override // io.dcloud.common.DHInterface.DAI
    public void act(String str, Object obj) {
        atcn(str, obj);
    }

    @Override // io.dcloud.common.DHInterface.DAI
    public void ar(String str, Object obj) {
        arn(str, obj);
    }

    @Override // io.dcloud.common.DHInterface.DAI
    public void sc() {
        scn();
    }
}
