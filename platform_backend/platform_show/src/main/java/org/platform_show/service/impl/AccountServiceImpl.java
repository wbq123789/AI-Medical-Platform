package org.platform_show.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.platform_show.entity.dto.Account;
import org.platform_show.mapper.AccountMapper;
import org.platform_show.service.AccountService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 账户信息处理相关服务
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    /**
     * 通过用户名或邮件地址查找用户
     * @param text 用户名或邮件
     * @return 账户实体
     */
    public Account findAccountByName(String text){
        return this.query()
                .eq("username", text)
                .one();
    }

    /**
     * 通过用户ID查找用户
     * @param id 用户ID
     * @return 账户实体
     */
    @Override
    public Account findAccountById(int id) {
        return this.query().eq("id", id).one();
    }

    /**
     * 从数据库中通过用户名或邮箱查找用户详细信息
     * @param username 用户名
     * @return 用户详细信息
     * @throws UsernameNotFoundException 如果用户未找到则抛出此异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByName(username);
        if(account == null)
            throw new UsernameNotFoundException("用户名或密码错误");
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

}
