package com.ruoyi.project.smt.ship.mapper;

import com.ruoyi.project.smt.ship.domain.SmtProductShip;
import com.ruoyi.project.smt.ship.domain.SmtProductShipVO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 产品出货Mapper接口
 * 
 * @author popo
 * @date 2019-11-27
 */
@Component
public interface SmtProductShipMapper 
{
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
     * 新增产品出货
     * 
     * @param smtProductShip 产品出货
     * @return 结果
     */
    public int insertSmtProductShip(SmtProductShip smtProductShip);

    /**
     * 批量新增产品出货
     *
     * @param list 产品出货集合
     * @return 结果
     */
    public int batchInsertSmtProductShip(List<SmtProductShip> list);

    /**
     * 修改产品出货
     * 
     * @param smtProductShip 产品出货
     * @return 结果
     */
    public int updateSmtProductShip(SmtProductShip smtProductShip);

    /**
     * 删除产品出货
     * 
     * @param id 产品出货ID
     * @return 结果
     */
    public int deleteSmtProductShipById(Integer id);

    /**
     * 根据出货单号删除产品出货
     *
     * @param shipNos 出货单号集合
     * @return 结果
     */
    public int deleteSmtProductShipByShipNos(Integer[] shipNos);

    /**
     * 批量删除产品出货
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtProductShipByIds(String[] ids);

    /**
     * 统计FPC已出货量
     * @param map
     * @return
     */
    SmtProductShipVO getFpcShipQty(HashMap map);

    /**
     * 通过出货单号查询FPC出货数量
     * @param shipNo
     * @return
     */
    Integer selectSmtProductShipByShipNoAndOrderType(Integer shipNo);
}
