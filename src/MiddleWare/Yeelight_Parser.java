package MiddleWare;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Yeelight_Parser {
    String falut = "wrong date";
    String result;
    public String parser(JSONObject input){
        if (input.get("method") == "set_bright"){
            return bright_parser(input);
        } else if (input.get("method") == "set_ct_abx"){
            return ct_parser(input);
        } else if (input.get("method") == "set_hsv"){
            return hsv_parser(input);
        } else{
            return falut;
        }
    }
    public String bright_parser(JSONObject input){
        List param = new ArrayList<>();
        param = (List) input.get("params");
        result = "밝기 : " + param.get(0).toString() + "변경";
        return result;
    }
    public String ct_parser(JSONObject input){
        List param = new ArrayList<>();
        param = (List) input.get("params");
        result = "색온도 : " + param.get(0).toString() + "변경";
        return result;
    }
    public String hsv_parser(JSONObject input){
        List param = new ArrayList<>();
        param = (List) input.get("params");
        String asd = (String) param.get(0);
        int value = Integer.parseInt(asd);
        if (value < 7) {
            result = "색상 : 빨강색으로 변경";
        } else if (6 < (value) && (value) < 32 ) {
            result = "색상 : 주황색으로 변경";
        }
    }
}
