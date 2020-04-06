package com.ruoyi.project.smt.paymentrecord.controller;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.project.smt.paymentrecord.domain.SmtPaymentRecord;
import com.ruoyi.project.smt.paymentrecord.service.ISmtPaymentRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 付款记录Controller
 *
 * @author popo
 * @date 2020-04-05
 */
@Controller
@RequestMapping("/smt/paymentRecord")
public class SmtPaymentRecordController extends BaseController {
    private String prefix = "smt/paymentrecord";

    @Autowired
    private ISmtPaymentRecordService smtPaymentRecordService;

    @RequiresPermissions("smt:paymentRecord:view")
    @GetMapping()
    public String record() {
        return prefix + "/record";
    }

    /**
     * 查询付款记录列表
     */
    @RequiresPermissions("smt:paymentRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtPaymentRecord smtPaymentRecord) {
        startPage();
        List<SmtPaymentRecord> list = smtPaymentRecordService.selectSmtPaymentRecordList(smtPaymentRecord);
        return getDataTable(list);
    }

    /**
     * 导出付款记录列表
     */
    @RequiresPermissions("smt:paymentRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtPaymentRecord smtPaymentRecord) {
        List<SmtPaymentRecord> list = smtPaymentRecordService.selectSmtPaymentRecordList(smtPaymentRecord);
        ExcelUtil<SmtPaymentRecord> util = new ExcelUtil<SmtPaymentRecord>(SmtPaymentRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增付款记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存付款记录
     */
    @RequiresPermissions("smt:paymentRecord:add")
    @Log(title = "付款记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtPaymentRecord smtPaymentRecord) {
        smtPaymentRecord.setCreateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(smtPaymentRecordService.insertSmtPaymentRecord(smtPaymentRecord));
    }

    /**
     * 修改付款记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtPaymentRecord smtPaymentRecord = smtPaymentRecordService.selectSmtPaymentRecordById(id);
        mmap.put("smtPaymentRecord", smtPaymentRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存付款记录
     */
    @RequiresPermissions("smt:paymentRecord:edit")
    @Log(title = "付款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtPaymentRecord smtPaymentRecord) {
        return toAjax(smtPaymentRecordService.updateSmtPaymentRecord(smtPaymentRecord));
    }

    /**
     * 删除付款记录
     */
    @RequiresPermissions("smt:paymentRecord:remove")
    @Log(title = "付款记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smtPaymentRecordService.deleteSmtPaymentRecordByIds(ids));
    }
}
