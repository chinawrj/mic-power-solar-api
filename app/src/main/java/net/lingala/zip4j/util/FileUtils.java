package net.lingala.zip4j.util;

import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.performance.WXInstanceApm;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;

/* loaded from: classes3.dex */
public class FileUtils {
    public static final byte[] DEFAULT_POSIX_FILE_ATTRIBUTES = {0, 0, -92, -127};
    public static final byte[] DEFAULT_POSIX_FOLDER_ATTRIBUTES = {0, 0, -19, 65};

    public static void setFileAttributes(Path path, byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        if (isWindows()) {
            applyWindowsFileAttributes(path, bArr);
        } else if (isMac() || isUnix()) {
            applyPosixFileAttributes(path, bArr);
        }
    }

    public static void setFileLastModifiedTime(Path path, long j) throws IOException {
        if (j <= 0 || !Files.exists(path, new LinkOption[0])) {
            return;
        }
        try {
            Files.setLastModifiedTime(path, FileTime.fromMillis(Zip4jUtil.dosToExtendedEpochTme(j)));
        } catch (Exception unused) {
        }
    }

    public static void setFileLastModifiedTimeWithoutNio(File file, long j) {
        file.setLastModified(Zip4jUtil.dosToExtendedEpochTme(j));
    }

    public static byte[] getFileAttributes(File file) {
        if (file != null) {
            try {
                if (Files.isSymbolicLink(file.toPath()) || file.exists()) {
                    Path path = file.toPath();
                    if (isWindows()) {
                        return getWindowsFileAttributes(path);
                    }
                    if (!isMac() && !isUnix()) {
                        return new byte[4];
                    }
                    return getPosixFileAttributes(path);
                }
            } catch (NoSuchMethodError unused) {
                return new byte[4];
            }
        }
        return new byte[4];
    }

    public static List<File> getFilesInDirectoryRecursive(File file, ZipParameters zipParameters) throws ZipException {
        if (file == null) {
            throw new ZipException("input path is null, cannot read files in the directory");
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (file.isDirectory() && file.canRead() && fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if ((zipParameters.getExcludeFileFilter() == null || !zipParameters.getExcludeFileFilter().isExcluded(file2)) && (!file2.isHidden() || zipParameters.isReadHiddenFiles())) {
                    arrayList.add(file2);
                    ZipParameters.SymbolicLinkAction symbolicLinkAction = zipParameters.getSymbolicLinkAction();
                    boolean zIsSymbolicLink = isSymbolicLink(file2);
                    if ((zIsSymbolicLink && !ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(symbolicLinkAction)) || (!zIsSymbolicLink && file2.isDirectory())) {
                        arrayList.addAll(getFilesInDirectoryRecursive(file2, zipParameters));
                    }
                }
            }
        }
        return arrayList;
    }

