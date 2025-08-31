package com.taobao.weex.utils.tools;

/* loaded from: classes3.dex */
public class LogSwitch {
    private int low_level = 4;
    private int medium_level = 2;
    private int high_level = 1;
    private int log_switch = 0;
    private boolean showLowLevelLog = false;
    private boolean showMediumLevelLog = false;
    private boolean showHighLevelLog = true;

    public int getLog_switch() {
        return this.log_switch;
    }

    public void setLog_switch() {
        if (this.showLowLevelLog) {
            this.log_switch |= this.low_level;
        }
        if (this.showMediumLevelLog) {
            this.log_switch |= this.medium_level;
        }
        if (this.showHighLevelLog) {
            this.log_switch |= this.high_level;
        }
    }
}
