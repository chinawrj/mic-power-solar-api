package io.dcloud.sdk.core.module;

import android.app.Activity;
import androidx.core.app.NotificationCompat;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.p.b3;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.util.Const;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class a extends DCBaseAOL {
    private boolean A;
    private long B;
    private long w;
    private int x;
    private JSONObject y;
    private String z;

    public a(DCloudAOLSlot dCloudAOLSlot, Activity activity) {
        super(dCloudAOLSlot, activity);
        this.w = 0L;
        this.x = -1;
        this.z = "";
        this.A = true;
        this.B = 0L;
    }

    public void c(int i, String str) {
        this.A = i != -9999;
        b3.b("uniAD", getDCloudId() + ":" + getType() + ":" + i + ":" + str + ";id:" + getSlotId());
        this.x = 0;
        this.w = System.currentTimeMillis() - this.B;
        JSONObject jSONObject = new JSONObject();
        this.y = jSONObject;
        try {
            jSONObject.put("code", i);
            this.y.put(NotificationCompat.CATEGORY_MESSAGE, str);
        } catch (JSONException unused) {
        }
        if (getType().equals(Const.TYPE_GDT) && i == 6000) {
            this.z = getType() + ":" + i + Operators.BRACKET_START_STR + str + Operators.BRACKET_END_STR;
            return;
        }
        if (getType().equals(Const.TYPE_BD) && i == -1) {
            this.z = getType() + ":" + str;
            return;
        }
        this.z = getType() + ":" + i;
    }

    protected void d(int i) {
        this.x = i;
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public int getAdStatus() {
        return this.x;
    }

    @Override // io.dcloud.sdk.core.module.DCBaseAOL
    public void startLoadTime() {
        this.B = System.currentTimeMillis();
    }

    protected final long w() {
        return this.w;
    }

    protected void x() {
        this.w = System.currentTimeMillis() - this.B;
    }

    public void y() {
        b3.b("uniAD", getDCloudId() + ":" + getType() + ":success;id:" + getSlotId());
        this.x = 1;
        this.w = System.currentTimeMillis() - this.B;
    }
}
