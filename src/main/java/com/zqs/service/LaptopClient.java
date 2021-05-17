package com.zqs.service;

import com.gitlab.techschool.pcbook.pb.CreateLaptopRequest;
import com.gitlab.techschool.pcbook.pb.CreateLaptopResponse;
import com.gitlab.techschool.pcbook.pb.Laptop;
import com.gitlab.techschool.pcbook.pb.LaptopServiceGrpc;
import com.zqs.sample.Generator;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 客户端
 */
public class LaptopClient {

    // 重要对象: Channel 和 Stub
    private final ManagedChannel channel;
    private final LaptopServiceGrpc.LaptopServiceBlockingStub blockingStub;

    /** 初始化客户端
     *
     * 参数说明:
     *  usePlaintext表示明文传输，否则需要配置ssl
     *  newBlockingStub表示阻塞存根，意味着 RPC 调用要等待服务器应答，将会返回一个应答或抛出一个异常
     */
    public LaptopClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = LaptopServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    // 客户端的请求方法
    public void createLaptop(Laptop laptop) {
        //1. 创建请求
        CreateLaptopRequest request = CreateLaptopRequest.newBuilder().setLaptop(laptop).build();
        //2. 调用Stub的Service方法，传递请求，获取响应(rpc连接)
        CreateLaptopResponse response = blockingStub.createLaptop(request);
        //3. 取出响应对象的字段
        System.out.println("laptop id is " + response.getId());
    }


    // 测试: 客户端向服务端发送请求
    public static void main(String[] args) throws InterruptedException {
        LaptopClient client = new LaptopClient("127.0.0.1", 9999);

        // toBuilder() 在原有对象的基础上，修改属性， build()创建新的对象
        Generator generator = new Generator();
        Laptop laptop = generator.NewLaptop().toBuilder().setId("10002").build();

        try {
            client.createLaptop(laptop);
        } finally {
            client.shutdown();
        }

    }
}
