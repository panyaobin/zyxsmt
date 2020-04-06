package com.ruoyi.project.smt.delivery.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.service.ISmtBomService;
import com.ruoyi.project.smt.bom.vo.SmtBomDeliveryVO;
import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import com.ruoyi.project.smt.bomLine.service.ISmtBomLineService;
import com.ruoyi.project.smt.delivery.domain.DeliveryPrintVO;
import com.ruoyi.project.smt.delivery.domain.SmtDeliveryRecord;
import com.ruoyi.project.smt.delivery.domain.SmtDeliveryRecordVO;
import com.ruoyi.project.smt.delivery.service.ISmtDeliveryRecordService;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import com.ruoyi.project.smt.entry.domain.SmtOrderEntry;
import com.ruoyi.project.smt.entry.service.ISmtOrderEntryService;
import com.ruoyi.project.smt.entry.vo.SmtOrderEntryVO;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 发料记录Controller
 *
 * @author popo
 * @date 2019-11-03
 */
@Controller
@RequestMapping("/smt/delivery")
public class SmtDeliveryRecordController extends BaseController {
    private String prefix = "smt/delivery";

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ISmtDeliveryRecordService smtDeliveryRecordService;

    @Autowired
    private ISmtOrderEntryService smtOrderEntryService;

    @Autowired
    private ISmtDzlService smtDzlService;

    @Autowired
    private ISmtBomService smtBomService;


    @Autowired
    private ISmtBomLineService smtBomLineService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IUserService userService;


    @Value("${jasper.path.entry}")
    private String jasperEntryPath;


    @RequiresPermissions("smt:delivery:view")
    @GetMapping()
    public String delivery() {
        return prefix + "/record";
    }


    /**
     * -------------------------------------------------------------------FPC-------------------------------------------------------------
     * FPC仓列表
     *
     * @return
     */
    @RequiresPermissions("smt:delivery:view")
    @GetMapping("/fpc")
    public String fpc() {
        return prefix + "/fpc";
    }

    /**
     * 查询fpc仓列表
     */
    @RequiresPermissions("smt:delivery:list")
    @PostMapping("/fpcList")
    @ResponseBody
    public TableDataInfo fpcList(SmtOrderEntry entry) {
        entry.setOrderType(Constants.BOM_TYPE_FPC);
        List<SmtOrderEntryVO> list = smtOrderEntryService.selectSmtEntryAllList(entry);
        for (SmtOrderEntryVO vo : list) {
            SmtDeliveryRecord record = new SmtDeliveryRecord();
            record.setCusCode(vo.getCusCode());
            record.setBomId(vo.getBomId());
            record.setOrderType(vo.getOrderType());
            record.setOrderNo(vo.getOrderNo());
            Integer deliveryedQty = smtDeliveryRecordService.getDeliveryQty(record);
            int i = deliveryedQty == null ? 0 : deliveryedQty.intValue();
            vo.setOrderQty(vo.getOrderQty() - i);
        }
        startPage();
        List<SmtOrderEntryVO> collect = list.stream().filter(entry1 -> entry1.getOrderQty().intValue() > 0).collect(Collectors.toList());
        return getDataTable(convertList(collect));
    }

    /**
     * 转化订单入库数据
     *
     * @param list 订单入库元数据
     * @return
     */
    private List<SmtOrderEntryVO> convertList(List<SmtOrderEntryVO> list) {
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        Map<Integer, String> dzlNameMap = getDzlIdAndDzlTypeNameMap();
        list.stream().forEach(bom -> {
                    bom.setCusName(cusNameMap.get(bom.getCusCode()));
                    if (1 == bom.getOrderType().intValue()) {
                        bom.setTypeNo(smtBomService.selectSmtBomById(Long.valueOf(bom.getBomId())).getBomName());
                        bom.setTypeName(Constants.FPC_TYPE_NAME);
                    } else {
                        bom.setTypeNo(smtDzlService.selectSmtDzlById(Long.valueOf(bom.getBomId())).getDzlName());
                        bom.setTypeName(dzlNameMap.get(bom.getBomId()));
                    }
                }
        );
        return list;
    }


