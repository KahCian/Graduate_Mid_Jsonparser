package MiddleWare;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Yeelight_Launcher {
    public static void main(String[] args) throws ParseException {
        String bright_test = "{\"id\":77,\"method\":\"set_bright\",\"params\":[25, \"smooth\", 200]}";
        String ct_test = "{\"id\":78,\"method\":\"set_ct_abx\",\"params\":[4349, \"smooth\", 500]}";
        String hsv_test = "{\"id\":41,\"method\":\"set_hsv\",\"params\":[0, 100, \"smooth\", 200]}";

        org.json.simple.parser.JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(hsv_test);
        Yeelight_Parser test = new Yeelight_Parser();
        System.out.println(test.parser(jsonObj));
    }
}
