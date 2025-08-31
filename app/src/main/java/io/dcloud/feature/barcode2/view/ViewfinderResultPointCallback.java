package io.dcloud.feature.barcode2.view;

import com.dcloud.zxing2.ResultPoint;
import com.dcloud.zxing2.ResultPointCallback;

/* loaded from: classes3.dex */
public final class ViewfinderResultPointCallback implements ResultPointCallback {
    private final ViewfinderView viewfinderView;

    public ViewfinderResultPointCallback(ViewfinderView viewfinderView) {
        this.viewfinderView = viewfinderView;
    }

    @Override // com.dcloud.zxing2.ResultPointCallback
    public void foundPossibleResultPoint(ResultPoint resultPoint) {
        this.viewfinderView.addPossibleResultPoint(resultPoint);
    }
}
