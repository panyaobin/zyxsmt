package com.ruoyi.project.smt.bomLine.mapper;

import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 客户bom明细Mapper接口
 * 
 * @author popo
 * @date 2019-10-30
 */
@Component
public interface SmtBomLineMapper 
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
     * 批量新增客户bom明细
     * 
     * @param list 客户bom明细集合
     * @return 结果
     */
    public int batchInsertSmtBomLine(List<SmtBomLine> list);

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
     * 删除客户bom明细
     * 
     * @param id 客户bom明细ID
     * @return 结果
     */
    public int deleteSmtBomLineById(Integer id);

    /**
     * 根据bomId删除bom明细信息
     *
     * @param bomId 客户bomID
     * @return 结果
     */
    public int deleteSmtBomLineByBomId(@RequestParam Integer bomId);

    /**
     * 批量删除客户bom明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtBomLineByIds(String[] ids);

    /**
     * 查询bom明细中的电子料用量，根据bomName和dzlId查询
     * @return
     */
    public Integer selectDzlNumberByBomNameAndDzlId(String bomName,Integer dzlId);
}
