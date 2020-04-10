package com.ruoyi.project.smt.reconciliation.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 对账管理对象 smt_reconciliation
 *
 * @author popo
 * @date 2020-04-05
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SmtReconciliation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 对账单号
     */
    @Excel(name = "对账单号")
    private Integer reconciliationNo;

    /**
     * 客户代码
     */
    @Excel(name = "客户代码")
    private Long cusCode;

    /**
     * 对账日期
     */
    @Excel(name = "对账日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reconciliationTime;

    /**
     * 费用类型
     */
    @Excel(name = "费用类型")
    private String feeType;

    /**
     * 对账金额
     */
    @Excel(name = "对账金额")
    private BigDecimal reconciliationAmount;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 是否回传(0,未回传 1已回传)
     */
    @Excel(name = "是否回传(0,未回传 1已回传)")
    private String isBack;

    /**
     * 是否结清(0,未结清 1已结清)
     */
    @Excel(name = "是否结清(0,未结清 1已结清)")
    private String isSettle;

    /**
     * 客户确认金额
     */
    @Excel(name = "客户确认金额")
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
    @Excel(name = "扣款金额")
    private BigDecimal deductionAmount;

    /**
     * 删除标志（0代表存在1代表删除）
     */
    private String delFlag;


}
