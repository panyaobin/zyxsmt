package com.ruoyi.project.smt.delivery.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.smt.bom.mapper.SmtBomMapper;
import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.cus.mapper.SmtCusMapper;
import com.ruoyi.project.smt.delivery.domain.SmtDeliveryRecord;
import com.ruoyi.project.smt.delivery.domain.SmtDeliveryRecordVO;
import com.ruoyi.project.smt.delivery.mapper.SmtDeliveryRecordMapper;
import com.ruoyi.project.smt.delivery.service.ISmtDeliveryRecordService;
import com.ruoyi.project.smt.ship.domain.SmtProductShipVO;
import com.ruoyi.project.smt.ship.mapper.SmtProductShipMapper;
import net.sf.json.JSONArray;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 发料记录Service业务层处理
 *
 * @author popo
 * @date 2019-11-03
 */
@Service
public class SmtDeliveryRecordServiceImpl implements ISmtDeliveryRecordService {
    @Autowired
    private SmtDeliveryRecordMapper smtDeliveryRecordMapper;

    @Autowired
    private SmtCusMapper smtCusMapper;


    @Autowired
    private SmtProductShipMapper smtProductShipMapper;

    @Autowired
    private SmtBomMapper smtBomMapper;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 查询发料记录
     *
     * @param id 发料记录ID
     * @return 发料记录
     */
    @Override
    public SmtDeliveryRecord selectSmtDeliveryRecordById(Integer id) {
        return smtDeliveryRecordMapper.selectSmtDeliveryRecordById(id);
    }

    /**
     * 查询发料记录列表
     *
     * @param smtDeliveryRecord 发料记录
     * @return 发料记录
     */
    @Override
    public List<SmtDeliveryRecord> selectSmtDeliveryRecordList(SmtDeliveryRecord smtDeliveryRecord) {
        return smtDeliveryRecordMapper.selectSmtDeliveryRecordList(smtDeliveryRecord);
    }

    /**
     * 查询FPC在线列表
     *
     * @return 发料记录
     */
    @Override
    public List<SmtDeliveryRecord> selectFpcOnLineListList(SmtDeliveryRecord deliveryRecord) {
        List<SmtDeliveryRecord> list = smtDeliveryRecordMapper.selectFpcOnLineListList(deliveryRecord);
        List<SmtCus> cusList = smtCusMapper.selectSmtCusList(new SmtCus());
        Map<Integer, String> cusNameMap = cusList.stream().collect(Collectors.toMap(SmtCus::getCusCode, SmtCus::getCusName, (x1, y1) -> x1));
        for (SmtDeliveryRecord record : list) {
            record.setCusName(cusNameMap.get(record.getCusCode()));
            record.setBomName(smtBomMapper.selectSmtBomById(Long.valueOf(record.getBomId())).getBomName());
            record.setTypeName(Constants.FPC_TYPE_NAME);
            int i = getSumDeliveryQty(record);
            record.setSumDeliveryQty(i);
        }
        return list;
    }

    /**
     * 查询电子料在线列表
     *
     * @return 发料记录
     */
    @Override
    public List<SmtDeliveryRecord> selectDzlOnLineListList(SmtDeliveryRecord deliveryRecord) {
        List<SmtDeliveryRecord> list = smtDeliveryRecordMapper.selectDzlOnLineListList(deliveryRecord);
        List<SmtCus> cusList = smtCusMapper.selectSmtCusList(new SmtCus());
        Map<Integer, String> cusNameMap = cusList.stream().collect(Collectors.toMap(SmtCus::getCusCode, SmtCus::getCusName, (x1, y1) -> x1));
        for (SmtDeliveryRecord record : list) {
            record.setCusName(cusNameMap.get(record.getCusCode()));
            int i = getSumDeliveryQty(record);
            record.setSumDeliveryQty(i);
        }
        return list;
    }

