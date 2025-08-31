package io.dcloud.feature.ui;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.webview.WebResUtil;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.nineoldandroids.animation.Animator;
import io.dcloud.nineoldandroids.animation.ValueAnimator;
import io.dcloud.nineoldandroids.view.ViewHelper;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class e {
    private static HashMap e;
    AbsMgr a;
    HashMap b = new HashMap(1);
    final boolean c = false;
    String d;

    class a implements MessageHandler.IMessages {
        final /* synthetic */ io.dcloud.feature.ui.a a;
        final /* synthetic */ io.dcloud.feature.ui.c b;

        a(io.dcloud.feature.ui.a aVar, io.dcloud.feature.ui.c cVar) {
            this.a = aVar;
            this.b = cVar;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            this.a.g(this.b);
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ View a;

        b(View view) {
            this.a = view;
        }

        @Override // io.dcloud.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!(this.a.getLayoutParams() instanceof AbsoluteLayout.LayoutParams)) {
                if (this.a.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                    ViewHelper.setX(this.a, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                    return;
                }
                return;
            }
            AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) this.a.getLayoutParams();
            layoutParams.height = this.a.getHeight();
            layoutParams.width = this.a.getWidth();
            try {
                ViewHelper.setX(this.a, ((Integer) valueAnimator.getAnimatedValue()).intValue());
            } catch (Exception unused) {
                ViewHelper.setX(this.a, ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
            this.a.requestLayout();
        }
    }

    class c implements Animator.AnimatorListener {
        final /* synthetic */ View a;
        final /* synthetic */ io.dcloud.feature.ui.c b;
        final /* synthetic */ IWebview c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        c(View view, io.dcloud.feature.ui.c cVar, IWebview iWebview, String str, String str2) {
            this.a = view;
            this.b = cVar;
            this.c = iWebview;
            this.d = str;
            this.e = str2;
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) throws JSONException {
            io.dcloud.feature.ui.c cVar;
            if (this.a == null || (cVar = this.b) == null || cVar.r() == null) {
                return;
            }
            int iA = e.this.a(this.a);
            int width = this.a.getWidth();
            if (iA >= PlatformUtil.SCREEN_WIDTH(this.a.getContext()) || iA <= (-width)) {
                this.b.y.popFromViewStack();
            }
            if (this.c != null && !TextUtils.isEmpty(this.d)) {
                String strM = this.b.m();
                if (TextUtils.isEmpty(strM)) {
                    strM = "";
                }
                Deprecated_JSUtil.execCallback(this.c, this.d, StringUtil.format("{\"id\":\"%s\",\"target\":%s}", strM, this.b.h()), JSUtil.OK, true, true);
            }
            if (TextUtils.isEmpty(this.e)) {
                return;
            }
            if ("hide".equals(this.e)) {
                io.dcloud.feature.ui.c cVar2 = this.b;
                cVar2.a(cVar2.r(), "hide", JSONUtil.createJSONArray("[null,null,null]"));
            } else if (AbsoluteConst.EVENTS_CLOSE.equals(this.e)) {
                io.dcloud.feature.ui.c cVar3 = this.b;
                cVar3.a(cVar3.r(), AbsoluteConst.EVENTS_CLOSE, JSONUtil.createJSONArray("[null,null,null]"));
            }
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    static /* synthetic */ class d {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[EnumC0047e.values().length];
            a = iArr;
            try {
                iArr[EnumC0047e.findWindowByName.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[EnumC0047e.getTopWebview.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[EnumC0047e.prefetchURL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[EnumC0047e.prefetchURLs.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[EnumC0047e.enumWindow.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[EnumC0047e.getWapLaunchWebview.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[EnumC0047e.getLaunchWebview.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[EnumC0047e.getSecondWebview.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[EnumC0047e.currentWebview.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[EnumC0047e.createView.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[EnumC0047e.setcallbackid.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[EnumC0047e.debug.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[EnumC0047e.defaultHardwareAccelerated.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                a[EnumC0047e.startAnimation.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                a[EnumC0047e.getDisplayWebview.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                a[EnumC0047e.__callNativeModuleSync.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                a[EnumC0047e.postMessageToUniNView.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* renamed from: io.dcloud.feature.ui.e$e, reason: collision with other inner class name */
    private enum EnumC0047e {
        findWindowByName,
        enumWindow,
        getLaunchWebview,
        getWapLaunchWebview,
        currentWebview,
        getTopWebview,
        createView,
        setcallbackid,
        debug,
        setLogs,
        isLogs,
        defaultHardwareAccelerated,
        startAnimation,
        getSecondWebview,
        getDisplayWebview,
        updateAppFrameViews,
        prefetchURL,
        prefetchURLs,
        postMessageToUniNView,
        __callNativeModuleSync
    }

    e(AbsMgr absMgr, String str) {
        this.a = null;
        this.a = absMgr;
        this.d = str;
        a();
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str.startsWith("./") ? str.substring(2) : str.startsWith("../") ? str.substring(3) : str.startsWith(".../") ? str.substring(4) : str;
    }

    public synchronized String b(IWebview iWebview, String str, JSONArray jSONArray) {
        return a(iWebview, str, jSONArray);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:7|8|(3:384|10|(1:12)(12:13|(2:15|(1:17))(2:18|(2:20|(1:22))(2:23|(1:27)))|28|378|60|63|64|365|(5:66|(2:68|(1:70)(3:185|(2:187|184)|192))(3:188|(2:190|184)|192)|193|364|358)(3:197|198|(6:200|(1:202)(2:203|(4:205|208|364|358)(1:206))|207|208|364|358)(5:210|211|(2:213|(15:215|(1:217)(1:218)|219|(2:222|220)|392|223|374|224|225|226|380|227|228|(1:230)|243)(3:235|236|394))(7:237|238|239|240|373|(1:242)(3:246|247|(15:249|(1:251)|252|253|(2:255|(1:257))(1:259)|258|260|(1:262)|263|(1:265)(2:266|(1:268))|269|270|371|271|272)(3:276|277|(3:279|280|(4:282|(1:284)|285|(1:287)))(4:289|290|389|(2:292|(13:294|295|359|296|297|(1:301)|302|303|(1:305)(1:306)|(2:308|309)(1:310)|311|312|(4:314|315|316|(3:318|(1:320)(1:321)|322)(4:323|(5:325|326|376|327|328)(2:331|(3:333|(1:335)|336)(5:337|338|361|339|340))|357|393))))(2:345|(3:347|236|394)))))|243)|349|395))|32|357|393))(1:33)|(1:37)|387|38|(1:40)(1:41)|42|(1:44)(1:45)|46|(1:48)(1:49)|50|(2:52|53)(2:54|(2:57|58))|59|378|60|63|64|365|(0)(0)|32|357|393) */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x04cb, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x04cc, code lost:
    
        r10 = r30;
        r1 = r0;
        r14 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x08e3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x08e4, code lost:
    
        r10 = r30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x08e8, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x013b, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:197:0x04d1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0146 A[Catch: Exception -> 0x00a4, TRY_ENTER, TryCatch #13 {Exception -> 0x00a4, blocks: (B:10:0x0046, B:35:0x00af, B:37:0x00b8, B:52:0x0110, B:66:0x0146, B:70:0x0157, B:72:0x015c, B:74:0x016d, B:80:0x0185, B:82:0x018f, B:85:0x01b9, B:90:0x01c5, B:93:0x01ef, B:75:0x0176, B:77:0x017d, B:79:0x0181, B:98:0x01fc, B:99:0x0202, B:101:0x022e, B:103:0x0234, B:108:0x0245, B:111:0x0272, B:123:0x029f, B:130:0x02dc, B:133:0x0309, B:145:0x0335, B:151:0x0386, B:153:0x0398, B:138:0x031d, B:140:0x0325, B:141:0x032a, B:143:0x032e, B:116:0x0287, B:118:0x028f, B:119:0x0294, B:121:0x0298, B:154:0x03af, B:155:0x03bb, B:165:0x03ee, B:166:0x03f3, B:168:0x03ff, B:169:0x040a, B:170:0x043a, B:172:0x0444, B:173:0x0449, B:174:0x0469, B:176:0x046f, B:177:0x0474, B:179:0x047a, B:180:0x047f, B:182:0x0485, B:183:0x048a, B:185:0x0490, B:187:0x04af, B:188:0x04b4, B:190:0x04c0, B:200:0x04da, B:202:0x04e3, B:203:0x04f2, B:205:0x04f9, B:206:0x04fe, B:213:0x0537, B:215:0x053f, B:217:0x0550, B:220:0x055b, B:222:0x0561, B:223:0x0598, B:225:0x05ae, B:228:0x05c5, B:230:0x05f6, B:235:0x0600, B:58:0x0130, B:13:0x0052, B:15:0x005d, B:17:0x0061, B:18:0x006c, B:20:0x0077, B:22:0x007b, B:23:0x0086, B:25:0x0091, B:27:0x0095, B:156:0x03c5, B:158:0x03d3, B:159:0x03db, B:161:0x03e3), top: B:384:0x0046, inners: #2 }] */
    /* JADX WARN: Type inference failed for: r14v19, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v2, types: [org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r14v39 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v40 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(io.dcloud.common.DHInterface.IWebview r29, java.lang.String r30, org.json.JSONArray r31) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 2346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.e.a(io.dcloud.common.DHInterface.IWebview, java.lang.String, org.json.JSONArray):java.lang.String");
    }

    public void b(String str, io.dcloud.feature.ui.a aVar, IFrameView iFrameView) {
        String strValueOf = String.valueOf(iFrameView.hashCode());
        IWebview iWebviewObtainWebView = iFrameView.obtainWebView();
        String strObtainUrl = iWebviewObtainWebView.obtainUrl();
        AdaFrameView adaFrameView = (AdaFrameView) iFrameView;
        JSONObject jSONObject = adaFrameView.obtainFrameOptions() != null ? adaFrameView.obtainFrameOptions().mJsonViewOption : null;
        String strObtainFrameId = iWebviewObtainWebView.obtainFrameId();
        String str2 = !PdrUtil.isEmpty(strObtainFrameId) ? strObtainFrameId : iFrameView.getFrameType() == 2 ? str : strObtainUrl;
        JSONObject jSONObject2 = jSONObject;
        io.dcloud.feature.ui.c cVar = new io.dcloud.feature.ui.c(aVar, strObtainUrl, str2, strValueOf, jSONObject2);
        cVar.a(iFrameView.getContext(), aVar, iFrameView.obtainWebView(), strValueOf, jSONObject2);
        cVar.F = iFrameView.getFrameType() != 2 || adaFrameView.obtainMainView().getVisibility() == 0;
        cVar.I = true;
        adaFrameView.addFrameViewListener(cVar);
        cVar.a(iFrameView, str2);
        aVar.e(cVar);
        aVar.a(str, cVar, 0);
        MessageHandler.sendMessage(new a(aVar, cVar), null);
    }

    public void b(String str) {
        if (PdrUtil.isEmpty(str)) {
            Iterator it = this.b.values().iterator();
            while (it.hasNext()) {
                ((io.dcloud.feature.ui.a) it.next()).a();
            }
            this.b.clear();
            return;
        }
        io.dcloud.feature.ui.a aVar = (io.dcloud.feature.ui.a) this.b.get(str);
        if (aVar != null) {
            Logger.d(Logger.MAIN_TAG, "UIWidgetMgr.dispose pAppid=" + str);
            aVar.a();
        }
        this.b.remove(str);
    }

    private void a(String str, io.dcloud.feature.ui.a aVar, IFrameView iFrameView) {
        IWebview iWebviewObtainWebView = iFrameView.obtainWebView();
        String strValueOf = String.valueOf(iWebviewObtainWebView.obtainFrameId());
        String strObtainUrl = iWebviewObtainWebView.obtainUrl();
        String strObtainFrameId = iWebviewObtainWebView.obtainFrameId();
        String str2 = !PdrUtil.isEmpty(strObtainFrameId) ? strObtainFrameId : strObtainUrl;
        io.dcloud.feature.ui.c cVar = new io.dcloud.feature.ui.c(aVar, strObtainUrl, str2, strValueOf, null);
        cVar.a(iFrameView.getContext(), aVar, iWebviewObtainWebView, strValueOf, null);
        cVar.F = false;
        cVar.I = false;
        cVar.a(true);
        iFrameView.addFrameViewListener(cVar);
        cVar.a(iFrameView, str2);
        aVar.e(cVar);
        aVar.a(str, cVar, 0);
    }

    private io.dcloud.feature.ui.c a(io.dcloud.feature.ui.a aVar, IWebview iWebview, JSONArray jSONArray, IApp iApp, String str, boolean z) throws NumberFormatException {
        String str2;
        JSONObject jSONObject;
        io.dcloud.feature.ui.c cVarA = aVar.a(iWebview.obtainFrameView());
        String strOptString = jSONArray.optString(0);
        Log.e("UIWidgetMgr", "new -- JSNWindow=" + strOptString);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        String strOptString2 = jSONArray.optString(2);
        JSONObject jSONObjectOptJSONObject2 = jSONArray.optJSONObject(4);
        JSONArray jSONArrayOptJSONArray = jSONArray.optJSONArray(5);
        if (jSONObjectOptJSONObject == null) {
            jSONObject = new JSONObject("{}");
            str2 = "";
        } else {
            String string = JSONUtil.getString(jSONObjectOptJSONObject, "name");
            if (TextUtils.isEmpty(string)) {
                string = JSONUtil.getString(jSONObjectOptJSONObject, "webviewid");
            }
            str2 = string;
            jSONObject = jSONObjectOptJSONObject;
        }
        io.dcloud.feature.ui.c cVarA2 = a(aVar, iWebview, iApp, strOptString, str2, str, jSONObject, jSONObjectOptJSONObject2, jSONArrayOptJSONArray, z);
        if (cVarA != null) {
            cVarA.b(cVarA2);
        }
        if (strOptString2 != null) {
            cVarA2.b.put(iWebview.getWebviewANID(), strOptString2);
        }
        AnimOptions animOptions = ((AdaFrameItem) cVarA2.y).getAnimOptions();
        ViewOptions viewOptionsObtainFrameOptions = ((AdaFrameItem) cVarA2.y).obtainFrameOptions();
        cVarA2.K = viewOptionsObtainFrameOptions.hasBackground();
        animOptions.parseTransition(viewOptionsObtainFrameOptions.transition);
        animOptions.parseTransform(viewOptionsObtainFrameOptions.transform);
        return cVarA2;
    }

    private io.dcloud.feature.ui.c a(io.dcloud.feature.ui.a aVar, IWebview iWebview, IApp iApp, String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, JSONArray jSONArray, boolean z) throws NumberFormatException {
        String str4;
        io.dcloud.feature.ui.c cVarA;
        String str5;
        String str6;
        String str7;
        int i;
        int i2;
        String strOptString;
        String str8;
        String strConvert2LocalFullPath;
        String str9;
        String str10;
        Logger.e("createNWindow pUrl=" + str);
        boolean zOptBoolean = jSONObject.optBoolean("directPage", false);
        int iOptInt = jSONObject.optInt("winType", 0);
        String strConvert2WebviewFullPath = iApp.convert2WebviewFullPath(iOptInt == 0 ? iWebview.obtainFullUrl() : null, str);
        int i3 = zOptBoolean ? 5 : iOptInt;
        if (z) {
            str4 = null;
        } else {
            str4 = strConvert2WebviewFullPath;
            strConvert2WebviewFullPath = null;
        }
        iApp.obtainWebviewBaseUrl();
        a(iWebview, iApp, str4);
        String strObtainAppId = iApp.obtainAppId();
        boolean zIsEmpty = PdrUtil.isEmpty(str);
        if (i3 == 4) {
            cVarA = aVar.a(4);
        } else {
            cVarA = i3 == 5 ? aVar.a(5) : null;
        }
        if (cVarA == null) {
            str5 = strObtainAppId;
            str6 = strConvert2WebviewFullPath;
            str7 = str4;
            i = i3;
            i2 = 5;
            cVarA = new io.dcloud.feature.ui.c(aVar, str4, str2, str3, jSONObject);
        } else {
            str5 = strObtainAppId;
            str6 = strConvert2WebviewFullPath;
            str7 = str4;
            i = i3;
            i2 = 5;
        }
        io.dcloud.feature.ui.c cVar = cVarA;
        cVar.x = jSONObject2;
        AbsMgr absMgr = this.a;
        IMgr.MgrType mgrType = IMgr.MgrType.WindowMgr;
        Integer numValueOf = Integer.valueOf(i);
        IFrameView iFrameViewObtainFrameView = iWebview.obtainFrameView();
        Object[] objArr = new Object[i2];
        objArr[0] = numValueOf;
        objArr[1] = iApp;
        objArr[2] = new Object[]{str, jSONObject, str3};
        objArr[3] = iFrameViewObtainFrameView;
        objArr[4] = cVar;
        IFrameView iFrameView = (IFrameView) absMgr.processEvent(mgrType, 3, objArr);
        if (z) {
            iFrameView.obtainWebView().setOriginalUrl(str6);
        }
        if (jSONArray != null) {
            cVar.v = jSONArray;
            cVar.w = iWebview;
        }
        cVar.a(iFrameView, str2);
        IWebview iWebviewObtainWebView = cVar.y.obtainWebView();
        if (jSONObject.has("plusrequire")) {
            iWebviewObtainWebView.setWebviewProperty("plusrequire", jSONObject.optString("plusrequire"));
        }
        if (jSONObject.has("replacewebapi")) {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("replacewebapi");
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("geolocation")) {
                iWebviewObtainWebView.setWebviewProperty("geolocation", jSONObjectOptJSONObject.optString("geolocation"));
            }
        } else if (jSONObject.has("geolocation")) {
            iWebviewObtainWebView.setWebviewProperty("geolocation", jSONObject.optString("geolocation"));
        }
        if (jSONObject.has("injection")) {
            iWebviewObtainWebView.setWebviewProperty("injection", String.valueOf(jSONObject.optBoolean("injection")));
        }
        if (jSONObject.has(IApp.ConfigProperty.CONFIG_OVERRIDE_RESOURCE)) {
            iWebviewObtainWebView.setOverrideResourceRequest(jSONObject.optJSONArray(IApp.ConfigProperty.CONFIG_OVERRIDE_RESOURCE));
        }
        if (jSONObject.has(IApp.ConfigProperty.CONFIG_OVERRIDEURL)) {
            iWebviewObtainWebView.setOverrideUrlLoadingData(jSONObject.optJSONObject(IApp.ConfigProperty.CONFIG_OVERRIDEURL));
        }
        if (BaseInfo.isWap2AppAppid(str5) && (iWebviewObtainWebView.obtainFrameView().getFrameType() == 4 || iWebviewObtainWebView.obtainFrameView().getFrameType() == i2)) {
            if (iWebviewObtainWebView.getWebviewProperty("plusrequire").equals("none")) {
                str10 = null;
            } else {
                str10 = null;
                iWebviewObtainWebView.appendPreloadJsFile(iApp.convert2AbsFullPath(null, "_www/__wap2app.js"));
                iWebviewObtainWebView.appendPreloadJsFile(iApp.convert2AbsFullPath(null, "_www/__wap2appconfig.js"));
            }
            iWebviewObtainWebView.setPreloadJsFile(iApp.convert2AbsFullPath(iWebview.obtainFullUrl(), "_www/server_index_append.js"), true);
            String strConvert2AbsFullPath = iApp.convert2AbsFullPath(str10, "_www/server_index_append.css");
            if (new File(strConvert2AbsFullPath).exists()) {
                iWebviewObtainWebView.setCssFile(strConvert2AbsFullPath, str10);
            } else {
                String strConvert2AbsFullPath2 = iApp.convert2AbsFullPath(str10, "_www/__wap2app.css");
                if (new File(strConvert2AbsFullPath2).exists()) {
                    iWebviewObtainWebView.setCssFile(strConvert2AbsFullPath2, str10);
                }
            }
        }
        if (jSONObject.has("appendCss")) {
            strOptString = jSONObject.optString("appendCss");
        } else {
            strOptString = jSONObject.has("preloadcss") ? jSONObject.optString("preloadcss") : null;
        }
        if (TextUtils.isEmpty(strOptString)) {
            str8 = null;
        } else {
            str8 = null;
            iWebviewObtainWebView.setCssFile(null, strOptString);
        }
        if (jSONObject.has("appendJs")) {
            strConvert2LocalFullPath = iApp.convert2LocalFullPath(str8, jSONObject.optString("appendJs"));
        } else {
            strConvert2LocalFullPath = jSONObject.has("preloadjs") ? iApp.convert2LocalFullPath(str8, jSONObject.optString("preloadjs")) : str8;
        }
        if (!TextUtils.isEmpty(strConvert2LocalFullPath)) {
            iWebviewObtainWebView.appendPreloadJsFile(strConvert2LocalFullPath);
        }
        if (!zIsEmpty) {
            if (i == 6 && jSONObject.has(IApp.ConfigProperty.CONFIG_ADDITIONAL_HTTPHEADERS)) {
                str9 = str7;
                cVar.y.obtainWebView().setLoadURLHeads(str9, JSONUtil.toMap(jSONObject.optJSONObject(IApp.ConfigProperty.CONFIG_ADDITIONAL_HTTPHEADERS)));
            } else {
                str9 = str7;
            }
            cVar.y.obtainWebView().loadUrl(str9);
        }
        cVar.a(iWebview.getContext(), aVar, iFrameView.obtainWebView(), str3, jSONObject);
        iFrameView.obtainMainView().setVisibility(4);
        if (DeviceInfo.sDeviceSdkVer >= 11) {
            aVar.b(iFrameView);
        }
        aVar.e(cVar);
        cVar.a(jSONObject, false);
        Logger.d(Logger.VIEW_VISIBLE_TAG, str5 + " createNWindow webview_name=" + str2);
        return cVar;
    }

    private void a(IWebview iWebview, IApp iApp, String str) {
        if (!BaseInfo.isBase(iWebview.getContext()) || TextUtils.isEmpty(str)) {
            return;
        }
        String strObtainUrl = iWebview.obtainUrl();
        if (str.startsWith(DeviceInfo.HTTP_PROTOCOL) || strObtainUrl.startsWith(DeviceInfo.HTTP_PROTOCOL) || str.startsWith(DeviceInfo.HTTPS_PROTOCOL) || strObtainUrl.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
            return;
        }
        String originalUrl = WebResUtil.getOriginalUrl(strObtainUrl);
        String originalUrl2 = WebResUtil.getOriginalUrl(str);
        Log.i(AbsoluteConst.HBUILDER_TAG, StringUtil.format(AbsoluteConst.OPENLOG, c(WebResUtil.getHBuilderPrintUrl(iApp.convert2RelPath(originalUrl))), c(WebResUtil.getHBuilderPrintUrl(iApp.convert2RelPath(originalUrl2)))));
    }

    public static io.dcloud.feature.ui.b a(String str) throws IllegalAccessException, InstantiationException {
        if (!PdrUtil.isEmpty(str)) {
            try {
                Object objNewInstance = Class.forName((String) e.get(str.toLowerCase(Locale.ENGLISH))).newInstance();
                if (objNewInstance instanceof io.dcloud.feature.ui.b) {
                    return (io.dcloud.feature.ui.b) objNewInstance;
                }
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (InstantiationException e4) {
                e4.printStackTrace();
            }
        }
        return null;
    }

    private void a() {
        e = (HashMap) this.a.processEvent(IMgr.MgrType.FeatureMgr, 4, this.d);
    }

    private ValueAnimator a(View view, int i, int i2, String str, IWebview iWebview, String str2, io.dcloud.feature.ui.c cVar) {
        ValueAnimator valueAnimatorOfFloat;
        if (view.getLayoutParams() instanceof AbsoluteLayout.LayoutParams) {
            valueAnimatorOfFloat = ValueAnimator.ofInt(i, i2);
        } else {
            valueAnimatorOfFloat = view.getLayoutParams() instanceof FrameLayout.LayoutParams ? ValueAnimator.ofFloat(i, i2) : null;
        }
        valueAnimatorOfFloat.setDuration(200L);
        valueAnimatorOfFloat.addUpdateListener(new b(view));
        valueAnimatorOfFloat.addListener(new c(view, cVar, iWebview, str2, str));
        return valueAnimatorOfFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(View view) {
        if (view == null) {
            return 0;
        }
        if (!(view.getLayoutParams() instanceof AbsoluteLayout.LayoutParams) && !(view.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return 0;
        }
        float x = ViewHelper.getX(view);
        return (int) x;
    }
}
