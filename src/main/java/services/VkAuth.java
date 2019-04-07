package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import models.User;
import models.VkAuthUser;
import org.apache.commons.io.IOUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;


import java.sql.Date;
import java.text.SimpleDateFormat;
@NoArgsConstructor
public class VkAuth {

    private static final String clientId = "6743597";
    private static final String secretKey = "E9fa4jAIm6UxNjes4BU4";
    private static final String redirectUri = "http://localhost:8080/vkAuth";



    @SneakyThrows
    public VkAuthUser getUserToken(String code) {

        HttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder("https://oauth.vk.com/access_token");
        uriBuilder
                .setParameter("client_id", clientId)
                .setParameter("client_secret", secretKey)
                .setParameter("code", code)
                .setParameter("redirect_uri", redirectUri);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        HttpResponse response = httpClient.execute(httpGet);

        String token = IOUtils.toString(response.getEntity().getContent());
        JSONObject array = new JSONObject(token);

        VkAuthUser vkAuthUser = VkAuthUser.builder()
                .accessToken(array.getString("access_token"))
                .expiresIn(array.getInt("expires_in"))
                .userId(array.getInt("user_id"))
                .email(array.getString("email"))
                .build();


        return vkAuthUser;
    }

    @SneakyThrows
    public User getUser(VkAuthUser vkAuthUser) {

        HttpClient httpClient = HttpClients.createDefault();

        URIBuilder uriBuilder = new URIBuilder("https://api.vk.com/method/users.get");
        uriBuilder
                .setParameter("uids", String.valueOf(vkAuthUser.getUserId()))
                .setParameter("fields", "uid,first_name,last_name,screen_name,sex,bdate,photo_big,city,country,")
                .setParameter("access_token", vkAuthUser.getAccessToken())
                .setParameter("v", "5.87")
                .build();
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        HttpResponse response = httpClient.execute(httpGet);
        String responseStr = IOUtils.toString(response.getEntity().getContent());
        JSONArray responseJson = new JSONArray(new JSONObject(responseStr).get("response").toString());
        String userStr = responseJson.toString().substring(1, responseJson.toString().length() - 1);

        JSONObject jsonUser = new JSONObject(userStr);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date birthday = new Date(simpleDateFormat.parse(jsonUser.get("bdate").toString()).getTime());


        User user = User.builder()
                .lastName(jsonUser.getString("last_name"))
                .firstName(jsonUser.getString("first_name"))
                .img(jsonUser.getString("photo_big"))
                .gender(Short.parseShort(jsonUser.get("sex").toString()))
                .birthday(birthday)
                .email(vkAuthUser.getEmail())
                .build();

        if (jsonUser.keySet().contains("city")) {
            user.setCity(jsonUser.getJSONObject("city").getString("title"));
        }
        if (jsonUser.keySet().contains("country")) {
            user.setCountry(jsonUser.getJSONObject("country").getString("title"));
        }

        return user;
    }

}
