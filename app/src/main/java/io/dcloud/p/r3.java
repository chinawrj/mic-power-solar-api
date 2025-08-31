package io.dcloud.p;

import io.dcloud.common.DHInterface.IPdrModulesInfo;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class r3 implements IPdrModulesInfo {
    @Override // io.dcloud.common.DHInterface.IPdrModulesInfo
    public Map getPdrModuleMap() {
        HashMap map = new HashMap();
        map.put("commit", e0.class);
        return map;
    }
}
