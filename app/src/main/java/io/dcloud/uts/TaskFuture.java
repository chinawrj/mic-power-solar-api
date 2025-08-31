package io.dcloud.uts;

import android.os.Handler;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import java.util.concurrent.ScheduledFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSTimer.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\r\u001a\u00020\u000eR\u0010\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lio/dcloud/uts/TaskFuture;", "", "scheduledFuture", "Ljava/util/concurrent/ScheduledFuture;", "(Ljava/util/concurrent/ScheduledFuture;)V", "handler", "Landroid/os/Handler;", "taskRun", "Lio/dcloud/uts/UTSRunnable;", "(Landroid/os/Handler;Lio/dcloud/uts/UTSRunnable;)V", "hostHandler", "hostRun", "scheduler", BindingXConstants.STATE_CANCEL, "", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TaskFuture {
    private Handler hostHandler;
    private UTSRunnable hostRun;
    private ScheduledFuture<?> scheduler;

    public TaskFuture(ScheduledFuture<?> scheduledFuture) {
        Intrinsics.checkNotNullParameter(scheduledFuture, "scheduledFuture");
        this.scheduler = scheduledFuture;
    }

    public TaskFuture(Handler handler, UTSRunnable taskRun) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(taskRun, "taskRun");
        this.hostHandler = handler;
        this.hostRun = taskRun;
    }

    public final void cancel() {
        Handler handler = this.hostHandler;
        if (handler != null && this.hostRun != null) {
            Intrinsics.checkNotNull(handler);
            UTSRunnable uTSRunnable = this.hostRun;
            Intrinsics.checkNotNull(uTSRunnable);
            handler.removeCallbacks(uTSRunnable);
            UTSRunnable uTSRunnable2 = this.hostRun;
            Intrinsics.checkNotNull(uTSRunnable2);
            uTSRunnable2.setStop(true);
        }
        ScheduledFuture<?> scheduledFuture = this.scheduler;
        if (scheduledFuture != null) {
            Intrinsics.checkNotNull(scheduledFuture);
            scheduledFuture.cancel(true);
        }
    }
}
