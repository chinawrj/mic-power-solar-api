package uts.sdk.modules.DCloudUniNetwork;

import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u0012"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/UploadFileOptionFiles;", "Lio/dcloud/uts/UTSObject;", "name", "", "uri", "file", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getFile", "()Ljava/lang/Object;", "setFile", "(Ljava/lang/Object;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getUri", "setUri", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UploadFileOptionFiles extends UTSObject {
    private Object file;
    private String name;

    @JsonNotNull
    private String uri;

    public /* synthetic */ UploadFileOptionFiles(String str, String str2, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, str2, (i & 4) != 0 ? null : obj);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uri = str;
    }

    public Object getFile() {
        return this.file;
    }

    public void setFile(Object obj) {
        this.file = obj;
    }

    public UploadFileOptionFiles(String str, String uri, Object obj) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.name = str;
        this.uri = uri;
        this.file = obj;
    }
}
