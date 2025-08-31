package io.dcloud.feature.pdr;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.taobao.weex.performance.WXInstanceApm;
import dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0;
import io.dcloud.PandoraEntry;
import io.dcloud.PdrR;
import io.dcloud.WebAppActivity;
import io.dcloud.WebviewActivity;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.message.ActionBus;
import io.dcloud.common.DHInterface.message.action.BadgeSyncAction;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.StringConst;
import io.dcloud.common.ui.Info.AndroidPrivacyResponse;
import io.dcloud.common.ui.PrivacyManager;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class RuntimeFeatureImpl implements IFeature, MessageHandler.IMessages {
    final String a = PandoraEntry.class.getName();
    AbsMgr b = null;

    class a extends Thread {
        final /* synthetic */ String[] a;
        final /* synthetic */ String b;
        final /* synthetic */ IWebview c;

        a(String[] strArr, String str, IWebview iWebview) {
            this.a = strArr;
            this.b = str;
            this.c = iWebview;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String[] strArr = this.a;
            String str = strArr[1];
            Object[] objArr = (Object[]) RuntimeFeatureImpl.this.b.processEvent(IMgr.MgrType.AppMgr, 4, new Object[]{this.b, !PdrUtil.isEmpty(strArr[2]) ? JSONUtil.createJSONObject(this.a[2]) : null, this.c});
            boolean zBooleanValue = Boolean.valueOf(String.valueOf(objArr[0])).booleanValue();
            String strValueOf = String.valueOf(objArr[1]);
            if (zBooleanValue) {
                Deprecated_JSUtil.execCallback(this.c, str, strValueOf, JSUtil.ERROR, true, false);
            } else {
                Deprecated_JSUtil.execCallback(this.c, str, strValueOf, JSUtil.OK, true, false);
            }
        }
    }

    class b implements PrivacyManager.b {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;
        final /* synthetic */ WebAppActivity c;

        b(IWebview iWebview, String str, WebAppActivity webAppActivity) {
            this.a = iWebview;
            this.b = str;
            this.c = webAppActivity;
        }

        @Override // io.dcloud.common.ui.PrivacyManager.b
        public void a(String str) {
            JSUtil.execCallback(this.a, this.b, new JSONObject(), JSUtil.ERROR, false);
        }

        @Override // io.dcloud.common.ui.PrivacyManager.b
        public void b(AndroidPrivacyResponse androidPrivacyResponse) throws JSONException {
            if (!TextUtils.isEmpty(androidPrivacyResponse.second.message)) {
                PrivacyManager.getInstance().showPrivacyDialog(this.c, new a(), true, true);
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", -1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSUtil.execCallback(this.a, this.b, jSONObject, JSUtil.OK, false);
        }

        class a implements PrivacyManager.b {
            a() {
            }

            @Override // io.dcloud.common.ui.PrivacyManager.b
            public void a(String str) {
                JSONObject jSONObject = new JSONObject();
                b bVar = b.this;
                JSUtil.execCallback(bVar.a, bVar.b, jSONObject, JSUtil.ERROR, false);
            }

            @Override // io.dcloud.common.ui.PrivacyManager.b
            public void b(AndroidPrivacyResponse androidPrivacyResponse) throws JSONException {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("code", -1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                b bVar = b.this;
                JSUtil.execCallback(bVar.a, bVar.b, jSONObject, JSUtil.OK, false);
            }

            @Override // io.dcloud.common.ui.PrivacyManager.b
            public void a() throws JSONException {
                b.this.c.onPrivacySureAction();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("code", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                b bVar = b.this;
                JSUtil.execCallback(bVar.a, bVar.b, jSONObject, JSUtil.OK, false);
            }

            @Override // io.dcloud.common.ui.PrivacyManager.b
            public void a(AndroidPrivacyResponse androidPrivacyResponse) throws JSONException {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("code", 2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                b bVar = b.this;
                JSUtil.execCallback(bVar.a, bVar.b, jSONObject, JSUtil.OK, false);
            }
        }

        @Override // io.dcloud.common.ui.PrivacyManager.b
        public void a() throws JSONException {
            this.c.onPrivacySureAction();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", 1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSUtil.execCallback(this.a, this.b, jSONObject, JSUtil.OK, false);
        }

        @Override // io.dcloud.common.ui.PrivacyManager.b
        public void a(AndroidPrivacyResponse androidPrivacyResponse) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", 2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSUtil.execCallback(this.a, this.b, jSONObject, JSUtil.OK, false);
        }
    }

    class c implements ICallBack {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        c(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (i == 1) {
                return null;
            }
            Deprecated_JSUtil.excCallbackError(this.a, this.b, String.valueOf(obj), true);
            return null;
        }
    }

    class d implements ICallBack {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        d(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (i == 1) {
                Deprecated_JSUtil.execCallback(this.a, this.b, "{}", JSUtil.OK, true, false);
                return null;
            }
            Deprecated_JSUtil.excCallbackError(this.a, this.b, String.valueOf(obj), true);
            return null;
        }
    }

    private void a(IWebview iWebview, String str, String str2) throws Throwable {
        String strValueOf = (TextUtils.isEmpty(str) || str.equals(WXInstanceApm.VALUE_ERROR_CODE_DEFAULT)) ? "" : String.valueOf(Math.max(0, Integer.valueOf(str).intValue()));
        try {
            String str3 = Build.MANUFACTURER;
            if (str3.equalsIgnoreCase("Xiaomi")) {
                b(iWebview, strValueOf, str2);
                return;
            }
            if (str3.equalsIgnoreCase("samsung")) {
                c(iWebview, strValueOf);
                return;
            }
            Locale locale = Locale.ENGLISH;
            if (str3.toLowerCase(locale).contains("sony")) {
                d(iWebview, strValueOf);
                return;
            }
            if (str3.toLowerCase(locale).contains("huawei")) {
                e(iWebview, strValueOf);
                return;
            }
            if (str3.toLowerCase(locale).contains("vivo")) {
                g(iWebview, strValueOf);
            } else if (str3.toLowerCase(locale).contains("oppo")) {
                f(iWebview, strValueOf);
            } else if (str3.toLowerCase(locale).contains("honor")) {
                b(iWebview, strValueOf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(IWebview iWebview, String str, String str2) throws Throwable {
        JSONObject jSONObject;
        Notification.Builder builder;
        try {
            jSONObject = new JSONObject(str2);
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        NotificationManager notificationManager = (NotificationManager) iWebview.getContext().getSystemService("notification");
        boolean z = false;
        Notification notificationBuild = null;
        try {
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    a(iWebview.getContext());
                    Okio$$ExternalSyntheticApiModelOutline0.m$3();
                    builder = Okio$$ExternalSyntheticApiModelOutline0.m(iWebview.getContext(), "LOCAL_BADGE_NUM");
                } else {
                    builder = new Notification.Builder(iWebview.getContext());
                }
                builder.setContentText(PdrUtil.isEmpty(jSONObject.optString(UriUtil.LOCAL_CONTENT_SCHEME)) ? StringUtil.format(iWebview.getContext().getString(R.string.dcloud_common_msg_unread_prompt), str) : jSONObject.optString(UriUtil.LOCAL_CONTENT_SCHEME));
                builder.setAutoCancel(true);
                int i = PdrR.getInt(iWebview.getContext(), "drawable", "push");
                if (i <= 0) {
                    builder.setSmallIcon(iWebview.getContext().getApplicationInfo().icon);
                } else {
                    builder.setSmallIcon(i);
                }
                builder.setDefaults(4);
                String packageName = iWebview.getActivity().getPackageName();
                PackageManager packageManager = iWebview.getActivity().getPackageManager();
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
                builder.setContentTitle(PdrUtil.isEmpty(jSONObject.optString(AbsoluteConst.JSON_KEY_TITLE)) ? AndroidResources.mApplicationInfo.applicationInfo.loadLabel(packageManager) : jSONObject.optString(AbsoluteConst.JSON_KEY_TITLE));
                builder.setContentIntent(PendingIntent.getActivity(iWebview.getActivity(), 10019, launchIntentForPackage, 1073741824));
                builder.setAutoCancel(true);
                notificationBuild = builder.build();
                notificationBuild.flags = 16;
                Object obj = notificationBuild.getClass().getDeclaredField("extraNotification").get(notificationBuild);
                obj.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(obj, Integer.valueOf(Integer.parseInt(str)));
                notificationManager.notify(101010, notificationBuild);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
                    intent.putExtra("android.intent.extra.update_application_component_name", this.a);
                    intent.putExtra("android.intent.extra.update_application_message_text", str);
                    iWebview.getContext().sendBroadcast(intent);
                } catch (Throwable th) {
                    th = th;
                    if (notificationBuild != null) {
                        notificationManager.notify(101010, notificationBuild);
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            z = true;
            if (notificationBuild != null && z) {
                notificationManager.notify(101010, notificationBuild);
            }
            throw th;
        }
    }

    private void c(IWebview iWebview, String str) {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", Integer.parseInt(str));
        intent.putExtra("badge_count_package_name", iWebview.getContext().getPackageName());
        intent.putExtra("badge_count_class_name", this.a);
        iWebview.getContext().sendBroadcast(intent);
    }

    private void d(IWebview iWebview, String str) {
        if (iWebview.getContext().getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) == null) {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", str);
            intent.putExtra("badge_count_package_name", iWebview.getContext().getPackageName());
            intent.putExtra("badge_count_class_name", this.a);
            iWebview.getActivity().sendBroadcast(intent);
            return;
        }
        boolean z = Integer.parseInt(str) != 0;
        Intent intent2 = new Intent();
        intent2.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        intent2.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", z);
        intent2.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", this.a);
        intent2.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", str);
        intent2.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", iWebview.getContext().getPackageName());
        iWebview.getContext().sendBroadcast(intent2);
    }

    private void e(IWebview iWebview, String str) {
        int i = !str.equals("") ? Integer.parseInt(str) : 0;
        int i2 = i >= 0 ? i : 0;
        Bundle bundle = new Bundle();
        bundle.putString("package", iWebview.getContext().getPackageName());
        bundle.putString("class", iWebview.getContext().getPackageManager().getLaunchIntentForPackage(iWebview.getContext().getPackageName()).getComponent().getClassName());
        bundle.putInt("badgenumber", i2);
        iWebview.getContext().getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
        ActionBus.getInstance().sendToBus(BadgeSyncAction.obtain(BadgeSyncAction.ENUM_ACTION_TYPE.SYNC_NUM).setSyncNum(i2));
    }

    private void f(IWebview iWebview, String str) throws NumberFormatException {
        int i = Integer.parseInt(str);
        if (i == 0) {
            i = -1;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("app_badge_count", i);
        iWebview.getContext().getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
    }

    private void g(IWebview iWebview, String str) {
        try {
            Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            intent.putExtra("packageName", iWebview.getContext().getPackageName());
            intent.putExtra("className", iWebview.getContext().getPackageManager().getLaunchIntentForPackage(iWebview.getContext().getPackageName()).getComponent().getClassName());
            intent.putExtra("notificationNum", Integer.parseInt(str));
            iWebview.getContext().sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0152  */
    @Override // io.dcloud.common.DHInterface.IFeature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r17, java.lang.String r18, java.lang.String[] r19) throws org.json.JSONException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.pdr.RuntimeFeatureImpl.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.b = absMgr;
    }

    private void a(IWebview iWebview, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Intent intent = new Intent();
            intent.setClass(iWebview.getActivity(), WebviewActivity.class);
            intent.putExtra("url", str);
            intent.setData(Uri.parse(str));
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            iWebview.getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(String str, IApp iApp) throws JSONException {
        if (TextUtils.isEmpty(str) || !BaseInfo.ISAMU) {
            return;
        }
        int length = str.length();
        String str2 = StringConst.POINT_APP_EN;
        if ((length - str.indexOf(str2)) - str2.length() == 0 || (length - str.indexOf(".wgt")) - 4 == 0 || (length - str.indexOf(".wgtu")) - 5 == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "install");
                jSONObject.put("file", str);
                jSONObject.put("appid", iApp.obtainOriginalAppId());
                jSONObject.put("version", iApp.obtainAppVersionName());
                Log.i(AbsoluteConst.HBUILDER_TAG, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void a(Context context) {
        if (Build.VERSION.SDK_INT < 26 || !Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        notificationManager.createNotificationChannelGroup(Okio$$ExternalSyntheticApiModelOutline0.m("LOCAL_BADGE_SETTING", "badge"));
        Okio$$ExternalSyntheticApiModelOutline0.m$2();
        NotificationChannel notificationChannelM = Okio$$ExternalSyntheticApiModelOutline0.m("LOCAL_BADGE_NUM", context.getString(R.string.dcloud_nf_desktop_icon_corner), 3);
        notificationChannelM.enableLights(true);
        notificationChannelM.setShowBadge(true);
        notificationManager.createNotificationChannel(notificationChannelM);
    }

    private void b(IWebview iWebview, String str) {
        int i = !str.equals("") ? Integer.parseInt(str) : 0;
        int i2 = i >= 0 ? i : 0;
        Bundle bundle = new Bundle();
        bundle.putString("package", iWebview.getContext().getPackageName());
        bundle.putString("class", iWebview.getContext().getPackageManager().getLaunchIntentForPackage(iWebview.getContext().getPackageName()).getComponent().getClassName());
        bundle.putInt("badgenumber", i2);
        iWebview.getContext().getContentResolver().call(Uri.parse("content://com.hihonor.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
        ActionBus.getInstance().sendToBus(BadgeSyncAction.obtain(BadgeSyncAction.ENUM_ACTION_TYPE.SYNC_NUM).setSyncNum(i2));
    }

    private void b(String str, IApp iApp) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "openurl");
            jSONObject.put("url", str);
            jSONObject.put("appid", iApp.obtainOriginalAppId());
            jSONObject.put("version", iApp.obtainAppVersionName());
            Log.i(AbsoluteConst.HBUILDER_TAG, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:118:0x0257. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0030  */
    @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(java.lang.Object r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1006
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.pdr.RuntimeFeatureImpl.execute(java.lang.Object):void");
    }
}
