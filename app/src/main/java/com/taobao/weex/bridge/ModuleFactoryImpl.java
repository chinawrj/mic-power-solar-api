package com.taobao.weex.bridge;

/* loaded from: classes.dex */
public class ModuleFactoryImpl {
    public boolean hasRigster;
    public ModuleFactory mFactory;

    public ModuleFactoryImpl(ModuleFactory moduleFactory) {
        this.hasRigster = false;
        this.mFactory = moduleFactory;
    }

    public ModuleFactoryImpl(ModuleFactory moduleFactory, boolean z) {
        this.mFactory = moduleFactory;
        this.hasRigster = z;
    }
}
