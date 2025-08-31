package io.dcloud.feature.uniapp.dom;

import androidx.collection.ArrayMap;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.dom.binding.ELUtils;
import com.taobao.weex.dom.binding.JSONUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class AbsEvent extends ArrayList<String> implements Serializable, Cloneable {
    public static final String EVENT_KEY_ARGS = "params";
    public static final String EVENT_KEY_TYPE = "type";
    private static final long serialVersionUID = -8186587029452440107L;
    private ArrayMap mEventBindingArgs;
    private ArrayMap<String, List<Object>> mEventBindingArgsValues;

    private void addBindingArgsEvent(String str, Object obj) {
        if (!contains(str)) {
            add(str);
        }
        if (obj != null) {
            if (this.mEventBindingArgs == null) {
                this.mEventBindingArgs = new ArrayMap();
            }
            this.mEventBindingArgs.put(str, ELUtils.bindingBlock(obj));
        }
    }

    private String addBindingEvent(JSONObject jSONObject) {
        String string = jSONObject.getString("type");
        Object obj = jSONObject.get("params");
        if (string != null) {
            addBindingArgsEvent(string, obj);
        }
        return string;
    }

    public static String getEventName(Object obj) {
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).getString("type");
        }
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public void addEvent(Object obj) {
        if (!(obj instanceof CharSequence)) {
            if (obj instanceof JSONObject) {
                addBindingEvent((JSONObject) obj);
            }
        } else {
            if (JSONUtils.isJSON(obj.toString())) {
                addEvent(JSONUtils.toJSON(obj.toString()));
                return;
            }
            String string = obj.toString();
            if (contains(string)) {
                return;
            }
            add(string);
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        ArrayMap arrayMap = this.mEventBindingArgs;
        if (arrayMap != null) {
            arrayMap.clear();
        }
        ArrayMap<String, List<Object>> arrayMap2 = this.mEventBindingArgsValues;
        if (arrayMap2 != null) {
            arrayMap2.clear();
        }
        super.clear();
    }

    @Override // java.util.ArrayList
    public abstract AbsEvent clone();

    public ArrayMap getEventBindingArgs() {
        return this.mEventBindingArgs;
    }

    public ArrayMap<String, List<Object>> getEventBindingArgsValues() {
        return this.mEventBindingArgsValues;
    }

    public void parseStatements() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < size(); i++) {
            String str = get(i);
            if (JSONUtils.isJSON(str)) {
                set(i, addBindingEvent(JSONUtils.toJSON(str)));
            }
        }
    }

    public void putEventBindingArgsValue(String str, List<Object> list) {
        if (this.mEventBindingArgsValues == null) {
            this.mEventBindingArgsValues = new ArrayMap<>();
        }
        if (list == null) {
            this.mEventBindingArgsValues.remove(str);
        } else {
            this.mEventBindingArgsValues.put(str, list);
        }
    }

    public boolean remove(String str) {
        ArrayMap arrayMap = this.mEventBindingArgs;
        if (arrayMap != null) {
            arrayMap.remove(str);
        }
        ArrayMap<String, List<Object>> arrayMap2 = this.mEventBindingArgsValues;
        if (arrayMap2 != null) {
            arrayMap2.remove(str);
        }
        return super.remove((Object) str);
    }

    public void setEventBindingArgs(ArrayMap arrayMap) {
        this.mEventBindingArgs = arrayMap;
    }

    public void setEventBindingArgsValues(ArrayMap<String, List<Object>> arrayMap) {
        this.mEventBindingArgsValues = arrayMap;
    }
}
