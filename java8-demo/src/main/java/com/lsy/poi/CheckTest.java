package com.lsy.poi;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class CheckTest {
    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(
                new FileInputStream("/Users/icourt/Downloads/杨鑫辉Alpha案例库检索记录表20220302.xlsx"));
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 7; i < 8; i++) {
            StringBuilder stringBuilder = null;
            XSSFRow row = sheet.getRow(i);
            String val = row.getCell(10).getStringCellValue();
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
            System.out.println(yuan);
            System.out.println(bei);

        }

    }
}
