package io.dcloud.feature.weex.adapter;

import android.text.TextUtils;
import android.util.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.taobao.weex.appfram.websocket.WebSocketCloseCodes;
import dc.squareup.okhttp3.ConnectionPool;
import dc.squareup.okhttp3.Headers;
import dc.squareup.okhttp3.OkHttpClient;
import dc.squareup.okhttp3.Request;
import dc.squareup.okhttp3.Response;
import dc.squareup.okhttp3.WebSocket;
import dc.squareup.okhttp3.WebSocketListener;
import dc.squareup.okio.ByteString;
import io.dcloud.common.adapter.util.DCloudTrustManager;
import io.dcloud.common.util.ThrottleUtil;
import java.io.EOFException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes3.dex */
public class DefaultWebSocketAdapter implements IWebSocketAdapter {
    private static ConnectionPool mConnectPool;
    private IWebSocketAdapter.EventListener eventListener;
    private ThrottleUtil throttleUtil = null;
    private WebSocket ws;

    private void reportError(String str) {
        IWebSocketAdapter.EventListener eventListener = this.eventListener;
        if (eventListener != null) {
            eventListener.onError(str);
        }
    }

    @Override // com.taobao.weex.appfram.websocket.IWebSocketAdapter
    public void close(int i, String str) {
        WebSocket webSocket = this.ws;
        if (webSocket != null) {
            try {
                webSocket.close(i, str);
            } catch (Exception e) {
                e.printStackTrace();
                reportError(e.getMessage());
            }
        }
    }

