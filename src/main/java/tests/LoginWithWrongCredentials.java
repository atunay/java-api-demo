package tests;

import api.PostRequestsLogIn;
import helpers.ReadConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginWithWrongCredentials {
    private static String email;
    private static String password;

    @BeforeTest
    public static void credentials() {
        //email = "a.tunay+8@gmail.com";
        //password = "123456";

        ReadConfig readConfig = new ReadConfig();
        readConfig.readConfigFile();
        email = readConfig.getUsername();
        password = readConfig.getPassword();
    }

    @Test
    public static void testWrongPassword() throws IOException {
        PostRequestsLogIn postRequests = new PostRequestsLogIn();
        postRequests.login(email, password );
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertFalse(authMessage.contains("invalid"), authMessage);
        String responseBody = postRequests.getResponseBody();
        Assert.assertTrue(responseBody.contains("1"));
        System.out.println(responseBody);
    }
}
