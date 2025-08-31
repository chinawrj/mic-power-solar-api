package com.dcloud.android.widget.photoview;

import android.view.View;

/* loaded from: classes.dex */
class Compat {
    private static final int SIXTY_FPS_INTERVAL = 16;

    Compat() {
    }

    private static void postOnAnimationJellyBean(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        postOnAnimationJellyBean(view, runnable);
    }
}
