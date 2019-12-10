package com.ruoyi.project.smt.ship.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 产品出货对象 smt_product_ship
 * 
 * @author popo
 * @date 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtProductShip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Integer id;

    /** 出货单号 */
    private Integer shipNo;

    /**
     * DZL出货列表查询所用出货单号
     */
    private String shipSearchNo;
    /**
     * DZL出货列表查询所用出货单号
     */
    private String cusSearchCode;


    /**
     * 打印所需出货单号
     */
    private Integer shipPrintNo;

    /** 客户编码 */
    private Integer cusCode;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String cusName;

    /**
     * 导出所需要出货编号
     */
    @Excel(name = "出货单号")
    private String shipExportNo;


    /**
     * 电子料名称
     */
    @Excel(name = "电子料名称")
    private String dzlName;

    /**
     * 物料类型
     */
    @Excel(name = "物料类型")
    private String typeName;

    /**
     * 用量
     */
    @Excel(name = "用量")
    private Integer dzlNumber;


    /** 出货数量(FPC为数量,dzl为总用量) */
    @Excel(name = "数量")
    private Integer shipQty;

    /** 备品 */
    @Excel(name = "备品")
    private Integer bak;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /**
     * 物料名称
     */
    @Excel(name = "FPC型号")
    private String bomName;

    /**
     * fpc出货数量
     */
    @Excel(name = "FPC数量")
    private Integer fpcShipQty;

    /**
     * FPC出货总点数
     */
    private Integer fpcShipPoint;




    /** 订单类型(1.FPC,2.电子料) */
    private Integer orderType;

    /** 物料ID(bomId或者dzlId) */
    private Integer bomId;

    /** 出货类型(1.退货 2.出货) */
    private Integer shipType;

    /**
     * 出货时间
     */
    @Excel(name = "出货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 删除标志（0代表存在1代表删除） */
    private String delFlag;

    /**
     * 出货信息.包含退货
     */
    private String shipList;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setShipNo(Integer shipNo)
    {
        this.shipNo = shipNo;
    }

    public Integer getShipNo()
    {
        return shipNo;
    }
    public void setCusCode(Integer cusCode) 
    {
        this.cusCode = cusCode;
    }

    public Integer getCusCode() 
    {
        return cusCode;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
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
    public void setShipType(Integer shipType) 
    {
        this.shipType = shipType;
    }

    public Integer getShipType() 
    {
        return shipType;
    }
    public void setShipQty(Integer shipQty) 
    {
        this.shipQty = shipQty;
    }

    public Integer getShipQty() 
    {
        return shipQty;
    }
    public void setDzlNumber(Integer dzlNumber) 
    {
        this.dzlNumber = dzlNumber;
    }

    public Integer getDzlNumber() 
    {
        return dzlNumber;
    }
    public void setBak(Integer bak) 
    {
        this.bak = bak;
    }

    public Integer getBak() 
    {
        return bak;
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
            .append("shipNo", getShipNo())
            .append("cusCode", getCusCode())
            .append("orderNo", getOrderNo())
            .append("orderType", getOrderType())
            .append("bomId", getBomId())
            .append("shipType", getShipType())
            .append("shipQty", getShipQty())
            .append("dzlNumber", getDzlNumber())
            .append("bak", getBak())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
