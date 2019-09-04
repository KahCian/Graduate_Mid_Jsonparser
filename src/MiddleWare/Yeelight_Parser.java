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
        result = "밝기 : " + param.get(0).toString() + "% 변경";
        return result;
    }
    public String ct_parser(JSONObject input){
        List param = new ArrayList<>();
        param = (List) input.get("params");
        result = "색온도 : " + param.get(0).toString() + " 변경";
        return result;
    }
    public String hsv_parser(JSONObject input){
        List param = new ArrayList<>();
        param = (List) input.get("params");
        java.lang.Long value = (Long) param.get(0);
        if (value <= 6) {
            result = "색상 : 빨강색으로 변경";
        } else if (7 <= (value) && (value) <= 32 ) {
            result = "색상 : 주황색으로 변경";
        } else if (33 <= value && value <= 56){
            result = "색상 : 노랑색으로 변경";
        } else if (57<= value && value <= 97) {
            result = "색상 : 형광색으로 변경";
        } else if (98 <= value && value <= 153) {
            result = "색상 : 초록색으로 변경";
        } else if (154 <= value && value <= 206) {
            result = "색상 : 파란색으로 변경";
        } else if (207 <= value && value <= 260) {
            result = "색상 : 보라색으로 변경";
        } else if (261 <= value && value <= 285) {
            result = "색상 : 보라색으로 변경";
        } else if (286 <= value && value <= 333) {
            result = "색상 : 핑크색으로 변경";
        } else if (334 <=  value && value <= 360) {
            result = "색상 : 자몽색으로 변경";
        }
        return result;
    }
}
