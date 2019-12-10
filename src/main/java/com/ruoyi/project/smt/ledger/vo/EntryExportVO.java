package com.ruoyi.project.smt.ledger.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/12/9 22:52
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class EntryExportVO {

    //客户编码
    private String cusCode;
    //电子料名称
    private String dzlName;
    //来料日期
    private String entryTime;
    //订单号
    private String orderNo;
    //来料数量
    private Integer orderQty;
}
