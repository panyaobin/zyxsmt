package com.ruoyi.project.smt.dzl.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 电子料信息Controller
 * 
 * @author popo
 * @date 2019-10-19
 */
@Controller
@RequestMapping("/smt/dzl")
public class SmtDzlController extends BaseController
{
    private String prefix = "smt/dzl";

    @Autowired
    private ISmtDzlService smtDzlService;

    @RequiresPermissions("smt:dzl:view")
    @GetMapping()
    public String dzl()
    {
        return prefix + "/dzl";
    }

    /**
     * 查询电子料信息列表
     */
    @RequiresPermissions("smt:dzl:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtDzl smtDzl)
    {
        startPage();
        List<SmtDzl> list = smtDzlService.selectSmtDzlList(smtDzl);
        return getDataTable(list);
    }

    /**
     * 导出电子料信息列表
     */
    @RequiresPermissions("smt:dzl:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtDzl smtDzl)
    {
        List<SmtDzl> list = smtDzlService.selectSmtDzlList(smtDzl);
        ExcelUtil<SmtDzl> util = new ExcelUtil<SmtDzl>(SmtDzl.class);
        return util.exportExcel(list, "dzl");
    }

    /**
     * 新增电子料信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存电子料信息
     */
    @RequiresPermissions("smt:dzl:add")
    @Log(title = "电子料信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtDzl smtDzl)
    {
        smtDzl.setCreateBy(ShiroUtils.getLoginName());
//        smtDzl.setTypeName(StringUtils.isEmpty(smtDzl.getTypeName())==true?"":smtDzl.getTypeName());
        return toAjax(smtDzlService.insertSmtDzl(smtDzl));
    }

    /**
     * 修改电子料信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SmtDzl smtDzl = smtDzlService.selectSmtDzlById(id);
        mmap.put("smtDzl", smtDzl);
        return prefix + "/edit";
    }

    /**
     * 修改保存电子料信息
     */
    @RequiresPermissions("smt:dzl:edit")
    @Log(title = "电子料信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtDzl smtDzl)
    {
        smtDzl.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(smtDzlService.updateSmtDzl(smtDzl));
    }

    /**
     * 删除电子料信息
     */
    @RequiresPermissions("smt:dzl:remove")
    @Log(title = "电子料信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(smtDzlService.deleteSmtDzlByIds(ids));
    }

    /**
     * 下载模版
     * @return
     */
    @RequiresPermissions("smt:dzl:export")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SmtDzl> util = new ExcelUtil<SmtDzl>(SmtDzl.class);
        return util.importTemplateExcel("物料信息");
    }

    @Log(title = "物料管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("smt:dzl:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SmtDzl> util = new ExcelUtil<SmtDzl>(SmtDzl.class);
        List<SmtDzl> dzlList = util.importExcel(file.getInputStream());
        String message = smtDzlService.importDzl(dzlList, updateSupport);
        return AjaxResult.success(message);
    }

    /**
     * 验证电子料名称唯一性
     * @param dzlName
     * @return
     */
    @RequestMapping("/validateDzlNameUnique")
    @ResponseBody
    public int validateDzlNameUnique(@RequestParam String dzlName){
        return smtDzlService.validateDzlNameUnique(dzlName);
    }
}
