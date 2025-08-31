package com.taobao.weex.ui.component.richtext.node;

import android.content.Context;
import android.text.SpannableStringBuilder;
import com.taobao.weex.dom.TextDecorationSpan;
import io.dcloud.feature.uniapp.dom.AbsStyle;
import java.util.Map;

/* loaded from: classes3.dex */
class SpanNode extends RichTextNode {
    public static final String NODE_TYPE = "span";

    static class SpanNodeCreator implements RichTextNodeCreator<SpanNode> {
        SpanNodeCreator() {
        }

        @Override // com.taobao.weex.ui.component.richtext.node.RichTextNodeCreator
        public /* bridge */ /* synthetic */ RichTextNode createRichTextNode(Context context, String str, String str2, String str3, Map map, Map map2) {
            return createRichTextNode(context, str, str2, str3, (Map<String, Object>) map, (Map<String, Object>) map2);
        }

        @Override // com.taobao.weex.ui.component.richtext.node.RichTextNodeCreator
        public SpanNode createRichTextNode(Context context, String str, String str2) {
            return new SpanNode(context, str, str2);
        }

        @Override // com.taobao.weex.ui.component.richtext.node.RichTextNodeCreator
        public SpanNode createRichTextNode(Context context, String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
            return new SpanNode(context, str, str2, str3, map, map2);
        }
    }

    @Override // com.taobao.weex.ui.component.richtext.node.RichTextNode
    protected boolean isInternalNode() {
        return true;
    }

    @Override // com.taobao.weex.ui.component.richtext.node.RichTextNode
    public String toString() {
        Map<String, Object> map = this.attr;
        return (map == null || !map.containsKey("value")) ? "" : this.attr.get("value").toString();
    }

    @Override // com.taobao.weex.ui.component.richtext.node.RichTextNode
    protected void updateSpans(SpannableStringBuilder spannableStringBuilder, int i) {
        super.updateSpans(spannableStringBuilder, i);
        spannableStringBuilder.setSpan(new TextDecorationSpan(AbsStyle.getTextDecoration(this.style)), 0, spannableStringBuilder.length(), RichTextNode.createSpanFlag(i));
    }

    private SpanNode(Context context, String str, String str2) {
        super(context, str, str2);
    }

    private SpanNode(Context context, String str, String str2, String str3, Map<String, Object> map, Map<String, Object> map2) {
        super(context, str, str2, str3, map, map2);
    }
}
