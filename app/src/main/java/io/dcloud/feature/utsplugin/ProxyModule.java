package io.dcloud.feature.utsplugin;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.message.EnumUniqueID;
import io.dcloud.common.DHInterface.message.IObserveAble;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.AppConsoleLogUtil;
import io.dcloud.common.util.ErrorDialogUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.feature.uniapp.AbsSDKInstance;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.common.UniModule;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSJSONObject;
import io.dcloud.uts.android.AndroidUTSContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KProperty;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: UTSProxyModule.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 :2\u00020\u00012\u00020\u0002:\u0003:;<B\u0005¢\u0006\u0002\u0010\u0003J2\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J&\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J/\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00052\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0097@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ%\u0010\u001f\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\b\u0010 \u001a\u00020\tH\u0016J\b\u0010!\u001a\u00020\u0019H\u0016J\b\u0010\"\u001a\u00020\u0019H\u0016J\"\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010)\u001a\u00020\u0019H\u0016J\b\u0010*\u001a\u00020\u0019H\u0016J+\u0010+\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00162\u0006\u0010-\u001a\u00020.H\u0016¢\u0006\u0002\u0010/J:\u00100\u001a\u0004\u0018\u00010\u001b2\u0006\u00101\u001a\u00020\r2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\b\u00102\u001a\u0004\u0018\u00010\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u00103\u001a\u000204H\u0002J9\u00105\u001a\u0002H6\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H6072\u0016\u00108\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00130\u0016\"\u0004\u0018\u00010\u0013H\u0087@ø\u0001\u0000¢\u0006\u0002\u00109\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006="}, d2 = {"Lio/dcloud/feature/utsplugin/ProxyModule;", "Lio/dcloud/feature/uniapp/common/UniModule;", "Lio/dcloud/common/DHInterface/message/IObserveAble;", "()V", "findTargetFunc", "Lkotlin/reflect/KFunction;", "javaClazz", "Ljava/lang/Class;", "isCompanion", "", "methodName", "", AbsoluteConst.JSON_KEY_OPTION, "Lio/dcloud/feature/utsplugin/ProxyModule$InputOption;", "findTargetMethod", "Ljava/lang/reflect/Method;", "getActionObserverID", "Lio/dcloud/common/DHInterface/message/EnumUniqueID;", "getFunctionExecuteRet", "", "targetFunction", "paramArray", "", "(Lkotlin/reflect/KFunction;[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeAsync", "", "options", "Lcom/alibaba/fastjson/JSONObject;", WXBridgeManager.METHOD_CALLBACK, "Lcom/taobao/weex/bridge/JSCallback;", "(Lcom/alibaba/fastjson/JSONObject;Lcom/taobao/weex/bridge/JSCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeSync", "onActivityBack", "onActivityDestroy", "onActivityPause", "onActivityResult", WXModule.REQUEST_CODE, "", WXModule.RESULT_CODE, "data", "Landroid/content/Intent;", "onActivityResume", "onActivityStop", "onRequestPermissionsResult", "permissions", WXModule.GRANT_RESULTS, "", "(I[Ljava/lang/String;[I)V", "wrapDoTypeAction", "inputOption", "targetInstance", "errRet", "Lio/dcloud/feature/utsplugin/ReturnResult;", "callSuspend", "R", "Lkotlin/reflect/KCallable;", "args", "(Lkotlin/reflect/KCallable;[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "InputOption", "ModuleChecker", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProxyModule extends UniModule implements IObserveAble {
    private static int instanceDynamicId;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Map<Integer, Object> utsInstances = new LinkedHashMap();

    /* compiled from: UTSProxyModule.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule", f = "UTSProxyModule.kt", i = {0, 0}, l = {1054}, m = "callSuspend", n = {"$this$callSuspend", "args"}, s = {"L$0", "L$1"})
    /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$callSuspend$1, reason: invalid class name */
    static final class AnonymousClass1<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProxyModule.this.callSuspend(null, null, this);
        }
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule", f = "UTSProxyModule.kt", i = {}, l = {646, 647, 649}, m = "getFunctionExecuteRet", n = {}, s = {})
    /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$getFunctionExecuteRet$1, reason: invalid class name and case insensitive filesystem */
    static final class C01071 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01071(Continuation<? super C01071> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProxyModule.this.getFunctionExecuteRet(null, null, this);
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityDestroy() {
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityPause() {
        Iterator<Function0<Unit>> it = AndroidUTSContext.INSTANCE.getPauseListenFunc().iterator();
        while (it.hasNext()) {
            it.next().invoke();
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Iterator<Function3<Integer, Integer, Intent, Unit>> it = AndroidUTSContext.INSTANCE.getOnActivityResultListenFunc().iterator();
        while (it.hasNext()) {
            it.next().invoke(Integer.valueOf(requestCode), Integer.valueOf(resultCode), data);
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityResume() {
        Iterator<Function0<Unit>> it = AndroidUTSContext.INSTANCE.getResumeListenFunc().iterator();
        while (it.hasNext()) {
            it.next().invoke();
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityStop() {
        super.onActivityStop();
        Iterator<Function0<Unit>> it = AndroidUTSContext.INSTANCE.getStopListenFunc().iterator();
        while (it.hasNext()) {
            it.next().invoke();
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public boolean onActivityBack() {
        Iterator<Function0<Unit>> it = AndroidUTSContext.INSTANCE.getBackListenFunc().iterator();
        while (it.hasNext()) {
            it.next().invoke();
        }
        return false;
    }

    @Override // com.taobao.weex.common.WXModule
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        List listMutableListOf = CollectionsKt.mutableListOf(Arrays.copyOf(permissions, permissions.length));
        ArrayList arrayList = new ArrayList();
        for (int i : grantResults) {
            arrayList.add(Integer.valueOf(i));
        }
        Iterator<Function3<Integer, UTSArray<String>, UTSArray<Number>, Unit>> it = AndroidUTSContext.INSTANCE.getPermissionsResultListenFunc().iterator();
        while (it.hasNext()) {
            it.next().invoke(Integer.valueOf(requestCode), UTSArray.INSTANCE.fromNative(listMutableListOf), UTSArray.INSTANCE.fromNative(arrayList));
        }
    }

    private final Method findTargetMethod(Class<?> javaClazz, String methodName, InputOption option) throws SecurityException {
        Method[] methods = javaClazz.getMethods();
        Intrinsics.checkNotNullExpressionValue(methods, "methods");
        Method method = null;
        for (Method method2 : methods) {
            if (Intrinsics.areEqual(methodName, method2.getName()) && option.getParamArray().size() == UByte$$ExternalSyntheticBackport0.m(method2)) {
                int iM = UByte$$ExternalSyntheticBackport0.m(method2);
                boolean z = true;
                for (int i = 0; i < iM; i++) {
                    if (!method2.getParameterTypes()[i].isInstance(option.getParamArray().get(i))) {
                        z = false;
                    }
                }
                method = method2;
                if (z) {
                    break;
                }
            }
        }
        return method;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.KFunction<?> findTargetFunc(java.lang.Class<?> r11, boolean r12, java.lang.String r13, io.dcloud.feature.utsplugin.ProxyModule.InputOption r14) throws java.lang.IllegalAccessException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r10 = this;
            r0 = 0
            if (r12 == 0) goto L2f
            kotlin.reflect.KClass r11 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r11)
            kotlin.reflect.KClass r11 = kotlin.reflect.full.KClasses.getCompanionObject(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            java.util.Collection r11 = kotlin.reflect.full.KClasses.getMemberFunctions(r11)
            java.util.Iterator r11 = r11.iterator()
        L16:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto Led
            java.lang.Object r12 = r11.next()
            kotlin.reflect.KFunction r12 = (kotlin.reflect.KFunction) r12
            java.lang.String r14 = r12.getName()
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r13, r14)
            if (r14 == 0) goto L16
            r0 = r12
            goto Led
        L2f:
            java.lang.reflect.Method[] r11 = r11.getMethods()
            java.lang.String r12 = "methods"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r12)
            int r12 = r11.length
            r1 = 0
            r3 = r0
            r4 = r3
            r2 = r1
        L3d:
            if (r2 >= r12) goto Le0
            r5 = r11[r2]
            java.lang.String r6 = r5.getName()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r13, r6)
            if (r6 == 0) goto Ldc
            com.alibaba.fastjson.JSONArray r4 = r14.getParamArray()
            int r4 = r4.size()
            if (r4 != 0) goto La5
            java.lang.String r4 = "itemMethod"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r4)
            kotlin.reflect.KFunction r4 = kotlin.reflect.jvm.ReflectJvmMapping.getKotlinFunction(r5)
            if (r4 == 0) goto La1
            java.util.List r4 = r4.getParameters()
            if (r4 == 0) goto La1
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r6 = new java.util.ArrayList
            r7 = 10
            int r7 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r7)
            r6.<init>(r7)
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r4 = r4.iterator()
            r7 = r1
        L7a:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L9e
            java.lang.Object r8 = r4.next()
            kotlin.reflect.KParameter r8 = (kotlin.reflect.KParameter) r8
            kotlin.reflect.KType r9 = r8.getType()
            boolean r9 = r9.isMarkedNullable()
            if (r9 != 0) goto L98
            boolean r8 = r8.isOptional()
            if (r8 != 0) goto L98
            int r7 = r7 + 1
        L98:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            r6.add(r8)
            goto L7a
        L9e:
            java.util.List r6 = (java.util.List) r6
            goto La2
        La1:
            r7 = r1
        La2:
            if (r7 != 0) goto La5
            goto Ld8
        La5:
            com.alibaba.fastjson.JSONArray r4 = r14.getParamArray()
            int r4 = r4.size()
            int r6 = kotlin.UByte$$ExternalSyntheticBackport0.m(r5)
            if (r4 != r6) goto Ldb
            int r3 = kotlin.UByte$$ExternalSyntheticBackport0.m(r5)
            r4 = 1
            r6 = r1
        Lb9:
            if (r6 >= r3) goto Ld3
            java.lang.Class[] r7 = r5.getParameterTypes()
            r7 = r7[r6]
            com.alibaba.fastjson.JSONArray r8 = r14.getParamArray()
            java.lang.Object r8 = r8.get(r6)
            boolean r7 = r7.isInstance(r8)
            if (r7 != 0) goto Ld0
            r4 = r1
        Ld0:
            int r6 = r6 + 1
            goto Lb9
        Ld3:
            if (r4 == 0) goto Ld8
            r3 = r5
            r4 = r3
            goto Le0
        Ld8:
            r3 = r5
            r4 = r3
            goto Ldc
        Ldb:
            r4 = r5
        Ldc:
            int r2 = r2 + 1
            goto L3d
        Le0:
            if (r3 == 0) goto Le7
            kotlin.reflect.KFunction r0 = kotlin.reflect.jvm.ReflectJvmMapping.getKotlinFunction(r3)
            goto Led
        Le7:
            if (r4 == 0) goto Led
            kotlin.reflect.KFunction r0 = kotlin.reflect.jvm.ReflectJvmMapping.getKotlinFunction(r4)
        Led:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.utsplugin.ProxyModule.findTargetFunc(java.lang.Class, boolean, java.lang.String, io.dcloud.feature.utsplugin.ProxyModule$InputOption):kotlin.reflect.KFunction");
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR&\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lio/dcloud/feature/utsplugin/ProxyModule$Companion;", "", "()V", "instanceDynamicId", "", "getInstanceDynamicId", "()I", "setInstanceDynamicId", "(I)V", "utsInstances", "", "getUtsInstances", "()Ljava/util/Map;", "setUtsInstances", "(Ljava/util/Map;)V", "findTargetField", "Lkotlin/reflect/KCallable;", "javaClazz", "Ljava/lang/Class;", "isCompanion", "", "methodName", "", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getInstanceDynamicId() {
            return ProxyModule.instanceDynamicId;
        }

        public final void setInstanceDynamicId(int i) {
            ProxyModule.instanceDynamicId = i;
        }

        public final Map<Integer, Object> getUtsInstances() {
            return ProxyModule.utsInstances;
        }

        public final void setUtsInstances(Map<Integer, Object> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            ProxyModule.utsInstances = map;
        }

        public final KCallable<?> findTargetField(Class<?> javaClazz, boolean isCompanion, String methodName) {
            Field[] declaredFields;
            Collection<KProperty> memberProperties;
            Intrinsics.checkNotNullParameter(javaClazz, "javaClazz");
            Intrinsics.checkNotNullParameter(methodName, "methodName");
            if (isCompanion) {
                KClass<?> companionObject = KClasses.getCompanionObject(JvmClassMappingKt.getKotlinClass(javaClazz));
                Intrinsics.checkNotNull(companionObject);
                for (KCallable<?> kCallable : companionObject.getMembers()) {
                    if (Intrinsics.areEqual(methodName, kCallable.getName())) {
                        return kCallable;
                    }
                }
                return null;
            }
            try {
                memberProperties = KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(javaClazz));
                declaredFields = null;
            } catch (Exception unused) {
                declaredFields = javaClazz.getDeclaredFields();
                memberProperties = null;
            }
            if (memberProperties != null) {
                for (KProperty kProperty : memberProperties) {
                    if (Intrinsics.areEqual(methodName, kProperty.getName())) {
                        return kProperty;
                    }
                }
                return null;
            }
            if (declaredFields == null || declaredFields.length == 0) {
                return null;
            }
            Iterator it = ArrayIteratorKt.iterator(declaredFields);
            while (it.hasNext()) {
                Field field = (Field) it.next();
                if (Intrinsics.areEqual(methodName, field.getName())) {
                    return ReflectJvmMapping.getKotlinProperty(field);
                }
            }
            return null;
        }
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010;\u001a\u00020\u0006J\u0006\u0010<\u001a\u00020\u0006J\u0006\u0010=\u001a\u00020\u0019J*\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010?2\u0006\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010C2\b\b\u0002\u0010\u001f\u001a\u00020\u0019J0\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010?2\n\u0010@\u001a\u0006\u0012\u0002\b\u00030D2\b\u0010B\u001a\u0004\u0018\u00010C2\b\b\u0002\u0010\u001f\u001a\u00020\u0019H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001a\u0010\u001f\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u001a\u0010!\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001a\u0010#\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001a\"\u0004\b%\u0010\u001cR\u001a\u0010&\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\t\"\u0004\b.\u0010\u000bR\u001a\u0010/\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\t\"\u0004\b1\u0010\u000bR\u001a\u00102\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\t\"\u0004\b4\u0010\u000bR\u001a\u00105\u001a\u00020'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010)\"\u0004\b7\u0010+R\u001a\u00108\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\t\"\u0004\b:\u0010\u000b¨\u0006E"}, d2 = {"Lio/dcloud/feature/utsplugin/ProxyModule$InputOption;", "", "options", "Lcom/alibaba/fastjson/JSONObject;", "(Lcom/alibaba/fastjson/JSONObject;)V", "checkErrorMsg", "", "className", "getClassName", "()Ljava/lang/String;", "setClassName", "(Ljava/lang/String;)V", "inputModuleName", "getInputModuleName", "setInputModuleName", "inputModuleType", "getInputModuleType", "setInputModuleType", "instanceId", "", "getInstanceId", "()I", "setInstanceId", "(I)V", "isCompanion", "", "()Z", "setCompanion", "(Z)V", "isConstructor", "setConstructor", "isField", "setField", "isInstanceAction", "setInstanceAction", "keepAlive", "getKeepAlive", "setKeepAlive", "methodArray", "Lcom/alibaba/fastjson/JSONArray;", "getMethodArray", "()Lcom/alibaba/fastjson/JSONArray;", "setMethodArray", "(Lcom/alibaba/fastjson/JSONArray;)V", "methodName", "getMethodName", "setMethodName", "moduleName", "getModuleName", "setModuleName", "packageName", "getPackageName", "setPackageName", "paramArray", "getParamArray", "setParamArray", "type", "getType", "setType", "getErrorMsg", "getInputFlag", "isValid", "obtainParamsWithDefault", "", "targetFunction", "Ljava/lang/reflect/Method;", WXBridgeManager.METHOD_CALLBACK, "Lcom/taobao/weex/bridge/JSCallback;", "Lkotlin/reflect/KFunction;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InputOption {
        private String checkErrorMsg;
        private String className;
        private String inputModuleName;
        private String inputModuleType;
        private int instanceId;
        private boolean isCompanion;
        private boolean isConstructor;
        private boolean isField;
        private boolean isInstanceAction;
        private boolean keepAlive;
        private JSONArray methodArray;
        private String methodName;
        private String moduleName;
        private String packageName;
        private JSONArray paramArray;
        private String type;

        public final String getPackageName() {
            return this.packageName;
        }

        public final void setPackageName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.packageName = str;
        }

        public final String getClassName() {
            return this.className;
        }

        public final void setClassName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.className = str;
        }

        public final String getMethodName() {
            return this.methodName;
        }

        public final void setMethodName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.methodName = str;
        }

        public final JSONArray getParamArray() {
            return this.paramArray;
        }

        public final void setParamArray(JSONArray jSONArray) {
            Intrinsics.checkNotNullParameter(jSONArray, "<set-?>");
            this.paramArray = jSONArray;
        }

        public final JSONArray getMethodArray() {
            return this.methodArray;
        }

        public final void setMethodArray(JSONArray jSONArray) {
            Intrinsics.checkNotNullParameter(jSONArray, "<set-?>");
            this.methodArray = jSONArray;
        }

        /* renamed from: isConstructor, reason: from getter */
        public final boolean getIsConstructor() {
            return this.isConstructor;
        }

        public final void setConstructor(boolean z) {
            this.isConstructor = z;
        }

        /* renamed from: isCompanion, reason: from getter */
        public final boolean getIsCompanion() {
            return this.isCompanion;
        }

        public final void setCompanion(boolean z) {
            this.isCompanion = z;
        }

        /* renamed from: isField, reason: from getter */
        public final boolean getIsField() {
            return this.isField;
        }

        public final void setField(boolean z) {
            this.isField = z;
        }

        public final String getModuleName() {
            return this.moduleName;
        }

        public final void setModuleName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.moduleName = str;
        }

        /* renamed from: isInstanceAction, reason: from getter */
        public final boolean getIsInstanceAction() {
            return this.isInstanceAction;
        }

        public final void setInstanceAction(boolean z) {
            this.isInstanceAction = z;
        }

        public final int getInstanceId() {
            return this.instanceId;
        }

        public final void setInstanceId(int i) {
            this.instanceId = i;
        }

        public final String getInputModuleName() {
            return this.inputModuleName;
        }

        public final void setInputModuleName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.inputModuleName = str;
        }

        public final String getInputModuleType() {
            return this.inputModuleType;
        }

        public final void setInputModuleType(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.inputModuleType = str;
        }

        public final boolean getKeepAlive() {
            return this.keepAlive;
        }

        public final void setKeepAlive(boolean z) {
            this.keepAlive = z;
        }

        public final String getType() {
            return this.type;
        }

        public final void setType(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.type = str;
        }

        public InputOption(JSONObject options) {
            Intrinsics.checkNotNullParameter(options, "options");
            this.packageName = "";
            this.className = "";
            this.methodName = "";
            this.paramArray = new JSONArray();
            this.methodArray = new JSONArray();
            this.moduleName = "";
            this.inputModuleName = "";
            this.inputModuleType = "";
            this.type = "";
            this.checkErrorMsg = "";
            if (options.containsKey("moduleName")) {
                Object obj = options.get("moduleName");
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                this.inputModuleName = (String) obj;
            }
            if (options.containsKey("moduleType")) {
                Object obj2 = options.get("moduleType");
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                this.inputModuleType = (String) obj2;
            }
            if (options.containsKey("keepAlive")) {
                Object obj3 = options.get("keepAlive");
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Boolean");
                this.keepAlive = ((Boolean) obj3).booleanValue();
            }
            if (options.containsKey("package")) {
                Object obj4 = options.get("package");
                Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.String");
                this.packageName = (String) obj4;
            }
            if (options.containsKey("class")) {
                Object obj5 = options.get("class");
                Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.String");
                this.className = (String) obj5;
            }
            if (options.containsKey("params")) {
                Object obj6 = options.get("params");
                Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type com.alibaba.fastjson.JSONArray");
                this.paramArray = (JSONArray) obj6;
                this.isField = false;
            } else {
                this.isField = true;
            }
            if (options.containsKey("name")) {
                Object obj7 = options.get("name");
                Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.String");
                this.methodName = (String) obj7;
            }
            if (options.containsKey("id")) {
                Object obj8 = options.get("id");
                Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.Int");
                this.instanceId = ((Integer) obj8).intValue();
                this.isInstanceAction = true;
                this.moduleName = this.methodName;
            } else {
                this.isInstanceAction = false;
                if (options.containsKey("companion")) {
                    Object obj9 = options.get("companion");
                    Intrinsics.checkNotNull(obj9, "null cannot be cast to non-null type kotlin.Boolean");
                    this.isCompanion = ((Boolean) obj9).booleanValue();
                } else {
                    this.isCompanion = false;
                }
                if (!this.isField && Intrinsics.areEqual("constructor", this.methodName)) {
                    this.isConstructor = true;
                }
                this.moduleName = String.valueOf(this.packageName);
                if (!TextUtils.isEmpty(this.className)) {
                    this.moduleName = this.packageName + Operators.DOT + this.className;
                }
            }
            if (options.containsKey("method")) {
                JSONArray jSONArray = options.getJSONArray("method");
                Intrinsics.checkNotNullExpressionValue(jSONArray, "options.getJSONArray(\"method\")");
                this.methodArray = jSONArray;
            }
            if (options.containsKey("type")) {
                String string = options.getString("type");
                Intrinsics.checkNotNullExpressionValue(string, "options.getString(\"type\")");
                this.type = string;
            }
        }

        public static /* synthetic */ List obtainParamsWithDefault$default(InputOption inputOption, Method method, JSCallback jSCallback, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return inputOption.obtainParamsWithDefault(method, jSCallback, z);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00a5  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<java.lang.Object> obtainParamsWithDefault(java.lang.reflect.Method r12, com.taobao.weex.bridge.JSCallback r13, boolean r14) {
            /*
                r11 = this;
                java.lang.String r0 = "targetFunction"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.util.List r0 = (java.util.List) r0
                java.lang.reflect.Parameter[] r1 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r12)
                int r1 = r1.length
                if (r1 <= 0) goto Lba
                java.lang.reflect.Parameter[] r12 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r12)
                java.lang.String r1 = "targetFunction.parameters"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)
                int r1 = r12.length
                r2 = 0
                r3 = r2
                r4 = r3
            L20:
                if (r2 >= r1) goto Lba
                r5 = r12[r2]
                r6 = 0
                if (r14 == 0) goto L36
                java.lang.Class r5 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r5)
                java.lang.String r5 = r5.toString()
                java.lang.String r7 = "perParam.type.toString()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)
            L34:
                r7 = r6
                goto L6e
            L36:
                java.lang.Class r7 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r5)
                if (r7 == 0) goto L4f
                java.lang.Class r5 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r5)
                java.lang.String r7 = "null cannot be cast to non-null type java.lang.Class<*>"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r7)
                java.lang.String r5 = r5.getName()
                java.lang.String r7 = "perParam.type as Class<*>).name"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)
                goto L34
            L4f:
                java.lang.Class r7 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r5)
                java.lang.String r7 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r7)
                java.lang.String r8 = "perParam.type.typeName"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
                java.lang.Class r5 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r5)
                java.lang.String r8 = "null cannot be cast to non-null type java.lang.reflect.ParameterizedType"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r8)
                java.lang.reflect.ParameterizedType r5 = (java.lang.reflect.ParameterizedType) r5
                java.lang.reflect.Type[] r5 = r5.getActualTypeArguments()
                r10 = r7
                r7 = r5
                r5 = r10
            L6e:
                io.dcloud.feature.utsplugin.ParamConvertHelper r8 = new io.dcloud.feature.utsplugin.ParamConvertHelper
                boolean r9 = r11.keepAlive
                r8.<init>(r5, r7, r13, r9)
                com.alibaba.fastjson.JSONArray r5 = r11.paramArray
                int r5 = r5.size()
                if (r3 < r5) goto La5
                com.alibaba.fastjson.JSONArray r5 = r11.methodArray
                java.lang.Object r5 = r5.get(r4)
                java.lang.String r7 = "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r7)
                com.alibaba.fastjson.JSONObject r5 = (com.alibaba.fastjson.JSONObject) r5
                java.lang.String r7 = "default"
                java.lang.Object r9 = r5.get(r7)
                if (r9 != 0) goto L96
                r0.add(r6)
                goto Lb4
            L96:
                java.lang.Object r5 = r5.get(r7)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                java.lang.Object r5 = r8.getInstance(r5)
                r0.add(r5)
                goto Lb4
            La5:
                com.alibaba.fastjson.JSONArray r5 = r11.paramArray
                java.lang.Object r5 = r5.get(r3)
                java.lang.Object r5 = r8.getInstance(r5)
                r0.add(r5)
                int r3 = r3 + 1
            Lb4:
                int r4 = r4 + 1
                int r2 = r2 + 1
                goto L20
            Lba:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.utsplugin.ProxyModule.InputOption.obtainParamsWithDefault(java.lang.reflect.Method, com.taobao.weex.bridge.JSCallback, boolean):java.util.List");
        }

        public static /* synthetic */ List obtainParamsWithDefault$default(InputOption inputOption, KFunction kFunction, JSCallback jSCallback, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return inputOption.obtainParamsWithDefault((KFunction<?>) kFunction, jSCallback, z);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x00aa  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00d2  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.List<java.lang.Object> obtainParamsWithDefault(kotlin.reflect.KFunction<?> r10, com.taobao.weex.bridge.JSCallback r11, boolean r12) {
            /*
                r9 = this;
                java.lang.String r0 = "targetFunction"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.util.List r0 = (java.util.List) r0
                java.util.List r1 = r10.getParameters()
                int r1 = r1.size()
                if (r1 <= 0) goto Le5
                java.util.List r10 = r10.getParameters()
                java.util.Iterator r10 = r10.iterator()
                r1 = 0
                r2 = r1
            L20:
                boolean r3 = r10.hasNext()
                if (r3 == 0) goto Le5
                java.lang.Object r3 = r10.next()
                kotlin.reflect.KParameter r3 = (kotlin.reflect.KParameter) r3
                kotlin.reflect.KParameter$Kind r4 = r3.getKind()
                kotlin.reflect.KParameter$Kind r5 = kotlin.reflect.KParameter.Kind.INSTANCE
                if (r4 != r5) goto L42
                java.lang.String r4 = r3.getName()
                if (r4 != 0) goto Le1
                boolean r3 = r3.isOptional()
                if (r3 == 0) goto L20
                goto Le1
            L42:
                r4 = 0
                if (r12 == 0) goto L4f
                kotlin.reflect.KType r3 = r3.getType()
                java.lang.String r3 = r3.toString()
            L4d:
                r5 = r4
                goto L9b
            L4f:
                kotlin.reflect.KType r5 = r3.getType()
                java.lang.reflect.Type r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r5)
                boolean r5 = r5 instanceof java.lang.Class
                if (r5 == 0) goto L74
                kotlin.reflect.KType r3 = r3.getType()
                java.lang.reflect.Type r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r3)
                java.lang.String r5 = "null cannot be cast to non-null type java.lang.Class<*>"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
                java.lang.Class r3 = (java.lang.Class) r3
                java.lang.String r3 = r3.getName()
                java.lang.String r5 = "perParam.type.javaType as Class<*>).name"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
                goto L4d
            L74:
                kotlin.reflect.KType r5 = r3.getType()
                java.lang.reflect.Type r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r5)
                java.lang.String r5 = dc.squareup.okio.Okio$$ExternalSyntheticApiModelOutline0.m(r5)
                java.lang.String r6 = "perParam.type.javaType.typeName"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
                kotlin.reflect.KType r3 = r3.getType()
                java.lang.reflect.Type r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r3)
                java.lang.String r6 = "null cannot be cast to non-null type java.lang.reflect.ParameterizedType"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
                java.lang.reflect.ParameterizedType r3 = (java.lang.reflect.ParameterizedType) r3
                java.lang.reflect.Type[] r3 = r3.getActualTypeArguments()
                r8 = r5
                r5 = r3
                r3 = r8
            L9b:
                io.dcloud.feature.utsplugin.ParamConvertHelper r6 = new io.dcloud.feature.utsplugin.ParamConvertHelper
                boolean r7 = r9.keepAlive
                r6.<init>(r3, r5, r11, r7)
                com.alibaba.fastjson.JSONArray r3 = r9.paramArray
                int r3 = r3.size()
                if (r1 < r3) goto Ld2
                com.alibaba.fastjson.JSONArray r3 = r9.methodArray
                java.lang.Object r3 = r3.get(r2)
                java.lang.String r5 = "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
                com.alibaba.fastjson.JSONObject r3 = (com.alibaba.fastjson.JSONObject) r3
                java.lang.String r5 = "default"
                java.lang.Object r7 = r3.get(r5)
                if (r7 != 0) goto Lc3
                r0.add(r4)
                goto Le1
            Lc3:
                java.lang.Object r3 = r3.get(r5)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                java.lang.Object r3 = r6.getInstance(r3)
                r0.add(r3)
                goto Le1
            Ld2:
                com.alibaba.fastjson.JSONArray r3 = r9.paramArray
                java.lang.Object r3 = r3.get(r1)
                java.lang.Object r3 = r6.getInstance(r3)
                r0.add(r3)
                int r1 = r1 + 1
            Le1:
                int r2 = r2 + 1
                goto L20
            Le5:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.utsplugin.ProxyModule.InputOption.obtainParamsWithDefault(kotlin.reflect.KFunction, com.taobao.weex.bridge.JSCallback, boolean):java.util.List");
        }

        public final String getInputFlag() {
            return Operators.ARRAY_START_STR + this.moduleName + '-' + this.methodName + Operators.ARRAY_END;
        }

        public final boolean isValid() {
            if (!TextUtils.isEmpty(this.packageName) && !StringsKt.startsWith$default(this.packageName, "uts.sdk.modules", false, 2, (Object) null) && !StringsKt.startsWith$default(this.packageName, "uts.sdk", false, 2, (Object) null) && !StringsKt.startsWith$default(this.packageName, "uts.modules", false, 2, (Object) null)) {
                return false;
            }
            if (this.isInstanceAction) {
                return true;
            }
            if (!TextUtils.isEmpty(this.moduleName) && !TextUtils.isEmpty(this.methodName)) {
                return true;
            }
            this.checkErrorMsg = "param is null " + this.moduleName + ' ' + this.methodName;
            return false;
        }

        /* renamed from: getErrorMsg, reason: from getter */
        public final String getCheckErrorMsg() {
            return this.checkErrorMsg;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0065 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getFunctionExecuteRet(kotlin.reflect.KFunction<?> r7, java.lang.Object[] r8, kotlin.coroutines.Continuation<java.lang.Object> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.dcloud.feature.utsplugin.ProxyModule.C01071
            if (r0 == 0) goto L14
            r0 = r9
            io.dcloud.feature.utsplugin.ProxyModule$getFunctionExecuteRet$1 r0 = (io.dcloud.feature.utsplugin.ProxyModule.C01071) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.dcloud.feature.utsplugin.ProxyModule$getFunctionExecuteRet$1 r0 = new io.dcloud.feature.utsplugin.ProxyModule$getFunctionExecuteRet$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L3d
            if (r2 == r5) goto L39
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            goto L35
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L35:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L76
        L39:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L56
        L3d:
            kotlin.ResultKt.throwOnFailure(r9)
            boolean r9 = r7.isSuspend()
            if (r9 == 0) goto L66
            kotlin.reflect.KCallable r7 = (kotlin.reflect.KCallable) r7
            int r9 = r8.length
            java.lang.Object[] r8 = java.util.Arrays.copyOf(r8, r9)
            r0.label = r5
            java.lang.Object r9 = r6.callSuspend(r7, r8, r0)
            if (r9 != r1) goto L56
            return r1
        L56:
            java.lang.String r7 = "null cannot be cast to non-null type kotlinx.coroutines.Deferred<kotlin.Any?>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9, r7)
            kotlinx.coroutines.Deferred r9 = (kotlinx.coroutines.Deferred) r9
            r0.label = r4
            java.lang.Object r9 = r9.await(r0)
            if (r9 != r1) goto L76
            return r1
        L66:
            kotlin.reflect.KCallable r7 = (kotlin.reflect.KCallable) r7
            int r9 = r8.length
            java.lang.Object[] r8 = java.util.Arrays.copyOf(r8, r9)
            r0.label = r3
            java.lang.Object r9 = r6.callSuspend(r7, r8, r0)
            if (r9 != r1) goto L76
            return r1
        L76:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.utsplugin.ProxyModule.getFunctionExecuteRet(kotlin.reflect.KFunction, java.lang.Object[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final JSONObject wrapDoTypeAction(InputOption inputOption, Class<?> javaClazz, Object targetInstance, JSCallback callback, ReturnResult errRet) throws SecurityException {
        String strValueOf;
        String methodName = inputOption.getMethodName();
        if (StringsKt.equals("setter", inputOption.getType(), true)) {
            StringBuilder sb = new StringBuilder("set");
            if (methodName.length() > 0) {
                StringBuilder sb2 = new StringBuilder();
                char cCharAt = methodName.charAt(0);
                if (Character.isLowerCase(cCharAt)) {
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    strValueOf = CharsKt.titlecase(cCharAt, locale);
                } else {
                    strValueOf = String.valueOf(cCharAt);
                }
                sb2.append((Object) strValueOf);
                String strSubstring = methodName.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                sb2.append(strSubstring);
                methodName = sb2.toString();
            }
            sb.append(methodName);
            Method methodFindTargetMethod = findTargetMethod(javaClazz, sb.toString(), inputOption);
            if (methodFindTargetMethod == null) {
                errRet.updateError("method not found:" + inputOption.getInputFlag());
                return errRet.toJSON();
            }
            List listObtainParamsWithDefault$default = InputOption.obtainParamsWithDefault$default(inputOption, methodFindTargetMethod, callback, false, 4, (Object) null);
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            try {
                BuildersKt__BuildersKt.runBlocking$default(null, new C01092(objectRef, errRet, methodFindTargetMethod, targetInstance, listObtainParamsWithDefault$default, null), 1, null);
            } catch (Exception e) {
                objectRef.element = null;
                if (e.getCause() != null) {
                    errRet.updateError("targetMethod error::" + e.getCause());
                } else {
                    errRet.updateError("targetMethod error::" + e);
                }
            }
            if (objectRef.element != 0) {
                if (objectRef.element instanceof UTSJSONObject) {
                    T t = objectRef.element;
                    Intrinsics.checkNotNull(t, "null cannot be cast to non-null type io.dcloud.uts.UTSJSONObject");
                    errRet.updateJSON(((UTSJSONObject) t).toJSONObject());
                } else {
                    errRet.updateJSON(objectRef.element);
                }
                errRet.toJSON();
            }
        }
        return null;
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule$wrapDoTypeAction$2", f = "UTSProxyModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$wrapDoTypeAction$2, reason: invalid class name and case insensitive filesystem */
    static final class C01092 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ ReturnResult $errRet;
        final /* synthetic */ Ref.ObjectRef<Object> $executeRet;
        final /* synthetic */ List<Object> $paramList;
        final /* synthetic */ Object $targetInstance;
        final /* synthetic */ Method $targetMethod;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01092(Ref.ObjectRef<Object> objectRef, ReturnResult returnResult, Method method, Object obj, List<Object> list, Continuation<? super C01092> continuation) {
            super(2, continuation);
            this.$executeRet = objectRef;
            this.$errRet = returnResult;
            this.$targetMethod = method;
            this.$targetInstance = obj;
            this.$paramList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01092 c01092 = new C01092(this.$executeRet, this.$errRet, this.$targetMethod, this.$targetInstance, this.$paramList, continuation);
            c01092.L$0 = obj;
            return c01092;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C01092) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, new ProxyModule$wrapDoTypeAction$2$invokeSuspend$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this.$executeRet, this.$errRet), null, new C00512(this.$executeRet, this.$targetMethod, this.$targetInstance, this.$paramList, null), 2, null);
        }

        /* compiled from: UTSProxyModule.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule$wrapDoTypeAction$2$2", f = "UTSProxyModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$wrapDoTypeAction$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00512 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.ObjectRef<Object> $executeRet;
            final /* synthetic */ List<Object> $paramList;
            final /* synthetic */ Object $targetInstance;
            final /* synthetic */ Method $targetMethod;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00512(Ref.ObjectRef<Object> objectRef, Method method, Object obj, List<Object> list, Continuation<? super C00512> continuation) {
                super(2, continuation);
                this.$executeRet = objectRef;
                this.$targetMethod = method;
                this.$targetInstance = obj;
                this.$paramList = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00512(this.$executeRet, this.$targetMethod, this.$targetInstance, this.$paramList, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00512) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<Object> objectRef = this.$executeRet;
                Method method = this.$targetMethod;
                Object obj2 = this.$targetInstance;
                Object[] array = this.$paramList.toArray(new Object[0]);
                objectRef.element = method.invoke(obj2, Arrays.copyOf(array, array.length));
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01b0 A[Catch: Exception -> 0x01e9, TryCatch #2 {Exception -> 0x01e9, blocks: (B:10:0x0050, B:12:0x0060, B:20:0x00d4, B:29:0x0108, B:33:0x0132, B:50:0x01ac, B:52:0x01b0, B:54:0x01b6, B:55:0x01cb, B:57:0x01d8, B:46:0x0179, B:48:0x0181, B:49:0x0199, B:59:0x01e1, B:60:0x01e8, B:73:0x0209, B:75:0x0214, B:77:0x0219, B:79:0x025d, B:78:0x0241, B:81:0x0266, B:83:0x026c, B:85:0x027c, B:87:0x029c, B:89:0x02a2, B:91:0x02bb, B:90:0x02b3, B:93:0x02cb, B:95:0x02d1, B:97:0x02df, B:99:0x0316, B:98:0x02e9, B:101:0x0334, B:67:0x01fc, B:69:0x0200, B:64:0x01f1), top: B:139:0x004e, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01d8 A[Catch: Exception -> 0x01e9, TryCatch #2 {Exception -> 0x01e9, blocks: (B:10:0x0050, B:12:0x0060, B:20:0x00d4, B:29:0x0108, B:33:0x0132, B:50:0x01ac, B:52:0x01b0, B:54:0x01b6, B:55:0x01cb, B:57:0x01d8, B:46:0x0179, B:48:0x0181, B:49:0x0199, B:59:0x01e1, B:60:0x01e8, B:73:0x0209, B:75:0x0214, B:77:0x0219, B:79:0x025d, B:78:0x0241, B:81:0x0266, B:83:0x026c, B:85:0x027c, B:87:0x029c, B:89:0x02a2, B:91:0x02bb, B:90:0x02b3, B:93:0x02cb, B:95:0x02d1, B:97:0x02df, B:99:0x0316, B:98:0x02e9, B:101:0x0334, B:67:0x01fc, B:69:0x0200, B:64:0x01f1), top: B:139:0x004e, inners: #4 }] */
    /* JADX WARN: Type inference failed for: r0v0, types: [T, io.dcloud.feature.utsplugin.ReturnResult] */
    /* JADX WARN: Type inference failed for: r0v18, types: [T, kotlin.reflect.KFunction] */
    /* JADX WARN: Type inference failed for: r0v38, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v61, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v14, types: [T, kotlin.reflect.KFunction] */
    /* JADX WARN: Type inference failed for: r2v4, types: [T, java.util.List] */
    @io.dcloud.feature.uniapp.annotation.UniJSMethod(uiThread = false)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSync(com.alibaba.fastjson.JSONObject r21, com.taobao.weex.bridge.JSCallback r22, kotlin.coroutines.Continuation<java.lang.Object> r23) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1119
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.utsplugin.ProxyModule.invokeSync(com.alibaba.fastjson.JSONObject, com.taobao.weex.bridge.JSCallback, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule$invokeSync$2", f = "UTSProxyModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$invokeSync$2, reason: invalid class name and case insensitive filesystem */
    static final class C01082 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ Ref.ObjectRef<ReturnResult> $errRet;
        final /* synthetic */ Ref.ObjectRef<Object> $executeRet;
        final /* synthetic */ InputOption $inputOption;
        final /* synthetic */ Class<Object> $javaClazz;
        final /* synthetic */ Ref.ObjectRef<List<Object>> $paramList;
        final /* synthetic */ KFunction<?> $targetFunction;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ProxyModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01082(Ref.ObjectRef<Object> objectRef, Ref.ObjectRef<ReturnResult> objectRef2, InputOption inputOption, Ref.ObjectRef<List<Object>> objectRef3, Class<Object> cls, ProxyModule proxyModule, KFunction<?> kFunction, Continuation<? super C01082> continuation) {
            super(2, continuation);
            this.$executeRet = objectRef;
            this.$errRet = objectRef2;
            this.$inputOption = inputOption;
            this.$paramList = objectRef3;
            this.$javaClazz = cls;
            this.this$0 = proxyModule;
            this.$targetFunction = kFunction;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01082 c01082 = new C01082(this.$executeRet, this.$errRet, this.$inputOption, this.$paramList, this.$javaClazz, this.this$0, this.$targetFunction, continuation);
            c01082.L$0 = obj;
            return c01082;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C01082) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, new ProxyModule$invokeSync$2$invokeSuspend$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this.$executeRet, this.$errRet), null, new C00502(this.$inputOption, this.$paramList, this.$javaClazz, this.$executeRet, this.this$0, this.$targetFunction, null), 2, null);
        }

        /* compiled from: UTSProxyModule.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule$invokeSync$2$2", f = "UTSProxyModule.kt", i = {}, l = {846}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$invokeSync$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00502 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.ObjectRef<Object> $executeRet;
            final /* synthetic */ InputOption $inputOption;
            final /* synthetic */ Class<Object> $javaClazz;
            final /* synthetic */ Ref.ObjectRef<List<Object>> $paramList;
            final /* synthetic */ KFunction<?> $targetFunction;
            Object L$0;
            int label;
            final /* synthetic */ ProxyModule this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00502(InputOption inputOption, Ref.ObjectRef<List<Object>> objectRef, Class<Object> cls, Ref.ObjectRef<Object> objectRef2, ProxyModule proxyModule, KFunction<?> kFunction, Continuation<? super C00502> continuation) {
                super(2, continuation);
                this.$inputOption = inputOption;
                this.$paramList = objectRef;
                this.$javaClazz = cls;
                this.$executeRet = objectRef2;
                this.this$0 = proxyModule;
                this.$targetFunction = kFunction;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00502(this.$inputOption, this.$paramList, this.$javaClazz, this.$executeRet, this.this$0, this.$targetFunction, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00502) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Ref.ObjectRef<Object> objectRef;
                T t;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.$inputOption.getIsCompanion()) {
                        List<Object> list = this.$paramList.element;
                        Object companionObject = KClasses.getCompanionObject(JvmClassMappingKt.getKotlinClass(this.$javaClazz));
                        Intrinsics.checkNotNull(companionObject, "null cannot be cast to non-null type kotlin.Nothing");
                        list.add(0, (Void) companionObject);
                    }
                    Object[] array = this.$paramList.element.toArray(new Object[0]);
                    Ref.ObjectRef<Object> objectRef2 = this.$executeRet;
                    ProxyModule proxyModule = this.this$0;
                    KFunction<?> kFunction = this.$targetFunction;
                    Intrinsics.checkNotNull(kFunction);
                    this.L$0 = objectRef2;
                    this.label = 1;
                    Object functionExecuteRet = proxyModule.getFunctionExecuteRet(kFunction, array, this);
                    if (functionExecuteRet == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                    t = functionExecuteRet;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    t = obj;
                }
                objectRef.element = t;
                return Unit.INSTANCE;
            }
        }
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule$invokeSync$3", f = "UTSProxyModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$invokeSync$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ Ref.ObjectRef<ReturnResult> $errRet;
        final /* synthetic */ Ref.ObjectRef<Object> $executeRet;
        final /* synthetic */ Ref.ObjectRef<List<Object>> $paramList;
        final /* synthetic */ Ref.ObjectRef<KFunction<?>> $targetFunction;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ProxyModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.ObjectRef<Object> objectRef, Ref.ObjectRef<ReturnResult> objectRef2, Ref.ObjectRef<List<Object>> objectRef3, ProxyModule proxyModule, Ref.ObjectRef<KFunction<?>> objectRef4, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$executeRet = objectRef;
            this.$errRet = objectRef2;
            this.$paramList = objectRef3;
            this.this$0 = proxyModule;
            this.$targetFunction = objectRef4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$executeRet, this.$errRet, this.$paramList, this.this$0, this.$targetFunction, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, new ProxyModule$invokeSync$3$invokeSuspend$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this.$executeRet, this.$errRet), null, new AnonymousClass2(this.$paramList, this.$executeRet, this.this$0, this.$targetFunction, null), 2, null);
        }

        /* compiled from: UTSProxyModule.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule$invokeSync$3$2", f = "UTSProxyModule.kt", i = {}, l = {1001}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$invokeSync$3$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.ObjectRef<Object> $executeRet;
            final /* synthetic */ Ref.ObjectRef<List<Object>> $paramList;
            final /* synthetic */ Ref.ObjectRef<KFunction<?>> $targetFunction;
            Object L$0;
            int label;
            final /* synthetic */ ProxyModule this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(Ref.ObjectRef<List<Object>> objectRef, Ref.ObjectRef<Object> objectRef2, ProxyModule proxyModule, Ref.ObjectRef<KFunction<?>> objectRef3, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$paramList = objectRef;
                this.$executeRet = objectRef2;
                this.this$0 = proxyModule;
                this.$targetFunction = objectRef3;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$paramList, this.$executeRet, this.this$0, this.$targetFunction, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Ref.ObjectRef<Object> objectRef;
                T t;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Object[] array = this.$paramList.element.toArray(new Object[0]);
                    Ref.ObjectRef<Object> objectRef2 = this.$executeRet;
                    this.L$0 = objectRef2;
                    this.label = 1;
                    Object functionExecuteRet = this.this$0.getFunctionExecuteRet(this.$targetFunction.element, array, this);
                    if (functionExecuteRet == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                    t = functionExecuteRet;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    t = obj;
                }
                objectRef.element = t;
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <R> java.lang.Object callSuspend(kotlin.reflect.KCallable<? extends R> r5, java.lang.Object[] r6, kotlin.coroutines.Continuation<? super R> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.dcloud.feature.utsplugin.ProxyModule.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r7
            io.dcloud.feature.utsplugin.ProxyModule$callSuspend$1 r0 = (io.dcloud.feature.utsplugin.ProxyModule.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.dcloud.feature.utsplugin.ProxyModule$callSuspend$1 r0 = new io.dcloud.feature.utsplugin.ProxyModule$callSuspend$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r5 = r0.L$1
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            java.lang.Object r5 = r0.L$0
            kotlin.reflect.KCallable r5 = (kotlin.reflect.KCallable) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L7e
        L32:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3a:
            kotlin.ResultKt.throwOnFailure(r7)
            boolean r7 = r5.isSuspend()
            if (r7 != 0) goto L4d
            int r7 = r6.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)
            java.lang.Object r5 = r5.call(r6)
            return r5
        L4d:
            boolean r7 = r5 instanceof kotlin.reflect.KFunction
            if (r7 == 0) goto La2
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            java.util.List r6 = kotlin.collections.ArraysKt.toMutableList(r6)
            r6.add(r0)
            java.util.Collection r6 = (java.util.Collection) r6
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.Object[] r6 = r6.toArray(r7)
            int r7 = r6.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)
            java.lang.Object r7 = r5.call(r6)
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r7 != r6) goto L7b
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L7b:
            if (r7 != r1) goto L7e
            return r1
        L7e:
            kotlin.reflect.KType r6 = r5.getReturnType()
            kotlin.reflect.KClassifier r6 = r6.getClassifier()
            java.lang.Class<kotlin.Unit> r0 = kotlin.Unit.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            if (r6 == 0) goto La1
            kotlin.reflect.KType r5 = r5.getReturnType()
            boolean r5 = r5.isMarkedNullable()
            if (r5 != 0) goto La1
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            java.lang.Object r5 = (java.lang.Object) r5
            return r5
        La1:
            return r7
        La2:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Cannot callSuspend on a property "
            r7.<init>(r0)
            r7.append(r5)
            java.lang.String r5 = ": suspend properties are not supported yet"
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.utsplugin.ProxyModule.callSuspend(kotlin.reflect.KCallable, java.lang.Object[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/dcloud/feature/utsplugin/ProxyModule$ModuleChecker;", "", "inputOption", "Lio/dcloud/feature/utsplugin/ProxyModule$InputOption;", "(Lio/dcloud/feature/utsplugin/ProxyModule$InputOption;)V", "shallShowErrorDialog", "", "showErrorDialog", "", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ModuleChecker {
        private final InputOption inputOption;

        public ModuleChecker(InputOption inputOption) {
            Intrinsics.checkNotNullParameter(inputOption, "inputOption");
            this.inputOption = inputOption;
        }

        public final boolean shallShowErrorDialog() {
            if (StringsKt.equals("built-in", this.inputOption.getInputModuleType(), true)) {
                return !Intrinsics.areEqual("uni-getLocation-tencent-uni1", this.inputOption.getInputModuleName());
            }
            return false;
        }

        public final void showErrorDialog() {
            if (UTSAndroid.INSTANCE.getUniActivity() != null) {
                StringBuilder sb = new StringBuilder();
                Activity uniActivity = UTSAndroid.INSTANCE.getUniActivity();
                Intrinsics.checkNotNull(uniActivity);
                sb.append(uniActivity.getString(io.dcloud.base.R.string.dcloud_feature_error_tips2));
                sb.append("https://ask.dcloud.net.cn/article/283");
                ErrorDialogUtil.getLossDialog(UTSAndroid.INSTANCE.getUniActivity(), StringUtil.format(sb.toString(), this.inputOption.getInputModuleName()), "https://ask.dcloud.net.cn/article/283", this.inputOption.getInputModuleName()).show();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v27, types: [T, kotlin.reflect.KFunction] */
    /* JADX WARN: Type inference failed for: r0v37, types: [T, kotlin.reflect.KFunction] */
    /* JADX WARN: Type inference failed for: r0v9, types: [T, kotlin.reflect.KFunction] */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v3, types: [T, java.util.List] */
    @UniJSMethod(uiThread = false)
    public Object invokeAsync(JSONObject jSONObject, JSCallback jSCallback, Continuation<? super Unit> continuation) throws ClassNotFoundException {
        Class<?> cls;
        AndroidUTSContext androidUTSContext = AndroidUTSContext.INSTANCE;
        AbsSDKInstance mUniSDKInstance = this.mUniSDKInstance;
        Intrinsics.checkNotNullExpressionValue(mUniSDKInstance, "mUniSDKInstance");
        androidUTSContext.initContext(mUniSDKInstance);
        InputOption inputOption = new InputOption(jSONObject);
        try {
        } catch (Exception e) {
            AppConsoleLogUtil.DCLog("UTS: targetFunction invoke error - " + (e.getCause() != null ? String.valueOf(e.getCause()) : e.toString()), "ERROR");
        }
        if (!inputOption.isValid()) {
            AppConsoleLogUtil.DCLog("UTS: " + inputOption.getCheckErrorMsg(), "ERROR");
            return Unit.INSTANCE;
        }
        if (!inputOption.getIsInstanceAction()) {
            try {
                cls = Class.forName(inputOption.getModuleName());
            } catch (ClassNotFoundException unused) {
                cls = null;
            }
            if (cls == null) {
                ModuleChecker moduleChecker = new ModuleChecker(inputOption);
                if (moduleChecker.shallShowErrorDialog()) {
                    moduleChecker.showErrorDialog();
                }
                AppConsoleLogUtil.DCLog("error: " + inputOption.getInputModuleName() + " not found.", "ERROR");
                return Unit.INSTANCE;
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = findTargetFunc(cls, inputOption.getIsCompanion(), inputOption.getMethodName(), inputOption);
            FieldMethodDetector fieldMethodDetector = new FieldMethodDetector(cls, inputOption);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            if (objectRef.element == 0) {
                fieldMethodDetector.init();
                booleanRef.element = fieldMethodDetector.isFieldMethod();
                objectRef.element = fieldMethodDetector.getTargetFunction();
            }
            if (objectRef.element == 0) {
                AppConsoleLogUtil.DCLog("UTS: targetFunction not exists", "ERROR");
                return Unit.INSTANCE;
            }
            Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            booleanRef2.element = !Intrinsics.areEqual(((KFunction) objectRef.element).getReturnType(), Void.TYPE);
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = inputOption.obtainParamsWithDefault((KFunction<?>) objectRef.element, jSCallback, booleanRef.element);
            if (inputOption.getIsCompanion()) {
                ((List) objectRef2.element).add(0, KClasses.getCompanionObjectInstance(JvmClassMappingKt.getKotlinClass(cls)));
            }
            try {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), new ProxyModule$invokeAsync$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE), null, new AnonymousClass4(objectRef2, booleanRef2, booleanRef, this, objectRef, fieldMethodDetector, jSCallback, null), 2, null);
            } catch (Exception e2) {
                AppConsoleLogUtil.DCLog("UTS: targetFunction invoke error - " + (e2.getCause() != null ? String.valueOf(e2.getCause()) : e2.toString()), "ERROR");
            }
        } else {
            if (utsInstances.get(Boxing.boxInt(inputOption.getInstanceId())) == null) {
                AppConsoleLogUtil.DCLog("UTS: instance does not exists", "ERROR");
                return Unit.INSTANCE;
            }
            Object obj = utsInstances.get(Boxing.boxInt(inputOption.getInstanceId()));
            Intrinsics.checkNotNull(obj);
            Class<?> cls2 = obj.getClass();
            Object obj2 = utsInstances.get(Boxing.boxInt(inputOption.getInstanceId()));
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = findTargetFunc(cls2, false, inputOption.getMethodName(), inputOption);
            if (objectRef3.element == 0) {
                AppConsoleLogUtil.DCLog("UTS: targetFunction does not exists", "ERROR");
                return Unit.INSTANCE;
            }
            Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
            booleanRef3.element = !Intrinsics.areEqual(((KFunction) objectRef3.element).getReturnType(), Void.TYPE);
            Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
            objectRef4.element = InputOption.obtainParamsWithDefault$default(inputOption, (KFunction) objectRef3.element, jSCallback, false, 4, (Object) null);
            ((List) objectRef4.element).add(0, obj2);
            try {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass2(objectRef4, booleanRef3, this, objectRef3, jSCallback, null), 3, null);
            } catch (Exception e3) {
                AppConsoleLogUtil.DCLog("UTS: targetFunction invoke error - " + (e3.getCause() != null ? String.valueOf(e3.getCause()) : e3.toString()), "ERROR");
            }
        }
        return Unit.INSTANCE;
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule$invokeAsync$2", f = "UTSProxyModule.kt", i = {1}, l = {1171, 1175, 1187}, m = "invokeSuspend", n = {"ret"}, s = {"L$0"})
    /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$invokeAsync$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ JSCallback $callback;
        final /* synthetic */ Ref.BooleanRef $needReturn;
        final /* synthetic */ Ref.ObjectRef<List<Object>> $paramList;
        final /* synthetic */ Ref.ObjectRef<KFunction<?>> $targetFunction;
        Object L$0;
        int label;
        final /* synthetic */ ProxyModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.ObjectRef<List<Object>> objectRef, Ref.BooleanRef booleanRef, ProxyModule proxyModule, Ref.ObjectRef<KFunction<?>> objectRef2, JSCallback jSCallback, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$paramList = objectRef;
            this.$needReturn = booleanRef;
            this.this$0 = proxyModule;
            this.$targetFunction = objectRef2;
            this.$callback = jSCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$paramList, this.$needReturn, this.this$0, this.$targetFunction, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0075 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0076  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x007b  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0085  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L2a
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r7)
                goto Lad
            L16:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1e:
                java.lang.Object r0 = r6.L$0
                io.dcloud.feature.utsplugin.ReturnResult r0 = (io.dcloud.feature.utsplugin.ReturnResult) r0
                kotlin.ResultKt.throwOnFailure(r7)
                goto L77
            L26:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L5c
            L2a:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlin.jvm.internal.Ref$ObjectRef<java.util.List<java.lang.Object>> r7 = r6.$paramList
                T r7 = r7.element
                java.util.Collection r7 = (java.util.Collection) r7
                r1 = 0
                java.lang.Object[] r1 = new java.lang.Object[r1]
                java.lang.Object[] r7 = r7.toArray(r1)
                kotlin.jvm.internal.Ref$BooleanRef r1 = r6.$needReturn
                boolean r1 = r1.element
                if (r1 == 0) goto L91
                io.dcloud.feature.utsplugin.ProxyModule r1 = r6.this$0
                kotlin.jvm.internal.Ref$ObjectRef<kotlin.reflect.KFunction<?>> r2 = r6.$targetFunction
                T r2 = r2.element
                kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                kotlin.reflect.KCallable r2 = (kotlin.reflect.KCallable) r2
                int r5 = r7.length
                java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r5)
                r5 = r6
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r6.label = r4
                java.lang.Object r7 = r1.callSuspend(r2, r7, r5)
                if (r7 != r0) goto L5c
                return r0
            L5c:
                java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.Deferred<kotlin.Any?>"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r7, r1)
                kotlinx.coroutines.Deferred r7 = (kotlinx.coroutines.Deferred) r7
                io.dcloud.feature.utsplugin.ReturnResult r1 = new io.dcloud.feature.utsplugin.ReturnResult
                r1.<init>()
                r2 = r6
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r6.L$0 = r1
                r6.label = r3
                java.lang.Object r7 = r7.await(r2)
                if (r7 != r0) goto L76
                return r0
            L76:
                r0 = r1
            L77:
                boolean r1 = r7 instanceof io.dcloud.uts.UTSJSONObject
                if (r1 == 0) goto L85
                io.dcloud.uts.UTSJSONObject r7 = (io.dcloud.uts.UTSJSONObject) r7
                com.alibaba.fastjson.JSON r7 = r7.toJSONObject()
                r0.updateJSON(r7)
                goto L88
            L85:
                r0.updateJSON(r7)
            L88:
                com.taobao.weex.bridge.JSCallback r7 = r6.$callback
                kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
                r7.invoke(r0)
                goto Lc7
            L91:
                io.dcloud.feature.utsplugin.ProxyModule r1 = r6.this$0
                kotlin.jvm.internal.Ref$ObjectRef<kotlin.reflect.KFunction<?>> r3 = r6.$targetFunction
                T r3 = r3.element
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                kotlin.reflect.KCallable r3 = (kotlin.reflect.KCallable) r3
                int r4 = r7.length
                java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r4)
                r4 = r6
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r6.label = r2
                java.lang.Object r7 = r1.callSuspend(r3, r7, r4)
                if (r7 != r0) goto Lad
                return r0
            Lad:
                com.alibaba.fastjson.JSONObject r7 = new com.alibaba.fastjson.JSONObject
                r7.<init>()
                r0 = r7
                java.util.Map r0 = (java.util.Map) r0
                com.alibaba.fastjson.JSONArray r1 = new com.alibaba.fastjson.JSONArray
                r1.<init>()
                java.lang.String r2 = "params"
                r0.put(r2, r1)
                com.taobao.weex.bridge.JSCallback r0 = r6.$callback
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                r0.invoke(r7)
            Lc7:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.utsplugin.ProxyModule.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: UTSProxyModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.feature.utsplugin.ProxyModule$invokeAsync$4", f = "UTSProxyModule.kt", i = {}, l = {1277, 1282, TestUtil.PointTime.AC_TYPE_1_3}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.dcloud.feature.utsplugin.ProxyModule$invokeAsync$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ JSCallback $callback;
        final /* synthetic */ FieldMethodDetector $fieldMethodDetector;
        final /* synthetic */ Ref.BooleanRef $isFieldMethod;
        final /* synthetic */ Ref.BooleanRef $needReturn;
        final /* synthetic */ Ref.ObjectRef<List<Object>> $paramList;
        final /* synthetic */ Ref.ObjectRef<KFunction<?>> $targetFunction;
        int label;
        final /* synthetic */ ProxyModule this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.ObjectRef<List<Object>> objectRef, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, ProxyModule proxyModule, Ref.ObjectRef<KFunction<?>> objectRef2, FieldMethodDetector fieldMethodDetector, JSCallback jSCallback, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$paramList = objectRef;
            this.$needReturn = booleanRef;
            this.$isFieldMethod = booleanRef2;
            this.this$0 = proxyModule;
            this.$targetFunction = objectRef2;
            this.$fieldMethodDetector = fieldMethodDetector;
            this.$callback = jSCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(this.$paramList, this.$needReturn, this.$isFieldMethod, this.this$0, this.$targetFunction, this.$fieldMethodDetector, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x008b A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
            /*
                Method dump skipped, instructions count: 250
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.utsplugin.ProxyModule.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // io.dcloud.common.DHInterface.message.IObserveAble
    public EnumUniqueID getActionObserverID() {
        return EnumUniqueID.FEATURE_UTS;
    }
}
