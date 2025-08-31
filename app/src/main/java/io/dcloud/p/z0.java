package io.dcloud.p;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class z0 {
    private static z0 a;
    private static List b = new ArrayList();

    private z0() {
    }

    public static z0 a(Context context) {
        if (a == null) {
            synchronized (z0.class) {
                if (a == null) {
                    a = new z0();
                }
            }
        }
        return a;
    }

    public void b(y0 y0Var) {
        b.remove(y0Var);
    }

    public void a(y0 y0Var) {
        b.add(y0Var);
        v4.a().a(y0Var);
    }

    public List a() {
        return b;
    }
}
