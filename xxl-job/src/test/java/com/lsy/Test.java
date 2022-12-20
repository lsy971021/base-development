package com.lsy;

import java.io.*;
import java.nio.charset.Charset;

public class Test {

    @org.junit.Test
    public void executePython() throws IOException {
        String[] cmdarrayFinal = new String[]{"python", "/Users/far/Downloads/logs/xxl-job/jobhandler/gluesource/7_1668607122000.py", "00", "9999", "7777"};
        final Process process = Runtime.getRuntime().exec(cmdarrayFinal);
        InputStream inputStream = process.getInputStream();
        //用一个读输出流类去读
        InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("utf-8"));
        //用缓冲器读行
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String result = "";
        //知道读完为止
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
            result = result.concat(line);
        }
        System.out.println(stringBuilder.toString());
        System.out.println(result);
        System.out.println(process);

        File file = new File("/Users/far/Downloads/logs/xxl-job/jobhandler/gluesource/7_1668607122000.py");
        boolean delete = file.delete();
        System.out.println(delete);
    }
}
