package com.zqs.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *  服务器
 */
public class LaptopServer {

    private final int port;
    private final Server server;

    // 初始化服务器
    public LaptopServer(int port) {
        this.port = port;
        LaptopService laptopService = new LaptopService();
        server = ServerBuilder.forPort(port).addService(laptopService).build();
    }

    // 启动服务器
    public void start() throws IOException {
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("shut down gRPC server because JVM shuts down");
            try {
                LaptopServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("server shut down");
        }));
    }

    // 关闭Server
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    // Server阻塞到程序退出
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        LaptopServer laptopServer = new LaptopServer(9999);
        laptopServer.start();
        laptopServer.blockUntilShutdown();
    }
}
