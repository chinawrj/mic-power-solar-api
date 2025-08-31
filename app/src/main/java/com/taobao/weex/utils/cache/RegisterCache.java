package com.taobao.weex.utils.cache;

import com.taobao.weex.bridge.ModuleFactory;
import com.taobao.weex.bridge.WXModuleManager;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.ui.WXComponentRegistry;
import com.taobao.weex.ui.config.AutoScanConfigRegister;
import com.taobao.weex.utils.WXLogUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class RegisterCache {
    private static RegisterCache registerCache;
    private static Map<String, ModuleCache> moduleCacheMap = new ConcurrentHashMap();
    private static Map<String, ComponentCache> componentCacheMap = new ConcurrentHashMap();
    private boolean enable = false;
    private boolean enableAutoScan = true;
    private volatile boolean finished = false;
    private volatile int doNotCacheSize = Integer.MAX_VALUE;

    public class ComponentCache {
        public final Map<String, Object> componentInfo;
        public final IFComponentHolder holder;
        public final String type;

        ComponentCache(String str, IFComponentHolder iFComponentHolder, Map<String, Object> map) {
            this.type = str;
            this.componentInfo = map;
            this.holder = iFComponentHolder;
        }
    }

    public class ModuleCache {
        public final ModuleFactory factory;
        public final boolean global;
        public final String name;

        ModuleCache(String str, ModuleFactory moduleFactory, boolean z) {
            this.name = str;
            this.factory = moduleFactory;
            this.global = z;
        }
    }

    private RegisterCache() {
    }

    private void CacheComponentRegister() {
        if (componentCacheMap.isEmpty()) {
            return;
        }
        WXComponentRegistry.registerComponent(componentCacheMap);
    }

    private void CacheModuleRegister() {
        if (moduleCacheMap.isEmpty()) {
            return;
        }
        WXModuleManager.registerModule(moduleCacheMap);
    }

    private boolean canCache() {
        return enableCache() && !this.finished && getDoNotCacheSize() < 1;
    }

    private boolean enableCache() {
        return this.enable;
    }

    private int getDoNotCacheSize() {
        int i = this.doNotCacheSize;
        this.doNotCacheSize = i - 1;
        return i;
    }

    public static RegisterCache getInstance() {
        if (registerCache == null) {
            synchronized (RegisterCache.class) {
                if (registerCache == null) {
                    registerCache = new RegisterCache();
                }
            }
        }
        return registerCache;
    }

    public boolean cacheComponent(String str, IFComponentHolder iFComponentHolder, Map<String, Object> map) {
        if (!canCache()) {
            return false;
        }
        try {
            componentCacheMap.put(str, new ComponentCache(str, iFComponentHolder, map));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean cacheModule(String str, ModuleFactory moduleFactory, boolean z) {
        if (!canCache()) {
            return false;
        }
        try {
            moduleCacheMap.put(str, new ModuleCache(str, moduleFactory, z));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean enableAutoScan() {
        return this.enableAutoScan;
    }

    public boolean idle(boolean z) {
        if (this.finished) {
            return true;
        }
        WXLogUtils.e((z ? "idle from create instance" : "idle from external") + " cache size is " + (moduleCacheMap.size() + componentCacheMap.size()));
        this.finished = true;
        CacheComponentRegister();
        CacheModuleRegister();
        return true;
    }

    public void setDoNotCacheSize(int i) {
        this.doNotCacheSize = i;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public void setEnableAutoScan(boolean z) {
        if (this.enableAutoScan != z) {
            if (z) {
                AutoScanConfigRegister.doScanConfig();
            }
            this.enableAutoScan = z;
        }
    }
}
