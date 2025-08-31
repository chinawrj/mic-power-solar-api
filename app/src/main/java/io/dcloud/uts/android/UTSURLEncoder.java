package io.dcloud.uts.android;

import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.taobao.weex.utils.WXUtils;
import java.io.CharArrayWriter;
import java.nio.charset.Charset;
import java.util.BitSet;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

/* compiled from: UTSURLEncoder.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/dcloud/uts/android/UTSURLEncoder;", "", "isComponent", "", "(Z)V", "caseDiff", "", "dfltEncName", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "shouldNotNeedEncoding", "Ljava/util/BitSet;", "encode", "", "s", "charset", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSURLEncoder {
    private final int caseDiff;
    private Charset dfltEncName;
    private BitSet shouldNotNeedEncoding;

    public UTSURLEncoder(boolean z) {
        this.shouldNotNeedEncoding = new BitSet(256);
        this.caseDiff = 32;
        this.dfltEncName = Charset.forName("UTF-8");
        for (int i = 97; i < 123; i++) {
            this.shouldNotNeedEncoding.set(i);
        }
        for (int i2 = 65; i2 < 91; i2++) {
            this.shouldNotNeedEncoding.set(i2);
        }
        for (int i3 = 48; i3 < 58; i3++) {
            this.shouldNotNeedEncoding.set(i3);
        }
        Character[] chArr = {' ', '-', '_', Character.valueOf(Operators.DOT), '!', '~', '*', Character.valueOf(Operators.SINGLE_QUOTE), Character.valueOf(Operators.BRACKET_START), Character.valueOf(Operators.BRACKET_END)};
        int i4 = 0;
        for (int i5 = 10; i4 < i5; i5 = 10) {
            this.shouldNotNeedEncoding.set(chArr[i4].charValue());
            i4++;
        }
        if (z) {
            return;
        }
        Character[] chArr2 = {';', Character.valueOf(Operators.ARRAY_SEPRATOR), '/', Character.valueOf(Operators.CONDITION_IF), Character.valueOf(Operators.CONDITION_IF_MIDDLE), Character.valueOf(TemplateDom.SEPARATOR), Character.valueOf(Typography.amp), '=', '+', '$', '#'};
        for (int i6 = 0; i6 < 11; i6++) {
            this.shouldNotNeedEncoding.set(chArr2[i6].charValue());
        }
    }

    public /* synthetic */ UTSURLEncoder(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public static /* synthetic */ String encode$default(UTSURLEncoder uTSURLEncoder, String str, Charset dfltEncName, int i, Object obj) {
        if ((i & 2) != 0) {
            dfltEncName = uTSURLEncoder.dfltEncName;
            Intrinsics.checkNotNullExpressionValue(dfltEncName, "dfltEncName");
        }
        return uTSURLEncoder.encode(str, dfltEncName);
    }

    public final String encode(String s, Charset charset) {
        BitSet bitSet;
        int i;
        char cCharAt;
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(charset, "charset");
        StringBuilder sb = new StringBuilder(s.length());
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        int i2 = 0;
        boolean z = false;
        while (i2 < s.length()) {
            char cCharAt2 = s.charAt(i2);
            if (this.shouldNotNeedEncoding.get(cCharAt2)) {
                if (cCharAt2 == ' ') {
                    sb.append("%20");
                    z = true;
                } else {
                    sb.append(cCharAt2);
                }
                i2++;
            } else {
                do {
                    charArrayWriter.write(cCharAt2);
                    if (55296 <= cCharAt2 && cCharAt2 < 56320 && (i = i2 + 1) < s.length() && 56320 <= (cCharAt = s.charAt(i)) && cCharAt < 57344) {
                        charArrayWriter.write(cCharAt);
                        i2 = i;
                    }
                    i2++;
                    if (i2 >= s.length()) {
                        break;
                    }
                    bitSet = this.shouldNotNeedEncoding;
                    cCharAt2 = s.charAt(i2);
                } while (!bitSet.get(cCharAt2));
                charArrayWriter.flush();
                char[] charArray = charArrayWriter.toCharArray();
                Intrinsics.checkNotNullExpressionValue(charArray, "charArrayWriter.toCharArray()");
                byte[] bytes = new String(charArray).getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                int length = bytes.length;
                for (int i3 = 0; i3 < length; i3++) {
                    sb.append(WXUtils.PERCENT);
                    char cForDigit = Character.forDigit((bytes[i3] >> 4) & 15, 16);
                    if (Character.isLetter(cForDigit)) {
                        cForDigit = (char) (cForDigit - this.caseDiff);
                    }
                    sb.append(cForDigit);
                    char cForDigit2 = Character.forDigit(bytes[i3] & 15, 16);
                    if (Character.isLetter(cForDigit2)) {
                        cForDigit2 = (char) (cForDigit2 - this.caseDiff);
                    }
                    sb.append(cForDigit2);
                }
                charArrayWriter.reset();
                z = true;
            }
        }
        if (!z) {
            return s;
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "out.toString()");
        return string;
    }
}
