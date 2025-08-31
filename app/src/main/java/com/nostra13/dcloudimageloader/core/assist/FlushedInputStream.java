package com.nostra13.dcloudimageloader.core.assist;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class FlushedInputStream extends FilterInputStream {
    public FlushedInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = 0;
        while (j2 < j) {
            long jSkip = this.in.skip(j - j2);
            if (jSkip == 0) {
                if (read() < 0) {
                    break;
                }
                jSkip = 1;
            }
            j2 += jSkip;
        }
        return j2;
    }
}
