package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected final SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap map = new HashMap();
        for (String str : strArr) {
            map.put(str, str);
        }
        return map;
    }

    public JSONType getJSONType() {
        return this.beanInfo.jsonType;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        int i = 0;
        while (true) {
            FieldSerializer[] fieldSerializerArr = this.sortedGetters;
            if (i >= fieldSerializerArr.length) {
                break;
            }
            fieldSerializerArr[i] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i]);
            i++;
        }
        if (serializeBeanInfo.fields == serializeBeanInfo.sortedFields) {
            this.getters = this.sortedGetters;
        } else {
            this.getters = new FieldSerializer[serializeBeanInfo.fields.length];
            int i2 = 0;
            while (true) {
                if (i2 >= this.getters.length) {
                    break;
                }
                FieldSerializer fieldSerializer = getFieldSerializer(serializeBeanInfo.fields[i2].name);
                if (fieldSerializer != null) {
                    this.getters[i2] = fieldSerializer;
                    i2++;
                } else {
                    FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                    System.arraycopy(fieldSerializerArr2, 0, this.getters, 0, fieldSerializerArr2.length);
                    break;
                }
            }
        }
        if (serializeBeanInfo.jsonType != null) {
            for (Class<? extends SerializeFilter> cls : serializeBeanInfo.jsonType.serialzeFilters()) {
                try {
                    addFilter(cls.getConstructor(null).newInstance(null));
                } catch (Exception unused) {
                }
            }
        }
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(4:(7:427|61|62|437|63|(1:67)(1:66)|(4:449|69|(2:101|456)(1:(4:77|(3:(1:84)|101|456)(1:82)|85|(1:(3:91|99|(0)(21:102|(1:107)|108|109|110|450|111|112|(6:114|(1:116)|(2:121|(2:123|(2:130|(1:132)(2:133|(1:137)))(3:129|260|457))(2:138|(2:140|(2:147|(1:149))(3:146|260|457))(2:155|(2:157|(2:164|(1:166)(2:167|(1:171)))(3:163|260|457))(2:172|(2:174|(2:181|(1:183)(2:184|(1:188)))(3:180|260|457))(3:(0)(1:196)|260|457)))))(1:120)|(5:(2:254|(2:261|(1:263)))|(4:(1:266)(1:268)|269|426|270)(2:274|(2:(1:277)|278)(1:(6:280|(1:287)(1:286)|(1:(1:294)(1:295))(1:296)|(2:298|(2:320|(1:327)(2:326|458))(1:(4:304|(1:306)|307|(1:315)(1:314))(2:316|(1:318)(1:319))))(1:328)|329|(2:343|461)(2:333|(2:336|(3:338|(3:341|(3:462|343|461)(1:463)|339)|460)(0))(2:335|459)))(0)))|271|329|(3:331|343|461)(0))(1:252)|260|457)(1:(8:198|200|202|(0)|(0)(0)|271|329|(0)(0))(0))|366|398|(2:439|400)|401|(1:403)(1:(2:407|(1:409)(1:410)))|411|(1:413)|414|(1:416)|(1:418)(1:419)|420|421))(4:433|92|99|(0)(0)))(0))(0))|355)(0))|394|441|59) */
    /* JADX WARN: Code restructure failed: missing block: B:388:0x0519, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:389:0x051a, code lost:
    
        r25 = r1;
        r2 = r7;
        r4 = r9;
        r1 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x014f A[PHI: r25
  0x014f: PHI (r25v10 com.alibaba.fastjson.serializer.FieldSerializer) = 
  (r25v8 com.alibaba.fastjson.serializer.FieldSerializer)
  (r25v8 com.alibaba.fastjson.serializer.FieldSerializer)
  (r25v12 com.alibaba.fastjson.serializer.FieldSerializer)
  (r25v8 com.alibaba.fastjson.serializer.FieldSerializer)
  (r25v8 com.alibaba.fastjson.serializer.FieldSerializer)
 binds: [B:70:0x00fe, B:83:0x011e, B:100:0x014d, B:88:0x012f, B:75:0x010a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x035d A[Catch: all -> 0x0493, Exception -> 0x0497, TryCatch #20 {Exception -> 0x0497, all -> 0x0493, blocks: (B:111:0x0193, B:114:0x019b, B:116:0x01a7, B:118:0x01b6, B:120:0x01c0, B:123:0x01ca, B:125:0x01d5, B:127:0x01d9, B:130:0x01e0, B:132:0x01e4, B:133:0x01ea, B:135:0x01ef, B:137:0x01f6, B:140:0x0200, B:142:0x020b, B:144:0x020f, B:147:0x0216, B:150:0x021d, B:152:0x0222, B:155:0x022a, B:157:0x0232, B:159:0x023d, B:161:0x0241, B:164:0x0248, B:166:0x024c, B:167:0x0251, B:169:0x0256, B:171:0x025d, B:172:0x0262, B:174:0x026a, B:176:0x0275, B:178:0x0279, B:181:0x0280, B:183:0x0284, B:184:0x0289, B:186:0x028e, B:188:0x0295, B:190:0x029c, B:192:0x02a0, B:194:0x02aa, B:198:0x02b5, B:200:0x02b9, B:202:0x02c2, B:204:0x02cd, B:206:0x02d3, B:208:0x02d7, B:211:0x02e2, B:213:0x02e6, B:215:0x02ea, B:218:0x02f5, B:220:0x02f9, B:222:0x02fd, B:225:0x0308, B:227:0x030c, B:229:0x0310, B:232:0x031e, B:234:0x0322, B:236:0x0326, B:239:0x0333, B:241:0x0337, B:243:0x033b, B:246:0x0349, B:248:0x034d, B:250:0x0351, B:254:0x035d, B:256:0x0361, B:258:0x0365, B:261:0x0376, B:263:0x0381, B:266:0x0388), top: B:450:0x0193 }] */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0467 A[Catch: Exception -> 0x0398, all -> 0x0501, TryCatch #0 {all -> 0x0501, blocks: (B:270:0x0391, B:329:0x0463, B:331:0x0467, B:333:0x046b, B:336:0x0474, B:338:0x047c, B:339:0x0484, B:341:0x048a, B:277:0x03a5, B:278:0x03a8, B:280:0x03ae, B:282:0x03ba, B:289:0x03d0, B:294:0x03da, B:298:0x03ef, B:301:0x03f9, B:304:0x0403, B:306:0x040b, B:307:0x0418, B:309:0x0421, B:311:0x0428, B:312:0x042c, B:314:0x0433, B:315:0x0437, B:316:0x043b, B:318:0x0440, B:319:0x0444, B:320:0x0448, B:322:0x044c, B:324:0x0450, B:327:0x045c, B:328:0x0460, B:295:0x03e5, B:349:0x049f, B:373:0x04f2, B:375:0x04fa, B:381:0x0507), top: B:426:0x0391 }] */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0556 A[Catch: all -> 0x05d7, TRY_ENTER, TryCatch #13 {all -> 0x05d7, blocks: (B:400:0x0534, B:403:0x0556, B:411:0x05a6, B:413:0x05ac, B:414:0x05c4, B:416:0x05c8, B:420:0x05d1, B:421:0x05d6, B:405:0x056b, B:407:0x056f, B:409:0x0575, B:410:0x0590), top: B:439:0x0534 }] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x05ac A[Catch: all -> 0x05d7, TryCatch #13 {all -> 0x05d7, blocks: (B:400:0x0534, B:403:0x0556, B:411:0x05a6, B:413:0x05ac, B:414:0x05c4, B:416:0x05c8, B:420:0x05d1, B:421:0x05d6, B:405:0x056b, B:407:0x056f, B:409:0x0575, B:410:0x0590), top: B:439:0x0534 }] */
    /* JADX WARN: Removed duplicated region for block: B:416:0x05c8 A[Catch: all -> 0x05d7, TryCatch #13 {all -> 0x05d7, blocks: (B:400:0x0534, B:403:0x0556, B:411:0x05a6, B:413:0x05ac, B:414:0x05c4, B:416:0x05c8, B:420:0x05d1, B:421:0x05d6, B:405:0x056b, B:407:0x056f, B:409:0x0575, B:410:0x0590), top: B:439:0x0534 }] */
    /* JADX WARN: Removed duplicated region for block: B:418:0x05ce  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x05cf  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0534 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void write(com.alibaba.fastjson.serializer.JSONSerializer r33, java.lang.Object r34, java.lang.Object r35, java.lang.reflect.Type r36, int r37, boolean r38) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1501
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }

    protected void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String name = this.beanInfo.typeName;
        if (name == null) {
            Class<?> superclass = obj.getClass();
            if (TypeUtils.isProxy(superclass)) {
                superclass = superclass.getSuperclass();
            }
            name = superclass.getName();
        }
        jSONSerializer.write(name);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer == null) {
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer == null) {
            if (!z) {
                return null;
            }
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int length = this.sortedGetters.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    return this.sortedGetters[i2];
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArrValues;
        int iBinarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            long[] jArr = new long[this.sortedGetters.length * propertyNamingStrategyArrValues.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr = this.sortedGetters;
                if (i >= fieldSerializerArr.length) {
                    break;
                }
                String str = fieldSerializerArr[i].fieldInfo.name;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                i2++;
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArrValues) {
                    String strTranslate = propertyNamingStrategy.translate(str);
                    if (!str.equals(strTranslate)) {
                        jArr[i2] = TypeUtils.fnv1a_64(strTranslate);
                        i2++;
                    }
                }
                i++;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArrValues = null;
        }
        int iBinarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (iBinarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArrValues == null) {
                propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            int i3 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                if (i3 >= fieldSerializerArr2.length) {
                    break;
                }
                String str2 = fieldSerializerArr2[i3].fieldInfo.name;
                int iBinarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (iBinarySearch3 >= 0) {
                    sArr[iBinarySearch3] = (short) i3;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArrValues) {
                    String strTranslate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(strTranslate2) && (iBinarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(strTranslate2))) >= 0) {
                        sArr[iBinarySearch] = (short) i3;
                    }
                }
                i3++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[iBinarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            arrayList.add(fieldSerializer.getPropertyValue(obj));
        }
        return arrayList;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Set<String> getFieldNames(Object obj) throws Exception {
        HashSet hashSet = new HashSet();
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                hashSet.add(fieldSerializer.fieldInfo.name);
            }
        }
        return hashSet;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            boolean zIsEnabled = SerializerFeature.isEnabled(fieldSerializer.features, SerializerFeature.SkipTransientField);
            FieldInfo fieldInfo = fieldSerializer.fieldInfo;
            if (!zIsEnabled || fieldInfo == null || !fieldInfo.fieldTransient) {
                if (fieldSerializer.fieldInfo.unwrapped) {
                    Object json = JSON.toJSON(fieldSerializer.getPropertyValue(obj));
                    if (json instanceof Map) {
                        linkedHashMap.putAll((Map) json);
                    } else {
                        linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                    }
                } else {
                    linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                }
            }
        }
        return linkedHashMap;
    }

    protected BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    protected Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    protected char writeBefore(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.beforeFilters != null) {
            Iterator<BeforeFilter> it = jSONSerializer.beforeFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        if (this.beforeFilters != null) {
            Iterator<BeforeFilter> it2 = this.beforeFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.afterFilters != null) {
            Iterator<AfterFilter> it = jSONSerializer.afterFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        if (this.afterFilters != null) {
            Iterator<AfterFilter> it2 = this.afterFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        if (jSONSerializer.labelFilters != null) {
            Iterator<LabelFilter> it = jSONSerializer.labelFilters.iterator();
            while (it.hasNext()) {
                if (!it.next().apply(str)) {
                    return false;
                }
            }
        }
        if (this.labelFilters == null) {
            return true;
        }
        Iterator<LabelFilter> it2 = this.labelFilters.iterator();
        while (it2.hasNext()) {
            if (!it2.next().apply(str)) {
                return false;
            }
        }
        return true;
    }
}
