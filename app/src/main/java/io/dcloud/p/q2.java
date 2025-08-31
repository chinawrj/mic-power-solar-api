package io.dcloud.p;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

/* loaded from: classes3.dex */
public class q2 {
    private String a;
    private int b;
    private int c;

    public q2(String str, int i, int i2) {
        this.a = str;
        this.b = i;
        this.c = i2;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.a;
    }

    public boolean d() {
        return TextUtils.isEmpty(this.a);
    }

    public String toString() {
        return "IMGText{text='" + this.a + "', color=" + this.b + Operators.BLOCK_END;
    }
}
