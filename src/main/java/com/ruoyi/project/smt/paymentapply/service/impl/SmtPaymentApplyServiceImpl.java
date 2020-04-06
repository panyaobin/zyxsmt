package com.ruoyi.project.smt.paymentapply.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.paymentapply.mapper.SmtPaymentApplyMapper;
import com.ruoyi.project.smt.paymentapply.domain.SmtPaymentApply;
import com.ruoyi.project.smt.paymentapply.service.ISmtPaymentApplyService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 付款申请Service业务层处理
 *
 * @author popo
 * @date 2020-04-06
 */
@Service
public class SmtPaymentApplyServiceImpl implements ISmtPaymentApplyService {
    @Autowired
    private SmtPaymentApplyMapper smtPaymentApplyMapper;

    /**
     * 查询付款申请
     *
     * @param id 付款申请ID
     * @return 付款申请
     */
    @Override
    public SmtPaymentApply selectSmtPaymentApplyById(Integer id) {
        return smtPaymentApplyMapper.selectSmtPaymentApplyById(id);
    }

    /**
     * 查询付款申请列表
     *
     * @param smtPaymentApply 付款申请
     * @return 付款申请
     */
    @Override
    public List<SmtPaymentApply> selectSmtPaymentApplyList(SmtPaymentApply smtPaymentApply) {
        return smtPaymentApplyMapper.selectSmtPaymentApplyList(smtPaymentApply);
    }

    /**
     * 查询付款申请表格列表
     *
     * @param smtPaymentApply 付款申请
     * @return 付款申请
     */
    @Override
    public List<SmtPaymentApply> selectSmtPaymentApplyTableList(SmtPaymentApply smtPaymentApply) {
        return smtPaymentApplyMapper.selectSmtPaymentApplyTableList(smtPaymentApply);
    }

    /**
     * 新增付款申请
     *
     * @param smtPaymentApply 付款申请
     * @return 结果
     */
    @Override
    public int insertSmtPaymentApply(SmtPaymentApply smtPaymentApply) {
        smtPaymentApply.setCreateTime(DateUtils.getNowDate());
        return smtPaymentApplyMapper.insertSmtPaymentApply(smtPaymentApply);
    }

    /**
     * 修改付款申请
     *
     * @param smtPaymentApply 付款申请
     * @return 结果
     */
    @Override
    public int updateSmtPaymentApply(SmtPaymentApply smtPaymentApply) {
        smtPaymentApply.setUpdateTime(DateUtils.getNowDate());
        return smtPaymentApplyMapper.updateSmtPaymentApply(smtPaymentApply);
    }

    /**
     * 删除付款申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtPaymentApplyByIds(String ids) {
        return smtPaymentApplyMapper.deleteSmtPaymentApplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除付款申请信息
     *
     * @param id 付款申请ID
     * @return 结果
     */
    @Override
    public int deleteSmtPaymentApplyById(Integer id) {
        return smtPaymentApplyMapper.deleteSmtPaymentApplyById(id);
    }

    @Override
    public int changeStatus(SmtPaymentApply apply) {
        return smtPaymentApplyMapper.updateSmtPaymentApply(apply);
    }
}
