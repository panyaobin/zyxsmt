package com.ruoyi.project.smt.entryLine.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.entryLine.mapper.SmtOrderEntryLineMapper;
import com.ruoyi.project.smt.entryLine.domain.SmtOrderEntryLine;
import com.ruoyi.project.smt.entryLine.service.ISmtOrderEntryLineService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 订单入库明细Service业务层处理
 * 
 * @author popo
 * @date 2019-11-02
 */
@Service
public class SmtOrderEntryLineServiceImpl implements ISmtOrderEntryLineService 
{
    @Autowired
    private SmtOrderEntryLineMapper smtOrderEntryLineMapper;

    /**
     * 查询订单入库明细
     * 
     * @param id 订单入库明细ID
     * @return 订单入库明细
     */
    @Override
    public SmtOrderEntryLine selectSmtOrderEntryLineById(Integer id)
    {
        return smtOrderEntryLineMapper.selectSmtOrderEntryLineById(id);
    }

    /**
     * 查询订单入库明细列表
     * 
     * @param smtOrderEntryLine 订单入库明细
     * @return 订单入库明细
     */
    @Override
    public List<SmtOrderEntryLine> selectSmtOrderEntryLineList(SmtOrderEntryLine smtOrderEntryLine)
    {
        return smtOrderEntryLineMapper.selectSmtOrderEntryLineList(smtOrderEntryLine);
    }

    /**
     * 新增订单入库明细
     * 
     * @param smtOrderEntryLine 订单入库明细
     * @return 结果
     */
    @Override
    public int insertSmtOrderEntryLine(SmtOrderEntryLine smtOrderEntryLine)
    {
        smtOrderEntryLine.setCreateTime(DateUtils.getNowDate());
        return smtOrderEntryLineMapper.insertSmtOrderEntryLine(smtOrderEntryLine);
    }

    /**
     * 修改订单入库明细
     * 
     * @param smtOrderEntryLine 订单入库明细
     * @return 结果
     */
    @Override
    public int updateSmtOrderEntryLine(SmtOrderEntryLine smtOrderEntryLine)
    {
        smtOrderEntryLine.setUpdateTime(DateUtils.getNowDate());
        return smtOrderEntryLineMapper.updateSmtOrderEntryLine(smtOrderEntryLine);
    }

    /**
     * 删除订单入库明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtOrderEntryLineByIds(String ids)
    {
        return smtOrderEntryLineMapper.deleteSmtOrderEntryLineByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单入库明细信息
     * 
     * @param id 订单入库明细ID
     * @return 结果
     */
    @Override
    public int deleteSmtOrderEntryLineById(Integer id)
    {
        return smtOrderEntryLineMapper.deleteSmtOrderEntryLineById(id);
    }
}
