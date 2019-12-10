package com.ruoyi.project.smt.ship.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/12/6 11:30
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ShipPrintVO {

    private String index;
    private String dzlName;
    private String typeName;
    private String dzlNumber;
    private String bak;

    private String indexs;
    private String dzlNames;
    private String typeNames;
    private String dzlNumbers;
    private String baks;
}

