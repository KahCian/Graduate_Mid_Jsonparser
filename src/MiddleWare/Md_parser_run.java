package MiddleWare;

public class Md_parser_run {
    public static void main(String[] args) {
        String test = "200 bytes  1586 us\n" +
                "#         eosio <= eosio::newaccount            {\"creator\":\"eosio\",\"name\":\"";
        String wallet_name = "testtest";
        String account_name = "testtest";
        Md_parser_test MD = new Md_parser_test();
        System.out.println(MD.cleos_account_name(account_name, MD.cleos_first_strip(test)));
    }
}
