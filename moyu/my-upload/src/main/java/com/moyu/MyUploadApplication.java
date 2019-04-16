package com.moyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author JL
 * @date 19-4-16 下午3:28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MyUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyUploadApplication.class);
    }
}
