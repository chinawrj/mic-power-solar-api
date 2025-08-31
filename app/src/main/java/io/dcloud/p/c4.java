package io.dcloud.p;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class c4 {
    AbsMgr a;
    ConcurrentHashMap b = new ConcurrentHashMap();
    ArrayList c = new ArrayList();

    class a implements MessageHandler.IMessages {
        final /* synthetic */ c a;

        /* renamed from: io.dcloud.p.c4$a$a, reason: collision with other inner class name */
        class C0070a implements ICallBack {
            C0070a() {
            }

            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                if (i == -1) {
                    Log.i("console", "nativeApp pull fail");
                } else if (i == 1) {
                    Log.i("console", "nativeApp pull success");
                }
                c4.this.b.clear();
                c4.this.a();
                return null;
            }
        }

        class b implements Runnable {
            final /* synthetic */ String a;

            b(String str) {
                this.a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (DHFile.delete(this.a)) {
                    Log.i("console", "rm file success");
                } else {
                    Log.i("console", "rm file fail");
                }
                c4.this.b.clear();
                c4.this.a();
            }
        }

        a(c cVar) {
            this.a = cVar;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            String str = this.a.a;
            str.hashCode();
            str.hashCode();
            switch (str) {
                case "delete":
                    String str2 = this.a.b;
                    if (!new File(str2).exists()) {
                        Log.i("console", "rm file fail");
                        c4.this.b.clear();
                        c4.this.a();
                        break;
                    } else {
                        ThreadPool.self().addSingleThreadTask(new b(str2));
                        break;
                    }
                case "script":
                    String str3 = this.a.b;
                    if ("restart".equals(str3)) {
                        c4.this.a.processEvent(IMgr.MgrType.AppMgr, 3, "snc:CID");
                    } else if (AbsoluteConst.JSON_KEY_DEBUG_REFRESH.equals(str3)) {
                        c4.this.a.processEvent(IMgr.MgrType.AppMgr, 27, null);
                    } else if ("restartAndRun".equals(str3)) {
                        c4.this.a.getContext().startActivity(Intent.makeRestartActivityTask(c4.this.a.getContext().getPackageManager().getLaunchIntentForPackage(c4.this.a.getContext().getPackageName()).getComponent()));
                        Runtime.getRuntime().exit(0);
                    }
                    c4.this.b.clear();
                    c4.this.a();
                    break;
                case "update":
                    String str4 = this.a.b;
                    if ("all".equals(str4)) {
                        c4.this.a.processEvent(IMgr.MgrType.WindowMgr, 13, null);
                    } else if ("current".equals(str4)) {
                        c4.this.a.processEvent(IMgr.MgrType.WindowMgr, 12, null);
                    } else {
                        c4.this.a.processEvent(IMgr.MgrType.WindowMgr, 14, str4);
                    }
                    c4.this.b.clear();
                    c4.this.a();
                    break;
                case "pull":
                    String str5 = this.a.b;
                    if (!TextUtils.isEmpty(str5)) {
                        c4.this.a(str5, new C0070a());
                        break;
                    }
                    break;
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ File c;
        final /* synthetic */ ICallBack d;

        b(String str, String str2, File file, ICallBack iCallBack) {
            this.a = str;
            this.b = str2;
            this.c = file;
            this.d = iCallBack;
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            int iCopyFile = DHFile.copyFile(this.a, this.b);
            DHFile.delete(this.c.getParent());
            if (iCopyFile == 1) {
                ICallBack iCallBack = this.d;
                if (iCallBack != null) {
                    iCallBack.onCallBack(1, null);
                    return;
                }
                return;
            }
            ICallBack iCallBack2 = this.d;
            if (iCallBack2 != null) {
                iCallBack2.onCallBack(-1, null);
            }
        }
    }

    class c {
        String a;
        String b;

        c() {
        }
    }

    c4(AbsMgr absMgr) {
        this.a = null;
        this.a = absMgr;
    }

    private ArrayList b(String str) {
        String strTrim;
        ArrayList arrayList = new ArrayList(1);
        if (str.startsWith(AbsoluteConst.SOCKET_NATIVE_COMMAND)) {
            str = str.substring(4);
        }
        String strTrim2 = str.trim();
        int length = strTrim2.length();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        int i2 = 0;
        loop0: while (true) {
            int i3 = i2;
            while (i2 < length) {
                char cCharAt = strTrim2.charAt(i2);
                i2++;
                if (i2 == length || ((b(cCharAt) && arrayList2.size() % 2 == 0) || a(cCharAt))) {
                    strTrim = strTrim2.substring(i3, i2).trim();
                    if (!"".equals(strTrim)) {
                        break;
                    }
                }
            }
            arrayList2.add(strTrim);
        }
        int size = arrayList2.size();
        while (i < size) {
            c cVar = new c();
            cVar.a = (String) arrayList2.get(i);
            cVar.b = (String) arrayList2.get(i + 1);
            i += 2;
            arrayList.add(cVar);
        }
        return arrayList;
    }

    synchronized void a(String str) {
        ArrayList arrayListB = b(str);
        if (arrayListB != null && !arrayListB.isEmpty()) {
            this.c.addAll(arrayListB);
        }
        a();
    }

    boolean a(char c2) {
        return c2 == '\r' || c2 == '\n';
    }

    boolean b(char c2) {
        return c2 == '\t' || c2 == 11 || c2 == '\f' || c2 == ' ' || c2 == 160 || c2 == 12288;
    }

    public synchronized void a() {
        ArrayList arrayList;
        c cVar;
        if (this.b.isEmpty() && (arrayList = this.c) != null && !arrayList.isEmpty() && (cVar = (c) this.c.remove(0)) != null) {
            this.b.put("runing", cVar);
            a(cVar);
        }
    }

    public void a(c cVar) {
        MessageHandler.sendMessage(new a(cVar), null);
    }

    public synchronized void a(String str, ICallBack iCallBack) {
        JSONObject jSONObject;
        if (!PdrUtil.isEmpty(str)) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            if (jSONObject == null) {
                Log.i("console", "nativeApp pull fail");
                if (iCallBack != null) {
                    iCallBack.onCallBack(-1, null);
                }
                return;
            }
            String strOptString = jSONObject.optString("appid");
            String strOptString2 = jSONObject.optString("filePath");
            if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                String str2 = BaseInfo.sBaseFsAppsPath + strOptString + "/www";
                File file = new File(strOptString2);
                if (file.exists()) {
                    ThreadPool.self().addSingleThreadTask(new b(strOptString2, str2, file, iCallBack));
                } else {
                    Log.i("console", "nativeApp pull fail");
                    if (iCallBack != null) {
                        iCallBack.onCallBack(-1, null);
                    }
                }
            }
            Log.i("console", "nativeApp pull fail");
            if (iCallBack != null) {
                iCallBack.onCallBack(-1, null);
            }
        }
    }
}
