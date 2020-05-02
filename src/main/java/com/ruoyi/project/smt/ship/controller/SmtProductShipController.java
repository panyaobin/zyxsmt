package com.ruoyi.project.smt.ship.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.service.ISmtBomService;
import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import com.ruoyi.project.smt.bomLine.service.ISmtBomLineService;
import com.ruoyi.project.smt.delivery.domain.DeliveryPrintVO;
import com.ruoyi.project.smt.delivery.domain.SmtDeliveryRecord;
import com.ruoyi.project.smt.delivery.service.ISmtDeliveryRecordService;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import com.ruoyi.project.smt.ship.domain.ShipPrintVO;
import com.ruoyi.project.smt.ship.domain.SmtProductShip;
import com.ruoyi.project.smt.ship.service.ISmtProductShipService;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 产品出货Controller
 *
 * @author popo
 * @date 2019-11-27
 */
@Controller
@RequestMapping("/smt/ship")
public class SmtProductShipController extends BaseController {
    private String prefix = "smt/ship";

    @Autowired
    private ISmtBomService smtBomService;

    @Autowired
    private ISmtBomLineService smtBomLineService;

    @Autowired
    private ISmtDzlService smtDzlService;

    @Autowired
    private ISmtDeliveryRecordService smtDeliveryRecordService;


    @Autowired
    private ISmtProductShipService smtProductShipService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigService iConfigService;

    @Value("${jasper.path.ship}")
    private String jasperShipPath;

    @Value("${jasper.path.returns}")
    private String jasperReturnsPath;

    @Value("${jasper.path.entry}")
    private String jasperEntryPath;

    @RequiresPermissions("smt:ship:view")
    @GetMapping()
    public String ship() {
        return prefix + "/ship";
    }


    /**
     * FPC出货记录
     *
     * @return
     */
    @RequiresPermissions("smt:ship:view")
    @GetMapping("/fpcRecord")
    public String fpcRecord() {
        return prefix + "/fpcRecord";
    }

    /**
     * DZL出货记录
     *
     * @return
     */
    @RequiresPermissions("smt:ship:view")
    @GetMapping("/dzlRecord")
    public String dzlRecord() {
        return prefix + "/dzlRecord";
    }

    /**
     * 退货记录
     *
     * @return
     */
    @RequiresPermissions("smt:ship:view")
    @GetMapping("/returnsRecord")
    public String returnsRecord() {
        return prefix + "/returnsRecord";
    }

    /**
     * 跳转fpc在线页面
     *
     * @return
     */
    @RequiresPermissions("smt:ship:view")
    @GetMapping("/fpcOnLine")
    public String fpcOnLine() {
        return prefix + "/fpcOnLine";
    }


    /**
     * 跳转dzl在线页面
     *
     * @return
     */
    @RequiresPermissions("smt:ship:view")
    @GetMapping("/dzlOnLine")
    public String dzlOnLine() {
        return prefix + "/dzlOnLine";
    }


    /**
     * 查询FPC在线列表
     */
    @RequiresPermissions("smt:ship:online")
    @PostMapping("/fpcOnLineList")
    @ResponseBody
    public TableDataInfo fpcOnLineList(SmtDeliveryRecord record) {
        startPage();
        List<SmtDeliveryRecord> list = smtDeliveryRecordService.selectFpcOnLineListList(record);
//        if (StringUtils.isNotEmpty(list)){
//            startPage();
//            List<SmtDeliveryRecord> newList = list.stream().filter(deliveryRecord -> deliveryRecord.getSumDeliveryQty().intValue() != 0).collect(Collectors.toList());
//            return getDataTable(newList);
//        }
        return getDataTable(list);
    }

    /**
     * 查询DZL在线列表
     */
    @RequiresPermissions("smt:ship:online")
    @PostMapping("/dzlOnLineList")
    @ResponseBody
    public TableDataInfo dzlOnLineList(SmtDeliveryRecord record) {
        startPage();
        List<SmtDeliveryRecord> list = smtDeliveryRecordService.selectDzlOnLineListList(record);
//        if (StringUtils.isNotEmpty(list)) {
//            startPage();
//            List<SmtDeliveryRecord> newList = list.stream().filter(deliveryRecord -> deliveryRecord.getSumDeliveryQty().intValue() != 0).collect(Collectors.toList());
//            return getDataTable(newList);
//        }
        return getDataTable(list);
    }


