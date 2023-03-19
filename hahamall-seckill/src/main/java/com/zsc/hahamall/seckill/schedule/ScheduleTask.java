package com.zsc.hahamall.seckill.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: xiong.qiang
 * @date: 2023/3/2 15:33
 */


/**
 * 如果类被@Lazy修饰，可以让SpringBoot在启动时没有实例化，那么定时任务就不会开始执行。
 */
@Lazy
@Component
public class ScheduleTask {
    public static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);


    //每隔10秒执行一次
    @Scheduled(cron = "*/1 * * * * ?")
    public void printLog(){
        LOGGER.warn("执行定时任务");
    }

}
