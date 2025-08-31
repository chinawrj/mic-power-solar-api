package uts.sdk.modules.DCloudUniNetwork;

import com.taobao.weex.adapter.IWXUserTrackAdapter;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.feature.uniapp.adapter.AbsURIAdapter;
import io.dcloud.uts.Map;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSArrayKt;
import io.dcloud.uts.UTSBridge;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000¾\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\"\u001a\u0012\u0010#\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010$\u001a\u00020\u0002\u001a\u001f\u0010%\u001a\u00020&\"\u0006\b\u0000\u0010'\u0018\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H'0(H\u0086\b\u001a\u000e\u0010)\u001a\u00020!2\u0006\u0010\u0016\u001a\u00020*\"!\u0010\u0000\u001a\u0012\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\"\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\"0\u0010\u0011\u001a!\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0012j\u0002`\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"0\u0010\u001b\u001a!\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u001d0\u0012j\u0002`\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001a*@\u0010+\"\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u00122\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0012*\n\u0010,\"\u00020-2\u00020-*S\u0010.\"\u001d\u0012\u0013\u0012\u0011`/¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u001220\u0012&\u0012$0-j\u0011`/¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u00102\"\u001d\u0012\u0013\u0012\u001103¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u001103¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u00104\"\u001d\u0012\u0013\u0012\u001105¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u001105¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u00106\"\u001d\u0012\u0013\u0012\u001107¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u001107¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u00108\"\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(9\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(9\u0012\u0004\u0012\u0002010\u0012*\n\u0010:\"\u00020\u00022\u00020\u0002*@\u0010;\"\u001d\u0012\u0013\u0012\u00110<¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(9\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u00110<¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(9\u0012\u0004\u0012\u0002010\u0012*\n\u0010=\"\u00020\u00042\u00020\u0004*R\u0010>\u001a\u0004\b\u0000\u0010'\"#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H'0?¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(9\u0012\u0004\u0012\u0002010\u00122#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H'0?¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(9\u0012\u0004\u0012\u0002010\u0012*@\u0010@\"\u001d\u0012\u0013\u0012\u00110A¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u00110A¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u0010B\"\u001d\u0012\u0013\u0012\u00110C¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u00110C¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u0010D\"\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u001d0\u00122\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u001d0\u0012*@\u0010E\"\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u0010F\"\u001d\u0012\u0013\u0012\u00110G¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u00110G¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u0010H\"\u001d\u0012\u0013\u0012\u00110I¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u00110I¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012*@\u0010J\"\u001d\u0012\u0013\u0012\u00110K¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u00122\u001d\u0012\u0013\u0012\u00110K¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010\u0012¨\u0006L"}, d2 = {"NetWorkUniErrors", "Lio/dcloud/uts/Map;", "", "Luts/sdk/modules/DCloudUniNetwork/RequestErrorCode;", "", "getNetWorkUniErrors", "()Lio/dcloud/uts/Map;", "UniNetWorkErrorSubject", "getUniNetWorkErrorSubject", "()Ljava/lang/String;", "charsetPattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "getCharsetPattern", "()Ljava/util/regex/Pattern;", "setCharsetPattern", "(Ljava/util/regex/Pattern;)V", "downloadFile", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileOptions;", "Lkotlin/ParameterName;", "name", "options", "Luts/sdk/modules/DCloudUniNetwork/DownloadTask;", "Luts/sdk/modules/DCloudUniNetwork/DownloadFile;", "getDownloadFile", "()Lkotlin/jvm/functions/Function1;", "uploadFile", "Luts/sdk/modules/DCloudUniNetwork/UploadFileOptions;", "Luts/sdk/modules/DCloudUniNetwork/UploadTask;", "Luts/sdk/modules/DCloudUniNetwork/UploadFile;", "getUploadFile", "downloadFileByJs", "", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileOptionsJSONObject;", "getErrcode", IWXUserTrackAdapter.MONITOR_ERROR_CODE, AbsURIAdapter.REQUEST, "Luts/sdk/modules/DCloudUniNetwork/RequestTask;", ExifInterface.GPS_DIRECTION_TRUE, "Luts/sdk/modules/DCloudUniNetwork/RequestOptions;", "uploadFileByJs", "Luts/sdk/modules/DCloudUniNetwork/UploadFileOptionsJSONObject;", "DownloadFile", "DownloadFileComplete", "", "DownloadFileCompleteCallback", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileComplete;", "result", "", "DownloadFileFailCallback", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileFail;", "DownloadFileProgressUpdateCallback", "Luts/sdk/modules/DCloudUniNetwork/OnProgressDownloadResult;", "DownloadFileSuccessCallback", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileSuccess;", "RequestCompleteCallback", AbsoluteConst.JSON_KEY_OPTION, "RequestErrorCode", "RequestFailCallback", "Luts/sdk/modules/DCloudUniNetwork/RequestFail;", "RequestMethod", "RequestSuccessCallback", "Luts/sdk/modules/DCloudUniNetwork/RequestSuccess;", "RequestTaskOnChunkReceivedCallback", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnChunkReceivedListenerResult;", "RequestTaskOnHeadersReceivedCallback", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnHeadersReceivedListenerResult;", "UploadFile", "UploadFileCompleteCallback", "UploadFileFailCallback", "Luts/sdk/modules/DCloudUniNetwork/UploadFileFail;", "UploadFileProgressUpdateCallback", "Luts/sdk/modules/DCloudUniNetwork/OnProgressUpdateResult;", "UploadFileSuccessCallback", "Luts/sdk/modules/DCloudUniNetwork/UploadFileSuccess;", "uni-network_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IndexKt {
    private static final String UniNetWorkErrorSubject = "uni-request";
    private static final Map<Number, String> NetWorkUniErrors = new Map<>((UTSArray<UTSArray<Object>>) UTSArrayKt._uA(UTSArrayKt._uA(5, "time out"), UTSArrayKt._uA(1000, "server system error"), UTSArrayKt._uA(100001, "invalid json"), UTSArrayKt._uA(100002, "error message invalid json"), UTSArrayKt._uA(600003, "network interrupted error"), UTSArrayKt._uA(600008, "the data parameter type is invalid"), UTSArrayKt._uA(600009, "invalid URL"), UTSArrayKt._uA(602001, "request system error")));
    private static Pattern charsetPattern = Pattern.compile("charset=([a-z0-9-]+)");
    private static final Function1<UploadFileOptions, UploadTask> uploadFile = new Function1<UploadFileOptions, UploadTask>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$uploadFile$1
        @Override // kotlin.jvm.functions.Function1
        public final UploadTask invoke(UploadFileOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            return NetworkManager.INSTANCE.getInstance().uploadFile(options, new UploadNetworkListener(options));
        }
    };
    private static final Function1<DownloadFileOptions, DownloadTask> downloadFile = new Function1<DownloadFileOptions, DownloadTask>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$downloadFile$1
        @Override // kotlin.jvm.functions.Function1
        public final DownloadTask invoke(DownloadFileOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            return NetworkManager.INSTANCE.getInstance().downloadFile(options, new DownloadNetworkListener(options));
        }
    };

    public static final String getUniNetWorkErrorSubject() {
        return UniNetWorkErrorSubject;
    }

    public static final Map<Number, String> getNetWorkUniErrors() {
        return NetWorkUniErrors;
    }

    public static final Number getErrcode(Number errCode) {
        Intrinsics.checkNotNullParameter(errCode, "errCode");
        return NetWorkUniErrors.get(errCode) == null ? (Number) 602001 : errCode;
    }

    public static final Pattern getCharsetPattern() {
        return charsetPattern;
    }

    public static final void setCharsetPattern(Pattern pattern) {
        charsetPattern = pattern;
    }

    public static final /* synthetic */ <T> RequestTask request(RequestOptions<T> options) {
        Intrinsics.checkNotNullParameter(options, "options");
        UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
        Intrinsics.needClassReification();
        Type type = new TypeToken<T>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$request$$inlined$getGenericType$1
        }.getType();
        Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<T>(){}.type");
        UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
        Intrinsics.needClassReification();
        String name = new TypeToken<T>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$request$$inlined$getGenericClassName$1
        }.getRawType().getName();
        Intrinsics.checkNotNullExpressionValue(name, "object : TypeToken<T>(){}.rawType.name");
        return NetworkManager.INSTANCE.getInstance().request(options, new RequestNetworkListener(options, type, name));
    }

    public static final Function1<UploadFileOptions, UploadTask> getUploadFile() {
        return uploadFile;
    }

    public static final Function1<DownloadFileOptions, DownloadTask> getDownloadFile() {
        return downloadFile;
    }

    public static final int uploadFileByJs(final UploadFileOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return UTSBridge.INSTANCE.registerJavaScriptClassInstance(new UploadTaskByJsProxy(uploadFile.invoke(new UploadFileOptions(options.getUrl(), options.getFilePath(), options.getName(), options.getFiles(), options.getHeader(), options.getFormData(), options.getTimeout(), new Function1<UploadFileSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$uploadFileByJs$ins$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UploadFileSuccess uploadFileSuccess) throws SecurityException {
                invoke2(uploadFileSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UploadFileSuccess result) throws SecurityException {
                Intrinsics.checkNotNullParameter(result, "result");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(result);
                }
            }
        }, new Function1<UploadFileFail, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$uploadFileByJs$ins$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UploadFileFail uploadFileFail) throws SecurityException {
                invoke2(uploadFileFail);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UploadFileFail result) throws SecurityException {
                Intrinsics.checkNotNullParameter(result, "result");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(result);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$uploadFileByJs$ins$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object result) throws SecurityException {
                Intrinsics.checkNotNullParameter(result, "result");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(result);
                }
            }
        }))));
    }

    public static final int downloadFileByJs(final DownloadFileOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        return UTSBridge.INSTANCE.registerJavaScriptClassInstance(new DownloadTaskByJsProxy(downloadFile.invoke(new DownloadFileOptions(options.getUrl(), options.getHeader(), options.getFilePath(), options.getTimeout(), new Function1<DownloadFileSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$downloadFileByJs$ins$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DownloadFileSuccess downloadFileSuccess) throws SecurityException {
                invoke2(downloadFileSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadFileSuccess result) throws SecurityException {
                Intrinsics.checkNotNullParameter(result, "result");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(result);
                }
            }
        }, new Function1<DownloadFileFail, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$downloadFileByJs$ins$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DownloadFileFail downloadFileFail) throws SecurityException {
                invoke2(downloadFileFail);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DownloadFileFail result) throws SecurityException {
                Intrinsics.checkNotNullParameter(result, "result");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(result);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.IndexKt$downloadFileByJs$ins$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object result) throws SecurityException {
                Intrinsics.checkNotNullParameter(result, "result");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(result);
                }
            }
        }))));
    }
}
