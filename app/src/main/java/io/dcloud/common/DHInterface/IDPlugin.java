package io.dcloud.common.DHInterface;

import android.app.Activity;
import android.content.Context;

/* loaded from: classes3.dex */
public interface IDPlugin extends IFeature, IBoot {
    Activity getDPluginActivity();

    Context getDPluginContext();

    void initDPlugin(Context context, Activity activity);
}
