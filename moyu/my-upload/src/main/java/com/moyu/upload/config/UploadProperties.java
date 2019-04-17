package com.moyu.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author JL
 * @date 19-4-17 下午2:10
 */
@Data
@ConfigurationProperties(prefix = "my.upload")
public class UploadProperties {
    private String baseUrl;
    private List<String> allowTypes;
}
