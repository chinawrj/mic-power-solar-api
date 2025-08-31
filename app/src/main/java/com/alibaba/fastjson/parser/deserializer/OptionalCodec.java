package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/* loaded from: classes.dex */
public class OptionalCodec implements ObjectSerializer, ObjectDeserializer {
    public static OptionalCodec instance = new OptionalCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type == Chip$$ExternalSyntheticApiModelOutline0.m$8()) {
            Integer numCastToInt = TypeUtils.castToInt(defaultJSONParser.parseObject((Class) Integer.class));
            if (numCastToInt == null) {
                return (T) OptionalInt.empty();
            }
            return (T) OptionalInt.of(numCastToInt.intValue());
        }
        if (type == Chip$$ExternalSyntheticApiModelOutline0.m$9()) {
            Long lCastToLong = TypeUtils.castToLong(defaultJSONParser.parseObject((Class) Long.class));
            if (lCastToLong == null) {
                return (T) OptionalLong.empty();
            }
            return (T) OptionalLong.of(lCastToLong.longValue());
        }
        if (type == Chip$$ExternalSyntheticApiModelOutline0.m$10()) {
            Double dCastToDouble = TypeUtils.castToDouble(defaultJSONParser.parseObject((Class) Double.class));
            if (dCastToDouble == null) {
                return (T) OptionalDouble.empty();
            }
            return (T) OptionalDouble.of(dCastToDouble.doubleValue());
        }
        Object object = defaultJSONParser.parseObject(TypeUtils.unwrapOptional(type));
        if (object == null) {
            return (T) Optional.empty();
        }
        return (T) Optional.of(object);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        if (obj == null) {
            jSONSerializer.writeNull();
            return;
        }
        if (Chip$$ExternalSyntheticApiModelOutline0.m$2(obj)) {
            Optional optionalM$1 = Chip$$ExternalSyntheticApiModelOutline0.m$1(obj);
            jSONSerializer.write(optionalM$1.isPresent() ? optionalM$1.get() : null);
            return;
        }
        if (Chip$$ExternalSyntheticApiModelOutline0.m223m(obj)) {
            OptionalDouble optionalDoubleM211m = Chip$$ExternalSyntheticApiModelOutline0.m211m(obj);
            if (optionalDoubleM211m.isPresent()) {
                jSONSerializer.write(Double.valueOf(optionalDoubleM211m.getAsDouble()));
                return;
            } else {
                jSONSerializer.writeNull();
                return;
            }
        }
        if (Chip$$ExternalSyntheticApiModelOutline0.m229m$1(obj)) {
            OptionalInt optionalIntM213m = Chip$$ExternalSyntheticApiModelOutline0.m213m(obj);
            if (optionalIntM213m.isPresent()) {
                jSONSerializer.out.writeInt(optionalIntM213m.getAsInt());
                return;
            } else {
                jSONSerializer.writeNull();
                return;
            }
        }
        if (Chip$$ExternalSyntheticApiModelOutline0.m$3(obj)) {
            OptionalLong optionalLongM216m = Chip$$ExternalSyntheticApiModelOutline0.m216m(obj);
            if (optionalLongM216m.isPresent()) {
                jSONSerializer.out.writeLong(optionalLongM216m.getAsLong());
                return;
            } else {
                jSONSerializer.writeNull();
                return;
            }
        }
        throw new JSONException("not support optional : " + obj.getClass());
    }
}
