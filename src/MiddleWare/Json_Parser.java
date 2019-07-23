package MiddleWare;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Json_Parser{
    public static void main(String[] args) {

        String get_account = "{\"account_name\":\"polman\",\"head_block_num\":75761,\"head_block_time\":\"2019-07-09T15:04:30.500\",\"privileged\":false,\"last_code_update\":\"2019-06-22T09:57:22.000\",\"created\":\"2019-06-22T09:56:18.500\",\"ram_quota\":-1,\"net_weight\":-1,\"cpu_weight\":-1,\"net_limit\":{\"used\":-1,\"available\":-1,\"max\":-1},\"cpu_limit\":{\"used\":-1,\"available\":-1,\"max\":-1},\"ram_usage\":115864,\"permissions\":[{\"perm_name\":\"active\",\"parent\":\"owner\",\"required_auth\":{\"threshold\":1,\"keys\":[{\"key\":\"EOS5KwnBt3zAiYtTNohwtSwSzuBaL1u2PNzyjrsEGCpYnaX2wvb9o\",\"weight\":1}],\"accounts\":[],\"waits\":[]}},{\"perm_name\":\"owner\",\"parent\":\"\",\"required_auth\":{\"threshold\":1,\"keys\":[{\"key\":\"EOS5KwnBt3zAiYtTNohwtSwSzuBaL1u2PNzyjrsEGCpYnaX2wvb9o\",\"weight\":1}],\"accounts\":[],\"waits\":[]}}],\"total_resources\":null,\"self_delegated_bandwidth\":null,\"refund_request\":null,\"voter_info\":null}";
        String get_transaction = "{\"code\":500,\"message\":\"Internal Service Error\",\"error\":{\"code\":3010009,\"name\":\"transaction_id_type_exception\",\"what\":\"Invalid transaction ID\",\"details\":[]}}";
        String get_key_accounts = "{\"account_names\":[\"HelloWorld\"]}";
        Search_Json SJ = new Search_Json();
        System.out.println(SJ.Get_Account_names(get_key_accounts));
    }
}