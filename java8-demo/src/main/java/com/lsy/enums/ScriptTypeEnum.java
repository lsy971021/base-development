package com.lsy.enums;


import java.util.Objects;

public enum ScriptTypeEnum {

    GROOVY("Java", false, null, null, 1),
    PYTHON("Python", true, "python", ".py", 2),
    NODEJS("Nodejs", true, "node", ".js", 3),
    PHP("PHP", true, "php", ".php", 4),
    SHELL("Shell", true, "bash", ".sh", 5),
    POWERSHELL("PowerShell", true, "powershell", ".ps1", 6);

    private String desc;
    private boolean isScript;
    private String cmd;
    private String suffix;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    private ScriptTypeEnum(String desc, boolean isScript, String cmd, String suffix, Integer code) {
        this.desc = desc;
        this.isScript = isScript;
        this.cmd = cmd;
        this.suffix = suffix;
    }


    public boolean isScript() {
        return isScript;
    }

    public String getCmd() {
        return cmd;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getDesc() {
        return desc;
    }

    public static ScriptTypeEnum match(Integer code) {
        if (code == null)
            return null;
        for (ScriptTypeEnum item : ScriptTypeEnum.values()) {
            if (Objects.equals(item.code, code)) {
                return item;
            }
        }
        return null;
    }
}
