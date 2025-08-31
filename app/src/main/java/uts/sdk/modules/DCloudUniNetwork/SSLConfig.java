package uts.sdk.modules.DCloudUniNetwork;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\nJ\n\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0016J\u001b\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0005H\u0016R\u0018\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/SSLConfig;", "", "()V", "ca", "", "", "[Ljava/lang/String;", "keystore", "storePass", "getCa", "()[Ljava/lang/String;", "getKeystore", "getStorePass", "setCa", "", "reassignedCa", "([Ljava/lang/String;)V", "setKeystore", "reassignedKs", "setStorePass", "reassignedSp", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class SSLConfig {
    private String[] ca;
    private String keystore;
    private String storePass;

    public String getKeystore() {
        return this.keystore;
    }

    public void setKeystore(String reassignedKs) {
        Intrinsics.checkNotNullParameter(reassignedKs, "reassignedKs");
        this.keystore = reassignedKs;
    }

    public String getStorePass() {
        return this.storePass;
    }

    public void setStorePass(String reassignedSp) {
        Intrinsics.checkNotNullParameter(reassignedSp, "reassignedSp");
        this.storePass = reassignedSp;
    }

    public String[] getCa() {
        return this.ca;
    }

    public void setCa(String[] reassignedCa) {
        Intrinsics.checkNotNullParameter(reassignedCa, "reassignedCa");
        this.ca = reassignedCa;
    }
}
