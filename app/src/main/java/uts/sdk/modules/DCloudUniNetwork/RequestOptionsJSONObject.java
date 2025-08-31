package uts.sdk.modules.DCloudUniNetwork;

import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0004\n\u0002\b\u000b\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0007\"\u0004\b\u001f\u0010\tR\u001e\u0010 \u001a\u0004\u0018\u00010\u0017X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b!\u0010\u0019\"\u0004\b\"\u0010\u001bR\u001c\u0010#\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010(\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`)X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0013\"\u0004\b+\u0010\u0015R\u001c\u0010,\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0013\"\u0004\b.\u0010\u0015R\u001e\u0010/\u001a\u0004\u0018\u00010\u0017X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b0\u0010\u0019\"\u0004\b1\u0010\u001bR\u001c\u00102\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0007\"\u0004\b4\u0010\tR\u001c\u00105\u001a\u0004\u0018\u000106X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020\u0011X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0013\"\u0004\b=\u0010\u0015R\u001e\u0010>\u001a\u0004\u0018\u00010\u0017X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b?\u0010\u0019\"\u0004\b@\u0010\u001b¨\u0006A"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/RequestOptionsJSONObject;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/dcloud/uts/UTSJSONObject;", "()V", "complete", "Lio/dcloud/uts/UTSCallback;", "getComplete", "()Lio/dcloud/uts/UTSCallback;", "setComplete", "(Lio/dcloud/uts/UTSCallback;)V", "data", "", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "dataType", "", "getDataType", "()Ljava/lang/String;", "setDataType", "(Ljava/lang/String;)V", "enableChunked", "", "getEnableChunked", "()Ljava/lang/Boolean;", "setEnableChunked", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", Constants.Event.FAIL, "getFail", "setFail", "firstIpv4", "getFirstIpv4", "setFirstIpv4", WXBasicComponentType.HEADER, "getHeader", "()Lio/dcloud/uts/UTSJSONObject;", "setHeader", "(Lio/dcloud/uts/UTSJSONObject;)V", "method", "Luts/sdk/modules/DCloudUniNetwork/RequestMethod;", "getMethod", "setMethod", "responseType", "getResponseType", "setResponseType", "sslVerify", "getSslVerify", "setSslVerify", WXImage.SUCCEED, "getSuccess", "setSuccess", "timeout", "", "getTimeout", "()Ljava/lang/Number;", "setTimeout", "(Ljava/lang/Number;)V", "url", "getUrl", "setUrl", "withCredentials", "getWithCredentials", "setWithCredentials", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class RequestOptionsJSONObject<T> extends UTSJSONObject {
    private UTSCallback complete;
    private Object data;
    private String dataType;
    private Boolean enableChunked;
    private UTSCallback fail;
    private Boolean firstIpv4;
    private UTSJSONObject header;
    private String method;
    private String responseType;
    private Boolean sslVerify;
    private UTSCallback success;
    private Number timeout;
    public String url;
    private Boolean withCredentials;

    public String getUrl() {
        String str = this.url;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("url");
        return null;
    }

    public void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public UTSJSONObject getHeader() {
        return this.header;
    }

    public void setHeader(UTSJSONObject uTSJSONObject) {
        this.header = uTSJSONObject;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public Number getTimeout() {
        return this.timeout;
    }

    public void setTimeout(Number number) {
        this.timeout = number;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String str) {
        this.dataType = str;
    }

    public String getResponseType() {
        return this.responseType;
    }

    public void setResponseType(String str) {
        this.responseType = str;
    }

    public Boolean getSslVerify() {
        return this.sslVerify;
    }

    public void setSslVerify(Boolean bool) {
        this.sslVerify = bool;
    }

    public Boolean getWithCredentials() {
        return this.withCredentials;
    }

    public void setWithCredentials(Boolean bool) {
        this.withCredentials = bool;
    }

    public Boolean getFirstIpv4() {
        return this.firstIpv4;
    }

    public void setFirstIpv4(Boolean bool) {
        this.firstIpv4 = bool;
    }

    public Boolean getEnableChunked() {
        return this.enableChunked;
    }

    public void setEnableChunked(Boolean bool) {
        this.enableChunked = bool;
    }

    public UTSCallback getSuccess() {
        return this.success;
    }

    public void setSuccess(UTSCallback uTSCallback) {
        this.success = uTSCallback;
    }

    public UTSCallback getFail() {
        return this.fail;
    }

    public void setFail(UTSCallback uTSCallback) {
        this.fail = uTSCallback;
    }

    public UTSCallback getComplete() {
        return this.complete;
    }

    public void setComplete(UTSCallback uTSCallback) {
        this.complete = uTSCallback;
    }
}
