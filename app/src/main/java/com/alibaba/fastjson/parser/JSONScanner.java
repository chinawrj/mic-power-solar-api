package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import com.taobao.weex.common.WXRequest;
import com.taobao.weex.el.parse.Operators;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/* loaded from: classes.dex */
public final class JSONScanner extends JSONLexerBase {
    private final int len;
    private final String text;

    static boolean checkDate(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if (c >= '0' && c <= '9' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9') {
            if (c5 == '0') {
                if (c6 < '1' || c6 > '9') {
                    return false;
                }
            } else if (c5 != '1' || (c6 != '0' && c6 != '1' && c6 != '2')) {
                return false;
            }
            if (i == 48) {
                return i2 >= 49 && i2 <= 57;
            }
            if (i != 49 && i != 50) {
                return i == 51 && (i2 == 48 || i2 == 49);
            }
            if (i2 >= 48 && i2 <= 57) {
                return true;
            }
        }
        return false;
    }

    private boolean checkTime(char c, char c2, char c3, char c4, char c5, char c6) {
        if (c == '0') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else {
            if (c != '1') {
                if (c == '2' && c2 >= '0' && c2 <= '4') {
                }
                return false;
            }
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        }
        if (c3 < '0' || c3 > '5') {
            if (c3 != '6' || c4 != '0') {
                return false;
            }
        } else if (c4 < '0' || c4 > '9') {
            return false;
        }
        return (c5 < '0' || c5 > '5') ? c5 == '6' && c6 == '0' : c6 >= '0' && c6 <= '9';
    }

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        super(i);
        this.text = str;
        this.len = str.length();
        this.bp = -1;
        next();
        if (this.ch == 65279) {
            next();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i) {
        return i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        char cCharAt = i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
        this.ch = cCharAt;
        return cCharAt;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i2 + i, cArr, 0);
    }

    static boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.bp, cArr);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c, int i) {
        return this.text.indexOf(c, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        if (this.token == 26) {
            int i = this.np + 1;
            int i2 = this.sp;
            if (i2 % 2 != 0) {
                throw new JSONException("illegal state. " + i2);
            }
            int i3 = i2 / 2;
            byte[] bArr = new byte[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 2) + i;
                char cCharAt = this.text.charAt(i5);
                char cCharAt2 = this.text.charAt(i5 + 1);
                char c = '0';
                int i6 = cCharAt - (cCharAt <= '9' ? '0' : '7');
                if (cCharAt2 > '9') {
                    c = '7';
                }
                bArr[i4] = (byte) ((i6 << 4) | (cCharAt2 - c));
            }
            return bArr;
        }
        if (!this.hasSpecial) {
            return IOUtils.decodeBase64(this.text, this.np + 1, this.sp);
        }
        return IOUtils.decodeBase64(new String(this.sbuf, 0, this.sp));
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        if (!this.hasSpecial) {
            return subString(this.np + 1, this.sp);
        }
        return new String(this.sbuf, 0, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i, int i2) {
        if (ASMUtils.IS_ANDROID) {
            if (i2 < this.sbuf.length) {
                this.text.getChars(i, i + i2, this.sbuf, 0);
                return new String(this.sbuf, 0, i2);
            }
            char[] cArr = new char[i2];
            this.text.getChars(i, i2 + i, cArr, 0);
            return new String(cArr);
        }
        return this.text.substring(i, i2 + i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char[] sub_chars(int i, int i2) {
        if (ASMUtils.IS_ANDROID && i2 < this.sbuf.length) {
            this.text.getChars(i, i2 + i, this.sbuf, 0);
            return this.sbuf;
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i2 + i, cArr, 0);
        return cArr;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        if (i > 65535) {
            throw new JSONException("decimal overflow");
        }
        int i2 = this.np;
        if (i < this.sbuf.length) {
            this.text.getChars(i2, i2 + i, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, i, MathContext.UNLIMITED);
        }
        char[] cArr = new char[i];
        this.text.getChars(i2, i2 + i, cArr, 0);
        return new BigDecimal(cArr, 0, i, MathContext.UNLIMITED);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    private boolean scanISO8601DateIfMatch(boolean z, int i) throws NumberFormatException {
        int i2;
        boolean z2;
        char c;
        char cCharAt;
        char c2;
        char c3;
        char c4;
        int i3;
        int i4;
        int i5;
        int i6;
        char c5;
        char c6;
        char cCharAt2;
        char c7;
        char c8;
        char c9;
        int i7;
        char c10;
        char c11;
        char c12;
        char c13;
        int i8;
        char c14;
        char c15;
        char c16;
        int i9;
        int i10;
        char cCharAt3;
        char cCharAt4;
        char cCharAt5;
        char cCharAt6;
        char cCharAt7;
        char cCharAt8;
        if (i < 8) {
            return false;
        }
        char cCharAt9 = charAt(this.bp);
        char cCharAt10 = charAt(this.bp + 1);
        char cCharAt11 = charAt(this.bp + 2);
        int i11 = 3;
        char cCharAt12 = charAt(this.bp + 3);
        char cCharAt13 = charAt(this.bp + 4);
        int i12 = 5;
        char cCharAt14 = charAt(this.bp + 5);
        char cCharAt15 = charAt(this.bp + 6);
        char cCharAt16 = charAt(this.bp + 7);
        if (!z) {
            if (i > 13) {
                char cCharAt17 = charAt((this.bp + i) - 1);
                char cCharAt18 = charAt((this.bp + i) - 2);
                if (cCharAt9 == '/' && cCharAt10 == 'D' && cCharAt11 == 'a' && cCharAt12 == 't' && cCharAt13 == 'e' && cCharAt14 == '(' && cCharAt17 == '/' && cCharAt18 == ')') {
                    int i13 = -1;
                    for (int i14 = 6; i14 < i; i14++) {
                        char cCharAt19 = charAt(this.bp + i14);
                        if (cCharAt19 != '+') {
                            if (cCharAt19 < '0' || cCharAt19 > '9') {
                                break;
                            }
                        } else {
                            i13 = i14;
                        }
                    }
                    if (i13 == -1) {
                        return false;
                    }
                    int i15 = this.bp + 6;
                    long j = Long.parseLong(subString(i15, (this.bp + i13) - i15));
                    this.calendar = Calendar.getInstance(this.timeZone, this.locale);
                    this.calendar.setTimeInMillis(j);
                    this.token = 5;
                    return true;
                }
            }
            i12 = 5;
        }
        if (i == 8 || i == 14) {
            i2 = i12;
            z2 = false;
            c = Operators.CONDITION_IF_MIDDLE;
        } else {
            if (!(i == 16 && ((cCharAt8 = charAt(this.bp + 10)) == 'T' || cCharAt8 == ' ')) && (i != 17 || charAt(this.bp + 6) == '-')) {
                if (i < 9) {
                    return false;
                }
                char cCharAt20 = charAt(this.bp + 8);
                char cCharAt21 = charAt(this.bp + 9);
                if ((cCharAt13 == '-' && cCharAt16 == '-') || (cCharAt13 == '/' && cCharAt16 == '/')) {
                    if (cCharAt21 == ' ') {
                        c10 = cCharAt14;
                        c11 = cCharAt12;
                        c12 = cCharAt9;
                        c13 = cCharAt10;
                        c14 = '0';
                        i8 = 9;
                        cCharAt10 = cCharAt20;
                        c16 = cCharAt15;
                        c15 = cCharAt11;
                    } else {
                        c11 = cCharAt12;
                        c12 = cCharAt9;
                        i8 = 10;
                        c14 = cCharAt20;
                        c16 = cCharAt15;
                        c15 = cCharAt11;
                        c10 = cCharAt14;
                        c13 = cCharAt10;
                        cCharAt10 = cCharAt21;
                    }
                } else if (cCharAt13 == '-' && cCharAt15 == '-') {
                    if (cCharAt20 == ' ') {
                        c15 = cCharAt11;
                        c16 = cCharAt14;
                        c12 = cCharAt9;
                        c13 = cCharAt10;
                        c10 = '0';
                        i8 = 8;
                        cCharAt10 = cCharAt16;
                        c11 = cCharAt12;
                        c14 = '0';
                    } else {
                        c15 = cCharAt11;
                        c12 = cCharAt9;
                        c10 = '0';
                        i8 = 9;
                        c16 = cCharAt14;
                        c13 = cCharAt10;
                        cCharAt10 = cCharAt20;
                        c14 = cCharAt16;
                        c11 = cCharAt12;
                    }
                } else if ((cCharAt11 == '.' && cCharAt14 == '.') || (cCharAt11 == '-' && cCharAt14 == '-')) {
                    c13 = cCharAt16;
                    c11 = cCharAt21;
                    c10 = cCharAt12;
                    c14 = cCharAt9;
                    i8 = 10;
                    c16 = cCharAt13;
                    c12 = cCharAt15;
                    c15 = cCharAt20;
                } else if (cCharAt20 == 'T') {
                    c10 = cCharAt13;
                    c16 = cCharAt14;
                    c12 = cCharAt9;
                    c13 = cCharAt10;
                    i8 = 8;
                    cCharAt10 = cCharAt16;
                    c11 = cCharAt12;
                    c14 = cCharAt15;
                    c15 = cCharAt11;
                } else {
                    if (cCharAt13 != 24180 && cCharAt13 != 45380) {
                        return false;
                    }
                    if (cCharAt16 != 26376 && cCharAt16 != 50900) {
                        if (cCharAt15 != 26376 && cCharAt15 != 50900) {
                            return false;
                        }
                        if (cCharAt20 == 26085 || cCharAt20 == 51068) {
                            c15 = cCharAt11;
                            c16 = cCharAt14;
                            c12 = cCharAt9;
                            c13 = cCharAt10;
                            i8 = 10;
                            c10 = '0';
                            cCharAt10 = cCharAt16;
                            c11 = cCharAt12;
                            c14 = '0';
                        } else {
                            if (cCharAt21 != 26085 && cCharAt21 != 51068) {
                                return false;
                            }
                            c15 = cCharAt11;
                            c12 = cCharAt9;
                            i8 = 10;
                            c10 = '0';
                            c16 = cCharAt14;
                            c13 = cCharAt10;
                            cCharAt10 = cCharAt20;
                            c14 = cCharAt16;
                            c11 = cCharAt12;
                        }
                    } else if (cCharAt21 == 26085 || cCharAt21 == 51068) {
                        c10 = cCharAt14;
                        c11 = cCharAt12;
                        c12 = cCharAt9;
                        c13 = cCharAt10;
                        i8 = 10;
                        c14 = '0';
                        cCharAt10 = cCharAt20;
                        c16 = cCharAt15;
                        c15 = cCharAt11;
                    } else {
                        if (charAt(this.bp + 10) != 26085 && charAt(this.bp + 10) != 51068) {
                            return false;
                        }
                        c11 = cCharAt12;
                        c12 = cCharAt9;
                        i8 = 11;
                        c14 = cCharAt20;
                        c16 = cCharAt15;
                        c15 = cCharAt11;
                        c10 = cCharAt14;
                        c13 = cCharAt10;
                        cCharAt10 = cCharAt21;
                    }
                }
                if (!checkDate(c12, c13, c15, c11, c10, c16, c14, cCharAt10)) {
                    return false;
                }
                setCalendar(c12, c13, c15, c11, c10, c16, c14, cCharAt10);
                char cCharAt22 = charAt(this.bp + i8);
                char c17 = 'T';
                if (cCharAt22 == 'T') {
                    if (i == 16 && i8 == 8 && charAt(this.bp + 15) == 'Z') {
                        char cCharAt23 = charAt(this.bp + i8 + 1);
                        char cCharAt24 = charAt(this.bp + i8 + 2);
                        char cCharAt25 = charAt(this.bp + i8 + 3);
                        char cCharAt26 = charAt(this.bp + i8 + 4);
                        char cCharAt27 = charAt(this.bp + i8 + 5);
                        char cCharAt28 = charAt(this.bp + i8 + 6);
                        if (!checkTime(cCharAt23, cCharAt24, cCharAt25, cCharAt26, cCharAt27, cCharAt28)) {
                            return false;
                        }
                        setTime(cCharAt23, cCharAt24, cCharAt25, cCharAt26, cCharAt27, cCharAt28);
                        this.calendar.set(14, 0);
                        if (this.calendar.getTimeZone().getRawOffset() != 0) {
                            String[] availableIDs = TimeZone.getAvailableIDs(0);
                            if (availableIDs.length > 0) {
                                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
                            }
                        }
                        this.token = 5;
                        return true;
                    }
                    c17 = 'T';
                }
                if (cCharAt22 != c17 && (cCharAt22 != ' ' || z)) {
                    if (cCharAt22 == '\"' || cCharAt22 == 26 || cCharAt22 == 26085 || cCharAt22 == 51068) {
                        this.calendar.set(11, 0);
                        this.calendar.set(12, 0);
                        this.calendar.set(13, 0);
                        this.calendar.set(14, 0);
                        int i16 = this.bp + i8;
                        this.bp = i16;
                        this.ch = charAt(i16);
                        this.token = 5;
                        return true;
                    }
                    if ((cCharAt22 != '+' && cCharAt22 != '-') || this.len != i8 + 6 || charAt(this.bp + i8 + 3) != ':' || charAt(this.bp + i8 + 4) != '0' || charAt(this.bp + i8 + 5) != '0') {
                        return false;
                    }
                    setTime('0', '0', '0', '0', '0', '0');
                    this.calendar.set(14, 0);
                    setTimeZone(cCharAt22, charAt(this.bp + i8 + 1), charAt(this.bp + i8 + 2));
                    return true;
                }
                if (i < i8 + 9 || charAt(this.bp + i8 + 3) != ':' || charAt(this.bp + i8 + 6) != ':') {
                    return false;
                }
                char cCharAt29 = charAt(this.bp + i8 + 1);
                char cCharAt30 = charAt(this.bp + i8 + 2);
                char cCharAt31 = charAt(this.bp + i8 + 4);
                char cCharAt32 = charAt(this.bp + i8 + 5);
                char cCharAt33 = charAt(this.bp + i8 + 7);
                char cCharAt34 = charAt(this.bp + i8 + 8);
                if (!checkTime(cCharAt29, cCharAt30, cCharAt31, cCharAt32, cCharAt33, cCharAt34)) {
                    return false;
                }
                setTime(cCharAt29, cCharAt30, cCharAt31, cCharAt32, cCharAt33, cCharAt34);
                if (charAt(this.bp + i8 + 9) == '.') {
                    int i17 = i8 + 11;
                    if (i < i17 || (cCharAt5 = charAt(this.bp + i8 + 10)) < '0' || cCharAt5 > '9') {
                        return false;
                    }
                    int i18 = cCharAt5 - '0';
                    if (i <= i17 || (cCharAt7 = charAt(this.bp + i8 + 11)) < '0' || cCharAt7 > '9') {
                        i9 = 1;
                    } else {
                        i18 = (i18 * 10) + (cCharAt7 - '0');
                        i9 = 2;
                    }
                    if (i9 != 2 || (cCharAt6 = charAt(this.bp + i8 + 12)) < '0' || cCharAt6 > '9') {
                        i10 = i18;
                    } else {
                        i10 = (cCharAt6 - '0') + (i18 * 10);
                        i9 = 3;
                    }
                } else {
                    i9 = -1;
                    i10 = 0;
                }
                this.calendar.set(14, i10);
                char cCharAt35 = charAt(this.bp + i8 + 10 + i9);
                if (cCharAt35 == ' ') {
                    i9++;
                    cCharAt35 = charAt(this.bp + i8 + 10 + i9);
                }
                char c18 = cCharAt35;
                if (c18 == '+' || c18 == '-') {
                    char cCharAt36 = charAt(this.bp + i8 + 10 + i9 + 1);
                    if (cCharAt36 < '0' || cCharAt36 > '1' || (cCharAt3 = charAt(this.bp + i8 + 10 + i9 + 2)) < '0' || cCharAt3 > '9') {
                        return false;
                    }
                    char cCharAt37 = charAt(this.bp + i8 + 10 + i9 + 3);
                    char c19 = '3';
                    if (cCharAt37 == ':') {
                        char cCharAt38 = charAt(this.bp + i8 + 10 + i9 + 4);
                        cCharAt4 = charAt(this.bp + i8 + 10 + i9 + 5);
                        if (cCharAt38 == '4' && cCharAt4 == '5') {
                            if (cCharAt36 != '1' || (cCharAt3 != '2' && cCharAt3 != '3')) {
                                if (cCharAt36 != '0') {
                                    return false;
                                }
                                if (cCharAt3 != '5' && cCharAt3 != '8') {
                                    return false;
                                }
                            }
                        } else if ((cCharAt38 != '0' && cCharAt38 != '3') || cCharAt4 != '0') {
                            return false;
                        }
                        c19 = cCharAt38;
                        i11 = 6;
                    } else {
                        if (cCharAt37 == '0') {
                            char cCharAt39 = charAt(this.bp + i8 + 10 + i9 + 4);
                            if (cCharAt39 != '0' && cCharAt39 != '3') {
                                return false;
                            }
                            c19 = cCharAt39;
                        } else {
                            if (cCharAt37 != '3' || charAt(this.bp + i8 + 10 + i9 + 4) != '0') {
                                if (cCharAt37 == '4' && charAt(this.bp + i8 + 10 + i9 + 4) == '5') {
                                    cCharAt4 = '5';
                                    i11 = 5;
                                    c19 = '4';
                                } else {
                                    c19 = '0';
                                }
                            }
                            cCharAt4 = '0';
                        }
                        i11 = 5;
                        cCharAt4 = '0';
                    }
                    setTimeZone(c18, cCharAt36, cCharAt3, c19, cCharAt4);
                } else if (c18 == 'Z') {
                    if (this.calendar.getTimeZone().getRawOffset() != 0) {
                        String[] availableIDs2 = TimeZone.getAvailableIDs(0);
                        if (availableIDs2.length > 0) {
                            this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs2[0]));
                        }
                    }
                    i11 = 1;
                } else {
                    i11 = 0;
                }
                int i19 = i8 + 10 + i9 + i11;
                char cCharAt40 = charAt(this.bp + i19);
                if (cCharAt40 != 26 && cCharAt40 != '\"') {
                    return false;
                }
                int i20 = this.bp + i19;
                this.bp = i20;
                this.ch = charAt(i20);
                this.token = 5;
                return true;
            }
            z2 = false;
            c = Operators.CONDITION_IF_MIDDLE;
            i2 = 5;
        }
        if (z) {
            return z2;
        }
        char cCharAt41 = charAt(this.bp + 8);
        boolean z3 = cCharAt13 == '-' && cCharAt16 == '-';
        boolean z4 = z3 && i == 16;
        boolean z5 = z3 && i == 17;
        if (z5 || z4) {
            cCharAt = charAt(this.bp + 9);
            c2 = cCharAt14;
            c3 = cCharAt15;
            c4 = cCharAt41;
        } else if (cCharAt13 == '-' && cCharAt15 == '-') {
            c3 = cCharAt14;
            cCharAt = cCharAt16;
            c2 = '0';
            c4 = '0';
        } else {
            c2 = cCharAt13;
            c3 = cCharAt14;
            c4 = cCharAt15;
            cCharAt = cCharAt16;
        }
        if (!checkDate(cCharAt9, cCharAt10, cCharAt11, cCharAt12, c2, c3, c4, cCharAt)) {
            return false;
        }
        setCalendar(cCharAt9, cCharAt10, cCharAt11, cCharAt12, c2, c3, c4, cCharAt);
        if (i != 8) {
            char cCharAt42 = charAt(this.bp + 9);
            char cCharAt43 = charAt(this.bp + 10);
            char cCharAt44 = charAt(this.bp + 11);
            char cCharAt45 = charAt(this.bp + 12);
            char cCharAt46 = charAt(this.bp + 13);
            if ((z5 && cCharAt43 == 'T' && cCharAt46 == c && charAt(this.bp + 16) == 'Z') || (z4 && ((cCharAt43 == ' ' || cCharAt43 == 'T') && cCharAt46 == c))) {
                char cCharAt47 = charAt(this.bp + 14);
                cCharAt2 = charAt(this.bp + 15);
                c6 = cCharAt47;
                c8 = cCharAt44;
                c5 = cCharAt45;
                c9 = '0';
                c7 = '0';
            } else {
                c5 = cCharAt42;
                c6 = cCharAt43;
                cCharAt2 = cCharAt44;
                c7 = cCharAt46;
                c8 = cCharAt41;
                c9 = cCharAt45;
            }
            if (!checkTime(c8, c5, c6, cCharAt2, c9, c7)) {
                return false;
            }
            if (i != 17 || z5) {
                i7 = 0;
            } else {
                char cCharAt48 = charAt(this.bp + 14);
                char cCharAt49 = charAt(this.bp + 15);
                char cCharAt50 = charAt(this.bp + 16);
                if (cCharAt48 < '0' || cCharAt48 > '9' || cCharAt49 < '0' || cCharAt49 > '9' || cCharAt50 < '0' || cCharAt50 > '9') {
                    return false;
                }
                i7 = ((cCharAt48 - '0') * 100) + ((cCharAt49 - '0') * 10) + (cCharAt50 - '0');
            }
            i4 = ((c9 - '0') * 10) + (c7 - '0');
            i6 = ((c8 - '0') * 10) + (c5 - '0');
            i5 = i7;
            i3 = ((c6 - '0') * 10) + (cCharAt2 - '0');
        } else {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        this.calendar.set(11, i6);
        this.calendar.set(12, i3);
        this.calendar.set(13, i4);
        this.calendar.set(14, i5);
        this.token = i2;
        return true;
    }

    protected void setTime(char c, char c2, char c3, char c4, char c5, char c6) {
        this.calendar.set(11, ((c - '0') * 10) + (c2 - '0'));
        this.calendar.set(12, ((c3 - '0') * 10) + (c4 - '0'));
        this.calendar.set(13, ((c5 - '0') * 10) + (c6 - '0'));
    }

    protected void setTimeZone(char c, char c2, char c3) {
        setTimeZone(c, c2, c3, '0', '0');
    }

    protected void setTimeZone(char c, char c2, char c3, char c4, char c5) {
        int i = ((((c2 - '0') * 10) + (c3 - '0')) * 3600000) + ((((c4 - '0') * 10) + (c5 - '0')) * WXRequest.DEFAULT_TIMEOUT_MS);
        if (c == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            this.calendar.setTimeZone(new SimpleTimeZone(i, Integer.toString(i)));
        }
    }

    private void setCalendar(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar.set(1, ((c - '0') * 1000) + ((c2 - '0') * 100) + ((c3 - '0') * 10) + (c4 - '0'));
        this.calendar.set(2, (((c5 - '0') * 10) + (c6 - '0')) - 1);
        this.calendar.set(5, ((c7 - '0') * 10) + (c8 - '0'));
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.bp != this.len) {
            return this.ch == 26 && this.bp + 1 >= this.len;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0065, code lost:
    
        if (r12 != '.') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
    
        r14.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0069, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
    
        if (r4 >= 0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006c, code lost:
    
        r14.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006e, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006f, code lost:
    
        if (r7 == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0071, code lost:
    
        if (r12 == '\"') goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0073, code lost:
    
        r14.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0075, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0076, code lost:
    
        r15 = r15 + 2;
        r12 = charAt(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007c, code lost:
    
        r11 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0081, code lost:
    
        if (r12 == ',') goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0083, code lost:
    
        if (r12 != '}') goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x008a, code lost:
    
        if (isWhitespace(r12) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008c, code lost:
    
        r15 = r11 + 1;
        r12 = charAt(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0093, code lost:
    
        r14.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0095, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0096, code lost:
    
        r11 = r11 - 1;
        r14.bp = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x009b, code lost:
    
        if (r12 != ',') goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009d, code lost:
    
        r15 = r14.bp + 1;
        r14.bp = r15;
        r14.ch = charAt(r15);
        r14.matchStat = 3;
        r14.token = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ad, code lost:
    
        if (r3 == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b0, code lost:
    
        return -r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00b1, code lost:
    
        if (r12 != '}') goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b3, code lost:
    
        r14.bp = r11;
        r8 = r14.bp + 1;
        r14.bp = r8;
        r8 = charAt(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00be, code lost:
    
        if (r8 != ',') goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00c0, code lost:
    
        r14.token = 16;
        r15 = r14.bp + 1;
        r14.bp = r15;
        r14.ch = charAt(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d0, code lost:
    
        if (r8 != ']') goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d2, code lost:
    
        r14.token = 15;
        r15 = r14.bp + 1;
        r14.bp = r15;
        r14.ch = charAt(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00e2, code lost:
    
        if (r8 != '}') goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e4, code lost:
    
        r14.token = 13;
        r15 = r14.bp + 1;
        r14.bp = r15;
        r14.ch = charAt(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00f6, code lost:
    
        if (r8 != 26) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f8, code lost:
    
        r14.token = 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00fc, code lost:
    
        r14.matchStat = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0104, code lost:
    
        if (isWhitespace(r8) == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0106, code lost:
    
        r8 = r14.bp + 1;
        r14.bp = r8;
        r8 = charAt(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0110, code lost:
    
        r14.bp = r1;
        r14.ch = r2;
        r14.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0116, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0117, code lost:
    
        if (r3 == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x011a, code lost:
    
        return -r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x007c, code lost:
    
        r11 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:?, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:?, code lost:
    
        return r4;
     */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int scanFieldInt(char[] r15) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldInt(char[]):int");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        while (!charArrayCompare(this.text, this.bp, cArr)) {
            if (isWhitespace(this.ch)) {
                next();
                while (isWhitespace(this.ch)) {
                    next();
                }
            } else {
                this.matchStat = -2;
                return stringDefaultValue();
            }
        }
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        char cCharAt = charAt(length);
        int i3 = 0;
        if (cCharAt != '\"') {
            while (isWhitespace(cCharAt)) {
                i3++;
                int i4 = i2 + 1;
                char cCharAt2 = charAt(i2);
                i2 = i4;
                cCharAt = cCharAt2;
            }
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        int iIndexOf = indexOf('\"', i2);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        String strSubString = subString(i2, iIndexOf - i2);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i5 = 0;
                for (int i6 = iIndexOf - 1; i6 >= 0 && charAt(i6) == '\\'; i6--) {
                    i5++;
                }
                if (i5 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf('\"', iIndexOf + 1);
            }
            int length2 = iIndexOf - (((this.bp + cArr.length) + 1) + i3);
            strSubString = readString(sub_chars(this.bp + cArr.length + 1 + i3, length2), length2);
        }
        if ((this.features & Feature.TrimStringFieldValue.mask) != 0) {
            strSubString = strSubString.trim();
        }
        char cCharAt3 = charAt(iIndexOf + 1);
        while (cCharAt3 != ',' && cCharAt3 != '}') {
            if (isWhitespace(cCharAt3)) {
                char cCharAt4 = charAt(iIndexOf + 2);
                iIndexOf++;
                cCharAt3 = cCharAt4;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        this.bp = iIndexOf + 1;
        this.ch = cCharAt3;
        if (cCharAt3 == ',') {
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
            this.matchStat = 3;
            return strSubString;
        }
        int i8 = this.bp + 1;
        this.bp = i8;
        char cCharAt5 = charAt(i8);
        if (cCharAt5 == ',') {
            this.token = 16;
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
        } else if (cCharAt5 == ']') {
            this.token = 15;
            int i10 = this.bp + 1;
            this.bp = i10;
            this.ch = charAt(i10);
        } else if (cCharAt5 == '}') {
            this.token = 13;
            int i11 = this.bp + 1;
            this.bp = i11;
            this.ch = charAt(i11);
        } else if (cCharAt5 == 26) {
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c;
            this.matchStat = -1;
            return stringDefaultValue();
        }
        this.matchStat = 4;
        return strSubString;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanFieldDate(char[] cArr) {
        long j;
        Date date;
        char cCharAt;
        char cCharAt2;
        boolean z = false;
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        char cCharAt3 = charAt(length);
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf('\"', i2);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.bp = i2;
            if (scanISO8601DateIfMatch(false, iIndexOf - i2)) {
                date = this.calendar.getTime();
                cCharAt = charAt(iIndexOf + 1);
                this.bp = i;
                while (cCharAt != ',' && cCharAt != '}') {
                    if (isWhitespace(cCharAt)) {
                        int i3 = iIndexOf + 1;
                        char cCharAt4 = charAt(iIndexOf + 2);
                        iIndexOf = i3;
                        cCharAt = cCharAt4;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp = iIndexOf + 1;
                this.ch = cCharAt;
            } else {
                this.bp = i;
                this.matchStat = -1;
                return null;
            }
        } else {
            char c2 = '9';
            char c3 = '0';
            if (cCharAt3 != '-' && (cCharAt3 < '0' || cCharAt3 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt3 == '-') {
                cCharAt3 = charAt(i2);
                i2 = length + 2;
                z = true;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                j = 0;
            } else {
                long j2 = cCharAt3 - '0';
                while (true) {
                    int i4 = i2 + 1;
                    cCharAt2 = charAt(i2);
                    if (cCharAt2 < c3 || cCharAt2 > c2) {
                        break;
                    }
                    j2 = (j2 * 10) + (cCharAt2 - '0');
                    i2 = i4;
                    c2 = '9';
                    c3 = '0';
                }
                if (cCharAt2 == ',' || cCharAt2 == '}') {
                    this.bp = i2;
                }
                long j3 = j2;
                cCharAt3 = cCharAt2;
                j = j3;
            }
            if (j < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
            cCharAt = cCharAt3;
        }
        if (cCharAt == ',') {
            int i5 = this.bp + 1;
            this.bp = i5;
            this.ch = charAt(i5);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        int i6 = this.bp + 1;
        this.bp = i6;
        char cCharAt5 = charAt(i6);
        if (cCharAt5 == ',') {
            this.token = 16;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (cCharAt5 == ']') {
            this.token = 15;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (cCharAt5 == '}') {
            this.token = 13;
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
        } else if (cCharAt5 == 26) {
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        while (!charArrayCompare(this.text, this.bp, cArr)) {
            if (isWhitespace(this.ch)) {
                next();
                while (isWhitespace(this.ch)) {
                    next();
                }
            } else {
                this.matchStat = -2;
                return 0L;
            }
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        char cCharAt = charAt(length);
        if (cCharAt != '\"') {
            while (isWhitespace(cCharAt)) {
                cCharAt = charAt(i);
                i++;
            }
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0L;
            }
        }
        long j = TypeUtils.fnv1a_64_magic_hashcode;
        while (true) {
            int i2 = i + 1;
            char cCharAt2 = charAt(i);
            if (cCharAt2 == '\"') {
                this.bp = i2;
                char cCharAt3 = charAt(this.bp);
                this.ch = cCharAt3;
                while (cCharAt3 != ',') {
                    if (cCharAt3 == '}') {
                        next();
                        skipWhitespace();
                        char current = getCurrent();
                        if (current == ',') {
                            this.token = 16;
                            int i3 = this.bp + 1;
                            this.bp = i3;
                            this.ch = charAt(i3);
                        } else if (current == ']') {
                            this.token = 15;
                            int i4 = this.bp + 1;
                            this.bp = i4;
                            this.ch = charAt(i4);
                        } else if (current == '}') {
                            this.token = 13;
                            int i5 = this.bp + 1;
                            this.bp = i5;
                            this.ch = charAt(i5);
                        } else if (current == 26) {
                            this.token = 20;
                        } else {
                            this.matchStat = -1;
                            return 0L;
                        }
                        this.matchStat = 4;
                        return j;
                    }
                    if (isWhitespace(cCharAt3)) {
                        int i6 = this.bp + 1;
                        this.bp = i6;
                        cCharAt3 = charAt(i6);
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                int i7 = this.bp + 1;
                this.bp = i7;
                this.ch = charAt(i7);
                this.matchStat = 3;
                return j;
            }
            if (i2 > this.len) {
                this.matchStat = -1;
                return 0L;
            }
            j = (j ^ cCharAt2) * TypeUtils.fnv1a_64_magic_prime;
            i = i2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00de, code lost:
    
        if (r1 != ']') goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e4, code lost:
    
        if (r3.size() != 0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e6, code lost:
    
        r2 = r9 + 1;
        r1 = charAt(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ed, code lost:
    
        r17.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ef, code lost:
    
        return null;
     */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Collection<java.lang.String> scanFieldStringArray(char[] r18, java.lang.Class<?> r19) {
        /*
            Method dump skipped, instructions count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldStringArray(char[], java.lang.Class):java.util.Collection");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldLong(char[] cArr) {
        int i;
        char cCharAt;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        char c = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.bp + cArr.length;
        int i3 = length + 1;
        char cCharAt2 = charAt(length);
        boolean z2 = cCharAt2 == '\"';
        if (z2) {
            cCharAt2 = charAt(i3);
            i3 = length + 2;
        }
        if (cCharAt2 == '-') {
            cCharAt2 = charAt(i3);
            i3++;
            z = true;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.bp = i2;
            this.ch = c;
            this.matchStat = -1;
            return 0L;
        }
        long j = cCharAt2 - '0';
        while (true) {
            i = i3 + 1;
            cCharAt = charAt(i3);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j = (j * 10) + (cCharAt - '0');
            i3 = i;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0L;
        }
        if (z2) {
            if (cCharAt != '\"') {
                this.matchStat = -1;
                return 0L;
            }
            cCharAt = charAt(i);
            i = i3 + 2;
        }
        if (cCharAt == ',' || cCharAt == '}') {
            this.bp = i - 1;
        }
        if (j < 0 && (j != Long.MIN_VALUE || !z)) {
            this.bp = i2;
            this.ch = c;
            this.matchStat = -1;
            return 0L;
        }
        while (cCharAt != ',') {
            if (cCharAt == '}') {
                int i4 = 1;
                int i5 = this.bp + 1;
                this.bp = i5;
                char cCharAt3 = charAt(i5);
                while (true) {
                    if (cCharAt3 == ',') {
                        this.token = 16;
                        int i6 = this.bp + i4;
                        this.bp = i6;
                        this.ch = charAt(i6);
                        break;
                    }
                    if (cCharAt3 == ']') {
                        this.token = 15;
                        int i7 = this.bp + i4;
                        this.bp = i7;
                        this.ch = charAt(i7);
                        break;
                    }
                    if (cCharAt3 == '}') {
                        this.token = 13;
                        int i8 = this.bp + i4;
                        this.bp = i8;
                        this.ch = charAt(i8);
                        break;
                    }
                    if (cCharAt3 == 26) {
                        this.token = 20;
                        break;
                    }
                    if (isWhitespace(cCharAt3)) {
                        int i9 = this.bp + 1;
                        this.bp = i9;
                        cCharAt3 = charAt(i9);
                        i4 = 1;
                    } else {
                        this.bp = i2;
                        this.ch = c;
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                this.matchStat = 4;
                return z ? -j : j;
            }
            if (isWhitespace(cCharAt)) {
                this.bp = i;
                cCharAt = charAt(i);
                i++;
            } else {
                this.matchStat = -1;
                return 0L;
            }
        }
        int i10 = this.bp + 1;
        this.bp = i10;
        this.ch = charAt(i10);
        this.matchStat = 3;
        this.token = 16;
        return z ? -j : j;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x00fb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x010c  */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scanFieldBoolean(char[] r12) {
        /*
            Method dump skipped, instructions count: 395
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldBoolean(char[]):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x007d, code lost:
    
        if (r13 != '.') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
    
        r16.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0081, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0082, code lost:
    
        if (r7 == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0084, code lost:
    
        if (r13 == '\"') goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0086, code lost:
    
        r16.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0088, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0089, code lost:
    
        r13 = charAt(r12);
        r12 = r4 + 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0090, code lost:
    
        if (r3 >= 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0092, code lost:
    
        r16.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0094, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0097, code lost:
    
        if (r13 != r17) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0099, code lost:
    
        r16.bp = r12;
        r16.ch = charAt(r16.bp);
        r16.matchStat = 3;
        r16.token = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a8, code lost:
    
        if (r6 == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ab, code lost:
    
        return -r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b0, code lost:
    
        if (isWhitespace(r13) == false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b2, code lost:
    
        r13 = charAt(r12);
        r12 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ba, code lost:
    
        r16.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00bc, code lost:
    
        if (r6 == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00bf, code lost:
    
        return -r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:?, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:?, code lost:
    
        return r3;
     */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int scanInt(char r17) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanInt(char):int");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public double scanDouble(char c) throws NumberFormatException {
        int i;
        char cCharAt;
        long j;
        int i2;
        int i3;
        double d;
        char cCharAt2;
        this.matchStat = 0;
        int i4 = this.bp;
        int i5 = i4 + 1;
        char cCharAt3 = charAt(i4);
        boolean z = cCharAt3 == '\"';
        if (z) {
            cCharAt3 = charAt(i5);
            i5 = i4 + 2;
        }
        boolean z2 = cCharAt3 == '-';
        if (z2) {
            cCharAt3 = charAt(i5);
            i5++;
        }
        if (cCharAt3 >= '0') {
            char c2 = '9';
            if (cCharAt3 <= '9') {
                long j2 = cCharAt3 - '0';
                while (true) {
                    i = i5 + 1;
                    cCharAt = charAt(i5);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j2 = (j2 * 10) + (cCharAt - '0');
                    i5 = i;
                }
                if (cCharAt == '.') {
                    int i6 = i5 + 2;
                    char cCharAt4 = charAt(i);
                    if (cCharAt4 < '0' || cCharAt4 > '9') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    j2 = (j2 * 10) + (cCharAt4 - '0');
                    long j3 = 10;
                    while (true) {
                        i = i6 + 1;
                        cCharAt2 = charAt(i6);
                        if (cCharAt2 < '0' || cCharAt2 > c2) {
                            break;
                        }
                        j2 = (j2 * 10) + (cCharAt2 - '0');
                        j3 *= 10;
                        i6 = i;
                        c2 = '9';
                    }
                    long j4 = j3;
                    cCharAt = cCharAt2;
                    j = j4;
                } else {
                    j = 1;
                }
                boolean z3 = cCharAt == 'e' || cCharAt == 'E';
                if (z3) {
                    int i7 = i + 1;
                    char cCharAt5 = charAt(i);
                    if (cCharAt5 == '+' || cCharAt5 == '-') {
                        i += 2;
                        cCharAt = charAt(i7);
                    } else {
                        i = i7;
                        cCharAt = cCharAt5;
                    }
                    while (cCharAt >= '0' && cCharAt <= '9') {
                        int i8 = i + 1;
                        char cCharAt6 = charAt(i);
                        i = i8;
                        cCharAt = cCharAt6;
                    }
                }
                if (!z) {
                    i2 = this.bp;
                    i3 = (i - i2) - 1;
                } else {
                    if (cCharAt != '\"') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    int i9 = i + 1;
                    char cCharAt7 = charAt(i);
                    i2 = this.bp + 1;
                    i3 = (i9 - i2) - 2;
                    i = i9;
                    cCharAt = cCharAt7;
                }
                if (z3 || i3 >= 18) {
                    d = Double.parseDouble(subString(i2, i3));
                } else {
                    d = j2 / j;
                    if (z2) {
                        d = -d;
                    }
                }
                if (cCharAt == c) {
                    this.bp = i;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    this.token = 16;
                    return d;
                }
                this.matchStat = -1;
                return d;
            }
        }
        if (cCharAt3 == 'n') {
            int i10 = i5 + 1;
            if (charAt(i5) == 'u') {
                int i11 = i5 + 2;
                if (charAt(i10) == 'l') {
                    int i12 = i5 + 3;
                    if (charAt(i11) == 'l') {
                        this.matchStat = 5;
                        int i13 = i5 + 4;
                        char cCharAt8 = charAt(i12);
                        if (z && cCharAt8 == '\"') {
                            cCharAt8 = charAt(i13);
                            i13 = i5 + 5;
                        }
                        while (cCharAt8 != ',') {
                            if (cCharAt8 == ']') {
                                this.bp = i13;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0.0d;
                            }
                            if (isWhitespace(cCharAt8)) {
                                char cCharAt9 = charAt(i13);
                                i13++;
                                cCharAt8 = cCharAt9;
                            } else {
                                this.matchStat = -1;
                                return 0.0d;
                            }
                        }
                        this.bp = i13;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0.0d;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0.0d;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char c) {
        int i;
        char cCharAt;
        this.matchStat = 0;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char cCharAt2 = charAt(i2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(i3);
            i3 = i2 + 2;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(i3);
            i3++;
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            long j = cCharAt2 - '0';
            while (true) {
                i = i3 + 1;
                cCharAt = charAt(i3);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                j = (j * 10) + (cCharAt - '0');
                i3 = i;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (z) {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                cCharAt = charAt(i);
                i = i3 + 2;
            }
            if (j < 0 && (j != Long.MIN_VALUE || !z2)) {
                this.matchStat = -1;
                return 0L;
            }
            while (cCharAt != c) {
                if (isWhitespace(cCharAt)) {
                    cCharAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return j;
                }
            }
            this.bp = i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -j : j;
        }
        if (cCharAt2 == 'n') {
            int i4 = i3 + 1;
            if (charAt(i3) == 'u') {
                int i5 = i3 + 2;
                if (charAt(i4) == 'l') {
                    int i6 = i3 + 3;
                    if (charAt(i5) == 'l') {
                        this.matchStat = 5;
                        int i7 = i3 + 4;
                        char cCharAt3 = charAt(i6);
                        if (z && cCharAt3 == '\"') {
                            cCharAt3 = charAt(i7);
                            i7 = i3 + 5;
                        }
                        while (cCharAt3 != ',') {
                            if (cCharAt3 == ']') {
                                this.bp = i7;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0L;
                            }
                            if (isWhitespace(cCharAt3)) {
                                char cCharAt4 = charAt(i7);
                                i7++;
                                cCharAt3 = cCharAt4;
                            } else {
                                this.matchStat = -1;
                                return 0L;
                            }
                        }
                        this.bp = i7;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0L;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0L;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanDate(char c) {
        char cCharAt;
        long j;
        Date date;
        char cCharAt2;
        boolean z = false;
        this.matchStat = 0;
        int i = this.bp;
        char c2 = this.ch;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char cCharAt3 = charAt(i2);
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf('\"', i3);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.bp = i3;
            if (scanISO8601DateIfMatch(false, iIndexOf - i3)) {
                date = this.calendar.getTime();
                cCharAt = charAt(iIndexOf + 1);
                this.bp = i;
                while (cCharAt != ',' && cCharAt != ']') {
                    if (isWhitespace(cCharAt)) {
                        cCharAt = charAt(iIndexOf + 2);
                        iIndexOf++;
                    } else {
                        this.bp = i;
                        this.ch = c2;
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp = iIndexOf + 1;
                this.ch = cCharAt;
            } else {
                this.bp = i;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
        } else {
            if (cCharAt3 != '-' && (cCharAt3 < '0' || cCharAt3 > '9')) {
                if (cCharAt3 == 'n') {
                    int i4 = i2 + 2;
                    if (charAt(i3) == 'u') {
                        int i5 = i2 + 3;
                        if (charAt(i4) == 'l') {
                            int i6 = i2 + 4;
                            if (charAt(i5) == 'l') {
                                cCharAt = charAt(i6);
                                this.bp = i6;
                                date = null;
                            }
                        }
                    }
                }
                this.bp = i;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
            if (cCharAt3 == '-') {
                cCharAt3 = charAt(i3);
                i3 = i2 + 2;
                z = true;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                cCharAt = cCharAt3;
                j = 0;
            } else {
                j = cCharAt3 - '0';
                while (true) {
                    int i7 = i3 + 1;
                    cCharAt2 = charAt(i3);
                    if (cCharAt2 < '0' || cCharAt2 > '9') {
                        break;
                    }
                    j = (j * 10) + (cCharAt2 - '0');
                    i3 = i7;
                }
                if (cCharAt2 == ',' || cCharAt2 == ']') {
                    this.bp = i3;
                }
                cCharAt = cCharAt2;
            }
            if (j < 0) {
                this.bp = i;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        }
        if (cCharAt == ',') {
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
            this.matchStat = 3;
            return date;
        }
        int i9 = this.bp + 1;
        this.bp = i9;
        char cCharAt4 = charAt(i9);
        if (cCharAt4 == ',') {
            this.token = 16;
            int i10 = this.bp + 1;
            this.bp = i10;
            this.ch = charAt(i10);
        } else if (cCharAt4 == ']') {
            this.token = 15;
            int i11 = this.bp + 1;
            this.bp = i11;
            this.ch = charAt(i11);
        } else if (cCharAt4 == '}') {
            this.token = 13;
            int i12 = this.bp + 1;
            this.bp = i12;
            this.ch = charAt(i12);
        } else if (cCharAt4 == 26) {
            this.ch = JSONLexer.EOI;
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c2;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i3 + i, cArr, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        int i2 = 1;
        int i3 = 0;
        while (i3 < this.bp) {
            if (this.text.charAt(i3) == '\n') {
                i++;
                i2 = 1;
            }
            i3++;
            i2++;
        }
        sb.append("pos ");
        sb.append(this.bp);
        sb.append(", line ");
        sb.append(i);
        sb.append(", column ");
        sb.append(i2);
        if (this.text.length() < 65535) {
            sb.append(this.text);
        } else {
            sb.append(this.text.substring(0, 65535));
        }
        return sb.toString();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        int i2;
        char cCharAt;
        int i3 = this.bp;
        char c = this.ch;
        while (isWhitespace(this.ch)) {
            next();
        }
        if (cArr != null) {
            this.matchStat = 0;
            if (!charArrayCompare(cArr)) {
                this.matchStat = -2;
                return null;
            }
            int length = this.bp + cArr.length;
            int i4 = length + 1;
            char cCharAt2 = this.text.charAt(length);
            while (isWhitespace(cCharAt2)) {
                cCharAt2 = this.text.charAt(i4);
                i4++;
            }
            if (cCharAt2 == ':') {
                i2 = i4 + 1;
                cCharAt = this.text.charAt(i4);
                while (isWhitespace(cCharAt)) {
                    cCharAt = this.text.charAt(i2);
                    i2++;
                }
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            i2 = this.bp + 1;
            cCharAt = this.ch;
        }
        if (cCharAt == '[') {
            this.bp = i2;
            this.ch = this.text.charAt(this.bp);
            String[] strArr = i >= 0 ? new String[i] : new String[4];
            int i5 = 0;
            while (true) {
                if (isWhitespace(this.ch)) {
                    next();
                } else {
                    if (this.ch != '\"') {
                        this.bp = i3;
                        this.ch = c;
                        this.matchStat = -1;
                        return null;
                    }
                    String strScanSymbol = scanSymbol(symbolTable, '\"');
                    if (i5 == strArr.length) {
                        String[] strArr2 = new String[strArr.length + (strArr.length >> 1) + 1];
                        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                        strArr = strArr2;
                    }
                    int i6 = i5 + 1;
                    strArr[i5] = strScanSymbol;
                    while (isWhitespace(this.ch)) {
                        next();
                    }
                    if (this.ch == ',') {
                        next();
                        i5 = i6;
                    } else {
                        if (strArr.length != i6) {
                            String[] strArr3 = new String[i6];
                            System.arraycopy(strArr, 0, strArr3, 0, i6);
                            strArr = strArr3;
                        }
                        while (isWhitespace(this.ch)) {
                            next();
                        }
                        if (this.ch == ']') {
                            next();
                            return strArr;
                        }
                        this.bp = i3;
                        this.ch = c;
                        this.matchStat = -1;
                        return null;
                    }
                }
            }
        } else {
            if (cCharAt == 'n' && this.text.startsWith("ull", this.bp + 1)) {
                this.bp += 4;
                this.ch = this.text.charAt(this.bp);
                return null;
            }
            this.matchStat = -1;
            return null;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean matchField2(char[] cArr) {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        char cCharAt = this.text.charAt(length);
        while (isWhitespace(cCharAt)) {
            cCharAt = this.text.charAt(i);
            i++;
        }
        if (cCharAt == ':') {
            this.bp = i;
            this.ch = charAt(this.bp);
            return true;
        }
        this.matchStat = -2;
        return false;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void skipObject() {
        skipObject(false);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void skipObject(boolean z) {
        int i = this.bp;
        boolean z2 = false;
        int i2 = 0;
        while (i < this.text.length()) {
            char cCharAt = this.text.charAt(i);
            if (cCharAt == '\\') {
                if (i >= this.len - 1) {
                    this.ch = cCharAt;
                    this.bp = i;
                    throw new JSONException("illegal str, " + info());
                }
                i++;
            } else if (cCharAt == '\"') {
                z2 = !z2;
            } else if (cCharAt != '{') {
                if (cCharAt == '}' && !z2 && i2 - 1 == -1) {
                    this.bp = i + 1;
                    int i3 = this.bp;
                    int length = this.text.length();
                    char cCharAt2 = JSONLexer.EOI;
                    if (i3 == length) {
                        this.ch = JSONLexer.EOI;
                        this.token = 20;
                        return;
                    }
                    this.ch = this.text.charAt(this.bp);
                    if (this.ch == ',') {
                        this.token = 16;
                        int i4 = this.bp + 1;
                        this.bp = i4;
                        if (i4 < this.text.length()) {
                            cCharAt2 = this.text.charAt(i4);
                        }
                        this.ch = cCharAt2;
                        return;
                    }
                    if (this.ch == '}') {
                        this.token = 13;
                        next();
                        return;
                    } else if (this.ch == ']') {
                        this.token = 15;
                        next();
                        return;
                    } else {
                        nextToken(16);
                        return;
                    }
                }
            } else if (!z2) {
                i2++;
            }
            i++;
        }
        for (int i5 = 0; i5 < this.bp; i5++) {
            if (i5 < this.text.length() && this.text.charAt(i5) == ' ') {
                i++;
            }
        }
        if (i != this.text.length()) {
            return;
        }
        throw new JSONException("illegal str, " + info());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void skipArray() {
        skipArray(false);
    }

    public final void skipArray(boolean z) {
        int i = this.bp;
        boolean z2 = false;
        int i2 = 0;
        while (i < this.text.length()) {
            char cCharAt = this.text.charAt(i);
            if (cCharAt == '\\') {
                if (i >= this.len - 1) {
                    this.ch = cCharAt;
                    this.bp = i;
                    throw new JSONException("illegal str, " + info());
                }
                i++;
            } else if (cCharAt == '\"') {
                z2 = !z2;
            } else if (cCharAt != '[') {
                char cCharAt2 = JSONLexer.EOI;
                if (cCharAt == '{' && z) {
                    int i3 = this.bp + 1;
                    this.bp = i3;
                    if (i3 < this.text.length()) {
                        cCharAt2 = this.text.charAt(i3);
                    }
                    this.ch = cCharAt2;
                    skipObject(z);
                } else if (cCharAt == ']' && !z2 && i2 - 1 == -1) {
                    this.bp = i + 1;
                    if (this.bp == this.text.length()) {
                        this.ch = JSONLexer.EOI;
                        this.token = 20;
                        return;
                    } else {
                        this.ch = this.text.charAt(this.bp);
                        nextToken(16);
                        return;
                    }
                }
            } else if (!z2) {
                i2++;
            }
            i++;
        }
        if (i != this.text.length()) {
            return;
        }
        throw new JSONException("illegal str, " + info());
    }

    public final void skipString() {
        if (this.ch == '\"') {
            int i = this.bp;
            while (true) {
                i++;
                if (i < this.text.length()) {
                    char cCharAt = this.text.charAt(i);
                    if (cCharAt == '\\') {
                        if (i < this.len - 1) {
                            i++;
                        }
                    } else if (cCharAt == '\"') {
                        String str = this.text;
                        int i2 = i + 1;
                        this.bp = i2;
                        this.ch = str.charAt(i2);
                        return;
                    }
                } else {
                    throw new JSONException("unclosed str");
                }
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean seekArrayToItem(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index must > 0, but " + i);
        }
        if (this.token == 20) {
            return false;
        }
        if (this.token != 14) {
            throw new UnsupportedOperationException();
        }
        for (int i2 = 0; i2 < i; i2++) {
            skipWhitespace();
            if (this.ch == '\"' || this.ch == '\'') {
                skipString();
                if (this.ch == ',') {
                    next();
                } else {
                    if (this.ch == ']') {
                        next();
                        nextToken(16);
                        return false;
                    }
                    throw new JSONException("illegal json.");
                }
            } else {
                if (this.ch == '{') {
                    next();
                    this.token = 12;
                    skipObject(false);
                } else if (this.ch == '[') {
                    next();
                    this.token = 14;
                    skipArray(false);
                } else {
                    for (int i3 = this.bp + 1; i3 < this.text.length(); i3++) {
                        char cCharAt = this.text.charAt(i3);
                        if (cCharAt == ',') {
                            this.bp = i3 + 1;
                            this.ch = charAt(this.bp);
                        } else {
                            if (cCharAt == ']') {
                                this.bp = i3 + 1;
                                this.ch = charAt(this.bp);
                                nextToken();
                                return false;
                            }
                        }
                    }
                    throw new JSONException("illegal json.");
                }
                if (this.token != 16) {
                    if (this.token == 15) {
                        return false;
                    }
                    throw new UnsupportedOperationException();
                }
            }
        }
        nextToken();
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int seekObjectToField(long j, boolean z) {
        int i = -1;
        if (this.token == 20) {
            return -1;
        }
        if (this.token != 13) {
            int i2 = 15;
            if (this.token != 15) {
                int i3 = 16;
                if (this.token != 12 && this.token != 16) {
                    throw new UnsupportedOperationException(JSONToken.name(this.token));
                }
                while (this.ch != '}') {
                    if (this.ch == 26) {
                        return i;
                    }
                    if (this.ch != '\"') {
                        skipWhitespace();
                    }
                    if (this.ch == '\"') {
                        int i4 = this.bp + 1;
                        long j2 = TypeUtils.fnv1a_64_magic_hashcode;
                        while (true) {
                            if (i4 >= this.text.length()) {
                                break;
                            }
                            char cCharAt = this.text.charAt(i4);
                            if (cCharAt == '\\') {
                                i4++;
                                if (i4 == this.text.length()) {
                                    throw new JSONException("unclosed str, " + info());
                                }
                                cCharAt = this.text.charAt(i4);
                            }
                            if (cCharAt == '\"') {
                                this.bp = i4 + 1;
                                this.ch = this.bp >= this.text.length() ? (char) 26 : this.text.charAt(this.bp);
                            } else {
                                j2 = (j2 ^ cCharAt) * TypeUtils.fnv1a_64_magic_prime;
                                i4++;
                            }
                        }
                        if (j2 == j) {
                            if (this.ch != ':') {
                                skipWhitespace();
                            }
                            if (this.ch != ':') {
                                return 3;
                            }
                            int i5 = this.bp + 1;
                            this.bp = i5;
                            this.ch = i5 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i5);
                            if (this.ch == ',') {
                                int i6 = this.bp + 1;
                                this.bp = i6;
                                this.ch = i6 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i6);
                                this.token = i3;
                                return 3;
                            }
                            if (this.ch == ']') {
                                int i7 = this.bp + 1;
                                this.bp = i7;
                                this.ch = i7 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i7);
                                this.token = i2;
                                return 3;
                            }
                            if (this.ch == '}') {
                                int i8 = this.bp + 1;
                                this.bp = i8;
                                this.ch = i8 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i8);
                                this.token = 13;
                                return 3;
                            }
                            if (this.ch >= '0' && this.ch <= '9') {
                                this.sp = 0;
                                this.pos = this.bp;
                                scanNumber();
                                return 3;
                            }
                            nextToken(2);
                            return 3;
                        }
                        if (this.ch != ':') {
                            skipWhitespace();
                        }
                        if (this.ch == ':') {
                            int i9 = this.bp + 1;
                            this.bp = i9;
                            this.ch = i9 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i9);
                            if (this.ch != '\"' && this.ch != '\'' && this.ch != '{' && this.ch != '[' && this.ch != '0' && this.ch != '1' && this.ch != '2' && this.ch != '3' && this.ch != '4' && this.ch != '5' && this.ch != '6' && this.ch != '7' && this.ch != '8' && this.ch != '9' && this.ch != '+' && this.ch != '-') {
                                skipWhitespace();
                            }
                            if (this.ch == '-' || this.ch == '+' || (this.ch >= '0' && this.ch <= '9')) {
                                next();
                                while (this.ch >= '0' && this.ch <= '9') {
                                    next();
                                }
                                if (this.ch == '.') {
                                    next();
                                    while (this.ch >= '0' && this.ch <= '9') {
                                        next();
                                    }
                                }
                                if (this.ch == 'E' || this.ch == 'e') {
                                    next();
                                    if (this.ch == '-' || this.ch == '+') {
                                        next();
                                    }
                                    while (this.ch >= '0' && this.ch <= '9') {
                                        next();
                                    }
                                }
                                if (this.ch != ',') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == '\"') {
                                skipString();
                                if (this.ch != ',' && this.ch != '}') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == 't') {
                                next();
                                if (this.ch == 'r') {
                                    next();
                                    if (this.ch == 'u') {
                                        next();
                                        if (this.ch == 'e') {
                                            next();
                                        }
                                    }
                                }
                                if (this.ch != ',' && this.ch != '}') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == 'n') {
                                next();
                                if (this.ch == 'u') {
                                    next();
                                    if (this.ch == 'l') {
                                        next();
                                        if (this.ch == 'l') {
                                            next();
                                        }
                                    }
                                }
                                if (this.ch != ',' && this.ch != '}') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == 'f') {
                                next();
                                if (this.ch == 'a') {
                                    next();
                                    if (this.ch == 'l') {
                                        next();
                                        if (this.ch == 's') {
                                            next();
                                            if (this.ch == 'e') {
                                                next();
                                            }
                                        }
                                    }
                                }
                                if (this.ch != ',' && this.ch != '}') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == '{') {
                                int i10 = this.bp + 1;
                                this.bp = i10;
                                this.ch = i10 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i10);
                                if (z) {
                                    this.token = 12;
                                    return 1;
                                }
                                skipObject(false);
                                if (this.token == 13) {
                                    return -1;
                                }
                            } else if (this.ch == '[') {
                                next();
                                if (z) {
                                    this.token = 14;
                                    return 2;
                                }
                                skipArray(false);
                                if (this.token == 13) {
                                    return -1;
                                }
                            } else {
                                throw new UnsupportedOperationException();
                            }
                            i = -1;
                            i2 = 15;
                            i3 = 16;
                        } else {
                            throw new JSONException("illegal json, " + info());
                        }
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
                next();
                nextToken();
                return i;
            }
        }
        nextToken();
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0193, code lost:
    
        if (r14.ch == '{') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0197, code lost:
    
        if (r14.ch == '[') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x019b, code lost:
    
        if (r14.ch == '0') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01a1, code lost:
    
        if (r14.ch == '1') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01a7, code lost:
    
        if (r14.ch == '2') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01ad, code lost:
    
        if (r14.ch == '3') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x01b3, code lost:
    
        if (r14.ch == '4') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01b9, code lost:
    
        if (r14.ch == '5') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x01bf, code lost:
    
        if (r14.ch == '6') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x01c5, code lost:
    
        if (r14.ch == '7') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01cb, code lost:
    
        if (r14.ch == '8') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x01cf, code lost:
    
        if (r14.ch == '9') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x01d3, code lost:
    
        if (r14.ch == '+') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x01d7, code lost:
    
        if (r14.ch == '-') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x01d9, code lost:
    
        skipWhitespace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x01de, code lost:
    
        if (r14.ch == '-') goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01e2, code lost:
    
        if (r14.ch == '+') goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x01e6, code lost:
    
        if (r14.ch < '0') goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01ea, code lost:
    
        if (r14.ch > '9') goto L210;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x01ef, code lost:
    
        if (r14.ch != '\"') goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x01f1, code lost:
    
        skipString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x01f6, code lost:
    
        if (r14.ch == ',') goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x01fa, code lost:
    
        if (r14.ch == '}') goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01fc, code lost:
    
        skipWhitespace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0201, code lost:
    
        if (r14.ch != ',') goto L221;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0203, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x020a, code lost:
    
        if (r14.ch != '{') goto L213;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x020c, code lost:
    
        r1 = r14.bp + 1;
        r14.bp = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0218, code lost:
    
        if (r1 < r14.text.length()) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x021b, code lost:
    
        r4 = r14.text.charAt(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0221, code lost:
    
        r14.ch = r4;
        skipObject(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x022a, code lost:
    
        if (r14.ch != '[') goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x022c, code lost:
    
        next();
        skipArray(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0239, code lost:
    
        throw new java.lang.UnsupportedOperationException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x023a, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x023f, code lost:
    
        if (r14.ch < '0') goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0243, code lost:
    
        if (r14.ch > '9') goto L229;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0245, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x024d, code lost:
    
        if (r14.ch != '.') goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x024f, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0254, code lost:
    
        if (r14.ch < '0') goto L230;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0258, code lost:
    
        if (r14.ch > '9') goto L231;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x025a, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0262, code lost:
    
        if (r14.ch == 'E') goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0268, code lost:
    
        if (r14.ch != 'e') goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x026a, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x026f, code lost:
    
        if (r14.ch == '-') goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0273, code lost:
    
        if (r14.ch != '+') goto L234;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x0275, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x027a, code lost:
    
        if (r14.ch < '0') goto L232;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x027e, code lost:
    
        if (r14.ch > '9') goto L233;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0280, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0286, code lost:
    
        if (r14.ch == ',') goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0288, code lost:
    
        skipWhitespace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x028d, code lost:
    
        if (r14.ch != ',') goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x028f, code lost:
    
        next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x02ab, code lost:
    
        throw new com.alibaba.fastjson.JSONException("illegal json, " + info());
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a5, code lost:
    
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a8, code lost:
    
        if (r8 >= r15.length) goto L226;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ae, code lost:
    
        if (r6 != r15[r8]) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b1, code lost:
    
        r8 = r8 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b4, code lost:
    
        r8 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bd, code lost:
    
        if (r8 == (-1)) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c1, code lost:
    
        if (r14.ch == ':') goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c3, code lost:
    
        skipWhitespace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c8, code lost:
    
        if (r14.ch != ':') goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ca, code lost:
    
        r15 = r14.bp + 1;
        r14.bp = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00d6, code lost:
    
        if (r15 < r14.text.length()) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d8, code lost:
    
        r15 = 26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00da, code lost:
    
        r15 = r14.text.charAt(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00e0, code lost:
    
        r14.ch = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e4, code lost:
    
        if (r14.ch != ',') goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e6, code lost:
    
        r15 = r14.bp + 1;
        r14.bp = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00f2, code lost:
    
        if (r15 < r14.text.length()) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00f5, code lost:
    
        r4 = r14.text.charAt(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00fb, code lost:
    
        r14.ch = r4;
        r14.token = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0104, code lost:
    
        if (r14.ch != ']') goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0106, code lost:
    
        r15 = r14.bp + 1;
        r14.bp = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0112, code lost:
    
        if (r15 < r14.text.length()) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0115, code lost:
    
        r4 = r14.text.charAt(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x011b, code lost:
    
        r14.ch = r4;
        r14.token = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0124, code lost:
    
        if (r14.ch != '}') goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0126, code lost:
    
        r15 = r14.bp + 1;
        r14.bp = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0132, code lost:
    
        if (r15 < r14.text.length()) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0135, code lost:
    
        r4 = r14.text.charAt(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x013b, code lost:
    
        r14.ch = r4;
        r14.token = 13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0144, code lost:
    
        if (r14.ch < '0') goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0148, code lost:
    
        if (r14.ch > '9') goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x014a, code lost:
    
        r14.sp = 0;
        r14.pos = r14.bp;
        scanNumber();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0154, code lost:
    
        nextToken(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0158, code lost:
    
        r14.matchStat = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x015b, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x015e, code lost:
    
        if (r14.ch == ':') goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0160, code lost:
    
        skipWhitespace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0165, code lost:
    
        if (r14.ch != ':') goto L208;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0167, code lost:
    
        r3 = r14.bp + 1;
        r14.bp = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0173, code lost:
    
        if (r3 < r14.text.length()) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0175, code lost:
    
        r3 = 26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0177, code lost:
    
        r3 = r14.text.charAt(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x017d, code lost:
    
        r14.ch = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0189, code lost:
    
        if (r14.ch == '\"') goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x018f, code lost:
    
        if (r14.ch == '\'') goto L129;
     */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int seekObjectToField(long[] r15) {
        /*
            Method dump skipped, instructions count: 690
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.seekObjectToField(long[]):int");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public String scanTypeName(SymbolTable symbolTable) {
        int iIndexOf;
        if (!this.text.startsWith("\"@type\":\"", this.bp) || (iIndexOf = this.text.indexOf(34, this.bp + 9)) == -1) {
            return null;
        }
        this.bp += 9;
        int iCharAt = 0;
        for (int i = this.bp; i < iIndexOf; i++) {
            iCharAt = (iCharAt * 31) + this.text.charAt(i);
        }
        String strAddSymbol = addSymbol(this.bp, iIndexOf - this.bp, iCharAt, symbolTable);
        char cCharAt = this.text.charAt(iIndexOf + 1);
        if (cCharAt != ',' && cCharAt != ']') {
            return null;
        }
        this.bp = iIndexOf + 2;
        this.ch = this.text.charAt(this.bp);
        return strAddSymbol;
    }
}
