package org.platform_show.entity.vo.response;

import lombok.Data;

/**
 * @program: AI-Medical-Platform
 * @description:
 * @author: 王贝强
 * @create: 2024-08-10 13:45
 */
@Data
public class Command {
   String logPath;
   String round;
   String globalModelPath;
   String localModelPath;
}
