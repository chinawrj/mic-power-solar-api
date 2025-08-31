package io.dcloud.p;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.taobao.weex.common.WXConfig;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.WebAppActivity;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IBoot;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IConfusionMgr;
import io.dcloud.common.DHInterface.IDCloudWebviewClientListener;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.IPdrModule;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebviewStateListener;
import io.dcloud.common.DHInterface.ReceiveSystemEventVoucher;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.io.UnicodeInputStream;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.core.permission.PermissionControler;
import io.dcloud.common.ui.PrivacyManager;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.AppStatus;
import io.dcloud.common.util.AppStatusBarManager;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.CreateShortResultReceiver;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.NetworkTypeUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.common.util.ZipUtils;
import io.dcloud.feature.internal.sdk.SDK;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class a5 extends b5 implements IApp, ISysEventListener {
    public static String G1 = "webapp";
    ArrayList A0;
    private String Q0;
    private String e0;
    private String f0;
    private String g0;
    private String h0;
    c5 r;
    private IConfusionMgr s1;
    private String t1;
    r x;
    BaseInfo.BaseAppInfo s = null;
    byte t = 1;
    boolean u = false;
    boolean v = false;
    boolean w = false;
    String y = null;
    String z = "";
    String A = "";
    String B = "";
    String C = null;
    String D = "";
    String E = null;
    String F = null;
    String G = null;
    String H = null;
    String I = null;
    String J = null;
    String K = null;
    boolean L = true;
    boolean M = true;
    boolean N = true;
    boolean O = false;
    boolean P = false;
    boolean Q = true;
    boolean R = false;
    private String S = null;
    boolean T = false;
    private byte U = 1;
    private boolean V = false;
    private boolean W = false;
    private boolean X = true;
    private boolean Y = true;
    private int Z = 10000;
    private int a0 = 0;
    private int b0 = 0;
    private String c0 = null;
    private String d0 = null;
    boolean i0 = false;
    private String j0 = null;
    String k0 = null;
    String l0 = null;
    String m0 = null;
    boolean n0 = false;
    String o0 = "accept";
    String p0 = "file:///android_asset/data/dcloud_error.html";
    String q0 = null;
    private String r0 = null;
    String s0 = null;
    private String t0 = "-1";
    private JSONObject u0 = null;
    private String v0 = "";
    private boolean w0 = true;
    private boolean x0 = false;
    private String y0 = AbsoluteConst.UNI_V3;
    private String z0 = "fast";
    HashMap B0 = null;
    JSONObject C0 = null;
    JSONObject D0 = null;
    JSONObject E0 = null;
    JSONObject F0 = null;
    JSONObject G0 = null;
    JSONObject H0 = null;
    JSONObject I0 = null;
    JSONObject J0 = null;
    JSONObject K0 = null;
    String L0 = null;
    String M0 = null;
    Intent N0 = null;
    IApp.IAppStatusListener O0 = null;
    String P0 = null;
    private String R0 = "none";
    boolean S0 = false;
    private boolean T0 = false;
    private boolean U0 = false;
    private String V0 = "default";
    private String W0 = null;
    private String X0 = null;
    private String Y0 = null;
    private String Z0 = "";
    protected boolean a1 = false;
    private boolean b1 = false;
    private boolean c1 = false;
    private String d1 = null;
    private String e1 = null;
    private boolean f1 = false;
    long g1 = 0;
    boolean h1 = true;
    boolean i1 = false;
    boolean j1 = false;
    ArrayList k1 = new ArrayList();
    ArrayList l1 = new ArrayList();
    String m1 = null;
    String n1 = null;
    private String o1 = "";
    private boolean p1 = false;
    private String q1 = null;
    private int r1 = 1;
    IWebviewStateListener u1 = null;
    boolean v1 = false;
    JSONObject w1 = null;
    private boolean x1 = false;
    private String y1 = "none";
    private String z1 = AbsoluteConst.INSTALL_OPTIONS_FORCE;
    private String A1 = null;
    private String B1 = null;
    boolean C1 = true;
    HashMap D1 = null;
    String E1 = null;
    boolean F1 = false;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
                DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class c implements Runnable {
        final /* synthetic */ ICallBack a;

        class a implements MessageHandler.IMessages {
            a() {
            }

            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                c.this.a.onCallBack(0, null);
            }
        }

        c(ICallBack iCallBack) {
            this.a = iCallBack;
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            String str = BaseInfo.sCacheFsAppsPath + a5.this.o + DeviceInfo.sSeparatorChar + BaseInfo.APP_WWW_FS_DIR;
            long jCurrentTimeMillis = System.currentTimeMillis();
            Logger.d(a5.G1, a5.this.o + " copy resoure begin!!!");
            DHFile.delete(str);
            DHFile.copyDir(a5.this.j0, str);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            Logger.d(a5.G1, a5.this.o + " copy resoure end!!! useTime=" + (jCurrentTimeMillis2 - jCurrentTimeMillis));
            a5.this.U = (byte) 0;
            a5.this.setAppDataPath(str);
            a5 a5Var = a5.this;
            BaseInfo.BaseAppInfo baseAppInfo = a5Var.s;
            if (baseAppInfo != null) {
                baseAppInfo.saveToBundleData(a5Var.getActivity());
            }
            MessageHandler.sendMessage(new a(), null);
        }
    }

    class d implements Runnable {
        final /* synthetic */ String a;

        d(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TestUtil.PointTime.commitTid(a5.this.getActivity(), this.a, null, a5.this.M0, 1);
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DHFile.deleteFile(a5.this.obtainAppTempPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ class f {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[IApp.ConfigProperty.ThridInfo.values().length];
            a = iArr;
            try {
                iArr[IApp.ConfigProperty.ThridInfo.OverrideUrlJsonData.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[IApp.ConfigProperty.ThridInfo.OverrideResourceJsonData.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[IApp.ConfigProperty.ThridInfo.SecondWebviewJsonData.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[IApp.ConfigProperty.ThridInfo.LaunchWebviewJsonData.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[IApp.ConfigProperty.ThridInfo.TitleNViewJsonData.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[IApp.ConfigProperty.ThridInfo.SitemapJsonData.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[IApp.ConfigProperty.ThridInfo.URDJsonData.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[IApp.ConfigProperty.ThridInfo.DirectPageJsonData.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[IApp.ConfigProperty.ThridInfo.Tabbar.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    a5(r rVar, String str, byte b2) {
        this.r = null;
        this.x = null;
        this.A0 = null;
        this.x = rVar;
        this.o = str;
        b(b2);
        this.s1 = i0.c();
        this.r = new c5();
        this.A0 = new ArrayList(2);
        this.q = AppRuntime.isUniApp(str);
    }

    private void d() {
    }

    private void e() throws JSONException {
        JSONObject jSONObject = this.J0;
        if (jSONObject != null) {
            try {
                String strOptString = jSONObject.optString("webviewid");
                if (TextUtils.isEmpty(strOptString)) {
                    this.J0.put("webviewid", IntentConst.DIRECT_PAGE);
                }
                if (this.o.equals(strOptString)) {
                    this.I0 = this.J0.optJSONObject("titleNView");
                    return;
                }
                JSONObject jSONObjectOptJSONObject = this.J0.has("titleNView") ? this.J0.optJSONObject("titleNView") : null;
                if (jSONObjectOptJSONObject == null) {
                    jSONObjectOptJSONObject = new JSONObject();
                    this.J0.put("titleNView", jSONObjectOptJSONObject);
                }
                jSONObjectOptJSONObject.put("autoBackButton", true);
                if (jSONObjectOptJSONObject.has("homeButton")) {
                    return;
                }
                jSONObjectOptJSONObject.put("homeButton", true);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean e(String str) {
        return false;
    }

    private void f() {
        try {
            int iRename = DHFile.rename(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/", BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
            DHFile.copyDir("data/wap2app", BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/");
            if (iRename == 1) {
                ThreadPool.self().addThreadTask(new a());
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private JSONObject h() {
        JSONObject jSONObjectA = null;
        try {
            InputStream inputStreamObtainResInStream = obtainResInStream("_www/__template.json");
            if (inputStreamObtainResInStream == null) {
                return null;
            }
            jSONObjectA = a(inputStreamObtainResInStream);
            IOUtil.close(inputStreamObtainResInStream);
            return jSONObjectA;
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObjectA;
        }
    }

    private JSONObject i() {
        ThreadPool threadPoolSelf;
        b bVar;
        JSONObject jSONObject = null;
        try {
            boolean z = true;
            boolean z2 = false;
            if (BaseInfo.sCoverApkRuning) {
                if (new File(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/__template.json").exists()) {
                    InputStream inputStream = PlatformUtil.getInputStream(BaseInfo.sBaseConfigTemplatePath);
                    JSONObject jSONObjectA = a(inputStream);
                    String strOptString = jSONObjectA.optString("version");
                    IOUtil.close(inputStream);
                    InputStream inputStream2 = DHFile.getInputStream(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/__template.json");
                    JSONObject jSONObjectA2 = a(inputStream2);
                    String strOptString2 = jSONObjectA2.optString("version");
                    IOUtil.close(inputStream2);
                    BaseInfo.mWap2appTemplateFiles.clear();
                    BaseInfo.mW2AE.clear();
                    if (BaseInfo.BaseAppInfo.compareVersion(strOptString, strOptString2)) {
                        f();
                        z2 = true;
                        jSONObject = jSONObjectA;
                    } else {
                        jSONObject = jSONObjectA2;
                    }
                }
            }
            if (DHFile.isExist(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip")) {
                DHFile.rename(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/", BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
                try {
                    try {
                        ZipUtils.upZipFile(new File(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip"), BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/");
                        threadPoolSelf = ThreadPool.self();
                        bVar = new b();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        threadPoolSelf = ThreadPool.self();
                        bVar = new b();
                        z = z2;
                    }
                    threadPoolSelf.addThreadTask(bVar);
                } catch (Throwable th) {
                    ThreadPool.self().addThreadTask(new b());
                    throw th;
                }
            } else {
                if (new File(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/__template.json").exists()) {
                    z = z2;
                } else {
                    f();
                }
            }
            if (!z && !TextUtils.isEmpty(BaseInfo.sWap2AppTemplateVersion) && BaseInfo.mWap2appTemplateFiles.size() != 0 && this.s1.getData("__w2a__template__") != null) {
                return jSONObject;
            }
            if (!DHFile.isExist(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/__template.json")) {
                return jSONObject;
            }
            InputStream inputStream3 = DHFile.getInputStream(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/__template.json");
            JSONObject jSONObjectA3 = a(inputStream3);
            IOUtil.close(inputStream3);
            BaseInfo.mWap2appTemplateFiles.clear();
            BaseInfo.mW2AE.clear();
            this.s1.removeData("__w2a__template__");
            return jSONObjectA3;
        } catch (Exception e3) {
            e3.printStackTrace();
            return jSONObject;
        }
    }

    private void l() {
        String[] strArrSplit;
        this.D1 = new HashMap();
        String string = SP.getOrCreateBundle(getActivity(), this.o + "_1").getString("Authorize", null);
        this.E1 = string;
        if (string == null || (strArrSplit = string.split("&")) == null || strArrSplit.length <= 0) {
            return;
        }
        for (String str : strArrSplit) {
            if (!TextUtils.isEmpty(str)) {
                String[] strArrSplit2 = str.split("=");
                this.D1.put(strArrSplit2[0], Integer.valueOf(Integer.parseInt(strArrSplit2[1])));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void x() {
        /*
            Method dump skipped, instructions count: 499
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.a5.x():void");
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void addAllFeaturePermission() {
        PermissionControler.registerRootPermission(this.o);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void addFeaturePermission(String str) {
        this.A0.add(str.toLowerCase(Locale.ENGLISH));
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void applyMani() throws JSONException, NoSuchAlgorithmException {
        try {
            a(DHFile.getInputStream(DHFile.createFileHandler(a(BaseInfo.sConfigXML))), this.o, null);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void applySmartUpdate() {
        a(false);
    }

    void b(InputStream inputStream) {
    }

    boolean b(String str, JSONObject jSONObject) {
        boolean z;
        HashMap<String, BaseInfo.BaseAppInfo> map;
        InputStream inputStream = null;
        boolean zA = false;
        try {
            try {
                this.o = str;
                this.s1.removeData(str);
                k();
                if (this.U == 0 || !((map = BaseInfo.mBaseAppInfoSet) == null || map.containsKey(this.o))) {
                    inputStream = DHFile.getInputStream(DHFile.createFileHandler(a(BaseInfo.sConfigXML)));
                    if (inputStream == null && (inputStream = PlatformUtil.getResInputStream(a(BaseInfo.sConfigXML))) != null) {
                        this.U = (byte) 1;
                    }
                } else if (this.U == 1) {
                    inputStream = PlatformUtil.getResInputStream(a(BaseInfo.sConfigXML));
                }
                if (BaseInfo.isWap2AppAppid(this.o)) {
                    x();
                    z = false;
                } else {
                    z = true;
                }
            } catch (Exception e2) {
                Logger.w("parseConfig", e2);
            }
            if (inputStream == null) {
                if (BaseInfo.isWap2AppAppid(this.o) && !TextUtils.isEmpty(this.m1)) {
                    return true;
                }
                if (q() && !TextUtils.isEmpty(this.q0)) {
                    return true;
                }
                c5 c5Var = this.r;
                c5Var.a = true;
                if (c5Var.c) {
                    c5Var.b = DOMException.toJSON(DOMException.CODE_RUNTIME_WGTU_WWW_MANIFEST_NOT_EXIST, DOMException.MSG_RUNTIME_WGTU_WWW_MANIFEST_NOT_EXIST);
                } else {
                    c5Var.b = DOMException.toJSON(DOMException.CODE_RUNTIME_WGT_MANIFEST_NOT_EXIST, DOMException.MSG_RUNTIME_WGT_MANIFEST_NOT_EXIST);
                }
                return false;
            }
            zA = a(inputStream, str, jSONObject);
            if (z) {
                x();
            }
            c5 c5Var2 = this.r;
            if (c5Var2 != null && c5Var2.a) {
                Logger.i("WebApp", "InstallError---msg=" + this.r.b);
            }
            IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(getActivity());
            if (iActivityHandler != null) {
                iActivityHandler.updateSplash(this.s0);
            }
            this.u = true;
            return zA;
        } finally {
            IOUtil.close((InputStream) null);
        }
    }

    void c() {
        Activity activity = this.a;
        if (activity != null && (activity instanceof WebAppActivity)) {
            ((WebAppActivity) activity).onAppActive(this.o);
            ((WebAppActivity) this.a).onAppActive(this);
        }
        diyStatusBarState();
        setStatus((byte) 3);
        this.b.onAppActive(this);
        callSysEventListener(ISysEventListener.SysEventType.onWebAppForeground, IntentConst.obtainArgs(obtainWebAppIntent(), this.o));
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean callSysEventListener(ISysEventListener.SysEventType sysEventType, Object obj) {
        HashMap map = this.B0;
        boolean zOnExecute = false;
        if (map == null) {
            return false;
        }
        ArrayList arrayList = (ArrayList) map.get(sysEventType);
        ArrayList arrayList2 = (ArrayList) this.B0.get(ISysEventListener.SysEventType.AllSystemEvent);
        ArrayList arrayList3 = new ArrayList();
        if (arrayList != null) {
            arrayList3.addAll(arrayList);
        }
        if (arrayList2 != null) {
            arrayList3.addAll(arrayList2);
        }
        for (int size = arrayList3.size() - 1; size >= 0; size--) {
            ISysEventListener iSysEventListener = (ISysEventListener) arrayList3.get(size);
            if (a(iSysEventListener, sysEventType) && (zOnExecute || iSysEventListener.onExecute(sysEventType, obj)) && !a(sysEventType)) {
                break;
            }
        }
        return zOnExecute;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean checkIsCustomPath() {
        return this.n0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void checkOrLoadlaunchWebview() {
        r rVar = this.x;
        if (rVar != null) {
            AdaFrameView adaFrameView = (AdaFrameView) rVar.processEvent(IMgr.MgrType.WindowMgr, 46, obtainAppId());
            Logger.d("Direct_page", "checkOrLoadlaunchWebview " + manifestBeParsed() + ";adaFrameView=" + adaFrameView);
            this.v1 = manifestBeParsed() ^ true;
            if (adaFrameView == null || !manifestBeParsed()) {
                return;
            }
            adaFrameView.obtainWebView().checkIfNeedLoadOriginalUrl();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean checkPrivateDir(String str) {
        return str.startsWith(obtainAppDataPath()) || str.startsWith(BaseInfo.REL_PRIVATE_WWW_DIR);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String checkPrivateDirAndCopy2Temp(String str) throws IOException {
        if (obtainRunningAppMode() == 1 && checkPrivateDir(str)) {
            String str2 = "/" + BaseInfo.APP_WWW_FS_DIR;
            String strSubstring = str.substring(str.indexOf(str2) + str2.length());
            String str3 = this.j0 + strSubstring;
            str = obtainAppTempPath() + strSubstring;
            if (!DHFile.exists(str)) {
                DHFile.copyAssetsFile(str3, str);
            }
        }
        return str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean checkSchemeWhite(String str) {
        if (!q()) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator it = this.l1.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (!TextUtils.equals(str2, "*")) {
                if (str.startsWith(str2 + ":")) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean checkWhiteUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.k1.contains("*") || this.k1.contains(str);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void clearRuntimeArgs() {
        this.D = "";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2AbsFullPath(String str, String str2) {
        boolean z = true;
        try {
            if (!PdrUtil.isEmpty(str2)) {
                if (this.U == 1 && PlatformUtil.isResFileExists(str2)) {
                    return str2;
                }
                if (DHFile.isExist(str2)) {
                    return str2;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (PdrUtil.isEmpty(str2)) {
            return str2;
        }
        int iIndexOf = str2.indexOf(Operators.CONDITION_IF_STRING);
        if (iIndexOf > 0) {
            str2 = str2.substring(0, iIndexOf);
        }
        if (str2.startsWith("_documents/")) {
            return BaseInfo.sDocumentFullPath + str2.substring(11);
        }
        if (str2.startsWith(BaseInfo.REL_PUBLIC_DOCUMENTS_DIR)) {
            return BaseInfo.sDocumentFullPath + str2.substring(10);
        }
        if (str2.startsWith(AbsoluteConst.MINI_SERVER_APP_DOC)) {
            return obtainAppDocPath() + str2.substring(5);
        }
        if (str2.startsWith(BaseInfo.REL_PRIVATE_DOC_DIR)) {
            return obtainAppDocPath() + str2.substring(4);
        }
        if (str2.startsWith("_downloads/")) {
            return BaseInfo.sDownloadFullPath + str2.substring(11);
        }
        if (str2.startsWith(BaseInfo.REL_PUBLIC_DOWNLOADS_DIR)) {
            return BaseInfo.sDownloadFullPath + str2.substring(10);
        }
        if (str2.startsWith(AbsoluteConst.MINI_SERVER_APP_WWW)) {
            byte b2 = this.U;
            if (b2 != 1) {
                if (b2 != 0) {
                    return str2;
                }
                return this.j0 + str2.substring(5);
            }
            return BaseInfo.sBaseResAppsPath + this.o + "/" + BaseInfo.APP_WWW_FS_DIR + str2.substring(5);
        }
        if (str2.startsWith(BaseInfo.REL_PRIVATE_WWW_DIR)) {
            byte b3 = this.U;
            if (b3 != 1) {
                if (b3 != 0) {
                    return str2;
                }
                return this.j0 + str2.substring(4);
            }
            return BaseInfo.sBaseResAppsPath + this.o + "/" + BaseInfo.APP_WWW_FS_DIR + str2.substring(4);
        }
        if (str2.startsWith(DeviceInfo.FILE_PROTOCOL)) {
            return str2.substring(7);
        }
        if (str2.startsWith("content://") || str2.startsWith(DeviceInfo.sDeviceRootDir)) {
            return str2;
        }
        if (str2.startsWith("http://localhost")) {
            String strSubstring = str2.substring(16);
            return convert2AbsFullPath(null, strSubstring.substring(strSubstring.indexOf("/") + 1));
        }
        if (!str2.startsWith("/") && str != null) {
            z = false;
        } else if (str2.startsWith("/")) {
            str2 = str2.substring(1);
        }
        if (str != null) {
            if (str.startsWith(SDK.ANDROID_ASSET)) {
                str = str.substring(22);
            } else if (str.startsWith(DeviceInfo.FILE_PROTOCOL)) {
                str = str.substring(7);
            }
        }
        if (str != null && !z) {
            return PdrUtil.standardizedURL(str, str2);
        }
        if (!z) {
            return str2;
        }
        String strObtainAppDataPath = obtainAppDataPath();
        if (str != null && !PdrUtil.isEquals(str, strObtainAppDataPath) && str.contains("/www/")) {
            strObtainAppDataPath = str.substring(0, str.indexOf("/www/") + 5);
        }
        return strObtainAppDataPath + b(str2);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2LocalFullPath(String str, String str2) throws Throwable {
        String strConvert2AbsFullPath = convert2AbsFullPath(str, str2);
        byte b2 = this.U;
        if (b2 != 1 && !DeviceInfo.isPrivateDirectory) {
            return strConvert2AbsFullPath;
        }
        InputStream resInputStream = b2 == 1 ? PlatformUtil.getResInputStream(strConvert2AbsFullPath) : PlatformUtil.getInputStream(strConvert2AbsFullPath);
        if (resInputStream != null) {
            strConvert2AbsFullPath = obtainAppTempPath() + System.currentTimeMillis();
            try {
                DHFile.writeFile(resInputStream, strConvert2AbsFullPath);
                resInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return strConvert2AbsFullPath;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2RelPath(String str) {
        try {
            int length = obtainAppDataPath().length();
            int length2 = obtainAppDocPath().length();
            int length3 = BaseInfo.sDocumentFullPath.length();
            int length4 = BaseInfo.sDownloadFullPath.length();
            if (str.startsWith(obtainAppDataPath())) {
                str = BaseInfo.REL_PRIVATE_WWW_DIR + str.substring(length - 1);
            } else {
                int i = length - 1;
                if (str.startsWith(obtainAppDataPath().substring(0, i))) {
                    str = BaseInfo.REL_PRIVATE_WWW_DIR + str.substring(i, str.length());
                } else if (str.startsWith(obtainAppDocPath())) {
                    str = BaseInfo.REL_PRIVATE_DOC_DIR + str.substring(length2 - 1);
                } else {
                    int i2 = length2 - 1;
                    if (str.startsWith(obtainAppDocPath().substring(0, i2))) {
                        str = BaseInfo.REL_PRIVATE_DOC_DIR + str.substring(i2);
                    } else if (str.startsWith(BaseInfo.sDocumentFullPath)) {
                        str = BaseInfo.REL_PUBLIC_DOCUMENTS_DIR + str.substring(length3 - 1);
                    } else {
                        int i3 = length3 - 1;
                        if (str.startsWith(BaseInfo.sDocumentFullPath.substring(0, i3))) {
                            str = BaseInfo.REL_PUBLIC_DOCUMENTS_DIR + str.substring(i3);
                        } else if (str.startsWith(BaseInfo.sDownloadFullPath)) {
                            str = BaseInfo.REL_PUBLIC_DOWNLOADS_DIR + str.substring(length4 - 1);
                        } else {
                            int i4 = length4 - 1;
                            if (str.startsWith(BaseInfo.sDownloadFullPath.substring(0, i4))) {
                                str = BaseInfo.REL_PUBLIC_DOWNLOADS_DIR + str.substring(i4);
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2WebviewFullPath(String str, String str2) {
        boolean z;
        if (PdrUtil.isEmpty(str2)) {
            return str2;
        }
        if (this.T) {
            if (str2.startsWith(DeviceInfo.HTTP_PROTOCOL)) {
                return str2;
            }
            return this.r0 + str2;
        }
        if (str2.startsWith(DeviceInfo.FILE_PROTOCOL) || str2.startsWith(DeviceInfo.HTTP_PROTOCOL) || str2.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
            return str2;
        }
        try {
            if (DHFile.isExist(str2)) {
                return "file:///" + b(str2);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (str2.startsWith(DeviceInfo.sDeviceRootDir)) {
            return DeviceInfo.FILE_PROTOCOL + str2;
        }
        if (str2.startsWith("/")) {
            z = true;
            str2 = str2.substring(1);
        } else {
            z = false;
        }
        if (str2.startsWith(BaseInfo.REL_PRIVATE_WWW_DIR)) {
            return obtainWebviewBaseUrl() + b(str2.substring(4));
        }
        if (str2.startsWith(BaseInfo.REL_PUBLIC_DOCUMENTS_DIR)) {
            return DeviceInfo.FILE_PROTOCOL + BaseInfo.sDocumentFullPath + b(str2.substring(10));
        }
        if (str2.startsWith(BaseInfo.REL_PRIVATE_DOC_DIR)) {
            return DeviceInfo.FILE_PROTOCOL + obtainAppDocPath() + b(str2.substring(4));
        }
        if (str2.startsWith(BaseInfo.REL_PUBLIC_DOWNLOADS_DIR)) {
            return DeviceInfo.FILE_PROTOCOL + BaseInfo.sDownloadFullPath + b(str2.substring(10));
        }
        if (str != null && !z) {
            return PdrUtil.standardizedURL(str, str2);
        }
        String strObtainWebviewBaseUrl = obtainWebviewBaseUrl();
        if (str != null && !PdrUtil.isEquals(str, strObtainWebviewBaseUrl) && str.contains("/www/")) {
            strObtainWebviewBaseUrl = str.substring(0, str.indexOf("/www/") + 5);
        }
        return strObtainWebviewBaseUrl + b(str2);
    }

    JSONObject d(String str) throws IOException {
        if (this.w1 == null) {
            m();
        }
        if (this.w1 == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return PdrUtil.getSitemapParameters(this.w1, obtainAppId(), str);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void deleteAppTemp() {
        ThreadPool.self().addThreadTask(new e(), true);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void diyStatusBarState() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        AppStatusBarManager appStatusBarManager = this.m;
        if (appStatusBarManager == null) {
            return;
        }
        if (this.i) {
            appStatusBarManager.setFullScreen(getActivity(), this.i);
        } else {
            if (appStatusBarManager.checkImmersedStatusBar(getActivity(), this.a1)) {
                BaseInfo.isImmersive = true;
                this.m.setImmersive(getActivity(), true);
            } else {
                BaseInfo.isImmersive = false;
                this.m.setImmersive(getActivity(), false);
            }
            if (getActivity() != null) {
                if (PdrUtil.isEmpty(this.W0)) {
                    this.m.setStatusBarColor(getActivity(), BaseInfo.mDeStatusBarBackground);
                } else {
                    this.m.setStatusBarColor(getActivity(), this.W0.startsWith("#") ? PdrUtil.stringToColor(this.W0) : 0);
                }
            }
            this.m.setStatusBarMode(getActivity(), this.Z0);
        }
        if (this.m.isFullScreenOrImmersive()) {
            updateScreenInfo(2);
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String forceShortCut() {
        return this.z1;
    }

    boolean g(String str) {
        setRuntimeArgs(str);
        setStatus((byte) 3);
        Object objProcessEvent = this.x.processEvent(IMgr.MgrType.WindowMgr, 41, new Object[]{this, convert2WebviewFullPath(null, this.q0), Boolean.valueOf(this.S0)});
        if (objProcessEvent == null) {
            return true;
        }
        return Boolean.parseBoolean(String.valueOf(objProcessEvent));
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public IConfusionMgr getConfusionMgr() {
        return this.s1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getDirectPage() {
        return this.m1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public IApp.IAppStatusListener getIAppStatusListener() {
        return this.O0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getOriginalDirectPage() {
        return this.n1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getPathByType(byte b2) {
        if (b2 == 0) {
            return obtainAppDataPath();
        }
        if (b2 == 1) {
            return obtainAppDocPath();
        }
        if (b2 == 2) {
            return BaseInfo.sDocumentFullPath;
        }
        if (b2 == 3) {
            return BaseInfo.sDownloadFullPath;
        }
        if (b2 != -1) {
            return null;
        }
        return BaseInfo.sBaseResAppsPath + this.o + "/" + BaseInfo.APP_WWW_FS_DIR;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getPopGesture() {
        return this.R0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public int getQuitModel() {
        return this.r1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String getSystemInfo() throws JSONException {
        try {
            if (DeviceInfo.sSystemInfo == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(DeviceInfo.sSystemInfo.toString());
            jSONObject.put("uniCompileVersion", this.t0);
            jSONObject.put("uniRuntimeVersion", BaseInfo.uniVersionV3);
            jSONObject.put("browserName", WebViewFactory.isOther() ? "x5webview" : "chrome");
            jSONObject.put("appId", BaseInfo.sCurrentAppOriginalAppid);
            jSONObject.put(WXConfig.appName, this.s0);
            if (SDK.isUniMP) {
                jSONObject.put(WXConfig.appVersion, this.z);
                jSONObject.put("appVersionCode", this.A);
            } else {
                jSONObject.put(WXConfig.appVersion, b(getActivity()));
                jSONObject.put("appVersionCode", a((Context) getActivity()));
            }
            jSONObject.put("appWgtVersion", this.z);
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean isOnAppRunningMode() {
        return this.U == 1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean isUniApp() {
        return this.q;
    }

    public float j() {
        return (PermissionControler.checkPermission(this.o, IFeature.F_DEVICE.toLowerCase(Locale.ENGLISH)) && getActivity() != null && NetworkTypeUtil.getNetworkType(getActivity()) == 4) ? 1000.0f : 0.0f;
    }

    void k() {
        if (PdrUtil.isEmpty(this.j0) || !DeviceInfo.startsWithSdcard(this.j0)) {
            setAppDataPath(BaseInfo.sCacheFsAppsPath + this.o + "/" + BaseInfo.REAL_PRIVATE_WWW_DIR);
        }
        if (PdrUtil.isEmpty(this.A1) || !DeviceInfo.startsWithSdcard(this.A1)) {
            setAppDocPath(BaseInfo.sBaseFsAppsPath + this.o + "/" + BaseInfo.REAL_PRIVATE_DOC_DIR);
        }
        if (PdrUtil.isEmpty(this.B1) || !DeviceInfo.startsWithSdcard(this.B1)) {
            this.B1 = BaseInfo.sCacheFsAppsPath + this.o + "/" + BaseInfo.APP_WEB_CHACHE;
        }
    }

    void m() throws IOException {
        File file = new File(c(this.o));
        if (file.exists()) {
            try {
                JSONObject jSONObject = new JSONObject(IOUtil.toString(new FileInputStream(file)));
                this.w1 = jSONObject;
                this.B = jSONObject.optString("version");
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        byte[] fileContent = PlatformUtil.getFileContent("data/sitemap/" + this.o + ".json", 0);
        if (fileContent != null) {
            DHFile.writeFile(fileContent, 0, c(this.o));
            m();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean manifestBeParsed() {
        return this.u || SDK.IntegratedMode.WEBVIEW == BaseInfo.sRuntimeMode;
    }

    public boolean n() {
        if (q() && this.u) {
            return this.O;
        }
        return true;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean needRefreshApp() {
        return this.j1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean needReload() {
        return this.i1;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean o() {
        /*
            r5 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = io.dcloud.common.util.BaseInfo.uniVersionV3
            boolean r1 = io.dcloud.common.util.PdrUtil.isEmpty(r1)
            if (r1 == 0) goto L51
            boolean r1 = io.dcloud.common.util.BaseInfo.SyncDebug
            if (r1 == 0) goto L1d
            java.lang.String r1 = "uni-jsframework-dev.js"
            java.io.InputStream r2 = io.dcloud.common.adapter.util.PlatformUtil.getResInputStream(r1)
            if (r2 == 0) goto L1d
            boolean r2 = io.dcloud.feature.internal.sdk.SDK.isUniMPSDK()
            if (r2 != 0) goto L1d
            goto L1f
        L1d:
            java.lang.String r1 = "uni-jsframework.js"
        L1f:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L53
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L53
            android.app.Activity r4 = r5.getActivity()     // Catch: java.lang.Throwable -> L53
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Throwable -> L53
            java.io.InputStream r1 = r4.open(r1)     // Catch: java.lang.Throwable -> L53
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L53
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L53
            java.lang.String r1 = r2.readLine()     // Catch: java.lang.Throwable -> L53
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L53
            r3 = 2
            java.lang.String r1 = r1.substring(r3)     // Catch: java.lang.Throwable -> L53
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L53
            java.lang.String r1 = "version"
            java.lang.String r0 = r2.optString(r1)     // Catch: java.lang.Throwable -> L53
            android.app.Activity r1 = r5.getActivity()     // Catch: java.lang.Throwable -> L53
            io.dcloud.common.util.BaseInfo.setUniVersionV3(r0, r1)     // Catch: java.lang.Throwable -> L53
            goto L53
        L51:
            java.lang.String r0 = io.dcloud.common.util.BaseInfo.uniVersionV3
        L53:
            java.lang.String r1 = r5.v0
            boolean r1 = io.dcloud.common.util.PdrUtil.isEmpty(r1)
            r2 = 0
            if (r1 != 0) goto L69
            java.lang.String r1 = r5.v0
            boolean r1 = r1.contains(r0)
            if (r1 == 0) goto L69
            boolean r1 = r5.w0
            if (r1 != 0) goto L69
            return r2
        L69:
            boolean r1 = r5.x0
            if (r1 != 0) goto L93
            boolean r1 = r5.u
            if (r1 == 0) goto L93
            java.lang.String r1 = r5.t0
            java.lang.String r3 = "-1"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L93
            java.lang.String r1 = r5.t0
            java.lang.String r1 = r1.trim()
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L93
            boolean r0 = io.dcloud.common.util.PdrUtil.isEmpty(r0)
            if (r0 != 0) goto L93
            boolean r0 = r5.q
            if (r0 == 0) goto L93
            r0 = 1
            return r0
        L93:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.a5.o():boolean");
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAdaptationJs() throws IOException {
        if (this.S == null && !PdrUtil.isEmpty(this.J)) {
            byte[] fileContent = PlatformUtil.getFileContent(a(this.J), obtainRunningAppMode() == 1 ? 0 : 2);
            if (fileContent != null) {
                this.S = new String(fileContent);
            } else {
                this.S = "";
            }
        }
        return this.S;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppDataPath() {
        String str = this.j0;
        if (str != null) {
            return str;
        }
        return this.o + "/www/";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppDocPath() {
        return this.A1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppId() {
        return this.o;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppInfo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", this.o);
            jSONObject.put("versionName", this.z);
            jSONObject.put("name", this.s0);
            jSONObject.put("versionCode", this.A);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppLog() {
        return BaseInfo.sBaseFsAppsPath + this.o + "/log/";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppName() {
        return this.s0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public byte obtainAppStatus() {
        return this.t;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppTempPath() {
        return BaseInfo.sBaseFsAppsPath + this.o + "/temp/";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppVersionCode() {
        return this.A;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppVersionName() {
        return this.z;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAppWebCachePath() {
        return this.B1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainConfigProperty(String str) {
        String strValueOf;
        if (PdrUtil.isEquals(str, "adid")) {
            strValueOf = this.M0;
        } else if (PdrUtil.isEquals(str, "launchError")) {
            strValueOf = this.L0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_AUTOCLOSE)) {
            strValueOf = String.valueOf(this.X);
        } else if (PdrUtil.isEquals(str, "timeout")) {
            strValueOf = String.valueOf(this.Z);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_DELAY)) {
            strValueOf = String.valueOf(this.a0);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_SPLASHSCREEN)) {
            strValueOf = String.valueOf(this.V);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_WAITING)) {
            strValueOf = String.valueOf(this.W);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_H5PLUS)) {
            strValueOf = String.valueOf(this.Q);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_funSetUA)) {
            strValueOf = String.valueOf(this.R);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_USER_AGENT)) {
            strValueOf = this.K;
        } else if (PdrUtil.isEquals(str, "error")) {
            strValueOf = this.p0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_FULLSCREEN)) {
            strValueOf = String.valueOf(this.i);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_UNTRUSTEDCA)) {
            strValueOf = this.o0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_LOADED_TIME)) {
            strValueOf = this.P0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_RAM_CACHE_MODE)) {
            strValueOf = this.Q0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_JSERROR)) {
            strValueOf = this.M + "";
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_CRASH)) {
            strValueOf = this.L + "";
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_USE_ENCRYPTION)) {
            strValueOf = this.T0 + "";
        } else if (PdrUtil.isEquals(str, "w2a_delay")) {
            strValueOf = String.valueOf(this.b0);
        } else if (PdrUtil.isEquals(str, "w2a_autoclose")) {
            strValueOf = String.valueOf(this.Y);
        } else if (PdrUtil.isEquals(str, "wap2app_running_mode")) {
            strValueOf = this.N + "";
        } else if (PdrUtil.isEquals(str, "injection")) {
            strValueOf = this.h1 + "";
        } else if (PdrUtil.isEquals(str, "event")) {
            strValueOf = this.c0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_TARGET)) {
            strValueOf = this.d0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_LPLUSERQUIRE)) {
            strValueOf = this.e0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_SPLUSERQUIRE)) {
            strValueOf = this.f0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_LGEOLOCATION)) {
            strValueOf = this.g0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_SGEOLOCATION)) {
            strValueOf = this.h0;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_BC)) {
            strValueOf = this.W0 + "";
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_MODE)) {
            strValueOf = this.Z0;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED)) {
            strValueOf = this.a1 + "";
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_LAUNCH_ISSTATUS)) {
            strValueOf = String.valueOf(this.b1);
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_LAUNCH_STATUSBAR_COLOR)) {
            strValueOf = this.d1;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_SECOND_ISATATUS)) {
            strValueOf = String.valueOf(this.c1);
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_SECOND_STATUSBAR_COLOR)) {
            strValueOf = this.e1;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_MAP_COORD_TYPE)) {
            strValueOf = this.o1;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.UNIAPP_WEEX_JS_SERVICE)) {
            strValueOf = String.valueOf(this.f1);
        } else if (PdrUtil.isEquals(str, AbsoluteConst.APP_UNIAPP_VERSION)) {
            strValueOf = this.t0;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_UNIAPP_CONTROL)) {
            strValueOf = this.q ? this.y0 : "h5+";
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.UNI_NVUE_DATA)) {
            JSONObject jSONObject = this.u0;
            strValueOf = jSONObject == null ? null : jSONObject.toString();
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_CONCATENATE)) {
            strValueOf = this.P + "";
        } else if (PdrUtil.isEquals(str, AbsoluteConst.NVUE_LAUNCH_MODE)) {
            strValueOf = this.z0;
        } else if (PdrUtil.isEquals(str, AbsoluteConst.JSON_KEY_DEBUG_REFRESH)) {
            strValueOf = this.q1;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.UNI_RESTART_TO_DIRECT)) {
            strValueOf = String.valueOf(this.p1);
        } else if (PdrUtil.isEquals(str, AbsoluteConst.APP_IS_UNIAPP)) {
            strValueOf = String.valueOf(this.q);
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_USE_V3_ENCRYPTION)) {
            strValueOf = String.valueOf(this.U0);
        } else {
            if (!PdrUtil.isEquals(str, IntentConst.UNIMP_RUN_EXTRA_INFO)) {
                return null;
            }
            strValueOf = this.t1;
        }
        return strValueOf;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public IWebviewStateListener obtainLaunchPageStateListener() {
        return this.u1;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public Object obtainMgrData(IMgr.MgrType mgrType, int i, Object[] objArr) {
        return this.x.processEvent(mgrType, i, objArr);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainOriginalAppId() {
        return this.y;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public InputStream obtainResInStream(String str, String str2) {
        String strConvert2AbsFullPath = convert2AbsFullPath(str, str2);
        byte b2 = this.U;
        if (b2 == 1) {
            if (!PdrUtil.isDeviceRootDir(strConvert2AbsFullPath)) {
                return PlatformUtil.getResInputStream(strConvert2AbsFullPath);
            }
            try {
                return DHFile.getInputStream(DHFile.createFileHandler(strConvert2AbsFullPath));
            } catch (IOException e2) {
                Logger.w("WebApp.obtainResInStream", e2);
            }
        } else if (b2 == 0) {
            try {
                return DHFile.getInputStream(DHFile.createFileHandler(strConvert2AbsFullPath));
            } catch (IOException e3) {
                Logger.w("WebApp.obtainResInStream", e3);
            }
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public byte obtainRunningAppMode() {
        return this.U;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainRuntimeArgs(boolean z) {
        return z ? JSONObject.quote(this.D) : this.D;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public AppStatusBarManager obtainStatusBarMgr() {
        return this.m;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public JSONObject obtainThridInfo(IApp.ConfigProperty.ThridInfo thridInfo) throws IOException {
        switch (f.a[thridInfo.ordinal()]) {
            case 1:
                return this.C0;
            case 2:
                return this.E0;
            case 3:
                return this.F0;
            case 4:
                return this.G0;
            case 5:
                return this.I0;
            case 6:
                m();
                return this.w1;
            case 7:
                return this.x.g;
            case 8:
                return this.J0;
            case 9:
                return this.D0;
            default:
                return null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainVersionSitemap() {
        return this.B;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public Intent obtainWebAppIntent() {
        return this.N0;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainWebviewBaseUrl() {
        return a(this.U);
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        byte b2 = this.t;
        if (b2 == 3) {
            return callSysEventListener(sysEventType, obj);
        }
        if (b2 == 1 && (sysEventType == ISysEventListener.SysEventType.onWebAppStop || sysEventType == ISysEventListener.SysEventType.onStop)) {
            s();
        }
        return false;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void onSplashClosed() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        diyStatusBarState();
    }

    public boolean p() {
        return this.t == 3;
    }

    public boolean q() {
        Intent intentObtainWebAppIntent = obtainWebAppIntent();
        if (intentObtainWebAppIntent != null) {
            boolean z = this.F1;
            this.F1 = intentObtainWebAppIntent.getBooleanExtra(IntentConst.IS_STREAM_APP, z) | z;
        }
        return this.F1;
    }

    public boolean r() {
        Logger.d(Logger.AppMgr_TAG, this.o + " onStop");
        IApp.IAppStatusListener iAppStatusListener = this.O0;
        if (iAppStatusListener != null) {
            return iAppStatusListener.onStop();
        }
        return true;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void registerSysEventListener(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType) {
        if (this.B0 == null) {
            this.B0 = new HashMap(1);
        }
        ArrayList arrayList = (ArrayList) this.B0.get(sysEventType);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.B0.put(sysEventType, arrayList);
        }
        arrayList.add(iSysEventListener);
    }

    public void s() {
        this.k1.clear();
        this.l1.clear();
        Activity activity = this.a;
        if (activity != null && (activity instanceof WebAppActivity)) {
            ((WebAppActivity) activity).onAppStop(this.o);
        }
        Logger.d(Logger.AppMgr_TAG, "webapp.onStoped");
        BaseInfo.s_Runing_App_Count--;
        callSysEventListener(ISysEventListener.SysEventType.onWebAppStop, this);
        d();
        PermissionUtil.removeTempPermission(this.a, this.o);
        b();
        deleteAppTemp();
        PermissionControler.unregisterRootPermission(this.o);
        this.x.e(this);
        if (getIAppStatusListener() != null) {
            getIAppStatusListener().onStoped(false, null);
        }
        this.x.processEvent(IMgr.MgrType.WindowMgr, 25, this);
        PrivacyManager.getInstance().unRegisterPrivacyAgreeAllListener();
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setAppDataPath(String str) {
        if (this.U == 1) {
            if (str.startsWith(BaseInfo.sBaseResAppsPath)) {
                this.j0 = str;
                return;
            }
            this.j0 = BaseInfo.sBaseResAppsPath + this.o + "/" + BaseInfo.APP_WWW_FS_DIR;
            return;
        }
        if (new File(str).exists()) {
            this.j0 = str;
            return;
        }
        if (str.startsWith(DeviceInfo.sCacheRootDir)) {
            this.j0 = str;
            return;
        }
        this.j0 = DeviceInfo.sCacheRootDir + "/" + str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setAppDocPath(String str) {
        this.A1 = PdrUtil.appendByDeviceRootDir(str);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setConfigProperty(String str, String str2) {
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_AUTOCLOSE)) {
            this.X = PdrUtil.parseBoolean(str2, this.X, false);
            return;
        }
        if (PdrUtil.isEquals(str, "commit")) {
            a();
            return;
        }
        if (PdrUtil.isEquals(str, "timeout")) {
            this.Z = PdrUtil.parseInt(str2, this.Z);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_DELAY)) {
            this.a0 = PdrUtil.parseInt(str2, this.a0);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_SPLASHSCREEN)) {
            this.V = PdrUtil.parseBoolean(str2, this.V, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_WAITING)) {
            this.W = PdrUtil.parseBoolean(str2, this.W, false);
            return;
        }
        if (PdrUtil.isEquals(str, "name")) {
            this.s0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "name")) {
            this.F = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "email")) {
            this.G = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "url")) {
            this.I = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "name")) {
            this.z = str2;
            BaseInfo.sLastAppVersionName = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "code")) {
            this.A = str2;
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_RUNMODE_LIBERATE)) {
            this.i0 = PdrUtil.parseBoolean(str2, this.V, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_H5PLUS)) {
            this.Q = PdrUtil.parseBoolean(str2, true, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_funSetUA)) {
            this.R = PdrUtil.parseBoolean(str2, true, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_USER_AGENT)) {
            this.K = str2;
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_FULLSCREEN)) {
            this.i = PdrUtil.parseBoolean(str2, this.i, false);
            return;
        }
        if (PdrUtil.isEquals(str, "webcache_path")) {
            this.B1 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, "wap2app_running_mode")) {
            this.N = PdrUtil.parseBoolean(str2, false, false);
            return;
        }
        if (PdrUtil.isEquals(str, IApp.ConfigProperty.CONFIG_LOADED_TIME)) {
            this.P0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_BC)) {
            this.W0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_MODE)) {
            this.Z0 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED)) {
            this.a1 = Boolean.valueOf(str2).booleanValue();
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_LAUNCH_ISSTATUS)) {
            this.b1 = Boolean.valueOf(str2).booleanValue();
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_LAUNCH_STATUSBAR_COLOR)) {
            this.d1 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_SECOND_ISATATUS)) {
            this.c1 = Boolean.valueOf(str2).booleanValue();
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSONKEY_STATUSBAR_SECOND_STATUSBAR_COLOR)) {
            this.e1 = str2;
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.UNIAPP_WEEX_JS_SERVICE)) {
            this.f1 = Boolean.valueOf(str2).booleanValue();
            return;
        }
        if (PdrUtil.isEquals(str, AbsoluteConst.JSON_KEY_DEBUG_REFRESH)) {
            this.q1 = str2;
        } else if (PdrUtil.isEquals(str, IApp.ConfigProperty.UNI_RESTART_TO_DIRECT)) {
            this.p1 = Boolean.valueOf(str2).booleanValue();
        } else if (PdrUtil.isEquals(str, IntentConst.UNIMP_RUN_EXTRA_INFO)) {
            this.t1 = str2;
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setDirectPage(String str) {
        this.m1 = str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setHideNavBarState(boolean z) {
        this.p = z;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setIAppStatusListener(IApp.IAppStatusListener iAppStatusListener) {
        this.O0 = iAppStatusListener;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setLaunchPageStateListener(IWebviewStateListener iWebviewStateListener) {
        this.u1 = iWebviewStateListener;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setNeedRefreshApp(boolean z) {
        this.j1 = z;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setQuitModel(int i) {
        this.r1 = i;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setRuntimeArgs(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        this.D = str;
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setStatus(byte b2) {
        this.t = b2;
        if (b2 == 3) {
            this.g1 = System.currentTimeMillis();
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void setWebAppActivity(Activity activity) {
        this.a = activity;
        a(activity);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0117 A[Catch: Exception -> 0x01b7, MalformedURLException -> 0x01bc, TryCatch #4 {MalformedURLException -> 0x01bc, Exception -> 0x01b7, blocks: (B:14:0x0057, B:16:0x0062, B:19:0x006a, B:21:0x0070, B:24:0x0077, B:27:0x00a6, B:29:0x00a9, B:31:0x00b7, B:42:0x0110, B:41:0x010d, B:46:0x011a, B:48:0x012d, B:50:0x0133, B:51:0x013b, B:52:0x0142, B:54:0x0152, B:56:0x0163, B:58:0x0171, B:59:0x0173, B:60:0x0175, B:62:0x017d, B:64:0x0185, B:66:0x018e, B:68:0x0196, B:69:0x019e, B:70:0x01ae, B:73:0x01b3, B:44:0x0117, B:34:0x00c0, B:38:0x00ed, B:35:0x00d7, B:37:0x00da), top: B:93:0x0057, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0142 A[Catch: JSONException -> 0x01b2, Exception -> 0x01b7, MalformedURLException -> 0x01bc, TryCatch #0 {JSONException -> 0x01b2, blocks: (B:48:0x012d, B:50:0x0133, B:51:0x013b, B:52:0x0142, B:54:0x0152, B:56:0x0163, B:58:0x0171, B:59:0x0173, B:60:0x0175, B:62:0x017d, B:64:0x0185, B:66:0x018e, B:68:0x0196, B:69:0x019e, B:70:0x01ae), top: B:90:0x012d, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0152 A[Catch: JSONException -> 0x01b2, Exception -> 0x01b7, MalformedURLException -> 0x01bc, TryCatch #0 {JSONException -> 0x01b2, blocks: (B:48:0x012d, B:50:0x0133, B:51:0x013b, B:52:0x0142, B:54:0x0152, B:56:0x0163, B:58:0x0171, B:59:0x0173, B:60:0x0175, B:62:0x017d, B:64:0x0185, B:66:0x018e, B:68:0x0196, B:69:0x019e, B:70:0x01ae), top: B:90:0x012d, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01ae A[Catch: JSONException -> 0x01b2, Exception -> 0x01b7, MalformedURLException -> 0x01bc, TRY_LEAVE, TryCatch #0 {JSONException -> 0x01b2, blocks: (B:48:0x012d, B:50:0x0133, B:51:0x013b, B:52:0x0142, B:54:0x0152, B:56:0x0163, B:58:0x0171, B:59:0x0173, B:60:0x0175, B:62:0x017d, B:64:0x0185, B:66:0x018e, B:68:0x0196, B:69:0x019e, B:70:0x01ae), top: B:90:0x012d, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x012d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // io.dcloud.common.DHInterface.IApp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setWebAppIntent(android.content.Intent r18) {
        /*
            Method dump skipped, instructions count: 488
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.a5.setWebAppIntent(android.content.Intent):void");
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String shortcutQuit() {
        return this.y1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.dcloud.common.DHInterface.IApp
    public void showSplash() {
        Activity activity = getActivity();
        if (activity instanceof IOnCreateSplashView) {
            activity.setIntent(this.N0);
            ((IOnCreateSplashView) activity).onCreateSplash(activity);
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public boolean startFromShortCut() {
        return this.x1;
    }

    void t() {
        PermissionControler.registerPermission(this.o, this.A0);
    }

    public String toString() {
        return this.s0 + Operators.SUB + this.o + Operators.SUB + super.toString();
    }

    public void u() {
        b(false);
        setStatus((byte) 1);
        AppStatus.setAppStatus(this.o, 0);
        this.x.processEvent(IMgr.MgrType.FeatureMgr, 3, this.o);
        Logger.d(Logger.AppMgr_TAG, this.o + " will active change to unrunning");
        this.x.processEvent(null, 0, this);
        WebViewFactory.sUsePermissionWebviews.clear();
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void unregisterSysEventListener(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType) {
        ArrayList arrayList;
        HashMap map = this.B0;
        if (map == null || (arrayList = (ArrayList) map.get(sysEventType)) == null) {
            return;
        }
        arrayList.remove(iSysEventListener);
        if (arrayList.isEmpty()) {
            this.B0.remove(sysEventType);
        }
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public void updateDirectPage(String str) throws JSONException, IOException {
        if (TextUtils.isEmpty(str)) {
            str = this.m1;
        }
        JSONObject jSONObjectD = d(str);
        if (jSONObjectD != null) {
            this.J0 = jSONObjectD;
            e();
            this.x.processEvent(IMgr.MgrType.WindowMgr, 48, this);
        }
    }

    public String v() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", this.o);
            jSONObject.put("version", this.z);
            jSONObject.put("name", this.s0);
            jSONObject.put("versionCode", this.A);
            jSONObject.put("description", this.E);
            jSONObject.put("author", this.F);
            jSONObject.put("email", this.G);
            jSONObject.put(IApp.ConfigProperty.CONFIG_LICENSE, this.H);
            jSONObject.put("licensehref", this.I);
            jSONObject.put(IApp.ConfigProperty.CONFIG_FEATURES, new JSONArray((Collection) this.A0));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    void w() {
        b(true);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String obtainAuthority(String str) {
        String str2 = BaseInfo.sGlobalAuthority;
        if ((str2 != null && TextUtils.equals("*", str2)) || !q() || TextUtils.isEmpty(str) || e(this.o)) {
            return IApp.AUTHORITY_AUTHORIZED;
        }
        JSONObject jSONObject = this.H0;
        if (jSONObject == null) {
            return IApp.AUTHORITY_UNDETERMINED;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (str.equalsIgnoreCase(next)) {
                return this.H0.optString(next, IApp.AUTHORITY_UNDETERMINED);
            }
        }
        return IApp.AUTHORITY_UNDETERMINED;
    }

    /* JADX WARN: Removed duplicated region for block: B:289:0x0656  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x06a3  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x06c5  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x06dd  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x06f5  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0742  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0768  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0784  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x08ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean a(java.io.InputStream r62, java.lang.String r63, org.json.JSONObject r64) throws org.json.JSONException, java.security.NoSuchAlgorithmException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 2927
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.a5.a(java.io.InputStream, java.lang.String, org.json.JSONObject):boolean");
    }

    public void g() {
        ArrayList arrayList = this.A0;
        if (arrayList != null) {
            arrayList.clear();
            this.A0 = null;
        }
        HashMap map = this.B0;
        if (map != null) {
            map.clear();
            this.B0 = null;
        }
        this.s1.removeData(this.o);
        this.x = null;
        this.s = null;
        this.f1 = false;
    }

    private static PackageInfo c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean c(java.lang.String r10, org.json.JSONObject r11) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.a5.c(java.lang.String, org.json.JSONObject):boolean");
    }

    boolean f(String str) {
        if (!this.u && this.w) {
            return false;
        }
        Logger.e("Webapp start " + this.o);
        Activity activity = this.a;
        if (activity != null && (activity instanceof WebAppActivity)) {
            ((WebAppActivity) activity).onAppStart(this.o);
            ((WebAppActivity) this.a).onAppStart(this);
        }
        BaseInfo.s_Runing_App_Count++;
        this.v = true;
        this.w = !this.u;
        setRuntimeArgs(str);
        return a(5);
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public InputStream obtainResInStream(String str) {
        return obtainResInStream(null, str);
    }

    private String c(String str) {
        return BaseInfo.sBaseFsSitMapPath + str + "/_sitemap.json";
    }

    @Override // io.dcloud.common.DHInterface.IApp
    public String convert2AbsFullPath(String str) {
        return convert2AbsFullPath(null, str);
    }

    void b(byte b2) {
        this.U = b2;
    }

    private static String b(String str) {
        return (str == null || str.length() <= 0 || str.charAt(0) != '/') ? str : b(str.substring(1));
    }

    void b(boolean z) {
        this.b.onAppUnActive(this);
        if (z) {
            callSysEventListener(ISysEventListener.SysEventType.onWebAppPause, this);
            callSysEventListener(ISysEventListener.SysEventType.onWebAppBackground, this);
        }
        IApp.IAppStatusListener iAppStatusListener = this.O0;
        if (iAppStatusListener != null) {
            iAppStatusListener.onPause(this, null);
        }
        setStatus((byte) 2);
    }

    private void b() {
        if (q() || !s.c(getActivity())) {
            return;
        }
        ThreadPool.self().addThreadTask(new d(obtainAppId()));
    }

    public static String b(Context context) {
        return c(context).versionName;
    }

    private JSONObject a(InputStream inputStream) {
        try {
            if (!this.q) {
                inputStream = new UnicodeInputStream(inputStream, Charset.defaultCharset().name());
            }
            return new JSONObject(new String(IOUtil.getBytes(inputStream)));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // io.dcloud.p.b5
    void a(Activity activity) {
        super.a(activity);
        AppStatusBarManager appStatusBarManager = this.m;
        if (appStatusBarManager != null) {
            appStatusBarManager.checkImmersedStatusBar(activity, this.a1);
            this.m.isFullScreen = isFullScreen();
        }
        this.l.mJsonViewOption = JSONUtil.createJSONObject("{}");
        this.f = PdrUtil.parseInt(SP.getBundleData(getActivity(), BaseInfo.PDR, "StatusBarHeight"), 0);
        updateScreenInfo(4);
        this.x1 = false;
        IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(getActivity());
        if (!q() && iActivityHandler != null) {
            HashMap map = new HashMap();
            map.put(CreateShortResultReceiver.KEY_VERSIONNAME, this.z);
            map.put("appid", this.o);
            map.put("name", this.s0);
            map.put("adid", this.M0);
            map.put("bg", this.X0);
            map.put(WXBasicComponentType.IMG, convert2AbsFullPath(this.Y0));
            k.a(getActivity(), this.o, "save", map);
        }
        Intent intent = activity.getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey(IntentConst.FROM_SHORT_CUT_STRAT) && extras.getBoolean(IntentConst.FROM_SHORT_CUT_STRAT)) {
                this.x1 = true;
            }
            if (extras != null && extras.containsKey(IntentConst.WEBAPP_ACTIVITY_CREATE_SHORTCUT)) {
                this.z1 = extras.getString(IntentConst.WEBAPP_ACTIVITY_CREATE_SHORTCUT);
            }
            if (extras != null && extras.containsKey("shortcutQuit")) {
                this.y1 = extras.getString("shortcutQuit");
            }
            if (extras != null && extras.containsKey(IntentConst.START_FORCE_SHORT_QUIT)) {
                this.y1 = extras.getString(IntentConst.START_FORCE_SHORT_QUIT);
            }
            if (intent.hasExtra(IntentConst.START_FORCE_SHORT)) {
                this.z1 = intent.getStringExtra(IntentConst.START_FORCE_SHORT);
            }
            if (TextUtils.isEmpty(this.z1)) {
                String launchType = BaseInfo.getLaunchType(intent);
                this.z1 = AbsoluteConst.INSTALL_OPTIONS_FORCE;
                if (launchType.equals("scheme")) {
                    this.z1 = "query";
                    return;
                }
                if (this.O) {
                    this.z1 = AbsoluteConst.INSTALL_OPTIONS_FORCE;
                    return;
                }
                String string = SP.getOrCreateBundle(activity, "pdr").getString(AbsoluteConst.TEST_RUN + this.o, null);
                if (!TextUtils.isEmpty(string) && string.equals("__am=t")) {
                    this.z1 = AbsoluteConst.INSTALL_OPTIONS_FORCE;
                } else {
                    this.z1 = "none";
                }
            }
        }
    }

    String a(String str) {
        return this.j0 + str;
    }

    void a(ICallBack iCallBack) {
        if ((BaseInfo.ISDEBUG || this.i0) && this.U == 1) {
            ThreadPool.self().addThreadTask(new c(iCallBack), true);
        } else {
            iCallBack.onCallBack(0, null);
        }
    }

    private boolean a(int i) {
        PermissionUtil.sUseStreamAppPermissionDialogCount = 0;
        WebViewFactory.sUsePermissionWebviews.clear();
        PermissionUtil.removeTempPermission(this.a, this.o);
        Logger.e(G1, "start0 mAppid===" + this.o);
        BaseInfo.sCurrentAppOriginalAppid = this.y;
        BaseInfo.putStartupTimeData(this.o, String.valueOf(System.currentTimeMillis()));
        BaseInfo.sProcessId = Process.myPid();
        String str = G1;
        StringBuilder sb = new StringBuilder();
        sb.append(this.o);
        sb.append(this.U == 1 ? " APP_RUNNING_MODE" : " FS_RUNNING_MODE");
        Logger.i(str, sb.toString());
        t();
        setStatus((byte) 3);
        IApp.IAppStatusListener iAppStatusListener = this.O0;
        if (iAppStatusListener != null) {
            iAppStatusListener.onStart();
        }
        Logger.i(G1, "mLaunchPath=" + this.k0);
        Logger.i("download_manager", "webapp start task begin success appid=" + this.o + " mLaunchPath=" + this.k0);
        String str2 = TestUtil.START_STREAM_APP;
        StringBuilder sb2 = new StringBuilder("webapp start appid=");
        sb2.append(this.o);
        TestUtil.print(str2, sb2.toString());
        BaseInfo.setLoadingLaunchePage(true, "start0");
        String stringExtra = getActivity().getIntent().getStringExtra(IntentConst.WEBAPP_ACTIVITY_LAUNCH_PATH);
        if (stringExtra != null && !"".equals(stringExtra.trim())) {
            getActivity().getIntent().removeExtra(IntentConst.WEBAPP_ACTIVITY_LAUNCH_PATH);
            if (!"about:blank".equals(stringExtra)) {
                stringExtra = convert2WebviewFullPath(null, stringExtra);
            }
            this.n0 = true;
        } else if (BaseInfo.isWap2AppAppid(this.o) && !TextUtils.isEmpty(this.m0)) {
            stringExtra = convert2WebviewFullPath(null, this.m0);
        } else {
            stringExtra = convert2WebviewFullPath(null, this.k0);
        }
        if (a((IApp) this) && !new File(a(BaseInfo.sConfigXML)).exists()) {
            stringExtra = TextUtils.isEmpty(this.l0) ? this.n1 : this.l0;
        }
        Uri data = getActivity().getIntent().getData();
        if (data != null && data.toString().endsWith(".html")) {
            stringExtra = data.toString();
        }
        if (this.p1) {
            stringExtra = convert2WebviewFullPath(null, "__uniappview.html");
        }
        Object objProcessEvent = this.x.processEvent(IMgr.MgrType.WindowMgr, i, new Object[]{this, stringExtra, Boolean.valueOf(this.S0), this.V0});
        if (objProcessEvent == null) {
            return true;
        }
        return Boolean.parseBoolean(String.valueOf(objProcessEvent));
    }

    private boolean a(IApp iApp) {
        return (TextUtils.isEmpty(iApp.getOriginalDirectPage()) || iApp.obtainWebAppIntent().hasExtra(IntentConst.DIRECT_PAGE)) ? false : true;
    }

    boolean a(boolean z) {
        if (z) {
            this.q1 = null;
            this.x.processEvent(IMgr.MgrType.WindowMgr, 76, this);
        }
        setAppDataPath(BaseInfo.sCacheFsAppsPath + this.o + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
        boolean zB = b(this.o, null);
        if (!zB) {
            return zB;
        }
        setConfigProperty(IApp.ConfigProperty.CONFIG_funSetUA, String.valueOf(false));
        PermissionUtil.clearUseRejectedCache();
        showSplash();
        this.x.processEvent(IMgr.MgrType.FeatureMgr, 3, this.o);
        callSysEventListener(ISysEventListener.SysEventType.onWebAppReStart, null);
        this.f1 = false;
        TestUtil.record(AbsoluteConst.RUN_5AP_TIME_KEY);
        return a(10);
    }

    private void a() {
        IPdrModule iPdrModuleA;
        if (this.q) {
            return;
        }
        if (!SDK.isUniMPSDK()) {
            io.dcloud.p.b.f().a(this.a);
        }
        if (q() || (iPdrModuleA = q3.a().a("commit")) == null) {
            return;
        }
        String str = this.M0;
        JSONObject jSONObject = this.x.g;
        iPdrModuleA.execute("start_up", new Object[]{this, str, jSONObject != null ? jSONObject.optString("version") : "0.1"});
    }

    public static int a(Context context) {
        return c(context).versionCode;
    }

    IFrameView a(IWebviewStateListener iWebviewStateListener) {
        t();
        return (IFrameView) this.x.processEvent(IMgr.MgrType.WindowMgr, 17, new Object[]{this, convert2WebviewFullPath(null, this.k0), iWebviewStateListener});
    }

    IFrameView a(IWebviewStateListener iWebviewStateListener, IDCloudWebviewClientListener iDCloudWebviewClientListener) {
        t();
        return (IFrameView) this.x.processEvent(IMgr.MgrType.WindowMgr, 17, new Object[]{this, convert2WebviewFullPath(null, this.k0), iWebviewStateListener, iDCloudWebviewClientListener});
    }

    private boolean a(ISysEventListener iSysEventListener, ISysEventListener.SysEventType sysEventType) {
        if (!(iSysEventListener instanceof IBoot) || PdrUtil.parseBoolean(String.valueOf(this.x.processEvent(null, 1, iSysEventListener)), false, false)) {
            return true;
        }
        return !(sysEventType == ISysEventListener.SysEventType.onStart || sysEventType == ISysEventListener.SysEventType.onStop || sysEventType == ISysEventListener.SysEventType.onPause || sysEventType == ISysEventListener.SysEventType.onResume) || (iSysEventListener instanceof ReceiveSystemEventVoucher);
    }

    public static boolean a(ISysEventListener.SysEventType sysEventType) {
        return (sysEventType == ISysEventListener.SysEventType.onKeyDown || sysEventType == ISysEventListener.SysEventType.onKeyUp || sysEventType == ISysEventListener.SysEventType.onKeyLongPress) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0239 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01d8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0202 A[Catch: Exception -> 0x0289, LOOP:0: B:71:0x0200->B:72:0x0202, LOOP_END, TryCatch #1 {Exception -> 0x0289, blocks: (B:60:0x01d8, B:62:0x01df, B:66:0x01ed, B:68:0x01f5, B:70:0x01fb, B:72:0x0202, B:73:0x0223, B:75:0x0229, B:77:0x0239, B:79:0x023e, B:80:0x0255, B:82:0x025c, B:83:0x025f, B:85:0x0279, B:64:0x01e7), top: B:107:0x01d8, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0229 A[Catch: Exception -> 0x0289, TRY_LEAVE, TryCatch #1 {Exception -> 0x0289, blocks: (B:60:0x01d8, B:62:0x01df, B:66:0x01ed, B:68:0x01f5, B:70:0x01fb, B:72:0x0202, B:73:0x0223, B:75:0x0229, B:77:0x0239, B:79:0x023e, B:80:0x0255, B:82:0x025c, B:83:0x025f, B:85:0x0279, B:64:0x01e7), top: B:107:0x01d8, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02a6  */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v22 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v27, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.File] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean a(java.lang.String r14, org.json.JSONObject r15) {
        /*
            Method dump skipped, instructions count: 697
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.a5.a(java.lang.String, org.json.JSONObject):boolean");
    }

    private String a(byte b2) {
        byte b3 = this.U;
        if (b3 != 1) {
            if (b3 != 0) {
                return null;
            }
            return DeviceInfo.FILE_PROTOCOL + this.j0;
        }
        return BaseInfo.sBaseResAppsFullPath + this.o + "/" + BaseInfo.APP_WWW_FS_DIR;
    }

    public void a(String str, int i) {
        this.D1.put(str, Integer.valueOf(i));
        if (TextUtils.isEmpty(this.E1)) {
            this.E1 = str + "=" + i;
        } else {
            this.E1 += "&" + str + "=" + i;
        }
        SP.getOrCreateBundle(getActivity(), this.o + "_1").edit().putString("Authorize", this.E1).commit();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(org.json.JSONObject r4, io.dcloud.p.c5 r5) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == 0) goto L20
            java.lang.String r1 = "control"
            boolean r2 = r4.has(r1)
            if (r2 == 0) goto L20
            java.lang.String r4 = r4.optString(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 != 0) goto L1e
            java.lang.String r1 = "uni-v3"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L1e
            goto L20
        L1e:
            r4 = 0
            goto L21
        L20:
            r4 = r0
        L21:
            if (r4 != 0) goto L2f
            r5.a = r0
            java.lang.String r0 = io.dcloud.common.constant.DOMException.MSG_RUNTIME_COMPONENTS_MODE_NOT_SUPPORT
            r1 = 1250(0x4e2, float:1.752E-42)
            java.lang.String r0 = io.dcloud.common.constant.DOMException.toJSON(r1, r0)
            r5.b = r0
        L2f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.a5.a(org.json.JSONObject, io.dcloud.p.c5):boolean");
    }
}
