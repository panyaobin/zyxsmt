package com.ruoyi.project.smt.ledger.util;

import com.google.common.collect.Lists;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.smt.entryLine.domain.SmtOrderEntryLine;
import com.ruoyi.project.smt.ledger.vo.EntryExportVO;
import com.ruoyi.project.smt.ledger.vo.ShipExportVO;
import com.ruoyi.project.smt.ledger.vo.SmtLedgerExportVO;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Description
 * @Author Administrator
 * @Date 2019/12/9 22:34
 * @Version 1.0
 **/
@Slf4j
@Component
public class LedgerExportUtil {

    /**
     * 条件导出物料台账
     *
     * @param response
     * @throws Exception
     */
    public AjaxResult export(HttpServletResponse response, SmtLedgerExportVO vo) throws Exception {
        String fileName = "条件导出物料台账.xlsx";
        @Cleanup OutputStream out = new FileOutputStream(getAbsoluteFile(fileName));

        @Cleanup Workbook wb = new SXSSFWorkbook(5000);
        //设置第一行标题栏样式
        CellStyle rowStyle = wb.createCellStyle();
        //设置水平对齐格式
        rowStyle.setAlignment(HorizontalAlignment.CENTER);
        rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置字体颜色等信息
        Font font = wb.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 16);
        rowStyle.setFont(font);
        Sheet sheet = wb.createSheet();
        //创建第一栏分类栏，来料和出货分类
        Row row0 = sheet.createRow(0);
        //合并单元格的方法：指定 4 个参数，起始行，结束行，起始列，结束列。然后这个区域将被合并。
        CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 5);
        CellRangeAddress region2 = new CellRangeAddress(0, 0, 6, 14);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        row0.createCell(0).setCellValue("来料");
        row0.getCell(0).setCellStyle(rowStyle);
        row0.createCell(6).setCellValue("出货");
        row0.getCell(6).setCellStyle(rowStyle);
        row0.setHeight((short) 550);
        //第一行标题栏
        Row row = sheet.createRow(1);
        row.setRowStyle(rowStyle);
        row.setHeight((short) 500);
        for (int i = 0; i < LedgerConstants.EXPORTTITLEARR.length; i++) {
            sheet.setColumnWidth(0, 1500);
            if (i == 1 || i == 6 || i == 7 || i == 13) {
                sheet.setColumnWidth(i, 5000);
            } else {
                sheet.setColumnWidth(i, 3000);
            }
            Cell cell = row.createCell(i);
            cell.setCellStyle(rowStyle);
            cell.setCellValue(LedgerConstants.EXPORTTITLEARR[i]);
        }
        //获取[来料]集合，组装数据
        String entryData = vo.getEntryData();
        //获取[出货]集合，组装数据
        String shipData = vo.getShipData();
        //如果不为空处理来料Excel，为空不做操作
        int entrySize = 0;
        int shipSize = 0;
        List<EntryExportVO> entryList = Lists.newArrayList();
        List<ShipExportVO> shipList = Lists.newArrayList();
        if (StringUtils.isNotEmpty(entryData)) {
            entryList = (List<EntryExportVO>) JSONArray.toCollection(JSONArray.fromObject(entryData), EntryExportVO.class);
            entrySize = entryList.size();
        }

        //如果不为空处理出货Excel，为空不做操作
        if (StringUtils.isNotEmpty(shipData)) {
            shipList = (List<ShipExportVO>) JSONArray.toCollection(JSONArray.fromObject(shipData), ShipExportVO.class);
            shipSize = shipList.size();
        }
        int size = entrySize > shipSize ? entrySize : shipSize;

        //设置第一列[序号]样式
        CellStyle style = wb.createCellStyle();
        //设置水平对齐格式
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        //比较【来料】和【出货】拿个集合数量多，则创建多的数据行数
        for (int i = 2; i <= size + 1; i++) {
            Row r = sheet.createRow(i);
            r.setRowStyle(style);
            r.setHeight((short) 500);
            r.createCell(0).setCellValue(i - 1);
            r.getCell(0).setCellStyle(style);
            if (entrySize >= i - 1) {
                r.createCell(1).setCellValue(entryList.get(i - 2).getDzlName());
                r.getCell(1).setCellStyle(style);
                r.createCell(2).setCellValue(entryList.get(i - 2).getCusCode());
                r.getCell(2).setCellStyle(style);
                r.createCell(3).setCellValue((entryList.get(i - 2).getEntryTime()).substring(0, 10));
                r.getCell(3).setCellStyle(style);
                r.createCell(4).setCellValue(entryList.get(i - 2).getOrderNo());
                r.getCell(4).setCellStyle(style);
                r.createCell(5).setCellValue(entryList.get(i - 2).getOrderQty());
                r.getCell(5).setCellStyle(style);
            }
            if (shipSize >= i - 1) {
                r.createCell(6).setCellValue(shipList.get(i - 2).getDzlName());
                r.getCell(6).setCellStyle(style);
                r.createCell(7).setCellValue(shipList.get(i - 2).getCusCode());
                r.getCell(7).setCellStyle(style);
                r.createCell(8).setCellValue((shipList.get(i - 2).getShipTime().substring(0, 10)));
                r.getCell(8).setCellStyle(style);
                r.createCell(9).setCellValue(shipList.get(i - 2).getOrderNo());
                r.getCell(9).setCellStyle(style);
                r.createCell(10).setCellValue(shipList.get(i - 2).getBomName());
                r.getCell(10).setCellStyle(style);
                r.createCell(11).setCellValue(shipList.get(i - 2).getFpcShipQty());
                r.getCell(11).setCellStyle(style);
                r.createCell(12).setCellValue(shipList.get(i - 2).getDzlNumber());
                r.getCell(12).setCellStyle(style);
                r.createCell(13).setCellValue(shipList.get(i - 2).getShipQty());
                r.getCell(13).setCellStyle(style);
                r.createCell(14).setCellValue(shipList.get(i - 2).getBak());
                r.getCell(14).setCellStyle(style);
            }
        }
        wb.write(out);
        return AjaxResult.success(fileName);
    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public String getAbsoluteFile(String filename) {
        String downloadPath = RuoYiConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }
}
