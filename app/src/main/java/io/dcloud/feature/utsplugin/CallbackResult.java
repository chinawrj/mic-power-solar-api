package io.dcloud.feature.utsplugin;

import com.alibaba.fastjson.JSONArray;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CallbackResult.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010¨\u0006\u0019"}, d2 = {"Lio/dcloud/feature/utsplugin/CallbackResult;", "", "()V", "id", "", "getId", "()I", "keepAlive", "", "getKeepAlive", "()Z", "setKeepAlive", "(Z)V", "name", "", "getName", "()Ljava/lang/String;", "param", "Lcom/alibaba/fastjson/JSONArray;", "getParam", "()Lcom/alibaba/fastjson/JSONArray;", "setParam", "(Lcom/alibaba/fastjson/JSONArray;)V", "type", "getType", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CallbackResult {
    private boolean keepAlive;
    private final String type = "params";
    private final int id = -1;
    private final String name = "complete";
    private JSONArray param = new JSONArray();

    public String getType() {
        return this.type;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final JSONArray getParam() {
        return this.param;
    }

    public final void setParam(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "<set-?>");
        this.param = jSONArray;
    }

    public final boolean getKeepAlive() {
        return this.keepAlive;
    }

    public final void setKeepAlive(boolean z) {
        this.keepAlive = z;
    }
}
