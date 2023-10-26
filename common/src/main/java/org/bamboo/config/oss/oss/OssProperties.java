package org.bamboo.config.oss.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
//@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class OssProperties {
    private String endPoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
}