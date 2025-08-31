package io.dcloud.p;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;

/* loaded from: classes3.dex */
public interface p2 {

    public interface a {
        boolean a(View view);

        void b(View view);

        void c(View view);
    }

    void a(Canvas canvas);

    void a(a aVar);

    boolean a();

    void b(a aVar);

    boolean b();

    boolean dismiss();

    RectF getFrame();
}
