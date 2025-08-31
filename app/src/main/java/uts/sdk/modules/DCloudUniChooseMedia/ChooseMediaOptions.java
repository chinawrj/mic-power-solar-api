package uts.sdk.modules.DCloudUniChooseMedia;

import androidx.core.app.FrameMetricsAggregator;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0016\u0018\u00002\u00020\u0001Bù\u0001\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012+\b\u0002\u0010\f\u001a%\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rj\u0004\u0018\u0001`\u0013\u0012>\b\u0002\u0010\u0014\u001a8\u0012&\u0012$0\u0015j\u0011`\u0016¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rj\u0004\u0018\u0001`\u0017\u0012+\b\u0002\u0010\u0018\u001a%\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rj\u0004\u0018\u0001`\u001a¢\u0006\u0002\u0010\u001bR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR=\u0010\u0018\u001a%\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rj\u0004\u0018\u0001`\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'RP\u0010\u0014\u001a8\u0012&\u0012$0\u0015j\u0011`\u0016¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rj\u0004\u0018\u0001`\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010%\"\u0004\b+\u0010'R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u0010\u0002\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001d\"\u0004\b1\u0010\u001fR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010-\"\u0004\b3\u0010/R=\u0010\f\u001a%\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rj\u0004\u0018\u0001`\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010!\"\u0004\b5\u0010#¨\u00066"}, d2 = {"Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaOptions;", "Lio/dcloud/uts/UTSObject;", "pageOrientation", "", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaPageOrientation;", "count", "", "mediaType", "Lio/dcloud/uts/UTSArray;", "sourceType", "maxDuration", "camera", WXImage.SUCCEED, "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaSuccess;", "Lkotlin/ParameterName;", "name", WXBridgeManager.METHOD_CALLBACK, "", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaSuccessCallback;", Constants.Event.FAIL, "Luts/sdk/modules/DCloudUniChooseMedia/IChooseMediaError;", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaFail;", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaFailCallback;", "complete", "", "Luts/sdk/modules/DCloudUniChooseMedia/ChooseMediaCompleteCallback;", "(Ljava/lang/String;Ljava/lang/Number;Lio/dcloud/uts/UTSArray;Lio/dcloud/uts/UTSArray;Ljava/lang/Number;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getCamera", "()Ljava/lang/String;", "setCamera", "(Ljava/lang/String;)V", "getComplete", "()Lkotlin/jvm/functions/Function1;", "setComplete", "(Lkotlin/jvm/functions/Function1;)V", "getCount", "()Ljava/lang/Number;", "setCount", "(Ljava/lang/Number;)V", "getFail", "setFail", "getMaxDuration", "setMaxDuration", "getMediaType", "()Lio/dcloud/uts/UTSArray;", "setMediaType", "(Lio/dcloud/uts/UTSArray;)V", "getPageOrientation", "setPageOrientation", "getSourceType", "setSourceType", "getSuccess", "setSuccess", "uni-chooseMedia_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ChooseMediaOptions extends UTSObject {
    private String camera;
    private Function1<Object, Unit> complete;
    private Number count;
    private Function1<? super IChooseMediaError, Unit> fail;
    private Number maxDuration;
    private UTSArray<String> mediaType;
    private String pageOrientation;
    private UTSArray<String> sourceType;
    private Function1<? super ChooseMediaSuccess, Unit> success;

    public ChooseMediaOptions() {
        this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public /* synthetic */ ChooseMediaOptions(String str, Number number, UTSArray uTSArray, UTSArray uTSArray2, Number number2, String str2, Function1 function1, Function1 function12, Function1 function13, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : number, (i & 4) != 0 ? null : uTSArray, (i & 8) != 0 ? null : uTSArray2, (i & 16) != 0 ? null : number2, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : function1, (i & 128) != 0 ? null : function12, (i & 256) == 0 ? function13 : null);
    }

    public String getPageOrientation() {
        return this.pageOrientation;
    }

    public void setPageOrientation(String str) {
        this.pageOrientation = str;
    }

    public Number getCount() {
        return this.count;
    }

    public void setCount(Number number) {
        this.count = number;
    }

    public UTSArray<String> getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(UTSArray<String> uTSArray) {
        this.mediaType = uTSArray;
    }

    public UTSArray<String> getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(UTSArray<String> uTSArray) {
        this.sourceType = uTSArray;
    }

    public Number getMaxDuration() {
        return this.maxDuration;
    }

    public void setMaxDuration(Number number) {
        this.maxDuration = number;
    }

    public String getCamera() {
        return this.camera;
    }

    public void setCamera(String str) {
        this.camera = str;
    }

    public Function1<ChooseMediaSuccess, Unit> getSuccess() {
        return this.success;
    }

    public void setSuccess(Function1<? super ChooseMediaSuccess, Unit> function1) {
        this.success = function1;
    }

    public Function1<IChooseMediaError, Unit> getFail() {
        return this.fail;
    }

    public void setFail(Function1<? super IChooseMediaError, Unit> function1) {
        this.fail = function1;
    }

    public Function1<Object, Unit> getComplete() {
        return this.complete;
    }

    public void setComplete(Function1<Object, Unit> function1) {
        this.complete = function1;
    }

    public ChooseMediaOptions(String str, Number number, UTSArray<String> uTSArray, UTSArray<String> uTSArray2, Number number2, String str2, Function1<? super ChooseMediaSuccess, Unit> function1, Function1<? super IChooseMediaError, Unit> function12, Function1<Object, Unit> function13) {
        this.pageOrientation = str;
        this.count = number;
        this.mediaType = uTSArray;
        this.sourceType = uTSArray2;
        this.maxDuration = number2;
        this.camera = str2;
        this.success = function1;
        this.fail = function12;
        this.complete = function13;
    }
}
