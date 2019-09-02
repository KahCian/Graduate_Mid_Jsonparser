package MiddleWare;

public class Md_parser_test {
    public String cleos_create_key_private(String ip){
        return ip.substring(12, 65);
    }

    public String cleos_create_key_public(String ip){
        return ip.substring(76, 129);
    }

    public String cleos_first_strip(String ip){
        return ip.substring(65);
    }

    public String cleos_create_wallet_private(String wallet_name, String ip) {
        return ip.substring(171 + (wallet_name).length(), 224 + (wallet_name).length());
    }

    public String cleos_account_name(String account_name, String ip) { // 계정 ID 추출
        return ip.substring(148 , 148 + (account_name).length());
    }
    public String cleos_account_key(String account_name, String ip) {
        return ip.substring(222 + (account_name).length(), 275 + (account_name).length());
    }

}


