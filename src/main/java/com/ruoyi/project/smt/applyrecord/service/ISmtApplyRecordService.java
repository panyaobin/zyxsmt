package com.ruoyi.project.smt.applyrecord.service;

import com.ruoyi.project.smt.applyrecord.domain.SmtApplyRecord;
import com.ruoyi.project.smt.financereport.vo.PaymentVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 应付账目付款记录Service接口
 * 
 * @author popo
 * @date 2020-04-09
 */
public interface ISmtApplyRecordService 
{
    /**
     * 查询应付账目付款记录
     * 
     * @param id 应付账目付款记录ID
     * @return 应付账目付款记录
     */
    public SmtApplyRecord selectSmtApplyRecordById(Integer id);

    /**
     * 查询应付账目付款记录列表
     * 
     * @param smtApplyRecord 应付账目付款记录
     * @return 应付账目付款记录集合
     */
    public List<SmtApplyRecord> selectSmtApplyRecordList(SmtApplyRecord smtApplyRecord);

    /**
     * 新增应付账目付款记录
     * 
     * @param smtApplyRecord 应付账目付款记录
     * @return 结果
     */
    public int insertSmtApplyRecord(SmtApplyRecord smtApplyRecord);

    /**
     * 修改应付账目付款记录
     * 
     * @param smtApplyRecord 应付账目付款记录
     * @return 结果
     */
    public int updateSmtApplyRecord(SmtApplyRecord smtApplyRecord);

    /**
     * 批量删除应付账目付款记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtApplyRecordByIds(String ids);

    /**
     * 删除应付账目付款记录信息
     * 
     * @param id 应付账目付款记录ID
     * @return 结果
     */
    public int deleteSmtApplyRecordById(Integer id);

    /**
     * 根据付款单号查询付款金额总和
     * @param id 付款单号
     * @return
     */
    BigDecimal selectSumApplyAmount(Integer id);

    /**
     * 查询应付账目付款记录
     * @return
     */
    List<SmtApplyRecord> getApplyRecordList(PaymentVO pVo);
}
