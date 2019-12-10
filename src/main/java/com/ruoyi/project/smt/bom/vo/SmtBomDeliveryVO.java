package com.ruoyi.project.smt.bom.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/11/16 11:36
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtBomDeliveryVO {

    /**
     * bomId
     */
    private  Integer bomId;


    /**
     * 客户编码
     */
    private Integer cusCode;
    /**
     * 客户名称
     */
    private String cusName;

    /**
     * 电子料名称
     */
    private String dzlName;

    /**
     * 电子料类型
     */
    private String typeName;
    /**
     * 电子料用量
     */
    private Integer dzlNumber;
    /**
     * bom类型（1.FPC 2.DZL）
     */
    private Integer orderType;

    /**
     * 库存数量，带发料数量
     */
    private Integer sumOrderQty;
}
