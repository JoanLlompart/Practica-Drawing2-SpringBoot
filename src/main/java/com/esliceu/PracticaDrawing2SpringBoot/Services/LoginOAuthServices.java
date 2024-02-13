package com.esliceu.PracticaDrawing2SpringBoot.Services;

import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginOAuthServices {
    @Value("${client-idDiscord}")
    String clientidDiscord;
    @Value("${redirect-uriDiscord}")
    String redirecturiDiscord;

    @Value("${client-secretDiscord}")
    String clientSecretDiscord;

    public String getDiscordRedirection() throws Exception {
        URIBuilder uri = new URIBuilder("https://discord.com/api/oauth2/authorize");
        uri.addParameter("client_id", clientidDiscord);
        uri.addParameter("redirect_uri", redirecturiDiscord);
        uri.addParameter("response_type", "code");
        uri.addParameter("scope", "identify email");
        return uri.build().toURL().toString();
    }

    public String getDiscordUserEmail(String code) throws Exception {
        URL uri = new URL("https://discord.com/api/oauth2/token");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", clientidDiscord);
        parameters.put("client_secret", clientSecretDiscord);
        parameters.put("code", code);
        parameters.put("redirect_uri", redirecturiDiscord);
        parameters.put("grant_type", "authorization_code");

        String res = doPostDiscord(uri, parameters);
        Map<String, String> mapJson = new Gson().fromJson(res, HashMap.class);
        String access_token = mapJson.get("access_token");

        URIBuilder uri2 = new URIBuilder("https://discord.com/api/users/@me");
        String response = doGetDiscord(uri2.build().toURL(), access_token);
        Map<String, String> mapJson2 = new Gson().fromJson(response, HashMap.class);
        return mapJson2.get("email");
    }

    private String doGetDiscord(URL url, String accessToken) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url.toString());
        get.setHeader("Authorization", "Bearer " + accessToken);

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity());
        }
        throw new RuntimeException("Error in response: GET");
    }

    private String doPostDiscord(URL url, Map<String, String> parameters) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url.toString());
        List<NameValuePair> nvps = new ArrayList<>();
        for (String s : parameters.keySet()) {
            nvps.add(new BasicNameValuePair(s, parameters.get(s)));
        }
        post.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println(response);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity());
        }
        throw new RuntimeException("Error in response");
    }

    /*
    public String getDiscordUserName(String code) throws Exception{
        // Este método es similar al getDiscordUserEmail, pero en vez de devolver el email, devuelve el username
        URL uri = new URL("https://discord.com/api/oauth2/token");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", clientidDiscord);
        parameters.put("client_secret", clientSecretDiscord);
        parameters.put("code", code);
        parameters.put("redirect_uri", redirecturiDiscord);
        parameters.put("grant_type", "authorization_code");

        String res = doPostDiscord(uri, parameters);
        Map<String, String> mapJson = new Gson().fromJson(res, HashMap.class);
        String access_token = mapJson.get("access_token");

        URIBuilder uri2 = new URIBuilder("https://discord.com/api/users/@me");
        String response = doGetDiscord(uri2.build().toURL(), access_token);
        Map<String, String> mapJson2 = new Gson().fromJson(response, HashMap.class);
        return mapJson2.get("username");
    }

     */
}
