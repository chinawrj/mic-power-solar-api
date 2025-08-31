package io.dcloud.p;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import io.dcloud.sdk.base.entry.AdData;

/* loaded from: classes3.dex */
public abstract class u2 {
    protected c a;
    private Context b;
    protected String c;
    protected AdData d;

    class a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ String b;

        a(int i, String str) {
            this.a = i;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            u2.this.a.onError(this.a, this.b);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            u2.this.a.o();
        }
    }

    public interface c {
        void a();

        void a(int i, String str);

        void b();

        void i();

        void m();

        void o();

        void onError(int i, String str);
    }

    public u2(c cVar, Context context, String str) {
        this.a = cVar;
        this.b = context;
        this.c = str;
    }

    public void a(ViewGroup viewGroup) {
        if (viewGroup == null) {
            a(60010, "广告容器不可见");
        } else if (this.d == null) {
            a(60005, "数据解析失败");
        } else {
            new m4(viewGroup.getContext(), this.a, this.d).a(viewGroup);
        }
    }

    public Context b() {
        return this.b;
    }

    protected void a(int i, String str) {
        if (this.a != null) {
            new Handler(Looper.getMainLooper()).post(new a(i, str));
        }
    }

    protected void a() {
        if (this.a != null) {
            new Handler(Looper.getMainLooper()).post(new b());
        }
    }
}
