package io.dcloud.uts;

import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.uts.nativeregex.RegexpBridge;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: String.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b\u001a\u0014\u0010\n\u001a\u0004\u0018\u00010\u0003*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f\u001a\u0012\u0010\r\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f\u001a\u0014\u0010\u000e\u001a\u0004\u0018\u00010\f*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\f\u001a\u0014\u0010\u0010\u001a\u0004\u0018\u00010\f*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f\u001a#\u0010\u0011\u001a\u00020\u0003*\u00020\u00032\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0013\"\u00020\u0003¢\u0006\u0002\u0010\u0014\u001a\u001c\u0010\u0015\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\f\u001a#\u0010\u0018\u001a\u00020\u0003*\u00020\u00192\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0013\"\u00020\f¢\u0006\u0002\u0010\u001b\u001a#\u0010\u001c\u001a\u00020\u0003*\u00020\u00192\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0013\"\u00020\f¢\u0006\u0002\u0010\u001b\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\fH\u0086\u0002\u001a\u001e\u0010\u001f\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\f\u001a\u001e\u0010!\u001a\u00020\f*\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\f\u001a\n\u0010\"\u001a\u00020\b*\u00020\u0003\u001a\u001e\u0010#\u001a\u00020\f*\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\f\u001a\u0014\u0010$\u001a\u0004\u0018\u00010%*\u00020\u00032\u0006\u0010&\u001a\u00020'\u001a\u0014\u0010$\u001a\u0004\u0018\u00010%*\u00020\u00032\u0006\u0010&\u001a\u00020\u0003\u001a\u0014\u0010(\u001a\u0004\u0018\u00010%*\u00020\u00032\u0006\u0010&\u001a\u00020'\u001a\u001e\u0010)\u001a\u00020\u0003*\u00020\u00032\u0006\u0010*\u001a\u00020\f2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0003\u001a\u001e\u0010,\u001a\u00020\u0003*\u00020\u00032\u0006\u0010*\u001a\u00020\f2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0003\u001a\u0012\u0010-\u001a\u00020\u0003*\u00020\u00032\u0006\u0010.\u001a\u00020\f\u001a_\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2K\u00101\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000302\u001a{\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2`\u00101\u001a\\\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000307H\u0007¢\u0006\u0002\b9\u001a\u0090\u0001\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2u\u00101\u001aq\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030:H\u0007¢\u0006\u0002\b<\u001a§\u0001\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2\u008b\u0001\u00101\u001a\u0086\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030=H\u0007¢\u0006\u0002\b?\u001a¼\u0001\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2 \u0001\u00101\u001a\u009b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030@H\u0007¢\u0006\u0002\bB\u001aÑ\u0001\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2µ\u0001\u00101\u001a°\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030CH\u0007¢\u0006\u0002\bE\u001av\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2b\u00101\u001a^\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000307\u001a\u008d\u0001\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2y\u00101\u001au\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030:\u001a¦\u0001\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2\u0091\u0001\u00101\u001a\u008c\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(;\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(>\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030=\u001a½\u0001\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2¨\u0001\u00101\u001a£\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(;\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(>\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(A\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030@\u001aÔ\u0001\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2¿\u0001\u00101\u001aº\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(8\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(;\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(>\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(A\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(D\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00030C\u001a\u001a\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020'2\u0006\u0010F\u001a\u00020\u0003\u001a_\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u00100\u001a\u00020\u00032K\u00101\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\f¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000302\u001a\u001a\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\u0003\u001a\u001a\u0010G\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\u0003\u001a\u0012\u0010H\u001a\u00020\f*\u00020\u00032\u0006\u00100\u001a\u00020'\u001a\u0012\u0010H\u001a\u00020\f*\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003\u001a\u001e\u0010I\u001a\u00020\u0003*\u00020\u00032\b\b\u0002\u0010J\u001a\u00020\f2\b\b\u0002\u0010K\u001a\u00020\f\u001a\u0018\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00030M*\u00020\u00032\u0006\u00100\u001a\u00020'\u001a\u0018\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00030M*\u00020\u00032\u0006\u0010N\u001a\u00020\u0003\u001a \u0010L\u001a\b\u0012\u0004\u0012\u00020\u00030M*\u00020\u00032\u0006\u0010N\u001a\u00020\u00032\u0006\u0010O\u001a\u00020\f\u001a\u001e\u0010P\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\f2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\f\u001a\n\u0010Q\u001a\u00020\u0003*\u00020\u0003\u001a\n\u0010R\u001a\u00020\u0003*\u00020\u0003\u001a\n\u0010S\u001a\u00020\u0003*\u00020\u0003\u001a\n\u0010T\u001a\u00020\u0003*\u00020\u0003\u001a\n\u0010U\u001a\u00020\u0003*\u00020\u0003\u001a\n\u0010V\u001a\u00020\u0003*\u00020\u0003¨\u0006W"}, d2 = {"indexOfImpl", "", "str", "", "other", "startIndex", "endIndex", "ignoreCase", "", "last", "at", "pos", "", "charAt", "charCodeAt", "index", "codePointAt", "concat", "strings", "", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "endsWith", "searchString", "endPosition", "fromCharCode", "Lkotlin/String$Companion;", "codes", "(Lkotlin/jvm/internal/StringCompanionObject;[Ljava/lang/Number;)Ljava/lang/String;", "fromCharPoint", "get", "", "includes", "position", "indexOf", "isWellFormed", "lastIndexOf", "match", "Lio/dcloud/uts/RegExpMatchArray;", "regexp", "Lio/dcloud/uts/UTSRegExp;", "matchAll", "padEnd", Constants.Name.MAX_LENGTH, "fillString", "padStart", "repeat", "count", "replace", "regex", WXBridgeManager.METHOD_CALLBACK, "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "offset", "string", "Lkotlin/Function4;", "p1", "replace_p1_wrong", "Lkotlin/Function5;", "p2", "replace_p2_wrong", "Lkotlin/Function6;", "p3", "replace_p3_wrong", "Lkotlin/Function7;", "p4", "replace_p4_wrong", "Lkotlin/Function8;", "p5", "replace_p5_wrong", "replaceString", "replaceAll", "search", "slice", "start", "end", "split", "Lio/dcloud/uts/UTSArray;", "separator", "limit", "substring", "toLocaleLowerCase", "toLocaleUpperCase", "toLowerCase", "toUpperCase", "toWellFormed", "valueOf", "utsplugin_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StringKt {
    public static final String valueOf(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return str;
    }

    public static final String fromCharPoint(StringCompanionObject stringCompanionObject, Number... codes) {
        Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>");
        Intrinsics.checkNotNullParameter(codes, "codes");
        return ArraysKt.joinToString$default(codes, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1<Number, CharSequence>() { // from class: io.dcloud.uts.StringKt.fromCharPoint.1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(Number code) {
                Intrinsics.checkNotNullParameter(code, "code");
                char[] chars = Character.toChars(code.intValue());
                Intrinsics.checkNotNullExpressionValue(chars, "toChars(code.toInt())");
                return StringsKt.concatToString(chars);
            }
        }, 30, (java.lang.Object) null);
    }

    public static final String at(String str, Number pos) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(pos, "pos");
        int iIntValue = pos.intValue();
        int length = str.length();
        if (iIntValue < 0) {
            iIntValue += length;
        }
        if (iIntValue >= length || iIntValue < 0) {
            return null;
        }
        return String.valueOf(str.charAt(iIntValue));
    }

    public static final String charAt(String str, Number pos) {
        String strAt;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(pos, "pos");
        return (NumberKt.compareTo(pos, (Number) 0) >= 0 && (strAt = at(str, pos)) != null) ? strAt : "";
    }

    public static final Number charCodeAt(String str, Number index) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(index, "index");
        int length = str.length();
        int iIntValue = index.intValue();
        if (iIntValue >= length || iIntValue < 0) {
            return null;
        }
        return Integer.valueOf(str.charAt(iIntValue));
    }

    public static final Number codePointAt(String str, Number pos) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(pos, "pos");
        try {
            return Integer.valueOf(str.codePointAt(pos.intValue()));
        } catch (Exception unused) {
            return null;
        }
    }

    public static final String concat(String str, String... strings) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(strings, "strings");
        return str + ArraysKt.joinToString$default(strings, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (java.lang.Object) null);
    }

    public static final boolean endsWith(String str, String searchString, Number number) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        int length = (number == null || number.intValue() > str.length()) ? str.length() : number.intValue();
        return Intrinsics.areEqual(substring(str, Integer.valueOf(length - searchString.length()), Integer.valueOf(length)), searchString);
    }

    public static /* synthetic */ boolean includes$default(String str, String str2, Number number, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number = (Number) 0;
        }
        return includes(str, str2, number);
    }

    public static final boolean includes(String str, String searchString, Number number) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        if (number == null) {
            number = (Number) 0;
        }
        return NumberKt.compareTo(indexOf(str, searchString, number), (Number) (-1)) > 0;
    }

    public static /* synthetic */ int indexOfImpl$default(String str, String str2, int i, int i2, boolean z, boolean z2, int i3, java.lang.Object obj) {
        if ((i3 & 32) != 0) {
            z2 = false;
        }
        return indexOfImpl(str, str2, i, i2, z, z2);
    }

    public static final int indexOfImpl(String str, String other, int i, int i2, boolean z, boolean z2) {
        IntRange intRangeDownTo;
        Intrinsics.checkNotNullParameter(str, "str");
        Intrinsics.checkNotNullParameter(other, "other");
        if (!z2) {
            intRangeDownTo = new IntRange(RangesKt.coerceAtLeast(i, 0), RangesKt.coerceAtMost(i2, str.length()));
        } else {
            intRangeDownTo = RangesKt.downTo(RangesKt.coerceAtMost(i, StringsKt.getLastIndex(str)), RangesKt.coerceAtLeast(i2, 0));
        }
        int first = intRangeDownTo.getFirst();
        int last = intRangeDownTo.getLast();
        int step = intRangeDownTo.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return -1;
        }
        while (!StringsKt.regionMatches(other, 0, str, first, other.length(), z)) {
            if (first == last) {
                return -1;
            }
            first += step;
        }
        return first;
    }

    public static /* synthetic */ Number indexOf$default(String str, String str2, Number number, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number = (Number) 0;
        }
        return indexOf(str, str2, number);
    }

    public static final Number indexOf(String str, String searchString, Number number) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        int iIntValue = number != null ? number.intValue() : 0;
        if (iIntValue > str.length()) {
            iIntValue = str.length();
        }
        return Integer.valueOf(indexOfImpl$default(str, searchString, iIntValue, str.length(), false, false, 32, null));
    }

    public static /* synthetic */ Number lastIndexOf$default(String str, String str2, Number number, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number = null;
        }
        return lastIndexOf(str, str2, number);
    }

    public static final Number lastIndexOf(String str, String searchString, Number number) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        if (Intrinsics.areEqual(searchString, "")) {
            return Integer.valueOf(number != null ? number.intValue() : str.length());
        }
        return Integer.valueOf(indexOfImpl(str, searchString, number != null ? number.intValue() : StringsKt.getLastIndex(str), 0, false, true));
    }

    public static /* synthetic */ String substring$default(String str, Number number, Number number2, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        return substring(str, number, number2);
    }

    public static final String substring(String str, Number startIndex, Number number) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(startIndex, "startIndex");
        int iIntValue = startIndex.intValue();
        if (number == null) {
            String strSubstring = str.substring(iIntValue);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…substring(realStartIndex)");
            return strSubstring;
        }
        int iIntValue2 = number.intValue();
        if (iIntValue == iIntValue2) {
            return "";
        }
        if (iIntValue > iIntValue2) {
            iIntValue2 = iIntValue;
            iIntValue = iIntValue2;
        }
        if (iIntValue < 0) {
            iIntValue = 0;
        }
        if (iIntValue2 < 0) {
            iIntValue2 = 0;
        }
        if (iIntValue2 > str.length()) {
            iIntValue2 = str.length();
        }
        String strSubstring2 = str.substring(iIntValue, iIntValue2);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…StartIndex, realEndIndex)");
        return strSubstring2;
    }

    public static final RegExpMatchArray match(String str, String regexp) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regexp, "regexp");
        return match(str, new UTSRegExp(regexp));
    }

    public static final RegExpMatchArray match(String str, UTSRegExp regexp) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regexp, "regexp");
        RegExpMatchArray regExpMatchArray = new RegExpMatchArray();
        if (regexp.getGlobal()) {
            Sequence sequenceFindAll$default = Regex.findAll$default(regexp.toRegex(), str, 0, 2, null);
            if (SequencesKt.toList(sequenceFindAll$default).isEmpty()) {
                return null;
            }
            for (MatchResult matchResult : SequencesKt.toList(sequenceFindAll$default)) {
                if (!matchResult.getGroups().isEmpty()) {
                    kotlin.text.MatchGroup matchGroup = matchResult.getGroups().get(0);
                    Intrinsics.checkNotNull(matchGroup);
                    regExpMatchArray.add(matchGroup.getValue());
                }
            }
        } else {
            MatchResult matchResultFind$default = Regex.find$default(regexp.toRegex(), str, 0, 2, null);
            if (matchResultFind$default == null) {
                return null;
            }
            MatchGroupCollection groups = matchResultFind$default.getGroups();
            if (groups != null) {
                for (kotlin.text.MatchGroup matchGroup2 : groups) {
                    if (matchGroup2 != null) {
                        regExpMatchArray.add(matchGroup2.getValue());
                    } else {
                        regExpMatchArray.add(matchGroup2);
                    }
                }
            }
        }
        return regExpMatchArray;
    }

    public static final RegExpMatchArray matchAll(String str, UTSRegExp regexp) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regexp, "regexp");
        if (!regexp.getGlobal()) {
            throw new IllegalArgumentException("String.prototype.matchAll called with a non-global RegExp argument");
        }
        return match(str, regexp);
    }

    public static /* synthetic */ String padEnd$default(String str, Number number, String str2, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            str2 = Operators.SPACE_STR;
        }
        return padEnd(str, number, str2);
    }

    public static final String padEnd(String str, Number maxLength, String str2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(maxLength, "maxLength");
        int iIntValue = maxLength.intValue();
        if (str.length() > iIntValue) {
            return str;
        }
        if (str2 != null && str2.length() == 0) {
            return str;
        }
        if (str2 == null) {
            str2 = Operators.SPACE_STR;
        }
        int length = iIntValue - str.length();
        if (length > str2.length()) {
            str2 = str2 + repeat(str2, Integer.valueOf(length / str2.length()));
        }
        return str + StringsKt.slice(str2, RangesKt.until(0, length));
    }

    public static /* synthetic */ String padStart$default(String str, Number number, String str2, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            str2 = Operators.SPACE_STR;
        }
        return padStart(str, number, str2);
    }

    public static final String padStart(String str, Number maxLength, String str2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(maxLength, "maxLength");
        int iIntValue = maxLength.intValue();
        if (str.length() > iIntValue) {
            return str;
        }
        if (str2 != null && str2.length() == 0) {
            return str;
        }
        if (str2 == null) {
            str2 = Operators.SPACE_STR;
        }
        int length = iIntValue - str.length();
        if (length > str2.length()) {
            str2 = str2 + repeat(str2, Integer.valueOf(length / str2.length()));
        }
        return StringsKt.slice(str2, RangesKt.until(0, length)) + str;
    }

    public static final String repeat(String str, Number count) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(count, "count");
        int iIntValue = count.intValue();
        if (iIntValue < 0) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + iIntValue + Operators.DOT).toString());
        }
        if (iIntValue != 0) {
            int i = 1;
            if (iIntValue == 1) {
                return str;
            }
            int length = str.length();
            if (length != 0) {
                if (length == 1) {
                    char cCharAt = str.charAt(0);
                    char[] cArr = new char[iIntValue];
                    for (int i2 = 0; i2 < iIntValue; i2++) {
                        cArr[i2] = cCharAt;
                    }
                    return StringsKt.concatToString(cArr);
                }
                StringBuilder sb = new StringBuilder(str.length() * iIntValue);
                if (1 <= iIntValue) {
                    while (true) {
                        sb.append(str);
                        if (i == iIntValue) {
                            break;
                        }
                        i++;
                    }
                }
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "{\n                    va…tring()\n                }");
                return string;
            }
        }
        return "";
    }

    public static final String replace(String str, String searchString, String replaceString) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        Intrinsics.checkNotNullParameter(replaceString, "replaceString");
        return StringsKt.replaceFirst$default(str, searchString, replaceString, false, 4, (java.lang.Object) null);
    }

    public static final String replace(String str, UTSRegExp regex, String replaceString) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(replaceString, "replaceString");
        String strReplace = RegexpBridge.INSTANCE.replace(regex.getHostJSRegexp().getBytecode(), str, replaceString, regex.getGlobal());
        Intrinsics.checkNotNull(strReplace);
        return strReplace;
    }

    public static final String replace(final String str, final String regex, final Function3<? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final boolean z = false;
        return new Regex(regex).replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                if (!z && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.isEmpty()) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function3<String, Number, String, String> function3 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String strInvoke = function3.invoke(matchGroup2.getValue(), Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    public static final String replace(final String str, final UTSRegExp regex, final Function3<? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.isEmpty()) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function3<String, Number, String, String> function3 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String strInvoke = function3.invoke(matchGroup2.getValue(), Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    @Deprecated(message = "replace with p1 can be null")
    public static final String replace_p1_wrong(final String str, final UTSRegExp regex, final Function4<? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 2) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function4<String, String, Number, String, String> function4 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value = matchGroup2.getValue();
                kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                Intrinsics.checkNotNull(matchGroup3);
                String strInvoke = function4.invoke(value, matchGroup3.getValue(), Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    public static final String replace(final String str, final UTSRegExp regex, final Function4<? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                String value;
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 2) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function4<String, String, Number, String, String> function4 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value2 = matchGroup2.getValue();
                if (groups.get(1) == null) {
                    value = null;
                } else {
                    kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                    Intrinsics.checkNotNull(matchGroup3);
                    value = matchGroup3.getValue();
                }
                String strInvoke = function4.invoke(value2, value, Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    public static final String replace(final String str, final UTSRegExp regex, final Function5<? super String, ? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                String value;
                String value2;
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 3) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function5<String, String, String, Number, String, String> function5 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value3 = matchGroup2.getValue();
                if (groups.get(1) == null) {
                    value = null;
                } else {
                    kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                    Intrinsics.checkNotNull(matchGroup3);
                    value = matchGroup3.getValue();
                }
                if (groups.get(2) == null) {
                    value2 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup4 = groups.get(2);
                    Intrinsics.checkNotNull(matchGroup4);
                    value2 = matchGroup4.getValue();
                }
                String strInvoke = function5.invoke(value3, value, value2, Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    @Deprecated(message = "replace with p1 p2 can be null")
    public static final String replace_p2_wrong(final String str, final UTSRegExp regex, final Function5<? super String, ? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 3) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function5<String, String, String, Number, String, String> function5 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value = matchGroup2.getValue();
                kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                Intrinsics.checkNotNull(matchGroup3);
                String value2 = matchGroup3.getValue();
                kotlin.text.MatchGroup matchGroup4 = groups.get(2);
                Intrinsics.checkNotNull(matchGroup4);
                String strInvoke = function5.invoke(value, value2, matchGroup4.getValue(), Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    public static final String replace(final String str, final UTSRegExp regex, final Function6<? super String, ? super String, ? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                String value;
                String value2;
                String value3;
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 4) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function6<String, String, String, String, Number, String, String> function6 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value4 = matchGroup2.getValue();
                if (groups.get(1) == null) {
                    value = null;
                } else {
                    kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                    Intrinsics.checkNotNull(matchGroup3);
                    value = matchGroup3.getValue();
                }
                if (groups.get(2) == null) {
                    value2 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup4 = groups.get(2);
                    Intrinsics.checkNotNull(matchGroup4);
                    value2 = matchGroup4.getValue();
                }
                if (groups.get(3) == null) {
                    value3 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup5 = groups.get(3);
                    Intrinsics.checkNotNull(matchGroup5);
                    value3 = matchGroup5.getValue();
                }
                String strInvoke = function6.invoke(value4, value, value2, value3, Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    @Deprecated(message = "replace with p1 p2 p3 can be null")
    public static final String replace_p3_wrong(final String str, final UTSRegExp regex, final Function6<? super String, ? super String, ? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 4) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function6<String, String, String, String, Number, String, String> function6 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value = matchGroup2.getValue();
                kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                Intrinsics.checkNotNull(matchGroup3);
                String value2 = matchGroup3.getValue();
                kotlin.text.MatchGroup matchGroup4 = groups.get(2);
                Intrinsics.checkNotNull(matchGroup4);
                String value3 = matchGroup4.getValue();
                kotlin.text.MatchGroup matchGroup5 = groups.get(3);
                Intrinsics.checkNotNull(matchGroup5);
                String strInvoke = function6.invoke(value, value2, value3, matchGroup5.getValue(), Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    public static final String replace(final String str, final UTSRegExp regex, final Function7<? super String, ? super String, ? super String, ? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                String value;
                String value2;
                String value3;
                String value4;
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 5) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function7<String, String, String, String, String, Number, String, String> function7 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value5 = matchGroup2.getValue();
                if (groups.get(1) == null) {
                    value = null;
                } else {
                    kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                    Intrinsics.checkNotNull(matchGroup3);
                    value = matchGroup3.getValue();
                }
                if (groups.get(2) == null) {
                    value2 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup4 = groups.get(2);
                    Intrinsics.checkNotNull(matchGroup4);
                    value2 = matchGroup4.getValue();
                }
                if (groups.get(3) == null) {
                    value3 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup5 = groups.get(3);
                    Intrinsics.checkNotNull(matchGroup5);
                    value3 = matchGroup5.getValue();
                }
                if (groups.get(4) == null) {
                    value4 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup6 = groups.get(4);
                    Intrinsics.checkNotNull(matchGroup6);
                    value4 = matchGroup6.getValue();
                }
                String strInvoke = function7.invoke(value5, value, value2, value3, value4, Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    @Deprecated(message = "replace with p1 p2 p3 p4 can be null")
    public static final String replace_p4_wrong(final String str, final UTSRegExp regex, final Function7<? super String, ? super String, ? super String, ? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 5) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function7<String, String, String, String, String, Number, String, String> function7 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value = matchGroup2.getValue();
                kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                Intrinsics.checkNotNull(matchGroup3);
                String value2 = matchGroup3.getValue();
                kotlin.text.MatchGroup matchGroup4 = groups.get(2);
                Intrinsics.checkNotNull(matchGroup4);
                String value3 = matchGroup4.getValue();
                kotlin.text.MatchGroup matchGroup5 = groups.get(3);
                Intrinsics.checkNotNull(matchGroup5);
                String value4 = matchGroup5.getValue();
                kotlin.text.MatchGroup matchGroup6 = groups.get(4);
                Intrinsics.checkNotNull(matchGroup6);
                String strInvoke = function7.invoke(value, value2, value3, value4, matchGroup6.getValue(), Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    public static final String replace(final String str, final UTSRegExp regex, final Function8<? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                String value;
                String value2;
                String value3;
                String value4;
                String value5;
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 6) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function8<String, String, String, String, String, String, Number, String, String> function8 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value6 = matchGroup2.getValue();
                if (groups.get(1) == null) {
                    value = null;
                } else {
                    kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                    Intrinsics.checkNotNull(matchGroup3);
                    value = matchGroup3.getValue();
                }
                if (groups.get(2) == null) {
                    value2 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup4 = groups.get(2);
                    Intrinsics.checkNotNull(matchGroup4);
                    value2 = matchGroup4.getValue();
                }
                if (groups.get(3) == null) {
                    value3 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup5 = groups.get(3);
                    Intrinsics.checkNotNull(matchGroup5);
                    value3 = matchGroup5.getValue();
                }
                if (groups.get(4) == null) {
                    value4 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup6 = groups.get(4);
                    Intrinsics.checkNotNull(matchGroup6);
                    value4 = matchGroup6.getValue();
                }
                if (groups.get(5) == null) {
                    value5 = null;
                } else {
                    kotlin.text.MatchGroup matchGroup7 = groups.get(5);
                    Intrinsics.checkNotNull(matchGroup7);
                    value5 = matchGroup7.getValue();
                }
                String strInvoke = function8.invoke(value6, value, value2, value3, value4, value5, Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    @Deprecated(message = "replace with p1 p2 p3 p4 p5 can be null")
    public static final String replace_p5_wrong(final String str, final UTSRegExp regex, final Function8<? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Number, ? super String, String> callback) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final boolean global = regex.getGlobal();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        return regex.toRegex().replace(str, new Function1<MatchResult, CharSequence>() { // from class: io.dcloud.uts.StringKt.replace.12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult match) {
                Intrinsics.checkNotNullParameter(match, "match");
                if (!global && booleanRef.element) {
                    return match.getValue();
                }
                MatchGroupCollection groups = match.getGroups();
                if (groups.size() < 6) {
                    console.error("String.replace input param error. please check input " + regex);
                    booleanRef.element = true;
                    return match.getValue();
                }
                kotlin.text.MatchGroup matchGroup = groups.get(0);
                Intrinsics.checkNotNull(matchGroup);
                int first = matchGroup.getRange().getFirst();
                Function8<String, String, String, String, String, String, Number, String, String> function8 = callback;
                kotlin.text.MatchGroup matchGroup2 = groups.get(0);
                Intrinsics.checkNotNull(matchGroup2);
                String value = matchGroup2.getValue();
                kotlin.text.MatchGroup matchGroup3 = groups.get(1);
                Intrinsics.checkNotNull(matchGroup3);
                String value2 = matchGroup3.getValue();
                kotlin.text.MatchGroup matchGroup4 = groups.get(2);
                Intrinsics.checkNotNull(matchGroup4);
                String value3 = matchGroup4.getValue();
                kotlin.text.MatchGroup matchGroup5 = groups.get(3);
                Intrinsics.checkNotNull(matchGroup5);
                String value4 = matchGroup5.getValue();
                kotlin.text.MatchGroup matchGroup6 = groups.get(4);
                Intrinsics.checkNotNull(matchGroup6);
                String value5 = matchGroup6.getValue();
                kotlin.text.MatchGroup matchGroup7 = groups.get(5);
                Intrinsics.checkNotNull(matchGroup7);
                String strInvoke = function8.invoke(value, value2, value3, value4, value5, matchGroup7.getValue(), Integer.valueOf(first), str);
                booleanRef.element = true;
                return strInvoke;
            }
        });
    }

    public static final String replaceAll(String str, String searchString, String replaceString) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        Intrinsics.checkNotNullParameter(replaceString, "replaceString");
        return StringsKt.replace(str, searchString, replaceString, false);
    }

    public static final Number search(String str, String searchString) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        return Integer.valueOf(indexOf$default(str, searchString, null, 2, null).intValue());
    }

    public static final Number search(String str, UTSRegExp regex) {
        IntRange range;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        MatchResult matchResultFind$default = Regex.find$default(regex.toRegex(), str, 0, 2, null);
        return Integer.valueOf((matchResultFind$default == null || (range = matchResultFind$default.getRange()) == null) ? -1 : range.getStart().intValue());
    }

    public static /* synthetic */ String slice$default(String str, Number number, Number number2, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            number = (Number) 0;
        }
        if ((i & 2) != 0) {
            number2 = Integer.valueOf(str.length());
        }
        return slice(str, number, number2);
    }

    public static final String slice(String str, Number start, Number end) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        int iIntValue = start.intValue();
        if (iIntValue < 0) {
            iIntValue += str.length();
        }
        if (iIntValue < 0) {
            iIntValue = 0;
        }
        int iIntValue2 = end.intValue();
        if (iIntValue2 < 0) {
            iIntValue2 += str.length();
        } else if (iIntValue2 > str.length()) {
            iIntValue2 = str.length();
        }
        return StringsKt.slice(str, RangesKt.until(iIntValue, iIntValue2));
    }

    public static final UTSArray<String> split(String str, String separator) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        UTSArray<String> uTSArray = new UTSArray<>((List<? extends String>) StringsKt.split$default((CharSequence) str, new String[]{separator}, false, 0, 6, (java.lang.Object) null));
        if (Intrinsics.areEqual(separator, "") && uTSArray.size() > 1) {
            if (Intrinsics.areEqual(uTSArray.get(0), "")) {
                uTSArray.remove(0);
            }
            if (Intrinsics.areEqual(uTSArray.get(uTSArray.size() - 1), "")) {
                uTSArray.remove(uTSArray.size() - 1);
            }
        }
        return uTSArray;
    }

    public static final UTSArray<String> split(String str, UTSRegExp regex) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(regex, "regex");
        Regex regex2 = regex.toRegex();
        int last = 0;
        if (Intrinsics.areEqual("(?:)", regex2.getPattern())) {
            UTSArray<String> uTSArray = new UTSArray<>();
            if (regex.getUnicode()) {
                int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, str.length() - 1, 2);
                if (progressionLastElement >= 0) {
                    while (true) {
                        char cCharAt = str.charAt(last);
                        char cCharAt2 = str.charAt(last + 1);
                        if (Character.isHighSurrogate(cCharAt) && Character.isLowSurrogate(cCharAt2)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(cCharAt);
                            sb.append(cCharAt2);
                            uTSArray.add(sb.toString());
                        }
                        if (last == progressionLastElement) {
                            break;
                        }
                        last += 2;
                    }
                }
                return uTSArray;
            }
            String str2 = str;
            while (last < str2.length()) {
                uTSArray.add(String.valueOf(str2.charAt(last)));
                last++;
            }
            return uTSArray;
        }
        UTSArray<String> uTSArray2 = new UTSArray<>();
        for (MatchResult matchResult : Regex.findAll$default(regex2, str, 0, 2, null)) {
            IntRange range = matchResult.getRange();
            if (!range.isEmpty()) {
                uTSArray2.add(StringsKt.substring(str, RangesKt.until(last, range.getFirst())));
                if (!matchResult.getDestructured().toList().isEmpty()) {
                    uTSArray2.addAll(matchResult.getDestructured().toList());
                }
                last = range.getLast() + 1;
            }
        }
        uTSArray2.add(substring$default(str, Integer.valueOf(last), null, 2, null));
        return uTSArray2;
    }

    public static final UTSArray<String> split(String str, String separator, Number limit) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(limit, "limit");
        List mutableList = CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) str, new String[]{separator}, false, 0, 6, (java.lang.Object) null));
        if (Intrinsics.areEqual(separator, "") && mutableList.size() > 1) {
            if (Intrinsics.areEqual(mutableList.get(0), "")) {
                mutableList.remove(0);
            }
            if (Intrinsics.areEqual(mutableList.get(mutableList.size() - 1), "")) {
                mutableList.remove(mutableList.size() - 1);
            }
        }
        int iIntValue = limit.intValue();
        if (iIntValue >= mutableList.size()) {
            return UTSArray.INSTANCE.fromNative(mutableList);
        }
        return UTSArray.INSTANCE.fromNative(mutableList.subList(0, iIntValue));
    }

    public static final String toLocaleLowerCase(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return lowerCase;
    }

    public static final String toLocaleUpperCase(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String upperCase = str.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase;
    }

    public static final String toLowerCase(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return lowerCase;
    }

    public static final String toUpperCase(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String upperCase = str.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase;
    }

    public static final char get(String str, Number index) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(index, "index");
        return str.charAt(index.intValue());
    }

    public static final boolean isWellFormed(String str) {
        int index;
        IndexedValue<Character> next;
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        Iterator<IndexedValue<Character>> it = StringsKt.withIndex(str).iterator();
        do {
            index = -1;
            while (it.hasNext()) {
                next = it.next();
                if (Character.isLowSurrogate(next.getValue().charValue())) {
                    if (next.getIndex() == 0) {
                        return false;
                    }
                } else {
                    if (index != -1) {
                        return false;
                    }
                    if (Character.isHighSurrogate(next.getValue().charValue())) {
                        if (next.getIndex() == length - 1) {
                            return false;
                        }
                        index = next.getIndex();
                    }
                }
            }
            return true;
        } while (index == next.getIndex() - 1);
        return false;
    }

    public static final String toWellFormed(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        while (true) {
            IndexedValue<Character> indexedValue = null;
            for (IndexedValue<Character> indexedValue2 : StringsKt.withIndex(str)) {
                if (Character.isHighSurrogate(indexedValue2.getValue().charValue())) {
                    if (indexedValue != null) {
                        sb.append("��");
                    } else if (indexedValue2.getIndex() == length - 1) {
                        sb.append((char) 65533);
                    } else {
                        indexedValue = indexedValue2;
                    }
                } else if (Character.isLowSurrogate(indexedValue2.getValue().charValue())) {
                    if (indexedValue == null) {
                        sb.append((char) 65533);
                    } else if (indexedValue.getIndex() != indexedValue2.getIndex() - 1) {
                        sb.append((char) 65533);
                    } else {
                        sb.append(indexedValue.getValue().charValue());
                        sb.append(indexedValue2.getValue().charValue());
                    }
                } else if (indexedValue != null) {
                    sb.append((char) 65533);
                    sb.append(indexedValue2.getValue().charValue());
                } else {
                    sb.append(indexedValue2.getValue().charValue());
                }
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            return string;
        }
    }

    public static final String fromCharCode(StringCompanionObject stringCompanionObject, Number... codes) {
        Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>");
        Intrinsics.checkNotNullParameter(codes, "codes");
        ArrayList arrayList = new ArrayList(codes.length);
        for (Number number : codes) {
            arrayList.add(Character.valueOf((char) number.intValue()));
        }
        return StringsKt.concatToString(CollectionsKt.toCharArray(arrayList));
    }
}
