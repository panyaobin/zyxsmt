package com.ruoyi.project.smt.delivery.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 发料打印VO
 * @Author Administrator
 * @Date 2019/12/5 21:10
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryPrintVO {

    private String index;
    private String bomName;
    private String bomType;
    private String deliveryQty;
    private String remark;
}
