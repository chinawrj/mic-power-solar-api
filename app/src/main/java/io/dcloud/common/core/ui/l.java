package io.dcloud.common.core.ui;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.dcloud.android.widget.StatusBarView;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.IWebviewStateListener;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.AdaWebViewParent;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.nineoldandroids.view.ViewHelper;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class l extends AbsMgr implements IMgr.WindowEvent {
    HashMap a;
    List b;
    String c;
    Runnable d;
    Runnable e;
    boolean f;
    WindowManager.LayoutParams g;

    class a implements IEventCallback {
        final /* synthetic */ io.dcloud.common.core.ui.b a;
        final /* synthetic */ IApp b;
        final /* synthetic */ io.dcloud.common.core.ui.a c;

        a(io.dcloud.common.core.ui.b bVar, IApp iApp, io.dcloud.common.core.ui.a aVar) {
            this.a = bVar;
            this.b = iApp;
            this.c = aVar;
        }

        @Override // io.dcloud.common.DHInterface.IEventCallback
        public Object onCallBack(String str, Object obj) {
            if (!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_CLOSE)) {
                return null;
            }
            this.a.removeFrameViewListener(this);
            l.this.a(this.b, this.c);
            return null;
        }
    }

    class b implements IWebviewStateListener {
        boolean a = false;
        final /* synthetic */ IApp b;
        final /* synthetic */ io.dcloud.common.core.ui.b c;
        final /* synthetic */ boolean d;
        final /* synthetic */ boolean e;
        final /* synthetic */ String f;
        final /* synthetic */ AdaWebview g;
        final /* synthetic */ io.dcloud.common.core.ui.a h;
        final /* synthetic */ int i;

        class a implements MessageHandler.IMessages {
            a() {
            }

            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                if (((io.dcloud.common.core.ui.a) b.this.b.obtainWebAppRootView()).a(5) == null) {
                    b.this.b.checkOrLoadlaunchWebview();
                }
            }
        }

        b(IApp iApp, io.dcloud.common.core.ui.b bVar, boolean z, boolean z2, String str, AdaWebview adaWebview, io.dcloud.common.core.ui.a aVar, int i) {
            this.b = iApp;
            this.c = bVar;
            this.d = z;
            this.e = z2;
            this.f = str;
            this.g = adaWebview;
            this.h = aVar;
            this.i = i;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            int i2 = AbsoluteConst.EVENTS_TITLE_UPDATE.equals(l.this.c) ? 4 : AbsoluteConst.EVENTS_RENDERING.equals(l.this.c) ? 6 : 1;
            if (i == 3 && !this.a) {
                Integer num = (Integer) obj;
                if (num.intValue() >= 50) {
                    this.a = true;
                    Intent intent = new Intent();
                    intent.setAction(this.b.getActivity().getPackageName() + ".streamdownload.downloadfinish." + this.b.obtainAppId());
                    intent.putExtra("appid", this.b.obtainAppId());
                    intent.putExtra("progress", num.intValue());
                    intent.putExtra("flag", AbsoluteConst.STREAMAPP_KEY_DIRECT_PAGE_PROGRESSED);
                    this.b.getActivity().sendBroadcast(intent);
                }
            }
            if (i == 1) {
                if (this.c.getFrameType() == 5) {
                    this.b.checkOrLoadlaunchWebview();
                } else if (this.c.getFrameType() == 4) {
                    MessageHandler.sendMessage(new a(), 3000L, null);
                }
            }
            if (i == i2 && this.d) {
                boolean z = this.e;
                int i3 = TestUtil.PointTime.AC_TYPE_1_1;
                if (z || (PdrUtil.isNetPath(this.f) && (i == 4 || i == 6))) {
                    boolean z2 = this.e;
                    if (!z2) {
                        if (i == 4) {
                            i3 = TestUtil.PointTime.AC_TYPE_1_2;
                        } else if (i == 6) {
                            i3 = TestUtil.PointTime.AC_TYPE_1_3;
                        }
                    }
                    l lVar = l.this;
                    lVar.f = false;
                    lVar.a(this.g, this.b, z2, this.h, 1, this.c, this.i, i3);
                } else {
                    this.b.setConfigProperty("timeout", "-1");
                    io.dcloud.common.core.ui.a aVar = this.h;
                    aVar.a(aVar, this.c, this.i, true, TestUtil.PointTime.AC_TYPE_1_1);
                }
            }
            return null;
        }
    }

    class c implements Runnable {
        final /* synthetic */ AdaFrameItem a;

        c(AdaFrameItem adaFrameItem) {
            this.a = adaFrameItem;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((AdaFrameView) this.a).changeWebParentViewRect();
        }
    }

    class d implements ICallBack {
        final /* synthetic */ io.dcloud.common.core.ui.b a;
        final /* synthetic */ Object[] b;

        d(io.dcloud.common.core.ui.b bVar, Object[] objArr) {
            this.a = bVar;
            this.b = objArr;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (this.a.u) {
                return null;
            }
            this.a.c(((Boolean) this.b[1]).booleanValue());
            return null;
        }
    }

    class e implements ICallBack {
        final /* synthetic */ io.dcloud.common.core.ui.b a;

        e(io.dcloud.common.core.ui.b bVar) {
            this.a = bVar;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            this.a.setVisible(true, false);
            this.a.p();
            this.a.lastShowTime = System.currentTimeMillis();
            this.a.k.k();
            io.dcloud.common.core.ui.b bVar = this.a;
            if (!bVar.isChildOfFrameView) {
                TestUtil.record("computeStackArray");
                io.dcloud.common.core.ui.b bVar2 = this.a;
                bVar2.k.b(bVar2);
                io.dcloud.common.core.ui.b bVar3 = this.a;
                bVar3.onPushToStack(bVar3.isAutoPop());
                TestUtil.print("computeStackArray", "计算满屏幕时间");
                if (this.a.k.d().contains(this.a)) {
                    this.a.k.l();
                } else {
                    io.dcloud.common.core.ui.b bVar4 = this.a;
                    bVar4.k.e(bVar4);
                }
            } else if (bVar.getParentFrameItem() != null) {
                io.dcloud.common.core.ui.b bVar5 = this.a;
                bVar5.k.h(bVar5);
            }
            io.dcloud.common.core.ui.b bVar6 = this.a;
            if (!bVar6.isChildOfFrameView) {
                int i2 = bVar6.obtainApp().getInt(0);
                int i3 = this.a.obtainApp().getInt(1);
                if ((i2 == this.a.obtainFrameOptions().width && this.a.obtainFrameOptions().height + 1 >= i3) || (this.a.obtainFrameOptions().width == -1 && this.a.obtainFrameOptions().height == -1)) {
                    io.dcloud.common.core.ui.i.a(this.a, 0);
                }
                if (PdrUtil.isEquals(this.a.getAnimOptions().mAnimType, "none")) {
                    this.a.makeViewOptions_animate();
                    this.a.m();
                } else {
                    this.a.s();
                    this.a.startAnimator(0);
                }
            } else if (PdrUtil.isEquals(bVar6.getAnimOptions().mAnimType, AnimOptions.ANIM_FADE_IN)) {
                this.a.s();
                this.a.startAnimator(0);
            } else {
                this.a.makeViewOptions_animate();
                this.a.m();
            }
            io.dcloud.common.core.ui.b bVar7 = this.a;
            bVar7.k.i(bVar7);
            return null;
        }
    }

    class f implements ICallBack {
        final /* synthetic */ io.dcloud.common.core.ui.b a;

        f(io.dcloud.common.core.ui.b bVar) {
            this.a = bVar;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            io.dcloud.common.core.ui.b bVar = this.a;
            int iC = bVar.k.c(bVar);
            this.a.p();
            boolean z = false;
            boolean z2 = this.a.obtainMainView().getVisibility() == AdaFrameItem.VISIBLE;
            io.dcloud.common.core.ui.b bVar2 = this.a;
            if (bVar2.inStack && z2 && !bVar2.isChildOfFrameView) {
                bVar2.k.b(bVar2);
                if (this.a.e()) {
                    l.this.processEvent(IMgr.MgrType.WindowMgr, 28, this.a.b);
                    this.a.b = null;
                }
                int i2 = this.a.obtainApp().getInt(0);
                int i3 = this.a.obtainApp().getInt(1);
                if ((i2 == this.a.obtainFrameOptions().width && this.a.obtainFrameOptions().height + 1 >= i3) || (this.a.obtainFrameOptions().width == -1 && this.a.obtainFrameOptions().height == -1)) {
                    z = true;
                }
                if ((!PdrUtil.isEquals(this.a.getAnimOptions().mAnimType_close, "none") || (BaseInfo.isDefaultAim && z)) && iC >= 0) {
                    this.a.s();
                    if (z && !PdrUtil.isEquals(this.a.getAnimOptions().mAnimType_close, "none")) {
                        io.dcloud.common.core.ui.i.a(this.a, 1);
                    }
                    this.a.startAnimator(1);
                } else {
                    this.a.makeViewOptions_animate();
                    this.a.l();
                    this.a.k();
                }
            } else {
                bVar2.makeViewOptions_animate();
                this.a.l();
                this.a.k();
            }
            return null;
        }
    }

    class g implements ICallBack {
        final /* synthetic */ io.dcloud.common.core.ui.b a;
        final /* synthetic */ int b;

        g(io.dcloud.common.core.ui.b bVar, int i) {
            this.a = bVar;
            this.b = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0089  */
        @Override // io.dcloud.common.DHInterface.ICallBack
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object onCallBack(int r10, java.lang.Object r11) {
            /*
                Method dump skipped, instructions count: 323
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.g.onCallBack(int, java.lang.Object):java.lang.Object");
        }
    }

    class h implements ICallBack {
        final /* synthetic */ io.dcloud.common.core.ui.b a;

        h(io.dcloud.common.core.ui.b bVar) {
            this.a = bVar;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            io.dcloud.common.core.ui.b bVar = this.a;
            bVar.k.e(bVar);
            this.a.setVisible(true, false);
            this.a.k.i();
            return Boolean.FALSE;
        }
    }

    class i implements IWebviewStateListener {
        boolean a = false;
        final /* synthetic */ String b;
        final /* synthetic */ boolean c;
        final /* synthetic */ IApp d;
        final /* synthetic */ io.dcloud.common.core.ui.a e;
        final /* synthetic */ String f;
        final /* synthetic */ IWebview g;
        final /* synthetic */ int h;
        final /* synthetic */ io.dcloud.common.core.ui.b i;
        final /* synthetic */ int j;
        final /* synthetic */ long k;

        i(String str, boolean z, IApp iApp, io.dcloud.common.core.ui.a aVar, String str2, IWebview iWebview, int i, io.dcloud.common.core.ui.b bVar, int i2, long j) {
            this.b = str;
            this.c = z;
            this.d = iApp;
            this.e = aVar;
            this.f = str2;
            this.g = iWebview;
            this.h = i;
            this.i = bVar;
            this.j = i2;
            this.k = j;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            IActivityHandler iActivityHandler;
            int i2 = AbsoluteConst.EVENTS_TITLE_UPDATE.equals(l.this.c) ? 4 : AbsoluteConst.EVENTS_RENDERING.equals(l.this.c) ? 6 : 1;
            Logger.d(Logger.MAIN_TAG, "autoCloseSplash4LaunchWebview  IWebviewStateListener pType= " + i + ";pArgs=" + obj);
            if (i != i2) {
                if (i != 3 || (iActivityHandler = DCloudAdapterUtil.getIActivityHandler(this.d.getActivity())) == null) {
                    return null;
                }
                iActivityHandler.updateParam("progress", obj);
                return null;
            }
            if (this.b.equals("id:*") && this.c) {
                l.this.a(this.d, this.e);
            } else if (this.b.equals("default") && this.c) {
                boolean zIsNetPath = PdrUtil.isNetPath(this.f);
                int i3 = TestUtil.PointTime.AC_TYPE_1_1;
                if (zIsNetPath && (i == 4 || i == 6)) {
                    if (i == 4) {
                        i3 = TestUtil.PointTime.AC_TYPE_1_2;
                    } else if (i == 6) {
                        i3 = TestUtil.PointTime.AC_TYPE_1_3;
                    }
                    l lVar = l.this;
                    lVar.f = false;
                    lVar.a(this.g, this.d, false, this.e, this.h, this.i, this.j, i3);
                } else {
                    this.d.setConfigProperty("timeout", "-1");
                    io.dcloud.common.core.ui.a aVar = this.e;
                    aVar.a(aVar, this.i, this.j, true, TestUtil.PointTime.AC_TYPE_1_1);
                }
            }
            BaseInfo.setLoadingLaunchePage(false, "f_need_auto_close_splash");
            long jCurrentTimeMillis = System.currentTimeMillis() - this.k;
            this.d.setConfigProperty(IApp.ConfigProperty.CONFIG_LOADED_TIME, String.valueOf(jCurrentTimeMillis));
            this.g.evalJS(AbsoluteConst.PROTOCOL_JAVASCRIPT + StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format(AbsoluteConst.JS_RUNTIME_LOADEDTIME, String.valueOf(jCurrentTimeMillis))));
            return null;
        }
    }

    class j implements Runnable {
        final /* synthetic */ io.dcloud.common.core.ui.a a;
        final /* synthetic */ io.dcloud.common.core.ui.b b;
        final /* synthetic */ int c;

        j(io.dcloud.common.core.ui.a aVar, io.dcloud.common.core.ui.b bVar, int i) {
            this.a = aVar;
            this.b = bVar;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            io.dcloud.common.core.ui.a aVar = this.a;
            if (aVar != null) {
                aVar.a(aVar, this.b, this.c, true, 1000);
            }
            l.this.d = null;
        }
    }

    class k implements Runnable {
        final /* synthetic */ io.dcloud.common.core.ui.b a;
        final /* synthetic */ io.dcloud.common.core.ui.a b;
        final /* synthetic */ IApp c;

        k(io.dcloud.common.core.ui.b bVar, io.dcloud.common.core.ui.a aVar, IApp iApp) {
            this.a = bVar;
            this.b = aVar;
            this.c = iApp;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!this.a.obtainWebView().isLoaded() || this.a.obtainWebView().obtainUrl().endsWith("__uniappservice.html") || this.a.obtainWebView().checkWhite("auto")) {
                    l.this.a(this.c, this.b);
                } else {
                    io.dcloud.common.core.ui.a aVar = this.b;
                    aVar.a(aVar, this.a, 0, true, 1);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: io.dcloud.common.core.ui.l$l, reason: collision with other inner class name */
    class RunnableC0037l implements Runnable {
        final /* synthetic */ io.dcloud.common.core.ui.a a;
        final /* synthetic */ boolean b;
        final /* synthetic */ io.dcloud.common.core.ui.b c;
        final /* synthetic */ IWebview d;
        final /* synthetic */ IApp e;
        final /* synthetic */ int f;
        final /* synthetic */ int g;
        final /* synthetic */ int h;

        RunnableC0037l(io.dcloud.common.core.ui.a aVar, boolean z, io.dcloud.common.core.ui.b bVar, IWebview iWebview, IApp iApp, int i, int i2, int i3) {
            this.a = aVar;
            this.b = z;
            this.c = bVar;
            this.d = iWebview;
            this.e = iApp;
            this.f = i;
            this.g = i2;
            this.h = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                io.dcloud.common.core.ui.a aVar = this.a;
                if (aVar == null || aVar.q || l.this.f) {
                    return;
                }
                if ((this.b || this.c.obtainFrameOptions().titleNView == null) && this.d.checkWhite("auto")) {
                    l.this.a(this.d, this.e, this.b, this.a, this.h, this.c, this.f, this.g);
                    return;
                }
                System.currentTimeMillis();
                String str = BaseInfo.sGlobalUserAgent;
                this.e.setConfigProperty("timeout", "-1");
                io.dcloud.common.core.ui.a aVar2 = this.a;
                aVar2.a(aVar2, this.c, this.f, true, this.g);
            } catch (Exception unused) {
            }
        }
    }

    public interface m {
        void onAnimationEnd();
    }

    public l(ICore iCore) {
        super(iCore, "windowmgr", IMgr.MgrType.WindowMgr);
        this.a = new HashMap(0);
        this.b = Collections.synchronizedList(new ArrayList());
        this.c = null;
        this.d = null;
        this.f = false;
        this.g = null;
    }

    private boolean a(int i2, int i3, int i4, int i5, int i6, int i7) {
        return i2 == 0 && i3 == 0 && i4 == i6 && i5 == i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(int r17, java.lang.Object r18) {
        /*
            r16 = this;
            r1 = r16
            r0 = r18
            boolean r2 = r0 instanceof java.lang.Object[]
            if (r2 == 0) goto Lc6
            r2 = r0
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            r3 = 0
            r0 = r2[r3]
            r4 = r0
            io.dcloud.common.DHInterface.IApp r4 = (io.dcloud.common.DHInterface.IApp) r4
            int r0 = r2.length
            r5 = 2
            r6 = 3
            if (r0 < r6) goto L20
            r0 = r2[r5]
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r7 = r0
            goto L21
        L20:
            r7 = r3
        L21:
            java.lang.String r8 = r4.obtainAppId()
            java.util.HashMap r0 = r1.a
            java.lang.Object r0 = r0.get(r8)
            r9 = r0
            io.dcloud.common.core.ui.a r9 = (io.dcloud.common.core.ui.a) r9
            io.dcloud.common.core.ui.b r0 = r9.d
            r10 = 1
            if (r0 != 0) goto L35
            r11 = r10
            goto L36
        L35:
            r11 = r3
        L36:
            if (r0 != 0) goto L84
            android.content.Intent r0 = r4.obtainWebAppIntent()
            java.lang.String r13 = "__from_stream_open_style__"
            java.lang.String r0 = r0.getStringExtra(r13)
            boolean r14 = android.text.TextUtils.isEmpty(r0)     // Catch: org.json.JSONException -> L5e
            if (r14 != 0) goto L57
            org.json.JSONObject r14 = new org.json.JSONObject     // Catch: org.json.JSONException -> L5e
            r14.<init>(r0)     // Catch: org.json.JSONException -> L5e
            android.content.Intent r0 = r4.obtainWebAppIntent()     // Catch: org.json.JSONException -> L55
            r0.removeExtra(r13)     // Catch: org.json.JSONException -> L55
            goto L63
        L55:
            r0 = move-exception
            goto L60
        L57:
            java.lang.String r0 = "{}"
            org.json.JSONObject r14 = io.dcloud.common.util.JSONUtil.createJSONObject(r0)     // Catch: org.json.JSONException -> L5e
            goto L63
        L5e:
            r0 = move-exception
            r14 = 0
        L60:
            r0.printStackTrace()
        L63:
            io.dcloud.common.DHInterface.IMgr$MgrType r0 = io.dcloud.common.DHInterface.IMgr.MgrType.WindowMgr
            java.lang.Integer r13 = java.lang.Integer.valueOf(r6)
            r15 = r2[r10]
            java.lang.Object[] r12 = new java.lang.Object[r5]
            r12[r3] = r15
            r12[r10] = r14
            r14 = 4
            java.lang.Object[] r14 = new java.lang.Object[r14]
            r14[r3] = r13
            r14[r10] = r4
            r14[r5] = r12
            r14[r6] = r9
            java.lang.Object r0 = r1.processEvent(r0, r6, r14)
            io.dcloud.common.core.ui.b r0 = (io.dcloud.common.core.ui.b) r0
            r9.d = r0
        L84:
            io.dcloud.common.DHInterface.IWebview r4 = r0.obtainWebView()
            if (r7 != 0) goto L93
            android.view.ViewGroup r3 = r4.obtainWindowView()
            r5 = 0
            r3.setLayerType(r10, r5)
            goto L9b
        L93:
            r5 = 0
            android.view.ViewGroup r6 = r4.obtainWindowView()
            r6.setLayerType(r3, r5)
        L9b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "load "
            r3.<init>(r5)
            r3.append(r8)
            java.lang.String r5 = " launchPage ="
            r3.append(r5)
            r5 = r2[r10]
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "Main_Path"
            io.dcloud.common.adapter.util.Logger.d(r5, r3)
            r2 = r2[r10]
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r4.loadUrl(r2)
            if (r11 == 0) goto Lc6
            r9.e(r0)
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.b(int, java.lang.Object):void");
    }

    protected synchronized void c() {
        if (this.b == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (m mVar : this.b) {
                mVar.onAnimationEnd();
                arrayList.add(mVar);
            }
            if (arrayList.size() > 0) {
                this.b.removeAll(arrayList);
            }
            arrayList.clear();
        } catch (Exception unused) {
        }
    }

    public void d(io.dcloud.common.core.ui.b bVar) {
        IApp iAppObtainApp = bVar.obtainApp();
        iAppObtainApp.setMaskLayer(true);
        iAppObtainApp.obtainWebAppRootView().obtainMainView().invalidate();
    }

    @Override // io.dcloud.common.DHInterface.AbsMgr
    public void dispose() {
        try {
            List list = this.b;
            if (list != null) {
                list.clear();
            }
            Iterator it = this.a.keySet().iterator();
            while (it.hasNext()) {
                ((io.dcloud.common.core.ui.a) this.a.get((String) it.next())).dispose();
            }
            this.a.clear();
            if (BaseInfo.ISDEBUG) {
                io.dcloud.common.core.ui.f.a();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x064b A[Catch: all -> 0x0034, TryCatch #1 {all -> 0x0034, blocks: (B:5:0x002a, B:306:0x070d, B:308:0x0711, B:310:0x071c, B:312:0x0722, B:314:0x072a, B:316:0x0730, B:317:0x0739, B:319:0x0740, B:321:0x0746, B:322:0x0751, B:324:0x0755, B:326:0x0764, B:328:0x076a, B:329:0x0773, B:331:0x0779, B:333:0x077f, B:334:0x078a, B:336:0x078e, B:338:0x07a0, B:340:0x07aa, B:343:0x07f3, B:339:0x07a5, B:346:0x07fb, B:348:0x0809, B:349:0x080f, B:351:0x081d, B:352:0x0823, B:354:0x083a, B:355:0x0848, B:356:0x087c, B:357:0x0891, B:152:0x036b, B:154:0x036f, B:159:0x038f, B:155:0x037b, B:157:0x037f, B:170:0x03c2, B:172:0x03d7, B:173:0x03dc, B:174:0x03e4, B:176:0x03ee, B:178:0x03f7, B:180:0x0402, B:183:0x040c, B:185:0x0410, B:186:0x0414, B:188:0x041e, B:189:0x0451, B:190:0x0459, B:192:0x045e, B:193:0x046c, B:195:0x0471, B:197:0x047b, B:200:0x0485, B:202:0x0489, B:203:0x048d, B:205:0x0499, B:207:0x049f, B:209:0x04a3, B:212:0x04ae, B:214:0x04b8, B:216:0x04be, B:217:0x04c6, B:218:0x0507, B:221:0x050f, B:223:0x0515, B:225:0x0522, B:227:0x052e, B:229:0x0534, B:231:0x053a, B:233:0x053e, B:235:0x0548, B:237:0x0550, B:239:0x0558, B:240:0x0561, B:241:0x056c, B:242:0x0574, B:243:0x0577, B:245:0x057c, B:246:0x0584, B:247:0x0599, B:249:0x059d, B:254:0x05a6, B:255:0x05ce, B:257:0x05d2, B:262:0x05db, B:264:0x0605, B:266:0x0614, B:268:0x061e, B:269:0x0644, B:278:0x0680, B:280:0x0688, B:296:0x06e7, B:300:0x06f1, B:304:0x06fd, B:305:0x0706, B:281:0x0692, B:283:0x0696, B:284:0x06a2, B:286:0x06a6, B:290:0x06b7, B:293:0x06d5, B:130:0x02da, B:131:0x02df, B:133:0x02f4, B:135:0x02fc, B:137:0x0302, B:142:0x0331, B:144:0x033d, B:145:0x0344, B:151:0x0350, B:22:0x0061, B:23:0x006e, B:24:0x0078, B:25:0x0082, B:26:0x008c, B:28:0x009a, B:30:0x00a4, B:31:0x00ae, B:33:0x00b3, B:35:0x00bf, B:37:0x00da, B:38:0x00e0, B:39:0x00e8, B:47:0x0123, B:50:0x0148, B:52:0x014c, B:54:0x0157, B:55:0x0161, B:56:0x016a, B:58:0x0172, B:65:0x018f, B:68:0x0197, B:69:0x01a6, B:71:0x01ae, B:72:0x01bc, B:74:0x01c0, B:75:0x01d1, B:76:0x01db, B:78:0x01e1, B:79:0x01fa, B:81:0x0218, B:83:0x0220, B:85:0x0226, B:87:0x022b, B:89:0x022f, B:91:0x0235, B:97:0x0243, B:99:0x0247, B:101:0x024e, B:104:0x0254, B:108:0x0260, B:109:0x0264, B:111:0x0268, B:116:0x0272, B:120:0x027a, B:92:0x0238, B:94:0x023c, B:121:0x0290, B:123:0x029a, B:128:0x02ac, B:129:0x02b2, B:126:0x02a1, B:270:0x064b, B:272:0x064f, B:277:0x0657), top: B:462:0x0028, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x022f A[Catch: all -> 0x0034, TryCatch #1 {all -> 0x0034, blocks: (B:5:0x002a, B:306:0x070d, B:308:0x0711, B:310:0x071c, B:312:0x0722, B:314:0x072a, B:316:0x0730, B:317:0x0739, B:319:0x0740, B:321:0x0746, B:322:0x0751, B:324:0x0755, B:326:0x0764, B:328:0x076a, B:329:0x0773, B:331:0x0779, B:333:0x077f, B:334:0x078a, B:336:0x078e, B:338:0x07a0, B:340:0x07aa, B:343:0x07f3, B:339:0x07a5, B:346:0x07fb, B:348:0x0809, B:349:0x080f, B:351:0x081d, B:352:0x0823, B:354:0x083a, B:355:0x0848, B:356:0x087c, B:357:0x0891, B:152:0x036b, B:154:0x036f, B:159:0x038f, B:155:0x037b, B:157:0x037f, B:170:0x03c2, B:172:0x03d7, B:173:0x03dc, B:174:0x03e4, B:176:0x03ee, B:178:0x03f7, B:180:0x0402, B:183:0x040c, B:185:0x0410, B:186:0x0414, B:188:0x041e, B:189:0x0451, B:190:0x0459, B:192:0x045e, B:193:0x046c, B:195:0x0471, B:197:0x047b, B:200:0x0485, B:202:0x0489, B:203:0x048d, B:205:0x0499, B:207:0x049f, B:209:0x04a3, B:212:0x04ae, B:214:0x04b8, B:216:0x04be, B:217:0x04c6, B:218:0x0507, B:221:0x050f, B:223:0x0515, B:225:0x0522, B:227:0x052e, B:229:0x0534, B:231:0x053a, B:233:0x053e, B:235:0x0548, B:237:0x0550, B:239:0x0558, B:240:0x0561, B:241:0x056c, B:242:0x0574, B:243:0x0577, B:245:0x057c, B:246:0x0584, B:247:0x0599, B:249:0x059d, B:254:0x05a6, B:255:0x05ce, B:257:0x05d2, B:262:0x05db, B:264:0x0605, B:266:0x0614, B:268:0x061e, B:269:0x0644, B:278:0x0680, B:280:0x0688, B:296:0x06e7, B:300:0x06f1, B:304:0x06fd, B:305:0x0706, B:281:0x0692, B:283:0x0696, B:284:0x06a2, B:286:0x06a6, B:290:0x06b7, B:293:0x06d5, B:130:0x02da, B:131:0x02df, B:133:0x02f4, B:135:0x02fc, B:137:0x0302, B:142:0x0331, B:144:0x033d, B:145:0x0344, B:151:0x0350, B:22:0x0061, B:23:0x006e, B:24:0x0078, B:25:0x0082, B:26:0x008c, B:28:0x009a, B:30:0x00a4, B:31:0x00ae, B:33:0x00b3, B:35:0x00bf, B:37:0x00da, B:38:0x00e0, B:39:0x00e8, B:47:0x0123, B:50:0x0148, B:52:0x014c, B:54:0x0157, B:55:0x0161, B:56:0x016a, B:58:0x0172, B:65:0x018f, B:68:0x0197, B:69:0x01a6, B:71:0x01ae, B:72:0x01bc, B:74:0x01c0, B:75:0x01d1, B:76:0x01db, B:78:0x01e1, B:79:0x01fa, B:81:0x0218, B:83:0x0220, B:85:0x0226, B:87:0x022b, B:89:0x022f, B:91:0x0235, B:97:0x0243, B:99:0x0247, B:101:0x024e, B:104:0x0254, B:108:0x0260, B:109:0x0264, B:111:0x0268, B:116:0x0272, B:120:0x027a, B:92:0x0238, B:94:0x023c, B:121:0x0290, B:123:0x029a, B:128:0x02ac, B:129:0x02b2, B:126:0x02a1, B:270:0x064b, B:272:0x064f, B:277:0x0657), top: B:462:0x0028, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0247 A[Catch: all -> 0x0034, TryCatch #1 {all -> 0x0034, blocks: (B:5:0x002a, B:306:0x070d, B:308:0x0711, B:310:0x071c, B:312:0x0722, B:314:0x072a, B:316:0x0730, B:317:0x0739, B:319:0x0740, B:321:0x0746, B:322:0x0751, B:324:0x0755, B:326:0x0764, B:328:0x076a, B:329:0x0773, B:331:0x0779, B:333:0x077f, B:334:0x078a, B:336:0x078e, B:338:0x07a0, B:340:0x07aa, B:343:0x07f3, B:339:0x07a5, B:346:0x07fb, B:348:0x0809, B:349:0x080f, B:351:0x081d, B:352:0x0823, B:354:0x083a, B:355:0x0848, B:356:0x087c, B:357:0x0891, B:152:0x036b, B:154:0x036f, B:159:0x038f, B:155:0x037b, B:157:0x037f, B:170:0x03c2, B:172:0x03d7, B:173:0x03dc, B:174:0x03e4, B:176:0x03ee, B:178:0x03f7, B:180:0x0402, B:183:0x040c, B:185:0x0410, B:186:0x0414, B:188:0x041e, B:189:0x0451, B:190:0x0459, B:192:0x045e, B:193:0x046c, B:195:0x0471, B:197:0x047b, B:200:0x0485, B:202:0x0489, B:203:0x048d, B:205:0x0499, B:207:0x049f, B:209:0x04a3, B:212:0x04ae, B:214:0x04b8, B:216:0x04be, B:217:0x04c6, B:218:0x0507, B:221:0x050f, B:223:0x0515, B:225:0x0522, B:227:0x052e, B:229:0x0534, B:231:0x053a, B:233:0x053e, B:235:0x0548, B:237:0x0550, B:239:0x0558, B:240:0x0561, B:241:0x056c, B:242:0x0574, B:243:0x0577, B:245:0x057c, B:246:0x0584, B:247:0x0599, B:249:0x059d, B:254:0x05a6, B:255:0x05ce, B:257:0x05d2, B:262:0x05db, B:264:0x0605, B:266:0x0614, B:268:0x061e, B:269:0x0644, B:278:0x0680, B:280:0x0688, B:296:0x06e7, B:300:0x06f1, B:304:0x06fd, B:305:0x0706, B:281:0x0692, B:283:0x0696, B:284:0x06a2, B:286:0x06a6, B:290:0x06b7, B:293:0x06d5, B:130:0x02da, B:131:0x02df, B:133:0x02f4, B:135:0x02fc, B:137:0x0302, B:142:0x0331, B:144:0x033d, B:145:0x0344, B:151:0x0350, B:22:0x0061, B:23:0x006e, B:24:0x0078, B:25:0x0082, B:26:0x008c, B:28:0x009a, B:30:0x00a4, B:31:0x00ae, B:33:0x00b3, B:35:0x00bf, B:37:0x00da, B:38:0x00e0, B:39:0x00e8, B:47:0x0123, B:50:0x0148, B:52:0x014c, B:54:0x0157, B:55:0x0161, B:56:0x016a, B:58:0x0172, B:65:0x018f, B:68:0x0197, B:69:0x01a6, B:71:0x01ae, B:72:0x01bc, B:74:0x01c0, B:75:0x01d1, B:76:0x01db, B:78:0x01e1, B:79:0x01fa, B:81:0x0218, B:83:0x0220, B:85:0x0226, B:87:0x022b, B:89:0x022f, B:91:0x0235, B:97:0x0243, B:99:0x0247, B:101:0x024e, B:104:0x0254, B:108:0x0260, B:109:0x0264, B:111:0x0268, B:116:0x0272, B:120:0x027a, B:92:0x0238, B:94:0x023c, B:121:0x0290, B:123:0x029a, B:128:0x02ac, B:129:0x02b2, B:126:0x02a1, B:270:0x064b, B:272:0x064f, B:277:0x0657), top: B:462:0x0028, inners: #3 }] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v145 */
    /* JADX WARN: Type inference failed for: r1v152, types: [io.dcloud.common.core.ui.b] */
    /* JADX WARN: Type inference failed for: r1v158, types: [io.dcloud.common.core.ui.b] */
    /* JADX WARN: Type inference failed for: r1v168, types: [io.dcloud.common.core.ui.b] */
    /* JADX WARN: Type inference failed for: r1v172, types: [io.dcloud.common.core.ui.b] */
    /* JADX WARN: Type inference failed for: r1v19, types: [io.dcloud.common.core.ui.b] */
    /* JADX WARN: Type inference failed for: r1v217, types: [io.dcloud.common.adapter.ui.AdaFrameItem, io.dcloud.common.core.ui.b] */
    /* JADX WARN: Type inference failed for: r1v257 */
    /* JADX WARN: Type inference failed for: r1v258, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v259 */
    /* JADX WARN: Type inference failed for: r1v26, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r1v260 */
    /* JADX WARN: Type inference failed for: r1v261 */
    /* JADX WARN: Type inference failed for: r1v29, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v34, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v59, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v75, types: [io.dcloud.common.DHInterface.IFrameView] */
    /* JADX WARN: Type inference failed for: r1v78, types: [io.dcloud.common.core.ui.b] */
    /* JADX WARN: Type inference failed for: r21v0, types: [io.dcloud.common.DHInterface.AbsMgr, io.dcloud.common.core.ui.l] */
    /* JADX WARN: Type inference failed for: r2v53, types: [io.dcloud.common.DHInterface.IWebview, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v79, types: [org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r4v94, types: [java.lang.Object, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v17, types: [io.dcloud.common.DHInterface.ICallBack, io.dcloud.common.DHInterface.IWebviewStateListener] */
    @Override // io.dcloud.common.DHInterface.IMgr
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object processEvent(io.dcloud.common.DHInterface.IMgr.MgrType r22, int r23, java.lang.Object r24) {
        /*
            Method dump skipped, instructions count: 2990
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.processEvent(io.dcloud.common.DHInterface.IMgr$MgrType, int, java.lang.Object):java.lang.Object");
    }

    public synchronized void a(m mVar) {
        if (!this.b.contains(mVar)) {
            this.b.add(mVar);
        }
    }

    void a(ViewGroup viewGroup, IApp iApp, IWebview iWebview, ViewGroup.LayoutParams layoutParams) {
        a(iApp, iApp.obtainAppId());
        io.dcloud.common.core.ui.a aVar = (io.dcloud.common.core.ui.a) this.a.get(iApp.obtainAppId());
        io.dcloud.common.core.ui.b bVar = (io.dcloud.common.core.ui.b) iWebview.obtainFrameView();
        bVar.k = aVar;
        View viewObtainMainView = bVar.obtainMainView();
        if (viewObtainMainView.getParent() != null) {
            ((ViewGroup) viewObtainMainView.getParent()).removeView(viewObtainMainView);
        }
        viewGroup.addView(viewObtainMainView, layoutParams);
    }

    public void c(io.dcloud.common.core.ui.b bVar) {
        bVar.a(io.dcloud.common.core.ui.b.A);
        bVar.p();
        bVar.k.b(bVar);
        if (bVar.e()) {
            processEvent(IMgr.MgrType.WindowMgr, 28, bVar.b);
            bVar.b = null;
        }
        bVar.makeViewOptions_animate();
        bVar.l();
        bVar.k();
    }

    synchronized boolean a(IApp iApp, String str) {
        boolean z;
        Logger.e("streamsdk", "come into createAppRootView pAppid===" + str);
        io.dcloud.common.core.ui.a aVar = (io.dcloud.common.core.ui.a) this.a.get(str);
        z = false;
        if (aVar == null || !aVar.h) {
            if (aVar != null && !aVar.h) {
                this.a.remove(str);
            }
            Logger.e("streamsdk", "come into createAppRootView and new le rootview  pAppid===" + str);
            Logger.d(Logger.MAIN_TAG, "create " + str + " AppRootView");
            io.dcloud.common.core.ui.a aVar2 = new io.dcloud.common.core.ui.a(iApp.getActivity(), iApp, null);
            aVar2.onAppStart(iApp);
            aVar2.obtainFrameOptions().setParentViewRect(iApp.getAppViewRect());
            z = true;
            aVar2.obtainFrameOptions().updateViewData(JSONUtil.createJSONObject("{}"), iApp.getInt(0), iApp.getInt(1));
            this.a.put(str, aVar2);
            iApp.obtainAppId();
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03a9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x015e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:209:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x021e  */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v4, types: [byte] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r24v0, types: [io.dcloud.common.core.ui.l] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(int r25, java.lang.Object r26) throws org.json.JSONException, java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 1054
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(int, java.lang.Object):void");
    }

    void b(IApp iApp, IWebview iWebview) {
        if (iApp.obtainThridInfo(IApp.ConfigProperty.ThridInfo.SecondWebviewJsonData) != null || (BaseInfo.isWap2AppAppid(iApp.obtainAppId()) && !TextUtils.isEmpty(iApp.getOriginalDirectPage()))) {
            processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{iWebview, "UI", "n_createSecondWebview", null});
        }
    }

    private io.dcloud.common.core.ui.b b(IApp iApp) {
        io.dcloud.common.core.ui.a aVar = (io.dcloud.common.core.ui.a) iApp.obtainWebAppRootView();
        if (aVar != null) {
            return aVar.h();
        }
        return null;
    }

    private io.dcloud.common.core.ui.a b() {
        return (io.dcloud.common.core.ui.a) this.a.get(String.valueOf(processEvent(IMgr.MgrType.AppMgr, 11, null)));
    }

    public void b(io.dcloud.common.core.ui.b bVar) {
        IApp iAppObtainApp = bVar.obtainApp();
        iAppObtainApp.setMaskLayer(false);
        iAppObtainApp.obtainWebAppRootView().obtainMainView().invalidate();
    }

    private io.dcloud.common.core.ui.c b(IApp iApp, io.dcloud.common.core.ui.a aVar) {
        JSONObject jSONObjectObtainThridInfo = iApp.obtainThridInfo(IApp.ConfigProperty.ThridInfo.Tabbar);
        if (jSONObjectObtainThridInfo == null) {
            return null;
        }
        io.dcloud.common.core.ui.c cVar = new io.dcloud.common.core.ui.c(iApp.getActivity(), this, iApp, aVar, 8, jSONObjectObtainThridInfo);
        int i2 = iApp.getInt(0);
        int i3 = iApp.getInt(1);
        ViewOptions viewOptionsObtainFrameOptions = cVar.obtainFrameOptions();
        ViewOptions viewOptionsObtainFrameOptions2 = aVar.obtainFrameOptions();
        if (viewOptionsObtainFrameOptions2.height > i3) {
            viewOptionsObtainFrameOptions2.updateViewData(viewOptionsObtainFrameOptions2.mJsonViewOption, i2, i3);
        }
        viewOptionsObtainFrameOptions.setParentViewRect(viewOptionsObtainFrameOptions2);
        viewOptionsObtainFrameOptions.popGesture = iApp.getPopGesture();
        View viewObtainMainView = cVar.obtainMainView();
        viewOptionsObtainFrameOptions.width = -1;
        viewOptionsObtainFrameOptions.height = -1;
        AdaFrameItem.LayoutParamsUtil.setViewLayoutParams(viewObtainMainView, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, -1, -1);
        aVar.addFrameItem(cVar, new ViewGroup.LayoutParams(-1, -1));
        cVar.k.e(cVar);
        processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{cVar.obtainWebView(), "UI", "", null});
        return cVar;
    }

    public void a(IApp iApp, io.dcloud.common.core.ui.a aVar, String str, String str2, JSONObject jSONObject) {
        String strOptString = (jSONObject == null || !jSONObject.has(AbsoluteConst.XML_PATH)) ? null : jSONObject.optString(AbsoluteConst.XML_PATH);
        if (PdrUtil.isEmpty(strOptString)) {
            return;
        }
        iApp.setConfigProperty(AbsoluteConst.UNIAPP_WEEX_JS_SERVICE, String.valueOf(true));
        int i2 = iApp.getInt(0);
        int i3 = iApp.getInt(1);
        io.dcloud.common.core.ui.b bVar = new io.dcloud.common.core.ui.b(iApp.getActivity(), this, iApp, aVar, 7, null);
        io.dcloud.common.core.ui.d dVar = new io.dcloud.common.core.ui.d(iApp.getActivity(), bVar, strOptString, str, jSONObject, true);
        dVar.initWebviewUUID(str);
        ViewOptions viewOptionsObtainFrameOptions = bVar.obtainFrameOptions();
        ViewOptions viewOptionsObtainFrameOptions2 = aVar.obtainFrameOptions();
        if (viewOptionsObtainFrameOptions2.height > i3) {
            viewOptionsObtainFrameOptions2.updateViewData(viewOptionsObtainFrameOptions2.mJsonViewOption, i2, i3);
        }
        viewOptionsObtainFrameOptions.setParentViewRect(viewOptionsObtainFrameOptions2);
        viewOptionsObtainFrameOptions.popGesture = iApp.getPopGesture();
        View viewObtainMainView = bVar.obtainMainView();
        int i4 = viewOptionsObtainFrameOptions.width;
        if (i4 == i2) {
            i4 = -1;
        }
        int i5 = viewOptionsObtainFrameOptions.height;
        if (i5 == i3) {
            i5 = -1;
        }
        AdaFrameItem.LayoutParamsUtil.setViewLayoutParams(viewObtainMainView, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, i4, i5);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        bVar.addFrameItem(bVar.obtainWebviewParent(), layoutParams);
        bVar.setVisible(false, false);
        aVar.addFrameItem(bVar, layoutParams);
        dVar.setFrameId(str2);
        bVar.k.e(bVar);
        processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{dVar, "UI", "", null});
    }

    private void a(IApp iApp, ViewGroup viewGroup) throws JSONException {
        if (!BaseInfo.isUniNViewBackgroud() || BaseInfo.isWeexUniJs(iApp)) {
            return;
        }
        Object objProcessEvent = processEvent(IMgr.MgrType.AppMgr, 24, null);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("template", String.valueOf(objProcessEvent));
            jSONObject.put(AbsoluteConst.XML_PATH, iApp.obtainAppDataPath() + "nvue_service.js");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iApp, "weex,io.dcloud.feature.weex.WeexFeature", "createServiceUniNView", new Object[]{iApp, jSONObject, viewGroup, "__uniapp__nvue"}});
    }

    private boolean a(IApp iApp) {
        return (TextUtils.isEmpty(iApp.getOriginalDirectPage()) || iApp.obtainWebAppIntent().hasExtra(IntentConst.DIRECT_PAGE)) ? false : true;
    }

    private void a(int i2, io.dcloud.common.core.ui.a aVar, String str, io.dcloud.common.core.ui.b bVar, IApp iApp, String str2, IWebview iWebview) throws NumberFormatException {
        boolean z;
        io.dcloud.common.core.ui.a aVar2;
        IWebviewStateListener iWebviewStateListenerObtainLaunchPageStateListener = iApp.obtainLaunchPageStateListener();
        if (iWebviewStateListenerObtainLaunchPageStateListener != null) {
            boolean z2 = PdrUtil.parseBoolean(String.valueOf(iWebviewStateListenerObtainLaunchPageStateListener.onCallBack(-1, iWebview)), true, false);
            iWebview.addStateListener(iApp.obtainLaunchPageStateListener());
            z = z2;
        } else {
            z = true;
        }
        int i3 = Integer.parseInt(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_DELAY));
        boolean z3 = Boolean.parseBoolean(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_AUTOCLOSE));
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z4 = BaseInfo.isWap2AppAppid(str) && Boolean.parseBoolean(iApp.obtainConfigProperty("w2a_autoclose"));
        Intent intentObtainWebAppIntent = iApp.obtainWebAppIntent();
        String strObtainConfigProperty = iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_TARGET);
        if (TextUtils.isEmpty(strObtainConfigProperty)) {
            strObtainConfigProperty = "default";
        }
        boolean z5 = z3 || z4;
        int intExtra = intentObtainWebAppIntent.getIntExtra(IntentConst.FROM_STREAM_OPEN_TIMEOUT, 6000);
        boolean booleanExtra = intentObtainWebAppIntent.getBooleanExtra(IntentConst.FROM_STREAM_OPEN_AUTOCLOSE, z5);
        if (strObtainConfigProperty.startsWith("id:") && booleanExtra) {
            intExtra = 10000;
        }
        int i4 = intExtra;
        int i5 = z4 ? Integer.parseInt(iApp.obtainConfigProperty("w2a_delay")) : i3;
        if (BaseInfo.isWap2AppAppid(str) && PdrUtil.isNetPath(str2)) {
            this.c = AbsoluteConst.EVENTS_RENDERING;
        } else {
            this.c = AbsoluteConst.EVENTS_LOADED;
        }
        String strObtainConfigProperty2 = iApp.obtainConfigProperty("event");
        if (!TextUtils.isEmpty(strObtainConfigProperty2)) {
            this.c = strObtainConfigProperty2;
        }
        Logger.d(Logger.MAIN_TAG, "_need_auto_close_splash = " + z3 + ";_delay=" + i3 + ";appid=" + str + ";f_event=" + this.c);
        int i6 = i5;
        iWebview.addStateListener(new i(strObtainConfigProperty, booleanExtra, iApp, aVar, str2, iWebview, i2, bVar, i6, jCurrentTimeMillis));
        if (booleanExtra) {
            aVar2 = aVar;
            a(i4, aVar2, bVar, i6);
        } else {
            aVar2 = aVar;
        }
        if (!z || bVar.isChildOfFrameView) {
            return;
        }
        aVar2.e(bVar);
    }

    private void a(int i2, io.dcloud.common.core.ui.a aVar, io.dcloud.common.core.ui.b bVar, int i3) {
        if (this.d != null) {
            aVar.obtainMainView().removeCallbacks(this.d);
        }
        this.d = new j(aVar, bVar, i3);
        aVar.obtainMainView().postDelayed(this.d, i2);
    }

    private void a(io.dcloud.common.core.ui.a aVar) {
        if (this.d == null || aVar == null) {
            return;
        }
        aVar.obtainMainView().removeCallbacks(this.d);
        this.d = null;
    }

    void a(IApp iApp, IWebview iWebview, JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, (Object) null);
            jSONArray.put(1, (Object) null);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(0, (Object) null);
            jSONArray.put(2, jSONArray2);
            jSONArray.put(3, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{iWebview, "UI", "n_createHDWebview", jSONArray});
    }

    void a(IApp iApp, IWebview iWebview) {
        if (BaseInfo.isWap2AppAppid(iApp.obtainAppId()) && iApp.obtainWebAppIntent().hasExtra(IntentConst.DIRECT_PAGE)) {
            processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{iWebview, "UI", "n_createDirectWebview", null});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IApp iApp, io.dcloud.common.core.ui.a aVar) {
        io.dcloud.common.core.ui.b bVarH;
        if (aVar == null || aVar.q || (bVarH = aVar.h()) == null) {
            return;
        }
        k kVar = new k(bVarH, aVar, iApp);
        Runnable runnable = this.e;
        if (runnable != null) {
            this.f = true;
            MessageHandler.removeCallbacks(runnable);
        }
        MessageHandler.postDelayed(kVar, 100L);
    }

    public void a(IWebview iWebview, IApp iApp, boolean z, io.dcloud.common.core.ui.a aVar, int i2, io.dcloud.common.core.ui.b bVar, int i3, int i4) {
        RunnableC0037l runnableC0037l = new RunnableC0037l(aVar, z, bVar, iWebview, iApp, i3, i4, i2);
        this.e = runnableC0037l;
        MessageHandler.postDelayed(runnableC0037l, 100L);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0405 A[LOOP:0: B:181:0x03ff->B:183:0x0405, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0224  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    io.dcloud.common.core.ui.b a(int r31, io.dcloud.common.DHInterface.IApp r32, io.dcloud.common.core.ui.a r33, io.dcloud.common.core.ui.b r34, io.dcloud.common.DHInterface.IEventCallback r35, java.lang.Object[] r36, io.dcloud.common.DHInterface.IDCloudWebviewClientListener r37) throws org.json.JSONException, android.content.res.Resources.NotFoundException, java.lang.ClassNotFoundException, java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 1304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(int, io.dcloud.common.DHInterface.IApp, io.dcloud.common.core.ui.a, io.dcloud.common.core.ui.b, io.dcloud.common.DHInterface.IEventCallback, java.lang.Object[], io.dcloud.common.DHInterface.IDCloudWebviewClientListener):io.dcloud.common.core.ui.b");
    }

    private void a(io.dcloud.common.core.ui.b bVar, boolean z) throws JSONException {
        int i2 = bVar.obtainApp().getInt(0);
        int i3 = bVar.obtainApp().getInt(1);
        AdaWebViewParent adaWebViewParentObtainWebviewParent = bVar.obtainWebviewParent();
        ViewOptions viewOptionsObtainFrameOptions = bVar.obtainFrameOptions();
        ViewOptions viewOptionsObtainFrameOptions2 = adaWebViewParentObtainWebviewParent.obtainFrameOptions();
        io.dcloud.common.core.ui.a aVar = (io.dcloud.common.core.ui.a) bVar.obtainWebAppRootView();
        ViewOptions viewOptionsObtainFrameOptions3 = aVar.obtainFrameOptions();
        viewOptionsObtainFrameOptions2.setParentViewRect(viewOptionsObtainFrameOptions3);
        viewOptionsObtainFrameOptions2.updateViewData(viewOptionsObtainFrameOptions);
        viewOptionsObtainFrameOptions.left = 0;
        viewOptionsObtainFrameOptions.top = 0;
        viewOptionsObtainFrameOptions.anim_top = 0;
        viewOptionsObtainFrameOptions.anim_left = 0;
        ViewHelper.setY(bVar.obtainMainView(), 0.0f);
        ViewHelper.setX(bVar.obtainMainView(), 0.0f);
        viewOptionsObtainFrameOptions.width = i2;
        viewOptionsObtainFrameOptions.height = i3;
        int i4 = viewOptionsObtainFrameOptions2.left;
        int i5 = viewOptionsObtainFrameOptions2.top;
        int i6 = viewOptionsObtainFrameOptions2.width;
        int i7 = viewOptionsObtainFrameOptions2.height;
        adaWebViewParentObtainWebviewParent.setFrameOptions_Birth(ViewOptions.createViewOptionsData(viewOptionsObtainFrameOptions2, viewOptionsObtainFrameOptions3, viewOptionsObtainFrameOptions2));
        viewOptionsObtainFrameOptions2.allowUpdate = false;
        viewOptionsObtainFrameOptions2.maskColor = viewOptionsObtainFrameOptions.maskColor;
        adaWebViewParentObtainWebviewParent.mNeedOrientationUpdate = true;
        viewOptionsObtainFrameOptions.checkValueIsPercentage("left", -1, -1, false, true);
        viewOptionsObtainFrameOptions.checkValueIsPercentage("top", -1, -1, false, true);
        viewOptionsObtainFrameOptions.checkValueIsPercentage("width", -1, -1, false, true);
        viewOptionsObtainFrameOptions.checkValueIsPercentage("height", -1, -1, false, true);
        if (a(i4, i5, i6, i7, aVar.obtainFrameOptions().width, aVar.obtainFrameOptions().height)) {
            Logger.d("winmgr", "createWindow use LayoutParams.MATCH_PARENT !");
            bVar.addFrameItem(bVar.obtainWebviewParent(), new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        bVar.addFrameItem(bVar.obtainWebviewParent(), AdaFrameItem.LayoutParamsUtil.createLayoutParams(i4, i5, i6, i7));
        if (z) {
            bVar.a(i2, i3);
            return;
        }
        int i8 = i4 + i6;
        if (i8 > i2 || i5 + i7 > i3) {
            StringBuilder sb = new StringBuilder("updateLayoutParams allW=");
            sb.append(i8);
            sb.append(";pdrW=");
            sb.append(i2);
            sb.append(";pdrH=");
            sb.append(i3);
            sb.append(";allH=");
            int i9 = i5 + i7;
            sb.append(i9);
            Logger.d("winmgr", sb.toString());
            bVar.a(Math.max(i8, i2), Math.max(i9, i3));
        }
    }

    private boolean a(int i2, String str, String str2, boolean z) {
        return (TextUtils.isEmpty(str2) || !str2.startsWith("id:") || PdrUtil.isEmpty(str)) ? i2 == 4 ? !TextUtils.isEmpty(str2) && str2.equals("second") : i2 == 5 && z : str2.substring(3).equals(str);
    }

    private void a(AdaFrameItem adaFrameItem, IApp iApp) throws NumberFormatException {
        int statusHeight;
        int iStringToColor;
        ViewOptions viewOptionsObtainFrameOptions = adaFrameItem.obtainFrameOptions();
        if (viewOptionsObtainFrameOptions.isStatusbar) {
            if ((PdrUtil.isEmpty(viewOptionsObtainFrameOptions.mStatusbarColor) || iApp.obtainStatusBarMgr().isImmersive) && -1 != (statusHeight = DeviceInfo.getStatusHeight(adaFrameItem.getContext()))) {
                int iHashCode = adaFrameItem.hashCode();
                int statusBarDefaultColor = iApp.obtainStatusBarMgr().getStatusBarDefaultColor();
                if (!PdrUtil.isEmpty(viewOptionsObtainFrameOptions.mStatusbarColor)) {
                    try {
                        iStringToColor = Color.parseColor(viewOptionsObtainFrameOptions.mStatusbarColor);
                    } catch (Exception unused) {
                        iStringToColor = PdrUtil.stringToColor(viewOptionsObtainFrameOptions.mStatusbarColor);
                    }
                    if (PdrUtil.checkStatusbarColor(iStringToColor)) {
                        statusBarDefaultColor = iStringToColor;
                    }
                }
                ViewGroup viewGroup = (ViewGroup) adaFrameItem.obtainMainView();
                if (viewGroup.findViewById(iHashCode) == null && viewOptionsObtainFrameOptions.height != 0) {
                    StatusBarView statusBarView = new StatusBarView(adaFrameItem.getContext());
                    statusBarView.setStatusBarHeight(statusHeight);
                    statusBarView.setBackgroundColor(statusBarDefaultColor);
                    statusBarView.setId(iHashCode);
                    ViewGroup viewGroup2 = (ViewGroup) ((AdaFrameView) adaFrameItem).obtainWebviewParent().obtainMainView();
                    if (viewOptionsObtainFrameOptions.isStatusbarDodifyHeight) {
                        viewGroup.getLayoutParams().height = viewOptionsObtainFrameOptions.height + DeviceInfo.sStatusBarHeight;
                        viewGroup.addView(statusBarView);
                    } else {
                        viewGroup.addView(statusBarView);
                    }
                    JSONObject jSONObject = viewOptionsObtainFrameOptions.titleNView;
                    if (jSONObject == null || !TitleNViewUtil.isTitleTypeForDef(jSONObject)) {
                        viewGroup2.post(new c(adaFrameItem));
                    }
                }
            }
        }
    }

    private io.dcloud.common.core.ui.b a() {
        io.dcloud.common.core.ui.a aVarB = b();
        if (aVarB != null) {
            return aVarB.h();
        }
        return null;
    }

    public void a(io.dcloud.common.core.ui.b bVar) {
        bVar.a(io.dcloud.common.core.ui.b.B);
        bVar.p();
        bVar.k.b(bVar);
        if (bVar.e()) {
            processEvent(IMgr.MgrType.WindowMgr, 28, bVar.b);
            bVar.b = null;
        }
        bVar.r();
        bVar.i();
        bVar.i = false;
        bVar.h = false;
        bVar.inStack = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x006d A[PHI: r1
  0x006d: PHI (r1v17 java.lang.String) = 
  (r1v16 java.lang.String)
  (r1v16 java.lang.String)
  (r1v16 java.lang.String)
  (r1v18 java.lang.String)
  (r1v18 java.lang.String)
 binds: [B:19:0x0089, B:21:0x008f, B:26:0x00a0, B:8:0x0053, B:13:0x0065] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.core.ui.b r10, java.lang.Object[] r11) throws org.json.JSONException, android.content.res.Resources.NotFoundException, java.lang.ClassNotFoundException {
        /*
            r9 = this;
            io.dcloud.common.DHInterface.IApp r0 = r10.obtainApp()
            io.dcloud.common.util.AppStatusBarManager r0 = r0.obtainStatusBarMgr()
            boolean r0 = r0.isImmersive
            int r1 = r10.getFrameType()
            r2 = 2
            if (r1 != r2) goto L2d
            io.dcloud.common.DHInterface.IApp r11 = r10.obtainApp()
            io.dcloud.common.DHInterface.IApp$ConfigProperty$ThridInfo r1 = io.dcloud.common.DHInterface.IApp.ConfigProperty.ThridInfo.TitleNViewJsonData
            org.json.JSONObject r11 = r11.obtainThridInfo(r1)
            io.dcloud.common.DHInterface.IWebview r1 = r10.obtainWebView()
            android.view.ViewGroup r1 = r1.obtainWindowView()
            int r1 = r1.hashCode()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            goto Ld7
        L2d:
            int r1 = r10.getFrameType()
            r3 = 4
            java.lang.String r4 = "navigationbar"
            java.lang.String r5 = "titleNView"
            r6 = 0
            if (r1 != r3) goto L70
            io.dcloud.common.DHInterface.IWebview r11 = r10.obtainWebView()
            android.view.ViewGroup r11 = r11.obtainWindowView()
            int r11 = r11.hashCode()
            java.lang.String r1 = java.lang.String.valueOf(r11)
            io.dcloud.common.DHInterface.IApp r11 = r10.obtainApp()
            io.dcloud.common.DHInterface.IApp$ConfigProperty$ThridInfo r2 = io.dcloud.common.DHInterface.IApp.ConfigProperty.ThridInfo.SecondWebviewJsonData
            org.json.JSONObject r11 = r11.obtainThridInfo(r2)
            if (r11 == 0) goto L6d
            boolean r2 = r11.has(r5)
            if (r2 == 0) goto L61
            org.json.JSONObject r11 = io.dcloud.common.util.JSONUtil.getJSONObject(r11, r5)
            goto Ld7
        L61:
            boolean r2 = r11.has(r4)
            if (r2 == 0) goto L6d
            org.json.JSONObject r11 = io.dcloud.common.util.JSONUtil.getJSONObject(r11, r4)
            goto Ld7
        L6d:
            r11 = r6
            goto Ld7
        L70:
            int r1 = r10.getFrameType()
            r3 = 5
            r7 = 1
            if (r1 != r3) goto La7
            io.dcloud.common.DHInterface.IWebview r1 = r10.obtainWebView()
            android.view.ViewGroup r1 = r1.obtainWindowView()
            int r1 = r1.hashCode()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r11.length
            if (r2 <= r7) goto L6d
            r11 = r11[r7]
            org.json.JSONObject r11 = (org.json.JSONObject) r11
            if (r11 == 0) goto L6d
            boolean r2 = r11.has(r5)
            if (r2 == 0) goto L9c
            org.json.JSONObject r11 = io.dcloud.common.util.JSONUtil.getJSONObject(r11, r5)
            goto Ld7
        L9c:
            boolean r2 = r11.has(r4)
            if (r2 == 0) goto L6d
            org.json.JSONObject r11 = io.dcloud.common.util.JSONUtil.getJSONObject(r11, r4)
            goto Ld7
        La7:
            int r1 = r11.length
            if (r1 <= r7) goto Ld5
            r1 = r11[r7]
            org.json.JSONObject r1 = (org.json.JSONObject) r1
            if (r1 == 0) goto Lc6
            boolean r3 = r1.has(r5)
            if (r3 == 0) goto Lbb
            org.json.JSONObject r1 = io.dcloud.common.util.JSONUtil.getJSONObject(r1, r5)
            goto Lc7
        Lbb:
            boolean r3 = r1.has(r4)
            if (r3 == 0) goto Lc6
            org.json.JSONObject r1 = io.dcloud.common.util.JSONUtil.getJSONObject(r1, r4)
            goto Lc7
        Lc6:
            r1 = r6
        Lc7:
            int r3 = r11.length
            if (r3 <= r2) goto Ld2
            r11 = r11[r2]
            java.lang.String r11 = (java.lang.String) r11
            r8 = r1
            r1 = r11
            r11 = r8
            goto Ld7
        Ld2:
            r11 = r1
            r1 = r6
            goto Ld7
        Ld5:
            r11 = r6
            r1 = r11
        Ld7:
            r9.a(r10, r0, r11, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(io.dcloud.common.core.ui.b, java.lang.Object[]):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.core.ui.b r17, boolean r18, org.json.JSONObject r19, java.lang.String r20) throws org.json.JSONException, android.content.res.Resources.NotFoundException, java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instructions count: 452
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(io.dcloud.common.core.ui.b, boolean, org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.core.ui.b r34, io.dcloud.common.core.ui.b r35) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 678
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(io.dcloud.common.core.ui.b, io.dcloud.common.core.ui.b):void");
    }
}
