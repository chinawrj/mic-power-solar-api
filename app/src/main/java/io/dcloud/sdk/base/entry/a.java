package io.dcloud.sdk.base.entry;

import android.content.Context;
import io.dcloud.p.j4;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class a {
    private long a;
    private String b;
    private AdData c;

    public a(String str) {
        this.b = str;
    }

    public void a(String str) {
        try {
            Date date = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(new JSONObject(str).optString("expires"));
            if (date != null) {
                this.a = date.getTime();
            }
        } catch (ParseException | JSONException unused) {
        }
    }

    public boolean b() {
        return System.currentTimeMillis() > this.a;
    }

    public void a(Context context, AdData adData) throws IllegalAccessException, SecurityException, IOException, IllegalArgumentException {
        if (adData != null) {
            adData.c(context);
            String strA = j4.a().a(context, this.b);
            if (strA != null) {
                try {
                    adData.a(new JSONObject(strA), null, false);
                } catch (JSONException unused) {
                }
            }
        }
    }

    public void a(AdData adData) {
        this.c = adData;
    }

    public String a() {
        return this.b;
    }
}
