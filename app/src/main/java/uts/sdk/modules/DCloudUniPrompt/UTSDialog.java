package uts.sdk.modules.DCloudUniPrompt;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import com.taobao.weex.ui.component.WXBasicComponentType;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.prompt.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\rH\u0016J\u0010\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020\"H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\u001e\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\u001a\u0010!\u001a\u00020\"X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006-"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/UTSDialog;", "Landroid/app/Dialog;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "et_content", "Landroid/widget/EditText;", "getEt_content", "()Landroid/widget/EditText;", "setEt_content", "(Landroid/widget/EditText;)V", "hostStyle", "Luts/sdk/modules/DCloudUniPrompt/ShowModalOptions;", "getHostStyle", "()Luts/sdk/modules/DCloudUniPrompt/ShowModalOptions;", "setHostStyle", "(Luts/sdk/modules/DCloudUniPrompt/ShowModalOptions;)V", "tvCancelAction", "Landroid/widget/TextView;", "getTvCancelAction", "()Landroid/widget/TextView;", "setTvCancelAction", "(Landroid/widget/TextView;)V", "tvSureAction", "getTvSureAction", "setTvSureAction", "tv_content", "getTv_content", "setTv_content", "tv_title", "getTv_title", "setTv_title", "view_split_line_v", "Landroid/view/View;", "getView_split_line_v", "()Landroid/view/View;", "setView_split_line_v", "(Landroid/view/View;)V", "dismiss", "", "initStyle", "style", "onClick", WXBasicComponentType.VIEW, "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UTSDialog extends Dialog implements View.OnClickListener {
    private EditText et_content;
    private ShowModalOptions hostStyle;
    private TextView tvCancelAction;
    private TextView tvSureAction;
    private TextView tv_content;
    private TextView tv_title;
    private View view_split_line_v;

    public TextView getTv_title() {
        return this.tv_title;
    }

    public void setTv_title(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.tv_title = textView;
    }

    public TextView getTv_content() {
        return this.tv_content;
    }

    public void setTv_content(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.tv_content = textView;
    }

    public EditText getEt_content() {
        return this.et_content;
    }

    public void setEt_content(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "<set-?>");
        this.et_content = editText;
    }

    public TextView getTvCancelAction() {
        return this.tvCancelAction;
    }

    public void setTvCancelAction(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.tvCancelAction = textView;
    }

    public TextView getTvSureAction() {
        return this.tvSureAction;
    }

    public void setTvSureAction(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.tvSureAction = textView;
    }

    public View getView_split_line_v() {
        return this.view_split_line_v;
    }

    public void setView_split_line_v(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.view_split_line_v = view;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UTSDialog(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        if (UTSAndroid.INSTANCE.getAppDarkMode()) {
            setContentView(R.layout.uni_app_uni_prompt_modal_dialog_night);
        } else {
            setContentView(R.layout.uni_app_uni_prompt_modal_dialog);
        }
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        View viewFindViewById = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "this.findViewById<TextView>(R.id.tv_title)");
        setTv_title((TextView) viewFindViewById);
        View viewFindViewById2 = findViewById(R.id.tv_content);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "this.findViewById<TextView>(R.id.tv_content)");
        setTv_content((TextView) viewFindViewById2);
        View viewFindViewById3 = findViewById(R.id.et_content);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "this.findViewById<EditText>(R.id.et_content)");
        setEt_content((EditText) viewFindViewById3);
        View viewFindViewById4 = findViewById(R.id.tvCancelAction);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "this.findViewById<TextView>(R.id.tvCancelAction)");
        setTvCancelAction((TextView) viewFindViewById4);
        View viewFindViewById5 = findViewById(R.id.tvSureAction);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "this.findViewById<TextView>(R.id.tvSureAction)");
        setTvSureAction((TextView) viewFindViewById5);
        View viewFindViewById6 = findViewById(R.id.view_split_line_v);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(R.id.view_split_line_v)");
        setView_split_line_v(viewFindViewById6);
    }

    public ShowModalOptions getHostStyle() {
        return this.hostStyle;
    }

    public void setHostStyle(ShowModalOptions showModalOptions) {
        this.hostStyle = showModalOptions;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Function1<Object, Unit> complete;
        ShowModalSuccess showModalSuccess = new ShowModalSuccess(false, true, null, 4, null);
        ShowModalOptions hostStyle = getHostStyle();
        if (hostStyle != null && (complete = hostStyle.getComplete()) != null) {
            complete.invoke(showModalSuccess);
        }
        if (getHostStyle() != null) {
            ShowModalOptions hostStyle2 = getHostStyle();
            Intrinsics.checkNotNull(hostStyle2);
            hostStyle2.setComplete(null);
        }
        super.dismiss();
    }

    public void initStyle(ShowModalOptions style) {
        Intrinsics.checkNotNullParameter(style, "style");
        setHostStyle(style);
        setCanceledOnTouchOutside(false);
        if (style.getShowCancel() != null && Intrinsics.areEqual((Object) style.getShowCancel(), (Object) true)) {
            getTvCancelAction().setVisibility(0);
            getView_split_line_v().setVisibility(0);
            getTvSureAction().setVisibility(0);
            if (UTSAndroid.INSTANCE.getAppDarkMode()) {
                getTvSureAction().setBackgroundResource(R.drawable.uni_app_uni_prompt_dialog_button_select_right_night);
            } else {
                getTvSureAction().setBackgroundResource(R.drawable.uni_app_uni_prompt_dialog_button_select_right);
            }
        } else {
            getTvCancelAction().setVisibility(8);
            getView_split_line_v().setVisibility(8);
            getTvSureAction().setVisibility(0);
            if (UTSAndroid.INSTANCE.getAppDarkMode()) {
                getTvSureAction().setBackgroundResource(R.drawable.uni_app_uni_prompt_dialog_button_select_total_night);
            } else {
                getTvSureAction().setBackgroundResource(R.drawable.uni_app_uni_prompt_dialog_button_select_total);
            }
        }
        if (style.getEditable() != null && Intrinsics.areEqual((Object) style.getEditable(), (Object) true)) {
            getEt_content().setVisibility(0);
            getTv_content().setVisibility(8);
            if (!TextUtils.isEmpty(style.getContent())) {
                getEt_content().setFocusable(true);
                getEt_content().setFocusableInTouchMode(true);
                getEt_content().requestFocus();
                getEt_content().setText(style.getContent());
                getEt_content().setSelection(getEt_content().getText().length());
            }
            if (style.getPlaceholderText() != null) {
                getEt_content().setHint(style.getPlaceholderText());
            }
            if (!TextUtils.isEmpty(style.getTitle())) {
                getTv_title().setVisibility(0);
                getTv_title().setText(style.getTitle());
            } else {
                getTv_title().setVisibility(8);
            }
        } else {
            getEt_content().setVisibility(8);
            if (!TextUtils.isEmpty(style.getTitle())) {
                getTv_title().setVisibility(0);
                getTv_title().setText(style.getTitle());
            } else {
                getTv_title().setVisibility(8);
            }
            if (!TextUtils.isEmpty(style.getContent())) {
                getTv_content().setVisibility(0);
                getTv_content().setText(style.getContent());
                if (TextUtils.isEmpty(style.getTitle())) {
                    if (UTSAndroid.INSTANCE.getAppDarkMode()) {
                        TextView tv_content = getTv_content();
                        Context appContext = UTSAndroid.INSTANCE.getAppContext();
                        Intrinsics.checkNotNull(appContext);
                        tv_content.setTextColor(appContext.getResources().getColor(R.color.uni_app_uni_prompt_dialog_title_text_night));
                    } else {
                        TextView tv_content2 = getTv_content();
                        Context appContext2 = UTSAndroid.INSTANCE.getAppContext();
                        Intrinsics.checkNotNull(appContext2);
                        tv_content2.setTextColor(appContext2.getResources().getColor(R.color.uni_app_uni_prompt_dialog_title_text));
                    }
                }
            } else if (!TextUtils.isEmpty(style.getTitle())) {
                getTv_title().setVisibility(8);
                getTv_content().setVisibility(0);
                getTv_content().setText(style.getTitle());
                if (UTSAndroid.INSTANCE.getAppDarkMode()) {
                    TextView tv_content3 = getTv_content();
                    Context appContext3 = UTSAndroid.INSTANCE.getAppContext();
                    Intrinsics.checkNotNull(appContext3);
                    tv_content3.setTextColor(appContext3.getResources().getColor(R.color.uni_app_uni_prompt_dialog_title_text_night));
                } else {
                    TextView tv_content4 = getTv_content();
                    Context appContext4 = UTSAndroid.INSTANCE.getAppContext();
                    Intrinsics.checkNotNull(appContext4);
                    tv_content4.setTextColor(appContext4.getResources().getColor(R.color.uni_app_uni_prompt_dialog_title_text));
                }
            } else {
                getTv_content().setVisibility(8);
            }
        }
        if (style.getCancelText() != null) {
            getTvCancelAction().setText(style.getCancelText());
        }
        if (style.getCancelColor() != null) {
            try {
                getTvCancelAction().setTextColor(Color.parseColor(style.getCancelColor()));
            } catch (Throwable unused) {
            }
        }
        if (style.getConfirmText() != null) {
            getTvSureAction().setText(style.getConfirmText());
        }
        if (style.getConfirmColor() != null) {
            try {
                getTvSureAction().setTextColor(Color.parseColor(style.getConfirmColor()));
            } catch (Throwable unused2) {
            }
        }
        UTSDialog uTSDialog = this;
        getTvCancelAction().setOnClickListener(uTSDialog);
        getTvSureAction().setOnClickListener(uTSDialog);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Function1<Object, Unit> complete;
        Function1<ShowModalSuccess, Unit> success;
        Intrinsics.checkNotNullParameter(view, "view");
        ShowModalSuccess showModalSuccess = new ShowModalSuccess(true, false, null, 4, null);
        if (view.getId() == R.id.tvCancelAction) {
            showModalSuccess.setConfirm(false);
            showModalSuccess.setCancel(true);
        } else if (view.getId() == R.id.tvSureAction) {
            String string = getEt_content().getEditableText().toString();
            if (TextUtils.isEmpty(string)) {
                showModalSuccess.setContent(null);
            }
            showModalSuccess.setContent(string);
        }
        ShowModalOptions hostStyle = getHostStyle();
        if (hostStyle != null && (success = hostStyle.getSuccess()) != null) {
            success.invoke(showModalSuccess);
        }
        ShowModalOptions hostStyle2 = getHostStyle();
        if (hostStyle2 != null && (complete = hostStyle2.getComplete()) != null) {
            complete.invoke(showModalSuccess);
        }
        if (getHostStyle() != null) {
            ShowModalOptions hostStyle3 = getHostStyle();
            Intrinsics.checkNotNull(hostStyle3);
            hostStyle3.setComplete(null);
        }
        dismiss();
    }
}
