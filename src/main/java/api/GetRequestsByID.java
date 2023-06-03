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

    public static String urlString = ("http://restapi.adequateshop.com/api/users/"); //"http://restapi.adequateshop.com/api/users/" + "234178";

    private static String accessToken; // = PostRequestsLogIn.getAccessToken();
    private static String responseBody;
    private static String responseCode;
    private static String ID;


    public static void main(String[] args) throws IOException {
        ID = "234178";
        accessToken = "01dd701f-3737-4dce-afb6-519d8f03eb65";
        //accessToken = PostRequestsLogIn.getAccessToken(); //01dd701f-3737-4dce-afb6-519d8f03eb65


        // String urlString = "http://restapi.adequateshop.com/api/users/234178"; //"http://restapi.adequateshop.com/api/users/" + "234178";
        HttpGet getUsers = new HttpGet(urlString + ID);
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
        System.out.println("responseCode: " + responseCode);
        System.out.println("responseBody: " + responseBody);
        System.out.println("accessToken: " + accessToken);
        System.out.println("ID: " + ID);
    }
}
