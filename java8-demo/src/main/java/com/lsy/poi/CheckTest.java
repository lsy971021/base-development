package com.lsy.poi;

import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CheckTest {
    private static final String path = "/Users/icourt/Downloads/杨鑫辉Alpha案例库检索记录表20220302.xlsx";
    //需要解析的列
    private static int sourceLine=10;
    //开始解析的行
    private static int startRow=7;
    //插入的原告列
    private static int line1=11;
    //插入的被告列
    private static int line2=12;

    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(
                new FileInputStream(path));
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = startRow; i < sheet.getLastRowNum(); i++) {
            StringBuilder stringBuilder = null;
            XSSFRow row = sheet.getRow(i);
            String val = row.getCell(sourceLine).getStringCellValue();
            System.out.println(val);
            boolean flagStart = false;
            boolean flagMid = false;
            boolean flagEnd = false;
            boolean isYuan = false;
            List<String> yuan = new ArrayList<>();
            List<String> bei = new ArrayList<>();
            for (int o = 0; o < val.length(); o++) {
                if (!flagStart && (val.charAt(o) == '原' || val.charAt(o) == '被')) {
                    flagStart = true;
                    isYuan = val.charAt(o) == '原' ? true : false;
                    continue;
                }
                if (flagStart) {
                    if (val.charAt(o) == '告') {
                        flagMid = true;
                        continue;
                    }
                    flagStart = false;
                }
                if (flagMid) {
                    if (val.charAt(o) == '：') {
                        flagEnd = true;
                        continue;
                    }
                    flagStart = false;
                    flagMid = false;
                }
                if (flagEnd) {
                    if (stringBuilder == null) stringBuilder = new StringBuilder();
                    stringBuilder.append(val.charAt(o));
                    if (val.charAt(o) == '司' || val.charAt(o) == '，' || val.charAt(o) == '。') {
                        if (val.charAt(o - 1) == '公') {
                            if (isYuan)
                                yuan.add(stringBuilder.toString());
                            else bei.add(stringBuilder.toString());
                        }
                        stringBuilder = null;
                        flagStart = false;
                        flagMid = false;
                        flagEnd = false;
                    }
                }
            }
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("微软雅黑");
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            XSSFCell cell1 = row.createCell(line1);
            cell1.setCellStyle(cellStyle);
            XSSFCell cell2 = row.createCell(line2);
            cell2.setCellStyle(cellStyle);
            if(!yuan.isEmpty())
                cell1.setCellValue(yuan.toString().substring(1,yuan.toString().length()-1).replaceAll(", ","\r\n"));
            if(!bei.isEmpty())
                cell2.setCellValue(bei.toString().substring(1,bei.toString().length()-1).replaceAll(", ","\r\n"));
            workbook.write(new FileOutputStream(path));
        }
    }
}
