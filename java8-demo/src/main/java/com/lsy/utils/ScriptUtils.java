package com.lsy.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.lsy.entity.ScriptBO;
import com.lsy.enums.ScriptTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScriptUtils {

    /**
     * 执行脚本，获取结果
     * 环境：python等
     * 权限: rwx
     *
     * @param script 脚本临时存放位置
     * @return 脚本执行结果
     * @throws Exception
     */
    public static String execScript(ScriptBO script) throws Exception {
        if (Objects.isNull(script)) {
            return null;
        }
        ScriptTypeEnum scriptType = script.getScriptType();
        if (Objects.isNull(scriptType) || !scriptType.isScript()) {
            return null;
        }

        String realPath = script.getRealPath();
        String scriptContent = script.getScriptContent();

        if (StringUtils.isAnyBlank(realPath, scriptContent))
            return null;
        List<String> params = script.getParams();
        File scriptFile = new File(realPath);
        if (!scriptFile.exists()) {
            // 创建临时 脚本文件
            markScriptFile(realPath, scriptContent);
        } else {
            scriptFile.delete();
        }
        List<String> cmdarray = new ArrayList<>();
        cmdarray.add(scriptType.getCmd());
        cmdarray.add(realPath);
        if (CollectionUtil.isNotEmpty(params)) {
            cmdarray.addAll(params);
        }
        String[] cmdarrayFinal = cmdarray.toArray(new String[cmdarray.size()]);
        // 执行脚本
        final Process process = Runtime.getRuntime().exec(cmdarrayFinal);

        // 读取脚本执行结果

        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = process.getInputStream()) {
            //用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            //用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } finally {
            scriptFile.delete();
        }
        return stringBuilder.toString();
    }


    /**
     * 创建脚本到指定位置
     *
     * @param scriptFileName 脚本绝对路径
     * @param content        脚本内容
     * @throws IOException
     */
    public static void markScriptFile(String scriptFileName, String content) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(scriptFileName)) {
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
        }
    }
}
