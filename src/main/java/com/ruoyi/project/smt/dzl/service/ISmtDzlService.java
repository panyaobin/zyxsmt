package com.ruoyi.project.smt.dzl.service;

import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 电子料信息Service接口
 *
 * @author popo
 * @date 2019-10-19
 */
public interface ISmtDzlService {
    /**
     * 查询电子料信息
     *
     * @param id 电子料信息ID
     * @return 电子料信息
     */
    public SmtDzl selectSmtDzlById(Long id);

    /**
     * 查询电子料信息列表
     *
     * @param smtDzl 电子料信息
     * @return 电子料信息集合
     */
    public List<SmtDzl> selectSmtDzlList(SmtDzl smtDzl);

    /**
     * 新增电子料信息
     *
     * @param smtDzl 电子料信息
     * @return 结果
     */
    public int insertSmtDzl(SmtDzl smtDzl);

    /**
     * 修改电子料信息
     *
     * @param smtDzl 电子料信息
     * @return 结果
     */
    public int updateSmtDzl(SmtDzl smtDzl);

    /**
     * 批量删除电子料信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtDzlByIds(String ids);

    /**
     * 删除电子料信息信息
     *
     * @param id 电子料信息ID
     * @return 结果
     */
    public int deleteSmtDzlById(Long id);

    /**
     * 导入用户数据
     *
     * @param dzlList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importDzl(List<SmtDzl> dzlList, Boolean isUpdateSupport);

    /**
     * 验证电子料名称唯一性
     * @param dzlName
     * @return
     */
    public int validateDzlNameUnique(String dzlName);
}
