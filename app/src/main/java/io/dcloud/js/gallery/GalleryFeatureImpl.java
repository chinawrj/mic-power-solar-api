package io.dcloud.js.gallery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.hjq.permissions.Permission;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class GalleryFeatureImpl implements IFeature {
    private static String d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/";
    private static int e = 1001;
    private static int f = 1002;
    private static int g = 1003;
    private static int h = 1004;
    private static IWebview i;
    private ArrayList b;
    AbsMgr a = null;
    private boolean c = false;

    class a extends PermissionUtil.Request {
        final /* synthetic */ String[] a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;

        a(String[] strArr, IWebview iWebview, String str) {
            this.a = strArr;
            this.b = iWebview;
            this.c = str;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            if (Permission.READ_MEDIA_VIDEO.equals(str)) {
                return;
            }
            if (ActivityCompat.checkSelfPermission(this.b.getContext(), Permission.READ_MEDIA_VISUAL_USER_SELECTED) != -1) {
                onGranted(Permission.READ_MEDIA_IMAGES);
                return;
            }
            String json = DOMException.toJSON(12, DOMException.MSG_NO_PERMISSION);
            Deprecated_JSUtil.execCallback(this.b, this.a[0], json, JSUtil.ERROR, true, false);
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            String[] strArr = this.a;
            boolean zOptBoolean = false;
            if (strArr.length >= 2) {
                String str2 = strArr[1];
                if (!PdrUtil.isEmpty(str2)) {
                    try {
                        zOptBoolean = new JSONObject(str2).optBoolean("multiple", false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (GalleryFeatureImpl.this.c) {
                return;
            }
            if (zOptBoolean) {
                GalleryFeatureImpl.this.a(this.b, this.c, this.a);
            } else {
                GalleryFeatureImpl.this.b(this.b, this.c, this.a);
            }
            GalleryFeatureImpl.this.c = true;
        }
    }

    class b extends PermissionUtil.StreamPermissionRequest {
        final /* synthetic */ String a;
        final /* synthetic */ IApp b;
        final /* synthetic */ IWebview c;
        final /* synthetic */ String d;
        final /* synthetic */ String[] e;

        class a extends CustomTarget {
            a() {
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(byte[] bArr, Transition transition) throws Throwable {
                String downloadFilename = PdrUtil.getDownloadFilename(null, "image/*", b.this.a);
                if (!downloadFilename.contains(Operators.DOT_STR) || FileUtil.getFileTypeForSuffix(downloadFilename.substring(downloadFilename.lastIndexOf(Operators.DOT_STR) + 1)) == null) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                    String str = options.outMimeType;
                    if (!PdrUtil.isEmpty(str)) {
                        String[] strArrSplit = str.split("/");
                        if (strArrSplit.length > 1 && !PdrUtil.isEmpty(strArrSplit[1]) && !strArrSplit[1].contains("*")) {
                            downloadFilename = downloadFilename + "_" + System.currentTimeMillis() + Operators.DOT_STR + strArrSplit[1];
                        }
                    }
                }
                String pathFromUri = GalleryFeatureImpl.d + downloadFilename;
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                if (FileUtil.needMediaStoreOpenFile(b.this.b.getActivity())) {
                    Uri uriCopyMediaFileToDCIM = FileUtil.copyMediaFileToDCIM(b.this.c.getContext(), byteArrayInputStream, downloadFilename);
                    if (uriCopyMediaFileToDCIM != null) {
                        pathFromUri = FileUtil.getPathFromUri(b.this.c.getContext(), uriCopyMediaFileToDCIM);
                    } else {
                        String json = DOMException.toJSON(12, "SAVE ERROR");
                        b bVar = b.this;
                        Deprecated_JSUtil.execCallback(bVar.c, bVar.d, json, JSUtil.ERROR, true, false);
                    }
                } else {
                    FileUtil.writeStream2File(byteArrayInputStream, new File(pathFromUri));
                }
                String strConvert2WebviewFullPath = b.this.b.convert2WebviewFullPath(null, pathFromUri);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("file", strConvert2WebviewFullPath);
                    jSONObject.put(AbsoluteConst.XML_PATH, strConvert2WebviewFullPath);
                    b bVar2 = b.this;
                    JSUtil.execCallback(bVar2.c, bVar2.d, jSONObject, JSUtil.OK, false);
                    b.this.c.getContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse(DeviceInfo.FILE_PROTOCOL + pathFromUri)));
                } catch (JSONException unused) {
                }
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                String json = DOMException.toJSON(12, "UNKOWN ERROR");
                b bVar = b.this;
                Deprecated_JSUtil.execCallback(bVar.c, bVar.d, json, JSUtil.ERROR, true, false);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(IApp iApp, String str, IApp iApp2, IWebview iWebview, String str2, String[] strArr) {
            super(iApp);
            this.a = str;
            this.b = iApp2;
            this.c = iWebview;
            this.d = str2;
            this.e = strArr;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            Deprecated_JSUtil.execCallback(this.c, this.d, DOMException.toJSON(12, DOMException.MSG_NO_PERMISSION), JSUtil.ERROR, true, false);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x00bf  */
        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onGranted(java.lang.String r8) throws org.json.JSONException, java.io.IOException {
            /*
                Method dump skipped, instructions count: 304
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.gallery.GalleryFeatureImpl.b.onGranted(java.lang.String):void");
        }
    }

    class c implements ISysEventListener {
        final /* synthetic */ IApp a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;

        c(IApp iApp, IWebview iWebview, String str) {
            this.a = iApp;
            this.b = iWebview;
            this.c = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x00af  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00b1  */
        @Override // io.dcloud.common.DHInterface.ISysEventListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onExecute(io.dcloud.common.DHInterface.ISysEventListener.SysEventType r11, java.lang.Object r12) throws org.json.JSONException {
            /*
                r10 = this;
                java.lang.Object[] r12 = (java.lang.Object[]) r12
                r0 = 0
                r1 = r12[r0]
                java.lang.Integer r1 = (java.lang.Integer) r1
                int r1 = r1.intValue()
                r2 = 1
                r2 = r12[r2]
                java.lang.Integer r2 = (java.lang.Integer) r2
                r2.intValue()
                r2 = 2
                r12 = r12[r2]
                android.content.Intent r12 = (android.content.Intent) r12
                io.dcloud.common.DHInterface.ISysEventListener$SysEventType r2 = io.dcloud.common.DHInterface.ISysEventListener.SysEventType.onActivityResult
                if (r11 != r2) goto Lb6
                io.dcloud.common.DHInterface.IApp r11 = r10.a
                r11.unregisterSysEventListener(r10, r2)
                r11 = 0
                int r2 = io.dcloud.js.gallery.GalleryFeatureImpl.b()     // Catch: java.lang.Exception -> La4
                if (r1 != r2) goto L30
                java.lang.String r1 = "all_path"
                java.lang.String[] r1 = r12.getStringArrayExtra(r1)     // Catch: java.lang.Exception -> La4
                goto La2
            L30:
                int r2 = io.dcloud.js.gallery.GalleryFeatureImpl.c()     // Catch: java.lang.Exception -> La4
                if (r1 != r2) goto L90
                if (r12 == 0) goto La4
                android.content.ClipData r1 = r12.getClipData()     // Catch: java.lang.Exception -> La4
                if (r1 == 0) goto L5e
                int r2 = r1.getItemCount()     // Catch: java.lang.Exception -> La4
                java.lang.String[] r3 = new java.lang.String[r2]     // Catch: java.lang.Exception -> La4
                r4 = r0
            L45:
                if (r4 >= r2) goto La5
                android.content.ClipData$Item r5 = r1.getItemAt(r4)     // Catch: java.lang.Exception -> La5
                android.net.Uri r5 = r5.getUri()     // Catch: java.lang.Exception -> La5
                io.dcloud.common.DHInterface.IWebview r6 = r10.b     // Catch: java.lang.Exception -> La5
                android.app.Activity r6 = r6.getActivity()     // Catch: java.lang.Exception -> La5
                java.lang.String r5 = io.dcloud.common.adapter.util.ContentUriUtil.getImageAbsolutePath(r6, r5)     // Catch: java.lang.Exception -> La5
                r3[r4] = r5     // Catch: java.lang.Exception -> La5
                int r4 = r4 + 1
                goto L45
            L5e:
                android.net.Uri r1 = r12.getData()     // Catch: java.lang.Exception -> La4
                if (r1 == 0) goto La4
                android.net.Uri r1 = r12.getData()     // Catch: java.lang.Exception -> La4
                java.lang.String r1 = r1.getPath()     // Catch: java.lang.Exception -> La4
                boolean r1 = io.dcloud.common.util.PdrUtil.isDeviceRootDir(r1)     // Catch: java.lang.Exception -> La4
                if (r1 == 0) goto L7b
                android.net.Uri r1 = r12.getData()     // Catch: java.lang.Exception -> La4
                java.lang.String r1 = r1.getPath()     // Catch: java.lang.Exception -> La4
                goto L89
            L7b:
                io.dcloud.common.DHInterface.IWebview r1 = r10.b     // Catch: java.lang.Exception -> La4
                android.app.Activity r1 = r1.getActivity()     // Catch: java.lang.Exception -> La4
                android.net.Uri r2 = r12.getData()     // Catch: java.lang.Exception -> La4
                java.lang.String r1 = io.dcloud.common.adapter.util.ContentUriUtil.getImageAbsolutePath(r1, r2)     // Catch: java.lang.Exception -> La4
            L89:
                if (r1 == 0) goto La4
                java.lang.String[] r3 = new java.lang.String[]{r1}     // Catch: java.lang.Exception -> La4
                goto La5
            L90:
                int r2 = io.dcloud.js.gallery.GalleryFeatureImpl.d()     // Catch: java.lang.Exception -> La4
                if (r1 != r2) goto La4
                java.lang.String r1 = "select_result"
                java.util.ArrayList r1 = r12.getParcelableArrayListExtra(r1)     // Catch: java.lang.Exception -> La4
                io.dcloud.js.gallery.GalleryFeatureImpl r2 = io.dcloud.js.gallery.GalleryFeatureImpl.this     // Catch: java.lang.Exception -> La4
                java.lang.String[] r1 = r2.mediasToJSONArray(r1)     // Catch: java.lang.Exception -> La4
            La2:
                r3 = r1
                goto La5
            La4:
                r3 = r11
            La5:
                io.dcloud.js.gallery.GalleryFeatureImpl r4 = io.dcloud.js.gallery.GalleryFeatureImpl.this
                io.dcloud.common.DHInterface.IApp r5 = r10.a
                io.dcloud.common.DHInterface.IWebview r6 = r10.b
                java.lang.String r7 = r10.c
                if (r12 == 0) goto Lb1
                r8 = r3
                goto Lb2
            Lb1:
                r8 = r11
            Lb2:
                r9 = 1
                io.dcloud.js.gallery.GalleryFeatureImpl.a(r4, r5, r6, r7, r8, r9)
            Lb6:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.gallery.GalleryFeatureImpl.c.onExecute(io.dcloud.common.DHInterface.ISysEventListener$SysEventType, java.lang.Object):boolean");
        }
    }

    class d extends BroadcastReceiver {
        final /* synthetic */ Activity a;

        d(Activity activity) {
            this.a = activity;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("_onMaxedId");
            if (!TextUtils.isEmpty(stringExtra)) {
                GalleryFeatureImpl.onMaxed(this.a, stringExtra);
                return;
            }
            if (GalleryFeatureImpl.this.b != null) {
                GalleryFeatureImpl.this.b.remove(this);
            }
            LocalBroadcastManager.getInstance(this.a).unregisterReceiver(this);
        }
    }

    class e implements ISysEventListener {
        final /* synthetic */ IApp a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;

        e(IApp iApp, IWebview iWebview, String str) {
            this.a = iApp;
            this.b = iWebview;
            this.c = str;
        }

        @Override // io.dcloud.common.DHInterface.ISysEventListener
        public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) throws JSONException {
            String[] strArrMediasToJSONArray;
            Uri data;
            Object[] objArr = (Object[]) obj;
            int iIntValue = ((Integer) objArr[0]).intValue();
            ((Integer) objArr[1]).intValue();
            Intent intent = (Intent) objArr[2];
            ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onActivityResult;
            if (sysEventType == sysEventType2) {
                this.a.unregisterSysEventListener(this, sysEventType2);
                if (iIntValue == GalleryFeatureImpl.e) {
                    String path = (intent == null || (data = intent.getData()) == null) ? null : PdrUtil.isDeviceRootDir(data.getPath()) ? data.getPath() : data.toString();
                    GalleryFeatureImpl.this.a(this.a, this.b, this.c, path != null ? new String[]{path} : null, false);
                } else if (iIntValue == GalleryFeatureImpl.h) {
                    String str = (intent == null || (strArrMediasToJSONArray = GalleryFeatureImpl.this.mediasToJSONArray(intent.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT))) == null || strArrMediasToJSONArray.length <= 0) ? null : strArrMediasToJSONArray[0];
                    GalleryFeatureImpl.this.a(this.a, this.b, this.c, str != null ? new String[]{str} : null, false);
                }
            }
            return false;
        }
    }

    public static void onMaxed(Context context, String str) {
        IWebview iWebview = i;
        if (iWebview != null) {
            JSUtil.execCallback(iWebview, str, "", JSUtil.OK, true);
            return;
        }
        Intent intent = new Intent("io.dcloud.streamapp.Gallery.onMax." + str);
        intent.putExtra("_onMaxedId", str);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public ArrayList<Media> JSONArrayToMedias(int i2, JSONArray jSONArray) {
        ArrayList<Media> arrayList = null;
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            try {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                File file = new File(URI.create(jSONArray.getString(i3)));
                if (file.exists()) {
                    arrayList.add(new Media(file.getPath(), file.getName(), 0L, i2 == 102 ? 3 : 1, file.length(), 0, ""));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        ArrayList arrayList = this.b;
        if (arrayList != null && i != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                LocalBroadcastManager.getInstance(i.getActivity()).unregisterReceiver((BroadcastReceiver) it.next());
            }
            this.b.clear();
        }
        i = null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        i = iWebview;
        AppRuntime.checkPrivacyComplianceAndPrompt(iWebview.getContext(), "Gallery-" + str);
        if (!"pick".equals(str)) {
            if (!"save".equals(str)) {
                return null;
            }
            IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
            PermissionUtil.usePermission(iAppObtainApp.getActivity(), "gallery", PermissionUtil.PMS_STORAGE, 2, new b(iAppObtainApp, strArr[0], iAppObtainApp, iWebview, strArr[1], strArr));
            return null;
        }
        this.c = false;
        a aVar = new a(strArr, iWebview, str);
        if (Build.VERSION.SDK_INT < 34) {
            PermissionUtil.usePermission(iWebview.obtainApp().getActivity(), "gallery", PermissionUtil.PMS_STORAGE_IMAGE, 2, aVar);
            return null;
        }
        if (ActivityCompat.checkSelfPermission(iWebview.getContext(), Permission.READ_MEDIA_VISUAL_USER_SELECTED) == -1) {
            PermissionUtil.usePermission(iWebview.obtainApp().getActivity(), "gallery", PermissionUtil.PMS_STORAGE_IMAGE, 2, aVar);
            return null;
        }
        aVar.onGranted(Permission.READ_MEDIA_IMAGES);
        return null;
    }

    public Intent getMediaPickerIntent(Context context, String str, int i2, JSONArray jSONArray, String str2, boolean z, String str3, JSONObject jSONObject, String str4, String str5, boolean z2) throws JSONException, NumberFormatException {
        Intent intent = new Intent();
        int i3 = (str.contains("video") && str.contains("image")) ? 101 : str.contains("video") ? 102 : 100;
        boolean zContains = str.contains("__Single__");
        intent.setClassName(context, "com.dmcbig.mediapicker.PickerActivity");
        intent.putExtra("select_mode", i3);
        intent.putExtra(PickerConfig.SINGLE_SELECT, zContains);
        intent.putExtra(PickerConfig.COMPRESSED, z2);
        if (i2 > 0) {
            intent.putExtra("max_select_count", i2);
        }
        if (jSONArray != null) {
            intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST, JSONArrayToMedias(i3, jSONArray));
        }
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra(PickerConfig.SELECTED_MAX_CALLBACK_ID, str3);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra(PickerConfig.DONE_BUTTON_TEXT, str2);
        }
        if (!TextUtils.isEmpty(str4)) {
            intent.putExtra(PickerConfig.SIZE_TYPE, str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            intent.putExtra("doc_path", str5);
        }
        if (jSONObject != null && jSONObject.has("width") && jSONObject.has("height")) {
            Pattern patternCompile = Pattern.compile("[^0-9]");
            try {
                int i4 = Integer.parseInt(patternCompile.matcher(jSONObject.optString("width")).replaceAll(""));
                int i5 = Integer.parseInt(patternCompile.matcher(jSONObject.optString("height")).replaceAll(""));
                if (i4 > 0 && i5 > 0) {
                    jSONObject.put("width", i4);
                    jSONObject.put("height", i5);
                    intent.putExtra(PickerConfig.IMAGE_CROP, jSONObject.toString());
                    intent.putExtra("max_select_count", 1);
                }
            } catch (Exception unused) {
            }
        }
        intent.putExtra(PickerConfig.IMAGE_EDITABLE, z);
        return intent;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.a = absMgr;
    }

    public String[] mediasToJSONArray(ArrayList<Parcelable> arrayList) {
        String[] strArr = null;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Media media = (Media) arrayList.get(i2);
            if (strArr == null) {
                strArr = new String[arrayList.size()];
            }
            strArr[i2] = media.path;
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(IWebview iWebview, String str, String[] strArr) {
        String str2;
        String str3;
        IApp iAppObtainApp;
        boolean zOptBoolean;
        String str4;
        JSONObject jSONObject;
        String str5;
        boolean z;
        String str6;
        try {
            str3 = strArr[0];
            try {
                iAppObtainApp = iWebview.obtainFrameView().obtainApp();
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            str2 = null;
        }
        try {
            iAppObtainApp.registerSysEventListener(new e(iAppObtainApp, iWebview, str3), ISysEventListener.SysEventType.onActivityResult);
            String str7 = "image/*";
            if (PdrUtil.isEmpty(strArr[1])) {
                zOptBoolean = false;
                str4 = null;
                jSONObject = null;
                str5 = null;
                z = true;
            } else {
                JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(strArr[1]);
                String string = JSONUtil.getString(jSONObjectCreateJSONObject, Constants.Name.FILTER);
                if ("video".equals(string)) {
                    str7 = "video/*";
                } else if ("none".equals(string)) {
                    str7 = "image/*|video/*";
                }
                String strOptString = jSONObjectCreateJSONObject.optString("confirmText");
                boolean zOptBoolean2 = jSONObjectCreateJSONObject.optBoolean("editable", true);
                JSONObject jSONObjectOptJSONObject = jSONObjectCreateJSONObject.optJSONObject("crop");
                String strOptString2 = jSONObjectCreateJSONObject.optString("sizeType");
                zOptBoolean = jSONObjectCreateJSONObject.optBoolean("videoCompress", false);
                str4 = strOptString;
                str5 = strOptString2;
                z = zOptBoolean2;
                jSONObject = jSONObjectOptJSONObject;
            }
            new Intent("android.intent.action.PICK");
            String strConcat = str7.concat("__Single__");
            if (strArr.length > 2) {
                String str8 = strArr[2];
                a(iWebview.getActivity(), str8);
                str6 = str8;
            } else {
                str6 = null;
            }
            iWebview.getActivity().startActivityForResult(getMediaPickerIntent(iWebview.getContext(), strConcat, 1, null, str4, z, str6, jSONObject, str5, iWebview.obtainApp().convert2LocalFullPath(null, BaseInfo.REL_PRIVATE_DOC_DIR), zOptBoolean), h);
        } catch (Exception e4) {
            e = e4;
            str2 = str3;
            e.printStackTrace();
            Deprecated_JSUtil.execCallback(iWebview, str2, DOMException.toJSON(12, e.getMessage()), JSUtil.ERROR, true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IWebview iWebview, String str, String[] strArr) {
        String str2;
        boolean zOptBoolean;
        boolean z;
        JSONArray jSONArray;
        String strOptString;
        JSONObject jSONObject;
        String str3;
        String str4;
        try {
            String str5 = strArr[0];
            try {
                IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
                try {
                    iAppObtainApp.registerSysEventListener(new c(iAppObtainApp, iWebview, str5), ISysEventListener.SysEventType.onActivityResult);
                    Intent intent = new Intent();
                    String str6 = "image/*";
                    int iOptInt = -1;
                    if (PdrUtil.isEmpty(strArr[1])) {
                        zOptBoolean = false;
                        z = true;
                        jSONArray = null;
                        strOptString = null;
                        jSONObject = null;
                        str3 = null;
                    } else {
                        JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(strArr[1]);
                        String string = JSONUtil.getString(jSONObjectCreateJSONObject, Constants.Name.FILTER);
                        if ("video".equals(string)) {
                            str6 = "video/*";
                        } else if ("none".equals(string)) {
                            str6 = "image/*|video/*";
                        }
                        iOptInt = jSONObjectCreateJSONObject.optInt("maximum", -1);
                        JSONArray jSONArrayOptJSONArray = jSONObjectCreateJSONObject.optJSONArray("selected");
                        strOptString = jSONObjectCreateJSONObject.optString("confirmText");
                        boolean zOptBoolean2 = jSONObjectCreateJSONObject.optBoolean("editable", true);
                        JSONObject jSONObjectOptJSONObject = jSONObjectCreateJSONObject.optJSONObject("crop");
                        String strOptString2 = jSONObjectCreateJSONObject.optString("sizeType");
                        zOptBoolean = jSONObjectCreateJSONObject.optBoolean("videoCompress", false);
                        jSONArray = jSONArrayOptJSONArray;
                        z = zOptBoolean2;
                        jSONObject = jSONObjectOptJSONObject;
                        str3 = strOptString2;
                    }
                    intent.setType(str6);
                    intent.setAction("android.intent.action.OPEN_DOCUMENT");
                    intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                    intent.addCategory("android.intent.category.OPENABLE");
                    int i2 = g;
                    try {
                        if (strArr.length > 2) {
                            try {
                                str4 = strArr[2];
                                a(iWebview.getActivity(), str4);
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                iWebview.getActivity().startActivityForResult(intent, i2);
                            }
                        } else {
                            str4 = null;
                        }
                        try {
                            intent = getMediaPickerIntent(iWebview.getContext(), str6, iOptInt, jSONArray, strOptString, z, str4, jSONObject, str3, iWebview.obtainApp().convert2LocalFullPath(null, BaseInfo.REL_PRIVATE_DOC_DIR), zOptBoolean);
                            i2 = h;
                        } catch (Exception e3) {
                            e = e3;
                            intent = intent;
                            e.printStackTrace();
                            iWebview.getActivity().startActivityForResult(intent, i2);
                        }
                    } catch (Exception e4) {
                        e = e4;
                    }
                    iWebview.getActivity().startActivityForResult(intent, i2);
                } catch (Exception e5) {
                    e = e5;
                    str2 = str5;
                    e.printStackTrace();
                    Deprecated_JSUtil.execCallback(iWebview, str2, DOMException.toJSON(12, e.getMessage()), JSUtil.ERROR, true, false);
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Exception e7) {
            e = e7;
            str2 = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(Activity activity, String str) {
        if ((activity instanceof IActivityHandler) && ((IActivityHandler) activity).isMultiProcessMode()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("io.dcloud.streamapp.Gallery.onMax." + str);
            d dVar = new d(activity);
            if (this.b == null) {
                this.b = new ArrayList();
            }
            this.b.add(dVar);
            LocalBroadcastManager.getInstance(activity).registerReceiver(dVar, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IApp iApp, IWebview iWebview, String str, String[] strArr, boolean z) throws JSONException {
        String str2;
        boolean z2;
        String strConvert2WebviewFullPath;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("multiple", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        JSONArray jSONArray = new JSONArray();
        if (strArr == null || strArr.length <= 0) {
            str2 = "User cancelled";
            z2 = false;
        } else {
            for (String str3 : strArr) {
                if (str3.startsWith("content://")) {
                    strConvert2WebviewFullPath = FileUtil.getPathFromUri(iApp.getActivity(), Uri.parse(str3));
                    if (!TextUtils.isEmpty(strConvert2WebviewFullPath) && !strConvert2WebviewFullPath.startsWith(DeviceInfo.FILE_PROTOCOL)) {
                        strConvert2WebviewFullPath = DeviceInfo.FILE_PROTOCOL + strConvert2WebviewFullPath;
                    }
                } else {
                    strConvert2WebviewFullPath = iApp.convert2WebviewFullPath(null, str3);
                }
                jSONArray.put(strConvert2WebviewFullPath);
            }
            try {
                jSONObject.put("files", jSONArray);
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            z2 = true;
            str2 = "pickImage path wrong";
        }
        if (!z2) {
            Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(12, str2), JSUtil.ERROR, true, false);
        } else {
            JSUtil.execCallback(iWebview, str, jSONObject, JSUtil.OK, false);
        }
    }
}
