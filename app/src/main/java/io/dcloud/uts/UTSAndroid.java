package io.dcloud.uts;

import android.R;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.content.FileProvider;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.facebook.common.util.UriUtil;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.module.WXDomModule;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.EntryProxy;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.message.action.PermissionRequestAction;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.ui.webview.WebViewFactory;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.core.ui.TabBarWebview;
import io.dcloud.common.core.ui.TabBarWebviewMgr;
import io.dcloud.common.ui.PrivacyManager;
import io.dcloud.common.ui.blur.DCBlurDraweeView;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.uniapp.AbsSDKInstance;
import io.dcloud.feature.weex.WeexInstanceMgr;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.android.AndroidUTSContext;
import io.dcloud.uts.gson.reflect.TypeToken;
import io.dcloud.uts.task.IOTaskDispatcher;
import io.dcloud.uts.task.MainTaskDispatcher;
import io.dcloud.uts.task.UTSTaskDispatcher;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: UTSAndroid.kt */
@Metadata(d1 = {"\u0000\u008c\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012J!\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\u0006\u0010\u0015\u001a\u0002H\u00142\u0006\u0010\u0016\u001a\u00020\u0005¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bJ?\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u00052#\u0010\u001f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\n0\b2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0007J\u0006\u0010#\u001a\u00020\nJ\b\u0010$\u001a\u0004\u0018\u00010\u0005J\b\u0010%\u001a\u0004\u0018\u00010&J\u0006\u0010'\u001a\u00020\rJ\u0006\u0010(\u001a\u00020\u0005J\u0006\u0010)\u001a\u00020\u0005J\n\u0010*\u001a\u0004\u0018\u00010\u0005H\u0007J\u0006\u0010+\u001a\u00020\u0005J\u0006\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020&J\u0012\u0010/\u001a\u0002002\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0005J\u0006\u00101\u001a\u000202J\u0010\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u000206J\u0011\u00107\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0014\u0018\u0001H\u0086\bJ\u0011\u00108\u001a\u000209\"\u0006\b\u0000\u0010\u0014\u0018\u0001H\u0086\bJ\u0006\u0010:\u001a\u00020\u0005J\n\u0010;\u001a\u0004\u0018\u00010<H\u0002J\u0012\u0010=\u001a\u0006\u0012\u0002\b\u00030>2\u0006\u0010?\u001a\u00020\u0001J\u0012\u0010@\u001a\u0006\u0012\u0002\b\u00030A2\u0006\u0010?\u001a\u00020\u0001J\u000e\u0010B\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020&J\u0006\u0010C\u001a\u00020\u0005J\u0006\u0010D\u001a\u00020\u0005J\u000e\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005J\u0006\u0010G\u001a\u00020-J\b\u0010H\u001a\u00020IH\u0002J\u0006\u0010J\u001a\u00020-J\u0006\u0010K\u001a\u00020LJ\"\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012J\b\u0010N\u001a\u0004\u0018\u00010\u0010J\b\u0010O\u001a\u0004\u0018\u00010PJ\b\u0010Q\u001a\u0004\u0018\u00010\u0010J\u0006\u0010R\u001a\u00020\u0005J\u0006\u0010S\u001a\u00020\u0005J\u000e\u0010T\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020&J\u0006\u0010U\u001a\u00020LJ\u0006\u0010V\u001a\u00020LJ\u001c\u0010W\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012J\u0010\u0010X\u001a\u00020\n2\u0006\u0010Y\u001a\u00020ZH\u0002J\u0006\u0010[\u001a\u00020\rJ\u0006\u0010\\\u001a\u00020\rJ\u0006\u0010]\u001a\u00020\rJ\u0006\u0010^\u001a\u00020\rJ\u0006\u0010_\u001a\u00020\rJ\u0006\u0010`\u001a\u00020\rJ\u0018\u0010a\u001a\u00020\n2\u0010\b\u0002\u0010b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010cJ\u0018\u0010d\u001a\u00020\n2\u0010\b\u0002\u0010b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010cJ\u0018\u0010e\u001a\u00020\n2\u0010\b\u0002\u0010b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010cJe\u0010f\u001a\u00020\n2[\b\u0002\u0010b\u001aU\u0012\u0013\u0012\u00110L¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(h\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(i\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001b0\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(j\u0012\u0004\u0012\u00020\n\u0018\u00010gH\u0007JY\u0010k\u001a\u00020\n2Q\b\u0002\u0010b\u001aK\u0012\u0013\u0012\u00110L¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(h\u0012\u0013\u0012\u00110L¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(l\u0012\u0015\u0012\u0013\u0018\u00010m¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(n\u0012\u0004\u0012\u00020\n\u0018\u00010gJ\u0018\u0010o\u001a\u00020\n2\u0010\b\u0002\u0010b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010cJ\u0014\u0010p\u001a\u00020\n2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0cJ\u001e\u0010q\u001a\u00020\n2\u0016\b\u0002\u0010b\u001a\u0010\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\n\u0018\u00010\bJ\u001e\u0010r\u001a\u00020\n2\u0016\b\u0002\u0010b\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\n\u0018\u00010\bJ/\u0010s\u001a\u00020\n2'\u0010t\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(u\u0012\u0004\u0012\u00020\n0\bJ/\u0010v\u001a\u00020\n2'\u0010t\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(u\u0012\u0004\u0012\u00020\n0\bJ/\u0010w\u001a\u00020\n2'\u0010t\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(u\u0012\u0004\u0012\u00020\n0\bJ\u001a\u0010x\u001a\u00020\n2\u0012\u0010b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ\u0014\u0010y\u001a\u00020\n2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0cJ\u0014\u0010z\u001a\u00020\n2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0cJ\u0014\u0010{\u001a\u00020\n2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0cJa\u0010|\u001a\u00020\n2W\u0010b\u001aS\u0012\u0013\u0012\u00110L¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(h\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(i\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001b0\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(j\u0012\u0004\u0012\u00020\n0gH\u0007JU\u0010}\u001a\u00020\n2M\u0010b\u001aI\u0012\u0013\u0012\u00110L¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(h\u0012\u0013\u0012\u00110L¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(l\u0012\u0015\u0012\u0013\u0018\u00010m¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(n\u0012\u0004\u0012\u00020\n0gJ\u0014\u0010~\u001a\u00020\n2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0cJ\u0014\u0010\u007f\u001a\u00020\n2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0cJ\u001b\u0010\u0080\u0001\u001a\u00020\n2\u0012\u0010b\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\n0\bJ\u001b\u0010\u0081\u0001\u001a\u00020\n2\u0012\u0010b\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\n0\bJ0\u0010\u0082\u0001\u001a\u00020\n2'\u0010t\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(u\u0012\u0004\u0012\u00020\n0\bJ0\u0010\u0083\u0001\u001a\u00020\n2'\u0010t\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(u\u0012\u0004\u0012\u00020\n0\bJ0\u0010\u0084\u0001\u001a\u00020\n2'\u0010t\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(u\u0012\u0004\u0012\u00020\n0\bJ\u001b\u0010\u0085\u0001\u001a\u00020\n2\u0012\u0010b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ%\u0010\u0086\u0001\u001a\u0002H\u0014\"\n\b\u0000\u0010\u0014\u0018\u0001*\u00020-2\u0007\u0010\u0087\u0001\u001a\u00020-H\u0086\b¢\u0006\u0003\u0010\u0088\u0001J\t\u0010\u0089\u0001\u001a\u00020\nH\u0007J¬\u0001\u0010\u008a\u0001\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122@\u0010\u008b\u0001\u001a;\u0012\u0014\u0012\u00120\r¢\u0006\r\b \u0012\t\b!\u0012\u0005\b\b(\u008d\u0001\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\r\b \u0012\t\b!\u0012\u0005\b\b(\u008e\u0001\u0012\u0004\u0012\u00020\n0\u008c\u00012@\u0010\u008f\u0001\u001a;\u0012\u0014\u0012\u00120\r¢\u0006\r\b \u0012\t\b!\u0012\u0005\b\b(\u0090\u0001\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\r\b \u0012\t\b!\u0012\u0005\b\b(\u008e\u0001\u0012\u0004\u0012\u00020\n0\u008c\u00012\t\b\u0002\u0010\u0091\u0001\u001a\u00020\rJ\u0007\u0010\u0092\u0001\u001a\u00020\nJ\u0010\u0010\u0093\u0001\u001a\u00020\u001b2\u0007\u0010\u0094\u0001\u001a\u00020\u001bJ\u0010\u0010\u0095\u0001\u001a\u00020\n2\u0007\u0010\u0096\u0001\u001a\u00020\rJ\u0012\u0010\u0097\u0001\u001a\u00020\u00052\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u0001J\u0019\u0010\u0097\u0001\u001a\u00020\u00052\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0099\u0001¢\u0006\u0003\u0010\u009a\u0001J\u0019\u0010\u0097\u0001\u001a\u00020\u00052\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u009b\u0001¢\u0006\u0003\u0010\u009c\u0001J\u0018\u0010\u0097\u0001\u001a\u00020\u00052\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010I¢\u0006\u0003\u0010\u009d\u0001J\u0018\u0010\u0097\u0001\u001a\u00020\u00052\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010L¢\u0006\u0003\u0010\u009e\u0001J\u0019\u0010\u0097\u0001\u001a\u00020\u00052\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u009f\u0001¢\u0006\u0003\u0010 \u0001J\u0012\u0010\u0097\u0001\u001a\u00020\u00052\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u001bJ\u0019\u0010\u0097\u0001\u001a\u00020\u00052\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010¡\u0001¢\u0006\u0003\u0010¢\u0001J\u001f\u0010\u0097\u0001\u001a\u00020\u00052\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010£\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0003\b¤\u0001J\u001f\u0010\u0097\u0001\u001a\u00020\u00052\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010¥\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0003\b¦\u0001J\u001f\u0010\u0097\u0001\u001a\u00020\u00052\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010§\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0003\b¨\u0001J\u001f\u0010\u0097\u0001\u001a\u00020\u00052\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010©\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0003\bª\u0001R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0006\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006«\u0001"}, d2 = {"Lio/dcloud/uts/UTSAndroid;", "", "()V", "initedHookProxys", "", "", "privacyAgreeList", "Lkotlin/Pair;", "Lkotlin/Function1;", "Lio/dcloud/uts/PrivacyOption;", "", "appJSBundleUrl", "appRunAssetMode", "", "checkSystemPermissionGranted", "context", "Landroid/app/Activity;", "requestPermission", "Lio/dcloud/uts/UTSArray;", "consoleDebugError", ExifInterface.GPS_DIRECTION_TRUE, UriUtil.LOCAL_RESOURCE_SCHEME, "info", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "convert2AbsFullPath", "relativePath", "devicePX2px", "", "devicePX", "dispatchAsync", "threadName", "action", "Lkotlin/ParameterName;", "name", "param", BindingXConstants.STATE_EXIT, "getAppCachePath", "getAppContext", "Landroid/content/Context;", "getAppDarkMode", "getAppId", "getAppName", "getAppTempPath", "getAppTheme", "getAppVersion", "Lio/dcloud/uts/UTSJSONObject;", "getDeviceID", "getDispatcher", "Lio/dcloud/uts/task/UTSTaskDispatcher;", "getDomCoroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getFileProviderUri", "Landroid/net/Uri;", "file", "Ljava/io/File;", "getGenericClassName", "getGenericType", "Ljava/lang/reflect/Type;", "getInnerVersion", "getInstance", "Lio/dcloud/feature/uniapp/AbsSDKInstance;", "getJavaClass", "Ljava/lang/Class;", "input", "getKotlinClass", "Lkotlin/reflect/KClass;", "getLanguageInfo", "getOAID", "getOsTheme", "getResourcePath", "resourceName", "getSafeAreaInsets", "getScale", "", "getScreenInfo", "getStatusBarHeight", "", "getSystemPermissionDenied", "getTopPageActivity", "getTopPageView", "Landroid/view/View;", "getUniActivity", "getUniCompileVersion", "getUniRuntimeVersion", "getWebViewInfo", "getWindowHeight", "getWindowWidth", "gotoSystemPermissionActivity", "initUTSHooksClassArray", "application", "Landroid/app/Application;", "isPrivacyAgree", "isTabBarShow", "isTitleNViewShow", "isUIThread", "isUniAppX", "isUniMp", "offAppActivityBack", WXBridgeManager.METHOD_CALLBACK, "Lkotlin/Function0;", "offAppActivityDestroy", "offAppActivityPause", "offAppActivityRequestPermissionsResult", "Lkotlin/Function3;", WXModule.REQUEST_CODE, "permissions", WXModule.GRANT_RESULTS, "offAppActivityResult", WXModule.RESULT_CODE, "Landroid/content/Intent;", "data", "offAppActivityResume", "offAppActivityStop", "offAppConfigChange", "offAppTrimMemory", "offPermissionComplete", "listener", "permission", "offPermissionConfirm", "offPermissionRequest", "offPrivacyAgreeChange", "onAppActivityBack", "onAppActivityDestroy", "onAppActivityPause", "onAppActivityRequestPermissionsResult", "onAppActivityResult", "onAppActivityResume", "onAppActivityStop", "onAppConfigChange", "onAppTrimMemory", "onPermissionComplete", "onPermissionConfirm", "onPermissionRequest", "onPrivacyAgreeChange", "parseObject", "opts", "(Lio/dcloud/uts/UTSJSONObject;)Lio/dcloud/uts/UTSJSONObject;", "quitApp", "requestSystemPermission", WXImage.SUCCEED, "Lkotlin/Function2;", "allRight", "grantedList", Constants.Event.FAIL, "doNotAskAgain", "shallUnCheck", "resetPrivacyAgree", "rpx2px", "rpx", "setPrivacyAgree", "newVal", "typeof", "value", "", "(Ljava/lang/Byte;)Ljava/lang/String;", "", "(Ljava/lang/Double;)Ljava/lang/String;", "(Ljava/lang/Float;)Ljava/lang/String;", "(Ljava/lang/Integer;)Ljava/lang/String;", "", "(Ljava/lang/Long;)Ljava/lang/String;", "", "(Ljava/lang/Short;)Ljava/lang/String;", "Lkotlin/UByte;", "typeof-3swpYEE", "Lkotlin/UInt;", "typeof-ExVfyTY", "Lkotlin/ULong;", "typeof-ADd3fzo", "Lkotlin/UShort;", "typeof-ffyZV3s", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSAndroid {
    public static final UTSAndroid INSTANCE = new UTSAndroid();
    private static final List<String> initedHookProxys = new ArrayList();
    private static final List<Pair<String, Function1<PrivacyOption, Unit>>> privacyAgreeList = new ArrayList();

    public final boolean isUniAppX() {
        return false;
    }

    private UTSAndroid() {
    }

    public final <T> T consoleDebugError(T res, String info) {
        Intrinsics.checkNotNullParameter(info, "info");
        if (res == null && ObjectKt.getGlobalError().get(Thread.currentThread().getName()) != null) {
            console.error(ObjectKt.getGlobalError().get(Thread.currentThread().getName()), info);
            java.util.Map<String, Exception> globalError = ObjectKt.getGlobalError();
            String name = Thread.currentThread().getName();
            Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
            globalError.put(name, null);
        }
        return res;
    }

    public final /* synthetic */ <T extends UTSJSONObject> T parseObject(UTSJSONObject opts) {
        java.lang.Object next;
        Intrinsics.checkNotNullParameter(opts, "opts");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(UTSJSONObject.class);
        T t = (T) KClasses.createInstance(orCreateKotlinClass);
        Collection memberProperties = KClasses.getMemberProperties(Reflection.getOrCreateKotlinClass(opts.getClass()));
        for (KProperty1 kProperty1 : KClasses.getMemberProperties(orCreateKotlinClass)) {
            String name = kProperty1.getName();
            Iterator it = memberProperties.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((KProperty1) next).getName(), name)) {
                    break;
                }
            }
            KProperty1 kProperty12 = (KProperty1) next;
            if (kProperty12 != null) {
                if (Intrinsics.areEqual(kProperty1.getGetter().getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(UTSCallback.class))) {
                    if (kProperty1 instanceof KMutableProperty1) {
                        KMutableProperty1.Setter setter = ((KMutableProperty1) kProperty1).getSetter();
                        V vCall = kProperty12.getGetter().call(opts);
                        Intrinsics.checkNotNull(vCall);
                        setter.call(t, new UTSCallback(vCall));
                    }
                } else if (kProperty1 instanceof KMutableProperty1) {
                    ((KMutableProperty1) kProperty1).getSetter().call(t, kProperty12.getGetter().call(opts));
                }
            }
        }
        return t;
    }

    public final void onAppConfigChange(Function1<? super UTSJSONObject, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getOnConfigChangedListenFunc().add(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void offAppConfigChange$default(UTSAndroid uTSAndroid, Function1 function1, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        uTSAndroid.offAppConfigChange(function1);
    }

    public final void offAppConfigChange(Function1<? super UTSJSONObject, Unit> callback) {
        if (callback == null) {
            AndroidUTSContext.INSTANCE.getOnConfigChangedListenFunc().clear();
        } else {
            AndroidUTSContext.INSTANCE.getOnConfigChangedListenFunc().remove(callback);
        }
    }

    public final void onAppTrimMemory(Function1<? super Number, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getOnTrimMemoryListenFunc().add(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void offAppTrimMemory$default(UTSAndroid uTSAndroid, Function1 function1, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        uTSAndroid.offAppTrimMemory(function1);
    }

    public final void offAppTrimMemory(Function1<? super Number, Unit> callback) {
        if (callback == null) {
            AndroidUTSContext.INSTANCE.getOnTrimMemoryListenFunc().clear();
        } else {
            AndroidUTSContext.INSTANCE.getOnTrimMemoryListenFunc().remove(callback);
        }
    }

    public final void onAppActivityPause(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getPauseListenFunc().add(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void offAppActivityPause$default(UTSAndroid uTSAndroid, Function0 function0, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        uTSAndroid.offAppActivityPause(function0);
    }

    public final void offAppActivityPause(Function0<Unit> callback) {
        if (callback == null) {
            AndroidUTSContext.INSTANCE.getPauseListenFunc().clear();
        } else {
            AndroidUTSContext.INSTANCE.getPauseListenFunc().remove(callback);
        }
    }

    public final void onAppActivityResume(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getResumeListenFunc().add(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void offAppActivityResume$default(UTSAndroid uTSAndroid, Function0 function0, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        uTSAndroid.offAppActivityResume(function0);
    }

    public final void offAppActivityResume(Function0<Unit> callback) {
        if (callback == null) {
            AndroidUTSContext.INSTANCE.getResumeListenFunc().clear();
        } else {
            AndroidUTSContext.INSTANCE.getResumeListenFunc().remove(callback);
        }
    }

    public final void onAppActivityStop(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getStopListenFunc().add(callback);
    }

    public final void offAppActivityStop(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getStopListenFunc().remove(callback);
    }

    public final void onAppActivityDestroy(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getDestroyListenFunc().add(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void offAppActivityDestroy$default(UTSAndroid uTSAndroid, Function0 function0, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        uTSAndroid.offAppActivityDestroy(function0);
    }

    public final void offAppActivityDestroy(Function0<Unit> callback) {
        if (callback == null) {
            AndroidUTSContext.INSTANCE.getDestroyListenFunc().clear();
        } else {
            AndroidUTSContext.INSTANCE.getDestroyListenFunc().remove(callback);
        }
    }

    public final void onAppActivityResult(Function3<? super Integer, ? super Integer, ? super Intent, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getOnActivityResultListenFunc().add(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void offAppActivityResult$default(UTSAndroid uTSAndroid, Function3 function3, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function3 = null;
        }
        uTSAndroid.offAppActivityResult(function3);
    }

    public final void offAppActivityResult(Function3<? super Integer, ? super Integer, ? super Intent, Unit> callback) {
        if (callback == null) {
            AndroidUTSContext.INSTANCE.getOnActivityResultListenFunc().clear();
        } else {
            AndroidUTSContext.INSTANCE.getOnActivityResultListenFunc().remove(callback);
        }
    }

    public final void onAppActivityBack(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getBackListenFunc().add(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void offAppActivityBack$default(UTSAndroid uTSAndroid, Function0 function0, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        uTSAndroid.offAppActivityBack(function0);
    }

    public final void offAppActivityBack(Function0<Unit> callback) {
        if (callback == null) {
            AndroidUTSContext.INSTANCE.getBackListenFunc().clear();
        } else {
            AndroidUTSContext.INSTANCE.getBackListenFunc().remove(callback);
        }
    }

    @Deprecated(message = "已废弃，请使用requestSystemPermission", replaceWith = @ReplaceWith(expression = "requestSystemPermission", imports = {}))
    public final void onAppActivityRequestPermissionsResult(Function3<? super Integer, ? super UTSArray<String>, ? super UTSArray<Number>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AndroidUTSContext.INSTANCE.getPermissionsResultListenFunc().add(callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void offAppActivityRequestPermissionsResult$default(UTSAndroid uTSAndroid, Function3 function3, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function3 = null;
        }
        uTSAndroid.offAppActivityRequestPermissionsResult(function3);
    }

    @Deprecated(message = "已废弃，请使用requestSystemPermission", replaceWith = @ReplaceWith(expression = "requestSystemPermission", imports = {}))
    public final void offAppActivityRequestPermissionsResult(Function3<? super Integer, ? super UTSArray<String>, ? super UTSArray<Number>, Unit> callback) {
        if (callback == null) {
            AndroidUTSContext.INSTANCE.getPermissionsResultListenFunc().clear();
        } else {
            AndroidUTSContext.INSTANCE.getPermissionsResultListenFunc().remove(callback);
        }
    }

    public final Context getAppContext() {
        return AndroidUTSContext.INSTANCE.getHostAppContext();
    }

    public final Activity getUniActivity() {
        if (getInstance() == null) {
            return null;
        }
        WeexInstanceMgr weexInstanceMgrSelf = WeexInstanceMgr.self();
        AbsSDKInstance uTSAndroid = getInstance();
        Intrinsics.checkNotNull(uTSAndroid, "null cannot be cast to non-null type com.taobao.weex.WXSDKInstance");
        return weexInstanceMgrSelf.findWebview((WXSDKInstance) uTSAndroid).getActivity();
    }

    public final Activity getTopPageActivity() {
        return getUniActivity();
    }

    public final View getTopPageView() {
        ViewGroup viewGroup;
        Activity uniActivity = getUniActivity();
        if (uniActivity == null || (viewGroup = (ViewGroup) uniActivity.findViewById(R.id.content)) == null) {
            return null;
        }
        return viewGroup.getChildAt(0);
    }

    public final String getResourcePath(String resourceName) {
        Intrinsics.checkNotNullParameter(resourceName, "resourceName");
        if (StringsKt.startsWith$default(resourceName, "/", false, 2, (java.lang.Object) null)) {
            resourceName = StringKt.substring$default(resourceName, (Number) 1, null, 2, null);
        }
        boolean zIsAppResourcesInAssetsPath = AppRuntime.isAppResourcesInAssetsPath(getAppContext(), "");
        String str = BaseInfo.sCacheFsAppsPath;
        String str2 = BaseInfo.sDefaultBootApp + "/www/" + resourceName;
        String str3 = SDK.ANDROID_ASSET + str2;
        if (zIsAppResourcesInAssetsPath) {
            return str3;
        }
        return str + str2;
    }

    public final String convert2AbsFullPath(String relativePath) {
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        if (getInstance() == null) {
            return "";
        }
        WeexInstanceMgr weexInstanceMgrSelf = WeexInstanceMgr.self();
        AbsSDKInstance uTSAndroid = getInstance();
        Intrinsics.checkNotNull(uTSAndroid, "null cannot be cast to non-null type com.taobao.weex.WXSDKInstance");
        IWebview iWebviewFindWebview = weexInstanceMgrSelf.findWebview((WXSDKInstance) uTSAndroid);
        String strConvert2AbsFullPath = iWebviewFindWebview.obtainFrameView().obtainApp().convert2AbsFullPath(iWebviewFindWebview.obtainFullUrl(), relativePath);
        Intrinsics.checkNotNullExpressionValue(strConvert2AbsFullPath, "hostWebview.obtainFrameV…nFullUrl(), relativePath)");
        return strConvert2AbsFullPath;
    }

    public final boolean isUniMp() {
        return SDK.isUniMP;
    }

    public final String typeof(Double value) {
        if (value == null) {
            return "object";
        }
        if (Double.isNaN(value.doubleValue()) || Intrinsics.areEqual(value, Double.POSITIVE_INFINITY) || Intrinsics.areEqual(value, Double.NEGATIVE_INFINITY)) {
            return "number";
        }
        return "Double";
    }

    public final String typeof(Float value) {
        if (value == null) {
            return "object";
        }
        if (Float.isNaN(value.floatValue()) || Intrinsics.areEqual(value, Float.POSITIVE_INFINITY) || Intrinsics.areEqual(value, Float.NEGATIVE_INFINITY)) {
            return "number";
        }
        return "Float";
    }

    public final String typeof(Integer value) {
        if (value == null) {
            return "object";
        }
        return "Int";
    }

    /* renamed from: typeof-ExVfyTY, reason: not valid java name */
    public final String m310typeofExVfyTY(UInt value) {
        if (value == null) {
            return "object";
        }
        return "UInt";
    }

    public final String typeof(Byte value) {
        if (value == null) {
            return "object";
        }
        return "Byte";
    }

    /* renamed from: typeof-3swpYEE, reason: not valid java name */
    public final String m308typeof3swpYEE(UByte value) {
        if (value == null) {
            return "object";
        }
        return "UByte";
    }

    public final String typeof(Short value) {
        if (value == null) {
            return "object";
        }
        return "Short";
    }

    /* renamed from: typeof-ffyZV3s, reason: not valid java name */
    public final String m311typeofffyZV3s(UShort value) {
        if (value == null) {
            return "object";
        }
        return "UShort";
    }

    public final String typeof(Long value) {
        if (value == null) {
            return "object";
        }
        return "Long";
    }

    /* renamed from: typeof-ADd3fzo, reason: not valid java name */
    public final String m309typeofADd3fzo(ULong value) {
        if (value == null) {
            return "object";
        }
        return "ULong";
    }

    public final String typeof(Number value) {
        if (value == null) {
            return "object";
        }
        return "number";
    }

    public final String typeof(java.lang.Object value) {
        if (value instanceof String) {
            return "string";
        }
        if (value instanceof Character) {
            return "Char";
        }
        if (value instanceof Number) {
            return "number";
        }
        if (value instanceof Boolean) {
            return "boolean";
        }
        if (value instanceof Function) {
            return "function";
        }
        if (value instanceof UByte) {
            return "UByte";
        }
        if (value instanceof UShort) {
            return "UShort";
        }
        if (value instanceof ULong) {
            return "ULong";
        }
        if (value instanceof UInt) {
            return "UInt";
        }
        return "object";
    }

    public final /* synthetic */ <T> Type getGenericType() {
        Intrinsics.needClassReification();
        Type type = new TypeToken<T>() { // from class: io.dcloud.uts.UTSAndroid.getGenericType.1
        }.getType();
        Intrinsics.checkNotNullExpressionValue(type, "object : TypeToken<T>(){}.type");
        return type;
    }

    public final /* synthetic */ <T> String getGenericClassName() {
        Intrinsics.needClassReification();
        String name = new TypeToken<T>() { // from class: io.dcloud.uts.UTSAndroid.getGenericClassName.1
        }.getRawType().getName();
        Intrinsics.checkNotNullExpressionValue(name, "object : TypeToken<T>(){}.rawType.name");
        return name;
    }

    public final boolean checkSystemPermissionGranted(Activity context, UTSArray<String> requestPermission) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(requestPermission, "requestPermission");
        return XXPermissions.isGranted(context, requestPermission);
    }

    public final UTSArray<String> getSystemPermissionDenied(Activity context, UTSArray<String> requestPermission) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(requestPermission, "requestPermission");
        UTSArray.Companion companion = UTSArray.INSTANCE;
        List<String> denied = XXPermissions.getDenied(context, requestPermission);
        Intrinsics.checkNotNullExpressionValue(denied, "getDenied(context, requestPermission)");
        return companion.fromNative(denied);
    }

    public final void gotoSystemPermissionActivity(Activity context, UTSArray<String> requestPermission) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(requestPermission, "requestPermission");
        XXPermissions.startPermissionActivity(context, (List<String>) requestPermission);
    }

    public static /* synthetic */ void requestSystemPermission$default(UTSAndroid uTSAndroid, Activity activity, UTSArray uTSArray, Function2 function2, Function2 function22, boolean z, int i, java.lang.Object obj) {
        if ((i & 16) != 0) {
            z = false;
        }
        uTSAndroid.requestSystemPermission(activity, uTSArray, function2, function22, z);
    }

    public final void requestSystemPermission(Activity context, UTSArray<String> requestPermission, final Function2<? super Boolean, ? super UTSArray<String>, Unit> success, final Function2<? super Boolean, ? super UTSArray<String>, Unit> fail, boolean shallUnCheck) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(requestPermission, "requestPermission");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(fail, "fail");
        AndroidUTSContext androidUTSContext = AndroidUTSContext.INSTANCE;
        String TYPE_REQUEST = PermissionRequestAction.TYPE_REQUEST;
        Intrinsics.checkNotNullExpressionValue(TYPE_REQUEST, "TYPE_REQUEST");
        androidUTSContext.permission(TYPE_REQUEST, requestPermission);
        Activity activity = context;
        UTSArray<String> uTSArray = requestPermission;
        if (XXPermissions.isGranted(activity, uTSArray)) {
            success.invoke(true, UTSArray.INSTANCE.fromNative(uTSArray));
            AndroidUTSContext androidUTSContext2 = AndroidUTSContext.INSTANCE;
            String TYPE_COMPLETE = PermissionRequestAction.TYPE_COMPLETE;
            Intrinsics.checkNotNullExpressionValue(TYPE_COMPLETE, "TYPE_COMPLETE");
            androidUTSContext2.permission(TYPE_COMPLETE, requestPermission);
            return;
        }
        List<String> deniedPermissions = XXPermissions.getDenied(activity, uTSArray);
        Intrinsics.checkNotNullExpressionValue(deniedPermissions, "deniedPermissions");
        requestPermission.removeAll(deniedPermissions);
        if (requestPermission.size() > 0) {
            AndroidUTSContext androidUTSContext3 = AndroidUTSContext.INSTANCE;
            String TYPE_COMPLETE2 = PermissionRequestAction.TYPE_COMPLETE;
            Intrinsics.checkNotNullExpressionValue(TYPE_COMPLETE2, "TYPE_COMPLETE");
            androidUTSContext3.permission(TYPE_COMPLETE2, requestPermission);
        }
        AndroidUTSContext androidUTSContext4 = AndroidUTSContext.INSTANCE;
        String TYPE_CONFIRM = PermissionRequestAction.TYPE_CONFIRM;
        Intrinsics.checkNotNullExpressionValue(TYPE_CONFIRM, "TYPE_CONFIRM");
        androidUTSContext4.permission(TYPE_CONFIRM, UTSArray.INSTANCE.fromNative(deniedPermissions));
        XXPermissions xXPermissionsWith = XXPermissions.with(activity);
        Intrinsics.checkNotNullExpressionValue(xXPermissionsWith, "with(context)");
        if (shallUnCheck) {
            xXPermissionsWith = XXPermissions.with(activity).unchecked();
            Intrinsics.checkNotNullExpressionValue(xXPermissionsWith, "with(context).unchecked()");
        }
        xXPermissionsWith.permission(deniedPermissions).request(new OnPermissionCallback() { // from class: io.dcloud.uts.UTSAndroid.requestSystemPermission.1
            @Override // com.hjq.permissions.OnPermissionCallback
            public void onGranted(List<String> permissions, boolean allGranted) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                UTSArray<String> uTSArrayFromNative = UTSArray.INSTANCE.fromNative(permissions);
                success.invoke(Boolean.valueOf(allGranted), uTSArrayFromNative);
                AndroidUTSContext androidUTSContext5 = AndroidUTSContext.INSTANCE;
                String TYPE_COMPLETE3 = PermissionRequestAction.TYPE_COMPLETE;
                Intrinsics.checkNotNullExpressionValue(TYPE_COMPLETE3, "TYPE_COMPLETE");
                androidUTSContext5.permission(TYPE_COMPLETE3, uTSArrayFromNative);
            }

            @Override // com.hjq.permissions.OnPermissionCallback
            public void onDenied(List<String> permissions, boolean doNotAskAgain) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                UTSArray<String> uTSArrayFromNative = UTSArray.INSTANCE.fromNative(permissions);
                fail.invoke(Boolean.valueOf(doNotAskAgain), uTSArrayFromNative);
                AndroidUTSContext androidUTSContext5 = AndroidUTSContext.INSTANCE;
                String TYPE_COMPLETE3 = PermissionRequestAction.TYPE_COMPLETE;
                Intrinsics.checkNotNullExpressionValue(TYPE_COMPLETE3, "TYPE_COMPLETE");
                androidUTSContext5.permission(TYPE_COMPLETE3, uTSArrayFromNative);
            }
        });
    }

    public final void onPermissionRequest(Function1<? super UTSArray<String>, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        AndroidUTSContext.INSTANCE.getPermissionRequestFunc().add(listener);
    }

    public final void onPermissionConfirm(Function1<? super UTSArray<String>, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        AndroidUTSContext.INSTANCE.getPermissionConfirmFunc().add(listener);
    }

    public final void onPermissionComplete(Function1<? super UTSArray<String>, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        AndroidUTSContext.INSTANCE.getPermissionRequestFinishedFunc().add(listener);
    }

    public final void offPermissionRequest(Function1<? super UTSArray<String>, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        AndroidUTSContext.INSTANCE.getPermissionRequestFunc().remove(listener);
    }

    public final void offPermissionConfirm(Function1<? super UTSArray<String>, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        AndroidUTSContext.INSTANCE.getPermissionConfirmFunc().remove(listener);
    }

    public final void offPermissionComplete(Function1<? super UTSArray<String>, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        AndroidUTSContext.INSTANCE.getPermissionRequestFinishedFunc().remove(listener);
    }

    private final AbsSDKInstance getInstance() {
        if (AndroidUTSContext.INSTANCE.getInstance() != null) {
            return AndroidUTSContext.INSTANCE.getInstance();
        }
        if (WXSDKManager.getInstance().getAllInstanceMap().isEmpty()) {
            return null;
        }
        java.util.Map<String, WXSDKInstance> allInstanceMap = WXSDKManager.getInstance().getAllInstanceMap();
        Intrinsics.checkNotNullExpressionValue(allInstanceMap, "getInstance().allInstanceMap");
        return (AbsSDKInstance) ((Pair) CollectionsKt.last(MapsKt.toList(allInstanceMap))).getSecond();
    }

    public final String appJSBundleUrl() {
        AbsSDKInstance uTSAndroid = getInstance();
        if (uTSAndroid != null) {
            return uTSAndroid.getBundleUrl();
        }
        return null;
    }

    public final boolean appRunAssetMode() {
        return AppRuntime.isAppResourcesInAssetsPath(getAppContext(), "");
    }

    public final boolean getAppDarkMode() {
        return AppRuntime.getAppDarkMode(getAppContext());
    }

    public final String getAppTheme() {
        String appTheme = AppRuntime.getAppTheme(getAppContext());
        Intrinsics.checkNotNullExpressionValue(appTheme, "getAppTheme(getAppContext())");
        return appTheme;
    }

    @Deprecated(message = "已废弃，请使用getAppCachePath", replaceWith = @ReplaceWith(expression = "getAppCachePath", imports = {}))
    public final String getAppTempPath() {
        if (getInstance() == null) {
            return null;
        }
        WeexInstanceMgr weexInstanceMgrSelf = WeexInstanceMgr.self();
        AbsSDKInstance uTSAndroid = getInstance();
        Intrinsics.checkNotNull(uTSAndroid, "null cannot be cast to non-null type com.taobao.weex.WXSDKInstance");
        IApp iAppObtainApp = weexInstanceMgrSelf.findWebview((WXSDKInstance) uTSAndroid).obtainApp();
        if (iAppObtainApp != null) {
            return iAppObtainApp.obtainAppTempPath();
        }
        return null;
    }

    public final String getAppCachePath() {
        if (getInstance() == null) {
            return null;
        }
        WeexInstanceMgr weexInstanceMgrSelf = WeexInstanceMgr.self();
        AbsSDKInstance uTSAndroid = getInstance();
        Intrinsics.checkNotNull(uTSAndroid, "null cannot be cast to non-null type com.taobao.weex.WXSDKInstance");
        IApp iAppObtainApp = weexInstanceMgrSelf.findWebview((WXSDKInstance) uTSAndroid).obtainApp();
        if (iAppObtainApp != null) {
            return iAppObtainApp.obtainAppTempPath();
        }
        return null;
    }

    public final boolean isPrivacyAgree() {
        return PrivacyManager.getInstance().isAgreePrivacy(getAppContext());
    }

    public final boolean isUIThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public final void setPrivacyAgree(boolean newVal) {
        PrivacyManager.getInstance().setAgreePrivacy(getAppContext(), newVal);
    }

    public final void resetPrivacyAgree() {
        PrivacyManager.getInstance().resetPrivacyLocalConfig(getAppContext());
    }

    public final UTSJSONObject getLanguageInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        uTSJSONObject.set("appLanguage", LanguageUtil.getCurrentLocaleLanguage(context));
        uTSJSONObject.set("osLanguage", LanguageUtil.getDeviceDefLocalLanguage());
        return uTSJSONObject;
    }

    public final UTSJSONObject getWebViewInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (getInstance() == null) {
            return new UTSJSONObject();
        }
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        WeexInstanceMgr weexInstanceMgrSelf = WeexInstanceMgr.self();
        AbsSDKInstance uTSAndroid = getInstance();
        Intrinsics.checkNotNull(uTSAndroid, "null cannot be cast to non-null type com.taobao.weex.WXSDKInstance");
        uTSJSONObject.set("ua", weexInstanceMgrSelf.findWebview((WXSDKInstance) uTSAndroid).getWebviewProperty(IWebview.USER_AGENT));
        uTSJSONObject.set("version", WebViewFactory.getWebViewUserAgentVersion(context));
        uTSJSONObject.set("kernel", WebViewFactory.isOther() ? "x5webview" : "chrome");
        return uTSJSONObject;
    }

    public final String getDeviceID(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String dCloudDeviceID = AppRuntime.getDCloudDeviceID(context);
        Intrinsics.checkNotNullExpressionValue(dCloudDeviceID, "getDCloudDeviceID(context)");
        return dCloudDeviceID;
    }

    public final String getOAID() {
        if (DeviceInfo.oaids == null) {
            return "";
        }
        String oaids = DeviceInfo.oaids;
        Intrinsics.checkNotNullExpressionValue(oaids, "oaids");
        String[] strArr = (String[]) StringKt.split(oaids, "|").toArray(new String[0]);
        return !(strArr.length == 0) ? strArr[0] : "";
    }

    public final String getInnerVersion() {
        return "1.9.9.82448";
    }

    public final String getUniCompileVersion() {
        java.lang.Object objDispatchEvent = EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 28, BaseInfo.sDefaultBootApp);
        Intrinsics.checkNotNull(objDispatchEvent, "null cannot be cast to non-null type io.dcloud.common.DHInterface.IApp");
        String strObtainConfigProperty = ((IApp) objDispatchEvent).obtainConfigProperty(AbsoluteConst.APP_UNIAPP_VERSION);
        Intrinsics.checkNotNullExpressionValue(strObtainConfigProperty, "app.obtainConfigProperty(\"appUniVersion\")");
        return strObtainConfigProperty;
    }

    public final String getUniRuntimeVersion() {
        String uniVersionV3 = BaseInfo.uniVersionV3;
        Intrinsics.checkNotNullExpressionValue(uniVersionV3, "uniVersionV3");
        return uniVersionV3;
    }

    public final String getAppId() {
        String sCurrentAppOriginalAppid = BaseInfo.sCurrentAppOriginalAppid;
        Intrinsics.checkNotNullExpressionValue(sCurrentAppOriginalAppid, "sCurrentAppOriginalAppid");
        return sCurrentAppOriginalAppid;
    }

    public final UTSJSONObject getAppVersion() {
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        java.lang.Object objDispatchEvent = EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 28, BaseInfo.sDefaultBootApp);
        Intrinsics.checkNotNull(objDispatchEvent, "null cannot be cast to non-null type io.dcloud.common.DHInterface.IApp");
        IApp iApp = (IApp) objDispatchEvent;
        uTSJSONObject.set("name", iApp.obtainAppVersionName());
        uTSJSONObject.set("code", iApp.obtainAppVersionCode());
        return uTSJSONObject;
    }

    public final String getAppName() {
        java.lang.Object objDispatchEvent = EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 28, BaseInfo.sDefaultBootApp);
        Intrinsics.checkNotNull(objDispatchEvent, "null cannot be cast to non-null type io.dcloud.common.DHInterface.IApp");
        String strObtainAppName = ((IApp) objDispatchEvent).obtainAppName();
        Intrinsics.checkNotNullExpressionValue(strObtainAppName, "app.obtainAppName()");
        return strObtainAppName;
    }

    public final String getOsTheme() {
        Boolean boolIsSystemNightMode = DeviceInfo.isSystemNightMode(getUniActivity());
        Intrinsics.checkNotNullExpressionValue(boolIsSystemNightMode, "isSystemNightMode(getUniActivity())");
        return boolIsSystemNightMode.booleanValue() ? DCBlurDraweeView.DARK : DCBlurDraweeView.LIGHT;
    }

    public final UTSJSONObject getScreenInfo() {
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        java.lang.Object objDispatchEvent = EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 28, BaseInfo.sDefaultBootApp);
        Intrinsics.checkNotNull(objDispatchEvent, "null cannot be cast to non-null type io.dcloud.common.DHInterface.IApp");
        IApp iApp = (IApp) objDispatchEvent;
        int i = iApp.getInt(0);
        int i2 = iApp.getInt(1);
        float scale = getScale();
        uTSJSONObject.set("screenWidth", Integer.valueOf(MathKt.roundToInt(i / scale)));
        uTSJSONObject.set("screenHeight", Integer.valueOf(MathKt.roundToInt(i2 / scale)));
        return uTSJSONObject;
    }

    public final int getStatusBarHeight() {
        DeviceInfo.updateStatusBarHeight(getUniActivity());
        return MathKt.roundToInt(DeviceInfo.sStatusBarHeight / getScale());
    }

    public final boolean isTitleNViewShow() {
        WeexInstanceMgr weexInstanceMgrSelf = WeexInstanceMgr.self();
        AbsSDKInstance uTSAndroid = getInstance();
        Intrinsics.checkNotNull(uTSAndroid, "null cannot be cast to non-null type com.taobao.weex.WXSDKInstance");
        IWebview iWebviewFindWebview = weexInstanceMgrSelf.findWebview((WXSDKInstance) uTSAndroid);
        Intrinsics.checkNotNull(iWebviewFindWebview, "null cannot be cast to non-null type io.dcloud.common.adapter.ui.AdaWebview");
        ViewOptions viewOptionsObtainFrameOptions = ((AdaWebview) iWebviewFindWebview).mFrameView.obtainFrameOptions();
        return (viewOptionsObtainFrameOptions == null || viewOptionsObtainFrameOptions.titleNView != null || viewOptionsObtainFrameOptions.isStatusbar) ? false : true;
    }

    public final boolean isTabBarShow() {
        TabBarWebview launchTabBar = TabBarWebviewMgr.getInstance().getLaunchTabBar();
        return launchTabBar != null && launchTabBar.isVisible();
    }

    private final float getScale() {
        Activity uniActivity = getUniActivity();
        java.lang.Object systemService = uniActivity != null ? uniActivity.getSystemService("window") : null;
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.scaledDensity;
    }

    public final int getWindowHeight() {
        int statusBarHeight = getStatusBarHeight();
        Intrinsics.checkNotNull(EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 28, BaseInfo.sDefaultBootApp), "null cannot be cast to non-null type io.dcloud.common.DHInterface.IApp");
        int iRoundToInt = MathKt.roundToInt(((IApp) r1).getInt(1) / getScale());
        return Math.min(Integer.valueOf((iRoundToInt - (isTitleNViewShow() ? statusBarHeight + 44 : 0)) - (isTabBarShow() ? 50 : 0)), Integer.valueOf(iRoundToInt)).intValue();
    }

    public final int getWindowWidth() {
        Intrinsics.checkNotNull(EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 28, BaseInfo.sDefaultBootApp), "null cannot be cast to non-null type io.dcloud.common.DHInterface.IApp");
        return Math.ceil(Float.valueOf(((IApp) r0).getInt(0) / getScale())).intValue();
    }

    public final UTSJSONObject getSafeAreaInsets() {
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        uTSJSONObject.set("left", 0);
        uTSJSONObject.set("right", 0);
        uTSJSONObject.set("bottom", 0);
        uTSJSONObject.set("top", Integer.valueOf(isTitleNViewShow() ? 0 : getStatusBarHeight()));
        return uTSJSONObject;
    }

    @Deprecated(message = "已废弃，使用 exit()替代", replaceWith = @ReplaceWith(expression = "utsAndroid!!.quitApp()", imports = {"io.dcloud.uts.UTSAndroid.utsAndroid"}))
    public final void quitApp() {
        Activity uniActivity = INSTANCE.getUniActivity();
        if (uniActivity != null) {
            uniActivity.finish();
        }
    }

    public final void exit() {
        Activity uniActivity = INSTANCE.getUniActivity();
        if (uniActivity != null) {
            uniActivity.finish();
        }
    }

    public final Class<?> getJavaClass(java.lang.Object input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return input.getClass();
    }

    public final KClass<?> getKotlinClass(java.lang.Object input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return Reflection.getOrCreateKotlinClass(input.getClass());
    }

    public static /* synthetic */ UTSTaskDispatcher getDispatcher$default(UTSAndroid uTSAndroid, String str, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return uTSAndroid.getDispatcher(str);
    }

    public final UTSTaskDispatcher getDispatcher(String threadName) {
        String str = threadName;
        if (str == null || str.length() == 0) {
            return new UTSTaskDispatcher(Looper.myLooper());
        }
        String lowerCase = StringKt.toLowerCase(threadName);
        int iHashCode = lowerCase.hashCode();
        if (iHashCode != 3366) {
            if (iHashCode != 99650) {
                if (iHashCode == 3343801 && lowerCase.equals("main")) {
                    return new MainTaskDispatcher();
                }
            } else if (lowerCase.equals(WXDomModule.WXDOM)) {
                return new MainTaskDispatcher();
            }
        } else if (lowerCase.equals("io")) {
            return new IOTaskDispatcher();
        }
        console.log("getDispatcher error: can not find " + threadName);
        return new UTSTaskDispatcher(Looper.myLooper());
    }

    @Deprecated(message = "已废弃，使用 getDispatcher 方法来替代")
    public final void dispatchAsync(String threadName, Function1<java.lang.Object, Unit> action, java.lang.Object param) {
        Intrinsics.checkNotNullParameter(threadName, "threadName");
        Intrinsics.checkNotNullParameter(action, "action");
        int iHashCode = threadName.hashCode();
        if (iHashCode != 3366) {
            if (iHashCode != 99650) {
                if (iHashCode == 3343801 && threadName.equals("main")) {
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new AnonymousClass1(action, param, null), 3, null);
                    return;
                }
            } else if (threadName.equals(WXDomModule.WXDOM)) {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new AnonymousClass2(action, param, null), 3, null);
                return;
            }
        } else if (threadName.equals("io")) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass3(action, param, null), 3, null);
            return;
        }
        console.log("error: can not find " + threadName);
    }

    /* compiled from: UTSAndroid.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.uts.UTSAndroid$dispatchAsync$1", f = "UTSAndroid.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.dcloud.uts.UTSAndroid$dispatchAsync$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, java.lang.Object> {
        final /* synthetic */ Function1<java.lang.Object, Unit> $action;
        final /* synthetic */ java.lang.Object $param;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Function1<java.lang.Object, Unit> function1, java.lang.Object obj, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$action = function1;
            this.$param = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(java.lang.Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$action, this.$param, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final java.lang.Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$action.invoke(this.$param);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: UTSAndroid.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.uts.UTSAndroid$dispatchAsync$2", f = "UTSAndroid.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.dcloud.uts.UTSAndroid$dispatchAsync$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, java.lang.Object> {
        final /* synthetic */ Function1<java.lang.Object, Unit> $action;
        final /* synthetic */ java.lang.Object $param;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Function1<java.lang.Object, Unit> function1, java.lang.Object obj, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$action = function1;
            this.$param = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(java.lang.Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$action, this.$param, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final java.lang.Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$action.invoke(this.$param);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: UTSAndroid.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.dcloud.uts.UTSAndroid$dispatchAsync$3", f = "UTSAndroid.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.dcloud.uts.UTSAndroid$dispatchAsync$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, java.lang.Object> {
        final /* synthetic */ Function1<java.lang.Object, Unit> $action;
        final /* synthetic */ java.lang.Object $param;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Function1<java.lang.Object, Unit> function1, java.lang.Object obj, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$action = function1;
            this.$param = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(java.lang.Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.$action, this.$param, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final java.lang.Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$action.invoke(this.$param);
            return Unit.INSTANCE;
        }
    }

    public final Number rpx2px(Number rpx) {
        Intrinsics.checkNotNullParameter(rpx, "rpx");
        if (getInstance() == null) {
            return (Number) 0;
        }
        float floatByViewport = WXUtils.getFloatByViewport((java.lang.Object) rpx, 750);
        AbsSDKInstance uTSAndroid = getInstance();
        Intrinsics.checkNotNull(uTSAndroid);
        float realPxByWidth = (int) WXViewUtils.getRealPxByWidth(floatByViewport, uTSAndroid.getInstanceViewPortWidthWithFloat());
        AbsSDKInstance uTSAndroid2 = getInstance();
        Intrinsics.checkNotNull(uTSAndroid2);
        return Integer.valueOf(MathKt.roundToInt(realPxByWidth / uTSAndroid2.getContext().getApplicationContext().getResources().getDisplayMetrics().density));
    }

    public final Number devicePX2px(Number devicePX) {
        Intrinsics.checkNotNullParameter(devicePX, "devicePX");
        return Float.valueOf(WXViewUtils.getWeexPxByReal(devicePX.floatValue(), 750));
    }

    private final void initUTSHooksClassArray(Application application) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException {
        try {
            Class<?> cls = Class.forName(application.getPackageName() + ".BuildConfig");
            if (cls == null) {
                return;
            }
            java.lang.Object obj = cls.getField("UTSHooksClassArray").get(null);
            String[] strArr = obj instanceof String[] ? (String[]) obj : null;
            if (strArr != null) {
                for (String str : strArr) {
                    List<String> list = initedHookProxys;
                    if (!list.contains(str)) {
                        try {
                            Class<?> cls2 = Class.forName(str);
                            if (cls2 != null) {
                                java.lang.Object objNewInstance = cls2.newInstance();
                                Intrinsics.checkNotNullExpressionValue(objNewInstance, "configClass.newInstance()");
                                if (objNewInstance instanceof UTSAndroidHookProxy) {
                                    list.add(str);
                                    ((UTSAndroidHookProxy) objNewInstance).onCreate(application);
                                }
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final CoroutineDispatcher getDomCoroutineDispatcher() {
        return Dispatchers.getDefault();
    }

    public final Uri getFileProviderUri(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        UTSAndroid uTSAndroid = INSTANCE;
        if (uTSAndroid.getAppContext() == null) {
            return null;
        }
        Context appContext = uTSAndroid.getAppContext();
        Intrinsics.checkNotNull(appContext);
        String packageName = appContext.getPackageName();
        Context appContext2 = uTSAndroid.getAppContext();
        Intrinsics.checkNotNull(appContext2);
        return FileProvider.getUriForFile(appContext2, packageName + ".dc.fileprovider", file);
    }

    public final void onPrivacyAgreeChange(final Function1<? super PrivacyOption, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        privacyAgreeList.add(new Pair<>(string, callback));
        PrivacyManager.getInstance().registerPrivacyAgreeListener(string, new PrivacyManager.PrivacyAgreeCallback() { // from class: io.dcloud.uts.UTSAndroid$$ExternalSyntheticLambda0
            @Override // io.dcloud.common.ui.PrivacyManager.PrivacyAgreeCallback
            public final void call(Boolean bool) {
                UTSAndroid.onPrivacyAgreeChange$lambda$3(callback, bool);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onPrivacyAgreeChange$lambda$3(Function1 callback, Boolean it) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        callback.invoke(new PrivacyOption(it.booleanValue()));
    }

    public final void offPrivacyAgreeChange(Function1<? super PrivacyOption, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Iterator<Pair<String, Function1<PrivacyOption, Unit>>> it = privacyAgreeList.iterator();
        while (it.hasNext()) {
            Pair<String, Function1<PrivacyOption, Unit>> next = it.next();
            if (Intrinsics.areEqual(next.getSecond(), callback)) {
                it.remove();
                PrivacyManager.getInstance().unRegisterPrivacyAgreeListener(next.getFirst());
            }
        }
    }
}
