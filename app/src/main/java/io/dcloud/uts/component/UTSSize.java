package io.dcloud.uts.component;

import com.taobao.weex.el.parse.Operators;
import kotlin.Metadata;

/* compiled from: UTSSize.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u000e"}, d2 = {"Lio/dcloud/uts/component/UTSSize;", "", "width", "", "height", "(FF)V", "getHeight", "()F", "setHeight", "(F)V", "getWidth", "setWidth", "toString", "", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSSize {
    private float height;
    private float width;

    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    public UTSSize(float f, float f2) {
        this.width = f;
        this.height = f2;
    }

    public String toString() {
        return "UTSSize(width=" + this.width + ", height=" + this.height + Operators.BRACKET_END;
    }
}
