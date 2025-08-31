package uts.sdk.modules.DCloudUniNetwork;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uniapp.SourceError;
import io.dcloud.uts.Map;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSIteratorKt;
import io.dcloud.uts.UTSJSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\bH\u0002J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\bH\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/UploadController;", "", "()V", "uploadExecutorService", "Ljava/util/concurrent/ExecutorService;", "checkPrivatePath", "", "reassignedPath", "", "copyAssetFileToPrivateDir", "Ljava/io/File;", "context", "Landroid/content/Context;", "fileName", "createUploadClient", "Lokhttp3/OkHttpClient;", AbsoluteConst.JSON_KEY_OPTION, "Luts/sdk/modules/DCloudUniNetwork/UploadFileOptions;", "createUploadRequest", "Lokhttp3/Request;", "options", "listener", "Luts/sdk/modules/DCloudUniNetwork/NetworkUploadFileListener;", "getFileInformation", "Luts/sdk/modules/DCloudUniNetwork/FileInformation;", "reassignedUri", "getMimeType", AbsoluteConst.JSON_KEY_FILENAME, "isAssetFile", "filePath", "uploadFile", "Luts/sdk/modules/DCloudUniNetwork/UploadTask;", "Companion", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UploadController {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static UploadController instance;
    private ExecutorService uploadExecutorService;

    public UploadTask uploadFile(UploadFileOptions options, NetworkUploadFileListener listener) throws IOException {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(listener, "listener");
        OkHttpClient okHttpClientCreateUploadClient = createUploadClient(options);
        Request requestCreateUploadRequest = createUploadRequest(options, listener);
        if (requestCreateUploadRequest == null) {
            return new NetworkUploadTaskImpl(null, listener);
        }
        Call callNewCall = okHttpClientCreateUploadClient.newCall(requestCreateUploadRequest);
        Intrinsics.checkNotNullExpressionValue(callNewCall, "client.newCall(request)");
        callNewCall.enqueue(new SimpleUploadCallback(listener));
        return new NetworkUploadTaskImpl(callNewCall, listener);
    }

    private final OkHttpClient createUploadClient(UploadFileOptions option) {
        long jLongValue;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (option.getTimeout() != null) {
            Number timeout = option.getTimeout();
            Intrinsics.checkNotNull(timeout);
            jLongValue = timeout.longValue();
        } else {
            jLongValue = 120000;
        }
        builder.connectTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.readTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.writeTimeout(jLongValue, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new CookieInterceptor());
        if (this.uploadExecutorService == null) {
            this.uploadExecutorService = Executors.newFixedThreadPool(10);
        }
        builder.dispatcher(new Dispatcher(this.uploadExecutorService));
        OkHttpClient okHttpClientBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(okHttpClientBuild, "clientBuilder.build()");
        return okHttpClientBuild;
    }

    private final Request createUploadRequest(UploadFileOptions options, NetworkUploadFileListener listener) throws IOException {
        Request.Builder builder;
        String str;
        String str2;
        InputStream inputStream;
        UploadController uploadController = this;
        Request.Builder builder2 = new Request.Builder();
        try {
            builder2.url(options.getUrl());
            MultipartBody.Builder type = new MultipartBody.Builder("----" + UUID.randomUUID()).setType(MultipartBody.FORM);
            UTSJSONObject formData = options.getFormData();
            Map<String, Object> map = formData != null ? formData.toMap() : null;
            int i = 10;
            String str3 = "";
            if (map != null) {
                for (Map.Entry entry : ((java.util.Map) UTSIteratorKt.resolveUTSKeyIterator(map)).entrySet()) {
                    String str4 = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        type.addFormDataPart(str4, "" + NumberKt.toString_number_nullable(value, Integer.valueOf(i)));
                        i = 10;
                    }
                }
            }
            UTSArray<UploadFileOptionFiles> files = options.getFiles();
            String str5 = "*/*";
            String str6 = "file";
            if (files == null || NumberKt.compareTo(files.getLength(), (Number) 0) <= 0) {
                builder = builder2;
                str = "";
                String filePath = options.getFilePath();
                if (filePath == null) {
                    UTSJSONObject uTSJSONObject = new UTSJSONObject();
                    uTSJSONObject.set("statusCode", "-1");
                    uTSJSONObject.set("errorCode", "-1");
                    uTSJSONObject.set("errorMsg", "filePath is null");
                    uTSJSONObject.set("cause", null);
                    if (listener != null) {
                        listener.onComplete(uTSJSONObject);
                    }
                    return null;
                }
                FileInformation fileInformation = getFileInformation(filePath);
                String name = options.getName();
                String str7 = name == null ? "file" : name;
                InputStream inputStream2 = fileInformation != null ? fileInformation.getInputStream() : null;
                if (fileInformation != null && inputStream2 != null) {
                    String mime = fileInformation.getMime();
                    MediaType mediaType = MediaType.parse(mime == null ? "*/*" : mime);
                    Intrinsics.checkNotNull(mediaType);
                    type.addFormDataPart(str7, fileInformation.getName(), new InputStreamRequestBody(mediaType, fileInformation.getSize(), inputStream2));
                } else {
                    UTSJSONObject uTSJSONObject2 = new UTSJSONObject();
                    uTSJSONObject2.set("statusCode", "-1");
                    uTSJSONObject2.set("errorCode", "-1");
                    uTSJSONObject2.set("errorMsg", "Illegal file");
                    uTSJSONObject2.set("cause", null);
                    if (listener != null) {
                        listener.onComplete(uTSJSONObject2);
                    }
                    return null;
                }
            } else {
                Number numberInc = (Number) 0;
                while (NumberKt.compareTo(numberInc, files.getLength()) < 0) {
                    UploadFileOptionFiles uploadFileOptionFiles = files.get(numberInc);
                    UTSArray<UploadFileOptionFiles> uTSArray = files;
                    FileInformation fileInformation2 = uploadController.getFileInformation(uploadFileOptionFiles.getUri());
                    String name2 = uploadFileOptionFiles.getName();
                    if (name2 == null) {
                        name2 = str6;
                    }
                    if (fileInformation2 != null) {
                        str2 = str5;
                        inputStream = fileInformation2.getInputStream();
                    } else {
                        str2 = str5;
                        inputStream = null;
                    }
                    if (fileInformation2 != null && inputStream != null) {
                        String str8 = str6;
                        String mime2 = fileInformation2.getMime();
                        String str9 = str3;
                        if (mime2 == null) {
                            mime2 = str2;
                        }
                        MediaType mediaType2 = MediaType.parse(mime2);
                        Intrinsics.checkNotNull(mediaType2);
                        type.addFormDataPart(name2, fileInformation2.getName(), new InputStreamRequestBody(mediaType2, fileInformation2.getSize(), inputStream));
                        numberInc = NumberKt.inc(numberInc);
                        uploadController = this;
                        files = uTSArray;
                        str5 = str2;
                        str6 = str8;
                        builder2 = builder2;
                        str3 = str9;
                    } else {
                        UTSJSONObject uTSJSONObject3 = new UTSJSONObject();
                        uTSJSONObject3.set("statusCode", "-1");
                        uTSJSONObject3.set("errorCode", "-1");
                        uTSJSONObject3.set("errorMsg", "Illegal file");
                        uTSJSONObject3.set("cause", null);
                        if (listener != null) {
                            listener.onComplete(uTSJSONObject3);
                        }
                        return null;
                    }
                }
                builder = builder2;
                str = str3;
            }
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Context appContext = UTSAndroid.INSTANCE.getAppContext();
            Intrinsics.checkNotNull(appContext);
            Request.Builder builder3 = builder;
            builder3.header(IWebview.USER_AGENT, NumberKt.toString_number_nullable$default(uTSAndroid.getWebViewInfo(appContext).get("ua"), (Number) null, 1, (Object) null));
            UTSJSONObject header = options.getHeader();
            io.dcloud.uts.Map<String, Object> map2 = header != null ? header.toMap() : null;
            if (map2 != null) {
                for (Map.Entry entry2 : ((java.util.Map) UTSIteratorKt.resolveUTSKeyIterator(map2)).entrySet()) {
                    String str10 = (String) entry2.getKey();
                    Object value2 = entry2.getValue();
                    if (value2 != null) {
                        builder3.addHeader(str10, str + NumberKt.toString_number_nullable(value2, (Number) 10));
                    }
                }
            }
            MultipartBody multipartBodyBuild = type.build();
            Intrinsics.checkNotNullExpressionValue(multipartBodyBuild, "multiPartBody.build()");
            builder3.post(new ProgressRequestBody(multipartBodyBuild, new NetworkUploadProgressListener(listener)));
            return builder3.build();
        } catch (Exception e) {
            UTSJSONObject uTSJSONObject4 = new UTSJSONObject();
            uTSJSONObject4.set("statusCode", "-1");
            uTSJSONObject4.set("errorCode", "600009");
            uTSJSONObject4.set("errorMsg", "invalid URL");
            uTSJSONObject4.set("cause", new SourceError(NumberKt.toString_number_nullable$default(e.getCause(), (Number) null, 1, (Object) null)));
            if (listener != null) {
                listener.onComplete(uTSJSONObject4);
            }
            return null;
        }
    }

    private final FileInformation getFileInformation(String reassignedUri) throws IOException {
        String strConvert2AbsFullPath;
        if (StringsKt.startsWith$default(reassignedUri, "content://", false, 2, (Object) null)) {
            Uri uri = Uri.parse(reassignedUri);
            Context appContext = UTSAndroid.INSTANCE.getAppContext();
            Intrinsics.checkNotNull(appContext);
            Cursor cursorQuery = appContext.getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery == null) {
                return null;
            }
            cursorQuery.moveToFirst();
            FileInformation fileInformation = new FileInformation();
            fileInformation.setInputStream(appContext.getContentResolver().openInputStream(uri));
            fileInformation.setSize(cursorQuery.getInt(cursorQuery.getColumnIndex("_size")));
            fileInformation.setName(cursorQuery.getString(cursorQuery.getColumnIndex("_display_name")));
            fileInformation.setMime(cursorQuery.getString(cursorQuery.getColumnIndex("mime_type")));
            cursorQuery.close();
            return fileInformation;
        }
        if (StringsKt.startsWith$default(reassignedUri, DeviceInfo.FILE_PROTOCOL, false, 2, (Object) null)) {
            strConvert2AbsFullPath = StringKt.substring$default(reassignedUri, (Number) 7, null, 2, null);
        } else if (StringsKt.startsWith$default(reassignedUri, "unifile://", false, 2, (Object) null)) {
            strConvert2AbsFullPath = UTSAndroid.INSTANCE.convert2AbsFullPath(reassignedUri);
        } else {
            strConvert2AbsFullPath = UTSAndroid.INSTANCE.convert2AbsFullPath(reassignedUri);
            if (StringsKt.startsWith$default(strConvert2AbsFullPath, "/android_asset/", false, 2, (Object) null)) {
                String strReplace = StringKt.replace(strConvert2AbsFullPath, "/android_asset/", "");
                Context appContext2 = UTSAndroid.INSTANCE.getAppContext();
                Intrinsics.checkNotNull(appContext2);
                Intrinsics.checkNotNull(appContext2);
                File fileCopyAssetFileToPrivateDir = copyAssetFileToPrivateDir(appContext2, strReplace);
                if (fileCopyAssetFileToPrivateDir != null) {
                    strConvert2AbsFullPath = fileCopyAssetFileToPrivateDir.getPath();
                    Intrinsics.checkNotNullExpressionValue(strConvert2AbsFullPath, "apkFile.getPath()");
                }
            }
        }
        File file = new File(strConvert2AbsFullPath);
        FileInputStream fileInputStream = new FileInputStream(file);
        long length = file.length();
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        String mimeType = getMimeType(name);
        FileInformation fileInformation2 = new FileInformation();
        fileInformation2.setInputStream(fileInputStream);
        fileInformation2.setSize(length);
        fileInformation2.setName(name);
        fileInformation2.setMime(mimeType);
        return fileInformation2;
    }

    private final File copyAssetFileToPrivateDir(Context context, String fileName) throws IOException {
        try {
            File file = new File(context.getCacheDir().getPath() + "/uploadFiles/" + fileName);
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            InputStream inputStreamOpen = context.getAssets().open(fileName);
            Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.getAssets().open(fileName)");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    inputStreamOpen.close();
                    fileOutputStream.close();
                    return file;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final boolean checkPrivatePath(String reassignedPath) {
        String parent;
        if (Build.VERSION.SDK_INT > 29 && Environment.isExternalStorageManager()) {
            return true;
        }
        if (StringsKt.startsWith$default(reassignedPath, DeviceInfo.FILE_PROTOCOL, false, 2, (Object) null)) {
            reassignedPath = StringKt.replace(reassignedPath, DeviceInfo.FILE_PROTOCOL, "");
        }
        Context appContext = UTSAndroid.INSTANCE.getAppContext();
        Intrinsics.checkNotNull(appContext);
        File externalCacheDir = appContext.getExternalCacheDir();
        if (externalCacheDir == null) {
            parent = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + appContext.getPackageName();
        } else {
            parent = externalCacheDir.getParent();
            Intrinsics.checkNotNullExpressionValue(parent, "cache.getParent()");
        }
        String sPrivateDir = appContext.getFilesDir().getParent();
        if (StringsKt.startsWith$default(parent, "/", false, 2, (Object) null) && !StringsKt.startsWith$default(reassignedPath, "/", false, 2, (Object) null)) {
            reassignedPath = "/" + reassignedPath;
        }
        String str = reassignedPath;
        Intrinsics.checkNotNullExpressionValue(sPrivateDir, "sPrivateDir");
        return StringsKt.contains$default((CharSequence) str, (CharSequence) sPrivateDir, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) parent, false, 2, (Object) null) || isAssetFile(reassignedPath) || Build.VERSION.SDK_INT < 29;
    }

    private final boolean isAssetFile(String filePath) {
        return StringsKt.startsWith$default(filePath, "apps/", false, 2, (Object) null) || StringsKt.startsWith$default(filePath, "/android_asset/", false, 2, (Object) null) || StringsKt.startsWith$default(filePath, "android_asset/", false, 2, (Object) null);
    }

    private final String getMimeType(String filename) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(filename);
        if (fileExtensionFromUrl == null && NumberKt.compareTo(StringKt.lastIndexOf$default(filename, Operators.DOT_STR, null, 2, null), (Number) 0) >= 0) {
            fileExtensionFromUrl = StringKt.substring$default(filename, NumberKt.plus(StringKt.lastIndexOf$default(filename, Operators.DOT_STR, null, 2, null), (Number) 1), null, 2, null);
        }
        String mimeTypeFromExtension = singleton.getMimeTypeFromExtension(fileExtensionFromUrl);
        if (TextUtils.isEmpty(mimeTypeFromExtension)) {
            if (TextUtils.isEmpty(fileExtensionFromUrl)) {
                mimeTypeFromExtension = "*/*";
            } else {
                mimeTypeFromExtension = "application/" + fileExtensionFromUrl;
            }
        }
        Intrinsics.checkNotNull(mimeTypeFromExtension);
        return mimeTypeFromExtension;
    }

    /* compiled from: index.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/UploadController$Companion;", "", "()V", "instance", "Luts/sdk/modules/DCloudUniNetwork/UploadController;", "getInstance", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UploadController getInstance() {
            if (UploadController.instance == null) {
                UploadController.instance = new UploadController();
            }
            UploadController uploadController = UploadController.instance;
            Intrinsics.checkNotNull(uploadController);
            return uploadController;
        }
    }
}
