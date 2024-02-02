import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            //创建ServerSocket 对象，指定监听的端口号
            ServerSocket serverSocket = new ServerSocket(12345);

            System.out.println("等待客户端连接...");

            //监听客户端的连接请求
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接");

            //获取输入流和输出流，输入流和输出流是通过socket对象来进行数据传输的
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);

            String message;

            while(true){
                //读取客户端发送的信息
                message = in.readLine();
                if(message.equalsIgnoreCase("exit")){
                    break;
                }
                System.out.println("收到客户端消息："+message);
                //发送响应给客户端
                out.println("已收到你的消息"+message);
            }
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
