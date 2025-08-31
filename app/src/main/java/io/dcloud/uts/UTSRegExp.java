package io.dcloud.uts;

import io.dcloud.sdk.core.util.Const;
import io.dcloud.uts.nativeregex.JSRegexp;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

/* compiled from: UTSRegExp2.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0000¢\u0006\u0002\u0010\tJ\u0010\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u0004J\u0006\u0010,\u001a\u00020\u0004J\u000e\u0010-\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u0004J\u0006\u0010.\u001a\u00020/J\b\u00100\u001a\u00020\u0004H\u0016R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0013\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\rR$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b#\u0010\rR\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b$\u0010\u0010R\u0011\u0010%\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b&\u0010\rR\u0011\u0010'\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b(\u0010\r¨\u00061"}, d2 = {"Lio/dcloud/uts/UTSRegExp;", "", "()V", "source", "", "(Ljava/lang/String;)V", "mode", "(Ljava/lang/String;Ljava/lang/String;)V", "reg", "(Lio/dcloud/uts/UTSRegExp;)V", "dotAll", "", "getDotAll", "()Z", "flags", "getFlags", "()Ljava/lang/String;", Const.PROVIDER_TYPE_GLOBAL, "getGlobal", "hasIndices", "getHasIndices", "hostJSRegexp", "Lio/dcloud/uts/nativeregex/JSRegexp;", "getHostJSRegexp", "()Lio/dcloud/uts/nativeregex/JSRegexp;", "ignoreCase", "getIgnoreCase", "value", "", "lastIndex", "getLastIndex", "()I", "setLastIndex", "(I)V", "multiline", "getMultiline", "getSource", "sticky", "getSticky", "unicode", "getUnicode", "exec", "Lio/dcloud/uts/RegExpExecArray;", "input", "getQuickJSFlags", "test", "toRegex", "Lkotlin/text/Regex;", "toString", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSRegExp {
    private final JSRegexp hostJSRegexp;

    public final JSRegexp getHostJSRegexp() {
        return this.hostJSRegexp;
    }

    public UTSRegExp() {
        this.hostJSRegexp = JSRegexp.INSTANCE.create("(?:)", "");
    }

    public UTSRegExp(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.hostJSRegexp = JSRegexp.INSTANCE.create(source, "");
    }

    public UTSRegExp(String source, String mode) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.hostJSRegexp = JSRegexp.INSTANCE.create(source, mode);
    }

    public UTSRegExp(UTSRegExp reg) {
        Intrinsics.checkNotNullParameter(reg, "reg");
        this.hostJSRegexp = reg.hostJSRegexp;
    }

    public final boolean getGlobal() {
        return this.hostJSRegexp.isGlobal();
    }

    public final boolean getDotAll() {
        return this.hostJSRegexp.isDotAll();
    }

    public final boolean getIgnoreCase() {
        return this.hostJSRegexp.isIgnoreCase();
    }

    public final boolean getMultiline() {
        return this.hostJSRegexp.isMultiline();
    }

    public final boolean getHasIndices() {
        return this.hostJSRegexp.hasIndices();
    }

    public final boolean getSticky() {
        return this.hostJSRegexp.isSticky();
    }

    public final boolean getUnicode() {
        return this.hostJSRegexp.isUnicode();
    }

    public final String getQuickJSFlags() {
        StringBuilder sb = new StringBuilder();
        if (getHasIndices()) {
            sb.append('d');
        }
        if (getGlobal()) {
            sb.append('g');
        }
        if (getIgnoreCase()) {
            sb.append('i');
        }
        if (getMultiline()) {
            sb.append('m');
        }
        if (getDotAll()) {
            sb.append('s');
        }
        if (getUnicode()) {
            sb.append('u');
        }
        if (getSticky()) {
            sb.append('y');
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    public final String getFlags() {
        return getQuickJSFlags();
    }

    public final String getSource() {
        return this.hostJSRegexp.getSource();
    }

    public final int getLastIndex() {
        return this.hostJSRegexp.getLastIndex();
    }

    public final void setLastIndex(int i) {
        this.hostJSRegexp.setLastIndex(i);
    }

    public final RegExpExecArray exec(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return JSRegexp.exec$default(this.hostJSRegexp, input, 0, 2, null);
    }

    public final boolean test(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return this.hostJSRegexp.test(input);
    }

    public String toString() {
        return this.hostJSRegexp.toString();
    }

    public final Regex toRegex() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (this.hostJSRegexp.isIgnoreCase()) {
            linkedHashSet.add(RegexOption.IGNORE_CASE);
        }
        if (this.hostJSRegexp.isMultiline()) {
            linkedHashSet.add(RegexOption.MULTILINE);
        }
        if (this.hostJSRegexp.isDotAll()) {
            linkedHashSet.add(RegexOption.DOT_MATCHES_ALL);
        }
        return new Regex(this.hostJSRegexp.getSource(), linkedHashSet);
    }
}
