package io.dcloud.feature.pdr;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.db.DCStorage;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class NStorageFeatureImpl implements IFeature {

    class a implements Runnable {
        final /* synthetic */ DCStorage a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;

        a(DCStorage dCStorage, IWebview iWebview, String str, String str2) {
            this.a = dCStorage;
            this.b = iWebview;
            this.c = str;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            Object obj;
            DCStorage.StorageInfo storageInfoPerformGetItem = this.a.performGetItem(this.b.obtainApp().obtainAppId(), this.c);
            JSONObject jSONObject = new JSONObject();
            int i = storageInfoPerformGetItem.code;
            if (i == 1 && (obj = storageInfoPerformGetItem.v) != null) {
                try {
                    jSONObject.put("data", String.valueOf(obj));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSUtil.execCallback(this.b, this.d, jSONObject, JSUtil.OK, false);
                return;
            }
            try {
                jSONObject.put("code", i);
                jSONObject.put("message", storageInfoPerformGetItem.meg);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            JSUtil.execCallback(this.b, this.d, jSONObject, JSUtil.ERROR, false);
        }
    }

    class b implements Runnable {
        final /* synthetic */ DCStorage a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        b(DCStorage dCStorage, IWebview iWebview, String str, String str2, String str3) {
            this.a = dCStorage;
            this.b = iWebview;
            this.c = str;
            this.d = str2;
            this.e = str3;
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            DCStorage.StorageInfo storageInfoPerformSetItem = this.a.performSetItem(this.b.getActivity(), this.b.obtainApp().obtainAppId(), this.c, this.d);
            int i = JSUtil.ERROR;
            JSONObject jSONObject = new JSONObject();
            int i2 = storageInfoPerformSetItem.code;
            if (i2 == 1) {
                i = JSUtil.OK;
            } else {
                try {
                    jSONObject.put("code", i2);
                    jSONObject.put("message", storageInfoPerformSetItem.meg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            JSUtil.execCallback(this.b, this.e, jSONObject, i, false);
        }
    }

    class c implements Runnable {
        final /* synthetic */ DCStorage a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;

        c(DCStorage dCStorage, IWebview iWebview, String str) {
            this.a = dCStorage;
            this.b = iWebview;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            DCStorage.StorageInfo storageInfoPerformClear = this.a.performClear(this.b.getContext(), this.b.obtainApp().obtainAppId());
            JSONObject jSONObject = new JSONObject();
            int i = storageInfoPerformClear.code;
            if (i == 1) {
                JSUtil.execCallback(this.b, this.c, jSONObject, JSUtil.OK, false);
                return;
            }
            try {
                jSONObject.put("code", i);
                jSONObject.put("message", storageInfoPerformClear.meg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSUtil.execCallback(this.b, this.c, jSONObject, JSUtil.ERROR, false);
        }
    }

    class d implements Runnable {
        final /* synthetic */ DCStorage a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;

        d(DCStorage dCStorage, IWebview iWebview, String str, String str2) {
            this.a = dCStorage;
            this.b = iWebview;
            this.c = str;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            DCStorage.StorageInfo storageInfoPerformRemoveItem = this.a.performRemoveItem(this.b.getContext(), this.b.obtainApp().obtainAppId(), this.c);
            JSONObject jSONObject = new JSONObject();
            int i = storageInfoPerformRemoveItem.code;
            if (i == 1) {
                JSUtil.execCallback(this.b, this.d, jSONObject, JSUtil.OK, false);
                return;
            }
            if (i == -3) {
                JSUtil.execCallback(this.b, this.d, jSONObject, JSUtil.OK, false);
                return;
            }
            try {
                jSONObject.put("code", i);
                jSONObject.put("message", storageInfoPerformRemoveItem.meg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSUtil.execCallback(this.b, this.d, jSONObject, JSUtil.ERROR, false);
        }
    }

    class e implements Runnable {
        final /* synthetic */ DCStorage a;
        final /* synthetic */ IWebview b;
        final /* synthetic */ String c;

        e(DCStorage dCStorage, IWebview iWebview, String str) {
            this.a = dCStorage;
            this.b = iWebview;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            Object obj;
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            DCStorage.StorageInfo storageInfoPerformGetAllKeys = this.a.performGetAllKeys(this.b.obtainApp().obtainAppId());
            int i = storageInfoPerformGetAllKeys.code;
            if (i != 1 || (obj = storageInfoPerformGetAllKeys.v) == null) {
                try {
                    jSONObject.put("code", i);
                    jSONObject.put("message", storageInfoPerformGetAllKeys.meg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSUtil.execCallback(this.b, this.c, jSONObject, JSUtil.ERROR, false);
                return;
            }
            List list = (List) obj;
            if (list.size() > 0) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put((String) it.next());
                }
            }
            try {
                jSONObject.put("keys", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            JSUtil.execCallback(this.b, this.c, jSONObject, JSUtil.OK, false);
        }
    }

    void a(IWebview iWebview, String str, String str2) {
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                dCStorage.execute(new a(dCStorage, iWebview, str, str2));
            }
        } catch (Exception unused) {
        }
    }

    String b(IWebview iWebview, String str) {
        DCStorage.StorageInfo storageInfoPerformGetItem;
        Object obj;
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage == null || (storageInfoPerformGetItem = dCStorage.performGetItem(iWebview.obtainApp().obtainAppId(), str)) == null || storageInfoPerformGetItem.code != 1 || (obj = storageInfoPerformGetItem.v) == null) {
                return null;
            }
            return (String) obj;
        } catch (Exception unused) {
            return null;
        }
    }

    void c(IWebview iWebview, String str, String str2) {
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                dCStorage.performSetItem(iWebview.getActivity(), iWebview.obtainApp().obtainAppId(), str, str2);
            }
        } catch (Exception unused) {
        }
    }

    void d(IWebview iWebview, String str) {
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                dCStorage.performRemoveItem(iWebview.getContext(), iWebview.obtainApp().obtainAppId(), str);
            }
        } catch (Exception unused) {
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        DCStorage dCStorage = DCStorage.getDCStorage(null);
        if (dCStorage != null) {
            dCStorage.close();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        str.hashCode();
        str.hashCode();
        switch (str) {
            case "clearAsync":
                a(iWebview, strArr[0]);
                return null;
            case "setItemAsync":
                a(iWebview, strArr[1], strArr[2], strArr[0]);
                return null;
            case "getItem":
                String strB = b(iWebview, strArr[0]);
                if (strB == null) {
                    return "null:";
                }
                return "string:" + strB;
            case "removeItemAsync":
                b(iWebview, strArr[1], strArr[0]);
                return null;
            case "key":
                return Deprecated_JSUtil.wrapJsVar(a(iWebview, Integer.parseInt(strArr[0])), true);
            case "clear":
                a(iWebview);
                return null;
            case "getAllKeys":
                return b(iWebview);
            case "getLength":
                return JSUtil.wrapJsVar(c(iWebview));
            case "removeItem":
                d(iWebview, strArr[0]);
                return null;
            case "getAllKeysAsync":
                c(iWebview, strArr[0]);
                return null;
            case "setItem":
                c(iWebview, strArr[0], strArr[1]);
                return null;
            case "getItemAsync":
                a(iWebview, strArr[1], strArr[0]);
                return null;
            default:
                return null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
    }

    void a(IWebview iWebview, String str, String str2, String str3) {
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                dCStorage.execute(new b(dCStorage, iWebview, str, str2, str3));
            }
        } catch (Exception unused) {
        }
    }

    void c(IWebview iWebview, String str) {
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                dCStorage.execute(new e(dCStorage, iWebview, str));
            }
        } catch (Exception unused) {
        }
    }

    void b(IWebview iWebview, String str, String str2) {
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                dCStorage.execute(new d(dCStorage, iWebview, str, str2));
            }
        } catch (Exception unused) {
        }
    }

    void a(IWebview iWebview) {
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                dCStorage.performClear(iWebview.getContext(), iWebview.obtainApp().obtainAppId());
            }
        } catch (Exception unused) {
        }
    }

    int c(IWebview iWebview) {
        Object obj;
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage == null) {
                return 0;
            }
            DCStorage.StorageInfo storageInfoPerformGetAllKeys = dCStorage.performGetAllKeys(iWebview.obtainApp().obtainAppId());
            if (storageInfoPerformGetAllKeys.code != 1 || (obj = storageInfoPerformGetAllKeys.v) == null) {
                return 0;
            }
            return ((List) obj).size();
        } catch (Exception unused) {
            return 0;
        }
    }

    String b(IWebview iWebview) {
        Object obj;
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                DCStorage.StorageInfo storageInfoPerformGetAllKeys = dCStorage.performGetAllKeys(iWebview.obtainApp().obtainAppId());
                if (storageInfoPerformGetAllKeys.code == 1 && (obj = storageInfoPerformGetAllKeys.v) != null) {
                    List list = (List) obj;
                    StringBuffer stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
                    if (list.size() > 0) {
                        int size = list.size() - 1;
                        for (int i = 0; i < list.size(); i++) {
                            stringBuffer.append("'").append((String) list.get(i)).append("'");
                            if (i == size) {
                                stringBuffer.append(Operators.ARRAY_END_STR);
                            } else {
                                stringBuffer.append(",");
                            }
                        }
                    } else {
                        stringBuffer.append(Operators.ARRAY_END_STR);
                    }
                    return stringBuffer.toString();
                }
                return "";
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    void a(IWebview iWebview, String str) {
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null) {
                dCStorage.execute(new c(dCStorage, iWebview, str));
            }
        } catch (Exception unused) {
        }
    }

    String a(IWebview iWebview, int i) {
        DCStorage.StorageInfo storageInfoPerformGetAllKeys;
        Object obj;
        List list;
        try {
            DCStorage dCStorage = DCStorage.getDCStorage(iWebview.getContext());
            if (dCStorage != null && (obj = (storageInfoPerformGetAllKeys = dCStorage.performGetAllKeys(iWebview.obtainApp().obtainAppId())).v) != null && (obj instanceof List) && storageInfoPerformGetAllKeys.code == 1 && (list = (List) obj) != null && i < list.size()) {
                return (String) list.get(i);
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }
}
