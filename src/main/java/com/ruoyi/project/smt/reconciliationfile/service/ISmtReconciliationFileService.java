package com.ruoyi.project.smt.reconciliationfile.service;

import com.ruoyi.project.smt.reconciliationfile.domain.SmtReconciliationFile;
import java.util.List;

/**
 * 对账单附件Service接口
 * 
 * @author popo
 * @date 2020-04-08
 */
public interface ISmtReconciliationFileService 
{
    /**
     * 查询对账单附件
     * 
     * @param id 对账单附件ID
     * @return 对账单附件
     */
    public SmtReconciliationFile selectSmtReconciliationFileById(Integer id);

    /**
     * 查询对账单附件列表
     * 
     * @param smtReconciliationFile 对账单附件
     * @return 对账单附件集合
     */
    public List<SmtReconciliationFile> selectSmtReconciliationFileList(SmtReconciliationFile smtReconciliationFile);

    /**
     * 新增对账单附件
     * 
     * @param smtReconciliationFile 对账单附件
     * @return 结果
     */
    public int insertSmtReconciliationFile(SmtReconciliationFile smtReconciliationFile);

    /**
     * 修改对账单附件
     * 
     * @param smtReconciliationFile 对账单附件
     * @return 结果
     */
    public int updateSmtReconciliationFile(SmtReconciliationFile smtReconciliationFile);

    /**
     * 批量删除对账单附件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtReconciliationFileByIds(String ids);

    /**
     * 删除对账单附件信息
     * 
     * @param id 对账单附件ID
     * @return 结果
     */
    public int deleteSmtReconciliationFileById(Integer id);
}
