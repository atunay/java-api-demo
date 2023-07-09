package api;

import static api.PostRequestsLogIn.*;

public class IDHolder {
    public static String ID;

    public static void setID(String id) {

        ID = id;
    }
    public static String getID() {
        return ID;
    }

    public static void main(String[] args) {

        System.out.println(ID);
    }
}
