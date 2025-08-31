package io.dcloud.uts.android;

import android.content.Context;
import android.content.Intent;
import androidtranscoder.format.MediaFormatExtraConstants;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.common.WXModule;
import io.dcloud.common.DHInterface.message.AbsActionObserver;
import io.dcloud.common.DHInterface.message.ActionBus;
import io.dcloud.common.DHInterface.message.EnumUniqueID;
import io.dcloud.common.DHInterface.message.IObserveAble;
import io.dcloud.common.DHInterface.message.action.AppOnConfigChangedAction;
import io.dcloud.common.DHInterface.message.action.AppOnTrimMemoryAction;
import io.dcloud.common.DHInterface.message.action.IAction;
import io.dcloud.common.DHInterface.message.action.PermissionRequestAction;
import io.dcloud.common.DHInterface.message.action.WebActivityOnDestroyAction;
import io.dcloud.feature.uniapp.AbsSDKInstance;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSJSONObject;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidUTSContext.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0004\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010I\u001a\u00020\u0006J\u000e\u0010J\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015J\u001c\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u0002082\f\u0010?\u001a\b\u0012\u0004\u0012\u00020807R&\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019Rg\u0010\u001a\u001aO\u0012K\u0012I\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b( \u0012\u0015\u0012\u0013\u0018\u00010!¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u00060\u001b0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\b\"\u0004\b$\u0010\nR;\u0010%\u001a#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110'¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00060&0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\b\"\u0004\b*\u0010\nR&\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\b\"\u0004\b-\u0010\nR;\u0010.\u001a#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110/¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00060&0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\b\"\u0004\b2\u0010\nR&\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\b\"\u0004\b5\u0010\nR)\u00106\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020807\u0012\u0004\u0012\u00020\u00060&0\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\bR)\u0010:\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020807\u0012\u0004\u0012\u00020\u00060&0\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\bR)\u0010<\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020807\u0012\u0004\u0012\u00020\u00060&0\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\bRq\u0010>\u001aY\u0012U\u0012S\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020807¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(?\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020/07¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(@\u0012\u0004\u0012\u00020\u00060\u001b0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\b\"\u0004\bB\u0010\nR&\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\b\"\u0004\bE\u0010\nR&\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\b\"\u0004\bH\u0010\n¨\u0006M"}, d2 = {"Lio/dcloud/uts/android/AndroidUTSContext;", "", "()V", "backListenFunc", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function0;", "", "getBackListenFunc", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setBackListenFunc", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "destroyListenFunc", "getDestroyListenFunc", "setDestroyListenFunc", "hostAppContext", "Landroid/content/Context;", "getHostAppContext", "()Landroid/content/Context;", "setHostAppContext", "(Landroid/content/Context;)V", "instance", "Lio/dcloud/feature/uniapp/AbsSDKInstance;", "getInstance", "()Lio/dcloud/feature/uniapp/AbsSDKInstance;", "setInstance", "(Lio/dcloud/feature/uniapp/AbsSDKInstance;)V", "onActivityResultListenFunc", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", WXModule.REQUEST_CODE, WXModule.RESULT_CODE, "Landroid/content/Intent;", "data", "getOnActivityResultListenFunc", "setOnActivityResultListenFunc", "onConfigChangedListenFunc", "Lkotlin/Function1;", "Lio/dcloud/uts/UTSJSONObject;", BindingXConstants.KEY_CONFIG, "getOnConfigChangedListenFunc", "setOnConfigChangedListenFunc", "onCreateListenFunc", "getOnCreateListenFunc", "setOnCreateListenFunc", "onTrimMemoryListenFunc", "", MediaFormatExtraConstants.KEY_LEVEL, "getOnTrimMemoryListenFunc", "setOnTrimMemoryListenFunc", "pauseListenFunc", "getPauseListenFunc", "setPauseListenFunc", "permissionConfirmFunc", "Lio/dcloud/uts/UTSArray;", "", "getPermissionConfirmFunc", "permissionRequestFinishedFunc", "getPermissionRequestFinishedFunc", "permissionRequestFunc", "getPermissionRequestFunc", "permissionsResultListenFunc", "permissions", WXModule.GRANT_RESULTS, "getPermissionsResultListenFunc", "setPermissionsResultListenFunc", "resumeListenFunc", "getResumeListenFunc", "setResumeListenFunc", "stopListenFunc", "getStopListenFunc", "setStopListenFunc", "initApp", "initContext", "permission", "type", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidUTSContext {
    private static Context hostAppContext;
    private static AbsSDKInstance instance;
    public static final AndroidUTSContext INSTANCE = new AndroidUTSContext();
    private static CopyOnWriteArrayList<Function3<Integer, Integer, Intent, Unit>> onActivityResultListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function0<Unit>> destroyListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function0<Unit>> pauseListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function0<Unit>> resumeListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function0<Unit>> stopListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function0<Unit>> backListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function3<Integer, UTSArray<String>, UTSArray<Number>, Unit>> permissionsResultListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function1<Number, Unit>> onTrimMemoryListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function1<UTSJSONObject, Unit>> onConfigChangedListenFunc = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Function0<Unit>> onCreateListenFunc = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<Function1<UTSArray<String>, Unit>> permissionRequestFunc = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<Function1<UTSArray<String>, Unit>> permissionConfirmFunc = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<Function1<UTSArray<String>, Unit>> permissionRequestFinishedFunc = new CopyOnWriteArrayList<>();

    private AndroidUTSContext() {
    }

    public final Context getHostAppContext() {
        return hostAppContext;
    }

    public final void setHostAppContext(Context context) {
        hostAppContext = context;
    }

    public final AbsSDKInstance getInstance() {
        return instance;
    }

    public final void setInstance(AbsSDKInstance absSDKInstance) {
        instance = absSDKInstance;
    }

    public final CopyOnWriteArrayList<Function3<Integer, Integer, Intent, Unit>> getOnActivityResultListenFunc() {
        return onActivityResultListenFunc;
    }

    public final void setOnActivityResultListenFunc(CopyOnWriteArrayList<Function3<Integer, Integer, Intent, Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        onActivityResultListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function0<Unit>> getDestroyListenFunc() {
        return destroyListenFunc;
    }

    public final void setDestroyListenFunc(CopyOnWriteArrayList<Function0<Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        destroyListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function0<Unit>> getPauseListenFunc() {
        return pauseListenFunc;
    }

    public final void setPauseListenFunc(CopyOnWriteArrayList<Function0<Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        pauseListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function0<Unit>> getResumeListenFunc() {
        return resumeListenFunc;
    }

    public final void setResumeListenFunc(CopyOnWriteArrayList<Function0<Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        resumeListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function0<Unit>> getStopListenFunc() {
        return stopListenFunc;
    }

    public final void setStopListenFunc(CopyOnWriteArrayList<Function0<Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        stopListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function0<Unit>> getBackListenFunc() {
        return backListenFunc;
    }

    public final void setBackListenFunc(CopyOnWriteArrayList<Function0<Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        backListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function3<Integer, UTSArray<String>, UTSArray<Number>, Unit>> getPermissionsResultListenFunc() {
        return permissionsResultListenFunc;
    }

    public final void setPermissionsResultListenFunc(CopyOnWriteArrayList<Function3<Integer, UTSArray<String>, UTSArray<Number>, Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        permissionsResultListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function1<Number, Unit>> getOnTrimMemoryListenFunc() {
        return onTrimMemoryListenFunc;
    }

    public final void setOnTrimMemoryListenFunc(CopyOnWriteArrayList<Function1<Number, Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        onTrimMemoryListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function1<UTSJSONObject, Unit>> getOnConfigChangedListenFunc() {
        return onConfigChangedListenFunc;
    }

    public final void setOnConfigChangedListenFunc(CopyOnWriteArrayList<Function1<UTSJSONObject, Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        onConfigChangedListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function0<Unit>> getOnCreateListenFunc() {
        return onCreateListenFunc;
    }

    public final void setOnCreateListenFunc(CopyOnWriteArrayList<Function0<Unit>> copyOnWriteArrayList) {
        Intrinsics.checkNotNullParameter(copyOnWriteArrayList, "<set-?>");
        onCreateListenFunc = copyOnWriteArrayList;
    }

    public final CopyOnWriteArrayList<Function1<UTSArray<String>, Unit>> getPermissionRequestFunc() {
        return permissionRequestFunc;
    }

    public final CopyOnWriteArrayList<Function1<UTSArray<String>, Unit>> getPermissionConfirmFunc() {
        return permissionConfirmFunc;
    }

    public final CopyOnWriteArrayList<Function1<UTSArray<String>, Unit>> getPermissionRequestFinishedFunc() {
        return permissionRequestFinishedFunc;
    }

    public final void initContext(AbsSDKInstance instance2) {
        Intrinsics.checkNotNullParameter(instance2, "instance");
        hostAppContext = instance2.getContext().getApplicationContext();
        instance = instance2;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [io.dcloud.uts.android.AndroidUTSContext$initApp$2] */
    public final void initApp() {
        ActionBus.getInstance().observeAction(new AbsActionObserver(new IObserveAble() { // from class: io.dcloud.uts.android.AndroidUTSContext.initApp.2
            @Override // io.dcloud.common.DHInterface.message.IObserveAble
            public EnumUniqueID getActionObserverID() {
                return EnumUniqueID.FEATURE_UTS;
            }
        }) { // from class: io.dcloud.uts.android.AndroidUTSContext.initApp.1
            {
                super(anonymousClass2);
            }

            @Override // io.dcloud.common.DHInterface.message.AbsActionObserver
            public boolean handleMessage(IAction t) {
                if (t instanceof AppOnTrimMemoryAction) {
                    Iterator<Function1<Number, Unit>> it = AndroidUTSContext.INSTANCE.getOnTrimMemoryListenFunc().iterator();
                    while (it.hasNext()) {
                        it.next().invoke(Integer.valueOf(((AppOnTrimMemoryAction) t).getLevel()));
                    }
                    return true;
                }
                if (t instanceof AppOnConfigChangedAction) {
                    Iterator<Function1<UTSJSONObject, Unit>> it2 = AndroidUTSContext.INSTANCE.getOnConfigChangedListenFunc().iterator();
                    while (it2.hasNext()) {
                        Function1<UTSJSONObject, Unit> next = it2.next();
                        UTSJSONObject uTSJSONObject = new UTSJSONObject();
                        JSONObject object = JSONObject.parseObject(JSON.toJSONString(((AppOnConfigChangedAction) t).getConfig()));
                        Intrinsics.checkNotNullExpressionValue(object, "parseObject(JSON.toJSONString(t.config))");
                        uTSJSONObject.fillJSON(object);
                        next.invoke(uTSJSONObject);
                    }
                    return true;
                }
                if (t instanceof WebActivityOnDestroyAction) {
                    Iterator<Function0<Unit>> it3 = AndroidUTSContext.INSTANCE.getDestroyListenFunc().iterator();
                    while (it3.hasNext()) {
                        it3.next().invoke();
                    }
                    AndroidUTSContext.INSTANCE.getDestroyListenFunc().clear();
                    AndroidUTSContext.INSTANCE.getPauseListenFunc().clear();
                    AndroidUTSContext.INSTANCE.getResumeListenFunc().clear();
                    AndroidUTSContext.INSTANCE.getStopListenFunc().clear();
                    AndroidUTSContext.INSTANCE.getBackListenFunc().clear();
                    AndroidUTSContext.INSTANCE.getPermissionsResultListenFunc().clear();
                    AndroidUTSContext.INSTANCE.getOnTrimMemoryListenFunc().clear();
                    AndroidUTSContext.INSTANCE.getOnConfigChangedListenFunc().clear();
                    AndroidUTSContext.INSTANCE.getPermissionRequestFunc().clear();
                    AndroidUTSContext.INSTANCE.getPermissionConfirmFunc().clear();
                    AndroidUTSContext.INSTANCE.getPermissionRequestFinishedFunc().clear();
                    ActionBus.getInstance().stopObserve(EnumUniqueID.FEATURE_UTS);
                    return true;
                }
                if (!(t instanceof PermissionRequestAction)) {
                    return false;
                }
                AndroidUTSContext androidUTSContext = AndroidUTSContext.INSTANCE;
                PermissionRequestAction permissionRequestAction = (PermissionRequestAction) t;
                String type = permissionRequestAction.getType();
                Intrinsics.checkNotNullExpressionValue(type, "t.type");
                UTSArray.Companion companion = UTSArray.INSTANCE;
                String[] permissions = permissionRequestAction.getPermissions();
                Intrinsics.checkNotNullExpressionValue(permissions, "t.permissions");
                androidUTSContext.permission(type, companion.fromNative(permissions));
                return false;
            }
        });
    }

    public final void permission(String type, UTSArray<String> permissions) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (Intrinsics.areEqual(type, PermissionRequestAction.TYPE_REQUEST)) {
            Iterator<Function1<UTSArray<String>, Unit>> it = permissionRequestFunc.iterator();
            while (it.hasNext()) {
                it.next().invoke(permissions);
            }
        } else if (Intrinsics.areEqual(type, PermissionRequestAction.TYPE_CONFIRM)) {
            Iterator<Function1<UTSArray<String>, Unit>> it2 = permissionConfirmFunc.iterator();
            while (it2.hasNext()) {
                it2.next().invoke(permissions);
            }
        } else if (Intrinsics.areEqual(type, PermissionRequestAction.TYPE_COMPLETE)) {
            Iterator<Function1<UTSArray<String>, Unit>> it3 = permissionRequestFinishedFunc.iterator();
            while (it3.hasNext()) {
                it3.next().invoke(permissions);
            }
        }
    }
}
