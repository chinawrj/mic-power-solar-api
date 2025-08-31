package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

/* compiled from: AbstractSignatureParts.kt */
/* loaded from: classes3.dex */
public abstract class AbstractSignatureParts<TAnnotation> {
    public abstract AbstractAnnotationTypeQualifierResolver<TAnnotation> getAnnotationTypeQualifierResolver();

    public abstract Iterable<TAnnotation> getAnnotations(KotlinTypeMarker kotlinTypeMarker);

    public abstract Iterable<TAnnotation> getContainerAnnotations();

    public abstract AnnotationQualifierApplicabilityType getContainerApplicabilityType();

    public abstract JavaTypeQualifiersByElementType getContainerDefaultTypeQualifiers();

    public abstract boolean getContainerIsVarargParameter();

    public abstract boolean getEnableImprovementsInStrictMode();

    public abstract KotlinTypeMarker getEnhancedForWarnings(KotlinTypeMarker kotlinTypeMarker);

    public boolean getForceOnlyHeadTypeConstructor() {
        return false;
    }

    public abstract boolean getForceWarning(TAnnotation tannotation);

    public abstract FqNameUnsafe getFqNameUnsafe(KotlinTypeMarker kotlinTypeMarker);

    public abstract boolean getSkipRawTypeArguments();

    public abstract TypeSystemContext getTypeSystem();

    public abstract boolean isArrayOrPrimitiveArray(KotlinTypeMarker kotlinTypeMarker);

    public abstract boolean isCovariant();

    public abstract boolean isEqual(KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2);

    public abstract boolean isFromJava(TypeParameterMarker typeParameterMarker);

    public boolean isNotNullTypeParameterCompat(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        return false;
    }

    private final NullabilityQualifier getNullabilityQualifier(KotlinTypeMarker kotlinTypeMarker) {
        TypeSystemContext typeSystem = getTypeSystem();
        if (typeSystem.isMarkedNullable(typeSystem.lowerBoundIfFlexible(kotlinTypeMarker))) {
            return NullabilityQualifier.NULLABLE;
        }
        if (typeSystem.isMarkedNullable(typeSystem.upperBoundIfFlexible(kotlinTypeMarker))) {
            return null;
        }
        return NullabilityQualifier.NOT_NULL;
    }

    private final JavaTypeQualifiers extractQualifiers(KotlinTypeMarker kotlinTypeMarker) {
        NullabilityQualifier nullabilityQualifier;
        NullabilityQualifier nullabilityQualifier2 = getNullabilityQualifier(kotlinTypeMarker);
        MutabilityQualifier mutabilityQualifier = null;
        if (nullabilityQualifier2 == null) {
            KotlinTypeMarker enhancedForWarnings = getEnhancedForWarnings(kotlinTypeMarker);
            nullabilityQualifier = enhancedForWarnings != null ? getNullabilityQualifier(enhancedForWarnings) : null;
        } else {
            nullabilityQualifier = nullabilityQualifier2;
        }
        TypeSystemContext typeSystem = getTypeSystem();
        if (JavaToKotlinClassMap.INSTANCE.isReadOnly(getFqNameUnsafe(typeSystem.lowerBoundIfFlexible(kotlinTypeMarker)))) {
            mutabilityQualifier = MutabilityQualifier.READ_ONLY;
        } else if (JavaToKotlinClassMap.INSTANCE.isMutable(getFqNameUnsafe(typeSystem.upperBoundIfFlexible(kotlinTypeMarker)))) {
            mutabilityQualifier = MutabilityQualifier.MUTABLE;
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, getTypeSystem().isDefinitelyNotNullType(kotlinTypeMarker) || isNotNullTypeParameterCompat(kotlinTypeMarker), nullabilityQualifier != nullabilityQualifier2);
    }

