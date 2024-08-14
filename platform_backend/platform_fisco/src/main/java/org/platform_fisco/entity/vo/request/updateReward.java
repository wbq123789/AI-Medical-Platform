package org.platform_fisco.entity.vo.request;

import lombok.Data;

/**
 * @program: AI-Medical-Platform
 * @description:
 * @author: 王贝强
 * @create: 2024-08-09 21:48
 */
@Data
public class updateReward {
    String Agency_id;
    String File_id;
    int Round;
    int Reward;
}
