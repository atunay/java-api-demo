package tests;

import api.PostRequestSignUp;
import api.PostRequestsLogIn;
//import api.AccessTokenHolder;
import helpers.ReadConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

//import static api.AccessTokenHolder.getAccessToken;


public class RegistrationAndLogin {

    private static String email;
    private static String password;
    private static String name;
    private static String accessToken;
    //private static String accessToken = "Bearer 9bf089a7-9f68-48d3-99b2-23ba9c66416c";
    private static String responseCode;
    private static String responseBody;
    //protected static String urlString = "http://restapi.adequateshop.com/api/users?page=10";
    //protected static String urlString = "http://restapi.adequateshop.com/api/users/ + 238536;
    protected static String urlString;
    public static String ID;

    @BeforeTest
    public static void credentials() {
        //name = "tuni";
        //email = "a.tunay+8@gmail.com";
        //password = "123456";

        ReadConfig readConfig = new ReadConfig();
        readConfig.readConfigFile();
        email = readConfig.getUsername();
        password = readConfig.getPassword();
        name = readConfig.getName();
        String qaEnv = readConfig.getQaEnv();
        urlString = (qaEnv + "/users/");


    }

    @Test(priority = 1)
    public static void testSuccessfulSignUp() throws IOException {
        PostRequestSignUp postRequests = new PostRequestSignUp();
        postRequests.signUp(name, email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertFalse(authMessage.contains("success"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertFalse(responseBody.contains("0"));
        Assert.assertFalse(responseBody.contains("tuni"));
        System.out.println(responseBody);
    }


    @Test(priority = 2)
    public static void testSuccessfulLogin() throws IOException {
        PostRequestsLogIn postRequests = new PostRequestsLogIn();
        postRequests.login(email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertTrue(responseBody.contains("0"));
        Assert.assertTrue(responseBody.contains("tuni"));
        accessToken = postRequests.getAccessToken();
        System.out.println(responseBody);
    }

    @Test(priority = 3)
    public static void testWrongPassword() throws IOException {
        PostRequestsLogIn postRequests = new PostRequestsLogIn();
        postRequests.login(email, "123450");
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertTrue(responseBody.contains("1"));
        System.out.println(responseBody);
    }

    @Test(priority = 4)
    public static void testWrongUsername() throws IOException {
        PostRequestsLogIn postRequests = new PostRequestsLogIn();
        postRequests.login("test@test.com", password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertFalse(responseBody.contains("0"));
        System.out.println(responseBody);
    }
    @Test(priority = 5)
    public static void testGetUsers() throws IOException {
        PostRequestsLogIn postRequests = new PostRequestsLogIn();
        postRequests.login(email, password);
        HttpGet getUser = new HttpGet(urlString);
        getUser.setHeader("Content-type", "application/json");
        getUser.setHeader("Authorization", "Bearer " + accessToken);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getUser);
        responseCode = response.getStatusLine().toString();

        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertTrue(responseBody.contains("0"));
        Assert.assertTrue(responseBody.contains("241964"));
        Assert.assertTrue(responseBody.contains("tuni"));
        accessToken = postRequests.getAccessToken();

        System.out.println(responseBody);
    }
}
