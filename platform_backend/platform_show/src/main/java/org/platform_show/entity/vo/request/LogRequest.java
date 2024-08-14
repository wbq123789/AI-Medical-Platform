package org.platform_show.entity.vo.request;

import lombok.Data;

/**
 * @program: AI-Medical-Platform
 * @description:
 * @author: 王贝强
 * @create: 2024-08-04 19:12
 */
@Data
public class LogRequest {
    private String file_path;
    private Integer start_round_number= -1;
    private Integer end_round_number=-1;// 默认为0
}