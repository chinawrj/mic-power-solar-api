package io.dcloud.p;

import android.app.Activity;
import io.dcloud.p.b0;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.util.AOLErrorUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class g4 extends x3 implements Runnable {
    protected DCloudAOLSlot b;
    protected final Activity c;
    protected int d;
    private b0.a e;

    class a extends b0.a {
        a(String str) {
            super(str);
        }

        @Override // io.dcloud.p.b0.a
        public void a(JSONObject jSONObject) {
            g4.this.a(jSONObject);
        }

        @Override // io.dcloud.p.b0.a
        public void a(int i, String str) {
            g4.this.b(i, str);
        }
    }

    public g4(Activity activity) {
        this.c = activity;
    }

    private void e() throws JSONException {
        if (this.e == null) {
            this.e = new a(this.b.getAdpid());
        }
        b0.a().a(this.c, d(), this.e);
    }

    protected abstract void a(int i, String str);

    protected abstract void a(t0 t0Var);

    protected final void a(DCloudAOLSlot dCloudAOLSlot) {
        this.b = dCloudAOLSlot;
        dCloudAOLSlot.setType(this.d);
    }

    protected void b(int i, String str) {
        a(i, str);
    }

    protected int d() {
        return 2;
    }

    @Override // java.lang.Runnable
    public void run() throws JSONException {
        e();
    }

    protected void a(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() > 0) {
            a(new t0().a(jSONObject, d()));
        } else {
            a(-5001, AOLErrorUtil.getErrorMsg(-5001));
        }
    }

    protected void a(b0.a aVar) {
        this.e = aVar;
    }
}