    /**
     * 统计就是发货的数量，包含退货
     *
     * @param record
     * @return
     */
    private int getSumDeliveryQty(SmtDeliveryRecord record) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("bomId", record.getBomId());
        map.put("orderType", record.getOrderType());
        if (Constants.BOM_TYPE_FPC.intValue() == record.getOrderType().intValue()) {
            map.put("orderNo", record.getOrderNo());
        }
        map.put("cusCode", record.getCusCode());
        SmtProductShipVO qty = smtProductShipMapper.getFpcShipQty(map);
        int sumBak = 0;
        int sumShipQty = 0;
        if (null != qty) {
            sumBak = Optional.ofNullable(qty.getSumBak()).orElse(0);
            sumShipQty = Optional.ofNullable(qty.getSumShipQty()).orElse(0);
        }
        return record.getSumDeliveryQty() - sumBak - sumShipQty;
    }

    /**
     * 新增发料记录
     *
     * @param smtDeliveryRecord 发料记录
     * @return 结果
     */
    @Override
    public int insertSmtDeliveryRecord(SmtDeliveryRecord smtDeliveryRecord) {
        smtDeliveryRecord.setCreateTime(DateUtils.getNowDate());
        return smtDeliveryRecordMapper.insertSmtDeliveryRecord(smtDeliveryRecord);
    }

    @Override
    public int batchInsertSmtDeliveryRecord(SmtDeliveryRecordVO vo) {
        List<SmtDeliveryRecord> recordList = smtDeliveryRecordMapper.selectSmtDeliveryRecordList(new SmtDeliveryRecord());
        List<SmtDeliveryRecord> cacheList = Lists.newArrayList();
        int no;
        if (StringUtils.isNotEmpty(recordList)) {
            no = recordList.stream().mapToInt(SmtDeliveryRecord::getDeliveryNo).max().getAsInt();
        } else {
            no = Constants.DELIVERY_NO_START;
        }
        List<SmtDeliveryRecord> voList = (List<SmtDeliveryRecord>) JSONArray.toCollection(JSONArray.fromObject(vo.getDeliveryDzlList()), SmtDeliveryRecord.class);
        for (SmtDeliveryRecord record : voList) {
            record.setCreateBy(ShiroUtils.getLoginName());
            record.setCreateTime(new Date());
            //如果发料数量超过1，说明是批量或者成套发料，则发料单号是同一个
            if (voList.size() == 1) {
                record.setDeliveryNo(++no);
            } else {
                record.setDeliveryNo(no + 1);
            }
            cacheList.add(record);
            smtDeliveryRecordMapper.insertSmtDeliveryRecord(record);
        }
        Cache<String, Object> cache = cacheManager.getCache("defaultCache");
        cache.put("deliveryCacheList", cacheList);
        return 1;
    }


    /**
     * 修改发料记录
     *
     * @param smtDeliveryRecord 发料记录
     * @return 结果
     */
    @Override
    public int updateSmtDeliveryRecord(SmtDeliveryRecord smtDeliveryRecord) {
        smtDeliveryRecord.setUpdateTime(DateUtils.getNowDate());
        return smtDeliveryRecordMapper.updateSmtDeliveryRecord(smtDeliveryRecord);
    }

    /**
     * 删除发料记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtDeliveryRecordByIds(String ids) {
        return smtDeliveryRecordMapper.deleteSmtDeliveryRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发料记录信息
     *
     * @param id 发料记录ID
     * @return 结果
     */
    @Override
    public int deleteSmtDeliveryRecordById(Integer id) {
        return smtDeliveryRecordMapper.deleteSmtDeliveryRecordById(id);
    }

    @Override
    public Integer getDeliveryQty(SmtDeliveryRecord record) {
        return smtDeliveryRecordMapper.getDeliveryQty(record);
    }

    @Override
    public List<SmtDeliveryRecord> getDeliveryRecordByNo(String deliveryNo) {
        return smtDeliveryRecordMapper.getDeliveryRecordByNo(deliveryNo);
    }
}
