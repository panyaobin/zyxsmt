package com.ruoyi.project.smt.bomLine.controller;

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
import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import com.ruoyi.project.smt.bomLine.service.ISmtBomLineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 客户bom明细Controller
 * 
 * @author popo
 * @date 2019-10-30
 */
@Controller
@RequestMapping("/smt/line")
public class SmtBomLineController extends BaseController
{
    private String prefix = "smt/line";

    @Autowired
    private ISmtBomLineService smtBomLineService;

    @RequiresPermissions("smt:line:view")
    @GetMapping()
    public String line()
    {
        return prefix + "/line";
    }

    /**
     * 查询客户bom明细列表
     */
    @RequiresPermissions("smt:line:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtBomLine smtBomLine)
    {
        startPage();
        List<SmtBomLine> list = smtBomLineService.selectSmtBomLineList(smtBomLine);
        return getDataTable(list);
    }

    /**
     * 导出客户bom明细列表
     */
    @RequiresPermissions("smt:line:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtBomLine smtBomLine)
    {
        List<SmtBomLine> list = smtBomLineService.selectSmtBomLineList(smtBomLine);
        ExcelUtil<SmtBomLine> util = new ExcelUtil<SmtBomLine>(SmtBomLine.class);
        return util.exportExcel(list, "line");
    }

    /**
     * 新增客户bom明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户bom明细
     */
    @RequiresPermissions("smt:line:add")
    @Log(title = "客户bom明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtBomLine smtBomLine)
    {
        return toAjax(smtBomLineService.insertSmtBomLine(smtBomLine));
    }

    /**
     * 修改客户bom明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SmtBomLine smtBomLine = smtBomLineService.selectSmtBomLineById(id);
        mmap.put("smtBomLine", smtBomLine);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户bom明细
     */
    @RequiresPermissions("smt:line:edit")
    @Log(title = "客户bom明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtBomLine smtBomLine)
    {
        return toAjax(smtBomLineService.updateSmtBomLine(smtBomLine));
    }

    /**
     * 删除客户bom明细
     */
    @RequiresPermissions("smt:line:remove")
    @Log(title = "客户bom明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(smtBomLineService.deleteSmtBomLineByIds(ids));
    }
}
