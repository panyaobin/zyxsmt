package com.ruoyi.project.smt.delivery.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 发料记录对象 smt_delivery_record
 * 
 * @author popo
 * @date 2019-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtDeliveryRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Integer id;

    /** 发料单号 */
    @Excel(name = "发料单号")
    private Integer deliveryNo;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private Integer cusCode;

    /**
     * 客户名称
     */
    private String cusName;

    /**
     * bom名称
     */
    private String bomName;

    /**
     * 电子料名称
     */
    private String dzlName;

    /**
     * 电子料类型名称
     */
    private String typeName;

    /**
     * 总在线数量
     */
    private Integer sumDeliveryQty;

    /**
     * 总备品数
     */
    private Integer sumBak;



    /**
     * 订单号
     */
    private String orderNo;

    /** 订单类型(1.FPC,2.电子料) */
    @Excel(name = "订单类型(1.FPC,2.电子料)")
    private Integer orderType;

    /** 物料ID(bomId或者dzlId) */
    @Excel(name = "物料ID(bomId或者dzlId)")
    private Integer bomId;

    /** 发料数量 */
    @Excel(name = "发料数量")
    private Integer deliveryQty;

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
    public void setDeliveryNo(Integer deliveryNo)
    {
        this.deliveryNo = deliveryNo;
    }

    public Integer getDeliveryNo()
    {
        return deliveryNo;
    }
    public void setCusCode(Integer cusCode) 
    {
        this.cusCode = cusCode;
    }

    public Integer getCusCode() 
    {
        return cusCode;
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
    public void setDeliveryQty(Integer deliveryQty) 
    {
        this.deliveryQty = deliveryQty;
    }

    public Integer getDeliveryQty() 
    {
        return deliveryQty;
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
            .append("deliveryNo", getDeliveryNo())
            .append("cusCode", getCusCode())
            .append("orderType", getOrderType())
            .append("bomId", getBomId())
            .append("deliveryQty", getDeliveryQty())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
