package com.ruoyi.project.smt.ship.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.mapper.SmtBomMapper;
import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import com.ruoyi.project.smt.bomLine.mapper.SmtBomLineMapper;
import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.cus.mapper.SmtCusMapper;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.mapper.SmtDzlMapper;
import net.sf.json.JSONArray;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smt.ship.mapper.SmtProductShipMapper;
import com.ruoyi.project.smt.ship.domain.SmtProductShip;
import com.ruoyi.project.smt.ship.service.ISmtProductShipService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 产品出货Service业务层处理
 *
 * @author popo
 * @date 2019-11-27
 */
@Service
public class SmtProductShipServiceImpl implements ISmtProductShipService {
    @Autowired
    private SmtProductShipMapper smtProductShipMapper;

    @Autowired
    private SmtBomMapper smtBomMapper;

    @Autowired
    private SmtDzlMapper smtDzlMapper;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SmtCusMapper smtCusMapper;

    @Autowired
    private SmtBomLineMapper smtBomLineMapper;

    /**
     * 查询产品出货
     *
     * @param id 产品出货ID
     * @return 产品出货
     */
    @Override
    public SmtProductShip selectSmtProductShipById(Integer id) {
        return smtProductShipMapper.selectSmtProductShipById(id);
    }

    /**
     * 查询产品出货列表
     *
     * @param smtProductShip 产品出货
     * @return 产品出货
     */
    @Override
    public List<SmtProductShip> selectSmtProductShipList(SmtProductShip smtProductShip) {
        List<SmtProductShip> shipList = smtProductShipMapper.selectSmtProductShipList(smtProductShip);
        //查询所有的电子料信息
        List<SmtDzl> dzlList = smtDzlMapper.selectSmtDzlList(new SmtDzl());
        Map<Integer, String> dzlNameMap = dzlList.stream().collect(Collectors.toMap(SmtDzl::getId, SmtDzl::getDzlName, (x, y) -> x));
        Map<Integer, String> typeNameMap = dzlList.stream().collect(Collectors.toMap(SmtDzl::getId, SmtDzl::getTypeName, (x, y) -> x));
        //查询所有的客户信息
        List<SmtCus> cusList = smtCusMapper.selectSmtCusList(new SmtCus());
        Map<Integer, String> cusNameMap = cusList.stream().collect(Collectors.toMap(SmtCus::getCusCode, SmtCus::getCusName, (x, y) -> x));
        for (SmtProductShip ship : shipList) {
            ship.setCusName(cusNameMap.get(ship.getCusCode()));
            ship.setShipExportNo(ship.getCusCode() + "" + ship.getShipNo());
            if (Constants.BOM_TYPE_DZL.intValue() == ship.getOrderType().intValue()) {
                ship.setBomName(dzlNameMap.get(ship.getBomId()));
                ship.setTypeName(typeNameMap.get(ship.getBomId()));
                //通过出货单号查询FPC出货数量,orderType为1(FPC)的
                Integer qty = smtProductShipMapper.selectSmtProductShipByShipNoAndOrderType(ship.getShipNo());
                ship.setFpcShipQty(qty);
            } else {
                SmtBom bom = smtBomMapper.selectSmtBomById(Long.valueOf(ship.getBomId()));
                ship.setBomName(bom.getBomName());
                //总点数
                int bomPoint = bom.getBomPoint().intValue();
                ship.setFpcShipPoint(bomPoint * ship.getShipQty());
                ship.setTypeName(Constants.FPC_TYPE_NAME);
            }
        }
        return shipList;
    }


