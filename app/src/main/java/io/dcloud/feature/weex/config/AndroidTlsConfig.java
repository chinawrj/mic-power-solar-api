package io.dcloud.feature.weex.config;

import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes3.dex */
public class AndroidTlsConfig {
    private String[] ca;
    private String keystore;
    private String storePass;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AndroidTlsConfig androidTlsConfig = (AndroidTlsConfig) obj;
        return this.keystore.equals(androidTlsConfig.keystore) && this.storePass.equals(androidTlsConfig.storePass) && Arrays.toString(this.ca).equals(Arrays.toString(androidTlsConfig.ca));
    }

    public String[] getCa() {
        return this.ca;
    }

    public String getKeystore() {
        return this.keystore;
    }

    public String getStorePass() {
        return this.storePass;
    }

    public int hashCode() {
        return (Objects.hash(this.keystore, this.storePass, Arrays.toString(this.ca)) * 31) + Arrays.hashCode(this.ca);
    }

    public void setCa(String[] strArr) {
        if (strArr == null) {
            strArr = new String[0];
        }
        this.ca = strArr;
    }

    public void setKeystore(String str) {
        if (str == null) {
            str = "";
        }
        this.keystore = str;
    }

    public void setStorePass(String str) {
        if (str == null) {
            str = "";
        }
        this.storePass = str;
    }

    public String toString() {
        return "AndroidTlsConfig{keystore='" + this.keystore + "', storePass='" + this.storePass + "', ca=" + Arrays.toString(this.ca) + Operators.BLOCK_END;
    }
}
