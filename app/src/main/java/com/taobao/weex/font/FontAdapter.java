package com.taobao.weex.font;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class FontAdapter {
    private List<FontListener> mFontListener = new CopyOnWriteArrayList();

    public void addFontListener(FontListener fontListener) {
        this.mFontListener.add(fontListener);
    }

    public void onAddFontRule(String str, String str2, String str3) {
        synchronized (this) {
            Iterator<FontListener> it = this.mFontListener.iterator();
            while (it.hasNext()) {
                it.next().onAddFontRule(str, str2, str3);
            }
        }
    }

    public void onFontLoad(String str, String str2, String str3) {
        synchronized (this) {
            Iterator<FontListener> it = this.mFontListener.iterator();
            while (it.hasNext()) {
                it.next().onFontLoad(str, str2, str3);
            }
        }
    }

    public void removeFontListener(FontListener fontListener) {
        this.mFontListener.remove(fontListener);
    }
}
