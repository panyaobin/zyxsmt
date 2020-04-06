package com.ruoyi.project.smt.paymentapply.controller;

import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.paymentinfo.domain.SmtPaymentInfo;
import com.ruoyi.project.smt.paymentinfo.service.ISmtPaymentInfoService;
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
import com.ruoyi.project.smt.paymentapply.domain.SmtPaymentApply;
import com.ruoyi.project.smt.paymentapply.service.ISmtPaymentApplyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 付款申请Controller
 *
 * @author popo
 * @date 2020-04-06
 */
@Controller
@RequestMapping("/smt/paymentApply")
public class SmtPaymentApplyController extends BaseController {
    private String prefix = "smt/paymentApply";

    @Autowired
    private ISmtPaymentApplyService smtPaymentApplyService;

    @Autowired
    private ISmtPaymentInfoService smtPaymentInfoService;

    @RequiresPermissions("smt:paymentApply:view")
    @GetMapping()
    public String paymentApply() {
        return prefix + "/paymentApply";
    }

    /**
     * 查询付款申请列表
     */
    @RequiresPermissions("smt:paymentApply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtPaymentApply smtPaymentApply) {
        startPage();
        List<SmtPaymentApply> list = smtPaymentApplyService.selectSmtPaymentApplyTableList(smtPaymentApply);
        return getDataTable(list);
    }

    /**
     * 导出付款申请列表
     */
    @RequiresPermissions("smt:paymentApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtPaymentApply smtPaymentApply) {
        List<SmtPaymentApply> list = smtPaymentApplyService.selectSmtPaymentApplyList(smtPaymentApply);
        ExcelUtil<SmtPaymentApply> util = new ExcelUtil<SmtPaymentApply>(SmtPaymentApply.class);
        return util.exportExcel(list, "paymentApply");
    }

    /**
     * 新增付款申请
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存付款申请
     */
    @RequiresPermissions("smt:paymentApply:add")
    @Log(title = "付款申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtPaymentApply smtPaymentApply) {
        smtPaymentApply.setCreateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(smtPaymentApplyService.insertSmtPaymentApply(smtPaymentApply));
    }

    /**
     * 修改付款申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtPaymentApply apply = new SmtPaymentApply();
        apply.setId(id);
        SmtPaymentApply paymentApply = smtPaymentApplyService.selectSmtPaymentApplyTableList(apply).get(0);
        mmap.put("smtPaymentApply", paymentApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存付款申请
     */
    @RequiresPermissions("smt:paymentApply:edit")
    @Log(title = "付款申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtPaymentApply smtPaymentApply) {
        return toAjax(smtPaymentApplyService.updateSmtPaymentApply(smtPaymentApply));
    }

    /**
     * 删除付款申请
     */
    @RequiresPermissions("smt:paymentApply:remove")
    @Log(title = "付款申请", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smtPaymentApplyService.deleteSmtPaymentApplyByIds(ids));
    }


    /**
     * 根据ID查询付款信息
     * @param id
     * @return
     */
    @RequestMapping("/getPaymentInfoById")
    @ResponseBody
    public SmtPaymentInfo getPaymentInfoById(Integer id) {
        SmtPaymentInfo info = smtPaymentInfoService.selectSmtPaymentInfoById(id);
        return info;
    }


    /**
     * 状态修改
     */
    @Log(title = "付款申请状态管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("smt:paymentApply:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SmtPaymentApply apply)
    {
       return toAjax(smtPaymentApplyService.changeStatus(apply));
    }
}
