package io.dcloud.common.DHInterface.message;

import io.dcloud.common.DHInterface.message.action.IAction;

/* loaded from: classes3.dex */
public abstract class AbsActionObserver {
    IObserveAble observeAble;

    public AbsActionObserver(IObserveAble iObserveAble) {
        this.observeAble = iObserveAble;
    }

    public EnumUniqueID getObserverUniqueID() {
        return this.observeAble.getActionObserverID();
    }

    public abstract boolean handleMessage(IAction iAction);
}
