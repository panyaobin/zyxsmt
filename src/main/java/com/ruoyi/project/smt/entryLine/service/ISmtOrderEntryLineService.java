package com.ruoyi.project.smt.entryLine.service;

import com.ruoyi.project.smt.entryLine.domain.SmtOrderEntryLine;
import java.util.List;

/**
 * 订单入库明细Service接口
 * 
 * @author popo
 * @date 2019-11-02
 */
public interface ISmtOrderEntryLineService 
{
    /**
     * 查询订单入库明细
     * 
     * @param id 订单入库明细ID
     * @return 订单入库明细
     */
    public SmtOrderEntryLine selectSmtOrderEntryLineById(Integer id);

    /**
     * 查询订单入库明细列表
     * 
     * @param smtOrderEntryLine 订单入库明细
     * @return 订单入库明细集合
     */
    public List<SmtOrderEntryLine> selectSmtOrderEntryLineList(SmtOrderEntryLine smtOrderEntryLine);

    /**
     * 新增订单入库明细
     * 
     * @param smtOrderEntryLine 订单入库明细
     * @return 结果
     */
    public int insertSmtOrderEntryLine(SmtOrderEntryLine smtOrderEntryLine);

    /**
     * 修改订单入库明细
     * 
     * @param smtOrderEntryLine 订单入库明细
     * @return 结果
     */
    public int updateSmtOrderEntryLine(SmtOrderEntryLine smtOrderEntryLine);

    /**
     * 批量删除订单入库明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtOrderEntryLineByIds(String ids);

    /**
     * 删除订单入库明细信息
     * 
     * @param id 订单入库明细ID
     * @return 结果
     */
    public int deleteSmtOrderEntryLineById(Integer id);
}
