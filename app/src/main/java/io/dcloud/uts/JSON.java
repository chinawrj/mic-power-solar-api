package io.dcloud.uts;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.uts.JSON;
import io.dcloud.uts.android.ClassLogWrapper;
import io.dcloud.uts.android.UTSGsonEncoder;
import io.dcloud.uts.gson.Gson;
import io.dcloud.uts.gson.GsonBuilder;
import io.dcloud.uts.gson.JsonElement;
import io.dcloud.uts.gson.JsonNull;
import io.dcloud.uts.gson.ToNumberPolicy;
import io.dcloud.uts.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: JSON.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u00010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J'\u0010\u0012\u001a\u0004\u0018\u0001H\u0015\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0016\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0002\u0010\u0018J1\u0010\u0012\u001a\u0004\u0018\u0001H\u0015\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0016\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007¢\u0006\u0002\u0010\u001bJ\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0007J,\u0010\u0012\u001a\u0004\u0018\u0001H\u0015\"\u0006\b\u0000\u0010\u0015\u0018\u00012\u0006\u0010\u0016\u001a\u00020\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0087\b¢\u0006\u0004\b\u001c\u0010\u001dJ\u0016\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J&\u0010\u001e\u001a\n\u0012\u0004\u0012\u0002H\u0015\u0018\u00010\u000b\"\u0006\b\u0000\u0010\u0015\u0018\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b¢\u0006\u0002\b\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\"\u0010 \u001a\u0004\u0018\u0001H\u0015\"\u0006\b\u0000\u0010\u0015\u0018\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b¢\u0006\u0004\b!\u0010\"J\u0012\u0010#\u001a\u00020\u00142\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u0007J3\u0010#\u001a\u00020\u00142\b\u0010$\u001a\u0004\u0018\u00010\u00012\u000e\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\b'Jd\u0010#\u001a\u00020\u00142\b\u0010$\u001a\u0004\u0018\u00010\u00012D\b\u0002\u0010(\u001a>\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(-\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010)j\u0004\u0018\u0001`.2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0001H\u0007J\"\u0010\u0012\u001a\u0002H\u0015\"\u0006\b\u0000\u0010\u0015\u0018\u0001*\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0014H\u0086\b¢\u0006\u0002\u0010/R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lio/dcloud/uts/JSON;", "", "()V", "cacheParseGson", "Lio/dcloud/uts/gson/Gson;", "getCacheParseGson", "()Lio/dcloud/uts/gson/Gson;", "hostAnyType", "Ljava/lang/reflect/Type;", "kotlin.jvm.PlatformType", "convertJSONArray", "Lio/dcloud/uts/UTSArray;", "jsonArray", "Lcom/alibaba/fastjson/JSONArray;", "convertJSONObject", "Lio/dcloud/uts/UTSJSONObject;", "inputObject", "Lcom/alibaba/fastjson/JSONObject;", "parse", "inputString", "", ExifInterface.GPS_DIRECTION_TRUE, "json", "typeOfT", "(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;", "ignoreError", "", "(Ljava/lang/String;Ljava/lang/reflect/Type;Z)Ljava/lang/Object;", "parseType", "(Ljava/lang/String;Z)Ljava/lang/Object;", "parseArray", "parseArrayType", "parseObject", "parseObjectType", "(Ljava/lang/String;)Ljava/lang/Object;", "stringify", "sourceInput", "replacerArray", "space", "stringify_replacerArray", "replacer", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", IApp.ConfigProperty.CONFIG_KEY, "value", "Lio/dcloud/uts/stringifyReplacer;", "(Lio/dcloud/uts/gson/Gson;Ljava/lang/String;)Ljava/lang/Object;", "JSON_SKIP_OBJECT", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class JSON {
    public static final JSON INSTANCE = new JSON();
    private static final Gson cacheParseGson;
    private static final Type hostAnyType;

    private JSON() {
    }

    /* compiled from: JSON.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/dcloud/uts/JSON$JSON_SKIP_OBJECT;", "", "()V", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class JSON_SKIP_OBJECT {
        public static final JSON_SKIP_OBJECT INSTANCE = new JSON_SKIP_OBJECT();

        private JSON_SKIP_OBJECT() {
        }
    }

    public final /* synthetic */ <T> T parse(Gson gson, String json) {
        Intrinsics.checkNotNullParameter(gson, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.needClassReification();
        return (T) gson.fromJson(json, new TypeToken<T>() { // from class: io.dcloud.uts.JSON.parse.1
        }.getType());
    }

    public static /* synthetic */ java.lang.Object parseType$default(String json, boolean z, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        if (Intrinsics.areEqual("String", java.lang.Object.class.getSimpleName())) {
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return json;
        }
        java.util.Map<String, Exception> globalError = ObjectKt.getGlobalError();
        String name = Thread.currentThread().getName();
        Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
        globalError.put(name, null);
        try {
            Gson cacheParseGson2 = INSTANCE.getCacheParseGson();
            Intrinsics.needClassReification();
            return cacheParseGson2.fromJson(json, new JSON$parse$$inlined$parse$1().getType());
        } catch (Exception e) {
            if (z) {
                return null;
            }
            java.util.Map<String, Exception> globalError2 = ObjectKt.getGlobalError();
            String name2 = Thread.currentThread().getName();
            Intrinsics.checkNotNullExpressionValue(name2, "currentThread().name");
            globalError2.put(name2, e);
            return null;
        }
    }

    @JvmStatic
    public static final /* synthetic */ <T> T parseType(String json, boolean ignoreError) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        if (Intrinsics.areEqual("String", java.lang.Object.class.getSimpleName())) {
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) json;
        }
        java.util.Map<String, Exception> globalError = ObjectKt.getGlobalError();
        String name = Thread.currentThread().getName();
        Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
        globalError.put(name, null);
        try {
            Gson cacheParseGson2 = INSTANCE.getCacheParseGson();
            Intrinsics.needClassReification();
            return (T) cacheParseGson2.fromJson(json, new JSON$parse$$inlined$parse$1().getType());
        } catch (Exception e) {
            if (ignoreError) {
                return null;
            }
            java.util.Map<String, Exception> globalError2 = ObjectKt.getGlobalError();
            String name2 = Thread.currentThread().getName();
            Intrinsics.checkNotNullExpressionValue(name2, "currentThread().name");
            globalError2.put(name2, e);
            return null;
        }
    }

    @JvmStatic
    public static final <T> T parse(String json, Type typeOfT) {
        Intrinsics.checkNotNullParameter(json, "json");
        return (T) parse(json, typeOfT, false);
    }

    public static /* synthetic */ java.lang.Object parse$default(String str, Type type, boolean z, int i, java.lang.Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return parse(str, type, z);
    }

    @JvmStatic
    public static final <T> T parse(String json, Type typeOfT, boolean ignoreError) {
        Intrinsics.checkNotNullParameter(json, "json");
        if ((typeOfT instanceof Class) && String.class.isAssignableFrom((Class) typeOfT)) {
            return (T) json;
        }
        java.util.Map<String, Exception> globalError = ObjectKt.getGlobalError();
        String name = Thread.currentThread().getName();
        Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
        globalError.put(name, null);
        try {
            return (T) cacheParseGson.fromJson(json, typeOfT);
        } catch (Exception e) {
            if (ignoreError) {
                return null;
            }
            java.util.Map<String, Exception> globalError2 = ObjectKt.getGlobalError();
            String name2 = Thread.currentThread().getName();
            Intrinsics.checkNotNullExpressionValue(name2, "currentThread().name");
            globalError2.put(name2, e);
            return null;
        }
    }

    public final UTSJSONObject convertJSONObject(JSONObject inputObject) {
        Intrinsics.checkNotNullParameter(inputObject, "inputObject");
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        for (Map.Entry<String, java.lang.Object> entry : inputObject.entrySet()) {
            java.lang.Object value = entry.getValue();
            if (value instanceof JSONObject) {
                uTSJSONObject.getDynamicJSONFields().put(entry.getKey(), convertJSONObject((JSONObject) value));
            } else if (value instanceof JSONArray) {
                uTSJSONObject.getDynamicJSONFields().put(entry.getKey(), convertJSONArray((JSONArray) value));
            } else {
                uTSJSONObject.getDynamicJSONFields().put(entry.getKey(), value);
            }
        }
        return uTSJSONObject;
    }

    public final UTSArray<?> convertJSONArray(JSONArray jsonArray) {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        UTSArray<?> uTSArray = new UTSArray<>();
        for (java.lang.Object it : jsonArray) {
            if (it instanceof JSONObject) {
                JSON json = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                it = json.convertJSONObject((JSONObject) it);
            } else if (it instanceof JSONArray) {
                JSON json2 = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                it = json2.convertJSONArray((JSONArray) it);
            }
            uTSArray.add(it);
        }
        return uTSArray;
    }

    static {
        Gson gsonCreate = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.UTS_Number).registerTypeAdapter(UTSJSONObject.class, new UTSJsonDeserializer()).create();
        Intrinsics.checkNotNullExpressionValue(gsonCreate, "GsonBuilder()\n        .s…izer())\n        .create()");
        cacheParseGson = gsonCreate;
        hostAnyType = new TypeToken<java.lang.Object>() { // from class: io.dcloud.uts.JSON$hostAnyType$1
        }.getType();
    }

    public final Gson getCacheParseGson() {
        return cacheParseGson;
    }

    @JvmStatic
    public static final java.lang.Object parse(String inputString) {
        Intrinsics.checkNotNullParameter(inputString, "inputString");
        return parse(inputString, false);
    }

    public static /* synthetic */ java.lang.Object parse$default(String str, boolean z, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return parse(str, z);
    }

    @JvmStatic
    public static final java.lang.Object parse(String inputString, boolean ignoreError) {
        Intrinsics.checkNotNullParameter(inputString, "inputString");
        java.util.Map<String, Exception> globalError = ObjectKt.getGlobalError();
        String name = Thread.currentThread().getName();
        Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
        globalError.put(name, null);
        if (StringsKt.isBlank(inputString)) {
            if (!ignoreError) {
                java.util.Map<String, Exception> globalError2 = ObjectKt.getGlobalError();
                String name2 = Thread.currentThread().getName();
                Intrinsics.checkNotNullExpressionValue(name2, "currentThread().name");
                globalError2.put(name2, new IllegalArgumentException("JSON.parse error: input text is empty"));
            }
            return null;
        }
        try {
            return cacheParseGson.fromJson(inputString, hostAnyType);
        } catch (Exception e) {
            if (ignoreError) {
                return null;
            }
            java.util.Map<String, Exception> globalError3 = ObjectKt.getGlobalError();
            String name3 = Thread.currentThread().getName();
            Intrinsics.checkNotNullExpressionValue(name3, "currentThread().name");
            globalError3.put(name3, e);
            return null;
        }
    }

    public static /* synthetic */ String stringify_replacerArray$default(java.lang.Object obj, UTSArray uTSArray, java.lang.Object obj2, int i, java.lang.Object obj3) {
        if ((i & 4) != 0) {
            obj2 = null;
        }
        return stringify_replacerArray(obj, uTSArray, obj2);
    }

    @JvmStatic
    public static final String stringify_replacerArray(java.lang.Object sourceInput, final UTSArray<java.lang.Object> replacerArray, java.lang.Object space) {
        Intrinsics.checkNotNullParameter(replacerArray, "replacerArray");
        return stringify(sourceInput, new Function2<java.lang.Object, java.lang.Object, java.lang.Object>() { // from class: io.dcloud.uts.JSON$stringify$replacer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final java.lang.Object invoke(java.lang.Object key, java.lang.Object obj) {
                Intrinsics.checkNotNullParameter(key, "key");
                return (Intrinsics.areEqual(key, "") || replacerArray.contains(key)) ? obj : JSON.JSON_SKIP_OBJECT.INSTANCE;
            }
        }, space);
    }

    @JvmStatic
    public static final String stringify(java.lang.Object sourceInput) {
        return stringify(sourceInput, null, null);
    }

    public static /* synthetic */ String stringify$default(java.lang.Object obj, Function2 function2, java.lang.Object obj2, int i, java.lang.Object obj3) {
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            obj2 = null;
        }
        return stringify(obj, function2, obj2);
    }

    @JvmStatic
    public static final String stringify(java.lang.Object sourceInput, Function2<? super String, java.lang.Object, ? extends java.lang.Object> replacer, java.lang.Object space) {
        if (replacer != null) {
            sourceInput = replacer.invoke("", sourceInput);
        }
        if (sourceInput == null) {
            String string = JsonNull.INSTANCE.toString();
            Intrinsics.checkNotNullExpressionValue(string, "INSTANCE.toString()");
            return string;
        }
        if (sourceInput instanceof Function) {
            return "";
        }
        if (sourceInput instanceof Number) {
            if (UTSNumber.INSTANCE.isNaN(sourceInput)) {
                String string2 = JsonNull.INSTANCE.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "INSTANCE.toString()");
                return string2;
            }
            if (Intrinsics.areEqual(sourceInput, Double.valueOf(Double.POSITIVE_INFINITY)) || Intrinsics.areEqual(sourceInput, Float.valueOf(Float.POSITIVE_INFINITY)) || Intrinsics.areEqual(sourceInput, Double.valueOf(Double.NEGATIVE_INFINITY)) || Intrinsics.areEqual(sourceInput, Float.valueOf(Float.NEGATIVE_INFINITY))) {
                String string3 = JsonNull.INSTANCE.toString();
                Intrinsics.checkNotNullExpressionValue(string3, "INSTANCE.toString()");
                return string3;
            }
            return ClassLogWrapper.INSTANCE.wrapNumberText(sourceInput);
        }
        if (sourceInput instanceof JSONObject) {
            String jSONString = ((JSONObject) sourceInput).toJSONString();
            Intrinsics.checkNotNullExpressionValue(jSONString, "inputObject.toJSONString()");
            return jSONString;
        }
        JsonElement jsonElementEncode = new UTSGsonEncoder().encode(sourceInput, replacer);
        if (jsonElementEncode == null) {
            String string4 = JsonNull.INSTANCE.toString();
            Intrinsics.checkNotNullExpressionValue(string4, "INSTANCE.toString()");
            return string4;
        }
        if (space == null) {
            String string5 = jsonElementEncode.toString();
            Intrinsics.checkNotNullExpressionValue(string5, "gsonTree.toString()");
            return string5;
        }
        if (space instanceof Number) {
            int iIntValue = ((Number) space).intValue();
            if (iIntValue > 0) {
                String string6 = jsonElementEncode.toString(StringKt.repeat(Operators.SPACE_STR, Integer.valueOf(iIntValue <= 10 ? iIntValue : 10)));
                Intrinsics.checkNotNullExpressionValue(string6, "{\n                      …tr)\n                    }");
                return string6;
            }
            String string7 = jsonElementEncode.toString();
            Intrinsics.checkNotNullExpressionValue(string7, "{\n                      …g()\n                    }");
            return string7;
        }
        if (space instanceof String) {
            String strSubstring = (String) space;
            if (strSubstring.length() > 10) {
                strSubstring = StringKt.substring(strSubstring, (Number) 0, (Number) 10);
            }
            String string8 = jsonElementEncode.toString(strSubstring);
            Intrinsics.checkNotNullExpressionValue(string8, "gsonTree.toString(spaceStr)");
            return string8;
        }
        String string9 = jsonElementEncode.toString();
        Intrinsics.checkNotNullExpressionValue(string9, "gsonTree.toString()");
        return string9;
    }

    @JvmStatic
    public static final UTSJSONObject parseObject(String inputString) {
        Intrinsics.checkNotNullParameter(inputString, "inputString");
        java.lang.Object obj = parse(inputString);
        if (obj != null && (obj instanceof UTSJSONObject)) {
            return (UTSJSONObject) obj;
        }
        return null;
    }

    @JvmStatic
    public static final /* synthetic */ <T> T parseObjectType(String inputString) {
        T t;
        Intrinsics.checkNotNullParameter(inputString, "inputString");
        JSON json = INSTANCE;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        if (Intrinsics.areEqual("String", java.lang.Object.class.getSimpleName())) {
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            t = (T) inputString;
        } else {
            java.util.Map<String, Exception> globalError = ObjectKt.getGlobalError();
            String name = Thread.currentThread().getName();
            Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
            globalError.put(name, null);
            try {
                Gson cacheParseGson2 = json.getCacheParseGson();
                Intrinsics.needClassReification();
                t = (T) cacheParseGson2.fromJson(inputString, new TypeToken<T>() { // from class: io.dcloud.uts.JSON$parseObject$$inlined$parseType$default$1
                }.getType());
            } catch (Exception e) {
                java.util.Map<String, Exception> globalError2 = ObjectKt.getGlobalError();
                String name2 = Thread.currentThread().getName();
                Intrinsics.checkNotNullExpressionValue(name2, "currentThread().name");
                globalError2.put(name2, e);
                t = null;
            }
        }
        if (t == null) {
            return null;
        }
        return t;
    }

    @JvmStatic
    public static final UTSArray<?> parseArray(String inputString) {
        Intrinsics.checkNotNullParameter(inputString, "inputString");
        java.lang.Object obj = parse(inputString);
        if (obj != null && (obj instanceof UTSArray)) {
            return (UTSArray) obj;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final /* synthetic */ <T> UTSArray<T> parseArrayType(String inputString) {
        java.lang.Object objFromJson;
        Intrinsics.checkNotNullParameter(inputString, "inputString");
        JSON json = INSTANCE;
        if (Intrinsics.areEqual("String", "UTSArray")) {
            objFromJson = (UTSArray) inputString;
        } else {
            java.util.Map<String, Exception> globalError = ObjectKt.getGlobalError();
            String name = Thread.currentThread().getName();
            Intrinsics.checkNotNullExpressionValue(name, "currentThread().name");
            globalError.put(name, null);
            try {
                objFromJson = json.getCacheParseGson().fromJson(inputString, new JSON$parseArray$$inlined$parseType$default$1().getType());
            } catch (Exception e) {
                java.util.Map<String, Exception> globalError2 = ObjectKt.getGlobalError();
                String name2 = Thread.currentThread().getName();
                Intrinsics.checkNotNullExpressionValue(name2, "currentThread().name");
                globalError2.put(name2, e);
                objFromJson = null;
            }
        }
        UTSArray<T> uTSArray = (UTSArray) objFromJson;
        if (uTSArray != null) {
            return uTSArray;
        }
        return null;
    }
}
