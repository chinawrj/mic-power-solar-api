package io.dcloud.p;

import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes3.dex */
public abstract class i {
    private static HashMap a = new HashMap();
    static ArrayList b = new ArrayList();

    public static void a(String str) {
        b.add(str);
    }

    public static void b(String str) {
        b.remove(str);
    }
}
