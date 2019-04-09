package com.moyu.common;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author JL
 * @date 19-4-7 下午4:41
 */
@EnableDiscoveryClient
@SpringCloudApplication
@EnableZuulProxy
public class MyGateway {
    public static void main(String[] args) {
        SpringApplication.run(MyGateway.class);
    }
}
