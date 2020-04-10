package com.ruoyi.project.smt.paymentrecord.mapper;

import com.ruoyi.project.smt.paymentrecord.domain.SmtPaymentRecord;
import com.ruoyi.project.smt.paymentrecord.vo.SmtPaymentRecordVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 付款记录Mapper接口
 * 
 * @author popo
 * @date 2020-04-05
 */
public interface SmtPaymentRecordMapper 
{
    /**
     * 查询付款记录
     * 
     * @param id 付款记录ID
     * @return 付款记录
     */
    public SmtPaymentRecord selectSmtPaymentRecordById(Integer id);

    /**
     * 查询付款记录列表
     * 
     * @param smtPaymentRecord 付款记录
     * @return 付款记录集合
     */
    public List<SmtPaymentRecord> selectSmtPaymentRecordList(SmtPaymentRecord smtPaymentRecord);

    /**
     * 查询付款记录表格列表
     *
     * @param vo 付款记录
     * @return 付款记录集合
     */
    public List<SmtPaymentRecordVO> selectSmtPaymentRecordVOList(SmtPaymentRecordVO vo);

    /**
     * 新增付款记录
     * 
     * @param smtPaymentRecord 付款记录
     * @return 结果
     */
    public int insertSmtPaymentRecord(SmtPaymentRecord smtPaymentRecord);

    /**
     * 修改付款记录
     * 
     * @param smtPaymentRecord 付款记录
     * @return 结果
     */
    public int updateSmtPaymentRecord(SmtPaymentRecord smtPaymentRecord);

    /**
     * 删除付款记录
     * 
     * @param id 付款记录ID
     * @return 结果
     */
    public int deleteSmtPaymentRecordById(Integer id);

    /**
     * 批量删除付款记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtPaymentRecordByIds(String[] ids);

    /**
     * 对账单号查询所有客户付款汇总付款金额
     * @return
     */
    BigDecimal selectSumPaymentAmount(Integer id);

    /**
     * 根据付款单号删除付款记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtPaymentRecordByPaymentNos(List<String> ids);
}
