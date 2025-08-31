package uts.sdk.modules.DCloudUniNetwork;

import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.UTSJSONObject;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0005H&R=\u0010\u0002\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004j\u0002`\n0\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkUploadFileListener;", "", "progressListeners", "Ljava/util/ArrayList;", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniNetwork/OnProgressUpdateResult;", "Lkotlin/ParameterName;", "name", "result", "", "Luts/sdk/modules/DCloudUniNetwork/UploadFileProgressUpdateCallback;", "getProgressListeners", "()Ljava/util/ArrayList;", "setProgressListeners", "(Ljava/util/ArrayList;)V", "onComplete", AbsoluteConst.JSON_KEY_OPTION, "Lio/dcloud/uts/UTSJSONObject;", "onProgress", "progressUpdate", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface NetworkUploadFileListener {
    ArrayList<Function1<OnProgressUpdateResult, Unit>> getProgressListeners();

    void onComplete(UTSJSONObject option);

    void onProgress(OnProgressUpdateResult progressUpdate);

    void setProgressListeners(ArrayList<Function1<OnProgressUpdateResult, Unit>> arrayList);
}
