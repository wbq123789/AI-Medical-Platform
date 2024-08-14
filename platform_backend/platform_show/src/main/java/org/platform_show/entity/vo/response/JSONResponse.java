package org.platform_show.entity.vo.response;

import lombok.Data;

/**
 * @program: AI-Medical-Platform
 * @description:
 * @author: 王贝强
 * @create: 2024-08-04 18:50
 */
@Data
public class JSONResponse {
    private String status;
    private String message;
    private String detail;
}
