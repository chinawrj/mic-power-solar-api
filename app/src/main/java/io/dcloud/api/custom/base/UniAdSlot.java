package io.dcloud.api.custom.base;

/* loaded from: classes3.dex */
public class UniAdSlot {
    private String a;
    private int b = 0;
    private int c = 0;
    private int d = 3;
    private String e;
    private String f;

    public int getAdCount() {
        return this.d;
    }

    public String getExtra() {
        return this.e;
    }

    public int getHeight() {
        return this.c;
    }

    public String getSlotId() {
        return this.a;
    }

    public String getUserId() {
        return this.f;
    }

    public int getWidth() {
        return this.b;
    }

    public void setAdCount(int i) {
        this.d = i;
    }

    public void setExtra(String str) {
        this.e = str;
    }

    public void setHeight(int i) {
        this.c = i;
    }

    public void setSlotId(String str) {
        this.a = str;
    }

    public void setUserId(String str) {
        this.f = str;
    }

    public void setWidth(int i) {
        this.b = i;
    }

    public String toString() {
        return "UniAdSlot{slotId='" + this.a + "', width=" + this.b + ", height=" + this.c + ", adCount=" + this.d + ", extra='" + this.e + "', userId='" + this.f + "'}";
    }
}
