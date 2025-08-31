package com.taobao.weex.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class WXHorizontalScrollView extends HorizontalScrollView implements IWXScroller, WXGestureObservable {
    private ScrollViewListener mScrollViewListener;
    private List<ScrollViewListener> mScrollViewListeners;
    private boolean scrollable;
    private WXGesture wxGesture;

    public interface ScrollViewListener {
        void onScrollChanged(WXHorizontalScrollView wXHorizontalScrollView, int i, int i2, int i3, int i4);
    }

    public WXHorizontalScrollView(Context context) {
        super(context);
        this.scrollable = true;
        init();
    }

    private void init() {
        setWillNotDraw(false);
        setOverScrollMode(2);
    }

    public void addScrollViewListener(ScrollViewListener scrollViewListener) {
        if (this.mScrollViewListeners == null) {
            this.mScrollViewListeners = new CopyOnWriteArrayList();
        }
        if (this.mScrollViewListeners.contains(scrollViewListener)) {
            return;
        }
        this.mScrollViewListeners.add(scrollViewListener);
    }

    @Override // com.taobao.weex.ui.view.IWXScroller
    public void destroy() {
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        WXGesture wXGesture = this.wxGesture;
        return wXGesture != null ? zDispatchTouchEvent | wXGesture.onTouch(this, motionEvent) : zDispatchTouchEvent;
    }

    public Rect getContentFrame() {
        return new Rect(0, 0, computeHorizontalScrollRange(), computeVerticalScrollRange());
    }

    @Override // com.taobao.weex.ui.view.gesture.WXGestureObservable
    public WXGesture getGestureListener() {
        return this.wxGesture;
    }

    public boolean isScrollable() {
        return this.scrollable;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        ScrollViewListener scrollViewListener = this.mScrollViewListener;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, i, i2, i3, i4);
        }
        List<ScrollViewListener> list = this.mScrollViewListeners;
        if (list != null) {
            Iterator<ScrollViewListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onScrollChanged(this, i, i2, i3, i4);
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.scrollable) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // com.taobao.weex.ui.view.gesture.WXGestureObservable
    public void registerGestureListener(WXGesture wXGesture) {
        this.wxGesture = wXGesture;
    }

    public void removeScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mScrollViewListeners.remove(scrollViewListener);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mScrollViewListener = scrollViewListener;
    }

    public void setScrollable(boolean z) {
        this.scrollable = z;
    }

    public WXHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scrollable = true;
        init();
    }
}
