package MiddleWare;

public class Md_parser_run {
    public static void main(String[] args) {
        Md_parser_test MD = new Md_parser_test();
        String ip = "executed transaction: 2e32a1714e3c761447eb273417dcb24e2b9284dba13dc894d357f10d91a99207  200 bytes  1586 us#         eosio <= eosio::newaccount            {\"creator\":\"eosio\",\"name\":\"testman\",\"owner\":{\"threshold\":1,\"keys\":[{\"key\":\"EOS7YBnFN9rhv3U2AQHFnrD9J...\n" +
                "warning: transaction executed locally, but may not be confirmed by the network yet         ] \n";
        String ip2 = "";
        System.out.println("account_name:  " + MD.cleos_account_name("testman", ip));
        System.out.println("account_key:  " + MD.cleos_account_key("testman", ip));
    }
}
