package MiddleWare;

public class Middleware_parser {
    public String cleos_create_key_private(String ip){
        return ip.substring(12, 65);
    }

    public String cleos_create_key_public(String ip){
        return ip.substring(76, 129);
    }
}
