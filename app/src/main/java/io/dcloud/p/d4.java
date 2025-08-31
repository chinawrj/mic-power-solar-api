package io.dcloud.p;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes3.dex */
public abstract class d4 {
    public static void a(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(str, 0).edit();
        editorEdit.putString(str2, str3);
        editorEdit.apply();
    }

    public static String a(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, "");
        } catch (Exception unused) {
            return "";
        }
    }
}
