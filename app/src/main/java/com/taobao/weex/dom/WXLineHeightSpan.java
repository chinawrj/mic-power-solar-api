package com.taobao.weex.dom;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class WXLineHeightSpan implements LineHeightSpan {
    private int lineHeight;

    public WXLineHeightSpan(int i) {
        this.lineHeight = i;
    }

    @Override // android.text.style.LineHeightSpan
    public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("LineHeight", ((Object) charSequence) + " ; start " + i + "; end " + i2 + "; spanstartv " + i3 + "; v " + i4 + "; fm " + fontMetricsInt);
        }
        int i5 = this.lineHeight;
        int i6 = fontMetricsInt.descent;
        int i7 = fontMetricsInt.ascent;
        int i8 = i5 - (i6 - i7);
        int i9 = i8 / 2;
        int i10 = i8 - i9;
        fontMetricsInt.top -= i10;
        fontMetricsInt.bottom += i9;
        fontMetricsInt.ascent = i7 - i10;
        fontMetricsInt.descent = i6 + i9;
    }
}
