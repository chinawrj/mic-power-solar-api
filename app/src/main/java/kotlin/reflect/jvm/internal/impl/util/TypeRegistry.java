package kotlin.reflect.jvm.internal.impl.util;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: ArrayMapOwner.kt */
/* loaded from: classes3.dex */
public abstract class TypeRegistry<K, V> {
    private final ConcurrentHashMap<KClass<? extends K>, Integer> idPerType = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public abstract <T extends K> int customComputeIfAbsent(ConcurrentHashMap<KClass<? extends K>, Integer> concurrentHashMap, KClass<T> kClass, Function1<? super KClass<? extends K>, Integer> function1);

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends V, KK extends K> NullableArrayMapAccessor<K, V, T> generateNullableAccessor(KClass<KK> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        return new NullableArrayMapAccessor<>(kClass, getId(kClass));
    }

    public final <T extends K> int getId(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        return customComputeIfAbsent(this.idPerType, kClass, new Function1<KClass<? extends K>, Integer>(this) { // from class: kotlin.reflect.jvm.internal.impl.util.TypeRegistry.getId.1
            final /* synthetic */ TypeRegistry<K, V> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(KClass<? extends K> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Integer.valueOf(((TypeRegistry) this.this$0).idCounter.getAndIncrement());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Collection<Integer> getIndices() {
        Collection<Integer> collectionValues = this.idPerType.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "idPerType.values");
        return collectionValues;
    }
}
