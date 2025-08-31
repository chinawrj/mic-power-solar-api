package com.taobao.weex.ui.action;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.font.FontAdapter;
import com.taobao.weex.utils.FontDO;
import com.taobao.weex.utils.TypefaceUtil;

/* loaded from: classes3.dex */
public class ActionAddRule implements IExecutable {
    private final JSONObject mData;
    private final String mPageId;
    private final String mType;

    public ActionAddRule(String str, String str2, JSONObject jSONObject) {
        this.mPageId = str;
        this.mType = str2;
        this.mData = jSONObject;
    }

    private void notifyAddFontRule(WXSDKInstance wXSDKInstance, FontDO fontDO) {
        FontAdapter fontAdapter = WXSDKManager.getInstance().getFontAdapter();
        if (fontAdapter != null) {
            fontAdapter.onAddFontRule(wXSDKInstance.getInstanceId(), fontDO.getFontFamilyName(), fontDO.getUrl());
        }
    }

    private FontDO parseFontDO(JSONObject jSONObject, WXSDKInstance wXSDKInstance) {
        if (jSONObject == null) {
            return null;
        }
        return new FontDO(jSONObject.getString(Constants.Name.FONT_FAMILY), jSONObject.getString("src"), wXSDKInstance);
    }

    @Override // com.taobao.weex.ui.action.IExecutable
    public void executeAction() {
        FontDO fontDO;
        WXSDKInstance wXSDKInstance = WXSDKManager.getInstance().getWXRenderManager().getWXSDKInstance(this.mPageId);
        if (wXSDKInstance == null || wXSDKInstance.isDestroy() || !Constants.Name.FONT_FACE.equals(this.mType) || (fontDO = parseFontDO(this.mData, wXSDKInstance)) == null || TextUtils.isEmpty(fontDO.getFontFamilyName())) {
            return;
        }
        notifyAddFontRule(wXSDKInstance, fontDO);
        FontDO fontDO2 = TypefaceUtil.getFontDO(fontDO.getFontFamilyName());
        if (fontDO2 != null && TextUtils.equals(fontDO2.getUrl(), fontDO.getUrl())) {
            TypefaceUtil.loadTypeface(fontDO2, true);
        } else {
            TypefaceUtil.putFontDO(fontDO);
            TypefaceUtil.loadTypeface(fontDO, true);
        }
    }
}
