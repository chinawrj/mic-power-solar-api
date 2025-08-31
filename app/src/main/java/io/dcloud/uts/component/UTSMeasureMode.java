package io.dcloud.uts.component;

import com.taobao.weex.el.parse.Operators;
import kotlin.Metadata;

/* compiled from: UTSSize.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006\u0010"}, d2 = {"Lio/dcloud/uts/component/UTSMeasureMode;", "", "widthMode", "", "heightMode", "(II)V", "height", "getHeight", "()I", "setHeight", "(I)V", "width", "getWidth", "setWidth", "toString", "", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSMeasureMode {
    private int height;
    private int width;

    public UTSMeasureMode(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public String toString() {
        return "UTSMode(width=" + this.width + ", height=" + this.height + Operators.BRACKET_END;
    }
}
