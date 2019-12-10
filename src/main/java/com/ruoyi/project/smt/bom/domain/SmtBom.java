package com.ruoyi.project.smt.bom.domain;

import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 客户bom信息对象 smt_bom
 *
 * @author popo
 * @date 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtBom extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 客户代码
     */
    //@Excel(name = "客户代码")
    private Integer cusCode;

    /**
     * 客户代码
     */
    @Excel(name = "客户名称")
    private String cusName;

    /**
     * BOM名称
     */
    @Excel(name = "BOM名称")
    private String bomName;

    /**
     * BOM总点数
     */
    @Excel(name = "BOM总点数")
    private Long bomPoint;

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
     * 电子料名称
     */
    private String dzlName;

    /**
     * FPC发货量
     */
    private Integer count;

    /**
     * bom明细集合字符串
     */
    private String bomLineList;

    /**
     * bom明细集合
     */
    private List<SmtBomLine> bomLines;

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

    public void setBomName(String bomName) {
        this.bomName = bomName;
    }

    public String getBomName() {
        return bomName;
    }

    public void setBomPoint(Long bomPoint) {
        this.bomPoint = bomPoint;
    }

    public Long getBomPoint() {
        return bomPoint;
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
                .append("bomName", getBomName())
                .append("bomPoint", getBomPoint())
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
