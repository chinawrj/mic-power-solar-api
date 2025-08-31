package io.dcloud.feature.nativeObj.photoview;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.nostra13.dcloudimageloader.core.DisplayImageOptions;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.EventDispatchManager;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.RuningAcitvityUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.nativeObj.BannerLayout;
import io.dcloud.feature.nativeObj.data.NativeImageDataItem;
import io.dcloud.feature.nativeObj.photoview.subscaleview.BitmapUtil;
import io.dcloud.feature.nativeObj.photoview.subscaleview.ImageSource;
import io.dcloud.feature.nativeObj.photoview.subscaleview.SubsamplingScaleImageView;
import io.src.dcloud.adapter.DCloudBaseActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PhotoActivity extends DCloudBaseActivity {
    public static String IMAGE_BG_KEY = "image_backgroud_color";
    public static String IMAGE_CURRENT_INDEX_KEY = "image_current_index";
    public static String IMAGE_INDICATOR_KEY = "image_indicator";
    public static String IMAGE_LOOP_KEY = "image_loop";
    public static String IMAGE_PHOTO_KEY = "image_photo";
    public static String IMAGE_PHOTO_TOP = "image_photo_top";
    public static String IMAGE_URLLIST_KEY = "image_urlList";
    public static String IMAGE_URLS_KEY = "image_urls";
    private String callbackId;
    private String[] localImageUrls;
    private DisplayImageOptions mDefOps;
    ArrayList<NativeImageDataItem> mImageUrls;
    private ArrayList originalImageUrls;
    private BannerLayout photoLayout;
    int mCurrentItmeIndex = 0;
    public String mIndicatorType = "default";
    public boolean isBack = false;

    private DisplayImageOptions getIconDisplayOptions() {
        return new DisplayImageOptions.Builder().cacheOnDisc(true).cacheInMemory(true).imageScaleType(ImageScaleType.NONE).bitmapConfig(Bitmap.Config.ARGB_8888).showImageOnLoading(new ColorDrawable(0)).build();
    }

    public ArrayList<NativeImageDataItem> listToNativeDataItems(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return null;
        }
        ArrayList<NativeImageDataItem> arrayList2 = new ArrayList<>();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            NativeImageDataItem nativeImageDataItem = new NativeImageDataItem();
            nativeImageDataItem.setUrl(next);
            arrayList2.add(nativeImageDataItem);
        }
        return arrayList2;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (EventDispatchManager.getInstance().dispatchEvent(ISysEventListener.SysEventType.onKeyUp, new Object[]{4, null})) {
            return;
        }
        if (this.callbackId != null) {
            LongClickEventManager.getInstance().removeOnLongClickListener(this.callbackId);
        }
        RuningAcitvityUtil.removeRuningActivity(getComponentName().getClassName());
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override // io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws IllegalAccessException, NoSuchFieldException, Resources.NotFoundException, SecurityException, IllegalArgumentException {
        super.onCreate(bundle);
        RuningAcitvityUtil.putRuningActivity(this);
        this.mDefOps = getIconDisplayOptions();
        Intent intent = getIntent();
        if (intent.hasExtra(IMAGE_URLLIST_KEY)) {
            this.mImageUrls = listToNativeDataItems(intent.getStringArrayListExtra(IMAGE_URLLIST_KEY));
        } else {
            this.mImageUrls = intent.getParcelableArrayListExtra(IMAGE_URLS_KEY);
        }
        if (this.mImageUrls == null) {
            onBackPressed();
            return;
        }
        int intExtra = intent.getIntExtra(IMAGE_CURRENT_INDEX_KEY, this.mCurrentItmeIndex);
        this.mCurrentItmeIndex = intExtra;
        if (intExtra > this.mImageUrls.size()) {
            this.mCurrentItmeIndex = 0;
        }
        if (intent.hasExtra("preview_callback")) {
            this.callbackId = intent.getStringExtra("preview_callback");
        }
        if (intent.hasExtra("original_image_urlArray")) {
            this.originalImageUrls = intent.getStringArrayListExtra("original_image_urlArray");
        }
        if (intent.hasExtra("screen_orientation")) {
            setRequestedOrientation(intent.getIntExtra("screen_orientation", 2));
        }
        boolean booleanExtra = intent.getBooleanExtra(IMAGE_LOOP_KEY, false);
        boolean booleanExtra2 = intent.getBooleanExtra(IMAGE_PHOTO_KEY, false);
        if (intent.hasExtra(IMAGE_INDICATOR_KEY)) {
            this.mIndicatorType = intent.getStringExtra(IMAGE_INDICATOR_KEY);
        }
        RelativeLayout relativeLayout = new RelativeLayout(this.that);
        relativeLayout.setBackgroundColor(intent.getIntExtra(IMAGE_BG_KEY, -16777216));
        relativeLayout.postDelayed(new Runnable() { // from class: io.dcloud.feature.nativeObj.photoview.PhotoActivity.1
            @Override // java.lang.Runnable
            public void run() {
                PhotoActivity.this.isBack = true;
            }
        }, 1000L);
        if (this.mImageUrls.size() > 0) {
            this.localImageUrls = new String[(booleanExtra && this.mImageUrls.size() == 2) ? this.mImageUrls.size() + 2 : this.mImageUrls.size()];
            BannerLayout bannerLayout = new BannerLayout(this.that, booleanExtra, booleanExtra2);
            this.photoLayout = bannerLayout;
            bannerLayout.setImageLoader(new BannerLayout.ImageLoader() { // from class: io.dcloud.feature.nativeObj.photoview.PhotoActivity.2
                @Override // io.dcloud.feature.nativeObj.BannerLayout.ImageLoader
                public void displayImage(Context context, final String str, final View view, final int i) {
                    Uri imageFileUri;
                    Object tag = view.getTag();
                    view.setTag(null);
                    if (str.startsWith(DeviceInfo.FILE_PROTOCOL)) {
                        str = str.replace(DeviceInfo.FILE_PROTOCOL, "");
                    }
                    if (!PdrUtil.isDeviceRootDir(str) && !PdrUtil.isNetPath(str) && !PdrUtil.isBase64ImagePath(str)) {
                        if (str.startsWith("assets://")) {
                            str = str.replace("assets://", SDK.ANDROID_ASSET);
                        } else if (str.startsWith("/")) {
                            str = "file:///android_asset" + str;
                        } else {
                            str = SDK.ANDROID_ASSET + str;
                        }
                    }
                    if (!FileUtil.checkPrivatePath(context, str) && (imageFileUri = FileUtil.getImageFileUri(context, str)) != null) {
                        str = imageFileUri.toString();
                    }
                    Glide.with((FragmentActivity) PhotoActivity.this).download(str).into((RequestBuilder<File>) new SimpleTarget<File>() { // from class: io.dcloud.feature.nativeObj.photoview.PhotoActivity.2.1
                        private ProgressBar bar;
                        private SubsamplingScaleImageView subImageview;

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadStarted(Drawable drawable) {
                            View view2 = view;
                            if (view2 instanceof RelativeLayout) {
                                try {
                                    this.subImageview = (SubsamplingScaleImageView) ((RelativeLayout) view2).getChildAt(0);
                                    this.bar = (ProgressBar) ((RelativeLayout) view).getChildAt(1);
                                } catch (Exception unused) {
                                }
                            }
                        }

                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                            onResourceReady((File) obj, (Transition<? super File>) transition);
                        }

                        public boolean typeOf(File file, String str2) {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                            return options.outMimeType.toLowerCase(Locale.ENGLISH).contains(str2);
                        }

                        public void onResourceReady(File file, Transition<? super File> transition) {
                            View view2 = view;
                            if (view2 instanceof RelativeLayout) {
                                try {
                                    if (typeOf(file, "gif")) {
                                        this.subImageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                        this.subImageview.setImageURI(Uri.fromFile(file));
                                    } else {
                                        this.subImageview.setImage(typeOf(file, "bmp") ? BitmapUtil.needRatationWithExif(file) ? ImageSource.bitmap(BitmapUtil.getRotatedBitmapFromFile(file)) : ImageSource.bitmap(BitmapFactory.decodeFile(file.getAbsolutePath())) : BitmapUtil.needRatationWithExif(file) ? ImageSource.bitmap(BitmapUtil.getRotatedBitmapFromFile(file)) : ImageSource.uri(file.getAbsolutePath()));
                                    }
                                    this.bar.setVisibility(8);
                                } catch (Exception unused) {
                                }
                            } else {
                                ((ImageView) view2).setImageURI(Uri.fromFile(file));
                            }
                            if (!PdrUtil.isNetPath(str) || PhotoActivity.this.localImageUrls == null || i >= PhotoActivity.this.localImageUrls.length) {
                                return;
                            }
                            PhotoActivity.this.localImageUrls[i] = file.getAbsolutePath();
                        }
                    });
                    view.setTag(tag);
                }
            });
            this.photoLayout.setIndicatorContainerData(null, 20, 10, 18, this.mIndicatorType);
            this.photoLayout.setScrollDuration(500);
            this.photoLayout.setViewUrls(this.mImageUrls, this.mCurrentItmeIndex);
            if (this.mImageUrls.size() == 2 && booleanExtra && this.photoLayout.getPager() != null) {
                this.photoLayout.getPager().setOffscreenPageLimit(2);
            }
            this.photoLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() { // from class: io.dcloud.feature.nativeObj.photoview.PhotoActivity.3
                @Override // io.dcloud.feature.nativeObj.BannerLayout.OnBannerItemClickListener
                public void onItemClick(int i) {
                    PhotoActivity photoActivity = PhotoActivity.this;
                    if (photoActivity.isBack) {
                        photoActivity.onBackPressed();
                    }
                }

                @Override // io.dcloud.feature.nativeObj.BannerLayout.OnBannerItemClickListener
                public void onItemLongClick(int i) throws JSONException {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("index", i);
                        if (PhotoActivity.this.originalImageUrls != null && PhotoActivity.this.originalImageUrls.get(i) != null) {
                            jSONObject.put("url", PhotoActivity.this.originalImageUrls.get(i));
                        }
                        if (PhotoActivity.this.localImageUrls[i] != null) {
                            jSONObject.put(AbsoluteConst.XML_PATH, PhotoActivity.this.localImageUrls[i]);
                        }
                    } catch (JSONException unused) {
                    }
                    LongClickEventManager.getInstance().fireEvent(jSONObject);
                }
            });
            relativeLayout.addView(this.photoLayout, new RelativeLayout.LayoutParams(-1, -1));
        }
        setContentView(relativeLayout, new ViewGroup.LayoutParams(-1, -1));
        fullScreen(this);
    }

    private void fullScreen(Activity activity) {
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(1792);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
    }
}
