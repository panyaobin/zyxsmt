package com.ruoyi.project.smt.paymentapply.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 付款申请对象 smt_payment_apply
 * 
 * @author popo
 * @date 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtPaymentApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer id;

    /** 付款单号 */
    @Excel(name = "付款单号")
    private Integer paymentNo;

    /** 收款单位 */
    @Excel(name = "收款单位")
    private Integer receiveId;

    /** 付款类型 */
    @Excel(name = "付款类型")
    private Integer paymentType;

    /** 申请金额 */
    @Excel(name = "申请金额")
    private BigDecimal applyAmount;

    /** 付款原因 */
    @Excel(name = "付款原因")
    private String paymentReason;

    /** 是否结清(0未结清 1已结清) */
    @Excel(name = "是否结清(0未结清 1已结清)")
    private String isSettle;

    /** 状态(0启用 1禁用) */
    @Excel(name = "状态(0启用 1禁用)")
    private String status;

    /** 删除标志（0代表存在1代表删除） */
    private String delFlag;

    /**
     * 收款单位
     */
    private String collectionUnit;
    /**
     * 户名
     */
    private String accountName ;
    /**
     * 开户账号
     */
    private String accountNumber;
    /**
     * 开户银行
     */
    private String accountBank;

    /**
     * 已付金额
     */
    private BigDecimal paidAmount;

    /**
     * 欠款
     */
    private BigDecimal arrears;

}
