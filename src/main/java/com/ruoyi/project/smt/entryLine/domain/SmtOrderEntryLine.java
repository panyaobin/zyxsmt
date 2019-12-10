package com.ruoyi.project.smt.entryLine.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 订单入库明细对象 smt_order_entry_line
 * 
 * @author popo
 * @date 2019-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtOrderEntryLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer id;

    /** 入库主id */
    @Excel(name = "入库主id")
    private Integer entryId;

    /** 订单类型(1.FPC,2.电子料) */
    @Excel(name = "订单类型(1.FPC,2.电子料)")
    private Integer orderType;

    /** 电子料类型(0.FPC,其他是bom) */
    @Excel(name = "电子料类型(0.FPC,其他是bom)")
    private Integer bomId;

    /** 电子料名称 */
    private String bomName;

    /** 数量 */
    @Excel(name = "数量")
    private Integer orderQty;

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
    public void setEntryId(Integer entryId) 
    {
        this.entryId = entryId;
    }

    public Integer getEntryId() 
    {
        return entryId;
    }
    public void setOrderType(Integer orderType) 
    {
        this.orderType = orderType;
    }

    public Integer getOrderType() 
    {
        return orderType;
    }
    public void setBomId(Integer bomId) 
    {
        this.bomId = bomId;
    }

    public Integer getBomId() 
    {
        return bomId;
    }
    public void setOrderQty(Integer orderQty) 
    {
        this.orderQty = orderQty;
    }

    public Integer getOrderQty() 
    {
        return orderQty;
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
            .append("entryId", getEntryId())
            .append("orderType", getOrderType())
            .append("bomId", getBomId())
            .append("orderQty", getOrderQty())
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
