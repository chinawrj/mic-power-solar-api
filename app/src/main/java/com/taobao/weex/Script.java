package com.taobao.weex;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class Script {
    private byte[] mBinary;
    private String mContent;

    public Script(String str) {
        this.mContent = str;
    }

    public byte[] getBinary() {
        return this.mBinary;
    }

    public String getContent() {
        return this.mContent;
    }

    public boolean isEmpty() {
        byte[] bArr;
        return TextUtils.isEmpty(this.mContent) && ((bArr = this.mBinary) == null || bArr.length == 0);
    }

    public int length() {
        String str = this.mContent;
        if (str != null) {
            return str.length();
        }
        byte[] bArr = this.mBinary;
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }

    public Script(byte[] bArr) {
        this.mBinary = bArr;
    }
}