    public static String getFileNameWithoutExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(Operators.DOT_STR);
        return iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf);
    }

    public static String getZipFileNameWithoutExtension(String str) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("zip file name is empty or null, cannot determine zip file name");
        }
        if (str.contains(System.getProperty("file.separator"))) {
            str = str.substring(str.lastIndexOf(System.getProperty("file.separator")) + 1);
        }
        return str.endsWith(".zip") ? str.substring(0, str.lastIndexOf(Operators.DOT_STR)) : str;
    }

    public static List<File> getSplitZipFiles(ZipModel zipModel) throws ZipException {
        String str;
        if (zipModel == null) {
            throw new ZipException("cannot get split zip files: zipmodel is null");
        }
        if (zipModel.getEndOfCentralDirectoryRecord() == null) {
            return null;
        }
        if (!zipModel.getZipFile().exists()) {
            throw new ZipException("zip file does not exist");
        }
        ArrayList arrayList = new ArrayList();
        File zipFile = zipModel.getZipFile();
        if (!zipModel.isSplitArchive()) {
            arrayList.add(zipFile);
            return arrayList;
        }
        int numberOfThisDisk = zipModel.getEndOfCentralDirectoryRecord().getNumberOfThisDisk();
        if (numberOfThisDisk == 0) {
            arrayList.add(zipFile);
            return arrayList;
        }
        for (int i = 0; i <= numberOfThisDisk; i++) {
            if (i == numberOfThisDisk) {
                arrayList.add(zipModel.getZipFile());
            } else {
                if (i < 9) {
                    str = ".z0";
                } else {
                    str = ".z";
                }
                arrayList.add(new File((zipFile.getName().contains(Operators.DOT_STR) ? zipFile.getPath().substring(0, zipFile.getPath().lastIndexOf(Operators.DOT_STR)) : zipFile.getPath()) + str + (i + 1)));
            }
        }
        return arrayList;
    }

    public static String getRelativeFileName(File file, ZipParameters zipParameters) throws IOException {
        String nameOfFileInZip;
        String strSubstring;
        try {
            String canonicalPath = file.getCanonicalPath();
            if (Zip4jUtil.isStringNotNullAndNotEmpty(zipParameters.getDefaultFolderPath())) {
                String canonicalPath2 = new File(zipParameters.getDefaultFolderPath()).getCanonicalPath();
                if (!canonicalPath2.endsWith(InternalZipConstants.FILE_SEPARATOR)) {
                    canonicalPath2 = canonicalPath2 + InternalZipConstants.FILE_SEPARATOR;
                }
                if (isSymbolicLink(file)) {
                    strSubstring = new File(file.getParentFile().getCanonicalFile().getPath() + File.separator + file.getCanonicalFile().getName()).getPath().substring(canonicalPath2.length());
                } else if (!file.getCanonicalFile().getPath().startsWith(canonicalPath2)) {
                    strSubstring = file.getCanonicalFile().getParentFile().getName() + InternalZipConstants.FILE_SEPARATOR + file.getCanonicalFile().getName();
                } else {
                    strSubstring = canonicalPath.substring(canonicalPath2.length());
                }
                if (strSubstring.startsWith(System.getProperty("file.separator"))) {
                    strSubstring = strSubstring.substring(1);
                }
                File file2 = new File(canonicalPath);
                if (file2.isDirectory()) {
                    nameOfFileInZip = strSubstring.replaceAll("\\\\", "/") + "/";
                } else {
                    nameOfFileInZip = strSubstring.substring(0, strSubstring.lastIndexOf(file2.getName())).replaceAll("\\\\", "/") + getNameOfFileInZip(file2, zipParameters.getFileNameInZip());
                }
            } else {
                File file3 = new File(canonicalPath);
                nameOfFileInZip = getNameOfFileInZip(file3, zipParameters.getFileNameInZip());
                if (file3.isDirectory()) {
                    nameOfFileInZip = nameOfFileInZip + "/";
                }
            }
            String rootFolderNameInZip = zipParameters.getRootFolderNameInZip();
            if (Zip4jUtil.isStringNotNullAndNotEmpty(rootFolderNameInZip)) {
                if (!rootFolderNameInZip.endsWith("\\") && !rootFolderNameInZip.endsWith("/")) {
                    rootFolderNameInZip = rootFolderNameInZip + InternalZipConstants.FILE_SEPARATOR;
                }
                rootFolderNameInZip = rootFolderNameInZip.replaceAll("\\\\", "/");
                nameOfFileInZip = rootFolderNameInZip + nameOfFileInZip;
            }
            if (Zip4jUtil.isStringNotNullAndNotEmpty(nameOfFileInZip)) {
                return nameOfFileInZip;
            }
            String str = "fileName to add to zip is empty or null. fileName: '" + nameOfFileInZip + "' DefaultFolderPath: '" + zipParameters.getDefaultFolderPath() + "' FileNameInZip: " + zipParameters.getFileNameInZip();
            if (isSymbolicLink(file)) {
                str = str + "isSymlink: true ";
            }
            if (Zip4jUtil.isStringNotNullAndNotEmpty(rootFolderNameInZip)) {
                str = "rootFolderNameInZip: '" + rootFolderNameInZip + "' ";
            }
            throw new ZipException(str);
        } catch (IOException e) {
            throw new ZipException(e);
        }
    }

    private static String getNameOfFileInZip(File file, String str) throws IOException {
        return Zip4jUtil.isStringNotNullAndNotEmpty(str) ? str : isSymbolicLink(file) ? file.toPath().toRealPath(LinkOption.NOFOLLOW_LINKS).getFileName().toString() : file.getName();
    }

    public static boolean isZipEntryDirectory(String str) {
        return str.endsWith("/") || str.endsWith("\\");
    }

    public static void copyFile(RandomAccessFile randomAccessFile, OutputStream outputStream, long j, long j2, ProgressMonitor progressMonitor, int i) throws InterruptedException, IOException {
        byte[] bArr;
        long j3 = 0;
        if (j < 0 || j2 < 0 || j > j2) {
            throw new ZipException("invalid offsets");
        }
        if (j == j2) {
            return;
        }
        try {
            randomAccessFile.seek(j);
            long j4 = j2 - j;
            if (j4 < i) {
                bArr = new byte[(int) j4];
            } else {
                bArr = new byte[i];
            }
            while (true) {
                int i2 = randomAccessFile.read(bArr);
                if (i2 == -1) {
                    return;
                }
                outputStream.write(bArr, 0, i2);
                long j5 = i2;
                progressMonitor.updateWorkCompleted(j5);
                if (progressMonitor.isCancelAllTasks()) {
                    progressMonitor.setResult(ProgressMonitor.Result.CANCELLED);
                    return;
                }
                j3 += j5;
                if (j3 == j4) {
                    return;
                }
                if (bArr.length + j3 > j4) {
                    bArr = new byte[(int) (j4 - j3)];
                }
            }
        } catch (IOException e) {
            throw new ZipException(e);
        }
    }

    public static void assertFilesExist(List<File> list, ZipParameters.SymbolicLinkAction symbolicLinkAction) throws ZipException {
        for (File file : list) {
            if (isSymbolicLink(file)) {
                if (symbolicLinkAction.equals(ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE) || symbolicLinkAction.equals(ZipParameters.SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY)) {
                    assertSymbolicLinkTargetExists(file);
                }
            } else {
                assertFileExists(file);
            }
        }
    }

    public static boolean isNumberedSplitFile(File file) {
        return file.getName().endsWith(InternalZipConstants.SEVEN_ZIP_SPLIT_FILE_EXTENSION_PATTERN);
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        if (!name.contains(Operators.DOT_STR)) {
            return "";
        }
        return name.substring(name.lastIndexOf(Operators.DOT_STR) + 1);
    }

    public static File[] getAllSortedNumberedSplitFiles(File file) {
        final String fileNameWithoutExtension = getFileNameWithoutExtension(file.getName());
        File[] fileArrListFiles = file.getParentFile().listFiles(new FilenameFilter() { // from class: net.lingala.zip4j.util.FileUtils.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.startsWith(fileNameWithoutExtension + Operators.DOT_STR);
            }
        });
        if (fileArrListFiles == null) {
            return new File[0];
        }
        Arrays.sort(fileArrListFiles);
        return fileArrListFiles;
    }

    public static String getNextNumberedSplitFileCounterAsExtension(int i) {
        return Operators.DOT_STR + getExtensionZerosPrefix(i) + (i + 1);
    }

    public static boolean isSymbolicLink(File file) {
        try {
            return Files.isSymbolicLink(file.toPath());
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public static String readSymbolicLink(File file) {
        try {
            return Files.readSymbolicLink(file.toPath()).toString();
        } catch (Error | Exception unused) {
            return "";
        }
    }

    public static byte[] getDefaultFileAttributes(boolean z) {
        byte[] bArr = new byte[4];
        if (isUnix() || isMac()) {
            if (z) {
                System.arraycopy(DEFAULT_POSIX_FOLDER_ATTRIBUTES, 0, bArr, 0, 4);
            } else {
                System.arraycopy(DEFAULT_POSIX_FILE_ATTRIBUTES, 0, bArr, 0, 4);
            }
        } else if (isWindows() && z) {
            bArr[0] = BitUtils.setBit(bArr[0], 4);
        }
        return bArr;
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    public static boolean isUnix() {
        return System.getProperty("os.name").toLowerCase().contains("nux");
    }

    private static String getExtensionZerosPrefix(int i) {
        if (i < 9) {
            return "00";
        }
        if (i < 99) {
            return WXInstanceApm.VALUE_ERROR_CODE_DEFAULT;
        }
        return "";
    }

    private static void applyWindowsFileAttributes(Path path, byte[] bArr) throws IOException {
        DosFileAttributeView dosFileAttributeViewM1655m;
        if (bArr[0] == 0 || (dosFileAttributeViewM1655m = StreamsKt$$ExternalSyntheticApiModelOutline0.m1655m((Object) Files.getFileAttributeView(path, StreamsKt$$ExternalSyntheticApiModelOutline0.m$2(), LinkOption.NOFOLLOW_LINKS))) == null) {
            return;
        }
        try {
            dosFileAttributeViewM1655m.setReadOnly(BitUtils.isBitSet(bArr[0], 0));
            dosFileAttributeViewM1655m.setHidden(BitUtils.isBitSet(bArr[0], 1));
            dosFileAttributeViewM1655m.setSystem(BitUtils.isBitSet(bArr[0], 2));
            dosFileAttributeViewM1655m.setArchive(BitUtils.isBitSet(bArr[0], 5));
        } catch (IOException unused) {
        }
    }

    private static void applyPosixFileAttributes(Path path, byte[] bArr) throws IOException {
        if (bArr[2] == 0 && bArr[3] == 0) {
            return;
        }
        try {
            HashSet hashSet = new HashSet();
            addIfBitSet(bArr[3], 0, hashSet, PosixFilePermission.OWNER_READ);
            addIfBitSet(bArr[2], 7, hashSet, PosixFilePermission.OWNER_WRITE);
            addIfBitSet(bArr[2], 6, hashSet, PosixFilePermission.OWNER_EXECUTE);
            addIfBitSet(bArr[2], 5, hashSet, PosixFilePermission.GROUP_READ);
            addIfBitSet(bArr[2], 4, hashSet, PosixFilePermission.GROUP_WRITE);
            addIfBitSet(bArr[2], 3, hashSet, PosixFilePermission.GROUP_EXECUTE);
            addIfBitSet(bArr[2], 2, hashSet, PosixFilePermission.OTHERS_READ);
            addIfBitSet(bArr[2], 1, hashSet, PosixFilePermission.OTHERS_WRITE);
            addIfBitSet(bArr[2], 0, hashSet, PosixFilePermission.OTHERS_EXECUTE);
            Files.setPosixFilePermissions(path, hashSet);
        } catch (IOException unused) {
        }
    }

    private static byte[] getWindowsFileAttributes(Path path) throws IOException {
        DosFileAttributeView dosFileAttributeViewM1655m;
        byte[] bArr = new byte[4];
        try {
            dosFileAttributeViewM1655m = StreamsKt$$ExternalSyntheticApiModelOutline0.m1655m((Object) Files.getFileAttributeView(path, StreamsKt$$ExternalSyntheticApiModelOutline0.m$2(), LinkOption.NOFOLLOW_LINKS));
        } catch (IOException unused) {
        }
        if (dosFileAttributeViewM1655m == null) {
            return bArr;
        }
        DosFileAttributes attributes = dosFileAttributeViewM1655m.readAttributes();
        bArr[0] = setBitIfApplicable(attributes.isArchive(), setBitIfApplicable(attributes.isDirectory(), setBitIfApplicable(attributes.isSystem(), setBitIfApplicable(attributes.isHidden(), setBitIfApplicable(attributes.isReadOnly(), (byte) 0, 0), 1), 2), 4), 5);
        return bArr;
    }

    private static void assertFileExists(File file) throws ZipException {
        if (file.exists()) {
            return;
        }
        throw new ZipException("File does not exist: " + file);
    }

    private static void assertSymbolicLinkTargetExists(File file) throws ZipException {
        if (file.exists()) {
            return;
        }
        throw new ZipException("Symlink target '" + readSymbolicLink(file) + "' does not exist for link '" + file + "'");
    }

    private static byte[] getPosixFileAttributes(Path path) {
        byte[] bArr = new byte[4];
        try {
            Set setPermissions = StreamsKt$$ExternalSyntheticApiModelOutline0.m1658m((Object) Files.getFileAttributeView(path, StreamsKt$$ExternalSyntheticApiModelOutline0.m$1(), LinkOption.NOFOLLOW_LINKS)).readAttributes().permissions();
            boolean zIsSymbolicLink = Files.isSymbolicLink(path);
            if (zIsSymbolicLink) {
                byte bit = BitUtils.setBit(bArr[3], 7);
                bArr[3] = bit;
                bArr[3] = BitUtils.unsetBit(bit, 6);
            } else {
                bArr[3] = setBitIfApplicable(Files.isRegularFile(path, new LinkOption[0]), bArr[3], 7);
                bArr[3] = setBitIfApplicable(Files.isDirectory(path, new LinkOption[0]), bArr[3], 6);
            }
            bArr[3] = setBitIfApplicable(zIsSymbolicLink, bArr[3], 5);
            bArr[3] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.OWNER_READ), bArr[3], 0);
            bArr[2] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.OWNER_WRITE), bArr[2], 7);
            bArr[2] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.OWNER_EXECUTE), bArr[2], 6);
            bArr[2] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.GROUP_READ), bArr[2], 5);
            bArr[2] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.GROUP_WRITE), bArr[2], 4);
            bArr[2] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.GROUP_EXECUTE), bArr[2], 3);
            bArr[2] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.OTHERS_READ), bArr[2], 2);
            bArr[2] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.OTHERS_WRITE), bArr[2], 1);
            bArr[2] = setBitIfApplicable(setPermissions.contains(PosixFilePermission.OTHERS_EXECUTE), bArr[2], 0);
        } catch (IOException unused) {
        }
        return bArr;
    }

    private static byte setBitIfApplicable(boolean z, byte b, int i) {
        return z ? BitUtils.setBit(b, i) : b;
    }

    private static void addIfBitSet(byte b, int i, Set<PosixFilePermission> set, PosixFilePermission posixFilePermission) {
        if (BitUtils.isBitSet(b, i)) {
            set.add(posixFilePermission);
        }
    }
}
