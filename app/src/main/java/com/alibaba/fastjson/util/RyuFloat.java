package com.alibaba.fastjson.util;

import com.taobao.weex.el.parse.Operators;

/* loaded from: classes.dex */
public final class RyuFloat {
    private static final int[][] POW5_SPLIT = {new int[]{536870912, 0}, new int[]{671088640, 0}, new int[]{838860800, 0}, new int[]{1048576000, 0}, new int[]{655360000, 0}, new int[]{819200000, 0}, new int[]{1024000000, 0}, new int[]{640000000, 0}, new int[]{800000000, 0}, new int[]{1000000000, 0}, new int[]{625000000, 0}, new int[]{781250000, 0}, new int[]{976562500, 0}, new int[]{610351562, 1073741824}, new int[]{762939453, 268435456}, new int[]{953674316, 872415232}, new int[]{596046447, 1619001344}, new int[]{745058059, 1486880768}, new int[]{931322574, 1321730048}, new int[]{582076609, 289210368}, new int[]{727595761, 898383872}, new int[]{909494701, 1659850752}, new int[]{568434188, 1305842176}, new int[]{710542735, 1632302720}, new int[]{888178419, 1503507488}, new int[]{555111512, 671256724}, new int[]{693889390, 839070905}, new int[]{867361737, 2122580455}, new int[]{542101086, 521306416}, new int[]{677626357, 1725374844}, new int[]{847032947, 546105819}, new int[]{1058791184, 145761362}, new int[]{661744490, 91100851}, new int[]{827180612, 1187617888}, new int[]{1033975765, 1484522360}, new int[]{646234853, 1196261931}, new int[]{807793566, 2032198326}, new int[]{1009741958, 1466506084}, new int[]{631088724, 379695390}, new int[]{788860905, 474619238}, new int[]{986076131, 1130144959}, new int[]{616297582, 437905143}, new int[]{770371977, 1621123253}, new int[]{962964972, 415791331}, new int[]{601853107, 1333611405}, new int[]{752316384, 1130143345}, new int[]{940395480, 1412679181}};
    private static final int[][] POW5_INV_SPLIT = {new int[]{268435456, 1}, new int[]{214748364, 1717986919}, new int[]{171798691, 1803886265}, new int[]{137438953, 1013612282}, new int[]{219902325, 1192282922}, new int[]{175921860, 953826338}, new int[]{140737488, 763061070}, new int[]{225179981, 791400982}, new int[]{180143985, 203624056}, new int[]{144115188, 162899245}, new int[]{230584300, 1978625710}, new int[]{184467440, 1582900568}, new int[]{147573952, 1266320455}, new int[]{236118324, 308125809}, new int[]{188894659, 675997377}, new int[]{151115727, 970294631}, new int[]{241785163, 1981968139}, new int[]{193428131, 297084323}, new int[]{154742504, 1955654377}, new int[]{247588007, 1840556814}, new int[]{198070406, 613451992}, new int[]{158456325, 61264864}, new int[]{253530120, 98023782}, new int[]{202824096, 78419026}, new int[]{162259276, 1780722139}, new int[]{259614842, 1990161963}, new int[]{207691874, 733136111}, new int[]{166153499, 1016005619}, new int[]{265845599, 337118801}, new int[]{212676479, 699191770}, new int[]{170141183, 988850146}};

    public static String toString(float f) {
        char[] cArr = new char[15];
        return new String(cArr, 0, toString(f, cArr, 0));
    }

