package com.example.grpc.client;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.lib.proto.CreateReply;
import com.example.lib.proto.CreateRequest;
import com.example.lib.proto.OrderServiceGrpc;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class OrderServiceClient {

	private static final Logger logger = Logger.getLogger(OrderServiceClient.class.getName());

	private final OrderServiceGrpc.OrderServiceBlockingStub blockingStub;

	public OrderServiceClient(Channel channel) {
		blockingStub = OrderServiceGrpc.newBlockingStub(channel);
	}

	/**
	 * 通过购物车ID创建订单
	 * 
	 * @param cartId
	 */
	public void create(String cartId) {
		CreateRequest req = CreateRequest.newBuilder().setCartId(cartId).build();
		CreateReply resp;

		try {
			resp = blockingStub.create(req);
		} catch (StatusRuntimeException e) {
			logger.log(Level.WARNING, "Rpc field: {0}", e.getStatus());
			return;
		}

		logger.info("Created order id: " + resp.getId());
	}

	/**
	 * 测试
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String target = "localhost:9090";

		ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

		try {
			OrderServiceClient client = new OrderServiceClient(channel);
			client.create("test_cart_id");
		} finally {
			channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		}		
	}

}
