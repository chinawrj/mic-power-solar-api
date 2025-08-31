package io.dcloud.feature.nativeObj.richtext.dom;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.widget.TextView;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.nativeObj.richtext.IAssets;
import io.dcloud.feature.nativeObj.richtext.span.HrSpan;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes3.dex */
public class HrDomElement extends DomElement {
    String align;
    String size;
    String width;

    String getColor() {
        HashMap<String, String> map = this.style;
        return (map == null || !map.containsKey("border-color")) ? "#EEEEEE" : this.style.get("border-color");
    }

    @Override // io.dcloud.feature.nativeObj.richtext.dom.DomElement
    public void makeSpan(IAssets iAssets, TextView textView, SpannableStringBuilder spannableStringBuilder) {
        int i;
        spannableStringBuilder.append("\n");
        spannableStringBuilder.append("1");
        int iStringToColor = iAssets.stringToColor(getColor());
        if ("left".equalsIgnoreCase(this.align)) {
            i = 1;
        } else {
            i = "right".equalsIgnoreCase(this.align) ? 2 : 0;
        }
        spannableStringBuilder.setSpan(new HrSpan(i, (int) iAssets.convertHeight(this.size, 1.0f), (int) iAssets.convertWidth(this.width, 1.0f), iStringToColor, (int) iAssets.convertWidth("100%", 1.0f)), spannableStringBuilder.length() - 1, spannableStringBuilder.length(), 17);
    }

    @Override // io.dcloud.feature.nativeObj.richtext.dom.DomElement
    public void parseDomElement(XmlPullParser xmlPullParser) {
        super.parseDomElement(xmlPullParser);
        this.align = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), AbsoluteConst.JSON_KEY_ALIGN);
        String attributeValue = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), "width");
        this.width = attributeValue;
        if (TextUtils.isEmpty(attributeValue)) {
            this.width = "100%";
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(), AbsoluteConst.JSON_KEY_SIZE);
        this.size = attributeValue2;
        if (TextUtils.isEmpty(attributeValue2)) {
            this.size = "1px";
        }
    }
}
