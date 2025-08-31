package uts.sdk.modules.DCloudUniNetwork;

import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSIteratorKt;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Dns;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/OKDns;", "Lokhttp3/Dns;", "()V", "lookup", "", "Ljava/net/InetAddress;", "hostName", "", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class OKDns implements Dns {
    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String hostName) throws UnknownHostException {
        Intrinsics.checkNotNullParameter(hostName, "hostName");
        try {
            UTSArray uTSArray = new UTSArray();
            Object objResolveUTSKeyIterator = UTSIteratorKt.resolveUTSKeyIterator(InetAddress.getAllByName(hostName));
            Intrinsics.checkNotNullExpressionValue(objResolveUTSKeyIterator, "resolveUTSKeyIterator(inetAddresses)");
            for (InetAddress inetAddress : (InetAddress[]) objResolveUTSKeyIterator) {
                if (inetAddress instanceof Inet4Address) {
                    uTSArray.unshift(inetAddress);
                } else {
                    Intrinsics.checkNotNullExpressionValue(inetAddress, "inetAddress");
                    uTSArray.push(inetAddress);
                }
            }
            return uTSArray;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException("error");
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }
}
