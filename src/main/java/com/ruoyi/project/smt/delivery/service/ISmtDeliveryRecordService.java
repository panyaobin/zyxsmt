package com.ruoyi.project.smt.delivery.service;

import com.ruoyi.project.smt.delivery.domain.SmtDeliveryRecord;
import com.ruoyi.project.smt.delivery.domain.SmtDeliveryRecordVO;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 发料记录Service接口
 * 
 * @author popo
 * @date 2019-11-03
 */
public interface ISmtDeliveryRecordService 
{
    /**
     * 查询发料记录
     * 
     * @param id 发料记录ID
     * @return 发料记录
     */
    public SmtDeliveryRecord selectSmtDeliveryRecordById(Integer id);

    /**
     * 查询发料记录列表
     * 
     * @param smtDeliveryRecord 发料记录
     * @return 发料记录集合
     */
    public List<SmtDeliveryRecord> selectSmtDeliveryRecordList(SmtDeliveryRecord smtDeliveryRecord);

    /**
     * 查询FPC在线列表
     * @return 发料记录集合
     */
    public List<SmtDeliveryRecord> selectFpcOnLineListList(SmtDeliveryRecord record);

    /**
     * 查询dzl在线列表
     * @return 发料记录集合
     */
    public List<SmtDeliveryRecord> selectDzlOnLineListList(SmtDeliveryRecord record);

    /**
     * 新增发料记录
     * 
     * @param smtDeliveryRecord 发料记录
     * @return 结果
     */
    public int insertSmtDeliveryRecord(SmtDeliveryRecord smtDeliveryRecord);

      /**
     * 批量新增发料记录
     *
     * @param vo 发料记录
     * @return 结果
     */
    public int batchInsertSmtDeliveryRecord(SmtDeliveryRecordVO vo);


    /**
     * 修改发料记录
     * 
     * @param smtDeliveryRecord 发料记录
     * @return 结果
     */
    public int updateSmtDeliveryRecord(SmtDeliveryRecord smtDeliveryRecord);

    /**
     * 批量删除发料记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtDeliveryRecordByIds(String ids);

    /**
     * 删除发料记录信息
     * 
     * @param id 发料记录ID
     * @return 结果
     */
    public int deleteSmtDeliveryRecordById(Integer id);

    /**
     * 查询已发料数量
     * @param record
     * @return
     */
    public Integer getDeliveryQty(SmtDeliveryRecord record);

    /**
     * 根据发料单号查询发料信息
     */
    public List<SmtDeliveryRecord> getDeliveryRecordByNo(String deliveryNo);
}
