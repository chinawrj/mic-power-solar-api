package com.taobao.weex.common;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
/* loaded from: classes.dex */
public @interface WXModuleAnno {
    @Deprecated
    boolean moduleMethod() default true;

    boolean runOnUIThread() default true;
}
