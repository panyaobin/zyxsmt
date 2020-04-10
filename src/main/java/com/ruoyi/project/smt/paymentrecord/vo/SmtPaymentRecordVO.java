package com.ruoyi.project.smt.paymentrecord.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.project.smt.paymentrecord.domain.SmtPaymentRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 付款记录vo
 * @author popo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtPaymentRecordVO extends SmtPaymentRecord {

    /**
     * 客户代码
     */
    private Long cusCode;

    /**
     * 对账日期
     */
    private Date reconciliationTime;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 对账金额
     */
    private BigDecimal reconciliationAmount;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 是否回传(0,未回传 1已回传)
     */
    private String isBack;

    /**
     * 是否结清(0,未结清 1已结清)
     */
    private String isSettle;

    /**
     * 客户确认金额
     */
    private BigDecimal confirmAmount;

    /**
     * 已付金额
     */
    private BigDecimal paidAmount;

    /**
     * 欠款
     */
    private BigDecimal arrears;

    /**
     * 扣款金额
     */
    private BigDecimal deductionAmount;
}
