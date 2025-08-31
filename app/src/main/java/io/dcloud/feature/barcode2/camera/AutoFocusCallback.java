package io.dcloud.feature.barcode2.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

/* loaded from: classes3.dex */
final class AutoFocusCallback implements Camera.AutoFocusCallback {
    private static final long AUTOFOCUS_INTERVAL_MS = 200;
    private static final String TAG = "AutoFocusCallback";
    private Handler autoFocusHandler;
    private int autoFocusMessage;

    AutoFocusCallback() {
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean z, Camera camera) {
        Handler handler = this.autoFocusHandler;
        if (handler == null) {
            Log.d(TAG, "Got auto-focus callback, but no handler for it");
            return;
        }
        this.autoFocusHandler.sendMessageDelayed(handler.obtainMessage(this.autoFocusMessage, Boolean.valueOf(z)), AUTOFOCUS_INTERVAL_MS);
        this.autoFocusHandler = null;
    }

    void setHandler(Handler handler, int i) {
        this.autoFocusHandler = handler;
        this.autoFocusMessage = i;
    }
}
