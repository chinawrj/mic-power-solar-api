package com.taobao.weex.dom;

import androidx.collection.ArrayMap;
import io.dcloud.feature.uniapp.dom.AbsStyle;
import java.util.Map;

/* loaded from: classes.dex */
public class WXStyle extends AbsStyle {
    public WXStyle() {
    }

    public WXStyle(Map<String, Object> map) {
        super(map);
    }

    public WXStyle(Map<String, Object> map, boolean z) {
        super(map, z);
    }

    @Override // io.dcloud.feature.uniapp.dom.AbsStyle
    /* renamed from: clone */
    public WXStyle mo240clone() {
        WXStyle wXStyle = new WXStyle();
        wXStyle.mStyles.putAll(this.mStyles);
        if (this.mBindingStyle != null) {
            wXStyle.mBindingStyle = new ArrayMap<>(this.mBindingStyle);
        }
        if (this.mPesudoStyleMap != null) {
            wXStyle.mPesudoStyleMap = new ArrayMap();
            for (Map.Entry<String, Map<String, Object>> entry : this.mPesudoStyleMap.entrySet()) {
                ArrayMap arrayMap = new ArrayMap();
                arrayMap.putAll(entry.getValue());
                wXStyle.mPesudoStyleMap.put(entry.getKey(), arrayMap);
            }
        }
        if (this.mPesudoResetStyleMap != null) {
            ArrayMap arrayMap2 = new ArrayMap();
            wXStyle.mPesudoResetStyleMap = arrayMap2;
            arrayMap2.putAll(this.mPesudoResetStyleMap);
        }
        return wXStyle;
    }
}
