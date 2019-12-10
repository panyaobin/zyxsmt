package com.ruoyi.framework.web.service;

import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.cus.service.ISmtCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/11/2 22:24
 * @Version 1.0
 **/
@Service("cus")
public class CusService {

    @Autowired
    private ISmtCusService smtCusService;

    /**
     * 查询所有的客户
     * @return
     */
    public List<SmtCus> getCus(){
        return smtCusService.selectSmtCusList(new SmtCus());
    }
}
