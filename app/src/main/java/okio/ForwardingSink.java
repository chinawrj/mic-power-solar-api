package okio;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class ForwardingSink implements Sink {
    private final Sink delegate;

    public ForwardingSink(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = sink;
    }

    public final Sink delegate() {
        return this.delegate;
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        this.delegate.write(buffer, j);
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    public String toString() {
        return getClass().getSimpleName() + Operators.BRACKET_START_STR + this.delegate.toString() + Operators.BRACKET_END_STR;
    }
}