    /**
     * DZL出货查询产品出货列表
     *
     * @param smtProductShip 产品出货
     * @return 产品出货
     */
    @Override
    public List<SmtProductShip> selectSmtProductShipExportList(SmtProductShip smtProductShip) {
        if (StringUtils.isNotEmpty(smtProductShip.getShipSearchNo())) {
            String no = smtProductShip.getShipSearchNo();
            smtProductShip.setCusSearchCode(no.substring(0, 3));
            smtProductShip.setShipSearchNo(no.substring(3));
        }
        List<SmtProductShip> shipList = smtProductShipMapper.selectSmtProductShipList(smtProductShip);
        //查询所有的电子料信息
        // List<SmtDzl> dzlList = smtDzlMapper.selectSmtDzlList(new SmtDzl());
        // Map<Integer, String> dzlNameMap = dzlList.stream().collect(Collectors.toMap(SmtDzl::getId, SmtDzl::getDzlName, (x, y) -> x));
        // Map<Integer, String> typeNameMap = dzlList.stream().collect(Collectors.toMap(SmtDzl::getId, SmtDzl::getTypeName, (x, y) -> x));
        //查询所有的客户信息
        List<SmtCus> cusList = smtCusMapper.selectSmtCusList(new SmtCus());
        Map<Integer, String> cusNameMap = cusList.stream().collect(Collectors.toMap(SmtCus::getCusCode, SmtCus::getCusName, (x, y) -> x));
        for (SmtProductShip ship : shipList) {
            ship.setCusName(cusNameMap.get(ship.getCusCode()));
            ship.setShipExportNo(ship.getCusCode() + "" + ship.getShipNo());
            // ship.setDzlName(dzlNameMap.get(ship.getBomId()));
            // ship.setTypeName(typeNameMap.get(ship.getBomId()));
            //通过出货单号查询FPC出货数量,orderType为1(FPC)的
            Integer qty = smtProductShipMapper.selectSmtProductShipByShipNoAndOrderType(ship.getShipNo());
            ship.setFpcShipQty(qty);
            //查询bom明细中的电子料用量，根据bomName和dzlId查询
            Integer dzlNumber = smtBomLineMapper.selectDzlNumberByBomNameAndDzlId(ship.getBomName(), ship.getBomId());
            ship.setDzlNumber(null == dzlNumber ? 0 : dzlNumber);
        }
        return shipList;
    }


    /**
     * 新增产品出货
     *
     * @param smtProductShip 产品出货
     * @return 结果
     */
    @Override
    public int insertSmtProductShip(SmtProductShip smtProductShip) {
        smtProductShip.setCreateTime(DateUtils.getNowDate());
        return smtProductShipMapper.insertSmtProductShip(smtProductShip);
    }

    /**
     * 批量新增产品出货
     *
     * @param smtProductShip 产品出货
     * @return 结果
     */
    @Override
    public int batchInsertSmtProductShip(SmtProductShip smtProductShip) {
        List<SmtProductShip> shipList = (List<SmtProductShip>) JSONArray.toCollection(JSONArray.fromObject(smtProductShip.getShipList()), SmtProductShip.class);
        int returnsStart = Constants.RETURNS_NO_START;
        int shipStart = Constants.SHIP_NO_START;

        SmtProductShip productShip = new SmtProductShip();
        Integer cusCode = shipList.get(0).getCusCode();
        Integer shipType = shipList.get(0).getShipType();
        int shipTypeValue = shipList.get(0).getShipType().intValue();
        List<SmtProductShip> productShipList = smtProductShipMapper.selectSmtProductShipList(productShip);
        int hasShipNo = productShipList.stream().map(SmtProductShip::getShipNo).max(Integer::compareTo).get();
        Integer shipNo = null;

        if (StringUtils.isNotEmpty(productShipList)) {
            shipNo = shipList.size() == 1 ? ++hasShipNo : hasShipNo + 1;
        } else if (Constants.SHIP_TYPE_SHIP.intValue() == shipTypeValue) {
            shipNo = shipList.size() == 1 ? ++shipStart : shipStart + 1;
        } else if (Constants.SHIP_TYPE_RETURNS.intValue() == shipTypeValue) {
            shipNo = shipList.size() == 1 ? ++returnsStart : returnsStart + 1;
        }
        for (SmtProductShip ship : shipList) {
            ship.setShipNo(shipNo);
            ship.setCreateBy(ShiroUtils.getLoginName());
            ship.setCreateTime(new Date());
            smtProductShipMapper.insertSmtProductShip(ship);
        }
        Cache<String, Object> cache = cacheManager.getCache("defaultCache");
        cache.put("returnsPrint", shipList);
        return 1;
    }

