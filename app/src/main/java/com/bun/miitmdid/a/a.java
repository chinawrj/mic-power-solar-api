package com.bun.miitmdid.a;

import android.content.Context;
import com.bun.miitmdid.a.c.c;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a implements b {
    public C0007a a = new C0007a(this);

    /* renamed from: com.bun.miitmdid.a.a$a, reason: collision with other inner class name */
    public class C0007a {
        public com.bun.miitmdid.a.c.a a;
        public com.bun.miitmdid.a.c.b b;
        public c c;

        public C0007a(a aVar) {
        }
    }

    private a() {
    }

    public static native a a(Context context);

    private static native boolean a(a aVar, JSONObject jSONObject);

    private static native boolean b(a aVar, JSONObject jSONObject);

    private static native boolean c(a aVar, JSONObject jSONObject);

    @Override // com.bun.miitmdid.a.b
    public native String a();
}
