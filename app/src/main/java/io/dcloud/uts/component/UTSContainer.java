package io.dcloud.uts.component;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.uniapp.ui.action.AbsComponentData;
import io.dcloud.feature.uniapp.ui.component.AbsVContainer;
import io.dcloud.feature.uniapp.ui.component.UniVContainer;
import io.dcloud.uts.component.IUTSComWrap;
import io.dcloud.uts.console;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

/* compiled from: UTSContainer.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\b\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005B-\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0018\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020#2\b\u0010;\u001a\u0004\u0018\u00010$J\u0010\u0010<\u001a\u0002012\u0006\u0010=\u001a\u00020#H\u0016J\u0018\u0010<\u001a\u0002012\u0006\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u00020$H\u0016J\b\u0010?\u001a\u000201H\u0016JV\u0010@\u001a\u000201\"\u0004\b\u0001\u0010\u00012\u0006\u0010:\u001a\u00020#2@\u0010A\u001a<\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\bB\u0012\b\b:\u0012\u0004\b\b(C\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\bB\u0012\b\b:\u0012\u0004\b\b(D\u0012\u0004\u0012\u00020100j\b\u0012\u0004\u0012\u0002H\u0001`2J,\u0010@\u001a\u000201\"\u0004\b\u0001\u0010\u00012\u0006\u0010:\u001a\u00020#2\u0016\u0010A\u001a\u0012\u0012\u0004\u0012\u00020104j\b\u0012\u0004\u0012\u0002H\u0001`5JA\u0010@\u001a\u000201\"\u0004\b\u0001\u0010\u00012\u0006\u0010:\u001a\u00020#2+\u0010A\u001a'\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\bB\u0012\b\b:\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020107j\b\u0012\u0004\u0012\u0002H\u0001`8J\b\u0010E\u001a\u000201H\u0016J\b\u0010F\u001a\u000201H\u0016J\b\u0010G\u001a\u000201H\u0016J\r\u0010H\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0015J\b\u0010I\u001a\u000201H\u0016J\u0010\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020KH\u0017J\u0018\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020K2\u0006\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u000201H\u0016J\u001c\u0010P\u001a\u0002012\u0012\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0RH\u0016J\u001a\u0010S\u001a\u0002012\b\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010V\u001a\u00020WH\u0016J\b\u0010X\u001a\u000201H\u0016J\b\u0010Y\u001a\u000201H\u0016J\b\u0010Z\u001a\u000201H\u0016J\u0015\u0010[\u001a\u00028\u00002\u0006\u0010\\\u001a\u00020\u000eH\u0014¢\u0006\u0002\u0010]J\b\u0010^\u001a\u000201H\u0014J\u0015\u0010_\u001a\u0002012\u0006\u0010`\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0017J\u001c\u0010a\u001a\u00020b2\b\u0010c\u001a\u0004\u0018\u00010#2\b\u0010d\u001a\u0004\u0018\u00010$H\u0014J\b\u0010e\u001a\u000201H\u0016J\u001e\u0010f\u001a\u0002012\u0014\u0010g\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0018\u00010\"H\u0016J\u001e\u0010h\u001a\u0002012\u0014\u0010Q\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0018\u00010\"H\u0016J\u0006\u0010i\u001a\u000201R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR(\u0010!\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0006\u0012\u0004\u0018\u00010$0\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R6\u0010.\u001a*\u0012\u0004\u0012\u00020#\u0012 \u0012\u001e\u0012\u001a\u0012\u0018\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020100j\u0006\u0012\u0002\b\u0003`20/0\"X\u0082\u000e¢\u0006\u0002\n\u0000R.\u00103\u001a\"\u0012\u0004\u0012\u00020#\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020104j\u0006\u0012\u0002\b\u0003`50/0\"X\u0082\u000e¢\u0006\u0002\n\u0000R2\u00106\u001a&\u0012\u0004\u0012\u00020#\u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020107j\u0006\u0012\u0002\b\u0003`80/0\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lio/dcloud/uts/component/UTSContainer;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/ViewGroup;", "Lio/dcloud/feature/uniapp/ui/component/UniVContainer;", "Lio/dcloud/uts/component/IUTSComWrap;", "Lio/dcloud/uts/component/IMeasureAble;", "instance", "Lio/dcloud/feature/uniapp/UniSDKInstance;", "parent", "Lio/dcloud/feature/uniapp/ui/component/AbsVContainer;", "componentData", "Lio/dcloud/feature/uniapp/ui/action/AbsComponentData;", "(Lio/dcloud/feature/uniapp/UniSDKInstance;Lio/dcloud/feature/uniapp/ui/component/AbsVContainer;Lio/dcloud/feature/uniapp/ui/action/AbsComponentData;)V", "$androidContext", "Landroid/content/Context;", "get$androidContext", "()Landroid/content/Context;", "set$androidContext", "(Landroid/content/Context;)V", "$el", "get$el", "()Landroid/view/ViewGroup;", "set$el", "(Landroid/view/ViewGroup;)V", "Landroid/view/ViewGroup;", "allProperty", "", "Ljava/lang/reflect/Field;", "getAllProperty", "()[Ljava/lang/reflect/Field;", "setAllProperty", "([Ljava/lang/reflect/Field;)V", "[Ljava/lang/reflect/Field;", "componentPropertyMap", "", "", "", "getComponentPropertyMap", "()Ljava/util/Map;", "setComponentPropertyMap", "(Ljava/util/Map;)V", "refLocal", "getRefLocal", "()Ljava/lang/String;", "setRefLocal", "(Ljava/lang/String;)V", "watchList", "", "Lkotlin/Function2;", "", "Lio/dcloud/uts/component/Watcher;", "watchNoParamList", "Lkotlin/Function0;", "Lio/dcloud/uts/component/WatcherNoParam;", "watchOneParamList", "Lkotlin/Function1;", "Lio/dcloud/uts/component/WatcherOneParam;", "$componentWatchDispatch", "name", "value", "$emit", "event", "userInput", "$init", "$watch", "watcher", "Lkotlin/ParameterName;", "newValue", "oldValue", "NVBeforeLoad", "NVBeforeUnload", "NVLayouted", "NVLoad", "NVLoaded", "NVMeasure", "Lio/dcloud/uts/component/UTSSize;", AbsoluteConst.JSON_KEY_SIZE, "mode", "Lio/dcloud/uts/component/UTSMeasureMode;", "NVUnloaded", "NVUpdateStyles", "styles", "Lio/dcloud/uts/Map;", "addSubView", "child", "Landroid/view/View;", "index", "", "createViewImpl", "created", Constants.Event.SLOT_LIFECYCLE.DESTORY, "initComponentHostView", "context", "(Landroid/content/Context;)Landroid/view/ViewGroup;", "onFinishLayout", "onHostViewInitialized", "host", "setProperty", "", IApp.ConfigProperty.CONFIG_KEY, "param", "unmounted", "updateProperties", BindingXConstants.KEY_RUNTIME_PROPS, "updateStyles", "utsForceUpdate", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class UTSContainer<T extends ViewGroup> extends UniVContainer<T> implements IUTSComWrap<T>, IMeasureAble {
    private Context $androidContext;
    private T $el;
    private Field[] allProperty;
    private Map<String, Object> componentPropertyMap;
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

    public UTSContainer(UniSDKInstance uniSDKInstance, AbsVContainer<?> absVContainer, AbsComponentData<?> absComponentData) {
        super(uniSDKInstance, absVContainer, absComponentData);
        this.refLocal = "";
        this.$androidContext = getContext();
        this.allProperty = new Field[0];
        this.watchList = new LinkedHashMap();
        this.watchOneParamList = new LinkedHashMap();
        this.watchNoParamList = new LinkedHashMap();
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
        setContentBoxMeasurement(new ContentBoxMeasurement(this) { // from class: io.dcloud.uts.component.UTSContainer.1
            final /* synthetic */ UTSContainer<T> this$0;

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
                UTSSize uTSSizeNVMeasure = this.this$0.NVMeasure(new UTSSize(width, height));
                this.mMeasureWidth = uTSSizeNVMeasure.getWidth();
                this.mMeasureHeight = uTSSizeNVMeasure.getHeight();
            }
        });
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public void addSubView(View child, int index) {
        super.addSubView(child, index);
        if (child != null) {
            child.bringToFront();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.taobao.weex.ui.component.WXComponent
    public T initComponentHostView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return (T) NVLoad();
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
            throw new IllegalStateException("initComponentView error");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.taobao.weex.ui.component.WXComponent
    public void onHostViewInitialized(T host) {
        Intrinsics.checkNotNullParameter(host, "host");
        this.$el = getHostView();
        try {
            NVLoaded();
        } catch (Exception e) {
            console.INSTANCE.errorV1(e);
            e.printStackTrace();
        }
        super.onHostViewInitialized((UTSContainer<T>) host);
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

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent
    public void destroy() {
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

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent
    public void createViewImpl() {
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

    @Override // com.taobao.weex.ui.component.WXComponent
    public void updateProperties(Map<String, Object> props) {
        super.updateProperties(props);
    }

    public final void utsForceUpdate() {
        applyLayoutOnly();
    }

    public final <T> void $watch(String name, Function2<? super T, ? super T, Unit> watcher) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(watcher, "watcher");
        ArrayList arrayList = this.watchList.get(name);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.watchList.put(name, arrayList);
        }
        arrayList.add(watcher);
    }

    public final <T> void $watch(String name, Function1<? super T, Unit> watcher) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(watcher, "watcher");
        ArrayList arrayList = this.watchOneParamList.get(name);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.watchOneParamList.put(name, arrayList);
        }
        arrayList.add(watcher);
    }

    public final <T> void $watch(String name, Function0<Unit> watcher) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(watcher, "watcher");
        ArrayList arrayList = this.watchNoParamList.get(name);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.watchNoParamList.put(name, arrayList);
        }
        arrayList.add(watcher);
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
