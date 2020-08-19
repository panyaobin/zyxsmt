package com.ruoyi.project.smt.entry.mapper;

import com.ruoyi.project.smt.bom.vo.SmtBomDeliveryVO;
import com.ruoyi.project.smt.bom.vo.SmtBomVO;
import com.ruoyi.project.smt.entry.domain.SmtOrderEntry;
import com.ruoyi.project.smt.entry.vo.SmtOrderEntryVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单入库Mapper接口
 * 
 * @author popo
 * @date 2019-10-20
 */
@Component
public interface SmtOrderEntryMapper 
{
    /**
     * 查询订单入库
     * 
     * @param id 订单入库ID
     * @return 订单入库
     */
    public SmtOrderEntry selectSmtOrderEntryById(Integer id);

    /**
     * 查询订单入库列表
     * 
     * @param smtOrderEntry 订单入库
     * @return 订单入库集合
     */
    public List<SmtOrderEntry> selectSmtOrderEntryList(SmtOrderEntry smtOrderEntry);

    /**
     * 新增订单入库
     * 
     * @param smtOrderEntry 订单入库
     * @return 结果
     */
    public int insertSmtOrderEntry(SmtOrderEntry smtOrderEntry);

    /**
     * 修改订单入库
     * 
     * @param smtOrderEntry 订单入库
     * @return 结果
     */
    public int updateSmtOrderEntry(SmtOrderEntry smtOrderEntry);

    /**
     * 删除订单入库
     * 
     * @param id 订单入库ID
     * @return 结果
     */
    public int deleteSmtOrderEntryById(Integer id);

    /**
     * 批量删除订单入库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmtOrderEntryByIds(String[] ids);

    /**
     * 查询订单入库全部信息列表
     *
     * @param orderEntry 订单入库信息参数
     * @return 订单入库全部信息列表
     */
    public List<SmtOrderEntryVO> selectSmtEntryAllTableList(SmtOrderEntry orderEntry);

    public List<SmtOrderEntryVO> selectSmtEntryAllList(SmtOrderEntry orderEntry);

    /**
     * 查询所有电子料集合
     * @param orderEntry
     * @return
     */
    public List<SmtOrderEntryVO> selectSmtEntryAllDzlList(SmtOrderEntry orderEntry);

    /**
     * 查询入库数量
     * @param vo
     * @return
     */
    Integer getOrderQty(SmtBomDeliveryVO vo);

    /**
     * 查询电子料来料(入库)统计记录
     * @param entry
     * @return
     */
    List<SmtOrderEntryVO> dzlEntryList(SmtOrderEntry entry);
}
