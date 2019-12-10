package com.ruoyi.project.smt.bom.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.service.ISmtBomService;
import com.ruoyi.project.smt.bom.vo.SmtBomVO;
import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import com.ruoyi.project.smt.bomLine.service.ISmtBomLineService;
import com.ruoyi.project.smt.cus.service.ISmtCusService;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 客户bom信息Controller
 *
 * @author popo
 * @date 2019-10-19
 */
@Controller
@RequestMapping("/smt/bom")
public class SmtBomController extends BaseController {
    private String prefix = "smt/bom";

    @Autowired
    private ISmtBomService smtBomService;

    @Autowired
    private ISmtBomLineService iSmtBomLineService;

    @Autowired
    private ISmtCusService iSmtCusService;

    @Autowired
    private ISmtDzlService smtDzlService;

    @RequiresPermissions("smt:bom:view")
    @GetMapping()
    public String bom() {
        return prefix + "/bom";
    }

    /**
     * 查询所有的bom信息(主明细)---页面跳转
     *
     * @param
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return prefix + "/list";
    }

    /**
     * 查询客户bom信息列表
     */
    @RequiresPermissions("smt:bom:list")
    @PostMapping("/getAllList")
    @ResponseBody
    public TableDataInfo getAllList(SmtBom smtBom) {
        startPage();
        List<SmtBomVO> list = smtBomService.selectSmtBomAllList(smtBom);
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        list.stream().forEach(bom -> bom.setCusName(cusNameMap.get(bom.getCusCode())));
        return getDataTable(list);
    }

    /**
     * 查询客户bom信息列表
     */
    @RequiresPermissions("smt:bom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtBom smtBom) {
        startPage();
        List<SmtBom> list = smtBomService.selectSmtBomList(smtBom);
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        list.stream().forEach(bom -> bom.setCusName(cusNameMap.get(bom.getCusCode())));
        return getDataTable(list);
    }

    /**
     * 导出客户bom信息列表
     */
    @RequiresPermissions("smt:bom:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtBom smtBom) {
        List<SmtBomVO> list = smtBomService.selectSmtBomAllList(smtBom);
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        list.stream().forEach(bom -> bom.setCusName(cusNameMap.get(bom.getCusCode())));
        ExcelUtil<SmtBomVO> util = new ExcelUtil<SmtBomVO>(SmtBomVO.class);
        return util.exportExcel(list, "bom");
    }

    /**
     * 新增客户bom信息
     */
    @GetMapping("/add")
    public String add(Model model) {
        return prefix + "/add";
    }

    /**
     * 新增保存客户bom信息
     */
    @RequiresPermissions("smt:bom:add")
    @Log(title = "客户bom信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtBom smtBom) {
        return toAjax(smtBomService.insertSmtBomAndLine(smtBom));
    }

    /**
     * 修改客户bom信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SmtBom smtBom = smtBomService.selectSmtBomById(id);
        mmap.put("smtBom", smtBom);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户bom信息
     */
    @RequiresPermissions("smt:bom:edit")
    @Log(title = "客户bom信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtBom smtBom) {
        return toAjax(smtBomService.updateSmtBomAndBomLine(smtBom));
    }

    /**
     * 删除客户bom信息
     */
    @RequiresPermissions("smt:bom:remove")
    @Log(title = "客户bom信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smtBomService.deleteSmtBomByIds(ids));
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
     * @param smtBomLine
     * @return
     */
    @ResponseBody
    @RequestMapping("/getBomLineById")
    public List<SmtBomLine> getBomLineById(SmtBomLine smtBomLine) {
        List<SmtBomLine> smtBomLineList = iSmtBomLineService.selectSmtBomLineList(smtBomLine);
        Map<Integer, String> dzlNameMap = getDzlIdAndDzlTypeNameMap();
        smtBomLineList.stream().forEach(line -> line.setTypeName(dzlNameMap.get(line.getDzlId())));
        return smtBomLineList;
    }

    /**
     * 父子表格通过bomId查询bom明细信息
     *
     * @param smtBomLine
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDetailList")
    public TableDataInfo getDetailList(SmtBomLine smtBomLine) {
        TableDataInfo rspData = new TableDataInfo();
        List<SmtBomLine> smtBomLineList = iSmtBomLineService.selectSmtBomLineList(smtBomLine);
        List<SmtDzl> smtDzlList = smtDzlService.selectSmtDzlList(new SmtDzl());
        for (SmtBomLine bomLine : smtBomLineList) {
            for (SmtDzl dzl : smtDzlList) {
                if (bomLine.getDzlId().intValue() == dzl.getId().intValue()) {
                    bomLine.setDzlName(dzl.getDzlName());
                    bomLine.setTypeName(dzl.getTypeName());
                    break;
                }
            }
        }
        rspData.setRows(smtBomLineList);
        rspData.setTotal(smtBomLineList.size());
        return rspData;
    }

    @Log(title = "客户bom", businessType = BusinessType.IMPORT)
    @RequiresPermissions("smt:bom:import")
    @PostMapping("/import")
    @ResponseBody
    public SmtBom importData(MultipartFile file) throws Exception {
        SmtBom bom = smtBomService.importExcel(file.getInputStream());
        return bom;
    }

    /**
     * 验证客户bom唯一性
     *
     * @param bom
     * @return
     */
    @RequestMapping("/validateCusBomUnique")
    @ResponseBody
    public int validateCusBomUnique(SmtBom bom) {
        return smtBomService.validateCusBomUnique(bom);
    }
}
