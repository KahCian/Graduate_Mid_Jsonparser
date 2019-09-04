package MiddleWare;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Jisu on 2015-11-26.
 */
public class EchoClient {
    public static void main(String[] args){
        Socket socket = null;
        String ip="192.168.0.16";
        InputStream in = null;
        OutputStream out = null;
        try{
            socket = new Socket(ip, 1111);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            String str = "Hi, Jinsol";
            out.write(str.getBytes());
            byte arr[] = new byte[10];
            in.read(arr);
            System.out.println(new String(arr));

        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                in.close();
                out.close();
                socket.close();
            } catch(Exception e){}
        }
    }
}