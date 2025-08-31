package uts.sdk.modules.DCloudUniNetwork;

import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSJSONObject;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b+\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u009e\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u00127\b\u0002\u0010\u0014\u001a1\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0016¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u001b\u0012+\b\u0002\u0010\u001c\u001a%\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015j\u0004\u0018\u0001`\u001e\u0012+\b\u0002\u0010\u001f\u001a%\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015j\u0004\u0018\u0001` ¢\u0006\u0002\u0010!R=\u0010\u001f\u001a%\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015j\u0004\u0018\u0001` X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R=\u0010\u001c\u001a%\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015j\u0004\u0018\u0001`\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010#\"\u0004\b4\u0010%R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b5\u0010/\"\u0004\b6\u00101R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010\t\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010+\"\u0004\b<\u0010-R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010+\"\u0004\b>\u0010-R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b?\u0010/\"\u0004\b@\u00101RI\u0010\u0014\u001a1\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0016¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u001bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010#\"\u0004\bB\u0010%R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001e\u0010\u0003\u001a\u00020\u00048\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010+\"\u0004\bH\u0010-R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\bI\u0010/\"\u0004\bJ\u00101¨\u0006K"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/RequestOptions;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/dcloud/uts/UTSObject;", "url", "", "data", "", WXBasicComponentType.HEADER, "Lio/dcloud/uts/UTSJSONObject;", "method", "Luts/sdk/modules/DCloudUniNetwork/RequestMethod;", "timeout", "", "dataType", "responseType", "sslVerify", "", "withCredentials", "firstIpv4", "enableChunked", WXImage.SUCCEED, "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniNetwork/RequestSuccess;", "Lkotlin/ParameterName;", "name", AbsoluteConst.JSON_KEY_OPTION, "", "Luts/sdk/modules/DCloudUniNetwork/RequestSuccessCallback;", Constants.Event.FAIL, "Luts/sdk/modules/DCloudUniNetwork/RequestFail;", "Luts/sdk/modules/DCloudUniNetwork/RequestFailCallback;", "complete", "Luts/sdk/modules/DCloudUniNetwork/RequestCompleteCallback;", "(Ljava/lang/String;Ljava/lang/Object;Lio/dcloud/uts/UTSJSONObject;Ljava/lang/String;Ljava/lang/Number;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getComplete", "()Lkotlin/jvm/functions/Function1;", "setComplete", "(Lkotlin/jvm/functions/Function1;)V", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "getDataType", "()Ljava/lang/String;", "setDataType", "(Ljava/lang/String;)V", "getEnableChunked", "()Ljava/lang/Boolean;", "setEnableChunked", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getFail", "setFail", "getFirstIpv4", "setFirstIpv4", "getHeader", "()Lio/dcloud/uts/UTSJSONObject;", "setHeader", "(Lio/dcloud/uts/UTSJSONObject;)V", "getMethod", "setMethod", "getResponseType", "setResponseType", "getSslVerify", "setSslVerify", "getSuccess", "setSuccess", "getTimeout", "()Ljava/lang/Number;", "setTimeout", "(Ljava/lang/Number;)V", "getUrl", "setUrl", "getWithCredentials", "setWithCredentials", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class RequestOptions<T> extends UTSObject {
    private Function1<Object, Unit> complete;
    private Object data;
    private String dataType;
    private Boolean enableChunked;
    private Function1<? super RequestFail, Unit> fail;
    private Boolean firstIpv4;
    private UTSJSONObject header;
    private String method;
    private String responseType;
    private Boolean sslVerify;
    private Function1<? super RequestSuccess<T>, Unit> success;
    private Number timeout;

    @JsonNotNull
    private String url;
    private Boolean withCredentials;

    public /* synthetic */ RequestOptions(String str, Object obj, UTSJSONObject uTSJSONObject, String str2, Number number, String str3, String str4, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Function1 function1, Function1 function12, Function1 function13, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : obj, (i & 4) != 0 ? null : uTSJSONObject, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : number, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : bool, (i & 256) != 0 ? null : bool2, (i & 512) != 0 ? null : bool3, (i & 1024) != 0 ? null : bool4, (i & 2048) != 0 ? null : function1, (i & 4096) != 0 ? null : function12, (i & 8192) == 0 ? function13 : null);
    }

    public String getUrl() {
        return this.url;
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

    public Function1<RequestSuccess<T>, Unit> getSuccess() {
        return this.success;
    }

    public void setSuccess(Function1<? super RequestSuccess<T>, Unit> function1) {
        this.success = function1;
    }

    public Function1<RequestFail, Unit> getFail() {
        return this.fail;
    }

    public void setFail(Function1<? super RequestFail, Unit> function1) {
        this.fail = function1;
    }

    public Function1<Object, Unit> getComplete() {
        return this.complete;
    }

    public void setComplete(Function1<Object, Unit> function1) {
        this.complete = function1;
    }

    public RequestOptions(String url, Object obj, UTSJSONObject uTSJSONObject, String str, Number number, String str2, String str3, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Function1<? super RequestSuccess<T>, Unit> function1, Function1<? super RequestFail, Unit> function12, Function1<Object, Unit> function13) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        this.data = obj;
        this.header = uTSJSONObject;
        this.method = str;
        this.timeout = number;
        this.dataType = str2;
        this.responseType = str3;
        this.sslVerify = bool;
        this.withCredentials = bool2;
        this.firstIpv4 = bool3;
        this.enableChunked = bool4;
        this.success = function1;
        this.fail = function12;
        this.complete = function13;
    }
}
