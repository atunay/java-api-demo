package api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.io.InputStream;

public class GetRequests {

    private static String urlString = "http://restapi.adequateshop.com/api/users?page=10";
    private static String accessToken;
    private static String responseCode;
    private static String responseBody;


    public static void main(String[] args) throws IOException {
        //accessToken = "335ef78f-0b21-4dcf-89f8-03928eb2d0a7";
        HttpGet getUsers = new HttpGet(urlString);
        getUsers.setHeader("Content-type", "application/json");
        getUsers.setHeader("Authorization", "Bearer " + "335ef78f-0b21-4dcf-89f8-03928eb2d0a7");
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(getUsers);
        responseCode = response.getStatusLine().toString();


        //Parse the response body
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }

        System.out.println(responseCode);
        System.out.println(responseBody);
        System.out.println(accessToken);

    }
    public static String getAccessToken() {

        return accessToken;
    }


}
