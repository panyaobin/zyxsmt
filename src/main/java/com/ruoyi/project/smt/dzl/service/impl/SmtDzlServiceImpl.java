package com.ruoyi.project.smt.dzl.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.dzl.mapper.SmtDzlMapper;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 电子料信息Service业务层处理
 *
 * @author popo
 * @date 2019-10-19
 */
@Service
@Slf4j
public class SmtDzlServiceImpl implements ISmtDzlService {
    @Autowired
    private SmtDzlMapper smtDzlMapper;

    /**
     * 查询电子料信息
     *
     * @param id 电子料信息ID
     * @return 电子料信息
     */
    @Override
    public SmtDzl selectSmtDzlById(Long id) {
        return smtDzlMapper.selectSmtDzlById(id);
    }

    /**
     * 查询电子料信息列表
     *
     * @param smtDzl 电子料信息
     * @return 电子料信息
     */
    @Override
    public List<SmtDzl> selectSmtDzlList(SmtDzl smtDzl) {
        return smtDzlMapper.selectSmtDzlList(smtDzl);
    }

    /**
     * 新增电子料信息
     *
     * @param smtDzl 电子料信息
     * @return 结果
     */
    @Override
    public int insertSmtDzl(SmtDzl smtDzl) {
        smtDzl.setCreateTime(DateUtils.getNowDate());
        return smtDzlMapper.insertSmtDzl(smtDzl);
    }

    /**
     * 修改电子料信息
     *
     * @param smtDzl 电子料信息
     * @return 结果
     */
    @Override
    public int updateSmtDzl(SmtDzl smtDzl) {
        smtDzl.setUpdateTime(DateUtils.getNowDate());
        return smtDzlMapper.updateSmtDzl(smtDzl);
    }

    /**
     * 删除电子料信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtDzlByIds(String ids) {
        return smtDzlMapper.deleteSmtDzlByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除电子料信息信息
     *
     * @param id 电子料信息ID
     * @return 结果
     */
    @Override
    public int deleteSmtDzlById(Long id) {
        return smtDzlMapper.deleteSmtDzlById(id);
    }

    /**
     * 导入物料信息
     *
     * @param dzlList         物料数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importDzl(List<SmtDzl> dzlList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(dzlList) || dzlList.size() == 0) {
            throw new BusinessException("导入物料数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SmtDzl dzl : dzlList) {
            try {
                dzl.setMainType("2");
                dzl.setStatus(Constants.SUCCESS);
                dzl.setDelFlag(Constants.SUCCESS);
                dzl.setCreateBy(ShiroUtils.getLoginName());
                dzl.setCreateTime(DateUtils.getNowDate());
                this.insertSmtDzl(dzl);
                successNum++;
                successMsg.append("<br/>" + successNum + "、电子料 " + dzl.getDzlName() + " 导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、电子料 " + dzl.getDzlName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public int validateDzlNameUnique(String dzlName) {
        return smtDzlMapper.validateDzlNameUnique(dzlName);
    }
}
