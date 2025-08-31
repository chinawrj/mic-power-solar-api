package io.dcloud.uts;

import java.util.Iterator;
import kotlin.Metadata;

/* compiled from: UTSObject.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0001H¦\u0002J\u000f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H¦\u0002J\u001b\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00012\b\u0010\t\u001a\u0004\u0018\u00010\u0001H¦\u0002¨\u0006\n"}, d2 = {"Lio/dcloud/uts/IUTSObject;", "", "get", "propertyName", "iterator", "", "", "set", "", "value", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface IUTSObject {
    java.lang.Object get(java.lang.Object propertyName);

    Iterator<String> iterator();

    void set(java.lang.Object propertyName, java.lang.Object value);
}
