package io.dcloud.uts.nativeregex;

import io.dcloud.sdk.core.util.Const;
import io.dcloud.uts.RegExpExecArray;
import kotlin.Metadata;

/* compiled from: RegexpBridge.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0086 J\u0011\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0086 J#\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0004H\u0086 J\u0011\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\rH\u0086 J\u0013\u0010\u0019\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086 J+\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lio/dcloud/uts/nativeregex/RegexpBridge;", "", "()V", "FLAG_DOTALL", "", "FLAG_GLOBAL", "FLAG_IGNORECASE", "FLAG_INDICES", "FLAG_MULTILINE", "FLAG_STICKY", "FLAG_UNICODE", "FLAG_UNICODE_SETS", "compileRegexp", "", "pattern", "", "flags", "escape", "str", "execRegexp", "Lio/dcloud/uts/RegExpExecArray;", "bytecode", "input", "startIndex", "getFlags", "getSource", "replace", "replacement", Const.PROVIDER_TYPE_GLOBAL, "", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RegexpBridge {
    public static final int FLAG_DOTALL = 8;
    public static final int FLAG_GLOBAL = 1;
    public static final int FLAG_IGNORECASE = 2;
    public static final int FLAG_INDICES = 64;
    public static final int FLAG_MULTILINE = 4;
    public static final int FLAG_STICKY = 32;
    public static final int FLAG_UNICODE = 16;
    public static final int FLAG_UNICODE_SETS = 128;
    public static final RegexpBridge INSTANCE = new RegexpBridge();

    public final native byte[] compileRegexp(String pattern, int flags);

    public final native String escape(String str);

    public final native RegExpExecArray execRegexp(byte[] bytecode, String input, int startIndex);

    public final native int getFlags(byte[] bytecode);

    public final native String getSource(String pattern);

    public final native String replace(byte[] bytecode, String input, String replacement, boolean global);

    private RegexpBridge() {
    }

    static {
        System.loadLibrary("uts-runtime");
    }
}
