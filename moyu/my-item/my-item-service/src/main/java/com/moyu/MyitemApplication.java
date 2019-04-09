package com.moyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author JL
 * @date 19-4-7 下午5:10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MyitemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyitemApplication.class);
    }
}
