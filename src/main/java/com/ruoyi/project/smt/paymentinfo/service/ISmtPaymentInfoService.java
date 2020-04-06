package com.ruoyi.project.smt.paymentinfo.service;

import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.paymentinfo.domain.SmtPaymentInfo;
import java.util.List;

/**
 * 付款信息Service接口
 * 
 * @author popo
 * @date 2020-04-06
 */
public interface ISmtPaymentInfoService 
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
     * 批量删除付款信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtPaymentInfoByIds(String ids);

    /**
     * 删除付款信息信息
     * 
     * @param id 付款信息ID
     * @return 结果
     */
    public int deleteSmtPaymentInfoById(Integer id);

    /**
     * 状态修改
     *
     * @param info 付款信息
     * @return 结果
     */
    public int changeStatus(SmtPaymentInfo info);
}
