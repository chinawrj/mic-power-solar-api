package kotlin.internal;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.ULong;

/* compiled from: UProgressionUtil.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m1544differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int iM$4 = UByte$$ExternalSyntheticBackport0.m$4(i, i3);
        int iM$42 = UByte$$ExternalSyntheticBackport0.m$4(i2, i3);
        int iCompare = Integer.compare(iM$4 ^ Integer.MIN_VALUE, iM$42 ^ Integer.MIN_VALUE);
        int iM422constructorimpl = UInt.m422constructorimpl(iM$4 - iM$42);
        return iCompare >= 0 ? iM422constructorimpl : UInt.m422constructorimpl(iM422constructorimpl + i3);
    }

    /* renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m1545differenceModulosambcqE(long j, long j2, long j3) {
        long jM$3 = UByte$$ExternalSyntheticBackport0.m$3(j, j3);
        long jM$32 = UByte$$ExternalSyntheticBackport0.m$3(j2, j3);
        int iCompare = Long.compare(jM$3 ^ Long.MIN_VALUE, jM$32 ^ Long.MIN_VALUE);
        long jM501constructorimpl = ULong.m501constructorimpl(jM$3 - jM$32);
        return iCompare >= 0 ? jM501constructorimpl : ULong.m501constructorimpl(jM501constructorimpl + j3);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m1547getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) >= 0 ? i2 : UInt.m422constructorimpl(i2 - m1544differenceModuloWZ9TVnA(i2, i, UInt.m422constructorimpl(i3)));
        }
        if (i3 < 0) {
            return Integer.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE) <= 0 ? i2 : UInt.m422constructorimpl(i2 + m1544differenceModuloWZ9TVnA(i, i2, UInt.m422constructorimpl(-i3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    /* renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m1546getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        if (j3 > 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) >= 0 ? j2 : ULong.m501constructorimpl(j2 - m1545differenceModulosambcqE(j2, j, ULong.m501constructorimpl(j3)));
        }
        if (j3 < 0) {
            return Long.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE) <= 0 ? j2 : ULong.m501constructorimpl(j2 + m1545differenceModulosambcqE(j, j2, ULong.m501constructorimpl(-j3)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
