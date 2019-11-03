package com.guorui.solarf.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: GuoRUi
 * @Date: 2019/10/25 下午3:27
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
}
