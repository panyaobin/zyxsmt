package com.ruoyi.project.smt.entry.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.service.ISmtBomService;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import com.ruoyi.project.smt.entry.domain.SmtOrderEntry;
import com.ruoyi.project.smt.entry.service.ISmtOrderEntryService;
import com.ruoyi.project.smt.entry.vo.SmtOrderEntryVO;
import com.ruoyi.project.smt.entryLine.domain.SmtOrderEntryLine;
import com.ruoyi.project.smt.entryLine.service.ISmtOrderEntryLineService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单入库Controller
 *
 * @author popo
 * @date 2019-10-20
 */
@Controller
@RequestMapping("/smt/entry")
public class SmtOrderEntryController extends BaseController {
    private String prefix = "smt/entry";

    @Autowired
    private ISmtOrderEntryService smtOrderEntryService;

    @Autowired
    private ISmtDzlService smtDzlService;

    @Autowired
    private ISmtBomService smtBomService;

    @Autowired
    private ISmtOrderEntryLineService smtOrderEntryLineService;

    @RequiresPermissions("smt:entry:view")
    @GetMapping()
    public String entry() {
        return prefix + "/entry";
    }


    /**
     * 查询所有的订单入库信息(主明细)---页面跳转
     *
     * @param
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return prefix + "/list";
    }

    /**
     * 查询订单入库列表
     */
    @RequiresPermissions("smt:entry:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtOrderEntry smtOrderEntry) {
        startPage();
        List<SmtOrderEntry> list = smtOrderEntryService.selectSmtOrderEntryList(smtOrderEntry);
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        list.stream().forEach(bom -> bom.setCusName(cusNameMap.get(bom.getCusCode())));
        return getDataTable(list);
    }

    /**
     * 查询订单入库全部信息列表
     */
    @RequiresPermissions("smt:entry:list")
    @PostMapping("/getAllList")
    @ResponseBody
    public TableDataInfo getAllList(SmtOrderEntry orderEntry) {
        startPage();
        //List<SmtOrderEntryVO> entryVOList = smtOrderEntryService.selectSmtEntryAllList(orderEntry);
        List<SmtOrderEntryVO> entryVOList = smtOrderEntryService.selectSmtEntryAllTableList(orderEntry);
//        Map<Integer, String> dzlNameMap = getDzlIdAndDzlTypeNameMap();
//        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
//        entryVOList.stream().forEach(bom -> {
//                    bom.setCusName(cusNameMap.get(bom.getCusCode()));
//                    if (1 == bom.getOrderType().intValue()) {
//                        bom.setTypeNo(smtBomService.selectSmtBomById(Long.valueOf(bom.getBomId())).getBomName());
//                        bom.setTypeName(Constants.FPC_TYPE_NAME);
//                    } else {
//                        bom.setTypeNo(smtDzlService.selectSmtDzlById(Long.valueOf(bom.getBomId())).getDzlName());
//                        bom.setTypeName(dzlNameMap.get(bom.getBomId()));
//                    }
//                }
//        );
        return getDataTable(entryVOList);
    }

    /**
     * 导出订单入库列表
     */
    @RequiresPermissions("smt:entry:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtOrderEntry smtOrderEntry) {
        List<SmtOrderEntry> list = smtOrderEntryService.selectSmtOrderEntryList(smtOrderEntry);
        ExcelUtil<SmtOrderEntry> util = new ExcelUtil<SmtOrderEntry>(SmtOrderEntry.class);
        return util.exportExcel(list, "entry");
    }

    /**
     * 新增订单入库
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存订单入库
     */
    @RequiresPermissions("smt:entry:add")
    @Log(title = "订单入库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtOrderEntry smtOrderEntry) {
        return toAjax(smtOrderEntryService.insertSmtOrderEntryAndLine(smtOrderEntry));
    }

    /**
     * 修改订单入库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtOrderEntry smtOrderEntry = smtOrderEntryService.selectSmtOrderEntryById(id);
        mmap.put("smtOrderEntry", smtOrderEntry);
        return prefix + "/edit";
    }

    /**
     * 查看订单入库
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtOrderEntry smtOrderEntry = smtOrderEntryService.selectSmtOrderEntryById(id);
        mmap.put("smtOrderEntry", smtOrderEntry);
        return prefix + "/view";
    }

    /**
     * 修改保存订单入库
     */
    @RequiresPermissions("smt:entry:edit")
    @Log(title = "订单入库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtOrderEntry smtOrderEntry) {
        return toAjax(smtOrderEntryService.updateSmtBomAndBomLine(smtOrderEntry));
    }

    /**
     * 删除订单入库
     */
    @RequiresPermissions("smt:entry:remove")
    @Log(title = "订单入库", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smtOrderEntryService.deleteSmtOrderEntryByIds(ids));
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
     * 通过bomId查询bom明细信息
     *
     * @param smtOrderEntryLine
     * @return
     */
    @ResponseBody
    @RequestMapping("/getEntryLineById")
    public List<SmtOrderEntryLine> getEntryLineById(SmtOrderEntryLine smtOrderEntryLine) {
        List<SmtOrderEntryLine> orderEntryLineList = smtOrderEntryLineService.selectSmtOrderEntryLineList(smtOrderEntryLine);
        return orderEntryLineList;
    }


    /**
     * 通过bomId查询bom明细信息(通过订单编号查询)
     *
     * @param smtOrderEntryLine
     * @return
     */
    @ResponseBody
    @RequestMapping("/getViewEntryLineById")
    public List<SmtOrderEntryLine> getViewEntryLineById(SmtOrderEntryLine smtOrderEntryLine) {
        List<SmtOrderEntryLine> orderEntryLineList = smtOrderEntryLineService.selectSmtOrderEntryLineList(smtOrderEntryLine);
        for (SmtOrderEntryLine line : orderEntryLineList) {
            if (line.getOrderType().intValue() == Constants.BOM_TYPE_FPC.intValue()) {
                line.setBomName(getBomIdAndBomNameMap().get(line.getBomId()));
            }else{
                line.setBomName(getDzlIdAndDzlNameMap().get(line.getBomId()));
            }
        }
        return orderEntryLineList;
    }

    /**
     * 通过客户编码查询客户bom信息
     *
     * @param cusCode 客户编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCusBomByCode/{cusCode}")
    public List<SmtBom> getCusBomByCode(@PathVariable("cusCode") Integer cusCode) {
        SmtBom smtBom = new SmtBom();
        smtBom.setCusCode(cusCode);
        return smtBomService.selectSmtBomList(smtBom);
    }

}
