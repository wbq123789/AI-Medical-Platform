package org.platform_show.service.impl;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.platform_show.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @program: forum
 * @description: 文件上传相关接口实现类
 * @author: 王贝强
 * @create: 2024-06-10 12:42
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${minio.bucket-name}")
    String bucketName;
    @Resource
    MinioClient minioClient;
    private final SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");
    @Override
    public String uploadModel(InputStream file) throws IOException {
        String modelName= "/model/"+UUID.randomUUID().toString().replace("-","");
        PutObjectArgs objectArgs= PutObjectArgs.builder()
                .bucket(bucketName)
                .stream(file, file.available(), -1)
                .object(modelName)
                .build();
        try {
            minioClient.putObject(objectArgs);
            return modelName;
        }catch (Exception e){
            log.error("模型文件上传失败:{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void fetchModel(OutputStream outputStream, String modelPath) throws Exception {
        GetObjectArgs args= GetObjectArgs.builder()
                .bucket(bucketName)
                .object(modelPath)
                .build();
        GetObjectResponse object = minioClient.getObject(args);
        IOUtils.copy(object,outputStream);
    }
}
