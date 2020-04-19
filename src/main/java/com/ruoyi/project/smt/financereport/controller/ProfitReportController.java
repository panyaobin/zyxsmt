package com.ruoyi.project.smt.financereport.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 现金日记账controller
 * @Author Administrator
 * @Date 2020/4/19 11:09
 * @Version 1.0
 **/
@Controller
@RequestMapping("/smt/profitReport")
public class ProfitReportController extends BaseController {
    private String prefix = "smt/financereport";



    @RequiresPermissions("smt:profitReport:view")
    @GetMapping()
    public String applyRecord()
    {
        return prefix + "/profitReport";
    }


    /**
     * 查询利润表数据
     */
    @RequiresPermissions("smt:profitReport:list")
    @PostMapping("/getAllList")
    @ResponseBody
    public TableDataInfo getAllList(SmtBom smtBom) {
        List<SmtBom> a = new ArrayList<>();
        return getDataTable(a);
    }
}