    /**
     * FPC在线列表出货跳转
     *
     * @return
     */
    @GetMapping("/fpcShipHome")
    public String fpcShipHome() {
        return prefix + "/fpcShipHome";
    }

    /**
     * FPC在线列表退货跳转
     *
     * @return
     */
    @GetMapping("/fpcReturns")
    public String fpcReturns() {
        return prefix + "/fpcReturns";
    }

    /**
     * DZL在线列表退货跳转
     *
     * @return
     */
    @GetMapping("/dzlReturns")
    public String dzlReturns() {
        return prefix + "/dzlReturns";
    }


    /**
     * 查询产品出货列表
     */
    @RequiresPermissions("smt:ship:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmtProductShip smtProductShip) {
        startPage();
        List<SmtProductShip> list = smtProductShipService.selectSmtProductShipList(smtProductShip);
        return getDataTable(list);
    }

    /**
     * DZL出货查询产品出货列表
     */
    @RequiresPermissions("smt:ship:list")
    @PostMapping("/dzlShipList")
    @ResponseBody
    public TableDataInfo dzlShipList(SmtProductShip smtProductShip) {
        startPage();
        List<SmtProductShip> list = smtProductShipService.selectSmtProductShipExportList(smtProductShip);
        return getDataTable(list);
    }


    /**
     * 导出产品出货列表
     */
    @RequiresPermissions("smt:ship:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmtProductShip smtProductShip) {
        List<SmtProductShip> list = smtProductShipService.selectSmtProductShipExportList(smtProductShip);
        ExcelUtil<SmtProductShip> util = new ExcelUtil<SmtProductShip>(SmtProductShip.class);
        return util.exportExcel(list, "ship");
    }

    /**
     * 新增产品出货
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品出货
     */
    @RequiresPermissions("smt:ship:add")
    @Log(title = "产品出货", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmtProductShip smtProductShip) {
        return toAjax(smtProductShipService.batchInsertSmtProductShip(smtProductShip));
    }

    /**
     * 新增保存dzl退货
     */
    @RequiresPermissions("smt:ship:add")
    @Log(title = "dzl退货", businessType = BusinessType.INSERT)
    @PostMapping("/addDzl")
    @ResponseBody
    public AjaxResult addDzl(SmtProductShip smtProductShip) {
        return toAjax(smtProductShipService.batchInsertSmtProductShipDzl(smtProductShip));
    }

    /**
     * FPC计算电子料以后新增保存产品出货
     */
    @RequiresPermissions("smt:ship:add")
    @Log(title = "产品出货", businessType = BusinessType.INSERT)
    @PostMapping("/addShip")
    @ResponseBody
    public AjaxResult addShip(SmtProductShip smtProductShip) {
        return toAjax(smtProductShipService.batchAddSmtProductShip(smtProductShip));
    }

    /**
     * 修改产品出货
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SmtProductShip smtProductShip = smtProductShipService.selectSmtProductShipById(id);
        mmap.put("smtProductShip", smtProductShip);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品出货
     */
    @RequiresPermissions("smt:ship:edit")
    @Log(title = "产品出货", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmtProductShip smtProductShip) {
        return toAjax(smtProductShipService.updateSmtProductShip(smtProductShip));
    }

