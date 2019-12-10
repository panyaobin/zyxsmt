package com.ruoyi.project.smt.entryLine.mapper;

import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import com.ruoyi.project.smt.entryLine.domain.SmtOrderEntryLine;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 订单入库明细Mapper接口
 * 
 * @author popo
 * @date 2019-11-02
 */
@Component
public interface SmtOrderEntryLineMapper 
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
     * 批量新增订单入库明细
     *
     * @param list 订单入库明细集合
     * @return 结果
     */
    public int batchInsertSmtEntryLine(List<SmtOrderEntryLine> list);


    /**
     * 修改订单入库明细
     * 
     * @param smtOrderEntryLine 订单入库明细
     * @return 结果
     */
    public int updateSmtOrderEntryLine(SmtOrderEntryLine smtOrderEntryLine);

    /**
     * 删除订单入库明细
     * 
     * @param id 订单入库明细ID
     * @return 结果
     */
    public int deleteSmtOrderEntryLineById(Integer id);

    /**
     * 通过主ID批量删除订单入库明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtOrderEntryLineByEntryIds(String[] ids);
    /**
     * 根据entryId删除订单入库明细信息
     *
     * @param entryId 客户entryID
     * @return 结果
     */
    public int deleteSmtOrderEntryLineByEntryId(@RequestParam Integer entryId);

    /**
     * 批量删除订单入库明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtOrderEntryLineByIds(String[] ids);

}
