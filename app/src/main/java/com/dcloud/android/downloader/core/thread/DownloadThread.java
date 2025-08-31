package com.dcloud.android.downloader.core.thread;

import android.os.Process;
import android.util.Log;
import com.dcloud.android.downloader.config.Config;
import com.dcloud.android.downloader.core.DownloadResponse;
import com.dcloud.android.downloader.domain.DownloadInfo;
import com.dcloud.android.downloader.domain.DownloadThreadInfo;
import com.dcloud.android.downloader.exception.DownloadException;
import com.dcloud.android.downloader.exception.DownloadPauseException;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.adapter.util.DCloudTrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public class DownloadThread implements Runnable {
    public static final String TAG = "DownloadThread";
    private final Config config;
    private final DownloadInfo downloadInfo;
    private final DownloadProgressListener downloadProgressListener;
    private final DownloadResponse downloadResponse;
    private final DownloadThreadInfo downloadThreadInfo;
    private InputStream inputStream;
    private long lastProgress;
    private int retryDownloadCount = 0;

    public interface DownloadProgressListener {
        void onDownloadSuccess();

        void onProgress();
    }

    public DownloadThread(DownloadThreadInfo downloadThreadInfo, DownloadResponse downloadResponse, Config config, DownloadInfo downloadInfo, DownloadProgressListener downloadProgressListener) {
        this.downloadThreadInfo = downloadThreadInfo;
        this.downloadResponse = downloadResponse;
        this.config = config;
        this.downloadInfo = downloadInfo;
        this.lastProgress = downloadThreadInfo.getProgress();
        this.downloadProgressListener = downloadProgressListener;
    }

    private void checkPause() {
        if (this.downloadInfo.isPause()) {
            throw new DownloadPauseException(7);
        }
    }

    private void executeDownload() throws Throwable {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.downloadThreadInfo.getUri()).openConnection();
                try {
                    if (httpURLConnection2 instanceof HttpsURLConnection) {
                        SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
                        if (sSLSocketFactory != null) {
                            ((HttpsURLConnection) httpURLConnection2).setSSLSocketFactory(sSLSocketFactory);
                        }
                        ((HttpsURLConnection) httpURLConnection2).setHostnameVerifier(DCloudTrustManager.getHostnameVerifier(false));
                    }
                    httpURLConnection2.setConnectTimeout(this.config.getConnectTimeout());
                    httpURLConnection2.setReadTimeout(this.config.getReadTimeout());
                    httpURLConnection2.setRequestMethod(this.config.getMethod());
                    long start = this.downloadThreadInfo.getStart() + this.lastProgress;
                    if (this.downloadInfo.isSupportRanges()) {
                        if (start > this.downloadThreadInfo.getEnd()) {
                            start = 0;
                            this.lastProgress = 0L;
                        }
                        if (this.config.getEachDownloadThread() == 1) {
                            httpURLConnection2.setRequestProperty("Range", "bytes=" + start + Operators.SUB);
                        } else {
                            httpURLConnection2.setRequestProperty("Range", "bytes=" + start + Operators.SUB + this.downloadThreadInfo.getEnd());
                        }
                    }
                    int responseCode = httpURLConnection2.getResponseCode();
                    long j = Integer.parseInt(httpURLConnection2.getHeaderField("Content-Length")) + start;
                    if (this.config.getEachDownloadThread() == 1 && j != this.downloadThreadInfo.getEnd()) {
                        if (j - this.downloadThreadInfo.getEnd() != 1) {
                            throw new DownloadException(5, "IO error Data source change");
                        }
                        start--;
                        this.lastProgress--;
                    }
                    if (responseCode != 206 && responseCode != 200) {
                        throw new DownloadException(8, "UnSupported response code:" + responseCode);
                    }
                    this.inputStream = httpURLConnection2.getInputStream();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(this.downloadInfo.getPath(), "rwd");
                    if (this.config.getEachDownloadThread() == 1 && randomAccessFile.length() < this.lastProgress) {
                        throw new DownloadException(5, "IO error Have small download size");
                    }
                    randomAccessFile.seek(start);
                    byte[] bArr = new byte[4096];
                    int i = 0;
                    while (true) {
                        checkPause();
                        int i2 = this.inputStream.read(bArr);
                        if (i2 == -1) {
                            this.downloadProgressListener.onDownloadSuccess();
                            checkPause();
                            httpURLConnection2.disconnect();
                            return;
                        }
                        randomAccessFile.write(bArr, 0, i2);
                        i += i2;
                        this.downloadThreadInfo.setProgress(this.lastProgress + i);
                        this.downloadProgressListener.onProgress();
                        Log.d(TAG, "downloadInfo:" + this.downloadInfo.getId() + " thread:" + this.downloadThreadInfo.getThreadId() + " progress:" + this.downloadThreadInfo.getProgress() + ",start:" + this.downloadThreadInfo.getStart() + ",end:" + this.downloadThreadInfo.getEnd());
                    }
                } catch (DownloadPauseException unused) {
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (ProtocolException e) {
                    e = e;
                    throw new DownloadException(4, "Protocol error", e);
                } catch (IOException e2) {
                    e = e2;
                    throw new DownloadException(5, "IO error", e);
                } catch (KeyManagementException e3) {
                    e = e3;
                    throw new DownloadException(5, "Key management", e);
                } catch (NoSuchAlgorithmException e4) {
                    e = e4;
                    throw new DownloadException(5, "NO such", e);
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (DownloadPauseException unused2) {
            } catch (ProtocolException e5) {
                e = e5;
            } catch (IOException e6) {
                e = e6;
            } catch (KeyManagementException e7) {
                e = e7;
            } catch (NoSuchAlgorithmException e8) {
                e = e8;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        Process.setThreadPriority(10);
        checkPause();
        try {
            executeDownload();
        } catch (DownloadException e) {
            this.downloadInfo.setStatus(6);
            this.downloadInfo.setException(e);
            this.downloadResponse.onStatusChanged(this.downloadInfo);
            this.downloadResponse.handleException(e);
        } catch (Exception e2) {
            DownloadException downloadException = new DownloadException(9, "other error", e2);
            this.downloadInfo.setStatus(6);
            this.downloadInfo.setException(downloadException);
            this.downloadResponse.onStatusChanged(this.downloadInfo);
            this.downloadResponse.handleException(downloadException);
        }
    }
}
