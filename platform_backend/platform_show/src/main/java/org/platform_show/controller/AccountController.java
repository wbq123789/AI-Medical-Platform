package org.platform_show.controller;

import org.platform_show.entity.RestBean;
import org.platform_show.entity.dto.Account;
import org.platform_show.entity.vo.response.AccountVO;
import org.platform_show.service.AccountService;
import org.platform_show.utils.Const;
import org.platform_show.utils.ControllerUtils;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @program: forum
 * @description: 用户信息相关接口
 * @author: 王贝强
 * @create: 2024-06-07 20:55
 */
@RestController
@RequestMapping("/api/user")
public class AccountController {
    @Resource
    AccountService accountService;

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/info")
    public RestBean<AccountVO> getAccountInfo(@RequestAttribute(Const.ATTR_USER_ID) Integer userId) {
        Account account = accountService.findAccountById(userId);
        return RestBean.success(account.asViewObject(AccountVO.class));
    }
}
