package io.dcloud.api.custom.type.draw;

import io.dcloud.api.custom.type.feed.UniAdCustomNativeAd;
import io.dcloud.p.w1;

/* loaded from: classes3.dex */
public abstract class UniAdCustomDrawAd extends UniAdCustomNativeAd {
    public void onVideoPlayEnd() {
        w1 w1Var = this.a;
        if (w1Var instanceof w1.a) {
            ((w1.a) w1Var).onVideoPlayEnd();
        }
    }

    public void onVideoPlayError() {
        w1 w1Var = this.a;
        if (w1Var instanceof w1.a) {
            ((w1.a) w1Var).d();
        }
    }

    public void onVideoPlayPause() {
        w1 w1Var = this.a;
        if (w1Var instanceof w1.a) {
            ((w1.a) w1Var).j();
        }
    }

    public void onVideoPlayResume() {
        w1 w1Var = this.a;
        if (w1Var instanceof w1.a) {
            ((w1.a) w1Var).f();
        }
    }

    public void onVideoPlayStart() {
        w1 w1Var = this.a;
        if (w1Var instanceof w1.a) {
            ((w1.a) w1Var).l();
        }
    }
}
