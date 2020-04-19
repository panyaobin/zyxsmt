package com.ruoyi.project.smt.applyrecord.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.applyrecord.mapper.SmtApplyRecordMapper;
import com.ruoyi.project.smt.applyrecord.domain.SmtApplyRecord;
import com.ruoyi.project.smt.applyrecord.service.ISmtApplyRecordService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 应付账目付款记录Service业务层处理
 * 
 * @author popo
 * @date 2020-04-09
 */
@Service
public class SmtApplyRecordServiceImpl implements ISmtApplyRecordService 
{
    @Autowired
    private SmtApplyRecordMapper smtApplyRecordMapper;

    /**
     * 查询应付账目付款记录
     * 
     * @param id 应付账目付款记录ID
     * @return 应付账目付款记录
     */
    @Override
    public SmtApplyRecord selectSmtApplyRecordById(Integer id)
    {
        return smtApplyRecordMapper.selectSmtApplyRecordById(id);
    }

    /**
     * 查询应付账目付款记录列表
     * 
     * @param smtApplyRecord 应付账目付款记录
     * @return 应付账目付款记录
     */
    @Override
    public List<SmtApplyRecord> selectSmtApplyRecordList(SmtApplyRecord smtApplyRecord)
    {
        return smtApplyRecordMapper.selectSmtApplyRecordList(smtApplyRecord);
    }

    /**
     * 新增应付账目付款记录
     * 
     * @param smtApplyRecord 应付账目付款记录
     * @return 结果
     */
    @Override
    public int insertSmtApplyRecord(SmtApplyRecord smtApplyRecord)
    {
        smtApplyRecord.setCreateTime(DateUtils.getNowDate());
        return smtApplyRecordMapper.insertSmtApplyRecord(smtApplyRecord);
    }

    /**
     * 修改应付账目付款记录
     * 
     * @param smtApplyRecord 应付账目付款记录
     * @return 结果
     */
    @Override
    public int updateSmtApplyRecord(SmtApplyRecord smtApplyRecord)
    {
        smtApplyRecord.setUpdateTime(DateUtils.getNowDate());
        return smtApplyRecordMapper.updateSmtApplyRecord(smtApplyRecord);
    }

    /**
     * 删除应付账目付款记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtApplyRecordByIds(String ids)
    {
        return smtApplyRecordMapper.deleteSmtApplyRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除应付账目付款记录信息
     * 
     * @param id 应付账目付款记录ID
     * @return 结果
     */
    @Override
    public int deleteSmtApplyRecordById(Integer id)
    {
        return smtApplyRecordMapper.deleteSmtApplyRecordById(id);
    }

    @Override
    public BigDecimal selectSumApplyAmount(Integer id) {
        return smtApplyRecordMapper.selectSumApplyAmount(id);
    }

    @Override
    public List<SmtApplyRecord> getApplyRecordList() {
        return smtApplyRecordMapper.getApplyRecordList();
    }
}
