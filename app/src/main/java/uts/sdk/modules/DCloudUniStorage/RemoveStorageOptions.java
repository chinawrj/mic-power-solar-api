package uts.sdk.modules.DCloudUniStorage;

import com.facebook.common.util.UriUtil;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.uniapp.UniError;
import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001B\u0094\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012+\b\u0002\u0010\u0004\u001a%\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u000b\u0012+\b\u0002\u0010\f\u001a%\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u000e\u0012+\b\u0002\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0011¢\u0006\u0002\u0010\u0012R=\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R=\u0010\f\u001a%\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR=\u0010\u0004\u001a%\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016¨\u0006\u001f"}, d2 = {"Luts/sdk/modules/DCloudUniStorage/RemoveStorageOptions;", "Lio/dcloud/uts/UTSObject;", IApp.ConfigProperty.CONFIG_KEY, "", WXImage.SUCCEED, "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageSuccess;", "Lkotlin/ParameterName;", "name", UriUtil.LOCAL_RESOURCE_SCHEME, "", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageSuccessCallback;", Constants.Event.FAIL, "Lio/dcloud/uniapp/UniError;", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageFailCallback;", "complete", "", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageCompleteCallback;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getComplete", "()Lkotlin/jvm/functions/Function1;", "setComplete", "(Lkotlin/jvm/functions/Function1;)V", "getFail", "setFail", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "getSuccess", "setSuccess", "uni-storage_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class RemoveStorageOptions extends UTSObject {
    private Function1<Object, Unit> complete;
    private Function1<? super UniError, Unit> fail;

    @JsonNotNull
    private String key;
    private Function1<? super RemoveStorageSuccess, Unit> success;

    public /* synthetic */ RemoveStorageOptions(String str, Function1 function1, Function1 function12, Function1 function13, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : function1, (i & 4) != 0 ? null : function12, (i & 8) != 0 ? null : function13);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.key = str;
    }

    public Function1<RemoveStorageSuccess, Unit> getSuccess() {
        return this.success;
    }

    public void setSuccess(Function1<? super RemoveStorageSuccess, Unit> function1) {
        this.success = function1;
    }

    public Function1<UniError, Unit> getFail() {
        return this.fail;
    }

    public void setFail(Function1<? super UniError, Unit> function1) {
        this.fail = function1;
    }

    public Function1<Object, Unit> getComplete() {
        return this.complete;
    }

    public void setComplete(Function1<Object, Unit> function1) {
        this.complete = function1;
    }

    public RemoveStorageOptions(String key, Function1<? super RemoveStorageSuccess, Unit> function1, Function1<? super UniError, Unit> function12, Function1<Object, Unit> function13) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.key = key;
        this.success = function1;
        this.fail = function12;
        this.complete = function13;
    }
}
