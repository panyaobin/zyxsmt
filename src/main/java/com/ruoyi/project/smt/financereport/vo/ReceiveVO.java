package com.ruoyi.project.smt.financereport.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 现结日记账付款查询条件VO
 * @author popo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReceiveVO {

    /**
     * 账单号
     */
    private String reconciliationNo;

    /**
     * 账单日期
     */
    private String reconciliationTime;

    /**
     * 收款类型
     */
    private String receiveType;
}
