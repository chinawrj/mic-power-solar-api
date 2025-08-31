package uts.sdk.modules.DCloudUniNetwork;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.core.app.NotificationCompat;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.performance.WXInstanceApm;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uniapp.SourceError;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSJSONObject;
import io.dcloud.uts.UTSRegExp;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.StringTokenizer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u0005H\u0016J\b\u0010\r\u001a\u00020\tH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000bH\u0016J+\u0010\u001b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/SimpleDownloadCallback;", "Lokhttp3/Callback;", "listener", "Luts/sdk/modules/DCloudUniNetwork/NetworkDownloadFileListener;", "specifyPath", "", "(Luts/sdk/modules/DCloudUniNetwork/NetworkDownloadFileListener;Ljava/lang/String;)V", "downloadFilePath", "getFile", "Ljava/io/File;", "response", "Lokhttp3/Response;", "getRealPath", "getTempFile", "isAbsolute", "", AbsoluteConst.XML_PATH, "isDescendant", "parent", "child", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "exception", "Ljava/io/IOException;", "onResponse", "stringSplit", "", "str", "delim", "(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class SimpleDownloadCallback implements Callback {
    private String downloadFilePath;
    private NetworkDownloadFileListener listener;
    private String specifyPath;

    public SimpleDownloadCallback(NetworkDownloadFileListener listener, String specifyPath) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(specifyPath, "specifyPath");
        this.downloadFilePath = "/uni-download/";
        this.specifyPath = "";
        this.listener = listener;
        if (StringsKt.startsWith$default(specifyPath, "unifile://", false, 2, (Object) null)) {
            this.specifyPath = UTSAndroid.INSTANCE.convert2AbsFullPath(specifyPath);
        } else {
            this.specifyPath = specifyPath;
        }
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException exception) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(exception, "exception");
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        uTSJSONObject.set("statusCode", "-1");
        uTSJSONObject.set("errorCode", "-1");
        uTSJSONObject.set("errorMsg", exception.getMessage());
        uTSJSONObject.set("cause", new SourceError(NumberKt.toString_number_nullable$default(exception.getCause(), (Number) null, 1, (Object) null)));
        NetworkDownloadFileListener networkDownloadFileListener = this.listener;
        if (networkDownloadFileListener != null) {
            networkDownloadFileListener.onComplete(uTSJSONObject);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0155 A[Catch: all -> 0x016e, TRY_LEAVE, TryCatch #8 {all -> 0x016e, blocks: (B:75:0x0130, B:78:0x0149, B:80:0x0155), top: B:112:0x0130 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x017c  */
    @Override // okhttp3.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onResponse(okhttp3.Call r21, okhttp3.Response r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: uts.sdk.modules.DCloudUniNetwork.SimpleDownloadCallback.onResponse(okhttp3.Call, okhttp3.Response):void");
    }

    public File getTempFile() {
        Context appContext = UTSAndroid.INSTANCE.getAppContext();
        Intrinsics.checkNotNull(appContext);
        return new File(appContext.getExternalCacheDir(), "temp_" + System.currentTimeMillis());
    }

    public String getRealPath() {
        String appTempPath = UTSAndroid.INSTANCE.getAppTempPath();
        if (appTempPath == null) {
            appTempPath = "";
        }
        return appTempPath + this.downloadFilePath;
    }

    public File getFile(Response response) throws IOException {
        String realPath;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String strValueOf;
        String strSubstring;
        String[] strArrStringSplit;
        String[] strArr;
        String str6;
        String str7;
        String str8;
        String str9;
        String[] strArrStringSplit2;
        String string;
        String strSubstring$default;
        String strSubstring2;
        Intrinsics.checkNotNullParameter(response, "response");
        String str10 = "";
        String str11 = "cause";
        String str12 = "errorMsg";
        String str13 = "602001";
        String str14 = "errorCode";
        if (!Intrinsics.areEqual(this.specifyPath, "")) {
            if (isDescendant(new File(UTSAndroid.INSTANCE.convert2AbsFullPath("/")), new File(this.specifyPath))) {
                UTSJSONObject uTSJSONObject = new UTSJSONObject();
                uTSJSONObject.set("statusCode", "-1");
                uTSJSONObject.set("errorCode", "602001");
                uTSJSONObject.set("errorMsg", "This path is not supported");
                uTSJSONObject.set("cause", null);
                NetworkDownloadFileListener networkDownloadFileListener = this.listener;
                if (networkDownloadFileListener != null) {
                    networkDownloadFileListener.onComplete(uTSJSONObject);
                }
                return new File("");
            }
            if (NumberKt.numberEquals(StringKt.lastIndexOf$default(this.specifyPath, "/", null, 2, null), Integer.valueOf(this.specifyPath.length() - 1))) {
                if (isAbsolute(this.specifyPath)) {
                    realPath = this.specifyPath;
                } else {
                    StringBuilder sb = new StringBuilder();
                    String appTempPath = UTSAndroid.INSTANCE.getAppTempPath();
                    Intrinsics.checkNotNull(appTempPath);
                    Intrinsics.checkNotNull(appTempPath);
                    sb.append(appTempPath);
                    sb.append('/');
                    sb.append(this.specifyPath);
                    realPath = sb.toString();
                }
            } else {
                if (isAbsolute(this.specifyPath)) {
                    string = this.specifyPath;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    String appTempPath2 = UTSAndroid.INSTANCE.getAppTempPath();
                    Intrinsics.checkNotNull(appTempPath2);
                    Intrinsics.checkNotNull(appTempPath2);
                    sb2.append(appTempPath2);
                    sb2.append('/');
                    sb2.append(this.specifyPath);
                    string = sb2.toString();
                }
                File file = new File(string);
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                }
                if (file.exists() && file.isDirectory()) {
                    UTSJSONObject uTSJSONObject2 = new UTSJSONObject();
                    uTSJSONObject2.set("statusCode", "-1");
                    uTSJSONObject2.set("errorCode", "602001");
                    uTSJSONObject2.set("errorMsg", "The target file path is already a directory file, and file creation failed.");
                    uTSJSONObject2.set("cause", null);
                    NetworkDownloadFileListener networkDownloadFileListener2 = this.listener;
                    if (networkDownloadFileListener2 != null) {
                        networkDownloadFileListener2.onComplete(uTSJSONObject2);
                    }
                }
                if (file.exists()) {
                    Number numberLastIndexOf$default = StringKt.lastIndexOf$default(string, Operators.DOT_STR, null, 2, null);
                    if (NumberKt.compareTo(numberLastIndexOf$default, (Number) 0) >= 0) {
                        strSubstring2 = StringKt.substring(string, (Number) 0, numberLastIndexOf$default);
                        strSubstring$default = StringKt.substring$default(string, numberLastIndexOf$default, null, 2, null);
                    } else {
                        strSubstring$default = "";
                        strSubstring2 = string;
                    }
                    Number numberInc = (Number) 1;
                    while (new File(string).exists()) {
                        string = strSubstring2 + Operators.BRACKET_START + NumberKt.toString(numberInc, (Number) 10) + Operators.BRACKET_END + strSubstring$default;
                        numberInc = NumberKt.inc(numberInc);
                    }
                    file = new File(string);
                }
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (Exception e) {
                        UTSJSONObject uTSJSONObject3 = new UTSJSONObject();
                        uTSJSONObject3.set("statusCode", "-1");
                        uTSJSONObject3.set("errorCode", "602001");
                        uTSJSONObject3.set("errorMsg", e.getMessage());
                        uTSJSONObject3.set("cause", new SourceError(NumberKt.toString_number_nullable$default(e.getCause(), (Number) null, 1, (Object) null)));
                        NetworkDownloadFileListener networkDownloadFileListener3 = this.listener;
                        if (networkDownloadFileListener3 != null) {
                            networkDownloadFileListener3.onComplete(uTSJSONObject3);
                        }
                    }
                }
                return file;
            }
        } else {
            realPath = getRealPath();
        }
        String strHeader = response.header("content-disposition");
        if (TextUtils.isEmpty(strHeader) || (strArrStringSplit = stringSplit(strHeader, ";")) == null) {
            str = "cause";
            str2 = "errorMsg";
            str3 = "602001";
            str4 = "errorCode";
            str5 = "";
        } else {
            str5 = "";
            int i = 0;
            while (i < strArrStringSplit.length) {
                String str15 = strArrStringSplit[i];
                if (str15 != null) {
                    String str16 = str15;
                    strArr = strArrStringSplit;
                    str6 = str11;
                    str7 = str12;
                    str8 = str13;
                    str9 = str14;
                    if (StringsKt.contains$default((CharSequence) str16, (CharSequence) AbsoluteConst.JSON_KEY_FILENAME, false, 2, (Object) null) && (strArrStringSplit2 = stringSplit(StringsKt.trim((CharSequence) str16).toString(), "=")) != null && strArrStringSplit2.length > 1) {
                        String strReplace = strArrStringSplit2[0];
                        String strReplace2 = strArrStringSplit2[1];
                        UTSRegExp uTSRegExp = new UTSRegExp("^\"|\"$", "g");
                        if (strReplace != null) {
                            strReplace = StringKt.replace(strReplace, uTSRegExp, "");
                        }
                        if (strReplace2 != null) {
                            strReplace2 = StringKt.replace(strReplace2, uTSRegExp, "");
                        }
                        if (!TextUtils.isEmpty(strReplace) && !TextUtils.isEmpty(strReplace2)) {
                            Intrinsics.checkNotNull(strReplace);
                            if (StringsKt.equals(strReplace, AbsoluteConst.JSON_KEY_FILENAME, true) && strReplace2 != null) {
                                str5 = strReplace2;
                            }
                        }
                    }
                } else {
                    strArr = strArrStringSplit;
                    str6 = str11;
                    str7 = str12;
                    str8 = str13;
                    str9 = str14;
                }
                i++;
                strArrStringSplit = strArr;
                str11 = str6;
                str12 = str7;
                str13 = str8;
                str14 = str9;
            }
            str = str11;
            str2 = str12;
            str3 = str13;
            str4 = str14;
        }
        if (TextUtils.isEmpty(str5)) {
            String path = response.request().url().encodedPath();
            Intrinsics.checkNotNullExpressionValue(path, "path");
            Number numberLastIndexOf$default2 = StringKt.lastIndexOf$default(path, "/", null, 2, null);
            if (NumberKt.compareTo(numberLastIndexOf$default2, (Number) 0) >= 0) {
                Intrinsics.checkNotNullExpressionValue(path, "path");
                String path2 = StringKt.substring$default(path, NumberKt.plus(numberLastIndexOf$default2, (Number) 1), null, 2, null);
                Intrinsics.checkNotNullExpressionValue(path2, "path");
                if (NumberKt.compareTo(StringKt.indexOf$default(path2, Operators.DOT_STR, null, 2, null), (Number) 0) >= 0 || path2.length() > 0) {
                    Intrinsics.checkNotNullExpressionValue(path2, "path");
                    if (StringsKt.contains$default((CharSequence) path2, (CharSequence) Operators.CONDITION_IF_STRING, false, 2, (Object) null)) {
                        Intrinsics.checkNotNullExpressionValue(path2, "path");
                        Intrinsics.checkNotNullExpressionValue(path2, "path");
                        path2 = StringKt.substring(path2, (Number) 0, StringKt.indexOf$default(path2, Operators.CONDITION_IF_STRING, null, 2, null));
                    }
                    Intrinsics.checkNotNullExpressionValue(path2, "path");
                    str5 = path2;
                }
            }
        }
        if (TextUtils.isEmpty(str5)) {
            strValueOf = String.valueOf(System.currentTimeMillis());
            String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(response.header("content-type"));
            if (extensionFromMimeType != null) {
                str5 = strValueOf + Operators.DOT + extensionFromMimeType;
                strValueOf = str5;
            }
        } else {
            strValueOf = str5;
        }
        String strDecode = URLDecoder.decode(strValueOf, "UTF-8");
        Intrinsics.checkNotNullExpressionValue(strDecode, "decode(fileName, \"UTF-8\")");
        String separator = File.separator;
        Intrinsics.checkNotNullExpressionValue(separator, "separator");
        String strReplace3 = new Regex(separator).replace(strDecode, "");
        String str17 = strReplace3;
        if (StringsKt.contains$default((CharSequence) str17, (CharSequence) Operators.CONDITION_IF_STRING, false, 2, (Object) null)) {
            strReplace3 = new Regex("\\?").replace(str17, WXInstanceApm.VALUE_ERROR_CODE_DEFAULT);
        }
        if (strReplace3.length() > 80) {
            strReplace3 = StringKt.substring(strReplace3, (Number) 0, (Number) 80) + System.currentTimeMillis();
        }
        if (new File(realPath + strReplace3).exists()) {
            Number numberLastIndexOf$default3 = StringKt.lastIndexOf$default(strReplace3, Operators.DOT_STR, null, 2, null);
            if (NumberKt.compareTo(numberLastIndexOf$default3, (Number) 0) >= 0) {
                strSubstring = StringKt.substring(strReplace3, (Number) 0, numberLastIndexOf$default3);
                String strSubstring$default2 = StringKt.substring$default(strReplace3, numberLastIndexOf$default3, null, 2, null);
                if (Intrinsics.areEqual(strSubstring, "")) {
                    strSubstring = strSubstring$default2;
                } else {
                    str10 = strSubstring$default2;
                }
            } else {
                strSubstring = strReplace3;
            }
            Number numberInc2 = (Number) 1;
            while (true) {
                if (!new File(realPath + strReplace3).exists()) {
                    break;
                }
                strReplace3 = strSubstring + Operators.BRACKET_START + NumberKt.toString(numberInc2, (Number) 10) + Operators.BRACKET_END + str10;
                numberInc2 = NumberKt.inc(numberInc2);
            }
        }
        File file2 = new File(realPath + strReplace3);
        File parentFile2 = file2.getParentFile();
        if (parentFile2 != null && !parentFile2.exists()) {
            parentFile2.mkdirs();
        }
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception e2) {
                UTSJSONObject uTSJSONObject4 = new UTSJSONObject();
                uTSJSONObject4.set("statusCode", "-1");
                uTSJSONObject4.set(str4, str3);
                uTSJSONObject4.set(str2, e2.getMessage());
                uTSJSONObject4.set(str, new SourceError(NumberKt.toString_number_nullable$default(e2.getCause(), (Number) null, 1, (Object) null)));
                NetworkDownloadFileListener networkDownloadFileListener4 = this.listener;
                if (networkDownloadFileListener4 != null) {
                    networkDownloadFileListener4.onComplete(uTSJSONObject4);
                }
            }
        }
        return file2;
    }

    public boolean isAbsolute(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Context appContext = UTSAndroid.INSTANCE.getAppContext();
        Intrinsics.checkNotNull(appContext);
        Intrinsics.checkNotNull(appContext);
        String parent = appContext.getFilesDir().getParent();
        Intrinsics.checkNotNullExpressionValue(parent, "context.getFilesDir().getParent()");
        if (StringsKt.startsWith$default(path, parent, false, 2, (Object) null)) {
            return true;
        }
        File externalFilesDir = appContext.getExternalFilesDir(null);
        String parent2 = externalFilesDir != null ? externalFilesDir.getParent() : null;
        return parent2 != null && StringsKt.startsWith$default(path, parent2, false, 2, (Object) null);
    }

    public boolean isDescendant(File parent, File child) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        if (Intrinsics.areEqual(child.getCanonicalPath(), parent.getCanonicalPath())) {
            return true;
        }
        File parentFile = child.getParentFile();
        if (parentFile == null) {
            return false;
        }
        return isDescendant(parent, parentFile);
    }

    public String[] stringSplit(String str, String delim) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(delim)) {
            return null;
        }
        int i = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(str, delim, false);
        String[] strArr = new String[stringTokenizer.countTokens()];
        while (stringTokenizer.hasMoreElements()) {
            String strNextToken = stringTokenizer.nextToken();
            Intrinsics.checkNotNullExpressionValue(strNextToken, "stringTokenizer.nextToken()");
            strArr[i] = StringsKt.trim((CharSequence) strNextToken).toString();
            i++;
        }
        return strArr;
    }
}
