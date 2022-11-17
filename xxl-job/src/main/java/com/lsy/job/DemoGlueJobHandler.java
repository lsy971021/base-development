package com.lsy.job;

import com.lsy.mapper.XxlJobInfoMapper;
import com.lsy.model.XxlJobInfo;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoGlueJobHandler extends IJobHandler {

    @Autowired
    private XxlJobInfoMapper xxlJobInfoMapper;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.selectById(2);
        XxlJobLogger.log("=======" + xxlJobInfo.toString());
        ReturnT<String> returnT = new ReturnT<>();
        returnT.setContent(111111 + "");
        return returnT;
    }

}
