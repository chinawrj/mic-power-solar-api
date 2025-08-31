package io.dcloud.p;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes3.dex */
public class e2 extends d2 {
    public e2(Uri uri) {
        super(uri);
    }

    @Override // io.dcloud.p.d2
    public Bitmap a(BitmapFactory.Options options) {
        Uri uriA = a();
        if (uriA == null) {
            return null;
        }
        String path = uriA.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = new File(path);
        if (file.exists()) {
            return options.inJustDecodeBounds ? BitmapFactory.decodeFile(path, options) : z.a(file, options);
        }
        return null;
    }
}
