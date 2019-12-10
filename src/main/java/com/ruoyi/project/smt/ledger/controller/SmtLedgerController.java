package com.ruoyi.project.smt.ledger.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import com.ruoyi.project.smt.entry.domain.SmtOrderEntry;
import com.ruoyi.project.smt.entry.service.ISmtOrderEntryService;
import com.ruoyi.project.smt.entry.vo.SmtOrderEntryVO;
import com.ruoyi.project.smt.ledger.util.LedgerExportUtil;
import com.ruoyi.project.smt.ledger.vo.SmtLedgerExportVO;
import com.ruoyi.project.smt.ship.domain.SmtProductShip;
import com.ruoyi.project.smt.ship.service.ISmtProductShipService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/12/7 19:24
 * @Version 1.0
 **/
@Slf4j
@Controller
@RequestMapping("/smt/ledger")
public class SmtLedgerController extends BaseController {

    private String prefix = "smt/ledger";

    @Autowired
    private ISmtDzlService smtDzlService;

    @Autowired
    protected LedgerExportUtil exportUtil;

    @Autowired
    private ISmtOrderEntryService smtOrderEntryService;

    @Autowired
    private ISmtProductShipService smtProductShipService;

    @RequiresPermissions("smt:ledger:view")
    @GetMapping()
    public String ship() {
        return prefix + "/list";
    }


    /**
     * 查询电子料来料(入库)统计记录
     *
     * @param entry
     * @return
     */
    @RequiresPermissions("smt:ledger:list")
    @RequestMapping("/dzlEntryList")
    @ResponseBody
    public TableDataInfo dzlEntryList(SmtOrderEntry entry) {
        startPage();
        List<SmtOrderEntryVO> entryList = smtOrderEntryService.dzlEntryList(entry);
        Map<Integer, String> dzlNameMap = getDzlIdAndDzlNameMap();
        entryList.stream().forEach(en -> en.setDzlName(dzlNameMap.get(en.getBomId())));
        return getDataTable(entryList);
    }

    /**
     * 查询电子料出货统计记录
     *
     * @param ship
     * @return
     */
    @RequiresPermissions("smt:ledger:list")
    @RequestMapping("/dzlShipList")
    @ResponseBody
    public TableDataInfo dzlEntryList(SmtProductShip ship) {
        startPage();
        List<SmtProductShip> shipList = smtProductShipService.selectSmtProductShipExportList(ship);
        Map<Integer, String> dzlNameMap = getDzlIdAndDzlNameMap();
        shipList.stream().forEach(en -> en.setDzlName(dzlNameMap.get(en.getBomId())));
        return getDataTable(shipList);
    }


    /**
     * 条件导出物料台账
     */
    @RequiresPermissions("smt:ledger:export")
    @RequestMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtLedgerExportVO exportVO, HttpServletResponse response){
        try {
            return this.exportUtil.export(response, exportVO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SmtLedgerController->{export}物料台账导出excel失败");
        }
        return AjaxResult.error("物料台账导出失败!");
    }
}
