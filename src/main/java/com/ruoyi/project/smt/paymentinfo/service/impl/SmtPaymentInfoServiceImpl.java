package com.ruoyi.project.smt.paymentinfo.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.paymentinfo.mapper.SmtPaymentInfoMapper;
import com.ruoyi.project.smt.paymentinfo.domain.SmtPaymentInfo;
import com.ruoyi.project.smt.paymentinfo.service.ISmtPaymentInfoService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 付款信息Service业务层处理
 * 
 * @author popo
 * @date 2020-04-06
 */
@Service
public class SmtPaymentInfoServiceImpl implements ISmtPaymentInfoService 
{
    @Autowired
    private SmtPaymentInfoMapper smtPaymentInfoMapper;

    /**
     * 查询付款信息
     * 
     * @param id 付款信息ID
     * @return 付款信息
     */
    @Override
    public SmtPaymentInfo selectSmtPaymentInfoById(Integer id)
    {
        return smtPaymentInfoMapper.selectSmtPaymentInfoById(id);
    }

    /**
     * 查询付款信息列表
     * 
     * @param smtPaymentInfo 付款信息
     * @return 付款信息
     */
    @Override
    public List<SmtPaymentInfo> selectSmtPaymentInfoList(SmtPaymentInfo smtPaymentInfo)
    {
        return smtPaymentInfoMapper.selectSmtPaymentInfoList(smtPaymentInfo);
    }

    /**
     * 新增付款信息
     * 
     * @param smtPaymentInfo 付款信息
     * @return 结果
     */
    @Override
    public int insertSmtPaymentInfo(SmtPaymentInfo smtPaymentInfo)
    {
        smtPaymentInfo.setCreateTime(DateUtils.getNowDate());
        return smtPaymentInfoMapper.insertSmtPaymentInfo(smtPaymentInfo);
    }

    /**
     * 修改付款信息
     * 
     * @param smtPaymentInfo 付款信息
     * @return 结果
     */
    @Override
    public int updateSmtPaymentInfo(SmtPaymentInfo smtPaymentInfo)
    {
        smtPaymentInfo.setUpdateTime(DateUtils.getNowDate());
        return smtPaymentInfoMapper.updateSmtPaymentInfo(smtPaymentInfo);
    }

    /**
     * 删除付款信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtPaymentInfoByIds(String ids)
    {
        return smtPaymentInfoMapper.deleteSmtPaymentInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除付款信息信息
     * 
     * @param id 付款信息ID
     * @return 结果
     */
    @Override
    public int deleteSmtPaymentInfoById(Integer id)
    {
        return smtPaymentInfoMapper.deleteSmtPaymentInfoById(id);
    }

    @Override
    public int changeStatus(SmtPaymentInfo info) {
        return smtPaymentInfoMapper.updateSmtPaymentInfo(info);

    }
}
