/*
 * 项目名称:material
 * 类名称:ExcelUtils.java
 * 包名称:cn.zertone.sgcc.common.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/30 15:44    林佛权      初版完成
 *
 * Copyright (c) 2017-2019 咨同科技
 */
package com.tongyi.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 操作excel、word、pdf、csv、excel与html互相转换
 *
 * @author 林佛权
 */
@Slf4j
@Component
public class EasyPoiService {
    /**
     * 2003
     */
    private static final String EXCEL_XLS = "xls";
    /**
     * 2007
     */
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 读入excel文件，解析后返回
     * 多sheet页，
     *
     * @param file
     * @return key:sheetName value:sheet内容
     */
    public Map<String, List<String[]>> readExcel(MultipartFile file) throws Exception {
        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        Map<String, List<String[]>> map = new HashMap<>(16);
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                List<String[]> list = new ArrayList<>();
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                String sheetName = workbook.getSheetName(sheetNum);
                if (sheet == null) {
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
                if (list.size() > 0) {
                    map.put(sheetName, list);
                }
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private void checkFile(MultipartFile file) throws Exception {
        //判断文件是否存在
        if (null == file) {
            log.error("文件不存在！");
            throw new Exception("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(EXCEL_XLS) && !fileName.endsWith(EXCEL_XLSX)) {
            log.error(fileName + "不是excel文件");
            throw new Exception(fileName + "不是excel文件");
        }
    }

    private void checkFile(File file) throws FileNotFoundException {
        //判断文件是否存在
        if (null == file) {
            log.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        if (!file.exists()) {
            log.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        checkFile(file.getName());
    }
    private void checkFile(String fileName) throws FileNotFoundException {
        //判断文件是否存在
        if (null == fileName) {
            log.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        //判断文件是否是excel文件
        if (!fileName.endsWith(EXCEL_XLS) && !fileName.endsWith(EXCEL_XLSX)) {
            log.error(fileName + "不是excel文件");
            throw new FileNotFoundException(fileName + "不是excel文件");
        }
    }
    private Workbook getWorkBook(MultipartFile file) throws IOException {
        //获得文件名
        String fileName = file.getOriginalFilename();
        return getWorkBook(fileName,file.getInputStream());
    }
    private Workbook getWorkBook(File file) throws FileNotFoundException {
        return getWorkBook(file.getName(),new FileInputStream(file));
    }
    private Workbook getWorkBook(String fileName, InputStream in){
        //获得文件名
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流

            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(EXCEL_XLS)) {
                //2003
                workbook = new HSSFWorkbook(in);
            } else if (fileName.endsWith(EXCEL_XLSX)) {
                //2007
                workbook = new XSSFWorkbook(in);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return workbook;
    }

    private String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == CellType.NUMERIC) {
            if (!HSSFDateUtil.isCellDateFormatted(cell)) {
                cell.setCellType(CellType.STRING);
            }
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            //数字
            case NUMERIC: {
                int format = cell.getCellStyle().getDataFormat();
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    cellValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
                } else if (format > 176 && format < 185) {
                    cellValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
                } else {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            //字符串
            case STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            //Boolean
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            //公式
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            //空值
            case BLANK:
                cellValue = "";
                break;
            //故障
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /**
     * 开始导出数据信息
     *
     */
    public void exportToExcel(String sheetName, String[] headers, int rows, RowHandler callback, OutputStream out) throws IOException {
        //检查参数配置信息
        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook ();
        //创建工作表
        XSSFSheet wbSheet = wb.createSheet();
        wb.setSheetName(0, sheetName);
        //设置默认行宽
        wbSheet.setDefaultColumnWidth(20);

        // 标题样式（加粗，垂直居中）
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        //在第1行创建rows
        XSSFRow row = wbSheet.createRow(0);
        //设置列头元素
        XSSFCell cellHead = null;
        for (int i = 0; i < headers.length; i++) {
            cellHead = row.createCell(i);
            cellHead.setCellType(CellType.STRING);
            cellHead.setCellValue(headers[i]);
        }

        //开始写入实体数据信息
        for (int i = 0; i < rows; i++) {
            XSSFRow roww = wbSheet.createRow(i+1);
            if (null!=callback) {
                callback.newRow(roww, i + 1);
            }
        }
        wb.write(out);
        wb.close();
    }
//    private void newCell(XSSFRow roww,int index,String value){
//        XSSFCell cell = roww.createCell(index);
//        cell.setCellType(CellType.STRING);
//        cell.setCellValue(Strings.isNullOrEmpty(value) ? "" : value);
//    }
}
