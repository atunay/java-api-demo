package api;


import helpers.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import tests.RegistrationAndLogin;


import java.io.IOException;
import java.io.InputStream;

public class GetRequestsByID {

    public static String urlString = ("http://restapi.adequateshop.com/api/users/"); //"http://restapi.adequateshop.com/api/users/" + "234178";

    public static String accessToken = "ec97b1c2-0d4c-429c-993c-96f6b478d39d";
    private static String responseBody;
    private static String responseCode;
    private static final String ID = "238044";


    public static void main(String[] args) throws IOException {
        HttpGet getUsers = new HttpGet(urlString + ID);
        getUsers.setHeader("Content-type", "application/json");
        getUsers.setHeader("Authorization", "Bearer " + accessToken);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getUsers);
        responseCode = response.getStatusLine().toString();

        // Parse the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }


        System.out.println("responseCode: " + responseCode);
        System.out.println("responseBody: " + responseBody);
        System.out.println("accessToken: " + accessToken);
        System.out.println("ID: " + ID);
    }
}
