package com.ruoyi.project.smt.financereport.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 利润表数据DTO
 * @author popo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceReportDTO {

    /**
     * 序号
     */
    private Integer index;

    /**
     * 费用类型名称
     */
    private String paymentTypeName;

    /**
     * 费用类型
     */
    private Integer paymentType;

    /**
     * 月份
     */
    private BigDecimal one;
    private BigDecimal two;
    private BigDecimal three;
    private BigDecimal four;
    private BigDecimal five;
    private BigDecimal six;
    private BigDecimal seven;
    private BigDecimal eight;
    private BigDecimal nine;
    private BigDecimal ten;
    private BigDecimal eleven;
    private BigDecimal twelve;

}
