package MiddleWare;

import java.util.Scanner;

import com.jcraft.jsch.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args) {

        String username = "root";
        String host = "192.168.0.14";
        int port = 22;
        String password = "root";

        System.out.println("==> Connecting to" + host);
        Session session = null;
        Channel channel = null;

        // 2. 세션 객체를 생성한다 (사용자 이름, 접속할 호스트, 포트를 인자로 준다.)
        try {
            // 1. JSch 객체를 생성한다.
            JSch jsch = new JSch();
            session = jsch.getSession("TechReaderDongKey", "192.168.0.14", 22);
            session.setPassword("dong2307");

            // 4. 세션과 관련된 정보를 설정한다.
            java.util.Properties config = new java.util.Properties();
            // 4-1. 호스트 정보를 검사하지 않는다.
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // 5. 접속한다.
            session.connect();

            // 6. sftp 채널을 연다.
            channel = session.openChannel("exec");

            // 8. 채널을 SSH용 채널 객체로 캐스팅한다
            ChannelExec channelExec = (ChannelExec) channel;

            System.out.println("==> Connected to" + host);

            channelExec.setCommand("touch /Users/DongHo/testssh/jschTest.txt");
            channelExec.connect();

            System.out.println("==> Connected to" + host);

        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }

    }

}
