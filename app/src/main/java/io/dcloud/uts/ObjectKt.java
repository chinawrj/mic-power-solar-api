package io.dcloud.uts;

import android.util.Base64;
import io.dcloud.uts.android.UTSURLDecoder;
import io.dcloud.uts.android.UTSURLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: Object.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002\u001a\u000e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002\u001a\u0010\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u0002\u001a\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u0002\u001a\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u0002\u001a\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u0002\".\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00040\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"globalError", "", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "getGlobalError", "()Ljava/util/Map;", "setGlobalError", "(Ljava/util/Map;)V", "atob", "encode", "btoa", "source", "decodeURI", "input", "decodeURIComponent", "encodeURI", "encodeURIComponent", "utsplugin_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ObjectKt {
    private static java.util.Map<String, Exception> globalError;

    public static final String encodeURI(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return UTSURLEncoder.encode$default(new UTSURLEncoder(false, 1, null), input, null, 2, null);
    }

    public static final String encodeURIComponent(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return UTSURLEncoder.encode$default(new UTSURLEncoder(true), input, null, 2, null);
    }

    public static final String decodeURI(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return UTSURLDecoder.decode$default(new UTSURLDecoder(false, 1, null), input, null, 2, null);
    }

    public static final String decodeURIComponent(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return UTSURLDecoder.decode$default(new UTSURLDecoder(false, 1, null), input, null, 2, null);
    }

    public static final String btoa(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        byte[] bytes = source.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        String strEncodeToString = Base64.encodeToString(bytes, 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(source.to…roid.util.Base64.NO_WRAP)");
        return strEncodeToString;
    }

    public static final String atob(String encode) {
        Intrinsics.checkNotNullParameter(encode, "encode");
        byte[] bArrDecode = Base64.decode(encode, 2);
        Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(encode,android.util.Base64.NO_WRAP)");
        Charset charsetDefaultCharset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(charsetDefaultCharset, "defaultCharset()");
        return new String(bArrDecode, charsetDefaultCharset);
    }

    static {
        java.util.Map<String, Exception> mapSynchronizedMap = Collections.synchronizedMap(new WeakHashMap());
        Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "synchronizedMap(WeakHashMap<String, Exception?>())");
        globalError = mapSynchronizedMap;
    }

    public static final java.util.Map<String, Exception> getGlobalError() {
        return globalError;
    }

    public static final void setGlobalError(java.util.Map<String, Exception> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        globalError = map;
    }
}
