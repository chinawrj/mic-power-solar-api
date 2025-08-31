package io.dcloud.common.util.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;

/* loaded from: classes3.dex */
public class NetCheckReceiver extends BroadcastReceiver {
    public static final String netACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String simACTION = "android.intent.action.SIM_STATE_CHANGED";
    AbsMgr mNetMgr;

    NetCheckReceiver(AbsMgr absMgr) {
        this.mNetMgr = absMgr;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            if (action.equals(netACTION)) {
                this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 1, new Object[]{ISysEventListener.SysEventType.onDeviceNetChanged, null, null});
            } else if (action.equals(simACTION)) {
                this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 1, new Object[]{ISysEventListener.SysEventType.onSimStateChanged, null, null});
            }
        }
    }
}
