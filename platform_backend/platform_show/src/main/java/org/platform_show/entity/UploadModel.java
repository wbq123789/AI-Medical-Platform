package org.platform_show.entity;

import lombok.Data;

@Data
public class UploadModel {
    String group_id;
    String agency_id;
    String file_id;
    String file_param;
    String file_content;
    int round;
    int reward;
}