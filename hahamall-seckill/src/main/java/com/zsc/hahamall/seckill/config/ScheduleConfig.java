package com.zsc.hahamall.seckill.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by XQ
 * 2022/3/24
 */



/**
 * 想要使用@Scheduled注解， 首先要在配置类上加上注解@EnableScheduleing
 */
@EnableScheduling
@EnableAsync
@Configuration
public class ScheduleConfig {

}

