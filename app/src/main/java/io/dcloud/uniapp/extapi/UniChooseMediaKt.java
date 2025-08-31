package io.dcloud.uniapp.extapi;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import uts.sdk.modules.DCloudUniChooseMedia.ChooseMediaOptions;
import uts.sdk.modules.DCloudUniChooseMedia.IndexKt;

/* compiled from: uniChooseMedia.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\"0\u0010\u0000\u001a!\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t*\u000e\u0010\n\"\u0002`\u000b2\u00060\fj\u0002`\u000b*\u000e\u0010\r\"\u0002`\u000e2\u00060\u000fj\u0002`\u000e*\u000e\u0010\u0010\"\u0002`\u00112\u00060\u0012j\u0002`\u0011*\n\u0010\u0013\"\u00020\u00022\u00020\u0002*\u000e\u0010\u0014\"\u0002`\u00152\u00060\u0012j\u0002`\u0015*\n\u0010\u0016\"\u00020\u00172\u00020\u0017*\n\u0010\u0018\"\u00020\u00192\u00020\u0019*\n\u0010\u001a\"\u00020\u000f2\u00020\u000f¨\u0006\u001b"}, d2 = {"chooseMedia", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaOptions;", "Lkotlin/ParameterName;", "name", "options", "", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMedia;", "getChooseMedia", "()Lkotlin/jvm/functions/Function1;", "ChooseMediaErrorCode", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaErrorCode;", "", "ChooseMediaFail", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaFail;", "Luts/sdk/modules/DCloudUniChooseMedia/IChooseMediaError;", "ChooseMediaFileType", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaFileType;", "", "ChooseMediaOptions", "ChooseMediaPageOrientation", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaPageOrientation;", "ChooseMediaSuccess", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaSuccess;", "ChooseMediaTempFile", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaTempFile;", "IChooseMediaError", "uni-chooseMedia_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniChooseMediaKt {
    private static final Function1<ChooseMediaOptions, Unit> chooseMedia = IndexKt.getChooseMedia();

    public static final Function1<ChooseMediaOptions, Unit> getChooseMedia() {
        return chooseMedia;
    }
}
