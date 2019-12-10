package com.ruoyi.project.smt.bom.service.impl;

import com.google.common.collect.Lists;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.smt.bom.domain.SmtBom;
import com.ruoyi.project.smt.bom.mapper.SmtBomMapper;
import com.ruoyi.project.smt.bom.service.ISmtBomService;
import com.ruoyi.project.smt.bom.vo.SmtBomVO;
import com.ruoyi.project.smt.bomLine.domain.SmtBomLine;
import com.ruoyi.project.smt.bomLine.mapper.SmtBomLineMapper;
import com.ruoyi.project.smt.dzl.mapper.SmtDzlMapper;
import net.sf.json.JSONArray;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 客户bom信息Service业务层处理
 *
 * @author popo
 * @date 2019-10-19
 */
@Service
public class SmtBomServiceImpl implements ISmtBomService {
    @Autowired
    private SmtBomMapper smtBomMapper;

    @Autowired
    private SmtBomLineMapper smtBomLineMapper;

    @Autowired
    private SmtDzlMapper smtDzlMapper;


    /**
     * 查询客户bom信息
     *
     * @param id 客户bom信息ID
     * @return 客户bom信息
     */
    @Override
    public SmtBom selectSmtBomById(Long id) {
        return smtBomMapper.selectSmtBomById(id);
    }

    /**
     * 查询客户bom信息列表
     *
     * @param smtBom 客户bom信息
     * @return 客户bom信息
     */
    @Override
    public List<SmtBom> selectSmtBomList(SmtBom smtBom) {
        return smtBomMapper.selectSmtBomList(smtBom);
    }

    @Override
    public List<SmtBomVO> selectSmtBomAllList(SmtBom smtBom) {
        return smtBomMapper.selectSmtBomAllList(smtBom);
    }

    /**
     * 新增客户bom信息
     *
     * @param smtBom 客户bom信息
     * @return 结果
     */
    @Override
    public int insertSmtBom(SmtBom smtBom) {
        smtBom.setCreateTime(DateUtils.getNowDate());
        return smtBomMapper.insertSmtBom(smtBom);
    }

    /**
     * 新增客户bom信息和明细信息
     *
     * @param smtBom 客户bom信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSmtBomAndLine(SmtBom smtBom) {
        smtBom.setCreateBy(ShiroUtils.getLoginName());
        smtBom.setCreateTime(DateUtils.getNowDate());
        smtBomMapper.insertSmtBom(smtBom);
        //bom明细信息
        List<SmtBomLine> smtBomLineList = (List<SmtBomLine>) JSONArray.toCollection(JSONArray.fromObject(smtBom.getBomLineList()), SmtBomLine.class);
        smtBomLineList.stream().forEach(line -> {
            line.setBomId(smtBom.getId());
            line.setStatus(Constants.SUCCESS);
            line.setCreateBy(ShiroUtils.getLoginName());
            line.setCreateTime(DateUtils.getNowDate());
            line.setDelFlag(Constants.SUCCESS);
        });
        return smtBomLineMapper.batchInsertSmtBomLine(smtBomLineList);
    }

    /**
     * 修改客户bom信息
     *
     * @param smtBom 客户bom信息
     * @return 结果
     */
    @Override
    public int updateSmtBom(SmtBom smtBom) {
        smtBom.setUpdateTime(DateUtils.getNowDate());
        return smtBomMapper.updateSmtBom(smtBom);
    }

    @Override
    public int updateSmtBomAndBomLine(SmtBom smtBom) {
        smtBom.setUpdateBy(ShiroUtils.getLoginName());
        smtBom.setUpdateTime(DateUtils.getNowDate());
        smtBomMapper.updateSmtBom(smtBom);
        //删除原有的电子料明细信息
        smtBomLineMapper.deleteSmtBomLineByBomId(smtBom.getId());
        //bom明细信息
        String bomLineListStr = smtBom.getBomLineList();
        List<SmtBomLine> smtBomLineList = (List<SmtBomLine>) JSONArray.toCollection(JSONArray.fromObject(bomLineListStr), SmtBomLine.class);
        smtBomLineList.stream().forEach(line -> {
            line.setBomId(smtBom.getId());
            line.setStatus(Constants.SUCCESS);
            line.setCreateBy(ShiroUtils.getLoginName());
            line.setDelFlag(Constants.SUCCESS);
            line.setCreateTime(DateUtils.getNowDate());
        });
        return smtBomLineMapper.batchInsertSmtBomLine(smtBomLineList);
    }

