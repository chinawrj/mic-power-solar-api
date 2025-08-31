package io.dcloud.uts;

import io.dcloud.uts.android.UTSGsonEncoder;
import io.dcloud.uts.gson.JsonElement;
import io.dcloud.uts.gson.JsonNull;
import io.dcloud.uts.gson.JsonSerializationContext;
import io.dcloud.uts.gson.JsonSerializer;
import io.dcloud.uts.json.IJsonStringify;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSJSONObject.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Lio/dcloud/uts/UTSJSONSelfJsonSerializer;", "Lio/dcloud/uts/gson/JsonSerializer;", "Lio/dcloud/uts/json/IJsonStringify;", "()V", "serialize", "Lio/dcloud/uts/gson/JsonElement;", "src", "typeOfSrc", "Ljava/lang/reflect/Type;", "context", "Lio/dcloud/uts/gson/JsonSerializationContext;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSJSONSelfJsonSerializer implements JsonSerializer<IJsonStringify> {
    @Override // io.dcloud.uts.gson.JsonSerializer
    public JsonElement serialize(IJsonStringify src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement jsonElementEncode = new UTSGsonEncoder().encode(src, null);
        if (jsonElementEncode != null) {
            return jsonElementEncode;
        }
        JsonNull INSTANCE = JsonNull.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
        return INSTANCE;
    }
}
