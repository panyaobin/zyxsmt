package com.ruoyi.project.smt.paymentapply.controller;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.NumberToCN;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.smt.applyrecord.service.ISmtApplyRecordService;
import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.paymentapply.print.SmtPaymentApplyPrintVO;
import com.ruoyi.project.smt.paymentinfo.domain.SmtPaymentInfo;
import com.ruoyi.project.smt.paymentinfo.service.ISmtPaymentInfoService;
import com.ruoyi.project.smt.reconciliation.domain.SmtReconciliation;
import com.ruoyi.project.smt.reconciliationfile.domain.SmtReconciliationFile;
import com.ruoyi.project.smt.reconciliationfile.service.ISmtReconciliationFileService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.smt.paymentapply.domain.SmtPaymentApply;
import com.ruoyi.project.smt.paymentapply.service.ISmtPaymentApplyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 付款申请Controller
 *
 * @author popo
 * @date 2020-04-06
 */
@lombok.extern.java.Log
@Controller
@RequestMapping("/smt/paymentApply")
public class SmtPaymentApplyController extends BaseController {
    private String prefix = "smt/paymentapply";

    @Value("${jasper.path.apply}")
    private String applyPrintPath;

    @Autowired
    private ISmtPaymentApplyService smtPaymentApplyService;

    @Autowired
    private ISmtPaymentInfoService smtPaymentInfoService;

    @Autowired
    private ISmtReconciliationFileService smtReconciliationFileService;

    @Autowired
    private ISmtApplyRecordService smtApplyRecordService;

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
        for (SmtPaymentApply apply : list) {
            BigDecimal decimal = smtApplyRecordService.selectSumApplyAmount(apply.getPaymentNo());
            apply.setPaidAmount(null == decimal ? new BigDecimal(0) : decimal);
            apply.setArrears(apply.getApplyAmount().subtract(null == decimal ? new BigDecimal(0) : decimal));
        }
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
    public String add(Model model) {
        Integer no = getMaxPaymentNo();
        log.info("新增付款申请生成对账单号是："+no);
        model.addAttribute("no",no);
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
     *
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
    public AjaxResult changeStatus(SmtPaymentApply apply) {
        return toAjax(smtPaymentApplyService.changeStatus(apply));
    }


    /**
     * 获取现在付款申请单号，如果没有初始化第一位，有则递增
     *
     * @return
     */
//    @RequestMapping("/getMaxPaymentNo")
//    @ResponseBody
    public Integer getMaxPaymentNo() {
        List<SmtPaymentApply> list = smtPaymentApplyService.selectSmtPaymentApplyList(new SmtPaymentApply());
        if (!StringUtils.isEmpty(list)) {
            //获取num列表取最大值
            List<Integer> numList = list.stream().map(SmtPaymentApply::getPaymentNo).collect(Collectors.toList());
            int no = Collections.max(numList);
            return no + 1;
        }
        return Constants.PAYMENT_APPLY_NO + 1;
    }

    /**
     * 客户对账新增上传文件
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file, @RequestParam("num") Integer num, @RequestParam("type") Integer type) {
        try {
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            SmtReconciliationFile smtFile = new SmtReconciliationFile();
            smtFile.setReconciliationNo(num);
            smtFile.setFileType(type);
            smtFile.setFileUrl(avatar);
            smtFile.setCreateBy(ShiroUtils.getSysUser().getUserName());
            smtReconciliationFileService.insertSmtReconciliationFile(smtFile);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }

    /**
     * 付款申请打印功能
     * @param id
     * @param response
     */
    @RequestMapping(value = "print")
    public void test(@RequestParam("id") String id, HttpServletResponse response) {
        SmtPaymentApplyPrintVO apply = smtPaymentApplyService.selectPrintSmtPaymentApplyById(id);
        System.out.println("付款申请打印jasper文件的路径是：" + applyPrintPath);
        Map<String, Object> map = new HashMap<>(10);
        String date = DateFormatUtils.format(apply.getCreateTime(), "yyyy年MM月dd日");
        map.put("createDate",date);
        map.put("num",String.valueOf(apply.getPaymentNo()));
        map.put("paymentUnit",apply.getCollectionUnit());
        map.put("accountNumber",apply.getAccountNumber()+"  "+apply.getAccountName());
        map.put("accountBank",apply.getAccountBank());
        map.put("paymentMoney", NumberToCN.number2CNMontrayUnit(apply.getApplyAmount()));
        map.put("money",String.valueOf(apply.getApplyAmount()));
        map.put("paymentReason",null!=apply.getPaymentReason()?apply.getPaymentReason():"");
        map.put("remarks",null!=apply.getRemark()?apply.getRemark():"");
        map.put("createUser", apply.getCreateBy());
        List<SmtPaymentApplyPrintVO> list=new ArrayList<>();
        list.add(apply);
        try {
            demo(response, map, list, applyPrintPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
