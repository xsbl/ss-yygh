package com.sisheng.yygh.cmn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author bobochang
 * @description
 * @created 2022/7/1-14:51
 **/
//@SpringBootApplication(exclude = MongoAutoConfiguration.class)
//@ComponentScan(basePackages = "com.sisheng")
//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.sisheng.yygh.cmn.mapper")
@ComponentScan("com.sisheng.yygh")
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }
}
