package io.dcloud.uts;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.performance.WXInstanceApm;
import io.dcloud.common.util.AppConsoleLogUtil;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.uts.android.ClassLogWrapper;
import io.dcloud.uts.gson.Gson;
import io.dcloud.uts.gson.JsonArray;
import io.dcloud.uts.gson.JsonNull;
import io.dcloud.uts.gson.JsonObject;
import io.dcloud.uts.log.LogSelf;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Deprecated;
import kotlin.ExceptionsKt;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: console.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u0010\fJ%\u0010\r\u001a\u00020\u00072\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u00020\u00072\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u000eJ#\u0010\u0010\u001a\u00020\u00072\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000eJ#\u0010\u0011\u001a\u00020\u00072\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0013\u001a\u00020\u0001H\u0002J/\u0010\u0014\u001a\u00020\t2\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\u00020\t2\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0001H\u0016J%\u0010\u001c\u001a\u00020\u00072\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u000eJ%\u0010\u001d\u001a\u00020\u00072\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u000eJ%\u0010\u001e\u001a\u00020\u00072\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u001f\u001a\u00020\t2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030!H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lio/dcloud/uts/console;", "", "()V", "consoleThreadPool", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "asyncV2Log", "", "tag", "", "data", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "debug", "([Ljava/lang/Object;)V", "error", "errorV1", "errorV1WithStack", "getBasicField", "anyInstance", "getLog", "alwaysNeedStack", "", "([Ljava/lang/Object;Z)Ljava/lang/String;", "getLogV2", "([Ljava/lang/Object;)Ljava/lang/String;", "getObjectJSON", "Lio/dcloud/uts/gson/JsonObject;", "info", "log", "warn", "wrapClassLogStr", "k", "Ljava/lang/Class;", "Companion", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class console {
    public static final console INSTANCE = new console();
    private static final ExecutorService consoleThreadPool = Executors.newFixedThreadPool(1);

    private console() {
    }

    /* compiled from: console.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0007J%\u0010\b\u001a\u00020\u00042\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0007J%\u0010\t\u001a\u00020\u00042\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0007J%\u0010\n\u001a\u00020\u00042\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0007J%\u0010\u000b\u001a\u00020\u00042\u0016\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006\"\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\f"}, d2 = {"Lio/dcloud/uts/console$Companion;", "", "()V", "debug", "", "data", "", "([Ljava/lang/Object;)V", "error", "info", "log", "warn", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public static final Companion INSTANCE = new Companion();

        private Companion() {
        }

        @Deprecated(message = "use console.debug() instead", replaceWith = @ReplaceWith(expression = "console.debug(*data)", imports = {}))
        @JvmStatic
        public static final void debug(java.lang.Object... data) {
            Intrinsics.checkNotNullParameter(data, "data");
            console.debug(Arrays.copyOf(data, data.length));
        }

        @Deprecated(message = "use console.error() instead", replaceWith = @ReplaceWith(expression = "console.error(*data)", imports = {}))
        @JvmStatic
        public static final void error(java.lang.Object... data) {
            Intrinsics.checkNotNullParameter(data, "data");
            console.error(Arrays.copyOf(data, data.length));
        }

        @Deprecated(message = "use console.info() instead", replaceWith = @ReplaceWith(expression = "console.info(*data)", imports = {}))
        @JvmStatic
        public static final void info(java.lang.Object... data) {
            Intrinsics.checkNotNullParameter(data, "data");
            console.info(Arrays.copyOf(data, data.length));
        }

        @Deprecated(message = "use console.log() instead", replaceWith = @ReplaceWith(expression = "console.log(*data)", imports = {}))
        @JvmStatic
        public static final void log(java.lang.Object... data) {
            Intrinsics.checkNotNullParameter(data, "data");
            console.log(Arrays.copyOf(data, data.length));
        }

        @Deprecated(message = "use console.warn() instead", replaceWith = @ReplaceWith(expression = "console.warn(*data)", imports = {}))
        @JvmStatic
        public static final void warn(java.lang.Object... data) {
            Intrinsics.checkNotNullParameter(data, "data");
            console.warn(Arrays.copyOf(data, data.length));
        }
    }

    public String wrapClassLogStr(Class<?> k) {
        String canonicalName;
        Intrinsics.checkNotNullParameter(k, "k");
        String canonicalName2 = k.getCanonicalName();
        if (canonicalName2 == null || canonicalName2.length() == 0) {
            canonicalName = "";
        } else {
            canonicalName = k.getCanonicalName();
            Intrinsics.checkNotNull(canonicalName);
            if (StringsKt.endsWith$default(canonicalName, ".Companion", false, 2, (java.lang.Object) null)) {
                canonicalName = StringKt.substring(canonicalName, (Number) 0, StringKt.lastIndexOf$default(canonicalName, ".Companion", null, 2, null));
            }
        }
        return "" + canonicalName + Operators.SPACE_STR;
    }

    private final java.lang.Object getBasicField(java.lang.Object anyInstance) {
        if ((anyInstance instanceof String) || (anyInstance instanceof Boolean) || (anyInstance instanceof Number)) {
            return anyInstance;
        }
        return null;
    }

    public JsonObject getObjectJSON(java.lang.Object anyInstance) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(anyInstance, "anyInstance");
        JsonObject jsonObject = new JsonObject();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Field[] declaredFields = anyInstance.getClass().getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "anyInstance::class.java.declaredFields");
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if ((field.getModifiers() & 2) == 0) {
                try {
                    java.lang.Object obj = field.get(anyInstance);
                    if (obj != null) {
                        console consoleVar = INSTANCE;
                        java.lang.Object basicField = consoleVar.getBasicField(obj);
                        if (basicField != null) {
                            if (basicField instanceof String) {
                                jsonObject.addProperty(field.getName(), (String) basicField);
                            } else if (basicField instanceof Number) {
                                jsonObject.addProperty(field.getName(), (Number) basicField);
                            } else if (basicField instanceof Boolean) {
                                jsonObject.addProperty(field.getName(), (Boolean) basicField);
                            }
                        } else if (obj instanceof java.util.Map) {
                            jsonObject.add(field.getName(), new Gson().toJsonTree(obj));
                        } else if ((obj instanceof Collection) && !(obj instanceof java.util.Map)) {
                            JsonArray jsonArray = new JsonArray();
                            for (java.lang.Object obj2 : (Iterable) obj) {
                                if (obj2 == null) {
                                    console consoleVar2 = INSTANCE;
                                    JsonNull INSTANCE2 = JsonNull.INSTANCE;
                                    Intrinsics.checkNotNullExpressionValue(INSTANCE2, "INSTANCE");
                                    jsonArray.add(consoleVar2.getObjectJSON(INSTANCE2));
                                } else {
                                    jsonArray.add(INSTANCE.getObjectJSON(obj2));
                                }
                            }
                            jsonObject.add(field.getName(), jsonArray);
                        } else if (obj instanceof UTSObject) {
                            jsonObject.add(field.getName(), new Gson().toJsonTree(obj));
                        } else {
                            jsonObject.addProperty(field.getName(), consoleVar.wrapClassLogStr(obj.getClass()));
                        }
                        String fieldName = field.getName();
                        Intrinsics.checkNotNullExpressionValue(fieldName, "fieldName");
                        if (fieldName.length() > 0) {
                            linkedHashSet.add("get" + StringKt.toUpperCase(StringKt.substring(fieldName, (Number) 0, (Number) 1)) + StringKt.substring$default(fieldName, (Number) 1, null, 2, null));
                            linkedHashSet.add("set" + StringKt.toUpperCase(StringKt.substring(fieldName, (Number) 0, (Number) 1)) + StringKt.substring$default(fieldName, (Number) 1, null, 2, null));
                        }
                    } else {
                        jsonObject.add(field.getName(), JsonNull.INSTANCE);
                        String fieldName2 = field.getName();
                        Intrinsics.checkNotNullExpressionValue(fieldName2, "fieldName");
                        if (fieldName2.length() > 0) {
                            linkedHashSet.add("get" + StringKt.toUpperCase(StringKt.substring(fieldName2, (Number) 0, (Number) 1)) + StringKt.substring$default(fieldName2, (Number) 1, null, 2, null));
                            linkedHashSet.add("set" + StringKt.toUpperCase(StringKt.substring(fieldName2, (Number) 0, (Number) 1)) + StringKt.substring$default(fieldName2, (Number) 1, null, 2, null));
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
        Method[] declaredMethods = anyInstance.getClass().getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "anyInstance::class.java.declaredMethods");
        for (Method method : declaredMethods) {
            method.setAccessible(true);
            if ((method.getModifiers() & 2) == 0 && !linkedHashSet.contains(method.getName())) {
                jsonObject.addProperty(method.getName(), "f () { [native code] } ");
            }
        }
        return jsonObject;
    }

    private final void asyncV2Log(final String tag, final java.lang.Object... data) {
        consoleThreadPool.submit(new Runnable() { // from class: io.dcloud.uts.console$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                console.asyncV2Log$lambda$3(data, tag);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void asyncV2Log$lambda$3(java.lang.Object[] data, String tag) {
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(tag, "$tag");
        AppConsoleLogUtil.DCLog(INSTANCE.getLogV2(Arrays.copyOf(data, data.length)), tag);
    }

    @JvmStatic
    public static final void debug(java.lang.Object... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        INSTANCE.asyncV2Log("DEBUG", Arrays.copyOf(data, data.length));
    }

    @JvmStatic
    public static final void error(java.lang.Object... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        INSTANCE.asyncV2Log("ERROR", Arrays.copyOf(data, data.length));
    }

    @JvmStatic
    public static final void info(java.lang.Object... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        INSTANCE.asyncV2Log("INFO", Arrays.copyOf(data, data.length));
    }

    @JvmStatic
    public static final void log(java.lang.Object... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        INSTANCE.asyncV2Log("LOG", Arrays.copyOf(data, data.length));
    }

    @JvmStatic
    public static final void warn(java.lang.Object... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        INSTANCE.asyncV2Log("WARN", Arrays.copyOf(data, data.length));
    }

    public final void errorV1(java.lang.Object... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AppConsoleLogUtil.DCLog(getLog$default(this, Arrays.copyOf(data, data.length), false, 2, null), "ERROR");
    }

    public final void errorV1WithStack(java.lang.Object... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AppConsoleLogUtil.DCLog(getLog(Arrays.copyOf(data, data.length), true), "ERROR");
    }

    static /* synthetic */ String getLog$default(console consoleVar, java.lang.Object[] objArr, boolean z, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return consoleVar.getLog(objArr, z);
    }

    private final String getLog(java.lang.Object[] data, boolean alwaysNeedStack) {
        String string;
        String strValueOf;
        if (data.length == 0) {
            return "---NULL---";
        }
        String str = "";
        boolean z = false;
        for (java.lang.Object obj : data) {
            boolean z2 = obj instanceof String;
            if ((!z2 || !StringsKt.startsWith$default(StringsKt.trim((CharSequence) obj).toString(), "at ", false, 2, (java.lang.Object) null)) && z) {
                str = str + "---COMMA---";
            }
            if (obj == null) {
                str = str + "---NULL---";
            } else if (obj instanceof LogSelf) {
                java.lang.Object log = ((LogSelf) obj).toLog();
                if (log instanceof String) {
                    str = str + log;
                } else if (log instanceof UTSJSONObject) {
                    str = ((str + "---BEGIN:JSON---") + ((UTSJSONObject) log).toJSONString()) + "---END:JSON---";
                } else if (log instanceof UTSArray) {
                    str = ((str + "---BEGIN:JSON---") + JSON.stringify(obj)) + "---END:JSON---";
                } else {
                    if (log == null) {
                        return "---NULL---";
                    }
                }
            } else if (obj instanceof Throwable) {
                StringBuilder sb = new StringBuilder();
                if ((obj instanceof UTSError) && !alwaysNeedStack) {
                    sb.append(((Throwable) obj).toString());
                } else if (obj instanceof InvocationTargetException) {
                    Throwable targetEx = ((InvocationTargetException) obj).getTargetException();
                    Intrinsics.checkNotNullExpressionValue(targetEx, "targetEx");
                    sb.append(ExceptionsKt.stackTraceToString(targetEx));
                } else {
                    sb.append(ExceptionsKt.stackTraceToString((Throwable) obj));
                }
                str = ((str + "---BEGIN:EXCEPTION---") + ((java.lang.Object) sb)) + "---END:EXCEPTION---";
            } else if (obj instanceof Function) {
                str = str + "f () { [native code] } ";
            } else if (obj instanceof Date) {
                str = str + ((Date) obj).toString();
            } else {
                boolean z3 = obj instanceof UTSJSONObject;
                if (z3 || (obj instanceof JSONObject) || (obj instanceof JSONArray) || (obj instanceof UTSObject)) {
                    String str2 = str + "---BEGIN:JSON---";
                    str = (z3 ? str2 + ((UTSJSONObject) obj).toJSONObject() : str2 + JSON.stringify(obj)) + "---END:JSON---";
                } else if (obj instanceof UTSArray) {
                    str = ((str + "---BEGIN:JSON---") + JSON.stringify(obj)) + "---END:JSON---";
                } else if (obj instanceof java.util.Map) {
                    str = ((str + "---BEGIN:JSON---") + ("Map(" + ((java.util.Map) obj).size() + ") ") + JSON.stringify(obj)) + "---END:JSON---";
                } else if (obj instanceof Number) {
                    boolean zAreEqual = Intrinsics.areEqual(obj, Float.valueOf(0.0f));
                    String str3 = WXInstanceApm.VALUE_ERROR_CODE_DEFAULT;
                    if (!zAreEqual && !Intrinsics.areEqual(obj, Double.valueOf(0.0d)) && !Intrinsics.areEqual(obj, (java.lang.Object) 0L) && (!Intrinsics.areEqual(obj, (java.lang.Object) 0) || !(obj instanceof Integer))) {
                        Number number = (Number) obj;
                        if (number.intValue() != 0 || (!(obj instanceof Short) && !(obj instanceof Byte) && !(obj instanceof UInt) && !(obj instanceof UByte) && !(obj instanceof ULong) && !(obj instanceof UShort))) {
                            if (Intrinsics.areEqual(NumberKt.div(number, Integer.valueOf(number.intValue())), Double.valueOf(1.0d))) {
                                strValueOf = String.valueOf(number.intValue());
                            } else {
                                NumberFormat numberFormat = NumberFormat.getInstance();
                                numberFormat.setGroupingUsed(false);
                                numberFormat.setMaximumFractionDigits(16);
                                strValueOf = numberFormat.format(obj);
                            }
                            str3 = strValueOf;
                            Intrinsics.checkNotNullExpressionValue(str3, "{\n                      …                        }");
                        }
                    }
                    str = ((str + "---BEGIN:NUMBER---") + str3) + "---END:NUMBER---";
                } else if ((obj instanceof UInt) || (obj instanceof UByte) || (obj instanceof UShort) || (obj instanceof ULong)) {
                    str = ((str + "---BEGIN:NUMBER---") + obj) + "---END:NUMBER---";
                } else if (z2) {
                    str = str + obj;
                } else {
                    if (obj instanceof Class) {
                        string = "---BEGIN:CLASS---" + wrapClassLogStr(obj.getClass()) + "---END:CLASS---";
                    } else {
                        java.lang.Object basicField = getBasicField(obj);
                        if (basicField != null) {
                            string = basicField.toString();
                        } else {
                            string = getObjectJSON(obj).toString();
                            Intrinsics.checkNotNullExpressionValue(string, "getObjectJSON(perItem).toString()");
                        }
                    }
                    str = str + string;
                }
            }
            z = true;
        }
        return str;
    }

    public final String getLogV2(java.lang.Object... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (BaseInfo.SyncDebug) {
            if (data.length == 0) {
                return "---BEGIN:CONSOLE------END:CONSOLE---";
            }
            if (data.length == 1) {
                String string = ClassLogWrapper.wrapClass$default(ClassLogWrapper.INSTANCE, data[0], null, new HashSet(), null, 8, null).toString();
                Intrinsics.checkNotNullExpressionValue(string, "ClassLogWrapper.wrapClas…ull,HashSet()).toString()");
                return "---BEGIN:CONSOLE---" + string + "---END:CONSOLE---";
            }
            JsonArray jsonArray = new JsonArray();
            for (java.lang.Object obj : data) {
                jsonArray.add(ClassLogWrapper.wrapClass$default(ClassLogWrapper.INSTANCE, obj, null, new HashSet(), null, 8, null));
            }
            String string2 = jsonArray.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "jsonLogArray.toString()");
            return "---BEGIN:CONSOLE---" + string2 + "---END:CONSOLE---";
        }
        return "";
    }
}
