package io.dcloud.uts;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextDecoder.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000eJ\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000fJ\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010\u0012\u001a\u00020\u0003H\u0002R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/dcloud/uts/TextDecoder;", "", "label", "", "(Ljava/lang/String;)V", "encoding", "getEncoding", "()Ljava/lang/String;", "setEncoding", "supportEncoding", "", "decode", "input", "Lio/dcloud/uts/ArrayBuffer;", "Lio/dcloud/uts/DataView;", "Lio/dcloud/uts/TypedArray;", "getRes", "buffer", "getSupportEncoding", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextDecoder {
    private String encoding;
    private final List<String> supportEncoding;

    public final String getEncoding() {
        return this.encoding;
    }

    public final void setEncoding(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.encoding = str;
    }

    public TextDecoder(String str) {
        String lowerCase;
        this.encoding = "utf-8";
        ArrayList arrayList = new ArrayList();
        arrayList.add("utf-8");
        arrayList.add("gb2312");
        arrayList.add("gbk");
        this.supportEncoding = arrayList;
        this.encoding = (str == null || (lowerCase = StringKt.toLowerCase(str)) == null) ? this.encoding : lowerCase;
    }

    public /* synthetic */ TextDecoder(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "utf-8" : str);
    }

    private final String getSupportEncoding() {
        java.lang.Object next;
        if (this.supportEncoding.indexOf(this.encoding) != -1) {
            Iterator<T> it = this.supportEncoding.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual((String) next, this.encoding)) {
                    break;
                }
            }
            Intrinsics.checkNotNull(next);
            return (String) next;
        }
        return this.encoding;
    }

    private final String getRes(ArrayBuffer buffer) {
        ByteBuffer byteBuffer;
        CharsetDecoder charsetDecoderOnUnmappableCharacter = Charset.forName(getSupportEncoding()).newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        Intrinsics.checkNotNullExpressionValue(charsetDecoderOnUnmappableCharacter, "charset.newDecoder()\n   …odingErrorAction.REPLACE)");
        if (buffer != null && (byteBuffer = buffer.getByteBuffer()) != null) {
            byteBuffer.rewind();
        }
        String string = charsetDecoderOnUnmappableCharacter.decode(buffer != null ? buffer.getByteBuffer() : null).toString();
        Intrinsics.checkNotNullExpressionValue(string, "charBuffer.toString()");
        return string;
    }

    public final String decode(TypedArray input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return getRes(input.getBuffer());
    }

    public final String decode(ArrayBuffer input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return getRes(input);
    }

    public final String decode(DataView input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return getRes(input.getBuffer());
    }
}
