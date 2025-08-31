package io.dcloud.feature.weex.adapter.Fresco.imagepipeline;

import android.content.Context;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import dc.squareup.okhttp3.OkHttpClient;

/* loaded from: classes3.dex */
public class OkHttpImagePipelineConfigFactory {
    public static ImagePipelineConfig.Builder newBuilder(Context context, OkHttpClient okHttpClient) {
        return ImagePipelineConfig.newBuilder(context).setNetworkFetcher(new OkHttpNetworkFetcher(okHttpClient));
    }
}
