package com.ruoyi.project.smt.cus.controller;

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
import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.cus.service.ISmtCusService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 客户信息Controller
 * 
 * @author popo
 * @date 2019-10-19
 */
@Controller
@RequestMapping("/smt/cus")
public class SmtCusController extends BaseController
{
    private String prefix = "smt/cus";

    @Autowired
    private ISmtCusService smtCusService;

    @RequiresPermissions("smt:cus:view")
    @GetMapping()
    public String cus()
    {
        return prefix + "/cus";
    }

    /**
     * 查询客户信息列表
     */
    @RequiresPermissions("smt:cus:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtCus smtCus)
    {
        startPage();
        List<SmtCus> list = smtCusService.selectSmtCusList(smtCus);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @RequiresPermissions("smt:cus:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtCus smtCus)
    {
        List<SmtCus> list = smtCusService.selectSmtCusList(smtCus);
        ExcelUtil<SmtCus> util = new ExcelUtil<SmtCus>(SmtCus.class);
        return util.exportExcel(list, "cus");
    }

    /**
     * 新增客户信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户信息
     */
    @RequiresPermissions("smt:cus:add")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtCus smtCus)
    {
        smtCus.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(smtCusService.insertSmtCus(smtCus));
    }

    /**
     * 修改客户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SmtCus smtCus = smtCusService.selectSmtCusById(id);
        mmap.put("smtCus", smtCus);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户信息
     */
    @RequiresPermissions("smt:cus:edit")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtCus smtCus)
    {
        smtCus.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(smtCusService.updateSmtCus(smtCus));
    }

    /**
     * 删除客户信息
     */
    @RequiresPermissions("smt:cus:remove")
    @Log(title = "客户信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(smtCusService.deleteSmtCusByIds(ids));
    }

    /**
     * 验证客户代码唯一性
     */
    @PostMapping( "/validateUnique")
    @ResponseBody
    public Integer validateUnique(String code)
    {
        return smtCusService.validateUniqueByCode(code);
    }
}
