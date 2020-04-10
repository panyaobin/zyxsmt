package com.ruoyi.project.smt.reconciliationfile.controller;

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
import com.ruoyi.project.smt.reconciliationfile.domain.SmtReconciliationFile;
import com.ruoyi.project.smt.reconciliationfile.service.ISmtReconciliationFileService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 对账单附件Controller
 * 
 * @author popo
 * @date 2020-04-08
 */
@Controller
@RequestMapping("/smt/reconciliationFile")
public class SmtReconciliationFileController extends BaseController
{
    private String prefix = "smt/reconciliationfile";

    @Autowired
    private ISmtReconciliationFileService smtReconciliationFileService;

    @RequiresPermissions("smt:reconciliationFile:view")
    @GetMapping()
    public String reconciliationFile()
    {
        return prefix + "/reconciliationFile";
    }

    /**
     * 查询对账单附件列表
     */
    @RequiresPermissions("smt:reconciliationFile:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtReconciliationFile smtReconciliationFile)
    {
        startPage();
        List<SmtReconciliationFile> list = smtReconciliationFileService.selectSmtReconciliationFileList(smtReconciliationFile);
        return getDataTable(list);
    }

    /**
     * 导出对账单附件列表
     */
    @RequiresPermissions("smt:reconciliationFile:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtReconciliationFile smtReconciliationFile)
    {
        List<SmtReconciliationFile> list = smtReconciliationFileService.selectSmtReconciliationFileList(smtReconciliationFile);
        ExcelUtil<SmtReconciliationFile> util = new ExcelUtil<SmtReconciliationFile>(SmtReconciliationFile.class);
        return util.exportExcel(list, "reconciliationFile");
    }

    /**
     * 新增对账单附件
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存对账单附件
     */
    @RequiresPermissions("smt:reconciliationFile:add")
    @Log(title = "对账单附件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtReconciliationFile smtReconciliationFile)
    {
        return toAjax(smtReconciliationFileService.insertSmtReconciliationFile(smtReconciliationFile));
    }

    /**
     * 修改对账单附件
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SmtReconciliationFile smtReconciliationFile = smtReconciliationFileService.selectSmtReconciliationFileById(id);
        mmap.put("smtReconciliationFile", smtReconciliationFile);
        return prefix + "/edit";
    }

    /**
     * 修改保存对账单附件
     */
    @RequiresPermissions("smt:reconciliationFile:edit")
    @Log(title = "对账单附件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtReconciliationFile smtReconciliationFile)
    {
        return toAjax(smtReconciliationFileService.updateSmtReconciliationFile(smtReconciliationFile));
    }

    /**
     * 删除对账单附件
     */
    @RequiresPermissions("smt:reconciliationFile:remove")
    @Log(title = "对账单附件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(smtReconciliationFileService.deleteSmtReconciliationFileByIds(ids));
    }
}
