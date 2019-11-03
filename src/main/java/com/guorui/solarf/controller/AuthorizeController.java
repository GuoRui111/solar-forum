package com.guorui.solarf.controller;

import com.guorui.solarf.model.dto.AccessTokenDTO;
import com.guorui.solarf.model.user.GithubUser;
import com.guorui.solarf.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: GuoRUi
 * @Date: 2019/10/25 下午2:58
 * @Version 1.0
 */
@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.client_secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                .client_id(client_id)
                .client_secret(client_secret)
                .code(code)
                .redirect_uri(redirect_uri)
                .state(state).build();

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());

        return "index";
    }

    public String getClient_id() {
        return client_id;
    }


}
