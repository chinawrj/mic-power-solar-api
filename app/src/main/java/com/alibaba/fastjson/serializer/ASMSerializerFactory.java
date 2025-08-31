package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.FieldWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.MethodWriter;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.facebook.common.callercontext.ContextChain;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class ASMSerializerFactory implements Opcodes {
    static final String JSONSerializer = ASMUtils.type(JSONSerializer.class);
    static final String JavaBeanSerializer;
    static final String JavaBeanSerializer_desc;
    static final String ObjectSerializer;
    static final String ObjectSerializer_desc;
    static final String SerialContext_desc;
    static final String SerializeFilterable_desc;
    static final String SerializeWriter;
    static final String SerializeWriter_desc;
    protected final ASMClassLoader classLoader = new ASMClassLoader();
    private final AtomicLong seed = new AtomicLong();

    static {
        String strType = ASMUtils.type(ObjectSerializer.class);
        ObjectSerializer = strType;
        ObjectSerializer_desc = "L" + strType + ";";
        String strType2 = ASMUtils.type(SerializeWriter.class);
        SerializeWriter = strType2;
        SerializeWriter_desc = "L" + strType2 + ";";
        JavaBeanSerializer = ASMUtils.type(JavaBeanSerializer.class);
        JavaBeanSerializer_desc = "L" + ASMUtils.type(JavaBeanSerializer.class) + ";";
        SerialContext_desc = ASMUtils.desc((Class<?>) SerialContext.class);
        SerializeFilterable_desc = ASMUtils.desc((Class<?>) SerializeFilterable.class);
    }

    static class Context {
        static final int features = 5;
        static int fieldName = 6;
        static final int obj = 2;
        static int original = 7;
        static final int paramFieldName = 3;
        static final int paramFieldType = 4;
        static int processValue = 8;
        static final int serializer = 1;
        private final SerializeBeanInfo beanInfo;
        private final String className;
        private final FieldInfo[] getters;
        private final boolean nonContext;
        private final boolean writeDirect;
        private Map<String, Integer> variants = new HashMap();
        private int variantIndex = 9;

        public Context(FieldInfo[] fieldInfoArr, SerializeBeanInfo serializeBeanInfo, String str, boolean z, boolean z2) {
            this.getters = fieldInfoArr;
            this.className = str;
            this.beanInfo = serializeBeanInfo;
            this.writeDirect = z;
            this.nonContext = z2 || serializeBeanInfo.beanType.isEnum();
        }

        public int var(String str) {
            if (this.variants.get(str) == null) {
                Map<String, Integer> map = this.variants;
                int i = this.variantIndex;
                this.variantIndex = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            return this.variants.get(str).intValue();
        }

        public int var(String str, int i) {
            if (this.variants.get(str) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i;
            }
            return this.variants.get(str).intValue();
        }

        public int getFieldOrinal(String str) {
            int length = this.getters.length;
            for (int i = 0; i < length; i++) {
                if (this.getters[i].name.equals(str)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public JavaBeanSerializer createJavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) throws Exception {
        String str;
        String str2;
        boolean z;
        FieldInfo[] fieldInfoArr;
        String str3;
        boolean z2;
        boolean z3;
        String str4;
        boolean z4;
        boolean z5;
        JSONType jSONType;
        String str5;
        int i;
        boolean z6;
        int i2;
        char c;
        ClassWriter classWriter;
        char c2;
        int i3;
        Class<?> cls = serializeBeanInfo.beanType;
        if (cls.isPrimitive()) {
            throw new JSONException("unsupportd class " + cls.getName());
        }
        JSONType jSONType2 = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
        FieldInfo[] fieldInfoArr2 = serializeBeanInfo.fields;
        for (FieldInfo fieldInfo : fieldInfoArr2) {
            if (fieldInfo.field == null && fieldInfo.method != null && fieldInfo.method.getDeclaringClass().isInterface()) {
                return new JavaBeanSerializer(serializeBeanInfo);
            }
        }
        FieldInfo[] fieldInfoArr3 = serializeBeanInfo.sortedFields;
        boolean z7 = serializeBeanInfo.sortedFields == serializeBeanInfo.fields;
        if (fieldInfoArr3.length > 256) {
            return new JavaBeanSerializer(serializeBeanInfo);
        }
        for (FieldInfo fieldInfo2 : fieldInfoArr3) {
            if (!ASMUtils.checkName(fieldInfo2.getMember().getName())) {
                return new JavaBeanSerializer(serializeBeanInfo);
            }
        }
        String str6 = "ASMSerializer_" + this.seed.incrementAndGet() + "_" + cls.getSimpleName();
        Package r2 = ASMSerializerFactory.class.getPackage();
        if (r2 != null) {
            String name = r2.getName();
            String str7 = name.replace(Operators.DOT, '/') + "/" + str6;
            str = name + Operators.DOT_STR + str6;
            str2 = str7;
        } else {
            str = str6;
            str2 = str;
        }
        ClassWriter classWriter2 = new ClassWriter();
        classWriter2.visit(49, 33, str2, JavaBeanSerializer, new String[]{ObjectSerializer});
        int length = fieldInfoArr3.length;
        int i4 = 0;
        while (i4 < length) {
            FieldInfo fieldInfo3 = fieldInfoArr3[i4];
            if (fieldInfo3.fieldClass.isPrimitive() || fieldInfo3.fieldClass == String.class) {
                i3 = length;
            } else {
                i3 = length;
                new FieldWriter(classWriter2, 1, fieldInfo3.name + "_asm_fieldType", "Ljava/lang/reflect/Type;").visitEnd();
                if (List.class.isAssignableFrom(fieldInfo3.fieldClass)) {
                    new FieldWriter(classWriter2, 1, fieldInfo3.name + "_asm_list_item_ser_", ObjectSerializer_desc).visitEnd();
                }
                new FieldWriter(classWriter2, 1, fieldInfo3.name + "_asm_ser_", ObjectSerializer_desc).visitEnd();
            }
            i4++;
            length = i3;
        }
        MethodWriter methodWriter = new MethodWriter(classWriter2, 1, "<init>", Operators.BRACKET_START_STR + ASMUtils.desc((Class<?>) SerializeBeanInfo.class) + ")V", null, null);
        int i5 = 25;
        methodWriter.visitVarInsn(25, 0);
        methodWriter.visitVarInsn(25, 1);
        String str8 = JavaBeanSerializer;
        String str9 = Operators.BRACKET_START_STR + ASMUtils.desc((Class<?>) SerializeBeanInfo.class) + ")V";
        char c3 = Typography.middleDot;
        methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, str8, "<init>", str9);
        int i6 = 0;
        while (i6 < fieldInfoArr3.length) {
            FieldInfo fieldInfo4 = fieldInfoArr3[i6];
            if (fieldInfo4.fieldClass.isPrimitive()) {
                classWriter = classWriter2;
                c2 = c3;
            } else if (fieldInfo4.fieldClass == String.class) {
                classWriter = classWriter2;
                c2 = Typography.middleDot;
            } else {
                methodWriter.visitVarInsn(i5, 0);
                if (fieldInfo4.method != null) {
                    methodWriter.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo4.declaringClass)));
                    methodWriter.visitLdcInsn(fieldInfo4.method.getName());
                    classWriter = classWriter2;
                    methodWriter.visitMethodInsn(Opcodes.INVOKESTATIC, ASMUtils.type(ASMUtils.class), "getMethodType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
                    c2 = Typography.middleDot;
                } else {
                    classWriter = classWriter2;
                    methodWriter.visitVarInsn(i5, 0);
                    methodWriter.visitLdcInsn(Integer.valueOf(i6));
                    String str10 = JavaBeanSerializer;
                    c2 = Typography.middleDot;
                    methodWriter.visitMethodInsn(Opcodes.INVOKESPECIAL, str10, "getFieldType", "(I)Ljava/lang/reflect/Type;");
                }
                methodWriter.visitFieldInsn(Opcodes.PUTFIELD, str2, fieldInfo4.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            }
            i6++;
            c3 = c2;
            classWriter2 = classWriter;
            i5 = 25;
        }
        ClassWriter classWriter3 = classWriter2;
        methodWriter.visitInsn(Opcodes.RETURN);
        methodWriter.visitMaxs(4, 4);
        methodWriter.visitEnd();
        if (jSONType2 != null) {
            for (SerializerFeature serializerFeature : jSONType2.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.DisableCircularReferenceDetect) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        int i7 = 0;
        while (true) {
            fieldInfoArr = fieldInfoArr2;
            if (i7 >= 3) {
                break;
            }
            if (i7 == 0) {
                str4 = "write";
                z5 = z;
                z4 = true;
            } else if (i7 == 1) {
                str4 = "writeNormal";
                z5 = z;
                z4 = false;
            } else {
                str4 = "writeDirectNonContext";
                z4 = true;
                z5 = true;
            }
            ClassWriter classWriter4 = classWriter3;
            String str11 = str;
            String str12 = str2;
            Context context = new Context(fieldInfoArr3, serializeBeanInfo, str2, z4, z5);
            StringBuilder sb = new StringBuilder("(L");
            String str13 = JSONSerializer;
            sb.append(str13);
            sb.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            int i8 = i7;
            MethodWriter methodWriter2 = new MethodWriter(classWriter4, 1, str4, sb.toString(), null, new String[]{"java/io/IOException"});
            Label label = new Label();
            methodWriter2.visitVarInsn(25, 2);
            methodWriter2.visitJumpInsn(Opcodes.IFNONNULL, label);
            methodWriter2.visitVarInsn(25, 1);
            methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str13, "writeNull", "()V");
            methodWriter2.visitInsn(Opcodes.RETURN);
            methodWriter2.visitLabel(label);
            methodWriter2.visitVarInsn(25, 1);
            methodWriter2.visitFieldInsn(180, str13, "out", SerializeWriter_desc);
            methodWriter2.visitVarInsn(58, context.var("out"));
            if (z7 || context.writeDirect || !(jSONType2 == null || jSONType2.alphabetic())) {
                jSONType = jSONType2;
                str5 = str12;
            } else {
                Label label2 = new Label();
                methodWriter2.visitVarInsn(25, context.var("out"));
                jSONType = jSONType2;
                methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isSortField", "()Z");
                methodWriter2.visitJumpInsn(Opcodes.IFNE, label2);
                methodWriter2.visitVarInsn(25, 0);
                methodWriter2.visitVarInsn(25, 1);
                methodWriter2.visitVarInsn(25, 2);
                methodWriter2.visitVarInsn(25, 3);
                methodWriter2.visitVarInsn(25, 4);
                methodWriter2.visitVarInsn(21, 5);
                str5 = str12;
                methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "writeUnsorted", "(L" + str13 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodWriter2.visitInsn(Opcodes.RETURN);
                methodWriter2.visitLabel(label2);
            }
            if (!context.writeDirect || z5) {
                i = i8;
                z6 = z7;
                i2 = Opcodes.RETURN;
                c = 4;
            } else {
                Label label3 = new Label();
                Label label4 = new Label();
                methodWriter2.visitVarInsn(25, 0);
                methodWriter2.visitVarInsn(25, 1);
                String str14 = JavaBeanSerializer;
                StringBuilder sb2 = new StringBuilder("(L");
                sb2.append(str13);
                z6 = z7;
                sb2.append(";)Z");
                i = i8;
                methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str14, "writeDirect", sb2.toString());
                methodWriter2.visitJumpInsn(Opcodes.IFNE, label4);
                methodWriter2.visitVarInsn(25, 0);
                methodWriter2.visitVarInsn(25, 1);
                methodWriter2.visitVarInsn(25, 2);
                methodWriter2.visitVarInsn(25, 3);
                methodWriter2.visitVarInsn(25, 4);
                methodWriter2.visitVarInsn(21, 5);
                methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "writeNormal", "(L" + str13 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodWriter2.visitInsn(Opcodes.RETURN);
                methodWriter2.visitLabel(label4);
                methodWriter2.visitVarInsn(25, context.var("out"));
                methodWriter2.visitLdcInsn(Integer.valueOf(SerializerFeature.DisableCircularReferenceDetect.mask));
                methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
                methodWriter2.visitJumpInsn(Opcodes.IFEQ, label3);
                methodWriter2.visitVarInsn(25, 0);
                methodWriter2.visitVarInsn(25, 1);
                methodWriter2.visitVarInsn(25, 2);
                methodWriter2.visitVarInsn(25, 3);
                c = 4;
                methodWriter2.visitVarInsn(25, 4);
                methodWriter2.visitVarInsn(21, 5);
                methodWriter2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "writeDirectNonContext", "(L" + str13 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                i2 = Opcodes.RETURN;
                methodWriter2.visitInsn(Opcodes.RETURN);
                methodWriter2.visitLabel(label3);
            }
            methodWriter2.visitVarInsn(25, 2);
            methodWriter2.visitTypeInsn(192, ASMUtils.type(cls));
            methodWriter2.visitVarInsn(58, context.var("entity"));
            generateWriteMethod(cls, methodWriter2, fieldInfoArr3, context);
            methodWriter2.visitInsn(i2);
            methodWriter2.visitMaxs(7, context.variantIndex + 2);
            methodWriter2.visitEnd();
            i7 = i + 1;
            str2 = str5;
            jSONType2 = jSONType;
            z7 = z6;
            fieldInfoArr2 = fieldInfoArr;
            classWriter3 = classWriter4;
            str = str11;
        }
        String str15 = str;
        String str16 = str2;
        ClassWriter classWriter5 = classWriter3;
        if (!z7) {
            Context context2 = new Context(fieldInfoArr3, serializeBeanInfo, str16, false, z);
            StringBuilder sb3 = new StringBuilder("(L");
            String str17 = JSONSerializer;
            sb3.append(str17);
            sb3.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            MethodWriter methodWriter3 = new MethodWriter(classWriter5, 1, "writeUnsorted", sb3.toString(), null, new String[]{"java/io/IOException"});
            methodWriter3.visitVarInsn(25, 1);
            methodWriter3.visitFieldInsn(180, str17, "out", SerializeWriter_desc);
            methodWriter3.visitVarInsn(58, context2.var("out"));
            methodWriter3.visitVarInsn(25, 2);
            methodWriter3.visitTypeInsn(192, ASMUtils.type(cls));
            methodWriter3.visitVarInsn(58, context2.var("entity"));
            generateWriteMethod(cls, methodWriter3, fieldInfoArr, context2);
            methodWriter3.visitInsn(Opcodes.RETURN);
            methodWriter3.visitMaxs(7, context2.variantIndex + 2);
            methodWriter3.visitEnd();
        }
        for (int i9 = 0; i9 < 3; i9++) {
            if (i9 == 0) {
                str3 = "writeAsArray";
                z3 = z;
                z2 = true;
            } else if (i9 == 1) {
                str3 = "writeAsArrayNormal";
                z3 = z;
                z2 = false;
            } else {
                str3 = "writeAsArrayNonContext";
                z2 = true;
                z3 = true;
            }
            Context context3 = new Context(fieldInfoArr3, serializeBeanInfo, str16, z2, z3);
            StringBuilder sb4 = new StringBuilder("(L");
            String str18 = JSONSerializer;
            sb4.append(str18);
            sb4.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            MethodWriter methodWriter4 = new MethodWriter(classWriter5, 1, str3, sb4.toString(), null, new String[]{"java/io/IOException"});
            methodWriter4.visitVarInsn(25, 1);
            methodWriter4.visitFieldInsn(180, str18, "out", SerializeWriter_desc);
            methodWriter4.visitVarInsn(58, context3.var("out"));
            methodWriter4.visitVarInsn(25, 2);
            methodWriter4.visitTypeInsn(192, ASMUtils.type(cls));
            methodWriter4.visitVarInsn(58, context3.var("entity"));
            generateWriteAsArray(cls, methodWriter4, fieldInfoArr3, context3);
            methodWriter4.visitInsn(Opcodes.RETURN);
            methodWriter4.visitMaxs(7, context3.variantIndex + 2);
            methodWriter4.visitEnd();
        }
        byte[] byteArray = classWriter5.toByteArray();
        return (JavaBeanSerializer) this.classLoader.defineClassPublic(str15, byteArray, 0, byteArray.length).getConstructor(SerializeBeanInfo.class).newInstance(serializeBeanInfo);
    }

    private void generateWriteAsArray(Class<?> cls, MethodVisitor methodVisitor, FieldInfo[] fieldInfoArr, Context context) throws Exception {
        String str;
        int i;
        int i2;
        String str2;
        String str3;
        char c;
        boolean z;
        int i3;
        ASMSerializerFactory aSMSerializerFactory;
        char c2;
        int i4;
        Label label;
        String str4;
        java.lang.reflect.Type type;
        Class<?> cls2;
        int i5;
        Label label2;
        Label label3;
        Label label4;
        String str5;
        Label label5;
        int i6;
        int i7;
        String str6;
        int i8;
        Label label6;
        int i9;
        ASMSerializerFactory aSMSerializerFactory2 = this;
        FieldInfo[] fieldInfoArr2 = fieldInfoArr;
        Label label7 = new Label();
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 0);
        String str7 = JSONSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str7, "hasPropertyFilters", Operators.BRACKET_START_STR + SerializeFilterable_desc + ")Z");
        methodVisitor.visitJumpInsn(Opcodes.IFNE, label7);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, 3);
        methodVisitor.visitVarInsn(25, 4);
        methodVisitor.visitVarInsn(21, 5);
        String str8 = JavaBeanSerializer;
        String str9 = "(L";
        StringBuilder sb = new StringBuilder("(L");
        sb.append(str7);
        String str10 = ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V";
        sb.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, str8, "writeNoneASM", sb.toString());
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitLabel(label7);
        String str11 = "out";
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(16, 91);
        String str12 = SerializeWriter;
        String str13 = "(I)V";
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str12, "write", "(I)V");
        int length = fieldInfoArr2.length;
        if (length == 0) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 93);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str12, "write", "(I)V");
            return;
        }
        int i10 = 0;
        while (i10 < length) {
            int i11 = i10 == length + (-1) ? 93 : 44;
            FieldInfo fieldInfo = fieldInfoArr2[i10];
            Class<?> cls3 = fieldInfo.fieldClass;
            methodVisitor.visitLdcInsn(fieldInfo.name);
            methodVisitor.visitVarInsn(58, Context.fieldName);
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                str = str11;
                i = length;
                i2 = i10;
                int i12 = i11;
                str2 = str9;
                str3 = str13;
                c = 25;
                z = false;
                i3 = Opcodes.INVOKEVIRTUAL;
                methodVisitor.visitVarInsn(25, context.var(str));
                methodVisitor.visitInsn(89);
                aSMSerializerFactory = this;
                aSMSerializerFactory._get(methodVisitor, context, fieldInfo);
                String str14 = SerializeWriter;
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str14, "writeInt", str3);
                c2 = 16;
                methodVisitor.visitVarInsn(16, i12);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str14, "write", str3);
            } else {
                if (cls3 == Long.TYPE) {
                    methodVisitor.visitVarInsn(25, context.var(str11));
                    methodVisitor.visitInsn(89);
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    String str15 = SerializeWriter;
                    i9 = Opcodes.INVOKEVIRTUAL;
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str15, "writeLong", "(J)V");
                    methodVisitor.visitVarInsn(16, i11);
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str15, "write", str13);
                } else if (cls3 == Float.TYPE) {
                    methodVisitor.visitVarInsn(25, context.var(str11));
                    methodVisitor.visitInsn(89);
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitInsn(4);
                    String str16 = SerializeWriter;
                    i9 = Opcodes.INVOKEVIRTUAL;
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str16, "writeFloat", "(FZ)V");
                    methodVisitor.visitVarInsn(16, i11);
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str16, "write", str13);
                } else if (cls3 == Double.TYPE) {
                    methodVisitor.visitVarInsn(25, context.var(str11));
                    methodVisitor.visitInsn(89);
                    aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                    methodVisitor.visitInsn(4);
                    String str17 = SerializeWriter;
                    i9 = Opcodes.INVOKEVIRTUAL;
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str17, "writeDouble", "(DZ)V");
                    methodVisitor.visitVarInsn(16, i11);
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str17, "write", str13);
                } else {
                    if (cls3 == Boolean.TYPE) {
                        methodVisitor.visitVarInsn(25, context.var(str11));
                        methodVisitor.visitInsn(89);
                        aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                        String str18 = SerializeWriter;
                        i4 = Opcodes.INVOKEVIRTUAL;
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str18, "write", "(Z)V");
                        methodVisitor.visitVarInsn(16, i11);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str18, "write", str13);
                    } else if (cls3 == Character.TYPE) {
                        methodVisitor.visitVarInsn(25, context.var(str11));
                        aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "toString", "(C)Ljava/lang/String;");
                        methodVisitor.visitVarInsn(16, i11);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeString", "(Ljava/lang/String;C)V");
                        i = length;
                        i2 = i10;
                        i3 = 182;
                        str2 = str9;
                        str3 = str13;
                        z = false;
                        aSMSerializerFactory = aSMSerializerFactory2;
                        str = str11;
                        c2 = 16;
                        c = 25;
                    } else if (cls3 == String.class) {
                        methodVisitor.visitVarInsn(25, context.var(str11));
                        aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                        methodVisitor.visitVarInsn(16, i11);
                        String str19 = SerializeWriter;
                        i3 = Opcodes.INVOKEVIRTUAL;
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str19, "writeString", "(Ljava/lang/String;C)V");
                        i = length;
                        i2 = i10;
                        str2 = str9;
                        str3 = str13;
                        z = false;
                        c2 = 16;
                        aSMSerializerFactory = aSMSerializerFactory2;
                        str = str11;
                        c = 25;
                    } else {
                        i4 = Opcodes.INVOKEVIRTUAL;
                        if (cls3.isEnum()) {
                            methodVisitor.visitVarInsn(25, context.var(str11));
                            methodVisitor.visitInsn(89);
                            aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                            String str20 = SerializeWriter;
                            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str20, "writeEnum", "(Ljava/lang/Enum;)V");
                            methodVisitor.visitVarInsn(16, i11);
                            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str20, "write", str13);
                        } else if (List.class.isAssignableFrom(cls3)) {
                            java.lang.reflect.Type type2 = fieldInfo.fieldType;
                            if (type2 instanceof Class) {
                                type = Object.class;
                            } else {
                                type = ((ParameterizedType) type2).getActualTypeArguments()[0];
                            }
                            if (!(type instanceof Class) || (cls2 = (Class) type) == Object.class) {
                                cls2 = null;
                            }
                            aSMSerializerFactory2._get(methodVisitor, context, fieldInfo);
                            i = length;
                            methodVisitor.visitTypeInsn(192, "java/util/List");
                            i2 = i10;
                            methodVisitor.visitVarInsn(58, context.var(WXBasicComponentType.LIST));
                            if (cls2 == String.class && context.writeDirect) {
                                methodVisitor.visitVarInsn(25, context.var(str11));
                                methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(Ljava/util/List;)V");
                                str = str11;
                                i8 = 182;
                                i5 = i11;
                                str2 = str9;
                                str6 = str13;
                                i6 = 25;
                                i7 = 16;
                            } else {
                                Label label8 = new Label();
                                Label label9 = new Label();
                                i5 = i11;
                                methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
                                methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label9);
                                methodVisitor.visitVarInsn(25, context.var(str11));
                                String str21 = SerializeWriter;
                                java.lang.reflect.Type type3 = type;
                                String str22 = str10;
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str21, "writeNull", "()V");
                                methodVisitor.visitJumpInsn(Opcodes.GOTO, label8);
                                methodVisitor.visitLabel(label9);
                                methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", AbsoluteConst.JSON_KEY_SIZE, "()I");
                                methodVisitor.visitVarInsn(54, context.var(AbsoluteConst.JSON_KEY_SIZE));
                                methodVisitor.visitVarInsn(25, context.var(str11));
                                methodVisitor.visitVarInsn(16, 91);
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str21, "write", str13);
                                Label label10 = new Label();
                                Label label11 = new Label();
                                Label label12 = new Label();
                                methodVisitor.visitInsn(3);
                                String str23 = str9;
                                methodVisitor.visitVarInsn(54, context.var(ContextChain.TAG_INFRA));
                                methodVisitor.visitLabel(label10);
                                methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                                methodVisitor.visitVarInsn(21, context.var(AbsoluteConst.JSON_KEY_SIZE));
                                methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, label12);
                                methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label11);
                                methodVisitor.visitVarInsn(25, context.var(str11));
                                methodVisitor.visitVarInsn(16, 44);
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str21, "write", str13);
                                methodVisitor.visitLabel(label11);
                                methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
                                methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
                                methodVisitor.visitVarInsn(58, context.var("list_item"));
                                Label label13 = new Label();
                                Label label14 = new Label();
                                String str24 = str13;
                                methodVisitor.visitVarInsn(25, context.var("list_item"));
                                methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label14);
                                methodVisitor.visitVarInsn(25, context.var(str11));
                                String str25 = str11;
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str21, "writeNull", "()V");
                                methodVisitor.visitJumpInsn(Opcodes.GOTO, label13);
                                methodVisitor.visitLabel(label14);
                                Label label15 = new Label();
                                Label label16 = new Label();
                                if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                                    label2 = label10;
                                    label3 = label13;
                                    label4 = label12;
                                    str5 = str21;
                                    str10 = str22;
                                    str2 = str23;
                                    label5 = label16;
                                } else {
                                    methodVisitor.visitVarInsn(25, context.var("list_item"));
                                    str5 = str21;
                                    label4 = label12;
                                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                                    methodVisitor.visitJumpInsn(Opcodes.IF_ACMPNE, label16);
                                    aSMSerializerFactory2._getListFieldItemSer(context, methodVisitor, fieldInfo, cls2);
                                    methodVisitor.visitVarInsn(58, context.var("list_item_desc"));
                                    Label label17 = new Label();
                                    Label label18 = new Label();
                                    if (context.writeDirect) {
                                        methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                                        String str26 = JavaBeanSerializer;
                                        methodVisitor.visitTypeInsn(Opcodes.INSTANCEOF, str26);
                                        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label17);
                                        methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                                        methodVisitor.visitTypeInsn(192, str26);
                                        methodVisitor.visitVarInsn(25, 1);
                                        methodVisitor.visitVarInsn(25, context.var("list_item"));
                                        if (context.nonContext) {
                                            methodVisitor.visitInsn(1);
                                            label2 = label10;
                                            label3 = label13;
                                        } else {
                                            methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                                            label2 = label10;
                                            label3 = label13;
                                            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                        }
                                        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                                        methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                                        str2 = str23;
                                        StringBuilder sb2 = new StringBuilder(str2);
                                        sb2.append(JSONSerializer);
                                        str10 = str22;
                                        sb2.append(str10);
                                        label6 = label16;
                                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str26, "writeAsArrayNonContext", sb2.toString());
                                        methodVisitor.visitJumpInsn(Opcodes.GOTO, label18);
                                        methodVisitor.visitLabel(label17);
                                    } else {
                                        label2 = label10;
                                        label3 = label13;
                                        str10 = str22;
                                        str2 = str23;
                                        label6 = label16;
                                    }
                                    methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                                    methodVisitor.visitVarInsn(25, 1);
                                    methodVisitor.visitVarInsn(25, context.var("list_item"));
                                    if (context.nonContext) {
                                        methodVisitor.visitInsn(1);
                                    } else {
                                        methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                                        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                    }
                                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                                    methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                                    methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ObjectSerializer, "write", str2 + JSONSerializer + str10);
                                    methodVisitor.visitLabel(label18);
                                    methodVisitor.visitJumpInsn(Opcodes.GOTO, label15);
                                    label5 = label6;
                                }
                                methodVisitor.visitLabel(label5);
                                methodVisitor.visitVarInsn(25, 1);
                                methodVisitor.visitVarInsn(25, context.var("list_item"));
                                if (context.nonContext) {
                                    methodVisitor.visitInsn(1);
                                } else {
                                    methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                                    methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                }
                                if (cls2 != null && Modifier.isPublic(cls2.getModifiers())) {
                                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) type3)));
                                    methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                                } else {
                                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                                }
                                methodVisitor.visitLabel(label15);
                                methodVisitor.visitLabel(label3);
                                methodVisitor.visitIincInsn(context.var(ContextChain.TAG_INFRA), 1);
                                methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);
                                methodVisitor.visitLabel(label4);
                                str = str25;
                                i6 = 25;
                                methodVisitor.visitVarInsn(25, context.var(str));
                                i7 = 16;
                                methodVisitor.visitVarInsn(16, 93);
                                str6 = str24;
                                i8 = Opcodes.INVOKEVIRTUAL;
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "write", str6);
                                methodVisitor.visitLabel(label8);
                            }
                            methodVisitor.visitVarInsn(i6, context.var(str));
                            methodVisitor.visitVarInsn(i7, i5);
                            methodVisitor.visitMethodInsn(i8, SerializeWriter, "write", str6);
                            aSMSerializerFactory = this;
                            i3 = i8;
                            str3 = str6;
                            c = 25;
                            z = false;
                            c2 = 16;
                        } else {
                            String str27 = str11;
                            i = length;
                            i2 = i10;
                            str2 = str9;
                            String str28 = str13;
                            Label label19 = new Label();
                            Label label20 = new Label();
                            _get(methodVisitor, context, fieldInfo);
                            methodVisitor.visitInsn(89);
                            methodVisitor.visitVarInsn(58, context.var("field_" + fieldInfo.fieldClass.getName()));
                            methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label20);
                            methodVisitor.visitVarInsn(25, context.var(str27));
                            String str29 = SerializeWriter;
                            int i13 = i11;
                            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str29, "writeNull", "()V");
                            methodVisitor.visitJumpInsn(Opcodes.GOTO, label19);
                            methodVisitor.visitLabel(label20);
                            Label label21 = new Label();
                            Label label22 = new Label();
                            methodVisitor.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls3)));
                            methodVisitor.visitJumpInsn(Opcodes.IF_ACMPNE, label22);
                            _getFieldSer(context, methodVisitor, fieldInfo);
                            methodVisitor.visitVarInsn(58, context.var("fied_ser"));
                            Label label23 = new Label();
                            Label label24 = new Label();
                            if (context.writeDirect && Modifier.isPublic(cls3.getModifiers())) {
                                methodVisitor.visitVarInsn(25, context.var("fied_ser"));
                                String str30 = JavaBeanSerializer;
                                methodVisitor.visitTypeInsn(Opcodes.INSTANCEOF, str30);
                                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label23);
                                str4 = "writeWithFieldName";
                                methodVisitor.visitVarInsn(25, context.var("fied_ser"));
                                methodVisitor.visitTypeInsn(192, str30);
                                methodVisitor.visitVarInsn(25, 1);
                                methodVisitor.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                                methodVisitor.visitVarInsn(25, Context.fieldName);
                                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls3)));
                                methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                                label = label22;
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str30, "writeAsArrayNonContext", str2 + JSONSerializer + str10);
                                methodVisitor.visitJumpInsn(Opcodes.GOTO, label24);
                                methodVisitor.visitLabel(label23);
                            } else {
                                label = label22;
                                str4 = "writeWithFieldName";
                            }
                            methodVisitor.visitVarInsn(25, context.var("fied_ser"));
                            methodVisitor.visitVarInsn(25, 1);
                            methodVisitor.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                            methodVisitor.visitVarInsn(25, Context.fieldName);
                            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls3)));
                            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                            String str31 = ObjectSerializer;
                            StringBuilder sb3 = new StringBuilder(str2);
                            String str32 = JSONSerializer;
                            sb3.append(str32);
                            sb3.append(str10);
                            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, str31, "write", sb3.toString());
                            methodVisitor.visitLabel(label24);
                            methodVisitor.visitJumpInsn(Opcodes.GOTO, label21);
                            methodVisitor.visitLabel(label);
                            String format = fieldInfo.getFormat();
                            methodVisitor.visitVarInsn(25, 1);
                            methodVisitor.visitVarInsn(25, context.var("field_" + fieldInfo.fieldClass.getName()));
                            if (format != null) {
                                methodVisitor.visitLdcInsn(format);
                                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str32, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
                                i3 = 182;
                            } else {
                                methodVisitor.visitVarInsn(25, Context.fieldName);
                                if ((fieldInfo.fieldType instanceof Class) && ((Class) fieldInfo.fieldType).isPrimitive()) {
                                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str32, str4, "(Ljava/lang/Object;Ljava/lang/Object;)V");
                                    i3 = 182;
                                } else {
                                    z = false;
                                    methodVisitor.visitVarInsn(25, 0);
                                    methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                                    methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                                    i3 = Opcodes.INVOKEVIRTUAL;
                                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str32, str4, "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                                    methodVisitor.visitLabel(label21);
                                    methodVisitor.visitLabel(label19);
                                    str = str27;
                                    c = 25;
                                    methodVisitor.visitVarInsn(25, context.var(str));
                                    methodVisitor.visitVarInsn(16, i13);
                                    str3 = str28;
                                    methodVisitor.visitMethodInsn(i3, str29, "write", str3);
                                    aSMSerializerFactory = this;
                                    c2 = 16;
                                }
                            }
                            z = false;
                            methodVisitor.visitLabel(label21);
                            methodVisitor.visitLabel(label19);
                            str = str27;
                            c = 25;
                            methodVisitor.visitVarInsn(25, context.var(str));
                            methodVisitor.visitVarInsn(16, i13);
                            str3 = str28;
                            methodVisitor.visitMethodInsn(i3, str29, "write", str3);
                            aSMSerializerFactory = this;
                            c2 = 16;
                        }
                    }
                    aSMSerializerFactory = aSMSerializerFactory2;
                    str = str11;
                    i = length;
                    i2 = i10;
                    i3 = i4;
                    str2 = str9;
                    str3 = str13;
                    c = 25;
                    z = false;
                    c2 = 16;
                }
                i = length;
                i2 = i10;
                i3 = i9;
                str2 = str9;
                str3 = str13;
                z = false;
                c2 = 16;
                aSMSerializerFactory = aSMSerializerFactory2;
                str = str11;
                c = 25;
            }
            str13 = str3;
            length = i;
            i10 = i2 + 1;
            fieldInfoArr2 = fieldInfoArr;
            str11 = str;
            aSMSerializerFactory2 = aSMSerializerFactory;
            str9 = str2;
        }
    }

    private void generateWriteMethod(Class<?> cls, MethodVisitor methodVisitor, FieldInfo[] fieldInfoArr, Context context) throws Exception {
        Label label;
        String str;
        String str2;
        int i;
        String str3;
        int i2;
        Class<?> cls2;
        Label label2 = new Label();
        int length = fieldInfoArr.length;
        String str4 = "out";
        if (context.writeDirect) {
            label = label2;
        } else {
            Label label3 = new Label();
            Label label4 = new Label();
            label = label2;
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.PrettyFormat.mask));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label4);
            int length2 = fieldInfoArr.length;
            int i3 = 0;
            while (true) {
                if (i3 < length2) {
                    int i4 = length2;
                    if (fieldInfoArr[i3].method != null) {
                        methodVisitor.visitVarInsn(25, context.var("out"));
                        methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.IgnoreErrorGetter.mask));
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
                        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label3);
                        break;
                    }
                    i3++;
                    length2 = i4;
                } else {
                    methodVisitor.visitJumpInsn(Opcodes.GOTO, label3);
                    break;
                }
            }
            methodVisitor.visitLabel(label4);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, 3);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(21, 5);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, JavaBeanSerializer, "write", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitLabel(label3);
        }
        if (!context.nonContext) {
            Label label5 = new Label();
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(21, 5);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "writeReference", "(L" + JSONSerializer + ";Ljava/lang/Object;I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label5);
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitLabel(label5);
        }
        if (context.writeDirect) {
            if (context.nonContext) {
                str = "writeAsArrayNonContext";
            } else {
                str = "writeAsArray";
            }
        } else {
            str = "writeAsArrayNormal";
        }
        if ((context.beanInfo.features & SerializerFeature.BeanToArray.mask) == 0) {
            Label label6 = new Label();
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.BeanToArray.mask));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label6);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, 3);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(21, 5);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, context.className, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitLabel(label6);
        } else {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, 3);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(21, 5);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, context.className, str, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitInsn(Opcodes.RETURN);
        }
        if (!context.nonContext) {
            methodVisitor.visitVarInsn(25, 1);
            String str5 = JSONSerializer;
            StringBuilder sb = new StringBuilder("()");
            String str6 = SerialContext_desc;
            sb.append(str6);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "getContext", sb.toString());
            methodVisitor.visitVarInsn(58, context.var("parent"));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("parent"));
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, 3);
            methodVisitor.visitLdcInsn(Integer.valueOf(context.beanInfo.features));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "setContext", Operators.BRACKET_START_STR + str6 + "Ljava/lang/Object;Ljava/lang/Object;I)V");
        }
        boolean z = (context.beanInfo.features & SerializerFeature.WriteClassName.mask) != 0;
        if (z || !context.writeDirect) {
            Label label7 = new Label();
            Label label8 = new Label();
            Label label9 = new Label();
            if (z) {
                str2 = "parent";
                i = Opcodes.INVOKEVIRTUAL;
            } else {
                methodVisitor.visitVarInsn(25, 1);
                methodVisitor.visitVarInsn(25, 4);
                methodVisitor.visitVarInsn(25, 2);
                String str7 = JSONSerializer;
                str2 = "parent";
                i = Opcodes.INVOKEVIRTUAL;
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str7, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
                methodVisitor.visitJumpInsn(Opcodes.IFEQ, label8);
            }
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(i, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            methodVisitor.visitJumpInsn(Opcodes.IF_ACMPEQ, label8);
            methodVisitor.visitLabel(label9);
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 123);
            methodVisitor.visitMethodInsn(i, SerializeWriter, "write", "(I)V");
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            if (context.beanInfo.typeKey != null) {
                methodVisitor.visitLdcInsn(context.beanInfo.typeKey);
            } else {
                methodVisitor.visitInsn(1);
            }
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "writeClassName", "(L" + JSONSerializer + ";Ljava/lang/String;Ljava/lang/Object;)V");
            methodVisitor.visitVarInsn(16, 44);
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label7);
            methodVisitor.visitLabel(label8);
            methodVisitor.visitVarInsn(16, 123);
            methodVisitor.visitLabel(label7);
        } else {
            methodVisitor.visitVarInsn(16, 123);
            str2 = "parent";
        }
        methodVisitor.visitVarInsn(54, context.var("seperator"));
        if (!context.writeDirect) {
            _before(methodVisitor, context);
        }
        if (!context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isNotWriteDefaultValue", "()Z");
            methodVisitor.visitVarInsn(54, context.var("notWriteDefaultValue"));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 0);
            String str8 = JSONSerializer;
            StringBuilder sb2 = new StringBuilder(Operators.BRACKET_START_STR);
            String str9 = SerializeFilterable_desc;
            sb2.append(str9);
            sb2.append(")Z");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str8, "checkValue", sb2.toString());
            methodVisitor.visitVarInsn(54, context.var("checkValue"));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str8, "hasNameFilters", Operators.BRACKET_START_STR + str9 + ")Z");
            methodVisitor.visitVarInsn(54, context.var("hasNameFilters"));
        }
        int i5 = 0;
        while (i5 < length) {
            FieldInfo fieldInfo = fieldInfoArr[i5];
            Class<?> cls3 = fieldInfo.fieldClass;
            methodVisitor.visitLdcInsn(fieldInfo.name);
            methodVisitor.visitVarInsn(58, Context.fieldName);
            if (cls3 == Byte.TYPE || cls3 == Short.TYPE || cls3 == Integer.TYPE) {
                str3 = str4;
                i2 = i5;
                _int(cls, methodVisitor, fieldInfo, context, context.var(cls3.getName()), 'I');
            } else {
                if (cls3 == Long.TYPE) {
                    cls2 = cls;
                    _long(cls2, methodVisitor, fieldInfo, context);
                } else {
                    cls2 = cls;
                    if (cls3 == Float.TYPE) {
                        _float(cls2, methodVisitor, fieldInfo, context);
                    } else if (cls3 == Double.TYPE) {
                        _double(cls2, methodVisitor, fieldInfo, context);
                    } else if (cls3 == Boolean.TYPE) {
                        str3 = str4;
                        i2 = i5;
                        _int(cls, methodVisitor, fieldInfo, context, context.var("boolean"), 'Z');
                    } else {
                        str3 = str4;
                        i2 = i5;
                        if (cls3 == Character.TYPE) {
                            _int(cls, methodVisitor, fieldInfo, context, context.var("char"), 'C');
                        } else if (cls3 == String.class) {
                            _string(cls2, methodVisitor, fieldInfo, context);
                        } else if (cls3 == BigDecimal.class) {
                            _decimal(cls2, methodVisitor, fieldInfo, context);
                        } else if (List.class.isAssignableFrom(cls3)) {
                            _list(cls2, methodVisitor, fieldInfo, context);
                        } else if (cls3.isEnum()) {
                            _enum(cls2, methodVisitor, fieldInfo, context);
                        } else {
                            _object(cls2, methodVisitor, fieldInfo, context);
                        }
                    }
                }
                str3 = str4;
                i2 = i5;
            }
            i5 = i2 + 1;
            str4 = str3;
        }
        String str10 = str4;
        if (!context.writeDirect) {
            _after(methodVisitor, context);
        }
        Label label10 = new Label();
        Label label11 = new Label();
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitIntInsn(16, 123);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label10);
        methodVisitor.visitVarInsn(25, context.var(str10));
        methodVisitor.visitVarInsn(16, 123);
        String str11 = SerializeWriter;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str11, "write", "(I)V");
        methodVisitor.visitLabel(label10);
        methodVisitor.visitVarInsn(25, context.var(str10));
        methodVisitor.visitVarInsn(16, 125);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str11, "write", "(I)V");
        methodVisitor.visitLabel(label11);
        methodVisitor.visitLabel(label);
        if (context.nonContext) {
            return;
        }
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(str2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "setContext", Operators.BRACKET_START_STR + SerialContext_desc + ")V");
    }

    private void _object(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("object"));
        _filters(methodVisitor, fieldInfo, context, label);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitLabel(label);
    }

    private void _enum(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label3);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitTypeInsn(192, "java/lang/Enum");
        methodVisitor.visitVarInsn(58, context.var("enum"));
        _filters(methodVisitor, fieldInfo, context, label3);
        methodVisitor.visitVarInsn(25, context.var("enum"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);
        methodVisitor.visitLabel(label);
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Enum", "name", "()Ljava/lang/String;");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValueStringWithDoubleQuote", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            String str = SerializeWriter;
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "write", "(I)V");
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitInsn(3);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "writeFieldName", "(Ljava/lang/String;Z)V");
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLabel(label3);
    }

    private void _int(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, int i, char c) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, i);
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(21, i);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;" + c + ")V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _long(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(55, context.var("long", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(22, context.var("long", 2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;J)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _float(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(56, context.var("float"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(23, context.var("float"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;F)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _double(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(57, context.var("double", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(24, context.var("double", 2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;D)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _get(MethodVisitor methodVisitor, Context context, FieldInfo fieldInfo) {
        Method method = fieldInfo.method;
        if (method != null) {
            methodVisitor.visitVarInsn(25, context.var("entity"));
            Class<?> declaringClass = method.getDeclaringClass();
            methodVisitor.visitMethodInsn(declaringClass.isInterface() ? Opcodes.INVOKEINTERFACE : Opcodes.INVOKEVIRTUAL, ASMUtils.type(declaringClass), method.getName(), ASMUtils.desc(method));
            if (method.getReturnType().equals(fieldInfo.fieldClass)) {
                return;
            }
            methodVisitor.visitTypeInsn(192, ASMUtils.type(fieldInfo.fieldClass));
            return;
        }
        methodVisitor.visitVarInsn(25, context.var("entity"));
        Field field = fieldInfo.field;
        methodVisitor.visitFieldInsn(180, ASMUtils.type(fieldInfo.declaringClass), field.getName(), ASMUtils.desc(field.getType()));
        if (field.getType().equals(fieldInfo.fieldClass)) {
            return;
        }
        methodVisitor.visitTypeInsn(192, ASMUtils.type(fieldInfo.fieldClass));
    }

    private void _decimal(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("decimal"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label3);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label4);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, Context.fieldName);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label4);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLabel(label);
    }

    private void _string(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        if (fieldInfo.name.equals(context.beanInfo.typeKey)) {
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 4);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("string"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("string"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label2);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label3);
        methodVisitor.visitLabel(label2);
        if (AbsoluteConst.XML_TRIM.equals(fieldInfo.format)) {
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", AbsoluteConst.XML_TRIM, "()Ljava/lang/String;");
            methodVisitor.visitVarInsn(58, context.var("string"));
        }
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValueStringWithDoubleQuoteCheck", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(21, context.var("seperator"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLabel(label);
    }

    private void _list(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label;
        Label label2;
        Label label3;
        String str;
        Label label4;
        Label label5;
        String str2;
        Label label6;
        FieldInfo fieldInfo2;
        int i;
        int i2;
        int i3;
        Label label7;
        String str3;
        String str4;
        java.lang.reflect.Type collectionItemType = TypeUtils.getCollectionItemType(fieldInfo.fieldType);
        Class<?> cls2 = null;
        Class<?> cls3 = collectionItemType instanceof Class ? (Class) collectionItemType : null;
        if (cls3 != Object.class && cls3 != Serializable.class) {
            cls2 = cls3;
        }
        Label label8 = new Label();
        Label label9 = new Label();
        Label label10 = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label8);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitTypeInsn(192, "java/util/List");
        methodVisitor.visitVarInsn(58, context.var(WXBasicComponentType.LIST));
        _filters(methodVisitor, fieldInfo, context, label8);
        methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label9);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label10);
        methodVisitor.visitLabel(label9);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        String str5 = SerializeWriter;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "write", "(I)V");
        _writeFieldName(methodVisitor, context);
        methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", AbsoluteConst.JSON_KEY_SIZE, "()I");
        methodVisitor.visitVarInsn(54, context.var(AbsoluteConst.JSON_KEY_SIZE));
        Label label11 = new Label();
        Label label12 = new Label();
        methodVisitor.visitVarInsn(21, context.var(AbsoluteConst.JSON_KEY_SIZE));
        methodVisitor.visitInsn(3);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, label11);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitLdcInsn("[]");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "write", "(Ljava/lang/String;)V");
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label12);
        methodVisitor.visitLabel(label11);
        if (context.nonContext) {
            label = label12;
        } else {
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            label = label12;
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
        }
        if (collectionItemType == String.class && context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "write", "(Ljava/util/List;)V");
            i2 = 25;
            i3 = 182;
            i = 1;
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 91);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "write", "(I)V");
            Label label13 = new Label();
            Label label14 = new Label();
            Label label15 = new Label();
            methodVisitor.visitInsn(3);
            methodVisitor.visitVarInsn(54, context.var(ContextChain.TAG_INFRA));
            methodVisitor.visitLabel(label13);
            methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
            methodVisitor.visitVarInsn(21, context.var(AbsoluteConst.JSON_KEY_SIZE));
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, label15);
            methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label14);
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 44);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "write", "(I)V");
            methodVisitor.visitLabel(label14);
            methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
            methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;");
            methodVisitor.visitVarInsn(58, context.var("list_item"));
            Label label16 = new Label();
            Label label17 = new Label();
            methodVisitor.visitVarInsn(25, context.var("list_item"));
            methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label17);
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, "writeNull", "()V");
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label16);
            methodVisitor.visitLabel(label17);
            Label label18 = new Label();
            Label label19 = new Label();
            if (cls2 == null || !Modifier.isPublic(cls2.getModifiers())) {
                label2 = label13;
                label3 = label16;
                str = "out";
                label4 = label18;
                label5 = label19;
                str2 = "write";
                label6 = label15;
                fieldInfo2 = fieldInfo;
            } else {
                str = "out";
                methodVisitor.visitVarInsn(25, context.var("list_item"));
                label6 = label15;
                label2 = label13;
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                methodVisitor.visitJumpInsn(Opcodes.IF_ACMPNE, label19);
                fieldInfo2 = fieldInfo;
                _getListFieldItemSer(context, methodVisitor, fieldInfo2, cls2);
                methodVisitor.visitVarInsn(58, context.var("list_item_desc"));
                Label label20 = new Label();
                Label label21 = new Label();
                if (context.writeDirect) {
                    if (context.nonContext && context.writeDirect) {
                        label3 = label16;
                        str4 = "writeDirectNonContext";
                        label5 = label19;
                    } else {
                        label3 = label16;
                        label5 = label19;
                        str4 = "write";
                    }
                    label7 = label18;
                    methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                    String str6 = JavaBeanSerializer;
                    methodVisitor.visitTypeInsn(Opcodes.INSTANCEOF, str6);
                    methodVisitor.visitJumpInsn(Opcodes.IFEQ, label20);
                    str3 = "write";
                    methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                    methodVisitor.visitTypeInsn(192, str6);
                    methodVisitor.visitVarInsn(25, 1);
                    methodVisitor.visitVarInsn(25, context.var("list_item"));
                    if (context.nonContext) {
                        methodVisitor.visitInsn(1);
                    } else {
                        methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                        methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                    }
                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                    methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str6, str4, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                    methodVisitor.visitJumpInsn(Opcodes.GOTO, label21);
                    methodVisitor.visitLabel(label20);
                } else {
                    label3 = label16;
                    label7 = label18;
                    label5 = label19;
                    str3 = "write";
                }
                methodVisitor.visitVarInsn(25, context.var("list_item_desc"));
                methodVisitor.visitVarInsn(25, 1);
                methodVisitor.visitVarInsn(25, context.var("list_item"));
                if (context.nonContext) {
                    methodVisitor.visitInsn(1);
                } else {
                    methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                    methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                }
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls2)));
                methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                str2 = str3;
                methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ObjectSerializer, str2, "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
                methodVisitor.visitLabel(label21);
                label4 = label7;
                methodVisitor.visitJumpInsn(Opcodes.GOTO, label4);
            }
            methodVisitor.visitLabel(label5);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("list_item"));
            if (context.nonContext) {
                methodVisitor.visitInsn(1);
            } else {
                methodVisitor.visitVarInsn(21, context.var(ContextChain.TAG_INFRA));
                methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            }
            if (cls2 != null && Modifier.isPublic(cls2.getModifiers())) {
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) collectionItemType)));
                methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo2.serialzeFeatures));
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            } else {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            }
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLabel(label3);
            i = 1;
            methodVisitor.visitIincInsn(context.var(ContextChain.TAG_INFRA), 1);
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);
            methodVisitor.visitLabel(label6);
            i2 = 25;
            methodVisitor.visitVarInsn(25, context.var(str));
            methodVisitor.visitVarInsn(16, 93);
            i3 = Opcodes.INVOKEVIRTUAL;
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str5, str2, "(I)V");
        }
        methodVisitor.visitVarInsn(i2, i);
        methodVisitor.visitMethodInsn(i3, JSONSerializer, "popContext", "()V");
        methodVisitor.visitLabel(label);
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label10);
        methodVisitor.visitLabel(label8);
    }

    private void _filters(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (fieldInfo.fieldTransient) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.SkipTransientField.mask));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
        _notWriteDefault(methodVisitor, fieldInfo, context, label);
        if (context.writeDirect) {
            return;
        }
        _apply(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        _processKey(methodVisitor, fieldInfo, context);
        _processValue(methodVisitor, fieldInfo, context, label);
    }

    private void _nameApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (!context.writeDirect) {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, 2);
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "applyName", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
            _labelApply(methodVisitor, fieldInfo, context, label);
        }
        if (fieldInfo.field == null) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitLdcInsn(Integer.valueOf(SerializerFeature.IgnoreNonFieldGetter.mask));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "isEnabled", "(I)Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
    }

    private void _labelApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(fieldInfo.label);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "applyLabel", "(L" + JSONSerializer + ";Ljava/lang/String;)Z");
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
    }

    private void _writeObject(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        String str;
        Label label2;
        Label label3;
        String str2;
        String format = fieldInfo.getFormat();
        Class<?> cls = fieldInfo.fieldClass;
        Label label4 = new Label();
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("object"));
        } else {
            methodVisitor.visitVarInsn(25, Context.processValue);
        }
        methodVisitor.visitInsn(89);
        methodVisitor.visitVarInsn(58, context.var("object"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label4);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "write", "(I)V");
        _writeFieldName(methodVisitor, context);
        Label label5 = new Label();
        Label label6 = new Label();
        if (!Modifier.isPublic(cls.getModifiers()) || ParserConfig.isPrimitive2(cls)) {
            str = format;
            label2 = label5;
            label3 = label6;
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
            methodVisitor.visitJumpInsn(Opcodes.IF_ACMPNE, label6);
            _getFieldSer(context, methodVisitor, fieldInfo);
            methodVisitor.visitVarInsn(58, context.var("fied_ser"));
            Label label7 = new Label();
            Label label8 = new Label();
            methodVisitor.visitVarInsn(25, context.var("fied_ser"));
            String str3 = JavaBeanSerializer;
            methodVisitor.visitTypeInsn(Opcodes.INSTANCEOF, str3);
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label7);
            boolean z = (SerializerFeature.DisableCircularReferenceDetect.mask & fieldInfo.serialzeFeatures) != 0;
            str = format;
            boolean z2 = (SerializerFeature.BeanToArray.mask & fieldInfo.serialzeFeatures) != 0;
            if (z || (context.nonContext && context.writeDirect)) {
                str2 = z2 ? "writeAsArrayNonContext" : "writeDirectNonContext";
            } else {
                str2 = z2 ? "writeAsArray" : "write";
            }
            methodVisitor.visitVarInsn(25, context.var("fied_ser"));
            methodVisitor.visitTypeInsn(192, str3);
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
            StringBuilder sb = new StringBuilder("(L");
            String str4 = JSONSerializer;
            sb.append(str4);
            sb.append(";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str3, str2, sb.toString());
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label8);
            methodVisitor.visitLabel(label7);
            methodVisitor.visitVarInsn(25, context.var("fied_ser"));
            methodVisitor.visitVarInsn(25, 1);
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
            methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ObjectSerializer, "write", "(L" + str4 + ";Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            methodVisitor.visitLabel(label8);
            label2 = label5;
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);
            label3 = label6;
        }
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, 1);
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("object"));
        } else {
            methodVisitor.visitVarInsn(25, Context.processValue);
        }
        if (str != null) {
            methodVisitor.visitLdcInsn(str);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, Context.fieldName);
            if ((fieldInfo.fieldType instanceof Class) && ((Class) fieldInfo.fieldType).isPrimitive()) {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                if (fieldInfo.fieldClass == String.class) {
                    methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc((Class<?>) String.class)));
                } else {
                    methodVisitor.visitVarInsn(25, 0);
                    methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                }
                methodVisitor.visitLdcInsn(Integer.valueOf(fieldInfo.serialzeFeatures));
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;I)V");
            }
        }
        methodVisitor.visitLabel(label2);
        _seperator(methodVisitor, context);
    }

    private void _before(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "writeBefore", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _after(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "writeAfter", "(L" + JSONSerializer + ";Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _notWriteDefault(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (context.writeDirect) {
            return;
        }
        Label label2 = new Label();
        methodVisitor.visitVarInsn(21, context.var("notWriteDefaultValue"));
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label2);
        Class<?> cls = fieldInfo.fieldClass;
        if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        } else if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long"));
            methodVisitor.visitInsn(9);
            methodVisitor.visitInsn(Opcodes.LCMP);
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitInsn(11);
            methodVisitor.visitInsn(Opcodes.FCMPL);
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double"));
            methodVisitor.visitInsn(14);
            methodVisitor.visitInsn(Opcodes.DCMPL);
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        }
        methodVisitor.visitLabel(label2);
    }

    private void _apply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class<?> cls = fieldInfo.fieldClass;
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "apply", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
    }

    private void _processValue(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        Label label2 = new Label();
        Class<?> cls = fieldInfo.fieldClass;
        if (cls.isPrimitive()) {
            Label label3 = new Label();
            methodVisitor.visitVarInsn(21, context.var("checkValue"));
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label3);
            methodVisitor.visitInsn(1);
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(58, Context.processValue);
            methodVisitor.visitJumpInsn(Opcodes.GOTO, label2);
            methodVisitor.visitLabel(label3);
        }
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitLdcInsn(Integer.valueOf(context.getFieldOrinal(fieldInfo.name)));
        String str = JavaBeanSerializer;
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "getBeanContext", "(I)" + ASMUtils.desc((Class<?>) BeanContext.class));
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
            methodVisitor.visitInsn(89);
            methodVisitor.visitVarInsn(58, Context.original);
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitVarInsn(58, Context.original);
            methodVisitor.visitVarInsn(25, Context.original);
        }
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, str, "processValue", "(L" + JSONSerializer + ";" + ASMUtils.desc((Class<?>) BeanContext.class) + "Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor.visitVarInsn(58, Context.processValue);
        methodVisitor.visitVarInsn(25, Context.original);
        methodVisitor.visitVarInsn(25, Context.processValue);
        methodVisitor.visitJumpInsn(Opcodes.IF_ACMPEQ, label2);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, label);
        methodVisitor.visitLabel(label2);
    }

    private void _processKey(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        methodVisitor.visitVarInsn(21, context.var("hasNameFilters"));
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, label);
        Class<?> cls = fieldInfo.fieldClass;
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, Context.fieldName);
        if (cls == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (cls == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        } else if (cls == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (cls == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (cls == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        } else if (cls == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        } else if (cls == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        } else if (cls == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (cls == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (cls == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (cls.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(cls)) {
            methodVisitor.visitVarInsn(25, context.var(WXBasicComponentType.LIST));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JavaBeanSerializer, "processKey", "(L" + JSONSerializer + ";Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        methodVisitor.visitVarInsn(58, Context.fieldName);
        methodVisitor.visitLabel(label);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x012f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void _if_write_null(com.alibaba.fastjson.asm.MethodVisitor r12, com.alibaba.fastjson.util.FieldInfo r13, com.alibaba.fastjson.serializer.ASMSerializerFactory.Context r14) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.ASMSerializerFactory._if_write_null(com.alibaba.fastjson.asm.MethodVisitor, com.alibaba.fastjson.util.FieldInfo, com.alibaba.fastjson.serializer.ASMSerializerFactory$Context):void");
    }

    private void _writeFieldName(MethodVisitor methodVisitor, Context context) {
        if (context.writeDirect) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldNameDirect", "(Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, Context.fieldName);
            methodVisitor.visitInsn(3);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, SerializeWriter, "writeFieldName", "(Ljava/lang/String;Z)V");
        }
    }

    private void _seperator(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(16, 44);
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _getListFieldItemSer(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        String str = context.className;
        String str2 = fieldInfo.name + "_asm_list_item_ser_";
        String str3 = ObjectSerializer_desc;
        methodVisitor.visitFieldInsn(180, str, str2, str3);
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(cls)));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "getObjectWriter", "(Ljava/lang/Class;)" + str3);
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, context.className, fieldInfo.name + "_asm_list_item_ser_", str3);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_list_item_ser_", str3);
    }

    private void _getFieldSer(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        String str = context.className;
        String str2 = fieldInfo.name + "_asm_ser_";
        String str3 = ObjectSerializer_desc;
        methodVisitor.visitFieldInsn(180, str, str2, str3);
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.desc(fieldInfo.fieldClass)));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, JSONSerializer, "getObjectWriter", "(Ljava/lang/Class;)" + str3);
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, context.className, fieldInfo.name + "_asm_ser_", str3);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(180, context.className, fieldInfo.name + "_asm_ser_", str3);
    }
}
