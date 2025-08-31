package io.dcloud.p;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class h0 {

    class a implements Runnable {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String[] b;

        a(IWebview iWebview, String[] strArr) {
            this.a = iWebview;
            this.b = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            h0.a(this.a, this.b);
        }
    }

    static class b {
        float a;
        float b;
        float c;
        float d;
        float e;
        float f;

        public b(String str, String str2, String str3, String str4, float f, float f2) {
            this.e = f;
            this.f = f2;
            this.a = h0.a(str, f2, 0.0f);
            float fA = h0.a(str2, this.e, 0.0f);
            this.b = fA;
            float f3 = this.e;
            this.c = h0.a(str3, f3, f3 - fA);
            float f4 = this.f;
            float fA2 = h0.a(str4, f4, f4 - this.a);
            this.d = fA2;
            float f5 = this.c;
            float f6 = this.b;
            float f7 = f5 + f6;
            float f8 = this.e;
            if (f7 > f8) {
                this.c = f8 - f6;
            }
            float f9 = this.a;
            float f10 = fA2 + f9;
            float f11 = this.f;
            if (f10 > f11) {
                this.d = f11 - f9;
            }
        }

        public boolean a() {
            return this.a <= this.f && this.b <= this.e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x019f A[Catch: all -> 0x0210, TryCatch #0 {, blocks: (B:5:0x0014, B:6:0x0019, B:7:0x001e, B:11:0x002b, B:13:0x0036, B:14:0x003a, B:16:0x0046, B:18:0x004c, B:19:0x005f, B:26:0x00a4, B:30:0x00b0, B:32:0x00b4, B:34:0x00bb, B:36:0x00c1, B:38:0x00f1, B:40:0x0108, B:42:0x0118, B:44:0x012f, B:46:0x0154, B:52:0x0174, B:54:0x019f, B:59:0x01f8, B:61:0x01fe, B:55:0x01e1, B:57:0x01e7, B:58:0x01ea, B:48:0x015a, B:50:0x0170, B:21:0x006a, B:22:0x0096, B:17:0x0049, B:66:0x0205), top: B:72:0x0014, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01e1 A[Catch: all -> 0x0210, TryCatch #0 {, blocks: (B:5:0x0014, B:6:0x0019, B:7:0x001e, B:11:0x002b, B:13:0x0036, B:14:0x003a, B:16:0x0046, B:18:0x004c, B:19:0x005f, B:26:0x00a4, B:30:0x00b0, B:32:0x00b4, B:34:0x00bb, B:36:0x00c1, B:38:0x00f1, B:40:0x0108, B:42:0x0118, B:44:0x012f, B:46:0x0154, B:52:0x0174, B:54:0x019f, B:59:0x01f8, B:61:0x01fe, B:55:0x01e1, B:57:0x01e7, B:58:0x01ea, B:48:0x015a, B:50:0x0170, B:21:0x006a, B:22:0x0096, B:17:0x0049, B:66:0x0205), top: B:72:0x0014, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01fe A[Catch: all -> 0x0210, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0014, B:6:0x0019, B:7:0x001e, B:11:0x002b, B:13:0x0036, B:14:0x003a, B:16:0x0046, B:18:0x004c, B:19:0x005f, B:26:0x00a4, B:30:0x00b0, B:32:0x00b4, B:34:0x00bb, B:36:0x00c1, B:38:0x00f1, B:40:0x0108, B:42:0x0118, B:44:0x012f, B:46:0x0154, B:52:0x0174, B:54:0x019f, B:59:0x01f8, B:61:0x01fe, B:55:0x01e1, B:57:0x01e7, B:58:0x01ea, B:48:0x015a, B:50:0x0170, B:21:0x006a, B:22:0x0096, B:17:0x0049, B:66:0x0205), top: B:72:0x0014, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void a(io.dcloud.common.DHInterface.IWebview r24, java.lang.String[] r25) {
        /*
            Method dump skipped, instructions count: 531
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.h0.a(io.dcloud.common.DHInterface.IWebview, java.lang.String[]):void");
    }

    public static void b(IWebview iWebview, String[] strArr) {
        ThreadPool.self().addThreadTask(new a(iWebview, strArr));
    }

    public static Bitmap.CompressFormat c(String str) {
        return (str.contains(".jpg") || str.contains(".jpeg")) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG;
    }

    public static boolean b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                if (file.length() >= 5) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    static class c {
        String a;
        String b;
        String c;
        boolean d;
        int e;
        float f;
        float g;
        int h;
        int i;
        int j;
        b k;
        boolean l = false;
        long m;

        c() {
        }

        public boolean a(JSONObject jSONObject, IWebview iWebview, String str) {
            this.a = jSONObject.optString("src");
            this.b = jSONObject.optString("dst");
            if (!a(iWebview, str)) {
                return false;
            }
            this.d = jSONObject.optBoolean("overwrite", false);
            this.c = jSONObject.optString(AbsoluteConst.JSON_KEY_FORMAT);
            this.e = jSONObject.optInt(Constants.Name.QUALITY, -1);
            a(iWebview.getContext(), jSONObject.optString("width", "auto"), jSONObject.optString("height", "auto"));
            this.h = jSONObject.optInt("rotate", -1);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("clip");
            if (jSONObjectOptJSONObject == null) {
                return true;
            }
            b bVar = new b(jSONObjectOptJSONObject.optString("top"), jSONObjectOptJSONObject.optString("left"), jSONObjectOptJSONObject.optString("width"), jSONObjectOptJSONObject.optString("height"), this.f, this.g);
            this.k = bVar;
            if (bVar.a()) {
                return true;
            }
            h0.a(iWebview, str, DOMException.MSG_PARAMETER_ERROR, -1);
            return false;
        }

        public boolean a(IWebview iWebview, String str) {
            if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
                String strConvert2AbsFullPath = iWebview.obtainFrameView().obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), this.a);
                this.a = strConvert2AbsFullPath;
                if (!h0.b(strConvert2AbsFullPath)) {
                    h0.a(iWebview, str, DOMException.MSG_FILE_NOT_EXIST, -4);
                    return false;
                }
                this.b = iWebview.obtainFrameView().obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), this.b);
                return true;
            }
            h0.a(iWebview, str, DOMException.MSG_PARAMETER_ERROR, -1);
            return false;
        }

        public void a(Context context, String str, String str2) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                h0.a(context, this.a, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int i = options.outWidth;
            this.i = i;
            int i2 = options.outHeight;
            this.j = i2;
            if (i2 <= 0 || i <= 0) {
                return;
            }
            this.m = new File(this.a).length();
            if (str.equals("auto") && str2.endsWith("auto")) {
                this.l = false;
                this.f = this.i;
                this.g = this.j;
                return;
            }
            if (str.equals("auto")) {
                this.l = true;
                float f = this.j;
                float f2 = PdrUtil.parseFloat(str2, f, f);
                this.g = f2;
                this.f = (this.i * f2) / this.j;
                return;
            }
            if (str2.equals("auto")) {
                this.l = true;
                float f3 = this.i;
                float f4 = PdrUtil.parseFloat(str, f3, f3);
                this.f = f4;
                this.g = (this.j * f4) / this.i;
                return;
            }
            this.l = true;
            float f5 = this.i;
            this.f = PdrUtil.parseFloat(str, f5, f5);
            float f6 = this.j;
            this.g = PdrUtil.parseFloat(str2, f6, f6);
        }
    }

    private static void a(String str, String str2) {
        Object obj;
        String string;
        String attribute;
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            ExifInterface exifInterface2 = new ExifInterface(str2);
            for (Field field : ExifInterface.class.getFields()) {
                String name = field.getName();
                if (!TextUtils.isEmpty(name) && name.startsWith("TAG") && (obj = field.get(ExifInterface.class)) != null && (attribute = exifInterface.getAttribute((string = obj.toString()))) != null) {
                    exifInterface2.setAttribute(string, attribute);
                }
            }
            exifInterface2.saveAttributes();
        } catch (Throwable unused) {
        }
    }

    public static Bitmap a(Context context, String str, BitmapFactory.Options options) throws IOException {
        if (!FileUtil.checkPrivatePath(context, str)) {
            InputStream fileInputStream = FileUtil.getFileInputStream(context, str);
            if (fileInputStream == null) {
                return null;
            }
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
            return bitmapDecodeStream;
        }
        return BitmapFactory.decodeFile(str, options);
    }

    public static void a(IWebview iWebview, String str, String str2, int i) {
        Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(i, str2), JSUtil.ERROR, true, false);
    }

    public static void a(IWebview iWebview, String str, String str2) {
        Deprecated_JSUtil.execCallback(iWebview, str, str2, JSUtil.OK, true, false);
    }

    public static long a(String str, Bitmap bitmap, boolean z, int i) throws IOException {
        File file = new File(str);
        if (file.exists()) {
            if (file.length() >= 1 && !z) {
                return -1L;
            }
            file.delete();
        } else if (a(str)) {
            file = new File(str);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(c(str), i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return file.length();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1L;
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1L;
        }
    }

    public static boolean a(String str) {
        int iLastIndexOf;
        if (!TextUtils.isEmpty(str) && (iLastIndexOf = str.lastIndexOf("/")) != -1 && iLastIndexOf != 0) {
            try {
                File file = new File(str.substring(0, iLastIndexOf));
                if (file.exists()) {
                    return true;
                }
                file.mkdirs();
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static float a(String str, float f, float f2) {
        return str.equals("auto") ? f2 : PdrUtil.parseFloat(str, f, f2);
    }
}
