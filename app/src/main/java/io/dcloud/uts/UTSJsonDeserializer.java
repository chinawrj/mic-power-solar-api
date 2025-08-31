package io.dcloud.uts;

import io.dcloud.uts.gson.JsonDeserializationContext;
import io.dcloud.uts.gson.JsonDeserializer;
import io.dcloud.uts.gson.JsonElement;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JSON.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J(\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lio/dcloud/uts/UTSJsonDeserializer;", "Lio/dcloud/uts/gson/JsonDeserializer;", "Lio/dcloud/uts/UTSJSONObject;", "()V", "deserialize", "json", "Lio/dcloud/uts/gson/JsonElement;", "typeOfT", "Ljava/lang/reflect/Type;", "context", "Lio/dcloud/uts/gson/JsonDeserializationContext;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSJsonDeserializer implements JsonDeserializer<UTSJSONObject> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.dcloud.uts.gson.JsonDeserializer
    public UTSJSONObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if (json == null) {
            return new UTSJSONObject();
        }
        String string = json.toString();
        Intrinsics.checkNotNullExpressionValue(string, "json.toString()");
        return JSON.parseObject(string);
    }
}
