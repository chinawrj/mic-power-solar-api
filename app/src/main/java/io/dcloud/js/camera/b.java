package io.dcloud.js.camera;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import io.dcloud.base.R;
import java.io.IOException;

/* loaded from: classes3.dex */
abstract class b {
    /* JADX WARN: Removed duplicated region for block: B:36:0x0043 A[Catch: Exception -> 0x003f, TRY_LEAVE, TryCatch #0 {Exception -> 0x003f, blocks: (B:32:0x003b, B:36:0x0043), top: B:40:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.graphics.Bitmap r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L1f
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L1f
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Throwable -> L19 java.lang.Exception -> L1b
            r3 = 100
            r4.compress(r2, r3, r1)     // Catch: java.lang.Throwable -> L19 java.lang.Exception -> L1b
            r1.close()     // Catch: java.lang.Exception -> L14
            r4.recycle()     // Catch: java.lang.Exception -> L14
            goto L18
        L14:
            r4 = move-exception
            r4.printStackTrace()
        L18:
            return r5
        L19:
            r5 = move-exception
            goto L39
        L1b:
            r5 = move-exception
            goto L21
        L1d:
            r5 = move-exception
            goto L38
        L1f:
            r5 = move-exception
            r1 = r0
        L21:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L36
            if (r1 == 0) goto L2c
            r1.close()     // Catch: java.lang.Exception -> L2a
            goto L2c
        L2a:
            r4 = move-exception
            goto L32
        L2c:
            if (r4 == 0) goto L35
            r4.recycle()     // Catch: java.lang.Exception -> L2a
            goto L35
        L32:
            r4.printStackTrace()
        L35:
            return r0
        L36:
            r5 = move-exception
            r0 = r1
        L38:
            r1 = r0
        L39:
            if (r1 == 0) goto L41
            r1.close()     // Catch: java.lang.Exception -> L3f
            goto L41
        L3f:
            r4 = move-exception
            goto L47
        L41:
            if (r4 == 0) goto L4a
            r4.recycle()     // Catch: java.lang.Exception -> L3f
            goto L4a
        L47:
            r4.printStackTrace()
        L4a:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.camera.b.a(android.graphics.Bitmap, java.lang.String):java.lang.String");
    }

    public static Bitmap b(String str) {
        return BitmapFactory.decodeFile(str, new BitmapFactory.Options());
    }

    public static int c(String str) {
        int attributeInt;
        try {
            attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (attributeInt == 3) {
            return 180;
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? 0 : 270;
        }
        return 90;
    }

    public static String a(String str) {
        int iC = c(str);
        if (iC == 0) {
            return str;
        }
        Bitmap bitmapB = b(str);
        if (bitmapB == null) {
            return null;
        }
        return a(a(iC, bitmapB), str);
    }

    public static Bitmap a(int i, Bitmap bitmap) {
        Bitmap bitmapCreateBitmap;
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError unused) {
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = bitmap;
        }
        if (bitmap != bitmapCreateBitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Dialog a(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.dialog_transparent);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dcloud_dialog_loading, (ViewGroup) null);
        viewGroup.findViewById(R.id.loading_background).setBackgroundColor(0);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setView(viewGroup, 0, 0, 0, 0);
        return alertDialogCreate;
    }
}
