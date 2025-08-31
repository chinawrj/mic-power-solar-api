package dc.squareup.okhttp3;

import java.io.IOException;

/* loaded from: classes3.dex */
public interface Authenticator {
    public static final Authenticator NONE = new Authenticator() { // from class: dc.squareup.okhttp3.Authenticator.1
        @Override // dc.squareup.okhttp3.Authenticator
        public Request authenticate(Route route, Response response) {
            return null;
        }
    };

    Request authenticate(Route route, Response response) throws IOException;
}