    /**
     * 删除客户bom信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmtBomByIds(String ids) {
        String[] array = Convert.toStrArray(ids);
        for (String id : array) {
            //删除主bom信息
            smtBomMapper.deleteSmtBomById(Integer.valueOf(id));
            //根据主bomId删除对应bom明细信息
            smtBomLineMapper.deleteSmtBomLineByBomId(Integer.valueOf(id));

        }
        return 1;
    }

    /**
     * 删除客户bom信息信息
     *
     * @param id 客户bom信息ID
     * @return 结果
     */
    @Override
    public int deleteSmtBomById(Integer id) {
        return smtBomMapper.deleteSmtBomById(id);
    }

    @Override
    public SmtBom importExcel(InputStream is) throws Exception {
        SmtBom smtBom = new SmtBom();
        List<SmtBomLine> lines = Lists.newArrayList();
        Workbook wb = WorkbookFactory.create(is);
        //默认只想第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            throw new IOException("文件sheet不存在");
        }
        int rows = sheet.getPhysicalNumberOfRows();
        if (rows > 0) {
            //获取第1行第2列 客户编码字段，下表从0开始
            Row rowOne = sheet.getRow(0);
            smtBom.setCusCode(Double.valueOf(String.valueOf(rowOne.getCell(1))).intValue());
            //获取第2行第2列[产品型号]和第6列备注
            Row rowTwo = sheet.getRow(1);
            smtBom.setBomName(rowTwo.getCell(1).getStringCellValue());
            smtBom.setRemark(rowTwo.getCell(6).getStringCellValue());

            for (int i = 4; i < rows; i++) {
                Row row = sheet.getRow(i);
                //从第4行开始，如果第1列不为空，就是有效数据，添加到明细列表集合
                if (StringUtils.isNotEmpty(String.valueOf(row.getCell(0)))) {
                    SmtBomLine line = new SmtBomLine();
                    line.setId(i-3);
                    //第3行是空行，从第4行开始读数据,下表都是从0开始
                    // 2-第3列，电子料名称
                    String dzlName = row.getCell(2).getStringCellValue();
                    line.setDzlName(dzlName.trim());
                    //根据电子料名称获取电子料id
                    Integer id = smtDzlMapper.getDzlIdByDzlName(dzlName);
                    line.setDzlId(id);
                    // 4-第5列，用量
                    Integer dzlNumber = Double.valueOf(String.valueOf(row.getCell(4))).intValue();
                    line.setDzlNumber(dzlNumber);
                    // 5-第6列，备品
                    Integer bak = Double.valueOf(String.valueOf(row.getCell(5))).intValue();
                    line.setBak(bak);
                    // 6-第7列，件位
                    String position = row.getCell(6).getStringCellValue();
                    line.setPosition(position);
                    lines.add(line);
                } else {
                    //如果第1列开始是空，说明有效数据结束，开始读取 总点数，总点数位于 第8列，下标为7
                    Long point = Double.valueOf(String.valueOf(row.getCell(7))).longValue();
                    smtBom.setBomPoint(point);
                    break;
                }
            }
            smtBom.setBomLines(lines);
        }
        is.close();
        return smtBom;
    }

    @Override
    public int validateCusBomUnique(SmtBom bom) {
        return smtBomMapper.validateCusBomUnique(bom);
    }

    @Override
    public String selectSmtBomByBomName(Integer cusCode,String bomName) {
        return smtBomMapper.selectSmtBomByBomName(cusCode,bomName);
    }

}