    private final JavaTypeQualifiers extractQualifiersFromAnnotations(TypeAndDefaultQualifiers typeAndDefaultQualifiers) {
        List listEmptyList;
        AnnotationQualifierApplicabilityType containerApplicabilityType;
        NullabilityQualifierWithMigrationStatus nullabilityQualifier;
        NullabilityQualifierWithMigrationStatus boundsNullability;
        KotlinTypeMarker type;
        TypeConstructorMarker typeConstructorMarkerTypeConstructor;
        if (typeAndDefaultQualifiers.getType() == null) {
            TypeSystemContext typeSystem = getTypeSystem();
            TypeParameterMarker typeParameterForArgument = typeAndDefaultQualifiers.getTypeParameterForArgument();
            if ((typeParameterForArgument != null ? typeSystem.getVariance(typeParameterForArgument) : null) == TypeVariance.IN) {
                return JavaTypeQualifiers.Companion.getNONE();
            }
        }
        boolean z = typeAndDefaultQualifiers.getTypeParameterForArgument() == null;
        KotlinTypeMarker type2 = typeAndDefaultQualifiers.getType();
        if (type2 == null || (listEmptyList = getAnnotations(type2)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        TypeSystemContext typeSystem2 = getTypeSystem();
        KotlinTypeMarker type3 = typeAndDefaultQualifiers.getType();
        TypeParameterMarker typeParameterClassifier = (type3 == null || (typeConstructorMarkerTypeConstructor = typeSystem2.typeConstructor(type3)) == null) ? null : typeSystem2.getTypeParameterClassifier(typeConstructorMarkerTypeConstructor);
        boolean z2 = getContainerApplicabilityType() == AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
        if (z) {
            if (!z2 && getEnableImprovementsInStrictMode() && (type = typeAndDefaultQualifiers.getType()) != null && isArrayOrPrimitiveArray(type)) {
                Iterable<TAnnotation> containerAnnotations = getContainerAnnotations();
                ArrayList arrayList = new ArrayList();
                for (TAnnotation tannotation : containerAnnotations) {
                    if (!getAnnotationTypeQualifierResolver().isTypeUseAnnotation(tannotation)) {
                        arrayList.add(tannotation);
                    }
                }
                listEmptyList = CollectionsKt.plus((Collection) arrayList, (Iterable) listEmptyList);
            } else {
                listEmptyList = CollectionsKt.plus((Iterable) getContainerAnnotations(), (Iterable) listEmptyList);
            }
        }
        MutabilityQualifier mutabilityQualifierExtractMutability = getAnnotationTypeQualifierResolver().extractMutability(listEmptyList);
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullability = getAnnotationTypeQualifierResolver().extractNullability((Iterable) listEmptyList, (Function1) new Function1<TAnnotation, Boolean>(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$extractQualifiersFromAnnotations$annotationsNullability$1
            final /* synthetic */ AbstractSignatureParts<TAnnotation> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(TAnnotation extractNullability) {
                Intrinsics.checkNotNullParameter(extractNullability, "$this$extractNullability");
                return Boolean.valueOf(this.this$0.getForceWarning(extractNullability));
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return invoke((AbstractSignatureParts$extractQualifiersFromAnnotations$annotationsNullability$1<TAnnotation>) obj);
            }
        });
        if (nullabilityQualifierWithMigrationStatusExtractNullability != null) {
            return new JavaTypeQualifiers(nullabilityQualifierWithMigrationStatusExtractNullability.getQualifier(), mutabilityQualifierExtractMutability, nullabilityQualifierWithMigrationStatusExtractNullability.getQualifier() == NullabilityQualifier.NOT_NULL && typeParameterClassifier != null, nullabilityQualifierWithMigrationStatusExtractNullability.isForWarningOnly());
        }
        if (z || z2) {
            containerApplicabilityType = getContainerApplicabilityType();
        } else {
            containerApplicabilityType = AnnotationQualifierApplicabilityType.TYPE_USE;
        }
        JavaTypeQualifiersByElementType defaultQualifiers = typeAndDefaultQualifiers.getDefaultQualifiers();
        JavaDefaultQualifiers javaDefaultQualifiers = defaultQualifiers != null ? defaultQualifiers.get(containerApplicabilityType) : null;
        NullabilityQualifierWithMigrationStatus boundsNullability2 = typeParameterClassifier != null ? getBoundsNullability(typeParameterClassifier) : null;
        if (boundsNullability2 == null || (nullabilityQualifier = NullabilityQualifierWithMigrationStatus.copy$default(boundsNullability2, NullabilityQualifier.NOT_NULL, false, 2, null)) == null) {
            nullabilityQualifier = javaDefaultQualifiers != null ? javaDefaultQualifiers.getNullabilityQualifier() : null;
        }
        boolean z3 = (boundsNullability2 != null ? boundsNullability2.getQualifier() : null) == NullabilityQualifier.NOT_NULL || !(typeParameterClassifier == null || javaDefaultQualifiers == null || !javaDefaultQualifiers.getDefinitelyNotNull());
        TypeParameterMarker typeParameterForArgument2 = typeAndDefaultQualifiers.getTypeParameterForArgument();
        if (typeParameterForArgument2 == null || (boundsNullability = getBoundsNullability(typeParameterForArgument2)) == null) {
            boundsNullability = null;
        } else if (boundsNullability.getQualifier() == NullabilityQualifier.NULLABLE) {
            boundsNullability = NullabilityQualifierWithMigrationStatus.copy$default(boundsNullability, NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
        }
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusMostSpecific = mostSpecific(boundsNullability, nullabilityQualifier);
        return new JavaTypeQualifiers(nullabilityQualifierWithMigrationStatusMostSpecific != null ? nullabilityQualifierWithMigrationStatusMostSpecific.getQualifier() : null, mutabilityQualifierExtractMutability, z3, nullabilityQualifierWithMigrationStatusMostSpecific != null && nullabilityQualifierWithMigrationStatusMostSpecific.isForWarningOnly());
    }

    private final NullabilityQualifierWithMigrationStatus mostSpecific(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus2) {
        return nullabilityQualifierWithMigrationStatus == null ? nullabilityQualifierWithMigrationStatus2 : nullabilityQualifierWithMigrationStatus2 == null ? nullabilityQualifierWithMigrationStatus : (!nullabilityQualifierWithMigrationStatus.isForWarningOnly() || nullabilityQualifierWithMigrationStatus2.isForWarningOnly()) ? (nullabilityQualifierWithMigrationStatus.isForWarningOnly() || !nullabilityQualifierWithMigrationStatus2.isForWarningOnly()) ? (nullabilityQualifierWithMigrationStatus.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus2.getQualifier()) >= 0 && nullabilityQualifierWithMigrationStatus.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus2.getQualifier()) > 0) ? nullabilityQualifierWithMigrationStatus : nullabilityQualifierWithMigrationStatus2 : nullabilityQualifierWithMigrationStatus : nullabilityQualifierWithMigrationStatus2;
    }

    private final NullabilityQualifierWithMigrationStatus getBoundsNullability(TypeParameterMarker typeParameterMarker) {
        ArrayList arrayList;
        NullabilityQualifier nullabilityQualifier;
        TypeSystemContext typeSystem = getTypeSystem();
        if (!isFromJava(typeParameterMarker)) {
            return null;
        }
        List<KotlinTypeMarker> upperBounds = typeSystem.getUpperBounds(typeParameterMarker);
        List<KotlinTypeMarker> list = upperBounds;
        boolean z = list instanceof Collection;
        if (!z || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (!typeSystem.isError((KotlinTypeMarker) it.next())) {
                    if (!z || !list.isEmpty()) {
                        Iterator<T> it2 = list.iterator();
                        while (it2.hasNext()) {
                            if (getNullabilityQualifier((KotlinTypeMarker) it2.next()) != null) {
                                arrayList = upperBounds;
                                break;
                            }
                        }
                    }
                    if (!z || !list.isEmpty()) {
                        Iterator<T> it3 = list.iterator();
                        while (it3.hasNext()) {
                            if (getEnhancedForWarnings((KotlinTypeMarker) it3.next()) != null) {
                                ArrayList arrayList2 = new ArrayList();
                                Iterator<T> it4 = list.iterator();
                                while (it4.hasNext()) {
                                    KotlinTypeMarker enhancedForWarnings = getEnhancedForWarnings((KotlinTypeMarker) it4.next());
                                    if (enhancedForWarnings != null) {
                                        arrayList2.add(enhancedForWarnings);
                                    }
                                }
                                arrayList = arrayList2;
                                List<KotlinTypeMarker> list2 = arrayList;
                                if ((list2 instanceof Collection) && list2.isEmpty()) {
                                    nullabilityQualifier = NullabilityQualifier.NULLABLE;
                                } else {
                                    Iterator<T> it5 = list2.iterator();
                                    while (it5.hasNext()) {
                                        if (!typeSystem.isNullableType((KotlinTypeMarker) it5.next())) {
                                            nullabilityQualifier = NullabilityQualifier.NOT_NULL;
                                            break;
                                        }
                                    }
                                    nullabilityQualifier = NullabilityQualifier.NULLABLE;
                                }
                                return new NullabilityQualifierWithMigrationStatus(nullabilityQualifier, arrayList != upperBounds);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public final Function1<Integer, JavaTypeQualifiers> computeIndexedQualifiers(KotlinTypeMarker kotlinTypeMarker, Iterable<? extends KotlinTypeMarker> overrides, final TypeEnhancementInfo typeEnhancementInfo, boolean z) {
        int size;
        KotlinTypeMarker type;
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        Intrinsics.checkNotNullParameter(overrides, "overrides");
        List<TypeAndDefaultQualifiers> indexed = toIndexed(kotlinTypeMarker);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overrides, 10));
        Iterator<? extends KotlinTypeMarker> it = overrides.iterator();
        while (it.hasNext()) {
            arrayList.add(toIndexed(it.next()));
        }
        ArrayList arrayList2 = arrayList;
        if (getForceOnlyHeadTypeConstructor()) {
            size = 1;
            break;
        }
        if (isCovariant() && (!(overrides instanceof Collection) || !((Collection) overrides).isEmpty())) {
            Iterator<? extends KotlinTypeMarker> it2 = overrides.iterator();
            while (it2.hasNext()) {
                if (!isEqual(kotlinTypeMarker, it2.next())) {
                    size = 1;
                    break;
                }
            }
        }
        size = indexed.size();
        final JavaTypeQualifiers[] javaTypeQualifiersArr = new JavaTypeQualifiers[size];
        int i = 0;
        while (i < size) {
            JavaTypeQualifiers javaTypeQualifiersExtractQualifiersFromAnnotations = extractQualifiersFromAnnotations(indexed.get(i));
            ArrayList arrayList3 = new ArrayList();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                TypeAndDefaultQualifiers typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) CollectionsKt.getOrNull((List) it3.next(), i);
                JavaTypeQualifiers javaTypeQualifiersExtractQualifiers = (typeAndDefaultQualifiers == null || (type = typeAndDefaultQualifiers.getType()) == null) ? null : extractQualifiers(type);
                if (javaTypeQualifiersExtractQualifiers != null) {
                    arrayList3.add(javaTypeQualifiersExtractQualifiers);
                }
            }
            javaTypeQualifiersArr[i] = TypeEnhancementUtilsKt.computeQualifiersForOverride(javaTypeQualifiersExtractQualifiersFromAnnotations, arrayList3, i == 0 && isCovariant(), i == 0 && getContainerIsVarargParameter(), z);
            i++;
        }
        return new Function1<Integer, JavaTypeQualifiers>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts.computeIndexedQualifiers.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ JavaTypeQualifiers invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final JavaTypeQualifiers invoke(int i2) {
                Map<Integer, JavaTypeQualifiers> map;
                JavaTypeQualifiers javaTypeQualifiers;
                TypeEnhancementInfo typeEnhancementInfo2 = typeEnhancementInfo;
                if (typeEnhancementInfo2 != null && (map = typeEnhancementInfo2.getMap()) != null && (javaTypeQualifiers = map.get(Integer.valueOf(i2))) != null) {
                    return javaTypeQualifiers;
                }
                JavaTypeQualifiers[] javaTypeQualifiersArr2 = javaTypeQualifiersArr;
                return (i2 < 0 || i2 > ArraysKt.getLastIndex(javaTypeQualifiersArr2)) ? JavaTypeQualifiers.Companion.getNONE() : javaTypeQualifiersArr2[i2];
            }
        };
    }

    private final <T> void flattenTree(T t, List<T> list, Function1<? super T, ? extends Iterable<? extends T>> function1) {
        list.add(t);
        Iterable<? extends T> iterableInvoke = function1.invoke(t);
        if (iterableInvoke != null) {
            Iterator<? extends T> it = iterableInvoke.iterator();
            while (it.hasNext()) {
                flattenTree(it.next(), list, function1);
            }
        }
    }

    private final <T> List<T> flattenTree(T t, Function1<? super T, ? extends Iterable<? extends T>> function1) {
        ArrayList arrayList = new ArrayList(1);
        flattenTree(t, arrayList, function1);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JavaTypeQualifiersByElementType extractAndMergeDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType) {
        return getAnnotationTypeQualifierResolver().extractAndMergeDefaultQualifiers(javaTypeQualifiersByElementType, getAnnotations(kotlinTypeMarker));
    }

    private final List<TypeAndDefaultQualifiers> toIndexed(KotlinTypeMarker kotlinTypeMarker) {
        final TypeSystemContext typeSystem = getTypeSystem();
        return flattenTree(new TypeAndDefaultQualifiers(kotlinTypeMarker, extractAndMergeDefaultQualifiers(kotlinTypeMarker, getContainerDefaultTypeQualifiers()), null), new Function1<TypeAndDefaultQualifiers, Iterable<? extends TypeAndDefaultQualifiers>>(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$toIndexed$1$1
            final /* synthetic */ AbstractSignatureParts<TAnnotation> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Iterable<AbstractSignatureParts.TypeAndDefaultQualifiers> invoke(AbstractSignatureParts.TypeAndDefaultQualifiers it) {
                TypeConstructorMarker typeConstructorMarkerTypeConstructor;
                List<TypeParameterMarker> parameters;
                AbstractSignatureParts.TypeAndDefaultQualifiers typeAndDefaultQualifiers;
                FlexibleTypeMarker flexibleTypeMarkerAsFlexibleType;
                Intrinsics.checkNotNullParameter(it, "it");
                ArrayList arrayList = null;
                if (this.this$0.getSkipRawTypeArguments()) {
                    KotlinTypeMarker type = it.getType();
                    if (((type == null || (flexibleTypeMarkerAsFlexibleType = typeSystem.asFlexibleType(type)) == null) ? null : typeSystem.asRawType(flexibleTypeMarkerAsFlexibleType)) != null) {
                        return null;
                    }
                }
                KotlinTypeMarker type2 = it.getType();
                if (type2 != null && (typeConstructorMarkerTypeConstructor = typeSystem.typeConstructor(type2)) != null && (parameters = typeSystem.getParameters(typeConstructorMarkerTypeConstructor)) != null) {
                    List<TypeParameterMarker> list = parameters;
                    List<TypeArgumentMarker> arguments = typeSystem.getArguments(it.getType());
                    TypeSystemContext typeSystemContext = typeSystem;
                    AbstractSignatureParts<TAnnotation> abstractSignatureParts = this.this$0;
                    Iterator<T> it2 = list.iterator();
                    Iterator<T> it3 = arguments.iterator();
                    ArrayList arrayList2 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(list, 10), CollectionsKt.collectionSizeOrDefault(arguments, 10)));
                    while (it2.hasNext() && it3.hasNext()) {
                        Object next = it2.next();
                        TypeArgumentMarker typeArgumentMarker = (TypeArgumentMarker) it3.next();
                        TypeParameterMarker typeParameterMarker = (TypeParameterMarker) next;
                        if (typeSystemContext.isStarProjection(typeArgumentMarker)) {
                            typeAndDefaultQualifiers = new AbstractSignatureParts.TypeAndDefaultQualifiers(null, it.getDefaultQualifiers(), typeParameterMarker);
                        } else {
                            KotlinTypeMarker type3 = typeSystemContext.getType(typeArgumentMarker);
                            typeAndDefaultQualifiers = new AbstractSignatureParts.TypeAndDefaultQualifiers(type3, abstractSignatureParts.extractAndMergeDefaultQualifiers(type3, it.getDefaultQualifiers()), typeParameterMarker);
                        }
                        arrayList2.add(typeAndDefaultQualifiers);
                    }
                    arrayList = arrayList2;
                }
                return arrayList;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractSignatureParts.kt */
    static final class TypeAndDefaultQualifiers {
        private final JavaTypeQualifiersByElementType defaultQualifiers;
        private final KotlinTypeMarker type;
        private final TypeParameterMarker typeParameterForArgument;

        public TypeAndDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType, TypeParameterMarker typeParameterMarker) {
            this.type = kotlinTypeMarker;
            this.defaultQualifiers = javaTypeQualifiersByElementType;
            this.typeParameterForArgument = typeParameterMarker;
        }

        public final KotlinTypeMarker getType() {
            return this.type;
        }

        public final JavaTypeQualifiersByElementType getDefaultQualifiers() {
            return this.defaultQualifiers;
        }

        public final TypeParameterMarker getTypeParameterForArgument() {
            return this.typeParameterForArgument;
        }
    }
}
