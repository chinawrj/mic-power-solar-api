package io.dcloud.p;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.constant.DataInterface;
import io.dcloud.common.constant.StringConst;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.DataUtil;
import io.dcloud.common.util.ErrorDialogUtil;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.internal.sdk.SDK;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class r extends AbsMgr implements IMgr.AppEvent {
    private static String j;
    c4 a;
    ArrayList b;
    ArrayList c;
    a4 d;
    Class[] e;
    private AlertDialog f;
    JSONObject g;
    private AlertDialog h;
    private AlertDialog i;

    class a implements ICallBack {
        a() {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            String unused = r.j = String.valueOf(obj);
            return null;
        }
    }

    class c implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity a;
        final /* synthetic */ String b;
        final /* synthetic */ a5 c;
        final /* synthetic */ CheckBox d;
        final /* synthetic */ String e;
        final /* synthetic */ a5 f;
        final /* synthetic */ a5 g;
        final /* synthetic */ boolean h;

        c(Activity activity, String str, a5 a5Var, CheckBox checkBox, String str2, a5 a5Var2, a5 a5Var3, boolean z) {
            this.a = activity;
            this.b = str;
            this.c = a5Var;
            this.d = checkBox;
            this.e = str2;
            this.f = a5Var2;
            this.g = a5Var3;
            this.h = z;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i != -2) {
                if (i != -3 && i == -1) {
                    if (this.d.isChecked()) {
                        SP.setBundleData(this.a, "pdr", AbsoluteConst.TEST_RUN + this.b, "__am=t");
                    }
                    r.this.a(this.a, this.b, this.e, this.c, this.f, this.g, this.h);
                    r.this.f.dismiss();
                    return;
                }
                return;
            }
            r.this.f.dismiss();
            IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(this.a);
            if (iActivityHandler != null) {
                iActivityHandler.closeAppStreamSplash(this.b);
                BaseInfo.setLoadingLaunchePage(false, "closeSplashScreen0");
                if (r.this.d.e() == 0) {
                    this.a.finish();
                    return;
                }
                a5 a5Var = this.c;
                if (a5Var != null) {
                    a5Var.w();
                }
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                this.a.startActivity(intent);
            }
        }
    }

    class d implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity a;

        d(Activity activity) {
            this.a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://ask.dcloud.net.cn/article/35627"));
            this.a.startActivity(intent);
        }
    }

    class e implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity a;

        e(Activity activity) {
            this.a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://ask.dcloud.net.cn/article/35877"));
            this.a.startActivity(intent);
        }
    }

    class f implements ICallBack {
        final /* synthetic */ a5 a;

        f(a5 a5Var) {
            this.a = a5Var;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (AppRuntime.hasPrivacyForNotShown(this.a.getActivity())) {
                return null;
            }
            ((AbsMgr) r.this).mCore.onRestart(this.a.getActivity());
            return null;
        }
    }

    class g implements ICallBack {
        final /* synthetic */ a5 a;
        final /* synthetic */ boolean b;
        final /* synthetic */ String c;
        final /* synthetic */ ICallBack d;

        g(a5 a5Var, boolean z, String str, ICallBack iCallBack) {
            this.a = a5Var;
            this.b = z;
            this.c = str;
            this.d = iCallBack;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (this.a.a(this.b)) {
                this.d.onCallBack(0, null);
            } else {
                Logger.e(Logger.AppMgr_TAG, "reboot " + this.c + " app failed !!!");
            }
            return null;
        }
    }

    class h implements ICallBack {
        final /* synthetic */ a5 a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ boolean d;

        class a implements ICallBack {
            a() {
            }

            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                h hVar = h.this;
                r.this.a(hVar.a, hVar.b, hVar.c, hVar.d);
                return null;
            }
        }

        h(a5 a5Var, String str, String str2, boolean z) {
            this.a = a5Var;
            this.b = str;
            this.c = str2;
            this.d = z;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (!WebViewFactory.isOther() || WebViewFactory.isOtherInitialised() || WebViewFactory.isIsLoadOtherTimeOut()) {
                r.this.a(this.a, this.b, this.c, this.d);
                return null;
            }
            WebViewFactory.setOtherCallBack(new a());
            return null;
        }
    }

    class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DHFile.deleteFile(StringConst.STREAMAPP_KEY_ROOTPATH + "splash_temp/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public r(ICore iCore) throws IOException {
        super(iCore, Logger.AppMgr_TAG, IMgr.MgrType.AppMgr);
        this.a = null;
        this.b = new ArrayList(1);
        this.c = new ArrayList(1);
        this.d = null;
        this.e = new Class[0];
        this.g = null;
        if (iCore != null) {
            a(iCore.obtainContext());
        }
        c();
        b();
        d();
        a();
        this.d = new a4(this);
    }

    private void d(a5 a5Var) {
        Object objNewInstance = PlatformUtil.newInstance("android.app.ActivityManager$TaskDescription", new Class[]{String.class, Bitmap.class}, new Object[]{a5Var.obtainAppName(), BitmapFactory.decodeResource(getContext().getResources(), getContext().getApplicationInfo().icon)});
        PlatformUtil.invokeMethod(a5Var.getActivity(), "setTaskDescription", new Class[]{objNewInstance.getClass()}, objNewInstance);
    }

    a5 c(String str) {
        return a((Activity) null, str);
    }

    @Override // io.dcloud.common.DHInterface.AbsMgr
    public void dispose() {
        ArrayList arrayList = this.c;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((a5) it.next()).g();
            }
        }
        this.c.clear();
        this.b.clear();
        a4 a4Var = this.d;
        if (a4Var != null) {
            a4Var.a();
        }
        this.d = null;
        ThreadPool.self().addThreadTask(new i());
    }

    void e(a5 a5Var) {
        this.d.b(a5Var.o);
        b(a5Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:172:0x0441  */
    @Override // io.dcloud.common.DHInterface.IMgr
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object processEvent(io.dcloud.common.DHInterface.IMgr.MgrType r23, int r24, java.lang.Object r25) {
        /*
            Method dump skipped, instructions count: 1710
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.r.processEvent(io.dcloud.common.DHInterface.IMgr$MgrType, int, java.lang.Object):java.lang.Object");
    }

    class b implements Runnable {
        final /* synthetic */ Context a;

        b(Context context) {
            this.a = context;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0013  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r4 = this;
                boolean r0 = io.dcloud.common.util.BaseInfo.SyncDebug
                if (r0 == 0) goto L13
                java.lang.String r0 = "uni-jsframework-dev.js"
                java.io.InputStream r1 = io.dcloud.common.adapter.util.PlatformUtil.getResInputStream(r0)
                if (r1 == 0) goto L13
                boolean r1 = io.dcloud.feature.internal.sdk.SDK.isUniMPSDK()
                if (r1 != 0) goto L13
                goto L15
            L13:
                java.lang.String r0 = "uni-jsframework.js"
            L15:
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L42
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L42
                android.content.Context r3 = r4.a     // Catch: java.lang.Exception -> L42
                android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Exception -> L42
                java.io.InputStream r0 = r3.open(r0)     // Catch: java.lang.Exception -> L42
                r2.<init>(r0)     // Catch: java.lang.Exception -> L42
                r1.<init>(r2)     // Catch: java.lang.Exception -> L42
                java.lang.String r0 = r1.readLine()     // Catch: java.lang.Exception -> L42
                org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L42
                r2 = 2
                java.lang.String r0 = r0.substring(r2)     // Catch: java.lang.Exception -> L42
                r1.<init>(r0)     // Catch: java.lang.Exception -> L42
                java.lang.String r0 = "version"
                java.lang.String r0 = r1.optString(r0)     // Catch: java.lang.Exception -> L42
                android.content.Context r1 = r4.a     // Catch: java.lang.Exception -> L42
                io.dcloud.common.util.BaseInfo.setUniVersionV3(r0, r1)     // Catch: java.lang.Exception -> L42
            L42:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.r.b.run():void");
        }
    }

    void b() {
        c5 c5Var;
        HashMap<String, BaseInfo.BaseAppInfo> map = BaseInfo.mBaseAppInfoSet;
        if (map == null || map.isEmpty()) {
            return;
        }
        Set<String> setKeySet = BaseInfo.mBaseAppInfoSet.keySet();
        int size = setKeySet.size();
        String[] strArr = new String[size];
        setKeySet.toArray(strArr);
        for (int i2 = 0; i2 < size; i2++) {
            String str = strArr[i2];
            BaseInfo.BaseAppInfo baseAppInfo = BaseInfo.mBaseAppInfoSet.get(str);
            if (!BaseInfo.mUnInstalledAppInfoSet.containsKey(str) && !b(str)) {
                a5 a5VarB = b(BaseInfo.sBaseResAppsPath + str, str);
                if (a5VarB != null && (c5Var = a5VarB.r) != null) {
                    if (c5Var.a) {
                        Logger.e("AppMgr", str + "  app error," + a5VarB.r);
                    } else {
                        a5VarB.s = baseAppInfo;
                        c(a5VarB);
                    }
                }
            }
        }
    }

    void c(a5 a5Var) {
        this.b.add(a5Var.obtainAppId());
        this.c.add(a5Var);
    }

    private void a() {
        if (PdrUtil.isEmpty(j)) {
            DataUtil.datToJsString(BaseInfo.sUniNViewServiceJsPath, new a());
        }
    }

    void c() {
        c5 c5Var;
        HashMap<String, BaseInfo.BaseAppInfo> map = BaseInfo.mInstalledAppInfoSet;
        if (map == null || map.isEmpty()) {
            return;
        }
        Set<String> setKeySet = BaseInfo.mInstalledAppInfoSet.keySet();
        int size = setKeySet.size();
        String[] strArr = new String[size];
        setKeySet.toArray(strArr);
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            String str = strArr[i2];
            if (!BaseInfo.mUnInstalledAppInfoSet.containsKey(str) && !b(str)) {
                a5 a5VarB = b(BaseInfo.sCacheFsAppsPath + str, str);
                if (a5VarB != null && (c5Var = a5VarB.r) != null && !c5Var.a) {
                    a5VarB.deleteAppTemp();
                    if (SDK.isUniMPSDK()) {
                        a5VarB.i0 = true;
                    } else {
                        a5VarB.i0 = false;
                    }
                    c(a5VarB);
                } else {
                    BaseInfo.mInstalledAppInfoSet.get(str).clearBundleData();
                    BaseInfo.mInstalledAppInfoSet.remove(str);
                    z = true;
                }
            }
        }
        if (z) {
            BaseInfo.saveInstalledAppInfo(getContext());
        }
    }

    private void a(Context context) {
        if (TextUtils.isEmpty(BaseInfo.uniVersionV3)) {
            ThreadPool.self().addThreadTask(new b(context), true);
        }
    }

    void d() throws IOException {
        File file = new File(BaseInfo.sURDFilePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            DHFile.copyAssetsFile("data/dcloud_url.json", file.getAbsolutePath());
        }
        if (file.exists()) {
            try {
                this.g = new JSONObject(new String(DHFile.readAll(file)));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void a(a5 a5Var, String str, boolean z) {
        if (a5Var != null) {
            String strObtainConfigProperty = a5Var.obtainConfigProperty(IApp.ConfigProperty.CONFIG_UNIAPP_CONTROL);
            f fVar = new f(a5Var);
            if (!TextUtils.isEmpty(strObtainConfigProperty) && strObtainConfigProperty.equals(AbsoluteConst.UNI_V3)) {
                if (a5Var.getActivity() != null) {
                    BaseInfo.isFirstRun = false;
                    a5Var.showSplash();
                    AppRuntime.restartWeex(a5Var.getActivity().getApplication(), new g(a5Var, z, str, fVar), a5Var.o);
                    return;
                }
                return;
            }
            if (!a5Var.a(z)) {
                Logger.e(Logger.AppMgr_TAG, "reboot " + str + " app failed !!!");
                return;
            }
            fVar.onCallBack(0, null);
            return;
        }
        Logger.e(Logger.AppMgr_TAG, "not found " + str + " app!!!");
    }

    void b(a5 a5Var) {
        this.b.remove(a5Var.o);
        this.c.remove(a5Var);
    }

    private boolean b(String str) {
        return this.b.contains(str);
    }

    private a5 b(String str, String str2) {
        return a(str, str2);
    }

    private void a(a5 a5Var) {
        if (SDK.isUniMPSDK() && SDK.isEnableBackground) {
            d(a5Var);
        }
    }

    public void a(Activity activity, String str, String str2, a5 a5Var, a5 a5Var2, a5 a5Var3, boolean z) {
        Log.i("ylyl", "startOneApp " + str);
        BaseInfo.sLastRunApp = str;
        BaseInfo.CmtInfo cmitInfo = BaseInfo.getCmitInfo(str);
        if (cmitInfo.needUpdate) {
            cmitInfo.templateVersion = a5Var2.C;
            cmitInfo.rptCrs = a5Var2.L;
            cmitInfo.rptJse = a5Var2.M;
            cmitInfo.plusLauncher = BaseInfo.getLaunchType(a5Var2.obtainWebAppIntent());
            cmitInfo.sfd = DataInterface.getStreamappFrom(a5Var2.obtainWebAppIntent());
            cmitInfo.needUpdate = false;
        }
        if (!b4.c()) {
            if (a5Var2.t == 4) {
                ErrorDialogUtil.checkAppKeyErrorTips(activity);
                return;
            }
        } else if (!PdrUtil.checkIntl()) {
            q.a(activity, a5Var2);
            if (a5Var2.t == 4) {
                return;
            }
        }
        if (a5Var2.t == 3) {
            a5Var2.t = a5Var2.p() ? a5Var2.t : (byte) 2;
        }
        if (a5Var != null && a5Var != a5Var2 && a5Var != a5Var3) {
            a5Var.w();
        }
        byte b2 = a5Var2.t;
        if (b2 == 1 || ((z && !a5Var2.v) || ((a5Var2.w && a5Var2.u) || !z))) {
            Logger.d(Logger.AppMgr_TAG, str + " will unrunning change to active");
            a5Var2.a(activity);
            processEvent(IMgr.MgrType.WindowMgr, 4, new Object[]{a5Var2, str});
            a5Var2.a(new h(a5Var2, str, str2, z));
        } else if (b2 == 2) {
            Logger.d(Logger.AppMgr_TAG, str + " will unactive change to active");
            a5Var2.c();
        } else {
            Logger.d(Logger.AppMgr_TAG, str + " is active");
        }
        if (SDK.isUniMPSDK()) {
            a(a5Var2);
        }
        if (a5Var3 == null || a5Var3 == a5Var2) {
            return;
        }
        a5Var3.u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a5 a5Var, String str, String str2, boolean z) {
        if (this.d == null) {
            return;
        }
        boolean zF = z ? a5Var.f(str2) : a5Var.g(str2);
        if (!a5Var.v && a5Var.u) {
            a5Var.f(str2);
        }
        if (zF) {
            this.d.a(str, a5Var);
            return;
        }
        Logger.e(Logger.AppMgr_TAG, str + " run failed!!!");
    }

    a5 a(Activity activity, String str) {
        return a(activity, str, true);
    }

    private a5 a(String str, boolean z) {
        return a((Activity) null, str, z);
    }

    private a5 a(Activity activity, String str, boolean z) {
        int iIndexOf;
        a5 a5Var = (!this.b.contains(str) || (iIndexOf = this.b.indexOf(str)) < 0) ? null : (a5) this.c.get(iIndexOf);
        if (a5Var == null && z) {
            a5Var = new a5(this, str, (byte) 0);
            a5Var.setAppDataPath(BaseInfo.sCacheFsAppsPath + str + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
            if (a5Var.a == null) {
                a5Var.a = activity;
            }
            if (activity != null) {
                a5Var.setWebAppIntent(activity.getIntent());
            }
            a5Var.b(str, null);
            if (a5Var.r.a) {
                a5Var.o = str;
            }
            c(a5Var);
        } else if (a5Var != null && activity != null) {
            if (a5Var.a == null) {
                a5Var.a = activity;
            }
            if (a5Var.a.getIntent() != null) {
                if (a5Var.manifestBeParsed()) {
                    a5Var.setWebAppIntent(a5Var.a.getIntent());
                }
            } else {
                a5Var.a.setIntent(a5Var.obtainWebAppIntent());
            }
            if (!a5Var.u) {
                a5Var.b(str, null);
            }
        }
        return a5Var;
    }

    a5 a(String str, String str2, String str3, byte b2) {
        a5 a5VarA = a(str, false);
        if (a5VarA == null) {
            a5VarA = new a5(this, str, b2);
            a5VarA.t = (byte) 3;
            a5VarA.o = str;
            if (!PdrUtil.isEmpty(str2)) {
                a5VarA.setAppDataPath(str2);
            }
            a5VarA.k0 = str3;
            c(a5VarA);
            this.d.a(str, a5VarA);
        }
        return a5VarA;
    }

    a5 a(String str, String str2) {
        return a(str, str2, (JSONObject) null);
    }

    a5 a(String str, String str2, JSONObject jSONObject) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Exception e2;
        a5 a5Var;
        PackageInfo apkInfo;
        Class<?> cls;
        a5 a5VarA = a(str2, false);
        if (a5VarA != null) {
            try {
                a5VarA.r.a();
            } catch (Exception e3) {
                e2 = e3;
                a5Var = a5VarA;
                e2.printStackTrace();
                Logger.e(Logger.AppMgr_TAG, "installWebApp " + str + " is Illegal path");
                return a5Var;
            }
        }
        if (!DHFile.isExist(str) && !PdrUtil.isDeviceRootDir(str)) {
            boolean zContains = str.substring(str.lastIndexOf(47)).contains(".wgt");
            resInputStream = zContains ? PlatformUtil.getResInputStream(str) : null;
            if (a5VarA == null) {
                a5VarA = new a5(this, str2, (byte) 1);
            }
            if (!zContains && resInputStream == null) {
                a5VarA.setAppDataPath(str + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
                a5VarA.b(str2, jSONObject);
            } else {
                a5VarA.b(resInputStream);
            }
        } else {
            boolean zIsFile = new File(str).isFile();
            if (zIsFile) {
                if (zIsFile && str.toLowerCase(Locale.ENGLISH).endsWith(".wgtu")) {
                    if (a5VarA == null) {
                        a5VarA = new a5(this, str2, (byte) 0);
                    }
                    a5VarA.a(str, jSONObject);
                    c5 c5Var = a5VarA.r;
                    c5Var.c = false;
                    c5Var.d = false;
                } else if (zIsFile && str.toLowerCase(Locale.ENGLISH).endsWith(".wgt")) {
                    boolean z = a5VarA == null;
                    a5VarA.r.d = true;
                    if (z) {
                        a5Var = new a5(this, str2, (byte) 0);
                        try {
                            a5Var.o = str2;
                            a5Var.setAppDataPath(BaseInfo.sCacheFsAppsPath + str2 + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
                            a5VarA = a5Var;
                        } catch (Exception e4) {
                            e2 = e4;
                            e2.printStackTrace();
                            Logger.e(Logger.AppMgr_TAG, "installWebApp " + str + " is Illegal path");
                            return a5Var;
                        }
                    }
                    boolean zC = a5VarA.c(str, jSONObject);
                    a5VarA.r.d = false;
                    if (zC && z) {
                        c(a5VarA);
                    }
                } else if (zIsFile && str.toLowerCase(Locale.ENGLISH).endsWith(StringConst.POINT_APP_EN)) {
                    try {
                        apkInfo = PlatformUtil.parseApkInfo(getContext(), str);
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        a5VarA.r.b = StringUtil.format(DOMException.JSON_ERROR_INFO, 10, e5.getMessage());
                        apkInfo = null;
                    }
                    try {
                        cls = Class.forName("io.dcloud.feature.pack.FileUtils");
                    } catch (Exception unused) {
                        cls = null;
                    }
                    if (apkInfo != null && cls != null) {
                        a5VarA.r.a = false;
                        String str3 = apkInfo.versionName;
                        String str4 = apkInfo.packageName;
                        String string = getContext().getPackageManager().getApplicationLabel(apkInfo.applicationInfo).toString();
                        if (string == null) {
                            string = "";
                        }
                        a5VarA.r.b = StringUtil.format("{pname:'%s',version:'%s',name:'%s'}", str4, str3, string);
                        try {
                            cls.getDeclaredMethod("addFileToSystem", Context.class, String.class, String.class).invoke(null, getContext(), getContext().getPackageName() + ".dc.fileprovider", str);
                        } catch (Exception unused2) {
                        }
                    } else {
                        a5VarA.r.a = true;
                    }
                } else {
                    c5 c5Var2 = a5VarA.r;
                    c5Var2.a = true;
                    c5Var2.b = StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(DOMException.CODE_RUNTIME_WGT_OR_WGTU_ERROR_MALFORMED), DOMException.MSG_RUNTIME_WGT_OR_WGTU_ERROR_MALFORMED);
                }
            } else {
                if (a5VarA != null) {
                    a5VarA.b((byte) 0);
                } else {
                    a5VarA = new a5(this, str2, (byte) 0);
                }
                a5VarA.setAppDataPath(str + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
                a5VarA.b(str2, jSONObject);
            }
        }
        IOUtil.close(resInputStream);
        return a5VarA;
    }
}
