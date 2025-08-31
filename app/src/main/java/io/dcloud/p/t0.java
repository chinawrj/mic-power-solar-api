package io.dcloud.p;

import androidtranscoder.format.MediaFormatExtraConstants;
import com.facebook.common.callercontext.ContextChain;
import com.nostra13.dcloudimageloader.core.download.BaseImageDownloader;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.p.t4;
import io.dcloud.sdk.core.util.Const;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class t0 {
    private String a;
    private int b;
    private int c;
    private int d;
    private List e;
    private int f = 0;
    private boolean g = false;
    private int h = 1;
    private final List i = new ArrayList();

    public t0 a(JSONObject jSONObject, int i) {
        this.a = jSONObject.optString("adpid");
        this.b = jSONObject.optInt("type", -1);
        int iOptInt = jSONObject.optInt("tto", 18000);
        this.c = iOptInt;
        if (iOptInt < 1000) {
            this.c = 18000;
        }
        int iOptInt2 = jSONObject.optInt("dsto", BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT);
        this.d = iOptInt2;
        if (iOptInt2 < 1000) {
            this.d = BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT;
        }
        this.f = jSONObject.optInt("sr", 0);
        this.g = jSONObject.optInt("ord", 0) == 1;
        int iOptInt3 = jSONObject.optInt("ft", 1);
        this.h = jSONObject.optInt("mt", 1);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("cfgs");
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
            if (this.e == null) {
                this.e = new ArrayList();
            }
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                if (jSONObjectOptJSONObject != null) {
                    int iOptInt4 = jSONObjectOptJSONObject.has("sto") ? jSONObjectOptJSONObject.optInt("sto", BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT) : this.d;
                    if (iOptInt4 < 1000) {
                        iOptInt4 = 5000;
                    }
                    t4 t4VarA = new t4.b().d(jSONObjectOptJSONObject.optString("sid")).c(jSONObjectOptJSONObject.optInt("cpm", -1)).b(jSONObjectOptJSONObject.optInt("bdt", 0) == 1).f(jSONObjectOptJSONObject.optInt(MediaFormatExtraConstants.KEY_LEVEL, -1)).g(jSONObjectOptJSONObject.optInt(WXComponent.PROP_FS_MATCH_PARENT, -1)).c(jSONObjectOptJSONObject.optString(ContextChain.TAG_PRODUCT)).i(jSONObjectOptJSONObject.optInt("ss", -1)).j(iOptInt4).k(jSONObjectOptJSONObject.optInt(WXComponent.PROP_FS_WRAP_CONTENT, -1)).d(iOptInt3).b(this.b).a(jSONObjectOptJSONObject.optString("mrt")).b(jSONObjectOptJSONObject.optString(AbsoluteConst.XML_PATH)).h(jSONObjectOptJSONObject.optInt("mt", -1)).a(jSONObjectOptJSONObject.optInt("end", 0) == 1).a(jSONObjectOptJSONObject.optJSONObject("params")).a(jSONObjectOptJSONObject.optInt("acpt", 0)).e(this.h).a();
                    if (!t4VarA.l().equals(Const.TYPE_HW) || i != 3) {
                        this.i.add(t4VarA.l());
                        this.e.add(t4VarA);
                    }
                }
            }
        }
        return this;
    }

    public int b() {
        return this.f;
    }

    public List c() {
        return this.e;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.b;
    }

    public boolean f() {
        return this.g;
    }

    public String a() {
        return this.a;
    }
}
