package com.tongyi.common.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;

public interface RowHandler {
    public void newRow(XSSFRow row ,int index);
}
