package com.ruoyi.project.smt.cus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 客户信息对象 smt_cus
 * 
 * @author popo
 * @date 2019-10-19
 */
public class SmtCus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer id;

    /** 客户代码 */
    @Excel(name = "客户代码")
    private Integer cusCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String cusName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String cusContacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String cusTel;

    /** 对帐日期 */
    @Excel(name = "对帐日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cusStatement;

    /** 角色状态（0正常 1停用） */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
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
    public void setCusCode(Integer cusCode) 
    {
        this.cusCode = cusCode;
    }

    public Integer getCusCode() 
    {
        return cusCode;
    }
    public void setCusName(String cusName) 
    {
        this.cusName = cusName;
    }

    public String getCusName() 
    {
        return cusName;
    }
    public void setCusContacts(String cusContacts) 
    {
        this.cusContacts = cusContacts;
    }

    public String getCusContacts() 
    {
        return cusContacts;
    }
    public void setCusTel(String cusTel) 
    {
        this.cusTel = cusTel;
    }

    public String getCusTel() 
    {
        return cusTel;
    }
    public void setCusStatement(Date cusStatement) 
    {
        this.cusStatement = cusStatement;
    }

    public Date getCusStatement() 
    {
        return cusStatement;
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
            .append("cusCode", getCusCode())
            .append("cusName", getCusName())
            .append("cusContacts", getCusContacts())
            .append("cusTel", getCusTel())
            .append("cusStatement", getCusStatement())
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
