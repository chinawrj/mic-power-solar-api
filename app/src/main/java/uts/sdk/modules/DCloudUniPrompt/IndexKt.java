package uts.sdk.modules.DCloudUniPrompt;

import android.app.Activity;
import android.widget.Toast;
import com.facebook.common.util.UriUtil;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArrayKt;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import io.dcloud.uts.UTSTimerKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000ò\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010B\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u0011\u001a\u0010\u0010D\u001a\u00020\b2\b\u0010E\u001a\u0004\u0018\u000101\u001a\u0006\u0010F\u001a\u00020\b\u001a\u0006\u0010G\u001a\u00020\b\u001a\u0006\u0010H\u001a\u00020\b\u001a\u0006\u0010I\u001a\u00020\b\u001a\u0010\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u000101\u001a\u001e\u0010M\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u00192\u0006\u0010E\u001a\u0002012\u0006\u0010N\u001a\u000201\u001a\u001e\u0010O\u001a\u00020\b2\u0006\u0010C\u001a\u00020!2\u0006\u0010E\u001a\u0002012\u0006\u0010N\u001a\u000201\u001a\u000e\u0010P\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020Q\u001a\u000e\u0010R\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020S\u001a\u000e\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020\u0019\u001a\u000e\u0010V\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020W\u001a\u000e\u0010X\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u001d\u001a\u000e\u0010Y\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020Z\u001a\u000e\u0010[\u001a\u00020\b2\u0006\u0010C\u001a\u00020!\"\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"\u001b\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u001b\u0010\f\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"0\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u0010j\u0002`\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"0\u0010\u0018\u001a!\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u0010j\u0002`\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"0\u0010\u001c\u001a!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u0010j\u0002`\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017\"0\u0010 \u001a!\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u0010j\u0002`\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017\"\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)\"\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/\"\u001c\u00100\u001a\u0004\u0018\u000101X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105\"\u001c\u00106\u001a\u0004\u0018\u000107X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;\"\u001c\u0010<\u001a\u0004\u0018\u00010=X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A*\u0016\u0010\\\"\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0012\u0004\u0012\u00020\b0\u0007*\u0016\u0010]\"\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0012\u0004\u0012\u00020\b0\u0007*\n\u0010^\"\u0002012\u000201*\n\u0010_\"\u00020%2\u00020%*@\u0010`\"\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u0010*@\u0010a\"\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*\n\u0010d\"\u00020e2\u00020e*S\u0010f\"\u001d\u0012\u0013\u0012\u0011`g¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u001020\u0012&\u0012$0ej\u0011`g¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*@\u0010h\"\u001d\u0012\u0013\u0012\u00110i¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110i¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*@\u0010j\"\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u0010*@\u0010k\"\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*\n\u0010l\"\u00020e2\u00020e*S\u0010m\"\u001d\u0012\u0013\u0012\u0011`n¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u001020\u0012&\u0012$0ej\u0011`n¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*@\u0010o\"\u001d\u0012\u0013\u0012\u00110p¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110p¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*@\u0010q\"\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u0010*@\u0010r\"\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*\n\u0010s\"\u00020e2\u00020e*S\u0010t\"\u001d\u0012\u0013\u0012\u0011`u¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u001020\u0012&\u0012$0ej\u0011`u¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*@\u0010v\"\u001d\u0012\u0013\u0012\u00110w¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110w¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*@\u0010x\"\u001d\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u0010*@\u0010y\"\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*\n\u0010z\"\u00020e2\u00020e*S\u0010{\"\u001d\u0012\u0013\u0012\u0011`|¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u001020\u0012&\u0012$0ej\u0011`|¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010*\n\u0010}\"\u0002012\u000201*@\u0010~\"\u001d\u0012\u0013\u0012\u00110\u007f¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u00102\u001d\u0012\u0013\u0012\u00110\u007f¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020\b0\u0010¨\u0006\u0080\u0001"}, d2 = {"androidToast", "Landroid/widget/Toast;", "getAndroidToast", "()Landroid/widget/Toast;", "setAndroidToast", "(Landroid/widget/Toast;)V", "hideLoading", "Lkotlin/Function0;", "", "Luts/sdk/modules/DCloudUniPrompt/HideLoading;", "getHideLoading", "()Lkotlin/jvm/functions/Function0;", "hideToast", "Luts/sdk/modules/DCloudUniPrompt/HideToast;", "getHideToast", "showActionSheet", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "Lkotlin/ParameterName;", "name", "options", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheet;", "getShowActionSheet", "()Lkotlin/jvm/functions/Function1;", "showLoading", "Luts/sdk/modules/DCloudUniPrompt/ShowLoadingOptions;", "Luts/sdk/modules/DCloudUniPrompt/ShowLoading;", "getShowLoading", "showModal", "Luts/sdk/modules/DCloudUniPrompt/ShowModalOptions;", "Luts/sdk/modules/DCloudUniPrompt/ShowModal;", "getShowModal", "showToast", "Luts/sdk/modules/DCloudUniPrompt/ShowToastOptions;", "Luts/sdk/modules/DCloudUniPrompt/ShowToast;", "getShowToast", "timeout", "", "getTimeout", "()Ljava/lang/Number;", "setTimeout", "(Ljava/lang/Number;)V", "toast", "Luts/sdk/modules/DCloudUniPrompt/WaitingView;", "getToast", "()Luts/sdk/modules/DCloudUniPrompt/WaitingView;", "setToast", "(Luts/sdk/modules/DCloudUniPrompt/WaitingView;)V", "toastType", "", "getToastType", "()Ljava/lang/String;", "setToastType", "(Ljava/lang/String;)V", "uniActionSheet", "Luts/sdk/modules/DCloudUniPrompt/UniActionSheet;", "getUniActionSheet", "()Luts/sdk/modules/DCloudUniPrompt/UniActionSheet;", "setUniActionSheet", "(Luts/sdk/modules/DCloudUniPrompt/UniActionSheet;)V", "utsDialog", "Luts/sdk/modules/DCloudUniPrompt/UTSDialog;", "getUtsDialog", "()Luts/sdk/modules/DCloudUniPrompt/UTSDialog;", "setUtsDialog", "(Luts/sdk/modules/DCloudUniPrompt/UTSDialog;)V", "actionSheetImpl", "style", "closeToast", "type", "hideLoadingByJs", "hideLoadingImpl", "hideToastByJs", "hideToastImpl", "isValidColor", "", "colorStr", "makeLoading", IWXUserTrackAdapter.MONITOR_ERROR_MSG, "makeToast", "showActionSheetByJs", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptionsJSONObject;", "showLoadingByJs", "Luts/sdk/modules/DCloudUniPrompt/ShowLoadingOptionsJSONObject;", "showLoadingImpl", AbsoluteConst.JSON_KEY_OPTION, "showModalByJs", "Luts/sdk/modules/DCloudUniPrompt/ShowModalOptionsJSONObject;", "showModalImpl", "showToastByJs", "Luts/sdk/modules/DCloudUniPrompt/ShowToastOptionsJSONObject;", "showToastImpl", "HideLoading", "HideToast", "Icon", "PromptErrorCode", "ShowActionSheet", "ShowActionSheetCompleteCallback", "", UriUtil.LOCAL_RESOURCE_SCHEME, "ShowActionSheetFail", "Luts/sdk/modules/DCloudUniPrompt/IPromptError;", "ShowActionSheetFailCallback", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetFail;", "ShowActionSheetSuccessCallback", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetSuccess;", "ShowLoading", "ShowLoadingCompleteCallback", "ShowLoadingFail", "ShowLoadingFailCallback", "Luts/sdk/modules/DCloudUniPrompt/ShowLoadingFail;", "ShowLoadingSuccessCallback", "Luts/sdk/modules/DCloudUniPrompt/ShowLoadingSuccess;", "ShowModal", "ShowModalCompleteCallback", "ShowModalFail", "ShowModalFailCallback", "Luts/sdk/modules/DCloudUniPrompt/ShowModalFail;", "ShowModalSuccessCallback", "Luts/sdk/modules/DCloudUniPrompt/ShowModalSuccess;", "ShowToast", "ShowToastCompleteCallback", "ShowToastFail", "ShowToastFailCallback", "Luts/sdk/modules/DCloudUniPrompt/ShowToastFail;", "ShowToastPosition", "ShowToastSuccessCallback", "Luts/sdk/modules/DCloudUniPrompt/ShowToastSuccess;", "uni-prompt_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IndexKt {
    private static Toast androidToast;
    private static Number timeout;
    private static WaitingView toast;
    private static String toastType;
    private static UniActionSheet uniActionSheet;
    private static UTSDialog utsDialog;
    private static final Function1<ShowToastOptions, Unit> showToast = new Function1<ShowToastOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$showToast$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ShowToastOptions showToastOptions) {
            invoke2(showToastOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final ShowToastOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            UTSAndroid.INSTANCE.dispatchAsync("main", new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$showToast$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke2(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object obj) {
                    IndexKt.showToastImpl(options);
                }
            }, null);
        }
    };
    private static final Function0<Unit> hideToast = new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$hideToast$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            UTSAndroid.INSTANCE.dispatchAsync("main", new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$hideToast$1.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke2(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object obj) {
                    IndexKt.hideToastImpl();
                }
            }, null);
        }
    };
    private static final Function1<ShowLoadingOptions, Unit> showLoading = new Function1<ShowLoadingOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$showLoading$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ShowLoadingOptions showLoadingOptions) {
            invoke2(showLoadingOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final ShowLoadingOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            UTSAndroid.INSTANCE.dispatchAsync("main", new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$showLoading$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke2(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object obj) {
                    IndexKt.showLoadingImpl(options);
                }
            }, null);
        }
    };
    private static final Function0<Unit> hideLoading = new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$hideLoading$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            UTSAndroid.INSTANCE.dispatchAsync("main", new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$hideLoading$1.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke2(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object obj) {
                    IndexKt.hideLoadingImpl();
                }
            }, null);
        }
    };
    private static final Function1<ShowModalOptions, Unit> showModal = new Function1<ShowModalOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$showModal$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ShowModalOptions showModalOptions) {
            invoke2(showModalOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final ShowModalOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            UTSAndroid.INSTANCE.dispatchAsync("main", new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$showModal$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke2(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object obj) {
                    IndexKt.showModalImpl(options);
                }
            }, null);
        }
    };
    private static final Function1<ShowActionSheetOptions, Unit> showActionSheet = new Function1<ShowActionSheetOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$showActionSheet$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ShowActionSheetOptions showActionSheetOptions) {
            invoke2(showActionSheetOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final ShowActionSheetOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            UTSAndroid.INSTANCE.dispatchAsync("main", new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt$showActionSheet$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                    invoke2(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object obj) {
                    IndexKt.actionSheetImpl(options);
                }
            }, null);
        }
    };

    public static final Number getTimeout() {
        return timeout;
    }

    public static final void setTimeout(Number number) {
        timeout = number;
    }

    public static final WaitingView getToast() {
        return toast;
    }

    public static final void setToast(WaitingView waitingView) {
        toast = waitingView;
    }

    public static final String getToastType() {
        return toastType;
    }

    public static final void setToastType(String str) {
        toastType = str;
    }

    public static final void showToastImpl(ShowToastOptions style) {
        Intrinsics.checkNotNullParameter(style, "style");
        makeToast(style, "toast", "showToast");
    }

    public static final void hideToastImpl() {
        closeToast("toast");
    }

    public static final void showLoadingImpl(ShowLoadingOptions option) {
        Intrinsics.checkNotNullParameter(option, "option");
        makeLoading(option, "loading", "showLoading");
    }

    public static final void hideLoadingImpl() {
        closeToast("loading");
    }

    public static final void closeToast(String str) {
        if (str == null || str == toastType) {
            Number number = timeout;
            if (number != null) {
                Intrinsics.checkNotNull(number, "null cannot be cast to non-null type kotlin.Number");
                if (NumberKt.compareTo(number, (Number) 0) > 0) {
                    Number number2 = timeout;
                    Intrinsics.checkNotNull(number2, "null cannot be cast to non-null type kotlin.Number");
                    UTSTimerKt.clearTimeout(number2);
                    timeout = null;
                }
            }
            WaitingView waitingView = toast;
            if (waitingView != null) {
                Intrinsics.checkNotNull(waitingView, "null cannot be cast to non-null type uts.sdk.modules.DCloudUniPrompt.WaitingView");
                waitingView.close();
                toast = null;
            }
            WaitingView waitingView2 = toast;
            if (waitingView2 != null) {
                Intrinsics.checkNotNull(waitingView2, "null cannot be cast to non-null type uts.sdk.modules.DCloudUniPrompt.WaitingView");
                waitingView2.close();
                toast = null;
            }
            Toast toast2 = androidToast;
            if (toast2 != null) {
                Intrinsics.checkNotNull(toast2);
                toast2.cancel();
                androidToast = null;
            }
            toastType = null;
        }
    }

    public static final void makeLoading(ShowLoadingOptions style, String type, String errMsg) {
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(errMsg, "errMsg");
        closeToast(null);
        if (style.getTitle() == null) {
            PromptErrorImpl promptErrorImpl = new PromptErrorImpl((Number) 1001, "showLoading:title is null");
            Function1<IPromptError, Unit> fail = style.getFail();
            if (fail != null) {
                fail.invoke(promptErrorImpl);
            }
            Function1<Object, Unit> complete = style.getComplete();
            if (complete != null) {
                complete.invoke(promptErrorImpl);
                return;
            }
            return;
        }
        toastType = type;
        new UTSJSONObject();
        UTSJSONObject uTSJSONObject = new UTSJSONObject(style) { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.makeLoading.1
            private Boolean modal;
            private String name;
            private String back = "transmit";
            private String padding = "10";
            private String size = "16";

            {
                this.name = style.getTitle();
                this.modal = style.getMask();
            }

            public final String getName() {
                return this.name;
            }

            public final void setName(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.name = str;
            }

            public final Boolean getModal() {
                return this.modal;
            }

            public final void setModal(Boolean bool) {
                this.modal = bool;
            }

            public final String getBack() {
                return this.back;
            }

            public final void setBack(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.back = str;
            }

            public final String getPadding() {
                return this.padding;
            }

            public final void setPadding(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.padding = str;
            }

            public final String getSize() {
                return this.size;
            }

            public final void setSize(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.size = str;
            }
        };
        uTSJSONObject.set("width", "140");
        uTSJSONObject.set("height", "112");
        WaitingView waitingView = new WaitingView(UTSAndroid.INSTANCE.getTopPageActivity(), uTSJSONObject, UTSAndroid.INSTANCE.getTopPageView());
        toast = waitingView;
        waitingView.showWaiting();
        UTSAndroid.INSTANCE.onAppActivityDestroy(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.makeLoading.2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                WaitingView toast2 = IndexKt.getToast();
                if (toast2 != null) {
                    toast2.close();
                }
                IndexKt.setToast(null);
            }
        });
        ShowLoadingSuccess showLoadingSuccess = new ShowLoadingSuccess();
        Function1<ShowLoadingSuccess, Unit> success = style.getSuccess();
        if (success != null) {
            success.invoke(showLoadingSuccess);
        }
        Function1<Object, Unit> complete2 = style.getComplete();
        if (complete2 != null) {
            complete2.invoke(showLoadingSuccess);
        }
    }

    public static final Toast getAndroidToast() {
        return androidToast;
    }

    public static final void setAndroidToast(Toast toast2) {
        androidToast = toast2;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.String] */
    public static final void makeToast(ShowToastOptions style, String type, String errMsg) {
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(errMsg, "errMsg");
        closeToast(null);
        if (style.getTitle() == null || style.getTitle().length() == 0) {
            PromptErrorImpl promptErrorImpl = new PromptErrorImpl((Number) 1001, "showLoading:title is null");
            Function1<IPromptError, Unit> fail = style.getFail();
            if (fail != null) {
                fail.invoke(promptErrorImpl);
            }
            Function1<Object, Unit> complete = style.getComplete();
            if (complete != null) {
                complete.invoke(promptErrorImpl);
                return;
            }
            return;
        }
        toastType = type;
        if (CollectionsKt.indexOf((List<? extends String>) UTSArrayKt._uA("top", "center", "bottom"), style.getPosition()) >= 0) {
            androidToast = Toast.makeText(UTSAndroid.INSTANCE.getAppContext(), style.getTitle(), 0);
            String position = style.getPosition();
            if (position != null) {
                int iHashCode = position.hashCode();
                if (iHashCode != -1383228885) {
                    if (iHashCode != -1364013995) {
                        if (iHashCode == 115029 && position.equals("top")) {
                            Toast toast2 = androidToast;
                            Intrinsics.checkNotNull(toast2);
                            Toast toast3 = androidToast;
                            Intrinsics.checkNotNull(toast3);
                            int xOffset = toast3.getXOffset();
                            Toast toast4 = androidToast;
                            Intrinsics.checkNotNull(toast4);
                            toast2.setGravity(48, xOffset, toast4.getYOffset());
                        }
                    } else if (position.equals("center")) {
                        Toast toast5 = androidToast;
                        Intrinsics.checkNotNull(toast5);
                        toast5.setGravity(17, 0, 0);
                    }
                } else if (position.equals("bottom")) {
                    Toast toast6 = androidToast;
                    Intrinsics.checkNotNull(toast6);
                    Toast toast7 = androidToast;
                    Intrinsics.checkNotNull(toast7);
                    int xOffset2 = toast7.getXOffset();
                    Toast toast8 = androidToast;
                    Intrinsics.checkNotNull(toast8);
                    toast6.setGravity(80, xOffset2, toast8.getYOffset());
                }
            }
            Toast toast9 = androidToast;
            Intrinsics.checkNotNull(toast9);
            toast9.show();
            ShowToastSuccess showToastSuccess = new ShowToastSuccess();
            Function1<ShowToastSuccess, Unit> success = style.getSuccess();
            if (success != null) {
                success.invoke(showToastSuccess);
            }
            Function1<Object, Unit> complete2 = style.getComplete();
            if (complete2 != null) {
                complete2.invoke(showToastSuccess);
                return;
            }
            return;
        }
        new UTSJSONObject();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = style.getIcon();
        if (objectRef.element == 0 || UTSArrayKt._uA(WXImage.SUCCEED, "loading", "error", "none").indexOf(objectRef.element) < 0) {
            objectRef.element = WXImage.SUCCEED;
        }
        UTSJSONObject uTSJSONObject = new UTSJSONObject(style) { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.makeToast.1
            private Boolean modal;
            private String name;
            private String back = "transmit";
            private String padding = "10";
            private String size = "16";

            {
                this.name = style.getTitle();
                this.modal = style.getMask();
            }

            public final String getName() {
                return this.name;
            }

            public final void setName(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.name = str;
            }

            public final Boolean getModal() {
                return this.modal;
            }

            public final void setModal(Boolean bool) {
                this.modal = bool;
            }

            public final String getBack() {
                return this.back;
            }

            public final void setBack(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.back = str;
            }

            public final String getPadding() {
                return this.padding;
            }

            public final void setPadding(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.padding = str;
            }

            public final String getSize() {
                return this.size;
            }

            public final void setSize(String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.size = str;
            }
        };
        if ((style.getImage() == null || Intrinsics.areEqual(style.getImage(), "")) && Intrinsics.areEqual(objectRef.element, "none")) {
            uTSJSONObject.set("loading", new UTSJSONObject() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.makeToast.2
                private String display = "none";

                public final String getDisplay() {
                    return this.display;
                }

                public final void setDisplay(String str) {
                    Intrinsics.checkNotNullParameter(str, "<set-?>");
                    this.display = str;
                }
            });
        } else {
            uTSJSONObject.set("width", "140");
            uTSJSONObject.set("height", "112");
        }
        if (style.getImage() != null && !Intrinsics.areEqual(style.getImage(), "")) {
            uTSJSONObject.set("loading", new UTSJSONObject(style) { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.makeToast.3
                private String display = AbsoluteConst.JSON_VALUE_BLOCK;
                private String height = "55";
                private String icon;

                {
                    String image = style.getImage();
                    Intrinsics.checkNotNull(image);
                    this.icon = image;
                }

                public final String getDisplay() {
                    return this.display;
                }

                public final void setDisplay(String str) {
                    Intrinsics.checkNotNullParameter(str, "<set-?>");
                    this.display = str;
                }

                public final String getHeight() {
                    return this.height;
                }

                public final void setHeight(String str) {
                    Intrinsics.checkNotNullParameter(str, "<set-?>");
                    this.height = str;
                }

                public final String getIcon() {
                    return this.icon;
                }

                public final void setIcon(String str) {
                    Intrinsics.checkNotNullParameter(str, "<set-?>");
                    this.icon = str;
                }
            });
        } else if (UTSArrayKt._uA(WXImage.SUCCEED, "error").indexOf(objectRef.element) >= 0) {
            uTSJSONObject.set("loading", new UTSJSONObject(objectRef) { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.makeToast.4
                private String display = AbsoluteConst.JSON_VALUE_BLOCK;
                private String height = "36";
                private String icon;

                {
                    String str;
                    if (Intrinsics.areEqual(objectRef.element, WXImage.SUCCEED)) {
                        str = "successIcon";
                    } else {
                        str = "errorIcon";
                    }
                    this.icon = str;
                }

                public final String getDisplay() {
                    return this.display;
                }

                public final void setDisplay(String str) {
                    Intrinsics.checkNotNullParameter(str, "<set-?>");
                    this.display = str;
                }

                public final String getHeight() {
                    return this.height;
                }

                public final void setHeight(String str) {
                    Intrinsics.checkNotNullParameter(str, "<set-?>");
                    this.height = str;
                }

                public final String getIcon() {
                    return this.icon;
                }

                public final void setIcon(String str) {
                    Intrinsics.checkNotNullParameter(str, "<set-?>");
                    this.icon = str;
                }
            });
        }
        WaitingView waitingView = new WaitingView(UTSAndroid.INSTANCE.getTopPageActivity(), uTSJSONObject, UTSAndroid.INSTANCE.getTopPageView());
        toast = waitingView;
        waitingView.showWaiting();
        UTSAndroid.INSTANCE.onAppActivityDestroy(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.makeToast.5
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                WaitingView toast10 = IndexKt.getToast();
                if (toast10 != null) {
                    toast10.close();
                }
                IndexKt.setToast(null);
            }
        });
        Number duration = style.getDuration();
        if (duration == null || NumberKt.compareTo(duration, (Number) 0) <= 0) {
            duration = (Number) 1500;
        }
        if (type != "loading") {
            timeout = UTSTimerKt.setTimeout(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.makeToast.6
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    WaitingView toast10 = IndexKt.getToast();
                    if (toast10 != null) {
                        toast10.close();
                    }
                }
            }, duration);
        }
        ShowToastSuccess showToastSuccess2 = new ShowToastSuccess();
        Function1<ShowToastSuccess, Unit> success2 = style.getSuccess();
        if (success2 != null) {
            success2.invoke(showToastSuccess2);
        }
        Function1<Object, Unit> complete3 = style.getComplete();
        if (complete3 != null) {
            complete3.invoke(showToastSuccess2);
        }
    }

    public static final UTSDialog getUtsDialog() {
        return utsDialog;
    }

    public static final void setUtsDialog(UTSDialog uTSDialog) {
        utsDialog = uTSDialog;
    }

    public static final void showModalImpl(ShowModalOptions style) {
        Intrinsics.checkNotNullParameter(style, "style");
        UTSDialog uTSDialog = utsDialog;
        if (uTSDialog != null) {
            if (uTSDialog != null) {
                uTSDialog.dismiss();
            }
            utsDialog = null;
        }
    }

    public static final UniActionSheet getUniActionSheet() {
        return uniActionSheet;
    }

    public static final void setUniActionSheet(UniActionSheet uniActionSheet2) {
        uniActionSheet = uniActionSheet2;
    }

    public static final void actionSheetImpl(ShowActionSheetOptions style) {
        Intrinsics.checkNotNullParameter(style, "style");
        if (style.getItemList() == null || NumberKt.compareTo(style.getItemList().getLength(), (Number) 1) < 0) {
            PromptErrorImpl promptErrorImpl = new PromptErrorImpl((Number) 1001, "showActionSheet:fail parameter error: parameter.itemList should have at least 1 item");
            Function1<IPromptError, Unit> fail = style.getFail();
            if (fail != null) {
                fail.invoke(promptErrorImpl);
            }
            Function1<Object, Unit> complete = style.getComplete();
            if (complete != null) {
                complete.invoke(promptErrorImpl);
                return;
            }
            return;
        }
        if (style.getItemList().size() > 6) {
            PromptErrorImpl promptErrorImpl2 = new PromptErrorImpl((Number) 1001, "showActionSheet:fail parameter error: itemList should not be large than 6");
            Function1<IPromptError, Unit> fail2 = style.getFail();
            if (fail2 != null) {
                fail2.invoke(promptErrorImpl2);
            }
            Function1<Object, Unit> complete2 = style.getComplete();
            if (complete2 != null) {
                complete2.invoke(promptErrorImpl2);
                return;
            }
            return;
        }
        UniActionSheet uniActionSheet2 = uniActionSheet;
        if (uniActionSheet2 != null) {
            if (uniActionSheet2 != null) {
                uniActionSheet2.dismiss();
            }
            uniActionSheet = null;
        }
        if (UTSAndroid.INSTANCE.getTopPageActivity() == null) {
            return;
        }
        Activity topPageActivity = UTSAndroid.INSTANCE.getTopPageActivity();
        Intrinsics.checkNotNull(topPageActivity);
        if (topPageActivity.isFinishing()) {
            return;
        }
        Activity topPageActivity2 = UTSAndroid.INSTANCE.getTopPageActivity();
        Intrinsics.checkNotNull(topPageActivity2);
        UniActionSheet uniActionSheet3 = new UniActionSheet(topPageActivity2, style);
        uniActionSheet = uniActionSheet3;
        uniActionSheet3.show();
        UTSAndroid.INSTANCE.onAppActivityDestroy(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.actionSheetImpl.1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                UniActionSheet uniActionSheet4 = IndexKt.getUniActionSheet();
                if (uniActionSheet4 != null) {
                    uniActionSheet4.dismiss();
                }
                IndexKt.setUniActionSheet(null);
            }
        });
    }

    public static final boolean isValidColor(String str) {
        return str != null && str.length() == 7 && StringsKt.startsWith$default(str, "#", false, 2, (Object) null);
    }

    public static final Function1<ShowToastOptions, Unit> getShowToast() {
        return showToast;
    }

    public static final Function0<Unit> getHideToast() {
        return hideToast;
    }

    public static final Function1<ShowLoadingOptions, Unit> getShowLoading() {
        return showLoading;
    }

    public static final Function0<Unit> getHideLoading() {
        return hideLoading;
    }

    public static final Function1<ShowModalOptions, Unit> getShowModal() {
        return showModal;
    }

    public static final Function1<ShowActionSheetOptions, Unit> getShowActionSheet() {
        return showActionSheet;
    }

    public static final void showToastByJs(final ShowToastOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        showToast.invoke(new ShowToastOptions(options.getTitle(), options.getIcon(), options.getImage(), options.getMask(), options.getDuration(), options.getPosition(), new Function1<ShowToastSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showToastByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ShowToastSuccess showToastSuccess) throws SecurityException {
                invoke2(showToastSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ShowToastSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<IPromptError, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showToastByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IPromptError iPromptError) throws SecurityException {
                invoke2(iPromptError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IPromptError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showToastByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }));
    }

    public static final void hideToastByJs() {
        hideToast.invoke();
    }

    public static final void showLoadingByJs(final ShowLoadingOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        showLoading.invoke(new ShowLoadingOptions(options.getTitle(), options.getMask(), new Function1<ShowLoadingSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showLoadingByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ShowLoadingSuccess showLoadingSuccess) throws SecurityException {
                invoke2(showLoadingSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ShowLoadingSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<IPromptError, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showLoadingByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IPromptError iPromptError) throws SecurityException {
                invoke2(iPromptError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IPromptError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showLoadingByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }));
    }

    public static final void hideLoadingByJs() {
        hideLoading.invoke();
    }

    public static final void showModalByJs(final ShowModalOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        showModal.invoke(new ShowModalOptions(options.getTitle(), options.getContent(), options.getShowCancel(), options.getCancelText(), options.getCancelColor(), options.getConfirmText(), options.getConfirmColor(), options.getEditable(), options.getPlaceholderText(), new Function1<ShowModalSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showModalByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ShowModalSuccess showModalSuccess) throws SecurityException {
                invoke2(showModalSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ShowModalSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<IPromptError, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showModalByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IPromptError iPromptError) throws SecurityException {
                invoke2(iPromptError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IPromptError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showModalByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }));
    }

    public static final void showActionSheetByJs(final ShowActionSheetOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        showActionSheet.invoke(new ShowActionSheetOptions(options.getTitle(), options.getAlertText(), options.getItemList(), options.getItemColor(), options.getPopover(), new Function1<ShowActionSheetSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showActionSheetByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ShowActionSheetSuccess showActionSheetSuccess) throws SecurityException {
                invoke2(showActionSheetSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ShowActionSheetSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<IPromptError, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showActionSheetByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IPromptError iPromptError) throws SecurityException {
                invoke2(iPromptError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IPromptError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrompt.IndexKt.showActionSheetByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }));
    }
}
