package com.ruoyi.project.smt.reconciliationfile.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 对账单附件对象 smt_reconciliation_file
 *
 * @author popo
 * @date 2020-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmtReconciliationFile extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 对账单号
     */
    @Excel(name = "对账单号")
    private Integer reconciliationNo;

    /**
     * 文件类型(1.对账单附件 2.回传附件)
     */
    @Excel(name = "文件类型(1.对账单附件 2.回传附件)")
    private Integer fileType;

    /**
     * 文件上传地址
     */
    @Excel(name = "文件上传地址")
    private String fileUrl;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在1代表删除）
     */
    private String delFlag;


}
