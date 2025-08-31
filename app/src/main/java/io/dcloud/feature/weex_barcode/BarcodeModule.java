package io.dcloud.feature.weex_barcode;

import android.graphics.BitmapFactory;
import android.net.Uri;
import com.alibaba.fastjson.JSONArray;
import com.dcloud.zxing2.Result;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.feature.barcode2.decoding.CaptureActivityHandler;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class BarcodeModule extends WXModule {
    @JSMethod
    public void scan(String str, JSCallback jSCallback, JSONArray jSONArray, boolean z) {
        try {
            String path = this.mWXSDKInstance.rewriteUri(Uri.parse(str), "image").getPath();
            Result resultDecode = CaptureActivityHandler.decode(BitmapFactory.decodeFile(path), z);
            if (resultDecode != null) {
                HashMap map = new HashMap();
                map.put("type", WXImage.SUCCEED);
                map.put("code", resultDecode.getBarcodeFormat().toString());
                map.put("message", JSONUtil.toJSONableString(resultDecode.getText()));
                if (path == null) {
                    path = "";
                }
                map.put("file", path);
                map.put("charSet", resultDecode.textCharset);
                HashMap map2 = new HashMap();
                map2.put("detail", map);
                jSCallback.invoke(map2);
            } else {
                HashMap map3 = new HashMap();
                map3.put("type", Constants.Event.FAIL);
                map3.put("code", 8);
                map3.put("message", "");
                map3.put("charSet", resultDecode.textCharset);
                HashMap map4 = new HashMap();
                map4.put("detail", map3);
                jSCallback.invoke(map4);
            }
        } catch (Exception e) {
            HashMap map5 = new HashMap();
            map5.put("type", Constants.Event.FAIL);
            map5.put("code", 8);
            map5.put("message", e.getMessage());
            HashMap map6 = new HashMap();
            map6.put("detail", map5);
            jSCallback.invoke(map6);
        }
    }
}
