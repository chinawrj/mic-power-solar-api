package io.dcloud.uts;

import com.taobao.weex.common.Constants;
import kotlin.Metadata;

/* compiled from: UTSTimer.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lio/dcloud/uts/UTSRunnable;", "Ljava/lang/Runnable;", "()V", Constants.Value.STOP, "", "getStop", "()Z", "setStop", "(Z)V", "doSth", "", "run", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class UTSRunnable implements Runnable {
    private boolean stop;

    public abstract void doSth();

    public boolean getStop() {
        return this.stop;
    }

    public void setStop(boolean z) {
        this.stop = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (getStop()) {
            return;
        }
        doSth();
    }
}
