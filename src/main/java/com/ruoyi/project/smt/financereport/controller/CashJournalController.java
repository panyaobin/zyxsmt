package com.ruoyi.project.smt.financereport.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.applyrecord.domain.SmtApplyRecord;
import com.ruoyi.project.smt.applyrecord.service.ISmtApplyRecordService;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.vo.SmtBomVO;
import com.ruoyi.project.smt.financereport.vo.CashJournalVO;
import com.ruoyi.project.smt.financereport.vo.PaymentVO;
import com.ruoyi.project.smt.financereport.vo.ReceiveVO;
import com.ruoyi.project.smt.paymentrecord.domain.SmtPaymentRecord;
import com.ruoyi.project.smt.paymentrecord.service.ISmtPaymentRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 现金日记账controller
 * @Author Administrator
 * @Date 2020/4/19 11:09
 * @Version 1.0
 **/
@Controller
@RequestMapping("/smt/cashJournal")
public class CashJournalController extends BaseController {
    private String prefix = "smt/financereport";

    @Autowired
    private ISmtPaymentRecordService smtPaymentRecordService;

    @Autowired
    private ISmtApplyRecordService smtApplyRecordService;


    @RequiresPermissions("smt:cashJournal:view")
    @GetMapping()
    public String applyRecord()
    {
        return prefix + "/cashJournal";
    }

    /**
     * 查询现金日记账数据
     */
    @RequiresPermissions("smt:cashJournal:list")
    @PostMapping("/getAllList")
    @ResponseBody
    public TableDataInfo getAllList(ReceiveVO rVo, PaymentVO pVo) {
        List<CashJournalVO> list = buildCashJournalVOList(rVo,pVo);
        return getDataTable(list);
    }

    /**
     * 导出现金日记账数据
     */
    @RequiresPermissions("smt:cashJournal:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceiveVO rVo, PaymentVO pVo) {
        List<CashJournalVO> list = buildCashJournalVOList(rVo,pVo);
        ExcelUtil<CashJournalVO> util = new ExcelUtil<CashJournalVO>(CashJournalVO.class);
        return util.exportExcel(list, "现金日记账");
    }

    /**
     * 组建现金日记账数据
     * @return
     */
    public List<CashJournalVO> buildCashJournalVOList(ReceiveVO rVo, PaymentVO pVo){
        //首先查询【应收账目】客户付款记录
        List<SmtPaymentRecord> paymentRecordList = smtPaymentRecordService.getPaymentRecordList(rVo);
        //然后查询【应付账目】付款记录
        List<SmtApplyRecord> applyRecordList = smtApplyRecordService.getApplyRecordList(pVo);

        List<CashJournalVO> voList = Lists.newArrayList();
        //获取所有的客户费用类型
        Map<String, String> feeTypeList = getFeeTypeList("smt_fee_type");
        //获取所有的付款类型
        Map<String, String> payTypeList = getFeeTypeList("smt_payment_type");

        startPage();
        //组装客户付款记录

        if (StringUtils.isNotEmpty(paymentRecordList)&&StringUtils.isEmpty(pVo.getPaymentType())){
            paymentRecordList.stream().forEach(payment->{
                CashJournalVO vo = new CashJournalVO();
                vo.setReconciliationTime(payment.getReconciliationTime());
                vo.setReconciliationTimeStr(DateUtils.parseDateToStr("yyyy-MM",payment.getReconciliationTime()));
                vo.setFeeType(feeTypeList.get(payment.getFeeType()));
                vo.setReconciliationNo(payment.getReconciliationNo());
                vo.setCreateTime(payment.getCreateTime());
                vo.setPaymentTime(payment.getPaymentTime());
                vo.setCreateTimeStr(DateUtils.parseDateToStr("yyyy-MM",payment.getCreateTime()));
                vo.setReceiveFee(payment.getPaymentAmount());
                vo.setRemark(payment.getRemark());
                voList.add(vo);
            });
        }
        //组装付款记录
        if (StringUtils.isNotEmpty(applyRecordList)&&StringUtils.isEmpty(rVo.getReceiveType())){
            applyRecordList.stream().forEach(apply->{
                CashJournalVO vo = new CashJournalVO();
                vo.setReconciliationTime(apply.getReconciliationTime());
                vo.setReconciliationTimeStr(DateUtils.parseDateToStr("yyyy-MM",apply.getReconciliationTime()));
                vo.setFeeType(payTypeList.get(apply.getPaymentType()));
                vo.setReconciliationNo(apply.getPaymentNo());
                vo.setCreateTime(apply.getCreateTime());
                vo.setPaymentTime(apply.getPaymentTime());
                vo.setCreateTimeStr(DateUtils.parseDateToStr("yyyy-MM",apply.getCreateTime()));
                vo.setPaymentFee(apply.getPaymentAmount());
                vo.setHandFee(apply.getHandFee());
                vo.setRemark(apply.getRemark());
                voList.add(vo);
            });
        }
        return voList.stream().sorted(Comparator.comparing(CashJournalVO::getPaymentTime).reversed()).collect(Collectors.toList());
    }
}
