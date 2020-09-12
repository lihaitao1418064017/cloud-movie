package org.hhy.xxl.job.admin.core.alarm;

import org.hhy.xxl.job.admin.core.model.XxlJobInfo;
import org.hhy.xxl.job.admin.core.model.XxlJobLog;

/**
 * @author xuxueli 2020-01-19
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
