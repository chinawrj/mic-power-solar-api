package io.dcloud.nineoldandroids.util;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
class ReflectiveProperty<T, V> extends Property<T, V> {
    private static final String PREFIX_GET = "get";
    private static final String PREFIX_IS = "is";
    private static final String PREFIX_SET = "set";
    private Field mField;
    private Method mGetter;
    private Method mSetter;

    public ReflectiveProperty(Class<T> cls, Class<V> cls2, String str) throws NoSuchFieldException, NoSuchMethodException, SecurityException {
        super(cls2, str);
        char upperCase = Character.toUpperCase(str.charAt(0));
        String str2 = String.valueOf(upperCase) + str.substring(1);
        String str3 = PREFIX_GET + str2;
        try {
            try {
                this.mGetter = cls.getMethod(str3, null);
            } catch (NoSuchMethodException unused) {
                String str4 = PREFIX_IS + str2;
                try {
                    try {
                        try {
                            this.mGetter = cls.getMethod(str4, null);
                        } catch (NoSuchFieldException unused2) {
                            throw new NoSuchPropertyException("No accessor method or field found for property with name " + str);
                        }
                    } catch (NoSuchMethodException unused3) {
                        Method declaredMethod = cls.getDeclaredMethod(str4, null);
                        this.mGetter = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                } catch (NoSuchMethodException unused4) {
                    Field field = cls.getField(str);
                    this.mField = field;
                    Class<?> type = field.getType();
                    if (typesMatch(cls2, type)) {
                        return;
                    }
                    throw new NoSuchPropertyException("Underlying type (" + type + ") does not match Property type (" + cls2 + Operators.BRACKET_END_STR);
                }
            }
        } catch (NoSuchMethodException unused5) {
            Method declaredMethod2 = cls.getDeclaredMethod(str3, null);
            this.mGetter = declaredMethod2;
            declaredMethod2.setAccessible(true);
        }
        Class<?> returnType = this.mGetter.getReturnType();
        if (!typesMatch(cls2, returnType)) {
            throw new NoSuchPropertyException("Underlying type (" + returnType + ") does not match Property type (" + cls2 + Operators.BRACKET_END_STR);
        }
        try {
            Method declaredMethod3 = cls.getDeclaredMethod(PREFIX_SET + str2, returnType);
            this.mSetter = declaredMethod3;
            declaredMethod3.setAccessible(true);
        } catch (NoSuchMethodException unused6) {
        }
    }

    private boolean typesMatch(Class<V> cls, Class cls2) {
        if (cls2 != cls) {
            return cls2.isPrimitive() && ((cls2 == Float.TYPE && cls == Float.class) || ((cls2 == Integer.TYPE && cls == Integer.class) || ((cls2 == Boolean.TYPE && cls == Boolean.class) || ((cls2 == Long.TYPE && cls == Long.class) || ((cls2 == Double.TYPE && cls == Double.class) || ((cls2 == Short.TYPE && cls == Short.class) || ((cls2 == Byte.TYPE && cls == Byte.class) || (cls2 == Character.TYPE && cls == Character.class))))))));
        }
        return true;
    }

    @Override // io.dcloud.nineoldandroids.util.Property
    public void set(T t, V v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = this.mSetter;
        if (method != null) {
            try {
                method.invoke(t, v);
                return;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        Field field = this.mField;
        if (field != null) {
            try {
                field.set(t, v);
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        } else {
            throw new UnsupportedOperationException("Property " + getName() + " is read-only");
        }
    }

    @Override // io.dcloud.nineoldandroids.util.Property
    public V get(T t) {
        Method method = this.mGetter;
        if (method != null) {
            try {
                return (V) method.invoke(t, null);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        Field field = this.mField;
        if (field != null) {
            try {
                return (V) field.get(t);
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        }
        throw new AssertionError();
    }

    @Override // io.dcloud.nineoldandroids.util.Property
    public boolean isReadOnly() {
        return this.mSetter == null && this.mField == null;
    }
}
