package com.lsy.job;

import com.lsy.mapper.XxlJobInfoMapper;
import com.lsy.model.XxlJobInfo;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestJob {
    private static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Autowired
    private XxlJobInfoMapper xxlJobInfoMapper;

    /**
     * @XxlJob： 在调度中心新增任务时，JobHandler值需和 value属性值一致 且 运行模式为Bean
     * init: 《初次》执行时先执行 testInit() 方法
     * destroy: 《容器销毁》执行 testDestroy() 方法
     * @param param
     * @return
     */
    @XxlJob(value = "testJobHandler",init = "testInit",destroy = "testDestroy")
    public ReturnT<String> testJobHandler(String param) throws Exception{
        // 必须使用 xxljob 的 logger
        XxlJobLogger.log("start testJobHandler");
        XxlJobInfo xxlJobInfo = xxlJobInfoMapper.selectById(1);
        ReturnT<String> returnT = new ReturnT<>();
        returnT.setContent(xxlJobInfo.toString());
        return returnT;
    }

    public void testInit(){
        System.out.println("开始执行");
    }

    public void testDestroy(){
        System.out.println("执行结束");
    }


    @XxlJob(value = "jobHandler")
    public ReturnT<String> jobHandler(String param){
        System.out.println("demo~");
        XxlJobLogger.log("start jobHandler");

        return ReturnT.SUCCESS;
    }


}
