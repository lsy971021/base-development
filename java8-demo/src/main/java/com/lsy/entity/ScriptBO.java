package com.lsy.entity;

import com.lsy.enums.ScriptTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class ScriptBO {
    /**
     * 脚本生成路径
     */
    private String realPath;

    /**
     * 脚本内容
     */
    private String scriptContent;

    /**
     * 脚本参数
     */
    private List<String> params;

    /**
     * 脚本类型
     */
    private ScriptTypeEnum scriptType;
}
