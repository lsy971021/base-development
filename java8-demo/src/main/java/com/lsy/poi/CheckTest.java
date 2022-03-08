package com.lsy.poi;

import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CheckTest {
    private String path = "/Users/icourt/Downloads/待二次处理数据.xlsx";
    //需要解析的列
    private static int sourceLine = 10;
    //开始解析的行
    private static int startRow = 7;
    //最后解析的行
    private static int endRow = 30;
    //插入的原告列
    private static int line1 = 11;
    //插入的原告企业类型
    private static int line2 = 12;
    //插入的被告列
    private static int line3 = 13;
    //插入的被告企业类型
    private static int line4 = 14;
    //redis hash key
    private final String key="xlsxTest";

    @Test
    public void test1() throws Exception {
        Map<String,String> map = new HashMap<>();
        XSSFWorkbook workbook = new XSSFWorkbook(
                new FileInputStream(path));
        XSSFSheet sheet = workbook.getSheetAt(1);
        XSSFSheet result = workbook.createSheet("result");
        for (int i = 1; i < 961; i++) {
            XSSFRow row = sheet.getRow(i);
            XSSFCell cell = row.getCell(0);
            XSSFCell cell2 = row.getCell(1);
            String value = cell.getStringCellValue();
            String value2 = cell2.getStringCellValue();
            /*int flagStart=0;
            int flagEnd=0;
            for (int u = 0; u < value.length(); u++) {
                if(value.charAt(u)=='司'){
                    if(value.charAt(u-1)=='公'){
                        String key = value.substring(flagStart, u);

                        flagStart=u+1;
                        map.put(key,)
                    }
                }
            }*/
            String[] keys = value.split("公司");
            for (String s : keys) {
                s=s+"公司";
                System.out.println(s);
            }
            /*String[] values = value2.split("\r\n");
            System.out.println(keys.length+"===="+values.length);
            //System.out.println("values==="+values.toString());
            Arrays.stream(values).forEach(c-> System.out.println("values==="+c));
            for (int u = 0; u < keys.length; u++) {
                map.put(keys[u],values[u]);
            }*/
        }

        /*int row = 1;
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            XSSFRow resultRow = result.createRow(row++);
            resultRow.createCell(1).setCellValue(stringStringEntry.getKey());
            resultRow.createCell(2).setCellValue(stringStringEntry.getValue());
        }
        workbook.write(new FileOutputStream(path));
        workbook.close();*/
    }


    public void test() throws Exception {
        System.out.println("===============开始运行================");
        XSSFWorkbook workbook = new XSSFWorkbook(
                new FileInputStream("/Users/icourt/Downloads/杨鑫辉Alpha案例库检索记录表20220302.xlsx"));
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = startRow; i < endRow; i++) {
            StringBuilder stringBuilder = null;
            XSSFRow row = sheet.getRow(i);
            String val = row.getCell(sourceLine).getStringCellValue();
            System.out.println(val);
            boolean flagStart = false;
            boolean flagMid = false;
            boolean flagEnd = false;
            boolean isYuan = false;
            List<String> yuan = new ArrayList<>();
            List<String> yuanType = new ArrayList<>();
            List<String> bei = new ArrayList<>();
            List<String> beiType = new ArrayList<>();

            //提取原告被告信息
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

            //设置字体属性
            XSSFCellStyle cellStyle = setFont(workbook, row);
            XSSFCell cell1 = row.createCell(line1);
            cell1.setCellStyle(cellStyle);
            XSSFCell cell2 = row.createCell(line2);
            cell2.setCellStyle(cellStyle);
            XSSFCell cell3 = row.createCell(line3);
            cell3.setCellStyle(cellStyle);
            XSSFCell cell4 = row.createCell(line4);
            cell4.setCellStyle(cellStyle);

            //填充单元格信息
            solveData(yuan,yuanType,cell1,cell2);
            solveData(bei,beiType,cell3,cell4);

            //写入磁盘
            workbook.write(new FileOutputStream("/Users/icourt/Downloads/杨鑫辉Alpha案例库检索记录表20220302.xlsx"));
            workbook.close();
        }
    }

    public void solveData(List<String> data,List<String> dataType,XSSFCell val,XSSFCell type){
        if (!data.isEmpty()) {
            //设置原/被告值
            setCellVal(val, data);
            //对一行数据所有原/被告值遍历
            data.forEach(y -> {
                //判断缓存中是否存在该原/被告的类型信息
//                String value = redisTemplate.getHashVal(key, y);
                String value = null;
                if (value != null) {
                    dataType.add(value);
                } else {
                    //查询类型
                    //searchList(y, dataType);
                }
            });
            setCellVal(type, dataType);
        }
    }

    //根据公司名称查找公司类型
    /*public void searchList(String name, List<String> type) {
//        FuzzyMatchDTO data = null;
//        BaseDataDTO list = null;
        //查找列表
        BaseDataDTO list = search(new SearchVO() {{
            setPageNum(1);
            setSourceType(1);
            setThirdQueryId(name);
        }}).getData();
        JSONArray read = (JSONArray) JSONPath.read(JSON.toJSONString(list), "$.result");
        int size = read.size();
        if (size > 0) {
            final JSONObject o1 = (JSONObject) read.get(0);
            final FuzzyMatchDTO data = JSON.parseObject(JSON.toJSONString(o1), FuzzyMatchDTO.class);

            if (!Objects.isNull(data)) {
                //
                BaseinfoDTO info = baseInfo(new SearchVO() {{
                    //获取eid并设置eid
                    setEid(bindThirdId(data).getData());
                    setCompanyType(Integer.valueOf(data.getMergeSourceDTOList().get(0).getCompanyType()));
                    setSkipCache(false);
                    setSourceType(1);
                }}).getData();

                String value = info.getCompanyOrgType() == null ? (info.getHkEnterpriseBaseInfo().getCompanyOrgType() == null
                        ? "-" : info.getHkEnterpriseBaseInfo().getCompanyOrgType()) : info.getCompanyOrgType();
                type.add(value);
                redisTemplate.putHashVal(key,name,value);
            }else {
                type.add("-");
            }
        }else {
            type.add("-");
        }
    }
*/
    public XSSFCellStyle setFont(XSSFWorkbook workbook, XSSFRow row) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("微软雅黑");
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }

    public void setCellVal(XSSFCell cell, List list) {
        cell.setCellValue(list.toString().substring(1, list.toString().length() - 1).replaceAll(", ", "\r\n"));
    }

 /* {
    private String path = "/Users/icourt/Downloads/杨鑫辉Alpha案例库检索记录表20220302.xlsx";
    //需要解析的列
    private static int sourceLine = 10;
    //开始解析的行
    private static int startRow = 7;
    //插入的原告列
    private static int line1 = 11;
    //插入的原告企业类型
    private static int line2 = 12;
    //插入的被告列
    private static int line3 = 13;
    //插入的被告企业类型
    private static int line4 = 14;

    private Map<String, String> map = new HashMap<>();

    @GetMapping("/test")
    public void test(int endRow) throws Exception {
        System.out.println("===============开始运行================");
        XSSFWorkbook workbook = new XSSFWorkbook(
                new FileInputStream(path));
        XSSFSheet sheet = workbook.getSheetAt(0);
//        for (int i = startRow; i < sheet.getLastRowNum(); i++) {
        for (int i = startRow; i < endRow; i++) {
            StringBuilder stringBuilder = null;
            XSSFRow row = sheet.getRow(i);
            String val = row.getCell(sourceLine).getStringCellValue();
            System.out.println(val);
            boolean flagStart = false;
            boolean flagMid = false;
            boolean flagEnd = false;
            boolean isYuan = false;
            List<String> yuan = new ArrayList<>();
            List<String> yuanTyoe = new ArrayList<>();
            List<String> bei = new ArrayList<>();
            List<String> beiType = new ArrayList<>();
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
            XSSFCell cell3 = row.createCell(line2);
            cell2.setCellStyle(cellStyle);
            XSSFCell cell4 = row.createCell(line2);
            cell2.setCellStyle(cellStyle);

            if (!yuan.isEmpty()) {
                //cell1.setCellValue(yuan.toString().substring(1, yuan.toString().length() - 1).replaceAll(", ", "\r\n"));
                setCellVal(cell1, yuan);
                yuan.forEach(y -> {
                    if (map.get(y) != null) {
                        beiType.add(y);
                    } else {
                        FuzzyMatchDTO o = (FuzzyMatchDTO) search(new SearchVO() {{
                            setPageNum(1);
                            setSourceType(1);
                            setThirdQueryId(y);
                        }}).getData().getItems().get(0);

                        if (!Objects.isNull(o)) {
                            BaseinfoDTO data = baseInfo(new SearchVO() {{
                                setEid(bindThirdId(o).getData());
                                setCompanyType(Integer.valueOf(o.getMergeSourceDTOList().get(0).getCompanyType()));
                                setSkipCache(false);
                                setSourceType(1);
                            }}).getData();
                            yuanTyoe.add(data.getCompanyOrgType() == null ? (data.getHkEnterpriseBaseInfo().getCompanyOrgType() == null
                                    ? " " : data.getHkEnterpriseBaseInfo().getCompanyOrgType()) : data.getCompanyOrgType());
                        }
                    }
                });
                setCellVal(cell2, yuanTyoe);
                //cell2.setCellValue(yuan.toString().substring(1, yuan.toString().length() - 1).replaceAll(", ", "\r\n"));
            }
            if (!bei.isEmpty()) {
                //cell3.setCellValue(bei.toString().substring(1, bei.toString().length() - 1).replaceAll(", ", "\r\n"));
                setCellVal(cell3, bei);
                bei.forEach(y -> {
                    if (map.get(y) != null) {
                        beiType.add(y);
                    } else {
                        FuzzyMatchDTO o = (FuzzyMatchDTO) search(new SearchVO() {{
                            setPageNum(1);
                            setSourceType(1);
                            setThirdQueryId(y);
                        }}).getData().getItems().get(0);

                        if (!Objects.isNull(o)) {
                            BaseinfoDTO data = baseInfo(new SearchVO() {{
                                setEid(bindThirdId(o).getData());
                                setCompanyType(Integer.valueOf(o.getMergeSourceDTOList().get(0).getCompanyType()));
                                setSkipCache(false);
                                setSourceType(1);
                            }}).getData();
                            beiType.add(data.getCompanyOrgType() == null ? (data.getHkEnterpriseBaseInfo().getCompanyOrgType() == null
                                    ? " " : data.getHkEnterpriseBaseInfo().getCompanyOrgType()) : data.getCompanyOrgType());
                        }
                    }

                });
                setCellVal(cell4, beiType);
            }
            workbook.write(new FileOutputStream(path));
        }
    }


    public void setCellVal(XSSFCell cell, List list) {
        cell.setCellValue(list.toString().substring(1, list.toString().length() - 1).replaceAll(", ", "\r\n"));
    }
}*/
}
