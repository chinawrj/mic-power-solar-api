package uts.sdk.modules.DCloudUniNetwork;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.uts.UTSIteratorKt;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\n0\bH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\"\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\n0\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¨\u0006\u0012"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/CookieInterceptor;", "Lokhttp3/Interceptor;", "()V", "addCookies", "", "builder", "Lokhttp3/Request$Builder;", "localCookie", "", "", "", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "toMap", "headers", "Lokhttp3/Headers;", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class CookieInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        String strHeader = request.header("cookie");
        URI uri = request.url().uri();
        CookieHandler cookieHandler = CookieHandler.getDefault();
        if (strHeader == null) {
            Request.Builder requestBuilder = request.newBuilder();
            try {
                Headers headers = request.headers();
                Intrinsics.checkNotNullExpressionValue(headers, "request.headers()");
                Map<String, List<String>> localCookie = cookieHandler.get(uri, toMap(headers));
                Intrinsics.checkNotNullExpressionValue(requestBuilder, "requestBuilder");
                Intrinsics.checkNotNullExpressionValue(localCookie, "localCookie");
                addCookies(requestBuilder, localCookie);
            } catch (Exception unused) {
            }
            request = requestBuilder.build();
        }
        Response response = chain.proceed(request);
        try {
            Headers headers2 = response.headers();
            Intrinsics.checkNotNullExpressionValue(headers2, "response.headers()");
            cookieHandler.put(uri, toMap(headers2));
        } catch (Exception unused2) {
        }
        Intrinsics.checkNotNullExpressionValue(response, "response");
        return response;
    }

    private final Map<String, List<String>> toMap(Headers headers) {
        TreeMap treeMap = new TreeMap(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            TreeMap treeMap2 = treeMap;
            ArrayList arrayList = (List) treeMap2.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(name, "name");
                treeMap2.put(name, arrayList);
            }
            String strValue = headers.value(i);
            Intrinsics.checkNotNullExpressionValue(strValue, "headers.value(i)");
            arrayList.add(strValue);
        }
        return treeMap;
    }

    private final void addCookies(Request.Builder builder, Map<String, List<String>> localCookie) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str : (Set) UTSIteratorKt.resolveUTSKeyIterator(localCookie.keySet())) {
            if (arrayList2.size() == 2) {
                break;
            }
            if (StringsKt.equals("cookie", str, true) || StringsKt.equals("cookie2", str, true)) {
                arrayList2.add(str);
                List<String> list = localCookie.get(str);
                if (list != null && !list.isEmpty()) {
                    arrayList.addAll(list);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = ((List) UTSIteratorKt.resolveUTSKeyIterator(arrayList)).iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append("; ");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "headerStr.toString()");
        if (StringsKt.endsWith$default(string, "; ", false, 2, (Object) null)) {
            Intrinsics.checkNotNullExpressionValue(sb.delete(sb.length() - 2, sb.length() - 1), "this.delete(startIndex, endIndex)");
        }
        String string2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "headerStr.toString()");
        if (string2.length() == 0) {
            return;
        }
        builder.addHeader(IWebview.COOKIE, sb.toString());
    }
}
