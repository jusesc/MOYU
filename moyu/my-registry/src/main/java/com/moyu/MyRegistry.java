package com.moyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author JL
 * @date 19-4-7 下午4:15
 */
@EnableEurekaServer
@SpringBootApplication
public class MyRegistry {
    public static void main(String[] args) {
        SpringApplication.run(MyRegistry.class);
    }
}
