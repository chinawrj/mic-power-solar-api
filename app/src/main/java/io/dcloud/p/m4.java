package io.dcloud.p;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.dcloudimageloader.core.download.BaseImageDownloader;
import io.dcloud.base.R;
import io.dcloud.p.u2;
import io.dcloud.sdk.base.entry.AdData;
import java.io.IOException;
import java.util.Locale;
import pl.droidsonroids.gif.GifDrawable;

/* loaded from: classes3.dex */
public class m4 extends RelativeLayout implements View.OnClickListener {
    Drawable a;
    private ViewGroup b;
    private View c;
    private ImageView d;
    private TextView e;
    private u2.c f;
    private AdData g;
    private int h;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (m4.this.f != null) {
                m4.this.f.i();
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ ViewGroup a;

        b(ViewGroup viewGroup) {
            this.a = viewGroup;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!io.dcloud.p.a.a(this.a)) {
                if (m4.this.f != null) {
                    m4.this.f.a(60010, "广告容器不可见");
                    return;
                }
                return;
            }
            m4 m4Var = m4.this;
            if (m4Var.a == null) {
                if (m4Var.f != null) {
                    m4.this.f.a(60004, "图片资源加载失败");
                    return;
                }
                return;
            }
            this.a.removeAllViews();
            this.a.getGlobalVisibleRect(new Rect());
            this.a.getRootView().getGlobalVisibleRect(new Rect());
            this.a.addView(m4.this, new ViewGroup.LayoutParams(-1, -1));
            m4.this.g.a(new RectF(this.a.getX(), this.a.getY(), 0.0f, 0.0f));
            m4.this.d.setImageDrawable(m4.this.a);
            if (m4.this.f != null) {
                m4.this.f.b();
            }
            m4.this.g.a();
            j4.a().b(m4.this.getContext(), m4.this.g.k());
        }
    }

    public m4(Context context, u2.c cVar, AdData adData) {
        super(context);
        this.h = BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT;
        this.f = cVar;
        this.g = adData;
        this.h = adData.i();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.dcloud_ad_splash_container, (ViewGroup) null);
        this.b = viewGroup;
        this.c = viewGroup.findViewById(R.id.ad_dcloud_main_skip);
        this.d = (ImageView) this.b.findViewById(R.id.ad_dcloud_main_img);
        this.e = (TextView) this.b.findViewById(R.id.ad_dcloud_main_click);
        this.d.setOnClickListener(this);
        this.c.setOnClickListener(this);
        addView(this.b, -1);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (adData.g() == null) {
            BitmapFactory.decodeFile(adData.f(), options);
            String str = options.outMimeType;
            if (TextUtils.isEmpty(str) || !str.toLowerCase(Locale.ENGLISH).contains("gif")) {
                this.a = new k4(BitmapFactory.decodeFile(adData.f()), getContext());
                return;
            }
            try {
                this.a = new GifDrawable(adData.f());
                return;
            } catch (IOException unused) {
                this.a = new k4(BitmapFactory.decodeFile(adData.f()), getContext());
                return;
            }
        }
        BitmapFactory.decodeByteArray(adData.g(), 0, adData.g().length, options);
        String str2 = options.outMimeType;
        if (TextUtils.isEmpty(str2) || !str2.toLowerCase(Locale.ENGLISH).contains("gif")) {
            this.a = new k4(BitmapFactory.decodeByteArray(adData.g(), 0, adData.g().length), getContext());
            return;
        }
        try {
            this.a = new GifDrawable(adData.g());
        } catch (IOException unused2) {
            this.a = new k4(BitmapFactory.decodeByteArray(adData.g(), 0, adData.g().length), getContext());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.g.a(motionEvent);
        } else if (motionEvent.getAction() == 1) {
            this.g.b(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.ad_dcloud_main_skip) {
            u2.c cVar = this.f;
            if (cVar != null) {
                cVar.m();
                return;
            }
            return;
        }
        if (view.getId() == R.id.ad_dcloud_main_img) {
            u2.c cVar2 = this.f;
            if (cVar2 != null) {
                cVar2.a();
            }
            this.g.a(getContext());
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
    }

    public void a(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        viewGroup.postDelayed(new a(), this.h);
        viewGroup.postDelayed(new b(viewGroup), 50L);
    }
}
