package com.ruoyi.project.smt.applyrecord.controller;

import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
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
import com.ruoyi.project.smt.applyrecord.domain.SmtApplyRecord;
import com.ruoyi.project.smt.applyrecord.service.ISmtApplyRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 应付账目付款记录Controller
 * 
 * @author popo
 * @date 2020-04-09
 */
@Controller
@RequestMapping("/smt/applyRecord")
public class SmtApplyRecordController extends BaseController
{
    private String prefix = "smt/applyrecord";

    @Autowired
    private ISmtApplyRecordService smtApplyRecordService;

    @RequiresPermissions("smt:applyRecord:view")
    @GetMapping()
    public String applyRecord()
    {
        return prefix + "/applyRecord";
    }

    /**
     * 查询应付账目付款记录列表
     */
    @RequiresPermissions("smt:applyRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtApplyRecord smtApplyRecord)
    {
        startPage();
        List<SmtApplyRecord> list = smtApplyRecordService.selectSmtApplyRecordList(smtApplyRecord);
        return getDataTable(list);
    }

    /**
     * 导出应付账目付款记录列表
     */
    @RequiresPermissions("smt:applyRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtApplyRecord smtApplyRecord)
    {
        List<SmtApplyRecord> list = smtApplyRecordService.selectSmtApplyRecordList(smtApplyRecord);
        ExcelUtil<SmtApplyRecord> util = new ExcelUtil<SmtApplyRecord>(SmtApplyRecord.class);
        return util.exportExcel(list, "applyRecord");
    }

    /**
     * 新增应付账目付款记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存应付账目付款记录
     */
    @RequiresPermissions("smt:applyRecord:add")
    @Log(title = "应付账目付款记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtApplyRecord smtApplyRecord)
    {
        smtApplyRecord.setCreateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(smtApplyRecordService.insertSmtApplyRecord(smtApplyRecord));
    }

    /**
     * 修改应付账目付款记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SmtApplyRecord smtApplyRecord = smtApplyRecordService.selectSmtApplyRecordById(id);
        mmap.put("smtApplyRecord", smtApplyRecord);
        return prefix + "/edit";
    }

    /**
     * 查看应付账目付款记录
     */
    @GetMapping("/viewList/{id}")
    public String viewList(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SmtApplyRecord smtApplyRecord = smtApplyRecordService.selectSmtApplyRecordById(id);
        mmap.put("smtApplyRecord", smtApplyRecord);
        return prefix + "/viewList";
    }

    /**
     * 修改保存应付账目付款记录
     */
    @RequiresPermissions("smt:applyRecord:edit")
    @Log(title = "应付账目付款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtApplyRecord smtApplyRecord)
    {
        return toAjax(smtApplyRecordService.updateSmtApplyRecord(smtApplyRecord));
    }

    /**
     * 删除应付账目付款记录
     */
    @RequiresPermissions("smt:applyRecord:remove")
    @Log(title = "应付账目付款记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(smtApplyRecordService.deleteSmtApplyRecordByIds(ids));
    }

}
