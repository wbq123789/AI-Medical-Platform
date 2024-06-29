/*
 * Copyright (c) wbq 2023.
 */

package org.platform_fisco.cotroller.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.platform_fisco.entity.RestBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ValidationController {
    /**
     * @Description: 异常请求返回接口
     * @Param: [exception]
     * @return: booklibrary.library_backend.entity.RestBean<java.lang.Void>
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @ExceptionHandler(ValidationException.class)
    public RestBean<Void> ValidateException(ValidationException exception) {
        log.warn("Resolve [{}: {}]", exception.getClass().getName(), exception.getMessage());
        return RestBean.failure(400, "请求参数错误");
    }
}
