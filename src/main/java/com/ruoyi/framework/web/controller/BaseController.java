package com.ruoyi.framework.web.controller;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.service.ISmtBomService;
import com.ruoyi.project.smt.cus.domain.SmtCus;
import com.ruoyi.project.smt.cus.service.ISmtCusService;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import com.ruoyi.project.smt.paymentrecord.domain.SmtPaymentRecord;
import com.ruoyi.project.smt.paymentrecord.service.ISmtPaymentRecordService;
import com.ruoyi.project.smt.reconciliationfile.domain.SmtReconciliationFile;
import com.ruoyi.project.smt.reconciliationfile.service.ISmtReconciliationFileService;
import com.ruoyi.project.system.config.domain.Config;
import com.ruoyi.project.system.config.service.IConfigService;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictDataService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.AjaxResult.Type;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
public class BaseController {

    @Autowired
    private ISmtCusService iSmtCusService;

    @Autowired
    private ISmtDzlService iSmtDzlService;

    @Autowired
    private ISmtBomService iSmtBomService;

    @Autowired
    private IConfigService iConfigService;

    @Autowired
    private IDictDataService dictDataService;

    @Autowired
    private ISmtReconciliationFileService smtReconciliationFileService;


    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public AjaxResult error(Type type, String message) {
        return new AjaxResult(type, message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    public User getSysUser() {
        return ShiroUtils.getSysUser();
    }

    public void setSysUser(User user) {
        ShiroUtils.setSysUser(user);
    }

    public Long getUserId() {
        return getSysUser().getUserId();
    }

    public String getLoginName() {
        return getSysUser().getLoginName();
    }

    /**
     * 查询所有的客户代码和客户名称MAP集合
     *
     * @return
     */
    public Map<Integer, String> getCusCodeAndCusNameMap() {
        //查询所有的客户信息
        List<SmtCus> cusList = iSmtCusService.selectSmtCusList(new SmtCus());
        return cusList.stream().collect(Collectors.toMap(SmtCus::getCusCode, SmtCus::getCusName, (x, y) -> x));
    }

    /**
     * 查询所有的系统参数键值MAP集合
     *
     * @return
     */
    public Map<String, String> getConfigKeyAndValueMap() {
        //查询所有的参数信息
        List<Config> configList = iConfigService.selectConfigList(new Config());
        return configList.stream().collect(Collectors.toMap(Config::getConfigKey, Config::getConfigValue, (x, y) -> x));
    }

    /**
     * 查询所有的电子料ID和电子料类型名称
     *
     * @return
     */
    public Map<Integer, String> getDzlIdAndDzlTypeNameMap() {
        //查询所有的DZL信息
        List<SmtDzl> dzlList = iSmtDzlService.selectSmtDzlList(new SmtDzl());
        return dzlList.stream().collect(Collectors.toMap(SmtDzl::getId, SmtDzl::getTypeName, (x, y) -> x));
    }

    /**
     * 查询所有的电子料ID和电子料名称
     *
     * @return
     */
    public Map<Integer, String> getDzlIdAndDzlNameMap() {
        //查询所有的电子料信息
        List<SmtDzl> dzlList = iSmtDzlService.selectSmtDzlList(new SmtDzl());
        return dzlList.stream().collect(Collectors.toMap(SmtDzl::getId, SmtDzl::getDzlName, (x, y) -> x));
    }

    /**
     * 查询所有的bomID和bom名称
     *
     * @return
     */
    public Map<Integer, String> getBomIdAndBomNameMap() {
        //查询所有的bom信息
        List<SmtBom> bomList = iSmtBomService.selectSmtBomList(new SmtBom());
        return bomList.stream().collect(Collectors.toMap(SmtBom::getId, SmtBom::getBomName, (x, y) -> x));
    }

    /**
     * 查询所有的字典表中客户费用类型 smt_fee_type,smt_payment_type
     *
     * @return
     */
    public Map<String, String> getFeeTypeList(String dictType) {
        DictData data = new DictData();
        data.setDictType(dictType);
        List<DictData> dictData = dictDataService.selectDictDataList(data);
        return dictData.stream().collect(Collectors.toMap(DictData::getDictValue, DictData::getDictLabel, (x, y) -> x));
    }

    /**
     * 生成PDF
     *
     * @param response
     */
    public void demo(HttpServletResponse response, Map<String, Object> map, List lists, String jasperPath) {
        try {
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(lists);
            convertPDF(jrDataSource, jasperPath, response, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void convertPDF(JRDataSource jrDataSource, String jasperPath, HttpServletResponse response, Map map) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(jasperPath));
            response.setContentType("application/pdf");
            OutputStream os = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(fileInputStream, os, map, jrDataSource);
            fileInputStream.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}