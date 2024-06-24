package org.platform_show.entity.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * 登录验证成功的用户信息响应
 */
@Data
public class AuthorizeVO {
    String username;
    String role;
    String token;
    Date expire;
}
