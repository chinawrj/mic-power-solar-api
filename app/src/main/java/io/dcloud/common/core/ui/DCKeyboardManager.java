package io.dcloud.common.core.ui;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.FrameLayout;
import io.dcloud.common.DHInterface.IActivityDelegate;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.core.ui.keyboard.DCEditText;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.lang.ref.SoftReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class DCKeyboardManager {
    public static String SOFT_INPUT_MODE_ADJUST_NOTHING = "nothing";
    public static String SOFT_INPUT_MODE_ADJUST_PAN = "adjustPan";
    public static String SOFT_INPUT_MODE_ADJUST_RESIZE = "adjustResize";
    public static DCKeyboardManager instance;
    private Runnable keyBoardHideRunnable;
    private Runnable keyBoardShowRunnable;
    private View mContentView;
    private io.dcloud.common.core.ui.a mDHAppRoot;
    private Handler mHandler;
    String mInputMode;
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener;
    private View mRootContentView;
    int rootViewVisibleHeight;
    private final String TAG = "DCKeyboardManager";
    private float mFocusTop = 0.0f;
    private boolean isNativeFocus = false;
    private View mNativeView = null;
    private String mFrontInputType = "text";
    private String mActivitySoftInputMode = "";
    int mOrientation = -100;
    private boolean isAdjust = true;
    private IFrameView mInputRootFrame = null;
    boolean isAdministration = false;
    private boolean isAllScreen = false;
    private SoftReference<Activity> mSoftAc = null;
    private float mHtmlInputFT = -1.0f;
    boolean isNativeUpDate = false;
    float mNaiveCursorSpacing = 0.0f;
    String EVENTS_DOCUMENT_KEYBOARD = "javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true); e.height = %d;/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();";

    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ Activity a;

        /* renamed from: io.dcloud.common.core.ui.DCKeyboardManager$a$a, reason: collision with other inner class name */
        class RunnableC0033a implements Runnable {
            final /* synthetic */ int a;

            RunnableC0033a(int i) {
                this.a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                DCKeyboardManager.this.onKeyboardChanged(this.a, true);
                AndroidResources.sIMEAlive = true;
                DeviceInfo.isIMEShow = true;
                DeviceInfo.sInputMethodHeight = Math.abs(this.a);
            }
        }

        class b implements Runnable {
            b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DCKeyboardManager.this.onKeyboardChanged(-1, AndroidResources.sIMEAlive);
                if (DCKeyboardManager.this.mDHAppRoot != null) {
                    DCKeyboardManager.this.mDHAppRoot.g();
                }
                AndroidResources.sIMEAlive = false;
                DeviceInfo.isIMEShow = false;
            }
        }

        a(Activity activity) {
            this.a = activity;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(Activity activity) {
            int i;
            int height = DCKeyboardManager.this.mRootContentView.getHeight();
            int height2 = ((FrameLayout) DCKeyboardManager.this.mRootContentView).getChildAt(0).getHeight();
            DCKeyboardManager dCKeyboardManager = DCKeyboardManager.this;
            if (dCKeyboardManager.rootViewVisibleHeight == 0) {
                dCKeyboardManager.rootViewVisibleHeight = height;
                return;
            }
            boolean zIsFullScreen = PdrUtil.isFullScreen(activity);
            Rect rect = new Rect();
            DCKeyboardManager.this.mContentView.getWindowVisibleDisplayFrame(rect);
            int iHeight = rect.height();
            if (!zIsFullScreen && (i = DeviceInfo.sStatusBarHeight + iHeight) <= height2) {
                iHeight = i;
            }
            int i2 = DCKeyboardManager.this.isAllScreen ? height / 6 : height / 5;
            DCKeyboardManager dCKeyboardManager2 = DCKeyboardManager.this;
            if (dCKeyboardManager2.rootViewVisibleHeight == iHeight) {
                return;
            }
            int i3 = height2 - iHeight;
            try {
                if (i3 > i2) {
                    if (dCKeyboardManager2.keyBoardShowRunnable != null) {
                        DCKeyboardManager.this.mHandler.removeCallbacks(DCKeyboardManager.this.keyBoardShowRunnable);
                    }
                    DCKeyboardManager.this.keyBoardShowRunnable = new RunnableC0033a(i3);
                    DCKeyboardManager.this.mHandler.post(DCKeyboardManager.this.keyBoardShowRunnable);
                } else {
                    if (dCKeyboardManager2.keyBoardHideRunnable != null) {
                        DCKeyboardManager.this.mHandler.removeCallbacks(DCKeyboardManager.this.keyBoardHideRunnable);
                    }
                    DCKeyboardManager.this.keyBoardHideRunnable = new b();
                    DCKeyboardManager.this.mHandler.post(DCKeyboardManager.this.keyBoardHideRunnable);
                }
                DCKeyboardManager.this.rootViewVisibleHeight = iHeight;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (DCKeyboardManager.this.mContentView == null) {
                return;
            }
            View view = DCKeyboardManager.this.mContentView;
            final Activity activity = this.a;
            view.post(new Runnable() { // from class: io.dcloud.common.core.ui.DCKeyboardManager$a$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.a(activity);
                }
            });
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DCKeyboardManager.this.onKeyboardChanged(DCKeyboardManager.this.getKeyBoardHeight(), true);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DCKeyboardManager.this.onKeyboardChanged(DCKeyboardManager.this.getKeyBoardHeight(), true);
        }
    }

    class d implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ AdaFrameView b;
        final /* synthetic */ View c;

        d(String str, AdaFrameView adaFrameView, View view) {
            this.a = str;
            this.b = adaFrameView;
            this.c = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.equals(DCKeyboardManager.SOFT_INPUT_MODE_ADJUST_PAN)) {
                AdaFrameView adaFrameView = this.b;
                int i = adaFrameView != null ? adaFrameView.obtainFrameOptions().top : 0;
                View view = this.c;
                if (view != null) {
                    view.setTranslationY(i);
                }
            }
            ViewGroup.LayoutParams layoutParams = DCKeyboardManager.this.mContentView.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = -1;
            DCKeyboardManager.this.mContentView.setLayoutParams(layoutParams);
        }
    }

    class e implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ View b;

        e(int i, View view) {
            this.a = i;
            this.b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.setTranslationY(this.a - DCKeyboardManager.this.mFocusTop);
        }
    }

    private void fireKeyboardEvent(IFrameView iFrameView, int i) {
        if (!iFrameView.obtainWebView().isUniWebView()) {
            iFrameView.obtainWebView().loadUrl(StringUtil.format(this.EVENTS_DOCUMENT_KEYBOARD, "keyboardchange", Integer.valueOf((int) (i / iFrameView.obtainWebView().getScale()))));
        }
        if (BaseInfo.isUniAppAppid(iFrameView.obtainApp())) {
            iFrameView.obtainWindowMgr().processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iFrameView.obtainApp(), "weex,io.dcloud.feature.weex.WeexFeature", "onKeyboardHeightChange", new Object[]{"__uniapp__service", Integer.valueOf(i)}});
        }
    }

    private String getActivityInput(Activity activity) {
        int i = activity.getWindow().getAttributes().softInputMode;
        if (i == 16) {
            return SOFT_INPUT_MODE_ADJUST_RESIZE;
        }
        if (i == 32) {
            return SOFT_INPUT_MODE_ADJUST_PAN;
        }
        if (i == 48) {
            return SOFT_INPUT_MODE_ADJUST_NOTHING;
        }
        String metaValue = AndroidResources.getMetaValue("DCLOUD_INPUT_MODE");
        return !TextUtils.isEmpty(metaValue) ? metaValue : SOFT_INPUT_MODE_ADJUST_RESIZE;
    }

    public static DCKeyboardManager getInstance() {
        if (instance == null) {
            instance = new DCKeyboardManager();
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getKeyBoardHeight() {
        SoftReference<Activity> softReference;
        int i;
        if (this.mContentView == null || (softReference = this.mSoftAc) == null || softReference.get() == null) {
            return 0;
        }
        boolean zIsFullScreen = PdrUtil.isFullScreen(this.mSoftAc.get());
        Rect rect = new Rect();
        this.mContentView.getWindowVisibleDisplayFrame(rect);
        int iHeight = rect.height();
        int height = ((FrameLayout) this.mRootContentView).getChildAt(0).getHeight();
        if (!zIsFullScreen && (i = DeviceInfo.sStatusBarHeight + iHeight) <= height) {
            iHeight = i;
        }
        return height - iHeight;
    }

    private static View getScrollView(View view) {
        if (view instanceof ViewGroup) {
            try {
                if (view.canScrollVertically(1)) {
                    return view;
                }
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    View scrollView = getScrollView(((ViewGroup) view).getChildAt(i));
                    if (scrollView != null) {
                        return scrollView;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private void keyboardHide(AdaFrameView adaFrameView, View view, String str) {
        if (this.isAdjust) {
            this.mFocusTop = 0.0f;
            this.mContentView.post(new d(str, adaFrameView, view));
        }
    }

    private void keyboardShow(View view, int i, String str, boolean z) {
        if (str.equals(SOFT_INPUT_MODE_ADJUST_NOTHING) || !this.isAdjust || DeviceInfo.isIMEShow) {
            return;
        }
        int height = this.mContentView.getHeight();
        if (str.equals(SOFT_INPUT_MODE_ADJUST_RESIZE)) {
            ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
            layoutParams.height = height - i;
            this.mContentView.setLayoutParams(layoutParams);
        } else {
            int i2 = height - i;
            if (!z) {
                this.mFocusTop = this.mHtmlInputFT;
            }
            if (i2 < this.mFocusTop - (DeviceInfo.isIMEShow ? 0.0f - view.getTranslationY() : 0.0f)) {
                this.mContentView.post(new e(i2, view));
            }
        }
    }

    public void dhAppRootIsReady(io.dcloud.common.core.ui.a aVar) {
        if (this.isAdministration) {
            this.mDHAppRoot = aVar;
            this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutChangeListener);
        }
    }

    public String getFrameSoftInputMode() {
        io.dcloud.common.core.ui.a aVar = this.mDHAppRoot;
        if (aVar != null) {
            return aVar.h().obtainFrameOptions().softinputMode;
        }
        return null;
    }

    public String getFrontInputType() {
        return this.mFrontInputType;
    }

    public View getNativeInput() {
        return this.mNativeView;
    }

    public boolean isTakeOver() {
        String frameSoftInputMode = getInstance().getFrameSoftInputMode();
        if (frameSoftInputMode != null) {
            return SOFT_INPUT_MODE_ADJUST_PAN.equals(frameSoftInputMode) || SOFT_INPUT_MODE_ADJUST_NOTHING.equals(frameSoftInputMode);
        }
        return false;
    }

    public void nativeEditTextFocus(String str, View view, boolean z, String str2, float f) {
        this.isNativeFocus = z;
        this.isNativeUpDate = true;
        this.mInputRootFrame = null;
        this.mInputMode = str2;
        if (z) {
            this.mNativeView = view;
            this.mNaiveCursorSpacing = f;
            if (this.mDHAppRoot != null && !TextUtils.isEmpty(str)) {
                io.dcloud.common.core.ui.b bVarH = this.mDHAppRoot.h();
                if (bVarH == null) {
                    return;
                }
                Object objProcessEvent = bVarH.obtainWindowMgr().processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{bVarH.obtainApp(), "weex,io.dcloud.feature.weex.WeexFeature", "findWebviewByInstanceId", new Object[]{str}});
                if (objProcessEvent != null && (objProcessEvent instanceof IWebview)) {
                    this.mInputRootFrame = ((IWebview) objProcessEvent).obtainFrameView();
                }
            }
            if (!str2.equals(SOFT_INPUT_MODE_ADJUST_NOTHING) && DeviceInfo.isIMEShow) {
                Runnable runnable = this.keyBoardShowRunnable;
                if (runnable != null) {
                    this.mHandler.removeCallbacks(runnable);
                }
                c cVar = new c();
                this.keyBoardShowRunnable = cVar;
                this.mHandler.post(cVar);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate(Activity activity) {
        this.mActivitySoftInputMode = getActivityInput(activity);
        this.isAdministration = true;
        this.mSoftAc = new SoftReference<>(activity);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mOrientation = activity.getResources().getConfiguration().orientation;
        this.mRootContentView = activity.getWindow().getDecorView();
        IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(activity);
        if (iActivityHandler != null) {
            this.mContentView = iActivityHandler.obtainActivityContentView();
        } else if (activity instanceof IActivityDelegate) {
            this.mContentView = ((IActivityDelegate) activity).obtainActivityContentView();
        }
        this.isAllScreen = PdrUtil.isAllScreenDevice(activity);
        DeviceInfo.updateStatusBarHeight(activity);
        this.mLayoutChangeListener = new a(activity);
    }

    public void onKeyboardChanged(int i, boolean z) {
        io.dcloud.common.core.ui.b bVarH;
        View viewObtainMainView;
        View view;
        if (this.isAdjust && this.mDHAppRoot != null && this.mActivitySoftInputMode.equals(SOFT_INPUT_MODE_ADJUST_RESIZE) && (bVarH = this.mDHAppRoot.h()) != null) {
            if (TextUtils.isEmpty(this.mInputMode)) {
                this.mInputMode = getFrameSoftInputMode();
            }
            if (z) {
                IFrameView iFrameView = this.mInputRootFrame;
                if (iFrameView != null && iFrameView.obtainWebView() != null) {
                    fireKeyboardEvent(this.mInputRootFrame, i);
                } else if (bVarH.obtainWebView() != null) {
                    fireKeyboardEvent(bVarH, i);
                }
            }
            if (i > 1 && this.mInputMode.equals(SOFT_INPUT_MODE_ADJUST_NOTHING)) {
                View view2 = this.mNativeView;
                if (view2 == null || !(view2 instanceof DCEditText) || ((DCEditText) view2).getKeyboardHeightChangeListener() == null) {
                    return;
                }
                ((DCEditText) this.mNativeView).getKeyboardHeightChangeListener().onChange(true, i);
                return;
            }
            int height = this.mContentView.getHeight();
            IFrameView iFrameView2 = this.mInputRootFrame;
            if (iFrameView2 == null) {
                viewObtainMainView = bVarH.getChilds().size() > 1 ? bVarH.obtainMainView() : bVarH.obtainWebView().obtainWindowView();
            } else {
                if (iFrameView2.obtainWebView() == null) {
                    this.mInputRootFrame = null;
                    return;
                }
                viewObtainMainView = this.mInputRootFrame.obtainWebView().obtainWindowView();
            }
            try {
                if (i <= 0) {
                    View view3 = this.mNativeView;
                    if ((view3 instanceof DCEditText) && ((DCEditText) view3).getKeyboardHeightChangeListener() != null) {
                        ((DCEditText) this.mNativeView).getKeyboardHeightChangeListener().onChange(false, i);
                    }
                    if (this.mInputRootFrame != null || bVarH.getChilds().size() <= 1) {
                        bVarH = null;
                    }
                    keyboardHide(bVarH, viewObtainMainView, this.mInputMode);
                    this.isNativeUpDate = true;
                    return;
                }
                if (!this.isNativeFocus || (view = this.mNativeView) == null) {
                    this.mNativeView = null;
                    this.isNativeUpDate = true;
                    if (this.mInputMode.equals(SOFT_INPUT_MODE_ADJUST_PAN)) {
                        float f = this.mHtmlInputFT;
                        float f2 = height;
                        if (f > f2) {
                            int i2 = (int) (f - f2);
                            if (viewObtainMainView instanceof WebView) {
                                viewObtainMainView.scrollBy(0, i2);
                            }
                            this.mHtmlInputFT = f2;
                        }
                    }
                    keyboardShow(viewObtainMainView, i, this.mInputMode, false);
                    return;
                }
                if (this.isNativeUpDate) {
                    view.getLocationOnScreen(new int[2]);
                    float height2 = r5[1] + this.mNativeView.getHeight() + this.mNaiveCursorSpacing;
                    this.mFocusTop = height2;
                    float f3 = height;
                    if (height2 > f3) {
                        int i3 = (int) (height2 - f3);
                        View scrollView = getScrollView(viewObtainMainView);
                        if (scrollView != null) {
                            scrollView.scrollBy(0, i3);
                        }
                        this.mFocusTop = f3;
                    }
                    this.isNativeUpDate = false;
                }
                View view4 = this.mNativeView;
                if ((view4 instanceof DCEditText) && ((DCEditText) view4).getKeyboardHeightChangeListener() != null) {
                    ((DCEditText) this.mNativeView).getKeyboardHeightChangeListener().onChange(true, i);
                }
                keyboardShow(viewObtainMainView, i, this.mInputMode, true);
            } catch (Exception unused) {
                this.mInputMode = null;
                this.mNativeView = null;
            }
        }
    }

    public void onStop() {
        if (this.isAdministration) {
            this.mRootContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this.mLayoutChangeListener);
        }
    }

    public void setAdjust(boolean z) {
        this.isAdjust = z;
    }

    public void setContentView(IActivityHandler iActivityHandler) {
        if (iActivityHandler != null) {
            this.mContentView = iActivityHandler.obtainActivityContentView();
        }
    }

    public void setFrontInputType(String str) {
        this.mFrontInputType = str;
    }

    public void setHTMLInputRect(IWebview iWebview, String str) throws JSONException {
        try {
            if (this.isAdjust) {
                IFrameView iFrameViewObtainFrameView = iWebview.obtainFrameView();
                this.mInputRootFrame = iFrameViewObtainFrameView;
                if (iFrameViewObtainFrameView == null) {
                    return;
                }
                iFrameViewObtainFrameView.obtainWebView().obtainWindowView().getLocationOnScreen(new int[2]);
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = jSONObject.getJSONObject("position");
                String strOptString = jSONObject.optString("mode");
                this.mInputMode = strOptString;
                if (strOptString.equals(SOFT_INPUT_MODE_ADJUST_NOTHING)) {
                    return;
                }
                float f = PdrUtil.parseFloat(jSONObject2.optString("top"), 0.0f, 0.0f, iWebview.getScale()) + PdrUtil.parseFloat(jSONObject2.optString("height"), 0.0f, 0.0f, iWebview.getScale()) + r2[1];
                this.mHtmlInputFT = f;
                this.mFocusTop = f;
                if (DeviceInfo.isIMEShow) {
                    Runnable runnable = this.keyBoardShowRunnable;
                    if (runnable != null) {
                        this.mHandler.removeCallbacks(runnable);
                    }
                    b bVar = new b();
                    this.keyBoardShowRunnable = bVar;
                    this.mHandler.post(bVar);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mInputMode = null;
            this.mFocusTop = 0.0f;
            this.mHtmlInputFT = 0.0f;
        }
    }

    public void setNativeInput(View view, float f) {
        this.mNativeView = view;
        this.mNaiveCursorSpacing = f;
        this.isNativeUpDate = true;
    }
}
