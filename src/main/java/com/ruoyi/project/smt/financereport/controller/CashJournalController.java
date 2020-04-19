package com.ruoyi.project.smt.financereport.controller;

import com.google.common.collect.Lists;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.applyrecord.domain.SmtApplyRecord;
import com.ruoyi.project.smt.applyrecord.service.ISmtApplyRecordService;
import com.ruoyi.project.smt.financereport.vo.CashJournalVO;
import com.ruoyi.project.smt.paymentrecord.domain.SmtPaymentRecord;
import com.ruoyi.project.smt.paymentrecord.service.ISmtPaymentRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
    public TableDataInfo getAllList() {
        //首先查询【应收账目】客户付款记录
        List<SmtPaymentRecord> paymentRecordList = smtPaymentRecordService.getPaymentRecordList();
        //然后查询【应付账目】付款记录
        List<SmtApplyRecord> applyRecordList = smtApplyRecordService.getApplyRecordList();

        List<CashJournalVO> voList = Lists.newArrayList();
        //获取所有的客户费用类型
        Map<String, String> feeTypeList = getFeeTypeList("smt_fee_type");
        //获取所有的付款类型
        Map<String, String> payTypeList = getFeeTypeList("smt_payment_type");

        startPage();
        //组装客户付款记录
        paymentRecordList.stream().forEach(payment->{
            CashJournalVO vo = new CashJournalVO();
            vo.setReconciliationTime(payment.getReconciliationTime());
            vo.setFeeType(feeTypeList.get(payment.getFeeType()));
            vo.setReconciliationNo(payment.getReconciliationNo());
            vo.setCreateTime(payment.getCreateTime());
            vo.setReceiveFee(payment.getPaymentAmount());
            vo.setRemark(payment.getRemark());
            voList.add(vo);
        });
        //组装付款记录
        applyRecordList.stream().forEach(apply->{
            CashJournalVO vo = new CashJournalVO();
            vo.setReconciliationTime(apply.getReconciliationTime());
            vo.setFeeType(payTypeList.get(apply.getPaymentType()));
            vo.setReconciliationNo(apply.getPaymentNo());
            vo.setCreateTime(apply.getCreateTime());
            vo.setPaymentFee(apply.getPaymentAmount());
            vo.setHandFee(apply.getHandFee());
            vo.setRemark(apply.getRemark());
            voList.add(vo);
        });
        return getDataTable(voList);
    }
}
