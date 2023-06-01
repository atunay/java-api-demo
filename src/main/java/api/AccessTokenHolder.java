package api;

public class AccessTokenHolder {
    public static String accessToken;


    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String token) {
        accessToken = token;
    }
}
