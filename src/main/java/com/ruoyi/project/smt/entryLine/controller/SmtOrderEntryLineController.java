package com.ruoyi.project.smt.entryLine.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.smt.entryLine.domain.SmtOrderEntryLine;
import com.ruoyi.project.smt.entryLine.service.ISmtOrderEntryLineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 订单入库明细Controller
 * 
 * @author popo
 * @date 2019-11-02
 */
@Controller
@RequestMapping("/smt/entryLine")
public class SmtOrderEntryLineController extends BaseController
{
    private String prefix = "smt/entryLine";

    @Autowired
    private ISmtOrderEntryLineService smtOrderEntryLineService;

    @RequiresPermissions("smt:entryLine:view")
    @GetMapping()
    public String entryLine()
    {
        return prefix + "/entryLine";
    }

    /**
     * 查询订单入库明细列表
     */
    @RequiresPermissions("smt:entryLine:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtOrderEntryLine smtOrderEntryLine)
    {
        startPage();
        List<SmtOrderEntryLine> list = smtOrderEntryLineService.selectSmtOrderEntryLineList(smtOrderEntryLine);
        return getDataTable(list);
    }

    /**
     * 导出订单入库明细列表
     */
    @RequiresPermissions("smt:entryLine:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtOrderEntryLine smtOrderEntryLine)
    {
        List<SmtOrderEntryLine> list = smtOrderEntryLineService.selectSmtOrderEntryLineList(smtOrderEntryLine);
        ExcelUtil<SmtOrderEntryLine> util = new ExcelUtil<SmtOrderEntryLine>(SmtOrderEntryLine.class);
        return util.exportExcel(list, "entryLine");
    }

    /**
     * 新增订单入库明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单入库明细
     */
    @RequiresPermissions("smt:entryLine:add")
    @Log(title = "订单入库明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtOrderEntryLine smtOrderEntryLine)
    {
        return toAjax(smtOrderEntryLineService.insertSmtOrderEntryLine(smtOrderEntryLine));
    }

    /**
     * 修改订单入库明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SmtOrderEntryLine smtOrderEntryLine = smtOrderEntryLineService.selectSmtOrderEntryLineById(id);
        mmap.put("smtOrderEntryLine", smtOrderEntryLine);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单入库明细
     */
    @RequiresPermissions("smt:entryLine:edit")
    @Log(title = "订单入库明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtOrderEntryLine smtOrderEntryLine)
    {
        return toAjax(smtOrderEntryLineService.updateSmtOrderEntryLine(smtOrderEntryLine));
    }

    /**
     * 删除订单入库明细
     */
    @RequiresPermissions("smt:entryLine:remove")
    @Log(title = "订单入库明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(smtOrderEntryLineService.deleteSmtOrderEntryLineByIds(ids));
    }
}
