package com.taobao.weex.ui.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class WXCirclePageAdapter extends PagerAdapter {
    public boolean isRTL;
    private boolean needLoop;
    private List<View> originalViews;
    private List<View> shadow;
    private List<View> views;

    public WXCirclePageAdapter(List<View> list, boolean z) {
        this.views = new ArrayList();
        this.shadow = new ArrayList();
        this.needLoop = true;
        this.isRTL = false;
        this.originalViews = new ArrayList();
        this.views = new ArrayList(list);
        this.originalViews = new ArrayList(list);
        this.needLoop = z;
    }

    private void ensureShadow() {
        ArrayList arrayList = new ArrayList();
        if (!this.needLoop || this.views.size() <= 2) {
            arrayList.addAll(this.views);
        } else {
            arrayList.add(0, this.views.get(r1.size() - 1));
            for (View view : this.views) {
                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                arrayList.add(view);
            }
            arrayList.add(this.views.get(0));
        }
        this.shadow.clear();
        notifyDataSetChanged();
        this.shadow.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void addPageView(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("onPageSelected >>>> addPageView");
        }
        this.originalViews.add(view);
        if (this.isRTL) {
            this.views.add(0, view);
        } else {
            this.views.add(view);
        }
        ensureShadow();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("onPageSelected >>>> destroyItem >>>>> position:" + i);
        }
        if (this.needLoop) {
            return;
        }
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.shadow.size();
    }

    public int getFirst() {
        return (!this.needLoop || this.views.size() <= 2) ? 0 : 1;
    }

    public int getItemIndex(Object obj) {
        if (obj instanceof View) {
            return this.views.indexOf(obj);
        }
        return -1;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    public int getPagePosition(View view) {
        return this.views.indexOf(view);
    }

    public int getRealCount() {
        return this.views.size();
    }

    public int getRealPosition(int i) {
        if (i < 0 || i >= this.shadow.size()) {
            return -1;
        }
        return getItemIndex(this.shadow.get(i));
    }

    public List<View> getViews() {
        return this.views;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = null;
        try {
            View view2 = this.shadow.get(i);
            try {
                if (WXEnvironment.isApkDebugable()) {
                    WXLogUtils.d("onPageSelected >>>> instantiateItem >>>>> position:" + i + ",position % getRealCount()" + (i % getRealCount()));
                }
                if (view2.getParent() == null) {
                    viewGroup.addView(view2);
                    return view2;
                }
                ((ViewGroup) view2.getParent()).removeView(view2);
                viewGroup.addView(view2);
                return view2;
            } catch (Exception e) {
                e = e;
                view = view2;
                WXLogUtils.e("[CirclePageAdapter] instantiateItem: ", e);
                return view;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void removePageView(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("onPageSelected >>>> removePageView");
        }
        this.views.remove(view);
        this.originalViews.remove(view);
        ensureShadow();
    }

    public void replacePageView(View view, View view2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (WXEnvironment.isApkDebugable()) {
            WXLogUtils.d("onPageSelected >>>> replacePageView");
        }
        int iIndexOf = this.views.indexOf(view);
        this.views.remove(iIndexOf);
        this.views.add(iIndexOf, view2);
        ensureShadow();
        int iIndexOf2 = this.originalViews.indexOf(view);
        this.originalViews.remove(iIndexOf2);
        this.originalViews.add(iIndexOf2, view2);
    }

    public void setLayoutDirectionRTL(boolean z) {
        if (z == this.isRTL) {
            return;
        }
        this.isRTL = z;
        ArrayList arrayList = new ArrayList(this.originalViews);
        this.views = arrayList;
        if (z) {
            Collections.reverse(arrayList);
        }
        ensureShadow();
    }

    public WXCirclePageAdapter() {
        this(true);
    }

    public WXCirclePageAdapter(boolean z) {
        this.views = new ArrayList();
        this.shadow = new ArrayList();
        this.needLoop = true;
        this.isRTL = false;
        this.originalViews = new ArrayList();
        this.needLoop = z;
    }
}
