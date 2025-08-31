package uts.sdk.modules.DCloudUniNetwork;

import android.content.Context;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uniapp.SourceError;
import io.dcloud.uts.Map;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSIteratorKt;
import io.dcloud.uts.UTSJSONObject;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/DownloadController;", "", "()V", "downloadExecutorService", "Ljava/util/concurrent/ExecutorService;", "createDownloadClient", "Lokhttp3/OkHttpClient;", AbsoluteConst.JSON_KEY_OPTION, "Luts/sdk/modules/DCloudUniNetwork/DownloadFileOptions;", "createDownloadRequest", "Lokhttp3/Request;", "options", "listener", "Luts/sdk/modules/DCloudUniNetwork/NetworkDownloadFileListener;", "downloadFile", "Luts/sdk/modules/DCloudUniNetwork/DownloadTask;", "Companion", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class DownloadController {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static DownloadController instance;
    private ExecutorService downloadExecutorService;

    public DownloadTask downloadFile(DownloadFileOptions options, NetworkDownloadFileListener listener) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(listener, "listener");
        OkHttpClient okHttpClientCreateDownloadClient = createDownloadClient(options);
        Request requestCreateDownloadRequest = createDownloadRequest(options, listener);
        if (requestCreateDownloadRequest == null) {
            return new NetworkDownloadTaskImpl(null, listener);
        }
        Call callNewCall = okHttpClientCreateDownloadClient.newCall(requestCreateDownloadRequest);
        Intrinsics.checkNotNullExpressionValue(callNewCall, "client.newCall(request)");
        String filePath = options.getFilePath();
        if (filePath == null) {
            filePath = "";
        }
        callNewCall.enqueue(new SimpleDownloadCallback(listener, filePath));
        return new NetworkDownloadTaskImpl(callNewCall, listener);
    }

    private final OkHttpClient createDownloadClient(DownloadFileOptions option) {
        long jLongValue;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (option.getTimeout() != null) {
            Number timeout = option.getTimeout();
            Intrinsics.checkNotNull(timeout);
            jLongValue = timeout.longValue();
        } else {
            jLongValue = 120000;
        }
        builder.connectTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.readTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.writeTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new CookieInterceptor());
        if (this.downloadExecutorService == null) {
            this.downloadExecutorService = Executors.newFixedThreadPool(10);
        }
        builder.dispatcher(new Dispatcher(this.downloadExecutorService));
        OkHttpClient okHttpClientBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(okHttpClientBuild, "clientBuilder.build()");
        return okHttpClientBuild;
    }

    private final Request createDownloadRequest(DownloadFileOptions options, NetworkDownloadFileListener listener) {
        Request.Builder builder = new Request.Builder();
        try {
            builder.url(options.getUrl());
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Context appContext = UTSAndroid.INSTANCE.getAppContext();
            Intrinsics.checkNotNull(appContext);
            builder.header(IWebview.USER_AGENT, NumberKt.toString_number_nullable$default(uTSAndroid.getWebViewInfo(appContext).get("ua"), (Number) null, 1, (Object) null));
            UTSJSONObject header = options.getHeader();
            Map<String, Object> map = header != null ? header.toMap() : null;
            if (map != null) {
                for (Map.Entry entry : ((java.util.Map) UTSIteratorKt.resolveUTSKeyIterator(map)).entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        builder.addHeader(str, "" + NumberKt.toString_number_nullable(value, (Number) 10));
                    }
                }
            }
            return builder.build();
        } catch (Exception e) {
            UTSJSONObject uTSJSONObject = new UTSJSONObject();
            uTSJSONObject.set("statusCode", "-1");
            uTSJSONObject.set("errorCode", "-1");
            uTSJSONObject.set("errorMsg", "invalid URL");
            uTSJSONObject.set("cause", new SourceError(NumberKt.toString_number_nullable$default(e.getCause(), (Number) null, 1, (Object) null)));
            if (listener != null) {
                listener.onComplete(uTSJSONObject);
            }
            return null;
        }
    }

    /* compiled from: index.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/DownloadController$Companion;", "", "()V", "instance", "Luts/sdk/modules/DCloudUniNetwork/DownloadController;", "getInstance", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadController getInstance() {
            if (DownloadController.instance == null) {
                DownloadController.instance = new DownloadController();
            }
            DownloadController downloadController = DownloadController.instance;
            Intrinsics.checkNotNull(downloadController);
            return downloadController;
        }
    }
}
