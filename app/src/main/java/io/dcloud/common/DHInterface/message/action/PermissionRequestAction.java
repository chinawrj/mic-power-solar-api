package io.dcloud.common.DHInterface.message.action;

/* loaded from: classes3.dex */
public class PermissionRequestAction implements IAction {
    public static String TYPE_COMPLETE = "complete";
    public static String TYPE_CONFIRM = "confirm";
    public static String TYPE_REQUEST = "request";
    private String[] permissions;
    private final String type;

    private PermissionRequestAction(String str, String[] strArr) {
        this.type = str;
        this.permissions = strArr;
    }

    public static PermissionRequestAction obtain(String str, String[] strArr) {
        return new PermissionRequestAction(str, strArr);
    }

    public String[] getPermissions() {
        return this.permissions;
    }

    public String getType() {
        return this.type;
    }
}
