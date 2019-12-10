package com.ruoyi.project.smt.bomLine.service;

import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import java.util.List;

/**
 * 客户bom明细Service接口
 * 
 * @author popo
 * @date 2019-10-30
 */
public interface ISmtBomLineService 
{
    /**
     * 查询客户bom明细
     * 
     * @param id 客户bom明细ID
     * @return 客户bom明细
     */
    public SmtBomLine selectSmtBomLineById(Integer id);

    /**
     * 查询客户bom明细列表
     * 
     * @param smtBomLine 客户bom明细
     * @return 客户bom明细集合
     */
    public List<SmtBomLine> selectSmtBomLineList(SmtBomLine smtBomLine);

    /**
     * 新增客户bom明细
     * 
     * @param smtBomLine 客户bom明细
     * @return 结果
     */
    public int insertSmtBomLine(SmtBomLine smtBomLine);

    /**
     * 修改客户bom明细
     * 
     * @param smtBomLine 客户bom明细
     * @return 结果
     */
    public int updateSmtBomLine(SmtBomLine smtBomLine);

    /**
     * 批量删除客户bom明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtBomLineByIds(String ids);

    /**
     * 删除客户bom明细信息
     * 
     * @param id 客户bom明细ID
     * @return 结果
     */
    public int deleteSmtBomLineById(Integer id);
}
