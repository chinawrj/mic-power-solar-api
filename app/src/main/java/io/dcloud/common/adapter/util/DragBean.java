package io.dcloud.common.adapter.util;

import android.view.View;
import io.dcloud.common.DHInterface.IFrameView;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class DragBean {
    public JSONObject dragCurrentViewOp = null;
    public JSONObject dragBindViewOp = null;
    public IFrameView dragBindWebView = null;
    public IFrameView dragCallBackWebView = null;
    public String dragCbId = null;
    public View nativeView = null;
}
