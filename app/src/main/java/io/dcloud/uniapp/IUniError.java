package io.dcloud.uniapp;

import com.taobao.weex.adapter.IWXUserTrackAdapter;
import io.dcloud.uts.UTSError;
import kotlin.Metadata;

/* compiled from: UniError.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0004\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lio/dcloud/uniapp/IUniError;", "Lio/dcloud/uniapp/AsyncApiResult;", "cause", "Lio/dcloud/uts/UTSError;", "getCause", "()Lio/dcloud/uts/UTSError;", "setCause", "(Lio/dcloud/uts/UTSError;)V", "data", "", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", IWXUserTrackAdapter.MONITOR_ERROR_CODE, "", "getErrCode", "()Ljava/lang/Number;", "setErrCode", "(Ljava/lang/Number;)V", "errSubject", "", "getErrSubject", "()Ljava/lang/String;", "setErrSubject", "(Ljava/lang/String;)V", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface IUniError extends AsyncApiResult {
    UTSError getCause();

    Object getData();

    Number getErrCode();

    String getErrSubject();

    void setCause(UTSError uTSError);

    void setData(Object obj);

    void setErrCode(Number number);

    void setErrSubject(String str);
}
