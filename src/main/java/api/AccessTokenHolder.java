package api;

public class AccessTokenHolder {
    static String accessToken;
    public static void setAccessToken(String token) {
        accessToken = token;

    }

    public static String getAccessToken() {
        return accessToken;
    }
}
