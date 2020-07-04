package com.mashibing.nio;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//在一个线程里面完成了客户端和server端的操作
public class SocketNIO {
    public static void main(String[] args) throws Exception {
        List<SocketChannel> clients = new LinkedList<>();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9090));
        ssc.configureBlocking(false);// --------->!!!!!! OS NonBlocking !!!!!!

//        ssc.setOption(StandardSocketOptions.TCP_NODELAY, false);
//        StandardSocketOptions.IP_MULTICAST_IF;
//        StandardSocketOptions.IP_MULTICAST_LOOP
//        StandardSocketOptions.IP_MULTICAST_TTL
//        StandardSocketOptions.IP_TOS;
//        StandardSocketOptions.SO_BROADCAST
//        StandardSocketOptions.SO_KEEPALIVE
//        StandardSocketOptions.SO_RCVBUF
//        StandardSocketOptions.SO_SNDBUF
//        StandardSocketOptions.SO_REUSEADDR


        while (true) {
//            accept connection from client
            TimeUnit.SECONDS.sleep(1);

            SocketChannel client = ssc.accept();// non blocking ? -1 null
//            accept starts call kernal: no client connect, then return -1: it won't block anyway
//            if client connects, then return the file descriptor of the client
//            the code will continue, but need to handle different case
            if (client == null) {
                System.out.println("Null.... no client");
            } else {
                client.configureBlocking(true); //--------->!!!!!! 三次握手后， !!!!!!
                int port = client.socket().getPort();
                System.out.println("client...port:" + port);
                clients.add(client);
            }
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);// out of heap

//            遍历已经链接进来的客户端能不能读写数据
            for (SocketChannel c : clients) {
                int num = c.read(byteBuffer);// >0 -1 0 //不会阻塞
                if (num > 0) {
                    byteBuffer.flip();//将byteBuffer的开始位置设置为0了
                    byte[] dst = new byte[byteBuffer.limit()];
                    byteBuffer.get(dst);
                    String rst = new String(dst);
                    System.out.println(c.socket().getPort() + " : " + rst);
                    byteBuffer.clear();
                }
            }
        }
    }


}
