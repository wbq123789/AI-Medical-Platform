package org.platform_fisco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Ll
 * @description: TODO
 * @date 2024/4/21 下午2:28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class transaction {
    String agency_id;
    String round;
    String reward;
    String time;
}