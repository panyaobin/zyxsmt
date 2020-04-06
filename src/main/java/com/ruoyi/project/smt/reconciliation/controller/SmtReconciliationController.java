package com.ruoyi.project.smt.reconciliation.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.smt.paymentrecord.domain.SmtPaymentRecord;
import com.ruoyi.project.smt.paymentrecord.service.ISmtPaymentRecordService;
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
import com.ruoyi.project.smt.reconciliation.domain.SmtReconciliation;
import com.ruoyi.project.smt.reconciliation.service.ISmtReconciliationService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 对账管理Controller
 *
 * @author popo
 * @date 2020-04-05
 */
@Controller
@RequestMapping("/smt/reconciliation")
public class SmtReconciliationController extends BaseController {
    private String prefix = "smt/reconciliation";

    @Autowired
    private ISmtReconciliationService smtReconciliationService;

    @Autowired
    private ISmtPaymentRecordService smtPaymentRecordService;

    @RequiresPermissions("smt:reconciliation:view")
    @GetMapping()
    public String reconciliation() {
        return prefix + "/reconciliation";
    }

    /**
     * 查询对账管理列表
     */
    @RequiresPermissions("smt:reconciliation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtReconciliation smtReconciliation) {
        startPage();
        List<SmtReconciliation> list = smtReconciliationService.selectSmtReconciliationList(smtReconciliation);
        return getDataTable(list);
    }

    /**
     * 导出对账管理列表
     */
    @RequiresPermissions("smt:reconciliation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtReconciliation smtReconciliation) {
        List<SmtReconciliation> list = smtReconciliationService.selectSmtReconciliationList(smtReconciliation);
        ExcelUtil<SmtReconciliation> util = new ExcelUtil<SmtReconciliation>(SmtReconciliation.class);
        return util.exportExcel(list, "reconciliation");
    }

    /**
     * 新增对账管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存对账管理
     */
    @RequiresPermissions("smt:reconciliation:add")
    @Log(title = "对账管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtReconciliation smtReconciliation) {
        smtReconciliation.setCreateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(smtReconciliationService.insertSmtReconciliation(smtReconciliation));
    }

    /**
     * 修改对账管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtReconciliation smtReconciliation = smtReconciliationService.selectSmtReconciliationById(id);
        mmap.put("smtReconciliation", smtReconciliation);
        return prefix + "/edit";
    }

    /**
     * 审核对账管理
     */
    @GetMapping("/audit/{id}")
    public String audit(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtReconciliation smtReconciliation = smtReconciliationService.selectSmtReconciliationById(id);
        mmap.put("smtReconciliation", smtReconciliation);
        return prefix + "/audit";
    }

    /**
     * 修改保存对账管理
     */
    @RequiresPermissions("smt:reconciliation:edit")
    @Log(title = "对账管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtReconciliation smtReconciliation) {
        if (null == smtReconciliation.getConfirmAmount() || smtReconciliation.getConfirmAmount().intValue() < 0) {
            smtReconciliation.setConfirmAmount(new BigDecimal(0));
        }
        if (null == smtReconciliation.getDeductionAmount() || smtReconciliation.getDeductionAmount().intValue() < 0) {
            smtReconciliation.setDeductionAmount(new BigDecimal(0));
        }
        return toAjax(smtReconciliationService.updateSmtReconciliation(smtReconciliation));
    }

    /**
     * 删除对账管理
     */
    @RequiresPermissions("smt:reconciliation:remove")
    @Log(title = "对账管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smtReconciliationService.deleteSmtReconciliationByIds(ids));
    }




    /*------------------------------------------------------客户付款模块----------------------------------------------------------------*/


    @GetMapping("/paymentList")
    public String paymentList() {
        return prefix + "/paymentList";
    }

    /**
     * 新增客户付款信息
     */
    @GetMapping("/addPayment")
    public String addPayment() {
        return prefix + "/addPayment";
    }

    /**
     * 修改对账管理
     */
    @GetMapping("/editPayment/{id}")
    public String editPayment(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtReconciliation smtReconciliation = smtReconciliationService.selectSmtReconciliationById(id);
        mmap.put("smtReconciliation", smtReconciliation);
        return prefix + "/editPayment";
    }

    /**
     * 查询客户待付款列表
     */
    @RequiresPermissions("smt:reconciliation:list")
    @PostMapping("/paymentList")
    @ResponseBody
    public TableDataInfo paymentList(SmtReconciliation smtReconciliation) {
        startPage();
        smtReconciliation.setStatus("3");
        List<SmtReconciliation> list = smtReconciliationService.selectSmtReconciliationList(smtReconciliation);
        for (SmtReconciliation record : list) {
            BigDecimal decimal = smtPaymentRecordService.selectSumPaymentAmount(record.getReconciliationNo());
            record.setPaidAmount(null == decimal ? new BigDecimal(0) : decimal);
            record.setArrears(record.getConfirmAmount().subtract(null == decimal ? new BigDecimal(0) : decimal));
        }
        return getDataTable(list);
    }

    /**
     * 查询客户待付款列表
     */
    @RequiresPermissions("smt:reconciliation:edit")
    @GetMapping("/cleanPayment/{id}")
    @ResponseBody
    public AjaxResult cleanPayment(@PathVariable("id") Integer id) {
        SmtReconciliation smtReconciliation = smtReconciliationService.selectSmtReconciliationById(id);
        smtReconciliation.setIsSettle("1");
        return toAjax(smtReconciliationService.updateSmtReconciliation(smtReconciliation));
    }
}
