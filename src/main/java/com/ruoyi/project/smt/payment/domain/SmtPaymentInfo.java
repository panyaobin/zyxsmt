package com.ruoyi.project.smt.payment.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 付款信息对象 smt_payment_info
 * 
 * @author popo
 * @date 2019-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtPaymentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
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

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /**
     * dsadss
     */
    private String createDate2;

    /** 更新时间 */
    private Date updateDate;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String remarks;

    /** 删除标记 */
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
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
