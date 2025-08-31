package io.dcloud.p;

import android.view.View;

/* loaded from: classes3.dex */
public abstract class a {
    public static boolean a(View view) {
        return view != null && view.getVisibility() == 0 && view.isShown() && view.getWindowVisibility() == 0;
    }
}
