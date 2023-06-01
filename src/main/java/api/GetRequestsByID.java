package api;

import helpers.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

public class GetRequestsByID {

    public static String urlString = "http://restapi.adequateshop.com/api/users/234178"; //"http://restapi.adequateshop.com/api/users/" + "234178";

    private static String accessToken; // = PostRequestsLogIn.getAccessToken();
    private static String responseBody;
    private static String responseCode;


    public static void main(String[] args) throws IOException {
        //accessToken = "20642ea0-50b7-4440-a5aa-59a98890d629";
        //accessToken = PostRequestsLogIn.getAccessToken(); //20642ea0-50b7-4440-a5aa-59a98890d629

        accessToken = AccessTokenHolder.getAccessToken();


       // String urlString = "http://restapi.adequateshop.com/api/users/234178"; //"http://restapi.adequateshop.com/api/users/" + "234178";
        HttpGet getUsers = new HttpGet(urlString);
        getUsers.setHeader("Content-type", "application/json");
        getUsers.setHeader("Authorization", "Bearer " + accessToken);
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
}
