import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            //创建Socket对象，指定服务端的IP地址和端口号
            Socket socket = new Socket("127.0.0.1",12345);

            //获取输入流和输出流，输入流和输出流是通过socket对象来进行数据传输的
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while(true){
                System.out.println("请输入要发送的信息（输入‘exit’退出）：");
                message = reader.readLine();

                if(message.equalsIgnoreCase("exit")){
                    out.println("exit");
                    break;
                }

                //将用户输入的信息发送给服务端
                out.println(message);

                //接受服务端的响应并打印
                String response = in.readLine();
                System.out.println("服务端响应："+response);
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