    public static int toString(float f, char[] cArr, int i) {
        int i2;
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        if (!Float.isNaN(f)) {
            if (f == Float.POSITIVE_INFINITY) {
                cArr[i] = 'I';
                cArr[i + 1] = 'n';
                cArr[i + 2] = 'f';
                cArr[i + 3] = 'i';
                cArr[i + 4] = 'n';
                cArr[i + 5] = 'i';
                cArr[i + 6] = 't';
                i16 = i + 8;
                cArr[i + 7] = 'y';
            } else if (f == Float.NEGATIVE_INFINITY) {
                cArr[i] = '-';
                cArr[i + 1] = 'I';
                cArr[i + 2] = 'n';
                cArr[i + 3] = 'f';
                cArr[i + 4] = 'i';
                cArr[i + 5] = 'n';
                cArr[i + 6] = 'i';
                cArr[i + 7] = 't';
                i17 = i + 9;
                cArr[i + 8] = 'y';
            } else {
                int iFloatToIntBits = Float.floatToIntBits(f);
                if (iFloatToIntBits == 0) {
                    cArr[i] = '0';
                    cArr[i + 1] = Operators.DOT;
                    i17 = i + 3;
                    cArr[i + 2] = '0';
                } else if (iFloatToIntBits == Integer.MIN_VALUE) {
                    cArr[i] = '-';
                    cArr[i + 1] = '0';
                    cArr[i + 2] = Operators.DOT;
                    i16 = i + 4;
                    cArr[i + 3] = '0';
                } else {
                    int i18 = (iFloatToIntBits >> 23) & 255;
                    int i19 = 8388607 & iFloatToIntBits;
                    if (i18 == 0) {
                        i2 = -149;
                    } else {
                        i2 = i18 - 150;
                        i19 |= 8388608;
                    }
                    boolean z2 = iFloatToIntBits < 0;
                    boolean z3 = (i19 & 1) == 0;
                    int i20 = i19 * 4;
                    int i21 = i20 + 2;
                    int i22 = i20 - ((((long) i19) != 8388608 || i18 <= 1) ? 2 : 1);
                    int i23 = i2 - 2;
                    if (i23 >= 0) {
                        i9 = (int) ((i23 * 3010299) / 10000000);
                        int i24 = i9 == 0 ? 1 : (int) (((i9 * 23219280) + 9999999) / 10000000);
                        int i25 = (-i23) + i9;
                        int[][] iArr = POW5_INV_SPLIT;
                        int[] iArr2 = iArr[i9];
                        long j = iArr2[0];
                        long j2 = iArr2[1];
                        long j3 = i20;
                        int i26 = ((i24 + 58) + i25) - 31;
                        i3 = (int) (((j3 * j) + ((j3 * j2) >> 31)) >> i26);
                        long j4 = i21;
                        i12 = (int) (((j4 * j) + ((j4 * j2) >> 31)) >> i26);
                        z = z3;
                        int i27 = i22;
                        long j5 = i27;
                        i4 = (int) (((j * j5) + ((j5 * j2) >> 31)) >> i26);
                        if (i9 == 0 || (i12 - 1) / 10 > i4 / 10) {
                            i8 = 0;
                        } else {
                            int i28 = i9 - 1;
                            int i29 = (i25 - 1) + (i28 == 0 ? 1 : (int) (((i28 * 23219280) + 9999999) / 10000000)) + 58;
                            int[] iArr3 = iArr[i28];
                            i8 = (int) ((((iArr3[0] * j3) + ((j3 * iArr3[1]) >> 31)) >> (i29 - 31)) % 10);
                        }
                        int i30 = 0;
                        while (i21 > 0 && i21 % 5 == 0) {
                            i21 /= 5;
                            i30++;
                        }
                        int i31 = i20;
                        int i32 = 0;
                        while (i31 > 0 && i31 % 5 == 0) {
                            i31 /= 5;
                            i32++;
                        }
                        int i33 = 0;
                        while (i27 > 0 && i27 % 5 == 0) {
                            i27 /= 5;
                            i33++;
                        }
                        i10 = i30 >= i9 ? 1 : 0;
                        int i34 = i32 >= i9 ? 1 : 0;
                        i11 = i33 >= i9 ? 1 : 0;
                        i7 = i34;
                        i5 = 0;
                    } else {
                        z = z3;
                        int i35 = -i23;
                        int i36 = (int) ((i35 * 6989700) / 10000000);
                        int i37 = i35 - i36;
                        int i38 = i37 == 0 ? 1 : (int) (((i37 * 23219280) + 9999999) / 10000000);
                        int[][] iArr4 = POW5_SPLIT;
                        int[] iArr5 = iArr4[i37];
                        long j6 = iArr5[0];
                        long j7 = iArr5[1];
                        int i39 = (i36 - (i38 - 61)) - 31;
                        long j8 = i20;
                        long j9 = i21;
                        int i40 = (int) (((j9 * j6) + ((j9 * j7) >> 31)) >> i39);
                        i3 = (int) (((j8 * j6) + ((j8 * j7) >> 31)) >> i39);
                        long j10 = i22;
                        i4 = (int) (((j6 * j10) + ((j10 * j7) >> 31)) >> i39);
                        if (i36 == 0 || (i40 - 1) / 10 > i4 / 10) {
                            i5 = 0;
                            i6 = 0;
                        } else {
                            int i41 = i37 + 1;
                            int i42 = (i36 - 1) - ((i41 == 0 ? 1 : (int) (((i41 * 23219280) + 9999999) / 10000000)) - 61);
                            int[] iArr6 = iArr4[i41];
                            i5 = 0;
                            i6 = (int) ((((iArr6[0] * j8) + ((j8 * iArr6[1]) >> 31)) >> (i42 - 31)) % 10);
                        }
                        int i43 = i23 + i36;
                        int i44 = 1 >= i36 ? 1 : i5;
                        i7 = (i36 >= 23 || (i20 & ((1 << (i36 + (-1))) - 1)) != 0) ? i5 : 1;
                        int i45 = (i22 % 2 == 1 ? i5 : 1) >= i36 ? 1 : i5;
                        i8 = i6;
                        i9 = i43;
                        i10 = i44;
                        i11 = i45;
                        i12 = i40;
                    }
                    int i46 = 1000000000;
                    int i47 = 10;
                    while (i47 > 0 && i12 < i46) {
                        i46 /= 10;
                        i47--;
                    }
                    int i48 = i9 + i47;
                    int i49 = i48 - 1;
                    int i50 = (i49 < -3 || i49 >= 7) ? 1 : i5;
                    if (i10 != 0 && !z) {
                        i12--;
                    }
                    int i51 = i5;
                    while (true) {
                        int i52 = i12 / 10;
                        int i53 = i4 / 10;
                        if (i52 <= i53 || (i12 < 100 && i50 != 0)) {
                            break;
                        }
                        i11 &= i4 % 10 == 0 ? 1 : i5;
                        i8 = i3 % 10;
                        i3 /= 10;
                        i51++;
                        i12 = i52;
                        i4 = i53;
                    }
                    if (i11 != 0 && z) {
                        while (i4 % 10 == 0 && (i12 >= 100 || i50 == 0)) {
                            i12 /= 10;
                            i8 = i3 % 10;
                            i3 /= 10;
                            i4 /= 10;
                            i51++;
                        }
                    }
                    int i54 = i3;
                    if (i7 != 0 && i8 == 5 && i54 % 2 == 0) {
                        i8 = 4;
                    }
                    int i55 = (((i54 != i4 || (i11 != 0 && z)) && i8 < 5) ? i5 : 1) + i54;
                    int i56 = i47 - i51;
                    if (z2) {
                        i13 = i + 1;
                        cArr[i] = '-';
                    } else {
                        i13 = i;
                    }
                    if (i50 != 0) {
                        while (i5 < i56 - 1) {
                            int i57 = i55 % 10;
                            i55 /= 10;
                            cArr[(i13 + i56) - i5] = (char) (i57 + 48);
                            i5++;
                        }
                        cArr[i13] = (char) ((i55 % 10) + 48);
                        cArr[i13 + 1] = Operators.DOT;
                        int i58 = i13 + i56 + 1;
                        if (i56 == 1) {
                            cArr[i58] = '0';
                            i58++;
                        }
                        int i59 = i58 + 1;
                        cArr[i58] = 'E';
                        if (i49 < 0) {
                            cArr[i59] = '-';
                            i49 = -i49;
                            i59 = i58 + 2;
                        }
                        if (i49 >= 10) {
                            i15 = 48;
                            cArr[i59] = (char) ((i49 / 10) + 48);
                            i59++;
                        } else {
                            i15 = 48;
                        }
                        i14 = i59 + 1;
                        cArr[i59] = (char) ((i49 % 10) + i15);
                    } else {
                        int i60 = 48;
                        if (i49 < 0) {
                            int i61 = i13 + 1;
                            cArr[i13] = '0';
                            int i62 = i13 + 2;
                            cArr[i61] = Operators.DOT;
                            int i63 = -1;
                            while (i63 > i49) {
                                cArr[i62] = '0';
                                i63--;
                                i62++;
                            }
                            int i64 = i62;
                            int i65 = i5;
                            while (i65 < i56) {
                                cArr[((i62 + i56) - i65) - 1] = (char) ((i55 % 10) + i60);
                                i55 /= 10;
                                i64++;
                                i65++;
                                i60 = 48;
                            }
                            i14 = i64;
                        } else if (i48 >= i56) {
                            for (int i66 = i5; i66 < i56; i66++) {
                                cArr[((i13 + i56) - i66) - 1] = (char) ((i55 % 10) + 48);
                                i55 /= 10;
                            }
                            int i67 = i13 + i56;
                            while (i56 < i48) {
                                cArr[i67] = '0';
                                i56++;
                                i67++;
                            }
                            int i68 = i67 + 1;
                            cArr[i67] = Operators.DOT;
                            i14 = i67 + 2;
                            cArr[i68] = '0';
                        } else {
                            int i69 = i13 + 1;
                            while (i5 < i56) {
                                if ((i56 - i5) - 1 == i49) {
                                    cArr[((i69 + i56) - i5) - 1] = Operators.DOT;
                                    i69--;
                                }
                                cArr[((i69 + i56) - i5) - 1] = (char) ((i55 % 10) + 48);
                                i55 /= 10;
                                i5++;
                            }
                            i14 = i13 + i56 + 1;
                        }
                    }
                    return i14 - i;
                }
            }
            return i16 - i;
        }
        cArr[i] = 'N';
        cArr[i + 1] = 'a';
        i17 = i + 3;
        cArr[i + 2] = 'N';
        return i17 - i;
    }
}
