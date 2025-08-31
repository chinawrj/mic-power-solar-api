package com.alibaba.fastjson.util;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Array;
import java.math.BigInteger;

/* loaded from: classes.dex */
public final class RyuDouble {
    private static final int[][] POW5_SPLIT = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 326, 4);
    private static final int[][] POW5_INV_SPLIT = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 291, 4);

    static {
        BigInteger bigIntegerSubtract = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        BigInteger bigIntegerSubtract2 = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        int i = 0;
        while (i < 326) {
            BigInteger bigIntegerPow = BigInteger.valueOf(5L).pow(i);
            int iBitLength = bigIntegerPow.bitLength();
            int i2 = i == 0 ? 1 : (int) (((i * 23219280) + 9999999) / 10000000);
            if (i2 != iBitLength) {
                throw new IllegalStateException(iBitLength + " != " + i2);
            }
            if (i < POW5_SPLIT.length) {
                for (int i3 = 0; i3 < 4; i3++) {
                    POW5_SPLIT[i][i3] = bigIntegerPow.shiftRight((iBitLength - 121) + ((3 - i3) * 31)).and(bigIntegerSubtract).intValue();
                }
            }
            if (i < POW5_INV_SPLIT.length) {
                BigInteger bigIntegerAdd = BigInteger.ONE.shiftLeft(iBitLength + 121).divide(bigIntegerPow).add(BigInteger.ONE);
                for (int i4 = 0; i4 < 4; i4++) {
                    if (i4 == 0) {
                        POW5_INV_SPLIT[i][i4] = bigIntegerAdd.shiftRight((3 - i4) * 31).intValue();
                    } else {
                        POW5_INV_SPLIT[i][i4] = bigIntegerAdd.shiftRight((3 - i4) * 31).and(bigIntegerSubtract2).intValue();
                    }
                }
            }
            i++;
        }
    }

    public static String toString(double d) {
        char[] cArr = new char[24];
        return new String(cArr, 0, toString(d, cArr, 0));
    }

    public static int toString(double d, char[] cArr, int i) {
        int i2;
        boolean z;
        boolean z2;
        long j;
        long j2;
        int i3;
        long j3;
        boolean z3;
        boolean z4;
        long j4;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z5;
        int i9;
        int i10;
        int i11;
        int i12;
        if (Double.isNaN(d)) {
            cArr[i] = 'N';
            cArr[i + 1] = 'a';
            i12 = i + 3;
            cArr[i + 2] = 'N';
        } else {
            if (d == Double.POSITIVE_INFINITY) {
                cArr[i] = 'I';
                cArr[i + 1] = 'n';
                cArr[i + 2] = 'f';
                cArr[i + 3] = 'i';
                cArr[i + 4] = 'n';
                cArr[i + 5] = 'i';
                cArr[i + 6] = 't';
                cArr[i + 7] = 'y';
                return (i + 8) - i;
            }
            if (d == Double.NEGATIVE_INFINITY) {
                cArr[i] = '-';
                cArr[i + 1] = 'I';
                cArr[i + 2] = 'n';
                cArr[i + 3] = 'f';
                cArr[i + 4] = 'i';
                cArr[i + 5] = 'n';
                cArr[i + 6] = 'i';
                cArr[i + 7] = 't';
                i12 = i + 9;
                cArr[i + 8] = 'y';
            } else {
                long jDoubleToLongBits = Double.doubleToLongBits(d);
                if (jDoubleToLongBits != 0) {
                    if (jDoubleToLongBits == Long.MIN_VALUE) {
                        cArr[i] = '-';
                        cArr[i + 1] = '0';
                        cArr[i + 2] = Operators.DOT;
                        i8 = i + 4;
                        cArr[i + 3] = '0';
                    } else {
                        int i13 = (int) ((jDoubleToLongBits >>> 52) & 2047);
                        long j5 = jDoubleToLongBits & 4503599627370495L;
                        if (i13 == 0) {
                            i2 = -1074;
                        } else {
                            i2 = i13 - 1075;
                            j5 |= 4503599627370496L;
                        }
                        boolean z6 = jDoubleToLongBits < 0;
                        boolean z7 = (j5 & 1) == 0;
                        long j6 = 4 * j5;
                        long j7 = j6 + 2;
                        int i14 = (j5 != 4503599627370496L || i13 <= 1) ? 1 : 0;
                        long j8 = (j6 - 1) - i14;
                        int i15 = i2 - 2;
                        int i16 = 3;
                        if (i15 >= 0) {
                            int iMax = Math.max(0, ((int) ((i15 * 3010299) / 10000000)) - 1);
                            int i17 = (((-i15) + iMax) + ((iMax == 0 ? 1 : (int) (((iMax * 23219280) + 9999999) / 10000000)) + 121)) - 114;
                            if (i17 < 0) {
                                throw new IllegalArgumentException("" + i17);
                            }
                            int[] iArr = POW5_INV_SPLIT[iMax];
                            long j9 = j6 >>> 31;
                            long j10 = j6 & 2147483647L;
                            int i18 = iArr[0];
                            int i19 = iArr[1];
                            z = z6;
                            int i20 = iArr[2];
                            z2 = z7;
                            int i21 = iArr[3];
                            long j11 = ((((((((((((j10 * i21) >>> 31) + (i20 * j10)) + (j9 * i21)) >>> 31) + (i19 * j10)) + (i20 * j9)) >>> 31) + (i18 * j10)) + (i19 * j9)) >>> 21) + ((i18 * j9) << 10)) >>> i17;
                            long j12 = j7 >>> 31;
                            long j13 = j7 & 2147483647L;
                            long j14 = ((((((((((((j13 * i21) >>> 31) + (i20 * j13)) + (j12 * i21)) >>> 31) + (i19 * j13)) + (i20 * j12)) >>> 31) + (i18 * j13)) + (i19 * j12)) >>> 21) + ((i18 * j12) << 10)) >>> i17;
                            long j15 = j8 >>> 31;
                            long j16 = j8 & 2147483647L;
                            j3 = j14;
                            j2 = ((((((((((((j16 * i21) >>> 31) + (i20 * j16)) + (j15 * i21)) >>> 31) + (i19 * j16)) + (i20 * j15)) >>> 31) + (i18 * j16)) + (i19 * j15)) >>> 21) + ((i18 * j15) << 10)) >>> i17;
                            if (iMax <= 21) {
                                long j17 = j6 % 5;
                                if (j17 == 0) {
                                    if (j17 != 0) {
                                        i11 = 0;
                                    } else if (j6 % 25 != 0) {
                                        i11 = 1;
                                    } else if (j6 % 125 != 0) {
                                        i11 = 2;
                                    } else if (j6 % 625 != 0) {
                                        i11 = 3;
                                    } else {
                                        long j18 = j6 / 625;
                                        i11 = 4;
                                        for (long j19 = 0; j18 > j19 && j18 % 5 == j19; j19 = 0) {
                                            j18 /= 5;
                                            i11++;
                                        }
                                    }
                                    z4 = i11 >= iMax;
                                    z5 = false;
                                    j = j11;
                                    z3 = z5;
                                    i3 = iMax;
                                } else {
                                    if (z2) {
                                        if (j8 % 5 != 0) {
                                            i10 = 0;
                                        } else if (j8 % 25 != 0) {
                                            i10 = 1;
                                        } else if (j8 % 125 != 0) {
                                            i10 = 2;
                                        } else if (j8 % 625 != 0) {
                                            i10 = 3;
                                        } else {
                                            long j20 = j8 / 625;
                                            i10 = 4;
                                            for (long j21 = 0; j20 > j21 && j20 % 5 == j21; j21 = 0) {
                                                j20 /= 5;
                                                i10++;
                                            }
                                        }
                                        z5 = i10 >= iMax;
                                        z4 = false;
                                        j = j11;
                                        z3 = z5;
                                        i3 = iMax;
                                    } else {
                                        if (j7 % 5 != 0) {
                                            i9 = 0;
                                        } else if (j7 % 25 != 0) {
                                            i9 = 1;
                                        } else if (j7 % 125 != 0) {
                                            i9 = 2;
                                        } else if (j7 % 625 != 0) {
                                            i9 = 3;
                                        } else {
                                            long j22 = j7 / 625;
                                            i9 = 4;
                                            for (long j23 = 0; j22 > j23 && j22 % 5 == j23; j23 = 0) {
                                                j22 /= 5;
                                                i9++;
                                            }
                                        }
                                        if (i9 >= iMax) {
                                            j3--;
                                        }
                                    }
                                    z4 = false;
                                    j = j11;
                                    z3 = z5;
                                    i3 = iMax;
                                }
                            } else {
                                z4 = false;
                                j = j11;
                                z3 = z5;
                                i3 = iMax;
                            }
                        } else {
                            z = z6;
                            z2 = z7;
                            int i22 = -i15;
                            int iMax2 = Math.max(0, ((int) ((i22 * 6989700) / 10000000)) - 1);
                            int i23 = i22 - iMax2;
                            int i24 = (iMax2 - ((i23 == 0 ? 1 : (int) (((i23 * 23219280) + 9999999) / 10000000)) - 121)) - 114;
                            if (i24 < 0) {
                                throw new IllegalArgumentException("" + i24);
                            }
                            int[] iArr2 = POW5_SPLIT[i23];
                            long j24 = j6 >>> 31;
                            long j25 = j6 & 2147483647L;
                            int i25 = iArr2[0];
                            int i26 = iArr2[1];
                            int i27 = i14;
                            int i28 = iArr2[2];
                            int i29 = iArr2[3];
                            long j26 = ((((((((((((j25 * i29) >>> 31) + (i28 * j25)) + (j24 * i29)) >>> 31) + (i26 * j25)) + (i28 * j24)) >>> 31) + (i25 * j25)) + (i26 * j24)) >>> 21) + ((i25 * j24) << 10)) >>> i24;
                            long j27 = j7 >>> 31;
                            long j28 = j7 & 2147483647L;
                            j = j26;
                            long j29 = ((((((((((((i29 * j28) >>> 31) + (i28 * j28)) + (j27 * i29)) >>> 31) + (i26 * j28)) + (i28 * j27)) >>> 31) + (i25 * j28)) + (i26 * j27)) >>> 21) + ((i25 * j27) << 10)) >>> i24;
                            long j30 = j8 >>> 31;
                            long j31 = j8 & 2147483647L;
                            j2 = ((((((((((((i29 * j31) >>> 31) + (i28 * j31)) + (j30 * i29)) >>> 31) + (i26 * j31)) + (i28 * j30)) >>> 31) + (i25 * j31)) + (i26 * j30)) >>> 21) + ((i25 * j30) << 10)) >>> i24;
                            i3 = iMax2 + i15;
                            if (iMax2 <= 1) {
                                if (z2) {
                                    z4 = true;
                                    j3 = j29;
                                    z3 = i27 == 1;
                                } else {
                                    j3 = j29 - 1;
                                    z4 = true;
                                    z3 = false;
                                }
                            } else if (iMax2 < 63) {
                                z4 = (j6 & ((1 << (iMax2 - 1)) - 1)) == 0;
                                j3 = j29;
                                z3 = false;
                            } else {
                                j3 = j29;
                                z3 = false;
                                z4 = false;
                            }
                        }
                        if (j3 >= 1000000000000000000L) {
                            i16 = 19;
                        } else if (j3 >= 100000000000000000L) {
                            i16 = 18;
                        } else if (j3 >= 10000000000000000L) {
                            i16 = 17;
                        } else if (j3 >= 1000000000000000L) {
                            i16 = 16;
                        } else if (j3 >= 100000000000000L) {
                            i16 = 15;
                        } else if (j3 >= 10000000000000L) {
                            i16 = 14;
                        } else if (j3 >= 1000000000000L) {
                            i16 = 13;
                        } else if (j3 >= 100000000000L) {
                            i16 = 12;
                        } else if (j3 >= 10000000000L) {
                            i16 = 11;
                        } else if (j3 >= 1000000000) {
                            i16 = 10;
                        } else if (j3 >= 100000000) {
                            i16 = 9;
                        } else if (j3 >= 10000000) {
                            i16 = 8;
                        } else if (j3 >= 1000000) {
                            i16 = 7;
                        } else if (j3 >= 100000) {
                            i16 = 6;
                        } else if (j3 >= 10000) {
                            i16 = 5;
                        } else if (j3 >= 1000) {
                            i16 = 4;
                        } else if (j3 < 100) {
                            i16 = j3 >= 10 ? 2 : 1;
                        }
                        int i30 = i3 + i16;
                        int i31 = i30 - 1;
                        boolean z8 = i31 < -3 || i31 >= 7;
                        if (z3 || z4) {
                            int i32 = 0;
                            int i33 = 0;
                            while (true) {
                                long j32 = j3 / 10;
                                long j33 = j2 / 10;
                                if (j32 <= j33 || (j3 < 100 && z8)) {
                                    break;
                                }
                                z3 &= j2 % 10 == 0;
                                z4 &= i32 == 0;
                                i32 = (int) (j % 10);
                                j /= 10;
                                i33++;
                                j3 = j32;
                                j2 = j33;
                            }
                            if (z3 && z2) {
                                while (j2 % 10 == 0 && (j3 >= 100 || !z8)) {
                                    z4 &= i32 == 0;
                                    i32 = (int) (j % 10);
                                    j3 /= 10;
                                    j /= 10;
                                    j2 /= 10;
                                    i33++;
                                }
                            }
                            if (z4 && i32 == 5 && j % 2 == 0) {
                                i32 = 4;
                            }
                            j4 = j + (((j != j2 || (z3 && z2)) && i32 < 5) ? 0 : 1);
                            i4 = i33;
                        } else {
                            int i34 = 0;
                            i4 = 0;
                            while (true) {
                                long j34 = j3 / 10;
                                long j35 = j2 / 10;
                                if (j34 <= j35 || (j3 < 100 && z8)) {
                                    break;
                                }
                                i34 = (int) (j % 10);
                                j /= 10;
                                i4++;
                                j3 = j34;
                                j2 = j35;
                            }
                            j4 = j + ((j == j2 || i34 >= 5) ? 1 : 0);
                        }
                        int i35 = i16 - i4;
                        if (z) {
                            i5 = i + 1;
                            cArr[i] = '-';
                        } else {
                            i5 = i;
                        }
                        if (!z8) {
                            char c = '0';
                            if (i31 < 0) {
                                int i36 = i5 + 1;
                                cArr[i5] = '0';
                                int i37 = i5 + 2;
                                cArr[i36] = Operators.DOT;
                                int i38 = -1;
                                while (i38 > i31) {
                                    cArr[i37] = c;
                                    i38--;
                                    i37++;
                                    c = '0';
                                }
                                i6 = i37;
                                for (int i39 = 0; i39 < i35; i39++) {
                                    cArr[((i37 + i35) - i39) - 1] = (char) ((j4 % 10) + 48);
                                    j4 /= 10;
                                    i6++;
                                }
                            } else if (i30 >= i35) {
                                for (int i40 = 0; i40 < i35; i40++) {
                                    cArr[((i5 + i35) - i40) - 1] = (char) ((j4 % 10) + 48);
                                    j4 /= 10;
                                }
                                int i41 = i5 + i35;
                                while (i35 < i30) {
                                    cArr[i41] = '0';
                                    i35++;
                                    i41++;
                                }
                                cArr[i41] = Operators.DOT;
                                i6 = i41 + 2;
                                cArr[i41 + 1] = '0';
                            } else {
                                int i42 = i5 + 1;
                                for (int i43 = 0; i43 < i35; i43++) {
                                    if ((i35 - i43) - 1 == i31) {
                                        cArr[((i42 + i35) - i43) - 1] = Operators.DOT;
                                        i42--;
                                    }
                                    cArr[((i42 + i35) - i43) - 1] = (char) ((j4 % 10) + 48);
                                    j4 /= 10;
                                }
                                i6 = i5 + i35 + 1;
                            }
                            return i6 - i;
                        }
                        for (int i44 = 0; i44 < i35 - 1; i44++) {
                            int i45 = (int) (j4 % 10);
                            j4 /= 10;
                            cArr[(i5 + i35) - i44] = (char) (i45 + 48);
                        }
                        cArr[i5] = (char) ((j4 % 10) + 48);
                        cArr[i5 + 1] = Operators.DOT;
                        int i46 = i5 + i35 + 1;
                        if (i35 == 1) {
                            cArr[i46] = '0';
                            i46++;
                        }
                        int i47 = i46 + 1;
                        cArr[i46] = 'E';
                        if (i31 < 0) {
                            cArr[i47] = '-';
                            i31 = -i31;
                            i47 = i46 + 2;
                        }
                        if (i31 >= 100) {
                            int i48 = i47 + 1;
                            i7 = 48;
                            cArr[i47] = (char) ((i31 / 100) + 48);
                            i31 %= 100;
                            i47 += 2;
                            cArr[i48] = (char) ((i31 / 10) + 48);
                        } else {
                            i7 = 48;
                            if (i31 >= 10) {
                                cArr[i47] = (char) ((i31 / 10) + 48);
                                i47++;
                            }
                        }
                        i8 = i47 + 1;
                        cArr[i47] = (char) ((i31 % 10) + i7);
                    }
                    return i8 - i;
                }
                cArr[i] = '0';
                cArr[i + 1] = Operators.DOT;
                i12 = i + 3;
                cArr[i + 2] = '0';
            }
        }
        return i12 - i;
    }
}
