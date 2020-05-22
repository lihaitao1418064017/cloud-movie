package org.lht.boot.lang;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author: Songlin Ding
 * @Date: Created in 15:59 2018/7/30
 * @Description: 部门统计VO
 */
@Data
public class DeviceSummaryVO {

    //    private String deptCode;

    private String deptName;

    //    private Long peopleNum;
    //
    //    private Long deviceTotalNum;
    //
    //    private Long deviceEnableNum;
    //
    //    private Long deviceDisableNum;
    //
    //    private Long stationTotalNum;
    //
    //    private Long stationEnableNum;
    //
    //    private Long stationDisableNum;

    /**
     * 部门path
     */
    private String deptPath;

    /**
     * 上级部门id
     */
    private String deptParentId;

    private String deptId;

    private List<DeviceSummaryVO> children = Lists.newArrayList();

    private Integer num = 0;

}
