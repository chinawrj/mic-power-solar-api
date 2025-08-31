package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
import io.dcloud.common.util.JSUtil;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class ConstraintHelper extends View {
    protected int mCount;
    protected Helper mHelperWidget;
    protected int[] mIds;
    private String mReferenceIds;
    protected boolean mUseViewMeasure;
    protected Context myContext;

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
    }

    public void updatePostLayout(ConstraintLayout constraintLayout) {
    }

    public void updatePostMeasure(ConstraintLayout constraintLayout) {
    }

    public ConstraintHelper(Context context) throws IllegalAccessException, IllegalArgumentException {
        super(context);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.myContext = context;
        init(null);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet) throws IllegalAccessException, IllegalArgumentException {
        super(context, attributeSet);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.myContext = context;
        init(attributeSet);
    }

    public ConstraintHelper(Context context, AttributeSet attributeSet, int i) throws IllegalAccessException, IllegalArgumentException {
        super(context, attributeSet, i);
        this.mIds = new int[32];
        this.mUseViewMeasure = false;
        this.myContext = context;
        init(attributeSet);
    }

    protected void init(AttributeSet attributeSet) throws IllegalAccessException, IllegalArgumentException {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.mReferenceIds = string;
                    setIds(string);
                }
            }
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    public void setReferencedIds(int[] iArr) {
        this.mCount = 0;
        for (int i : iArr) {
            setTag(i, null);
        }
    }

    @Override // android.view.View
    public void setTag(int i, Object obj) {
        int i2 = this.mCount + 1;
        int[] iArr = this.mIds;
        if (i2 > iArr.length) {
            this.mIds = Arrays.copyOf(iArr, iArr.length * 2);
        }
        int[] iArr2 = this.mIds;
        int i3 = this.mCount;
        iArr2[i3] = i;
        this.mCount = i3 + 1;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mUseViewMeasure) {
            super.onMeasure(i, i2);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void validateParams() {
        if (this.mHelperWidget == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).widget = this.mHelperWidget;
        }
    }

    private void addID(String str) throws IllegalAccessException, IllegalArgumentException {
        int iIntValue;
        Object designInformation;
        if (str == null || this.myContext == null) {
            return;
        }
        String strTrim = str.trim();
        try {
            iIntValue = R.id.class.getField(strTrim).getInt(null);
        } catch (Exception unused) {
            iIntValue = 0;
        }
        if (iIntValue == 0) {
            iIntValue = this.myContext.getResources().getIdentifier(strTrim, "id", this.myContext.getPackageName());
        }
        if (iIntValue == 0 && isInEditMode() && (getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) getParent()).getDesignInformation(0, strTrim)) != null && (designInformation instanceof Integer)) {
            iIntValue = ((Integer) designInformation).intValue();
        }
        if (iIntValue != 0) {
            setTag(iIntValue, null);
            return;
        }
        Log.w("ConstraintHelper", "Could not find id of \"" + strTrim + JSUtil.QUOTE);
    }

    private void setIds(String str) throws IllegalAccessException, IllegalArgumentException {
        if (str == null) {
            return;
        }
        int i = 0;
        while (true) {
            int iIndexOf = str.indexOf(44, i);
            if (iIndexOf == -1) {
                addID(str.substring(i));
                return;
            } else {
                addID(str.substring(i, iIndexOf));
                i = iIndexOf + 1;
            }
        }
    }

    public void updatePreLayout(ConstraintLayout constraintLayout) throws IllegalAccessException, IllegalArgumentException {
        if (isInEditMode()) {
            setIds(this.mReferenceIds);
        }
        Helper helper = this.mHelperWidget;
        if (helper == null) {
            return;
        }
        helper.removeAllIds();
        for (int i = 0; i < this.mCount; i++) {
            View viewById = constraintLayout.getViewById(this.mIds[i]);
            if (viewById != null) {
                this.mHelperWidget.add(constraintLayout.getViewWidget(viewById));
            }
        }
    }
}
