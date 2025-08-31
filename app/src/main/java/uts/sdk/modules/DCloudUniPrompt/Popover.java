package uts.sdk.modules.DCloudUniPrompt;

import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u000f\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u001e\u0010\u0006\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0004\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001e\u0010\u0005\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0012"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/Popover;", "Lio/dcloud/uts/UTSObject;", "top", "", "left", "width", "height", "(Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;)V", "getHeight", "()Ljava/lang/Number;", "setHeight", "(Ljava/lang/Number;)V", "getLeft", "setLeft", "getTop", "setTop", "getWidth", "setWidth", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class Popover extends UTSObject {

    @JsonNotNull
    private Number height;

    @JsonNotNull
    private Number left;

    @JsonNotNull
    private Number top;

    @JsonNotNull
    private Number width;

    public Number getTop() {
        return this.top;
    }

    public void setTop(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.top = number;
    }

    public Number getLeft() {
        return this.left;
    }

    public void setLeft(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.left = number;
    }

    public Number getWidth() {
        return this.width;
    }

    public void setWidth(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.width = number;
    }

    public Number getHeight() {
        return this.height;
    }

    public void setHeight(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.height = number;
    }

    public Popover(Number top, Number left, Number width, Number height) {
        Intrinsics.checkNotNullParameter(top, "top");
        Intrinsics.checkNotNullParameter(left, "left");
        Intrinsics.checkNotNullParameter(width, "width");
        Intrinsics.checkNotNullParameter(height, "height");
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }
}
