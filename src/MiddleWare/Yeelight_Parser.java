package MiddleWare;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Yeelight_Parser {
    String falut = "wrong date";
    String result;
    public String parser(JSONObject input){
        if (input.get("method").equals("set_bright")){
            return bright_parser(input);
        } else if (input.get("method").equals("set_ct_abx")){
            return ct_parser(input);
        } else if (input.get("method").equals("set_hsv")){
            return hsv_parser(input);
        } else{
            return falut;
        }
    }
    public String bright_parser(JSONObject input){
        List param = new ArrayList<>();
        param = (List) input.get("params");
        result = "Bright : " + param.get(0).toString() + "% changed";
        return result;
    }
    public String ct_parser(JSONObject input){
        List param = new ArrayList<>();
        param = (List) input.get("params");
        result = "Color temperature : " + param.get(0).toString() + " changed";
        return result;
    }
    public String hsv_parser(JSONObject input){
        List param = new ArrayList<>();
        param = (List) input.get("params");
        java.lang.Long value = (Long) param.get(0);
        if (value <= 6) {
            result = "Color : change Red";
        } else if (7 <= (value) && (value) <= 32 ) {
            result = "Color : change Orange";
        } else if (33 <= value && value <= 56){
            result = "Color : change Yellow";
        } else if (57<= value && value <= 97) {
            result = "Color : change Fluorescent";
        } else if (98 <= value && value <= 153) {
            result = "Color : change Green";
        } else if (154 <= value && value <= 206) {
            result = "Color : change Blue";
        } else if (207 <= value && value <= 260) {
            result = "Color : change Indigo";
        } else if (261 <= value && value <= 285) {
            result = "Color : change Purple";
        } else if (286 <= value && value <= 333) {
            result = "Color : change Pink";
        } else if (334 <=  value && value <= 360) {
            result = "Color : change Graoefruit";
        }
        return result;
    }
}
