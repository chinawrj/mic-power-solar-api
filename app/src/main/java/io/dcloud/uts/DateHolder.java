package io.dcloud.uts;

import com.facebook.common.statfs.StatFsHelper;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXRequest;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Date2.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\tH\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tJ \u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0006H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\tH\u0002J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002J/\u0010\u001a\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u001c\"\u00020\u000b¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lio/dcloud/uts/DateHolder;", "", "()V", "monthDays", "", "daysFromYear", "", Constants.Name.Y, "daysInYear", "", "getDateField", "", "dateDouble", "", "magic", "getDateFields", "", "isLocal", "", AbsoluteConst.INSTALL_OPTIONS_FORCE, "getTimezoneOffset", "timestamp", "isLeapYear", "mathMod", "a", "b", "setDateField", "args", "", "(DI[Ljava/lang/Number;)D", "setDateFields", "fields", "yearFromDays", "days", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DateHolder {
    public static final DateHolder INSTANCE = new DateHolder();
    private static final int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private final double mathMod(double a, double b) {
        return ((a % b) + b) % b;
    }

    private DateHolder() {
    }

    public final Number getDateField(double dateDouble, int magic) {
        boolean z = (magic & 15) != 0;
        int i = (magic >> 4) & 15;
        boolean z2 = (magic & 256) != 0;
        double[] dateFields = getDateFields(dateDouble, z, false);
        if (dateFields == null) {
            return Double.valueOf(Double.NaN);
        }
        if (z2 && i == 0) {
            return Double.valueOf(dateFields[0] - 1900);
        }
        return Long.valueOf((long) dateFields[i]);
    }

    public final double[] getDateFields(double dateDouble, boolean isLocal, boolean force) {
        double d;
        int i;
        double d2 = dateDouble;
        double[] dArr = new double[9];
        double d3 = 0.0d;
        if (Double.isNaN(dateDouble)) {
            if (!force) {
                return null;
            }
            for (int i2 = 0; i2 < 9; i2++) {
                dArr[i2] = 0.0d;
            }
            return dArr;
        }
        if (isLocal) {
            d3 = -getTimezoneOffset((long) d2);
            d2 += 60000.0d * d3;
        }
        double dMathMod = mathMod(d2, 8.64E7d);
        long j = (long) ((d2 - dMathMod) / 8.64E7d);
        double d4 = dMathMod % 1000.0d;
        double d5 = (dMathMod - d4) / 1000.0d;
        double d6 = d5 % 60.0d;
        double d7 = (d5 - d6) / 60.0d;
        double d8 = d7 % 60.0d;
        double d9 = (d7 - d8) / 60.0d;
        double d10 = d3;
        double dMathMod2 = mathMod(j + 4.0d, 7.0d);
        int iYearFromDays = yearFromDays(j);
        long jDaysFromYear = j - daysFromYear(iYearFromDays);
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= 12) {
                d = dMathMod2;
                i = i4;
                break;
            }
            d = dMathMod2;
            double dDaysInYear = monthDays[i3];
            if (i3 == 1) {
                i = i4;
                dDaysInYear += daysInYear(iYearFromDays) - 365;
            } else {
                i = i4;
            }
            if (jDaysFromYear < dDaysInYear) {
                break;
            }
            jDaysFromYear -= (long) dDaysInYear;
            i4 = i + 1;
            i3++;
            dMathMod2 = d;
        }
        dArr[0] = iYearFromDays;
        dArr[1] = i;
        dArr[2] = jDaysFromYear + 1;
        dArr[3] = d9;
        dArr[4] = d8;
        dArr[5] = d6;
        dArr[6] = d4;
        dArr[7] = d;
        dArr[8] = d10;
        return dArr;
    }

    private final int yearFromDays(long days) {
        int i;
        if (days >= 0) {
            i = 1970;
            while (true) {
                int i2 = i + 1;
                if (days < daysFromYear(i2)) {
                    break;
                }
                i = i2;
            }
        } else {
            i = 1969;
            while (days < daysFromYear(i)) {
                i--;
            }
        }
        return i;
    }

    public final double setDateFields(double[] fields, boolean isLocal) {
        Intrinsics.checkNotNullParameter(fields, "fields");
        double d = fields[0];
        double d2 = fields[1];
        double d3 = fields[2];
        double d4 = 12;
        double dFloor = d + java.lang.Math.floor(d2 / d4);
        double d5 = d2 % d4;
        if (d5 < 0.0d) {
            d5 += d4;
        }
        if (dFloor < -271821.0d || dFloor > 275760.0d) {
            return Double.NaN;
        }
        int i = (int) d5;
        long jDaysFromYear = daysFromYear((int) dFloor);
        for (int i2 = 0; i2 < i; i2++) {
            jDaysFromYear += monthDays[i2];
            if (i2 == 1) {
                jDaysFromYear += daysInYear(r3) - 365;
            }
        }
        double timezoneOffset = (((jDaysFromYear + d3) - 1) * 86400000) + (fields[3] * 3600000) + (fields[4] * WXRequest.DEFAULT_TIMEOUT_MS) + (fields[5] * 1000) + fields[6];
        if (Double.isInfinite(timezoneOffset) || Double.isNaN(timezoneOffset)) {
            return Double.NaN;
        }
        if (isLocal) {
            timezoneOffset += getTimezoneOffset(timezoneOffset < -9.223372036854776E18d ? Long.MIN_VALUE : timezoneOffset >= 9.223372036854776E18d ? Long.MAX_VALUE : (long) timezoneOffset) * 60000.0d;
        }
        return DateParser.INSTANCE.timeClip(timezoneOffset);
    }

    private final boolean isLeapYear(int y) {
        return y % 4 == 0 && (y % 100 != 0 || y % StatFsHelper.DEFAULT_DISK_YELLOW_LEVEL_IN_MB == 0);
    }

    private final int daysInYear(int y) {
        return isLeapYear(y) ? 366 : 365;
    }

    private final long daysFromYear(long y) {
        return (((365 * (y - 1970)) + ((y - 1969) / 4)) - ((y - 1901) / 100)) + ((y - 1601) / StatFsHelper.DEFAULT_DISK_YELLOW_LEVEL_IN_MB);
    }

    private final int getTimezoneOffset(long timestamp) {
        java.util.Date date = new java.util.Date(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (-calendar.getTimeZone().getOffset(timestamp)) / WXRequest.DEFAULT_TIMEOUT_MS;
    }

    public final double setDateField(double dateDouble, int magic, Number... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        int i = (magic >> 8) & 15;
        int i2 = (magic >> 4) & 15;
        boolean z = (magic & 15) != 0;
        double[] dateFields = getDateFields(dateDouble, z, i == 0);
        if (dateFields == null) {
            return Double.NaN;
        }
        int iMin = java.lang.Math.min(args.length, i2 - i);
        for (int i3 = 0; i3 < iMin; i3++) {
            double dDoubleValue = args[i3].doubleValue();
            if (Double.isInfinite(dDoubleValue) || Double.isNaN(dDoubleValue)) {
                return Double.NaN;
            }
            dateFields[i + i3] = java.lang.Math.floor(dDoubleValue);
        }
        return !(args.length == 0) ? setDateFields(dateFields, z) : dateDouble;
    }
}
