import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSocket2 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8090);

        System.out.println("Step 1: new ServerSocket(8090)");
        while (true) {
//            循环让server接收连接
            Socket client = serverSocket.accept();
            System.out.println("Step 2: client \t" + client.getPort());
//线程是为了读取数据使用的
            new Thread(new Runnable() {
                Socket socket;

                public Runnable setSocket(Socket socket) {
                    this.socket = socket;
                    return this;
                }

                @Override
                public void run() {
                    try {
                        InputStream in = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        while (true) {
                            System.out.println(reader.readLine());
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
//                实例化一个线程对象，然后通过set方法将客户端的socket传过去。
//                此处的关键在于，因为server.accepts是block的，所以只有接收到了数据才能走到这一步
            }.setSocket(client)).start();

        }
    }
}
