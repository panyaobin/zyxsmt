package com.ruoyi.project.smt.bom.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 客户bom信息对象 smt_bom
 *
 * @author popo
 * @date 2019-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtBomVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private Integer id;
    /**
     * bomId
     */
    private Integer bomId;

    /**
     * 客户代码
     */
    private Integer cusCode;

    /**
     * 客户代码
     */
    @Excel(name = "客户名称")
    private String cusName;

    /**
     * 客户代码
     */
    @Excel(name = "bom名称")
    private String bomName;

    /**
     * BOM总点数
     */
    @Excel(name = "BOM总点数")
    private Long bomPoint;

    /**
     * 电子料名称
     */
    @Excel(name = "电子料名称")
    private String dzlName;

    /**
     * 电子料类型名称
     */
    @Excel(name = "类型")
    private String typeName;

    /** 电子料id */
    private Integer dzlId;

    /** 用量 */
    @Excel(name = "用量")
    private Integer dzlNumber;

    /** 件位 */
    @Excel(name = "件位")
    private String position;

    /** 备品 */
    @Excel(name = "备品")
    private Integer bak;


    /**
     * 角色状态（0正常 1停用）
     */
    //@Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * bom明细表备注信息
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * bom明细表备注信息
     */
    @Excel(name = "说明")
    private String remarks;

    /**
     * 删除标志（0代表存在1代表删除）
     */
    private String delFlag;


    /**
     * bom类型（1.FPC 2.DZL）
     */
    private Integer orderType;

}
