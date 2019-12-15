package com.ruoyi.project.smt.report.controller;

import com.ruoyi.framework.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Description 日历统计报表使用
 * @Author popo
 * @Date 2019/12/15 14:00
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/smt/report")
public class SmtReportController  extends BaseController {

    private String prefix = "smt/report";

    @RequiresPermissions("smt:report:view")
    @GetMapping()
    public String ship() {
        return prefix + "/list";
    }
}
