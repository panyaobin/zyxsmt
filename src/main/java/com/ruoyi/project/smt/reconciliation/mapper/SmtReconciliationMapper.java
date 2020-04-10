package com.ruoyi.project.smt.reconciliation.mapper;

import com.ruoyi.project.smt.reconciliation.domain.SmtReconciliation;
import java.util.List;

/**
 * 对账管理Mapper接口
 *
 * @author popo
 * @date 2020-04-05
 */
public interface SmtReconciliationMapper
{
    /**
     * 查询对账管理
     *
     * @param id 对账管理ID
     * @return 对账管理
     */
    public SmtReconciliation selectSmtReconciliationById(Integer id);

    /**
     * 查询对账管理列表
     *
     * @param smtReconciliation 对账管理
     * @return 对账管理集合
     */
    public List<SmtReconciliation> selectSmtReconciliationList(SmtReconciliation smtReconciliation);

    /**
     * 新增对账管理
     *
     * @param smtReconciliation 对账管理
     * @return 结果
     */
    public int insertSmtReconciliation(SmtReconciliation smtReconciliation);

    /**
     * 修改对账管理
     *
     * @param smtReconciliation 对账管理
     * @return 结果
     */
    public int updateSmtReconciliation(SmtReconciliation smtReconciliation);

    /**
     * 删除对账管理
     *
     * @param id 对账管理ID
     * @return 结果
     */
    public int deleteSmtReconciliationById(Integer id);

    /**
     * 批量删除对账管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtReconciliationByIds(String[] ids);

    /**
     * 根据对账单IDs查询对账单号
     *
     * @param ids 对账单IDs
     * @return 结果
     */
    public List<String> selectSmtReconciliationNoByIds(String[] ids);
}
