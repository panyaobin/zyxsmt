package com.ruoyi.project.smt.entry.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.smt.bom.vo.SmtBomDeliveryVO;
import com.ruoyi.project.smt.entry.domain.SmtOrderEntry;
import com.ruoyi.project.smt.entry.mapper.SmtOrderEntryMapper;
import com.ruoyi.project.smt.entry.service.ISmtOrderEntryService;
import com.ruoyi.project.smt.entry.vo.SmtOrderEntryVO;
import com.ruoyi.project.smt.entryLine.domain.SmtOrderEntryLine;
import com.ruoyi.project.smt.entryLine.mapper.SmtOrderEntryLineMapper;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单入库Service业务层处理
 *
 * @author popo
 * @date 2019-10-20
 */
@Service
public class SmtOrderEntryServiceImpl implements ISmtOrderEntryService {
    @Autowired
    private SmtOrderEntryMapper smtOrderEntryMapper;

    @Autowired
    private SmtOrderEntryLineMapper smtOrderEntryLineMapper;

    /**
     * 查询订单入库
     *
     * @param id 订单入库ID
     * @return 订单入库
     */
    @Override
    public SmtOrderEntry selectSmtOrderEntryById(Integer id) {
        return smtOrderEntryMapper.selectSmtOrderEntryById(id);
    }

    /**
     * 查询订单入库列表
     *
     * @param smtOrderEntry 订单入库
     * @return 订单入库
     */
    @Override
    public List<SmtOrderEntry> selectSmtOrderEntryList(SmtOrderEntry smtOrderEntry) {
        return smtOrderEntryMapper.selectSmtOrderEntryList(smtOrderEntry);
    }

    /**
     * 新增订单入库
     *
     * @param smtOrderEntry 订单入库
     * @return 结果
     */
    @Override
    public int insertSmtOrderEntry(SmtOrderEntry smtOrderEntry) {
        smtOrderEntry.setCreateTime(DateUtils.getNowDate());
        return smtOrderEntryMapper.insertSmtOrderEntry(smtOrderEntry);
    }

    /**
     * 新增订单入库及明细
     *
     * @param smtOrderEntry 订单入库
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSmtOrderEntryAndLine(SmtOrderEntry smtOrderEntry) {
        smtOrderEntry.setRemark(smtOrderEntry.getRemarks());
        smtOrderEntry.setCreateBy(ShiroUtils.getLoginName());
        smtOrderEntry.setCreateTime(DateUtils.getNowDate());
        smtOrderEntryMapper.insertSmtOrderEntry(smtOrderEntry);
        //明细信息
        List<SmtOrderEntryLine> lineList = (List<SmtOrderEntryLine>) JSONArray.toCollection(JSONArray.fromObject(smtOrderEntry.getEntryLineList()), SmtOrderEntryLine.class);
        lineList.stream().forEach(line -> {
            line.setEntryId(smtOrderEntry.getId());
            line.setStatus(Constants.SUCCESS);
            line.setCreateBy(ShiroUtils.getLoginName());
            line.setCreateTime(DateUtils.getNowDate());
            line.setDelFlag(Constants.SUCCESS);
        });
        return smtOrderEntryLineMapper.batchInsertSmtEntryLine(lineList);
    }

    /**
     * 修改订单入库
     *
     * @param smtOrderEntry 订单入库
     * @return 结果
     */
    @Override
    public int updateSmtOrderEntry(SmtOrderEntry smtOrderEntry) {
        smtOrderEntry.setUpdateTime(DateUtils.getNowDate());
        return smtOrderEntryMapper.updateSmtOrderEntry(smtOrderEntry);
    }

    /**
     * 修改订单入库及明细
     *
     * @param smtOrderEntry 订单入库
     * @return 结果
     */
    @Override
    public int updateSmtBomAndBomLine(SmtOrderEntry smtOrderEntry) {
        if (StringUtils.isNotEmpty(smtOrderEntry.getRemark()) && smtOrderEntry.getRemark().split(",").length > 0) {
            smtOrderEntry.setRemark(smtOrderEntry.getRemark().split(",")[0]);
        }else{
            smtOrderEntry.setRemark("");
        }
        smtOrderEntry.setUpdateTime(DateUtils.getNowDate());
        smtOrderEntry.setUpdateBy(ShiroUtils.getLoginName());
        smtOrderEntryMapper.updateSmtOrderEntry(smtOrderEntry);
        //删除原有的订单入库明细信息
        smtOrderEntryLineMapper.deleteSmtOrderEntryLineByEntryId(smtOrderEntry.getId());
        //新增订单入库明细信息
        String entryLineList = smtOrderEntry.getEntryLineList();
        List<SmtOrderEntryLine> orderEntryLineList = (List<SmtOrderEntryLine>) JSONArray.toCollection(JSONArray.fromObject(entryLineList), SmtOrderEntryLine.class);
        orderEntryLineList.stream().forEach(line -> {
            line.setEntryId(smtOrderEntry.getId());
            line.setStatus(Constants.SUCCESS);
            line.setCreateBy(ShiroUtils.getLoginName());
            line.setDelFlag(Constants.SUCCESS);
            line.setCreateTime(DateUtils.getNowDate());
        });
        return smtOrderEntryLineMapper.batchInsertSmtEntryLine(orderEntryLineList);
    }

    /**
     * 删除订单入库对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteSmtOrderEntryByIds(String ids) {
        String[] idArrs = Convert.toStrArray(ids);
        //先删除主表数据
        smtOrderEntryMapper.deleteSmtOrderEntryByIds(idArrs);
        //再删除明细表数据
        return smtOrderEntryLineMapper.deleteSmtOrderEntryLineByEntryIds(idArrs);
    }

    /**
     * 删除订单入库信息
     *
     * @param id 订单入库ID
     * @return 结果
     */
    @Override
    public int deleteSmtOrderEntryById(Integer id) {
        return smtOrderEntryMapper.deleteSmtOrderEntryById(id);
    }


    @Override
    public List<SmtOrderEntryVO> selectSmtEntryAllList(SmtOrderEntry orderEntry) {
        return smtOrderEntryMapper.selectSmtEntryAllList(orderEntry);
    }

    @Override
    public List<SmtOrderEntryVO> selectSmtEntryAllDzlList(SmtOrderEntry orderEntry) {
        return smtOrderEntryMapper.selectSmtEntryAllDzlList(orderEntry);
    }

    @Override
    public Integer getOrderQty(SmtBomDeliveryVO vo) {
        return smtOrderEntryMapper.getOrderQty(vo);
    }

    @Override
    public List<SmtOrderEntryVO> dzlEntryList(SmtOrderEntry entry) {
        return smtOrderEntryMapper.dzlEntryList(entry);
    }
}
