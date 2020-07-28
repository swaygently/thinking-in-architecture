package com.example.grpc;

import com.example.lib.proto.OrderServiceGrpc;
import com.example.lib.proto.CreateRequest;
import com.example.lib.proto.CreateReply;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderService extends OrderServiceGrpc.OrderServiceImplBase {

	@Override
	public void create(CreateRequest request, io.grpc.stub.StreamObserver<CreateReply> responseObserver) {

		CreateReply reply = CreateReply.newBuilder().build();

		responseObserver.onNext(reply);
		responseObserver.onCompleted();

	}

}
