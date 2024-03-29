package MiddleWare;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Search_Json {
    String result;
    List<String> result_list = new ArrayList<String>();
    List<String> removedevice_list = new ArrayList<String>();
    List<String> rqster_list = new ArrayList<String>();
    List<String> device_footprint = new ArrayList<String>();
    List<String> act_iot = new ArrayList<String>();
    List<List> test_list = new ArrayList<List>();
    List<String> finallist = new ArrayList<String>();
    HashMap<String, List<List>> per_iot = new HashMap<String, List<List>>();
    HashMap<String, List<String>> real_result = new HashMap<String, List<String>>();
    HashMap<Object, List> result_Hash = new HashMap<Object, List>();
    HashMap<Object, List> result_Hash_Hash = new HashMap<Object, List>();

    public List Device_Ip_Port(String input, String which){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            JSONArray actions = (JSONArray) jsonObj.get("actions");
            for (int i = 0; i < actions.size(); i++) {
                JSONObject first = (JSONObject) actions.get(i);
                JSONObject action_trace = (JSONObject) first.get("action_trace");
                JSONObject act = (JSONObject) action_trace.get("act");
                String name = String.valueOf(act.get("name"));
                JSONObject data = (JSONObject) act.get("data");
                String dvice = String.valueOf(data.get("dvice"));
                if (name.equals("attachdevice") && dvice.equals(which)) {
                    String iaddr = String.valueOf(data.get("iaddr"));
                    String port = String.valueOf(data.get("port"));
                    this.result_list.add(iaddr);
                    this.result_list.add(port);
                }
            }
        }catch (ParseException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result_list;
    }

    public List Recent_user_device(String input, String which) {
        List<String> device_list = new ArrayList<String>();
        List<String> user_list = new ArrayList<String>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            JSONArray actions = (JSONArray) jsonObj.get("actions");
            for (int i = 0; i < actions.size(); i++) {
                JSONObject first = (JSONObject) actions.get(i);
                JSONObject action_trace = (JSONObject) first.get("action_trace");
                JSONObject act = (JSONObject) action_trace.get("act");
                String name = String.valueOf(act.get("name"));
                JSONObject data = (JSONObject) act.get("data");
                if (which.equals("recentuser")) {
                    if (name.equals("adduser")) {
                        String wantuser = (String) data.get("wantuser");
                        user_list.add(wantuser);
                    }
                    else if (name.equals("removeuser")) {
                        String user = (String) data.get("user");
                        user_list.remove(user);
                    }
                    result_list = user_list;
                } else if (which.equals("recentdevice")) {
                    if (name.equals("attachdevice")) {
                        String dvice = (String) data.get("dvice");
                        device_list.add(dvice);
                    } else if (name.equals("removedevice")) {
                        String dvice = (String) data.get("dvice");
                        device_list.remove(dvice);
                    }
                    result_list = device_list;
                }
            }
//            List<String> adddevice_list = new ArrayList<String>();
//            adddevice_list = Parsing_Response(input, "attachdevice");
//            HashSet<String> distinctData1 = new HashSet<String>(removedevice_list);
//            removedevice_list = new ArrayList<String>(distinctData1);
//            HashSet<String> distinctData2 = new HashSet<String>(adddevice_list);
//            adddevice_list = new ArrayList<String>(distinctData2);
//            adddevice_list.removeAll(removedevice_list);
//            result_list = adddevice_list;
        } catch (Exception e){
            System.out.println(e);
        }
        return result_list;
    }

    public List Parsing_Response(String input, String which){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            JSONArray actions = (JSONArray) jsonObj.get("actions");
            for (int i = 0; i < actions.size(); i++) {
                JSONObject first = (JSONObject) actions.get(i);
                JSONObject action_trace = (JSONObject) first.get("action_trace");
                JSONObject act = (JSONObject) action_trace.get("act");
                String name = String.valueOf(act.get("name"));
                JSONObject data = (JSONObject) act.get("data");
                String rqster = String.valueOf(data.get("rqster"));
                if (name.equals("attachdevice") && which.equals("attachdevice")) {
                    String dvice = String.valueOf(data.get("dvice"));
                    this.result_list.add(dvice);
                } else if (name.equals("pushdata") && which.equals("pushdata")) {
                    String targetdevice = String.valueOf(data.get("targetdevice"));
                    String push_data = String.valueOf(data.get("data"));
                    String block_time = String.valueOf(first.get("block_time"));
                    this.result_list.add(" rqster: " + rqster + "\ntargetdevice: " + targetdevice + "\npush_data: " + push_data + "\nblock_time: " + block_time);
                } else if (name.equals("adduser") && which.equals("adduser")) {
                    String wantuser = String.valueOf(data.get("wantuser"));
                    this.result_list.add("adduser: " + wantuser);
                }
            }
        }catch (ParseException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result_list;
    }

    public HashMap<Object, List> Lookup_device_detail(String input){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            JSONArray actions = (JSONArray) jsonObj.get("actions");
            for (int i = 0; i < actions.size(); i++) {
                JSONObject first = (JSONObject) actions.get(i);
                JSONObject action_trace = (JSONObject) first.get("action_trace");
                JSONObject act = (JSONObject) action_trace.get("act");
                String name = String.valueOf(act.get("name"));
                JSONObject data = (JSONObject) act.get("data");
                String rqster = String.valueOf(data.get("rqster"));
                this.rqster_list.add(rqster);
                this.rqster_list = rqster_list.stream()
                        .filter(rqster_list -> !"null".equals(rqster_list))
                        .collect(Collectors.toList());
                this.rqster_list = rqster_list.stream()
                        .distinct()
                        .collect(Collectors.toList());
                if (name.equals("pushdata")) {
                    String push_data = String.valueOf(data.get("data"));
                    String block_time = String.valueOf(first.get("block_time"));
                    for (int j = 0; j < rqster_list.size(); j++) {
                        List<List> temp = new ArrayList<>();
                        List<String> temp2 = new ArrayList<>();
                        temp2.add(rqster);
                        temp2.add(push_data);
                        temp2.add(block_time);
                        // List<String> temp = new ArrayList<>(); 최근조작내역
                        if (rqster_list.get(j).equals(rqster)) {
                            temp.add(temp2);
                            this.per_iot.put(rqster, temp);
//                            this.per_iot.get(rqster).add(temp2);
                            System.out.println(rqster + ": " + this.per_iot.get(rqster));
                            test_list.add(this.per_iot.get(rqster));
                        }
                    }
                }
            }
        }catch (ParseException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("test_list : " + test_list.get(0));
//        System.out.println("test_list[0][0]: "+test_list.get(0).get(0).toString().replace("[", "").replace("]", ""));
        List<String> myList = new ArrayList<String>();
        JSONObject result_json = new JSONObject();
        for (int i = 0 ; i < test_list.size() ; i ++){
            myList = Arrays.
                    asList(test_list.get(i).get(0).toString().
                            replace("[", "").
                            replace("]", "").
                            split(","));
//            if (result_json.get(myList.get(0)).toString().equals("{}")){
//                result_json.put(myList.get(0), myList.get(1) + myList.get(2));
//            } else {
//                result_json.put(myList.get(0), String.valueOf(result_json.get(myList.get(0))) + myList.get(1) + myList.get(2));
//            }
            result_json.put(myList.get(0),(result_json.get(myList.get(0)))+myList.get(1)+myList.get(2));
        }
        List<String> new_temp_device = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> temp_data = new ArrayList<>();
        temp = Arrays.asList(result_json.keySet().toString().split(","));
        System.out.println("---------------------------------");
        new_temp_device = Arrays.asList(result_json.keySet().toString().replace("[", "").replace("]", "").split(","));
        new_temp_device.stream().map(s -> s.replaceAll("\\s", "")).forEach(System.out::println);
        List<String> temp_device = new_temp_device.stream().map(s->s.replaceAll("\\s", "")).collect(Collectors.toList());
        for (int i = 0 ; i < result_json.keySet().size() ; i ++){
            List<String> temptemp = new ArrayList<>();
            Object name = new Object();
            temp_data = Arrays.asList(result_json.get(temp_device.get(i)).toString().replace("[", "").replace("]", "").split(" "));
            for (int j = 1 ; j < temp_data.size() ; j ++) {
                temptemp.add(temp_data.get(j));
            }
            result_Hash.put(temp_device.get(i), temp_data);
        }
        System.out.println("result_Hash");
        System.out.println("---------------------------------");
        System.out.println(temp_device);
        System.out.println(result_Hash.keySet());
        for (int m = 0 ; m < result_Hash.size() ; m ++){ // 등록된 device 갯수만큼
            List<Object> tmp = new ArrayList<>();
            for (int n = 1 ; n < result_Hash.get(temp_device.get(m)).size() ; n ++){ // 등록된 device의 list size만큼 / n = 1 // null 제거
                tmp.add(result_Hash.get(temp_device.get(m)).get(n));
            }
            result_Hash_Hash.put(temp_device.get(m), tmp);
//            result_Hash_Hash.get(temp_device.get(m)).remove(0);
        }
        System.out.println("==================================");
        System.out.println(result_Hash.get(temp_device.get(0)).get(0));
        System.out.println("----------------------------------");
        System.out.println(per_iot);
        System.out.println("----------------------------------");
        return result_Hash_Hash;
    }

    // ====================================================================================================

    public String Get_Account_names(String input){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            JSONArray accountId = (JSONArray) jsonObj.get("account_names");
            this.result = String.valueOf(accountId);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public String Get_Accout_Public_key(String input){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            JSONArray first_step = (JSONArray) jsonObj.get("permissions");
            JSONObject first = (JSONObject) first_step.get(0);
            JSONObject required_auth = (JSONObject) first.get("required_auth");
            JSONArray keys = (JSONArray) required_auth.get("keys");
            JSONObject second = (JSONObject) keys.get(0);
            String key = (String) second.get("key");
            this.result = String.valueOf(key);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public String Get_Head_Block_time(String input){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            String accountId = (String) jsonObj.get("head_block_time");
            this.result = String.valueOf(accountId);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public String Get_Created(String input){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            String accountId = (String) jsonObj.get("created");
            this.result = String.valueOf(accountId);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public String Get_Account_ID(String input){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            String accountId = (String) jsonObj.get("account_name");
            this.result = String.valueOf(accountId);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public HashMap<String, Integer> Lookup_History(String input){
        List<String> power_on = new ArrayList<String>();
        List<String> power_off = new ArrayList<String>();
        List<String> temp = new ArrayList<String>();
        HashMap<String, Integer> RL = new HashMap();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(input);
            JSONArray actions = (JSONArray) jsonObj.get("actions");
            for (int i = 0; i < actions.size(); i++) {
                JSONObject first = (JSONObject) actions.get(i);
                String time = String.valueOf(first.get("block_time"));             // 시행 시간
                RL.put(time.substring(0, 10), 0);
            }
            for (int i = 0; i < actions.size(); i++) {
                JSONObject first = (JSONObject) actions.get(i);
                String time = String.valueOf(first.get("block_time"));             // 시행 시간
                JSONObject action_trace = (JSONObject) first.get("action_trace");
                JSONObject act = (JSONObject) action_trace.get("act");
                String name = String.valueOf(act.get("name"));
                JSONObject data = (JSONObject) act.get("data");
                String rqster = String.valueOf(data.get("rqster"));
                String targetdevice = String.valueOf(data.get("targetdevice"));
                String OB = String.valueOf(data.get("data"));        // Device_power_IS_on / Device_power_IS_off
                if (temp.size() < 2) {
                    if (OB.equals("Device_power_IS_on")) {
                        temp.add(time);
                    }
                    else if (OB.equals("Device_power_IS_off")){
                        if (temp.size() == 0) {
                            continue;
                        }
                        else {
                            temp.add(time);
                            String On_day = temp.get(0).substring(0, 10);
                            String Off_day = temp.get(1).substring(0, 10);
                            String On_time = temp.get(0).substring(11);
                            String Off_time = temp.get(1).substring(11);

                            String On_day_day = On_day.substring(8);
                            String Off_day_day = Off_day.substring(8);

                            if (On_day_day.equals(Off_day_day)){
                                int On_hour = Integer.parseInt(On_time.substring(0, 2));
                                int Off_hour = Integer.parseInt(Off_time.substring(0, 2));

                                int On_minute = Integer.parseInt(On_time.substring(3, 5));
                                int Off_minute = Integer.parseInt(Off_time.substring(3, 5));

                                int op_minute = ((Off_hour * 60) + Off_minute) - ((On_hour * 60) + On_minute);

                                RL.put(On_day, RL.get(On_day)+op_minute);
                                temp.clear();
                        }
                    }
                }
                else {
                    }
                }
//                if (OB.equals("Device_power_IS_on")) {         // 파워가 켜진 트랜잭션 실행 시간을 리스트에 저장
//                    power_on.add(targetdevice);
//                    power_on.add(time);
//                    continue;
//                }
//                else if (OB.equals("Device_power_IS_off")) {   // 파워가 꺼진 트랜잭션 실행 시간을 리스트에 저장
//                    power_off.add(targetdevice);
//                    power_off.add(time);
//                    continue;
//                }
            }
        }catch (ParseException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return RL;
    }
}