package io.dcloud.uts.gson;

import io.dcloud.uts.gson.stream.JsonReader;
import java.io.IOException;

/* loaded from: classes3.dex */
public interface ToNumberStrategy {
    Number readNumber(JsonReader jsonReader) throws IOException;
}
