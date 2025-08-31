package uts.sdk.modules.DCloudUniPrivacy;

import com.facebook.common.util.UriUtil;
import com.taobao.weex.bridge.WXBridgeManager;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.PrivacyOption;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSCallback;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u000e\u0010&\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020'\u001a\u000e\u0010(\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b\u001a\u000e\u0010)\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020*\u001a\u0006\u0010+\u001a\u00020\u0006\"0\u0010\u0000\u001a!\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\"0\u0010\u0010\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\t\"^\u0010\u0013\u001aO\u0012A\u0012?\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00060\u0001j\u0011`\u0016¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0017¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u000b0\u0001j\u0002`\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\t\"\u001d\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\t\"<\u0010\u001d\u001a-\u0012\u0004\u0012\u00020\u000b\u0012#\u0012!\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u00160\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \"\u001b\u0010!\u001a\f\u0012\u0004\u0012\u00020\u00060\"j\u0002`#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%*@\u0010,\"\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u00012\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001*@\u0010-\"\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00060\u00012\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00060\u0001*@\u00100\"\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00060\u00012\u001d\u0012\u0013\u0012\u00110.¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00060\u0001*@\u00101\"\u001d\u0012\u0013\u0012\u001102¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00060\u00012\u001d\u0012\u0013\u0012\u001102¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00060\u0001*@\u00103\"\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u00012\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u0001*n\u00104\"\u001d\u0012\u0013\u0012\u0011`\u0016¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u000b0\u00012K\u0012A\u0012?\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00060\u0001j\u0011`\u0016¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0017¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u000b0\u0001*@\u00105\"\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00060\u00012\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00060\u0001*\u0016\u00106\"\b\u0012\u0004\u0012\u00020\u00060\"2\b\u0012\u0004\u0012\u00020\u00060\"¨\u00067"}, d2 = {"getPrivacySetting", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniPrivacy/GetPrivacySettingOptions;", "Lkotlin/ParameterName;", "name", "options", "", "Luts/sdk/modules/DCloudUniPrivacy/GetPrivacySetting;", "getGetPrivacySetting", "()Lkotlin/jvm/functions/Function1;", "id", "", "getId", "()Ljava/lang/Number;", "setId", "(Ljava/lang/Number;)V", "offPrivacyAuthorizationChange", "Luts/sdk/modules/DCloudUniPrivacy/OffPrivacyAuthorizationChange;", "getOffPrivacyAuthorizationChange", "onPrivacyAuthorizationChange", "Luts/sdk/modules/DCloudUniPrivacy/PrivacyChangeResult;", UriUtil.LOCAL_RESOURCE_SCHEME, "Luts/sdk/modules/DCloudUniPrivacy/OnPrivacyAuthorizationChangeCallback;", WXBridgeManager.METHOD_CALLBACK, "Luts/sdk/modules/DCloudUniPrivacy/OnPrivacyAuthorizationChange;", "getOnPrivacyAuthorizationChange", "privacyAuthorizationChangeFunction", "Lio/dcloud/uts/PrivacyOption;", "getPrivacyAuthorizationChangeFunction", "privacyCallbacks", "Ljava/util/HashMap;", "getPrivacyCallbacks", "()Ljava/util/HashMap;", "resetPrivacyAuthorization", "Lkotlin/Function0;", "Luts/sdk/modules/DCloudUniPrivacy/ResetPrivacyAuthorization;", "getResetPrivacyAuthorization", "()Lkotlin/jvm/functions/Function0;", "getPrivacySettingByJs", "Luts/sdk/modules/DCloudUniPrivacy/GetPrivacySettingOptionsJSONObject;", "offPrivacyAuthorizationChangeByJs", "onPrivacyAuthorizationChangeByJs", "Lio/dcloud/uts/UTSCallback;", "resetPrivacyAuthorizationByJs", "GetPrivacySetting", "GetPrivacySettingCompleteCallback", "", "result", "GetPrivacySettingFailCallback", "GetPrivacySettingSuccessCallback", "Luts/sdk/modules/DCloudUniPrivacy/GetPrivacySettingSuccessResult;", "OffPrivacyAuthorizationChange", "OnPrivacyAuthorizationChange", "OnPrivacyAuthorizationChangeCallback", "ResetPrivacyAuthorization", "uni-privacy_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IndexKt {
    private static final Function1<GetPrivacySettingOptions, Unit> getPrivacySetting = new Function1<GetPrivacySettingOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt$getPrivacySetting$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetPrivacySettingOptions getPrivacySettingOptions) {
            invoke2(getPrivacySettingOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(GetPrivacySettingOptions option) {
            Intrinsics.checkNotNullParameter(option, "option");
            GetPrivacySettingSuccessResult getPrivacySettingSuccessResult = new GetPrivacySettingSuccessResult(!UTSAndroid.INSTANCE.isPrivacyAgree());
            Function1<GetPrivacySettingSuccessResult, Unit> success = option.getSuccess();
            if (success != null) {
                success.invoke(getPrivacySettingSuccessResult);
            }
            Function1<Object, Unit> complete = option.getComplete();
            if (complete != null) {
                complete.invoke(getPrivacySettingSuccessResult);
            }
        }
    };
    private static final Function0<Unit> resetPrivacyAuthorization = new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt$resetPrivacyAuthorization$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            UTSAndroid.INSTANCE.resetPrivacyAgree();
        }
    };
    private static final HashMap<Number, Function1<PrivacyChangeResult, Unit>> privacyCallbacks = new HashMap<>();
    private static Number id = (Number) 0;
    private static final Function1<PrivacyOption, Unit> privacyAuthorizationChangeFunction = new Function1<PrivacyOption, Unit>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt$privacyAuthorizationChangeFunction$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PrivacyOption privacyOption) {
            invoke2(privacyOption);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PrivacyOption option) {
            Intrinsics.checkNotNullParameter(option, "option");
            Collection<Function1<PrivacyChangeResult, Unit>> collectionValues = IndexKt.getPrivacyCallbacks().values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "privacyCallbacks.values");
            Iterator<T> it = collectionValues.iterator();
            while (it.hasNext()) {
                ((Function1) it.next()).invoke(new PrivacyChangeResult(!option.getIsAgree()));
            }
        }
    };
    private static final Function1<Function1<? super PrivacyChangeResult, Unit>, Number> onPrivacyAuthorizationChange = new Function1<Function1<? super PrivacyChangeResult, ? extends Unit>, Number>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt$onPrivacyAuthorizationChange$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Number invoke(Function1<? super PrivacyChangeResult, ? extends Unit> function1) {
            return invoke2((Function1<? super PrivacyChangeResult, Unit>) function1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Number invoke2(Function1<? super PrivacyChangeResult, Unit> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            if (IndexKt.getPrivacyCallbacks().isEmpty()) {
                UTSAndroid.INSTANCE.onPrivacyAgreeChange(IndexKt.getPrivacyAuthorizationChangeFunction());
            }
            IndexKt.setId(NumberKt.plus(IndexKt.getId(), (Number) 1));
            IndexKt.getPrivacyCallbacks().put(IndexKt.getId(), callback);
            return IndexKt.getId();
        }
    };
    private static final Function1<Number, Unit> offPrivacyAuthorizationChange = new Function1<Number, Unit>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt$offPrivacyAuthorizationChange$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Number number) {
            invoke2(number);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Number id2) {
            Intrinsics.checkNotNullParameter(id2, "id");
            IndexKt.getPrivacyCallbacks().remove(id2);
            if (IndexKt.getPrivacyCallbacks().isEmpty()) {
                UTSAndroid.INSTANCE.offPrivacyAgreeChange(IndexKt.getPrivacyAuthorizationChangeFunction());
            }
        }
    };

    public static final Function1<GetPrivacySettingOptions, Unit> getGetPrivacySetting() {
        return getPrivacySetting;
    }

    public static final Function0<Unit> getResetPrivacyAuthorization() {
        return resetPrivacyAuthorization;
    }

    public static final HashMap<Number, Function1<PrivacyChangeResult, Unit>> getPrivacyCallbacks() {
        return privacyCallbacks;
    }

    public static final Number getId() {
        return id;
    }

    public static final void setId(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        id = number;
    }

    public static final Function1<PrivacyOption, Unit> getPrivacyAuthorizationChangeFunction() {
        return privacyAuthorizationChangeFunction;
    }

    public static final Function1<Function1<? super PrivacyChangeResult, Unit>, Number> getOnPrivacyAuthorizationChange() {
        return onPrivacyAuthorizationChange;
    }

    public static final Function1<Number, Unit> getOffPrivacyAuthorizationChange() {
        return offPrivacyAuthorizationChange;
    }

    public static final void getPrivacySettingByJs(final GetPrivacySettingOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        getPrivacySetting.invoke(new GetPrivacySettingOptions(new Function1<GetPrivacySettingSuccessResult, Unit>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt.getPrivacySettingByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GetPrivacySettingSuccessResult getPrivacySettingSuccessResult) throws SecurityException {
                invoke2(getPrivacySettingSuccessResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GetPrivacySettingSuccessResult result) throws SecurityException {
                Intrinsics.checkNotNullParameter(result, "result");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(result);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt.getPrivacySettingByJs.2
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
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(result);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt.getPrivacySettingByJs.3
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
        }));
    }

    public static final void resetPrivacyAuthorizationByJs() {
        resetPrivacyAuthorization.invoke();
    }

    public static final Number onPrivacyAuthorizationChangeByJs(final UTSCallback callback) {
        Object fnJS;
        Intrinsics.checkNotNullParameter(callback, "callback");
        Function1<Function1<? super PrivacyChangeResult, Unit>, Number> function1 = onPrivacyAuthorizationChange;
        if (callback.getFnJS() != null) {
            fnJS = callback.getFnJS();
        } else {
            callback.setFnJS(new Function1<PrivacyChangeResult, Unit>() { // from class: uts.sdk.modules.DCloudUniPrivacy.IndexKt.onPrivacyAuthorizationChangeByJs.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PrivacyChangeResult privacyChangeResult) throws SecurityException {
                    invoke2(privacyChangeResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PrivacyChangeResult res) throws SecurityException {
                    Intrinsics.checkNotNullParameter(res, "res");
                    callback.invoke(res);
                }
            });
            fnJS = callback.getFnJS();
        }
        Intrinsics.checkNotNull(fnJS, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'res')] uts.sdk.modules.DCloudUniPrivacy.PrivacyChangeResult, kotlin.Unit>");
        return function1.invoke((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(fnJS, 1));
    }

    public static final void offPrivacyAuthorizationChangeByJs(Number id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        offPrivacyAuthorizationChange.invoke(id2);
    }
}
