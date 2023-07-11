package helpers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


    public class ReadConfig {

    private String baseUrl; //new
    private String qaEnv;//new
    private String username;//new
    private String password;//new
        private String name;//new


    public ReadConfig() {
    }
    public void readConfigFile() {
        try {
            //*absolute path used on local device only.
            //File myObj = new File("/home/tunay/java-api-demo/src/main/java/config/config.json");

            //*relative path used on many devices.
            String sysPath = System.getProperty("user.dir");
            System.out.println(sysPath);
            String sourceFile = (sysPath + "/src/main/java/config/config.json");
            File myObj = new File(sourceFile);
            Scanner myReader = new Scanner(myObj);
            StringBuilder jsonContent = new StringBuilder(); //new
            //int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                jsonContent.append(data); //new
               // i = i + 1;
                //System.out.println(i + " " + data);
                System.out.println(data);
            }
            myReader.close();
            ObjectMapper objectMapper = new ObjectMapper();//new
            ConfigData configData = objectMapper.readValue(jsonContent.toString(), ConfigData.class);//new
            this.baseUrl = configData.getBaseUrl();//new
            this.qaEnv = configData.getQaEnv();//new
            this.username = configData.getUsername();//new
            this.password = configData.getPassword();//new
            this.name = configData.getName();//new
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("An error occurred while parsing the config file.");
            e.printStackTrace(); //new
        }
    }
    // all is new
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

    /*private static class ConfigData {
        private String baseUrl;
        @JsonProperty("QA-env")
        private String qaEnv;
        private String username;
        private String password;

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
    }*/
}
