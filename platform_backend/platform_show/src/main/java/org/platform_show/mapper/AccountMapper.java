package org.platform_show.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.platform_show.entity.dto.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: forum
 * @description: 用户mapper接口类
 * @create: 2024-06-07 20:55
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
