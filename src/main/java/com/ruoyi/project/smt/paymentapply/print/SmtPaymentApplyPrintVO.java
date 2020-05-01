package com.ruoyi.project.smt.paymentapply.print;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 付款申请单打印vo
 * @author popo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtPaymentApplyPrintVO {

    /** 序号 */
    private Integer id;

    /** 付款单号 */
    private String paymentNo;

    /**
     * 创建日期
     */
    private Date createTime;

    /** 收款单位 */
    private String collectionUnit;

    /** 户名 */
    private String accountName;

    /*卡号*/
    private String accountNumber;

    /** 开户银行 */
    private String accountBank;


    /**
     * 金额
     */
    private BigDecimal applyAmount;

    /**
     * 付款原因
     */
    private String paymentReason;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createBy;

}
