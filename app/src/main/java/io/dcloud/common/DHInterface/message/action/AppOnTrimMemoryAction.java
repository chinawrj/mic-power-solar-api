package io.dcloud.common.DHInterface.message.action;

/* loaded from: classes3.dex */
public class AppOnTrimMemoryAction implements IAction {
    private int level;

    public AppOnTrimMemoryAction(int i) {
        this.level = i;
    }

    public static AppOnTrimMemoryAction obtain(int i) {
        return new AppOnTrimMemoryAction(i);
    }

    public int getLevel() {
        return this.level;
    }
}
