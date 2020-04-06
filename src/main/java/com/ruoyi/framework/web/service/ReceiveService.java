package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.project.smt.paymentinfo.domain.SmtPaymentInfo;
import com.ruoyi.project.smt.paymentinfo.service.ISmtPaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/11/2 22:24
 * @Version 1.0
 **/
@Service("receive")
public class ReceiveService {

    @Autowired
    private ISmtPaymentInfoService smtPaymentInfoService;

    /**
     * 查询所有的付款信息
     * @return
     */
    public List<SmtPaymentInfo> getPaymentInfo(){
        SmtPaymentInfo info = new SmtPaymentInfo();
        info.setStatus(Constants.SUCCESS);
        return smtPaymentInfoService.selectSmtPaymentInfoList(info);
    }
}
