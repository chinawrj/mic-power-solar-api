package io.dcloud.p;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.internal.sdk.SDK;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class p3 {
    Context a;
    k0 b;
    private long c = 0;

    public p3(Context context, ICore.ICoreStatusListener iCoreStatusListener) {
        this.b = null;
        this.a = context;
        this.b = k0.a(context, iCoreStatusListener);
    }

    public void a(Activity activity, Bundle bundle, SDK.IntegratedMode integratedMode, IOnCreateSplashView iOnCreateSplashView) throws JSONException, IOException, ParseException {
        Bundle extras;
        if (iOnCreateSplashView != null && integratedMode != SDK.IntegratedMode.WEBAPP && integratedMode != SDK.IntegratedMode.WEBVIEW) {
            iOnCreateSplashView.onCreateSplash(null);
        }
        this.b.a(activity, bundle, integratedMode);
        Intent intent = activity.getIntent();
        String string = (intent == null || (extras = intent.getExtras()) == null) ? null : extras.getString("appid");
        if (PdrUtil.isEmpty(string)) {
            string = BaseInfo.sDefaultBootApp;
        }
        if (SDK.isUniMPSDK()) {
            a(activity, (Intent) null, (IOnCreateSplashView) null, string);
        } else if (a(intent, string) && BaseInfo.sRuntimeMode == null) {
            a(activity, activity.getIntent(), (IOnCreateSplashView) null, string);
        }
    }

    public void b(Activity activity) {
        this.c = System.currentTimeMillis();
        Logger.i("onResume resumeTime=" + this.c);
        this.b.c(activity);
    }

    public boolean c(Activity activity) {
        Logger.i("onStop");
        if (!this.b.d(activity)) {
            return false;
        }
        this.b = null;
        return true;
    }

    public Context b() {
        return this.a;
    }

    boolean a(Intent intent, String str) {
        boolean booleanExtra = intent.getBooleanExtra(IntentConst.WEBAPP_ACTIVITY_HAS_STREAM_SPLASH, false);
        if (booleanExtra) {
            if (TextUtils.isEmpty(str)) {
                str = intent.getStringExtra("appid");
            }
            String str2 = BaseInfo.sCacheFsAppsPath + str + "/www/";
            if (new File(str2).exists()) {
                File file = new File(str2 + "/manifest.json");
                if (file.exists() && file.length() > 0) {
                    return true;
                }
            }
            if (intent.hasExtra(IntentConst.DIRECT_PAGE) && BaseInfo.isWap2AppAppid(str)) {
                return true;
            }
        }
        return !booleanExtra;
    }

    public IApp a(Activity activity, Intent intent, IOnCreateSplashView iOnCreateSplashView, String str) throws JSONException {
        String strObtainArgs = IntentConst.obtainArgs(intent, str);
        Logger.i("onStart appid=" + str + ";intentArgs=" + strObtainArgs);
        if (iOnCreateSplashView == null) {
            this.b.a(activity, str, strObtainArgs);
            return null;
        }
        return this.b.a(activity, str, strObtainArgs, iOnCreateSplashView);
    }

    public boolean a(Activity activity, ISysEventListener.SysEventType sysEventType, Object obj) {
        if (!a(activity.getIntent(), (String) null) && !"all".equalsIgnoreCase(BaseInfo.sSplashExitCondition)) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.c;
        if (jCurrentTimeMillis - j > 500) {
            return this.b.onActivityExecute(activity, sysEventType, obj);
        }
        return j > 0 && sysEventType == ISysEventListener.SysEventType.onKeyUp;
    }

    public void a(Activity activity) {
        Logger.i("onPause");
        this.b.b(activity);
        this.c = 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(Activity activity, Intent intent) throws JSONException {
        Bundle extras;
        String string = (intent == null || (extras = intent.getExtras()) == null) ? null : extras.getString("appid");
        String strObtainArgs = IntentConst.obtainArgs(intent, string);
        if (!PdrUtil.isEmpty(string)) {
            this.b.a(activity, string, strObtainArgs, activity instanceof IOnCreateSplashView ? (IOnCreateSplashView) activity : null, intent.getBooleanExtra(IntentConst.EXE_NEW_INTENT, true));
        } else {
            this.b.onActivityExecute(activity, ISysEventListener.SysEventType.onNewIntent, strObtainArgs);
        }
    }

    public void a(Activity activity, int i) {
        Logger.i(Logger.LAYOUT_TAG, "onConfigurationChanged pConfig=" + i);
        this.b.onActivityExecute(activity, ISysEventListener.SysEventType.onConfigurationChanged, 1);
    }

    public ICore a() {
        return this.b;
    }
}
