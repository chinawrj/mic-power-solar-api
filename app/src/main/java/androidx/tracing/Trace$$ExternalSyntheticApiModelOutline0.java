package androidx.tracing;

import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import android.view.inputmethod.InputContentInfo;
import android.webkit.SafeBrowsingResponse;
import android.webkit.ServiceWorkerWebSettings;
import android.webkit.TracingConfig;
import android.webkit.WebViewRenderProcess;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class Trace$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ AccessibilityNodeInfo.TouchDelegateInfo m(Map map) {
        return new AccessibilityNodeInfo.TouchDelegateInfo(map);
    }

    public static /* bridge */ /* synthetic */ AutofillId m(Object obj) {
        return (AutofillId) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ContentCaptureSession m153m(Object obj) {
        return (ContentCaptureSession) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ InputContentInfo m154m(Object obj) {
        return (InputContentInfo) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SafeBrowsingResponse m155m(Object obj) {
        return (SafeBrowsingResponse) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ServiceWorkerWebSettings m157m(Object obj) {
        return (ServiceWorkerWebSettings) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ TracingConfig.Builder m158m() {
        return new TracingConfig.Builder();
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ WebViewRenderProcess m163m(Object obj) {
        return (WebViewRenderProcess) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m166m() {
        return LocalTime.class;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ChronoZonedDateTime m173m(Object obj) {
        return (ChronoZonedDateTime) obj;
    }

    /* renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m178m(Object obj) {
        return obj instanceof ChronoZonedDateTime;
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return ZonedDateTime.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof LocalDateTime;
    }
}
