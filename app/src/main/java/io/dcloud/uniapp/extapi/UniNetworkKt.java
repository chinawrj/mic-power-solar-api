package io.dcloud.uniapp.extapi;

import io.dcloud.common.util.ExifInterface;
import io.dcloud.feature.uniapp.adapter.AbsURIAdapter;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import uts.sdk.modules.DCloudUniNetwork.DownloadFileOptions;
import uts.sdk.modules.DCloudUniNetwork.DownloadTask;
import uts.sdk.modules.DCloudUniNetwork.IndexKt;
import uts.sdk.modules.DCloudUniNetwork.NetworkManager;
import uts.sdk.modules.DCloudUniNetwork.RequestNetworkListener;
import uts.sdk.modules.DCloudUniNetwork.RequestOptions;
import uts.sdk.modules.DCloudUniNetwork.RequestTask;
import uts.sdk.modules.DCloudUniNetwork.UploadFileOptions;
import uts.sdk.modules.DCloudUniNetwork.UploadTask;

/* compiled from: uniNetwork.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011\"\u0006\b\u0000\u0010\u0012\u0018\u00012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u0002H\u00120\u0013j\b\u0012\u0004\u0012\u0002H\u0012`\u0014H\u0086\b\"0\u0010\u0000\u001a!\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"0\u0010\n\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\f0\u0001j\u0002`\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\t*\u000e\u0010\u0015\"\u0002`\u00162\u00060\u0017j\u0002`\u0016*\n\u0010\u0018\"\u00020\u00192\u00020\u0019*\n\u0010\u001a\"\u00020\u00022\u00020\u0002*\n\u0010\u001b\"\u00020\u001c2\u00020\u001c*\n\u0010\u001d\"\u00020\u00062\u00020\u0006*\n\u0010\u001e\"\u00020\u001f2\u00020\u001f*\n\u0010 \"\u00020!2\u00020!*\u000e\u0010\"\"\u0002`#2\u00060$j\u0002`#*\n\u0010%\"\u00020&2\u00020&*\u000e\u0010'\"\u0002`(2\u00060)j\u0002`(*\u001c\u0010*\u001a\u0004\b\u0000\u0010\u0012\"\b\u0012\u0004\u0012\u0002H\u00120\u00132\b\u0012\u0004\u0012\u0002H\u00120\u0013*\u001c\u0010+\u001a\u0004\b\u0000\u0010\u0012\"\b\u0012\u0004\u0012\u0002H\u00120,2\b\u0012\u0004\u0012\u0002H\u00120,*\n\u0010-\"\u00020\u00102\u00020\u0010*\n\u0010.\"\u00020/2\u00020/*\n\u00100\"\u0002012\u000201*\n\u00102\"\u0002032\u000203*\n\u00104\"\u0002052\u000205*\n\u00106\"\u00020\u000b2\u00020\u000b*\n\u00107\"\u0002082\u000208*\n\u00109\"\u00020\f2\u00020\f¨\u0006:"}, d2 = {"downloadFile", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileOptions;", "Lkotlin/ParameterName;", "name", "options", "Luts/sdk/modules/DCloudUniNetwork/DownloadTask;", "Luts/sdk/modules/DCloudUniNetwork/DownloadFile;", "getDownloadFile", "()Lkotlin/jvm/functions/Function1;", "uploadFile", "Luts/sdk/modules/DCloudUniNetwork/UploadFileOptions;", "Luts/sdk/modules/DCloudUniNetwork/UploadTask;", "Luts/sdk/modules/DCloudUniNetwork/UploadFile;", "getUploadFile", AbsURIAdapter.REQUEST, "Luts/sdk/modules/DCloudUniNetwork/RequestTask;", "Lio/dcloud/uniapp/extapi/RequestTask;", ExifInterface.GPS_DIRECTION_TRUE, "Luts/sdk/modules/DCloudUniNetwork/RequestOptions;", "Lio/dcloud/uniapp/extapi/RequestOptions;", "DownloadFileComplete", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileComplete;", "", "DownloadFileFail", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileFail;", "DownloadFileOptions", "DownloadFileSuccess", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileSuccess;", "DownloadTask", "OnProgressDownloadResult", "Luts/sdk/modules/DCloudUniNetwork/OnProgressDownloadResult;", "OnProgressUpdateResult", "Luts/sdk/modules/DCloudUniNetwork/OnProgressUpdateResult;", "RequestErrorCode", "Luts/sdk/modules/DCloudUniNetwork/RequestErrorCode;", "", "RequestFail", "Luts/sdk/modules/DCloudUniNetwork/RequestFail;", "RequestMethod", "Luts/sdk/modules/DCloudUniNetwork/RequestMethod;", "", "RequestOptions", "RequestSuccess", "Luts/sdk/modules/DCloudUniNetwork/RequestSuccess;", "RequestTask", "RequestTaskOnChunkReceivedListenerResult", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnChunkReceivedListenerResult;", "RequestTaskOnHeadersReceivedListenerResult", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnHeadersReceivedListenerResult;", "UploadFileFail", "Luts/sdk/modules/DCloudUniNetwork/UploadFileFail;", "UploadFileOptionFiles", "Luts/sdk/modules/DCloudUniNetwork/UploadFileOptionFiles;", "UploadFileOptions", "UploadFileSuccess", "Luts/sdk/modules/DCloudUniNetwork/UploadFileSuccess;", "UploadTask", "uni-network_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniNetworkKt {
    private static final Function1<UploadFileOptions, UploadTask> uploadFile = IndexKt.getUploadFile();
    private static final Function1<DownloadFileOptions, DownloadTask> downloadFile = IndexKt.getDownloadFile();

    public static final /* synthetic */ <T> RequestTask request(RequestOptions<T> options) {
        Intrinsics.checkNotNullParameter(options, "options");
        UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
        Intrinsics.needClassReification();
        Type type = new TypeToken<T>() { // from class: io.dcloud.uniapp.extapi.UniNetworkKt$request$$inlined$getGenericType$1
        }.getType();
        Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<T>(){}.type");
        UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
        Intrinsics.needClassReification();
        String name = new TypeToken<T>() { // from class: io.dcloud.uniapp.extapi.UniNetworkKt$request$$inlined$getGenericClassName$1
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
}
