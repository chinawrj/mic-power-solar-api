package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.io.InputStream;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.io.outputstream.SplitOutputStream;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.Zip4jUtil;

/* loaded from: classes3.dex */
public class AddStreamToZipTask extends AbstractAddFileToZipTask<AddStreamToZipTaskParameters> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(AddStreamToZipTaskParameters addStreamToZipTaskParameters) {
        return 0L;
    }

    public AddStreamToZipTask(ZipModel zipModel, char[] cArr, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, cArr, headerWriter, asyncTaskParameters);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(AddStreamToZipTaskParameters addStreamToZipTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        verifyZipParameters(addStreamToZipTaskParameters.zipParameters);
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(addStreamToZipTaskParameters.zipParameters.getFileNameInZip())) {
            throw new ZipException("fileNameInZip has to be set in zipParameters when adding stream");
        }
        removeFileIfExists(getZipModel(), addStreamToZipTaskParameters.zip4jConfig, addStreamToZipTaskParameters.zipParameters.getFileNameInZip(), progressMonitor);
        addStreamToZipTaskParameters.zipParameters.setWriteExtendedLocalFileHeader(true);
        if (addStreamToZipTaskParameters.zipParameters.getCompressionMethod().equals(CompressionMethod.STORE)) {
            addStreamToZipTaskParameters.zipParameters.setEntrySize(0L);
        }
        SplitOutputStream splitOutputStream = new SplitOutputStream(getZipModel().getZipFile(), getZipModel().getSplitLength());
        try {
            ZipOutputStream zipOutputStreamInitializeOutputStream = initializeOutputStream(splitOutputStream, addStreamToZipTaskParameters.zip4jConfig);
            try {
                byte[] bArr = new byte[addStreamToZipTaskParameters.zip4jConfig.getBufferSize()];
                ZipParameters zipParameters = addStreamToZipTaskParameters.zipParameters;
                zipOutputStreamInitializeOutputStream.putNextEntry(zipParameters);
                if (!zipParameters.getFileNameInZip().endsWith("/") && !zipParameters.getFileNameInZip().endsWith("\\")) {
                    while (true) {
                        int i = addStreamToZipTaskParameters.inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            zipOutputStreamInitializeOutputStream.write(bArr, 0, i);
                        }
                    }
                }
                FileHeader fileHeaderCloseEntry = zipOutputStreamInitializeOutputStream.closeEntry();
                if (CompressionMethod.STORE.equals(Zip4jUtil.getCompressionMethod(fileHeaderCloseEntry))) {
                    updateLocalFileHeader(fileHeaderCloseEntry, splitOutputStream);
                }
                if (zipOutputStreamInitializeOutputStream != null) {
                    zipOutputStreamInitializeOutputStream.close();
                }
                splitOutputStream.close();
            } finally {
            }
        } catch (Throwable th) {
            try {
                splitOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void removeFileIfExists(ZipModel zipModel, Zip4jConfig zip4jConfig, String str, ProgressMonitor progressMonitor) throws ZipException {
        FileHeader fileHeader = HeaderUtil.getFileHeader(zipModel, str);
        if (fileHeader != null) {
            removeFile(fileHeader, progressMonitor, zip4jConfig);
        }
    }

    public static class AddStreamToZipTaskParameters extends AbstractZipTaskParameters {
        private final InputStream inputStream;
        private final ZipParameters zipParameters;

        public AddStreamToZipTaskParameters(InputStream inputStream, ZipParameters zipParameters, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.inputStream = inputStream;
            this.zipParameters = zipParameters;
        }
    }
}
