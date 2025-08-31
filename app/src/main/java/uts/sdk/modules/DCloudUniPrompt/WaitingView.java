package uts.sdk.modules.DCloudUniPrompt;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.Math;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSJSONObject;
import io.dcloud.uts.prompt.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u00106\u001a\u000207H\u0016J\u0010\u00108\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0016J\b\u00109\u001a\u000207H\u0016J\b\u0010:\u001a\u000207H\u0016J\u0012\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020>H\u0016J\b\u0010?\u001a\u000207H\u0016J\u0010\u0010@\u001a\u00020$2\u0006\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u000207H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u000e\"\u0004\b*\u0010\u0010R\u000e\u0010+\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/WaitingView;", "", "context", "Landroid/app/Activity;", "style", "Lio/dcloud/uts/UTSJSONObject;", WXBasicComponentType.VIEW, "Landroid/view/View;", "(Landroid/app/Activity;Lio/dcloud/uts/UTSJSONObject;Landroid/view/View;)V", "LoadingIcon", "", "ScreenHeight", "", "getScreenHeight", "()I", "setScreenHeight", "(I)V", "currentHandler", "Landroid/os/Handler;", "density", "", "getDensity", "()Ljava/lang/Number;", "setDensity", "(Ljava/lang/Number;)V", "height", "hostView", "loadingHeight", "loadingdDisplay", "mBitmap", "Landroid/graphics/Bitmap;", "mImageView", "Landroid/widget/ImageView;", "mProgressBar", "Landroid/widget/ProgressBar;", AbsoluteConst.JSON_KEY_MASK, "", "mpopWindow", "Landroid/widget/PopupWindow;", "padding", "screenWidth", "getScreenWidth", "setScreenWidth", "seaparatorView", "Lcom/alibaba/fastjson/JSONObject;", "textSize", "textView", "Landroid/widget/TextView;", AbsoluteConst.JSON_KEY_TITLE, "waitingRootView", "Landroid/widget/LinearLayout;", "waitingView", "Landroid/view/ViewGroup;", "width", AbsoluteConst.EVENTS_CLOSE, "", "getBestScale", "handlerArguments", "initView", "inputStreamToArray", "", "inputStream", "Ljava/io/InputStream;", "makeBitmap", "passThrough", "ev", "Landroid/view/MotionEvent;", "showWaiting", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class WaitingView {
    private String LoadingIcon;
    private int ScreenHeight;
    private Activity context;
    private Handler currentHandler;
    private Number density;
    private int height;
    private View hostView;
    private Number loadingHeight;
    private String loadingdDisplay;
    private Bitmap mBitmap;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private boolean mask;
    private PopupWindow mpopWindow;
    private int padding;
    private int screenWidth;
    private View seaparatorView;
    private JSONObject style;
    private int textSize;
    private TextView textView;
    private String title;
    private LinearLayout waitingRootView;
    private ViewGroup waitingView;
    private int width;

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public int getScreenHeight() {
        return this.ScreenHeight;
    }

    public void setScreenHeight(int i) {
        this.ScreenHeight = i;
    }

    public Number getDensity() {
        return this.density;
    }

    public void setDensity(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.density = number;
    }

    public WaitingView(Activity activity, UTSJSONObject style, View view) throws IOException {
        Resources resources;
        Resources resources2;
        Resources resources3;
        Intrinsics.checkNotNullParameter(style, "style");
        this.height = -2;
        this.width = -2;
        this.title = "";
        this.density = (Number) 0;
        this.loadingdDisplay = "";
        this.loadingHeight = (Number) 0;
        this.LoadingIcon = "";
        this.context = activity;
        JSON jSONObject = style.toJSONObject();
        Intrinsics.checkNotNull(jSONObject, "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
        this.style = (JSONObject) jSONObject;
        DisplayMetrics displayMetrics = (activity == null || (resources3 = activity.getResources()) == null) ? null : resources3.getDisplayMetrics();
        Intrinsics.checkNotNull(displayMetrics);
        setScreenWidth(displayMetrics.widthPixels);
        DisplayMetrics displayMetrics2 = (activity == null || (resources2 = activity.getResources()) == null) ? null : resources2.getDisplayMetrics();
        Intrinsics.checkNotNull(displayMetrics2);
        setScreenHeight(displayMetrics2.heightPixels);
        DisplayMetrics displayMetrics3 = (activity == null || (resources = activity.getResources()) == null) ? null : resources.getDisplayMetrics();
        Intrinsics.checkNotNull(displayMetrics3);
        setDensity(Float.valueOf(displayMetrics3.density));
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout.uni_app_uni_prompt_loadingview, (ViewGroup) null, false);
        Intrinsics.checkNotNull(viewInflate, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup viewGroup = (ViewGroup) viewInflate;
        this.waitingView = viewGroup;
        View viewFindViewById = viewGroup.findViewById(R.id.dcloud_pd_root);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "this.waitingView.findVie…out>(R.id.dcloud_pd_root)");
        this.waitingRootView = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = this.waitingView.findViewById(R.id.dcloud_pb_loading);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "this.waitingView.findVie…>(R.id.dcloud_pb_loading)");
        this.mProgressBar = (ProgressBar) viewFindViewById2;
        View viewFindViewById3 = this.waitingView.findViewById(R.id.dcloud_tv_loading);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "this.waitingView.findVie…>(R.id.dcloud_tv_loading)");
        this.textView = (TextView) viewFindViewById3;
        View viewFindViewById4 = this.waitingView.findViewById(R.id.dcloud_view_seaparator);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "this.waitingView.findVie…d.dcloud_view_seaparator)");
        this.seaparatorView = viewFindViewById4;
        View viewFindViewById5 = this.waitingView.findViewById(R.id.dcloud_iv_loading);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "this.waitingView.findVie…>(R.id.dcloud_iv_loading)");
        this.mImageView = (ImageView) viewFindViewById5;
        this.hostView = view;
        this.textView.setMaxLines(2);
        handlerArguments();
        ViewGroup.LayoutParams layoutParams = this.waitingRootView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        int i = this.width;
        layoutParams2.width = i > 0 ? NumberKt.times(Integer.valueOf(i), getDensity()).intValue() : i;
        layoutParams2.height = -2;
        this.waitingRootView.setLayoutParams(layoutParams2);
        this.currentHandler = new Handler(Looper.getMainLooper());
        initView();
        makeBitmap();
    }

    public void handlerArguments() {
        if (this.style.get("height") != null) {
            Object obj = this.style.get("height");
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
            this.height = NumberKt.parseInt$default((String) obj, null, 2, null).intValue();
        }
        if (this.style.get("width") != null) {
            Object obj2 = this.style.get("width");
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
            this.width = NumberKt.parseInt$default((String) obj2, null, 2, null).intValue();
        }
        if (this.style.get(AbsoluteConst.JSON_KEY_MODAL) != null) {
            Object obj3 = this.style.get(AbsoluteConst.JSON_KEY_MODAL);
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Boolean");
            this.mask = ((Boolean) obj3).booleanValue();
        }
        Object obj4 = this.style.get("name");
        Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.String");
        this.title = (String) obj4;
        if (this.style.get("loading") != null) {
            Object obj5 = this.style.get("loading");
            Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
            JSONObject jSONObject = (JSONObject) obj5;
            Object obj6 = jSONObject.get("display");
            Intrinsics.checkNotNull(obj6, "null cannot be cast to non-null type kotlin.String");
            this.loadingdDisplay = (String) obj6;
            if (jSONObject.get(AbsoluteConst.JSON_KEY_ICON) != null) {
                Object obj7 = jSONObject.get(AbsoluteConst.JSON_KEY_ICON);
                Intrinsics.checkNotNull(obj7, "null cannot be cast to non-null type kotlin.String");
                this.LoadingIcon = (String) obj7;
            }
            if (jSONObject.get("height") != null) {
                Object obj8 = jSONObject.get("height");
                Intrinsics.checkNotNull(obj8, "null cannot be cast to non-null type kotlin.String");
                this.loadingHeight = Integer.valueOf(NumberKt.parseInt$default((String) obj8, null, 2, null).intValue());
            }
        }
        if (Intrinsics.areEqual(AbsoluteConst.JSON_VALUE_BLOCK, this.loadingdDisplay)) {
            this.waitingRootView.setOrientation(1);
            return;
        }
        if (Intrinsics.areEqual(AbsoluteConst.JSON_VALUE_INLINE, this.loadingdDisplay)) {
            this.waitingRootView.setOrientation(0);
        } else if (Intrinsics.areEqual("none", this.loadingdDisplay)) {
            this.seaparatorView.setVisibility(8);
            this.mProgressBar.setVisibility(8);
        }
    }

    public void initView() {
        this.textView.setTextColor(-1);
        this.textView.setGravity(17);
        this.textView.setText(this.title);
        this.textView.setTextSize(0, NumberKt.times((Number) 16, getDensity()).floatValue());
        if (this.title.length() < 1) {
            this.textView.setVisibility(8);
            this.waitingRootView.setPadding(NumberKt.times((Number) 10, getDensity()).intValue(), NumberKt.times((Number) 40, getDensity()).intValue(), NumberKt.times((Number) 10, getDensity()).intValue(), NumberKt.times((Number) 30, getDensity()).intValue());
        } else {
            this.waitingRootView.setPadding(NumberKt.times((Number) 10, getDensity()).intValue(), NumberKt.times((Number) 20, getDensity()).intValue(), NumberKt.times((Number) 10, getDensity()).intValue(), NumberKt.times((Number) 20, getDensity()).intValue());
        }
        Activity activity = this.context;
        Drawable drawable = activity != null ? activity.getDrawable(R.drawable.uni_app_uni_prompt_circle_white_progress) : null;
        Intrinsics.checkNotNull(drawable);
        if (NumberKt.compareTo(this.loadingHeight, (Number) 0) > 0) {
            this.mProgressBar.setLayoutParams(new LinearLayout.LayoutParams(NumberKt.times(this.loadingHeight, getDensity()).intValue(), NumberKt.times(this.loadingHeight, getDensity()).intValue()));
        } else {
            int intrinsicHeight = (int) (drawable.getIntrinsicHeight() * 0.3d);
            this.mProgressBar.setLayoutParams(new LinearLayout.LayoutParams(intrinsicHeight, intrinsicHeight));
        }
        this.mProgressBar.setIndeterminateDrawable(drawable);
        this.waitingRootView.setFocusable(true);
        this.waitingRootView.setAlpha(0.9f);
    }

    public boolean passThrough(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        Activity activity = this.context;
        if (activity == null) {
            return false;
        }
        Intrinsics.checkNotNull(activity);
        return activity.dispatchTouchEvent(ev);
    }

    public void showWaiting() {
        int i;
        Activity activity = this.context;
        if (activity != null) {
            Intrinsics.checkNotNull(activity);
            if (activity.isFinishing() || this.hostView == null) {
                return;
            }
            if (this.mask) {
                this.mask = false;
                i = -1;
            } else {
                i = -2;
            }
            PopupWindow popupWindow = new PopupWindow(this.waitingView, i, i, this.mask);
            this.mpopWindow = popupWindow;
            View view = this.hostView;
            Intrinsics.checkNotNull(view);
            popupWindow.showAtLocation(view, 17, 0, 0);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(new WaitingDismissListener());
            popupWindow.setTouchInterceptor(new TouchInterceptorListener(this.mask));
        }
    }

    public void close() {
        PopupWindow popupWindow = this.mpopWindow;
        if (popupWindow != null) {
            Intrinsics.checkNotNull(popupWindow, "null cannot be cast to non-null type android.widget.PopupWindow");
            if (popupWindow.isShowing()) {
                Handler handler = this.currentHandler;
                PopupWindow popupWindow2 = this.mpopWindow;
                Intrinsics.checkNotNull(popupWindow2, "null cannot be cast to non-null type android.widget.PopupWindow");
                handler.post(new MainThreadRunnable(popupWindow2));
            }
        }
        if (this.mBitmap != null) {
            this.mBitmap = null;
        }
    }

    public void makeBitmap() throws IOException {
        String resourcePath;
        String str = this.LoadingIcon;
        if (str != null) {
            byte[] bArrInputStreamToArray = null;
            bArrInputStreamToArray = null;
            bArrInputStreamToArray = null;
            if (Intrinsics.areEqual(str, "successIcon")) {
                Activity activity = this.context;
                Resources resources = activity != null ? activity.getResources() : null;
                Intrinsics.checkNotNull(resources);
                Intrinsics.checkNotNull(resources);
                InputStream inputStreamOpen = resources.getAssets().open("uni-uts/uni-prompt/uni_app_toast_success.png");
                Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "this.context?.getResourc…i_app_toast_success.png\")");
                bArrInputStreamToArray = inputStreamToArray(inputStreamOpen);
            } else if (Intrinsics.areEqual(this.LoadingIcon, "errorIcon")) {
                Activity activity2 = this.context;
                Resources resources2 = activity2 != null ? activity2.getResources() : null;
                Intrinsics.checkNotNull(resources2);
                Intrinsics.checkNotNull(resources2);
                InputStream inputStreamOpen2 = resources2.getAssets().open("uni-uts/uni-prompt/uni_app_toast_error.png");
                Intrinsics.checkNotNullExpressionValue(inputStreamOpen2, "this.context?.getResourc…uni_app_toast_error.png\")");
                bArrInputStreamToArray = inputStreamToArray(inputStreamOpen2);
            } else {
                if (this.LoadingIcon.length() <= 0) {
                    return;
                }
                if (StringsKt.startsWith$default(this.LoadingIcon, DeviceInfo.FILE_PROTOCOL, false, 2, (Object) null)) {
                    resourcePath = StringKt.replace(this.LoadingIcon, DeviceInfo.FILE_PROTOCOL, "");
                } else {
                    resourcePath = UTSAndroid.INSTANCE.getResourcePath(this.LoadingIcon);
                }
                if (resourcePath != null && resourcePath.length() > 0) {
                    if (StringsKt.startsWith$default(resourcePath, "/android_asset/", false, 2, (Object) null)) {
                        String strSlice$default = StringKt.slice$default(resourcePath, (Number) 15, null, 2, null);
                        Activity activity3 = this.context;
                        Resources resources3 = activity3 != null ? activity3.getResources() : null;
                        Intrinsics.checkNotNull(resources3);
                        Intrinsics.checkNotNull(resources3);
                        InputStream inputStreamOpen3 = resources3.getAssets().open(strSlice$default);
                        Intrinsics.checkNotNullExpressionValue(inputStreamOpen3, "this.context?.getResourc…ts().open(assetImagePath)");
                        bArrInputStreamToArray = inputStreamToArray(inputStreamOpen3);
                    } else {
                        try {
                            FileInputStream fileInputStream = new FileInputStream(new File(resourcePath));
                            bArrInputStreamToArray = inputStreamToArray(fileInputStream);
                            fileInputStream.close();
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            if (bArrInputStreamToArray == null || bArrInputStreamToArray.length == 0) {
                return;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArrInputStreamToArray, 0, bArrInputStreamToArray.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            options.inSampleSize = getBestScale(i2);
            options.inJustDecodeBounds = false;
            this.mBitmap = BitmapFactory.decodeByteArray(bArrInputStreamToArray, 0, bArrInputStreamToArray.length, options);
            this.mProgressBar.setVisibility(8);
            this.mImageView.setVisibility(0);
            this.mImageView.setImageBitmap(this.mBitmap);
            if (i % i2 != 0) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = this.mImageView.getLayoutParams();
            Intrinsics.checkNotNullExpressionValue(layoutParams, "this.mImageView.getLayoutParams()");
            if (NumberKt.compareTo(this.loadingHeight, (Number) 0) > 0) {
                layoutParams.height = NumberKt.times(this.loadingHeight, getDensity()).intValue();
                layoutParams.width = NumberKt.times(this.loadingHeight, getDensity()).intValue();
            } else {
                Bitmap bitmap = this.mBitmap;
                Intrinsics.checkNotNull(bitmap);
                Intrinsics.checkNotNull(bitmap);
                layoutParams.width = bitmap.getHeight();
                Bitmap bitmap2 = this.mBitmap;
                Intrinsics.checkNotNull(bitmap2);
                Intrinsics.checkNotNull(bitmap2);
                layoutParams.height = bitmap2.getHeight();
            }
            this.mImageView.setLayoutParams(layoutParams);
        }
    }

    public int getBestScale(int height) {
        Number numberMinus = NumberKt.minus(Math.min(Integer.valueOf(getScreenWidth()), Integer.valueOf(getScreenHeight())), NumberKt.times((Number) 20, getDensity()));
        if (NumberKt.compareTo(numberMinus, (Number) 0) <= 0 || NumberKt.compareTo(Integer.valueOf(height), numberMinus) <= 0) {
            return 1;
        }
        return NumberKt.div(Integer.valueOf(height), numberMinus).intValue();
    }

    public byte[] inputStreamToArray(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Throwable unused) {
            return null;
        }
    }
}
