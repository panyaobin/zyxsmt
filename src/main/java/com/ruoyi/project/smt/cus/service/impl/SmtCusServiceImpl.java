package com.ruoyi.project.smt.cus.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.cus.mapper.SmtCusMapper;
import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.cus.service.ISmtCusService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 客户信息Service业务层处理
 * 
 * @author popo
 * @date 2019-10-19
 */
@Service
public class SmtCusServiceImpl implements ISmtCusService 
{
    @Autowired
    private SmtCusMapper smtCusMapper;

    /**
     * 查询客户信息
     * 
     * @param id 客户信息ID
     * @return 客户信息
     */
    @Override
    public SmtCus selectSmtCusById(Integer id)
    {
        return smtCusMapper.selectSmtCusById(id);
    }

    /**
     * 查询客户信息列表
     * 
     * @param smtCus 客户信息
     * @return 客户信息
     */
    @Override
    public List<SmtCus> selectSmtCusList(SmtCus smtCus)
    {
        return smtCusMapper.selectSmtCusList(smtCus);
    }

    /**
     * 新增客户信息
     * 
     * @param smtCus 客户信息
     * @return 结果
     */
    @Override
    public int insertSmtCus(SmtCus smtCus)
    {
        smtCus.setCreateTime(DateUtils.getNowDate());
        return smtCusMapper.insertSmtCus(smtCus);
    }

    /**
     * 修改客户信息
     * 
     * @param smtCus 客户信息
     * @return 结果
     */
    @Override
    public int updateSmtCus(SmtCus smtCus)
    {
        smtCus.setUpdateTime(DateUtils.getNowDate());
        return smtCusMapper.updateSmtCus(smtCus);
    }

    /**
     * 删除客户信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtCusByIds(String ids)
    {
        return smtCusMapper.deleteSmtCusByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息信息
     * 
     * @param id 客户信息ID
     * @return 结果
     */
    @Override
    public int deleteSmtCusById(Integer id)
    {
        return smtCusMapper.deleteSmtCusById(id);
    }

    /**
     * 验证客户代码唯一性
     *
     * @param code 客户代码
     * @return 结果
     */
    public int validateUniqueByCode(String code){
        return smtCusMapper.validateUniqueByCode(code);
    }

    /**
     * 用户状态修改
     *
     * @param cus 客户信息
     * @return 结果
     */
    @Override
    public int changeStatus(SmtCus cus)
    {
        return smtCusMapper.updateSmtCus(cus);
    }
}
