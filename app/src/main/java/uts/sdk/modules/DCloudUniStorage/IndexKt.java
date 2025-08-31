package uts.sdk.modules.DCloudUniStorage;

import com.facebook.common.util.UriUtil;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.db.DCStorage;
import io.dcloud.uniapp.UniError;
import io.dcloud.uts.Date;
import io.dcloud.uts.JSON;
import io.dcloud.uts.Map;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSArrayKt;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import io.dcloud.uts.UTSJSONObjectKt;
import io.dcloud.uts.UTSRegExp;
import io.dcloud.uts.UTSTimerKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000æ\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u00106\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u000107\u001a\u0006\u00108\u001a\u00020\n\u001a\u000e\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u0001\u001a\u000e\u0010;\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020<\u001a\u000e\u0010=\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020>\u001a\u0006\u0010?\u001a\u00020\u001d\u001a\u0010\u0010@\u001a\u0004\u0018\u00010\"2\u0006\u0010!\u001a\u00020\u0001\u001a\u0010\u0010A\u001a\u0004\u0018\u00010\"2\u0006\u0010B\u001a\u00020\"\u001a\u0018\u0010C\u001a\u0004\u0018\u00010\"2\u0006\u0010D\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0001\u001a\u000e\u0010E\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020F\u001a\u000e\u0010G\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u0001\u001a\u000e\u0010H\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020I\u001a\u0016\u0010J\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u00012\u0006\u00102\u001a\u00020\"\u001aV\u0010K\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142#\u0010L\u001a\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052!\u0010M\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020N0\u0005\u001a5\u0010O\u001a\u0004\u0018\u00010\"2\u0006\u0010!\u001a\u00020\u00012#\u0010P\u001a\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005\u001ai\u0010Q\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020-26\u0010R\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\n012!\u0010S\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\n0\u0005\u001aq\u0010T\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u00012\u0006\u00102\u001a\u00020\"26\u0010U\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\n012!\u0010V\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\n0\u0005\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"2\u0010\u0004\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\"\u001b\u0010\u000e\u001a\f\u0012\u0004\u0012\u00020\n0\u000fj\u0002`\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"0\u0010\u0013\u001a!\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\r\"0\u0010\u0018\u001a!\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\r\"\u001b\u0010\u001c\u001a\f\u0012\u0004\u0012\u00020\u001d0\u000fj\u0002`\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"2\u0010 \u001a#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0006\u0012\u0004\u0018\u00010\"0\u0005j\u0002`#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\r\"0\u0010%\u001a!\u0012\u0013\u0012\u00110&¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\r\"0\u0010)\u001a!\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\n0\u0005j\u0002`*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\r\"0\u0010,\u001a!\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005j\u0002`.¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\r\"E\u00100\u001a6\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\n01j\u0002`3¢\u0006\b\n\u0000\u001a\u0004\b4\u00105*D\u0010W\"\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u00052\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005*@\u0010X\"\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010Z\"\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010\\\"\u001d\u0012\u0013\u0012\u00110]¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110]¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*\u0016\u0010^\"\b\u0012\u0004\u0012\u00020\n0\u000f2\b\u0012\u0004\u0012\u00020\n0\u000f*@\u0010_\"\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005*@\u0010`\"\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010a\"\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010b\"\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005*@\u0010c\"\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010d\"\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010e\"\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*\u0016\u0010f\"\b\u0012\u0004\u0012\u00020\u001d0\u000f2\b\u0012\u0004\u0012\u00020\u001d0\u000f*@\u0010g\"\u001d\u0012\u0013\u0012\u00110h¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110h¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*D\u0010i\"\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00052\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0006\u0012\u0004\u0018\u00010\"0\u0005*@\u0010j\"\u001d\u0012\u0013\u0012\u00110&¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110&¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005*@\u0010k\"\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010l\"\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010m\"\u001d\u0012\u0013\u0012\u00110n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010o\"\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\n0\u0005*@\u0010p\"\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\n0\u0005*@\u0010q\"\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010r\"\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110[¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*@\u0010s\"\u001d\u0012\u0013\u0012\u00110t¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u00052\u001d\u0012\u0013\u0012\u00110t¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\n0\u0005*j\u0010u\"2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\n0122\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\"¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\n01¨\u0006v"}, d2 = {"STORAGE_DATA_TYPE", "", "getSTORAGE_DATA_TYPE", "()Ljava/lang/String;", "clearStorage", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniStorage/ClearStorageOptions;", "Lkotlin/ParameterName;", "name", AbsoluteConst.JSON_KEY_OPTION, "", "Luts/sdk/modules/DCloudUniStorage/ClearStorage;", "getClearStorage", "()Lkotlin/jvm/functions/Function1;", "clearStorageSync", "Lkotlin/Function0;", "Luts/sdk/modules/DCloudUniStorage/ClearStorageSync;", "getClearStorageSync", "()Lkotlin/jvm/functions/Function0;", "getStorage", "Luts/sdk/modules/DCloudUniStorage/GetStorageOptions;", "options", "Luts/sdk/modules/DCloudUniStorage/GetStorage;", "getGetStorage", "getStorageInfo", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfoOptions;", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfo;", "getGetStorageInfo", "getStorageInfoSync", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfoSuccess;", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfoSync;", "getGetStorageInfoSync", "getStorageSync", IApp.ConfigProperty.CONFIG_KEY, "", "Luts/sdk/modules/DCloudUniStorage/GetStorageSync;", "getGetStorageSync", "removeStorage", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageOptions;", "Luts/sdk/modules/DCloudUniStorage/RemoveStorage;", "getRemoveStorage", "removeStorageSync", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageSync;", "getRemoveStorageSync", "setStorage", "Luts/sdk/modules/DCloudUniStorage/SetStorageOptions;", "Luts/sdk/modules/DCloudUniStorage/SetStorage;", "getSetStorage", "setStorageSync", "Lkotlin/Function2;", "data", "Luts/sdk/modules/DCloudUniStorage/SetStorageSync;", "getSetStorageSync", "()Lkotlin/jvm/functions/Function2;", "clearStorageByJs", "Luts/sdk/modules/DCloudUniStorage/ClearStorageOptionsJSONObject;", "clearStorageSyncByJs", "filterNativeType", "src", "getStorageByJs", "Luts/sdk/modules/DCloudUniStorage/GetStorageOptionsJSONObject;", "getStorageInfoByJs", "Luts/sdk/modules/DCloudUniStorage/GetStorageInfoOptionsJSONObject;", "getStorageInfoSyncByJs", "getStorageSyncByJs", "parseValue", "value", "praseGetStorage", "type", "removeStorageByJs", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageOptionsJSONObject;", "removeStorageSyncByJs", "setStorageByJs", "Luts/sdk/modules/DCloudUniStorage/SetStorageOptionsJSONObject;", "setStorageSyncByJs", "uni_getStorageAsync", "getItemAsyncHandler", "includesKey", "", "uni_getStorageSync", "getItemHandler", "uni_setStorageAsync", "saveItemAsyncHandler", "removeItemAsyncHandler", "uni_setStorageSync", "saveItemHandler", "removeItemHandler", "ClearStorage", "ClearStorageCompleteCallback", UriUtil.LOCAL_RESOURCE_SCHEME, "ClearStorageFailCallback", "Lio/dcloud/uniapp/UniError;", "ClearStorageSuccessCallback", "Luts/sdk/modules/DCloudUniStorage/ClearStorageSuccess;", "ClearStorageSync", "GetStorage", "GetStorageCompleteCallback", "GetStorageFailCallback", "GetStorageInfo", "GetStorageInfoCompleteCallback", "GetStorageInfoFailCallback", "GetStorageInfoSuccessCallback", "GetStorageInfoSync", "GetStorageSuccessCallback", "Luts/sdk/modules/DCloudUniStorage/GetStorageSuccess;", "GetStorageSync", "RemoveStorage", "RemoveStorageCompleteCallback", "RemoveStorageFailCallback", "RemoveStorageSuccessCallback", "Luts/sdk/modules/DCloudUniStorage/RemoveStorageSuccess;", "RemoveStorageSync", "SetStorage", "SetStorageCompleteCallback", "SetStorageFailCallback", "SetStorageSuccessCallback", "Luts/sdk/modules/DCloudUniStorage/SetStorageSuccess;", "SetStorageSync", "uni-storage_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IndexKt {
    private static final String STORAGE_DATA_TYPE = "__TYPE";
    private static final Function1<SetStorageOptions, Unit> setStorage = new Function1<SetStorageOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$setStorage$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SetStorageOptions setStorageOptions) {
            invoke2(setStorageOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final SetStorageOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            UTSTimerKt.setTimeout(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$setStorage$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Type inference failed for: r1v2, types: [T, io.dcloud.common.util.db.DCStorage] */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    objectRef.element = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
                    if (objectRef.element == 0) {
                        UniError uniError = new UniError("uni-setStorage", (Number) (-1), "storage not found.");
                        Function1<UniError, Unit> fail = options.getFail();
                        if (fail != null) {
                            fail.invoke(uniError);
                        }
                        Function1<Object, Unit> complete = options.getComplete();
                        if (complete != null) {
                            complete.invoke(uniError);
                            return;
                        }
                        return;
                    }
                    IndexKt.uni_setStorageAsync(options, new Function2<String, String, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.setStorage.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                            invoke2(str, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String itemKey, String itemData) {
                            Intrinsics.checkNotNullParameter(itemKey, "itemKey");
                            Intrinsics.checkNotNullParameter(itemData, "itemData");
                            objectRef.element.performSetItem(UTSAndroid.INSTANCE.getAppContext(), UTSAndroid.INSTANCE.getAppId(), itemKey, itemData);
                        }
                    }, new Function1<String, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.setStorage.1.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str) {
                            invoke2(str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String itemKey) {
                            Intrinsics.checkNotNullParameter(itemKey, "itemKey");
                            objectRef.element.performRemoveItem(UTSAndroid.INSTANCE.getAppContext(), UTSAndroid.INSTANCE.getAppId(), itemKey);
                        }
                    });
                }
            }, (Number) 0);
        }
    };
    private static final Function2<String, Object, Unit> setStorageSync = new Function2<String, Object, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$setStorageSync$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, Object obj) {
            invoke2(str, obj);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [T, io.dcloud.common.util.db.DCStorage] */
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String key, Object data) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(data, "data");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
            if (objectRef.element == 0) {
                return;
            }
            IndexKt.uni_setStorageSync(key, data, new Function2<String, String, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$setStorageSync$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                    invoke2(str, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String itemKey, String itemValue) {
                    Intrinsics.checkNotNullParameter(itemKey, "itemKey");
                    Intrinsics.checkNotNullParameter(itemValue, "itemValue");
                    objectRef.element.performSetItem(UTSAndroid.INSTANCE.getAppContext(), UTSAndroid.INSTANCE.getAppId(), itemKey, itemValue);
                }
            }, new Function1<String, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$setStorageSync$1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String itemKey) {
                    Intrinsics.checkNotNullParameter(itemKey, "itemKey");
                    objectRef.element.performRemoveItem(UTSAndroid.INSTANCE.getAppContext(), UTSAndroid.INSTANCE.getAppId(), itemKey);
                }
            });
        }
    };
    private static final Function1<GetStorageOptions, Unit> getStorage = new Function1<GetStorageOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$getStorage$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetStorageOptions getStorageOptions) {
            invoke2(getStorageOptions);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [T, io.dcloud.common.util.db.DCStorage] */
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final GetStorageOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
            if (objectRef.element == 0) {
                UniError uniError = new UniError("uni-setStorage", (Number) (-1), "storage not found.");
                Function1<UniError, Unit> fail = options.getFail();
                if (fail != null) {
                    fail.invoke(uniError);
                }
                Function1<Object, Unit> complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(uniError);
                    return;
                }
                return;
            }
            UTSTimerKt.setTimeout(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$getStorage$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    GetStorageOptions getStorageOptions = options;
                    final Ref.ObjectRef<DCStorage> objectRef2 = objectRef;
                    Function1<String, String> function1 = new Function1<String, String>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.getStorage.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final String invoke(String itemKey) {
                            Intrinsics.checkNotNullParameter(itemKey, "itemKey");
                            DCStorage.StorageInfo storageInfoPerformGetItem = objectRef2.element.performGetItem(UTSAndroid.INSTANCE.getAppId(), itemKey);
                            if (storageInfoPerformGetItem == null || storageInfoPerformGetItem.code != 1 || storageInfoPerformGetItem.v == null) {
                                return null;
                            }
                            Object obj = storageInfoPerformGetItem.v;
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                            return (String) obj;
                        }
                    };
                    final Ref.ObjectRef<DCStorage> objectRef3 = objectRef;
                    IndexKt.uni_getStorageAsync(getStorageOptions, function1, new Function1<String, Boolean>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.getStorage.1.1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Boolean invoke(final String key) {
                            Intrinsics.checkNotNullParameter(key, "key");
                            UTSArray uTSArray = new UTSArray();
                            DCStorage.StorageInfo storageInfoPerformGetAllKeys = objectRef3.element.performGetAllKeys(UTSAndroid.INSTANCE.getAppId());
                            if (storageInfoPerformGetAllKeys.code == 1 && storageInfoPerformGetAllKeys.v != null) {
                                uTSArray = new UTSArray();
                                Object obj = storageInfoPerformGetAllKeys.v;
                                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.List<kotlin.String>");
                                Iterator it = ((List) obj).iterator();
                                while (it.hasNext()) {
                                    uTSArray.push((String) it.next());
                                }
                            }
                            return Boolean.valueOf(((String) uTSArray.find(new Function1<String, Boolean>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$getStorage$1$1$2$item$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Boolean invoke(String value) {
                                    Intrinsics.checkNotNullParameter(value, "value");
                                    if (Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(value), "string")) {
                                        return Boolean.valueOf(Intrinsics.areEqual(value, key));
                                    }
                                    return false;
                                }
                            })) != null);
                        }
                    });
                }
            }, (Number) 0);
        }
    };
    private static final Function1<String, Object> getStorageSync = new Function1<String, Object>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$getStorageSync$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return IndexKt.uni_getStorageSync(key, new Function1<String, String>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$getStorageSync$1.1
                @Override // kotlin.jvm.functions.Function1
                public final String invoke(String itemKey) {
                    DCStorage.StorageInfo storageInfoPerformGetItem;
                    Intrinsics.checkNotNullParameter(itemKey, "itemKey");
                    DCStorage dCStorage = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
                    if (dCStorage == null || (storageInfoPerformGetItem = dCStorage.performGetItem(UTSAndroid.INSTANCE.getAppId(), itemKey)) == null || storageInfoPerformGetItem.code != 1 || storageInfoPerformGetItem.v == null) {
                        return "";
                    }
                    Object obj = storageInfoPerformGetItem.v;
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            });
        }
    };
    private static final Function1<GetStorageInfoOptions, Unit> getStorageInfo = new Function1<GetStorageInfoOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$getStorageInfo$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetStorageInfoOptions getStorageInfoOptions) {
            invoke2(getStorageInfoOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final GetStorageInfoOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            UTSTimerKt.setTimeout(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$getStorageInfo$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    DCStorage dCStorage = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
                    if (dCStorage == null) {
                        UniError uniError = new UniError("uni-setStorage", (Number) (-1), "storage not found.");
                        Function1<UniError, Unit> fail = options.getFail();
                        if (fail != null) {
                            fail.invoke(uniError);
                        }
                        Function1<Object, Unit> complete = options.getComplete();
                        if (complete != null) {
                            complete.invoke(uniError);
                        }
                    }
                    GetStorageInfoSuccess getStorageInfoSuccess = new GetStorageInfoSuccess(new UTSArray(), (Number) 0, (Number) 10240);
                    DCStorage.StorageInfo storageInfoPerformGetAllKeys = dCStorage.performGetAllKeys(UTSAndroid.INSTANCE.getAppId());
                    if (storageInfoPerformGetAllKeys.code != 1 || storageInfoPerformGetAllKeys.v == null) {
                        return;
                    }
                    UTSArray<String> uTSArray = new UTSArray<>();
                    Object obj = storageInfoPerformGetAllKeys.v;
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.List<kotlin.String>");
                    Iterator it = ((List) obj).iterator();
                    while (it.hasNext()) {
                        uTSArray.push((String) it.next());
                    }
                    try {
                        getStorageInfoSuccess.setKeys(uTSArray);
                        double d = 1024;
                        getStorageInfoSuccess.setLimitSize(Double.valueOf(dCStorage.getDBMaxLength(UTSAndroid.INSTANCE.getAppId()).longValue() / d));
                        getStorageInfoSuccess.setCurrentSize(Double.valueOf(dCStorage.getDBCurrentLength(UTSAndroid.INSTANCE.getAppId()).longValue() / d));
                    } catch (Throwable unused) {
                    }
                    Function1<GetStorageInfoSuccess, Unit> success = options.getSuccess();
                    if (success != null) {
                        success.invoke(getStorageInfoSuccess);
                    }
                }
            }, (Number) 0);
        }
    };
    private static final Function0<GetStorageInfoSuccess> getStorageInfoSync = new Function0<GetStorageInfoSuccess>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$getStorageInfoSync$1
        @Override // kotlin.jvm.functions.Function0
        public final GetStorageInfoSuccess invoke() {
            GetStorageInfoSuccess getStorageInfoSuccess = new GetStorageInfoSuccess(new UTSArray(), (Number) 0, (Number) 10240);
            DCStorage dCStorage = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
            if (dCStorage == null) {
                return getStorageInfoSuccess;
            }
            DCStorage.StorageInfo storageInfoPerformGetAllKeys = dCStorage.performGetAllKeys(UTSAndroid.INSTANCE.getAppId());
            if (storageInfoPerformGetAllKeys.code == 1 && storageInfoPerformGetAllKeys.v != null) {
                UTSArray<String> uTSArray = new UTSArray<>();
                Object obj = storageInfoPerformGetAllKeys.v;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.List<kotlin.String>");
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    uTSArray.push((String) it.next());
                }
                getStorageInfoSuccess.setKeys(uTSArray);
            }
            try {
                double d = 1024;
                getStorageInfoSuccess.setLimitSize(Double.valueOf(dCStorage.getDBMaxLength(UTSAndroid.INSTANCE.getAppId()).longValue() / d));
                getStorageInfoSuccess.setCurrentSize(Double.valueOf(dCStorage.getDBCurrentLength(UTSAndroid.INSTANCE.getAppId()).longValue() / d));
            } catch (Throwable unused) {
            }
            return getStorageInfoSuccess;
        }
    };
    private static final Function1<RemoveStorageOptions, Unit> removeStorage = new Function1<RemoveStorageOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$removeStorage$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(RemoveStorageOptions removeStorageOptions) {
            invoke2(removeStorageOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final RemoveStorageOptions options) {
            Intrinsics.checkNotNullParameter(options, "options");
            UTSTimerKt.setTimeout(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$removeStorage$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    DCStorage dCStorage = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
                    if (dCStorage == null) {
                        UniError uniError = new UniError("uni-removeStorage", (Number) (-1), "storage not found.");
                        Function1<UniError, Unit> fail = options.getFail();
                        if (fail != null) {
                            fail.invoke(uniError);
                        }
                        Function1<Object, Unit> complete = options.getComplete();
                        if (complete != null) {
                            complete.invoke(uniError);
                            return;
                        }
                        return;
                    }
                    dCStorage.performRemoveItem(UTSAndroid.INSTANCE.getAppContext(), UTSAndroid.INSTANCE.getAppId(), options.getKey());
                    RemoveStorageSuccess removeStorageSuccess = new RemoveStorageSuccess();
                    Function1<RemoveStorageSuccess, Unit> success = options.getSuccess();
                    if (success != null) {
                        success.invoke(removeStorageSuccess);
                    }
                    Function1<Object, Unit> complete2 = options.getComplete();
                    if (complete2 != null) {
                        complete2.invoke(removeStorageSuccess);
                    }
                }
            }, (Number) 0);
        }
    };
    private static final Function1<String, Unit> removeStorageSync = new Function1<String, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$removeStorageSync$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(String str) {
            invoke2(str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            DCStorage dCStorage = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
            if (dCStorage == null) {
                return;
            }
            dCStorage.performRemoveItem(UTSAndroid.INSTANCE.getAppContext(), UTSAndroid.INSTANCE.getAppId(), key);
        }
    };
    private static final Function1<ClearStorageOptions, Unit> clearStorage = new Function1<ClearStorageOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$clearStorage$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClearStorageOptions clearStorageOptions) {
            invoke2(clearStorageOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final ClearStorageOptions clearStorageOptions) {
            UTSTimerKt.setTimeout(new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$clearStorage$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Function1<Object, Unit> complete;
                    Function1<ClearStorageSuccess, Unit> success;
                    Function1<Object, Unit> complete2;
                    Function1<UniError, Unit> fail;
                    DCStorage dCStorage = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
                    if (dCStorage == null) {
                        UniError uniError = new UniError("uni-clearStorage", (Number) (-1), "error:storage not found.");
                        ClearStorageOptions clearStorageOptions2 = clearStorageOptions;
                        if (clearStorageOptions2 != null && (fail = clearStorageOptions2.getFail()) != null) {
                            fail.invoke(uniError);
                        }
                        ClearStorageOptions clearStorageOptions3 = clearStorageOptions;
                        if (clearStorageOptions3 == null || (complete2 = clearStorageOptions3.getComplete()) == null) {
                            return;
                        }
                        complete2.invoke(uniError);
                        return;
                    }
                    dCStorage.performClear(UTSAndroid.INSTANCE.getAppContext(), UTSAndroid.INSTANCE.getAppId());
                    ClearStorageSuccess clearStorageSuccess = new ClearStorageSuccess();
                    ClearStorageOptions clearStorageOptions4 = clearStorageOptions;
                    if (clearStorageOptions4 != null && (success = clearStorageOptions4.getSuccess()) != null) {
                        success.invoke(clearStorageSuccess);
                    }
                    ClearStorageOptions clearStorageOptions5 = clearStorageOptions;
                    if (clearStorageOptions5 == null || (complete = clearStorageOptions5.getComplete()) == null) {
                        return;
                    }
                    complete.invoke(clearStorageSuccess);
                }
            }, (Number) 0);
        }
    };
    private static final Function0<Unit> clearStorageSync = new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt$clearStorageSync$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            DCStorage dCStorage = DCStorage.getDCStorage(UTSAndroid.INSTANCE.getAppContext());
            if (dCStorage == null) {
                return;
            }
            dCStorage.performClear(UTSAndroid.INSTANCE.getAppContext(), UTSAndroid.INSTANCE.getAppId());
        }
    };

    public static final String getSTORAGE_DATA_TYPE() {
        return STORAGE_DATA_TYPE;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0067 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String filterNativeType(java.lang.String r1) {
        /*
            java.lang.String r0 = "src"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            int r0 = r1.hashCode()
            switch(r0) {
                case -1781628633: goto L5e;
                case 73679: goto L55;
                case 2086184: goto L4c;
                case 2374300: goto L43;
                case 2605914: goto L3a;
                case 67973692: goto L31;
                case 79860828: goto L28;
                case 80585469: goto L1f;
                case 80873585: goto L16;
                case 2052876273: goto Ld;
                default: goto Lc;
            }
        Lc:
            goto L69
        Ld:
            java.lang.String r0 = "Double"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L16:
            java.lang.String r0 = "ULong"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L1f:
            java.lang.String r0 = "UByte"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L28:
            java.lang.String r0 = "Short"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L69
            goto L67
        L31:
            java.lang.String r0 = "Float"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L3a:
            java.lang.String r0 = "UInt"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L43:
            java.lang.String r0 = "Long"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L4c:
            java.lang.String r0 = "Byte"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L55:
            java.lang.String r0 = "Int"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L5e:
            java.lang.String r0 = "UShort"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L67
            goto L69
        L67:
            java.lang.String r1 = "number"
        L69:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: uts.sdk.modules.DCloudUniStorage.IndexKt.filterNativeType(java.lang.String):java.lang.String");
    }

    public static final Object parseValue(Object value) throws IllegalAccessException, IllegalArgumentException {
        boolean z;
        Map<String, Object> map;
        String str;
        Intrinsics.checkNotNullParameter(value, "value");
        UTSArray uTSArray_uA = UTSArrayKt._uA("object", "string", "number", "boolean", Constants.Name.UNDEFINED);
        if (Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(value), "string")) {
            value = JSON.parse((String) value);
        }
        if (value == null) {
            return null;
        }
        String strTypeof = UTSAndroid.INSTANCE.typeof(value);
        if (uTSArray_uA.indexOf(strTypeof) >= 0 && (((z = value instanceof UTSJSONObject)) || (value instanceof Map))) {
            if (z) {
                map = ((UTSJSONObject) value).toMap();
            } else {
                map = (Map) value;
            }
            if (map.size() == 2 && map.has("data") && map.has("type")) {
                if (map.get("type") == null) {
                    str = "";
                } else {
                    Object obj = map.get("type");
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    str = (String) obj;
                }
                if (Intrinsics.areEqual(filterNativeType(UTSAndroid.INSTANCE.typeof(map.get("data"))), str) && !Intrinsics.areEqual(str, "string")) {
                    return map.get("data");
                }
                if (Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(map.get("data")), str) && Intrinsics.areEqual(str, "string")) {
                    UTSRegExp uTSRegExp = new UTSRegExp("^\\d{4}-\\d{2}-\\d{2}T\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{3}Z$", "");
                    if (Intrinsics.areEqual(strTypeof, "object")) {
                        Object obj2 = map.get("data");
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                        if (uTSRegExp.test((String) obj2)) {
                            Object obj3 = map.get("data");
                            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.String");
                            return new Date((String) obj3);
                        }
                    }
                    return map.get("data");
                }
            } else if (map.size() >= 1) {
                return "";
            }
        }
        return null;
    }

    public static final Object praseGetStorage(String type, String value) throws IllegalAccessException, IllegalArgumentException {
        Object obj;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        if ((Intrinsics.areEqual(type, "string") && (!Intrinsics.areEqual(type, "string") || !Intrinsics.areEqual(value, "{\"type\":\"undefined\"}"))) || (obj = JSON.parse(value)) == null) {
            return value;
        }
        Object value2 = parseValue(obj);
        if (value2 != null) {
            return value2;
        }
        if (Intrinsics.areEqual(type, "")) {
            return value;
        }
        if (Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(obj), "string")) {
            Object obj2 = JSON.parse((String) obj);
            String strTypeof = UTSAndroid.INSTANCE.typeof(obj2);
            if (Intrinsics.areEqual(strTypeof, "number") && Intrinsics.areEqual(type, "date")) {
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Number");
                return new Date((Number) obj2);
            }
            if (UTSArrayKt._uA("null", "array").indexOf(type) >= 0) {
                type = "object";
            }
            if (Intrinsics.areEqual(strTypeof, type)) {
                return obj2;
            }
        }
        return obj;
    }

    public static final void uni_setStorageSync(String key, Object data, Function2<? super String, ? super String, Unit> saveItemHandler, Function1<? super String, Unit> removeItemHandler) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(saveItemHandler, "saveItemHandler");
        Intrinsics.checkNotNullParameter(removeItemHandler, "removeItemHandler");
        String strFilterNativeType = filterNativeType(UTSAndroid.INSTANCE.typeof(data));
        String strStringify = Intrinsics.areEqual(strFilterNativeType, "string") ? (String) data : JSON.stringify(UTSJSONObjectKt._uO(TuplesKt.to("type", strFilterNativeType), TuplesKt.to("data", data)));
        if (Intrinsics.areEqual(strFilterNativeType, "string") && parseValue(data) != null) {
            saveItemHandler.invoke(key + STORAGE_DATA_TYPE, strFilterNativeType);
        } else {
            removeItemHandler.invoke(key + STORAGE_DATA_TYPE);
        }
        if (strStringify == null) {
            strStringify = "";
        }
        saveItemHandler.invoke(key, strStringify);
    }

    public static final void uni_setStorageAsync(SetStorageOptions options, Function2<? super String, ? super String, Unit> saveItemAsyncHandler, Function1<? super String, Unit> removeItemAsyncHandler) {
        String strStringify;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(saveItemAsyncHandler, "saveItemAsyncHandler");
        Intrinsics.checkNotNullParameter(removeItemAsyncHandler, "removeItemAsyncHandler");
        String strFilterNativeType = filterNativeType(UTSAndroid.INSTANCE.typeof(options.getData()));
        if (Intrinsics.areEqual(strFilterNativeType, "string")) {
            Object data = options.getData();
            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.String");
            strStringify = (String) data;
        } else {
            strStringify = JSON.stringify(UTSJSONObjectKt._uO(TuplesKt.to("type", strFilterNativeType), TuplesKt.to("data", options.getData())));
        }
        if (strStringify == null) {
            UniError uniError = new UniError("uni-storage", (Number) (-1), "data can not be stringify");
            Function1<UniError, Unit> fail = options.getFail();
            if (fail != null) {
                fail.invoke(uniError);
            }
            Function1<Object, Unit> complete = options.getComplete();
            if (complete != null) {
                complete.invoke(uniError);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(strFilterNativeType, "string") && parseValue(options.getData()) != null) {
            saveItemAsyncHandler.invoke(options.getKey() + STORAGE_DATA_TYPE, strFilterNativeType);
        } else {
            removeItemAsyncHandler.invoke(options.getKey() + STORAGE_DATA_TYPE);
        }
        saveItemAsyncHandler.invoke(options.getKey(), strStringify);
        SetStorageSuccess setStorageSuccess = new SetStorageSuccess();
        Function1<SetStorageSuccess, Unit> success = options.getSuccess();
        if (success != null) {
            success.invoke(setStorageSuccess);
        }
        Function1<Object, Unit> complete2 = options.getComplete();
        if (complete2 != null) {
            complete2.invoke(setStorageSuccess);
        }
    }

    public static final Object uni_getStorageSync(String key, Function1<? super String, String> getItemHandler) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(getItemHandler, "getItemHandler");
        String strInvoke = getItemHandler.invoke(key);
        String strInvoke2 = getItemHandler.invoke(key + STORAGE_DATA_TYPE);
        if (strInvoke2 == null) {
            strInvoke2 = "";
        }
        String lowerCase = StringKt.toLowerCase(strInvoke2);
        if (!Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(strInvoke), "string")) {
            return "";
        }
        if (strInvoke == null) {
            strInvoke = "";
        }
        return praseGetStorage(lowerCase, strInvoke);
    }

    public static final void uni_getStorageAsync(GetStorageOptions options, Function1<? super String, String> getItemAsyncHandler, Function1<? super String, Boolean> includesKey) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(getItemAsyncHandler, "getItemAsyncHandler");
        Intrinsics.checkNotNullParameter(includesKey, "includesKey");
        if (!includesKey.invoke(options.getKey()).booleanValue()) {
            UniError uniError = new UniError("uni-storage", (Number) (-2), "getStorage:fail data not found");
            Function1<UniError, Unit> fail = options.getFail();
            if (fail != null) {
                fail.invoke(uniError);
            }
            Function1<Object, Unit> complete = options.getComplete();
            if (complete != null) {
                complete.invoke(uniError);
                return;
            }
            return;
        }
        String strInvoke = getItemAsyncHandler.invoke(options.getKey());
        if (strInvoke == null) {
            strInvoke = "";
        }
        String strInvoke2 = getItemAsyncHandler.invoke(options.getKey() + STORAGE_DATA_TYPE);
        if (strInvoke2 == null) {
            strInvoke2 = "";
        }
        String lowerCase = StringKt.toLowerCase(strInvoke2);
        if (!Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(strInvoke), "string")) {
            GetStorageSuccess getStorageSuccess = new GetStorageSuccess("");
            Function1<GetStorageSuccess, Unit> success = options.getSuccess();
            if (success != null) {
                success.invoke(getStorageSuccess);
            }
            Function1<Object, Unit> complete2 = options.getComplete();
            if (complete2 != null) {
                complete2.invoke(getStorageSuccess);
                return;
            }
            return;
        }
        GetStorageSuccess getStorageSuccess2 = new GetStorageSuccess(praseGetStorage(lowerCase, strInvoke));
        Function1<GetStorageSuccess, Unit> success2 = options.getSuccess();
        if (success2 != null) {
            success2.invoke(getStorageSuccess2);
        }
        Function1<Object, Unit> complete3 = options.getComplete();
        if (complete3 != null) {
            complete3.invoke(getStorageSuccess2);
        }
    }

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

    public static final void setStorageByJs(final SetStorageOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        setStorage.invoke(new SetStorageOptions(options.getKey(), options.getData(), new Function1<SetStorageSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.setStorageByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SetStorageSuccess setStorageSuccess) throws SecurityException {
                invoke2(setStorageSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SetStorageSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<UniError, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.setStorageByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UniError uniError) throws SecurityException {
                invoke2(uniError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UniError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.setStorageByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }));
    }

    public static final void setStorageSyncByJs(String key, Object data) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(data, "data");
        setStorageSync.invoke(key, data);
    }

    public static final void getStorageByJs(final GetStorageOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        getStorage.invoke(new GetStorageOptions(options.getKey(), new Function1<GetStorageSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.getStorageByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GetStorageSuccess getStorageSuccess) throws SecurityException {
                invoke2(getStorageSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GetStorageSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<UniError, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.getStorageByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UniError uniError) throws SecurityException {
                invoke2(uniError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UniError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.getStorageByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }));
    }

    public static final Object getStorageSyncByJs(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getStorageSync.invoke(key);
    }

    public static final void getStorageInfoByJs(final GetStorageInfoOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        getStorageInfo.invoke(new GetStorageInfoOptions(new Function1<GetStorageInfoSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.getStorageInfoByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GetStorageInfoSuccess getStorageInfoSuccess) throws SecurityException {
                invoke2(getStorageInfoSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GetStorageInfoSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<UniError, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.getStorageInfoByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UniError uniError) throws SecurityException {
                invoke2(uniError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UniError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.getStorageInfoByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }));
    }

    public static final GetStorageInfoSuccess getStorageInfoSyncByJs() {
        return getStorageInfoSync.invoke();
    }

    public static final void removeStorageByJs(final RemoveStorageOptionsJSONObject options) {
        Intrinsics.checkNotNullParameter(options, "options");
        removeStorage.invoke(new RemoveStorageOptions(options.getKey(), new Function1<RemoveStorageSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.removeStorageByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RemoveStorageSuccess removeStorageSuccess) throws SecurityException {
                invoke2(removeStorageSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RemoveStorageSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback success = options.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<UniError, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.removeStorageByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UniError uniError) throws SecurityException {
                invoke2(uniError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UniError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback fail = options.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.removeStorageByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                UTSCallback complete = options.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }));
    }

    public static final void removeStorageSyncByJs(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        removeStorageSync.invoke(key);
    }

    public static final void clearStorageByJs(final ClearStorageOptionsJSONObject clearStorageOptionsJSONObject) {
        clearStorage.invoke(clearStorageOptionsJSONObject != null ? new ClearStorageOptions(new Function1<ClearStorageSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.clearStorageByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ClearStorageSuccess clearStorageSuccess) throws SecurityException {
                invoke2(clearStorageSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ClearStorageSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                ClearStorageOptionsJSONObject clearStorageOptionsJSONObject2 = clearStorageOptionsJSONObject;
                Intrinsics.checkNotNull(clearStorageOptionsJSONObject2);
                UTSCallback success = clearStorageOptionsJSONObject2.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<UniError, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.clearStorageByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UniError uniError) throws SecurityException {
                invoke2(uniError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UniError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                ClearStorageOptionsJSONObject clearStorageOptionsJSONObject2 = clearStorageOptionsJSONObject;
                Intrinsics.checkNotNull(clearStorageOptionsJSONObject2);
                UTSCallback fail = clearStorageOptionsJSONObject2.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniStorage.IndexKt.clearStorageByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                ClearStorageOptionsJSONObject clearStorageOptionsJSONObject2 = clearStorageOptionsJSONObject;
                Intrinsics.checkNotNull(clearStorageOptionsJSONObject2);
                UTSCallback complete = clearStorageOptionsJSONObject2.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }) : null);
    }

    public static final void clearStorageSyncByJs() {
        clearStorageSync.invoke();
    }
}
