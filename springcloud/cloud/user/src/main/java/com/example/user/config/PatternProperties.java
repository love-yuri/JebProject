package com.example.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component // 注册为spring bean
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    private String dateFormat;
}
