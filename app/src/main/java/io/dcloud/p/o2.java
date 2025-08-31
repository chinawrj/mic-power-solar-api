package io.dcloud.p;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes3.dex */
public class o2 {
    private static final Matrix d = new Matrix();
    private View a;
    private float b;
    private float c;

    public o2(View view) {
        this.a = view;
    }

    public boolean a(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.b = motionEvent.getX();
            this.c = motionEvent.getY();
            Matrix matrix = d;
            matrix.reset();
            matrix.setRotate(view.getRotation());
            return true;
        }
        if (actionMasked != 2) {
            return false;
        }
        float[] fArr = {motionEvent.getX() - this.b, motionEvent.getY() - this.c};
        d.mapPoints(fArr);
        view.setTranslationX(this.a.getTranslationX() + fArr[0]);
        view.setTranslationY(this.a.getTranslationY() + fArr[1]);
        return true;
    }
}
