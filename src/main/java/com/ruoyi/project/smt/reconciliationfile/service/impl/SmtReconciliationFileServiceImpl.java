package com.ruoyi.project.smt.reconciliationfile.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.reconciliationfile.mapper.SmtReconciliationFileMapper;
import com.ruoyi.project.smt.reconciliationfile.domain.SmtReconciliationFile;
import com.ruoyi.project.smt.reconciliationfile.service.ISmtReconciliationFileService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 对账单附件Service业务层处理
 * 
 * @author popo
 * @date 2020-04-08
 */
@Service
public class SmtReconciliationFileServiceImpl implements ISmtReconciliationFileService 
{
    @Autowired
    private SmtReconciliationFileMapper smtReconciliationFileMapper;

    /**
     * 查询对账单附件
     * 
     * @param id 对账单附件ID
     * @return 对账单附件
     */
    @Override
    public SmtReconciliationFile selectSmtReconciliationFileById(Integer id)
    {
        return smtReconciliationFileMapper.selectSmtReconciliationFileById(id);
    }

    /**
     * 查询对账单附件列表
     * 
     * @param smtReconciliationFile 对账单附件
     * @return 对账单附件
     */
    @Override
    public List<SmtReconciliationFile> selectSmtReconciliationFileList(SmtReconciliationFile smtReconciliationFile)
    {
        return smtReconciliationFileMapper.selectSmtReconciliationFileList(smtReconciliationFile);
    }

    /**
     * 新增对账单附件
     * 
     * @param smtReconciliationFile 对账单附件
     * @return 结果
     */
    @Override
    public int insertSmtReconciliationFile(SmtReconciliationFile smtReconciliationFile)
    {
        smtReconciliationFile.setCreateTime(DateUtils.getNowDate());
        return smtReconciliationFileMapper.insertSmtReconciliationFile(smtReconciliationFile);
    }

    /**
     * 修改对账单附件
     * 
     * @param smtReconciliationFile 对账单附件
     * @return 结果
     */
    @Override
    public int updateSmtReconciliationFile(SmtReconciliationFile smtReconciliationFile)
    {
        smtReconciliationFile.setUpdateTime(DateUtils.getNowDate());
        return smtReconciliationFileMapper.updateSmtReconciliationFile(smtReconciliationFile);
    }

    /**
     * 删除对账单附件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtReconciliationFileByIds(String ids)
    {
        return smtReconciliationFileMapper.deleteSmtReconciliationFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除对账单附件信息
     * 
     * @param id 对账单附件ID
     * @return 结果
     */
    @Override
    public int deleteSmtReconciliationFileById(Integer id)
    {
        return smtReconciliationFileMapper.deleteSmtReconciliationFileById(id);
    }
}
