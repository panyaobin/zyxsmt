package com.ruoyi.project.smt.ledger.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 物料台账条件导出数据VO
 * @Author Administrator
 * @Date 2019/12/9 21:19
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtLedgerExportVO implements Serializable {

    /**
     * 来料数据
     */
    private String entryData;

    /**
     * 出货数据
     */
    private String shipData;

}
