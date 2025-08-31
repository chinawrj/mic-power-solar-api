package io.dcloud.uniapp.extapi;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import uts.sdk.modules.DCloudUniPrompt.IndexKt;
import uts.sdk.modules.DCloudUniPrompt.ShowActionSheetOptions;
import uts.sdk.modules.DCloudUniPrompt.ShowLoadingOptions;
import uts.sdk.modules.DCloudUniPrompt.ShowModalOptions;
import uts.sdk.modules.DCloudUniPrompt.ShowToastOptions;

/* compiled from: uniPrompt.kt */
@Metadata(d1 = {"\u0000´\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u001b\u0010\u0000\u001a\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u001b\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0005\"0\u0010\t\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00020\nj\u0002`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"0\u0010\u0012\u001a!\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00020\nj\u0002`\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"0\u0010\u0016\u001a!\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00020\nj\u0002`\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011\"0\u0010\u001a\u001a!\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00020\nj\u0002`\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011*\n\u0010\u001e\"\u00020\u001f2\u00020\u001f*\u000e\u0010 \"\u0002`!2\u00060\"j\u0002`!*\n\u0010#\"\u00020$2\u00020$*\u000e\u0010%\"\u0002`&2\u00060'j\u0002`&*\u000e\u0010(\"\u0002`)2\u00060\u001fj\u0002`)*\n\u0010*\"\u00020\u000b2\u00020\u000b*\n\u0010+\"\u00020,2\u00020,*\u000e\u0010-\"\u0002`.2\u00060\u001fj\u0002`.*\n\u0010/\"\u00020\u00132\u00020\u0013*\n\u00100\"\u0002012\u000201*\u000e\u00102\"\u0002`32\u00060\u001fj\u0002`3*\n\u00104\"\u00020\u00172\u00020\u0017*\n\u00105\"\u0002062\u000206*\u000e\u00107\"\u0002`82\u00060\u001fj\u0002`8*\n\u00109\"\u00020\u001b2\u00020\u001b*\u000e\u0010:\"\u0002`;2\u00060\"j\u0002`;*\n\u0010<\"\u00020=2\u00020=¨\u0006>"}, d2 = {"hideLoading", "Lkotlin/Function0;", "", "Luts/sdk/modules/DCloudUniPrompt/HideLoading;", "getHideLoading", "()Lkotlin/jvm/functions/Function0;", "hideToast", "Luts/sdk/modules/DCloudUniPrompt/HideToast;", "getHideToast", "showActionSheet", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "Lkotlin/ParameterName;", "name", "options", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheet;", "getShowActionSheet", "()Lkotlin/jvm/functions/Function1;", "showLoading", "Luts/sdk/modules/DCloudUniPrompt/ShowLoadingOptions;", "Luts/sdk/modules/DCloudUniPrompt/ShowLoading;", "getShowLoading", "showModal", "Luts/sdk/modules/DCloudUniPrompt/ShowModalOptions;", "Luts/sdk/modules/DCloudUniPrompt/ShowModal;", "getShowModal", "showToast", "Luts/sdk/modules/DCloudUniPrompt/ShowToastOptions;", "Luts/sdk/modules/DCloudUniPrompt/ShowToast;", "getShowToast", "IPromptError", "Luts/sdk/modules/DCloudUniPrompt/IPromptError;", "Icon", "Luts/sdk/modules/DCloudUniPrompt/Icon;", "", "Popover", "Luts/sdk/modules/DCloudUniPrompt/Popover;", "PromptErrorCode", "Luts/sdk/modules/DCloudUniPrompt/PromptErrorCode;", "", "ShowActionSheetFail", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetFail;", "ShowActionSheetOptions", "ShowActionSheetSuccess", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetSuccess;", "ShowLoadingFail", "Luts/sdk/modules/DCloudUniPrompt/ShowLoadingFail;", "ShowLoadingOptions", "ShowLoadingSuccess", "Luts/sdk/modules/DCloudUniPrompt/ShowLoadingSuccess;", "ShowModalFail", "Luts/sdk/modules/DCloudUniPrompt/ShowModalFail;", "ShowModalOptions", "ShowModalSuccess", "Luts/sdk/modules/DCloudUniPrompt/ShowModalSuccess;", "ShowToastFail", "Luts/sdk/modules/DCloudUniPrompt/ShowToastFail;", "ShowToastOptions", "ShowToastPosition", "Luts/sdk/modules/DCloudUniPrompt/ShowToastPosition;", "ShowToastSuccess", "Luts/sdk/modules/DCloudUniPrompt/ShowToastSuccess;", "uni-prompt_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniPromptKt {
    private static final Function1<ShowToastOptions, Unit> showToast = IndexKt.getShowToast();
    private static final Function0<Unit> hideToast = IndexKt.getHideToast();
    private static final Function1<ShowLoadingOptions, Unit> showLoading = IndexKt.getShowLoading();
    private static final Function0<Unit> hideLoading = IndexKt.getHideLoading();
    private static final Function1<ShowModalOptions, Unit> showModal = IndexKt.getShowModal();
    private static final Function1<ShowActionSheetOptions, Unit> showActionSheet = IndexKt.getShowActionSheet();

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
}