    /**
     * -------------------------------------------------------------------DZL-------------------------------------------------------------
     * DZL仓列表
     *
     * @return
     */
    @RequiresPermissions("smt:delivery:view")
    @GetMapping("/dzl")
    public String dzl() {
        return prefix + "/dzl";
    }


    /**
     * 查询查询dzl仓列表
     */
    @RequiresPermissions("smt:delivery:list")
    @PostMapping("/dzlList")
    @ResponseBody
    public TableDataInfo dzlList(SmtOrderEntry entry) {
        entry.setOrderType(Constants.BOM_TYPE_DZL);
        List<SmtOrderEntryVO> list = smtOrderEntryService.selectSmtEntryAllDzlList(entry);
        for (SmtOrderEntryVO vo : list) {
            SmtDeliveryRecord record = new SmtDeliveryRecord();
            record.setBomId(vo.getBomId());
            record.setCusCode(vo.getCusCode());
            record.setOrderType(vo.getOrderType());
            Integer deliveryedQty = smtDeliveryRecordService.getDeliveryQty(record);
            int i = deliveryedQty == null ? 0 : deliveryedQty.intValue();
            vo.setSumOrderQty(vo.getSumOrderQty() - i);
        }
        startPage();
        List<SmtOrderEntryVO> collect = list.stream().filter(dzl -> dzl.getSumOrderQty().intValue() > 0).collect(Collectors.toList());
        return getDataTable(convertList(collect));
    }


    @RequiresPermissions("smt:delivery:view")
    @GetMapping("/warehouse")
    public String warehouse() {
        return prefix + "/warehouse";
    }

    /**
     * 获取所有电子料
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllDzl")
    public List<SmtDzl> getAllDzl() {
        return smtDzlService.selectSmtDzlList(new SmtDzl());
    }


    /**
     * 查询客户bom信息列表
     */
    @RequiresPermissions("smt:delivery:list")
    @PostMapping("/warehouseList")
    @ResponseBody
    public TableDataInfo warehouseList(SmtBom smtBom) {
        startPage();
        List<SmtBom> list = smtBomService.selectSmtBomList(smtBom);
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        list.stream().forEach(bom -> bom.setCusName(cusNameMap.get(bom.getCusCode())));
        return getDataTable(list);
    }

    /**
     * FPC仓，DZL仓发料页面跳转
     *
     * @return
     */
    @RequiresPermissions("smt:delivery:add")
    @GetMapping("/deliveryDzl")
    public String deliveryDzl() {
        return prefix + "/deliveryDzl";
    }


    /**
     * 仓库开始发料页面跳转
     *
     * @return
     */
    @RequiresPermissions("smt:delivery:add")
    @GetMapping("/deliveryBom/{bomId}")
    public String deliveryBom(@PathVariable Integer bomId, Model model) {
        model.addAttribute("bomId", bomId);
        return prefix + "/deliveryBom";
    }

    /**
     * FPC发料记录页面跳转
     *
     * @return
     */
    @RequiresPermissions("smt:delivery:view")
    @GetMapping("/fpcRecord")
    public String fpcRecord() {
        return prefix + "/fpcRecord";
    }

    /**
     * DZL发料记录页面跳转
     *
     * @return
     */
    @RequiresPermissions("smt:delivery:view")
    @GetMapping("/dzlRecord")
    public String dzlRecord() {
        return prefix + "/dzlRecord";
    }

