package net.lingala.zip4j.tasks;

import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.List;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryLocator;
import net.lingala.zip4j.model.Zip64EndOfCentralDirectoryRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.RawIO;

/* loaded from: classes3.dex */
public class MergeSplitZipFileTask extends AsyncZipTask<MergeSplitZipFileTaskParameters> {
    private final RawIO rawIO;
    private final ZipModel zipModel;

    public MergeSplitZipFileTask(ZipModel zipModel, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(asyncTaskParameters);
        this.rawIO = new RawIO();
        this.zipModel = zipModel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005e A[Catch: all -> 0x0051, TRY_LEAVE, TryCatch #0 {all -> 0x0051, blocks: (B:12:0x0038, B:20:0x005e, B:15:0x004d), top: B:69:0x0038 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a7 A[Catch: all -> 0x00ea, TRY_ENTER, TRY_LEAVE, TryCatch #7 {all -> 0x00ea, blocks: (B:31:0x00a7, B:46:0x00c8, B:45:0x00c5, B:47:0x00c9, B:51:0x00e2, B:52:0x00e9, B:41:0x00bf), top: B:82:0x001f, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00aa A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[Catch: all -> 0x00ea, SYNTHETIC, TryCatch #7 {all -> 0x00ea, blocks: (B:31:0x00a7, B:46:0x00c8, B:45:0x00c5, B:47:0x00c9, B:51:0x00e2, B:52:0x00e9, B:41:0x00bf), top: B:82:0x001f, inners: #2 }] */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void executeTask(net.lingala.zip4j.tasks.MergeSplitZipFileTask.MergeSplitZipFileTaskParameters r26, net.lingala.zip4j.progress.ProgressMonitor r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.tasks.MergeSplitZipFileTask.executeTask(net.lingala.zip4j.tasks.MergeSplitZipFileTask$MergeSplitZipFileTaskParameters, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(MergeSplitZipFileTaskParameters mergeSplitZipFileTaskParameters) {
        long length = 0;
        if (!this.zipModel.isSplitArchive()) {
            return 0L;
        }
        for (int i = 0; i <= this.zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk(); i++) {
            length += getNextSplitZipFile(this.zipModel, i).length();
        }
        return length;
    }

    private void updateFileHeaderOffsetsForIndex(List<FileHeader> list, long j, int i, int i2) {
        for (FileHeader fileHeader : list) {
            if (fileHeader.getDiskNumberStart() == i) {
                fileHeader.setOffsetLocalHeader((fileHeader.getOffsetLocalHeader() + j) - i2);
                fileHeader.setDiskNumberStart(0);
            }
        }
    }

    private File getNextSplitZipFile(ZipModel zipModel, int i) {
        String str;
        if (i == zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk()) {
            return zipModel.getZipFile();
        }
        if (i < 9) {
            str = ".z0";
        } else {
            str = ".z";
        }
        return new File(zipModel.getZipFile().getPath().substring(0, zipModel.getZipFile().getPath().lastIndexOf(Operators.DOT_STR)) + str + (i + 1));
    }

    private RandomAccessFile createSplitZipFileStream(ZipModel zipModel, int i) throws FileNotFoundException {
        return new RandomAccessFile(getNextSplitZipFile(zipModel, i), RandomAccessFileMode.READ.getValue());
    }

    private void updateHeadersForMergeSplitFileAction(ZipModel zipModel, long j, OutputStream outputStream, Charset charset) throws IOException, CloneNotSupportedException {
        ZipModel zipModel2 = (ZipModel) zipModel.clone();
        zipModel2.getEndOfCentralDirectoryRecord().setOffsetOfStartOfCentralDirectory(j);
        updateSplitZipModel(zipModel2, j);
        new HeaderWriter().finalizeZipFileWithoutValidations(zipModel2, outputStream, charset);
    }

    private void updateSplitZipModel(ZipModel zipModel, long j) {
        zipModel.setSplitArchive(false);
        updateSplitEndCentralDirectory(zipModel);
        if (zipModel.isZip64Format()) {
            updateSplitZip64EndCentralDirLocator(zipModel, j);
            updateSplitZip64EndCentralDirRec(zipModel, j);
        }
    }

    private void updateSplitEndCentralDirectory(ZipModel zipModel) {
        int size = zipModel.getCentralDirectory().getFileHeaders().size();
        EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = zipModel.getEndOfCentralDirectoryRecord();
        endOfCentralDirectoryRecord.setNumberOfThisDisk(0);
        endOfCentralDirectoryRecord.setNumberOfThisDiskStartOfCentralDir(0);
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectory(size);
        endOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(size);
    }

    private void updateSplitZip64EndCentralDirLocator(ZipModel zipModel, long j) {
        if (zipModel.getZip64EndOfCentralDirectoryLocator() == null) {
            return;
        }
        Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator = zipModel.getZip64EndOfCentralDirectoryLocator();
        zip64EndOfCentralDirectoryLocator.setNumberOfDiskStartOfZip64EndOfCentralDirectoryRecord(0);
        zip64EndOfCentralDirectoryLocator.setOffsetZip64EndOfCentralDirectoryRecord(zip64EndOfCentralDirectoryLocator.getOffsetZip64EndOfCentralDirectoryRecord() + j);
        zip64EndOfCentralDirectoryLocator.setTotalNumberOfDiscs(1);
    }

    private void updateSplitZip64EndCentralDirRec(ZipModel zipModel, long j) {
        if (zipModel.getZip64EndOfCentralDirectoryRecord() == null) {
            return;
        }
        Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord = zipModel.getZip64EndOfCentralDirectoryRecord();
        zip64EndOfCentralDirectoryRecord.setNumberOfThisDisk(0);
        zip64EndOfCentralDirectoryRecord.setNumberOfThisDiskStartOfCentralDirectory(0);
        zip64EndOfCentralDirectoryRecord.setTotalNumberOfEntriesInCentralDirectoryOnThisDisk(zipModel.getEndOfCentralDirectoryRecord().getTotalNumberOfEntriesInCentralDirectory());
        zip64EndOfCentralDirectoryRecord.setOffsetStartCentralDirectoryWRTStartDiskNumber(zip64EndOfCentralDirectoryRecord.getOffsetStartCentralDirectoryWRTStartDiskNumber() + j);
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    protected ProgressMonitor.Task getTask() {
        return ProgressMonitor.Task.MERGE_ZIP_FILES;
    }

    public static class MergeSplitZipFileTaskParameters extends AbstractZipTaskParameters {
        private File outputZipFile;

        public MergeSplitZipFileTaskParameters(File file, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.outputZipFile = file;
        }
    }
}
