package MiddleWare;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class  MW {
    String result;

    public String MW(String Command){
        ArrayList totalmsg = null;
        String host = "test.rebex.net";
        String user = "demo";
        //여기에 형 EOS Dev 비밀번호
        String password = "dong2307";
        // String command1 = "ls"; // 여기안에 입력하고자 하는 EOS 명령어
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            // Create a JSch session to connect to the server
            Session session = jsch.getSession("gpc", "192.168.0.17", 22); //host:ip주소
            session.setPassword("dong2307");
            session.setConfig(config);
            // Establish the connection
            session.connect();
            System.out.println("Connected...");

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(Command);
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
                    System.out.print(new String(tmp, 0, i));
                    this.result = new String(tmp, 0, i);
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
        result = result.substring(0, 2);
        return result;
    }
}