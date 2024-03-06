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
        /*
       1 Solicitud de autorización (Authorization Request):
        Esta función construye y devuelve la URL de redirección para que el cliente solicite la autorización
        del usuario en Discord.
        Incluye parámetros como el client_id, redirect_uri, response_type y scope, necesarios para solicitar la autorización.
         */
        URIBuilder uri = new URIBuilder("https://discord.com/api/oauth2/authorize");
        uri.addParameter("client_id", clientidDiscord);
        uri.addParameter("redirect_uri", redirecturiDiscord);
        uri.addParameter("response_type", "code");
        uri.addParameter("scope", "identify email");
        return uri.build().toURL().toString();
    }

    public String getDiscordUserEmail(String code) throws Exception {
        /*
        4- Solicitud de token de acceso (Access Token Request):
        Esta función realiza una solicitud POST al endpoint de Discord para intercambiar el código de autorización (code)
        por un token de acceso. Los parámetros necesarios se pasan en el cuerpo de la solicitud,
        incluyendo el client_id, client_secret, code, redirect_uri, y grant_type.
         */
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



    private String doPostDiscord(URL url, Map<String, String> parameters) throws Exception {
        /*
        5 .Recepción del token de acceso (Access Token Response):
        La recepción del token de acceso ocurre implícitamente en el método doPostDiscord() cuando se recibe una respuesta
        exitosa del servidor de autorización. El token de acceso se extrae de la respuesta y se utiliza para
        acceder a los recursos protegidos.
         */
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

    private String doGetDiscord(URL url, String accessToken) throws Exception {
    /*
    6 - Acceso a los recursos protegidos (Access Protected Resources):
    */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url.toString());
        get.setHeader("Authorization", "Bearer " + accessToken);

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity());
        }
        throw new RuntimeException("Error in response: GET");
    }
}
