package io.dcloud.feature.ui.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.Window;
import com.alibaba.fastjson.parser.JSONLexer;
import com.dcloud.android.widget.toast.ToastCompat;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.ui.webview.DCWebView;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.constant.StringConst;
import io.dcloud.common.ui.blur.DCBlurDraweeView;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.LoadAppUtils;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ShortCutUtil;
import io.dcloud.common.util.ShortcutCreateUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.emulator.EmulatorCheckUtil;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class NavigatorUIFeatureImpl implements IFeature {
    AbsMgr a;

    class a extends PermissionUtil.StreamPermissionRequest {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String[] b;
        final /* synthetic */ IApp c;
        final /* synthetic */ String d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(IApp iApp, IWebview iWebview, String[] strArr, IApp iApp2, String str) {
            super(iApp);
            this.a = iWebview;
            this.b = strArr;
            this.c = iApp2;
            this.d = str;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            NavigatorUIFeatureImpl.this.a(this.a, this.b, this.c, this.d);
        }
    }

    class b implements ISysEventListener {
        final /* synthetic */ int a;
        final /* synthetic */ IApp b;
        final /* synthetic */ IWebview c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        b(int i, IApp iApp, IWebview iWebview, String str, String str2) {
            this.a = i;
            this.b = iApp;
            this.c = iWebview;
            this.d = str;
            this.e = str2;
        }

        @Override // io.dcloud.common.DHInterface.ISysEventListener
        public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
            Object[] objArr = (Object[]) obj;
            int iIntValue = ((Integer) objArr[0]).intValue();
            int[] iArr = (int[]) objArr[2];
            ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onRequestPermissionsResult;
            if (sysEventType2 == sysEventType && iIntValue == this.a) {
                this.b.unregisterSysEventListener(this, sysEventType2);
                Deprecated_JSUtil.execCallback(this.c, this.e, StringUtil.format("{result:'%s'}", PermissionUtil.convert5PlusValue(iArr.length > 0 ? iArr[0] : this.c.obtainApp().checkSelfPermission(this.d, this.c.obtainApp().obtainAppName()))), JSUtil.OK, true, false);
            }
            return true;
        }
    }

    private void b(Context context, IWebview iWebview, String str, String str2) {
        String strRequestShortCut = ShortCutUtil.requestShortCut(context, str2);
        try {
            JSUtil.execCallback(iWebview, str, new JSONObject(ShortCutUtil.SHORT_CUT_EXISTING.equals(strRequestShortCut) ? StringUtil.format(DOMException.JSON_SHORTCUT_RESULT_INFO, "existing") : ShortCutUtil.SHORT_CUT_NONE.equals(strRequestShortCut) ? StringUtil.format(DOMException.JSON_SHORTCUT_RESULT_INFO, "none") : ShortCutUtil.NOPERMISSIONS.equals(strRequestShortCut) ? StringUtil.format(DOMException.JSON_SHORTCUT_RESULT_INFO, ShortCutUtil.NOPERMISSIONS) : StringUtil.format(DOMException.JSON_SHORTCUT_RESULT_INFO, "unknown")), JSUtil.OK, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, SecurityException, IOException, IllegalArgumentException, ParseException, InvocationTargetException {
        String strWrapJsVar;
        int iStringToColor;
        IApp iAppObtainApp = iWebview.obtainApp();
        String strObtainAppId = iAppObtainApp.obtainAppId();
        str.hashCode();
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -2079769446:
                if (str.equals("getOrientation")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1980692731:
                if (str.equals("hideSystemNavigation")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1921914628:
                if (str.equals("updateSplashscreen")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1763010304:
                if (str.equals("hasShortcut")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1294581845:
                if (str.equals("closeSplashscreen")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1250806682:
                if (str.equals("getStatusBarStyle")) {
                    c2 = 5;
                    break;
                }
                break;
            case -1180327431:
                if (str.equals("isLogs")) {
                    c2 = 6;
                    break;
                }
                break;
            case -831443264:
                if (str.equals("showSystemNavigation")) {
                    c2 = 7;
                    break;
                }
                break;
            case -802912774:
                if (str.equals("isSimulator")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -583672202:
                if (str.equals("removeSessionCookie")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -452882469:
                if (str.equals("isImmersedStatusbar")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -108255335:
                if (str.equals("getStatusBarBackground")) {
                    c2 = 11;
                    break;
                }
                break;
            case 126640486:
                if (str.equals("setCookie")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 204345677:
                if (str.equals("hasSplashscreen")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 301825860:
                if (str.equals("getUserAgent")) {
                    c2 = 14;
                    break;
                }
                break;
            case 341257562:
                if (str.equals("getCookie")) {
                    c2 = 15;
                    break;
                }
                break;
            case 580068706:
                if (str.equals("createShortcut")) {
                    c2 = 16;
                    break;
                }
                break;
            case 586449341:
                if (str.equals("setFullscreen")) {
                    c2 = 17;
                    break;
                }
                break;
            case 586897223:
                if (str.equals("getUiStyle")) {
                    c2 = 18;
                    break;
                }
                break;
            case 686218487:
                if (str.equals("checkPermission")) {
                    c2 = 19;
                    break;
                }
                break;
            case 746581438:
                if (str.equals("requestPermission")) {
                    c2 = 20;
                    break;
                }
                break;
            case 839078392:
                if (str.equals("isBackground")) {
                    c2 = 21;
                    break;
                }
                break;
            case 1063979522:
                if (str.equals("getSignature")) {
                    c2 = 22;
                    break;
                }
                break;
            case 1094478863:
                if (str.equals("hasNotchInScreen")) {
                    c2 = 23;
                    break;
                }
                break;
            case 1204872973:
                if (str.equals("setStatusBarBackground")) {
                    c2 = 24;
                    break;
                }
                break;
            case 1217359681:
                if (str.equals("removeAllCookie")) {
                    c2 = 25;
                    break;
                }
                break;
            case 1365206181:
                if (str.equals("isFullScreen")) {
                    c2 = JSONLexer.EOI;
                    break;
                }
                break;
            case 1841443122:
                if (str.equals("getStatusbarHeight")) {
                    c2 = 27;
                    break;
                }
                break;
            case 1850818488:
                if (str.equals("setUserAgent")) {
                    c2 = 28;
                    break;
                }
                break;
            case 1984754993:
                if (str.equals("setLogs")) {
                    c2 = 29;
                    break;
                }
                break;
            case 2104007794:
                if (str.equals("setStatusBarStyle")) {
                    c2 = 30;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                try {
                    int rotation = iWebview.getActivity().getWindowManager().getDefaultDisplay().getRotation();
                    strWrapJsVar = JSUtil.wrapJsVar(rotation != 1 ? rotation != 2 ? rotation != 3 ? 0 : -90 : 180 : 90);
                    break;
                } catch (Exception unused) {
                    strWrapJsVar = JSUtil.wrapJsVar(0.0f);
                    break;
                }
            case 1:
                Window window = iAppObtainApp.getActivity().getWindow();
                window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 4866);
                iWebview.obtainApp().setHideNavBarState(true);
                return null;
            case 2:
                try {
                    JSONObject jSONObject = new JSONObject(strArr[0]);
                    SharedPreferences.Editor editorEdit = SP.getOrCreateBundle(iWebview.getContext(), "pdr").edit();
                    String strObtainAppId2 = iAppObtainApp.obtainAppId();
                    String strOptString = jSONObject.optString("image", null);
                    if (!TextUtils.isEmpty(strOptString)) {
                        String strConvert2AbsFullPath = iAppObtainApp.convert2AbsFullPath(iWebview.obtainFullUrl(), strOptString);
                        if (PdrUtil.isDeviceRootDir(strConvert2AbsFullPath)) {
                            DHFile.copyFile(strConvert2AbsFullPath, StringConst.STREAMAPP_KEY_ROOTPATH + "splash/" + iAppObtainApp.obtainAppId() + ".png", true, false);
                        }
                        editorEdit.putString(SP.UPDATE_SPLASH_IMG_PATH, strConvert2AbsFullPath);
                    }
                    if (!jSONObject.isNull(IApp.ConfigProperty.CONFIG_AUTOCLOSE)) {
                        editorEdit.putBoolean(strObtainAppId2 + SP.UPDATE_SPLASH_AUTOCLOSE, jSONObject.optBoolean(IApp.ConfigProperty.CONFIG_AUTOCLOSE));
                    }
                    if (!jSONObject.isNull(IApp.ConfigProperty.CONFIG_DELAY)) {
                        editorEdit.putInt(strObtainAppId2 + SP.UPDATE_SPLASH_DELAY, jSONObject.optInt(IApp.ConfigProperty.CONFIG_DELAY));
                    }
                    if (BaseInfo.isWap2AppAppid(strObtainAppId2)) {
                        if (!jSONObject.isNull(IApp.ConfigProperty.CONFIG_AUTOCLOSE_W2A)) {
                            editorEdit.putBoolean(strObtainAppId2 + SP.UPDATE_SPLASH_AUTOCLOSE_W2A, jSONObject.optBoolean(IApp.ConfigProperty.CONFIG_AUTOCLOSE_W2A));
                        }
                        if (!jSONObject.isNull(IApp.ConfigProperty.CONFIG_DELAY_W2A)) {
                            editorEdit.putInt(strObtainAppId2 + SP.UPDATE_SPLASH_DELAY_W2A, jSONObject.optInt(IApp.ConfigProperty.CONFIG_DELAY_W2A));
                        }
                    }
                    editorEdit.commit();
                    return null;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            case 3:
                AppRuntime.checkPrivacyComplianceAndPrompt(iWebview.getContext(), "Navigator-" + str);
                String str2 = strArr[0];
                String str3 = strArr[1];
                String strObtainAppName = iWebview.obtainApp().obtainAppName();
                try {
                    strObtainAppName = new JSONObject(str2).optString("name", strObtainAppName);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                b(iWebview.getContext(), iWebview, str3, strObtainAppName);
                return null;
            case 4:
                Logger.d(Logger.MAIN_TAG, "appid=" + strObtainAppId + " closeSplashscreen");
                TestUtil.print(TestUtil.START_STREAM_APP, "closeSplashscreen appid=" + strObtainAppId);
                Logger.i("download_manager", "javascript webapp task begin success appid=" + strObtainAppId + " closeSplashscreen");
                this.a.processEvent(IMgr.MgrType.WindowMgr, 11, iWebview.obtainFrameView());
                return null;
            case 5:
                strWrapJsVar = JSUtil.wrapJsVar(iAppObtainApp.obtainConfigProperty(AbsoluteConst.JSONKEY_STATUSBAR_MODE));
                break;
            case 6:
                strWrapJsVar = JSUtil.wrapJsVar(Logger.isOpen());
                break;
            case 7:
                Window window2 = iAppObtainApp.getActivity().getWindow();
                window2.getDecorView().setSystemUiVisibility(window2.getDecorView().getSystemUiVisibility() & (-515));
                iWebview.obtainApp().setHideNavBarState(false);
                return null;
            case '\b':
                AppRuntime.checkPrivacyComplianceAndPrompt(iWebview.getContext(), "Navigator-" + str);
                strWrapJsVar = JSUtil.wrapJsVar(EmulatorCheckUtil.getSingleInstance().emulatorCheck(iWebview.getContext()));
                break;
            case '\t':
                try {
                    iWebview.removeSessionCookie();
                    return null;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return null;
                }
            case '\n':
                strWrapJsVar = JSUtil.wrapJsVar(iAppObtainApp.obtainStatusBarMgr().checkImmersedStatusBar(iWebview.getActivity(), Boolean.valueOf(iAppObtainApp.obtainConfigProperty(AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED)).booleanValue()));
                break;
            case 11:
                strWrapJsVar = JSUtil.wrapJsVar(PdrUtil.toHexFromColor(iAppObtainApp.getActivity().getWindow().getStatusBarColor()));
                break;
            case '\f':
                iWebview.setCookie(strArr[0], strArr[1]);
                return null;
            case '\r':
                strWrapJsVar = JSUtil.wrapJsVar(!iAppObtainApp.obtainWebAppRootView().didCloseSplash());
                break;
            case 14:
                boolean z = Boolean.parseBoolean(iAppObtainApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_funSetUA));
                String strObtainConfigProperty = iWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_USER_AGENT);
                if (TextUtils.isEmpty(strObtainConfigProperty)) {
                    strObtainConfigProperty = "";
                }
                if (!z) {
                    boolean z2 = Boolean.parseBoolean(iAppObtainApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_CONCATENATE));
                    boolean z3 = Boolean.parseBoolean(iAppObtainApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_H5PLUS));
                    if (z2) {
                        strObtainConfigProperty = BaseInfo.sDefWebViewUserAgent + Operators.SPACE_STR + strObtainConfigProperty;
                    }
                    if (PdrUtil.isEmpty(strObtainConfigProperty)) {
                        strObtainConfigProperty = strObtainConfigProperty + BaseInfo.sDefWebViewUserAgent;
                    }
                    if (z3) {
                        strWrapJsVar = strObtainConfigProperty + DCWebView.UserAgentExtInfo;
                        break;
                    }
                }
                return strObtainConfigProperty;
            case 15:
                strWrapJsVar = iWebview.getCookie(strArr[0]);
                break;
            case 16:
                AppRuntime.checkPrivacyComplianceAndPrompt(iWebview.getContext(), "Navigator-" + str);
                PermissionUtil.usePermission(iWebview.getActivity(), IFeature.F_NAVIGATOR, "SHORTCUT", 2, new a(iAppObtainApp, iWebview, strArr, iAppObtainApp, strObtainAppId));
                return null;
            case 17:
                iAppObtainApp.setFullScreen(PdrUtil.parseBoolean(String.valueOf(strArr[0]), false, false));
                return null;
            case 18:
                strWrapJsVar = JSUtil.wrapJsVar(AppRuntime.getAppDarkMode(iWebview.getContext()) ? DCBlurDraweeView.DARK : DCBlurDraweeView.LIGHT);
                break;
            case 19:
                strWrapJsVar = JSUtil.wrapJsVar(PermissionUtil.checkPermission(iWebview, strArr));
                break;
            case 20:
                String str4 = strArr[0];
                String str5 = strArr[1];
                int requestCode = PermissionUtil.getRequestCode();
                String strConvertNativePermission = PermissionUtil.convertNativePermission(str4);
                iAppObtainApp.registerSysEventListener(new b(requestCode, iAppObtainApp, iWebview, strConvertNativePermission, str5), ISysEventListener.SysEventType.onRequestPermissionsResult);
                iAppObtainApp.requestPermissions(new String[]{strConvertNativePermission}, requestCode);
                return null;
            case 21:
                strWrapJsVar = JSUtil.wrapJsVar(iAppObtainApp.obtainAppStatus() == 2);
                break;
            case 22:
                strWrapJsVar = JSUtil.wrapJsVar(LoadAppUtils.getAppSignatureSHA1(iWebview.getContext()));
                break;
            case 23:
                strWrapJsVar = JSUtil.wrapJsVar(QueryNotchTool.hasNotchInScreen(iWebview.getActivity()));
                break;
            case 24:
                String str6 = strArr[0];
                if (TextUtils.isEmpty(str6)) {
                    return null;
                }
                try {
                    iStringToColor = Color.parseColor(str6);
                } catch (Exception unused2) {
                    iStringToColor = PdrUtil.stringToColor(str6);
                }
                iAppObtainApp.setConfigProperty(AbsoluteConst.JSONKEY_STATUSBAR_BC, strArr[0]);
                iAppObtainApp.obtainStatusBarMgr().setStatusBarColor(iAppObtainApp.getActivity(), iStringToColor);
                return null;
            case 25:
                try {
                    iWebview.removeAllCookie();
                    return null;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return null;
                }
            case 26:
                strWrapJsVar = JSUtil.wrapJsVar(iAppObtainApp.isFullScreen());
                break;
            case 27:
                DeviceInfo.updateStatusBarHeight(iWebview.getActivity());
                strWrapJsVar = JSUtil.wrapJsVar(DeviceInfo.sStatusBarHeight / iWebview.getScale());
                break;
            case 28:
                String str7 = strArr[0];
                String str8 = strArr[1];
                iAppObtainApp.setConfigProperty(IApp.ConfigProperty.CONFIG_USER_AGENT, str7);
                iAppObtainApp.setConfigProperty(IApp.ConfigProperty.CONFIG_funSetUA, AbsoluteConst.TRUE);
                iAppObtainApp.setConfigProperty(IApp.ConfigProperty.CONFIG_H5PLUS, str8);
                iWebview.setWebviewProperty(IWebview.USER_AGENT, str7);
                return null;
            case 29:
                Logger.setOpen(PdrUtil.parseBoolean(String.valueOf(strArr[0]), false, false));
                return null;
            case 30:
                String str9 = strArr[0];
                iAppObtainApp.setConfigProperty(AbsoluteConst.JSONKEY_STATUSBAR_MODE, str9);
                iAppObtainApp.obtainStatusBarMgr().setStatusBarMode(iAppObtainApp.getActivity(), str9);
                return null;
            default:
                return null;
        }
        return strWrapJsVar;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.a = absMgr;
    }

    class c implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ IWebview c;
        final /* synthetic */ String d;

        c(Context context, String str, IWebview iWebview, String str2) {
            this.a = context;
            this.b = str;
            this.c = iWebview;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            if (!ShortCutUtil.SHORT_CUT_EXISTING.equals(ShortCutUtil.requestShortCutForCommit(this.a, this.b))) {
                str = AbsoluteConst.FALSE;
            } else {
                str = AbsoluteConst.TRUE;
            }
            try {
                JSUtil.execCallback(this.c, this.d, new JSONObject(StringUtil.format(DOMException.JSON_SHORTCUT_SUCCESS_INFO, str)), JSUtil.OK, false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|(10:57|3|(2:53|5)|6|63|7|59|8|(1:10)|11)|(3:55|12|13)|65|28|(1:30)|(2:51|(4:32|61|33|34)(1:38))|(1:48)(2:41|42)|49|50|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d1, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009d A[Catch: Exception -> 0x00d1, TRY_LEAVE, TryCatch #7 {Exception -> 0x00d1, blocks: (B:28:0x0097, B:30:0x009d), top: B:65:0x0097 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d7 A[PHI: r7
  0x00d7: PHI (r7v5 android.graphics.Bitmap) = (r7v4 android.graphics.Bitmap), (r7v9 android.graphics.Bitmap), (r7v9 android.graphics.Bitmap) binds: [B:47:0x00d4, B:39:0x00bb, B:40:0x00bd] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(io.dcloud.common.DHInterface.IWebview r17, java.lang.String[] r18, io.dcloud.common.DHInterface.IApp r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.navigator.NavigatorUIFeatureImpl.a(io.dcloud.common.DHInterface.IWebview, java.lang.String[], io.dcloud.common.DHInterface.IApp, java.lang.String):boolean");
    }

    private String b(IApp iApp) {
        Intent intentObtainWebAppIntent = iApp.obtainWebAppIntent();
        if (intentObtainWebAppIntent == null) {
            return "";
        }
        return intentObtainWebAppIntent.getStringExtra(IntentConst.WEBAPP_ACTIVITY_APPICON);
    }

    private void a(Context context, IWebview iWebview, String str, String str2) {
        MessageHandler.postDelayed(new c(context, str2, iWebview, str), Build.VERSION.SDK_INT >= 25 ? 1500 : 500);
    }

    private Bitmap a(IApp iApp) {
        String strB = b(iApp);
        if (strB != null) {
            return BitmapFactory.decodeFile(strB);
        }
        return null;
    }

    private void a(IWebview iWebview, String str, Bitmap bitmap, String str2, String str3, JSONObject jSONObject, boolean z, boolean z2, String str4) {
        Intent intentObtainWebAppIntent;
        IApp iAppObtainApp = iWebview.obtainApp();
        String strObtainAppId = iAppObtainApp.obtainAppId();
        Activity activity = iWebview.getActivity();
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(iWebview.getContext(), "pdr");
        String strObtainAppName = PdrUtil.isEmpty(str) ? iAppObtainApp.obtainAppName() : str;
        boolean z3 = orCreateBundle.getBoolean(strObtainAppId + SP.K_CREATED_SHORTCUT, false);
        String stringExtra = (!TextUtils.isEmpty(str2) || (intentObtainWebAppIntent = iWebview.obtainApp().obtainWebAppIntent()) == null) ? str2 : intentObtainWebAppIntent.getStringExtra(IntentConst.WEBAPP_SHORT_CUT_CLASS_NAME);
        if (Build.VERSION.SDK_INT >= 25) {
            if ((!ShortCutUtil.hasShortcut(activity, strObtainAppName) || z) && ShortCutUtil.createShortcutToDeskTop(activity, strObtainAppId, strObtainAppName, bitmap, stringExtra, jSONObject, true) && !TextUtils.isEmpty(str3)) {
                ToastCompat.makeText(activity.getApplicationContext(), (CharSequence) str3, 1).show();
            }
        } else if (ShortcutCreateUtil.isDuplicateLauncher(activity)) {
            if (ShortCutUtil.createShortcutToDeskTop(activity, strObtainAppId, strObtainAppName, bitmap, stringExtra, jSONObject, true) && !TextUtils.isEmpty(str3) && ShortcutCreateUtil.needToast(activity)) {
                ToastCompat.makeText(activity.getApplicationContext(), (CharSequence) str3, 1).show();
            }
        } else if (!ShortCutUtil.hasShortcut(activity, strObtainAppName)) {
            if (z) {
                if (!TextUtils.isEmpty(str3) && ShortcutCreateUtil.needToast(activity)) {
                    ToastCompat.makeText(activity.getApplicationContext(), (CharSequence) str3, 1).show();
                }
                ShortCutUtil.createShortcutToDeskTop(activity, strObtainAppId, strObtainAppName, bitmap, stringExtra, jSONObject, true);
            } else {
                if (z3) {
                    return;
                }
                if (ShortCutUtil.createShortcutToDeskTop(activity, strObtainAppId, strObtainAppName, bitmap, stringExtra, jSONObject, true) && !TextUtils.isEmpty(str3) && ShortcutCreateUtil.needToast(activity)) {
                    ToastCompat.makeText(activity.getApplicationContext(), (CharSequence) str3, 1).show();
                }
            }
        }
        a(iWebview.getContext(), iWebview, str4, strObtainAppName);
    }
}
