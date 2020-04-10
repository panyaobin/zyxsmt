package com.ruoyi.project.smt.applyrecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 应付账目付款记录对象 smt_apply_record
 * 
 * @author popo
 * @date 2020-04-09
 */
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
    private Double paymentAmount;

    /** 手续费 */
    @Excel(name = "手续费")
    private Double handFee;

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

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setPaymentNo(Long paymentNo) 
    {
        this.paymentNo = paymentNo;
    }

    public Long getPaymentNo() 
    {
        return paymentNo;
    }
    public void setPaymentAmount(Double paymentAmount) 
    {
        this.paymentAmount = paymentAmount;
    }

    public Double getPaymentAmount() 
    {
        return paymentAmount;
    }
    public void setHandFee(Double handFee) 
    {
        this.handFee = handFee;
    }

    public Double getHandFee() 
    {
        return handFee;
    }
    public void setPaymentWay(String paymentWay) 
    {
        this.paymentWay = paymentWay;
    }

    public String getPaymentWay() 
    {
        return paymentWay;
    }
    public void setPaymentName(String paymentName) 
    {
        this.paymentName = paymentName;
    }

    public String getPaymentName() 
    {
        return paymentName;
    }
    public void setPaymentNumber(String paymentNumber) 
    {
        this.paymentNumber = paymentNumber;
    }

    public String getPaymentNumber() 
    {
        return paymentNumber;
    }
    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paymentNo", getPaymentNo())
            .append("paymentAmount", getPaymentAmount())
            .append("handFee", getHandFee())
            .append("paymentWay", getPaymentWay())
            .append("paymentName", getPaymentName())
            .append("paymentNumber", getPaymentNumber())
            .append("paymentTime", getPaymentTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
