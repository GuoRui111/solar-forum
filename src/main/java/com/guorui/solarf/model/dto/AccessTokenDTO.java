package com.guorui.solarf.model.dto;


import lombok.Builder;
import lombok.Data;

/**
 * @Author: GuoRUi
 * @Date: 2019/10/25 下午3:04
 * @Version 1.0
 */
@Data
@Builder
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
