package com.ruoyi.framework.web.service;

import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/11/2 22:24
 * @Version 1.0
 **/
@Service("dzl")
public class DzlService {

    @Autowired
    private ISmtDzlService smtDzlService;

    /**
     * 查询所有的客户
     *
     * @return
     */
    public List<SmtDzl> getDzl() {
        return smtDzlService.selectSmtDzlList(new SmtDzl());
    }
}
