package api;

import helpers.JsonParser;
import helpers.ReadConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;


public class PostRequestSignUp {
private static final String SignUpURL = "http://restapi.adequateshop.com/api/authaccount/registration";
//private static String SignUpURL;
private static String responseCode;
private static String responseBody;
private static String accessToken;
private static String authMessage;

 public static void main (String[] args) {
     /*String name = "tuni";
     String email = "a.tunay+8@gmail.com";
     String password = "123456";*/

     ReadConfig readConfig = new ReadConfig();
     readConfig.readConfigFile();


     String qaEnv = readConfig.getQaEnv();
     String email = readConfig.getUsername();
     String password = readConfig.getPassword();
     String name = readConfig.getName();
     //SignUpURL = (qaEnv + "/authaccount/registration"); //ако е локална променлива,.теста не минава. Адреса е null???
     //SignUpURL = (readConfig.getBaseUrl() + "/api/authaccount/registration");

     try {
         signUp(name, email, password);
     } catch (IOException e) {
         throw new RuntimeException(e);
     }
     printAccessToken();
 }
    public static void signUp(String name, String email, String password) throws IOException {
        // Build the post request
        String postBody = "{\"name\":\"" + name + "\", " + "\"password\":\"" + password + "\", " + "\"email\":\"" + email + "\"}";
        HttpPost postSignUp = new HttpPost(SignUpURL);
        postSignUp.setEntity(new StringEntity(postBody));
        postSignUp.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        // Execute the post request
        HttpResponse response = httpClient.execute(postSignUp);
        responseCode = response.getStatusLine().toString();
        // Fill in the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }
        // Extract and set the access token
        if (responseCode.contains("200") == true) {
            JsonParser json = new JsonParser();
            String authCode = json.getResponseCode(responseBody);
            authMessage = json.getAuthMessage(responseBody);
            if (authCode.equals("0")) {
                accessToken = json.getAccessToken(responseBody);
            }
        }
    }

    public static String getAccessToken() {

        return accessToken;
    }

    public static String getResponseCode() {

        return responseCode;
    }

    public static String getResponseBody() {

        return responseBody;
    }

    public static String getLoginMessage() {

        return authMessage;
    }


    public static void printAccessToken() {
        System.out.println(responseCode);
        System.out.println(authMessage);
        System.out.println(responseBody);
        System.out.println(accessToken);
    }
}
