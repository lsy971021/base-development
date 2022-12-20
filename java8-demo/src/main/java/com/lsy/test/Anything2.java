package com.lsy.test;

import com.lsy.entity.ScriptBO;
import com.lsy.enums.ScriptTypeEnum;
import com.lsy.utils.ScriptUtils;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Anything2 {

    @Test
    public void test(){
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("file.txt");

        Map<String, String> getenv = System.getenv();
        Map properties = System.getProperties();
        System.out.println(getenv);
        System.out.println(properties);
    }

    @Test
    public void test2() throws Exception {
        String content = "#!/usr/bin/python\n" +
                "# -*- coding: UTF-8 -*-\n" +
                "import time\n" +
                "import sys\n" +
                "print \"xxl-job: hello python\"\n" +
                "print \"脚本位置：\", sys.argv[0]\n" +
                "print \"任务参数：\", sys.argv[1]\n" +
                "print \"分片序号：\", sys.argv[2]\n" +
                "print \"分片总数：\", sys.argv[3]\n" +
                "\n" +
                "print \"Good bye!\"\n" +
                "print \"刘思远！！！\"\n" +
                "exit(0)\n";
        ScriptBO scriptBO = new ScriptBO();
        scriptBO.setRealPath("/Users/far/Downloads/python".concat(ScriptTypeEnum.PYTHON.getSuffix()));
        scriptBO.setScriptType(ScriptTypeEnum.PYTHON);
        scriptBO.setScriptContent(content);
        List<String> params = new ArrayList<>();
        params.add("param");
        params.add("1");
        params.add("10");

        scriptBO.setParams(params);
        String s = ScriptUtils.execScript(scriptBO);
        System.out.println(s);
    }
}
