package com.ruoyi.project.smt.entry.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/11/3 11:37
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtOrderEntryVO {
    /**
     * 客户编码
     */
    private Integer cusCode;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String cusName;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 入库时间
     */
    private Date entryTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 入库明细序列ID
     */
    private Integer id;
    /**
     * 主入库单id
     */
    private Integer entryId;
    /**
     * 订单类型（1.PFC 2.电子料）
     */
    private Integer orderType;
    /**
     * 类型ID
     * orderType为1时，这个值表示的是客户bomId
     * orderType为2时，这个值表示的是电子料dzlId
     */
    private Integer bomId;

    /**
     * bom名称
     */
    private String bomName;

    /**
     * 电子料名称
     */
    @Excel(name = "产品型号")
    private String dzlName;

    /**
     * 类型ID
     * orderType为1时，FPC
     * orderType为2时，物料类型
     */
    @Excel(name = "类型")
    private String typeName;

    /**
     * 类型ID
     * orderType为1时，FPC
     * orderType为2时，物料名称
     */
    private String typeNo;

    /**
     * 数量
     */
    private Integer orderQty;

    /**
     * 总数量
     */
    @Excel(name = "数量")
    private Integer sumOrderQty;

    /**
     * 说明
     */
    private String remark;

}
