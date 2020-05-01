package com.ruoyi.project.smt.financereport.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.MonthConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.financereport.dto.FinanceReportDTO;
import com.ruoyi.project.smt.financereport.vo.FinanceReportVO;
import com.ruoyi.project.smt.paymentrecord.service.ISmtPaymentRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private ISmtPaymentRecordService smtPaymentRecordService;

    @RequiresPermissions("smt:profitReport:view")
    @GetMapping()
    public String applyRecord() {
        return prefix + "/profitReport";
    }


    /**
     * 查询利润表数据
     */
    @RequiresPermissions("smt:profitReport:list")
    @PostMapping("/getAllList")
    @ResponseBody
    public TableDataInfo getAllList(SmtBom smtBom) {
        List<FinanceReportDTO> dtoList = new ArrayList<>();

        //获取所有的客户费用类型
        Map<String, String> feeTypeMap = getFeeTypeList("smt_fee_type");
        //获取所有的付款类型
        Map<String, String> payTypeMap = getFeeTypeList("smt_payment_type");

        //统计 应付模块付款记录
        List<FinanceReportVO> vos = smtPaymentRecordService.getPaymentProfitReportList();

        //遍历所有的付款类型，列表有多少显示多少，没有数据的显示0
        for (int i = 1; i <= payTypeMap.size(); i++) {
            //payTypeMap    1,2,3,4

            FinanceReportDTO dto = new FinanceReportDTO();
            dto.setIndex(i);
            dto.setPaymentType(i);
            dto.setPaymentTypeName(payTypeMap.get(String.valueOf(i)));

            //遍历所有的结果，匹配类型一致的
            for (int k = 0; k < vos.size(); k++) {
                //vos.get(k).getPaymentType().intValue()    4,2,1,4
                System.out.println("付款类型：" + vos.get(k).getPaymentType());
                if (vos.get(k).getPaymentType().intValue() == i) {
                    build(vos.get(k), dto, dtoList);
                }
            }

            dtoList.add(dto);
        }

        return getDataTable(dtoList);
    }


    private void build(FinanceReportVO vo, FinanceReportDTO dto, List<FinanceReportDTO> dtoList) {
        if (StringUtils.isNotEmpty(dtoList)) {
            System.out.println("付款类型是：" + vo.getPaymentType().intValue());
            for (FinanceReportDTO reportDTO : dtoList) {
                if (reportDTO.getPaymentType().intValue() == vo.getPaymentType().intValue()) {
                    dto = reportDTO;
                }
            }
        }
        if (MonthConstants.one.equals(vo.getMonths())) {
            dto.setOne(vo.getTotalPayment());
        } else {
            dto.setOne(new BigDecimal(0));
        }
        if (MonthConstants.two.equals(vo.getMonths())) {
            dto.setTwo(vo.getTotalPayment());
        } else {
            dto.setTwo(new BigDecimal(0));
        }
        if (MonthConstants.three.equals(vo.getMonths())) {
            dto.setThree(vo.getTotalPayment());
        } else {
            dto.setThree(new BigDecimal(0));
        }
        if (MonthConstants.four.equals(vo.getMonths())) {
            dto.setFour(vo.getTotalPayment());
        } else {
            dto.setFour(new BigDecimal(0));
        }
        if (MonthConstants.five.equals(vo.getMonths())) {
            dto.setFive(vo.getTotalPayment());
        } else {
            dto.setFive(new BigDecimal(0));
        }
        if (MonthConstants.six.equals(vo.getMonths())) {
            dto.setSix(vo.getTotalPayment());
        } else {
            dto.setSix(new BigDecimal(0));
        }
        if (MonthConstants.seven.equals(vo.getMonths())) {
            dto.setSeven(vo.getTotalPayment());
        } else {
            dto.setSeven(new BigDecimal(0));
        }
        if (MonthConstants.eight.equals(vo.getMonths())) {
            dto.setEight(vo.getTotalPayment());
        } else {
            dto.setEight(new BigDecimal(0));
        }
        if (MonthConstants.nine.equals(vo.getMonths())) {
            dto.setNine(vo.getTotalPayment());
        } else {
            dto.setNine(new BigDecimal(0));
        }
        if (MonthConstants.ten.equals(vo.getMonths())) {
            dto.setTen(vo.getTotalPayment());
        } else {
            dto.setTen(new BigDecimal(0));
        }
        if (MonthConstants.eleven.equals(vo.getMonths())) {
            dto.setEleven(vo.getTotalPayment());
        } else {
            dto.setEleven(new BigDecimal(0));
        }
        if (MonthConstants.twelve.equals(vo.getMonths())) {
            dto.setTwelve(vo.getTotalPayment());
        } else {
            dto.setTwelve(new BigDecimal(0));
        }
    }

}
