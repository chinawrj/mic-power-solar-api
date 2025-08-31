package com.taobao.weex.utils.tools;

import com.alibaba.fastjson.annotation.JSONField;
import com.taobao.weex.common.RenderTypes;

/* loaded from: classes3.dex */
public class Info {

    @JSONField(name = "instanceId")
    public String instanceId;

    @JSONField(name = RenderTypes.RENDER_TYPE_NATIVE)
    public String platform;

    @JSONField(name = "taskId")
    public int taskId;

    @JSONField(name = "taskInfo")
    public TaskInfo taskInfo = new TaskInfo();

    @JSONField(name = "taskName")
    public String taskName;

    public String toString() {
        return "Info : {instanceId = '" + this.instanceId + "',taskName = '" + this.taskName + "',taskInfo = '" + this.taskInfo + "',platform = '" + this.platform + "',taskId = '" + this.taskId + "'}";
    }
}
