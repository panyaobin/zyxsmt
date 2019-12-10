package com.ruoyi.project.smt.cus.mapper;

import com.ruoyi.project.smt.cus.domain.SmtCus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 客户信息Mapper接口
 * 
 * @author popo
 * @date 2019-10-19
 */
@Component
public interface SmtCusMapper 
{
    /**
     * 查询客户信息
     * 
     * @param id 客户信息ID
     * @return 客户信息
     */
    public SmtCus selectSmtCusById(Integer id);

    /**
     * 查询客户信息列表
     * 
     * @param smtCus 客户信息
     * @return 客户信息集合
     */
    public List<SmtCus> selectSmtCusList(SmtCus smtCus);

    /**
     * 新增客户信息
     * 
     * @param smtCus 客户信息
     * @return 结果
     */
    public int insertSmtCus(SmtCus smtCus);

    /**
     * 修改客户信息
     * 
     * @param smtCus 客户信息
     * @return 结果
     */
    public int updateSmtCus(SmtCus smtCus);

    /**
     * 删除客户信息
     * 
     * @param id 客户信息ID
     * @return 结果
     */
    public int deleteSmtCusById(Integer id);

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtCusByIds(String[] ids);

    /**
     * 验证客户代码唯一性
     *
     * @param code 客户代码
     * @return 结果
     */
    public int validateUniqueByCode(String code);
}
