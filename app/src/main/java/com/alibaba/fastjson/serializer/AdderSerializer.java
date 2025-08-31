package com.alibaba.fastjson.serializer;

import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class AdderSerializer implements ObjectSerializer {
    public static final AdderSerializer instance = new AdderSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (Chip$$ExternalSyntheticApiModelOutline0.m$4(obj)) {
            serializeWriter.writeFieldValue(Operators.BLOCK_START, "value", Chip$$ExternalSyntheticApiModelOutline0.m218m(obj).longValue());
            serializeWriter.write(125);
        } else if (Chip$$ExternalSyntheticApiModelOutline0.m$5(obj)) {
            serializeWriter.writeFieldValue(Operators.BLOCK_START, "value", Chip$$ExternalSyntheticApiModelOutline0.m217m(obj).doubleValue());
            serializeWriter.write(125);
        }
    }
}
