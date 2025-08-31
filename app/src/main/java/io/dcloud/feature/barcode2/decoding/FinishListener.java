package io.dcloud.feature.barcode2.decoding;

import android.app.Activity;
import android.content.DialogInterface;

/* loaded from: classes3.dex */
public final class FinishListener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {
    private final Activity activityToFinish;

    public FinishListener(Activity activity) {
        this.activityToFinish = activity;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        run();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        run();
    }

    @Override // java.lang.Runnable
    public void run() {
    }
}
