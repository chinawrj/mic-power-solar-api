package io.dcloud.js.file;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.taobao.weex.common.Constants;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.AsyncTaskHandler;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.Md5Utils;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class FileFeatureImpl implements IFeature {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;

    class a implements AsyncTaskHandler.IAsyncTaskListener {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ String d;
        final /* synthetic */ IWebview e;
        final /* synthetic */ String f;

        a(String str, int i, int i2, String str2, IWebview iWebview, String str3) {
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = str2;
            this.e = iWebview;
            this.f = str3;
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onCancel() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteBegin() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteEnd(Object obj) {
            if (obj != null) {
                Deprecated_JSUtil.execCallback(this.e, this.f, String.valueOf(obj), JSUtil.OK, true, false);
            } else {
                FileFeatureImpl.this.a(10, this.e, this.f);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:47:0x0098 A[Catch: IOException -> 0x0094, TRY_LEAVE, TryCatch #6 {IOException -> 0x0094, blocks: (B:43:0x0090, B:47:0x0098), top: B:71:0x0090 }] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x00ae A[Catch: IOException -> 0x00aa, TRY_LEAVE, TryCatch #2 {IOException -> 0x00aa, blocks: (B:55:0x00a6, B:59:0x00ae), top: B:67:0x00a6 }] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object onExecuting() throws java.lang.Throwable {
            /*
                r10 = this;
                r0 = 0
                java.lang.String r1 = r10.a     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
                java.lang.Object r1 = io.dcloud.common.adapter.io.DHFile.createFileHandler(r1)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
                java.io.InputStream r1 = io.dcloud.common.adapter.io.DHFile.getInputStream(r1)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L85
                int r2 = r1.available()     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L80
                io.dcloud.common.adapter.io.UnicodeInputStream r3 = new io.dcloud.common.adapter.io.UnicodeInputStream     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L7c
                java.nio.charset.Charset r4 = java.nio.charset.Charset.defaultCharset()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L7c
                java.lang.String r4 = r4.name()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L7c
                r3.<init>(r1, r4)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L7c
                r1 = r3
                goto L22
            L1e:
                r3 = move-exception
                r3.printStackTrace()     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L80
            L22:
                java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L80
                r3.<init>()     // Catch: java.lang.Throwable -> L7c java.lang.Exception -> L80
                int r4 = r10.b     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                r5 = 0
                if (r4 <= 0) goto L42
                if (r4 < r2) goto L30
                int r4 = r2 + (-1)
            L30:
                int r2 = r10.c     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                int r4 = r4 - r2
                int r4 = r4 + 1
                byte[] r6 = new byte[r4]     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                long r7 = (long) r2     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                r1.skip(r7)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                r1.read(r6, r5, r4)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                r3.write(r6, r5, r4)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                goto L4e
            L42:
                r2 = 204800(0x32000, float:2.86986E-40)
                byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                int r4 = r1.read(r2)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                r6 = -1
                if (r4 != r6) goto L71
            L4e:
                java.lang.String r2 = r10.d     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                boolean r2 = io.dcloud.common.util.PdrUtil.isEmpty(r2)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                if (r2 == 0) goto L5b
                java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                goto L61
            L5b:
                java.lang.String r2 = r10.d     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                java.lang.String r0 = r3.toString(r2)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
            L61:
                java.lang.String r0 = io.dcloud.common.util.JSONUtil.toJSONableString(r0)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                r1.close()     // Catch: java.io.IOException -> L6c
                r3.close()     // Catch: java.io.IOException -> L6c
                goto La0
            L6c:
                r1 = move-exception
                r1.printStackTrace()
                goto La0
            L71:
                r3.write(r2, r5, r4)     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77
                goto L42
            L75:
                r0 = move-exception
                goto La4
            L77:
                r2 = move-exception
                r9 = r1
                r1 = r0
                r0 = r9
                goto L8b
            L7c:
                r2 = move-exception
                r3 = r0
                r0 = r1
                goto La2
            L80:
                r2 = move-exception
                goto L88
            L82:
                r2 = move-exception
                r3 = r0
                goto La2
            L85:
                r1 = move-exception
                r2 = r1
                r1 = r0
            L88:
                r3 = r0
                r0 = r1
                r1 = r3
            L8b:
                r2.printStackTrace()     // Catch: java.lang.Throwable -> La1
                if (r0 == 0) goto L96
                r0.close()     // Catch: java.io.IOException -> L94
                goto L96
            L94:
                r0 = move-exception
                goto L9c
            L96:
                if (r3 == 0) goto L9f
                r3.close()     // Catch: java.io.IOException -> L94
                goto L9f
            L9c:
                r0.printStackTrace()
            L9f:
                r0 = r1
            La0:
                return r0
            La1:
                r2 = move-exception
            La2:
                r1 = r0
                r0 = r2
            La4:
                if (r1 == 0) goto Lac
                r1.close()     // Catch: java.io.IOException -> Laa
                goto Lac
            Laa:
                r1 = move-exception
                goto Lb2
            Lac:
                if (r3 == 0) goto Lb5
                r3.close()     // Catch: java.io.IOException -> Laa
                goto Lb5
            Lb2:
                r1.printStackTrace()
            Lb5:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.a.onExecuting():java.lang.Object");
        }
    }

    class b implements AsyncTaskHandler.IAsyncTaskListener {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ IWebview d;
        final /* synthetic */ String e;

        b(String str, int i, int i2, IWebview iWebview, String str2) {
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = iWebview;
            this.e = str2;
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onCancel() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteBegin() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteEnd(Object obj) {
            if (obj != null) {
                Deprecated_JSUtil.execCallback(this.d, this.e, String.valueOf(obj), JSUtil.OK, true, false);
            } else {
                FileFeatureImpl.this.a(10, this.d, this.e);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00a0 A[Catch: IOException -> 0x009c, TRY_LEAVE, TryCatch #10 {IOException -> 0x009c, blocks: (B:50:0x0098, B:54:0x00a0), top: B:79:0x0098 }] */
        /* JADX WARN: Removed duplicated region for block: B:64:0x00b3 A[Catch: IOException -> 0x00af, TRY_LEAVE, TryCatch #6 {IOException -> 0x00af, blocks: (B:60:0x00ab, B:64:0x00b3), top: B:73:0x00ab }] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object onExecuting() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 187
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.b.onExecuting():java.lang.Object");
        }
    }

    class c extends CustomTarget {
        final /* synthetic */ String a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;

        c(String str, IWebview iWebview, String str2) {
            this.a = str;
            this.b = iWebview;
            this.c = str2;
        }

        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(File file, Transition transition) throws JSONException, IOException {
            if (PdrUtil.isEmpty(this.a)) {
                FileFeatureImpl.this.a(file.getAbsolutePath(), this.b, this.c);
            } else if (DHFile.copyFile(file.getPath(), this.a) != 1) {
                Deprecated_JSUtil.execCallback(this.b, this.c, DOMException.toJSON(13, "Failed to load resource"), JSUtil.ERROR, true, false);
            } else {
                FileFeatureImpl.this.a(this.a, this.b, this.c);
            }
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(Drawable drawable) {
            Deprecated_JSUtil.execCallback(this.b, this.c, DOMException.toJSON(13, "Failed to load resource"), JSUtil.ERROR, true, false);
        }
    }

    class d implements Runnable {
        final /* synthetic */ File a;
        final /* synthetic */ String b;
        final /* synthetic */ IWebview c;
        final /* synthetic */ String d;

        d(File file, String str, IWebview iWebview, String str2) {
            this.a = file;
            this.b = str;
            this.c = iWebview;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            String strMd5 = Md5Utils.md5(this.a, this.b);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AbsoluteConst.JSON_KEY_SIZE, this.a.length());
                if (strMd5 != null) {
                    jSONObject.put(Constants.CodeCache.BANNER_DIGEST, strMd5.toUpperCase(Locale.US));
                }
            } catch (JSONException unused) {
            }
            Deprecated_JSUtil.execCallback(this.c, this.d, jSONObject.toString(), JSUtil.OK, true, false);
        }
    }

    class e implements ISysEventListener {
        final /* synthetic */ int a;
        final /* synthetic */ IApp b;
        final /* synthetic */ IWebview c;
        final /* synthetic */ String d;

        e(int i, IApp iApp, IWebview iWebview, String str) {
            this.a = i;
            this.b = iApp;
            this.c = iWebview;
            this.d = str;
        }

        @Override // io.dcloud.common.DHInterface.ISysEventListener
        public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) throws JSONException {
            Object[] objArr = (Object[]) obj;
            int iIntValue = ((Integer) objArr[0]).intValue();
            Intent intent = (Intent) objArr[2];
            ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onActivityResult;
            if (sysEventType == sysEventType2 && iIntValue == this.a) {
                this.b.unregisterSysEventListener(this, sysEventType2);
                if (intent == null || (intent.getData() == null && intent.getClipData() == null)) {
                    Deprecated_JSUtil.execCallback(this.c, this.d, StringUtil.format(DOMException.JSON_ERROR_INFO, -2, DOMException.MSG_USER_CANCEL), JSUtil.ERROR, true, false);
                } else {
                    JSONArray jSONArray = new JSONArray();
                    ClipData clipData = intent.getClipData();
                    if (clipData != null) {
                        int itemCount = clipData.getItemCount();
                        for (int i = 0; i < itemCount; i++) {
                            jSONArray.put(FileUtil.getPathFromUri(this.b.getActivity(), clipData.getItemAt(i).getUri()));
                        }
                    } else {
                        Uri data = intent.getData();
                        String pathFromUri = FileUtil.getPathFromUri(this.b.getActivity(), data);
                        if (PdrUtil.isEmpty(pathFromUri)) {
                            pathFromUri = data.toString();
                        }
                        jSONArray.put(pathFromUri);
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("files", jSONArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Deprecated_JSUtil.execCallback(this.c, this.d, jSONObject.toString(), JSUtil.OK, true, false);
                }
            }
            return false;
        }
    }

    class f implements AsyncTaskHandler.IAsyncTaskListener {
        final /* synthetic */ String[] a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;
        final /* synthetic */ int d;
        final /* synthetic */ String e;

        f(String[] strArr, IWebview iWebview, String str, int i, String str2) {
            this.a = strArr;
            this.b = iWebview;
            this.c = str;
            this.d = i;
            this.e = str2;
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onCancel() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteBegin() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteEnd(Object obj) {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public Object onExecuting() throws IOException {
            byte[] bArrDecode2bytes = Base64.decode2bytes(this.a[1]);
            if (bArrDecode2bytes == null) {
                Deprecated_JSUtil.execCallback(this.b, this.c, StringUtil.format(DOMException.JSON_ERROR_INFO, 16, this.b.getContext().getString(R.string.dcloud_io_write_non_base64)).toString(), JSUtil.ERROR, true, false);
                return null;
            }
            DHFile.writeFile(bArrDecode2bytes, this.d, this.e);
            JSUtil.execCallback(this.b, this.c, bArrDecode2bytes.length, JSUtil.OK, false);
            return null;
        }
    }

    class g implements AsyncTaskHandler.IAsyncTaskListener {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ IWebview d;
        final /* synthetic */ String e;

        g(String str, int i, int i2, IWebview iWebview, String str2) {
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = iWebview;
            this.e = str2;
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onCancel() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteBegin() {
        }

        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        public void onExecuteEnd(Object obj) {
            if (obj != null) {
                Deprecated_JSUtil.execCallback(this.d, this.e, String.valueOf(obj), JSUtil.OK, true, false);
            } else {
                FileFeatureImpl.this.a(10, this.d, this.e);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00b0 A[Catch: IOException -> 0x00ac, TRY_LEAVE, TryCatch #8 {IOException -> 0x00ac, blocks: (B:49:0x00a8, B:53:0x00b0), top: B:66:0x00a8 }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:72:? A[SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v4, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r6v5, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r6v6 */
        /* JADX WARN: Type inference failed for: r6v7, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r6v8 */
        @Override // io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object onExecuting() throws java.lang.Throwable {
            /*
                r16 = this;
                r1 = r16
                r2 = 0
                r3 = 2
                r4 = 1
                r5 = 0
                java.lang.String r0 = r1.a     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6c
                java.lang.Object r0 = io.dcloud.common.adapter.io.DHFile.createFileHandler(r0)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6c
                java.io.InputStream r6 = io.dcloud.common.adapter.io.DHFile.getInputStream(r0)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6c
                int r0 = r6.available()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                int r7 = r1.b     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                if (r7 <= 0) goto L2f
                if (r7 < r0) goto L1c
                int r7 = r0 + (-1)
            L1c:
                int r0 = r1.c     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                int r7 = r7 - r0
                int r7 = r7 + r4
                byte[] r8 = new byte[r7]     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                long r9 = (long) r0     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                r6.skip(r9)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                r6.read(r8, r2, r7)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                java.lang.String r0 = android.util.Base64.encodeToString(r8, r3)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                r7 = r5
                goto L48
            L2f:
                java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
                r7.<init>()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L66
            L34:
                r0 = 204800(0x32000, float:2.86986E-40)
                byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
                int r8 = r6.read(r0)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
                r9 = -1
                if (r8 != r9) goto L5a
                byte[] r0 = r7.toByteArray()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
                java.lang.String r0 = android.util.Base64.encodeToString(r0, r3)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            L48:
                java.lang.String r5 = io.dcloud.common.util.JSONUtil.toJSONableString(r0)     // Catch: java.lang.Exception -> L61 java.lang.Throwable -> La2
                r6.close()     // Catch: java.io.IOException -> L55
                if (r7 == 0) goto La1
                r7.close()     // Catch: java.io.IOException -> L55
                goto La1
            L55:
                r0 = move-exception
                r0.printStackTrace()
                goto La1
            L5a:
                r7.write(r0, r2, r8)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
                goto L34
            L5e:
                r0 = move-exception
                r2 = r0
                goto La6
            L61:
                r0 = move-exception
                goto L6f
            L63:
                r0 = move-exception
                r7 = r5
                goto La3
            L66:
                r0 = move-exception
                r7 = r5
                goto L6f
            L69:
                r0 = move-exception
                r7 = r5
                goto La4
            L6c:
                r0 = move-exception
                r6 = r5
                r7 = r6
            L6f:
                java.lang.String r8 = "{code:%d,message:'%s'}"
                r9 = 13
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> La2
                java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> La2
                java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La2
                r3[r2] = r9     // Catch: java.lang.Throwable -> La2
                r3[r4] = r0     // Catch: java.lang.Throwable -> La2
                java.lang.String r12 = io.dcloud.common.util.StringUtil.format(r8, r3)     // Catch: java.lang.Throwable -> La2
                io.dcloud.common.DHInterface.IWebview r10 = r1.d     // Catch: java.lang.Throwable -> La2
                java.lang.String r11 = r1.e     // Catch: java.lang.Throwable -> La2
                int r13 = io.dcloud.common.util.JSUtil.ERROR     // Catch: java.lang.Throwable -> La2
                r14 = 1
                r15 = 0
                io.dcloud.common.util.JSUtil.execCallback(r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> La2
                if (r6 == 0) goto L98
                r6.close()     // Catch: java.io.IOException -> L96
                goto L98
            L96:
                r0 = move-exception
                goto L9e
            L98:
                if (r7 == 0) goto La1
                r7.close()     // Catch: java.io.IOException -> L96
                goto La1
            L9e:
                r0.printStackTrace()
            La1:
                return r5
            La2:
                r0 = move-exception
            La3:
                r5 = r6
            La4:
                r2 = r0
                r6 = r5
            La6:
                if (r6 == 0) goto Lae
                r6.close()     // Catch: java.io.IOException -> Lac
                goto Lae
            Lac:
                r0 = move-exception
                goto Lb4
            Lae:
                if (r7 == 0) goto Lb7
                r7.close()     // Catch: java.io.IOException -> Lac
                goto Lb7
            Lb4:
                r0.printStackTrace()
            Lb7:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.g.onExecuting():java.lang.Object");
        }
    }

    private void b(IWebview iWebview, String[] strArr, String str) {
        try {
            String str2 = strArr[0];
            if (!FileUtil.checkPathAccord(iWebview.getContext(), str2)) {
                a(15, iWebview, str);
                return;
            }
            boolean zCheckPrivateDir = iWebview.obtainApp().checkPrivateDir(str2);
            int iIntValue = Integer.valueOf(strArr[2]).intValue();
            if (strArr[1] != null && !zCheckPrivateDir) {
                AsyncTaskHandler.executeThreadTask(new f(strArr, iWebview, str, iIntValue, str2));
                return;
            }
            a(4, iWebview, str);
        } catch (Exception unused) {
            a(10, iWebview, str);
        }
    }

    private String c(String str) {
        if (str.startsWith(a)) {
            return AbsoluteConst.MINI_SERVER_APP_WWW + str.substring(a.length(), str.length());
        }
        if (str.startsWith(c)) {
            return AbsoluteConst.MINI_SERVER_APP_DOC + str.substring(c.length(), str.length());
        }
        if (str.startsWith(d)) {
            return "_documents/" + str.substring(d.length(), str.length());
        }
        if (!str.startsWith(e)) {
            return null;
        }
        return "_downloads/" + str.substring(e.length(), str.length());
    }

    private String d(String str) {
        boolean z;
        String strB = b(str);
        if (PdrUtil.isEmpty(strB)) {
            strB = String.valueOf(-1);
            z = true;
        } else {
            z = false;
        }
        return z ? str.startsWith(a) ? String.valueOf(1) : str.startsWith(c) ? String.valueOf(2) : str.startsWith(d) ? String.valueOf(3) : str.startsWith(e) ? String.valueOf(4) : strB : strB;
    }

    private boolean e(String str) {
        return str.endsWith(BaseInfo.REL_PRIVATE_WWW_DIR) || str.endsWith(BaseInfo.REL_PUBLIC_DOCUMENTS_DIR) || str.endsWith(BaseInfo.REL_PUBLIC_DOWNLOADS_DIR) || str.endsWith(BaseInfo.REL_PRIVATE_DOC_DIR) || str.endsWith(AbsoluteConst.MINI_SERVER_APP_WWW) || str.endsWith("_documents/") || str.endsWith("_downloads/") || str.endsWith(AbsoluteConst.MINI_SERVER_APP_DOC);
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0440 A[Catch: JSONException -> 0x0474, TryCatch #14 {JSONException -> 0x0474, blocks: (B:214:0x03bb, B:217:0x03c3, B:219:0x03cb, B:221:0x03d1, B:225:0x03dc, B:227:0x03e9, B:228:0x0410, B:243:0x0440, B:244:0x046d, B:240:0x043a, B:231:0x041d, B:233:0x0427, B:234:0x042a), top: B:608:0x03bb, inners: #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:244:0x046d A[Catch: JSONException -> 0x0474, TRY_LEAVE, TryCatch #14 {JSONException -> 0x0474, blocks: (B:214:0x03bb, B:217:0x03c3, B:219:0x03cb, B:221:0x03d1, B:225:0x03dc, B:227:0x03e9, B:228:0x0410, B:243:0x0440, B:244:0x046d, B:240:0x043a, B:231:0x041d, B:233:0x0427, B:234:0x042a), top: B:608:0x03bb, inners: #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:401:0x07e8  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x07f0  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x005d  */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8, types: [java.io.FileInputStream] */
    @Override // io.dcloud.common.DHInterface.IFeature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r21, java.lang.String r22, java.lang.String[] r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 3306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
    }

    private void a(IWebview iWebview, IApp iApp, String[] strArr, String str) {
        String str2 = strArr[0];
        if (!FileUtil.checkPathAccord(iWebview.getContext(), str2)) {
            a(15, iWebview, str);
            return;
        }
        boolean zCheckPrivateDir = iApp.checkPrivateDir(str2);
        int i = PdrUtil.parseInt(strArr[1], 0);
        int i2 = PdrUtil.parseInt(strArr[2], -1);
        if (zCheckPrivateDir && iApp.isOnAppRunningMode()) {
            a(10, iWebview, str);
        } else {
            AsyncTaskHandler.executeThreadTask(new g(str2, i2, i, iWebview, str));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x0228 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0217 A[Catch: Exception -> 0x025c, TryCatch #3 {Exception -> 0x025c, blocks: (B:25:0x00b2, B:27:0x00c1, B:31:0x00d6, B:33:0x00ff, B:36:0x0107, B:41:0x0116, B:46:0x0147, B:49:0x0163, B:51:0x0172, B:55:0x0188, B:57:0x018f, B:52:0x0176, B:54:0x0180, B:65:0x01c5, B:91:0x0206, B:93:0x0217, B:94:0x021c, B:72:0x01d8, B:75:0x01e0, B:78:0x01ea, B:98:0x024c, B:28:0x00c5, B:30:0x00cf), top: B:118:0x00b2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.lang.String r18, java.lang.String r19, io.dcloud.common.DHInterface.IWebview r20, java.lang.String r21) throws org.json.JSONException, java.io.IOException, java.lang.SecurityException, java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instructions count: 645
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.a(java.lang.String, java.lang.String, io.dcloud.common.DHInterface.IWebview, java.lang.String):void");
    }

    public static String b(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    private JSONObject a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (str.startsWith(a)) {
            jSONObject.put("type", 1);
            jSONObject.put("fsName", "PRIVATE_WWW");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PRIVATE_WWW", a, c(str), true));
        } else if (str.startsWith(c)) {
            jSONObject.put("type", 2);
            jSONObject.put("fsName", "PRIVATE_DOCUMENTS");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PRIVATE_DOCUMENTS", c, c(str), true));
        } else if (str.startsWith(d)) {
            jSONObject.put("type", 3);
            jSONObject.put("fsName", "PUBLIC_DOCUMENTS");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PUBLIC_DOCUMENTS", d, c(str), true));
        } else if (str.startsWith(e)) {
            jSONObject.put("type", 4);
            jSONObject.put("fsName", "PUBLIC_DOWNLOADS");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PUBLIC_DOWNLOADS", e, c(str), true));
        } else if (str.startsWith(b)) {
            jSONObject.put("type", 1);
            jSONObject.put("fsName", "PRIVATE_WWW");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PRIVATE_WWW", b, c(str), true));
        } else if (PdrUtil.isDeviceRootDir(str)) {
            jSONObject.put("type", 5);
            jSONObject.put("fsName", "PUBLIC_DEVICE_ROOT");
            jSONObject.put("fsRoot", io.dcloud.js.file.a.a("PUBLIC_DEVICE_ROOT", DeviceInfo.sDeviceRootDir, c(str), true));
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, IWebview iWebview, String str) {
        Deprecated_JSUtil.execCallback(iWebview, str, a(iWebview.getContext(), i), JSUtil.ERROR, true, false);
    }

    private String a(Context context, int i) {
        switch (i) {
            case 1:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_file_not_found));
            case 2:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_without_authorization));
            case 3:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_common_cancel));
            case 4:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_file_not_read));
            case 5:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_coding_error));
            case 6:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_no_modification_allowed));
            case 7:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_invalid_state));
            case 8:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_grammar_mistakes));
            case 9:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_invalid_modification));
            case 10:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_perform_error));
            case 11:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_type_mismatch));
            case 12:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_path_exists));
            case 13:
            default:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_unknown_error));
            case 14:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), context.getString(R.string.dcloud_io_path_not_exist));
            case 15:
                return StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), DOMException.MSG_PATH_NOT_PRIVATE_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r15, io.dcloud.common.DHInterface.IWebview r16, java.lang.String r17) throws org.json.JSONException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.file.FileFeatureImpl.a(java.lang.String, io.dcloud.common.DHInterface.IWebview, java.lang.String):void");
    }

    private String[] a(IWebview iWebview, String[] strArr, String str) {
        if (strArr.length > 1 && !PdrUtil.isEmpty(strArr[1])) {
            try {
                return JSUtil.jsonArrayToStringArr(new JSONArray(strArr[1]));
            } catch (JSONException e2) {
                e2.printStackTrace();
                a(8, iWebview, str);
            }
        }
        return null;
    }
}
