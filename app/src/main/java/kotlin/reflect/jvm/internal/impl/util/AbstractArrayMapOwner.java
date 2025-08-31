package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.KClass;

/* compiled from: ArrayMapOwner.kt */
/* loaded from: classes3.dex */
public abstract class AbstractArrayMapOwner<K, V> implements Iterable<V>, KMappedMarker {
    protected abstract ArrayMap<V> getArrayMap();

    protected abstract TypeRegistry<K, V> getTypeRegistry();

    protected abstract void registerComponent(KClass<? extends K> kClass, V v);

    /* compiled from: ArrayMapOwner.kt */
    public static abstract class AbstractArrayMapAccessor<K, V, T extends V> {
        private final int id;
        private final KClass<? extends K> key;

        public AbstractArrayMapAccessor(KClass<? extends K> key, int i) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.key = key;
            this.id = i;
        }

        protected final T extractValue(AbstractArrayMapOwner<K, V> thisRef) {
            Intrinsics.checkNotNullParameter(thisRef, "thisRef");
            return thisRef.getArrayMap().get(this.id);
        }
    }

    @Override // java.lang.Iterable
    public final Iterator<V> iterator() {
        return getArrayMap().iterator();
    }

    public final boolean isEmpty() {
        return getArrayMap().getSize() == 0;
    }
}
