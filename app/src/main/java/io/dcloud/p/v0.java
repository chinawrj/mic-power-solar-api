package io.dcloud.p;

import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.DeviceInfo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/* loaded from: classes3.dex */
public abstract class v0 {
    public static boolean a(Object obj) throws InterruptedException {
        boolean zDelete;
        if (obj == null) {
            return false;
        }
        try {
            File fileC = c(obj);
            if (!fileC.exists()) {
                return false;
            }
            if (fileC.isFile()) {
                return fileC.delete();
            }
            File[] fileArrListFiles = fileC.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                for (int i = 0; i < fileArrListFiles.length; i++) {
                    b3.a("delete:" + fileArrListFiles[i].getPath());
                    if (fileArrListFiles[i].isDirectory()) {
                        zDelete = a((Object) (fileC.getPath() + "/" + fileArrListFiles[i].getName()));
                    } else {
                        zDelete = fileArrListFiles[i].delete();
                        Thread.sleep(2L);
                    }
                    if (!zDelete) {
                        return false;
                    }
                }
            }
            boolean zDelete2 = fileC.delete();
            b3.a("delete " + obj + ":" + String.valueOf(zDelete2));
            return zDelete2;
        } catch (Exception e) {
            b3.d("DHFile.delete" + e);
            return false;
        }
    }

    public static boolean b(Object obj) {
        boolean zExists;
        try {
            if (obj instanceof String) {
                String strSubstring = (String) obj;
                if (strSubstring.endsWith("/")) {
                    strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
                }
                zExists = new File(strSubstring).exists();
            } else {
                if (!(obj instanceof File)) {
                    return false;
                }
                zExists = ((File) obj).exists();
            }
            return zExists;
        } catch (Exception unused) {
            return false;
        }
    }

    private static File c(Object obj) {
        if (!(obj instanceof String)) {
            if (obj instanceof File) {
                return (File) obj;
            }
            return null;
        }
        String strSubstring = (String) obj;
        if (strSubstring.endsWith("/")) {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        return new File(strSubstring);
    }

    public static InputStream d(Object obj) {
        File file;
        if (obj instanceof String) {
            String strSubstring = (String) obj;
            if (strSubstring.startsWith(DeviceInfo.FILE_PROTOCOL)) {
                strSubstring = strSubstring.substring(7);
            }
            file = new File(strSubstring);
        } else {
            file = obj instanceof File ? (File) obj : null;
        }
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException unused) {
            b3.b("uniAD", "DHFile getInputStream not found file: " + file.getPath());
            return null;
        } catch (SecurityException e) {
            b3.d("getInputStream2" + e);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.InputStream] */
    public static byte[] e(Object obj) throws Throwable {
        InputStream inputStreamD;
        ?? r3 = 0;
        try {
            try {
                try {
                    inputStreamD = d(obj);
                } catch (FileNotFoundException e) {
                    e = e;
                    inputStreamD = null;
                } catch (IOException e2) {
                    e = e2;
                    inputStreamD = null;
                } catch (SecurityException e3) {
                    e = e3;
                    inputStreamD = null;
                } catch (Throwable th) {
                    th = th;
                    if (r3 != 0) {
                        try {
                            r3.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            if (inputStreamD == null) {
                if (inputStreamD != null) {
                    inputStreamD.close();
                }
                return null;
            }
            try {
                byte[] bArrA = a(inputStreamD);
                try {
                    inputStreamD.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                return bArrA;
            } catch (FileNotFoundException e7) {
                e = e7;
                b3.a("readAll 0:" + e.getLocalizedMessage());
                if (inputStreamD != null) {
                    inputStreamD.close();
                }
                return null;
            } catch (IOException e8) {
                e = e8;
                b3.d("readAll 2:" + e);
                if (inputStreamD != null) {
                    inputStreamD.close();
                }
                return null;
            } catch (SecurityException e9) {
                e = e9;
                b3.d("readAll 1:" + e);
                if (inputStreamD != null) {
                    inputStreamD.close();
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            r3 = obj;
        }
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[DHFile.BUF_SIZE];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                a((OutputStream) byteArrayOutputStream);
                return byteArray;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static void a(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean a(String str) {
        return b(c(str));
    }

    public static void a(byte[] bArr, int i, String str) throws IOException {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            b3.a(str + "cannot create!");
            return;
        }
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
                try {
                    if (bArr != null) {
                        try {
                            fileOutputStream.write(bArr, 0, bArr.length);
                            fileOutputStream.flush();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                            fileOutputStream.close();
                            return;
                        }
                    }
                    fileOutputStream.close();
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        }
    }

    public static void a(InputStream inputStream, String str) throws IOException {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            b3.a(str + "cannot create!");
            return;
        }
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rws");
                randomAccessFile.seek(file.length());
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = inputStream.read(bArr, 0, 8192);
                    if (i != -1) {
                        randomAccessFile.write(bArr, 0, i);
                    } else {
                        randomAccessFile.close();
                        return;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
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
            try {
                try {
                    if (fileOutputStream != null) {
                        try {
                            byte[] bArr2 = new byte[8192];
                            while (true) {
                                int i2 = inputStream.read(bArr2, 0, 8192);
                                if (i2 == -1) {
                                    break;
                                } else {
                                    fileOutputStream.write(bArr2, 0, i2);
                                }
                            }
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                            fileOutputStream.close();
                        }
                    }
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                throw th;
            }
        }
    }

    public static boolean a(String str, String str2) throws IOException {
        try {
            File file = new File(str);
            if (!file.exists() || !file.isFile() || !file.canRead()) {
                return false;
            }
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (-1 != i) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
