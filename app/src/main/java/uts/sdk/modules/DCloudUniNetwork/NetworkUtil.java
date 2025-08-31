package uts.sdk.modules.DCloudUniNetwork;

import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSJSONObject;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkUtil;", "", "()V", "Companion", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NetworkUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: index.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004¨\u0006\f"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkUtil$Companion;", "", "()V", "convertHeaders", "Lio/dcloud/uts/UTSJSONObject;", "headers", "", "", "", "parseCookie", "Lio/dcloud/uts/UTSArray;", WXBasicComponentType.HEADER, "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UTSJSONObject convertHeaders(Map<String, List<String>> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            UTSJSONObject uTSJSONObject = new UTSJSONObject();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (key == null) {
                    key = "_";
                }
                if (value.size() != 0) {
                    if (value.size() == 1) {
                        uTSJSONObject.set(key, value.get(0));
                    } else {
                        uTSJSONObject.set(key, value.toString());
                    }
                }
            }
            return uTSJSONObject;
        }

        public final UTSArray<String> parseCookie(UTSJSONObject header) throws IllegalAccessException, IllegalArgumentException {
            if (header != null) {
                String string = header.getString(IWebview.SET_COOKIE);
                if (string == null) {
                    string = header.getString("set-cookie");
                }
                if (string != null) {
                    UTSArray uTSArray = new UTSArray();
                    if (Intrinsics.areEqual(StringKt.charAt(string, (Number) 0), Operators.ARRAY_START_STR) && Intrinsics.areEqual(StringKt.charAt(string, Integer.valueOf(string.length() - 1)), Operators.ARRAY_END_STR)) {
                        string = StringKt.slice(string, (Number) 1, (Number) (-1));
                    }
                    UTSArray<String> uTSArraySplit = StringKt.split(string, ";");
                    for (Number numberInc = (Number) 0; NumberKt.compareTo(numberInc, uTSArraySplit.getLength()) < 0; numberInc = NumberKt.inc(numberInc)) {
                        if (!NumberKt.numberEquals(StringKt.indexOf$default(uTSArraySplit.get(numberInc), "Expires=", null, 2, null), -1) || !NumberKt.numberEquals(StringKt.indexOf$default(uTSArraySplit.get(numberInc), "expires=", null, 2, null), -1)) {
                            uTSArray.push(StringKt.replace(uTSArraySplit.get(numberInc), ",", ""));
                        } else {
                            uTSArray.push(uTSArraySplit.get(numberInc));
                        }
                    }
                    return StringKt.split(uTSArray.join(";"), ",");
                }
                return new UTSArray<>();
            }
            return new UTSArray<>();
        }
    }
}
