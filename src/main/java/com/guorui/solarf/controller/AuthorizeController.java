package com.guorui.solarf.controller;

import com.guorui.solarf.mapper.UserMapper;
import com.guorui.solarf.model.User;
import com.guorui.solarf.model.dto.AccessTokenDTO;
import com.guorui.solarf.model.dto.GithubUser;
import com.guorui.solarf.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: GuoRUi
 * @Date: 2019/10/25 下午2:58
 * @Version 1.0
 */
@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;

    @Autowired
    UserMapper userMapper;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.client_secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                .client_id(client_id)
                .client_secret(client_secret)
                .code(code)
                .redirect_uri(redirect_uri)
                .state(state).build();

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser);

        if (githubUser != null) {
            //登录成功

            String token = UUID.randomUUID().toString();
            User user = new User();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());

            userMapper.insert(user);

            response.addCookie(new Cookie("token", token));

            request.getSession().setAttribute("user", githubUser);

            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }

    }

    public String getClient_id() {
        return client_id;
    }


}
