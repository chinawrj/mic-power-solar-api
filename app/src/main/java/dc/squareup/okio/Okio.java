package dc.squareup.okio;

import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
public final class Okio {
    static final Logger logger = Logger.getLogger(Okio.class.getName());

    private Okio() {
    }

    public static Sink appendingSink(File file) throws FileNotFoundException {
        if (file != null) {
            return sink(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Sink blackhole() {
        return new Sink() { // from class: dc.squareup.okio.Okio.3
            @Override // dc.squareup.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
            }

            @Override // dc.squareup.okio.Sink, java.io.Flushable
            public void flush() throws IOException {
            }

            @Override // dc.squareup.okio.Sink
            public Timeout timeout() {
                return Timeout.NONE;
            }

            @Override // dc.squareup.okio.Sink
            public void write(Buffer buffer, long j) throws IOException {
                buffer.skip(j);
            }
        };
    }

    public static BufferedSource buffer(Source source) {
        return new RealBufferedSource(source);
    }

    static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static Sink sink(OutputStream outputStream) {
        return sink(outputStream, new Timeout());
    }

    public static Source source(InputStream inputStream) {
        return source(inputStream, new Timeout());
    }

    private static AsyncTimeout timeout(final Socket socket) {
        return new AsyncTimeout() { // from class: dc.squareup.okio.Okio.4
            @Override // dc.squareup.okio.AsyncTimeout
            protected IOException newTimeoutException(IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            @Override // dc.squareup.okio.AsyncTimeout
            protected void timedOut() throws IOException {
                try {
                    socket.close();
                } catch (AssertionError e) {
                    if (!Okio.isAndroidGetsocknameError(e)) {
                        throw e;
                    }
                    Okio.logger.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e);
                } catch (Exception e2) {
                    Okio.logger.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e2);
                }
            }
        };
    }

    public static BufferedSink buffer(Sink sink) {
        return new RealBufferedSink(sink);
    }

    private static Sink sink(final OutputStream outputStream, final Timeout timeout) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (timeout != null) {
            return new Sink() { // from class: dc.squareup.okio.Okio.1
                @Override // dc.squareup.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    outputStream.close();
                }

                @Override // dc.squareup.okio.Sink, java.io.Flushable
                public void flush() throws IOException {
                    outputStream.flush();
                }

                @Override // dc.squareup.okio.Sink
                public Timeout timeout() {
                    return timeout;
                }

                public String toString() {
                    return "sink(" + outputStream + Operators.BRACKET_END_STR;
                }

                @Override // dc.squareup.okio.Sink
                public void write(Buffer buffer, long j) throws IOException {
                    Util.checkOffsetAndCount(buffer.size, 0L, j);
                    while (j > 0) {
                        timeout.throwIfReached();
                        Segment segment = buffer.head;
                        int iMin = (int) Math.min(j, segment.limit - segment.pos);
                        outputStream.write(segment.data, segment.pos, iMin);
                        int i = segment.pos + iMin;
                        segment.pos = i;
                        long j2 = iMin;
                        j -= j2;
                        buffer.size -= j2;
                        if (i == segment.limit) {
                            buffer.head = segment.pop();
                            SegmentPool.recycle(segment);
                        }
                    }
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    private static Source source(final InputStream inputStream, final Timeout timeout) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (timeout != null) {
            return new Source() { // from class: dc.squareup.okio.Okio.2
                @Override // dc.squareup.okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    inputStream.close();
                }

                @Override // dc.squareup.okio.Source
                public long read(Buffer buffer, long j) throws IOException {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    }
                    if (j == 0) {
                        return 0L;
                    }
                    try {
                        timeout.throwIfReached();
                        Segment segmentWritableSegment = buffer.writableSegment(1);
                        int i = inputStream.read(segmentWritableSegment.data, segmentWritableSegment.limit, (int) Math.min(j, 8192 - segmentWritableSegment.limit));
                        if (i != -1) {
                            segmentWritableSegment.limit += i;
                            long j2 = i;
                            buffer.size += j2;
                            return j2;
                        }
                        if (segmentWritableSegment.pos != segmentWritableSegment.limit) {
                            return -1L;
                        }
                        buffer.head = segmentWritableSegment.pop();
                        SegmentPool.recycle(segmentWritableSegment);
                        return -1L;
                    } catch (AssertionError e) {
                        if (Okio.isAndroidGetsocknameError(e)) {
                            throw new IOException(e);
                        }
                        throw e;
                    }
                }

                @Override // dc.squareup.okio.Source
                public Timeout timeout() {
                    return timeout;
                }

                public String toString() {
                    return "source(" + inputStream + Operators.BRACKET_END_STR;
                }
            };
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static Sink sink(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getOutputStream() != null) {
                AsyncTimeout asyncTimeoutTimeout = timeout(socket);
                return asyncTimeoutTimeout.sink(sink(socket.getOutputStream(), asyncTimeoutTimeout));
            }
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static Source source(File file) throws FileNotFoundException {
        if (file != null) {
            return source(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Source source(Path path, OpenOption... openOptionArr) throws IOException {
        if (path != null) {
            return source(Files.newInputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }

    public static Source source(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getInputStream() != null) {
                AsyncTimeout asyncTimeoutTimeout = timeout(socket);
                return asyncTimeoutTimeout.source(source(socket.getInputStream(), asyncTimeoutTimeout));
            }
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static Sink sink(File file) throws FileNotFoundException {
        if (file != null) {
            return sink(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Sink sink(Path path, OpenOption... openOptionArr) throws IOException {
        if (path != null) {
            return sink(Files.newOutputStream(path, openOptionArr));
        }
        throw new IllegalArgumentException("path == null");
    }
}
