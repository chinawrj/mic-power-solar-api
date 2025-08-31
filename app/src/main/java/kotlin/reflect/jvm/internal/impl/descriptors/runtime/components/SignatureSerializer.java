package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

/* compiled from: ReflectKotlinClass.kt */
/* loaded from: classes3.dex */
final class SignatureSerializer {
    public static final SignatureSerializer INSTANCE = new SignatureSerializer();

    private SignatureSerializer() {
    }

    public final String methodDesc(Method method) {
        Intrinsics.checkNotNullParameter(method, "method");
        StringBuilder sb = new StringBuilder(Operators.BRACKET_START_STR);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "method.parameterTypes");
        for (Class<?> parameterType : parameterTypes) {
            Intrinsics.checkNotNullExpressionValue(parameterType, "parameterType");
            sb.append(ReflectClassUtilKt.getDesc(parameterType));
        }
        sb.append(Operators.BRACKET_END_STR);
        Class<?> returnType = method.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType, "method.returnType");
        sb.append(ReflectClassUtilKt.getDesc(returnType));
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    public final String constructorDesc(Constructor<?> constructor) {
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        StringBuilder sb = new StringBuilder(Operators.BRACKET_START_STR);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "constructor.parameterTypes");
        for (Class<?> parameterType : parameterTypes) {
            Intrinsics.checkNotNullExpressionValue(parameterType, "parameterType");
            sb.append(ReflectClassUtilKt.getDesc(parameterType));
        }
        sb.append(")V");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    public final String fieldDesc(Field field) {
        Intrinsics.checkNotNullParameter(field, "field");
        Class<?> type = field.getType();
        Intrinsics.checkNotNullExpressionValue(type, "field.type");
        return ReflectClassUtilKt.getDesc(type);
    }
}
