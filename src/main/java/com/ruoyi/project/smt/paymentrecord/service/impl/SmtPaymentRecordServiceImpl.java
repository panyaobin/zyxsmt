package com.ruoyi.project.smt.paymentrecord.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.smt.paymentrecord.vo.SmtPaymentRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.paymentrecord.mapper.SmtPaymentRecordMapper;
import com.ruoyi.project.smt.paymentrecord.domain.SmtPaymentRecord;
import com.ruoyi.project.smt.paymentrecord.service.ISmtPaymentRecordService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 付款记录Service业务层处理
 * 
 * @author popo
 * @date 2020-04-05
 */
@Service
public class SmtPaymentRecordServiceImpl implements ISmtPaymentRecordService 
{
    @Autowired
    private SmtPaymentRecordMapper smtPaymentRecordMapper;

    /**
     * 查询付款记录
     * 
     * @param id 付款记录ID
     * @return 付款记录
     */
    @Override
    public SmtPaymentRecord selectSmtPaymentRecordById(Integer id)
    {
        return smtPaymentRecordMapper.selectSmtPaymentRecordById(id);
    }

    /**
     * 查询付款记录列表
     * 
     * @param smtPaymentRecord 付款记录
     * @return 付款记录
     */
    @Override
    public List<SmtPaymentRecord> selectSmtPaymentRecordList(SmtPaymentRecord smtPaymentRecord)
    {
        return smtPaymentRecordMapper.selectSmtPaymentRecordList(smtPaymentRecord);
    }

    @Override
    public List<SmtPaymentRecordVO> selectSmtPaymentRecordVOList(SmtPaymentRecordVO vo) {
        return smtPaymentRecordMapper.selectSmtPaymentRecordVOList(vo);
    }

    /**
     * 新增付款记录
     * 
     * @param smtPaymentRecord 付款记录
     * @return 结果
     */
    @Override
    public int insertSmtPaymentRecord(SmtPaymentRecord smtPaymentRecord)
    {
        smtPaymentRecord.setCreateTime(DateUtils.getNowDate());
        return smtPaymentRecordMapper.insertSmtPaymentRecord(smtPaymentRecord);
    }

    /**
     * 修改付款记录
     * 
     * @param smtPaymentRecord 付款记录
     * @return 结果
     */
    @Override
    public int updateSmtPaymentRecord(SmtPaymentRecord smtPaymentRecord)
    {
        smtPaymentRecord.setUpdateTime(DateUtils.getNowDate());
        return smtPaymentRecordMapper.updateSmtPaymentRecord(smtPaymentRecord);
    }

    /**
     * 删除付款记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtPaymentRecordByIds(String ids)
    {
        return smtPaymentRecordMapper.deleteSmtPaymentRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除付款记录信息
     * 
     * @param id 付款记录ID
     * @return 结果
     */
    @Override
    public int deleteSmtPaymentRecordById(Integer id)
    {
        return smtPaymentRecordMapper.deleteSmtPaymentRecordById(id);
    }

    @Override
    public BigDecimal selectSumPaymentAmount(Integer id) {
        return smtPaymentRecordMapper.selectSumPaymentAmount(id);
    }
}
