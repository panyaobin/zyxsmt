package com.ruoyi.project.smt.bom.mapper;

import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.vo.SmtBomVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 客户bom信息Mapper接口
 *
 * @author popo
 * @date 2019-10-19
 */
@Component
public interface SmtBomMapper {
    /**
     * 查询客户bom信息
     *
     * @param id 客户bom信息ID
     * @return 客户bom信息
     */
    public SmtBom selectSmtBomById(Long id);

    /**
     * 查询客户bom信息列表
     *
     * @param smtBom 客户bom信息
     * @return 客户bom信息集合
     */
    public List<SmtBom> selectSmtBomList(SmtBom smtBom);

    /**
     * 新增客户bom信息
     *
     * @param smtBom 客户bom信息
     * @return 结果
     */
    public int insertSmtBom(SmtBom smtBom);

    /**
     * 修改客户bom信息
     *
     * @param smtBom 客户bom信息
     * @return 结果
     */
    public int updateSmtBom(SmtBom smtBom);

    /**
     * 删除客户bom信息
     *
     * @param id 客户bom信息ID
     * @return 结果
     */
    public int deleteSmtBomById(Integer id);

    /**
     * 批量删除客户bom信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtBomByIds(String[] ids);

    /**
     * 查询客户bom全部信息列表
     *
     * @param smtBom 客户bom信息
     * @return 客户bom信息集合
     */
    public List<SmtBomVO> selectSmtBomAllList(SmtBom smtBom);

    /**
     * 验证客户bom唯一性
     *
     * @param bom
     * @return
     */
    public int validateCusBomUnique(SmtBom bom);

    /**
     * 根据bomName查询总点数
     * @param cusCode
     * @param bomName
     * @return
     */
    String selectSmtBomByBomName(Integer cusCode, String bomName);
}