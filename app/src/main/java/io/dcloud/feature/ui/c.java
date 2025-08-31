package io.dcloud.feature.ui;

import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IContainerView;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.INativeBitmap;
import io.dcloud.common.DHInterface.INativeView;
import io.dcloud.common.DHInterface.ITitleNView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.ReceiveJSValue;
import io.dcloud.common.adapter.ui.webview.WebResUtil;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class c extends io.dcloud.feature.ui.b implements IEventCallback {
    private static final HashMap d0;
    int A;
    Object B;
    boolean C;
    boolean D;
    int E;
    boolean F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    boolean L;
    protected ArrayList M;
    String N;
    String O;
    IWebview P;
    String Q;
    IWebview R;
    String S;
    IWebview T;
    String U;
    private boolean V;
    c W;
    private ArrayList X;
    private boolean Y;
    private String Z;
    private int a0;
    private boolean b0;
    Runnable c0;
    long u;
    JSONArray v;
    IWebview w;
    JSONObject x;
    IFrameView y;
    String z;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.y.obtainWebView().checkWhite(c.this.Z)) {
                c.this.l();
            } else {
                c.this.a(AbsoluteConst.EVENTS_WEBVIEW_RENDERED, "{}", false);
            }
            c.this.c0 = null;
        }
    }

    class b implements ReceiveJSValue.ReceiveJSValueCallback {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        b(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.adapter.ui.ReceiveJSValue.ReceiveJSValueCallback
        public String callback(JSONArray jSONArray) throws JSONException {
            Object obj;
            String string = JSONUtil.getString(jSONArray, 0);
            try {
                obj = jSONArray.get(1);
            } catch (JSONException unused) {
                obj = null;
            }
            if ((obj instanceof String) || "string".equals(string)) {
                Deprecated_JSUtil.execCallback(this.a, this.b, String.valueOf(obj), JSUtil.OK, false, false);
            } else if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || "object".equals(string) || !Constants.Name.UNDEFINED.equals(string)) {
                Deprecated_JSUtil.execCallback(this.a, this.b, obj.toString(), JSUtil.OK, true, false);
            } else {
                Deprecated_JSUtil.execCallback(this.a, this.b, Constants.Name.UNDEFINED, JSUtil.OK, true, false);
            }
            return null;
        }
    }

    /* renamed from: io.dcloud.feature.ui.c$c, reason: collision with other inner class name */
    class RunnableC0046c implements Runnable {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        RunnableC0046c(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.evalJSSync(this.b, null);
        }
    }

    class d implements MessageHandler.UncheckedCallable {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        class a implements ICallBack {
            final /* synthetic */ MessageHandler.WaitableRunnable a;

            a(MessageHandler.WaitableRunnable waitableRunnable) {
                this.a = waitableRunnable;
            }

            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                MessageHandler.WaitableRunnable waitableRunnable = this.a;
                if (waitableRunnable == null) {
                    return null;
                }
                waitableRunnable.callBack(obj);
                return null;
            }
        }

        d(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.UncheckedCallable
        public void run(MessageHandler.WaitableRunnable waitableRunnable) {
            try {
                this.a.evalJSSync(this.b, new a(waitableRunnable));
            } catch (Exception e) {
                e.printStackTrace();
                if (waitableRunnable != null) {
                    waitableRunnable.callBack("");
                }
            }
        }
    }

    class e implements Runnable {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;
        final /* synthetic */ IWebview c;
        final /* synthetic */ String d;

        e(IWebview iWebview, String str, IWebview iWebview2, String str2) {
            this.a = iWebview;
            this.b = str;
            this.c = iWebview2;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                boolean zCheckWhite = this.a.checkWhite(this.b);
                IWebview iWebview = this.c;
                String str = this.d;
                StringBuilder sb = new StringBuilder("{\"code\":100,\"rendered\":");
                sb.append(!zCheckWhite);
                sb.append(Operators.BLOCK_END_STR);
                Deprecated_JSUtil.execCallback(iWebview, str, sb.toString(), JSUtil.OK, true, false);
            } catch (Exception unused) {
                if (c.this.a() != null) {
                    Deprecated_JSUtil.execCallback(this.c, this.d, "{\"code\":-100,\"message\":\"" + c.this.a().getString(R.string.dcloud_common_screenshot_fail) + "\"}", JSUtil.ERROR, true, false);
                }
            }
        }
    }

    class f implements ICallBack {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        f(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            Deprecated_JSUtil.execCallback(this.a, this.b, null, JSUtil.OK, false, false);
            return null;
        }
    }

    class g implements ICallBack {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        g(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            Deprecated_JSUtil.execCallback(this.a, this.b, "{\"code\":-100,\"message\":\"" + this.a.getContext().getString(R.string.dcloud_common_screenshot_fail) + "\"}", JSUtil.ERROR, true, false);
            return null;
        }
    }

    class h implements ICallBack {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        h(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            Deprecated_JSUtil.execCallback(this.a, this.b, null, JSUtil.OK, false, false);
            return null;
        }
    }

    class i implements ICallBack {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        i(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            IWebview iWebview = this.a;
            String str = this.b;
            StringBuilder sb = new StringBuilder("{\"code\":");
            sb.append(i);
            sb.append(",\"message\":\"");
            sb.append(obj != null ? obj.toString() : c.this.a().getString(R.string.dcloud_common_screenshot_fail));
            sb.append("\"}");
            Deprecated_JSUtil.execCallback(iWebview, str, sb.toString(), JSUtil.ERROR, true, false);
            return null;
        }
    }

    class j implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        j(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            TestUtil.PointTime.commitTid(c.this.a(), this.a, c.this.g, this.b, 10);
        }
    }

    static {
        HashMap map = new HashMap();
        d0 = map;
        map.put(AbsoluteConst.EVENTS_CLOSE, "onclose");
        map.put("loading", "onloading");
        map.put(AbsoluteConst.EVENTS_FAILED, "onerror");
        map.put(AbsoluteConst.EVENTS_LOADED, "onloaded");
    }

    c(io.dcloud.feature.ui.a aVar, String str, String str2, String str3, JSONObject jSONObject) {
        this(aVar, null, str, str2, str3, jSONObject);
    }

    private INativeBitmap g(IWebview iWebview, String str) {
        return (INativeBitmap) iWebview.obtainApp().obtainMgrData(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "getNativeBitmap", new String[]{iWebview.obtainApp().obtainAppId(), str}});
    }

    private void i() {
        if (this.y.getFrameType() == 6) {
            ThreadPool.self().addThreadTask(new j(this.c.f.obtainAppId(), this.c.f.obtainConfigProperty("adid")));
        }
    }

    private void j() {
        View viewObtainMainView = this.y.obtainMainView();
        if (viewObtainMainView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) viewObtainMainView;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt instanceof ITitleNView) {
                    childAt.bringToFront();
                    return;
                }
            }
        }
    }

    private boolean k() {
        c cVar = this.a;
        if (cVar != null) {
            return cVar.F && cVar.k();
        }
        return true;
    }

    private void s() throws NumberFormatException {
        this.c.b(this);
        if (!this.I) {
            d().onDispose();
            d().dispose();
        } else if (!this.J) {
            if (this.L) {
                c cVar = this.a;
                if (cVar != null) {
                    cVar.c(this);
                }
                d().onDispose();
                d().dispose();
            } else {
                ((AdaFrameItem) this.y).getAnimOptions().mOption = (byte) 1;
            }
        }
        e();
    }

    private void t() {
        ((AdaFrameItem) this.y).getAnimOptions().mOption = (byte) 3;
        this.F = false;
        this.G = true;
    }

    @Override // io.dcloud.feature.ui.b
    public void a(int i2, int i3, int i4, int i5, int i6, int i7) {
    }

    public void b(boolean z) {
        this.Y = z;
    }

    protected io.dcloud.feature.ui.b c(String str) {
        ArrayList arrayList = this.M;
        io.dcloud.feature.ui.b bVar = null;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int size = this.M.size() - 1; size >= 0; size--) {
                bVar = (io.dcloud.feature.ui.b) this.M.get(size);
                if (PdrUtil.isEquals(str, bVar.f)) {
                    break;
                }
            }
        }
        return bVar;
    }

    protected void d(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "maps", "appendToFrameView", new Object[]{this.y, str}});
    }

    protected void e(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "addNativeView", new Object[]{this.y, str}});
    }

    protected void f(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "videoplayer", "appendToFrameView", new Object[]{this.y, str}});
    }

    protected void h(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "removeNativeView", new Object[]{this.y, str}});
    }

    public void l() {
        HashMap map = this.m;
        if (map == null || !map.containsKey(AbsoluteConst.EVENTS_WEBVIEW_RENDERED)) {
            return;
        }
        Runnable runnable = this.c0;
        if (runnable != null) {
            MessageHandler.removeCallbacks(runnable);
        }
        a aVar = new a();
        this.c0 = aVar;
        MessageHandler.postDelayed(aVar, this.a0);
    }

    public String m() {
        IWebview iWebviewObtainWebView = this.y.obtainWebView();
        if (iWebviewObtainWebView != null) {
            return iWebviewObtainWebView.obtainFrameId();
        }
        return null;
    }

    protected String n() {
        ViewOptions viewOptionsObtainFrameOptions = ((AdaFrameItem) this.y).obtainFrameOptions();
        return StringUtil.format("{top:%d,left:%d,width:%d,height:%d}", Integer.valueOf((int) (viewOptionsObtainFrameOptions.top / viewOptionsObtainFrameOptions.mWebviewScale)), Integer.valueOf((int) (viewOptionsObtainFrameOptions.left / viewOptionsObtainFrameOptions.mWebviewScale)), Integer.valueOf((int) (viewOptionsObtainFrameOptions.width / viewOptionsObtainFrameOptions.mWebviewScale)), Integer.valueOf((int) (viewOptionsObtainFrameOptions.height / viewOptionsObtainFrameOptions.mWebviewScale)));
    }

    public boolean o() {
        return !this.y.isWebviewCovered();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0146  */
    @Override // io.dcloud.common.DHInterface.IEventCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object onCallBack(java.lang.String r14, java.lang.Object r15) throws org.json.JSONException, java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 1026
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.onCallBack(java.lang.String, java.lang.Object):java.lang.Object");
    }

    public boolean p() {
        return this.b0;
    }

    protected void q() {
    }

    public IWebview r() {
        return this.y.obtainWebView();
    }

    private c(io.dcloud.feature.ui.a aVar, IFrameView iFrameView, String str, String str2, String str3, JSONObject jSONObject) {
        super("NWindow");
        this.u = System.currentTimeMillis();
        this.v = null;
        this.w = null;
        this.x = null;
        this.A = -1;
        this.B = null;
        this.C = false;
        this.D = false;
        this.E = 0;
        this.F = false;
        this.G = false;
        this.H = true;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = true;
        this.W = null;
        this.X = null;
        this.Y = false;
        this.Z = "auto";
        this.a0 = 150;
        this.b0 = false;
        this.c0 = null;
        this.c = aVar;
        this.z = str;
        this.e = str3;
        this.h = jSONObject;
        a(iFrameView, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e(io.dcloud.common.DHInterface.IWebview r20, org.json.JSONArray r21, io.dcloud.feature.ui.c r22) throws org.json.JSONException, java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 845
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.e(io.dcloud.common.DHInterface.IWebview, org.json.JSONArray, io.dcloud.feature.ui.c):void");
    }

    public boolean b(io.dcloud.feature.ui.b bVar) {
        ArrayList arrayList = this.M;
        if (arrayList == null) {
            return false;
        }
        return arrayList.contains(bVar);
    }

    @Override // io.dcloud.feature.ui.b
    public AdaFrameItem d() {
        return (AdaFrameItem) this.y;
    }

    @Override // io.dcloud.feature.ui.b
    public String h() {
        if (PdrUtil.isEmpty(r().obtainFrameId())) {
            String str = this.e;
            String str2 = this.d;
            JSONObject jSONObject = this.x;
            return StringUtil.format("(function(){return {'uuid':'%s','id':%s,'identity':'%s','extras':%s}})()", str, Constants.Name.UNDEFINED, str2, jSONObject != null ? jSONObject.toString() : "{}");
        }
        String str3 = this.e;
        String strObtainFrameId = r().obtainFrameId();
        String str4 = this.d;
        JSONObject jSONObject2 = this.x;
        return StringUtil.format("(function(){return {'uuid':'%s','id':'%s','identity':'%s','extras':%s}})()", str3, strObtainFrameId, str4, jSONObject2 != null ? jSONObject2.toString() : "{}");
    }

    private void d(IWebview iWebview, JSONArray jSONArray, c cVar) throws JSONException, NumberFormatException {
        String string = JSONUtil.getString(jSONArray, 0);
        String string2 = JSONUtil.getString(jSONArray, 1);
        AnimOptions animOptions = ((AdaFrameItem) cVar.y).getAnimOptions();
        if (!PdrUtil.isEmpty(string2)) {
            animOptions.duration_close = PdrUtil.parseInt(string2, animOptions.duration_close);
        } else {
            animOptions.duration_close = animOptions.duration_show;
        }
        animOptions.setCloseAnimType(string);
        animOptions.mOption = (byte) 3;
        Logger.d(Logger.VIEW_VISIBLE_TAG, "NWindow.hide view=" + cVar.d());
        if (cVar.F) {
            if (cVar.k()) {
                a(iWebview, JSONUtil.getJSONObject(jSONArray, 2), cVar, string);
                this.c.d.processEvent(IMgr.MgrType.WindowMgr, 23, cVar.y);
            } else {
                onCallBack("hide", null);
                cVar.y.setVisible(false, true);
            }
            cVar.F = false;
        } else {
            cVar.y.setVisible(false, true);
        }
        cVar.G = true;
    }

    public void a(boolean z) {
        this.b0 = z;
    }

    public void a(IFrameView iFrameView, String str) {
        if (iFrameView != null) {
            this.y = iFrameView;
            IWebview iWebviewObtainWebView = iFrameView.obtainWebView();
            if (iWebviewObtainWebView != null) {
                iWebviewObtainWebView.initWebviewUUID(this.e);
                iWebviewObtainWebView.setFrameId(str);
            }
        }
    }

    protected void b(c cVar) {
        if (this.X == null) {
            this.X = new ArrayList();
        }
        this.X.add(cVar);
        cVar.W = this;
        if (cVar.r() != null) {
            cVar.r().setOpener(r());
        }
    }

    protected void c(io.dcloud.feature.ui.b bVar) throws NumberFormatException {
        ArrayList arrayList = this.M;
        if (arrayList == null || !arrayList.contains(bVar)) {
            return;
        }
        this.M.remove(bVar);
        bVar.a = null;
        byte bC = bVar.c();
        boolean z = bVar instanceof c;
        if (bC == io.dcloud.feature.ui.b.n) {
            this.y.obtainWebView().removeFrameItem(bVar.d());
            return;
        }
        if (bC == io.dcloud.feature.ui.b.o) {
            this.y.obtainWebviewParent().removeFrameItem(bVar.d());
        } else if (bC == io.dcloud.feature.ui.b.p) {
            this.y.removeFrameItem(bVar.d());
            if (z) {
                this.y.obtainWebviewParent().obtainFrameOptions().delRelViewRect(bVar.d().obtainFrameOptions());
            }
            d().resize();
        }
    }

    protected void a(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "ad", "addNativeView", new Object[]{this.y, str}});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01e7  */
    /* JADX WARN: Type inference failed for: r2v2, types: [io.dcloud.common.DHInterface.IWebview] */
    /* JADX WARN: Type inference failed for: r2v31, types: [io.dcloud.common.DHInterface.IFrameView] */
    /* JADX WARN: Type inference failed for: r2v4, types: [io.dcloud.common.DHInterface.IFrameView] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void a(io.dcloud.feature.ui.b r24) throws org.json.JSONException, java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 710
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.a(io.dcloud.feature.ui.b):void");
    }

    protected void b(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "barcode", "appendToFrameView", new Object[]{this.y, str}});
    }

    void b(IWebview iWebview, JSONArray jSONArray, c cVar) throws JSONException, NumberFormatException {
        if (cVar.I) {
            if (!cVar.J) {
                this.c.b(cVar);
                if (cVar.L) {
                    c cVar2 = cVar.a;
                    if (cVar2 != null) {
                        cVar2.c(cVar);
                    }
                    cVar.d().onDispose();
                    cVar.d().dispose();
                } else {
                    String string = JSONUtil.getString(jSONArray, 0);
                    String string2 = JSONUtil.getString(jSONArray, 1);
                    AnimOptions animOptions = ((AdaFrameItem) cVar.y).getAnimOptions();
                    if (PdrUtil.isEmpty(string)) {
                        string = "auto";
                    }
                    if (!PdrUtil.isEmpty(string2)) {
                        animOptions.duration_close = PdrUtil.parseInt(string2, animOptions.duration_close);
                    } else if (string.equals(AnimOptions.ANIM_POP_OUT)) {
                        animOptions.duration_close = 360;
                    } else {
                        animOptions.duration_close = animOptions.duration_show;
                    }
                    animOptions.setCloseAnimType(string);
                    animOptions.mOption = (byte) 1;
                    a(iWebview, JSONUtil.getJSONObject(jSONArray, 2), cVar, string);
                    this.c.d.processEvent(IMgr.MgrType.WindowMgr, 2, cVar.y);
                }
            }
        } else {
            this.c.b(cVar);
            cVar.d().onDispose();
            cVar.d().dispose();
        }
        cVar.e();
    }

    protected void c(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "livepusher", "appendToFrameView", new Object[]{this.y, str}});
    }

    private void c(IWebview iWebview, JSONArray jSONArray, c cVar) throws JSONException {
        boolean z;
        boolean z2;
        boolean z3;
        String str;
        Rect rect;
        String string = JSONUtil.getString(jSONArray, 0);
        String string2 = JSONUtil.getString(jSONArray, 1);
        View viewObtainMainView = this.c.a(string2, string2, (String) null).d().obtainMainView();
        String string3 = JSONUtil.getString(jSONArray, 2);
        JSONObject jSONObject = JSONUtil.getJSONObject(jSONArray, 3);
        if (jSONObject != null) {
            boolean zOptBoolean = jSONObject.optBoolean("check", false);
            boolean zOptBoolean2 = jSONObject.optBoolean("checkKeyboard", false);
            boolean zOptBoolean3 = jSONObject.optBoolean("wholeContent");
            String strOptString = jSONObject.optString("bit", "RGB565");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("clip");
            if (jSONObjectOptJSONObject != null) {
                int width = viewObtainMainView.getWidth();
                int height = viewObtainMainView.getHeight();
                float scale = iWebview.getScale();
                Rect rect2 = new Rect(PdrUtil.convertToScreenInt(jSONObjectOptJSONObject.optString("left"), width, 0, scale), PdrUtil.convertToScreenInt(jSONObjectOptJSONObject.optString("top"), height, 0, scale), PdrUtil.convertToScreenInt(jSONObjectOptJSONObject.optString("width"), width, width, scale), PdrUtil.convertToScreenInt(jSONObjectOptJSONObject.optString("height"), height, height, scale));
                str = strOptString;
                z3 = zOptBoolean3;
                rect = rect2;
                z = zOptBoolean;
                z2 = zOptBoolean2;
            } else {
                str = strOptString;
                z2 = zOptBoolean2;
                z3 = zOptBoolean3;
                rect = null;
                z = zOptBoolean;
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
            str = "RGB565";
            rect = null;
        }
        iWebview.obtainFrameView().draw(viewObtainMainView, g(iWebview, string), z, z2, z3, rect, str, TextUtils.isEmpty(string3) ? null : new h(iWebview, string3), TextUtils.isEmpty(string3) ? null : new i(iWebview, string3));
    }

    boolean b(String str, String str2, boolean z) {
        ArrayList arrayList = this.M;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                io.dcloud.feature.ui.b bVar = (io.dcloud.feature.ui.b) this.M.get(size);
                if (bVar instanceof c) {
                    c cVar = (c) bVar;
                    if (cVar.F && cVar.b(str, str2, z)) {
                        return true;
                    }
                }
            }
        }
        return a(str) && a(str, str2, z);
    }

    boolean c(String str, String str2, boolean z) throws JSONException, NumberFormatException {
        ArrayList arrayList = this.M;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                io.dcloud.feature.ui.b bVar = (io.dcloud.feature.ui.b) this.M.get(size);
                if (bVar instanceof c) {
                    c cVar = (c) bVar;
                    if (cVar.F && cVar.c(str, str2, z)) {
                        return true;
                    }
                }
            }
        }
        if (a(str)) {
            return a(str, str2, z);
        }
        IFrameView iFrameView = this.y;
        if (iFrameView instanceof AdaFrameView) {
            String str3 = ((AdaFrameView) iFrameView).obtainFrameOptions().historyBack;
            if ((str3.equals("backButton") || str3.equals("all")) && this.y.obtainWebView() != null && this.y.obtainWebView().canGoBack()) {
                this.y.obtainWebView().goBackOrForward(-1);
                return true;
            }
        }
        if ("hide".equals(d().obtainFrameOptions().backButtonAutoControl)) {
            d(this.y.obtainWebView(), JSONUtil.createJSONArray("['auto',null]"), this);
            return true;
        }
        if ("quit".equals(d().obtainFrameOptions().backButtonAutoControl)) {
            this.c.d.processEvent(IMgr.MgrType.WindowMgr, 20, this.y.obtainApp());
            return false;
        }
        if (!AbsoluteConst.EVENTS_CLOSE.equals(d().obtainFrameOptions().backButtonAutoControl)) {
            return false;
        }
        b(this.y.obtainWebView(), JSONUtil.createJSONArray("['auto',null]"), this);
        return true;
    }

    boolean b(String str) {
        ArrayList arrayList = this.M;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                io.dcloud.feature.ui.b bVar = (io.dcloud.feature.ui.b) this.M.get(size);
                if ((bVar instanceof c) && ((c) bVar).a(str)) {
                    return true;
                }
            }
        }
        return a(str);
    }

    private void a(io.dcloud.feature.ui.b bVar, c cVar) {
        if (!BaseInfo.isBase(bVar.a()) || this.z.startsWith(DeviceInfo.HTTP_PROTOCOL) || this.z.startsWith(DeviceInfo.HTTPS_PROTOCOL) || cVar.z.startsWith(DeviceInfo.HTTP_PROTOCOL) || cVar.z.startsWith(DeviceInfo.HTTPS_PROTOCOL) || TextUtils.isEmpty(this.z) || TextUtils.isEmpty(cVar.z)) {
            return;
        }
        Log.i(AbsoluteConst.HBUILDER_TAG, StringUtil.format(AbsoluteConst.FILIATIONLOG, io.dcloud.feature.ui.e.c(WebResUtil.getHBuilderPrintUrl(cVar.r().obtainApp().convert2RelPath(r().obtainUrl()))), io.dcloud.feature.ui.e.c(WebResUtil.getHBuilderPrintUrl(cVar.r().obtainUrl()))));
    }

    private static void a(IContainerView iContainerView, AdaFrameItem adaFrameItem, ViewGroup.LayoutParams layoutParams, int i2, int i3, int i4, int i5) throws JSONException {
        ViewOptions viewOptionsObtainFrameOptions = adaFrameItem.obtainFrameOptions();
        viewOptionsObtainFrameOptions.left = i2;
        viewOptionsObtainFrameOptions.top = i3;
        viewOptionsObtainFrameOptions.width = i4;
        viewOptionsObtainFrameOptions.height = i5;
        viewOptionsObtainFrameOptions.commitUpdate2JSONObject();
        AdaFrameView adaFrameView = (AdaFrameView) adaFrameItem;
        adaFrameView.isChildOfFrameView = true;
        View viewObtainMainView = adaFrameItem.obtainMainView();
        if (adaFrameView.obtainWebView().isUniWebView()) {
            viewObtainMainView.layout(0, 0, i4, i5);
        } else {
            viewObtainMainView.setTop(0);
            viewObtainMainView.setLeft(0);
        }
        ViewHelper.setX(viewObtainMainView, 0.0f);
        ViewHelper.setY(viewObtainMainView, 0.0f);
        iContainerView.addFrameItem(adaFrameItem, AdaFrameItem.LayoutParamsUtil.createLayoutParams(i2, i3, i4, i5));
        Logger.d(Logger.VIEW_VISIBLE_TAG, "appendNWindow Y=" + ViewHelper.getY(viewObtainMainView));
    }

    @Override // io.dcloud.feature.ui.b
    protected void e() {
        ArrayList arrayList;
        i();
        c cVar = this.W;
        if (cVar != null && (arrayList = cVar.X) != null) {
            arrayList.remove(this);
        }
        this.W = null;
        this.a = null;
        ArrayList arrayList2 = this.M;
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                ((io.dcloud.feature.ui.b) it.next()).e();
            }
            this.M.clear();
            this.M = null;
        }
        this.P = null;
        this.O = null;
        this.Q = null;
        this.R = null;
        this.a0 = 150;
        this.i = null;
        HashMap map = this.b;
        if (map != null) {
            map.clear();
        }
    }

    public static synchronized void a(String str, Object obj, List list, c cVar) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            JSUtil.broadcastWebviewEvent(((c) it.next()).r(), cVar.e, str, JSONUtil.toJSONableString(String.valueOf(obj)));
        }
        if (!list.contains(cVar)) {
            JSUtil.broadcastWebviewEvent(cVar.r(), cVar.e, str, JSONUtil.toJSONableString(String.valueOf(obj)));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:264:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x062b  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0748  */
    @Override // io.dcloud.feature.ui.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(io.dcloud.common.DHInterface.IWebview r27, java.lang.String r28, org.json.JSONArray r29) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 4028
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.a(io.dcloud.common.DHInterface.IWebview, java.lang.String, org.json.JSONArray):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x002b, code lost:
    
        if (r1.y.obtainMainView().getVisibility() == 0) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.DHInterface.IWebview r10, org.json.JSONArray r11) throws org.json.JSONException {
        /*
            r9 = this;
            java.lang.String r0 = "{\"code\":-100,\"message\":\""
            r1 = 0
            java.lang.String r1 = io.dcloud.common.util.JSONUtil.getString(r11, r1)
            r2 = 1
            java.lang.String r2 = io.dcloud.common.util.JSONUtil.getString(r11, r2)
            io.dcloud.feature.ui.a r3 = r9.c
            r4 = 0
            io.dcloud.feature.ui.c r1 = r3.a(r1, r1, r4)
            if (r1 == 0) goto L2d
            io.dcloud.common.DHInterface.IFrameView r3 = r1.y     // Catch: java.lang.Exception -> L52
            android.view.View r3 = r3.obtainMainView()     // Catch: java.lang.Exception -> L52
            android.view.ViewParent r3 = r3.getParent()     // Catch: java.lang.Exception -> L52
            if (r3 == 0) goto L2d
            io.dcloud.common.DHInterface.IFrameView r3 = r1.y     // Catch: java.lang.Exception -> L52
            android.view.View r3 = r3.obtainMainView()     // Catch: java.lang.Exception -> L52
            int r3 = r3.getVisibility()     // Catch: java.lang.Exception -> L52
            if (r3 == 0) goto L61
        L2d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L52
            r3.<init>(r0)     // Catch: java.lang.Exception -> L52
            android.content.Context r0 = r10.getContext()     // Catch: java.lang.Exception -> L52
            int r4 = io.dcloud.base.R.string.dcloud_ui_webview_not_finished     // Catch: java.lang.Exception -> L52
            java.lang.String r0 = r0.getString(r4)     // Catch: java.lang.Exception -> L52
            r3.append(r0)     // Catch: java.lang.Exception -> L52
            java.lang.String r0 = "\"}"
            r3.append(r0)     // Catch: java.lang.Exception -> L52
            java.lang.String r5 = r3.toString()     // Catch: java.lang.Exception -> L52
            int r6 = io.dcloud.common.util.JSUtil.ERROR     // Catch: java.lang.Exception -> L52
            r7 = 1
            r8 = 0
            r3 = r10
            r4 = r2
            io.dcloud.common.util.JSUtil.execCallback(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L52
            return
        L52:
            r0 = move-exception
            r0.printStackTrace()
            int r6 = io.dcloud.common.util.JSUtil.ERROR
            r7 = 1
            r8 = 0
            java.lang.String r5 = "{\"code\":-100,\"message\":\"\"+sWeb.getContext().getString(R.string.dcloud_ui_webview_not_finished)+\"\"}"
            r3 = r10
            r4 = r2
            io.dcloud.common.util.JSUtil.execCallback(r3, r4, r5, r6, r7, r8)
        L61:
            io.dcloud.common.DHInterface.IWebview r5 = r1.r()
            r0 = 2
            org.json.JSONObject r11 = io.dcloud.common.util.JSONUtil.getJSONObject(r11, r0)
            java.lang.String r0 = "auto"
            if (r11 == 0) goto L7c
            java.lang.String r1 = "type"
            boolean r3 = r11.has(r1)
            if (r3 == 0) goto L7c
            java.lang.String r11 = r11.optString(r1, r0)
            r6 = r11
            goto L7d
        L7c:
            r6 = r0
        L7d:
            android.view.ViewGroup r11 = r5.obtainWindowView()
            if (r11 == 0) goto L8f
            io.dcloud.feature.ui.c$e r0 = new io.dcloud.feature.ui.c$e
            r3 = r0
            r4 = r9
            r7 = r10
            r8 = r2
            r3.<init>(r5, r6, r7, r8)
            r11.post(r0)
        L8f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.a(io.dcloud.common.DHInterface.IWebview, org.json.JSONArray):void");
    }

    private void a(io.dcloud.feature.ui.b bVar, c cVar, String str) {
        c cVar2 = (c) bVar;
        if (cVar2.F) {
            cVar.u = cVar2.u - 1;
            cVar.F = true;
            cVar.I = true;
            cVar.G = false;
            this.c.a(str, cVar, this.c.c(this));
            this.c.d.processEvent(IMgr.MgrType.WindowMgr, 45, new Object[]{cVar.y, cVar2.y});
        }
    }

    private void a(c cVar, String str) {
        IApp iAppObtainApp;
        if (cVar == null || PdrUtil.isEmpty(str) || (iAppObtainApp = cVar.r().obtainApp()) == null) {
            return;
        }
        c cVar2 = cVar.W;
        if (cVar2 != null) {
            str = cVar2.r().obtainUrl();
        }
        String strObtainUrl = cVar.r().obtainUrl();
        if (!BaseInfo.isBase(cVar.a()) || TextUtils.isEmpty(str) || TextUtils.isEmpty(strObtainUrl) || str.startsWith(DeviceInfo.HTTP_PROTOCOL) || strObtainUrl.startsWith(DeviceInfo.HTTP_PROTOCOL)) {
            return;
        }
        Log.i(AbsoluteConst.HBUILDER_TAG, StringUtil.format(AbsoluteConst.OPENLOG, WebResUtil.getHBuilderPrintUrl(iAppObtainApp.convert2RelPath(WebResUtil.getOriginalUrl(str))), WebResUtil.getHBuilderPrintUrl(iAppObtainApp.convert2RelPath(WebResUtil.getOriginalUrl(strObtainUrl)))));
    }

    void a(IWebview iWebview, JSONArray jSONArray, c cVar, String str) throws JSONException {
        String str2;
        if (this.c.a(cVar)) {
            Logger.d(Logger.StreamApp_TAG, "showWebview url=" + cVar.z);
            cVar.A = 1;
            this.c.f(cVar);
            cVar.B = new Object[]{iWebview, jSONArray, cVar, str};
            return;
        }
        cVar.u = System.currentTimeMillis();
        cVar.F = true;
        String string = JSONUtil.getString(jSONArray, 0);
        String string2 = JSONUtil.getString(jSONArray, 1);
        String string3 = JSONUtil.getString(jSONArray, 3);
        this.O = string3;
        if (!PdrUtil.isEmpty(string3)) {
            this.P = iWebview;
        }
        AnimOptions animOptions = ((AdaFrameItem) cVar.y).getAnimOptions();
        if (PdrUtil.isEquals("auto", string)) {
            str2 = animOptions.mAnimType;
        } else {
            str2 = PdrUtil.isEmpty(string) ? "none" : string;
        }
        animOptions.mAnimType = str2;
        boolean z = !PdrUtil.isEquals("none", str2);
        if (PdrUtil.isEmpty(string2)) {
            if (animOptions.mAnimType.equals(AnimOptions.ANIM_POP_IN)) {
                animOptions.duration_show = 300;
            }
        } else {
            animOptions.duration_show = PdrUtil.parseInt(string2, animOptions.duration_show);
        }
        if (!cVar.G && cVar.I) {
            z = false;
        }
        this.c.a(str, cVar, this.c.c(this));
        a(iWebview, JSONUtil.getJSONObject(jSONArray, 4), cVar, string);
        if (cVar.G) {
            animOptions.mOption = (byte) 4;
            this.c.d.processEvent(IMgr.MgrType.WindowMgr, 24, cVar.y);
        } else {
            animOptions.mOption = (byte) 0;
            cVar.I = true;
            this.c.d.processEvent(IMgr.MgrType.WindowMgr, 1, new Object[]{cVar.y, Boolean.valueOf(z)});
        }
        cVar.G = false;
        Logger.d(Logger.VIEW_VISIBLE_TAG, "show " + cVar.y + ";webview_name=" + r().obtainFrameId());
    }

    private void a(IWebview iWebview, JSONObject jSONObject, c cVar, String str) {
        Object obj;
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject != null) {
            String strOptString = jSONObject.optString(AbsoluteConst.ACCELERATION);
            String str2 = TextUtils.isEmpty(strOptString) ? "auto" : strOptString;
            String strOptString2 = jSONObject.optString("action", "none");
            cVar.y.setAccelerationType(str2);
            if (jSONObject.has(AbsoluteConst.CAPTURE)) {
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(AbsoluteConst.CAPTURE);
                if (jSONObjectOptJSONObject2 == null) {
                    return;
                }
                String strOptString3 = jSONObjectOptJSONObject2.optString("__id__");
                if (jSONObjectOptJSONObject2.has("type") && jSONObjectOptJSONObject2.optString("type").equals(AbsoluteConst.NATIVE_NVIEW)) {
                    AbsMgr absMgr = this.c.d;
                    IMgr.MgrType mgrType = IMgr.MgrType.FeatureMgr;
                    IFrameView iFrameView = this.y;
                    obj = AbsoluteConst.NATIVE_NVIEW;
                    Object objProcessEvent = absMgr.processEvent(mgrType, 10, new Object[]{iWebview, "nativeobj", "getNativeView", new Object[]{iFrameView, strOptString3}});
                    if (objProcessEvent != null && (objProcessEvent instanceof INativeView)) {
                        cVar.y.setSnapshotView((INativeView) objProcessEvent, strOptString2);
                        cVar.y.setSnapshot(null);
                    }
                } else {
                    obj = AbsoluteConst.NATIVE_NVIEW;
                    INativeBitmap iNativeBitmapG = g(cVar.y.obtainWebView(), strOptString3);
                    cVar.y.setSnapshot(iNativeBitmapG != null ? iNativeBitmapG.getBitmap() : null);
                    cVar.y.setSnapshotView(null, "none");
                }
            } else {
                obj = AbsoluteConst.NATIVE_NVIEW;
            }
            IFrameView iFrameViewFindPageB = cVar.y.findPageB();
            if (iFrameViewFindPageB != null) {
                iFrameViewFindPageB.setAccelerationType(str2);
                if (!jSONObject.has("otherCapture") || (jSONObjectOptJSONObject = jSONObject.optJSONObject("otherCapture")) == null) {
                    return;
                }
                String strOptString4 = jSONObjectOptJSONObject.optString("__id__");
                if (jSONObjectOptJSONObject.has("type") && jSONObjectOptJSONObject.optString("type").equals(obj)) {
                    Object objProcessEvent2 = this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "getNativeView", new Object[]{iFrameViewFindPageB, strOptString4}});
                    if (objProcessEvent2 == null || !(objProcessEvent2 instanceof INativeView)) {
                        return;
                    }
                    iFrameViewFindPageB.setSnapshotView((INativeView) objProcessEvent2, strOptString2);
                    iFrameViewFindPageB.setSnapshot(null);
                    return;
                }
                INativeBitmap iNativeBitmapG2 = g(iFrameViewFindPageB.obtainWebView(), strOptString4);
                iFrameViewFindPageB.setSnapshot(iNativeBitmapG2 != null ? iNativeBitmapG2.getBitmap() : null);
                iFrameViewFindPageB.setSnapshotView(null, "none");
                return;
            }
            return;
        }
        cVar.y.setSnapshot(null);
        cVar.y.setAccelerationType("auto");
        IFrameView iFrameViewFindPageB2 = cVar.y.findPageB();
        if (iFrameViewFindPageB2 != null) {
            iFrameViewFindPageB2.setSnapshot(null);
            iFrameViewFindPageB2.setAccelerationType("auto");
        }
    }

    void a(IWebview iWebview, JSONArray jSONArray, c cVar) throws JSONException {
        String string = JSONUtil.getString(jSONArray, 0);
        String string2 = JSONUtil.getString(jSONArray, 1);
        cVar.y.captureSnapshot(string, TextUtils.isEmpty(string2) ? null : new f(iWebview, string2), TextUtils.isEmpty(string2) ? null : new g(iWebview, string2));
    }

    void a(c cVar, boolean z) {
        cVar.y.obtainWebView().reload(z);
    }

    boolean a(JSONObject jSONObject, boolean z) throws NumberFormatException {
        boolean z2 = false;
        if (!jSONObject.isNull(AbsoluteConst.JSON_KEY_ZINDEX)) {
            try {
                int i2 = Integer.parseInt(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_ZINDEX));
                if (i2 != this.E) {
                    z2 = true;
                    this.E = i2;
                    ((AdaFrameView) this.y).mZIndex = i2;
                    if (z) {
                        this.c.g(this);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return z2;
    }

    private static String a(ArrayList arrayList) {
        StringBuffer stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                io.dcloud.feature.ui.b bVar = (io.dcloud.feature.ui.b) arrayList.get(i2);
                if (bVar instanceof c) {
                    stringBuffer.append(((c) bVar).h());
                } else {
                    stringBuffer.append("'" + bVar.e + "'");
                }
                if (i2 != size - 1) {
                    stringBuffer.append(",");
                }
            }
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x0114 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x013e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x013e -> B:35:0x013f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.DHInterface.IWebview r45, io.dcloud.feature.ui.c r46, org.json.JSONObject r47, org.json.JSONObject r48) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 1078
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.a(io.dcloud.common.DHInterface.IWebview, io.dcloud.feature.ui.c, org.json.JSONObject, org.json.JSONObject):void");
    }

    private void a(c cVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{cVar.r().obtainApp(), "weex,io.dcloud.feature.weex.WeexFeature", "weexViewUpdate", new Object[]{cVar.r(), cVar.y.obtainMainView(), jSONObject, BaseInfo.getUniNViewId(cVar.y)}});
    }
}
