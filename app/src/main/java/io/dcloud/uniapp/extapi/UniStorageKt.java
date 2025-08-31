package io.dcloud.uniapp.extapi;

import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import uts.sdk.modules.DCloudUniStorage.ClearStorageOptions;
import uts.sdk.modules.DCloudUniStorage.GetStorageInfoOptions;
import uts.sdk.modules.DCloudUniStorage.GetStorageInfoSuccess;
import uts.sdk.modules.DCloudUniStorage.GetStorageOptions;
import uts.sdk.modules.DCloudUniStorage.IndexKt;
import uts.sdk.modules.DCloudUniStorage.RemoveStorageOptions;
import uts.sdk.modules.DCloudUniStorage.SetStorageOptions;

/* compiled from: uniStorage.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\"2\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u001b\u0010\n\u001a\f\u0012\u0004\u0012\u00020\u00060\u000bj\u0002`\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\"0\u0010\u000f\u001a!\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\t\"0\u0010\u0014\u001a!\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\t\"\u001b\u0010\u0018\u001a\f\u0012\u0004\u0012\u00020\u00190\u000bj\u0002`\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"2\u0010\u001c\u001a#\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0001j\u0002` ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\t\"0\u0010\"\u001a!\u0012\u0013\u0012\u00110#¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`$¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\t\"0\u0010&\u001a!\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\t\"0\u0010)\u001a!\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\t\"E\u0010-\u001a6\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00060.j\u0002`0¢\u0006\b\n\u0000\u001a\u0004\b1\u00102*\n\u00103\"\u00020\u00022\u00020\u0002*\n\u00104\"\u0002052\u000205*\n\u00106\"\u00020\u00152\u00020\u0015*\n\u00107\"\u00020\u00192\u00020\u0019*\n\u00108\"\u00020\u00102\u00020\u0010*\n\u00109\"\u00020:2\u00020:*\n\u0010;\"\u00020#2\u00020#*\n\u0010<\"\u00020=2\u00020=*\n\u0010>\"\u00020*2\u00020**\n\u0010?\"\u00020@2\u00020@¨\u0006A"}, d2 = {"clearStorage", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniStorage/ClearStorageOptions;", "Lkotlin/ParameterName;", "name", AbsoluteConst.JSON_KEY_OPTION, "", "Luts/sdk/modules/DCloudUniStorage/ClearStorage;", "getClearStorage", "()Lkotlin/jvm/functions/Function1;", "clearStorageSync", "Lkotlin/Function0;", "Luts/sdk/modules/DCloudUniStorage/ClearStorageSync;", "getClearStorageSync", "()Lkotlin/jvm/functions/Function0;", "getStorage", "Luts/sdk/modules/DCloudUniStorage/GetStorageOptions;", "options", "Luts/sdk/modules/DCloudUniStorage/GetStorage;", "getGetStorage", "getStorageInfo", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfoOptions;", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfo;", "getGetStorageInfo", "getStorageInfoSync", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfoSuccess;", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfoSync;", "getGetStorageInfoSync", "getStorageSync", "", IApp.ConfigProperty.CONFIG_KEY, "", "Luts/sdk/modules/DCloudUniStorage/GetStorageSync;", "getGetStorageSync", "removeStorage", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageOptions;", "Luts/sdk/modules/DCloudUniStorage/RemoveStorage;", "getRemoveStorage", "removeStorageSync", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageSync;", "getRemoveStorageSync", "setStorage", "Luts/sdk/modules/DCloudUniStorage/SetStorageOptions;", "Luts/sdk/modules/DCloudUniStorage/SetStorage;", "getSetStorage", "setStorageSync", "Lkotlin/Function2;", "data", "Luts/sdk/modules/DCloudUniStorage/SetStorageSync;", "getSetStorageSync", "()Lkotlin/jvm/functions/Function2;", "ClearStorageOptions", "ClearStorageSuccess", "Luts/sdk/modules/DCloudUniStorage/ClearStorageSuccess;", "GetStorageInfoOptions", "GetStorageInfoSuccess", "GetStorageOptions", "GetStorageSuccess", "Luts/sdk/modules/DCloudUniStorage/GetStorageSuccess;", "RemoveStorageOptions", "RemoveStorageSuccess", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageSuccess;", "SetStorageOptions", "SetStorageSuccess", "Luts/sdk/modules/DCloudUniStorage/SetStorageSuccess;", "uni-storage_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniStorageKt {
    private static final Function1<SetStorageOptions, Unit> setStorage = IndexKt.getSetStorage();
    private static final Function2<String, Object, Unit> setStorageSync = IndexKt.getSetStorageSync();
    private static final Function1<GetStorageOptions, Unit> getStorage = IndexKt.getGetStorage();
    private static final Function1<String, Object> getStorageSync = IndexKt.getGetStorageSync();
    private static final Function1<GetStorageInfoOptions, Unit> getStorageInfo = IndexKt.getGetStorageInfo();
    private static final Function0<GetStorageInfoSuccess> getStorageInfoSync = IndexKt.getGetStorageInfoSync();
    private static final Function1<RemoveStorageOptions, Unit> removeStorage = IndexKt.getRemoveStorage();
    private static final Function1<String, Unit> removeStorageSync = IndexKt.getRemoveStorageSync();
    private static final Function1<ClearStorageOptions, Unit> clearStorage = IndexKt.getClearStorage();
    private static final Function0<Unit> clearStorageSync = IndexKt.getClearStorageSync();

    public static final Function1<SetStorageOptions, Unit> getSetStorage() {
        return setStorage;
    }

    public static final Function2<String, Object, Unit> getSetStorageSync() {
        return setStorageSync;
    }

    public static final Function1<GetStorageOptions, Unit> getGetStorage() {
        return getStorage;
    }

    public static final Function1<String, Object> getGetStorageSync() {
        return getStorageSync;
    }

    public static final Function1<GetStorageInfoOptions, Unit> getGetStorageInfo() {
        return getStorageInfo;
    }

    public static final Function0<GetStorageInfoSuccess> getGetStorageInfoSync() {
        return getStorageInfoSync;
    }

    public static final Function1<RemoveStorageOptions, Unit> getRemoveStorage() {
        return removeStorage;
    }

    public static final Function1<String, Unit> getRemoveStorageSync() {
        return removeStorageSync;
    }

    public static final Function1<ClearStorageOptions, Unit> getClearStorage() {
        return clearStorage;
    }

    public static final Function0<Unit> getClearStorageSync() {
        return clearStorageSync;
    }
}
