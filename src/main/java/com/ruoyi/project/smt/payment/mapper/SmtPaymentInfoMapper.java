package com.ruoyi.project.smt.payment.mapper;

import com.ruoyi.project.smt.payment.domain.SmtPaymentInfo;
import java.util.List;

/**
 * 付款信息Mapper接口
 * 
 * @author popo
 * @date 2019-11-29
 */
public interface SmtPaymentInfoMapper 
{
    /**
     * 查询付款信息
     * 
     * @param id 付款信息ID
     * @return 付款信息
     */
    public SmtPaymentInfo selectSmtPaymentInfoById(Integer id);

    /**
     * 查询付款信息列表
     * 
     * @param smtPaymentInfo 付款信息
     * @return 付款信息集合
     */
    public List<SmtPaymentInfo> selectSmtPaymentInfoList(SmtPaymentInfo smtPaymentInfo);

    /**
     * 新增付款信息
     * 
     * @param smtPaymentInfo 付款信息
     * @return 结果
     */
    public int insertSmtPaymentInfo(SmtPaymentInfo smtPaymentInfo);

    /**
     * 修改付款信息
     * 
     * @param smtPaymentInfo 付款信息
     * @return 结果
     */
    public int updateSmtPaymentInfo(SmtPaymentInfo smtPaymentInfo);

    /**
     * 删除付款信息
     * 
     * @param id 付款信息ID
     * @return 结果
     */
    public int deleteSmtPaymentInfoById(Integer id);

    /**
     * 批量删除付款信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtPaymentInfoByIds(String[] ids);
}
