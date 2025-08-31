package io.dcloud.uts;

import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.util.ExifInterface;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Set.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a=\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u0006\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001a \u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\u001a-\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u0006\u0010\n\u001a\u0002H\u0002¢\u0006\u0002\u0010\u000b\u001a-\u0010\f\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u0006\u0010\n\u001a\u0002H\u0002¢\u0006\u0002\u0010\u000b*\u001c\u0010\r\u001a\u0004\b\u0000\u0010\u0002\"\b\u0012\u0004\u0012\u0002H\u0002`\u000e2\b\u0012\u0004\u0012\u0002H\u00020\u0001¨\u0006\u000f"}, d2 = {"add", "Ljava/util/LinkedHashSet;", ExifInterface.LONGITUDE_EAST, "Lio/dcloud/uts/Set;", "value", "(Ljava/util/LinkedHashSet;Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "clear", "", "delete", "", IApp.ConfigProperty.CONFIG_KEY, "(Ljava/util/LinkedHashSet;Ljava/lang/Object;)Z", "has", "Set", "Lkotlin/collections/LinkedHashSet;", "utsplugin_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SetKt {
    public static final <E> boolean delete(LinkedHashSet<E> linkedHashSet, E e) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
        if (!linkedHashSet.contains(e)) {
            return false;
        }
        linkedHashSet.remove(e);
        return true;
    }

    public static final <E> boolean has(LinkedHashSet<E> linkedHashSet, E e) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
        return linkedHashSet.contains(e);
    }

    public static final <E> LinkedHashSet<E> add(LinkedHashSet<E> linkedHashSet, E e) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
        linkedHashSet.add(e);
        return linkedHashSet;
    }

    public static final <E> void clear(LinkedHashSet<E> linkedHashSet) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "<this>");
        linkedHashSet.clear();
    }
}
