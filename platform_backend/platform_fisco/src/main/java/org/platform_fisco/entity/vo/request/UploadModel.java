package org.platform_fisco.entity.vo.request;

import lombok.Data;

/**
 * @program: AI-Medical-Platform
 * @description: 文件上传VO
 * @author: 王贝强
 * @create: 2024-08-02 18:02
 */
@Data
public class UploadModel {
    String group_id;
    String agency_id;
    String file_id;
    String file_param;
    String file_content;
    int reward;
    int round;
}
