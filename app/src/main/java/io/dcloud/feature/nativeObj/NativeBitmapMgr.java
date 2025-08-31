package io.dcloud.feature.nativeObj;

import android.graphics.Color;
import android.text.TextUtils;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.INativeBitmap;
import io.dcloud.common.DHInterface.INativeView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.feature.nativeObj.richtext.RichTextLayout;
import io.dcloud.feature.uniapp.adapter.AbsURIAdapter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class NativeBitmapMgr {
    private HashMap<String, INativeBitmap> mSnaps = new HashMap<>();
    private HashMap<String, String> mIds = new HashMap<>();
    protected LinkedHashMap<String, NativeView> mNativeViews = new LinkedHashMap<>();
    public final String SUCCESS_INFO = "{path:'file://%s', w:%d, h:%d, size:%d}";

    /* renamed from: io.dcloud.feature.nativeObj.NativeBitmapMgr$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action;

        static {
            int[] iArr = new int[Action.values().length];
            $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action = iArr;
            try {
                iArr[Action.View.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.setStyle.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.addEventListener.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.interceptTouchEvent.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.setTouchEventRect.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.getViewById.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.evalWeexJS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.drawRichText.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.drawBitmap.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.drawText.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.drawInput.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.getInputValueById.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.getInputFocusById.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.setInputFocusById.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.show.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.hide.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.view_close.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.view_animate.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.view_reset.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.view_restore.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.view_drawRect.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.isVisible.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.Bitmap.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.getItems.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.getBitmapById.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.clear.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.bitmapRecycle.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.load.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.loadBase64Data.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.save.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.toBase64Data.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.startAnimation.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.clearAnimation.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.view_clearRect.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.view_draw.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.setImages.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.addImages.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$io$dcloud$feature$nativeObj$NativeBitmapMgr$Action[Action.currentImageIndex.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
        }
    }

    protected enum Action {
        Bitmap,
        getItems,
        getBitmapById,
        clear,
        load,
        loadBase64Data,
        save,
        toBase64Data,
        View,
        startAnimation,
        clearAnimation,
        getViewById,
        drawBitmap,
        drawText,
        evalWeexJS,
        drawRichText,
        show,
        hide,
        setImages,
        addImages,
        view_animate,
        view_reset,
        view_restore,
        view_drawRect,
        isVisible,
        addEventListener,
        interceptTouchEvent,
        setTouchEventRect,
        bitmapRecycle,
        setStyle,
        view_clearRect,
        view_draw,
        view_close,
        currentImageIndex,
        drawInput,
        getInputValueById,
        getInputFocusById,
        setInputFocusById
    }

    private void createBitmap(IApp iApp, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "" + System.currentTimeMillis();
        }
        if (this.mSnaps.containsKey(str)) {
            return;
        }
        this.mSnaps.put(str, new NativeBitmap(iApp, str2, str, str3));
        this.mIds.put(str2, str);
    }

    private NativeView getNativeView(String str, String str2) {
        NativeView nativeView = this.mNativeViews.get(str2);
        if (nativeView != null || TextUtils.isEmpty(str)) {
            return nativeView;
        }
        for (NativeView nativeView2 : this.mNativeViews.values()) {
            if (TextUtils.equals(nativeView2.mID, str)) {
                return nativeView2;
            }
        }
        return nativeView;
    }

    private void load(final IWebview iWebview, NativeBitmap nativeBitmap, String str, final String str2) {
        nativeBitmap.load(iWebview, iWebview.obtainFrameView().obtainMainView().getContext(), str, TextUtils.isEmpty(str2) ? null : new ICallBack() { // from class: io.dcloud.feature.nativeObj.NativeBitmapMgr.1
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                Deprecated_JSUtil.execCallback(iWebview, str2, null, JSUtil.OK, false, false);
                return null;
            }
        }, TextUtils.isEmpty(str2) ? null : new ICallBack() { // from class: io.dcloud.feature.nativeObj.NativeBitmapMgr.2
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                String string = obj == null ? iWebview.getContext().getString(R.string.dcloud_native_obj_load_failed) : obj.toString();
                Deprecated_JSUtil.execCallback(iWebview, str2, "{\"code\":-100,\"message\":\"" + string + "\"}", JSUtil.ERROR, true, false);
                return null;
            }
        });
    }

    private void loadBase64Data(final IWebview iWebview, NativeBitmap nativeBitmap, String str, final String str2) {
        nativeBitmap.loadBase64Data(str, TextUtils.isEmpty(str2) ? null : new ICallBack() { // from class: io.dcloud.feature.nativeObj.NativeBitmapMgr.3
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                Deprecated_JSUtil.execCallback(iWebview, str2, null, JSUtil.OK, false, false);
                return null;
            }
        }, TextUtils.isEmpty(str2) ? null : new ICallBack() { // from class: io.dcloud.feature.nativeObj.NativeBitmapMgr.4
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                Deprecated_JSUtil.execCallback(iWebview, str2, "{\"code\":-100,\"message\":\"" + iWebview.getContext().getString(R.string.dcloud_native_obj_load_failed) + "\"}", JSUtil.ERROR, true, false);
                return null;
            }
        });
    }

    private void save(final IWebview iWebview, NativeBitmap nativeBitmap, String str, JSONObject jSONObject, final String str2) {
        nativeBitmap.save(iWebview.obtainFrameView().obtainApp(), str, new NativeBitmapSaveOptions(jSONObject.toString()), iWebview.getScale(), TextUtils.isEmpty(str2) ? null : new ICallBack() { // from class: io.dcloud.feature.nativeObj.NativeBitmapMgr.5
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                String str3;
                if (obj == null || !(obj instanceof NativeBitmapSaveOptions)) {
                    str3 = null;
                } else {
                    NativeBitmapSaveOptions nativeBitmapSaveOptions = (NativeBitmapSaveOptions) obj;
                    str3 = StringUtil.format("{path:'file://%s', w:%d, h:%d, size:%d}", nativeBitmapSaveOptions.path, Integer.valueOf(nativeBitmapSaveOptions.width), Integer.valueOf(nativeBitmapSaveOptions.height), Long.valueOf(nativeBitmapSaveOptions.size));
                }
                Deprecated_JSUtil.execCallback(iWebview, str2, str3, JSUtil.OK, true, false);
                return null;
            }
        }, TextUtils.isEmpty(str2) ? null : new ICallBack() { // from class: io.dcloud.feature.nativeObj.NativeBitmapMgr.6
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                Deprecated_JSUtil.execCallback(iWebview, str2, "{\"code\":-100,\"message\":\"\"+webview.getContext().getString(R.string.dcloud_native_obj_load_failed)+\"\"}", JSUtil.ERROR, true, false);
                return null;
            }
        });
    }

    public void destroy() {
        try {
            Iterator<INativeBitmap> it = this.mSnaps.values().iterator();
            while (it.hasNext()) {
                try {
                    it.next().clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.mIds.clear();
            this.mSnaps.clear();
            for (NativeView nativeView : this.mNativeViews.values()) {
                if (nativeView != null) {
                    nativeView.clearNativeViewData();
                }
            }
            this.mNativeViews.clear();
            NativeTypefaceFactory.clearCache();
            System.gc();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void destroyNativeView(NativeView nativeView) {
        NativeView nativeView2 = this.mNativeViews.get(nativeView.mUUID);
        nativeView.clearNativeViewData();
        if (nativeView2 == nativeView) {
            this.mNativeViews.remove(nativeView.mUUID);
        } else {
            this.mNativeViews.remove(nativeView.mID);
        }
    }

    public Object doForFeature(String str, Object obj) throws JSONException {
        NativeView nativeView;
        if ("addNativeView".equals(str)) {
            Object[] objArr = (Object[]) obj;
            IFrameView iFrameView = (IFrameView) objArr[0];
            String str2 = (String) objArr[1];
            NativeView nativeView2 = getNativeView(str2, str2);
            Logger.d("adadad", "addNativeView outter" + nativeView2);
            if (nativeView2 == null) {
                return null;
            }
            Logger.d("adadad", "addNativeView inner" + iFrameView);
            nativeView2.attachToViewGroup(iFrameView);
            return null;
        }
        if ("removeNativeView".equals(str)) {
            String str3 = (String) ((Object[]) obj)[1];
            NativeView nativeView3 = getNativeView(str3, str3);
            if (nativeView3 == null) {
                return null;
            }
            nativeView3.removeFromViewGroup();
            return null;
        }
        if ("getNativeView".equals(str)) {
            try {
                Object[] objArr2 = (Object[]) obj;
                String str4 = (String) objArr2[1];
                return getNativeView(str4, str4);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if ("makeRichText".equals(str)) {
            return RichTextLayout.makeRichText((Object[]) obj);
        }
        if ("View".equals(str)) {
            try {
                Object[] objArr3 = (Object[]) obj;
                IWebview iWebview = (IWebview) objArr3[1];
                String str5 = (String) objArr3[2];
                String str6 = (String) objArr3[3];
                JSONObject jSONObject = (JSONObject) objArr3[4];
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                JSONObject jSONObject2 = jSONObject;
                JSONArray jSONArray = objArr3.length > 5 ? (JSONArray) objArr3[5] : null;
                String str7 = AbsoluteConst.NATIVE_NVIEW;
                if (objArr3.length > 6) {
                    str7 = (String) objArr3[6];
                }
                if (this.mNativeViews.containsKey(str6)) {
                    return null;
                }
                NativeView nativeImageSlider = str7.equals(AbsoluteConst.NATIVE_IMAGESLIDER) ? new NativeImageSlider(iWebview.getContext(), iWebview, str6, str5, jSONObject2) : AbsoluteConst.NATIVE_TITLE_N_VIEW.equals(str7) ? new TitleNView(iWebview.getContext(), iWebview, str6, str5, jSONObject2) : new NativeView(iWebview.getContext(), iWebview, str6, str5, jSONObject2);
                this.mNativeViews.put(nativeImageSlider.mUUID, nativeImageSlider);
                initViewDrawItme(iWebview, nativeImageSlider, jSONArray);
                return nativeImageSlider;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        if (!"updateSubNViews".equals(str)) {
            return null;
        }
        Object[] objArr4 = (Object[]) obj;
        IWebview iWebview2 = (IWebview) objArr4[1];
        JSONArray jSONArray2 = (JSONArray) objArr4[2];
        AdaFrameView adaFrameView = (AdaFrameView) iWebview2.obtainFrameView();
        for (int i = 0; i < jSONArray2.length(); i++) {
            try {
                JSONObject jSONObject3 = jSONArray2.getJSONObject(i);
                String strOptString = jSONObject3.optString("id");
                if (!PdrUtil.isEmpty(strOptString)) {
                    Iterator<INativeView> it = adaFrameView.mChildNativeViewList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            nativeView = null;
                            break;
                        }
                        INativeView next = it.next();
                        if (strOptString.equals(next.getViewId())) {
                            nativeView = (NativeView) next;
                            break;
                        }
                    }
                    if (nativeView != null) {
                        JSONArray jSONArrayOptJSONArray = jSONObject3.optJSONArray("tags");
                        nativeView.setStyle(jSONObject3.optJSONObject("styles"), jSONArrayOptJSONArray == null);
                        initViewDrawItme(iWebview2, nativeView, jSONArrayOptJSONArray);
                    }
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x064e A[Catch: Exception -> 0x0678, TryCatch #5 {Exception -> 0x0678, blocks: (B:88:0x01ed, B:89:0x01f2, B:91:0x0202, B:92:0x0207, B:94:0x0217, B:95:0x021c, B:97:0x0228, B:98:0x022d, B:99:0x0256, B:101:0x0262, B:107:0x0271, B:111:0x0282, B:113:0x0285, B:122:0x02a5, B:116:0x028e, B:118:0x0294, B:120:0x029a, B:123:0x02ae, B:125:0x02ba, B:127:0x02c0, B:131:0x02c8, B:132:0x02d1, B:133:0x02da, B:135:0x02e6, B:137:0x02f0, B:138:0x02f7, B:140:0x02fd, B:148:0x030f, B:150:0x0317, B:152:0x031d, B:154:0x0326, B:158:0x0334, B:157:0x032f, B:146:0x0307, B:159:0x0349, B:161:0x0355, B:162:0x035a, B:164:0x0366, B:165:0x036b, B:167:0x0377, B:168:0x0380, B:170:0x038c, B:171:0x0396, B:173:0x03a2, B:175:0x03ad, B:177:0x03b1, B:178:0x03bb, B:180:0x03c7, B:183:0x03e4, B:185:0x03ec, B:188:0x03f6, B:189:0x03f9, B:190:0x03fe, B:198:0x0420, B:204:0x043c, B:210:0x0454, B:212:0x045e, B:216:0x046d, B:223:0x0483, B:218:0x0476, B:214:0x0467, B:224:0x0492, B:227:0x04a2, B:231:0x04b1, B:234:0x04c0, B:233:0x04ba, B:229:0x04ab, B:235:0x04d8, B:237:0x04e4, B:239:0x04ec, B:241:0x04f2, B:245:0x0501, B:248:0x0511, B:247:0x050b, B:243:0x04fb, B:249:0x0521, B:251:0x052f, B:253:0x0535, B:257:0x0544, B:260:0x0554, B:259:0x054e, B:255:0x053e, B:261:0x055e, B:262:0x0597, B:264:0x059e, B:266:0x05a3, B:268:0x05a9, B:269:0x05b3, B:271:0x05bf, B:273:0x05c7, B:275:0x05cd, B:276:0x05d2, B:278:0x05de, B:279:0x05e9, B:281:0x05f5, B:282:0x05fe, B:283:0x0604, B:286:0x0611, B:288:0x0617, B:285:0x060c, B:289:0x061c, B:291:0x0627, B:292:0x0629, B:295:0x0638, B:297:0x063b, B:300:0x0646, B:302:0x064e, B:304:0x0654, B:306:0x066d, B:305:0x0661, B:294:0x0632, B:155:0x0328), top: B:328:0x0032, inners: #0, #4, #6, #7, #8, #9, #14, #15, #16, #17, #19 }] */
    /* JADX WARN: Type inference failed for: r3v9, types: [io.dcloud.common.DHInterface.AbsMgr, io.dcloud.common.DHInterface.IMgr] */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r21, java.lang.String r22, java.lang.String[] r23) {
        /*
            Method dump skipped, instructions count: 1760
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.nativeObj.NativeBitmapMgr.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public INativeBitmap getBitmapById(String str) {
        return getBitmapByUuid(this.mIds.get(str));
    }

    public INativeBitmap getBitmapByUuid(String str) {
        return this.mSnaps.get(str);
    }

    public JSONArray getItems() {
        JSONArray jSONArray = new JSONArray();
        Iterator<Map.Entry<String, INativeBitmap>> it = this.mSnaps.entrySet().iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(new JSONObject(((NativeBitmap) it.next().getValue()).toJsString()));
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    public NativeBitmap getSrcNativeBitmap(IWebview iWebview, IApp iApp, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject != null) {
            return (NativeBitmap) getBitmapById(jSONObject.optString("id"));
        }
        if (!PdrUtil.isNetPath(str)) {
            str = (TextUtils.isEmpty(str) || str.equals("null")) ? null : iApp.convert2AbsFullPath(iWebview.obtainFullUrl(), str);
        }
        if (PdrUtil.isEmpty(str)) {
            return null;
        }
        String str2 = iApp.obtainAppId() + str.hashCode();
        return new NativeBitmap(iApp, str2, str2, str);
    }

    protected void initViewDrawItme(IWebview iWebview, NativeView nativeView, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject;
        String strOptString;
        String strOptString2;
        int i;
        String str;
        String str2;
        String str3;
        String str4;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONObject jSONObject4;
        int iStringToColor;
        JSONObject jSONObject5;
        JSONObject jSONObject6;
        JSONObject jSONObject7;
        JSONObject jSONObject8;
        JSONArray jSONArray2 = jSONArray;
        String str5 = "rectStyles";
        String str6 = "id";
        String str7 = "color";
        if (jSONArray2 == null || nativeView == null) {
            return;
        }
        int i2 = 0;
        while (i2 < jSONArray.length()) {
            try {
                jSONObject = jSONArray2.getJSONObject(i2);
                strOptString = jSONObject.has(str6) ? jSONObject.optString(str6) : null;
                strOptString2 = jSONObject.optString("tag");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (!strOptString2.equals(WXBasicComponentType.IMG)) {
                String str8 = strOptString;
                i = i2;
                str = str7;
                str2 = str6;
                str3 = str5;
                if (strOptString2.equals(AbsURIAdapter.FONT)) {
                    String strOptString3 = jSONObject.optString("text");
                    if (strOptString3 != null) {
                        try {
                            jSONObject5 = new JSONObject(jSONObject.optString("position"));
                        } catch (JSONException unused) {
                            jSONObject5 = new JSONObject();
                        }
                        JSONObject jSONObject9 = jSONObject5;
                        try {
                            jSONObject6 = new JSONObject(jSONObject.optString("textStyles"));
                        } catch (JSONException unused2) {
                            jSONObject6 = new JSONObject();
                        }
                        nativeView.makeOverlay(iWebview, null, strOptString3, -1, null, jSONObject9, jSONObject6, str8, AbsURIAdapter.FONT, false, true);
                    }
                } else {
                    if (strOptString2.equals("rect")) {
                        String strOptString4 = TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR;
                        if (jSONObject.has(str)) {
                            strOptString4 = jSONObject.optString(str);
                        }
                        String strOptString5 = jSONObject.optString("position");
                        JSONObject jSONObject10 = (TextUtils.isEmpty(strOptString5) || strOptString5.equals("null")) ? null : new JSONObject(strOptString5);
                        if (jSONObject.has(str3)) {
                            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str3);
                            if (jSONObjectOptJSONObject.has(str)) {
                                strOptString4 = jSONObjectOptJSONObject.optString(str);
                            }
                            jSONObject4 = jSONObjectOptJSONObject;
                        } else {
                            jSONObject4 = null;
                        }
                        try {
                            iStringToColor = Color.parseColor(strOptString4);
                        } catch (Exception unused3) {
                            iStringToColor = PdrUtil.stringToColor(strOptString4);
                        }
                        str4 = str;
                        nativeView.makeOverlay(iWebview, null, null, iStringToColor, null, jSONObject10, jSONObject4, str8, "rect", false, true);
                    } else {
                        str4 = str;
                        if (strOptString2.equals(WXBasicComponentType.RICHTEXT)) {
                            String strOptString6 = jSONObject.optString("text");
                            String strOptString7 = jSONObject.optString("position");
                            nativeView.makeRichText(iWebview, strOptString6, (TextUtils.isEmpty(strOptString7) || strOptString7.equals("null")) ? null : new JSONObject(strOptString7), jSONObject.has("richTextStyles") ? jSONObject.optJSONObject("richTextStyles") : null, str8);
                        } else if (strOptString2.equals("input")) {
                            try {
                                jSONObject2 = new JSONObject(jSONObject.optString("position"));
                            } catch (JSONException unused4) {
                                jSONObject2 = new JSONObject();
                            }
                            JSONObject jSONObject11 = jSONObject2;
                            try {
                                jSONObject3 = new JSONObject(jSONObject.optString("inputStyles"));
                            } catch (JSONException unused5) {
                                jSONObject3 = new JSONObject();
                            }
                            nativeView.makeOverlay(iWebview, null, null, -1, null, jSONObject11, jSONObject3, str8, "input", false, true);
                        } else if (strOptString2.equals("weex")) {
                            nativeView.makeWeexView(iWebview, jSONObject, str8);
                        }
                    }
                    i2 = i + 1;
                    str5 = str3;
                    str6 = str2;
                    str7 = str4;
                    jSONArray2 = jSONArray;
                }
                e.printStackTrace();
                return;
            }
            NativeBitmap srcNativeBitmap = getSrcNativeBitmap(iWebview, iWebview.obtainApp(), jSONObject.optString("src"));
            try {
                jSONObject7 = new JSONObject(jSONObject.optString("sprite"));
            } catch (JSONException unused6) {
                jSONObject7 = new JSONObject();
            }
            JSONObject jSONObject12 = jSONObject7;
            try {
                jSONObject8 = new JSONObject(jSONObject.optString("position"));
            } catch (JSONException unused7) {
                jSONObject8 = new JSONObject();
            }
            i = i2;
            str = str7;
            str2 = str6;
            str3 = str5;
            nativeView.makeOverlay(iWebview, srcNativeBitmap, null, -1, jSONObject12, jSONObject8, null, strOptString, WXBasicComponentType.IMG, false, true);
            str4 = str;
            i2 = i + 1;
            str5 = str3;
            str6 = str2;
            str7 = str4;
            jSONArray2 = jSONArray;
        }
        nativeView.nativeInvalidate(true);
    }
}
