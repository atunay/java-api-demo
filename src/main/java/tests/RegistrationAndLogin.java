package tests;

import api.PostRequestsLogIn;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {

    private static String email;
    private static String password;

    @BeforeTest
    public static void credentials() {
        email = "a.tunay+2@gmail.com"; //a.tunay+1@gmail.com
        password = "123456";
    }

    @Test
    public static void testSuccessfulLogin() throws IOException {
        PostRequestsLogIn postRequests = new PostRequestsLogIn();
        postRequests.login(email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertTrue(responseBody.contains("0"));
        Assert.assertTrue(responseBody.contains("234178")); //ID:234177
        Assert.assertTrue(responseBody.contains("tuni"));
        //String accessToken = postRequests.getAccessToken();
        //Assert.assertTrue(responseBody.contains(accessToken));
    }

    @Test
    public static void testWrongPassword() throws IOException {
        PostRequestsLogIn postRequests = new PostRequestsLogIn();
        postRequests.login(email, "123450");
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertTrue(responseBody.contains("1"));
    }

    @Test
    public static void testWrongUsername() throws IOException {
        PostRequestsLogIn postRequests = new PostRequestsLogIn();
        postRequests.login("test@test.com", password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertFalse(responseBody.contains("0"));
    }

}
