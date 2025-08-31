package io.dcloud.nineoldandroids.animation;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.animation.AnimationUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class AnimatorInflater {
    private static final int AnimatorSet_ordering = 0;
    private static final int Animator_duration = 1;
    private static final int Animator_interpolator = 0;
    private static final int Animator_repeatCount = 3;
    private static final int Animator_repeatMode = 4;
    private static final int Animator_startOffset = 2;
    private static final int Animator_valueFrom = 5;
    private static final int Animator_valueTo = 6;
    private static final int Animator_valueType = 7;
    private static final int PropertyAnimator_propertyName = 0;
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int[] AnimatorSet = {R.attr.ordering};
    private static final int[] PropertyAnimator = {R.attr.propertyName};
    private static final int[] Animator = {R.attr.interpolator, R.attr.duration, R.attr.startOffset, R.attr.repeatCount, R.attr.repeatMode, R.attr.valueFrom, R.attr.valueTo, R.attr.valueType};

    public static Animator loadAnimator(Context context, int i) throws Resources.NotFoundException {
        XmlResourceParser animation = null;
        try {
            try {
                animation = context.getResources().getAnimation(i);
                return createAnimatorFromXml(context, animation);
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    private static Animator createAnimatorFromXml(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(context, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
    
        if (r2 == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001c, code lost:
    
        r9 = new io.dcloud.nineoldandroids.animation.Animator[r2.size()];
        r10 = r2.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
    
        if (r10.hasNext() != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
    
        if (r13 != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
    
        r12.playTogether(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
    
        r12.playSequentially(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0036, code lost:
    
        r9[r6] = (io.dcloud.nineoldandroids.animation.Animator) r10.next();
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0018, code lost:
    
        if (r12 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static io.dcloud.nineoldandroids.animation.Animator createAnimatorFromXml(android.content.Context r9, org.xmlpull.v1.XmlPullParser r10, android.util.AttributeSet r11, io.dcloud.nineoldandroids.animation.AnimatorSet r12, int r13) throws org.xmlpull.v1.XmlPullParserException, android.content.res.Resources.NotFoundException, java.io.IOException {
        /*
            int r0 = r10.getDepth()
            r1 = 0
            r2 = r1
            r3 = r2
        L7:
            int r4 = r10.next()
            r5 = 3
            r6 = 0
            if (r4 != r5) goto L15
            int r5 = r10.getDepth()
            if (r5 <= r0) goto L18
        L15:
            r5 = 1
            if (r4 != r5) goto L43
        L18:
            if (r12 == 0) goto L42
            if (r2 == 0) goto L42
            int r9 = r2.size()
            io.dcloud.nineoldandroids.animation.Animator[] r9 = new io.dcloud.nineoldandroids.animation.Animator[r9]
            java.util.Iterator r10 = r2.iterator()
        L26:
            boolean r11 = r10.hasNext()
            if (r11 != 0) goto L36
            if (r13 != 0) goto L32
            r12.playTogether(r9)
            goto L42
        L32:
            r12.playSequentially(r9)
            goto L42
        L36:
            java.lang.Object r11 = r10.next()
            io.dcloud.nineoldandroids.animation.Animator r11 = (io.dcloud.nineoldandroids.animation.Animator) r11
            int r0 = r6 + 1
            r9[r6] = r11
            r6 = r0
            goto L26
        L42:
            return r3
        L43:
            r5 = 2
            if (r4 == r5) goto L47
            goto L7
        L47:
            java.lang.String r3 = r10.getName()
            java.lang.String r4 = "objectAnimator"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L58
            io.dcloud.nineoldandroids.animation.ObjectAnimator r3 = loadObjectAnimator(r9, r11)
            goto L91
        L58:
            java.lang.String r4 = "animator"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L65
            io.dcloud.nineoldandroids.animation.ValueAnimator r3 = loadAnimator(r9, r11, r1)
            goto L91
        L65:
            java.lang.String r4 = "set"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L9f
            io.dcloud.nineoldandroids.animation.AnimatorSet r3 = new io.dcloud.nineoldandroids.animation.AnimatorSet
            r3.<init>()
            int[] r4 = io.dcloud.nineoldandroids.animation.AnimatorInflater.AnimatorSet
            android.content.res.TypedArray r4 = r9.obtainStyledAttributes(r11, r4)
            android.util.TypedValue r5 = new android.util.TypedValue
            r5.<init>()
            r4.getValue(r6, r5)
            int r7 = r5.type
            r8 = 16
            if (r7 != r8) goto L88
            int r6 = r5.data
        L88:
            r5 = r3
            io.dcloud.nineoldandroids.animation.AnimatorSet r5 = (io.dcloud.nineoldandroids.animation.AnimatorSet) r5
            createAnimatorFromXml(r9, r10, r11, r3, r6)
            r4.recycle()
        L91:
            if (r12 == 0) goto L7
            if (r2 != 0) goto L9a
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L9a:
            r2.add(r3)
            goto L7
        L9f:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "Unknown animator name: "
            r11.<init>(r12)
            java.lang.String r10 = r10.getName()
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.nineoldandroids.animation.AnimatorInflater.createAnimatorFromXml(android.content.Context, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, io.dcloud.nineoldandroids.animation.AnimatorSet, int):io.dcloud.nineoldandroids.animation.Animator");
    }

    private static ObjectAnimator loadObjectAnimator(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        loadAnimator(context, attributeSet, objectAnimator);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, PropertyAnimator);
        objectAnimator.setPropertyName(typedArrayObtainStyledAttributes.getString(0));
        typedArrayObtainStyledAttributes.recycle();
        return objectAnimator;
    }

    private static ValueAnimator loadAnimator(Context context, AttributeSet attributeSet, ValueAnimator valueAnimator) throws Resources.NotFoundException {
        int i;
        int color;
        int color2;
        int color3;
        int i2;
        float dimension;
        float dimension2;
        float dimension3;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Animator);
        long j = typedArrayObtainStyledAttributes.getInt(1, 0);
        long j2 = typedArrayObtainStyledAttributes.getInt(2, 0);
        int i3 = typedArrayObtainStyledAttributes.getInt(7, 0);
        ValueAnimator valueAnimator2 = valueAnimator == null ? new ValueAnimator() : valueAnimator;
        boolean z = i3 == 0;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(5);
        boolean z2 = typedValuePeekValue != null;
        int i4 = z2 ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(6);
        boolean z3 = typedValuePeekValue2 != null;
        int i5 = z3 ? typedValuePeekValue2.type : 0;
        if ((z2 && i4 >= 28 && i4 <= 31) || (z3 && i5 >= 28 && i5 <= 31)) {
            valueAnimator2.setEvaluator(new ArgbEvaluator());
            z = false;
        }
        if (z) {
            if (z2) {
                if (i4 == 5) {
                    dimension2 = typedArrayObtainStyledAttributes.getDimension(5, 0.0f);
                } else {
                    dimension2 = typedArrayObtainStyledAttributes.getFloat(5, 0.0f);
                }
                if (z3) {
                    if (i5 == 5) {
                        dimension3 = typedArrayObtainStyledAttributes.getDimension(6, 0.0f);
                    } else {
                        dimension3 = typedArrayObtainStyledAttributes.getFloat(6, 0.0f);
                    }
                    i2 = 0;
                    valueAnimator2.setFloatValues(dimension2, dimension3);
                } else {
                    i2 = 0;
                    valueAnimator2.setFloatValues(dimension2);
                }
            } else {
                i2 = 0;
                if (i5 == 5) {
                    dimension = typedArrayObtainStyledAttributes.getDimension(6, 0.0f);
                } else {
                    dimension = typedArrayObtainStyledAttributes.getFloat(6, 0.0f);
                }
                valueAnimator2.setFloatValues(dimension);
            }
            i = i2;
        } else {
            if (z2) {
                if (i4 == 5) {
                    color2 = (int) typedArrayObtainStyledAttributes.getDimension(5, 0.0f);
                } else if (i4 >= 28 && i4 <= 31) {
                    color2 = typedArrayObtainStyledAttributes.getColor(5, 0);
                } else {
                    color2 = typedArrayObtainStyledAttributes.getInt(5, 0);
                }
                if (z3) {
                    if (i5 == 5) {
                        color3 = (int) typedArrayObtainStyledAttributes.getDimension(6, 0.0f);
                    } else if (i5 >= 28 && i5 <= 31) {
                        color3 = typedArrayObtainStyledAttributes.getColor(6, 0);
                    } else {
                        color3 = typedArrayObtainStyledAttributes.getInt(6, 0);
                    }
                    valueAnimator2.setIntValues(color2, color3);
                } else {
                    valueAnimator2.setIntValues(color2);
                }
            } else if (z3) {
                if (i5 == 5) {
                    color = (int) typedArrayObtainStyledAttributes.getDimension(6, 0.0f);
                    i = 0;
                } else if (i5 >= 28 && i5 <= 31) {
                    i = 0;
                    color = typedArrayObtainStyledAttributes.getColor(6, 0);
                } else {
                    i = 0;
                    color = typedArrayObtainStyledAttributes.getInt(6, 0);
                }
                valueAnimator2.setIntValues(color);
            }
            i = 0;
        }
        valueAnimator2.setDuration(j);
        valueAnimator2.setStartDelay(j2);
        if (typedArrayObtainStyledAttributes.hasValue(3)) {
            valueAnimator2.setRepeatCount(typedArrayObtainStyledAttributes.getInt(3, i));
        }
        if (typedArrayObtainStyledAttributes.hasValue(4)) {
            valueAnimator2.setRepeatMode(typedArrayObtainStyledAttributes.getInt(4, 1));
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(i, i);
        if (resourceId > 0) {
            valueAnimator2.setInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
        }
        typedArrayObtainStyledAttributes.recycle();
        return valueAnimator2;
    }
}
