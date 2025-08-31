package io.dcloud.common.adapter.util;

import android.text.TextUtils;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/* loaded from: classes3.dex */
public class DCloudTrustManager {
    private DCloudTrustManager() {
    }

    public static SecureRandom createSecureRandom() {
        return new SecureRandom();
    }

    public static X509HostnameVerifier getHostnameVerifier(boolean z) {
        return (z || !(PdrUtil.isEquals(BaseInfo.untrustedca, "refuse") || PdrUtil.isEquals(BaseInfo.untrustedca, "warning"))) ? SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER : SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    }

    public static javax.net.ssl.SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        return getSSLSocketFactory("TLSv1");
    }

    public static javax.net.ssl.SSLSocketFactory getSSLSocketFactory(String str) throws NoSuchAlgorithmException, KeyManagementException {
        Object objNewInstance;
        if (DCLoudApplicationImpl.self().getContext() != null) {
            String str2 = DCLoudApplicationImpl.self().getContext().getPackageName() + ".CustomTrustMgr";
            if (PlatformUtil.checkClass(str2) && (objNewInstance = PlatformUtil.newInstance(str2, null, null)) != null) {
                if (TextUtils.isEmpty(str)) {
                    str = "TLSv1";
                }
                SSLContext sSLContext = SSLContext.getInstance(str);
                sSLContext.init(null, new TrustManager[]{(TrustManager) objNewInstance}, createSecureRandom());
                return sSLContext.getSocketFactory();
            }
        }
        return null;
    }
}
