package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;

/* compiled from: SpecialTypes.kt */
/* loaded from: classes3.dex */
public final class SpecialTypesKt {
    public static final AbbreviatedType getAbbreviatedType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        UnwrappedType unwrappedTypeUnwrap = kotlinType.unwrap();
        if (unwrappedTypeUnwrap instanceof AbbreviatedType) {
            return (AbbreviatedType) unwrappedTypeUnwrap;
        }
        return null;
    }

    public static final SimpleType getAbbreviation(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        AbbreviatedType abbreviatedType = getAbbreviatedType(kotlinType);
        if (abbreviatedType != null) {
            return abbreviatedType.getAbbreviation();
        }
        return null;
    }

    public static final SimpleType withAbbreviation(SimpleType simpleType, SimpleType abbreviatedType) {
        Intrinsics.checkNotNullParameter(simpleType, "<this>");
        Intrinsics.checkNotNullParameter(abbreviatedType, "abbreviatedType");
        return KotlinTypeKt.isError(simpleType) ? simpleType : new AbbreviatedType(simpleType, abbreviatedType);
    }

    public static final boolean isDefinitelyNotNullType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return kotlinType.unwrap() instanceof DefinitelyNotNullType;
    }

    public static /* synthetic */ SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull$default(SimpleType simpleType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType, z);
    }

    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(SimpleType simpleType, boolean z) {
        Intrinsics.checkNotNullParameter(simpleType, "<this>");
        DefinitelyNotNullType definitelyNotNullTypeMakeDefinitelyNotNull = DefinitelyNotNullType.Companion.makeDefinitelyNotNull(simpleType, z);
        if (definitelyNotNullTypeMakeDefinitelyNotNull != null) {
            return definitelyNotNullTypeMakeDefinitelyNotNull;
        }
        SimpleType simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull = makeIntersectionTypeDefinitelyNotNullOrNotNull(simpleType);
        return simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull == null ? simpleType.makeNullableAsSpecified(false) : simpleTypeMakeIntersectionTypeDefinitelyNotNullOrNotNull;
    }

    public static final NewCapturedType withNotNullProjection(NewCapturedType newCapturedType) {
        Intrinsics.checkNotNullParameter(newCapturedType, "<this>");
        return new NewCapturedType(newCapturedType.getCaptureStatus(), newCapturedType.getConstructor(), newCapturedType.getLowerType(), newCapturedType.getAttributes(), newCapturedType.isMarkedNullable(), true);
    }

    public static /* synthetic */ UnwrappedType makeDefinitelyNotNullOrNotNull$default(UnwrappedType unwrappedType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return makeDefinitelyNotNullOrNotNull(unwrappedType, z);
    }

    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(UnwrappedType unwrappedType, boolean z) {
        Intrinsics.checkNotNullParameter(unwrappedType, "<this>");
        UnwrappedType unwrappedTypeMakeDefinitelyNotNull = DefinitelyNotNullType.Companion.makeDefinitelyNotNull(unwrappedType, z);
        return (unwrappedTypeMakeDefinitelyNotNull == null && (unwrappedTypeMakeDefinitelyNotNull = makeIntersectionTypeDefinitelyNotNullOrNotNull(unwrappedType)) == null) ? unwrappedType.makeNullableAsSpecified(false) : unwrappedTypeMakeDefinitelyNotNull;
    }

    private static final SimpleType makeIntersectionTypeDefinitelyNotNullOrNotNull(KotlinType kotlinType) {
        IntersectionTypeConstructor intersectionTypeConstructorMakeDefinitelyNotNullOrNotNull;
        TypeConstructor constructor = kotlinType.getConstructor();
        IntersectionTypeConstructor intersectionTypeConstructor = constructor instanceof IntersectionTypeConstructor ? (IntersectionTypeConstructor) constructor : null;
        if (intersectionTypeConstructor == null || (intersectionTypeConstructorMakeDefinitelyNotNullOrNotNull = makeDefinitelyNotNullOrNotNull(intersectionTypeConstructor)) == null) {
            return null;
        }
        return intersectionTypeConstructorMakeDefinitelyNotNullOrNotNull.createType();
    }

    private static final IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull(IntersectionTypeConstructor intersectionTypeConstructor) {
        KotlinType kotlinType;
        Collection<KotlinType> collectionMo1619getSupertypes = intersectionTypeConstructor.mo1619getSupertypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionMo1619getSupertypes, 10));
        Iterator<T> it = collectionMo1619getSupertypes.iterator();
        boolean z = false;
        while (true) {
            kotlinType = null;
            if (!it.hasNext()) {
                break;
            }
            UnwrappedType unwrappedTypeMakeDefinitelyNotNullOrNotNull$default = (KotlinType) it.next();
            if (TypeUtils.isNullableType(unwrappedTypeMakeDefinitelyNotNullOrNotNull$default)) {
                unwrappedTypeMakeDefinitelyNotNullOrNotNull$default = makeDefinitelyNotNullOrNotNull$default(unwrappedTypeMakeDefinitelyNotNullOrNotNull$default.unwrap(), false, 1, null);
                z = true;
            }
            arrayList.add(unwrappedTypeMakeDefinitelyNotNullOrNotNull$default);
        }
        ArrayList arrayList2 = arrayList;
        if (!z) {
            return null;
        }
        UnwrappedType alternativeType = intersectionTypeConstructor.getAlternativeType();
        if (alternativeType != null) {
            if (TypeUtils.isNullableType(alternativeType)) {
                alternativeType = makeDefinitelyNotNullOrNotNull$default(alternativeType.unwrap(), false, 1, null);
            }
            kotlinType = alternativeType;
        }
        return new IntersectionTypeConstructor(arrayList2).setAlternative(kotlinType);
    }
}
