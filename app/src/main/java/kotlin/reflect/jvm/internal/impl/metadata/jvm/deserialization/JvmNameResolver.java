package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;

/* compiled from: JvmNameResolver.kt */
/* loaded from: classes3.dex */
public final class JvmNameResolver extends JvmNameResolverBase {
    private final JvmProtoBuf.StringTableTypes types;

    /* JADX WARN: Illegal instructions before constructor call */
    public JvmNameResolver(JvmProtoBuf.StringTableTypes types, String[] strings) {
        Set set;
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(strings, "strings");
        List<Integer> _init_$lambda$0 = types.getLocalNameList();
        if (_init_$lambda$0.isEmpty()) {
            set = SetsKt.emptySet();
        } else {
            Intrinsics.checkNotNullExpressionValue(_init_$lambda$0, "_init_$lambda$0");
            set = CollectionsKt.toSet(_init_$lambda$0);
        }
        List<JvmProtoBuf.StringTableTypes.Record> recordList = types.getRecordList();
        Intrinsics.checkNotNullExpressionValue(recordList, "types.recordList");
        super(strings, set, JvmNameResolverKt.toExpandedRecordsList(recordList));
        this.types = types;
    }
}
