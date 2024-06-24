package org.platform_show.utils;

import org.platform_show.entity.RestBean;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @program: forum
 * @description: 控制器工具类
 * @author: 王贝强
 * @create: 2024-06-12 17:31
 */
@Component
public class ControllerUtils {
    public <T> RestBean<T> messageHandle(Supplier<String> action){
        String message = action.get();
        if(message == null)
            return RestBean.success();
        else
            return RestBean.failure(400, message);
    }
}
