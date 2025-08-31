package com.taobao.weex.utils;

import android.util.Log;

/* loaded from: classes3.dex */
public class Trace {
    private static final String TAG = "Weex_Trace";
    private static final boolean sEnabled = false;
    private static final AbstractTrace sTrace = new TraceDummy();

    private static abstract class AbstractTrace {
        private AbstractTrace() {
        }

        abstract void beginSection(String str);

        abstract void endSection();
    }

    private static final class TraceDummy extends AbstractTrace {
        private TraceDummy() {
            super();
        }

        @Override // com.taobao.weex.utils.Trace.AbstractTrace
        void beginSection(String str) {
        }

        @Override // com.taobao.weex.utils.Trace.AbstractTrace
        void endSection() {
        }
    }

    private static final class TraceJBMR2 extends AbstractTrace {
        private TraceJBMR2() {
            super();
        }

        @Override // com.taobao.weex.utils.Trace.AbstractTrace
        void beginSection(String str) {
            android.os.Trace.beginSection(str);
        }

        @Override // com.taobao.weex.utils.Trace.AbstractTrace
        void endSection() {
            android.os.Trace.endSection();
        }
    }

    public static void beginSection(String str) {
        Log.i(TAG, "beginSection() " + str);
        sTrace.beginSection(str);
    }

    public static void endSection() {
        sTrace.endSection();
        Log.i(TAG, "endSection()");
    }

    public static final boolean getTraceEnabled() {
        return sEnabled;
    }
}
