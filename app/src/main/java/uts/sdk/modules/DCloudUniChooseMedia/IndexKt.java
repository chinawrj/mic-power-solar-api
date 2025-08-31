package uts.sdk.modules.DCloudUniChooseMedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.dmcbig.mediapicker.PickerConfig;
import com.hjq.permissions.Permission;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.uts.JSON;
import io.dcloud.uts.Map;
import io.dcloud.uts.Math;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSArrayKt;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import uts.sdk.modules.DCloudUniMedia.Media;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010+\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0010\u001a\u000e\u0010,\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020-\u001a\u0016\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u0001\u001a\u000e\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006\u001a\u0006\u00104\u001a\u00020\u0001\u001a\u0018\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u00020\u0006\u001a\u000e\u00109\u001a\u00020:2\u0006\u00107\u001a\u00020\u0001\u001a\u0018\u0010;\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u00012\u0006\u00108\u001a\u00020\u0006\u001a\u0016\u0010<\u001a\u00020\u00062\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010>\u001a\u0016\u0010?\u001a\u00020\u00062\u000e\u0010@\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`A\u001a\u001a\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020\u00012\b\u0010E\u001a\u0004\u0018\u00010\u0001\u001ak\u0010F\u001a\u00020\u00142\u0006\u0010G\u001a\u00020\u00062\u0006\u0010H\u001a\u00020/2\u0006\u0010I\u001a\u00020\u00012K\u0010J\u001aG\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(H\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u00140\u001b\u001a\u001e\u0010L\u001a\u00020\u00142\u0006\u0010M\u001a\u00020\u00102\u0006\u0010G\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u0006\u001a\u0016\u0010O\u001a\u00020\u00142\u0006\u0010M\u001a\u00020\u00102\u0006\u0010H\u001a\u00020/\u001a\u0016\u0010P\u001a\u00020\u00142\u0006\u0010M\u001a\u00020\u00102\u0006\u0010H\u001a\u00020/\u001aR\u0010Q\u001a\u00020\u00142\u0006\u0010G\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010H\u001a\u00020/2\u000e\u0010R\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010>2\b\u0010S\u001a\u0004\u0018\u00010T2\u000e\u0010U\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`A2\b\u0010V\u001a\u0004\u0018\u00010\u0001\u001a\u001a\u0010W\u001a\u00020/2\b\u0010X\u001a\u0004\u0018\u00010Y2\b\u0010E\u001a\u0004\u0018\u00010\u0001\u001a[\u0010Z\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00102K\u0010J\u001aG\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110/¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(H\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020\u00140\u001b\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0014\u0010\t\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0014\u0010\f\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0003\"0\u0010\u000e\u001a!\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u000fj\u0002`\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0011\u0010\u0018\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0003\"c\u0010\u001a\u001aK\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\u001f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\"c\u0010%\u001aK\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\u001f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$\"c\u0010(\u001aK\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\u001f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\"\"\u0004\b*\u0010$*@\u0010[\"\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u000f2\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u000f*@\u0010\\\"\u001d\u0012\u0013\u0012\u00110T¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(]\u0012\u0004\u0012\u00020\u00140\u000f2\u001d\u0012\u0013\u0012\u00110T¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(]\u0012\u0004\u0012\u00020\u00140\u000f*\n\u0010^\"\u00020\u00062\u00020\u0006*\n\u0010_\"\u00020`2\u00020`*S\u0010a\"\u001d\u0012\u0013\u0012\u0011`b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(]\u0012\u0004\u0012\u00020\u00140\u000f20\u0012&\u0012$0`j\u0011`b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(]¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(]\u0012\u0004\u0012\u00020\u00140\u000f*\n\u0010c\"\u00020\u00012\u00020\u0001*\n\u0010d\"\u00020\u00012\u00020\u0001*@\u0010e\"\u001d\u0012\u0013\u0012\u00110f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(]\u0012\u0004\u0012\u00020\u00140\u000f2\u001d\u0012\u0013\u0012\u00110f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(]\u0012\u0004\u0012\u00020\u00140\u000f¨\u0006g"}, d2 = {"ASSETS_PATH", "", "getASSETS_PATH", "()Ljava/lang/String;", "ChooseMediaUniErrors", "Lio/dcloud/uts/Map;", "", "getChooseMediaUniErrors", "()Lio/dcloud/uts/Map;", "GALLERY_MEDIA_PICKER_RESULT", "getGALLERY_MEDIA_PICKER_RESULT", "()Ljava/lang/Number;", "UniError_ChooseMedia", "getUniError_ChooseMedia", "chooseMedia", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaOptions;", "Lkotlin/ParameterName;", "name", "options", "", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMedia;", "getChooseMedia", "()Lkotlin/jvm/functions/Function1;", "mediaCachePath", "getMediaCachePath", "openMediaFunction", "Lkotlin/Function3;", "", WXModule.REQUEST_CODE, WXModule.RESULT_CODE, "Landroid/content/Intent;", "data", "getOpenMediaFunction", "()Lkotlin/jvm/functions/Function3;", "setOpenMediaFunction", "(Lkotlin/jvm/functions/Function3;)V", "takeCameraFunction", "getTakeCameraFunction", "setTakeCameraFunction", "takeVideoFunction", "getTakeVideoFunction", "setTakeVideoFunction", "chooseMediaAll", "chooseMediaByJs", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaOptionsJSONObject;", "copyFile", "", "fromFilePath", "toFilePath", "formatNumber", "input", "getGlobalConfig", "getMediaFileByType", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaTempFile;", AbsoluteConst.XML_PATH, "mimeType", "getMediaInfo", "", "getMediaTempFile", "getMediaType", "types", "Lio/dcloud/uts/UTSArray;", "getOrientation", "reassignedPageOrientation", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaPageOrientation;", "getVideoMetadata", "Lio/dcloud/uts/UTSJSONObject;", "src", "filePath", "onMediaTypeSelect", "count", "compressed", AbsoluteConst.XML_ITEM, "onSourceTypeSelect", "index", "openAlbumForMedia", AbsoluteConst.JSON_KEY_OPTION, "type", "openCameraForMediaImage", "openCameraForMediaVideo", "openGalleryActivity", "sizeType", "crop", "", "pageOrientation", "useSystem", "saveBitmapToLocalPath", "bitmap", "Landroid/graphics/Bitmap;", "uniChooseMedia", "ChooseMedia", "ChooseMediaCompleteCallback", WXBridgeManager.METHOD_CALLBACK, "ChooseMediaErrorCode", "ChooseMediaFail", "Luts/sdk/modules/DCloudUniChooseMedia/IChooseMediaError;", "ChooseMediaFailCallback", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaFail;", "ChooseMediaFileType", "ChooseMediaPageOrientation", "ChooseMediaSuccessCallback", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaSuccess;", "uni-chooseMedia_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IndexKt {
    private static final String UniError_ChooseMedia = "uni-chooseMedia";
    private static Function3<? super Integer, ? super Integer, ? super Intent, Unit> openMediaFunction;
    private static Function3<? super Integer, ? super Integer, ? super Intent, Unit> takeCameraFunction;
    private static Function3<? super Integer, ? super Integer, ? super Intent, Unit> takeVideoFunction;
    private static final Map<Number, String> ChooseMediaUniErrors = new Map<>((UTSArray<UTSArray<Object>>) UTSArrayKt._uA(UTSArrayKt._uA(1101001, "user cancel"), UTSArrayKt._uA(1101005, DOMException.MSG_NO_PERMISSION), UTSArrayKt._uA(1101006, "save error"), UTSArrayKt._uA(1101008, "camera error")));
    private static final String mediaCachePath = UTSAndroid.INSTANCE.getAppCachePath() + "uni-media/";
    private static final String ASSETS_PATH = "/android_asset/";
    private static final Function1<ChooseMediaOptions, Unit> chooseMedia = new Function1<ChooseMediaOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt$chooseMedia$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ChooseMediaOptions chooseMediaOptions) {
            invoke2(chooseMediaOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(ChooseMediaOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            IndexKt.chooseMediaAll(options);
        }
    };
    private static final Number GALLERY_MEDIA_PICKER_RESULT = (Number) 1004;

    public static final String getUniError_ChooseMedia() {
        return UniError_ChooseMedia;
    }

    public static final Map<Number, String> getChooseMediaUniErrors() {
        return ChooseMediaUniErrors;
    }

    public static final String getMediaCachePath() {
        return mediaCachePath;
    }

    public static final String getASSETS_PATH() {
        return ASSETS_PATH;
    }

    public static final Function1<ChooseMediaOptions, Unit> getChooseMedia() {
        return chooseMedia;
    }

    public static final void chooseMediaAll(final ChooseMediaOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        uniChooseMedia(options, new Function3<Number, Boolean, Number, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.chooseMediaAll.1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Number number, Boolean bool, Number number2) {
                invoke(number, bool.booleanValue(), number2);
                return Unit.INSTANCE;
            }

            public final void invoke(final Number count, final boolean z, final Number index) {
                Intrinsics.checkNotNullParameter(count, "count");
                Intrinsics.checkNotNullParameter(index, "index");
                if (NumberKt.numberEquals(index, 1)) {
                    if (Build.VERSION.SDK_INT <= 32) {
                        Activity uniActivity = UTSAndroid.INSTANCE.getUniActivity();
                        Intrinsics.checkNotNull(uniActivity);
                        if (uniActivity.getApplicationInfo().targetSdkVersion < 33) {
                            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
                            Activity uniActivity2 = UTSAndroid.INSTANCE.getUniActivity();
                            Intrinsics.checkNotNull(uniActivity2);
                            UTSArray uTSArray_uA = UTSArrayKt._uA(Permission.READ_EXTERNAL_STORAGE);
                            final ChooseMediaOptions chooseMediaOptions = options;
                            Function2<Boolean, UTSArray<String>, Unit> function2 = new Function2<Boolean, UTSArray<String>, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.chooseMediaAll.1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, UTSArray<String> uTSArray) {
                                    invoke(bool.booleanValue(), uTSArray);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(boolean z2, UTSArray<String> b) {
                                    Intrinsics.checkNotNullParameter(b, "b");
                                    ChooseMediaOptions chooseMediaOptions2 = chooseMediaOptions;
                                    IndexKt.openAlbumForMedia(chooseMediaOptions2, count, IndexKt.getMediaType(chooseMediaOptions2.getMediaType()));
                                }
                            };
                            final ChooseMediaOptions chooseMediaOptions2 = options;
                            UTSAndroid.requestSystemPermission$default(uTSAndroid, uniActivity2, uTSArray_uA, function2, new Function2<Boolean, UTSArray<String>, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.chooseMediaAll.1.2
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, UTSArray<String> uTSArray) {
                                    invoke(bool.booleanValue(), uTSArray);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(boolean z2, UTSArray<String> b) {
                                    Intrinsics.checkNotNullParameter(b, "b");
                                    ChooseMediaErrorImpl chooseMediaErrorImpl = new ChooseMediaErrorImpl((Number) 1101005, IndexKt.getUniError_ChooseMedia());
                                    Function1<IChooseMediaError, Unit> fail = chooseMediaOptions2.getFail();
                                    if (fail != null) {
                                        fail.invoke(chooseMediaErrorImpl);
                                    }
                                    Function1<Object, Unit> complete = chooseMediaOptions2.getComplete();
                                    if (complete != null) {
                                        complete.invoke(chooseMediaErrorImpl);
                                    }
                                }
                            }, false, 16, null);
                            return;
                        }
                    }
                    ChooseMediaOptions chooseMediaOptions3 = options;
                    IndexKt.openAlbumForMedia(chooseMediaOptions3, count, IndexKt.getMediaType(chooseMediaOptions3.getMediaType()));
                    return;
                }
                UTSArray uTSArray_uA2 = UTSArrayKt._uA(Permission.CAMERA);
                Intrinsics.checkNotNull(uTSArray_uA2, "null cannot be cast to non-null type io.dcloud.uts.UTSArray<kotlin.String>");
                UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
                Activity uniActivity3 = UTSAndroid.INSTANCE.getUniActivity();
                Intrinsics.checkNotNull(uniActivity3);
                final ChooseMediaOptions chooseMediaOptions4 = options;
                Function2<Boolean, UTSArray<String>, Unit> function22 = new Function2<Boolean, UTSArray<String>, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.chooseMediaAll.1.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, UTSArray<String> uTSArray) {
                        invoke(bool.booleanValue(), uTSArray);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2, UTSArray<String> b) {
                        Intrinsics.checkNotNullParameter(b, "b");
                        if (NumberKt.numberEquals(index, 0)) {
                            IndexKt.openCameraForMediaImage(chooseMediaOptions4, z);
                        } else if (NumberKt.numberEquals(index, 2)) {
                            IndexKt.openCameraForMediaVideo(chooseMediaOptions4, z);
                        }
                    }
                };
                final ChooseMediaOptions chooseMediaOptions5 = options;
                UTSAndroid.requestSystemPermission$default(uTSAndroid2, uniActivity3, uTSArray_uA2, function22, new Function2<Boolean, UTSArray<String>, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.chooseMediaAll.1.4
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, UTSArray<String> uTSArray) {
                        invoke(bool.booleanValue(), uTSArray);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2, UTSArray<String> b) {
                        Intrinsics.checkNotNullParameter(b, "b");
                        ChooseMediaErrorImpl chooseMediaErrorImpl = new ChooseMediaErrorImpl((Number) 1101005, IndexKt.getUniError_ChooseMedia());
                        Function1<IChooseMediaError, Unit> fail = chooseMediaOptions5.getFail();
                        if (fail != null) {
                            fail.invoke(chooseMediaErrorImpl);
                        }
                        Function1<Object, Unit> complete = chooseMediaOptions5.getComplete();
                        if (complete != null) {
                            complete.invoke(chooseMediaErrorImpl);
                        }
                    }
                }, false, 16, null);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
    /* JADX WARN: Type inference failed for: r12v0, types: [T, io.dcloud.uts.UTSArray] */
    /* JADX WARN: Type inference failed for: r6v11, types: [T, io.dcloud.uts.UTSArray] */
    /* JADX WARN: Type inference failed for: r6v13, types: [T, io.dcloud.uts.UTSArray] */
    /* JADX WARN: Type inference failed for: r6v15, types: [T, io.dcloud.uts.UTSArray] */
    /* JADX WARN: Type inference failed for: r6v17, types: [T, io.dcloud.uts.UTSArray] */
    /* JADX WARN: Type inference failed for: r6v9, types: [T, io.dcloud.uts.UTSArray] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void uniChooseMedia(final uts.sdk.modules.DCloudUniChooseMedia.ChooseMediaOptions r23, final kotlin.jvm.functions.Function3<? super java.lang.Number, ? super java.lang.Boolean, ? super java.lang.Number, kotlin.Unit> r24) {
        /*
            Method dump skipped, instructions count: 377
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.uniChooseMedia(uts.sdk.modules.DCloudUniChooseMedia.ChooseMediaOptions, kotlin.jvm.functions.Function3):void");
    }

    public static final void onMediaTypeSelect(Number count, boolean z, String item, Function3<? super Number, ? super Boolean, ? super Number, Unit> onSourceTypeSelect) {
        Intrinsics.checkNotNullParameter(count, "count");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(onSourceTypeSelect, "onSourceTypeSelect");
        int iHashCode = item.hashCode();
        if (iHashCode == -1915178910) {
            if (item.equals("从相册选择")) {
                onSourceTypeSelect.invoke(count, Boolean.valueOf(z), 1);
            }
        } else if (iHashCode == 777242) {
            if (item.equals("录像")) {
                onSourceTypeSelect.invoke(count, Boolean.valueOf(z), 2);
            }
        } else if (iHashCode == 809751 && item.equals("拍摄")) {
            onSourceTypeSelect.invoke(count, Boolean.valueOf(z), 0);
        }
    }

    public static final Number getMediaType(UTSArray<String> uTSArray) {
        if (uTSArray == null) {
            return (Number) 101;
        }
        String string = uTSArray.toString();
        Locale ENGLISH = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
        String lowerCase = string.toLowerCase(ENGLISH);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        String str = lowerCase;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "mix", false, 2, (Object) null)) {
            return (Number) 101;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "image", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) str, (CharSequence) "video", false, 2, (Object) null)) {
            return (Number) 101;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "image", false, 2, (Object) null)) {
            return (Number) 100;
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "video", false, 2, (Object) null)) {
            return (Number) 102;
        }
        return (Number) 101;
    }

    public static final Function3<Integer, Integer, Intent, Unit> getTakeVideoFunction() {
        return takeVideoFunction;
    }

    public static final void setTakeVideoFunction(Function3<? super Integer, ? super Integer, ? super Intent, Unit> function3) {
        takeVideoFunction = function3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0100 A[Catch: Exception -> 0x0112, TryCatch #0 {Exception -> 0x0112, blocks: (B:6:0x003c, B:8:0x005a, B:9:0x0065, B:11:0x00c3, B:13:0x00d6, B:14:0x00e4, B:17:0x00ec, B:19:0x00fb, B:21:0x0103, B:23:0x010b, B:20:0x0100), top: B:26:0x003c }] */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, java.io.File] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void openCameraForMediaVideo(final uts.sdk.modules.DCloudUniChooseMedia.ChooseMediaOptions r4, boolean r5) {
        /*
            Method dump skipped, instructions count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.openCameraForMediaVideo(uts.sdk.modules.DCloudUniChooseMedia.ChooseMediaOptions, boolean):void");
    }

    public static final Function3<Integer, Integer, Intent, Unit> getTakeCameraFunction() {
        return takeCameraFunction;
    }

    public static final void setTakeCameraFunction(Function3<? super Integer, ? super Integer, ? super Intent, Unit> function3) {
        takeCameraFunction = function3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, java.io.File] */
    public static final void openCameraForMediaImage(final ChooseMediaOptions option, boolean z) {
        Intrinsics.checkNotNullParameter(option, "option");
        final Integer num = (Number) 24;
        if (takeCameraFunction != null) {
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Function3<? super Integer, ? super Integer, ? super Intent, Unit> function3 = takeCameraFunction;
            Intrinsics.checkNotNull(function3);
            uTSAndroid.offAppActivityResult(function3);
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = mediaCachePath + System.currentTimeMillis() + ".jpg";
        try {
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = new File((String) objectRef.element);
            if (!((File) objectRef2.element).getParentFile().exists()) {
                ((File) objectRef2.element).getParentFile().mkdirs();
            }
            takeCameraFunction = new Function3<Integer, Integer, Intent, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.openCameraForMediaImage.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Integer num2, Integer num3, Intent intent) {
                    invoke(num2.intValue(), num3.intValue(), intent);
                    return Unit.INSTANCE;
                }

                public final void invoke(int i, int i2, Intent intent) {
                    if (NumberKt.numberEquals(num, Integer.valueOf(i))) {
                        UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
                        Function3<Integer, Integer, Intent, Unit> takeCameraFunction2 = IndexKt.getTakeCameraFunction();
                        Intrinsics.checkNotNull(takeCameraFunction2);
                        uTSAndroid2.offAppActivityResult(takeCameraFunction2);
                        if (i2 == -1) {
                            if (objectRef2.element.exists()) {
                                ChooseMediaTempFile mediaFileByType = IndexKt.getMediaFileByType(objectRef.element, (Number) 1);
                                if (mediaFileByType != null) {
                                    ChooseMediaSuccess chooseMediaSuccess = new ChooseMediaSuccess(UTSArrayKt._uA(mediaFileByType), "image");
                                    Function1<ChooseMediaSuccess, Unit> success = option.getSuccess();
                                    if (success != null) {
                                        success.invoke(chooseMediaSuccess);
                                    }
                                    Function1<Object, Unit> complete = option.getComplete();
                                    if (complete != null) {
                                        complete.invoke(chooseMediaSuccess);
                                        return;
                                    }
                                    return;
                                }
                                ChooseMediaErrorImpl chooseMediaErrorImpl = new ChooseMediaErrorImpl((Number) 1101001, IndexKt.getUniError_ChooseMedia());
                                Function1<IChooseMediaError, Unit> fail = option.getFail();
                                if (fail != null) {
                                    fail.invoke(chooseMediaErrorImpl);
                                }
                                Function1<Object, Unit> complete2 = option.getComplete();
                                if (complete2 != null) {
                                    complete2.invoke(chooseMediaErrorImpl);
                                    return;
                                }
                                return;
                            }
                            ChooseMediaErrorImpl chooseMediaErrorImpl2 = new ChooseMediaErrorImpl((Number) 1101008, IndexKt.getUniError_ChooseMedia());
                            Function1<IChooseMediaError, Unit> fail2 = option.getFail();
                            if (fail2 != null) {
                                fail2.invoke(chooseMediaErrorImpl2);
                            }
                            Function1<Object, Unit> complete3 = option.getComplete();
                            if (complete3 != null) {
                                complete3.invoke(chooseMediaErrorImpl2);
                                return;
                            }
                            return;
                        }
                        ChooseMediaErrorImpl chooseMediaErrorImpl3 = new ChooseMediaErrorImpl((Number) 1101001, IndexKt.getUniError_ChooseMedia());
                        Function1<IChooseMediaError, Unit> fail3 = option.getFail();
                        if (fail3 != null) {
                            fail3.invoke(chooseMediaErrorImpl3);
                        }
                        Function1<Object, Unit> complete4 = option.getComplete();
                        if (complete4 != null) {
                            complete4.invoke(chooseMediaErrorImpl3);
                        }
                    }
                }
            };
            UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
            Function3<? super Integer, ? super Integer, ? super Intent, Unit> function32 = takeCameraFunction;
            Intrinsics.checkNotNull(function32);
            uTSAndroid2.onAppActivityResult(function32);
            Activity uniActivity = UTSAndroid.INSTANCE.getUniActivity();
            Intrinsics.checkNotNull(uniActivity);
            StringBuilder sb = new StringBuilder();
            Activity uniActivity2 = UTSAndroid.INSTANCE.getUniActivity();
            Intrinsics.checkNotNull(uniActivity2);
            sb.append(uniActivity2.getPackageName());
            sb.append(".dc.fileprovider");
            Uri uriForFile = FileProvider.getUriForFile(uniActivity, sb.toString(), (File) objectRef2.element);
            Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(UTSAndroid…c.fileprovider\", picFile)");
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", uriForFile);
            Activity uniActivity3 = UTSAndroid.INSTANCE.getUniActivity();
            if (uniActivity3 != null) {
                uniActivity3.startActivityForResult(intent, num.intValue());
            }
        } catch (Exception unused) {
        }
    }

    public static final Number getGALLERY_MEDIA_PICKER_RESULT() {
        return GALLERY_MEDIA_PICKER_RESULT;
    }

    public static final Function3<Integer, Integer, Intent, Unit> getOpenMediaFunction() {
        return openMediaFunction;
    }

    public static final void setOpenMediaFunction(Function3<? super Integer, ? super Integer, ? super Intent, Unit> function3) {
        openMediaFunction = function3;
    }

    public static final void openAlbumForMedia(final ChooseMediaOptions option, Number count, Number type) {
        Intrinsics.checkNotNullParameter(option, "option");
        Intrinsics.checkNotNullParameter(count, "count");
        Intrinsics.checkNotNullParameter(type, "type");
        if (openMediaFunction != null) {
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Function3<? super Integer, ? super Integer, ? super Intent, Unit> function3 = openMediaFunction;
            Intrinsics.checkNotNull(function3);
            uTSAndroid.offAppActivityResult(function3);
        }
        openMediaFunction = new Function3<Integer, Integer, Intent, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.openAlbumForMedia.1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2, Intent intent) {
                invoke(num.intValue(), num2.intValue(), intent);
                return Unit.INSTANCE;
            }

            public final void invoke(int i, int i2, Intent intent) {
                String strJoin;
                ArrayList parcelableArrayListExtra;
                if (NumberKt.numberEquals(Integer.valueOf(i), IndexKt.getGALLERY_MEDIA_PICKER_RESULT())) {
                    UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
                    Function3<Integer, Integer, Intent, Unit> openMediaFunction2 = IndexKt.getOpenMediaFunction();
                    Intrinsics.checkNotNull(openMediaFunction2);
                    uTSAndroid2.offAppActivityResult(openMediaFunction2);
                    UTSArray uTSArray = new UTSArray();
                    UTSArray uTSArray2 = new UTSArray();
                    if (intent != null && (parcelableArrayListExtra = intent.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT)) != null) {
                        for (Number numberInc = (Number) 0; NumberKt.compareTo(numberInc, Integer.valueOf(parcelableArrayListExtra.size())) < 0; numberInc = NumberKt.inc(numberInc)) {
                            Object obj = parcelableArrayListExtra.get(numberInc.intValue());
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type uts.sdk.modules.DCloudUniMedia.Media");
                            Media media = (Media) obj;
                            String str = media.path;
                            Intrinsics.checkNotNullExpressionValue(str, "element.path");
                            ChooseMediaTempFile mediaFileByType = IndexKt.getMediaFileByType(str, Integer.valueOf(media.mediaType));
                            if (mediaFileByType != null) {
                                uTSArray.add(mediaFileByType);
                                if (uTSArray2.indexOf(mediaFileByType.getFileType()) == -1) {
                                    uTSArray2.add(mediaFileByType.getFileType());
                                }
                            }
                        }
                    }
                    if (uTSArray.size() == 0) {
                        ChooseMediaErrorImpl chooseMediaErrorImpl = new ChooseMediaErrorImpl((Number) 1101001, IndexKt.getUniError_ChooseMedia());
                        Function1<IChooseMediaError, Unit> fail = option.getFail();
                        if (fail != null) {
                            fail.invoke(chooseMediaErrorImpl);
                        }
                        Function1<Object, Unit> complete = option.getComplete();
                        if (complete != null) {
                            complete.invoke(chooseMediaErrorImpl);
                            return;
                        }
                        return;
                    }
                    if (option.getMediaType() != null) {
                        UTSArray<String> mediaType = option.getMediaType();
                        Intrinsics.checkNotNull(mediaType);
                        String string = mediaType.toString();
                        Locale ENGLISH = Locale.ENGLISH;
                        Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                        String lowerCase = string.toLowerCase(ENGLISH);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                        strJoin = "mix";
                        if (!StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "mix", false, 2, (Object) null)) {
                            strJoin = TextUtils.join(",", uTSArray2);
                            Intrinsics.checkNotNullExpressionValue(strJoin, "join(\",\", resultTypes)");
                        }
                    } else {
                        strJoin = TextUtils.join(",", uTSArray2);
                        Intrinsics.checkNotNullExpressionValue(strJoin, "join(\",\", resultTypes)");
                    }
                    ChooseMediaSuccess chooseMediaSuccess = new ChooseMediaSuccess(uTSArray, strJoin);
                    Function1<ChooseMediaSuccess, Unit> success = option.getSuccess();
                    if (success != null) {
                        success.invoke(chooseMediaSuccess);
                    }
                    Function1<Object, Unit> complete2 = option.getComplete();
                    if (complete2 != null) {
                        complete2.invoke(chooseMediaSuccess);
                    }
                }
            }
        };
        UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
        Function3<? super Integer, ? super Integer, ? super Intent, Unit> function32 = openMediaFunction;
        Intrinsics.checkNotNull(function32);
        uTSAndroid2.onAppActivityResult(function32);
        openGalleryActivity(count, type, false, null, null, option.getPageOrientation(), null);
    }

    public static final void openGalleryActivity(Number count, Number type, boolean z, UTSArray<String> uTSArray, Object obj, String str, String str2) {
        Intrinsics.checkNotNullParameter(count, "count");
        Intrinsics.checkNotNullParameter(type, "type");
        Intent intent = new Intent();
        Activity uniActivity = UTSAndroid.INSTANCE.getUniActivity();
        Intrinsics.checkNotNull(uniActivity);
        Intrinsics.checkNotNull(uniActivity);
        intent.setClassName(uniActivity, "uts.sdk.modules.DCloudUniMedia.SystemPickerActivity");
        intent.putExtra("select_mode", type);
        intent.putExtra(PickerConfig.SINGLE_SELECT, false);
        intent.putExtra(PickerConfig.COMPRESSED, z);
        intent.putExtra("max_select_count", count);
        if (uTSArray != null) {
            intent.putExtra(PickerConfig.SIZE_TYPE, JSON.stringify(uTSArray));
        }
        intent.putExtra("doc_path", mediaCachePath);
        intent.putExtra(PickerConfig.IMAGE_EDITABLE, true);
        intent.putExtra("page_orientation", getOrientation(str));
        intent.putExtra("copy_privacy_path", !UTSAndroid.INSTANCE.isUniAppX());
        Activity uniActivity2 = UTSAndroid.INSTANCE.getUniActivity();
        Intrinsics.checkNotNull(uniActivity2);
        Intrinsics.checkNotNull(uniActivity2);
        uniActivity2.startActivityForResult(intent, GALLERY_MEDIA_PICKER_RESULT.intValue());
    }

    public static final ChooseMediaTempFile getMediaFileByType(String path, Number mimeType) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        if (StringsKt.startsWith$default(path, "content://", false, 2, (Object) null)) {
            Activity uniActivity = UTSAndroid.INSTANCE.getUniActivity();
            Intrinsics.checkNotNull(uniActivity);
            String type = uniActivity.getContentResolver().getType(Uri.parse(path));
            Intrinsics.checkNotNull(type);
            if (StringsKt.startsWith$default(type, "image/", false, 2, (Object) null)) {
                return getMediaTempFile(path, (Number) 1);
            }
            if (StringsKt.startsWith$default(type, "video", false, 2, (Object) null)) {
                return getMediaTempFile(path, (Number) 3);
            }
            return null;
        }
        if (NumberKt.numberEquals(mimeType, 1) || NumberKt.numberEquals(mimeType, 3)) {
            return getMediaTempFile(path, mimeType);
        }
        String strReplace = StringsKt.startsWith$default(path, DeviceInfo.FILE_PROTOCOL, false, 2, (Object) null) ? StringKt.replace(path, DeviceInfo.FILE_PROTOCOL, "") : path;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            BitmapFactory.decodeFile(strReplace, options);
            if (options.outHeight >= 0 && options.outWidth >= 0) {
                return getMediaTempFile(path, (Number) 1);
            }
            return getMediaTempFile(path, (Number) 3);
        } catch (Throwable unused) {
            return getMediaTempFile(path, (Number) 3);
        }
    }

    public static final ChooseMediaTempFile getMediaTempFile(String path, Number mimeType) {
        String str;
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        if (StringsKt.startsWith$default(path, DeviceInfo.FILE_PROTOCOL, false, 2, (Object) null) || StringsKt.startsWith$default(path, "content://", false, 2, (Object) null)) {
            str = path;
        } else {
            str = DeviceInfo.FILE_PROTOCOL + path;
        }
        if (NumberKt.numberEquals(mimeType, 1)) {
            return new ChooseMediaTempFile(str, "image", Long.valueOf(getMediaInfo(path)), null, null, null, null, null, 248, null);
        }
        if (!NumberKt.numberEquals(mimeType, 3)) {
            return null;
        }
        UTSJSONObject videoMetadata = getVideoMetadata(path, mediaCachePath + "video_thumb_" + System.currentTimeMillis() + ".jpg");
        if (videoMetadata == null) {
            videoMetadata = new UTSJSONObject() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.getMediaTempFile.1
                private Number duration;
                private String filePath;
                private Number height;
                private Number size;
                private Number width;

                {
                    Double dValueOf = Double.valueOf(0.0d);
                    this.duration = dValueOf;
                    this.size = dValueOf;
                    this.height = dValueOf;
                    this.width = dValueOf;
                    this.filePath = "";
                }

                public final Number getDuration() {
                    return this.duration;
                }

                public final void setDuration(Number number) {
                    Intrinsics.checkNotNullParameter(number, "<set-?>");
                    this.duration = number;
                }

                public final Number getSize() {
                    return this.size;
                }

                public final void setSize(Number number) {
                    Intrinsics.checkNotNullParameter(number, "<set-?>");
                    this.size = number;
                }

                public final Number getHeight() {
                    return this.height;
                }

                public final void setHeight(Number number) {
                    Intrinsics.checkNotNullParameter(number, "<set-?>");
                    this.height = number;
                }

                public final Number getWidth() {
                    return this.width;
                }

                public final void setWidth(Number number) {
                    Intrinsics.checkNotNullParameter(number, "<set-?>");
                    this.width = number;
                }

                public final String getFilePath() {
                    return this.filePath;
                }

                public final void setFilePath(String str2) {
                    Intrinsics.checkNotNullParameter(str2, "<set-?>");
                    this.filePath = str2;
                }
            };
        }
        Object obj = videoMetadata.get("filePath");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        String str2 = (String) obj;
        Object obj2 = videoMetadata.get("width");
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Number");
        Number number = (Number) obj2;
        Object obj3 = videoMetadata.get("height");
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Number");
        Number number2 = (Number) obj3;
        Object obj4 = videoMetadata.get(AbsoluteConst.JSON_KEY_SIZE);
        Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Number");
        Number number3 = (Number) obj4;
        Object obj5 = videoMetadata.get("duration");
        Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.Number");
        Object obj6 = videoMetadata.get("byteSize");
        Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.Number");
        return new ChooseMediaTempFile(str, "video", number3, (Number) obj6, (Number) obj5, number2, number, str2);
    }

    public static final long getMediaInfo(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Cursor cursorQuery = null;
        long j = 0;
        if (StringsKt.startsWith$default(path, "content://", false, 2, (Object) null)) {
            try {
                Context appContext = UTSAndroid.INSTANCE.getAppContext();
                Intrinsics.checkNotNull(appContext);
                cursorQuery = appContext.getContentResolver().query(Uri.parse(path), null, null, null, null);
            } catch (Throwable unused) {
                if (cursorQuery != null) {
                }
            }
            if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return 0L;
            }
            int columnIndex = cursorQuery.getColumnIndex("_size");
            if (!cursorQuery.isNull(columnIndex)) {
                j = cursorQuery.getLong(columnIndex);
            }
            cursorQuery.close();
            return j;
        }
        String str = ASSETS_PATH;
        if (StringsKt.startsWith$default(path, str, false, 2, (Object) null)) {
            try {
                Activity uniActivity = UTSAndroid.INSTANCE.getUniActivity();
                Intrinsics.checkNotNull(uniActivity);
                InputStream inputStreamOpen = uniActivity.getAssets().open(StringKt.replace(path, str, ""));
                Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "UTSAndroid.getUniActivit…replace(ASSETS_PATH, \"\"))");
                int iAvailable = inputStreamOpen.available();
                inputStreamOpen.close();
                return iAvailable;
            } catch (Throwable unused2) {
            }
        } else {
            File file = new File(path);
            if (file.exists()) {
                return file.length();
            }
            if (StringsKt.startsWith$default(path, DeviceInfo.FILE_PROTOCOL, false, 2, (Object) null)) {
                File file2 = new File(StringKt.replace(path, DeviceInfo.FILE_PROTOCOL, ""));
                if (file2.exists()) {
                    return file2.length();
                }
            }
        }
        return 0L;
    }

    public static final UTSJSONObject getVideoMetadata(String src, String str) {
        String str2;
        Integer numValueOf;
        Number numberRound;
        Intrinsics.checkNotNullParameter(src, "src");
        UTSJSONObject uTSJSONObject = new UTSJSONObject() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt$getVideoMetadata$videoInfo$1
            private Number duration;
            private Number height;
            private Number size;
            private Number width;

            {
                Double dValueOf = Double.valueOf(0.0d);
                this.duration = dValueOf;
                this.size = dValueOf;
                this.height = dValueOf;
                this.width = dValueOf;
            }

            public final Number getDuration() {
                return this.duration;
            }

            public final void setDuration(Number number) {
                Intrinsics.checkNotNullParameter(number, "<set-?>");
                this.duration = number;
            }

            public final Number getSize() {
                return this.size;
            }

            public final void setSize(Number number) {
                Intrinsics.checkNotNullParameter(number, "<set-?>");
                this.size = number;
            }

            public final Number getHeight() {
                return this.height;
            }

            public final void setHeight(Number number) {
                Intrinsics.checkNotNullParameter(number, "<set-?>");
                this.height = number;
            }

            public final Number getWidth() {
                return this.width;
            }

            public final void setWidth(Number number) {
                Intrinsics.checkNotNullParameter(number, "<set-?>");
                this.width = number;
            }
        };
        File file = new File(src);
        String strConvert2AbsFullPath = !file.exists() ? UTSAndroid.INSTANCE.convert2AbsFullPath(src) : src;
        if (StringsKt.startsWith$default(strConvert2AbsFullPath, "/android_asset/", false, 2, (Object) null)) {
            String str3 = UTSAndroid.INSTANCE.getAppCachePath() + "uni-media/_" + System.currentTimeMillis();
            if (!copyFile(strConvert2AbsFullPath, str3)) {
                return null;
            }
            strConvert2AbsFullPath = str3;
        }
        if (!file.exists()) {
            file = new File(strConvert2AbsFullPath);
        }
        if (!file.exists() && !StringsKt.startsWith$default(src, "content://", false, 2, (Object) null)) {
            return null;
        }
        uTSJSONObject.set("byteSize", Long.valueOf(getMediaInfo(strConvert2AbsFullPath)));
        uTSJSONObject.set(AbsoluteConst.JSON_KEY_SIZE, formatNumber(Double.valueOf(getMediaInfo(strConvert2AbsFullPath) / 1024.0d)));
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            if (StringsKt.startsWith$default(strConvert2AbsFullPath, "content://", false, 2, (Object) null)) {
                Activity uniActivity = UTSAndroid.INSTANCE.getUniActivity();
                Intrinsics.checkNotNull(uniActivity);
                mediaMetadataRetriever.setDataSource(uniActivity, Uri.parse(strConvert2AbsFullPath));
            } else {
                mediaMetadataRetriever.setDataSource(strConvert2AbsFullPath);
            }
            if (!saveBitmapToLocalPath(mediaMetadataRetriever.getFrameAtTime(0L, 2), str)) {
                uTSJSONObject.set("filePath", "");
            } else {
                uTSJSONObject.set("filePath", str);
            }
            String strExtractMetadata = mediaMetadataRetriever.extractMetadata(9);
            String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(18);
            Float fValueOf = strExtractMetadata2 != null ? Float.valueOf(Float.parseFloat(strExtractMetadata2)) : null;
            String strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(19);
            Float fValueOf2 = strExtractMetadata3 != null ? Float.valueOf(Float.parseFloat(strExtractMetadata3)) : null;
            String strExtractMetadata4 = mediaMetadataRetriever.extractMetadata(24);
            if (Intrinsics.areEqual("90", strExtractMetadata4) || Intrinsics.areEqual("270", strExtractMetadata4)) {
                if (fValueOf2 == null) {
                    fValueOf2 = (Number) 0;
                }
                uTSJSONObject.set("width", fValueOf2);
                if (fValueOf == null) {
                    fValueOf = (Number) 0;
                }
                uTSJSONObject.set("height", fValueOf);
            } else {
                if (fValueOf == null) {
                    fValueOf = (Number) 0;
                }
                uTSJSONObject.set("width", fValueOf);
                if (fValueOf2 == null) {
                    fValueOf2 = (Number) 0;
                }
                uTSJSONObject.set("height", fValueOf2);
            }
            if (strExtractMetadata4 != null) {
                int iHashCode = strExtractMetadata4.hashCode();
                if (iHashCode != 1815) {
                    if (iHashCode != 48873) {
                        if (iHashCode == 49803 && strExtractMetadata4.equals("270")) {
                            str2 = "left";
                        } else {
                            str2 = "up";
                        }
                    } else if (strExtractMetadata4.equals("180")) {
                        str2 = "down";
                    } else {
                        str2 = "up";
                    }
                } else if (strExtractMetadata4.equals("90")) {
                    str2 = "right";
                } else {
                    str2 = "up";
                }
            } else {
                str2 = "up";
            }
            Number numberValueOf = (Number) 0;
            if (strExtractMetadata != null) {
                try {
                    numberValueOf = Float.valueOf(Float.parseFloat(strExtractMetadata));
                } catch (Throwable unused) {
                }
            }
            BigDecimal scale = BigDecimal.valueOf(numberValueOf.doubleValue() / 1000).setScale(2, 4);
            Intrinsics.checkNotNullExpressionValue(scale, "valueOf(duration.toDoubl…BigDecimal.ROUND_HALF_UP)");
            uTSJSONObject.set("duration", Float.valueOf(scale.floatValue()));
            uTSJSONObject.set("orientation", str2);
            uTSJSONObject.set("type", mediaMetadataRetriever.extractMetadata(12));
            String strExtractMetadata5 = mediaMetadataRetriever.extractMetadata(20);
            if (strExtractMetadata5 != null) {
                try {
                    numValueOf = Integer.valueOf((int) (Float.parseFloat(strExtractMetadata5) / 1000));
                } catch (Throwable unused2) {
                }
            } else {
                numValueOf = null;
            }
            uTSJSONObject.set("bitrate", numValueOf);
            String strExtractMetadata6 = mediaMetadataRetriever.extractMetadata(32);
            if (TextUtils.isEmpty(strExtractMetadata6)) {
                MediaExtractor mediaExtractor = new MediaExtractor();
                Activity uniActivity2 = UTSAndroid.INSTANCE.getUniActivity();
                Intrinsics.checkNotNull(uniActivity2);
                mediaExtractor.setDataSource(uniActivity2, Uri.parse(strConvert2AbsFullPath), (java.util.Map<String, String>) null);
                int trackCount = mediaExtractor.getTrackCount();
                numberRound = null;
                for (Number numberInc = (Number) 0; NumberKt.compareTo(numberInc, Integer.valueOf(trackCount)) < 0; numberInc = NumberKt.inc(numberInc)) {
                    MediaFormat trackFormat = mediaExtractor.getTrackFormat(numberInc.intValue());
                    Intrinsics.checkNotNullExpressionValue(trackFormat, "extractor.getTrackFormat(i.toInt())");
                    String string = trackFormat.getString("mime");
                    if (!TextUtils.isEmpty(string)) {
                        Intrinsics.checkNotNull(string);
                        Intrinsics.checkNotNull(string);
                        if (StringsKt.startsWith$default(string, "video/", false, 2, (Object) null)) {
                            numberRound = Integer.valueOf(trackFormat.getInteger("frame-rate"));
                        }
                    }
                }
            } else {
                try {
                    Intrinsics.checkNotNull(strExtractMetadata6);
                    Intrinsics.checkNotNull(strExtractMetadata6);
                    numberRound = Math.round(NumberKt.times(NumberKt.div(Float.valueOf(Float.parseFloat(strExtractMetadata6)), numberValueOf), (Number) 1000));
                } catch (Throwable unused3) {
                    numberRound = null;
                }
            }
            uTSJSONObject.set("fps", numberRound);
            return uTSJSONObject;
        } catch (Throwable unused4) {
            return null;
        }
    }

    public static final boolean saveBitmapToLocalPath(Bitmap bitmap, String str) throws IOException {
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        if (bitmap == null) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable unused) {
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Throwable unused2) {
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            return false;
        }
    }

    public static final Number getOrientation(String str) {
        if (str == null) {
            str = getGlobalConfig();
        }
        Intrinsics.checkNotNull(str);
        int iHashCode = str.hashCode();
        if (iHashCode != 3005871) {
            if (iHashCode != 729267099) {
                if (iHashCode == 1430647483 && str.equals("landscape")) {
                    return (Number) 6;
                }
            } else if (str.equals("portrait")) {
                return (Number) 1;
            }
        } else if (str.equals("auto")) {
            return (Number) 2;
        }
        return (Number) 1;
    }

    public static final String getGlobalConfig() {
        Object obj;
        try {
            Class<?> cls = Class.forName("io.dcloud.uniapp.framework.IndexKt");
            if (cls != null) {
                Field declaredField = cls.getDeclaredField("__uniConfig");
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(null);
                if (obj2 != null) {
                    Field declaredField2 = obj2.getClass().getDeclaredField("globalStyle");
                    declaredField2.setAccessible(true);
                    Object obj3 = declaredField2.get(obj2);
                    if (obj3 != null && (obj3 instanceof Map) && (obj = ((java.util.Map) obj3).get("pageOrientation")) != null) {
                        return (String) obj;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return "portrait";
    }

    public static final boolean copyFile(String fromFilePath, String toFilePath) throws IOException {
        FileInputStream fileInputStream;
        Intrinsics.checkNotNullParameter(fromFilePath, "fromFilePath");
        Intrinsics.checkNotNullParameter(toFilePath, "toFilePath");
        try {
            String str = ASSETS_PATH;
            if (StringsKt.startsWith$default(fromFilePath, str, false, 2, (Object) null)) {
                Activity uniActivity = UTSAndroid.INSTANCE.getUniActivity();
                Intrinsics.checkNotNull(uniActivity);
                fileInputStream = uniActivity.getAssets().open(StringKt.replace(fromFilePath, str, ""));
            } else if (StringsKt.startsWith$default(fromFilePath, "content://", false, 2, (Object) null)) {
                Activity uniActivity2 = UTSAndroid.INSTANCE.getUniActivity();
                Intrinsics.checkNotNull(uniActivity2);
                fileInputStream = uniActivity2.getContentResolver().openInputStream(Uri.parse(fromFilePath));
            } else {
                File file = new File(fromFilePath);
                if (!file.exists() || !file.isFile() || !file.canRead()) {
                    return false;
                }
                fileInputStream = new FileInputStream(file);
            }
            if (fileInputStream == null) {
                return false;
            }
            File file2 = new File(toFilePath);
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            for (int i = fileInputStream.read(bArr); i > 0; i = fileInputStream.read(bArr)) {
                fileOutputStream.write(bArr, 0, i);
            }
            fileInputStream.close();
            fileOutputStream.close();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static final Number formatNumber(Number input) {
        Intrinsics.checkNotNullParameter(input, "input");
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        decimalFormat.setDecimalSeparatorAlwaysShown(false);
        String str = decimalFormat.format(input);
        Intrinsics.checkNotNullExpressionValue(str, "bd.format(input)");
        return Float.valueOf(Float.parseFloat(str));
    }

    public static final void chooseMediaByJs(final ChooseMediaOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        chooseMedia.invoke(new ChooseMediaOptions(options.getPageOrientation(), options.getCount(), options.getMediaType(), options.getSourceType(), options.getMaxDuration(), options.getCamera(), new Function1<ChooseMediaSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.chooseMediaByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ChooseMediaSuccess chooseMediaSuccess) throws SecurityException {
                invoke2(chooseMediaSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ChooseMediaSuccess callback) throws SecurityException {
                Intrinsics.checkNotNullParameter(callback, "callback");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(callback);
                }
            }
        }, new Function1<IChooseMediaError, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.chooseMediaByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IChooseMediaError iChooseMediaError) throws SecurityException {
                invoke2(iChooseMediaError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IChooseMediaError callback) throws SecurityException {
                Intrinsics.checkNotNullParameter(callback, "callback");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(callback);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniChooseMedia.IndexKt.chooseMediaByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object callback) throws SecurityException {
                Intrinsics.checkNotNullParameter(callback, "callback");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(callback);
                }
            }
        }));
    }
}
