package io.dcloud.uniapp;

import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.uts.UTSError;
import io.dcloud.uts.UTSJSONObject;
import io.dcloud.uts.json.IJsonStringify;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UniError.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0010\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u001f\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eB\u0005¢\u0006\u0002\u0010\u000fJ\b\u0010\u001f\u001a\u00020\u0011H\u0016J\b\u0010 \u001a\u00020\u0005H\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\r\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u0006R\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001b\"\u0004\b\u001e\u0010\u0006¨\u0006!"}, d2 = {"Lio/dcloud/uniapp/UniError;", "Lio/dcloud/uts/UTSError;", "Lio/dcloud/uts/json/IJsonStringify;", "Lio/dcloud/uniapp/IUniError;", "message", "", "(Ljava/lang/String;)V", "options", "Lio/dcloud/uts/UTSJSONObject;", "(Ljava/lang/String;Lio/dcloud/uts/UTSJSONObject;)V", "errSubject", IWXUserTrackAdapter.MONITOR_ERROR_CODE, "", IWXUserTrackAdapter.MONITOR_ERROR_MSG, "(Ljava/lang/String;Ljava/lang/Number;Ljava/lang/String;)V", "()V", "data", "", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "getErrCode", "()Ljava/lang/Number;", "setErrCode", "(Ljava/lang/Number;)V", "getErrMsg", "()Ljava/lang/String;", "setErrMsg", "getErrSubject", "setErrSubject", "toJSON", "toString", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UniError extends UTSError implements IJsonStringify, IUniError {
    private Object data;
    private Number errCode;
    private String errMsg;
    private String errSubject;

    public UniError() {
        this.errSubject = "";
        this.errCode = (Number) 0;
        this.errMsg = "";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UniError(String message) {
        this();
        Intrinsics.checkNotNullParameter(message, "message");
        setMessage(message);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UniError(String message, UTSJSONObject options) {
        this();
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(options, "options");
        setMessage(message);
        Object obj = options.get("cause");
        if (obj == null || !(obj instanceof UTSError)) {
            return;
        }
        setCause((UTSError) obj);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UniError(String errSubject, Number errCode, String errMsg) {
        this();
        Intrinsics.checkNotNullParameter(errSubject, "errSubject");
        Intrinsics.checkNotNullParameter(errCode, "errCode");
        Intrinsics.checkNotNullParameter(errMsg, "errMsg");
        setErrSubject(errSubject);
        setErrCode(errCode);
        setErrMsg(errMsg);
    }

    @Override // io.dcloud.uniapp.IUniError
    public String getErrSubject() {
        return this.errSubject;
    }

    @Override // io.dcloud.uniapp.IUniError
    public void setErrSubject(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.errSubject = str;
    }

    @Override // io.dcloud.uniapp.IUniError
    public Number getErrCode() {
        return this.errCode;
    }

    @Override // io.dcloud.uniapp.IUniError
    public void setErrCode(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.errCode = number;
    }

    @Override // io.dcloud.uniapp.AsyncApiResult
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override // io.dcloud.uniapp.AsyncApiResult
    public void setErrMsg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.errMsg = str;
    }

    @Override // io.dcloud.uniapp.IUniError
    public Object getData() {
        return this.data;
    }

    @Override // io.dcloud.uniapp.IUniError
    public void setData(Object obj) {
        this.data = obj;
    }

    @Override // io.dcloud.uts.UTSError, io.dcloud.uts.json.IJsonStringify
    public Object toJSON() {
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        uTSJSONObject.set(IWXUserTrackAdapter.MONITOR_ERROR_CODE, getErrCode());
        uTSJSONObject.set(IWXUserTrackAdapter.MONITOR_ERROR_MSG, getErrMsg());
        uTSJSONObject.set("errSubject", getErrSubject());
        if (getData() != null) {
            uTSJSONObject.set("data", getData());
        }
        if (getCause() != null) {
            if (getCause() instanceof IJsonStringify) {
                UTSError cause = getCause();
                Intrinsics.checkNotNull(cause, "null cannot be cast to non-null type io.dcloud.uts.json.IJsonStringify");
                uTSJSONObject.set("cause", cause.toJSON());
            } else {
                uTSJSONObject.set("cause", String.valueOf(getCause()));
            }
        }
        return uTSJSONObject;
    }

    @Override // io.dcloud.uts.UTSError, java.lang.Throwable
    public String toString() {
        return "UniError(errSubject='" + getErrSubject() + "', errCode=" + getErrCode() + ", errMsg='" + getErrMsg() + "', data=" + getData() + ", cause=" + getCause() + Operators.BRACKET_END;
    }
}
