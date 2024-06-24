package org.platform_show.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.platform_show.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByName(String text);
    Account findAccountById(int id);
}
