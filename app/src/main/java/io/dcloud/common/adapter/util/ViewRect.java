package io.dcloud.common.adapter.util;

import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ViewRect {
    public static final int DEFAULT_MARGIN = 0;
    public static byte DOCK_BOTTOM = 6;
    public static byte DOCK_LEFT = 3;
    public static byte DOCK_RIGHT = 4;
    public static byte DOCK_TOP = 5;
    public static byte POSITION_ABSOLUTE = 1;
    public static byte POSITION_DOCK = 2;
    public static byte POSITION_STATIC;
    public int anim_left;
    public int anim_top;
    public int bottom;
    public int height;
    public int left;
    public String mStatusbarColor;
    public int right;
    public int top;
    public int width;
    private ViewRect mParentViewRect = null;
    private ViewRect mFrameParentViewRect = null;
    private ArrayList<ViewRect> mRelViewRectDockSet = null;
    public float mWebviewScale = 1.0f;
    public String margin = String.valueOf(0);
    public boolean isStatusbar = false;
    private byte mPosition = POSITION_STATIC;
    private byte mDock = DOCK_TOP;
    public JSONObject mJsonViewOption = JSONUtil.createJSONObject("{}");
    public boolean isStatusbarDodifyHeight = false;
    public boolean isNotHeightFullScreen = false;
    private boolean doUpdate = false;
    public boolean allowUpdate = true;

    public static void layoutDockViewRect(ViewRect viewRect, ViewRect viewRect2, boolean z) {
        ViewRect viewRect3 = viewRect2.mParentViewRect;
        JSONObject jSONObject = viewRect2.mJsonViewOption;
        String string = JSONUtil.getString(jSONObject, "position");
        if (PdrUtil.isEmpty(string)) {
            return;
        }
        if (AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE.equals(string)) {
            viewRect2.mPosition = POSITION_ABSOLUTE;
            return;
        }
        if (!"dock".equals(string)) {
            if ("static".equals(string)) {
                viewRect2.mPosition = POSITION_STATIC;
                return;
            }
            return;
        }
        viewRect2.mPosition = POSITION_DOCK;
        String string2 = JSONUtil.getString(jSONObject, "dock");
        if (!PdrUtil.isEmpty(string2)) {
            if ("bottom".equals(string2)) {
                viewRect2.mDock = DOCK_BOTTOM;
            } else if ("top".equals(string2)) {
                viewRect2.mDock = DOCK_TOP;
            } else if ("left".equals(string2)) {
                viewRect2.mDock = DOCK_LEFT;
            } else if ("right".equals(string2)) {
                viewRect2.mDock = DOCK_RIGHT;
            }
        }
        String string3 = JSONUtil.getString(jSONObject, "left");
        String string4 = JSONUtil.getString(jSONObject, "top");
        String string5 = JSONUtil.getString(jSONObject, "width");
        String string6 = JSONUtil.getString(jSONObject, "height");
        boolean zIsEmpty = PdrUtil.isEmpty(string3);
        boolean zIsEmpty2 = PdrUtil.isEmpty(string4);
        boolean zIsEmpty3 = PdrUtil.isEmpty(string5);
        boolean zIsEmpty4 = PdrUtil.isEmpty(string6);
        viewRect2.width = PdrUtil.convertToScreenInt(string5, viewRect3.width, viewRect.width, viewRect3.mWebviewScale);
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(string6, viewRect3.height, viewRect.height, viewRect3.mWebviewScale);
        viewRect2.height = iConvertToScreenInt;
        int i = viewRect2.width;
        if (i < 0) {
            i = viewRect2.mParentViewRect.width;
        }
        viewRect2.width = i;
        if (iConvertToScreenInt < 0) {
            iConvertToScreenInt = viewRect2.mParentViewRect.height;
        }
        viewRect2.height = iConvertToScreenInt;
        int i2 = viewRect.top;
        int i3 = viewRect.left;
        int i4 = viewRect.width;
        int i5 = viewRect.height;
        int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(string3, viewRect3.width, i3, viewRect3.mWebviewScale);
        int iConvertToScreenInt3 = PdrUtil.convertToScreenInt(string4, viewRect3.height, viewRect.top, viewRect3.mWebviewScale);
        byte b = viewRect2.mDock;
        if (b == DOCK_BOTTOM) {
            if (zIsEmpty2 || !zIsEmpty4) {
                int i6 = viewRect.height - viewRect2.height;
                viewRect.height = i6;
                iConvertToScreenInt3 = i6 + viewRect.top;
            } else {
                viewRect.height = iConvertToScreenInt3 - viewRect.top;
                viewRect2.height -= iConvertToScreenInt3;
            }
        } else if (b == DOCK_RIGHT) {
            if (zIsEmpty || !zIsEmpty3) {
                int i7 = viewRect.width - viewRect2.width;
                viewRect.width = i7;
                iConvertToScreenInt2 = i7 + viewRect.left;
            } else {
                viewRect.width = iConvertToScreenInt2 - viewRect.left;
                viewRect2.width -= iConvertToScreenInt2;
            }
        } else if (b == DOCK_LEFT) {
            int i8 = viewRect2.width;
            viewRect.left = i8;
            viewRect.width -= i8;
            iConvertToScreenInt2 = 0;
        } else if (b == DOCK_TOP) {
            int i9 = viewRect2.height;
            viewRect.top = i9;
            viewRect.height -= i9;
            iConvertToScreenInt3 = 0;
        }
        if (!z) {
            viewRect.left = i3;
            viewRect.top = i2;
            viewRect.width = i4;
            viewRect.height = i5;
        }
        viewRect2.left = iConvertToScreenInt2;
        viewRect2.top = iConvertToScreenInt3;
    }

    private void layoutWithRelViewRect() {
        ArrayList<ViewRect> arrayList = this.mRelViewRectDockSet;
        if (arrayList == null) {
            return;
        }
        Iterator<ViewRect> it = arrayList.iterator();
        while (it.hasNext()) {
            layoutDockViewRect(this, it.next());
        }
    }

    public void checkValueIsPercentage(String str, int i, int i2, boolean z, boolean z2) throws JSONException {
        try {
            if (!this.mJsonViewOption.isNull(str) || z) {
                if ((this.mJsonViewOption.isNull(str) || this.mJsonViewOption.getString(str).indexOf("%") < 0) && !z2) {
                    this.mJsonViewOption.put(str, i / this.mWebviewScale);
                    return;
                }
                if (i2 > 0) {
                    this.mJsonViewOption.put(str, ((i * 100) / i2) + "%");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void commitUpdate2JSONObject(boolean z, boolean z2) throws JSONException {
        ViewRect viewRect = this.mFrameParentViewRect;
        int i = viewRect != null ? viewRect.width : this.mParentViewRect.width;
        if (viewRect == null) {
            viewRect = this.mParentViewRect;
        }
        int i2 = viewRect.height;
        checkValueIsPercentage("left", this.left, i, z, z2);
        checkValueIsPercentage("top", this.top, i2, z, z2);
        checkValueIsPercentage("width", this.width, i, z, z2);
        checkValueIsPercentage("height", this.height, i2, z, z2);
        checkValueIsPercentage("right", this.right, i, z, z2);
        checkValueIsPercentage("bottom", this.bottom, i2, z, z2);
    }

    public void delRelViewRect(ViewRect viewRect) {
        ArrayList<ViewRect> arrayList = this.mRelViewRectDockSet;
        if (arrayList != null) {
            arrayList.remove(viewRect);
        }
    }

    public ViewRect getParentViewRect() {
        return this.mParentViewRect;
    }

    public boolean hasHeightAbsolutevalue() {
        JSONObject jSONObject = this.mJsonViewOption;
        return (jSONObject == null || !jSONObject.has("height") || this.mJsonViewOption.isNull("height")) ? this.mJsonViewOption.has("bottom") && this.mJsonViewOption.has("top") : true ^ JSONUtil.getString(this.mJsonViewOption, "height").endsWith("%");
    }

    public boolean hasRectChanged(ViewRect viewRect, ViewRect viewRect2) {
        return (viewRect.left == viewRect2.left && viewRect.top == viewRect2.top && viewRect.height == viewRect2.height && viewRect.width == viewRect2.width) ? false : true;
    }

    public boolean isBottomAbsolute() {
        JSONObject jSONObject = this.mJsonViewOption;
        return jSONObject != null && jSONObject.has("bottom");
    }

    public boolean isHeightAbsolute() {
        JSONObject jSONObject = this.mJsonViewOption;
        if (jSONObject == null) {
            return true;
        }
        if (jSONObject.has("height") && !this.mJsonViewOption.isNull("height")) {
            return !JSONUtil.getString(this.mJsonViewOption, "height").equals("100%");
        }
        if (this.mJsonViewOption.has("bottom") && this.mJsonViewOption.has("top")) {
            return true;
        }
        if (!this.mJsonViewOption.has("height")) {
            this.mJsonViewOption.isNull("height");
        }
        return false;
    }

    public void onScreenChanged(int i, int i2) {
        updateViewData(this.mJsonViewOption, i, i2);
    }

    public void putRelViewRect(ViewRect viewRect) {
        if (this.mRelViewRectDockSet == null) {
            this.mRelViewRectDockSet = new ArrayList<>();
        }
        this.mRelViewRectDockSet.add(viewRect);
    }

    public void setFrameParentViewRect(ViewRect viewRect) {
        this.mFrameParentViewRect = null;
    }

    public void setParentViewRect(ViewRect viewRect) {
        this.mParentViewRect = viewRect;
    }

    public void setUpdateAction(boolean z) {
        this.doUpdate = z;
    }

    public boolean updateViewData(JSONObject jSONObject, int i, int i2) {
        return updateViewData(jSONObject, i, i2, this.mWebviewScale);
    }

    public void onScreenChanged() {
        updateViewData(this.mJsonViewOption);
    }

    public boolean updateViewData(JSONObject jSONObject, int i, int i2, float f) throws JSONException {
        int i3;
        boolean z;
        int i4;
        boolean z2;
        boolean z3;
        boolean zIsEquals;
        JSONObject jSONObject2 = jSONObject;
        JSONObject jSONObject3 = this.mJsonViewOption;
        if (jSONObject3 == null) {
            return false;
        }
        if (jSONObject3 != null) {
            JSONUtil.combinJSONObject(jSONObject3, jSONObject2);
            jSONObject2 = this.mJsonViewOption;
        } else {
            this.mJsonViewOption = jSONObject2;
        }
        int i5 = i2 < 0 ? this.height + this.bottom + this.top : i2;
        int i6 = i < 0 ? this.width : i;
        int i7 = this.left;
        int i8 = this.top;
        int i9 = this.width;
        int i10 = this.height;
        if (jSONObject2.has(AbsoluteConst.JSONKEY_STATUSBAR) && BaseInfo.isImmersive) {
            this.isStatusbar = true;
            JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject(AbsoluteConst.JSONKEY_STATUSBAR);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("background")) {
                this.mStatusbarColor = jSONObjectOptJSONObject.optString("background", this.mStatusbarColor);
            }
        }
        boolean z4 = this.doUpdate || !jSONObject2.isNull("left");
        boolean z5 = this.doUpdate || !jSONObject2.isNull("right");
        boolean z6 = this.doUpdate || !jSONObject2.isNull("top");
        if (this.doUpdate || !jSONObject2.isNull("width")) {
            i3 = i10;
            z = true;
        } else {
            i3 = i10;
            z = false;
        }
        if (this.doUpdate || !jSONObject2.isNull("height")) {
            i4 = i7;
            z2 = true;
        } else {
            i4 = i7;
            z2 = false;
        }
        boolean z7 = z6;
        boolean z8 = this.doUpdate || !jSONObject2.isNull("bottom");
        this.left = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "left"), i6, 0, f);
        this.top = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "top"), i5, 0, f);
        this.width = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "width"), i6, z ? this.width : i6, f);
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "height"), i5, z2 ? this.height : i5, f);
        this.height = iConvertToScreenInt;
        if (!z2 || iConvertToScreenInt >= i5) {
            z3 = true;
        } else {
            z3 = true;
            this.isNotHeightFullScreen = true;
            if (this.isStatusbar) {
                this.isStatusbarDodifyHeight = true;
            }
        }
        this.right = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "right"), i6, this.right, f);
        this.bottom = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject2, "bottom"), i5, this.bottom, f);
        if (jSONObject2.isNull("margin")) {
            zIsEquals = false;
        } else {
            String string = JSONUtil.getString(jSONObject2, "margin");
            this.margin = string;
            zIsEquals = PdrUtil.isEquals("auto", string);
        }
        if (z4) {
            if (!z && z5) {
                this.width = (i6 - this.left) - this.right;
            }
        } else if (!z && z5) {
            this.left = -this.right;
        } else if (z && !z5 && zIsEquals) {
            this.left = (i6 - this.width) / 2;
        } else if (z && z5) {
            this.left = (i6 - this.width) - this.right;
        }
        if (z7) {
            if (!z2 && z8) {
                this.height = (i5 - this.top) - this.bottom;
            }
        } else if (!z2 && z8) {
            this.top = -this.bottom;
        } else if (z2 && !z8 && zIsEquals) {
            this.top = (i5 - this.height) / 2;
        } else if (z2 && z8) {
            this.top = (i5 - this.height) - this.bottom;
        }
        layoutWithRelViewRect();
        layoutDockViewRect(this.mParentViewRect, this, false);
        int i11 = this.left;
        boolean z9 = (i4 == i11 && i8 == this.top && i3 == this.height && i9 == this.width) ? false : z3;
        this.anim_left = i11;
        this.anim_top = this.top;
        return z9;
    }

    public void commitUpdate2JSONObject() throws JSONException {
        commitUpdate2JSONObject(false, false);
    }

    public void updateViewData(ViewRect viewRect) {
        this.mWebviewScale = viewRect.mWebviewScale;
        this.left = viewRect.left;
        this.top = viewRect.top;
        this.width = viewRect.width;
        this.height = viewRect.height;
        this.right = viewRect.right;
        this.bottom = viewRect.bottom;
        updateViewData(viewRect.mJsonViewOption);
    }

    public static void layoutDockViewRect(ViewRect viewRect, ViewRect viewRect2) {
        layoutDockViewRect(viewRect, viewRect2, true);
    }

    public boolean updateViewData(JSONObject jSONObject) {
        ViewRect viewRect = this.mParentViewRect;
        if (viewRect == null) {
            return false;
        }
        return updateViewData(jSONObject, viewRect.width, viewRect.height);
    }
}
