/*
 * 项目名称:platform-plus
 * 类名称:ExcelUtils.java
 * 包名称:com.platform.common.utils
 *
 * 修改履历:
 *      日期                修正者      主要内容
 *      2019/1/30 15:44    李鹏军      初版完成
 *
 * Copyright (c) 2019-2019 微同软件
 */
package com.platform.common.utils;

import com.platform.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

/**
 * 操作excel、word、pdf、csv、excel与html互相转换
 *
 * @author 李鹏军
 */
@Slf4j
public class EasyPoiUtils {
    /**
     * 2003
     */
    private static final String EXCEL_XLS = "xls";
    /**
     * 2007
     */
    private static final String EXCEL_XLSX = "xlsx";

    private EasyPoiUtils() {
    }

    /**
     * 读入excel文件，解析后返回
     * 多sheet页，
     *
     * @param file
     * @return key:sheetName value:sheet内容
     */
    public static Map<String, List<String[]>> readExcel(MultipartFile file) {
        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        Map<String, List<String[]>> map = new HashMap<>(16);
        List<String[]> list = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
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
                map.put(sheetName, list);
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private static void checkFile(MultipartFile file) {
        //判断文件是否存在
        if (null == file) {
            log.error("文件不存在！");
            throw new BusinessException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(EXCEL_XLS) && !fileName.endsWith(EXCEL_XLSX)) {
            log.error(fileName + "不是excel文件");
            throw new BusinessException(fileName + "不是excel文件");
        }
    }

    private static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(EXCEL_XLS)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(EXCEL_XLSX)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return workbook;
    }

    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == NUMERIC) {
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            //数字
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
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
}
