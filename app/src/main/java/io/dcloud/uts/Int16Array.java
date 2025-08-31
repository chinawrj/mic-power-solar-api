package io.dcloud.uts;

import com.taobao.weex.common.Constants;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.uts.utils.IndexKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: Int16Array.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 +2\u00020\u0001:\u0001+B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\b\u0016\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J_\u0010\u0014\u001a\u00020\u0000\"\b\b\u0000\u0010\u0015*\u00020\u00012K\u0010\u0016\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u001b0\u0017H\u0016J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 H\u0096\u0002J_\u0010!\u001a\u00020\u0000\"\b\b\u0000\u0010\u0015*\u00020\u00012K\u0010\"\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u0011H\u0015¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00030\u0017H\u0016J\u001f\u0010#\u001a\u00020$2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0013\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010%J\u001c\u0010&\u001a\u00020\u00002\b\u0010'\u001a\u0004\u0018\u00010\u00032\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J\u001c\u0010)\u001a\u00020\u00002\b\u0010*\u001a\u0004\u0018\u00010\u00032\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016R\u001c\u0010\r\u001a\u00020\u0003X\u0096\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0004¨\u0006,"}, d2 = {"Lio/dcloud/uts/Int16Array;", "Lio/dcloud/uts/TypedArray;", "length", "", "(Ljava/lang/Number;)V", "array", "", "(Ljava/util/Collection;)V", "buffer", "Lio/dcloud/uts/ArrayBuffer;", "(Lio/dcloud/uts/ArrayBuffer;)V", "byteOffset", "(Lio/dcloud/uts/ArrayBuffer;Ljava/lang/Number;Ljava/lang/Number;)V", "BYTES_PER_ELEMENT", "getBYTES_PER_ELEMENT", "()Ljava/lang/Number;", "setBYTES_PER_ELEMENT", "BYTES_PER_ELEMENT$1", "convertValue", "value", Constants.Name.FILTER, ExifInterface.GPS_DIRECTION_TRUE, "predicate", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "index", "", "getAuto", "", "getBytesPerElement", "iterator", "", "map", "callbackfn", "putAuto", "", "(Ljava/lang/Integer;Ljava/lang/Number;)V", "slice", "start", "end", "subarray", "begin", "Companion", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Int16Array extends TypedArray {
    public static final int BYTES_PER_ELEMENT = 2;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: BYTES_PER_ELEMENT$1, reason: from kotlin metadata */
    private Number BYTES_PER_ELEMENT;

    @Override // io.dcloud.uts.TypedArray
    public Number getBYTES_PER_ELEMENT() {
        return this.BYTES_PER_ELEMENT;
    }

    @Override // io.dcloud.uts.TypedArray
    public void setBYTES_PER_ELEMENT(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.BYTES_PER_ELEMENT = number;
    }

    @Override // io.dcloud.uts.TypedArray
    public Number getBytesPerElement() {
        return (Number) 2;
    }

    @Override // io.dcloud.uts.TypedArray
    public Number convertValue(Number value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return Short.valueOf(value.shortValue());
    }

    /* compiled from: Int16Array.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u001c\b\u0002\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\u000bJ\u001f\u0010\f\u001a\u00020\u00062\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u000e\"\u00020\t¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/dcloud/uts/Int16Array$Companion;", "", "()V", "BYTES_PER_ELEMENT", "", "from", "Lio/dcloud/uts/Int16Array;", "arrayLike", "", "", "mapFn", "Lkotlin/Function2;", "of", "items", "", "([Ljava/lang/Number;)Lio/dcloud/uts/Int16Array;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Int16Array of(Number... items) {
            Intrinsics.checkNotNullParameter(items, "items");
            Int16Array int16Array = new Int16Array(Integer.valueOf(items.length));
            ArrayList arrayList = new ArrayList(items.length);
            for (Number number : items) {
                TypedArray.putAuto$default(int16Array, null, Short.valueOf(number.shortValue()), 1, null);
                arrayList.add(Unit.INSTANCE);
            }
            return int16Array;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Int16Array from$default(Companion companion, Collection collection, Function2 function2, int i, java.lang.Object obj) {
            if ((i & 2) != 0) {
                function2 = null;
            }
            return companion.from(collection, function2);
        }

        public final Int16Array from(Collection<? extends Number> arrayLike, Function2<? super Number, ? super Number, ? extends Number> mapFn) {
            Intrinsics.checkNotNullParameter(arrayLike, "arrayLike");
            Int16Array int16Array = new Int16Array(Integer.valueOf(arrayLike.size()));
            int i = 0;
            for (Number number : arrayLike) {
                if (mapFn != null) {
                    TypedArray.putAuto$default(int16Array, null, Short.valueOf(mapFn.invoke(number, Integer.valueOf(i)).shortValue()), 1, null);
                } else {
                    TypedArray.putAuto$default(int16Array, null, Short.valueOf(number.shortValue()), 1, null);
                }
                i++;
            }
            return int16Array;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Int16Array(Number length) {
        super(length);
        Intrinsics.checkNotNullParameter(length, "length");
        this.BYTES_PER_ELEMENT = (Number) 2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Int16Array(Collection<? extends Number> array) {
        super(array);
        Intrinsics.checkNotNullParameter(array, "array");
        this.BYTES_PER_ELEMENT = (Number) 2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Int16Array(ArrayBuffer buffer) {
        super(buffer);
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.BYTES_PER_ELEMENT = (Number) 2;
    }

    public /* synthetic */ Int16Array(ArrayBuffer arrayBuffer, Number number, Number number2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(arrayBuffer, (i & 2) != 0 ? null : number, (i & 4) != 0 ? null : number2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Int16Array(ArrayBuffer buffer, Number number, Number number2) {
        super(buffer, number, number2);
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.BYTES_PER_ELEMENT = (Number) 2;
    }

    @Override // io.dcloud.uts.TypedArray
    public <T extends TypedArray> Int16Array filter(Function3<? super Number, ? super Number, ? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        UTSArray uTSArray = new UTSArray();
        int size = size();
        for (int i = 0; i < size; i++) {
            short sShortValue = getAuto(i).shortValue();
            Short shValueOf = Short.valueOf(sShortValue);
            Integer numValueOf = Integer.valueOf(i);
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type T of io.dcloud.uts.Int16Array.filter");
            if (predicate.invoke(shValueOf, numValueOf, this).booleanValue()) {
                uTSArray.add(Short.valueOf(sShortValue));
            }
        }
        return new Int16Array(uTSArray);
    }

    @Override // io.dcloud.uts.TypedArray
    public void putAuto(Integer index, Number value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (index != null) {
            getByteBuffer().putShort(index.intValue() * getBYTES_PER_ELEMENT().intValue(), value.shortValue());
        } else {
            getByteBuffer().putShort(value.shortValue());
        }
    }

    @Override // io.dcloud.uts.TypedArray
    public Number getAuto(int index) {
        return Short.valueOf(getByteBuffer().getShort(index * getBYTES_PER_ELEMENT().intValue()));
    }

    @Override // io.dcloud.uts.TypedArray
    public <T extends TypedArray> Int16Array map(Function3<? super Number, ? super Number, ? super T, ? extends Number> callbackfn) {
        Intrinsics.checkNotNullParameter(callbackfn, "callbackfn");
        int size = size();
        UTSArray uTSArray = new UTSArray();
        for (int i = 0; i < size; i++) {
            Number auto = getAuto(i);
            Integer numValueOf = Integer.valueOf(i);
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type T of io.dcloud.uts.Int16Array.map");
            uTSArray.add(Short.valueOf(callbackfn.invoke(auto, numValueOf, this).shortValue()));
        }
        return new Int16Array(uTSArray);
    }

    @Override // io.dcloud.uts.TypedArray
    public Int16Array slice(Number start, Number end) {
        if (start == null) {
            start = (Number) 0;
        }
        if (end == null) {
            end = getLength();
        }
        UTSArray uTSArray = new UTSArray();
        int sliceIndex = IndexKt.toSliceIndex(end, getLength().intValue());
        for (int sliceIndex2 = IndexKt.toSliceIndex(start, getLength().intValue()); sliceIndex2 < sliceIndex; sliceIndex2++) {
            uTSArray.add(Short.valueOf(getAuto(sliceIndex2).shortValue()));
        }
        return new Int16Array(uTSArray);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    @Override // io.dcloud.uts.TypedArray
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public io.dcloud.uts.Int16Array subarray(java.lang.Number r5, java.lang.Number r6) {
        /*
            r4 = this;
            r0 = 0
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            if (r5 == 0) goto L1a
            r2 = r1
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = io.dcloud.uts.NumberKt.compareTo(r5, r2)
            if (r2 >= 0) goto L18
            java.lang.Number r2 = r4.getLength()
            java.lang.Number r5 = io.dcloud.uts.NumberKt.plus(r2, r5)
        L18:
            if (r5 != 0) goto L1d
        L1a:
            r5 = r1
            java.lang.Number r5 = (java.lang.Number) r5
        L1d:
            if (r6 == 0) goto L31
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = io.dcloud.uts.NumberKt.compareTo(r6, r1)
            if (r1 >= 0) goto L2f
            java.lang.Number r1 = r4.getLength()
            java.lang.Number r6 = io.dcloud.uts.NumberKt.plus(r1, r6)
        L2f:
            if (r6 != 0) goto L35
        L31:
            java.lang.Number r6 = r4.getLength()
        L35:
            int r5 = r5.intValue()
            java.lang.Number r1 = r4.getLength()
            int r1 = r1.intValue()
            int r5 = kotlin.ranges.RangesKt.coerceIn(r5, r0, r1)
            int r6 = r6.intValue()
            java.lang.Number r1 = r4.getLength()
            int r1 = r1.intValue()
            int r6 = kotlin.ranges.RangesKt.coerceIn(r6, r5, r1)
            io.dcloud.uts.UTSArray r1 = new io.dcloud.uts.UTSArray
            r1.<init>()
        L5a:
            if (r5 >= r6) goto L73
            java.lang.Number r2 = r4.getAuto(r5)
            short r2 = r2.shortValue()
            java.lang.Short r2 = java.lang.Short.valueOf(r2)
            r3 = 1
            java.lang.Short[] r3 = new java.lang.Short[r3]
            r3[r0] = r2
            r1.push(r3)
            int r5 = r5 + 1
            goto L5a
        L73:
            io.dcloud.uts.Int16Array r5 = new io.dcloud.uts.Int16Array
            java.util.Collection r1 = (java.util.Collection) r1
            r5.<init>(r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.uts.Int16Array.subarray(java.lang.Number, java.lang.Number):io.dcloud.uts.Int16Array");
    }

    /* compiled from: Int16Array.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0004H\u0096\u0002J\t\u0010\u0005\u001a\u00020\u0002H\u0096\u0002¨\u0006\u0006"}, d2 = {"io/dcloud/uts/Int16Array$iterator$1", "", "", "hasNext", "", "next", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: io.dcloud.uts.Int16Array$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<Number>, KMappedMarker {
        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return Int16Array.this.getByteBuffer().remaining() > 0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Number next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return Short.valueOf(Int16Array.this.getByteBuffer().getShort());
        }
    }

    @Override // io.dcloud.uts.TypedArray, java.util.Collection, java.lang.Iterable
    public Iterator<Number> iterator() {
        getByteBuffer().rewind();
        return new AnonymousClass1();
    }
}
