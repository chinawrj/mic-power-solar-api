package com.taobao.weex.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import androidx.appcompat.widget.SwitchCompat;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;

/* loaded from: classes3.dex */
public class WXSwitchView extends SwitchCompat implements WXGestureObservable {
    private WXGesture wxGesture;

    public WXSwitchView(Context context) {
        super(context);
        setShowText(false);
        setGravity(16);
    }

    @Override // com.taobao.weex.ui.view.gesture.WXGestureObservable
    public WXGesture getGestureListener() {
        return this.wxGesture;
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) throws Resources.NotFoundException {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        WXGesture wXGesture = this.wxGesture;
        return wXGesture != null ? zOnTouchEvent | wXGesture.onTouch(this, motionEvent) : zOnTouchEvent;
    }

    @Override // com.taobao.weex.ui.view.gesture.WXGestureObservable
    public void registerGestureListener(WXGesture wXGesture) {
        this.wxGesture = wXGesture;
    }
}
