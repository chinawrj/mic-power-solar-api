package com.dmcbig.mediapicker.adapter;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class SpacingDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int spanCount;

    public SpacingDecoration(int i, int i2) {
        this.spanCount = i;
        this.space = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = this.space;
        rect.left = i;
        rect.bottom = i;
        if (recyclerView.getChildLayoutPosition(view) % this.spanCount == 0) {
            rect.left = 0;
        }
    }
}
