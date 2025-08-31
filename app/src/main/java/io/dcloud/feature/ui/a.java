package io.dcloud.feature.ui;

import android.content.SharedPreferences;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.performance.WXInstanceApm;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class a implements ISysEventListener {
    AbsMgr d;
    IApp f;
    IActivityHandler g;
    SharedPreferences k;
    private c l;
    IWebview a = null;
    List b = null;
    List c = new ArrayList(1);
    HashMap e = new HashMap();
    boolean h = false;
    boolean i = false;
    boolean j = false;
    private List m = new ArrayList();
    boolean n = false;
    ArrayList o = null;
    private C0045a p = new C0045a();
    StringBuffer q = new StringBuffer();
    HashMap r = new HashMap();

    /* renamed from: io.dcloud.feature.ui.a$a, reason: collision with other inner class name */
    class C0045a implements Comparator {
        C0045a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            int i = cVar.E - cVar2.E;
            return i == 0 ? cVar.u > cVar2.u ? 1 : -1 : i;
        }
    }

    a(AbsMgr absMgr, IApp iApp) {
        this.f = null;
        this.g = null;
        Logger.e("IAN", "new AppWidgetMgr   " + System.currentTimeMillis() + "appid==" + iApp.obtainAppId());
        this.d = absMgr;
        this.f = iApp;
        this.k = DCLoudApplicationImpl.self().getContext().getApplicationContext().getSharedPreferences("pdr", 0);
        IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(iApp.getActivity());
        if (iActivityHandler != null) {
            this.g = iActivityHandler;
        }
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyDown);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyLongPress);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onWebAppSrcUpZip);
    }

    private void h() {
        for (c cVar : this.c) {
            if (cVar.D) {
                cVar.y.obtainWebView().loadUrl(cVar.z);
                cVar.D = false;
            }
        }
    }

    void a(String str, b bVar) {
        this.e.put(str, bVar);
    }

    public void b(IFrameView iFrameView) {
        this.d.processEvent(IMgr.MgrType.WindowMgr, 8, iFrameView);
    }

    boolean c(String str) {
        return false;
    }

    c d() {
        return a(3);
    }

    void e(c cVar) {
        if (this.c.contains(cVar)) {
            return;
        }
        this.c.add(cVar);
    }

    void f(c cVar) {
        if (cVar.C) {
            return;
        }
        Logger.d("AppWidgetMgr.showMaskLayer " + cVar.z);
        if (this.o == null) {
            this.o = new ArrayList();
        }
        cVar.C = true;
        this.o.add(cVar);
        this.d.processEvent(IMgr.MgrType.WindowMgr, 29, cVar.y);
    }

    void g(c cVar) {
        Logger.d(Logger.MAP_TAG, "sortNWindowByZIndex beign");
        Collections.sort(this.c, this.p);
        Collections.sort(this.b, this.p);
        this.d.processEvent(IMgr.MgrType.WindowMgr, 26, cVar.y.obtainWebAppRootView());
    }

    void i() throws JSONException, NumberFormatException {
        IFrameView iFrameView = (IFrameView) this.d.processEvent(IMgr.MgrType.WindowMgr, 44, this.f);
        int frameType = iFrameView.getFrameType();
        if (frameType != 2 && frameType != 4 && frameType != 5) {
            iFrameView.obtainApp().setNeedRefreshApp(true);
            return;
        }
        iFrameView.obtainApp().setNeedRefreshApp(false);
        int size = this.c.size();
        JSONArray jSONArray = new JSONArray();
        for (int i = size - 1; i >= 0; i--) {
            c cVar = (c) this.c.get(i);
            int frameType2 = cVar.y.getFrameType();
            if (frameType2 == 2) {
                a(cVar.y.obtainApp(), cVar.y.obtainWebView());
            } else if (frameType2 == 4 || frameType2 == 5) {
                cVar.a(cVar, true);
            } else {
                cVar.b(cVar.y.obtainWebView(), jSONArray, cVar);
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        IFrameView iFrameView;
        boolean z = true;
        if (BaseInfo.sDoingAnimation) {
            return true;
        }
        ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onKeyUp;
        boolean zA = false;
        if (sysEventType != sysEventType2 && sysEventType != ISysEventListener.SysEventType.onKeyDown && sysEventType != ISysEventListener.SysEventType.onKeyLongPress) {
            if (sysEventType != ISysEventListener.SysEventType.onStop) {
                if (sysEventType == ISysEventListener.SysEventType.onWebAppSrcUpZip) {
                    f();
                    h();
                }
                return false;
            }
            for (c cVar : this.c) {
                cVar.d().onDispose();
                cVar.d().dispose();
            }
            return true;
        }
        Integer num = (Integer) ((Object[]) obj)[0];
        int iIntValue = num.intValue();
        if (sysEventType == sysEventType2) {
            if (iIntValue == 4) {
                c cVarD = d();
                if (cVarD != null) {
                    iFrameView = cVarD.y;
                    boolean zB = cVarD.b("back", StringUtil.format("{keyType:'%s',keyCode:%d}", "back", num), false);
                    if (zB || this.c.size() != 1) {
                        zA = zB;
                    } else {
                        this.d.processEvent(IMgr.MgrType.WindowMgr, 20, this.f);
                        zA = true;
                    }
                } else {
                    iFrameView = (IFrameView) this.d.processEvent(IMgr.MgrType.WindowMgr, 43, this.f);
                }
                if (!zA && iFrameView != null) {
                    IWebview iWebviewObtainWebView = iFrameView.obtainWebView();
                    if (iWebviewObtainWebView.canGoBack()) {
                        iWebviewObtainWebView.goBackOrForward(-1);
                        zA = true;
                    } else if (cVarD != null) {
                        b(cVarD);
                        cVarD.e();
                    }
                    if (!zA) {
                        Collections.sort(this.c, this.p);
                        Collections.sort(this.b, this.p);
                        AbsMgr absMgr = this.d;
                        IMgr.MgrType mgrType = IMgr.MgrType.WindowMgr;
                        absMgr.processEvent(mgrType, 26, this.f.obtainWebAppRootView());
                        AnimOptions animOptions = ((AdaFrameItem) iFrameView).getAnimOptions();
                        if (PdrUtil.isEmpty(WXInstanceApm.VALUE_ERROR_CODE_DEFAULT)) {
                            animOptions.duration_close = animOptions.duration_show;
                        } else {
                            animOptions.duration_close = PdrUtil.parseInt(WXInstanceApm.VALUE_ERROR_CODE_DEFAULT, animOptions.duration_close);
                        }
                        animOptions.mOption = (byte) 1;
                        animOptions.setCloseAnimType(PdrUtil.isEmpty("none") ? "auto" : "none");
                        this.d.processEvent(mgrType, 2, iFrameView);
                        zA = true;
                    }
                }
                if (!zA) {
                    zA = a("back", sysEventType, "back", iIntValue, false);
                }
            } else if (iIntValue == 82) {
                zA = a(AbsoluteConst.EVENTS_MENU, sysEventType, AbsoluteConst.EVENTS_MENU, iIntValue, false);
            } else if (iIntValue == 24) {
                zA = a(AbsoluteConst.EVENTS_VOLUME_UP, sysEventType, AbsoluteConst.EVENTS_VOLUME_UP, iIntValue, false);
            } else if (iIntValue == 25) {
                zA = a(AbsoluteConst.EVENTS_VOLUME_DOWN, sysEventType, AbsoluteConst.EVENTS_VOLUME_DOWN, iIntValue, false);
            } else if (iIntValue == 84) {
                zA = a("search", sysEventType, "search", iIntValue, false);
            }
        }
        String str = sysEventType == sysEventType2 ? AbsoluteConst.EVENTS_KEY_UP : sysEventType == ISysEventListener.SysEventType.onKeyDown ? AbsoluteConst.EVENTS_KEY_DOWN : sysEventType == ISysEventListener.SysEventType.onKeyLongPress ? AbsoluteConst.EVENTS_LONG_PRESSED : null;
        if (DeviceInfo.isVolumeButtonEnabled || (iIntValue != 24 && iIntValue != 25)) {
            z = zA;
        }
        return z | a(str, sysEventType, str, iIntValue, false);
    }

    b a(String str) {
        return (b) this.e.get(str);
    }

    boolean b(IWebview iWebview) {
        return this.a == iWebview;
    }

    public void c(IFrameView iFrameView) {
        this.d.processEvent(IMgr.MgrType.WindowMgr, 22, iFrameView);
    }

    void d(c cVar) {
        if (cVar == null) {
            return;
        }
        Logger.d("AppWidgetMgr.hideMaskLayer " + cVar.z);
        this.d.processEvent(IMgr.MgrType.WindowMgr, 30, cVar.y);
        cVar.C = false;
        ArrayList arrayList = this.o;
        if (arrayList != null) {
            arrayList.remove(cVar);
        }
    }

    void a() {
        this.c.clear();
    }

    synchronized String b() {
        StringBuffer stringBuffer;
        stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
        boolean z = false;
        for (c cVar : this.c) {
            if (!a(cVar.r()) && !cVar.p()) {
                stringBuffer.append(cVar.h()).append(",");
                z = true;
            }
        }
        if (z) {
            stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    int c(c cVar) {
        int size = this.b.size();
        for (int i = size - 1; i >= 0; i--) {
            if (((c) this.b.get(i)).E <= cVar.E) {
                return i + 1;
            }
        }
        return size;
    }

    synchronized String e() {
        StringBuffer stringBuffer;
        stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
        boolean z = false;
        for (c cVar : this.c) {
            if (cVar.o() && !cVar.p()) {
                stringBuffer.append(cVar.h()).append(",");
                z = true;
            }
        }
        if (z) {
            stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    void a(String str, c cVar, int i) {
        if (this.b == null) {
            this.b = Collections.synchronizedList(new ArrayList(1));
        }
        if (!this.b.contains(cVar)) {
            this.b.add(i, cVar);
        }
        Collections.sort(this.c, this.p);
        Collections.sort(this.b, this.p);
    }

    void g() {
        this.a = null;
    }

    private void f() {
        d(this.l);
    }

    void c(IWebview iWebview) {
        this.a = iWebview;
    }

    c c() {
        return a(2);
    }

    boolean a(IWebview iWebview) {
        return iWebview.obtainFrameView().getFrameType() == 6 && !b(iWebview);
    }

    c a(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = (c) this.c.get(i2);
            if (cVar.y.getFrameType() == i) {
                return cVar;
            }
        }
        return null;
    }

    void b(c cVar) {
        this.b.remove(cVar);
        this.c.remove(cVar);
    }

    public boolean b(String str) {
        boolean z = true;
        if (this.g != null && str != null) {
            if (!PdrUtil.isNetPath(str.toLowerCase(Locale.getDefault())) && c(this.f.obtainAppId()) && BaseInfo.isWap2AppAppid(this.f.obtainAppId())) {
                if (str.startsWith(DeviceInfo.FILE_PROTOCOL)) {
                    str = str.substring(7);
                }
                str = PdrUtil.stripQuery(PdrUtil.stripAnchor(str));
                String strConvert2RelPath = this.f.convert2RelPath(str);
                if (strConvert2RelPath.startsWith(BaseInfo.REL_PRIVATE_WWW_DIR)) {
                    strConvert2RelPath = strConvert2RelPath.substring(5);
                }
                if (!BaseInfo.containsInTemplate(this.f, strConvert2RelPath) && !new File(str).exists()) {
                    z = false;
                }
            }
            Logger.d("hasFile = " + z + ";filePath=" + str);
        }
        return z;
    }

    c a(IFrameView iFrameView) {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            c cVar = (c) this.c.get(i);
            if (cVar.y.equals(iFrameView)) {
                return cVar;
            }
        }
        return null;
    }

    synchronized c a(String str, String str2, String str3) {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            c cVar = (c) this.c.get(i);
            String strValueOf = String.valueOf(cVar.y.hashCode());
            String strM = cVar.m();
            if ((PdrUtil.isEquals(str, strValueOf) || PdrUtil.isEquals(str2, cVar.e) || PdrUtil.isEquals(str3, strM)) && !a(cVar.r())) {
                return cVar;
            }
        }
        return null;
    }

    private boolean a(String str, ISysEventListener.SysEventType sysEventType, String str2, int i, boolean z) {
        String str3 = StringUtil.format("{keyType:'%s',keyCode:%d}", str2, Integer.valueOf(i));
        List list = this.b;
        if (list == null) {
            return false;
        }
        int size = list.size();
        Logger.d("AppWidgetMgr", "syncExecBaseEvent windowCount = " + size);
        int i2 = size - 1;
        for (int i3 = i2; i3 >= 0; i3--) {
            c cVar = (c) this.b.get(i3);
            if (cVar != null && cVar.a == null && ((cVar.p() || cVar.F) && cVar.y.getFrameType() != 6)) {
                if (i2 == i3 && "back".equals(str) && cVar.c(str, str3, z)) {
                    return true;
                }
                if (cVar.b(str, str3, z) || (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT && str2 != null && (cVar.b(str2) || (sysEventType == ISysEventListener.SysEventType.onKeyDown && ((cVar.b("back") && i == 4) || ((cVar.b(AbsoluteConst.EVENTS_MENU) && i == 82) || ((cVar.b(AbsoluteConst.EVENTS_VOLUME_DOWN) && i == 25) || ((cVar.b(AbsoluteConst.EVENTS_VOLUME_UP) && i == 24) || (cVar.b("search") && i == 84))))))))) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean a(c cVar) {
        if (!c(this.f.obtainAppId())) {
            cVar.D = false;
        }
        if (cVar.D && b(cVar.z)) {
            cVar.r().loadUrl(cVar.z);
            cVar.D = false;
        }
        return cVar.D;
    }

    void a(IApp iApp, IWebview iWebview) {
        if (!iWebview.getWebviewProperty("plusrequire").equals("none")) {
            iWebview.appendPreloadJsFile(iApp.convert2AbsFullPath(null, "_www/__wap2app.js"));
            iWebview.appendPreloadJsFile(iApp.convert2AbsFullPath(null, "_www/__wap2appconfig.js"));
        }
        iWebview.setPreloadJsFile(iApp.convert2AbsFullPath(iWebview.obtainFullUrl(), "_www/server_index_append.js"), true);
        String strConvert2AbsFullPath = iApp.convert2AbsFullPath(null, "_www/server_index_append.css");
        if (new File(strConvert2AbsFullPath).exists()) {
            iWebview.setCssFile(strConvert2AbsFullPath, null);
            return;
        }
        String strConvert2AbsFullPath2 = iApp.convert2AbsFullPath(null, "_www/__wap2app.css");
        if (new File(strConvert2AbsFullPath2).exists()) {
            iWebview.setCssFile(strConvert2AbsFullPath2, null);
        }
    }
}
