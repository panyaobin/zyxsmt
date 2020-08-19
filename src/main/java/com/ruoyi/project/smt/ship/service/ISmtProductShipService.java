package com.ruoyi.project.smt.ship.service;

import com.ruoyi.project.smt.ship.domain.SmtProductShip;

import java.util.List;
import java.util.Map;

/**
 * 产品出货Service接口
 *
 * @author popo
 * @date 2019-11-27
 */
public interface ISmtProductShipService {
    /**
     * 查询产品出货
     *
     * @param id 产品出货ID
     * @return 产品出货
     */
    public SmtProductShip selectSmtProductShipById(Integer id);

    /**
     * 查询产品出货列表
     *
     * @param smtProductShip 产品出货
     * @return 产品出货集合
     */
    public List<SmtProductShip> selectSmtProductShipList(SmtProductShip smtProductShip);

    /**
     * dzl出货查询产品出货列表
     *
     * @param smtProductShip 产品出货
     * @return 产品出货集合
     */
    public List<SmtProductShip> selectSmtProductShipExportList(SmtProductShip smtProductShip);

    /**
     * 新增产品出货
     *
     * @param smtProductShip 产品出货
     * @return 结果
     */
    public int insertSmtProductShip(SmtProductShip smtProductShip);

    /**
     * 批量新增产品出货
     *
     * @param smtProductShip 产品出货
     * @return 结果
     */
    public int batchInsertSmtProductShip(SmtProductShip smtProductShip);

/**
     * 批量新增DZL退货
     *
     * @param smtProductShip DZL退货
     * @return 结果
     */
    public int batchInsertSmtProductShipDzl(SmtProductShip smtProductShip);


    /**
     * FPC计算电子料以后新增保存产品出货
     *
     * @param smtProductShip 产品出货
     * @return 结果
     */
    public int batchAddSmtProductShip(SmtProductShip smtProductShip);

    /**
     * 修改产品出货
     *
     * @param smtProductShip 产品出货
     * @return 结果
     */
    public int updateSmtProductShip(SmtProductShip smtProductShip);

    /**
     * 批量删除产品出货
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtProductShipByIds(String ids);

    /**
     * 删除退货记录
     * @param ids
     * @return
     */
    public int deleteSmtProductShipReturnByIds(String ids);

    /**
     * 删除产品出货信息
     *
     * @param id 产品出货ID
     * @return 结果
     */
    public int deleteSmtProductShipById(Integer id);
}
