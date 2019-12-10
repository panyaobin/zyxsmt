package com.ruoyi.project.smt.ledger.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/12/9 22:52
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ShipExportVO {

    //客户编码
    private String cusCode;
    //电子料名称
    private String dzlName;
    //出货日期
    private String shipTime;
    //订单号
    private String orderNo;
    //FPC名称
    private String bomName;
    //FPC出货数量
    private Integer fpcShipQty;
    //用量
    private Integer dzlNumber;
    //电子料出货
    private Integer shipQty;
    //备品
    private Integer bak;
}