    /**
     * 查询发料记录列表
     */
    @RequiresPermissions("smt:delivery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtDeliveryRecord smtDeliveryRecord) {
        startPage();
        List<SmtDeliveryRecord> list = smtDeliveryRecordService.selectSmtDeliveryRecordList(smtDeliveryRecord);
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        Map<Integer, String> dzlTypeNameMap = getDzlIdAndDzlTypeNameMap();
        Map<Integer, String> dzlNameMap = getDzlIdAndDzlNameMap();
        for (SmtDeliveryRecord record : list) {
            record.setCusName(cusNameMap.get(record.getCusCode()));
            if (smtDeliveryRecord.getOrderType().intValue() == Constants.BOM_TYPE_DZL) {
                record.setDzlName(dzlNameMap.get(record.getBomId()));
                record.setTypeName(dzlTypeNameMap.get(record.getBomId()));
            } else {
                record.setBomName(smtBomService.selectSmtBomById(Long.valueOf(record.getBomId())).getBomName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出发料记录列表
     */
    @RequiresPermissions("smt:delivery:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtDeliveryRecord smtDeliveryRecord) {
        List<SmtDeliveryRecord> list = smtDeliveryRecordService.selectSmtDeliveryRecordList(smtDeliveryRecord);
        ExcelUtil<SmtDeliveryRecord> util = new ExcelUtil<SmtDeliveryRecord>(SmtDeliveryRecord.class);
        return util.exportExcel(list, "delivery");
    }

    /**
     * 新增发料记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }


    /**
     * 新增保存发料记录
     */
    @RequiresPermissions("smt:delivery:add")
    @Log(title = "发料记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtDeliveryRecordVO vo) {
        return toAjax(smtDeliveryRecordService.batchInsertSmtDeliveryRecord(vo));
    }

    /**
     * 修改发料记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtDeliveryRecord smtDeliveryRecord = smtDeliveryRecordService.selectSmtDeliveryRecordById(id);
        mmap.put("smtDeliveryRecord", smtDeliveryRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存发料记录
     */
    @RequiresPermissions("smt:delivery:edit")
    @Log(title = "发料记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtDeliveryRecord smtDeliveryRecord) {
        return toAjax(smtDeliveryRecordService.updateSmtDeliveryRecord(smtDeliveryRecord));
    }

    /**
     * 删除发料记录
     */
    @RequiresPermissions("smt:delivery:remove")
    @Log(title = "发料记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smtDeliveryRecordService.deleteSmtDeliveryRecordByIds(ids));
    }

    /**
     * 通过bomId查询bom明细信息
     *
     * @param smtBomLine
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBomLineById")
    public List<SmtBomLine> getBomLineById(SmtBomLine smtBomLine) {
        Map<Integer, String> dzlNameMap = getDzlIdAndDzlTypeNameMap();
        List<SmtBomLine> smtBomLineList = smtBomLineService.selectSmtBomLineList(smtBomLine);
        smtBomLineList.stream().forEach(line -> line.setTypeName(dzlNameMap.get(line.getDzlId())));
        return smtBomLineList;
    }

    /**
     * 通过bomId查询bom及组装明细信息
     *
     * @param bomId bomId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDeliveryInfoByBomId/{bomId}")
    public List<SmtBomDeliveryVO> getDeliveryInfoByBomId(@PathVariable Integer bomId) {
        List<SmtBomDeliveryVO> bomVOList = Lists.newArrayList();
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        Map<Integer, String> typeNameMap = getDzlIdAndDzlTypeNameMap();
        Map<Integer, String> dzlNameMap = getDzlIdAndDzlNameMap();
        SmtBom smtBom = smtBomService.selectSmtBomById(Long.valueOf(bomId));
        //组装bom明细信息
        SmtBomLine bomLine = new SmtBomLine();
        bomLine.setBomId(bomId);
        List<SmtBomLine> lineList = smtBomLineService.selectSmtBomLineList(bomLine);
        for (SmtBomLine line : lineList) {
            SmtBomDeliveryVO vo = new SmtBomDeliveryVO();
            vo.setBomId(line.getDzlId());
            vo.setDzlNumber(line.getDzlNumber());
            vo.setCusCode(smtBom.getCusCode());
            vo.setDzlName(dzlNameMap.get(line.getDzlId()));
            vo.setCusName(cusNameMap.get(smtBom.getCusCode()));
            vo.setOrderType(Constants.BOM_TYPE_DZL);
            vo.setTypeName(typeNameMap.get(line.getDzlId()));
            //查询DZL所有的入库量
            Integer qty = smtOrderEntryService.getOrderQty(vo);
            if (null == qty) {
                vo.setSumOrderQty(0);
            } else {
                SmtDeliveryRecord record = new SmtDeliveryRecord();
                record.setBomId(vo.getBomId());
                record.setOrderType(vo.getOrderType());
                record.setCusCode(vo.getCusCode());
                Integer dq = smtDeliveryRecordService.getDeliveryQty(record);
                int i = null == dq ? 0 : dq.intValue();
                vo.setSumOrderQty(qty - i);
            }

            bomVOList.add(vo);
        }
        return bomVOList;
    }

    /**
     * 打印入库单
     *
     * @param response
     */
    @RequestMapping("/print")
    public void entryPrint(HttpServletResponse response) {
        Cache<String, Object> cache = cacheManager.getCache("defaultCache");
        List<SmtDeliveryRecord> recordList = (List<SmtDeliveryRecord>) cache.get("deliveryCacheList");
        buildDeliveryPrintData(response, recordList, 1);
    }

    /**
     * @param response
     * @param recordList
     * @param code       1.代表保存打印  2.代表重复打印
     */
    private void buildDeliveryPrintData(HttpServletResponse response, List<SmtDeliveryRecord> recordList, int code) {
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        List<DeliveryPrintVO> printVOList = Lists.newArrayList();
        for (int i = 0; i < recordList.size(); i++) {
            SmtDeliveryRecord record = recordList.get(i);
            DeliveryPrintVO printVO = new DeliveryPrintVO();
            printVO.setIndex(String.valueOf(i + 1));
            //如果是 FPC，则需要获取 bomName和 bomType
            if (Constants.BOM_TYPE_FPC.intValue() == record.getOrderType().intValue()) {
                printVO.setBomName(smtBomService.selectSmtBomById(Long.valueOf(record.getBomId())).getBomName());
                printVO.setBomType(Constants.FPC_TYPE_NAME);
            }
            //如果是 DZL，则需要获取 dzlName和 bomType
            if (Constants.BOM_TYPE_DZL.intValue() == record.getOrderType().intValue()) {
                SmtDzl dzl = smtDzlService.selectSmtDzlById(Long.valueOf(record.getBomId()));
                printVO.setBomName(dzl.getDzlName());
                printVO.setBomType(dzl.getTypeName());
            }
            printVO.setDeliveryQty(String.valueOf(record.getDeliveryQty()));
            printVO.setRemark(null == record.getRemark() ? "" : record.getRemark());
            printVOList.add(printVO);
        }
        //如果数据条数不足十条
        int size = printVOList.size();
        if (size < 10) {
            for (int i = 0; i < 10 - size; i++) {
                DeliveryPrintVO vo = new DeliveryPrintVO();
                vo.setIndex(String.valueOf(size + i + 1));
                vo.setBomType("");
                vo.setBomName("");
                vo.setRemark("");
                vo.setDeliveryQty("");
                printVOList.add(vo);
            }
        }

//      这里是发料打印  String jasperPath = "D:\\entry.jasper";
        String jasperPath = jasperEntryPath;
        Map<String, Object> map = new HashMap<>(10);
        map.put("companyName", configService.selectConfigByKey("print.ship.companyName"));
        map.put("sendNo", String.valueOf(recordList.get(0).getDeliveryNo()));
        map.put("customerName", cusNameMap.get(recordList.get(0).getCusCode()));
        map.put("createDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if (code == 1) {
            map.put("createUser", getSysUser().getUserName());
        } else {
            String createBy = recordList.get(0).getCreateBy();
            User user = userService.selectUserByLoginName(createBy);
            map.put("createUser", user.getUserName());
        }
        try {
            System.out.println(jasperPath);
            demo(response, map, printVOList, jasperPath);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新打印
     *
     * @param response
     */
    @RequestMapping("/rePrint/{no}")
    public void rePrint(HttpServletResponse response, @PathVariable("no") String deliveryNo) {
        List<SmtDeliveryRecord> recordList = smtDeliveryRecordService.getDeliveryRecordByNo(deliveryNo);
        buildDeliveryPrintData(response, recordList, 2);
    }
}
