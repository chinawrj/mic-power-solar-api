package io.dcloud.uts.component;

import android.content.Context;
import android.view.View;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.uniapp.ui.action.AbsComponentData;
import io.dcloud.feature.uniapp.ui.component.AbsVContainer;
import io.dcloud.feature.uniapp.ui.component.UniComponent;
import io.dcloud.uts.component.IUTSComWrap;
import io.dcloud.uts.console;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;

/* compiled from: UTSComponent.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u000f\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005B-\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010;\u001a\u0002032\u0006\u0010<\u001a\u00020#2\b\u0010=\u001a\u0004\u0018\u00010$J\u0010\u0010>\u001a\u0002032\u0006\u0010?\u001a\u00020#H\u0016J\u0018\u0010>\u001a\u0002032\u0006\u0010?\u001a\u00020#2\u0006\u0010@\u001a\u00020$H\u0016J\b\u0010A\u001a\u000203H\u0016J`\u0010B\u001a\u000203\"\u0004\b\u0001\u0010\u00012\u0006\u0010<\u001a\u00020#2@\u0010C\u001a<\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\bD\u0012\b\b<\u0012\u0004\b\b(E\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\bD\u0012\b\b<\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020302j\b\u0012\u0004\u0012\u0002H\u0001`42\b\b\u0002\u0010G\u001a\u00020HJ6\u0010B\u001a\u000203\"\u0004\b\u0001\u0010\u00012\u0006\u0010<\u001a\u00020#2\u0016\u0010C\u001a\u0012\u0012\u0004\u0012\u00020306j\b\u0012\u0004\u0012\u0002H\u0001`72\b\b\u0002\u0010G\u001a\u00020HJK\u0010B\u001a\u000203\"\u0004\b\u0001\u0010\u00012\u0006\u0010<\u001a\u00020#2+\u0010C\u001a'\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\bD\u0012\b\b<\u0012\u0004\b\b(E\u0012\u0004\u0012\u00020309j\b\u0012\u0004\u0012\u0002H\u0001`:2\b\b\u0002\u0010G\u001a\u00020HJ\b\u0010I\u001a\u000203H\u0016J\b\u0010J\u001a\u000203H\u0016J\b\u0010K\u001a\u000203H\u0016J\r\u0010L\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0015J\b\u0010M\u001a\u000203H\u0016J\u0010\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020OH\u0017J\u0018\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020O2\u0006\u0010Q\u001a\u00020RH\u0016J\b\u0010S\u001a\u000203H\u0016J\u001c\u0010T\u001a\u0002032\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0VH\u0016J\b\u0010W\u001a\u000203H\u0014J\b\u0010X\u001a\u000203H\u0016J\b\u0010Y\u001a\u000203H\u0016J\u0014\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020#0[H\u0016J\u0015\u0010\\\u001a\u00028\u00002\u0006\u0010]\u001a\u00020\u000eH\u0014¢\u0006\u0002\u0010^J\b\u0010_\u001a\u000203H\u0014J\u0015\u0010`\u001a\u0002032\u0006\u0010a\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0017J\u001c\u0010b\u001a\u00020H2\b\u0010c\u001a\u0004\u0018\u00010#2\b\u0010d\u001a\u0004\u0018\u00010$H\u0014J\b\u0010e\u001a\u000203H\u0016J\u001e\u0010f\u001a\u0002032\u0014\u0010U\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0018\u00010\"H\u0016J\u001c\u0010g\u001a\u0002032\u0012\u0010h\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\"H\u0016J\u0006\u0010i\u001a\u000203R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR(\u0010!\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0006\u0012\u0004\u0018\u00010$0\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020#0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R6\u00100\u001a*\u0012\u0004\u0012\u00020#\u0012 \u0012\u001e\u0012\u001a\u0012\u0018\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020302j\u0006\u0012\u0002\b\u0003`4010\"X\u0082\u000e¢\u0006\u0002\n\u0000R.\u00105\u001a\"\u0012\u0004\u0012\u00020#\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020306j\u0006\u0012\u0002\b\u0003`7010\"X\u0082\u000e¢\u0006\u0002\n\u0000R2\u00108\u001a&\u0012\u0004\u0012\u00020#\u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020309j\u0006\u0012\u0002\b\u0003`:010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lio/dcloud/uts/component/UTSComponent;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "Lio/dcloud/feature/uniapp/ui/component/UniComponent;", "Lio/dcloud/uts/component/IUTSComWrap;", "Lio/dcloud/uts/component/IMeasureAble;", "instance", "Lio/dcloud/feature/uniapp/UniSDKInstance;", "parent", "Lio/dcloud/feature/uniapp/ui/component/AbsVContainer;", "componentData", "Lio/dcloud/feature/uniapp/ui/action/AbsComponentData;", "(Lio/dcloud/feature/uniapp/UniSDKInstance;Lio/dcloud/feature/uniapp/ui/component/AbsVContainer;Lio/dcloud/feature/uniapp/ui/action/AbsComponentData;)V", "$androidContext", "Landroid/content/Context;", "get$androidContext", "()Landroid/content/Context;", "set$androidContext", "(Landroid/content/Context;)V", "$el", "get$el", "()Landroid/view/View;", "set$el", "(Landroid/view/View;)V", "Landroid/view/View;", "allProperty", "", "Ljava/lang/reflect/Field;", "getAllProperty", "()[Ljava/lang/reflect/Field;", "setAllProperty", "([Ljava/lang/reflect/Field;)V", "[Ljava/lang/reflect/Field;", "componentPropertyMap", "", "", "", "getComponentPropertyMap", "()Ljava/util/Map;", "setComponentPropertyMap", "(Ljava/util/Map;)V", "immediateWatchKeys", "", "refLocal", "getRefLocal", "()Ljava/lang/String;", "setRefLocal", "(Ljava/lang/String;)V", "watchList", "", "Lkotlin/Function2;", "", "Lio/dcloud/uts/component/Watcher;", "watchNoParamList", "Lkotlin/Function0;", "Lio/dcloud/uts/component/WatcherNoParam;", "watchOneParamList", "Lkotlin/Function1;", "Lio/dcloud/uts/component/WatcherOneParam;", "$componentWatchDispatch", "name", "value", "$emit", "event", "userInput", "$init", "$watch", "watcher", "Lkotlin/ParameterName;", "newValue", "oldValue", "immediate", "", "NVBeforeLoad", "NVBeforeUnload", "NVLayouted", "NVLoad", "NVLoaded", "NVMeasure", "Lio/dcloud/uts/component/UTSSize;", AbsoluteConst.JSON_KEY_SIZE, "mode", "Lio/dcloud/uts/component/UTSMeasureMode;", "NVUnloaded", "NVUpdateStyles", "styles", "Lio/dcloud/uts/Map;", "createViewImpl", "created", Constants.Event.SLOT_LIFECYCLE.DESTORY, "getUTSPropertyTypeMap", "", "initComponentHostView", "context", "(Landroid/content/Context;)Landroid/view/View;", "onFinishLayout", "onHostViewInitialized", "host", "setProperty", IApp.ConfigProperty.CONFIG_KEY, "param", "unmounted", "updateStyles", "updateUTSProperty", TemplateDom.KEY_ATTRS, "utsForceUpdate", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class UTSComponent<T extends View> extends UniComponent<T> implements IUTSComWrap<T>, IMeasureAble {
    private Context $androidContext;
    private T $el;
    private Field[] allProperty;
    private Map<String, Object> componentPropertyMap;
    private final Set<String> immediateWatchKeys;
    private String refLocal;
    private Map<String, List<Function2<?, ?, Unit>>> watchList;
    private Map<String, List<Function0<Unit>>> watchNoParamList;
    private Map<String, List<Function1<?, Unit>>> watchOneParamList;

    public void $init() {
    }

    public void NVBeforeLoad() {
    }

    public void NVBeforeUnload() {
    }

    public void NVLayouted() {
    }

    public abstract T NVLoad();

    public void NVLoaded() {
    }

    @Override // io.dcloud.uts.component.IMeasureAble
    @Deprecated(message = "use NVMeasure(size:UTSSize,mode:UTSMeasureMode) instead", replaceWith = @ReplaceWith(expression = "NVMeasure(size,mode)", imports = {}))
    public UTSSize NVMeasure(UTSSize size) {
        Intrinsics.checkNotNullParameter(size, "size");
        return size;
    }

    @Override // io.dcloud.uts.component.IMeasureAble
    public UTSSize NVMeasure(UTSSize size, UTSMeasureMode mode) {
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(mode, "mode");
        return size;
    }

    public void NVUnloaded() {
    }

    public void NVUpdateStyles(io.dcloud.uts.Map<String, Object> styles) {
        Intrinsics.checkNotNullParameter(styles, "styles");
    }

    public void created() {
    }

    public void unmounted() {
    }

    public void updateUTSProperty(Map<String, Object> attrs) {
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    @Override // io.dcloud.uts.component.IUTSComWrap
    public void wrapComponentHostView(View view, WXComponent<?> wXComponent) {
        IUTSComWrap.DefaultImpls.wrapComponentHostView(this, view, wXComponent);
    }

    public final String getRefLocal() {
        return this.refLocal;
    }

    public final void setRefLocal(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.refLocal = str;
    }

    public final T get$el() {
        return this.$el;
    }

    public final void set$el(T t) {
        this.$el = t;
    }

    public final Context get$androidContext() {
        return this.$androidContext;
    }

    public final void set$androidContext(Context context) {
        this.$androidContext = context;
    }

    public UTSComponent(UniSDKInstance uniSDKInstance, AbsVContainer<?> absVContainer, AbsComponentData<?> absComponentData) {
        super(uniSDKInstance, absVContainer, absComponentData);
        this.refLocal = "";
        this.$androidContext = getContext();
        this.allProperty = new Field[0];
        this.watchList = new LinkedHashMap();
        this.watchOneParamList = new LinkedHashMap();
        this.watchNoParamList = new LinkedHashMap();
        this.immediateWatchKeys = new LinkedHashSet();
        this.componentPropertyMap = new LinkedHashMap();
        try {
            created();
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
        }
        String ref = getRef();
        Intrinsics.checkNotNullExpressionValue(ref, "this.ref");
        this.refLocal = ref;
        this.$androidContext = getContext();
        setContentBoxMeasurement(new ContentBoxMeasurement(this) { // from class: io.dcloud.uts.component.UTSComponent.1
            final /* synthetic */ UTSComponent<T> this$0;

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutAfter(float computedWidth, float computedHeight) {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutBefore() {
            }

            {
                this.this$0 = this;
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void measureInternal(float width, float height, int widthMeasureMode, int heightMeasureMode) {
                UTSSize uTSSizeNVMeasure = this.this$0.NVMeasure(new UTSSize(width, height), new UTSMeasureMode(widthMeasureMode, heightMeasureMode));
                this.mMeasureWidth = uTSSizeNVMeasure.getWidth();
                this.mMeasureHeight = uTSSizeNVMeasure.getHeight();
            }
        });
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    protected T initComponentHostView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return (T) NVLoad();
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
            throw new IllegalStateException("initComponentView error");
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    protected void onHostViewInitialized(T host) {
        Intrinsics.checkNotNullParameter(host, "host");
        this.$el = getHostView();
        try {
            NVLoaded();
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
        }
        super.onHostViewInitialized(host);
        try {
            $init();
        } catch (Exception e2) {
            console.INSTANCE.errorV1(e2);
            e2.printStackTrace();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    protected void onFinishLayout() {
        super.onFinishLayout();
        try {
            NVLayouted();
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
        }
    }

    @Override // io.dcloud.feature.uniapp.ui.component.UniComponent, com.taobao.weex.ui.component.WXComponent
    public void destroy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            NVBeforeUnload();
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
        }
        super.destroy();
        try {
            NVUnloaded();
            unmounted();
        } catch (Exception e2) {
            console.INSTANCE.errorV1(e2);
            e2.printStackTrace();
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    protected void createViewImpl() {
        try {
            NVBeforeLoad();
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
        }
        super.createViewImpl();
    }

    public final Field[] getAllProperty() {
        return this.allProperty;
    }

    public final void setAllProperty(Field[] fieldArr) {
        Intrinsics.checkNotNullParameter(fieldArr, "<set-?>");
        this.allProperty = fieldArr;
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    protected boolean setProperty(String key, Object param) throws IllegalAccessException, IllegalArgumentException {
        Integer numValueOf;
        try {
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
        }
        if (super.setProperty(key, param)) {
            return true;
        }
        if (this.allProperty.length == 0) {
            Field[] declaredFields = getClass().getDeclaredFields();
            Intrinsics.checkNotNullExpressionValue(declaredFields, "this::class.java.declaredFields");
            this.allProperty = declaredFields;
        }
        Field field = null;
        for (Field field2 : this.allProperty) {
            if (Intrinsics.areEqual(key, field2.getName())) {
                field = field2;
            }
        }
        if (field == null) {
            return false;
        }
        if (StringsKt.equals("boolean", field.getType().getName(), true)) {
            boolean z = Boolean.parseBoolean(String.valueOf(param));
            field.setAccessible(true);
            field.set(this, Boolean.valueOf(z));
            Intrinsics.checkNotNull(key);
            $componentWatchDispatch(key, Boolean.valueOf(z));
        } else if (StringsKt.equals("java.lang.Number", field.getType().getName(), true)) {
            if (StringsKt.contains$default((CharSequence) String.valueOf(param), (CharSequence) Operators.DOT_STR, false, 2, (Object) null)) {
                numValueOf = Double.valueOf(Double.parseDouble(String.valueOf(param)));
            } else {
                numValueOf = Integer.valueOf(Integer.parseInt(String.valueOf(param)));
            }
            field.setAccessible(true);
            field.set(this, numValueOf);
            Intrinsics.checkNotNull(key);
            $componentWatchDispatch(key, numValueOf);
        } else {
            if (!StringsKt.equals("java.lang.String", field.getType().getName(), true)) {
                return false;
            }
            field.setAccessible(true);
            field.set(this, param);
            Intrinsics.checkNotNull(key);
            $componentWatchDispatch(key, param);
        }
        return false;
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void updateStyles(Map<String, Object> styles) {
        if (styles != null) {
            try {
                io.dcloud.uts.Map<String, Object> map = new io.dcloud.uts.Map<>();
                map.putAll(styles);
                NVUpdateStyles(map);
            } catch (Exception e) {
                console.INSTANCE.errorV1(e);
                e.printStackTrace();
            }
        }
        super.updateStyles(styles);
    }

    public final void utsForceUpdate() {
        applyLayoutOnly();
    }

    public Map<String, String> getUTSPropertyTypeMap() {
        return new LinkedHashMap();
    }

    public static /* synthetic */ void $watch$default(UTSComponent uTSComponent, String str, Function2 function2, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: $watch");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        uTSComponent.$watch(str, function2, z);
    }

    public final <T> void $watch(String name, Function2<? super T, ? super T, Unit> watcher, boolean immediate) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(watcher, "watcher");
        ArrayList arrayList = this.watchList.get(name);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.watchList.put(name, arrayList);
        }
        arrayList.add(watcher);
        if (immediate) {
            this.immediateWatchKeys.add(name);
        }
    }

    public static /* synthetic */ void $watch$default(UTSComponent uTSComponent, String str, Function1 function1, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: $watch");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        uTSComponent.$watch(str, function1, z);
    }

    public final <T> void $watch(String name, Function1<? super T, Unit> watcher, boolean immediate) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(watcher, "watcher");
        ArrayList arrayList = this.watchOneParamList.get(name);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.watchOneParamList.put(name, arrayList);
        }
        arrayList.add(watcher);
        if (immediate) {
            this.immediateWatchKeys.add(name);
        }
    }

    public static /* synthetic */ void $watch$default(UTSComponent uTSComponent, String str, Function0 function0, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: $watch");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        uTSComponent.$watch(str, (Function0<Unit>) function0, z);
    }

    public final <T> void $watch(String name, Function0<Unit> watcher, boolean immediate) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(watcher, "watcher");
        ArrayList arrayList = this.watchNoParamList.get(name);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.watchNoParamList.put(name, arrayList);
        }
        arrayList.add(watcher);
        if (immediate) {
            this.immediateWatchKeys.add(name);
        }
    }

    public final Map<String, Object> getComponentPropertyMap() {
        return this.componentPropertyMap;
    }

    public final void setComponentPropertyMap(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.componentPropertyMap = map;
    }

    public final void $componentWatchDispatch(String name, Object value) {
        List<Function0<Unit>> list;
        List<Function1<?, Unit>> list2;
        List<Function2<?, ?, Unit>> list3;
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.componentPropertyMap.get(name);
        if (obj == null) {
            obj = value;
        }
        if (this.watchList.containsKey(name) && (list3 = this.watchList.get(name)) != null) {
            Iterator<T> it = list3.iterator();
            while (it.hasNext()) {
                Function2 function2 = (Function2) it.next();
                Intrinsics.checkNotNull(function2, "null cannot be cast to non-null type kotlin.Function2<@[ParameterName(name = 'newValue')] kotlin.Any?, @[ParameterName(name = 'oldValue')] kotlin.Any?, kotlin.Unit>{ io.dcloud.uts.component.UTSComponentKt.Watcher<kotlin.Any?> }");
                ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(value, obj);
            }
        }
        if (this.watchOneParamList.containsKey(name) && (list2 = this.watchOneParamList.get(name)) != null) {
            Iterator<T> it2 = list2.iterator();
            while (it2.hasNext()) {
                Function1 function1 = (Function1) it2.next();
                Intrinsics.checkNotNull(function1, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'newValue')] kotlin.Any?, kotlin.Unit>{ io.dcloud.uts.component.UTSComponentKt.WatcherOneParam<kotlin.Any?> }");
                ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(value);
            }
        }
        if (this.watchNoParamList.containsKey(name) && (list = this.watchNoParamList.get(name)) != null) {
            Iterator<T> it3 = list.iterator();
            while (it3.hasNext()) {
                Function0 function0 = (Function0) it3.next();
                Intrinsics.checkNotNull(function0, "null cannot be cast to non-null type kotlin.Function0<kotlin.Unit>{ io.dcloud.uts.component.UTSComponentKt.WatcherNoParam<kotlin.Any?> }");
                ((Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function0, 0)).invoke();
            }
        }
        this.componentPropertyMap.put(name, value);
    }

    public void $emit(String event) {
        Intrinsics.checkNotNullParameter(event, "event");
        fireEvent(event);
    }

    public void $emit(String event, Object userInput) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(userInput, "userInput");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("detail", userInput);
        fireEvent(event, MapsKt.toMap(linkedHashMap));
    }
}
