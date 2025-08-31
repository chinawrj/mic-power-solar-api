package uts.sdk.modules.DCloudUniPrompt;

import android.app.Dialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.prompt.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001$B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aH\u0016J\u001c\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00060\u0002R\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001aH\u0016R\u001a\u0010\b\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/ItemAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Luts/sdk/modules/DCloudUniPrompt/ItemAdapter$ViewHolder;", "dialog", "Landroid/app/Dialog;", "style", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "(Landroid/app/Dialog;Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;)V", "hostDialog", "getHostDialog", "()Landroid/app/Dialog;", "setHostDialog", "(Landroid/app/Dialog;)V", "hostStyle", "getHostStyle", "()Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "setHostStyle", "(Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;)V", "mItemList", "Lio/dcloud/uts/UTSArray;", "", "getMItemList", "()Lio/dcloud/uts/UTSArray;", "setMItemList", "(Lio/dcloud/uts/UTSArray;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Dialog hostDialog;
    private ShowActionSheetOptions hostStyle;
    private UTSArray<String> mItemList;

    public UTSArray<String> getMItemList() {
        return this.mItemList;
    }

    public void setMItemList(UTSArray<String> uTSArray) {
        Intrinsics.checkNotNullParameter(uTSArray, "<set-?>");
        this.mItemList = uTSArray;
    }

    public ShowActionSheetOptions getHostStyle() {
        return this.hostStyle;
    }

    public void setHostStyle(ShowActionSheetOptions showActionSheetOptions) {
        Intrinsics.checkNotNullParameter(showActionSheetOptions, "<set-?>");
        this.hostStyle = showActionSheetOptions;
    }

    public Dialog getHostDialog() {
        return this.hostDialog;
    }

    public void setHostDialog(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "<set-?>");
        this.hostDialog = dialog;
    }

    public ItemAdapter(Dialog dialog, ShowActionSheetOptions style) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Intrinsics.checkNotNullParameter(style, "style");
        setHostDialog(dialog);
        setMItemList(style.getItemList());
        setHostStyle(style);
    }

    /* compiled from: index.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0096\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/ItemAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", WXBasicComponentType.VIEW, "Landroid/view/View;", "(Luts/sdk/modules/DCloudUniPrompt/ItemAdapter;Landroid/view/View;)V", "itemName", "Landroid/widget/TextView;", "getItemName", "()Landroid/widget/TextView;", "setItemName", "(Landroid/widget/TextView;)V", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        final /* synthetic */ ItemAdapter this$0;

        public TextView getItemName() {
            return this.itemName;
        }

        public void setItemName(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.itemName = textView;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(ItemAdapter itemAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = itemAdapter;
            View viewFindViewById = view.findViewById(R.id.tvName);
            Intrinsics.checkNotNull(viewFindViewById, "null cannot be cast to non-null type android.widget.TextView");
            setItemName((TextView) viewFindViewById);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        String title = getHostStyle().getTitle();
        return ((title == null || StringsKt.isBlank(title)) && position == 0) ? 1001 : 1002;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewInflate;
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (viewType == 1002) {
            if (UTSAndroid.INSTANCE.getAppDarkMode()) {
                viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.uni_app_uni_prompt_ac_recyclerview_layout_night, parent, false);
                Intrinsics.checkNotNullExpressionValue(viewInflate, "from(parent.context).inf…out_night, parent, false)");
            } else {
                viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.uni_app_uni_prompt_ac_recyclerview_layout, parent, false);
                Intrinsics.checkNotNullExpressionValue(viewInflate, "from(parent.context).inf…ew_layout, parent, false)");
            }
        } else if (UTSAndroid.INSTANCE.getAppDarkMode()) {
            viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.uni_app_uni_prompt_ac_recyclerview_layout_top_night, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "from(parent.context).inf…top_night, parent, false)");
        } else {
            viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.uni_app_uni_prompt_ac_recyclerview_layout_top, parent, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "from(parent.context).inf…ayout_top, parent, false)");
        }
        return new ViewHolder(this, viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        String str = getMItemList().get(position);
        Intrinsics.checkNotNullExpressionValue(str, "mItemList[position]");
        holder.getItemName().setText(str);
        if (getHostStyle().getItemColor() != null && IndexKt.isValidColor(getHostStyle().getItemColor())) {
            holder.getItemName().setTextColor(Color.parseColor(getHostStyle().getItemColor()));
        }
        holder.getItemName().setOnClickListener(new ItemClickListener(getHostDialog(), getHostStyle(), Integer.valueOf(position)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getMItemList().size();
    }
}
