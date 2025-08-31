package io.dcloud.uts;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;

/* compiled from: UTSIterator.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0096\u0002J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bH\u0002J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0016J\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¨\u0006\u0011"}, d2 = {"Lio/dcloud/uts/UTSKeyIterable;", "", "get", "propertyName", "getAllFields", "", "Ljava/lang/reflect/Field;", "clazz", "Ljava/lang/Class;", "ignoredKeys", "Lio/dcloud/uts/UTSArray;", "", "keyIterator", "Lio/dcloud/uts/UTSIterator;", "set", "", "value", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface UTSKeyIterable {
    java.lang.Object get(java.lang.Object propertyName);

    UTSArray<String> ignoredKeys();

    UTSIterator<String> keyIterator();

    void set(java.lang.Object propertyName, java.lang.Object value);

    /* compiled from: UTSIterator.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        private static List<Field> getAllFields(UTSKeyIterable uTSKeyIterable, Class<?> cls) {
            ArrayList arrayList = new ArrayList();
            while (cls != null) {
                Field[] declaredFields = cls.getDeclaredFields();
                Intrinsics.checkNotNullExpressionValue(declaredFields, "clazz.declaredFields");
                for (Field field : declaredFields) {
                    Intrinsics.checkNotNullExpressionValue(field, "field");
                    arrayList.add(field);
                }
                cls = cls.getSuperclass();
            }
            return arrayList;
        }

        public static java.lang.Object get(UTSKeyIterable uTSKeyIterable, java.lang.Object propertyName) {
            java.lang.Object next;
            Intrinsics.checkNotNullParameter(propertyName, "propertyName");
            try {
                Iterator<T> it = getAllFields(uTSKeyIterable, uTSKeyIterable.getClass()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (Intrinsics.areEqual(((Field) next).getName(), propertyName)) {
                        break;
                    }
                }
                Field field = (Field) next;
                if (field == null) {
                    return null;
                }
                field.setAccessible(true);
                return field.get(uTSKeyIterable);
            } catch (Exception e) {
                e.printStackTrace();
                console.INSTANCE.errorV1(e);
                return null;
            }
        }

        public static void set(UTSKeyIterable uTSKeyIterable, java.lang.Object propertyName, java.lang.Object obj) throws IllegalAccessException, IllegalArgumentException {
            java.lang.Object next;
            Intrinsics.checkNotNullParameter(propertyName, "propertyName");
            try {
                Iterator<T> it = getAllFields(uTSKeyIterable, uTSKeyIterable.getClass()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (Intrinsics.areEqual(((Field) next).getName(), propertyName)) {
                            break;
                        }
                    }
                }
                Field field = (Field) next;
                if (field == null) {
                    console.INSTANCE.errorV1("not found field " + propertyName);
                    return;
                }
                field.setAccessible(true);
                field.set(uTSKeyIterable, obj);
            } catch (Exception e) {
                e.printStackTrace();
                console.INSTANCE.errorV1(e);
            }
        }

        public static UTSIterator<String> keyIterator(UTSKeyIterable uTSKeyIterable) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(uTSKeyIterable.getClass());
            UTSArray<String> uTSArrayIgnoredKeys = uTSKeyIterable.ignoredKeys();
            ArrayList arrayList = new ArrayList();
            for (KProperty1 kProperty1 : KClasses.getMemberProperties(orCreateKotlinClass)) {
                if (!uTSArrayIgnoredKeys.contains(kProperty1.getName())) {
                    arrayList.add(kProperty1.getName());
                }
            }
            final Iterator it = arrayList.iterator();
            return new UTSIterator<>(new Function0<UTSIteratorResult<String>>() { // from class: io.dcloud.uts.UTSKeyIterable$keyIterator$defaultIterator$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final UTSIteratorResult<String> invoke() {
                    if (it.hasNext()) {
                        return new UTSIteratorResult<>(false, it.next());
                    }
                    return new UTSIteratorResult<>(true, "");
                }
            });
        }

        public static UTSArray<String> ignoredKeys(UTSKeyIterable uTSKeyIterable) {
            return new UTSArray<>();
        }
    }
}
