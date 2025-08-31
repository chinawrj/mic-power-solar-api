package kotlin;

import java.lang.reflect.Method;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes3.dex */
public final /* synthetic */ class UByte$$ExternalSyntheticBackport0 {
    public static /* synthetic */ int m(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public static /* synthetic */ int m(int i, int i2) {
        long j = i + i2;
        int i3 = (int) j;
        if (j == i3) {
            return i3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ int m(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static /* synthetic */ int m(Method method) {
        return method.getParameterTypes().length;
    }

    public static /* synthetic */ int m(boolean z) {
        return z ? 1231 : 1237;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ long m395m(long j, long j2) {
        long j3 = j + j2;
        if (((j2 ^ j) < 0) || ((j ^ j3) >= 0)) {
            return j3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ boolean m(Object obj) {
        return obj == null;
    }

    public static /* synthetic */ int m$1(int i, int i2) {
        long j = i * i2;
        int i3 = (int) j;
        if (j == i3) {
            return i3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ long m$1(long j, long j2) {
        int iNumberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2);
        if (iNumberOfLeadingZeros > 65) {
            return j * j2;
        }
        if (iNumberOfLeadingZeros >= 64) {
            if ((j2 != Long.MIN_VALUE) | (j >= 0)) {
                long j3 = j * j2;
                if (j == 0 || j3 / j == j2) {
                    return j3;
                }
            }
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ int m$2(int i, int i2) {
        long j = i - i2;
        int i3 = (int) j;
        if (j == i3) {
            return i3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ long m$2(long j, long j2) {
        long j3 = j - j2;
        if (((j2 ^ j) >= 0) || ((j ^ j3) >= 0)) {
            return j3;
        }
        throw new ArithmeticException();
    }

    public static /* synthetic */ int m$3(int i, int i2) {
        return (int) ((i & InternalZipConstants.ZIP_64_SIZE_LIMIT) / (i2 & InternalZipConstants.ZIP_64_SIZE_LIMIT));
    }

    public static /* synthetic */ long m$3(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if ((j3 ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE)) {
            j2 = 0;
        }
        return j3 - j2;
    }

    public static /* synthetic */ int m$4(int i, int i2) {
        return (int) ((i & InternalZipConstants.ZIP_64_SIZE_LIMIT) % (i2 & InternalZipConstants.ZIP_64_SIZE_LIMIT));
    }

    public static /* synthetic */ long m$4(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0L : 1L;
        }
        if (j >= 0) {
            return j / j2;
        }
        long j3 = ((j >>> 1) / j2) << 1;
        return j3 + (((j - (j3 * j2)) ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0 : 1);
    }
}
