package io.dcloud.feature.gg.dcloud.dcmgr;

import android.app.Activity;
import android.os.SystemClock;
import android.widget.FrameLayout;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.p.u;
import io.dcloud.sdk.core.entry.SplashAOLConfig;
import io.dcloud.sdk.core.v3.sp.DCSplashAOLLoadListener;
import io.src.dcloud.adapter.DCloudAdapterUtil;

/* loaded from: classes3.dex */
public class SIAolManager {
    private static SIAolManager instance;
    private long loadTime = 0;
    u wrapper;

    public static SIAolManager getInstance() {
        if (instance == null) {
            synchronized (SIAolManager.class) {
                if (instance == null) {
                    SIAolManager sIAolManager = new SIAolManager();
                    instance = sIAolManager;
                    return sIAolManager;
                }
            }
        }
        return instance;
    }

    public void load(Activity activity) {
        this.wrapper = new u(activity);
        this.wrapper.a(new SplashAOLConfig.Builder().width(0).height(0).build(), (DCSplashAOLLoadListener) null, true);
        this.loadTime = SystemClock.elapsedRealtime();
    }

    public void show(Activity activity) {
        IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(activity);
        if (iActivityHandler == null || this.wrapper == null) {
            return;
        }
        FrameLayout frameLayoutObtainActivityContentView = iActivityHandler.obtainActivityContentView();
        if (this.wrapper.a(this.loadTime)) {
            this.wrapper.a(activity, "", frameLayoutObtainActivityContentView);
        }
    }
}
