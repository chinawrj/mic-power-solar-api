package com.alibaba.fastjson.serializer;

import com.taobao.weex.el.parse.Operators;

/* loaded from: classes.dex */
public class SerialContext {
    public final int features;
    public final Object fieldName;
    public final Object object;
    public final SerialContext parent;

    public SerialContext(SerialContext serialContext, Object obj, Object obj2, int i, int i2) {
        this.parent = serialContext;
        this.object = obj;
        this.fieldName = obj2;
        this.features = i;
    }

    public String toString() {
        if (this.parent == null) {
            return Operators.DOLLAR_STR;
        }
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    protected void toString(StringBuilder sb) {
        SerialContext serialContext = this.parent;
        if (serialContext == null) {
            sb.append('$');
            return;
        }
        serialContext.toString(sb);
        Object obj = this.fieldName;
        if (obj == null) {
            sb.append(".null");
            return;
        }
        if (obj instanceof Integer) {
            sb.append(Operators.ARRAY_START);
            sb.append(((Integer) this.fieldName).intValue());
            sb.append(Operators.ARRAY_END);
            return;
        }
        sb.append(Operators.DOT);
        String string = this.fieldName.toString();
        for (int i = 0; i < string.length(); i++) {
            char cCharAt = string.charAt(i);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < 'a' || cCharAt > 'z') && cCharAt <= 128))) {
                for (int i2 = 0; i2 < string.length(); i2++) {
                    char cCharAt2 = string.charAt(i2);
                    if (cCharAt2 == '\\') {
                        sb.append('\\');
                        sb.append('\\');
                        sb.append('\\');
                    } else if ((cCharAt2 >= '0' && cCharAt2 <= '9') || ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || ((cCharAt2 >= 'a' && cCharAt2 <= 'z') || cCharAt2 > 128))) {
                        sb.append(cCharAt2);
                    } else if (cCharAt2 == '\"') {
                        sb.append('\\');
                        sb.append('\\');
                        sb.append('\\');
                    } else {
                        sb.append('\\');
                        sb.append('\\');
                    }
                    sb.append(cCharAt2);
                }
                return;
            }
        }
        sb.append(string);
    }

    public SerialContext getParent() {
        return this.parent;
    }

    public Object getObject() {
        return this.object;
    }

    public Object getFieldName() {
        return this.fieldName;
    }

    public String getPath() {
        return toString();
    }
}
