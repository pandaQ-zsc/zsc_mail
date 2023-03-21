package com.zsc.hahamall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@EnableFeignClients
// @EnableDiscoveryClient  服务的注册发现
@EnableDiscoveryClient
// 因为common包里面有mybatis-plus 的驱动   不用到数据源，需要排除数据源配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class  HahamallSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(HahamallSearchApplication.class, args);
	}

}