    /**
     * 批量新增产品出货
     *
     * @param smtProductShip 产品出货
     * @return 结果
     */
    @Override
    public int batchInsertSmtProductShipDzl(SmtProductShip smtProductShip) {
        List<SmtProductShip> shipList = (List<SmtProductShip>) JSONArray.toCollection(JSONArray.fromObject(smtProductShip.getShipList()), SmtProductShip.class);
        int returnsStart = Constants.RETURNS_NO_START;
        //根据客户编号和出货类型查询已经存在 出货单号或者退货单号
        SmtProductShip productShip = new SmtProductShip();
        productShip.setCusCode(shipList.get(0).getCusCode());
        productShip.setShipType(shipList.get(0).getShipType());
        //根据客户编号和出货类型查询已经存在 出货单号或者退货单号
        List<SmtProductShip> productShipList = smtProductShipMapper.selectSmtProductShipList(productShip);
        int shipNo;
        if (StringUtils.isNotEmpty(productShipList)) {
            shipNo = productShipList.stream().map(SmtProductShip::getShipNo).max(Integer::compareTo).get() + 1;
        } else {
            shipNo = ++returnsStart;
        }
        for (SmtProductShip ship : shipList) {
            ship.setShipNo(shipNo);
            ship.setCreateBy(ShiroUtils.getLoginName());
            ship.setCreateTime(new Date());
            smtProductShipMapper.insertSmtProductShip(ship);
        }
        Cache<String, Object> cache = cacheManager.getCache("defaultCache");
        cache.put("returnsPrint", shipList);
        return 1;
    }

    @Override
    public int batchAddSmtProductShip(SmtProductShip smtProductShip) {
        List<SmtProductShip> shipList = (List<SmtProductShip>) JSONArray.toCollection(JSONArray.fromObject(smtProductShip.getShipList()), SmtProductShip.class);
        SmtProductShip productShip = new SmtProductShip();
        productShip.setCusCode(shipList.get(0).getCusCode());
        productShip.setShipType(shipList.get(0).getShipType());

        int returnsStart = Constants.RETURNS_NO_START;
        int shipStart = Constants.SHIP_NO_START;
        //根据客户编号和出货类型查询已经存在 出货单号或者退货单号
        List<SmtProductShip> productShipList = smtProductShipMapper.selectSmtProductShipList(productShip);
        int newShipNo = 0;
        if (StringUtils.isNotEmpty(productShipList)) {
            int shipNo = productShipList.stream().map(SmtProductShip::getShipNo).max(Integer::compareTo).get();
            newShipNo = shipNo + 1;
        } else if (Constants.SHIP_TYPE_SHIP.intValue() == smtProductShip.getShipType().intValue()) {
            newShipNo = shipStart + 1;
        } else if (Constants.SHIP_TYPE_RETURNS.intValue() == smtProductShip.getShipType().intValue()) {
            newShipNo = returnsStart + 1;
        }
        for (SmtProductShip ship : shipList) {
            ship.setShipNo(newShipNo);
            ship.setCreateBy(ShiroUtils.getLoginName());
            ship.setCreateTime(new Date());
            smtProductShipMapper.insertSmtProductShip(ship);
        }
        Cache<String, Object> cache = cacheManager.getCache("defaultCache");
        cache.put("shipPrintList", shipList);
        return 1;
    }

    /**
     * 修改产品出货
     *
     * @param smtProductShip 产品出货
     * @return 结果
     */
    @Override
    public int updateSmtProductShip(SmtProductShip smtProductShip) {
        smtProductShip.setUpdateTime(DateUtils.getNowDate());
        return smtProductShipMapper.updateSmtProductShip(smtProductShip);
    }

    /**
     * 删除产品出货对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtProductShipByIds(String ids) {
        //选择的数据主键ID
        String[] strings = Convert.toStrArray(ids);
        Integer[] shipNos = new Integer[100];
        for (int i = 0; i < strings.length; i++) {
            SmtProductShip ship = smtProductShipMapper.selectSmtProductShipById(Integer.valueOf(strings[i]));
            shipNos[i] = ship.getShipNo();
        }
        //删除相同订单号的数据
        return smtProductShipMapper.deleteSmtProductShipByShipNos(shipNos);
    }


    @Override
    public int deleteSmtProductShipReturnByIds(String ids) {
        //选择的数据主键ID
        return smtProductShipMapper.deleteSmtProductShipByIds(Convert.toStrArray(ids));
    }


    /**
     * 删除产品出货信息
     *
     * @param id 产品出货ID
     * @return 结果
     */
    @Override
    public int deleteSmtProductShipById(Integer id) {
        return smtProductShipMapper.deleteSmtProductShipById(id);
    }
}
