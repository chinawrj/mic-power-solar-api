package com.taobao.weex.adapter;

import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public interface IDrawableLoader {

    public interface AnimatedTarget extends DrawableTarget {
        void setAnimatedDrawable(Drawable drawable);
    }

    public interface DrawableTarget {
        void setDrawable(Drawable drawable, boolean z);
    }

    public interface StaticTarget extends DrawableTarget {
        @Override // com.taobao.weex.adapter.IDrawableLoader.DrawableTarget
        void setDrawable(Drawable drawable, boolean z);
    }

    void setDrawable(String str, DrawableTarget drawableTarget, DrawableStrategy drawableStrategy);
}
