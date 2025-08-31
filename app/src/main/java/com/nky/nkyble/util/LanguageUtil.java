package com.nky.nkyble.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import uni.dcloud.io.uniplugin_module.R;

/* loaded from: classes.dex */
public class LanguageUtil {
    private static final String TAG = "LanguageUtil";
    public static HashMap<String, Locale> supportLanguage = new HashMap<String, Locale>() { // from class: com.nky.nkyble.util.LanguageUtil.1
        {
            put("default", LanguageUtil.getSystemLanguage());
            put("zh-CN", Locale.SIMPLIFIED_CHINESE);
            put("en-US", Locale.ENGLISH);
            put("zh-HK", Locale.TRADITIONAL_CHINESE);
            put("de-DE", Locale.GERMANY);
            put("it-IT", Locale.ITALY);
            put("uk-UK", new Locale("uk", "UA"));
            put("fr-FR", Locale.FRANCE);
        }
    };

    public static Context getNewLocalContext(Context context) {
        try {
            Context contextAttachBaseContext = attachBaseContext(context);
            final Configuration configuration = contextAttachBaseContext.getResources().getConfiguration();
            return new ContextThemeWrapper(contextAttachBaseContext, R.style.AppTheme_Transparent) { // from class: com.nky.nkyble.util.LanguageUtil.2
                @Override // android.view.ContextThemeWrapper
                public void applyOverrideConfiguration(Configuration configuration2) {
                    if (configuration2 != null) {
                        configuration2.setTo(configuration);
                    }
                    super.applyOverrideConfiguration(configuration2);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
            return context;
        }
    }

    public static Context attachBaseContext(Context context) {
        String appLanguage = AppPrefsUtils.getAppLanguage();
        Log.d(TAG, "attachBaseContext: " + Build.VERSION.SDK_INT);
        return Build.VERSION.SDK_INT >= 24 ? updateResources(context, appLanguage) : context;
    }

    private static Context updateResources(Context context, String str) {
        Configuration configuration = context.getResources().getConfiguration();
        Locale languageLocale = getLanguageLocale(str);
        Okio$$ExternalSyntheticApiModelOutline0.m263m();
        configuration.setLocales(Okio$$ExternalSyntheticApiModelOutline0.m(new Locale[]{languageLocale}));
        return context.createConfigurationContext(configuration);
    }

    private static Locale getSystemLocal() {
        if (Build.VERSION.SDK_INT >= 24) {
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        }
        return Locale.getDefault();
    }

    private static Locale getLanguageLocale(String str) {
        supportLanguage.put("default", getSystemLanguage());
        if (supportLanguage.containsKey(str)) {
            return supportLanguage.get(str);
        }
        Locale systemLocal = getSystemLocal();
        Iterator<String> it = supportLanguage.keySet().iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(supportLanguage.get(it.next()).getLanguage(), systemLocal.getLanguage())) {
                return systemLocal;
            }
        }
        return Locale.ENGLISH;
    }

    public static Locale getSystemLanguage() {
        if (Build.VERSION.SDK_INT >= 24) {
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        }
        return Resources.getSystem().getConfiguration().locale;
    }
}
