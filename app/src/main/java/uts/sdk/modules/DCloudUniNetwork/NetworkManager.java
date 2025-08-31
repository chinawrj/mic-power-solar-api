package uts.sdk.modules.DCloudUniNetwork;

import android.content.Context;
import android.os.Looper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.common.util.net.NetWork;
import io.dcloud.feature.uniapp.adapter.AbsURIAdapter;
import io.dcloud.uts.ArrayBuffer;
import io.dcloud.uts.Map;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.ObjectKt;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSIteratorKt;
import io.dcloud.uts.UTSJSONObject;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0005\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\tH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u0012H\u0016J$\u0010\u0013\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\u001dH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkManager;", "", "()V", "requestExecutorService", "Ljava/util/concurrent/ExecutorService;", "createRequest", "Lokhttp3/Request;", ExifInterface.GPS_DIRECTION_TRUE, "param", "Luts/sdk/modules/DCloudUniNetwork/RequestOptions;", "listener", "Luts/sdk/modules/DCloudUniNetwork/NetworkRequestListener;", "createRequestClient", "Lokhttp3/OkHttpClient;", "downloadFile", "Luts/sdk/modules/DCloudUniNetwork/DownloadTask;", "options", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileOptions;", "Luts/sdk/modules/DCloudUniNetwork/NetworkDownloadFileListener;", AbsURIAdapter.REQUEST, "Luts/sdk/modules/DCloudUniNetwork/RequestTask;", "stringifyQuery", "", "url", "data", "Lio/dcloud/uts/UTSJSONObject;", "uploadFile", "Luts/sdk/modules/DCloudUniNetwork/UploadTask;", "Luts/sdk/modules/DCloudUniNetwork/UploadFileOptions;", "Luts/sdk/modules/DCloudUniNetwork/NetworkUploadFileListener;", "Companion", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NetworkManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static ConnectionPool connectPool;
    private static NetworkManager instance;
    private ExecutorService requestExecutorService;

    public <T> RequestTask request(RequestOptions<T> param, NetworkRequestListener listener) throws IllegalAccessException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (param.getData() != null && !(param.getData() instanceof UTSJSONObject) && !Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(param.getData()), "string") && !(param.getData() instanceof ArrayBuffer)) {
            UTSJSONObject uTSJSONObject = new UTSJSONObject();
            uTSJSONObject.set("statusCode", "-1");
            uTSJSONObject.set("errorCode", "600008");
            uTSJSONObject.set("errorMsg", "the data parameter type is invalid");
            uTSJSONObject.set("cause", new Exception("he data parameter type is invalid"));
            listener.onComplete(uTSJSONObject);
            return new NetworkRequestTaskImpl(null);
        }
        listener.onStart();
        OkHttpClient okHttpClientCreateRequestClient = createRequestClient(param);
        Request requestCreateRequest = createRequest(param, listener);
        if (requestCreateRequest == null) {
            return new NetworkRequestTaskImpl(null);
        }
        Call callNewCall = okHttpClientCreateRequestClient.newCall(requestCreateRequest);
        Intrinsics.checkNotNullExpressionValue(callNewCall, "client.newCall(request)");
        NetworkRequestTaskImpl networkRequestTaskImpl = new NetworkRequestTaskImpl(callNewCall);
        Boolean enableChunked = param.getEnableChunked();
        callNewCall.enqueue(new SimpleCallback(listener, networkRequestTaskImpl, enableChunked != null ? enableChunked.booleanValue() : false, Looper.myLooper()));
        return networkRequestTaskImpl;
    }

    public <T> OkHttpClient createRequestClient(RequestOptions<T> param) {
        long jLongValue;
        Intrinsics.checkNotNullParameter(param, "param");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (param.getTimeout() != null) {
            Number timeout = param.getTimeout();
            Intrinsics.checkNotNull(timeout);
            jLongValue = timeout.longValue();
        } else {
            jLongValue = 60000;
        }
        builder.connectTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.readTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.writeTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.protocols(Collections.singletonList(Protocol.HTTP_1_1));
        builder.addInterceptor(new CookieInterceptor());
        if (param.getFirstIpv4() != null) {
            Boolean firstIpv4 = param.getFirstIpv4();
            Intrinsics.checkNotNull(firstIpv4);
            if (firstIpv4.booleanValue()) {
                builder.dns(new OKDns());
            }
        }
        if (connectPool == null) {
            connectPool = new ConnectionPool();
        }
        builder.connectionPool(connectPool);
        if (this.requestExecutorService == null) {
            this.requestExecutorService = Executors.newFixedThreadPool(10);
        }
        builder.dispatcher(new Dispatcher(this.requestExecutorService));
        OkHttpClient okHttpClientBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(okHttpClientBuild, "clientBuilder.build()");
        return okHttpClientBuild;
    }

    public <T> Request createRequest(RequestOptions<T> param, NetworkRequestListener listener) throws IllegalAccessException, IllegalArgumentException {
        Object objArray;
        Object data;
        UTSJSONObject object;
        Intrinsics.checkNotNullParameter(param, "param");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Request.Builder builder = new Request.Builder();
        try {
            builder.url(param.getUrl());
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Context appContext = UTSAndroid.INSTANCE.getAppContext();
            Intrinsics.checkNotNull(appContext);
            Object obj = uTSAndroid.getWebViewInfo(appContext).get("ua");
            boolean z = true;
            builder.header(IWebview.USER_AGENT, NumberKt.toString_number_nullable$default(obj, (Number) null, 1, (Object) null));
            if (param.getHeader() == null) {
                param.setHeader(new UTSJSONObject());
            }
            UTSJSONObject header = param.getHeader();
            Intrinsics.checkNotNull(header);
            JSON jSONObject = header.toJSONObject();
            Intrinsics.checkNotNull(jSONObject, "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
            JSONObject jSONObject2 = (JSONObject) jSONObject;
            Iterator it = ((Set) UTSIteratorKt.resolveUTSKeyIterator(jSONObject2.keySet())).iterator();
            String string = "application/x-www-form-urlencoded; charset=UTF-8";
            boolean z2 = false;
            while (true) {
                objArray = "";
                if (!it.hasNext()) {
                    break;
                }
                String str = (String) it.next();
                if (StringsKt.equals(str, NetWork.CONTENT_TYPE, z)) {
                    StringBuilder sb = new StringBuilder("");
                    Object obj2 = jSONObject2.get(str);
                    Intrinsics.checkNotNull(obj2);
                    sb.append(NumberKt.toString(obj2, (Number) 10));
                    string = sb.toString();
                    z2 = true;
                }
                builder.header(str, "" + NumberKt.toString_number_nullable(jSONObject2.get(str), (Number) 10));
                z = true;
            }
            if (!z2 && !"GET".equals(param.getMethod())) {
                string = "application/json";
            }
            if ("POST".equals(param.getMethod()) || "PUT".equals(param.getMethod()) || "PATCH".equals(param.getMethod()) || "DELETE".equals(param.getMethod())) {
                if (param.getData() != null) {
                    listener.onProgress((Number) 0);
                }
                if (param.getData() != null) {
                    if (Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(param.getData()), "string")) {
                        Object data2 = param.getData();
                        Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type kotlin.String");
                        objArray = (String) data2;
                    } else if (param.getData() instanceof ArrayBuffer) {
                        try {
                            Object data3 = param.getData();
                            Intrinsics.checkNotNull(data3, "null cannot be cast to non-null type io.dcloud.uts.ArrayBuffer");
                            objArray = ((ArrayBuffer) data3).toByteBuffer().array();
                            Intrinsics.checkNotNullExpressionValue(objArray, "param.data as ArrayBuffer).toByteBuffer().array()");
                        } catch (Exception e) {
                            UTSJSONObject uTSJSONObject = new UTSJSONObject();
                            uTSJSONObject.set("statusCode", "-1");
                            uTSJSONObject.set("errorCode", "602001");
                            uTSJSONObject.set("errorMsg", "request system error");
                            uTSJSONObject.set("cause", e);
                            listener.onComplete(uTSJSONObject);
                            return null;
                        }
                    } else if (param.getData() instanceof UTSJSONObject) {
                        if (NumberKt.numberEquals(StringKt.indexOf$default(string, "application/x-www-form-urlencoded", null, 2, null), 0)) {
                            Object data4 = param.getData();
                            Intrinsics.checkNotNull(data4, "null cannot be cast to non-null type io.dcloud.uts.UTSJSONObject");
                            Map<String, Object> map = ((UTSJSONObject) data4).toMap();
                            final UTSArray uTSArray = new UTSArray();
                            map.forEach(new Function2<Object, String, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.NetworkManager.createRequest.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Object obj3, String str2) {
                                    invoke2(obj3, str2);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Object obj3, String key1) {
                                    Intrinsics.checkNotNullParameter(key1, "key1");
                                    uTSArray.push(key1 + '=' + NumberKt.toString_number_nullable(obj3, (Number) 10));
                                }
                            });
                            objArray = uTSArray.join("&");
                        } else {
                            objArray = io.dcloud.uts.JSON.stringify(param.getData());
                        }
                    }
                }
                if (objArray instanceof byte[]) {
                    builder.method(param.getMethod(), RequestBody.create(MediaType.parse(string), (byte[]) objArray));
                } else {
                    MediaType mediaType = MediaType.parse(string);
                    Intrinsics.checkNotNull(objArray, "null cannot be cast to non-null type kotlin.String");
                    builder.method(param.getMethod(), RequestBody.create(mediaType, (String) objArray));
                }
                listener.onProgress((Number) 100);
            } else if ("HEAD".equals(param.getMethod())) {
                builder.head();
            } else if ("OPTIONS".equals(param.getMethod())) {
                builder.method(param.getMethod(), null);
            } else if ((param.getMethod() == null || "GET".equals(param.getMethod())) && (data = param.getData()) != null) {
                if (Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(data), "string")) {
                    object = io.dcloud.uts.JSON.parseObject((String) data);
                } else {
                    object = data instanceof UTSJSONObject ? (UTSJSONObject) data : null;
                }
                if (object != null) {
                    try {
                        builder.url(stringifyQuery(param.getUrl(), object));
                    } catch (Exception e2) {
                        UTSJSONObject uTSJSONObject2 = new UTSJSONObject();
                        uTSJSONObject2.set("statusCode", "-1");
                        uTSJSONObject2.set("errorCode", "600009");
                        uTSJSONObject2.set("errorMsg", "invalid URL");
                        uTSJSONObject2.set("cause", e2);
                        listener.onComplete(uTSJSONObject2);
                        return null;
                    }
                }
            }
            return builder.build();
        } catch (Exception e3) {
            UTSJSONObject uTSJSONObject3 = new UTSJSONObject();
            uTSJSONObject3.set("statusCode", "-1");
            uTSJSONObject3.set("errorCode", "600009");
            uTSJSONObject3.set("errorMsg", "invalid URL");
            uTSJSONObject3.set("cause", e3);
            listener.onComplete(uTSJSONObject3);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [T, java.lang.String] */
    private final String stringifyQuery(String url, UTSJSONObject data) {
        String str;
        String str2;
        UTSArray<String> uTSArraySplit = StringKt.split(url, "#");
        if (NumberKt.compareTo(uTSArraySplit.getLength(), (Number) 1) > 0) {
            String str3 = uTSArraySplit.get(1);
            Intrinsics.checkNotNullExpressionValue(str3, "str[1]");
            str = str3;
        } else {
            str = "";
        }
        String str4 = uTSArraySplit.get(0);
        Intrinsics.checkNotNullExpressionValue(str4, "str[0]");
        UTSArray<String> uTSArraySplit2 = StringKt.split(str4, Operators.CONDITION_IF_STRING);
        if (NumberKt.compareTo(uTSArraySplit2.getLength(), (Number) 1) > 0) {
            String str5 = uTSArraySplit2.get(1);
            Intrinsics.checkNotNullExpressionValue(str5, "str[1]");
            str2 = str5;
        } else {
            str2 = "";
        }
        String str6 = uTSArraySplit2.get(0);
        Intrinsics.checkNotNullExpressionValue(str6, "str[0]");
        String str7 = str6;
        UTSArray<String> uTSArraySplit3 = StringKt.split(str2, "&");
        final Map map = new Map();
        uTSArraySplit3.forEach(new Function2<String, Number, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.NetworkManager.stringifyQuery.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str8, Number number) {
                invoke2(str8, number);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String item, Number number) {
                Intrinsics.checkNotNullParameter(item, "item");
                Intrinsics.checkNotNullParameter(number, "<anonymous parameter 1>");
                UTSArray<String> uTSArraySplit4 = StringKt.split(item, "=");
                if (NumberKt.compareTo(uTSArraySplit4.getLength(), (Number) 1) > 0) {
                    Map<String, String> map2 = map;
                    String str8 = uTSArraySplit4.get(0);
                    Intrinsics.checkNotNullExpressionValue(str8, "temp[0]");
                    String str9 = uTSArraySplit4.get(1);
                    Intrinsics.checkNotNullExpressionValue(str9, "temp[1]");
                    map2.put(str8, str9);
                }
            }
        });
        data.toMap().forEach(new Function2<Object, String, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.NetworkManager.stringifyQuery.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object obj, String str8) {
                invoke2(obj, str8);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object obj, String key1) {
                Intrinsics.checkNotNullParameter(key1, "key1");
                if (obj == null) {
                    obj = "";
                }
                String strEncodeURIComponent = ObjectKt.encodeURIComponent(key1);
                Intrinsics.checkNotNull(strEncodeURIComponent);
                if ((obj instanceof UTSJSONObject) || (obj instanceof UTSArray)) {
                    Map<String, String> map2 = map;
                    String strEncodeURIComponent2 = ObjectKt.encodeURIComponent(io.dcloud.uts.JSON.stringify(obj));
                    Intrinsics.checkNotNull(strEncodeURIComponent2);
                    map2.put(strEncodeURIComponent, strEncodeURIComponent2);
                    return;
                }
                Map<String, String> map3 = map;
                String strEncodeURIComponent3 = ObjectKt.encodeURIComponent("" + NumberKt.toString_number_nullable(obj, (Number) 10));
                Intrinsics.checkNotNull(strEncodeURIComponent3);
                map3.put(strEncodeURIComponent, strEncodeURIComponent3);
            }
        });
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        map.forEach(new Function2<String, String, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.NetworkManager.stringifyQuery.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str8, String str9) {
                invoke2(str8, str9);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r4v2, types: [T, java.lang.String] */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String value, String key1) {
                Intrinsics.checkNotNullParameter(value, "value");
                Intrinsics.checkNotNullParameter(key1, "key1");
                objectRef.element = objectRef.element + key1 + '=' + value + Typography.amp;
            }
        });
        objectRef.element = StringKt.slice((String) objectRef.element, (Number) 0, (Number) (-1));
        if (((String) objectRef.element).length() > 0) {
            str7 = str7 + Operators.CONDITION_IF + ((String) objectRef.element);
        }
        if (str.length() <= 0) {
            return str7;
        }
        return str7 + '#' + str;
    }

    public UploadTask uploadFile(UploadFileOptions options, NetworkUploadFileListener listener) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return UploadController.INSTANCE.getInstance().uploadFile(options, listener);
    }

    public DownloadTask downloadFile(DownloadFileOptions options, NetworkDownloadFileListener listener) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return DownloadController.INSTANCE.getInstance().downloadFile(options, listener);
    }

    /* compiled from: index.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkManager$Companion;", "", "()V", "connectPool", "Lokhttp3/ConnectionPool;", "instance", "Luts/sdk/modules/DCloudUniNetwork/NetworkManager;", "getInstance", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NetworkManager getInstance() {
            if (NetworkManager.instance == null) {
                NetworkManager.instance = new NetworkManager();
            }
            NetworkManager networkManager = NetworkManager.instance;
            Intrinsics.checkNotNull(networkManager);
            return networkManager;
        }
    }
}
