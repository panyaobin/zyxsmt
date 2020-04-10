package com.ruoyi.project.smt.reconciliation.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.smt.paymentrecord.mapper.SmtPaymentRecordMapper;
import com.ruoyi.project.smt.reconciliationfile.mapper.SmtReconciliationFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.reconciliation.mapper.SmtReconciliationMapper;
import com.ruoyi.project.smt.reconciliation.domain.SmtReconciliation;
import com.ruoyi.project.smt.reconciliation.service.ISmtReconciliationService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 对账管理Service业务层处理
 *
 * @author popo
 * @date 2020-04-05
 */
@Service
public class SmtReconciliationServiceImpl implements ISmtReconciliationService {
    @Autowired
    private SmtReconciliationMapper smtReconciliationMapper;

    @Autowired
    private SmtReconciliationFileMapper smtReconciliationFileMapper;

    @Autowired
    private SmtPaymentRecordMapper smtPaymentRecordMapper;

    /**
     * 查询对账管理
     *
     * @param id 对账管理ID
     * @return 对账管理
     */
    @Override
    public SmtReconciliation selectSmtReconciliationById(Integer id) {
        return smtReconciliationMapper.selectSmtReconciliationById(id);
    }

    /**
     * 查询对账管理列表
     *
     * @param smtReconciliation 对账管理
     * @return 对账管理
     */
    @Override
    public List<SmtReconciliation> selectSmtReconciliationList(SmtReconciliation smtReconciliation) {
        return smtReconciliationMapper.selectSmtReconciliationList(smtReconciliation);
    }

    /**
     * 新增对账管理
     *
     * @param smtReconciliation 对账管理
     * @return 结果
     */
    @Override
    public int insertSmtReconciliation(SmtReconciliation smtReconciliation) {
        smtReconciliation.setCreateTime(DateUtils.getNowDate());
        return smtReconciliationMapper.insertSmtReconciliation(smtReconciliation);
    }

    /**
     * 修改对账管理
     *
     * @param smtReconciliation 对账管理
     * @return 结果
     */
    @Override
    public int updateSmtReconciliation(SmtReconciliation smtReconciliation) {
        smtReconciliation.setUpdateTime(DateUtils.getNowDate());
        return smtReconciliationMapper.updateSmtReconciliation(smtReconciliation);
    }

    /**
     * 删除对账管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtReconciliationByIds(String ids) {
        String[] array = Convert.toStrArray(ids);
        //删除客户对账单需要同步删除对账单附件
        List<String> paymentNos = smtReconciliationMapper.selectSmtReconciliationNoByIds(array);
        smtReconciliationFileMapper.deleteSmtReconciliationFileByReconciliationNos(paymentNos);
        //删除客户对账单需要同步删除付款记录
        smtPaymentRecordMapper.deleteSmtPaymentRecordByPaymentNos(paymentNos);
        return smtReconciliationMapper.deleteSmtReconciliationByIds(array);
    }

    /**
     * 删除对账管理信息
     *
     * @param id 对账管理ID
     * @return 结果
     */
    @Override
    public int deleteSmtReconciliationById(Integer id) {
        return smtReconciliationMapper.deleteSmtReconciliationById(id);
    }

    @Override
    public int changeStatus(SmtReconciliation reconciliation) {
        return smtReconciliationMapper.updateSmtReconciliation(reconciliation);
    }


}
