package MiddleWare;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class  Middleware {

    public static void main(String[] args) {
        ArrayList totalmsg = null;
        String host = "test.rebex.net";
        String user = "demo";
        //여기에 형 EOS Dev 비밀번호
        String password = "dong2307";
//        String command1 = "cleos wallet create --to-console";
        String command1 = "cleos create key --to-console"; // 여기안에 입력하고자 하는 EOS 명령어
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            // Create a JSch session to connect to the server
            Session session = jsch.getSession("gpc", "61.102.44.210", 22); //host: eos vmware ip주소
            session.setPassword("1q2w3e4r");
            session.setConfig(config);
            // Establish the connection
            session.connect();
            System.out.println("Connected...");

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command1);
            channel.setErrStream(System.err);


            InputStream in = channel.getInputStream();
            System.out.println(in);
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
//                    System.out.print(new String(tmp, 0, i));
                    Middleware_parser Md = new Middleware_parser();
                    String result =  new String(tmp, 0, i);
                    String result2 = Md.cleos_create_key_private(result);
                    String result3 = Md.cleos_create_key_public(result);
                    System.out.println("private key is : " + result2);
                    System.out.println("public key is : " + result3);
                }
                if (channel.isClosed()) {
                    System.out.println("Exit Status: "
                            + channel.getExitStatus());
                    break;
                }
                Thread.sleep(1000);
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}