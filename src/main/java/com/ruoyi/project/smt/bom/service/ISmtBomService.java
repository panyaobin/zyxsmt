package com.ruoyi.project.smt.bom.service;

import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.vo.SmtBomVO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 客户bom信息Service接口
 * 
 * @author popo
 * @date 2019-10-19
 */
public interface ISmtBomService 
{
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
     * 查询客户bom全部信息列表
     *
     * @param smtBom 客户bom信息
     * @return 客户bom信息集合
     */
    public List<SmtBomVO> selectSmtBomAllList(SmtBom smtBom);

    /**
     * 新增客户bom信息
     * 
     * @param smtBom 客户bom信息
     * @return 结果
     */
    public int insertSmtBom(SmtBom smtBom);

    /**
     * 新增客户bom信息和明细信息
     *
     * @param smtBom 客户bom信息
     * @return 结果
     */
    public int insertSmtBomAndLine(SmtBom smtBom);

    /**
     * 修改客户bom信息
     * 
     * @param smtBom 客户bom信息
     * @return 结果
     */
    public int updateSmtBom(SmtBom smtBom);

    /**
     * 修改客户bom信息和bom明细
     *
     * @param smtBom 客户bom信息
     * @return 结果
     */
    public int updateSmtBomAndBomLine(SmtBom smtBom);

    /**
     * 批量删除客户bom信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtBomByIds(String ids);

    /**
     * 删除客户bom信息信息
     * 
     * @param id 客户bom信息ID
     * @return 结果
     */
    public int deleteSmtBomById(Integer id);

    /**
     * 导入客户bom
     * @param is
     * @return
     */
    SmtBom importExcel(InputStream is) throws Exception;

    /**
     * 验证客户bom唯一性
     * @param bom
     * @return
     */
    public int validateCusBomUnique(SmtBom bom);

    /**
     * 根据bomName查询总点数
     * @param bomName
     * @return
     */
    String selectSmtBomByBomName(Integer cusCode,String bomName);

}
