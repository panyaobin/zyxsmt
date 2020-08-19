package com.ruoyi.project.smt.financereport.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
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

    @Excel(name = "单号")
    private Long reconciliationNo;

    /**
     * 账单月份
     */
    private Date reconciliationTime;

    @Excel(name = "账单月份")
    private String reconciliationTimeStr;


    @Excel(name = "类型")
    private String feeType;


    /**
     * 日期
     */
    private Date createTime;

    /**
     * 付款日期
     */
    private Date paymentTime;

    @Excel(name = "日期")
    private String createTimeStr;


    @Excel(name = "收款")
    private BigDecimal receiveFee;


    @Excel(name = "付款")
    private BigDecimal paymentFee;


    @Excel(name = "手续费")
    private BigDecimal handFee;


    @Excel(name = "备注")
    private String remark;
}
