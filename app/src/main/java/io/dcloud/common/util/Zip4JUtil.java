package io.dcloud.common.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/* loaded from: classes3.dex */
public class Zip4JUtil {
    public static boolean isEncryptedZip(File file) {
        try {
            ZipFile zipFile = new ZipFile(file);
            if (zipFile.isValidZipFile()) {
                return zipFile.isEncrypted();
            }
            return false;
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void upZipFileWithPassword(File file, String str, String str2) throws IOException, IllegalArgumentException {
        if (!str.endsWith("/")) {
            str = str + File.separatorChar;
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file, str2 != null ? str2.toCharArray() : null);
        zipFile.setCharset(Charset.forName("UTF-8"));
        zipFile.extractAll(file2.getPath());
    }
}
