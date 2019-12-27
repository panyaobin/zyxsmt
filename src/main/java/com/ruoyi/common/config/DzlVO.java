package com.ruoyi.common.config;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DzlVO implements Serializable {
    private Integer dzlId;
    private String dzlName;
    private String dzlType;
}
