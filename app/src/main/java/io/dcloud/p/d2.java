package io.dcloud.p;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

/* loaded from: classes3.dex */
public abstract class d2 {
    private Uri a;

    public d2(Uri uri) {
        this.a = uri;
    }

    public abstract Bitmap a(BitmapFactory.Options options);

    public Uri a() {
        return this.a;
    }
}
