package org.platform_show.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: forum
 * @description: 文件上传相关接口
 * @author: 王贝强
 * @create: 2024-06-10 12:40
 */

public interface FileService {
    String uploadModel(InputStream file) throws IOException;
    void fetchModel(OutputStream outputStream,String modelPath) throws Exception;
}
