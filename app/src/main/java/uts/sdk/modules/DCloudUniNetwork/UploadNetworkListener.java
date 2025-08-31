package uts.sdk.modules.DCloudUniNetwork;

import android.os.Looper;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uniapp.SourceError;
import io.dcloud.uts.UTSJSONObject;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\nH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R?\u0010\u0007\u001a'\u0012#\u0012!\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/UploadNetworkListener;", "Luts/sdk/modules/DCloudUniNetwork/NetworkUploadFileListener;", "param", "Luts/sdk/modules/DCloudUniNetwork/UploadFileOptions;", "(Luts/sdk/modules/DCloudUniNetwork/UploadFileOptions;)V", "looper", "Landroid/os/Looper;", "progressListeners", "Ljava/util/ArrayList;", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniNetwork/OnProgressUpdateResult;", "Lkotlin/ParameterName;", "name", "result", "", "Luts/sdk/modules/DCloudUniNetwork/UploadFileProgressUpdateCallback;", "getProgressListeners", "()Ljava/util/ArrayList;", "setProgressListeners", "(Ljava/util/ArrayList;)V", "onComplete", AbsoluteConst.JSON_KEY_OPTION, "Lio/dcloud/uts/UTSJSONObject;", "onProgress", "progressUpdate", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UploadNetworkListener implements NetworkUploadFileListener {
    private Looper looper;
    private UploadFileOptions param;
    private ArrayList<Function1<OnProgressUpdateResult, Unit>> progressListeners;

    @Override // uts.sdk.modules.DCloudUniNetwork.NetworkUploadFileListener
    public ArrayList<Function1<OnProgressUpdateResult, Unit>> getProgressListeners() {
        return this.progressListeners;
    }

    @Override // uts.sdk.modules.DCloudUniNetwork.NetworkUploadFileListener
    public void setProgressListeners(ArrayList<Function1<OnProgressUpdateResult, Unit>> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.progressListeners = arrayList;
    }

    public UploadNetworkListener(UploadFileOptions param) {
        Intrinsics.checkNotNullParameter(param, "param");
        this.progressListeners = new ArrayList<>();
        this.param = param;
        this.looper = Looper.myLooper();
    }

    @Override // uts.sdk.modules.DCloudUniNetwork.NetworkUploadFileListener
    public void onProgress(final OnProgressUpdateResult progressUpdate) {
        Intrinsics.checkNotNullParameter(progressUpdate, "progressUpdate");
        if (getProgressListeners().size() != 0) {
            new RunnableTask1(this.looper, new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.UploadNetworkListener.onProgress.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    UploadNetworkListener uploadNetworkListener = UploadNetworkListener.this;
                    OnProgressUpdateResult onProgressUpdateResult = progressUpdate;
                    for (int i = 0; i < uploadNetworkListener.getProgressListeners().size(); i++) {
                        Function1<OnProgressUpdateResult, Unit> function1 = uploadNetworkListener.getProgressListeners().get(i);
                        Intrinsics.checkNotNullExpressionValue(function1, "this.progressListeners.get(i)");
                        function1.invoke(onProgressUpdateResult);
                    }
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            }).execute();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, uts.sdk.modules.DCloudUniNetwork.UploadFileOptions] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, uts.sdk.modules.DCloudUniNetwork.UploadFileSuccess] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, uts.sdk.modules.DCloudUniNetwork.UploadFileFailImpl] */
    @Override // uts.sdk.modules.DCloudUniNetwork.NetworkUploadFileListener
    public void onComplete(UTSJSONObject option) throws NumberFormatException {
        String str;
        Intrinsics.checkNotNullParameter(option, "option");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = this.param;
        if (objectRef.element != 0) {
            if (option.get("errorMsg") != null) {
                Object obj = option.get("errorCode");
                Intrinsics.checkNotNull(obj);
                int i = Integer.parseInt((String) obj);
                final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = new UploadFileFailImpl(IndexKt.getErrcode(Integer.valueOf(i)));
                Object obj2 = option.get("cause");
                if (obj2 != null) {
                    ((UploadFileFailImpl) objectRef2.element).setCause((SourceError) obj2);
                }
                new RunnableTask1(this.looper, new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.UploadNetworkListener.onComplete.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (objectRef.element != null) {
                            Function1<UploadFileFail, Unit> fail = objectRef.element.getFail();
                            if (fail != null) {
                                fail.invoke(objectRef2.element);
                            }
                            Function1<Object, Unit> complete = objectRef.element.getComplete();
                            if (complete != null) {
                                complete.invoke(objectRef2.element);
                            }
                        }
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                }).execute();
                return;
            }
            Object obj3 = option.get("data");
            if (obj3 == null) {
                str = "";
            } else {
                str = (String) obj3;
            }
            Object obj4 = option.get("statusCode");
            Intrinsics.checkNotNull(obj4);
            int i2 = Integer.parseInt((String) obj4);
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = new UploadFileSuccess(str, Integer.valueOf(i2));
            new RunnableTask1(this.looper, new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.UploadNetworkListener.onComplete.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    if (objectRef.element != null) {
                        Function1<UploadFileSuccess, Unit> success = objectRef.element.getSuccess();
                        if (success != null) {
                            success.invoke(objectRef3.element);
                        }
                        Function1<Object, Unit> complete = objectRef.element.getComplete();
                        if (complete != null) {
                            complete.invoke(objectRef3.element);
                        }
                    }
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            }).execute();
        }
    }
}
