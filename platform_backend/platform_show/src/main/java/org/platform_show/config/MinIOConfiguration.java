package org.platform_show.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: forum
 * @description: MinIO配置类
 * @author: 王贝强
 * @create: 2024-06-10 16:23
 */
@Slf4j
@Configuration
public class MinIOConfiguration {
    @Value("${minio.endpoint}")
    private String endpoint; //minIO地址

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient getMinioClient() {
        log.info("Init MinIO Client...");
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
