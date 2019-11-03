package com.guorui.solarf.provider;

import com.alibaba.fastjson.JSON;
import com.guorui.solarf.model.dto.AccessTokenDTO;
import com.guorui.solarf.model.user.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: GuoRUi
 * @Date: 2019/10/25 下午3:02
 * @Version 1.0
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String token = response.body().string();
            return token;
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);

            return githubUser;
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