    @Override // com.taobao.weex.appfram.websocket.IWebSocketAdapter
    public void connect(String str, String str2, IWebSocketAdapter.EventListener eventListener) {
        this.eventListener = eventListener;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
            if (sSLSocketFactory != null) {
                builder.sslSocketFactory(sSLSocketFactory);
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        TimeUnit timeUnit = TimeUnit.HOURS;
        builder.readTimeout(24L, timeUnit);
        builder.writeTimeout(24L, timeUnit);
        builder.hostnameVerifier(DCloudTrustManager.getHostnameVerifier(false));
        Request.Builder builder2 = new Request.Builder();
        if (!TextUtils.isEmpty(str2)) {
            builder2.addHeader(IWebSocketAdapter.HEADER_SEC_WEBSOCKET_PROTOCOL, str2);
        }
        builder2.addHeader("Origin", "http://localhost");
        builder2.url(str);
        builder.build().newWebSocket(builder2.build(), new WebSocketListener() { // from class: io.dcloud.feature.weex.adapter.DefaultWebSocketAdapter.1
            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onClosed(WebSocket webSocket, final int i, final String str3) {
                super.onClosed(webSocket, i, str3);
                if (DefaultWebSocketAdapter.this.throttleUtil == null) {
                    DefaultWebSocketAdapter.this.throttleUtil = new ThrottleUtil();
                }
                DefaultWebSocketAdapter.this.throttleUtil.throttlePost(new Runnable() { // from class: io.dcloud.feature.weex.adapter.DefaultWebSocketAdapter.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultWebSocketAdapter.this.eventListener.onClose(i, str3, true);
                    }
                }, 10L);
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onClosing(WebSocket webSocket, final int i, final String str3) {
                super.onClosing(webSocket, i, str3);
                if (DefaultWebSocketAdapter.this.throttleUtil == null) {
                    DefaultWebSocketAdapter.this.throttleUtil = new ThrottleUtil();
                }
                DefaultWebSocketAdapter.this.throttleUtil.throttlePost(new Runnable() { // from class: io.dcloud.feature.weex.adapter.DefaultWebSocketAdapter.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultWebSocketAdapter.this.eventListener.onClose(i, str3, true);
                    }
                }, 10L);
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                super.onFailure(webSocket, th, response);
                if (!(th instanceof EOFException)) {
                    DefaultWebSocketAdapter.this.eventListener.onError(th.getMessage());
                    return;
                }
                IWebSocketAdapter.EventListener eventListener2 = DefaultWebSocketAdapter.this.eventListener;
                WebSocketCloseCodes webSocketCloseCodes = WebSocketCloseCodes.CLOSE_NORMAL;
                eventListener2.onClose(webSocketCloseCodes.getCode(), webSocketCloseCodes.name(), true);
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, String str3) {
                super.onMessage(webSocket, str3);
                DefaultWebSocketAdapter.this.eventListener.onMessage(str3);
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                DefaultWebSocketAdapter.this.ws = webSocket;
                DefaultWebSocketAdapter.this.eventListener.onOpen();
                Headers headers = response.headers();
                HashMap map = new HashMap();
                for (String str3 : headers.names()) {
                    map.put(str3, headers.values(str3).toString());
                }
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, ByteString byteString) {
                super.onMessage(webSocket, byteString);
                String strEncodeToString = Base64.encodeToString(byteString.toByteArray(), 2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("@type", (Object) "binary");
                jSONObject.put("base64", (Object) strEncodeToString);
                DefaultWebSocketAdapter.this.eventListener.onMessage(jSONObject.toJSONString());
            }
        });
    }

    @Override // com.taobao.weex.appfram.websocket.IWebSocketAdapter
    public void destroy() {
        WebSocket webSocket = this.ws;
        if (webSocket != null) {
            try {
                WebSocketCloseCodes webSocketCloseCodes = WebSocketCloseCodes.CLOSE_GOING_AWAY;
                webSocket.close(webSocketCloseCodes.getCode(), webSocketCloseCodes.name());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.taobao.weex.appfram.websocket.IWebSocketAdapter
    public void send(String str) {
        try {
            JSONObject object = JSON.parseObject(str);
            String string = object.getString("@type");
            if (string != null && string.equals("binary") && object.containsKey("base64")) {
                send(object);
                return;
            }
        } catch (Exception unused) {
        }
        WebSocket webSocket = this.ws;
        if (webSocket == null) {
            reportError("WebSocket is not ready");
            return;
        }
        try {
            webSocket.send(str);
        } catch (Exception e) {
            e.printStackTrace();
            reportError(e.getMessage());
        }
    }

    public void send(JSONObject jSONObject) {
        byte[] bArrDecode;
        if (this.ws != null) {
            try {
                String string = jSONObject.getString("@type");
                if (string != null && string.equals("binary") && jSONObject.containsKey("base64") && (bArrDecode = Base64.decode(jSONObject.getString("base64"), 0)) != null) {
                    this.ws.send(ByteString.of(bArrDecode));
                } else {
                    reportError("some error occur");
                }
                return;
            } catch (Exception e) {
                reportError(e.getMessage());
                return;
            }
        }
        reportError("WebSocket is not ready");
    }

    @Override // com.taobao.weex.appfram.websocket.IWebSocketAdapter
    public void connect(String str, String str2, String str3, IWebSocketAdapter.EventListener eventListener) {
        Map map;
        this.eventListener = eventListener;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
            if (sSLSocketFactory != null) {
                builder.sslSocketFactory(sSLSocketFactory);
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        TimeUnit timeUnit = TimeUnit.HOURS;
        builder.readTimeout(24L, timeUnit);
        builder.writeTimeout(24L, timeUnit);
        if (mConnectPool == null) {
            mConnectPool = new ConnectionPool();
        }
        builder.connectionPool(mConnectPool);
        boolean z = false;
        builder.hostnameVerifier(DCloudTrustManager.getHostnameVerifier(false));
        Request.Builder builder2 = new Request.Builder();
        if (!TextUtils.isEmpty(str2)) {
            builder2.addHeader(IWebSocketAdapter.HEADER_SEC_WEBSOCKET_PROTOCOL, str2);
        }
        try {
            if (!TextUtils.isEmpty(str3) && (map = (Map) JSON.parse(str3)) != null) {
                for (Map.Entry entry : map.entrySet()) {
                    String str4 = (String) entry.getKey();
                    String str5 = (String) entry.getValue();
                    if ("Origin".equals(str4)) {
                        z = true;
                    }
                    if (!TextUtils.isEmpty(str4)) {
                        builder2.addHeader(str4, str5);
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (!z) {
            builder2.addHeader("Origin", "http://localhost");
        }
        builder2.url(str);
        builder.build().newWebSocket(builder2.build(), new WebSocketListener() { // from class: io.dcloud.feature.weex.adapter.DefaultWebSocketAdapter.2
            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onClosed(WebSocket webSocket, final int i, final String str6) {
                super.onClosed(webSocket, i, str6);
                if (DefaultWebSocketAdapter.this.throttleUtil == null) {
                    DefaultWebSocketAdapter.this.throttleUtil = new ThrottleUtil();
                }
                DefaultWebSocketAdapter.this.throttleUtil.throttlePost(new Runnable() { // from class: io.dcloud.feature.weex.adapter.DefaultWebSocketAdapter.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultWebSocketAdapter.this.eventListener.onClose(i, str6, true);
                    }
                }, 10L);
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onClosing(WebSocket webSocket, final int i, final String str6) {
                super.onClosing(webSocket, i, str6);
                if (DefaultWebSocketAdapter.this.throttleUtil == null) {
                    DefaultWebSocketAdapter.this.throttleUtil = new ThrottleUtil();
                }
                DefaultWebSocketAdapter.this.throttleUtil.throttlePost(new Runnable() { // from class: io.dcloud.feature.weex.adapter.DefaultWebSocketAdapter.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DefaultWebSocketAdapter.this.eventListener.onClose(i, str6, true);
                    }
                }, 10L);
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onFailure(WebSocket webSocket, Throwable th, Response response) {
                super.onFailure(webSocket, th, response);
                if (!(th instanceof EOFException)) {
                    DefaultWebSocketAdapter.this.eventListener.onError(th.getMessage());
                    return;
                }
                IWebSocketAdapter.EventListener eventListener2 = DefaultWebSocketAdapter.this.eventListener;
                WebSocketCloseCodes webSocketCloseCodes = WebSocketCloseCodes.CLOSE_NORMAL;
                eventListener2.onClose(webSocketCloseCodes.getCode(), webSocketCloseCodes.name(), true);
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, String str6) {
                super.onMessage(webSocket, str6);
                DefaultWebSocketAdapter.this.eventListener.onMessage(str6);
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                DefaultWebSocketAdapter.this.ws = webSocket;
                DefaultWebSocketAdapter.this.eventListener.onOpen();
                Headers headers = response.headers();
                HashMap map2 = new HashMap();
                for (String str6 : headers.names()) {
                    map2.put(str6, headers.values(str6).toString());
                }
            }

            @Override // dc.squareup.okhttp3.WebSocketListener
            public void onMessage(WebSocket webSocket, ByteString byteString) {
                super.onMessage(webSocket, byteString);
                String strEncodeToString = Base64.encodeToString(byteString.toByteArray(), 2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("@type", (Object) "binary");
                jSONObject.put("base64", (Object) strEncodeToString);
                DefaultWebSocketAdapter.this.eventListener.onMessage(jSONObject.toJSONString());
            }
        });
    }
}
