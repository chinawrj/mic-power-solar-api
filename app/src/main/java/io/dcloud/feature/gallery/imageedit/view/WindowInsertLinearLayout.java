package io.dcloud.feature.gallery.imageedit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.LinearLayout;

/* loaded from: classes3.dex */
public class WindowInsertLinearLayout extends LinearLayout {

    class a implements ViewGroup.OnHierarchyChangeListener {
        a() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            WindowInsertLinearLayout.this.requestApplyInsets();
        }
    }

    public WindowInsertLinearLayout(Context context) {
        super(context);
    }

    public WindowInsertLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnHierarchyChangeListener(new a());
    }

    @Override // android.view.ViewGroup, android.view.View
    public WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).dispatchApplyWindowInsets(windowInsets);
        }
        return windowInsets;
    }
}
