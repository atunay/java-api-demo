package tests;

import api.PostRequestsLogIn;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAndGetUsersDetails {
    private static String email;
    private static String password;
    private static String accessToken;
    protected static String urlString = "http://restapi.adequateshop.com/api/users?page=1";

    @BeforeTest
    public static void credentials() {
        email = "a.tunay+8@gmail.com";
        password = "123456";
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

        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertTrue(responseBody.contains("0"));
        Assert.assertTrue(responseBody.contains("241964"));
        Assert.assertTrue(responseBody.contains("tuni"));
        accessToken = postRequests.getAccessToken();

        System.out.println(accessToken);
    }
}
