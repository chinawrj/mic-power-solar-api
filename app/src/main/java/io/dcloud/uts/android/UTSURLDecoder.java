package io.dcloud.uts.android;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSURLDecoder.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\f\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0006J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\"\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/dcloud/uts/android/UTSURLDecoder;", "", "isComponent", "", "(Z)V", "dfltEncName", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "getDfltEncName", "()Ljava/nio/charset/Charset;", "setDfltEncName", "(Ljava/nio/charset/Charset;)V", "decode", "", "s", "charset", "isValidHexChar", "c", "", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSURLDecoder {
    private Charset dfltEncName;
    private boolean isComponent;

    private final boolean isValidHexChar(char c) {
        return ('0' <= c && c < ':') || ('a' <= c && c < 'g') || ('A' <= c && c < 'G');
    }

    public final Charset getDfltEncName() {
        return this.dfltEncName;
    }

    public final void setDfltEncName(Charset charset) {
        this.dfltEncName = charset;
    }

    public UTSURLDecoder(boolean z) {
        this.dfltEncName = Charset.forName("UTF-8");
        this.isComponent = z;
    }

    public /* synthetic */ UTSURLDecoder(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public static /* synthetic */ String decode$default(UTSURLDecoder uTSURLDecoder, String str, Charset dfltEncName, int i, Object obj) {
        if ((i & 2) != 0) {
            dfltEncName = uTSURLDecoder.dfltEncName;
            Intrinsics.checkNotNullExpressionValue(dfltEncName, "dfltEncName");
        }
        return uTSURLDecoder.decode(str, dfltEncName);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c3, code lost:
    
        r2 = io.dcloud.uts.ObjectKt.getGlobalError();
        r3 = java.lang.Thread.currentThread().getName();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, "currentThread().name");
        r9 = new java.lang.StringBuilder();
        r9.append("URLDecoder: Illegal hex characters in escape (%) pattern : ");
        r0 = r17.substring(r8, r8 + 3);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "this as java.lang.String…ing(startIndex, endIndex)");
        r9.append(r0);
        r2.put(r3, new java.lang.IllegalArgumentException(r9.toString()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00f4, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f5, code lost:
    
        if (r8 >= r3) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00f7, code lost:
    
        if (r11 != '%') goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f9, code lost:
    
        r0 = io.dcloud.uts.ObjectKt.getGlobalError();
        r2 = java.lang.Thread.currentThread().getName();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "currentThread().name");
        r0.put(r2, new java.lang.IllegalArgumentException("URLDecoder: Incomplete trailing escape (%) pattern"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0112, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0113, code lost:
    
        r4.append(new java.lang.String(r10, 0, r9, r18));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x011c, code lost:
    
        r9 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String decode(java.lang.String r17, java.nio.charset.Charset r18) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.uts.android.UTSURLDecoder.decode(java.lang.String, java.nio.charset.Charset):java.lang.String");
    }
}
