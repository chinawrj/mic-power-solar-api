package io.dcloud.feature.nativeObj;

import com.taobao.weex.common.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class NativeBitmapSaveOptions {
    public int height;
    public JSONObject mClip;
    public String mFormat;
    private JSONObject mJson;
    public boolean mOverwrite;
    public int mQuality;
    public String path;
    public long size;
    public int width;

    public NativeBitmapSaveOptions(String str) {
        this.mOverwrite = false;
        this.mFormat = "jpg";
        this.mQuality = 50;
        this.mJson = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.mJson = jSONObject;
            this.mOverwrite = jSONObject.optBoolean("overwrite", false);
            this.mFormat = this.mJson.optString(AbsoluteConst.JSON_KEY_FORMAT, "jpg");
            this.mQuality = this.mJson.optInt(Constants.Name.QUALITY, 50);
            this.mClip = this.mJson.optJSONObject("clip");
        } catch (JSONException unused) {
            this.mJson = new JSONObject();
        }
    }

    public String toJsString() {
        return this.mJson.toString();
    }
}
