package com.taobao.weex.ui.component.list;

import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class RecyclerTransform {
    private static final String TAG = "RecyclerTransform";
    public static final String TRANSFORM = "transform";
    private static final Pattern transformPattern = Pattern.compile("([a-z]+)\\(([0-9\\.]+),?([0-9\\.]+)?\\)");

    /* JADX WARN: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x006c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.recyclerview.widget.RecyclerView.ItemDecoration parseTransforms(int r16, java.lang.String r17) throws java.lang.NumberFormatException {
        /*
            r0 = r17
            java.lang.String r1 = "RecyclerTransform"
            java.lang.String r2 = "Invaild transform expression:"
            if (r0 != 0) goto La
            r0 = 0
            return r0
        La:
            java.util.regex.Pattern r3 = com.taobao.weex.ui.component.list.RecyclerTransform.transformPattern
            java.util.regex.Matcher r3 = r3.matcher(r0)
            r0 = 0
            r7 = r0
            r11 = r7
            r12 = r11
            r8 = 0
            r9 = 0
            r10 = 0
        L17:
            boolean r0 = r3.find()
            r5 = 1
            if (r0 == 0) goto Ld2
            java.lang.String r6 = r3.group()
            java.lang.String r0 = r3.group(r5)
            int r13 = r0.hashCode()     // Catch: java.lang.NumberFormatException -> Lbb
            r14 = -1267206133(0xffffffffb477f80b, float:-2.3093905E-7)
            r15 = 3
            r4 = 2
            if (r13 == r14) goto L5f
            r14 = -925180581(0xffffffffc8dadd5b, float:-448234.84)
            if (r13 == r14) goto L55
            r14 = 109250890(0x683094a, float:4.929037E-35)
            if (r13 == r14) goto L4b
            r14 = 1052832078(0x3ec0f14e, float:0.376841)
            if (r13 == r14) goto L41
            goto L69
        L41:
            java.lang.String r13 = "translate"
            boolean r0 = r0.equals(r13)     // Catch: java.lang.NumberFormatException -> Lbb
            if (r0 == 0) goto L69
            r0 = r5
            goto L6a
        L4b:
            java.lang.String r13 = "scale"
            boolean r0 = r0.equals(r13)     // Catch: java.lang.NumberFormatException -> Lbb
            if (r0 == 0) goto L69
            r0 = 0
            goto L6a
        L55:
            java.lang.String r13 = "rotate"
            boolean r0 = r0.equals(r13)     // Catch: java.lang.NumberFormatException -> Lbb
            if (r0 == 0) goto L69
            r0 = r15
            goto L6a
        L5f:
            java.lang.String r13 = "opacity"
            boolean r0 = r0.equals(r13)     // Catch: java.lang.NumberFormatException -> Lbb
            if (r0 == 0) goto L69
            r0 = r4
            goto L6a
        L69:
            r0 = -1
        L6a:
            if (r0 == 0) goto La9
            if (r0 == r5) goto L97
            if (r0 == r4) goto L8e
            if (r0 == r15) goto L85
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> Lbb
            r0.<init>()     // Catch: java.lang.NumberFormatException -> Lbb
            r0.append(r2)     // Catch: java.lang.NumberFormatException -> Lbb
            r0.append(r6)     // Catch: java.lang.NumberFormatException -> Lbb
            java.lang.String r0 = r0.toString()     // Catch: java.lang.NumberFormatException -> Lbb
            com.taobao.weex.utils.WXLogUtils.e(r1, r0)     // Catch: java.lang.NumberFormatException -> Lbb
            goto L17
        L85:
            java.lang.String r0 = r3.group(r4)     // Catch: java.lang.NumberFormatException -> Lbb
            int r10 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> Lbb
            goto L17
        L8e:
            java.lang.String r0 = r3.group(r4)     // Catch: java.lang.NumberFormatException -> Lbb
            float r7 = java.lang.Float.parseFloat(r0)     // Catch: java.lang.NumberFormatException -> Lbb
            goto L17
        L97:
            java.lang.String r0 = r3.group(r4)     // Catch: java.lang.NumberFormatException -> Lbb
            int r8 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> Lbb
            java.lang.String r0 = r3.group(r15)     // Catch: java.lang.NumberFormatException -> Lbb
            int r9 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> Lbb
            goto L17
        La9:
            java.lang.String r0 = r3.group(r4)     // Catch: java.lang.NumberFormatException -> Lbb
            float r11 = java.lang.Float.parseFloat(r0)     // Catch: java.lang.NumberFormatException -> Lbb
            java.lang.String r0 = r3.group(r15)     // Catch: java.lang.NumberFormatException -> Lbb
            float r12 = java.lang.Float.parseFloat(r0)     // Catch: java.lang.NumberFormatException -> Lbb
            goto L17
        Lbb:
            r0 = move-exception
            java.lang.String r4 = ""
            com.taobao.weex.utils.WXLogUtils.e(r4, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r2)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            com.taobao.weex.utils.WXLogUtils.e(r1, r0)
            goto L17
        Ld2:
            com.taobao.weex.ui.view.listview.adapter.TransformItemDecoration r0 = new com.taobao.weex.ui.view.listview.adapter.TransformItemDecoration
            r1 = r16
            if (r1 != r5) goto Lda
            r6 = r5
            goto Ldb
        Lda:
            r6 = 0
        Ldb:
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.RecyclerTransform.parseTransforms(int, java.lang.String):androidx.recyclerview.widget.RecyclerView$ItemDecoration");
    }
}
