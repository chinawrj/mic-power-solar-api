package io.dcloud.uts;

import android.os.Handler;
import android.os.Looper;
import com.taobao.weex.bridge.WXBridgeManager;
import io.dcloud.common.DHInterface.IApp;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: UTSTimer.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a\u001a\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a\u001a \u0010\u001c\u001a\u00020\u001a2\u0010\u0010\u001d\u001a\f\u0012\u0004\u0012\u00020\u00180\u001ej\u0002`\u001f2\u0006\u0010 \u001a\u00020\u001a\u001a(\u0010\u001c\u001a\u00020\u001a2\u0010\u0010\u001d\u001a\f\u0012\u0004\u0012\u00020\u00180\u001ej\u0002`\u001f2\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001a\u001a\u0018\u0010\"\u001a\u00020\u001a2\u0010\u0010\u001d\u001a\f\u0012\u0004\u0012\u00020\u00180\u001ej\u0002`\u001f\u001a \u0010\"\u001a\u00020\u001a2\u0010\u0010\u001d\u001a\f\u0012\u0004\u0012\u00020\u00180\u001ej\u0002`\u001f2\u0006\u0010 \u001a\u00020\u001a\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"Z\u0010\u0004\u001aB\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b \u0007* \u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b\u0018\u00010\t0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\"\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"Z\u0010\u0014\u001aB\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b \u0007* \u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b\u0018\u00010\t0\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\r*\u0016\u0010#\"\b\u0012\u0004\u0012\u00020\u00180\u001e2\b\u0012\u0004\u0012\u00020\u00180\u001e¨\u0006$"}, d2 = {"executorService", "Ljava/util/concurrent/ScheduledExecutorService;", "getExecutorService", "()Ljava/util/concurrent/ScheduledExecutorService;", "intervalTaskMap", "", "", "kotlin.jvm.PlatformType", "Lio/dcloud/uts/TaskFuture;", "", "getIntervalTaskMap", "()Ljava/util/Map;", "setIntervalTaskMap", "(Ljava/util/Map;)V", "taskDynamicId", "Ljava/util/concurrent/atomic/AtomicInteger;", "getTaskDynamicId", "()Ljava/util/concurrent/atomic/AtomicInteger;", "setTaskDynamicId", "(Ljava/util/concurrent/atomic/AtomicInteger;)V", "timeoutTaskMap", "getTimeoutTaskMap", "setTimeoutTaskMap", "clearInterval", "", "taskId", "", "clearTimeout", "setInterval", WXBridgeManager.METHOD_CALLBACK, "Lkotlin/Function0;", "Lio/dcloud/uts/TimerCallback;", "timeout", IApp.ConfigProperty.CONFIG_DELAY, "setTimeout", "TimerCallback", "utsplugin_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSTimerKt {
    private static final ScheduledExecutorService executorService;
    private static AtomicInteger taskDynamicId = new AtomicInteger(0);
    private static java.util.Map<Integer, TaskFuture> intervalTaskMap = Collections.synchronizedMap(new LinkedHashMap());
    private static java.util.Map<Integer, TaskFuture> timeoutTaskMap = Collections.synchronizedMap(new LinkedHashMap());

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(10);
        Intrinsics.checkNotNullExpressionValue(scheduledExecutorServiceNewScheduledThreadPool, "newScheduledThreadPool(10)");
        executorService = scheduledExecutorServiceNewScheduledThreadPool;
    }

    public static final AtomicInteger getTaskDynamicId() {
        return taskDynamicId;
    }

    public static final void setTaskDynamicId(AtomicInteger atomicInteger) {
        Intrinsics.checkNotNullParameter(atomicInteger, "<set-?>");
        taskDynamicId = atomicInteger;
    }

    public static final java.util.Map<Integer, TaskFuture> getIntervalTaskMap() {
        return intervalTaskMap;
    }

    public static final void setIntervalTaskMap(java.util.Map<Integer, TaskFuture> map) {
        intervalTaskMap = map;
    }

    public static final java.util.Map<Integer, TaskFuture> getTimeoutTaskMap() {
        return timeoutTaskMap;
    }

    public static final void setTimeoutTaskMap(java.util.Map<Integer, TaskFuture> map) {
        timeoutTaskMap = map;
    }

    public static final ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public static final synchronized Number setTimeout(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return setTimeout(callback, (Number) 0);
    }

    public static final synchronized Number setTimeout(final Function0<Unit> callback, Number timeout) {
        final int iIncrementAndGet;
        TaskFuture taskFuture;
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        iIncrementAndGet = taskDynamicId.incrementAndGet();
        if (Looper.myLooper() == null) {
            ScheduledFuture<?> scheduledFuture = executorService.schedule(new Runnable() { // from class: io.dcloud.uts.UTSTimerKt$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    UTSTimerKt.setTimeout$lambda$0(callback, iIncrementAndGet);
                }
            }, timeout.longValue(), TimeUnit.MILLISECONDS);
            Intrinsics.checkNotNullExpressionValue(scheduledFuture, "scheduledFuture");
            taskFuture = new TaskFuture(scheduledFuture);
        } else {
            Looper looperMyLooper = Looper.myLooper();
            Intrinsics.checkNotNull(looperMyLooper);
            Handler handler = new Handler(looperMyLooper);
            UTSRunnable uTSRunnable = new UTSRunnable() { // from class: io.dcloud.uts.UTSTimerKt$setTimeout$runnable$1
                @Override // io.dcloud.uts.UTSRunnable
                public void doSth() {
                    callback.invoke();
                    UTSTimerKt.clearTimeout(Integer.valueOf(iIncrementAndGet));
                }
            };
            handler.postDelayed(uTSRunnable, timeout.longValue());
            taskFuture = new TaskFuture(handler, uTSRunnable);
        }
        java.util.Map<Integer, TaskFuture> timeoutTaskMap2 = timeoutTaskMap;
        Intrinsics.checkNotNullExpressionValue(timeoutTaskMap2, "timeoutTaskMap");
        timeoutTaskMap2.put(Integer.valueOf(iIncrementAndGet), taskFuture);
        return Integer.valueOf(iIncrementAndGet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setTimeout$lambda$0(Function0 callback, int i) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        callback.invoke();
        clearTimeout(Integer.valueOf(i));
    }

    public static final synchronized Number setInterval(Function0<Unit> callback, Number timeout) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        return setInterval(callback, timeout, timeout);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [T, io.dcloud.uts.UTSTimerKt$setInterval$runnableTask$1] */
    public static final synchronized Number setInterval(final Function0<Unit> callback, Number delay, Number timeout) {
        TaskFuture taskFuture;
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(delay, "delay");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        if (NumberKt.compareTo(delay, (Number) 0) <= 0) {
            delay = (Number) 1;
        }
        if (NumberKt.compareTo(timeout, (Number) 0) <= 0) {
            timeout = (Number) 1;
        }
        if (Looper.myLooper() == null) {
            ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(new Runnable() { // from class: io.dcloud.uts.UTSTimerKt$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    UTSTimerKt.setInterval$lambda$1(callback);
                }
            }, delay.longValue(), timeout.longValue(), TimeUnit.MILLISECONDS);
            Intrinsics.checkNotNullExpressionValue(scheduledFuture, "scheduledFuture");
            taskFuture = new TaskFuture(scheduledFuture);
        } else {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new Function0<Unit>() { // from class: io.dcloud.uts.UTSTimerKt$setInterval$runnableTask$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    callback.invoke();
                }
            };
            Looper looperMyLooper = Looper.myLooper();
            Intrinsics.checkNotNull(looperMyLooper);
            final Handler handler = new Handler(looperMyLooper);
            ScheduledFuture<?> scheduledFuture2 = executorService.scheduleAtFixedRate(new Runnable() { // from class: io.dcloud.uts.UTSTimerKt$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    UTSTimerKt.setInterval$lambda$3(handler, objectRef);
                }
            }, delay.longValue(), timeout.longValue(), TimeUnit.MILLISECONDS);
            Intrinsics.checkNotNullExpressionValue(scheduledFuture2, "scheduledFuture");
            taskFuture = new TaskFuture(scheduledFuture2);
        }
        taskDynamicId.getAndIncrement();
        java.util.Map<Integer, TaskFuture> intervalTaskMap2 = intervalTaskMap;
        Intrinsics.checkNotNullExpressionValue(intervalTaskMap2, "intervalTaskMap");
        intervalTaskMap2.put(Integer.valueOf(taskDynamicId.get()), taskFuture);
        return Integer.valueOf(taskDynamicId.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInterval$lambda$1(Function0 callback) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        callback.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInterval$lambda$3(Handler testHandler, Ref.ObjectRef runnableTask) {
        Intrinsics.checkNotNullParameter(testHandler, "$testHandler");
        Intrinsics.checkNotNullParameter(runnableTask, "$runnableTask");
        final Function0 function0 = (Function0) runnableTask.element;
        testHandler.post(new Runnable() { // from class: io.dcloud.uts.UTSTimerKt$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UTSTimerKt.setInterval$lambda$3$lambda$2(function0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInterval$lambda$3$lambda$2(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    public static final synchronized void clearTimeout(Number taskId) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        if (timeoutTaskMap.get(Integer.valueOf(taskId.intValue())) == null) {
            return;
        }
        TaskFuture taskFuture = timeoutTaskMap.get(Integer.valueOf(taskId.intValue()));
        Intrinsics.checkNotNull(taskFuture);
        taskFuture.cancel();
        timeoutTaskMap.remove(Integer.valueOf(taskId.intValue()));
    }

    public static final synchronized void clearInterval(Number taskId) {
        Intrinsics.checkNotNullParameter(taskId, "taskId");
        if (intervalTaskMap.get(Integer.valueOf(taskId.intValue())) == null) {
            return;
        }
        TaskFuture taskFuture = intervalTaskMap.get(Integer.valueOf(taskId.intValue()));
        Intrinsics.checkNotNull(taskFuture);
        taskFuture.cancel();
        intervalTaskMap.remove(Integer.valueOf(taskId.intValue()));
    }
}
