package uts.sdk.modules.DCloudUniNetwork;

import android.os.Looper;
import androidx.core.app.NotificationCompat;
import io.dcloud.uts.ArrayBuffer;
import io.dcloud.uts.Math;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSArrayKt;
import io.dcloud.uts.UTSJSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import uts.sdk.modules.DCloudUniNetwork.NetworkUtil;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0002J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\rH\u0002J&\u0010\u001e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J\u001e\u0010!\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0#H\u0002J\u001e\u0010$\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0#H\u0002J&\u0010%\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J\u001e\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010 2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J\u001e\u0010)\u001a\u0004\u0018\u00010\r2\b\u0010(\u001a\u0004\u0018\u00010 2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J \u0010*\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020\rH\u0002J\u0018\u0010-\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\rH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/SimpleCallback;", "Lokhttp3/Callback;", "listener", "Luts/sdk/modules/DCloudUniNetwork/NetworkRequestListener;", "requestTask", "Luts/sdk/modules/DCloudUniNetwork/NetworkRequestTaskImpl;", "enableChunked", "", "looper", "Landroid/os/Looper;", "(Luts/sdk/modules/DCloudUniNetwork/NetworkRequestListener;Luts/sdk/modules/DCloudUniNetwork/NetworkRequestTaskImpl;ZLandroid/os/Looper;)V", "isEmptyLine", "lineData", "", "isHexadecimalLine", "isValidChunk", "sizeLine", "dataLine", "isZeroChunk", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "parseHexSize", "", "processChunkedResponse", "inputStream", "Ljava/io/InputStream;", "processLinePair", "pendingLines", "Lio/dcloud/uts/UTSArray;", "processPendingLines", "processStreamWithOkio", "readInputStream", "", "inputSteam", "readInputStreamAsBytes", "sendCombinedData", "data1", "data2", "sendData", "data", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class SimpleCallback implements Callback {
    private boolean enableChunked;
    private NetworkRequestListener listener;
    private Looper looper;
    private NetworkRequestTaskImpl requestTask;

    public SimpleCallback(NetworkRequestListener listener, NetworkRequestTaskImpl requestTask, boolean z, Looper looper) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(requestTask, "requestTask");
        this.listener = listener;
        this.requestTask = requestTask;
        this.enableChunked = z;
        this.looper = looper;
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) throws IOException {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        Headers headers = response.headers();
        Intrinsics.checkNotNullExpressionValue(headers, "response.headers()");
        Map<String, List<String>> headerMap = headers.toMultimap();
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = response.code();
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        uTSJSONObject.set("statusCode", NumberKt.plus(Integer.valueOf(intRef.element), ""));
        NetworkRequestListener networkRequestListener = this.listener;
        if (networkRequestListener != null) {
            Integer numValueOf = Integer.valueOf(intRef.element);
            Intrinsics.checkNotNullExpressionValue(headerMap, "headerMap");
            networkRequestListener.onHeadersReceived(numValueOf, headerMap);
        }
        io.dcloud.uts.Map<Number, Function1<RequestTaskOnHeadersReceivedListenerResult, Unit>> headersReceivedListeners = this.requestTask.getHeadersReceivedListeners();
        if (headersReceivedListeners.size() > 0) {
            NetworkUtil.Companion companion = NetworkUtil.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(headerMap, "headerMap");
            final UTSJSONObject uTSJSONObjectConvertHeaders = companion.convertHeaders(headerMap);
            headersReceivedListeners.forEach(new Function2<Function1<? super RequestTaskOnHeadersReceivedListenerResult, ? extends Unit>, Number, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.SimpleCallback.onResponse.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Function1<? super RequestTaskOnHeadersReceivedListenerResult, ? extends Unit> function1, Number number) {
                    invoke2((Function1<? super RequestTaskOnHeadersReceivedListenerResult, Unit>) function1, number);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Function1<? super RequestTaskOnHeadersReceivedListenerResult, Unit> callbackfn, Number key1) {
                    Intrinsics.checkNotNullParameter(callbackfn, "callbackfn");
                    Intrinsics.checkNotNullParameter(key1, "key1");
                    callbackfn.invoke(new RequestTaskOnHeadersReceivedListenerResult(NetworkUtil.INSTANCE.parseCookie(uTSJSONObjectConvertHeaders), uTSJSONObjectConvertHeaders, Integer.valueOf(intRef.element)));
                }
            });
        }
        ResponseBody responseBodyBody = response.body();
        Intrinsics.checkNotNull(responseBodyBody);
        InputStream inputStreamByteStream = responseBodyBody.byteStream();
        Intrinsics.checkNotNull(inputStreamByteStream);
        boolean z = this.enableChunked && Intrinsics.areEqual(response.header("Transfer-Encoding"), "chunked");
        try {
            if (!response.isSuccessful()) {
                uTSJSONObject.set("errorMsg", readInputStream(inputStreamByteStream, this.listener));
            } else if (z) {
                uTSJSONObject.set("originalData", processStreamWithOkio(this.requestTask, inputStreamByteStream, this.listener));
            } else {
                uTSJSONObject.set("originalData", readInputStreamAsBytes(inputStreamByteStream, this.listener));
            }
            if (networkRequestListener != null) {
                networkRequestListener.onComplete(uTSJSONObject);
            }
        } finally {
            try {
                inputStreamByteStream.close();
            } catch (Exception unused) {
            }
        }
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        NetworkRequestListener networkRequestListener = this.listener;
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        uTSJSONObject.set("statusCode", "-1");
        uTSJSONObject.set("errorCode", "602001");
        uTSJSONObject.set("errorMsg", e.getMessage());
        uTSJSONObject.set("cause", e);
        if (networkRequestListener != null) {
            networkRequestListener.onComplete(uTSJSONObject);
        }
    }

    private final byte[] readInputStreamAsBytes(InputStream inputSteam, NetworkRequestListener listener) throws IOException {
        if (inputSteam == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Number numberPlus = (Number) 0;
        byte[] bArr = new byte[2048];
        while (true) {
            int i = inputSteam.read(bArr, 0, 2048);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
                numberPlus = NumberKt.plus(numberPlus, Integer.valueOf(i));
                if (listener != null) {
                    listener.onProgress(numberPlus);
                }
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private final String readInputStream(InputStream inputSteam, NetworkRequestListener listener) throws IOException {
        if (inputSteam == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputSteam));
        char[] cArr = new char[2048];
        while (true) {
            int i = bufferedReader.read(cArr);
            if (i != -1) {
                sb.append(cArr, 0, i);
                if (listener != null) {
                    listener.onProgress(Integer.valueOf(sb.length()));
                }
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, uts.sdk.modules.DCloudUniNetwork.SharedStreamBuffer] */
    /* JADX WARN: Type inference failed for: r4v0, types: [T, java.util.concurrent.locks.ReentrantLock] */
    /* JADX WARN: Type inference failed for: r7v7, types: [T, io.dcloud.uts.ArrayBuffer] */
    private final byte[] processStreamWithOkio(final NetworkRequestTaskImpl requestTask, final InputStream inputStream, NetworkRequestListener listener) throws Throwable {
        Thread thread;
        Number totalBytesRead;
        byte[] byteArray;
        boolean z;
        Thread thread2 = null;
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new SharedStreamBuffer();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new ReentrantLock();
        try {
            thread = new Thread(new Runnable() { // from class: uts.sdk.modules.DCloudUniNetwork.SimpleCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SimpleCallback.processStreamWithOkio$lambda$0(inputStream, objectRef2, objectRef);
                }
            });
        } catch (Exception unused) {
            thread = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            thread.start();
            do {
                Thread.sleep(100L);
                Integer num = (Number) 0;
                ((ReentrantLock) objectRef2.element).lock();
                try {
                    if (((SharedStreamBuffer) objectRef.element).getHasNewData()) {
                        byteArray = ((SharedStreamBuffer) objectRef.element).getBuffer().toByteArray();
                        totalBytesRead = ((SharedStreamBuffer) objectRef.element).getTotalBytesRead();
                        ((SharedStreamBuffer) objectRef.element).getBuffer().reset();
                        ((SharedStreamBuffer) objectRef.element).setHasNewData(false);
                    } else {
                        totalBytesRead = num;
                        byteArray = null;
                    }
                    z = ((SharedStreamBuffer) objectRef.element).getIsStreamEnded() && !((SharedStreamBuffer) objectRef.element).getHasNewData();
                    if (byteArray != null && byteArray.length > 0) {
                        byteArrayOutputStream.write(byteArray, 0, byteArray.length);
                        if (requestTask.getChunkReceivedListeners().size() > 0) {
                            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                            ArrayBuffer.Companion companion = ArrayBuffer.INSTANCE;
                            ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArray);
                            Intrinsics.checkNotNullExpressionValue(byteBufferWrap, "wrap(bufferData)");
                            objectRef3.element = companion.fromByteBuffer(byteBufferWrap);
                            new RunnableTask(this.looper, new Function0<Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.SimpleCallback.processStreamWithOkio.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    io.dcloud.uts.Map<Number, Function1<RequestTaskOnChunkReceivedListenerResult, Unit>> chunkReceivedListeners = requestTask.getChunkReceivedListeners();
                                    final Ref.ObjectRef<ArrayBuffer> objectRef4 = objectRef3;
                                    chunkReceivedListeners.forEach(new Function2<Function1<? super RequestTaskOnChunkReceivedListenerResult, ? extends Unit>, Number, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.SimpleCallback.processStreamWithOkio.2.1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(2);
                                        }

                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Function1<? super RequestTaskOnChunkReceivedListenerResult, ? extends Unit> function1, Number number) {
                                            invoke2((Function1<? super RequestTaskOnChunkReceivedListenerResult, Unit>) function1, number);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Function1<? super RequestTaskOnChunkReceivedListenerResult, Unit> chunkListener, Number key1) {
                                            Intrinsics.checkNotNullParameter(chunkListener, "chunkListener");
                                            Intrinsics.checkNotNullParameter(key1, "key1");
                                            chunkListener.invoke(new RequestTaskOnChunkReceivedListenerResult(objectRef4.element));
                                        }
                                    });
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }
                            }).execute();
                        }
                        if (listener != null) {
                            listener.onProgress(totalBytesRead);
                        }
                    }
                } finally {
                    ((ReentrantLock) objectRef2.element).unlock();
                }
            } while (!z);
            byteArrayOutputStream.flush();
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            thread.interrupt();
            try {
                thread.join(1000L);
            } catch (InterruptedException unused2) {
            }
            try {
                if (objectRef.element != 0 && ((SharedStreamBuffer) objectRef.element).getBuffer() != null) {
                    ((SharedStreamBuffer) objectRef.element).getBuffer().close();
                }
                byteArrayOutputStream.close();
            } catch (Exception unused3) {
            }
            return byteArray2;
        } catch (Exception unused4) {
            if (thread != null) {
                thread.interrupt();
                try {
                    thread.join(1000L);
                } catch (InterruptedException unused5) {
                }
            }
            try {
                if (objectRef.element != 0 && ((SharedStreamBuffer) objectRef.element).getBuffer() != null) {
                    ((SharedStreamBuffer) objectRef.element).getBuffer().close();
                }
                byteArrayOutputStream.close();
            } catch (Exception unused6) {
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            thread2 = thread;
            if (thread2 != null) {
                thread2.interrupt();
                try {
                    thread2.join(1000L);
                } catch (InterruptedException unused7) {
                }
            }
            try {
                if (objectRef.element != 0 && ((SharedStreamBuffer) objectRef.element).getBuffer() != null) {
                    ((SharedStreamBuffer) objectRef.element).getBuffer().close();
                }
                byteArrayOutputStream.close();
                throw th;
            } catch (Exception unused8) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void processStreamWithOkio$lambda$0(InputStream inputStream, Ref.ObjectRef<ReentrantLock> objectRef, Ref.ObjectRef<SharedStreamBuffer> objectRef2) {
        BufferedSource bufferedSourceBuffer;
        try {
            bufferedSourceBuffer = Okio.buffer(Okio.source(inputStream));
        } catch (Exception unused) {
            objectRef.element.lock();
            try {
                objectRef2.element.setStreamEnded(true);
                return;
            } finally {
            }
        }
        while (!Thread.interrupted()) {
            if (bufferedSourceBuffer.exhausted()) {
                objectRef.element.lock();
                try {
                    objectRef2.element.setStreamEnded(true);
                    objectRef.element.unlock();
                    return;
                } finally {
                }
            }
            byte[] bArr = new byte[8192];
            int i = bufferedSourceBuffer.read(bArr);
            if (i <= 0) {
                objectRef.element.lock();
                try {
                    objectRef2.element.setStreamEnded(true);
                    objectRef.element.unlock();
                    return;
                } finally {
                }
            }
            objectRef.element.lock();
            try {
                objectRef2.element.getBuffer().write(bArr, 0, i);
                SharedStreamBuffer sharedStreamBuffer = objectRef2.element;
                sharedStreamBuffer.setTotalBytesRead(NumberKt.plus(sharedStreamBuffer.getTotalBytesRead(), Integer.valueOf(i)));
                objectRef2.element.setHasNewData(true);
                objectRef.element.unlock();
            } finally {
            }
            objectRef.element.lock();
            objectRef2.element.setStreamEnded(true);
            return;
        }
    }

    private final byte[] processChunkedResponse(NetworkRequestTaskImpl requestTask, InputStream inputStream, NetworkRequestListener listener) throws IOException {
        int i;
        int i2;
        int i3;
        InputStream inputStream2 = inputStream;
        NetworkRequestListener networkRequestListener = listener;
        if (inputStream2 == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        byte[] bArr = new byte[10];
        int i4 = 0;
        Number numberPlus = (Number) 0;
        UTSArray<byte[]> uTSArray = new UTSArray<>();
        Integer.valueOf(0);
        int i5 = -1;
        byte[] bArr2 = null;
        boolean z = false;
        int i6 = -1;
        while (true) {
            try {
                try {
                    Integer numValueOf = Integer.valueOf(inputStream2.read(bArr));
                    if (NumberKt.numberEquals(numValueOf, Integer.valueOf(i5))) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, i4, numValueOf.intValue());
                    numberPlus = NumberKt.plus(numberPlus, numValueOf);
                    if (networkRequestListener != null) {
                        networkRequestListener.onProgress(numberPlus);
                    }
                    if (requestTask.getChunkReceivedListeners().size() > 0) {
                        SimpleCallback simpleCallback = this;
                        Integer numValueOf2 = Integer.valueOf(i4);
                        while (NumberKt.compareTo(numValueOf2, numValueOf) < 0) {
                            int iIntValue = NumberKt.and(Byte.valueOf(bArr[numValueOf2.intValue()]), (Number) 255).intValue();
                            byteArrayOutputStream2.write(iIntValue);
                            if (i6 == 13 && iIntValue == 10) {
                                byte[] lineData = byteArrayOutputStream2.toByteArray();
                                Intrinsics.checkNotNullExpressionValue(lineData, "lineData");
                                if (isZeroChunk(lineData) && !z) {
                                    bArr2 = new byte[lineData.length];
                                    System.arraycopy(lineData, 0, bArr2, 0, lineData.length);
                                    i3 = 0;
                                    z = true;
                                } else if (z) {
                                    processPendingLines(requestTask, uTSArray);
                                    uTSArray = new UTSArray<>();
                                    if (isEmptyLine(lineData)) {
                                        Intrinsics.checkNotNull(bArr2);
                                        sendCombinedData(requestTask, bArr2, lineData);
                                    } else {
                                        byte[] bArr3 = new byte[lineData.length];
                                        System.arraycopy(lineData, 0, bArr3, 0, lineData.length);
                                        Intrinsics.checkNotNull(bArr2);
                                        processLinePair(requestTask, UTSArrayKt._uA(bArr2, bArr3));
                                    }
                                    i3 = 0;
                                    z = false;
                                    bArr2 = null;
                                } else {
                                    byte[] bArr4 = new byte[lineData.length];
                                    i3 = 0;
                                    System.arraycopy(lineData, 0, bArr4, 0, lineData.length);
                                    uTSArray.push(bArr4);
                                    if (NumberKt.numberEquals(uTSArray.getLength(), 2)) {
                                        processLinePair(requestTask, uTSArray);
                                        uTSArray = new UTSArray<>();
                                    }
                                }
                                byteArrayOutputStream2.reset();
                            } else {
                                i3 = 0;
                            }
                            numValueOf2 = NumberKt.inc(numValueOf2);
                            i6 = iIntValue;
                            i4 = i3;
                        }
                        i = i4;
                        i2 = -1;
                    } else {
                        i = i4;
                        i2 = i5;
                    }
                    if (NumberKt.numberEquals(numValueOf, Integer.valueOf(i2))) {
                        break;
                    }
                    i5 = i2;
                    i4 = i;
                    inputStream2 = inputStream;
                    networkRequestListener = listener;
                } catch (Exception unused) {
                    byteArrayOutputStream.close();
                    byteArrayOutputStream2.close();
                    return null;
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                        byteArrayOutputStream2.close();
                    } catch (Exception unused2) {
                    }
                    throw th;
                }
            } catch (Exception unused3) {
                return null;
            }
        }
        processPendingLines(requestTask, uTSArray);
        if (z && bArr2 != null) {
            sendData(requestTask, bArr2);
        }
        if (requestTask.getChunkReceivedListeners().size() > 0 && byteArrayOutputStream2.size() > 0) {
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "lineBuffer.toByteArray()");
            sendData(requestTask, byteArray);
        }
        byteArrayOutputStream.flush();
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
            byteArrayOutputStream2.close();
        } catch (Exception unused4) {
        }
        return byteArray2;
    }

    private final boolean isValidChunk(byte[] sizeLine, byte[] dataLine) {
        int hexSize;
        return isHexadecimalLine(sizeLine) && (hexSize = parseHexSize(sizeLine)) >= 0 && NumberKt.compareTo(Math.abs(Integer.valueOf((dataLine.length - 2) - hexSize)), (Number) 2) <= 0;
    }

    private final int parseHexSize(byte[] lineData) {
        if (lineData.length <= 2) {
            return -1;
        }
        int length = lineData.length - 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length && lineData[i] != 59; i++) {
            sb.append(StringKt.fromCharCode(StringCompanionObject.INSTANCE, Integer.valueOf(lineData[i])));
        }
        if (sb.length() == 0) {
            return -1;
        }
        try {
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "hexString.toString()");
            return Integer.parseInt(StringsKt.trim((CharSequence) string).toString(), 16);
        } catch (Exception unused) {
            return -1;
        }
    }

    private final boolean isHexadecimalLine(byte[] lineData) {
        int length;
        if (lineData.length <= 2 || (length = lineData.length - 2) <= 0) {
            return false;
        }
        int i = 0;
        boolean z = false;
        while (i < length) {
            byte b = lineData[i];
            if (b == 32 || b == 59) {
                break;
            }
            if ((b < 48 || b > 57) && ((b < 65 || b > 70) && (b < 97 || b > 102))) {
                return false;
            }
            i++;
            z = true;
        }
        return z;
    }

    private final boolean isZeroChunk(byte[] lineData) {
        if (lineData.length < 3 || lineData[0] != 48) {
            return false;
        }
        byte b = lineData[1];
        if (b != 59 && b != 13) {
            return false;
        }
        if (b == 13 && lineData.length >= 3) {
            return lineData[2] == 10;
        }
        if (b == 59) {
            for (int i = 2; i < lineData.length - 1; i++) {
                if (lineData[i] == 13 && lineData[i + 1] == 10) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isEmptyLine(byte[] lineData) {
        return lineData.length == 2 && lineData[0] == 13 && lineData[1] == 10;
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [T, io.dcloud.uts.ArrayBuffer] */
    private final void sendData(NetworkRequestTaskImpl requestTask, byte[] data) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ArrayBuffer.Companion companion = ArrayBuffer.INSTANCE;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(data);
        Intrinsics.checkNotNullExpressionValue(byteBufferWrap, "wrap(data)");
        objectRef.element = companion.fromByteBuffer(byteBufferWrap);
        requestTask.getChunkReceivedListeners().forEach(new Function2<Function1<? super RequestTaskOnChunkReceivedListenerResult, ? extends Unit>, Number, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.SimpleCallback.sendData.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super RequestTaskOnChunkReceivedListenerResult, ? extends Unit> function1, Number number) {
                invoke2((Function1<? super RequestTaskOnChunkReceivedListenerResult, Unit>) function1, number);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super RequestTaskOnChunkReceivedListenerResult, Unit> chunkListener, Number key1) {
                Intrinsics.checkNotNullParameter(chunkListener, "chunkListener");
                Intrinsics.checkNotNullParameter(key1, "key1");
                chunkListener.invoke(new RequestTaskOnChunkReceivedListenerResult(objectRef.element));
            }
        });
    }

    private final void sendCombinedData(NetworkRequestTaskImpl requestTask, byte[] data1, byte[] data2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(data1, 0, data1.length);
            byteArrayOutputStream.write(data2, 0, data2.length);
            byte[] combinedData = byteArrayOutputStream.toByteArray();
            Intrinsics.checkNotNullExpressionValue(combinedData, "combinedData");
            sendData(requestTask, combinedData);
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    private final void processPendingLines(NetworkRequestTaskImpl requestTask, UTSArray<byte[]> pendingLines) {
        if (NumberKt.numberEquals(pendingLines.getLength(), 0)) {
            return;
        }
        for (Number numberInc = (Number) 0; NumberKt.compareTo(numberInc, pendingLines.getLength()) < 0; numberInc = NumberKt.inc(numberInc)) {
            sendData(requestTask, pendingLines.get(numberInc));
        }
    }

    private final void processLinePair(NetworkRequestTaskImpl requestTask, UTSArray<byte[]> pendingLines) throws IOException {
        if (NumberKt.numberEquals(pendingLines.getLength(), 2)) {
            byte[] bArr = pendingLines.get(0);
            Intrinsics.checkNotNullExpressionValue(bArr, "pendingLines[0]");
            byte[] bArr2 = pendingLines.get(1);
            Intrinsics.checkNotNullExpressionValue(bArr2, "pendingLines[1]");
            if (isValidChunk(bArr, bArr2)) {
                byte[] bArr3 = pendingLines.get(0);
                Intrinsics.checkNotNullExpressionValue(bArr3, "pendingLines[0]");
                byte[] bArr4 = pendingLines.get(1);
                Intrinsics.checkNotNullExpressionValue(bArr4, "pendingLines[1]");
                sendCombinedData(requestTask, bArr3, bArr4);
                return;
            }
            processPendingLines(requestTask, pendingLines);
        }
    }
}
