package com.ruoyi.project.smt.paymentinfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 付款信息对象 smt_payment_info
 * 
 * @author popo
 * @date 2020-04-06
 */
public class SmtPaymentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer id;

    /** 收款单位 */
    @Excel(name = "收款单位")
    private String collectionUnit;

    /** 户名 */
    @Excel(name = "户名")
    private String accountName;

    /** 卡号 */
    @Excel(name = "卡号")
    private String accountNumber;

    /** 开户银行 */
    @Excel(name = "开户银行")
    private String accountBank;

    /** 状态 */
    @Excel(name = "状态")
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
    public void setCollectionUnit(String collectionUnit) 
    {
        this.collectionUnit = collectionUnit;
    }

    public String getCollectionUnit() 
    {
        return collectionUnit;
    }
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }
    public void setAccountNumber(String accountNumber) 
    {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() 
    {
        return accountNumber;
    }
    public void setAccountBank(String accountBank) 
    {
        this.accountBank = accountBank;
    }

    public String getAccountBank() 
    {
        return accountBank;
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
            .append("collectionUnit", getCollectionUnit())
            .append("accountName", getAccountName())
            .append("accountNumber", getAccountNumber())
            .append("accountBank", getAccountBank())
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
