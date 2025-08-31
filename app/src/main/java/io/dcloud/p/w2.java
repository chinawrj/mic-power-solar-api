package io.dcloud.p;

import android.content.Intent;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/* loaded from: classes3.dex */
public class w2 implements IEventCallback {
    static HashMap b = new HashMap(2);
    static w2 c = null;
    ArrayList a = new ArrayList();

    class a extends PermissionUtil.Request {
        final /* synthetic */ JSONArray a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String[] c;
        final /* synthetic */ JSONArray d;
        final /* synthetic */ JSONArray e;
        final /* synthetic */ JSONArray f;

        a(JSONArray jSONArray, IWebview iWebview, String[] strArr, JSONArray jSONArray2, JSONArray jSONArray3, JSONArray jSONArray4) {
            this.a = jSONArray;
            this.b = iWebview;
            this.c = strArr;
            this.d = jSONArray2;
            this.e = jSONArray3;
            this.f = jSONArray4;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) throws JSONException {
            String strConvertNativePermission = PermissionUtil.convertNativePermission(str);
            try {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this.b.getActivity(), strConvertNativePermission)) {
                    this.e.put(strConvertNativePermission);
                } else {
                    this.f.put(strConvertNativePermission);
                }
            } catch (RuntimeException unused) {
            }
            w2.this.a(this.b, this.c, this.d, this.a, this.e, this.f);
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) throws JSONException {
            this.a.put(PermissionUtil.convertNativePermission(str));
            w2.this.a(this.b, this.c, this.d, this.a, this.e, this.f);
        }
    }

    class b implements ISysEventListener {
        final /* synthetic */ IWebview a;
        final /* synthetic */ String b;

        b(IWebview iWebview, String str) {
            this.a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ISysEventListener
        public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
            if (sysEventType != ISysEventListener.SysEventType.onActivityResult) {
                return false;
            }
            Object[] objArr = (Object[]) obj;
            int iIntValue = ((Integer) objArr[0]).intValue();
            int iIntValue2 = ((Integer) objArr[1]).intValue();
            Intent intent = (Intent) objArr[2];
            StringBuffer stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
            stringBuffer.append(iIntValue);
            stringBuffer.append(",").append(iIntValue2);
            if (intent != null) {
                stringBuffer.append(",").append(w2.a(this.a, intent));
            }
            stringBuffer.append(Operators.ARRAY_END_STR);
            Deprecated_JSUtil.execCallback(this.a, this.b, stringBuffer.toString(), JSUtil.OK, true, true);
            return true;
        }
    }

    class c implements IEventCallback {
        final /* synthetic */ IWebview a;
        final /* synthetic */ ISysEventListener b;

        c(IWebview iWebview, ISysEventListener iSysEventListener) {
            this.a = iWebview;
            this.b = iSysEventListener;
        }

        @Override // io.dcloud.common.DHInterface.IEventCallback
        public Object onCallBack(String str, Object obj) {
            if (!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_WINDOW_CLOSE) && !PdrUtil.isEquals(str, AbsoluteConst.EVENTS_CLOSE)) {
                return null;
            }
            this.a.obtainApp().unregisterSysEventListener(this.b, ISysEventListener.SysEventType.onActivityResult);
            return null;
        }
    }

    static /* synthetic */ class d {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ISysEventListener.SysEventType.values().length];
            a = iArr;
            try {
                iArr[ISysEventListener.SysEventType.onActivityResult.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public w2(AbsMgr absMgr) {
        c = this;
    }

    static void b(Class cls, JSONStringer jSONStringer, ArrayList arrayList) throws JSONException {
        for (Class superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            String name = superclass.getName();
            if (!arrayList.contains(name)) {
                jSONStringer.value(name);
                arrayList.add(name);
                a(superclass, jSONStringer, arrayList);
            }
            if (superclass == Object.class) {
                return;
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IEventCallback
    public Object onCallBack(String str, Object obj) {
        if (!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_CLOSE) || !(obj instanceof IWebview)) {
            return null;
        }
        try {
            ((AdaFrameView) ((IWebview) obj).obtainFrameView()).removeFrameViewListener(this);
            this.a.remove(Integer.valueOf(((IWebview) obj).hashCode()));
            HashMap map = (HashMap) b.remove(Integer.valueOf(((IWebview) obj).hashCode()));
            if (map == null) {
                return null;
            }
            for (Map.Entry entry : map.entrySet()) {
                b.remove(entry.getKey());
                ((i3) entry.getValue()).a();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:172:0x04c3 A[PHI: r2
  0x04c3: PHI (r2v45 int) = (r2v44 int), (r2v49 int) binds: [B:167:0x04ba, B:169:0x04bd] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(io.dcloud.common.DHInterface.IWebview r19, java.lang.String r20, java.lang.String[] r21) throws org.json.JSONException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 1274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.w2.a(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    static String b(IWebview iWebview, Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        String strB = j3.b(cls);
        if (!j3.a((Class) cls) && cls != String.class && cls != CharSequence.class && !cls.isArray()) {
            str = "object";
        } else {
            str = "basic";
        }
        StringBuffer stringBuffer = new StringBuffer();
        a(iWebview, obj, cls, stringBuffer);
        return Deprecated_JSUtil.wrapJsVar(StringUtil.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", str, stringBuffer.toString(), strB, a((Class) cls)), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IWebview iWebview, String[] strArr, JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, JSONArray jSONArray4) throws JSONException {
        if (jSONArray.length() == jSONArray2.length() + jSONArray3.length() + jSONArray4.length()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("granted", jSONArray2);
                jSONObject.put("deniedPresent", jSONArray3);
                jSONObject.put("deniedAlways", jSONArray4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Deprecated_JSUtil.execCallback(iWebview, strArr[0], jSONObject.toString(), JSUtil.OK, true, false);
        }
    }

    private static String a(Exception exc, String str) {
        String message;
        StringBuilder sb = new StringBuilder();
        if (exc.getCause() != null) {
            message = exc.getCause();
        } else {
            boolean zIsEmpty = TextUtils.isEmpty(exc.getMessage());
            message = exc;
            if (!zIsEmpty) {
                message = exc.getMessage();
            }
        }
        sb.append((Object) message);
        sb.append(";at ");
        sb.append(str);
        return StringUtil.format("throw '%s';", sb.toString());
    }

    private void a(IWebview iWebview, String str, String str2) {
        if (d.a[ISysEventListener.SysEventType.valueOf(str).ordinal()] != 1) {
            return;
        }
        b bVar = new b(iWebview, str2);
        iWebview.obtainApp().registerSysEventListener(bVar, ISysEventListener.SysEventType.onActivityResult);
        iWebview.obtainFrameView().addFrameViewListener(new c(iWebview, bVar));
    }

    static String a(IWebview iWebview, Object obj) {
        String strValueOf;
        Class<?> cls = obj.getClass();
        String strB = j3.b(cls);
        if (cls != String.class && cls != CharSequence.class) {
            if (j3.a((Class) cls)) {
                strValueOf = String.valueOf(obj);
            } else {
                String strA = a(obj);
                a(iWebview, strA, obj);
                return StringUtil.format("plus.ios.__Tool.New(%s, true)", Deprecated_JSUtil.wrapJsVar(StringUtil.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", "object", JSUtil.QUOTE + strA + JSUtil.QUOTE, strB, a((Class) cls)), false));
            }
        } else {
            strValueOf = JSUtil.QUOTE + String.valueOf(obj) + JSUtil.QUOTE;
        }
        return Deprecated_JSUtil.wrapJsVar(StringUtil.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", "basic", strValueOf, strB, a((Class) cls)), false);
    }

    static String a(Class cls) throws JSONException {
        JSONStringer jSONStringer = new JSONStringer();
        ArrayList arrayList = new ArrayList();
        try {
            jSONStringer.array();
            b(cls, jSONStringer, arrayList);
            jSONStringer.endArray();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jSONStringer.toString();
        return string == null ? "[]" : string;
    }

    static void a(Class cls, JSONStringer jSONStringer, ArrayList arrayList) throws JSONException {
        Class<?>[] interfaces = cls.getInterfaces();
        if (interfaces != null) {
            for (Class<?> cls2 : interfaces) {
                String name = cls2.getName();
                if (!arrayList.contains(name)) {
                    jSONStringer.value(name);
                    arrayList.add(name);
                    a(cls2, jSONStringer, arrayList);
                }
            }
        }
    }

    static void a(IWebview iWebview, Object obj, Class cls, StringBuffer stringBuffer) {
        if (cls != String.class && cls != CharSequence.class) {
            if (j3.a(cls)) {
                stringBuffer.append(String.valueOf(obj));
                return;
            }
            if (cls.isArray()) {
                int length = Array.getLength(obj);
                stringBuffer.append(Operators.ARRAY_START_STR);
                for (int i = 0; i < length; i++) {
                    stringBuffer.append(b(iWebview, j3.a(Array.get(obj, i), cls)));
                    if (i != length - 1) {
                        stringBuffer.append(",");
                    }
                }
                stringBuffer.append(Operators.ARRAY_END_STR);
                return;
            }
            String strA = a(obj);
            a(iWebview, strA, obj);
            stringBuffer.append(JSUtil.QUOTE).append(strA).append(JSUtil.QUOTE);
            return;
        }
        stringBuffer.append(JSONObject.quote(String.valueOf(obj)));
    }

    private static HashMap a(IWebview iWebview) {
        HashMap map = (HashMap) b.get(Integer.valueOf(iWebview.hashCode()));
        if (map != null) {
            return map;
        }
        HashMap map2 = new HashMap(2);
        b.put(Integer.valueOf(iWebview.hashCode()), map2);
        return map2;
    }

    i3 a(HashMap map, String str) {
        return (i3) map.get(str);
    }

    i3 a(IWebview iWebview, String str) {
        return a(a(iWebview), str);
    }

    private static void a(IWebview iWebview, String str, i3 i3Var) {
        a(iWebview).put(str, i3Var);
    }

    private static i3 a(IWebview iWebview, String str, Object obj) {
        i3 i3Var = new i3(c, obj.getClass(), str, obj);
        a(iWebview, str, i3Var);
        return i3Var;
    }

    static String a(Object obj) {
        return IFeature.F_INVOCATION + obj.hashCode();
    }

    private boolean a(String str, String str2, IWebview iWebview) {
        return iWebview != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && Boolean.parseBoolean(iWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_USE_ENCRYPTION)) && "setWebContentsDebuggingEnabled".equalsIgnoreCase(str2) && (TextUtils.isEmpty(str) || "WebView".equalsIgnoreCase(str) || "android.webkit.WebView".equalsIgnoreCase(str));
    }

    public void a(String str) {
        TextUtils.isEmpty(str);
    }
}
