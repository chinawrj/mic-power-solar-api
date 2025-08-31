package io.dcloud.uts;

import com.taobao.weex.el.parse.Operators;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* compiled from: Date2.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ6\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0015\u0010\u0019\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001a\u001a\u00020\u0005¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u0005J$\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J&\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\u0010\u0010!\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001a\u001a\u00020\u0005J&\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J&\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\u0018\u0010$\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\u0018\u0010%\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J \u0010&\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u0005H\u0002J\u000e\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rR\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007¨\u0006*"}, d2 = {"Lio/dcloud/uts/DateParser;", "", "()V", "DAY_NAMES", "", "", "getDAY_NAMES", "()[Ljava/lang/String;", "[Ljava/lang/String;", "MONTH_NAMES", "getMONTH_NAMES", "getDateString", "dateDouble", "", "magic", "", "getDigits", "Lkotlin/Pair;", "str", "startPos", "minDigits", "maxDigits", "matchString", "", "match", "parseAndValidateDate", "dateString", "(Ljava/lang/String;)Ljava/lang/Double;", "parseISOString", "Lio/dcloud/uts/ISODateResult;", "isoString", "parseMilliseconds", "parseMonth", "parseOtherString", "parseTimeZoneAbbr", "parseTzOffset", "skipSeparators", "skipSpaces", "skipUntil", "chars", "timeClip", "t", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DateParser {
    public static final DateParser INSTANCE = new DateParser();
    private static final String[] DAY_NAMES = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static final String[] MONTH_NAMES = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private DateParser() {
    }

    public final String[] getDAY_NAMES() {
        return DAY_NAMES;
    }

    public final String[] getMONTH_NAMES() {
        return MONTH_NAMES;
    }

    public final String getDateString(double dateDouble, int magic) {
        String string;
        int i = magic >> 4;
        int i2 = i & 15;
        int i3 = magic & 15;
        double[] dateFields = DateHolder.INSTANCE.getDateFields(dateDouble, (i & 1) != 0, false);
        if (dateFields == null) {
            if (i2 != 2) {
                return "Invalid Date";
            }
            throw new RuntimeException("Date value is NaN");
        }
        int i4 = (int) dateFields[0];
        int i5 = (int) dateFields[1];
        int i6 = (int) dateFields[2];
        int i7 = (int) dateFields[3];
        int i8 = (int) dateFields[4];
        int i9 = (int) dateFields[5];
        int i10 = (int) dateFields[6];
        int i11 = (int) dateFields[7];
        int i12 = (int) dateFields[8];
        StringBuilder sb = new StringBuilder();
        if ((magic & 1) != 0) {
            if (i2 == 0) {
                String str = DAY_NAMES[i11];
                String str2 = MONTH_NAMES[i5];
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(", ");
                sb2.append(StringsKt.padStart(String.valueOf(i6), 2, '0'));
                sb2.append(' ');
                sb2.append(str2);
                sb2.append(' ');
                sb2.append(StringsKt.padStart(String.valueOf(i4), (i4 < 0 ? 1 : 0) + 4, '0'));
                sb2.append(' ');
                sb.append(sb2.toString());
            } else if (i2 == 1) {
                String str3 = DAY_NAMES[i11];
                String str4 = MONTH_NAMES[i5];
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str3);
                sb3.append(' ');
                sb3.append(str4);
                sb3.append(' ');
                sb3.append(StringsKt.padStart(String.valueOf(i6), 2, '0'));
                sb3.append(' ');
                sb3.append(StringsKt.padStart(String.valueOf(i4), (i4 < 0 ? 1 : 0) + 4, '0'));
                sb.append(sb3.toString());
                if (i3 == 3) {
                    sb.append(Operators.SPACE_STR);
                }
            } else if (i2 == 2) {
                if (i4 < 0 || i4 > 9999) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(i4 >= 0 ? Operators.PLUS : "");
                    sb4.append(StringsKt.padStart(String.valueOf(i4), 6, '0'));
                    string = sb4.toString();
                } else {
                    string = StringsKt.padStart(String.valueOf(i4), 4, '0');
                }
                sb.append(string + '-' + StringsKt.padStart(String.valueOf(i5 + 1), 2, '0') + '-' + StringsKt.padStart(String.valueOf(i6), 2, '0') + 'T');
            } else if (i2 == 3) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(StringsKt.padStart(String.valueOf(i5 + 1), 2, '0'));
                sb5.append('/');
                sb5.append(StringsKt.padStart(String.valueOf(i6), 2, '0'));
                sb5.append('/');
                sb5.append(StringsKt.padStart(String.valueOf(i4), (i4 < 0 ? 1 : 0) + 4, '0'));
                sb.append(sb5.toString());
                if (i3 == 3) {
                    sb.append(", ");
                }
            }
        }
        if ((magic & 2) != 0) {
            if (i2 == 0) {
                sb.append(StringsKt.padStart(String.valueOf(i7), 2, '0') + Operators.CONDITION_IF_MIDDLE + StringsKt.padStart(String.valueOf(i8), 2, '0') + Operators.CONDITION_IF_MIDDLE + StringsKt.padStart(String.valueOf(i9), 2, '0') + " GMT");
            } else if (i2 == 1) {
                sb.append(StringsKt.padStart(String.valueOf(i7), 2, '0') + Operators.CONDITION_IF_MIDDLE + StringsKt.padStart(String.valueOf(i8), 2, '0') + Operators.CONDITION_IF_MIDDLE + StringsKt.padStart(String.valueOf(i9), 2, '0') + " GMT");
                if (i12 < 0) {
                    sb.append(Operators.SUB);
                    i12 = -i12;
                } else {
                    sb.append(Operators.PLUS);
                }
                sb.append(StringsKt.padStart(String.valueOf(i12 / 60), 2, '0') + StringsKt.padStart(String.valueOf(i12 % 60), 2, '0'));
            } else if (i2 == 2) {
                sb.append(StringsKt.padStart(String.valueOf(i7), 2, '0') + Operators.CONDITION_IF_MIDDLE + StringsKt.padStart(String.valueOf(i8), 2, '0') + Operators.CONDITION_IF_MIDDLE + StringsKt.padStart(String.valueOf(i9), 2, '0') + Operators.DOT + StringsKt.padStart(String.valueOf(i10), 3, '0') + 'Z');
            } else if (i2 == 3) {
                int i13 = ((i7 + 11) % 12) + 1;
                sb.append(StringsKt.padStart(String.valueOf(i13), 2, '0') + Operators.CONDITION_IF_MIDDLE + StringsKt.padStart(String.valueOf(i8), 2, '0') + Operators.CONDITION_IF_MIDDLE + StringsKt.padStart(String.valueOf(i9), 2, '0') + ' ' + (i7 < 12 ? 'A' : 'P') + 'M');
            }
        }
        String string2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "result.toString()");
        return string2;
    }

    public final double timeClip(double t) {
        if (t < -8.64E15d || t > 8.64E15d) {
            return Double.NaN;
        }
        return MathKt.truncate(t) + 0.0d;
    }

    public final Double parseAndValidateDate(String dateString) {
        int i;
        Intrinsics.checkNotNullParameter(dateString, "dateString");
        DateParser dateParser = INSTANCE;
        ISODateResult iSOString = dateParser.parseISOString(dateString);
        if (iSOString == null && (iSOString = dateParser.parseOtherString(dateString)) == null) {
            return null;
        }
        int[] fields = iSOString.getFields();
        boolean zIsLocal = iSOString.isLocal();
        int[] iArr = {0, 11, 31, 24, 59, 59};
        boolean z = true;
        int i2 = 1;
        while (true) {
            if (i2 >= 6) {
                break;
            }
            if (fields[i2] > iArr[i2]) {
                z = false;
                break;
            }
            i2++;
        }
        if (fields[3] == 24 && (fields[4] != 0 || fields[5] != 0 || fields[6] != 0)) {
            z = false;
        }
        if (!z) {
            return null;
        }
        double[] dArr = new double[7];
        for (i = 0; i < 7; i++) {
            dArr[i] = fields[i];
        }
        return Double.valueOf(DateHolder.INSTANCE.setDateFields(dArr, zIsLocal) - (fields[8] * 60000.0d));
    }

    /* JADX WARN: Code restructure failed: missing block: B:143:0x026a, code lost:
    
        if (((r12 + r11) + r13) <= 3) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x026c, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x026e, code lost:
    
        if (r12 == 0) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0270, code lost:
    
        if (r12 == 1) goto L184;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0273, code lost:
    
        if (r12 == 2) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0275, code lost:
    
        if (r12 == 3) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x027a, code lost:
    
        r3 = r7[2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x027e, code lost:
    
        if (r3 >= 100) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0281, code lost:
    
        r17 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0283, code lost:
    
        r17 = r3 + r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0287, code lost:
    
        if (r3 >= 50) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0289, code lost:
    
        r16 = 100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x028c, code lost:
    
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x028e, code lost:
    
        r6[0] = r17 + r16;
        r1 = r7[0];
        r6[1] = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0297, code lost:
    
        if (r15 != false) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0299, code lost:
    
        r6[1] = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x029d, code lost:
    
        r6[2] = r7[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x02a5, code lost:
    
        if (r11 == 0) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x02a7, code lost:
    
        r1 = r7[0];
        r6[1] = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x02ab, code lost:
    
        if (r15 != false) goto L169;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x02ad, code lost:
    
        r6[1] = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x02b1, code lost:
    
        r6[2] = r7[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x02b6, code lost:
    
        if (r13 == 0) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x02b8, code lost:
    
        r1 = r7[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x02bc, code lost:
    
        if (r1 >= 100) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x02bf, code lost:
    
        r17 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x02c1, code lost:
    
        r17 = r1 + r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x02c5, code lost:
    
        if (r1 >= 50) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x02c7, code lost:
    
        r4 = 100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x02c9, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x02ca, code lost:
    
        r6[0] = r17 + r4;
        r6[2] = r7[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x02d5, code lost:
    
        r1 = r7[0];
        r6[1] = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x02db, code lost:
    
        if (r15 != false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x02dd, code lost:
    
        r6[1] = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x02e1, code lost:
    
        r6[2] = r7[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x02e8, code lost:
    
        if (r13 == 0) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x02ea, code lost:
    
        r6[2] = r7[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x02ef, code lost:
    
        r1 = r7[0];
        r6[1] = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x02f3, code lost:
    
        if (r15 != false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x02f5, code lost:
    
        r6[1] = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x02fb, code lost:
    
        if (r11 != 0) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x02fd, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x02ff, code lost:
    
        r3 = r6[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0301, code lost:
    
        if (r3 < 0) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0305, code lost:
    
        if (r3 <= 11) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0308, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0309, code lost:
    
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0236 A[PHI: r10
  0x0236: PHI (r10v4 int) = (r10v3 int), (r10v7 int) binds: [B:125:0x020f, B:130:0x0234] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final io.dcloud.uts.ISODateResult parseOtherString(java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 778
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.uts.DateParser.parseOtherString(java.lang.String):io.dcloud.uts.ISODateResult");
    }

    private final boolean skipSpaces(String str, int startPos) {
        while (startPos < str.length() && CharsKt.isWhitespace(str.charAt(startPos))) {
            startPos++;
        }
        return startPos < str.length();
    }

    private final Pair<Integer, Integer> parseMonth(String str, int startPos) {
        String[] strArr = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        for (int i = 0; i < 12; i++) {
            String str2 = strArr[i];
            if (str2.length() + startPos <= str.length()) {
                String lowerCase = StringKt.substring(str, Integer.valueOf(startPos), Integer.valueOf(str2.length() + startPos)).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (Intrinsics.areEqual(lowerCase, str2)) {
                    return new Pair<>(Integer.valueOf(i), Integer.valueOf(startPos + str2.length()));
                }
            }
        }
        return null;
    }

    private final boolean matchString(String str, int startPos, String match) {
        return match.length() + startPos <= str.length() && StringsKt.equals(StringKt.substring(str, Integer.valueOf(startPos), Integer.valueOf(startPos + match.length())), match, true);
    }

    private final Pair<Integer, Integer> parseTimeZoneAbbr(String str, int startPos) {
        for (Map.Entry entry : MapsKt.mapOf(TuplesKt.to("ut", 0), TuplesKt.to("utc", 0), TuplesKt.to("gmt", 0), TuplesKt.to("est", -300), TuplesKt.to("edt", -240), TuplesKt.to("cst", -360), TuplesKt.to("cdt", -300), TuplesKt.to("mst", -420), TuplesKt.to("mdt", -360), TuplesKt.to("pst", -480), TuplesKt.to("pdt", -420)).entrySet()) {
            String str2 = (String) entry.getKey();
            int iIntValue = ((Number) entry.getValue()).intValue();
            if (matchString(str, startPos, str2)) {
                return new Pair<>(Integer.valueOf(iIntValue), Integer.valueOf(startPos + str2.length()));
            }
        }
        return null;
    }

    private final int skipSeparators(String str, int startPos) {
        while (startPos < str.length() && (str.charAt(startPos) == ' ' || str.charAt(startPos) == ',' || str.charAt(startPos) == '-' || str.charAt(startPos) == '/')) {
            startPos++;
        }
        return startPos;
    }

    private final int skipUntil(String str, int startPos, String chars) {
        while (startPos < str.length() && StringsKt.indexOf$default((CharSequence) chars, str.charAt(startPos), 0, false, 6, (java.lang.Object) null) < 0) {
            startPos++;
        }
        return startPos;
    }

    public final ISODateResult parseISOString(String isoString) {
        int iIntValue;
        Intrinsics.checkNotNullParameter(isoString, "isoString");
        ISODateResult iSODateResult = new ISODateResult(null, false, 3, null);
        int[] fields = iSODateResult.getFields();
        int i = 0;
        while (true) {
            int i2 = 1;
            if (i >= 9) {
                break;
            }
            if (i != 2) {
                i2 = 0;
            }
            fields[i] = i2;
            i++;
        }
        if (isoString.length() <= 0) {
            return null;
        }
        char cCharAt = isoString.charAt(0);
        if (cCharAt == '-' || cCharAt == '+') {
            Pair<Integer, Integer> digits = getDigits(isoString, 1, 6, 6);
            if (digits == null) {
                return null;
            }
            int iIntValue2 = digits.getSecond().intValue();
            int iIntValue3 = digits.getFirst().intValue();
            fields[0] = iIntValue3;
            if (cCharAt == '-') {
                if (iIntValue3 == 0) {
                    return null;
                }
                fields[0] = -iIntValue3;
            }
            iIntValue = iIntValue2;
        } else {
            Pair<Integer, Integer> digits2 = getDigits(isoString, 0, 4, 4);
            if (digits2 == null) {
                return null;
            }
            iIntValue = digits2.getSecond().intValue();
            fields[0] = digits2.getFirst().intValue();
        }
        if (iIntValue < isoString.length() && isoString.charAt(iIntValue) == '-') {
            Pair<Integer, Integer> digits3 = getDigits(isoString, iIntValue + 1, 2, 2);
            if (digits3 == null) {
                return null;
            }
            iIntValue = digits3.getSecond().intValue();
            int iIntValue4 = digits3.getFirst().intValue() - 1;
            fields[1] = iIntValue4;
            if (iIntValue4 < 0) {
                return null;
            }
            if (iIntValue < isoString.length() && isoString.charAt(iIntValue) == '-') {
                Pair<Integer, Integer> digits4 = getDigits(isoString, iIntValue + 1, 2, 2);
                if (digits4 == null) {
                    return null;
                }
                iIntValue = digits4.getSecond().intValue();
                int iIntValue5 = digits4.getFirst().intValue();
                fields[2] = iIntValue5;
                if (iIntValue5 < 1) {
                    return null;
                }
            }
        }
        if (iIntValue < isoString.length() && isoString.charAt(iIntValue) == 'T') {
            iSODateResult.setLocal(true);
            Pair<Integer, Integer> digits5 = getDigits(isoString, iIntValue + 1, 2, 2);
            if (digits5 == null) {
                return null;
            }
            int iIntValue6 = digits5.getSecond().intValue();
            fields[3] = digits5.getFirst().intValue();
            if (iIntValue6 >= isoString.length() || isoString.charAt(iIntValue6) != ':') {
                fields[3] = 100;
                return iSODateResult;
            }
            Pair<Integer, Integer> digits6 = getDigits(isoString, iIntValue6 + 1, 2, 2);
            if (digits6 == null) {
                return null;
            }
            int iIntValue7 = digits6.getSecond().intValue();
            fields[4] = digits6.getFirst().intValue();
            if (iIntValue7 >= isoString.length() || isoString.charAt(iIntValue7) != ':') {
                iIntValue = iIntValue7;
            } else {
                Pair<Integer, Integer> digits7 = getDigits(isoString, iIntValue7 + 1, 2, 2);
                if (digits7 == null) {
                    return null;
                }
                iIntValue = digits7.getSecond().intValue();
                fields[5] = digits7.getFirst().intValue();
                if (iIntValue < isoString.length() && isoString.charAt(iIntValue) == '.') {
                    Pair<Integer, Integer> milliseconds = parseMilliseconds(isoString, iIntValue + 1);
                    fields[6] = milliseconds.getFirst().intValue();
                    iIntValue = milliseconds.getSecond().intValue();
                }
            }
        }
        if (iIntValue < isoString.length()) {
            iSODateResult.setLocal(false);
            if (isoString.charAt(iIntValue) == 'Z') {
                iIntValue++;
            } else {
                Pair<Integer, Integer> tzOffset = parseTzOffset(isoString, iIntValue);
                if (tzOffset == null) {
                    return null;
                }
                fields[8] = tzOffset.getFirst().intValue();
                iIntValue = tzOffset.getSecond().intValue();
            }
        }
        if (iIntValue == isoString.length()) {
            return iSODateResult;
        }
        return null;
    }

    private final Pair<Integer, Integer> getDigits(String str, int startPos, int minDigits, int maxDigits) {
        char cCharAt;
        int i = 0;
        int i2 = 0;
        while (startPos < str.length() && i < maxDigits && '0' <= (cCharAt = str.charAt(startPos)) && cCharAt < ':') {
            i2 = (i2 * 10) + (cCharAt - '0');
            startPos++;
            i++;
        }
        if (i >= minDigits) {
            return new Pair<>(Integer.valueOf(i2), Integer.valueOf(startPos));
        }
        return null;
    }

    private final Pair<Integer, Integer> parseMilliseconds(String str, int startPos) {
        char cCharAt;
        char cCharAt2;
        int i = 100;
        int iCharAt = 0;
        for (int i2 = 0; i2 < 3 && startPos < str.length() && '0' <= (cCharAt2 = str.charAt(startPos)) && cCharAt2 < ':'; i2++) {
            iCharAt += (str.charAt(startPos) - '0') * i;
            i /= 10;
            startPos++;
        }
        while (startPos < str.length() && '0' <= (cCharAt = str.charAt(startPos)) && cCharAt < ':') {
            startPos++;
        }
        return new Pair<>(Integer.valueOf(iCharAt), Integer.valueOf(startPos));
    }

    private final Pair<Integer, Integer> parseTzOffset(String str, int startPos) {
        int i;
        Pair<Integer, Integer> digits;
        if (startPos >= str.length()) {
            return null;
        }
        char cCharAt = str.charAt(startPos);
        if (cCharAt == '+') {
            i = 1;
        } else {
            if (cCharAt != '-') {
                return null;
            }
            i = -1;
        }
        Pair<Integer, Integer> digits2 = getDigits(str, startPos + 1, 2, 2);
        if (digits2 == null) {
            return null;
        }
        int iIntValue = digits2.getSecond().intValue();
        int iIntValue2 = digits2.getFirst().intValue() * 60;
        if (iIntValue < str.length() && str.charAt(iIntValue) == ':') {
            iIntValue++;
        }
        if (iIntValue < str.length() && (digits = getDigits(str, iIntValue, 2, 2)) != null) {
            iIntValue2 += digits.getFirst().intValue();
            iIntValue = digits.getSecond().intValue();
        }
        return new Pair<>(Integer.valueOf(i * iIntValue2), Integer.valueOf(iIntValue));
    }
}
