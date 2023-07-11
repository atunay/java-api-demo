package helpers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigData {
    private String baseUrl;
    @JsonProperty("QA-env")
    private String qaEnv;
    private String username;
    private String password;
    private String name;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getQaEnv() {
        return qaEnv;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
}
