package dc.squareup.okhttp3.internal.cache;

import dc.squareup.okio.Buffer;
import dc.squareup.okio.ForwardingSink;
import dc.squareup.okio.Sink;
import java.io.IOException;

/* loaded from: classes3.dex */
class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;

    FaultHidingSink(Sink sink) {
        super(sink);
    }

    @Override // dc.squareup.okio.ForwardingSink, dc.squareup.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    @Override // dc.squareup.okio.ForwardingSink, dc.squareup.okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    protected void onException(IOException iOException) {
    }

    @Override // dc.squareup.okio.ForwardingSink, dc.squareup.okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (this.hasErrors) {
            buffer.skip(j);
            return;
        }
        try {
            super.write(buffer, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }
}
