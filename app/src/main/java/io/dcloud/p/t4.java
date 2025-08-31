package io.dcloud.p;

import com.taobao.weex.el.parse.Operators;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class t4 {
    private int a;
    private int b;
    private String c;
    private String d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private int l;
    private String m;
    private String n;
    private int o;
    private boolean p;
    private JSONObject q;
    private int r;
    private int s;

    public static final class b {
        private int a;
        private int b;
        private String c;
        private String d;
        private int e;
        private int f;
        private int g;
        private boolean h;
        private int i;
        private int j;
        private int k;
        private String l;
        private String m;
        private int n;
        private boolean o;
        private JSONObject p;
        private int q;
        private int r;

        public b a(String str) {
            this.l = str;
            return this;
        }

        public b b(boolean z) {
            this.h = z;
            return this;
        }

        public b c(String str) {
            this.d = str;
            return this;
        }

        public b d(String str) {
            this.c = str;
            return this;
        }

        public b e(int i) {
            this.r = i;
            return this;
        }

        public b f(int i) {
            this.a = i;
            return this;
        }

        public b g(int i) {
            this.f = i;
            return this;
        }

        public b h(int i) {
            this.n = i;
            return this;
        }

        public b i(int i) {
            this.b = i;
            return this;
        }

        public b j(int i) {
            this.i = i;
            return this;
        }

        public b k(int i) {
            this.e = i;
            return this;
        }

        public b a(boolean z) {
            this.o = z;
            return this;
        }

        public b b(int i) {
            this.j = i;
            return this;
        }

        public b c(int i) {
            this.g = i;
            return this;
        }

        public b d(int i) {
            this.k = i;
            return this;
        }

        public b a(JSONObject jSONObject) {
            this.p = jSONObject;
            return this;
        }

        public b b(String str) {
            this.m = str;
            return this;
        }

        public b a(int i) {
            this.q = i;
            return this;
        }

        public t4 a() {
            return new t4(this);
        }
    }

    public void a(int i) {
        this.b = i;
    }

    public int b() {
        return this.j;
    }

    public int c() {
        return this.g;
    }

    public int d() {
        return this.l;
    }

    public int e() {
        return this.s;
    }

    public int f() {
        return this.a;
    }

    public int g() {
        return this.f;
    }

    public String h() {
        return this.m;
    }

    public int i() {
        return this.o;
    }

    public JSONObject j() {
        return this.q;
    }

    public String k() {
        return this.n;
    }

    public String l() {
        return this.d;
    }

    public int m() {
        return this.b;
    }

    public String n() {
        return this.c;
    }

    public int o() {
        return this.i;
    }

    public int p() {
        return this.e;
    }

    public boolean q() {
        return this.p;
    }

    public boolean r() {
        return this.k;
    }

    public boolean s() {
        return this.h;
    }

    public String toString() {
        return "cfg{level=" + this.a + ", ss=" + this.b + ", sid='" + this.c + "', p='" + this.d + "', w=" + this.e + ", m=" + this.f + ", cpm=" + this.g + ", bdt=" + this.h + ", sto=" + this.i + ", type=" + this.j + Operators.BLOCK_END;
    }

    private t4(b bVar) {
        this.k = false;
        this.o = -1;
        this.p = false;
        this.s = 1;
        this.a = bVar.a;
        this.b = bVar.b;
        this.c = bVar.c;
        this.d = bVar.d;
        this.e = bVar.e;
        this.f = bVar.f;
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.k = this.e > 0 || this.f > 0;
        this.l = bVar.k;
        this.m = bVar.l;
        this.n = bVar.m;
        this.o = bVar.n;
        this.p = bVar.o;
        this.q = bVar.p;
        this.r = bVar.q;
        this.s = bVar.r;
    }

    public int a() {
        return this.r;
    }
}
