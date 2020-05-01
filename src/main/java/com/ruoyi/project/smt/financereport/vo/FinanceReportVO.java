package com.ruoyi.project.smt.financereport.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 利润表条件参数VO
 * @author popo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FinanceReportVO {

    /**
     * 费用类型
     */
    private Integer paymentType;

    /**
     * 年份
     */
    private String years;

    /**
     * 月份
     */
    private String months;

    /**
     * 总金额
     */
    private BigDecimal totalPayment;
}
