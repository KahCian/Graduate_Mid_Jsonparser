package MiddleWare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubstringTest {
    public static void main(String[] args) throws IOException {
        HashMap<Object, List> result = new HashMap<Object, List>();
        HashMap<String, List> result2 = new HashMap<String, List>();
        List<String> result_list = new ArrayList<>();
        String input =  new String(Files.readAllBytes(Paths.get("C:\\Users\\USER\\IdeaProjects\\MiddleWare\\src\\MiddleWare\\test.txt")));
        String request = "pushdata";
        Search_Json sj = new Search_Json();
        result_list = sj.Recent_user_device(input, "recentuser");
        System.out.println(result_list);
//        System.out.println("-------------------------------------------------");
//        System.out.println(result.keySet()); // key value of result HashMap
//        System.out.println(result); // result
//        System.out.println("-------------------------------------------------");
//        for (int i = 0 ; i < result.keySet().size() ; i++){
//            List<String> device = new ArrayList<String>();
//            device.addAll(result.keySet());
//            System.out.println("----------------------------------------");
//            System.out.println(device.get(i));
//            for (int j = 0 ; j < result.get(device.get(i)).size() ; j++) {
//                System.out.println("==========================================");
//                System.out.println(result.get(device.get(i)).get(j));
//            }
//        }
    }
}

