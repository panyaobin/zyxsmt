package com.ruoyi.project.smt.paymentapply.mapper;

import com.ruoyi.project.smt.paymentapply.domain.SmtPaymentApply;
import com.ruoyi.project.smt.paymentapply.print.SmtPaymentApplyPrintVO;

import java.util.List;

/**
 * 付款申请Mapper接口
 * 
 * @author popo
 * @date 2020-04-06
 */
public interface SmtPaymentApplyMapper 
{
    /**
     * 查询付款申请
     * 
     * @param id 付款申请ID
     * @return 付款申请
     */
    public SmtPaymentApply selectSmtPaymentApplyById(Integer id);

    /**
     * 查询付款申请列表
     * 
     * @param smtPaymentApply 付款申请
     * @return 付款申请集合
     */
    public List<SmtPaymentApply> selectSmtPaymentApplyList(SmtPaymentApply smtPaymentApply);

    /**
     * 查询付款申请表格列表
     *
     * @param smtPaymentApply 付款申请
     * @return 付款申请集合
     */
    public List<SmtPaymentApply> selectSmtPaymentApplyTableList(SmtPaymentApply smtPaymentApply);

    /**
     * 新增付款申请
     * 
     * @param smtPaymentApply 付款申请
     * @return 结果
     */
    public int insertSmtPaymentApply(SmtPaymentApply smtPaymentApply);

    /**
     * 修改付款申请
     * 
     * @param smtPaymentApply 付款申请
     * @return 结果
     */
    public int updateSmtPaymentApply(SmtPaymentApply smtPaymentApply);

    /**
     * 删除付款申请
     * 
     * @param id 付款申请ID
     * @return 结果
     */
    public int deleteSmtPaymentApplyById(Integer id);

    /**
     * 批量删除付款申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtPaymentApplyByIds(String[] ids);

    /**
     * 根据对账单IDs查询对账单号
     *
     * @param ids 对账单IDs
     * @return 结果
     */
    public List<String> selectSmtPaymentNoByIds(String[] ids);

    /**
     * 根据付款申请单id查询相关付款信息
     * @param id
     * @return
     */
    SmtPaymentApplyPrintVO selectPrintSmtPaymentApplyById(String id);
}
