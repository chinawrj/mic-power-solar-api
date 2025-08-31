package io.dcloud.p;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes3.dex */
public abstract class l1 {
    public static void a(byte[] bArr, int i, String str) throws IOException {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (parentFile.exists() || parentFile.mkdirs()) {
            if (file.exists()) {
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws");
                    randomAccessFile.setLength(bArr.length + i);
                    randomAccessFile.seek(i);
                    randomAccessFile.write(bArr);
                    randomAccessFile.close();
                    return;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            try {
                file.createNewFile();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e4) {
                e4.printStackTrace();
                fileOutputStream = null;
            }
            if (fileOutputStream != null) {
                try {
                    if (bArr != null) {
                        try {
                            try {
                                fileOutputStream.write(bArr, 0, bArr.length);
                                fileOutputStream.flush();
                            } catch (Throwable th) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                                throw th;
                            }
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            fileOutputStream.close();
                            return;
                        }
                    }
                    fileOutputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
        }
    }
}
