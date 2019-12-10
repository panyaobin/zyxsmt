package com.ruoyi.project.smt.bomLine.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.bomLine.mapper.SmtBomLineMapper;
import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import com.ruoyi.project.smt.bomLine.service.ISmtBomLineService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 客户bom明细Service业务层处理
 * 
 * @author popo
 * @date 2019-10-30
 */
@Service
public class SmtBomLineServiceImpl implements ISmtBomLineService 
{
    @Autowired
    private SmtBomLineMapper smtBomLineMapper;

    /**
     * 查询客户bom明细
     * 
     * @param id 客户bom明细ID
     * @return 客户bom明细
     */
    @Override
    public SmtBomLine selectSmtBomLineById(Integer id)
    {
        return smtBomLineMapper.selectSmtBomLineById(id);
    }

    /**
     * 查询客户bom明细列表
     * 
     * @param smtBomLine 客户bom明细
     * @return 客户bom明细
     */
    @Override
    public List<SmtBomLine> selectSmtBomLineList(SmtBomLine smtBomLine)
    {
        return smtBomLineMapper.selectSmtBomLineList(smtBomLine);
    }

    /**
     * 新增客户bom明细
     * 
     * @param smtBomLine 客户bom明细
     * @return 结果
     */
    @Override
    public int insertSmtBomLine(SmtBomLine smtBomLine)
    {
        smtBomLine.setCreateTime(DateUtils.getNowDate());
        return smtBomLineMapper.insertSmtBomLine(smtBomLine);
    }

    /**
     * 修改客户bom明细
     * 
     * @param smtBomLine 客户bom明细
     * @return 结果
     */
    @Override
    public int updateSmtBomLine(SmtBomLine smtBomLine)
    {
        smtBomLine.setUpdateTime(DateUtils.getNowDate());
        return smtBomLineMapper.updateSmtBomLine(smtBomLine);
    }

    /**
     * 删除客户bom明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtBomLineByIds(String ids)
    {
        return smtBomLineMapper.deleteSmtBomLineByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户bom明细信息
     * 
     * @param id 客户bom明细ID
     * @return 结果
     */
    @Override
    public int deleteSmtBomLineById(Integer id)
    {
        return smtBomLineMapper.deleteSmtBomLineById(id);
    }
}
