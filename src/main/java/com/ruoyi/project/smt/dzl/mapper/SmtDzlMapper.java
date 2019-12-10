package com.ruoyi.project.smt.dzl.mapper;

import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 电子料信息Mapper接口
 * 
 * @author popo
 * @date 2019-10-19
 */
@Component
public interface SmtDzlMapper 
{
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
     * 删除电子料信息
     * 
     * @param id 电子料信息ID
     * @return 结果
     */
    public int deleteSmtDzlById(Long id);

    /**
     * 批量删除电子料信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtDzlByIds(String[] ids);

    /**
     * 通过电子料名称查询电子料id
     * @param dzlName
     * @return
     */
    Integer getDzlIdByDzlName(String dzlName);


    /**
     * 验证电子料名称唯一性
     * @param dzlName
     * @return
     */
    public int validateDzlNameUnique(String dzlName);
}
