package com.allen.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class ExcelUtil {

    public static Object[][] getTable(String filePath, String sheetName,int startRow){
        if (startRow < 0) {
            startRow = 0;
        }
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheet(sheetName);
        int totalRow = sheet.getLastRowNum();
        Row row = sheet.getRow(0);
        int totalCol = 0;
        if(null != row){
            totalCol = row.getLastCellNum();
        }
        Object[][] table = new Object[totalRow][totalCol];
        int ci = 0;
        for (int i = startRow; i <= totalRow; i++, ci++) {
            int cj = 0;
            for (int j = 0; j < totalCol; j++, cj++) {
                table[ci][cj] = getCellData(sheet, i, j);
            }
        }
        return table;
    }


    public static Object getCellData(Sheet sheet, int RowNum, int ColNum){
        try {
            Cell cell = sheet.getRow(RowNum).getCell(ColNum);
            if(null == cell){
                return null;
            }
            int dataType = cell.getCellType();
            switch (dataType){
                case 3:
                    return "";
                case 0:
                    return cell.getNumericCellValue();
                case 1:
                    return cell.getStringCellValue();
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }
}
