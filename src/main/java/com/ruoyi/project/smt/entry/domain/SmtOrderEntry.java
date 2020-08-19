package com.ruoyi.project.smt.entry.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 订单入库对象 smt_order_entry
 *
 * @author popo
 * @date 2019-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtOrderEntry extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 客户代码
     */
    @Excel(name = "客户代码")
    private Integer cusCode;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String cusName;

    /**
     * 电子料名称
     */
    private String dzlName;

    /**
     * 型号
     */
    private String typeNo;

    /**
     * 物料id,orderType是1时，指FPC 客户bomId
     * 物料id,orderType是2时，指dzl 的bomId
     */
    private Integer bomId;

    /**
     * bom名称
     */
    private String bomName;

    /**
     * 产品型号
     */
    @Excel(name = "产品型号")
    private String orderNo;

    /**
     * 入库时间
     */
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /**
     * 角色状态（0正常 1停用）
     */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在1代表删除）
     */
    private String delFlag;

    /**
     * 入库明细集合
     */
    private String entryLineList;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 订单类型(1.FPC,2.电子料)
     */
    private Integer orderType;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCusCode(Integer cusCode) {
        this.cusCode = cusCode;
    }

    public Integer getCusCode() {
        return cusCode;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("cusCode", getCusCode())
                .append("orderNo", getOrderNo())
                .append("entryTime", getEntryTime())
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
