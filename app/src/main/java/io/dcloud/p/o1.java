package io.dcloud.p;

import android.content.Context;
import android.util.Log;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.js.geolocation.GeoManagerBase;

/* loaded from: classes3.dex */
public class o1 {
    AbsMgr a;
    GeoManagerBase b = null;
    GeoManagerBase c = null;
    GeoManagerBase d = null;
    boolean e = false;

    public o1(AbsMgr absMgr) {
        this.a = absMgr;
    }

    private GeoManagerBase a(String str) {
        GeoManagerBase geoManagerBase;
        try {
            return (!str.equals("io.dcloud.js.geolocation.amap.AMapGeoManager") || (geoManagerBase = (GeoManagerBase) PlatformUtil.invokeMethod(str, "getInstance", null, new Class[]{Context.class}, new Object[]{this.a.getContext()})) == null) ? (GeoManagerBase) Class.forName(str).getConstructor(Context.class).newInstance(this.a.getContext()) : geoManagerBase;
        } catch (Exception unused) {
            Log.w("geoLoaction", str + " exception");
            return null;
        }
    }

    public String a(IWebview iWebview, String str, String[] strArr) {
        GeoManagerBase geoManagerBaseA;
        if ("clearWatch".equals(str)) {
            GeoManagerBase geoManagerBase = this.d;
            if (geoManagerBase != null && geoManagerBase.hasKey(strArr[0])) {
                geoManagerBaseA = this.d;
            } else {
                GeoManagerBase geoManagerBase2 = this.c;
                if (geoManagerBase2 != null && geoManagerBase2.hasKey(strArr[0])) {
                    geoManagerBaseA = this.c;
                } else {
                    GeoManagerBase geoManagerBase3 = this.b;
                    geoManagerBaseA = (geoManagerBase3 == null || !geoManagerBase3.hasKey(strArr[0])) ? null : this.b;
                }
            }
        } else {
            geoManagerBaseA = a(iWebview, strArr[4], strArr[3], strArr[0]);
        }
        if (geoManagerBaseA != null) {
            geoManagerBaseA.execute(iWebview, str, strArr);
        }
        return null;
    }

    private GeoManagerBase a(IWebview iWebview, String str, String str2, String str3) {
        GeoManagerBase geoManagerBaseA;
        if (PdrUtil.isEmpty(str2)) {
            str2 = "wgs84";
        }
        if (PdrUtil.isEmpty(str)) {
            if (PdrUtil.isEquals(str2, "wgs84")) {
                geoManagerBaseA = this.d;
                if (geoManagerBaseA == null) {
                    geoManagerBaseA = a("io.dcloud.js.geolocation.system.LocalGeoManager");
                }
                this.d = geoManagerBaseA;
            } else if (PdrUtil.isEquals(str2, "gcj02")) {
                geoManagerBaseA = this.c;
                if (geoManagerBaseA == null) {
                    geoManagerBaseA = a("io.dcloud.js.geolocation.amap.AMapGeoManager");
                }
                this.c = geoManagerBaseA;
                if (geoManagerBaseA == null) {
                    if (geoManagerBaseA == null) {
                        geoManagerBaseA = a("io.dcloud.js.geolocation.baidu.BaiduGeoManager");
                    }
                    this.c = geoManagerBaseA;
                }
            } else if (PdrUtil.isEquals(str2, "bd09") || PdrUtil.isEquals(str2, "bd09ll") || PdrUtil.isEquals(str2, "gcj02")) {
                geoManagerBaseA = this.c;
                if (geoManagerBaseA == null) {
                    geoManagerBaseA = a("io.dcloud.js.geolocation.baidu.BaiduGeoManager");
                }
                this.c = geoManagerBaseA;
            } else {
                geoManagerBaseA = null;
            }
            if (geoManagerBaseA == null) {
                Deprecated_JSUtil.execCallback(iWebview, str3, DOMException.toJSON(18, "not support " + str2), JSUtil.ERROR, true, false);
            }
        } else {
            if (PdrUtil.isEquals("baidu", str)) {
                geoManagerBaseA = this.b;
                if (geoManagerBaseA == null) {
                    geoManagerBaseA = a("io.dcloud.js.geolocation.baidu.BaiduGeoManager");
                }
                this.b = geoManagerBaseA;
            } else if (PdrUtil.isEquals("amap", str)) {
                geoManagerBaseA = this.c;
                if (geoManagerBaseA == null) {
                    geoManagerBaseA = a("io.dcloud.js.geolocation.amap.AMapGeoManager");
                }
                this.c = geoManagerBaseA;
            } else {
                geoManagerBaseA = this.d;
                if (geoManagerBaseA == null) {
                    geoManagerBaseA = a("io.dcloud.js.geolocation.system.LocalGeoManager");
                }
                this.d = geoManagerBaseA;
            }
            if (geoManagerBaseA == null) {
                Deprecated_JSUtil.execCallback(iWebview, str3, DOMException.toJSON(17, DOMException.MSG_GEOLOCATION_PROVIDER_ERROR), JSUtil.ERROR, true, false);
            }
        }
        return geoManagerBaseA;
    }

    public void a() {
        GeoManagerBase geoManagerBase = this.b;
        if (geoManagerBase != null) {
            geoManagerBase.onDestroy();
        }
        GeoManagerBase geoManagerBase2 = this.d;
        if (geoManagerBase2 != null) {
            geoManagerBase2.onDestroy();
        }
        GeoManagerBase geoManagerBase3 = this.c;
        if (geoManagerBase3 != null) {
            geoManagerBase3.onDestroy();
        }
    }
}
