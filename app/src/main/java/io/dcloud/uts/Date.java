package io.dcloud.uts;

import com.nky.nkyble.bean.ConnectError;
import com.taobao.weex.common.WXRequest;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: Date2.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b*\n\u0002\u0010\u0000\n\u0002\b\t\u0018\u0000 N2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001NB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0000¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\b¢\u0006\u0002\u0010\tBS\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\b\b\u0002\u0010\f\u001a\u00020\u0004\u0012\b\b\u0002\u0010\r\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0011\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0000H\u0096\u0002J\u0006\u0010\u001d\u001a\u00020\u0004J\u0006\u0010\u001e\u001a\u00020\u0004J\u0006\u0010\u001f\u001a\u00020\u0004J\u0006\u0010 \u001a\u00020\u0004J\u0006\u0010!\u001a\u00020\u0004J\u0006\u0010\"\u001a\u00020\u0004J\u0006\u0010#\u001a\u00020\u0004J\u0006\u0010$\u001a\u00020\u0004J\u0006\u0010%\u001a\u00020\u0004J\u0006\u0010&\u001a\u00020\u0004J\u0006\u0010'\u001a\u00020\u0004J\u0006\u0010(\u001a\u00020\u0004J\u0006\u0010)\u001a\u00020\u0004J\u0006\u0010*\u001a\u00020\u0004J\u0006\u0010+\u001a\u00020\u0004J\u0006\u0010,\u001a\u00020\u0004J\u0006\u0010-\u001a\u00020\u0004J\u0006\u0010.\u001a\u00020\u0004J\u0006\u0010/\u001a\u00020\u0004J\u000e\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0004J\u000e\u00102\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J&\u00102\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0004J\u000e\u00104\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J2\u00104\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u000e\u00105\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004J\u000e\u00106\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004J&\u00106\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u000e\u00107\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004J\u001a\u00107\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0004J\u000e\u00108\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004J\u001a\u00108\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u000e\u00109\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010:\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0004J&\u0010;\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0004J2\u0010<\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u000e\u0010=\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004J&\u0010>\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u001a\u0010?\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0004J\u001a\u0010@\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u000e\u0010A\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010B\u001a\u00020\bJ\u0006\u0010C\u001a\u00020\bJ\u0006\u0010D\u001a\u00020\bJ\b\u0010E\u001a\u0004\u0018\u00010FJ\u0006\u0010G\u001a\u00020\bJ\u0006\u0010H\u001a\u00020\bJ\u0006\u0010I\u001a\u00020\bJ\b\u0010J\u001a\u00020\bH\u0016J\u0006\u0010K\u001a\u00020\bJ\u0006\u0010L\u001a\u00020\bJ\u0006\u0010M\u001a\u00020\u0004R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006O"}, d2 = {"Lio/dcloud/uts/Date;", "", "()V", "value", "", "(Ljava/lang/Number;)V", "souceDate", "(Lio/dcloud/uts/Date;)V", "", "(Ljava/lang/String;)V", "year", "monthIndex", "day", "hours", "minutes", "seconds", "milliseconds", "isUTC", "", "(Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Z)V", "dateDouble", "", "getDateDouble", "()D", "setDateDouble", "(D)V", "compareTo", "", "other", "getDate", "getDay", "getFullYear", "getHours", "getMilliseconds", "getMinutes", "getMonth", "getSeconds", "getTime", "getTimezoneOffset", "getUTCDate", "getUTCDay", "getUTCFullYear", "getUTCHours", "getUTCMilliseconds", "getUTCMinutes", "getUTCMonth", "getUTCSeconds", "getYear", "setDate", "date", "setFullYear", "month", "setHours", "setMilliseconds", "setMinutes", "setMonth", "setSeconds", "setTime", "setUTCDate", "setUTCFullYear", "setUTCHours", "setUTCMilliseconds", "setUTCMinutes", "setUTCMonth", "setUTCSeconds", "setYear", "toDateString", "toGMTString", "toISOString", "toJSON", "", "toLocaleDateString", "toLocaleString", "toLocaleTimeString", "toString", "toTimeString", "toUTCString", "valueOf", "Companion", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Date implements Comparable<Date> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final double MAX_TIME_VALUE = 8.64E15d;
    private double dateDouble;

    @JvmStatic
    public static final Number UTC(Number number, Number number2, Number number3, Number number4, Number number5, Number number6, Number number7) {
        return INSTANCE.UTC(number, number2, number3, number4, number5, number6, number7);
    }

    @JvmStatic
    public static final Number now() {
        return INSTANCE.now();
    }

    @JvmStatic
    public static final Number parse(String str) {
        return INSTANCE.parse(str);
    }

    /* compiled from: Date2.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0004\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JJ\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u0006H\u0007J\b\u0010\u000e\u001a\u00020\u0006H\u0007J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lio/dcloud/uts/Date$Companion;", "", "()V", "MAX_TIME_VALUE", "", "UTC", "", "year", "month", "day", "hour", "minute", "second", "millisecond", "now", "parse", "value", "", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Number now() {
            return new Date().getTime();
        }

        @JvmStatic
        public final Number parse(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Date(value).getTime();
        }

        @JvmStatic
        public final Number UTC(Number year, Number month, Number day, Number hour, Number minute, Number second, Number millisecond) {
            Intrinsics.checkNotNullParameter(year, "year");
            Intrinsics.checkNotNullParameter(month, "month");
            Intrinsics.checkNotNullParameter(day, "day");
            Intrinsics.checkNotNullParameter(hour, "hour");
            Intrinsics.checkNotNullParameter(minute, "minute");
            Intrinsics.checkNotNullParameter(second, "second");
            Intrinsics.checkNotNullParameter(millisecond, "millisecond");
            double[] dArr = {0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d};
            Number[] numberArr = {year, month, day, hour, minute, second, millisecond};
            for (int i = 0; i < 7; i++) {
                double dDoubleValue = numberArr[i].doubleValue();
                if (Double.isInfinite(dDoubleValue) || Double.isNaN(dDoubleValue)) {
                    return Double.valueOf(Double.NaN);
                }
                dArr[i] = java.lang.Math.floor(dDoubleValue);
            }
            double d = dArr[0];
            if (d >= 0.0d && d < 100.0d) {
                dArr[0] = d + 1900;
            }
            return Double.valueOf(DateHolder.INSTANCE.setDateFields(dArr, false));
        }
    }

    public final double getDateDouble() {
        return this.dateDouble;
    }

    public final void setDateDouble(double d) {
        this.dateDouble = d;
    }

    public Date() {
        this.dateDouble = new java.util.Date().getTime();
    }

    public Date(Number value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.dateDouble = value.doubleValue();
    }

    public Date(Date souceDate) {
        Intrinsics.checkNotNullParameter(souceDate, "souceDate");
        this.dateDouble = souceDate.dateDouble;
    }

    public Date(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Double andValidateDate = DateParser.INSTANCE.parseAndValidateDate(value);
        Intrinsics.checkNotNull(andValidateDate);
        this.dateDouble = andValidateDate.doubleValue();
    }

    public /* synthetic */ Date(Number number, Number number2, Number number3, Number number4, Number number5, Number number6, Number number7, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(number, number2, (i & 4) != 0 ? (Number) 1 : number3, (i & 8) != 0 ? (Number) 0 : number4, (i & 16) != 0 ? (Number) 0 : number5, (i & 32) != 0 ? (Number) 0 : number6, (i & 64) != 0 ? (Number) 0 : number7, (i & 128) != 0 ? false : z);
    }

    public Date(Number year, Number monthIndex, Number day, Number hours, Number minutes, Number seconds, Number milliseconds, boolean z) {
        double dateFields;
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(monthIndex, "monthIndex");
        Intrinsics.checkNotNullParameter(day, "day");
        Intrinsics.checkNotNullParameter(hours, "hours");
        Intrinsics.checkNotNullParameter(minutes, "minutes");
        Intrinsics.checkNotNullParameter(seconds, "seconds");
        Intrinsics.checkNotNullParameter(milliseconds, "milliseconds");
        double[] dArr = new double[7];
        dArr[0] = 0.0d;
        dArr[1] = 0.0d;
        dArr[2] = 1.0d;
        Number[] numberArr = {year, monthIndex, day, hours, minutes, seconds, milliseconds};
        for (int i = 0; i < 7; i++) {
            double dDoubleValue = numberArr[i].doubleValue();
            if (Double.isInfinite(dDoubleValue) || Double.isNaN(dDoubleValue)) {
                dateFields = Double.NaN;
                break;
            }
            dArr[i] = MathKt.truncate(dDoubleValue);
            if (i == 0) {
                double d = dArr[0];
                if (d >= 0.0d && d < 100.0d) {
                    dArr[0] = d + 1900 + d;
                }
            }
        }
        dateFields = DateHolder.INSTANCE.setDateFields(dArr, !z);
        this.dateDouble = dateFields;
    }

    public final Number getTime() {
        return Long.valueOf((long) this.dateDouble);
    }

    public final Number valueOf() {
        return Long.valueOf((long) this.dateDouble);
    }

    public final Number getDate() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 33).intValue());
    }

    public final Number getUTCDate() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 32).intValue());
    }

    public final Number getYear() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 257).intValue());
    }

    public final Number getFullYear() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 1).intValue());
    }

    public final Number getUTCFullYear() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 0).intValue());
    }

    public final Number getMonth() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 17).intValue());
    }

    public final Number getUTCMonth() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 16).intValue());
    }

    public final Number getHours() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 49).intValue());
    }

    public final Number getUTCHours() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 48).intValue());
    }

    public final Number getMinutes() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 65).intValue());
    }

    public final Number getUTCMinutes() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 64).intValue());
    }

    public final Number getSeconds() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 81).intValue());
    }

    public final Number getUTCSeconds() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 80).intValue());
    }

    public final Number getMilliseconds() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 97).intValue());
    }

    public final Number getUTCMilliseconds() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 96).intValue());
    }

    public final Number getDay() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 113).intValue());
    }

    public final Number getUTCDay() {
        return Integer.valueOf(DateHolder.INSTANCE.getDateField(this.dateDouble, 112).intValue());
    }

    public final Number getTimezoneOffset() {
        if (Double.isNaN(this.dateDouble)) {
            return Double.valueOf(Double.NaN);
        }
        java.util.Date date = new java.util.Date((long) this.dateDouble);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Integer.valueOf((-calendar.getTimeZone().getOffset((long) this.dateDouble)) / WXRequest.DEFAULT_TIMEOUT_MS);
    }

    public final Number setTime(Number value) {
        Intrinsics.checkNotNullParameter(value, "value");
        double dDoubleValue = value.doubleValue();
        if (Double.isNaN(dDoubleValue) || dDoubleValue > MAX_TIME_VALUE || dDoubleValue < -8.64E15d) {
            dDoubleValue = Double.NaN;
        }
        this.dateDouble = dDoubleValue;
        return Double.valueOf(dDoubleValue);
    }

    public final Number setYear(Number year) {
        Intrinsics.checkNotNullParameter(year, "year");
        double dDoubleValue = year.doubleValue();
        if (Double.isNaN(dDoubleValue)) {
            this.dateDouble = Double.NaN;
            return Double.valueOf(Double.NaN);
        }
        double dFloor = java.lang.Math.floor(dDoubleValue);
        if (dFloor >= 0.0d && dFloor < 100.0d) {
            dFloor += 1900;
        }
        double dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 17, Double.valueOf(dFloor));
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setFullYear(Number year) {
        Intrinsics.checkNotNullParameter(year, "year");
        return setFullYear(year, null, null);
    }

    public static /* synthetic */ Number setFullYear$default(Date date, Number number, Number number2, Number number3, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        if ((i & 4) != 0) {
            number3 = null;
        }
        return date.setFullYear(number, number2, number3);
    }

    public final Number setFullYear(Number year, Number month, Number date) {
        double dateField;
        Intrinsics.checkNotNullParameter(year, "year");
        if (month != null && date != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 49, year, month, date);
        } else if (month != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 49, year, month);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 49, year);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setMonth(Number month) {
        Intrinsics.checkNotNullParameter(month, "month");
        return setMonth(month, null);
    }

    public static /* synthetic */ Number setMonth$default(Date date, Number number, Number number2, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        return date.setMonth(number, number2);
    }

    public final Number setMonth(Number month, Number date) {
        double dateField;
        Intrinsics.checkNotNullParameter(month, "month");
        if (date != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, ConnectError.SERVICE_NULL, month, date);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, ConnectError.SERVICE_NULL, month);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setDate(Number date) {
        Intrinsics.checkNotNullParameter(date, "date");
        double dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 561, date);
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setMilliseconds(Number milliseconds) {
        Intrinsics.checkNotNullParameter(milliseconds, "milliseconds");
        double dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1649, milliseconds);
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setUTCMilliseconds(Number milliseconds) {
        Intrinsics.checkNotNullParameter(milliseconds, "milliseconds");
        double dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1648, milliseconds);
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setSeconds(Number seconds) {
        Intrinsics.checkNotNullParameter(seconds, "seconds");
        return setSeconds(seconds, null);
    }

    public static /* synthetic */ Number setSeconds$default(Date date, Number number, Number number2, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        return date.setSeconds(number, number2);
    }

    public final Number setSeconds(Number seconds, Number milliseconds) {
        double dateField;
        Intrinsics.checkNotNullParameter(seconds, "seconds");
        if (milliseconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1393, seconds, milliseconds);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1393, seconds);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public static /* synthetic */ Number setUTCSeconds$default(Date date, Number number, Number number2, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        return date.setUTCSeconds(number, number2);
    }

    public final Number setUTCSeconds(Number seconds, Number milliseconds) {
        double dateField;
        Intrinsics.checkNotNullParameter(seconds, "seconds");
        if (milliseconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1392, seconds, milliseconds);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1392, seconds);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setMinutes(Number minutes) {
        Intrinsics.checkNotNullParameter(minutes, "minutes");
        return setMinutes(minutes, null, null);
    }

    public static /* synthetic */ Number setMinutes$default(Date date, Number number, Number number2, Number number3, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        if ((i & 4) != 0) {
            number3 = null;
        }
        return date.setMinutes(number, number2, number3);
    }

    public final Number setMinutes(Number minutes, Number seconds, Number milliseconds) {
        double dateField;
        Intrinsics.checkNotNullParameter(minutes, "minutes");
        if (seconds != null && milliseconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1137, minutes, seconds, milliseconds);
        } else if (seconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1137, minutes, seconds);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1137, minutes);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public static /* synthetic */ Number setUTCMinutes$default(Date date, Number number, Number number2, Number number3, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        if ((i & 4) != 0) {
            number3 = null;
        }
        return date.setUTCMinutes(number, number2, number3);
    }

    public final Number setUTCMinutes(Number minutes, Number seconds, Number milliseconds) {
        double dateField;
        Intrinsics.checkNotNullParameter(minutes, "minutes");
        if (seconds != null && milliseconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1136, minutes, seconds, milliseconds);
        } else if (seconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1136, minutes, seconds);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 1136, minutes);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setHours(Number hours) {
        Intrinsics.checkNotNullParameter(hours, "hours");
        return setHours(hours, null, null, null);
    }

    public static /* synthetic */ Number setHours$default(Date date, Number number, Number number2, Number number3, Number number4, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        if ((i & 4) != 0) {
            number3 = null;
        }
        if ((i & 8) != 0) {
            number4 = null;
        }
        return date.setHours(number, number2, number3, number4);
    }

    public final Number setHours(Number hours, Number minutes, Number seconds, Number milliseconds) {
        double dateField;
        Intrinsics.checkNotNullParameter(hours, "hours");
        if (minutes != null && seconds != null && milliseconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 881, hours, minutes, seconds, milliseconds);
        } else if (minutes != null && seconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 881, hours, minutes, seconds);
        } else if (minutes != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 881, hours, minutes);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 881, hours);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public static /* synthetic */ Number setUTCHours$default(Date date, Number number, Number number2, Number number3, Number number4, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        if ((i & 4) != 0) {
            number3 = null;
        }
        if ((i & 8) != 0) {
            number4 = null;
        }
        return date.setUTCHours(number, number2, number3, number4);
    }

    public final Number setUTCHours(Number hours, Number minutes, Number seconds, Number milliseconds) {
        double dateField;
        Intrinsics.checkNotNullParameter(hours, "hours");
        if (minutes != null && seconds != null && milliseconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 880, hours, minutes, seconds, milliseconds);
        } else if (minutes != null && seconds != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 880, hours, minutes, seconds);
        } else if (minutes != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 880, hours, minutes);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 880, hours);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public final Number setUTCDate(Number date) {
        Intrinsics.checkNotNullParameter(date, "date");
        double dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 560, date);
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public static /* synthetic */ Number setUTCMonth$default(Date date, Number number, Number number2, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        return date.setUTCMonth(number, number2);
    }

    public final Number setUTCMonth(Number month, Number date) {
        double dateField;
        Intrinsics.checkNotNullParameter(month, "month");
        if (date != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, ConnectError.BLE_CONNECTING, month, date);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, ConnectError.BLE_CONNECTING, month);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    public static /* synthetic */ Number setUTCFullYear$default(Date date, Number number, Number number2, Number number3, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            number2 = null;
        }
        if ((i & 4) != 0) {
            number3 = null;
        }
        return date.setUTCFullYear(number, number2, number3);
    }

    public final Number setUTCFullYear(Number year, Number month, Number date) {
        double dateField;
        Intrinsics.checkNotNullParameter(year, "year");
        if (month != null && date != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 48, year, month, date);
        } else if (month != null) {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 48, year, month);
        } else {
            dateField = DateHolder.INSTANCE.setDateField(this.dateDouble, 48, year);
        }
        this.dateDouble = dateField;
        return Double.valueOf(dateField);
    }

    @Override // java.lang.Comparable
    public int compareTo(Date other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return Double.compare(this.dateDouble, other.dateDouble);
    }

    public String toString() {
        return DateParser.INSTANCE.getDateString(this.dateDouble, 19);
    }

    public final String toUTCString() {
        return DateParser.INSTANCE.getDateString(this.dateDouble, 3);
    }

    public final String toGMTString() {
        return toUTCString();
    }

    public final String toISOString() {
        return DateParser.INSTANCE.getDateString(this.dateDouble, 35);
    }

    public final java.lang.Object toJSON() {
        double d = this.dateDouble;
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return null;
        }
        return toISOString();
    }

    public final String toDateString() {
        return DateParser.INSTANCE.getDateString(this.dateDouble, 17);
    }

    public final String toTimeString() {
        return DateParser.INSTANCE.getDateString(this.dateDouble, 18);
    }

    public final String toLocaleString() {
        return DateParser.INSTANCE.getDateString(this.dateDouble, 51);
    }

    public final String toLocaleDateString() {
        return DateParser.INSTANCE.getDateString(this.dateDouble, 49);
    }

    public final String toLocaleTimeString() {
        return DateParser.INSTANCE.getDateString(this.dateDouble, 50);
    }
}
