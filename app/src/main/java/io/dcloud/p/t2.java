package io.dcloud.p;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.sdk.poly.base.utils.PrivacyManager;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes3.dex */
public abstract class t2 {
    private static String a = "";
    private static boolean b = false;

    public static String a(Context context) {
        if (context == null) {
            return a;
        }
        if (!PrivacyManager.getInstance().d()) {
            return a;
        }
        if (!b0.a().a(context)) {
            return a;
        }
        if (b) {
            return a;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        String strA = connectivityManager.getNetworkInfo(0).isConnected() ? a() : connectivityManager.getNetworkInfo(1).isConnected() ? a(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress()) : null;
        a = strA;
        b = true;
        return strA;
    }

    public static String a() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                Iterator it2 = Collections.list(((NetworkInterface) it.next()).getInetAddresses()).iterator();
                while (it2.hasNext()) {
                    InetAddress inetAddress = (InetAddress) it2.next();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    public static String a(int i) {
        return (i & 255) + Operators.DOT_STR + ((i >> 8) & 255) + Operators.DOT_STR + ((i >> 16) & 255) + Operators.DOT_STR + ((i >> 24) & 255);
    }
}
