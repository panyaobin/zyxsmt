package com.ruoyi.project.smt.financereport.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 现金日记账vo
 * @Author Administrator
 * @Date 2020/4/19 13:00
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class CashJournalVO {

    //账单号
    private Long reconciliationNo;

    //账单月份
    private Date reconciliationTime;

    //费用类型
    private String feeType;

    //创建日期
    private Date createTime;

    //收款
    private BigDecimal receiveFee;

    //付款
    private BigDecimal paymentFee;

    //手续费
    private BigDecimal handFee;

    //备注
    private String remark;
}
