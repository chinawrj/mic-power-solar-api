package com.taobao.weex.http;

import io.dcloud.common.DHInterface.IReflectAble;

/* loaded from: classes.dex */
public class CertDTO implements IReflectAble {
    public String client;
    public String clientPassword;
    public String host;
    public String[] server;

    public String getClient() {
        return this.client;
    }

    public String getClientPassword() {
        return this.clientPassword;
    }

    public String getHost() {
        return this.host;
    }

    public String[] getServer() {
        return this.server;
    }

    public void setClient(String str) {
        this.client = str;
    }

    public void setClientPassword(String str) {
        this.clientPassword = str;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setServer(String[] strArr) {
        this.server = strArr;
    }
}
