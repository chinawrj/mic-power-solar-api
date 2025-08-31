package kotlin.reflect.jvm.internal.impl.renderer;

import com.taobao.weex.el.parse.Operators;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DescriptorRenderer.kt */
/* loaded from: classes3.dex */
public enum RenderingFormat {
    PLAIN { // from class: kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat.PLAIN
        @Override // kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat
        public String escape(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            return string;
        }
    },
    HTML { // from class: kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat.HTML
        @Override // kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat
        public String escape(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            return StringsKt.replace$default(StringsKt.replace$default(string, Operators.L, "&lt;", false, 4, (Object) null), Operators.G, "&gt;", false, 4, (Object) null);
        }
    };

    /* synthetic */ RenderingFormat(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String escape(String str);
}
