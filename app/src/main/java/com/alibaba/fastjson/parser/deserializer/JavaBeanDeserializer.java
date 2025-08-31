package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    private final ParserConfig.AutoTypeCheckHandler autoTypeCheckHandler;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private Map<String, FieldDeserializer> fieldDeserializerMap;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    protected final FieldDeserializer[] sortedFieldDeserializers;

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, JavaBeanInfo.build(cls, type, parserConfig.propertyNamingStrategy, parserConfig.fieldBased, parserConfig.compatibleWithJavaBean, parserConfig.isJacksonCompatible()));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) throws IllegalAccessException, InstantiationException {
        ParserConfig.AutoTypeCheckHandler autoTypeCheckHandlerNewInstance;
        this.clazz = javaBeanInfo.clazz;
        this.beanInfo = javaBeanInfo;
        HashMap map = null;
        if (javaBeanInfo.jsonType == null || javaBeanInfo.jsonType.autoTypeCheckHandler() == ParserConfig.AutoTypeCheckHandler.class) {
            autoTypeCheckHandlerNewInstance = null;
        } else {
            try {
                autoTypeCheckHandlerNewInstance = javaBeanInfo.jsonType.autoTypeCheckHandler().newInstance();
            } catch (Exception unused) {
            }
        }
        this.autoTypeCheckHandler = autoTypeCheckHandlerNewInstance;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer fieldDeserializerCreateFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
            this.sortedFieldDeserializers[i] = fieldDeserializerCreateFieldDeserializer;
            if (length > 128) {
                if (this.fieldDeserializerMap == null) {
                    this.fieldDeserializerMap = new HashMap();
                }
                this.fieldDeserializerMap.put(fieldInfo.name, fieldDeserializerCreateFieldDeserializer);
            }
            for (String str : fieldInfo.alternateNames) {
                if (map == null) {
                    map = new HashMap();
                }
                map.put(str, fieldDeserializerCreateFieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = map;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return getFieldDeserializer(str, null);
    }

    public FieldDeserializer getFieldDeserializer(String str, int[] iArr) {
        FieldDeserializer fieldDeserializer;
        if (str == null) {
            return null;
        }
        Map<String, FieldDeserializer> map = this.fieldDeserializerMap;
        if (map != null && (fieldDeserializer = map.get(str)) != null) {
            return fieldDeserializer;
        }
        int length = this.sortedFieldDeserializers.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    if (isSetFlag(i2, iArr)) {
                        return null;
                    }
                    return this.sortedFieldDeserializers[i2];
                }
                length = i2 - 1;
            }
        }
        Map<String, FieldDeserializer> map2 = this.alterNameFieldDeserializers;
        if (map2 != null) {
            return map2.get(str);
        }
        return null;
    }

    public FieldDeserializer getFieldDeserializer(long j) {
        int i = 0;
        if (this.hashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            int i2 = 0;
            while (true) {
                FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                if (i2 >= fieldDeserializerArr.length) {
                    break;
                }
                jArr[i2] = TypeUtils.fnv1a_64(fieldDeserializerArr[i2].fieldInfo.name);
                i2++;
            }
            Arrays.sort(jArr);
            this.hashArray = jArr;
        }
        int iBinarySearch = Arrays.binarySearch(this.hashArray, j);
        if (iBinarySearch < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            while (true) {
                FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                if (i >= fieldDeserializerArr2.length) {
                    break;
                }
                int iBinarySearch2 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(fieldDeserializerArr2[i].fieldInfo.name));
                if (iBinarySearch2 >= 0) {
                    sArr[iBinarySearch2] = (short) i;
                }
                i++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[iBinarySearch];
        if (s != -1) {
            return this.sortedFieldDeserializers[s];
        }
        return null;
    }

    static boolean isSetFlag(int i, int[] iArr) {
        int i2;
        if (iArr != null && (i2 = i / 32) < iArr.length) {
            return ((1 << (i % 32)) & iArr[i2]) != 0;
        }
        return false;
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
        Object obj = null;
        if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        }
        if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
            return null;
        }
        try {
            Constructor<?> constructor = this.beanInfo.defaultConstructor;
            if (this.beanInfo.defaultConstructorParameterSize != 0) {
                ParseContext context = defaultJSONParser.getContext();
                if (context == null || context.object == null) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                if (type instanceof Class) {
                    String name = ((Class) type).getName();
                    String strSubstring = name.substring(0, name.lastIndexOf(36));
                    Object obj2 = context.object;
                    String name2 = obj2.getClass().getName();
                    if (!name2.equals(strSubstring)) {
                        ParseContext parseContext = context.parent;
                        if (parseContext == null || parseContext.object == null || !("java.util.ArrayList".equals(name2) || "java.util.List".equals(name2) || "java.util.Collection".equals(name2) || "java.util.Map".equals(name2) || "java.util.HashMap".equals(name2))) {
                            obj = obj2;
                        } else if (parseContext.object.getClass().getName().equals(strSubstring)) {
                            obj = parseContext.object;
                        }
                        obj2 = obj;
                    }
                    if (obj2 == null || ((obj2 instanceof Collection) && ((Collection) obj2).isEmpty())) {
                        throw new JSONException("can't create non-static inner class instance.");
                    }
                    objNewInstance = constructor.newInstance(obj2);
                } else {
                    throw new JSONException("can't create non-static inner class instance.");
                }
            } else if (constructor != null) {
                objNewInstance = constructor.newInstance(null);
            } else {
                objNewInstance = this.beanInfo.factoryMethod.invoke(null, null);
            }
            if (defaultJSONParser != null && defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                for (FieldInfo fieldInfo : this.beanInfo.fields) {
                    if (fieldInfo.fieldClass == String.class) {
                        try {
                            fieldInfo.set(objNewInstance, "");
                        } catch (Exception e) {
                            throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                        }
                    }
                }
            }
            return objNewInstance;
        } catch (JSONException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e3);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, 0);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, int i) {
        return (T) deserialze(defaultJSONParser, type, obj, null, i, null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum<?> enumScanEnum;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() != 14) {
            throw new JSONException("error");
        }
        String strScanTypeName = jSONLexer.scanTypeName(defaultJSONParser.symbolTable);
        if (strScanTypeName != null) {
            ObjectDeserializer seeAlso = getSeeAlso(defaultJSONParser.getConfig(), this.beanInfo, strScanTypeName);
            if (seeAlso == null) {
                seeAlso = defaultJSONParser.getConfig().getDeserializer(defaultJSONParser.getConfig().checkAutoType(strScanTypeName, TypeUtils.getClass(type), jSONLexer.getFeatures()));
            }
            if (seeAlso instanceof JavaBeanDeserializer) {
                return (T) ((JavaBeanDeserializer) seeAlso).deserialzeArrayMapping(defaultJSONParser, type, obj, obj2);
            }
        }
        T t = (T) createInstance(defaultJSONParser, type);
        int length = this.sortedFieldDeserializers.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            char c = i == length + (-1) ? ']' : Operators.ARRAY_SEPRATOR;
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
            if (cls == Integer.TYPE) {
                fieldDeserializer.setValue((Object) t, jSONLexer.scanInt(c));
            } else if (cls == String.class) {
                fieldDeserializer.setValue((Object) t, jSONLexer.scanString(c));
            } else if (cls == Long.TYPE) {
                fieldDeserializer.setValue(t, jSONLexer.scanLong(c));
            } else if (cls.isEnum()) {
                char current = jSONLexer.getCurrent();
                if (current == '\"' || current == 'n') {
                    enumScanEnum = jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c);
                } else if (current >= '0' && current <= '9') {
                    enumScanEnum = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.getConfig())).valueOf(jSONLexer.scanInt(c));
                } else {
                    enumScanEnum = scanEnum(jSONLexer, c);
                }
                fieldDeserializer.setValue(t, enumScanEnum);
            } else if (cls == Boolean.TYPE) {
                fieldDeserializer.setValue(t, jSONLexer.scanBoolean(c));
            } else if (cls == Float.TYPE) {
                fieldDeserializer.setValue(t, Float.valueOf(jSONLexer.scanFloat(c)));
            } else if (cls == Double.TYPE) {
                fieldDeserializer.setValue(t, Double.valueOf(jSONLexer.scanDouble(c)));
            } else if (cls == Date.class && jSONLexer.getCurrent() == '1') {
                fieldDeserializer.setValue(t, new Date(jSONLexer.scanLong(c)));
            } else if (cls == BigDecimal.class) {
                fieldDeserializer.setValue(t, jSONLexer.scanDecimal(c));
            } else {
                jSONLexer.nextToken(14);
                fieldDeserializer.setValue(t, defaultJSONParser.parseObject(fieldDeserializer.fieldInfo.fieldType, fieldDeserializer.fieldInfo.name));
                if (jSONLexer.token() == 15) {
                    break;
                }
                check(jSONLexer, c == ']' ? 15 : 16);
            }
            i++;
        }
        jSONLexer.nextToken(16);
        return t;
    }

    protected void check(JSONLexer jSONLexer, int i) {
        if (jSONLexer.token() != i) {
            throw new JSONException("syntax error");
        }
    }

    protected Enum<?> scanEnum(JSONLexer jSONLexer, char c) {
        throw new JSONException("illegal enum. " + jSONLexer.info());
    }

    /* JADX WARN: Code restructure failed: missing block: B:295:0x037e, code lost:
    
        if (r12.matchStat == (-2)) goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x03c4, code lost:
    
        r1 = r27;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x0535, code lost:
    
        if (r3 != null) goto L401;
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x0537, code lost:
    
        r15 = r13.checkAutoType(r1, r2, r12.getFeatures());
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x0541, code lost:
    
        r15 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x0542, code lost:
    
        r2 = r34.getConfig().getDeserializer(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x054c, code lost:
    
        r3 = (T) r2.deserialze(r34, r15, r36);
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x0552, code lost:
    
        if ((r2 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L412;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x0554, code lost:
    
        r2 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:408:0x0556, code lost:
    
        if (r5 == null) goto L412;
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x0558, code lost:
    
        r2 = r2.getFieldDeserializer(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:410:0x055c, code lost:
    
        if (r2 == null) goto L412;
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x055e, code lost:
    
        r2.setValue((java.lang.Object) r3, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:412:0x0561, code lost:
    
        if (r6 == null) goto L414;
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:0x0563, code lost:
    
        r6.object = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x0567, code lost:
    
        r34.setContext(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x056a, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:420:0x0578, code lost:
    
        r31 = r7;
        r2 = r17;
        r37 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:492:0x06df, code lost:
    
        r12.nextToken();
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:505:0x0712, code lost:
    
        r12.nextToken(16);
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:625:0x0899, code lost:
    
        if (r33.beanInfo.fields[r1].fieldClass != java.lang.String.class) goto L643;
     */
    /* JADX WARN: Code restructure failed: missing block: B:627:0x089f, code lost:
    
        if (r33.beanInfo.kotlinDefaultConstructor == null) goto L643;
     */
    /* JADX WARN: Code restructure failed: missing block: B:628:0x08a1, code lost:
    
        r1 = (T) r33.beanInfo.kotlinDefaultConstructor.newInstance(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:629:0x08aa, code lost:
    
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:631:0x08ac, code lost:
    
        if (r5 >= r7.length) goto L774;
     */
    /* JADX WARN: Code restructure failed: missing block: B:632:0x08ae, code lost:
    
        r10 = r7[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:633:0x08b0, code lost:
    
        if (r10 == null) goto L776;
     */
    /* JADX WARN: Code restructure failed: missing block: B:635:0x08b6, code lost:
    
        if (r33.beanInfo.fields == null) goto L777;
     */
    /* JADX WARN: Code restructure failed: missing block: B:637:0x08bd, code lost:
    
        if (r5 >= r33.beanInfo.fields.length) goto L778;
     */
    /* JADX WARN: Code restructure failed: missing block: B:638:0x08bf, code lost:
    
        r33.beanInfo.fields[r5].set(r1, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:639:0x08c8, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:640:0x08cb, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:651:0x0902, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:654:0x092c, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error, " + r4 + ", " + r33.beanInfo.creatorConstructor.toGenericString(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:696:0x09e0, code lost:
    
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r12.token()));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0300 A[Catch: all -> 0x03da, TryCatch #18 {all -> 0x03da, blocks: (B:308:0x03b5, B:310:0x03bf, B:314:0x03cc, B:324:0x03ed, B:326:0x03f6, B:328:0x0402, B:330:0x0408, B:332:0x0410, B:334:0x0416, B:335:0x0419, B:336:0x0425, B:339:0x042e, B:341:0x0432, B:342:0x0435, B:344:0x0439, B:345:0x043c, B:346:0x0448, B:348:0x0450, B:349:0x0456, B:351:0x045c, B:353:0x0462, B:354:0x0468, B:355:0x046e, B:356:0x0472, B:359:0x047a, B:370:0x04aa, B:371:0x04c4, B:373:0x04c7, B:386:0x0500, B:388:0x0508, B:391:0x0514, B:393:0x051c, B:395:0x0524, B:397:0x052c, B:400:0x0537, B:402:0x0542, B:407:0x0554, B:409:0x0558, B:411:0x055e, B:134:0x01da, B:141:0x01e8, B:146:0x01f7, B:153:0x0206, B:155:0x020a, B:158:0x0213, B:163:0x021d, B:166:0x0226, B:171:0x0230, B:174:0x0239, B:177:0x023f, B:182:0x0249, B:187:0x0253, B:192:0x025d, B:194:0x0263, B:197:0x0271, B:199:0x0279, B:201:0x027d, B:204:0x028d, B:211:0x029e, B:214:0x02a8, B:219:0x02b3, B:222:0x02bc, B:227:0x02c7, B:230:0x02d0, B:233:0x02d7, B:237:0x02e3, B:239:0x02eb, B:244:0x02fa, B:247:0x0300, B:243:0x02f6, B:250:0x0307, B:252:0x0311, B:256:0x031c, B:259:0x0321, B:255:0x0318, B:262:0x0327, B:266:0x0338, B:269:0x033d, B:265:0x0334, B:272:0x0343, B:274:0x034d, B:278:0x0358, B:281:0x035d, B:277:0x0354, B:284:0x0363, B:286:0x036b, B:290:0x0376, B:293:0x037b, B:289:0x0372), top: B:746:0x03b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:428:0x05ac  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x05b7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:439:0x05e5  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x065d A[PHI: r3
  0x065d: PHI (r3v63 java.lang.String) = (r3v135 java.lang.String), (r3v136 java.lang.String) binds: [B:462:0x0637, B:468:0x0653] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:479:0x0696  */
    /* JADX WARN: Removed duplicated region for block: B:502:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x070c A[Catch: all -> 0x09ed, TryCatch #11 {all -> 0x09ed, blocks: (B:500:0x06ff, B:503:0x070c, B:505:0x0712, B:488:0x06d3, B:498:0x06f7), top: B:734:0x06ff }] */
    /* JADX WARN: Removed duplicated region for block: B:645:0x08da A[Catch: all -> 0x0998, TRY_ENTER, TryCatch #16 {all -> 0x0998, blocks: (B:512:0x0727, B:525:0x0745, B:526:0x074a, B:528:0x074d, B:530:0x0755, B:532:0x0765, B:572:0x07ec, B:533:0x076a, B:535:0x076e, B:536:0x0773, B:538:0x0777, B:539:0x077c, B:541:0x0780, B:542:0x0785, B:544:0x0789, B:545:0x078e, B:547:0x0792, B:548:0x0797, B:550:0x079b, B:553:0x07a2, B:557:0x07af, B:559:0x07b5, B:561:0x07bc, B:563:0x07c6, B:565:0x07ce, B:567:0x07d2, B:569:0x07dc, B:571:0x07e8, B:630:0x08ab, B:632:0x08ae, B:634:0x08b2, B:636:0x08b8, B:638:0x08bf, B:645:0x08da, B:646:0x08e2, B:648:0x08e8, B:650:0x08fa, B:665:0x0962, B:671:0x0973, B:678:0x0982, B:685:0x0990, B:686:0x0997, B:653:0x0905, B:654:0x092c), top: B:743:0x071b, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0a02  */
    /* JADX WARN: Removed duplicated region for block: B:724:0x03ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r34v0, types: [com.alibaba.fastjson.parser.DefaultJSONParser] */
    /* JADX WARN: Type inference failed for: r3v118 */
    /* JADX WARN: Type inference failed for: r3v119 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43, types: [com.alibaba.fastjson.parser.ParseContext] */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v48 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v62, types: [java.lang.Class<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r3v67, types: [int] */
    /* JADX WARN: Type inference failed for: r3v69, types: [int] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v70, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v71, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v72, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v73, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v74, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r3v86 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r34, java.lang.reflect.Type r35, java.lang.Object r36, java.lang.Object r37, int r38, int[] r39) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 2573
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object, int, int[]):java.lang.Object");
    }

    protected Enum scanEnum(JSONLexerBase jSONLexerBase, char[] cArr, ObjectDeserializer objectDeserializer) {
        EnumDeserializer enumDeserializer = objectDeserializer instanceof EnumDeserializer ? (EnumDeserializer) objectDeserializer : null;
        if (enumDeserializer == null) {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        long jScanEnumSymbol = jSONLexerBase.scanEnumSymbol(cArr);
        if (jSONLexerBase.matchStat <= 0) {
            return null;
        }
        Enum enumByHashCode = enumDeserializer.getEnumByHashCode(jScanEnumSymbol);
        if (enumByHashCode == null) {
            if (jScanEnumSymbol == TypeUtils.fnv1a_64_magic_hashcode) {
                return null;
            }
            if (jSONLexerBase.isEnabled(Feature.ErrorOnEnumNotMatch)) {
                throw new JSONException("not match enum value, " + enumDeserializer.enumClass);
            }
        }
        return enumByHashCode;
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        return parseField(defaultJSONParser, str, obj, type, map, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012a  */
    /* JADX WARN: Type inference failed for: r19v13 */
    /* JADX WARN: Type inference failed for: r19v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r19v5 */
    /* JADX WARN: Type inference failed for: r19v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean parseField(com.alibaba.fastjson.parser.DefaultJSONParser r22, java.lang.String r23, java.lang.Object r24, java.lang.reflect.Type r25, java.util.Map<java.lang.String, java.lang.Object> r26, int[] r27) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 598
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseField(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.String, java.lang.Object, java.lang.reflect.Type, java.util.Map, int[]):boolean");
    }

    public FieldDeserializer smartMatch(String str) {
        return smartMatch(str, null);
    }

    public FieldDeserializer smartMatch(String str, int[] iArr) {
        boolean zStartsWith;
        if (str == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str, iArr);
        if (fieldDeserializer == null) {
            int i = 0;
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                int i2 = 0;
                while (true) {
                    FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                    if (i2 >= fieldDeserializerArr.length) {
                        break;
                    }
                    jArr[i2] = fieldDeserializerArr[i2].fieldInfo.nameHashCode;
                    i2++;
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(str));
            if (iBinarySearch < 0) {
                iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_extract(str));
            }
            if (iBinarySearch < 0) {
                zStartsWith = str.startsWith("is");
                if (zStartsWith) {
                    iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_extract(str.substring(2)));
                }
            } else {
                zStartsWith = false;
            }
            if (iBinarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    short[] sArr = new short[this.smartMatchHashArray.length];
                    Arrays.fill(sArr, (short) -1);
                    while (true) {
                        FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                        if (i >= fieldDeserializerArr2.length) {
                            break;
                        }
                        int iBinarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, fieldDeserializerArr2[i].fieldInfo.nameHashCode);
                        if (iBinarySearch2 >= 0) {
                            sArr[iBinarySearch2] = (short) i;
                        }
                        i++;
                    }
                    this.smartMatchHashArrayMapping = sArr;
                }
                short s = this.smartMatchHashArrayMapping[iBinarySearch];
                if (s != -1 && !isSetFlag(s, iArr)) {
                    fieldDeserializer = this.sortedFieldDeserializers[s];
                }
            }
            if (fieldDeserializer != null) {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                if ((fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
                    return null;
                }
                Class<?> cls = fieldInfo.fieldClass;
                if (zStartsWith && cls != Boolean.TYPE && cls != Boolean.class) {
                    return null;
                }
            }
        }
        return fieldDeserializer;
    }

    private Object createFactoryInstance(ParserConfig parserConfig, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return this.beanInfo.factoryMethod.invoke(null, obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object createInstance(java.util.Map<java.lang.String, java.lang.Object> r13, com.alibaba.fastjson.parser.ParserConfig r14) throws java.lang.IllegalAccessException, java.lang.InstantiationException, java.lang.ArrayIndexOutOfBoundsException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 798
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.createInstance(java.util.Map, com.alibaba.fastjson.parser.ParserConfig):java.lang.Object");
    }

    public Type getFieldType(int i) {
        return this.sortedFieldDeserializers[i].fieldInfo.fieldType;
    }

    protected Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i) {
        return parseRest(defaultJSONParser, type, obj, obj2, i, new int[0]);
    }

    protected Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i, int[] iArr) {
        return deserialze(defaultJSONParser, type, obj, obj2, i, iArr);
    }

    protected static JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class<?> cls : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
            if (deserializer instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    protected static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        int i = jSONLexerBase.token();
        if (i == 8) {
            jSONLexerBase.nextToken(16);
            jSONLexerBase.token();
            return;
        }
        if (i != 14) {
            defaultJSONParser.throwException(i);
        }
        if (jSONLexerBase.getCurrent() == '[') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(14);
        } else {
            jSONLexerBase.nextToken(14);
        }
        if (jSONLexerBase.token() == 15) {
            jSONLexerBase.nextToken();
            return;
        }
        int i2 = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i2)));
            i2++;
            if (jSONLexerBase.token() != 16) {
                break;
            }
            if (jSONLexerBase.getCurrent() == '[') {
                jSONLexerBase.next();
                jSONLexerBase.setToken(14);
            } else {
                jSONLexerBase.nextToken(14);
            }
        }
        int i3 = jSONLexerBase.token();
        if (i3 != 15) {
            defaultJSONParser.throwException(i3);
        }
        if (jSONLexerBase.getCurrent() == ',') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(16);
        } else {
            jSONLexerBase.nextToken(16);
        }
    }
}
