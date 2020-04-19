package com.ruoyi.project.smt.paymentinfo.controller;

import java.util.List;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.smt.cus.domain.SmtCus;
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
import com.ruoyi.project.smt.paymentinfo.domain.SmtPaymentInfo;
import com.ruoyi.project.smt.paymentinfo.service.ISmtPaymentInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 付款信息Controller
 *
 * @author popo
 * @date 2020-04-06
 */
@Controller
@RequestMapping("/smt/paymentInfo")
public class SmtPaymentInfoController extends BaseController {
    private String prefix = "smt/paymentinfo";

    @Autowired
    private ISmtPaymentInfoService smtPaymentInfoService;

    @RequiresPermissions("smt:paymentInfo:view")
    @GetMapping()
    public String paymentInfo() {
        return prefix + "/paymentInfo";
    }

    /**
     * 查询付款信息列表
     */
    @RequiresPermissions("smt:paymentInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtPaymentInfo smtPaymentInfo) {
        startPage();
        List<SmtPaymentInfo> list = smtPaymentInfoService.selectSmtPaymentInfoList(smtPaymentInfo);
        return getDataTable(list);
    }

    /**
     * 导出付款信息列表
     */
    @RequiresPermissions("smt:paymentInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtPaymentInfo smtPaymentInfo) {
        List<SmtPaymentInfo> list = smtPaymentInfoService.selectSmtPaymentInfoList(smtPaymentInfo);
        ExcelUtil<SmtPaymentInfo> util = new ExcelUtil<SmtPaymentInfo>(SmtPaymentInfo.class);
        return util.exportExcel(list, "paymentInfo");
    }

    /**
     * 新增付款信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存付款信息
     */
    @RequiresPermissions("smt:paymentInfo:add")
    @Log(title = "付款信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtPaymentInfo smtPaymentInfo) {
        smtPaymentInfo.setCreateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(smtPaymentInfoService.insertSmtPaymentInfo(smtPaymentInfo));
    }

    /**
     * 修改付款信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtPaymentInfo smtPaymentInfo = smtPaymentInfoService.selectSmtPaymentInfoById(id);
        mmap.put("smtPaymentInfo", smtPaymentInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存付款信息
     */
    @RequiresPermissions("smt:paymentInfo:edit")
    @Log(title = "付款信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtPaymentInfo smtPaymentInfo) {
        smtPaymentInfo.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(smtPaymentInfoService.updateSmtPaymentInfo(smtPaymentInfo));
    }

    /**
     * 删除付款信息
     */
    @RequiresPermissions("smt:paymentInfo:remove")
    @Log(title = "付款信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smtPaymentInfoService.deleteSmtPaymentInfoByIds(ids));
    }

    /**
     * 状态修改
     */
    @Log(title = "付款信息状态管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("smt:paymentInfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SmtPaymentInfo paymentInfo) {
        return toAjax(smtPaymentInfoService.changeStatus(paymentInfo));
    }
}
