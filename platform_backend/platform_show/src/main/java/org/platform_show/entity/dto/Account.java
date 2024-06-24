package org.platform_show.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.platform_show.entity.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: forum
 * @description: 用户实体类
 * @author: 王贝强
 * @create: 2024-06-07 20:55
 */
@Data
@TableName("account")
@AllArgsConstructor
public class Account implements BaseData {
    @TableId(type = IdType.AUTO)
    Integer id;
    String username;
    String password;
    String role;
}
