package io.dcloud.uts;

import com.alibaba.fastjson.util.TypeUtils;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.ExifInterface;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Map.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\u00042\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u0005B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u000b¢\u0006\u0002\u0010\fB\u001b\b\u0016\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0002\u0010\u000eB\u0007\b\u0016¢\u0006\u0002\u0010\u000fB\u001d\b\u0016\u0012\u0014\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u0006¢\u0006\u0002\u0010\u0011B\u001b\b\u0016\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u0096\u0002J+\u0010\u001b\u001a\u00020\u001c2!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u001c0\u001eH\u0016J@\u0010\u001b\u001a\u00020\u001c26\u0010\u001d\u001a2\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u001c0\"H\u0016Ja\u0010\u001b\u001a\u00020\u001c2W\u0010\u001d\u001aS\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0017\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u001c0#H\u0016J+\u0010$\u001a\u00020\u001c2!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u001c0\u001eH\u0016J@\u0010$\u001a\u00020\u001c26\u0010\u001d\u001a2\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u001c0\"H\u0016Ja\u0010$\u001a\u00020\u001c2W\u0010\u001d\u001aS\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0017\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u001c0#H\u0016J\u0017\u0010%\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010&J\u0017\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010)J\u0017\u0010*\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010+J\u0017\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010.J\u0017\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\u0015\u00102\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\b\u00103\u001a\u00020\u000bH\u0016J)\u00104\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0017\u001a\u00028\u00002\u0006\u0010!\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00105J\b\u00106\u001a\u000200H\u0016J\u0016\u00107\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000608H\u0016¨\u00069"}, d2 = {"Lio/dcloud/uts/Map;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "Lio/dcloud/uts/UTSValueIterable;", "Lio/dcloud/uts/UTSArray;", "", AbsoluteConst.JSON_KEY_SIZE, "", "(Ljava/lang/Number;)V", "", "(I)V", "map", "(Lio/dcloud/uts/Map;)V", "()V", "mutableList", "(Lio/dcloud/uts/UTSArray;)V", "kotlinMap", "", "(Ljava/util/Map;)V", "delete", "", IApp.ConfigProperty.CONFIG_KEY, "(Ljava/lang/Object;)Z", "equals", "other", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/Function2;", "Lkotlin/Function3;", "forEachUTS", "getBoolean", "(Ljava/lang/Object;)Ljava/lang/Boolean;", "getDouble", "", "(Ljava/lang/Object;)Ljava/lang/Double;", "getInteger", "(Ljava/lang/Object;)Ljava/lang/Integer;", "getLong", "", "(Ljava/lang/Object;)Ljava/lang/Long;", "getString", "", "(Ljava/lang/Object;)Ljava/lang/String;", "has", "hashCode", "set", "(Ljava/lang/Object;Ljava/lang/Object;)Lio/dcloud/uts/Map;", "toString", "valueIterator", "Lio/dcloud/uts/UTSIterator;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class Map<K, V> extends LinkedHashMap<K, V> implements UTSValueIterable<UTSArray<java.lang.Object>> {
    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(java.lang.Object other) {
        return this == other;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return (Set<Map.Entry<K, V>>) getEntries();
    }

    public /* bridge */ Set<Map.Entry<java.lang.Object, java.lang.Object>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<java.lang.Object> getKeys() {
        return super.keySet();
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<java.lang.Object> getValues() {
        return super.values();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return (Set<K>) getKeys();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<V> values() {
        return (Collection<V>) getValues();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Map(Number size) {
        super(size.intValue());
        Intrinsics.checkNotNullParameter(size, "size");
    }

    public Map(int i) {
        super(i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Map(Map<K, V> map) {
        super(map);
        Intrinsics.checkNotNullParameter(map, "map");
    }

    public Map() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Map(UTSArray<UTSArray<java.lang.Object>> mutableList) {
        Intrinsics.checkNotNullParameter(mutableList, "mutableList");
        for (UTSArray<java.lang.Object> uTSArray : mutableList) {
            if (NumberKt.compareTo(uTSArray.getLength(), (Number) 1) > 0) {
                set(uTSArray.get(0), uTSArray.get(1));
            } else {
                Intrinsics.areEqual((java.lang.Object) uTSArray.getLength(), (java.lang.Object) 1);
            }
        }
    }

    public Map(java.util.Map<K, ? extends V> kotlinMap) {
        Intrinsics.checkNotNullParameter(kotlinMap, "kotlinMap");
        putAll(kotlinMap);
    }

    public Map<K, V> set(K key, V value) {
        put(key, value);
        return this;
    }

    public boolean delete(K key) {
        if (!containsKey(key)) {
            return false;
        }
        remove(key);
        return true;
    }

    public boolean has(K key) {
        return containsKey(key);
    }

    public void forEach(Function1<? super V, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        for (Map.Entry<K, V> entry : entrySet()) {
            entry.getKey();
            action.invoke(entry.getValue());
        }
    }

    public void forEach(Function2<? super V, ? super K, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        for (Map.Entry<K, V> entry : entrySet()) {
            action.invoke(entry.getValue(), entry.getKey());
        }
    }

    public void forEach(Function3<? super V, ? super K, ? super Map<K, V>, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        for (Map.Entry<K, V> entry : entrySet()) {
            action.invoke(entry.getValue(), entry.getKey(), this);
        }
    }

    public void forEachUTS(Function1<? super V, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        for (Map.Entry<K, V> entry : entrySet()) {
            entry.getKey();
            action.invoke(entry.getValue());
        }
    }

    public void forEachUTS(Function2<? super V, ? super K, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        for (Map.Entry<K, V> entry : entrySet()) {
            action.invoke(entry.getValue(), entry.getKey());
        }
    }

    public void forEachUTS(Function3<? super V, ? super K, ? super Map<K, V>, Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        for (Map.Entry<K, V> entry : entrySet()) {
            action.invoke(entry.getValue(), entry.getKey(), this);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return "[object Map]";
    }

    @Override // io.dcloud.uts.UTSValueIterable
    public UTSIterator<UTSArray<java.lang.Object>> valueIterator() {
        final Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        return new UTSIterator<>(new Function0<UTSIteratorResult<UTSArray<java.lang.Object>>>() { // from class: io.dcloud.uts.Map.valueIterator.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UTSIteratorResult<UTSArray<java.lang.Object>> invoke() {
                if (!it.hasNext()) {
                    return new UTSIteratorResult<>(true, new UTSArray());
                }
                Map.Entry<K, V> next = it.next();
                return new UTSIteratorResult<>(false, UTSArrayKt.utsArrayOf(next.getKey(), next.getValue()));
            }
        });
    }

    public String getString(K key) {
        if (get(key) == null) {
            return null;
        }
        return NumberKt.toString_number_nullable$default(get(key), (Number) null, 1, (java.lang.Object) null);
    }

    public Integer getInteger(K key) {
        return TypeUtils.castToInt(get(key));
    }

    public Boolean getBoolean(K key) {
        V v = get(key);
        if (v == null) {
            return null;
        }
        return TypeUtils.castToBoolean(v);
    }

    public Long getLong(K key) {
        return TypeUtils.castToLong(get(key));
    }

    public Double getDouble(K key) {
        return TypeUtils.castToDouble(get(key));
    }
}
