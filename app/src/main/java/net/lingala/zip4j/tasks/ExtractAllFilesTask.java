package net.lingala.zip4j.tasks;

import java.io.IOException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.io.inputstream.SplitFileInputStream;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.UnzipUtil;

/* loaded from: classes3.dex */
public class ExtractAllFilesTask extends AbstractExtractFileTask<ExtractAllFilesTaskParameters> {
    private final char[] password;
    private SplitFileInputStream splitInputStream;

    public ExtractAllFilesTask(ZipModel zipModel, char[] cArr, UnzipParameters unzipParameters, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, unzipParameters, asyncTaskParameters);
        this.password = cArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(ExtractAllFilesTaskParameters extractAllFilesTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        try {
            ZipInputStream zipInputStreamPrepareZipInputStream = prepareZipInputStream(extractAllFilesTaskParameters.zip4jConfig);
            try {
                for (FileHeader fileHeader : getZipModel().getCentralDirectory().getFileHeaders()) {
                    if (fileHeader.getFileName().startsWith("__MACOSX")) {
                        progressMonitor.updateWorkCompleted(fileHeader.getUncompressedSize());
                    } else {
                        this.splitInputStream.prepareExtractionForFileHeader(fileHeader);
                        extractFile(zipInputStreamPrepareZipInputStream, fileHeader, extractAllFilesTaskParameters.outputPath, null, progressMonitor, new byte[extractAllFilesTaskParameters.zip4jConfig.getBufferSize()]);
                        verifyIfTaskIsCancelled();
                    }
                }
                if (zipInputStreamPrepareZipInputStream != null) {
                    zipInputStreamPrepareZipInputStream.close();
                }
            } finally {
            }
        } finally {
            SplitFileInputStream splitFileInputStream = this.splitInputStream;
            if (splitFileInputStream != null) {
                splitFileInputStream.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(ExtractAllFilesTaskParameters extractAllFilesTaskParameters) {
        return HeaderUtil.getTotalUncompressedSizeOfAllFileHeaders(getZipModel().getCentralDirectory().getFileHeaders());
    }

    private ZipInputStream prepareZipInputStream(Zip4jConfig zip4jConfig) throws IOException {
        this.splitInputStream = UnzipUtil.createSplitInputStream(getZipModel());
        FileHeader firstFileHeader = getFirstFileHeader(getZipModel());
        if (firstFileHeader != null) {
            this.splitInputStream.prepareExtractionForFileHeader(firstFileHeader);
        }
        return new ZipInputStream(this.splitInputStream, this.password, zip4jConfig);
    }

    private FileHeader getFirstFileHeader(ZipModel zipModel) {
        if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null || zipModel.getCentralDirectory().getFileHeaders().size() == 0) {
            return null;
        }
        return zipModel.getCentralDirectory().getFileHeaders().get(0);
    }

    public static class ExtractAllFilesTaskParameters extends AbstractZipTaskParameters {
        private final String outputPath;

        public ExtractAllFilesTaskParameters(String str, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.outputPath = str;
        }
    }
}
