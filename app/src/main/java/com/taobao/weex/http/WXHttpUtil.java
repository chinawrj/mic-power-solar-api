package com.taobao.weex.http;

import android.content.Context;
import android.text.TextUtils;
import dc.squareup.HttpConstants;
import io.dcloud.common.util.BaseInfo;
import java.util.Map;

/* loaded from: classes.dex */
public class WXHttpUtil {
    public static final String KEY_USER_AGENT = "user-agent";

    public static String assembleUserAgent() {
        String defaultUA = HttpConstants.getDefaultUA();
        if (!TextUtils.isEmpty(defaultUA)) {
            return defaultUA;
        }
        HttpConstants.setUA(BaseInfo.sDefWebViewUserAgent);
        return HttpConstants.getDefaultUA();
    }

    public static String assembleUserAgent(Context context, Map<String, String> map) {
        return assembleUserAgent();
    }
}
