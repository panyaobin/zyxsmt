package com.ruoyi.project.smt.applyrecord.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 应付账目付款记录对象 smt_apply_record
 * 
 * @author popo
 * @date 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtApplyRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer id;

    /** 付款申请单号 */
    @Excel(name = "付款申请单号")
    private Long paymentNo;

    /** 付款金额 */
    @Excel(name = "付款金额")
    private BigDecimal paymentAmount;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal handFee;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String paymentWay;

    /** 付款对象户名 */
    @Excel(name = "付款对象户名")
    private String paymentName;

    /** 付款账号 */
    @Excel(name = "付款账号")
    private String paymentNumber;

    /** 付款日期 */
    @Excel(name = "付款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在1代表删除） */
    private String delFlag;

    //费用类型
    private String paymentType;

    //账单月份
    private Date reconciliationTime;


}
