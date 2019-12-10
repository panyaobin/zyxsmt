package com.ruoyi.project.smt.dzl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 电子料信息对象 smt_dzl
 * 
 * @author popo
 * @date 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtDzl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号
 */
    private Integer id;

    /** 电子料名称 */
    @Excel(name = "电子料名称")
    private String dzlName;

    /** 主类型(1为FPC,2为电子料) */
    private String mainType;

    /** 电子料类型 */
    @Excel(name = "电子料类型")
    private String typeName;

    /** 角色状态（0正常 1停用） */
    //@Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在1代表删除） */
    private String delFlag;

    /** 备注 */
    @Excel(name = "说明",width = 30)
    private String remark;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setDzlName(String dzlName) 
    {
        this.dzlName = dzlName;
    }

    public String getDzlName() 
    {
        return dzlName;
    }
    public void setMainType(String mainType) 
    {
        this.mainType = mainType;
    }

    public String getMainType() 
    {
        return mainType;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
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
            .append("dzlName", getDzlName())
            .append("mainType", getMainType())
            .append("typeName", getTypeName())
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
