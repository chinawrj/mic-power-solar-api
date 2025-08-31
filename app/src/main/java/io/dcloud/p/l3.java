package io.dcloud.p;

import android.content.Context;
import android.text.TextUtils;
import com.bun.miitmdid.core.ErrorCode;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes3.dex */
public class l3 {
    private static l3 b;
    private String a = "";

    private class a implements InvocationHandler {
        Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (method.getName().equalsIgnoreCase("OnSupport") && objArr != null && objArr.length > 0) {
                ((Boolean) objArr[0]).booleanValue();
                Object obj2 = objArr.length > 1 ? objArr[1] : null;
                if (obj2 != null) {
                    Object objA = l3.a(obj2, "getOAID", null, new Object[0]);
                    Object objA2 = l3.a(obj2, "getVAID", null, new Object[0]);
                    Object objA3 = l3.a(obj2, "getAAID", null, new Object[0]);
                    (objA == null ? "" : objA).toString();
                    if (objA2 == null) {
                        objA2 = "";
                    }
                    objA2.toString();
                    if (objA3 == null) {
                        objA3 = "";
                    }
                    objA3.toString();
                    l3.this.a = String.valueOf(objA);
                    Context context = this.a;
                    if (context != null) {
                        d4.a(context, "dcloud-ads", "oaid", String.valueOf(objA));
                    }
                }
            }
            return null;
        }
    }

    private l3() {
    }

    private boolean b(Context context) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int iA = a(context);
        return (iA == 1008612 || iA == 1008613 || iA == 1008611 || iA != 1008614) ? false : true;
    }

    public String c(Context context) {
        if (TextUtils.isEmpty(this.a)) {
            if (!b(context)) {
                return "";
            }
            if (context != null) {
                this.a = d4.a(context, "dcloud-ads", "oaid");
            }
        }
        return this.a;
    }

    public static l3 a() {
        if (b == null) {
            synchronized (l3.class) {
                if (b == null) {
                    b = new l3();
                }
            }
        }
        return b;
    }

    private int a(Context context) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls;
        try {
            Class<?> cls2 = Class.forName("com.bun.miitmdid.core.MdidSdkHelper");
            try {
                cls = Class.forName("com.bun.supplier.IIdentifierListener");
            } catch (Exception unused) {
                cls = Class.forName("com.bun.miitmdid.interfaces.IIdentifierListener");
            }
            Object objNewProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{cls}, new a(context));
            Method declaredMethod = cls2.getDeclaredMethod("InitSdk", Context.class, Boolean.TYPE, cls);
            Object objInvoke = null;
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                objInvoke = declaredMethod.invoke(null, context, Boolean.TRUE, objNewProxyInstance);
            }
            if (objInvoke instanceof Integer) {
                return ((Integer) objInvoke).intValue();
            }
        } catch (ClassNotFoundException | Exception unused2) {
        }
        return ErrorCode.INIT_HELPER_CALL_ERROR;
    }

    public static Object a(Object obj, String str, Class[] clsArr, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            if (objArr.length == 0) {
                objArr = null;
            }
            return method.invoke(obj, objArr);
        } catch (Throwable unused) {
            return null;
        }
    }
}
