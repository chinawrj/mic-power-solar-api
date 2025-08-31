package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import com.taobao.weex.el.parse.Operators;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.text.StringsKt;

/* compiled from: ReflectKotlinClassFinder.kt */
/* loaded from: classes3.dex */
public final class ReflectKotlinClassFinderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String toRuntimeFqName(ClassId classId) {
        String strAsString = classId.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "relativeClassName.asString()");
        String strReplace$default = StringsKt.replace$default(strAsString, Operators.DOT, '$', false, 4, (Object) null);
        if (classId.getPackageFqName().isRoot()) {
            return strReplace$default;
        }
        return classId.getPackageFqName() + Operators.DOT + strReplace$default;
    }
}
