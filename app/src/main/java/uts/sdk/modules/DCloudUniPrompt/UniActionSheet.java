package uts.sdk.modules.DCloudUniPrompt;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.prompt.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010,\u001a\u00020-H\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u001a\u0010 \u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001a\u0010#\u001a\u00020$X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\n\"\u0004\b+\u0010\f¨\u0006."}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/UniActionSheet;", "Landroid/app/Dialog;", "activity", "Landroid/app/Activity;", "style", "Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "(Landroid/app/Activity;Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;)V", BindingXConstants.STATE_CANCEL, "Landroidx/appcompat/widget/AppCompatTextView;", "getCancel", "()Landroidx/appcompat/widget/AppCompatTextView;", "setCancel", "(Landroidx/appcompat/widget/AppCompatTextView;)V", "hostActivity", "getHostActivity", "()Landroid/app/Activity;", "setHostActivity", "(Landroid/app/Activity;)V", "hostStyle", "getHostStyle", "()Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;", "setHostStyle", "(Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetOptions;)V", "lineCancel", "Landroidx/appcompat/widget/LinearLayoutCompat;", "getLineCancel", "()Landroidx/appcompat/widget/LinearLayoutCompat;", "setLineCancel", "(Landroidx/appcompat/widget/LinearLayoutCompat;)V", "lineContent", "getLineContent", "setLineContent", "lineTitle", "getLineTitle", "setLineTitle", "myRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getMyRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setMyRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", AbsoluteConst.JSON_KEY_TITLE, "getTitle", "setTitle", "dismiss", "", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UniActionSheet extends Dialog {
    private AppCompatTextView cancel;
    private Activity hostActivity;
    private ShowActionSheetOptions hostStyle;
    private LinearLayoutCompat lineCancel;
    private LinearLayoutCompat lineContent;
    private LinearLayoutCompat lineTitle;
    private RecyclerView myRecyclerView;
    private AppCompatTextView title;

    public Activity getHostActivity() {
        return this.hostActivity;
    }

    public void setHostActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<set-?>");
        this.hostActivity = activity;
    }

    public ShowActionSheetOptions getHostStyle() {
        return this.hostStyle;
    }

    public void setHostStyle(ShowActionSheetOptions showActionSheetOptions) {
        Intrinsics.checkNotNullParameter(showActionSheetOptions, "<set-?>");
        this.hostStyle = showActionSheetOptions;
    }

    public AppCompatTextView getTitle() {
        return this.title;
    }

    public void setTitle(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.title = appCompatTextView;
    }

    public AppCompatTextView getCancel() {
        return this.cancel;
    }

    public void setCancel(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.cancel = appCompatTextView;
    }

    public RecyclerView getMyRecyclerView() {
        return this.myRecyclerView;
    }

    public void setMyRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.myRecyclerView = recyclerView;
    }

    public LinearLayoutCompat getLineTitle() {
        return this.lineTitle;
    }

    public void setLineTitle(LinearLayoutCompat linearLayoutCompat) {
        Intrinsics.checkNotNullParameter(linearLayoutCompat, "<set-?>");
        this.lineTitle = linearLayoutCompat;
    }

    public LinearLayoutCompat getLineContent() {
        return this.lineContent;
    }

    public void setLineContent(LinearLayoutCompat linearLayoutCompat) {
        Intrinsics.checkNotNullParameter(linearLayoutCompat, "<set-?>");
        this.lineContent = linearLayoutCompat;
    }

    public LinearLayoutCompat getLineCancel() {
        return this.lineCancel;
    }

    public void setLineCancel(LinearLayoutCompat linearLayoutCompat) {
        Intrinsics.checkNotNullParameter(linearLayoutCompat, "<set-?>");
        this.lineCancel = linearLayoutCompat;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        PromptErrorImpl promptErrorImpl = new PromptErrorImpl((Number) 1, "showActionSheet:fail cancel");
        Function1<IPromptError, Unit> fail = getHostStyle().getFail();
        if (fail != null) {
            fail.invoke(promptErrorImpl);
        }
        getHostStyle().setFail(null);
        Function1<Object, Unit> complete = getHostStyle().getComplete();
        if (complete != null) {
            complete.invoke(promptErrorImpl);
        }
        getHostStyle().setComplete(null);
        super.dismiss();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UniActionSheet(Activity activity, ShowActionSheetOptions style) {
        super(activity, R.style.uni_app_uni_prompt_ActionsheetDialog);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(style, "style");
        setHostActivity(activity);
        setHostStyle(style);
        if (UTSAndroid.INSTANCE.getAppDarkMode()) {
            setContentView(R.layout.uni_app_uni_prompt_uts_action_sheet_night);
        } else {
            setContentView(R.layout.uni_app_uni_prompt_uts_action_sheet);
        }
        View viewFindViewById = findViewById(R.id.tvTitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.tvTitle)");
        setTitle((AppCompatTextView) viewFindViewById);
        View viewFindViewById2 = findViewById(R.id.tvCancelAction);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.tvCancelAction)");
        setCancel((AppCompatTextView) viewFindViewById2);
        View viewFindViewById3 = findViewById(R.id.line_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.line_title)");
        setLineTitle((LinearLayoutCompat) viewFindViewById3);
        View viewFindViewById4 = findViewById(R.id.line_content);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(R.id.line_content)");
        setLineContent((LinearLayoutCompat) viewFindViewById4);
        View viewFindViewById5 = findViewById(R.id.line_cancel);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(R.id.line_cancel)");
        setLineCancel((LinearLayoutCompat) viewFindViewById5);
        View viewFindViewById6 = findViewById(R.id.myRecyclerview);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(R.id.myRecyclerview)");
        setMyRecyclerView((RecyclerView) viewFindViewById6);
        getMyRecyclerView().setLayoutManager(new LinearLayoutManager(getHostActivity()));
        UniActionSheet uniActionSheet = this;
        getCancel().setOnClickListener(new CancelClickListener(uniActionSheet, getHostStyle()));
        getMyRecyclerView().setAdapter(new ItemAdapter(uniActionSheet, getHostStyle()));
        String title = style.getTitle();
        if (title != null && !StringsKt.isBlank(title)) {
            getLineTitle().setVisibility(0);
            getTitle().setText(style.getTitle());
        }
        if (getWindow() != null) {
            Window window = getWindow();
            Intrinsics.checkNotNull(window);
            window.setLayout(-1, -2);
            Window window2 = getWindow();
            Intrinsics.checkNotNull(window2);
            window2.setGravity(81);
            Window window3 = getWindow();
            Intrinsics.checkNotNull(window3);
            if (window3.getAttributes() != null) {
                Window window4 = getWindow();
                Intrinsics.checkNotNull(window4);
                WindowManager.LayoutParams attributes = window4.getAttributes();
                Intrinsics.checkNotNull(attributes);
                attributes.windowAnimations = R.style.uni_app_uni_prompt_DialogAnimations_slideWindow;
            }
        }
    }
}
