package io.dcloud.feature.utsplugin;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.common.Constants;
import io.dcloud.uts.UTSArray;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

/* compiled from: ReturnResult.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004J\u0010\u0010\u0015\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0006¨\u0006\u0017"}, d2 = {"Lio/dcloud/feature/utsplugin/ReturnResult;", "", "()V", IWXUserTrackAdapter.MONITOR_ERROR_MSG, "", "getErrMsg", "()Ljava/lang/String;", "setErrMsg", "(Ljava/lang/String;)V", "params", "getParams", "()Ljava/lang/Object;", "setParams", "(Ljava/lang/Object;)V", "type", "getType", "toJSON", "Lcom/alibaba/fastjson/JSONObject;", "updateError", "", "newErrMsg", "updateJSON", "newParam", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReturnResult {
    private final String type = Constants.Event.RETURN;
    private Object params = "";
    private String errMsg = "";

    public String getType() {
        return this.type;
    }

    public Object getParams() {
        return this.params;
    }

    public void setParams(Object obj) {
        this.params = obj;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.errMsg = str;
    }

    public final void updateJSON(Object newParam) {
        setParams(newParam);
        setErrMsg("");
    }

    public final void updateError(String newErrMsg) {
        Intrinsics.checkNotNullParameter(newErrMsg, "newErrMsg");
        setParams("");
        setErrMsg(newErrMsg);
    }

    public final JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "type", getType());
        if (TextUtils.isEmpty(getErrMsg())) {
            if ((getParams() instanceof String) || (getParams() instanceof Number) || (getParams() instanceof Boolean) || (getParams() instanceof JSONObject) || (getParams() instanceof JSONArray) || (getParams() instanceof UTSArray) || getParams() == null) {
                jSONObject2.put((JSONObject) "params", (String) getParams());
            } else {
                jSONObject2.put((JSONObject) "params", (String) JSON.parseObject(io.dcloud.uts.JSON.stringify(getParams())));
            }
        } else {
            jSONObject2.put((JSONObject) IWXUserTrackAdapter.MONITOR_ERROR_MSG, getErrMsg());
        }
        return jSONObject;
    }
}
