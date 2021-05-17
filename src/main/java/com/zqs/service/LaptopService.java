package com.zqs.service;

import com.gitlab.techschool.pcbook.pb.CreateLaptopRequest;
import com.gitlab.techschool.pcbook.pb.CreateLaptopResponse;
import com.gitlab.techschool.pcbook.pb.Laptop;
import com.gitlab.techschool.pcbook.pb.LaptopServiceGrpc;
import io.grpc.stub.StreamObserver;

// 继承抽象类，重写RPC的Service方法
public class LaptopService extends LaptopServiceGrpc.LaptopServiceImplBase {

    @Override
    public void createLaptop(CreateLaptopRequest request, StreamObserver<CreateLaptopResponse> responseObserver) {
        //1. 从请求对象 CreateLaptopRequest 获取信息
        Laptop laptop = request.getLaptop();
        String id = laptop.getId();
        //2. 执行业务逻辑，创建相应对象 CreateLaptopResponse
        CreateLaptopResponse response = CreateLaptopResponse.newBuilder().setId(id).build();
        //3. onNext()方法向客户端返回结果
        responseObserver.onNext(response);
        //4. 告诉客户端这次调用已经完成
        responseObserver.onCompleted();
    }
}
