package com.taobao.weex.common;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXUtils;
import io.dcloud.feature.uniapp.AbsSDKInstance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class WXModule implements IWXObject {
    public static final String ACTION_ACTIVITY_RESULT = "actionActivityResult";
    public static final String ACTION_REQUEST_PERMISSIONS_RESULT = "actionRequestPermissionsResult";
    public static final String GRANT_RESULTS = "grantResults";
    public static final String PERMISSIONS = "permissions";
    public static final String REQUEST_CODE = "requestCode";
    public static final String RESULT_CODE = "resultCode";
    private Map<String, List<String>> mEvents = new HashMap();
    private Map<String, Boolean> mKeepAlives = new HashMap();
    private String mModuleName;
    public AbsSDKInstance mUniSDKInstance;
    public WXSDKInstance mWXSDKInstance;

    @JSMethod
    public void addEventListener(String str, String str2, Map<String, Object> map) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.mKeepAlives.put(str2, Boolean.valueOf(map != null && map.size() > 0 && map.containsKey("once") && WXUtils.getBoolean(map.get("once"), Boolean.FALSE).booleanValue()));
        if (this.mEvents.get(str) == null) {
            this.mEvents.put(str, new ArrayList());
        }
        this.mEvents.get(str).add(str2);
    }

    protected final WXComponent findComponent(String str) {
        if (this.mWXSDKInstance == null || str == null) {
            return null;
        }
        return WXSDKManager.getInstance().getWXRenderManager().getWXComponent(this.mWXSDKInstance.getInstanceId(), str);
    }

    public List<String> getEventCallbacks(String str) {
        return this.mEvents.get(str);
    }

    public String getModuleName() {
        return this.mModuleName;
    }

    public boolean isOnce(String str) {
        return this.mKeepAlives.get(str).booleanValue();
    }

    public boolean onActivityBack() {
        return false;
    }

    public void onActivityCreate() {
    }

    public void onActivityDestroy() {
    }

    public void onActivityPause() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onActivityResume() {
    }

    public void onActivityStart() {
    }

    public void onActivityStop() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    @JSMethod
    public void removeAllEventListeners(String str) {
        if (this.mEvents.containsKey(str)) {
            Iterator<String> it = this.mEvents.remove(str).iterator();
            while (it.hasNext()) {
                this.mKeepAlives.remove(it.next());
            }
        }
    }

    public void setModuleName(String str) {
        this.mModuleName = str;
    }
}
