package io.dcloud.p;

import android.app.Activity;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.module.DCBaseAOL;
import io.dcloud.sdk.core.util.MainHandlerUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class j1 extends w {
    protected y1 r;

    public j1(Activity activity, int i) {
        super(activity);
        this.d = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(int i, String str, JSONArray jSONArray) {
        y1 y1Var = this.r;
        if (y1Var != null) {
            y1Var.onError(i, str, jSONArray);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(List list) {
        y1 y1Var = this.r;
        if (y1Var != null) {
            y1Var.onLoaded(list);
        }
    }

    @Override // io.dcloud.p.w
    protected void a(final int i, final String str, final JSONArray jSONArray) {
        b3.b("uniAd-loadError", "code:" + i + ";message:" + str + ";detail:" + String.valueOf(jSONArray));
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.j1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.c(i, str, jSONArray);
            }
        });
    }

    protected List d(List list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new i1((DCBaseAOL) it.next(), a()));
            }
        }
        return arrayList;
    }

    public void a(DCloudAOLSlot dCloudAOLSlot, y1 y1Var) {
        a(dCloudAOLSlot);
        this.r = y1Var;
        z2.a().post(this);
    }

    @Override // io.dcloud.p.w
    protected void c(List list) {
        final List listD = d(list);
        MainHandlerUtil.getMainHandler().post(new Runnable() { // from class: io.dcloud.p.j1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.e(listD);
            }
        });
    }
}
