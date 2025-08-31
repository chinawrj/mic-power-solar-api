package io.dcloud.sdk.core.v3.fd;

import android.app.Activity;
import io.dcloud.p.i1;
import io.dcloud.p.j1;
import io.dcloud.p.y1;
import io.dcloud.sdk.core.entry.DCloudAOLSlot;
import io.dcloud.sdk.core.util.AOLErrorUtil;
import io.dcloud.sdk.core.v3.base.DCBaseAOL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes3.dex */
public class DCFeedAOLLoader extends DCBaseAOL {
    protected j1 b;

    public DCFeedAOLLoader(Activity activity) {
        super(activity);
        this.b = new j1(activity, 4);
    }

    public void load(DCloudAOLSlot dCloudAOLSlot, final DCFeedAOLLoadListener dCFeedAOLLoadListener) {
        if (getContext() != null && dCloudAOLSlot != null) {
            this.b.a(dCloudAOLSlot, new y1() { // from class: io.dcloud.sdk.core.v3.fd.DCFeedAOLLoader.1
                @Override // io.dcloud.p.y1
                public void onError(int i, String str, JSONArray jSONArray) {
                    DCFeedAOLLoadListener dCFeedAOLLoadListener2 = dCFeedAOLLoadListener;
                    if (dCFeedAOLLoadListener2 != null) {
                        dCFeedAOLLoadListener2.onError(i, str, jSONArray);
                    }
                }

                @Override // io.dcloud.p.y1
                public void onLoaded(List<i1> list) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<i1> it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new DCFeedAOL(it.next()));
                    }
                    DCFeedAOLLoadListener dCFeedAOLLoadListener2 = dCFeedAOLLoadListener;
                    if (dCFeedAOLLoadListener2 != null) {
                        dCFeedAOLLoadListener2.onFeedAOLLoad(arrayList);
                    }
                }
            });
        } else if (dCFeedAOLLoadListener != null) {
            dCFeedAOLLoadListener.onError(-5014, AOLErrorUtil.getErrorMsg(-5014), null);
        }
    }
}
