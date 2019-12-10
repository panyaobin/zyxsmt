package com.ruoyi.project.smt.bomLine.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 客户bom明细对象 smt_bom_line
 * 
 * @author popo
 * @date 2019-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtBomLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** BOM主id */
    @Excel(name = "BOM主id")
    private Integer bomId;

    /** 电子料id */
    @Excel(name = "电子料id")
    private Integer dzlId;

    /**
     * 电子料名称
     */
    private String dzlName;

    /**
     * 电子料类型名称
     */
    private String typeName;
    /**
     * 总用量
     */
    private Integer counts;

    /** 用量 */
    @Excel(name = "用量")
    private Integer dzlNumber;

    /** 件位 */
    @Excel(name = "件位")
    private String position;

    /** 备品 */
    @Excel(name = "备品")
    private Integer bak;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在1代表删除） */
    private String delFlag;

    public String getDzlName() {
        return dzlName;
    }

    public void setDzlName(String dzlName) {
        this.dzlName = dzlName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setBomId(Integer bomId) 
    {
        this.bomId = bomId;
    }

    public Integer getBomId() 
    {
        return bomId;
    }
    public void setDzlId(Integer dzlId) 
    {
        this.dzlId = dzlId;
    }

    public Integer getDzlId() 
    {
        return dzlId;
    }
    public void setDzlNumber(Integer dzlNumber) 
    {
        this.dzlNumber = dzlNumber;
    }

    public Integer getDzlNumber() 
    {
        return dzlNumber;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setBak(Integer bak) 
    {
        this.bak = bak;
    }

    public Integer getBak() 
    {
        return bak;
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
            .append("bomId", getBomId())
            .append("dzlId", getDzlId())
            .append("dzlNumber", getDzlNumber())
            .append("position", getPosition())
            .append("bak", getBak())
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
