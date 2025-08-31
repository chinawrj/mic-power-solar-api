package io.dcloud.feature.uniapp.dom;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.facebook.common.util.UriUtil;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXImageSharpen;
import com.taobao.weex.dom.WXImageQuality;
import com.taobao.weex.dom.binding.ELUtils;
import com.taobao.weex.dom.binding.WXStatement;
import com.taobao.weex.el.parse.Parser;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class AbsAttr implements Map<String, Object>, Cloneable {
    private static final long serialVersionUID = -2619357510079360946L;
    private Map<String, Object> attr;
    private ArrayMap<String, Object> mBindingAttrs;
    private WXStatement mStatement;
    private Map<String, Object> writeAttr;

    public AbsAttr() {
        this.attr = new HashMap();
    }

    private boolean addBindingAttrIfStatement(String str, Object obj) {
        for (String str2 : ELUtils.EXCLUDES_BINDING) {
            if (str.equals(str2)) {
                return false;
            }
        }
        if (ELUtils.isBinding(obj)) {
            if (this.mBindingAttrs == null) {
                this.mBindingAttrs = new ArrayMap<>();
            }
            this.mBindingAttrs.put(str, ELUtils.bindingBlock(obj));
            return true;
        }
        if (WXStatement.WX_IF.equals(str)) {
            if (this.mStatement == null) {
                this.mStatement = new WXStatement();
            }
            if (obj != null) {
                this.mStatement.put(str, Parser.parse(obj.toString()));
            }
            return true;
        }
        if (WXStatement.WX_FOR.equals(str)) {
            if (this.mStatement == null) {
                this.mStatement = new WXStatement();
            }
            Object objVforBlock = ELUtils.vforBlock(obj);
            if (objVforBlock != null) {
                this.mStatement.put(str, objVforBlock);
                return true;
            }
        }
        if (WXStatement.WX_ONCE.equals(str)) {
            if (this.mStatement == null) {
                this.mStatement = new WXStatement();
            }
            this.mStatement.put(str, Boolean.TRUE);
        }
        return false;
    }

    private Map<String, Object> filterStatementsFromAttrs(Map map) {
        if (map != null && map.size() != 0) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (ELUtils.COMPONENT_PROPS.equals(entry.getKey())) {
                    entry.setValue(ELUtils.bindingBlock(entry.getValue()));
                } else if (addBindingAttrIfStatement((String) entry.getKey(), entry.getValue())) {
                    it.remove();
                }
            }
        }
        return map;
    }

    public static String getPrefix(Map<String, Object> map) {
        Object obj;
        if (map == null || (obj = map.get(Constants.Name.PREFIX)) == null) {
            return null;
        }
        return obj.toString();
    }

    public static String getSuffix(Map<String, Object> map) {
        Object obj;
        if (map == null || (obj = map.get(Constants.Name.SUFFIX)) == null) {
            return null;
        }
        return obj.toString();
    }

    public static String getValue(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Object obj = map.get("value");
        if (obj == null && (obj = map.get(UriUtil.LOCAL_CONTENT_SCHEME)) == null) {
            return null;
        }
        return obj.toString();
    }

    public boolean autoPlay() {
        Object obj = get(Constants.Name.AUTO_PLAY);
        if (obj == null) {
            return false;
        }
        try {
            return Boolean.parseBoolean(String.valueOf(obj));
        } catch (Exception e) {
            WXLogUtils.e("[WXAttr] autoPlay:", e);
            return false;
        }
    }

    public boolean canRecycled() {
        Object obj = get(Constants.Name.RECYCLE);
        if (obj == null) {
            return true;
        }
        try {
            return Boolean.parseBoolean(String.valueOf(obj));
        } catch (Exception e) {
            WXLogUtils.e("[WXAttr] recycle:", e);
            return true;
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.attr.clear();
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract AbsAttr mo239clone();

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.attr.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.attr.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.attr.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.attr.equals(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        Object obj2;
        Map<String, Object> map = this.writeAttr;
        return (map == null || (obj2 = map.get(obj)) == null) ? this.attr.get(obj) : obj2;
    }

    public Map<String, Object> getAttr() {
        return this.attr;
    }

    public ArrayMap<String, Object> getBindingAttrs() {
        return this.mBindingAttrs;
    }

    public int getColumnCount() throws NumberFormatException {
        Object obj = get(Constants.Name.COLUMN_COUNT);
        if (obj == null) {
            return -1;
        }
        String strValueOf = String.valueOf(obj);
        if ("auto".equals(strValueOf)) {
            return -1;
        }
        try {
            int i = Integer.parseInt(strValueOf);
            if (i > 0) {
                return i;
            }
            return -1;
        } catch (Exception e) {
            WXLogUtils.e("[WXAttr] getColumnCount:", e);
            return -1;
        }
    }

    public float getColumnGap() throws NumberFormatException {
        Object obj = get(Constants.Name.COLUMN_GAP);
        if (obj == null) {
            return 32.0f;
        }
        String strValueOf = String.valueOf(obj);
        if ("normal".equals(strValueOf)) {
            return 32.0f;
        }
        try {
            float f = Float.parseFloat(strValueOf);
            if (f >= 0.0f) {
                return f;
            }
            return -1.0f;
        } catch (Exception e) {
            WXLogUtils.e("[WXAttr] getColumnGap:", e);
            return 32.0f;
        }
    }

    public float getColumnWidth() throws NumberFormatException {
        Object obj = get(Constants.Name.COLUMN_WIDTH);
        if (obj == null) {
            return -1.0f;
        }
        String strValueOf = String.valueOf(obj);
        if ("auto".equals(strValueOf)) {
            return -1.0f;
        }
        try {
            float f = Float.parseFloat(strValueOf);
            if (f > 0.0f) {
                return f;
            }
            return 0.0f;
        } catch (Exception e) {
            WXLogUtils.e("[WXAttr] getColumnWidth:", e);
            return -1.0f;
        }
    }

    public float getElevation(float f) {
        Object obj = get(Constants.Name.ELEVATION);
        if (obj == null) {
            return Float.NaN;
        }
        String string = obj.toString();
        if (TextUtils.isEmpty(string)) {
            return 0.0f;
        }
        return WXViewUtils.getRealSubPxByWidth(WXUtils.getFloat(string), f);
    }

    public WXImageQuality getImageQuality() {
        String str = Constants.Name.QUALITY;
        if (!containsKey(Constants.Name.QUALITY)) {
            str = Constants.Name.IMAGE_QUALITY;
        }
        Object obj = get(str);
        WXImageQuality wXImageQuality = WXImageQuality.AUTO;
        if (obj == null) {
            return wXImageQuality;
        }
        String string = obj.toString();
        if (TextUtils.isEmpty(string)) {
            return wXImageQuality;
        }
        try {
            return WXImageQuality.valueOf(string.toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            WXLogUtils.e("Image", "Invalid value image quality. Only low, normal, high, original are valid");
            return wXImageQuality;
        }
    }

    public WXImageSharpen getImageSharpen() {
        Object obj = get(Constants.Name.SHARPEN);
        if (obj == null) {
            obj = get(Constants.Name.IMAGE_SHARPEN);
        }
        if (obj == null) {
            return WXImageSharpen.UNSHARPEN;
        }
        return obj.toString().equals(Constants.Name.SHARPEN) ? WXImageSharpen.SHARPEN : WXImageSharpen.UNSHARPEN;
    }

    public String getImageSrc() {
        Object obj = get("src");
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public boolean getIsRecycleImage() {
        Object obj = get(Constants.Name.RECYCLE_IMAGE);
        if (obj == null) {
            return true;
        }
        try {
            return Boolean.parseBoolean(String.valueOf(obj));
        } catch (Exception e) {
            WXLogUtils.e("[WXAttr] recycleImage:", e);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getLayoutType() {
        /*
            r4 = this;
            java.lang.String r0 = "layout"
            java.lang.Object r0 = r4.get(r0)
            r1 = 1
            if (r0 != 0) goto La
            return r1
        La:
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: java.lang.Exception -> L3b
            int r2 = r0.hashCode()     // Catch: java.lang.Exception -> L3b
            r3 = 3181382(0x308b46, float:4.458066E-39)
            if (r2 == r3) goto L27
            r3 = 674874986(0x2839c66a, float:1.0312587E-14)
            if (r2 == r3) goto L1d
            goto L31
        L1d:
            java.lang.String r2 = "multi-column"
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Exception -> L3b
            if (r0 == 0) goto L31
            r0 = 0
            goto L32
        L27:
            java.lang.String r2 = "grid"
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Exception -> L3b
            if (r0 == 0) goto L31
            r0 = r1
            goto L32
        L31:
            r0 = -1
        L32:
            if (r0 == 0) goto L39
            if (r0 == r1) goto L37
            return r1
        L37:
            r0 = 2
            return r0
        L39:
            r0 = 3
            return r0
        L3b:
            r0 = move-exception
            java.lang.String r2 = "[WXAttr] getLayoutType:"
            com.taobao.weex.utils.WXLogUtils.e(r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.uniapp.dom.AbsAttr.getLayoutType():int");
    }

    public String getLoadMoreOffset() {
        Object obj = get("loadmoreoffset");
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public String getLoadMoreRetry() {
        Object obj = get(Constants.Name.LOADMORERETRY);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public int getOrientation() {
        String scrollDirection = getScrollDirection();
        if (!TextUtils.isEmpty(scrollDirection) && scrollDirection.equals(Constants.Value.HORIZONTAL)) {
            return 0;
        }
        Object obj = get("orientation");
        return (obj == null || !Constants.Value.HORIZONTAL.equals(obj.toString())) ? 1 : 0;
    }

    public String getScope() {
        Object obj = get(Constants.Name.SCOPE);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public String getScrollDirection() {
        Object obj = get(Constants.Name.SCROLL_DIRECTION);
        return obj == null ? "vertical" : obj.toString();
    }

    public WXStatement getStatement() {
        return this.mStatement;
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.attr.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.attr.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.attr.keySet();
    }

    public void mergeAttr() {
        Map<String, Object> map = this.writeAttr;
        if (map != null) {
            this.attr.putAll(map);
            this.writeAttr = null;
        }
    }

    public String optString(String str) {
        if (!containsKey(str)) {
            return "";
        }
        Object obj = get(str);
        return obj instanceof String ? (String) obj : obj != null ? String.valueOf(obj) : "";
    }

    public void parseStatements() {
        Map<String, Object> map = this.attr;
        if (map != null) {
            this.attr = filterStatementsFromAttrs(map);
        }
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        if (this.writeAttr == null) {
            this.writeAttr = new ArrayMap();
        }
        this.writeAttr.putAll(map);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.attr.remove(obj);
    }

    public void setBindingAttrs(ArrayMap<String, Object> arrayMap) {
        this.mBindingAttrs = arrayMap;
    }

    public void setStatement(WXStatement wXStatement) {
        this.mStatement = wXStatement;
    }

    public boolean showIndicators() {
        Object obj = get(Constants.Name.SHOW_INDICATORS);
        if (obj == null) {
            return true;
        }
        try {
            return Boolean.parseBoolean(String.valueOf(obj));
        } catch (Exception e) {
            WXLogUtils.e("[WXAttr] showIndicators:", e);
            return true;
        }
    }

    @Override // java.util.Map
    public int size() {
        return this.attr.size();
    }

    public void skipFilterPutAll(Map<String, Object> map) {
        this.attr.putAll(map);
    }

    public String toString() {
        return this.attr.toString();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.attr.values();
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        if (addBindingAttrIfStatement(str, obj)) {
            return null;
        }
        return this.attr.put(str, obj);
    }

    public AbsAttr(Map<String, Object> map) {
        this.attr = map;
    }

    public AbsAttr(Map<String, Object> map, int i) {
        this.attr = map;
    }
}