    /**
     * 删除产品出货
     */
    @RequiresPermissions("smt:ship:remove")
    @Log(title = "产品出货", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smtProductShipService.deleteSmtProductShipByIds(ids));
    }

    /**
     * 查询客户bom信息
     *
     * @param smtBom
     */
    @RequestMapping("/getCustBomInfo")
    @ResponseBody
    public List<SmtBomLine> getCustBomInfo(SmtBom smtBom) {
        List<SmtBom> bomList = smtBomService.selectSmtBomList(smtBom);
        //fpc出货数量
        Integer count = smtBom.getCount();
        int bomId = bomList.get(0).getId();
        SmtBomLine line = new SmtBomLine();
        line.setBomId(bomId);
        List<SmtBomLine> smtBomLines = smtBomLineService.selectSmtBomLineList(line);
        List<SmtDzl> smtDzlList = smtDzlService.selectSmtDzlList(new SmtDzl());
        for (SmtBomLine bomLine : smtBomLines) {
            for (SmtDzl dzl : smtDzlList) {
                if (bomLine.getDzlId().intValue() == dzl.getId().intValue()) {
                    bomLine.setDzlName(dzl.getDzlName());
                    bomLine.setTypeName(dzl.getTypeName());
                }
            }
            //客户bom备品数量
            Integer bak = bomLine.getBak();
            //客户bom电子料用量
            Integer dzlNumber = bomLine.getDzlNumber();
            int total = count * dzlNumber;
            int bakNum = total * bak;
            bomLine.setCounts(total);
            BigDecimal scale = new BigDecimal(bakNum).divide(new BigDecimal(1000)).setScale(0, RoundingMode.HALF_UP);
            bomLine.setBak(scale.intValue());
        }
        return smtBomLines;
    }

    /**
     * 出货打印出货单
     *
     * @param response
     */
    @RequestMapping("/print")
    public void printShip(HttpServletResponse response) {
        try {
            Cache<String, Object> cache = cacheManager.getCache("defaultCache");
            List<SmtProductShip> shipList = (List<SmtProductShip>) cache.get("shipPrintList");
            buildPrintData(response, shipList, 1);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退货打印
     *
     * @param response
     */
    @RequestMapping("/returnsPrint")
    public void fpcReturnsPrint(HttpServletResponse response) {
        Cache<String, Object> cache = cacheManager.getCache("defaultCache");
        List<SmtProductShip> shipList = (List<SmtProductShip>) cache.get("returnsPrint");
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        List<DeliveryPrintVO> printVOList = Lists.newArrayList();
        for (int i = 0; i < shipList.size(); i++) {
            SmtProductShip ship = shipList.get(i);
            DeliveryPrintVO printVO = new DeliveryPrintVO();
            printVO.setIndex(String.valueOf(i + 1));
            //如果是 FPC，则需要获取 bomName和 bomType
            if (Constants.BOM_TYPE_FPC.intValue() == ship.getOrderType().intValue()) {
                printVO.setBomName(smtBomService.selectSmtBomById(Long.valueOf(ship.getBomId())).getBomName());
                printVO.setBomType(Constants.FPC_TYPE_NAME);
            } else {
                //如果是 DZL，则需要获取 dzlName和 bomType
                SmtDzl dzl = smtDzlService.selectSmtDzlById(Long.valueOf(ship.getBomId()));
                printVO.setBomName(dzl.getDzlName());
                printVO.setBomType(dzl.getTypeName());
            }
            printVO.setDeliveryQty(String.valueOf(ship.getShipQty()));
            printVO.setRemark(ship.getRemark());
            printVOList.add(printVO);
        }
        //如果数据条数不足十条
        int size = printVOList.size();
        if (size < 10) {
            for (int i = 0; i < 10 - size; i++) {
                DeliveryPrintVO vo = new DeliveryPrintVO();
                vo.setIndex(String.valueOf(size + i + 1));
                vo.setBomName("");
                vo.setBomType("");
                vo.setRemark("");
                vo.setDeliveryQty("");
                printVOList.add(vo);
            }
        }

        //这里是发料打印
//        String jasperPath = "D:\\returns.jasper";
        String jasperPath = jasperReturnsPath;
        Map<String, Object> map = new HashMap<>(10);
        map.put("companyName", iConfigService.selectConfigByKey("print.ship.companyName"));
        map.put("customerName", cusNameMap.get(shipList.get(0).getCusCode()));
        map.put("sendNo", shipList.get(0).getCusCode() + String.valueOf(shipList.get(0).getShipNo()));
        map.put("createDate", new SimpleDateFormat("yyyy-MM-dd").format(shipList.get(0).getCreateTime()));
        map.put("createUser", getSysUser().getUserName());
        try {
            System.out.println(jasperPath);
            demo(response, map, printVOList, jasperPath);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新打印出货单
     *
     * @param response
     */
    @RequestMapping("/rePrint/{cusCode}/{shipNo}")
    public void rePrint(HttpServletResponse response, @PathVariable("cusCode") Integer cusCode, @PathVariable("shipNo") Integer shipNo) {
        try {
            SmtProductShip ships = new SmtProductShip();
            ships.setCusCode(cusCode);
            ships.setShipPrintNo(shipNo);
            List<SmtProductShip> shipList = smtProductShipService.selectSmtProductShipList(ships);
            buildPrintData(response, shipList, 2);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新打印退货单
     *
     * @param response
     */
    @RequestMapping("/rePrintReturns/{cusCode}/{shipNo}")
    public void rePrintReturns(HttpServletResponse response, @PathVariable("cusCode") Integer cusCode, @PathVariable("shipNo") Integer shipNo) {
        try {
            SmtProductShip ships = new SmtProductShip();
            ships.setCusCode(cusCode);
            ships.setShipPrintNo(shipNo);
            List<SmtProductShip> shipList = smtProductShipService.selectSmtProductShipList(ships);

            List<DeliveryPrintVO> printVOList = Lists.newArrayList();
            Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
            for (int i = 0; i < shipList.size(); i++) {
                SmtProductShip ship = shipList.get(i);
                DeliveryPrintVO printVO = new DeliveryPrintVO();
                printVO.setIndex(String.valueOf(i + 1));
                //如果是 FPC，则需要获取 bomName和 bomType
                if (Constants.BOM_TYPE_FPC.intValue() == ship.getOrderType().intValue()) {
                    printVO.setBomName(smtBomService.selectSmtBomById(Long.valueOf(ship.getBomId())).getBomName());
                    printVO.setBomType(Constants.FPC_TYPE_NAME);
                } else {
                    //如果是 DZL，则需要获取 dzlName和 bomType
                    SmtDzl dzl = smtDzlService.selectSmtDzlById(Long.valueOf(ship.getBomId()));
                    printVO.setBomName(dzl.getDzlName());
                    printVO.setBomType(dzl.getTypeName());
                }
                printVO.setDeliveryQty(String.valueOf(ship.getShipQty()));
                printVO.setRemark(null == ship.getRemark() ? "" : ship.getRemark());
                printVOList.add(printVO);
            }
            //如果数据条数不足十条
            int size = printVOList.size();
            if (size < 10) {
                for (int i = 0; i < 10 - size; i++) {
                    DeliveryPrintVO vo = new DeliveryPrintVO();
                    vo.setIndex(String.valueOf(size + i + 1));
                    vo.setBomName("");
                    vo.setBomType("");
                    vo.setDeliveryQty("");
                    vo.setRemark("");
                    printVOList.add(vo);
                }
            }

            //这里是退货打印
//        String jasperPath = "D:\\returns.jasper";
            String jasperPath = jasperReturnsPath;
            Map<String, Object> map = new HashMap<>(10);
            map.put("customerName", cusNameMap.get(shipList.get(0).getCusCode()));
            map.put("companyName", configService.selectConfigByKey("print.ship.companyName"));
            map.put("sendNo", shipList.get(0).getCusCode() + String.valueOf(shipList.get(0).getShipNo()));
            String createBy = shipList.get(0).getCreateBy();
            User user = userService.selectUserByLoginName(createBy);
            map.put("createUser", user.getUserName());
            map.put("createDate", new SimpleDateFormat("yyyy-MM-dd").format(shipList.get(0).getCreateTime()));
            System.out.println(jasperPath);
            demo(response, map, printVOList, jasperPath);
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 组装出货打印数据
     *
     * @param response
     * @param shipList
     */
    private void buildPrintData(HttpServletResponse response, List<SmtProductShip> shipList, int code) {
        List<SmtDzl> dzlList = smtDzlService.selectSmtDzlList(new SmtDzl());
        Map<Integer, String> dzlNameMap = dzlList.stream().collect(Collectors.toMap(SmtDzl::getId, SmtDzl::getDzlName, (x, y) -> x));
        Map<Integer, String> typeNameMap = dzlList.stream().collect(Collectors.toMap(SmtDzl::getId, SmtDzl::getTypeName, (x, y) -> x));
        Map<Integer, String> cusNameMap = getCusCodeAndCusNameMap();
        Map<String, String> configKeyAndValueMap = getConfigKeyAndValueMap();
        //第一条数据是 FPC数据
        SmtProductShip fpcShip = shipList.get(0);
        //这里是出货打印
//        String jasperPath = "D:\\ship.jasper";
        String jasperPath = jasperShipPath;
        Map<String, Object> map = new HashMap<>(16);
        map.put("companyName", configKeyAndValueMap.get("print.ship.companyName"));
        map.put("address", configKeyAndValueMap.get("print.ship.address"));
        map.put("phone", configKeyAndValueMap.get("print.ship.phone"));
        map.put("customerName", cusNameMap.get(fpcShip.getCusCode()));
        map.put("shipNo", fpcShip.getCusCode() + String.valueOf(fpcShip.getShipNo()));
        map.put("bomName", fpcShip.getBomName());
        map.put("fpcCounts", String.valueOf(fpcShip.getShipQty()));
        //总点数
        String bomPoint = smtBomService.selectSmtBomByBomName(fpcShip.getCusCode(), fpcShip.getBomName());
        map.put("pointCounts", String.valueOf(fpcShip.getShipQty() * (Integer.valueOf(bomPoint))));
        map.put("createDate", new SimpleDateFormat("yyyy-MM-dd").format(fpcShip.getCreateTime()));
        map.put("orderNo", fpcShip.getOrderNo());
        map.put("remark", null == fpcShip.getRemark() ? "" : fpcShip.getRemark());
        if (code == 1) {
            map.put("createUser", getSysUser().getUserName());
        } else {
            String createBy = fpcShip.getCreateBy();
            User user = userService.selectUserByLoginName(createBy);
            map.put("createUser", user.getUserName());
        }

        List<ShipPrintVO> voList = Lists.newArrayList();
        //从第一条开始循环电子料
        for (int i = 1; i < shipList.size(); i++) {
            SmtProductShip ship = shipList.get(i);
            ShipPrintVO vo = new ShipPrintVO();
            vo.setIndex(String.valueOf(i));
            vo.setDzlName(dzlNameMap.get(ship.getBomId()));
            vo.setTypeName(typeNameMap.get(ship.getBomId()));
            vo.setDzlNumber(String.valueOf(ship.getShipQty()));
            vo.setBak(String.valueOf(ship.getBak()));
            voList.add(vo);
        }

        //组装24条数据，如果不够将组合24条
        int size = voList.size();
        if (size < 24) {
            for (int i = 0; i < 24 - size; i++) {
                ShipPrintVO printVO = new ShipPrintVO();
                printVO.setIndex(String.valueOf(size + i + 1));
                printVO.setDzlName("");
                printVO.setTypeName("");
                printVO.setDzlNumber("");
                printVO.setBak("");
                voList.add(printVO);
            }
        }
        //组装后需要打印的出货单集合
        List<ShipPrintVO> printVOList = Lists.newArrayList();
        //24条数据组装成 12条对称的数据
        for (int i = 0; i < 12; i++) {
            ShipPrintVO vo = new ShipPrintVO();
            vo.setIndex(String.valueOf(i + 1));
            vo.setDzlName(voList.get(i).getDzlName());
            vo.setTypeName(voList.get(i).getTypeName());
            vo.setDzlNumber(voList.get(i).getDzlNumber());
            vo.setBak(voList.get(i).getBak());
            vo.setIndexs(String.valueOf(13 + i));
            vo.setDzlNames(voList.get(12 + i).getDzlName());
            vo.setTypeNames(voList.get(12 + i).getTypeName());
            vo.setDzlNumbers(voList.get(12 + i).getDzlNumber());
            vo.setBaks(voList.get(12 + i).getBak());
            printVOList.add(vo);
        }
        System.out.println("出货打印的Jasper文件路径：" + jasperPath);
        demo(response, map, printVOList, jasperPath);
    }
}
