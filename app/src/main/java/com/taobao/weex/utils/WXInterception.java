package com.taobao.weex.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes3.dex */
public class WXInterception {

    private interface Intercepted {
    }

    public static abstract class InterceptionHandler<T> implements InvocationHandler {
        private T mDelegate;

        protected T delegate() {
            return this.mDelegate;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                return method.invoke(delegate(), objArr);
            } catch (IllegalAccessException e) {
                WXLogUtils.e("", e);
                return null;
            } catch (IllegalArgumentException e2) {
                WXLogUtils.e("", e2);
                return null;
            } catch (InvocationTargetException e3) {
                throw e3.getTargetException();
            }
        }

        void setDelegate(T t) {
            this.mDelegate = t;
        }
    }

    private WXInterception() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T proxy(Object obj, Class<T> cls, InterceptionHandler<T> interceptionHandler) {
        if (obj instanceof Intercepted) {
            return obj;
        }
        interceptionHandler.setDelegate(obj);
        return (T) Proxy.newProxyInstance(WXInterception.class.getClassLoader(), new Class[]{cls, Intercepted.class}, interceptionHandler);
    }

    public static <T> T proxy(Object obj, InterceptionHandler<T> interceptionHandler, Class<?>... clsArr) {
        interceptionHandler.setDelegate(obj);
        return (T) Proxy.newProxyInstance(WXInterception.class.getClassLoader(), clsArr, interceptionHandler);
    }
}
