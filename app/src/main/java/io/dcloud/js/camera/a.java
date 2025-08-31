package io.dcloud.js.camera;

import android.hardware.Camera;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class a {
    protected static int e = 501;
    protected static int f = 502;
    protected static int g = 5011;
    List a = null;
    List b = null;
    List c = null;
    int d;

    /* renamed from: io.dcloud.js.camera.a$a, reason: collision with other inner class name */
    static class C0066a {
        String a;
        String b;
        String c;
        int d;
        boolean e = true;
        int f = 0;
        JSONObject g = null;
        boolean h = false;

        C0066a() {
        }

        public String a() {
            return this.a;
        }

        public int b() {
            return this.f;
        }
    }

    a(int i) {
        this.d = i;
    }

    private String[] d() {
        List list = this.b;
        String[] strArrA = list != null ? a(list) : null;
        return strArrA == null ? new String[]{"['jpg']", "['mp4']"} : strArrA;
    }

    protected String a() {
        String[] strArrD = d();
        return StringUtil.format("(function(){return{supportedImageResolutions : %s,supportedVideoResolutions : %s,supportedImageFormats : %s,supportedVideoFormats : %s};})();", c(), e(), strArrD[0], strArrD[1]);
    }

    public void b() {
        Camera cameraOpen;
        try {
            if (this.d != 2 || DeviceInfo.sDeviceSdkVer < 9) {
                cameraOpen = null;
            } else {
                for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == 1) {
                        cameraOpen = Camera.open(i);
                        break;
                    }
                }
                cameraOpen = null;
            }
            if (cameraOpen == null) {
                cameraOpen = Camera.open();
            }
            if (DeviceInfo.sDeviceSdkVer >= 11) {
                this.a = cameraOpen.getParameters().getSupportedVideoSizes();
            }
            this.c = cameraOpen.getParameters().getSupportedPictureSizes();
            if (DeviceInfo.sDeviceSdkVer >= 8) {
                this.b = cameraOpen.getParameters().getSupportedPictureFormats();
            }
            cameraOpen.release();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String c() {
        List list = this.c;
        return list != null ? b(list) : "[]";
    }

    private String e() {
        List list = this.a;
        return (list == null || DeviceInfo.sDeviceSdkVer < 11) ? "[]" : b(list);
    }

    private String[] a(List list) {
        return new String[]{"['jpg']", "['mp4']"};
    }

    static C0066a a(String str, boolean z) {
        JSONObject jSONObject;
        C0066a c0066a = new C0066a();
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
                jSONObject = null;
            }
            c0066a.b = JSONUtil.getString(jSONObject, "resolution");
            String string = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_FILENAME);
            JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_FORMAT);
            String str2 = z ? "jpg" : "mp4";
            c0066a.c = str2;
            c0066a.a = PdrUtil.getDefaultPrivateDocPath(string, str2);
            c0066a.d = JSONUtil.getInt(jSONObject, "index");
            if (jSONObject != null && jSONObject.has("optimize")) {
                c0066a.e = JSONUtil.getBoolean(jSONObject, "optimize");
            }
            if (jSONObject != null && jSONObject.has("videoMaximumDuration")) {
                c0066a.f = JSONUtil.getInt(jSONObject, "videoMaximumDuration");
            }
            if (jSONObject != null && jSONObject.has("crop")) {
                c0066a.g = jSONObject.optJSONObject("crop");
            }
            if (!z) {
                if (jSONObject != null && jSONObject.has("videoCompress")) {
                    c0066a.h = jSONObject.optBoolean("videoCompress", false);
                }
            } else if (jSONObject != null && jSONObject.has("sizeType")) {
                String strOptString = jSONObject.optString("sizeType");
                if (strOptString.contains(Constants.Value.ORIGINAL) && strOptString.contains("compressed")) {
                    c0066a.h = true;
                } else {
                    c0066a.h = !strOptString.contains(Constants.Value.ORIGINAL);
                }
            }
        }
        return c0066a;
    }

    private String b(List list) {
        int size = list.size();
        if (size <= 1) {
            return "[]";
        }
        StringBuffer stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
        for (int i = 0; i < size; i++) {
            stringBuffer.append("'" + ((Camera.Size) list.get(i)).width + "*" + ((Camera.Size) list.get(i)).height + "'");
            if (i != size - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }
}
