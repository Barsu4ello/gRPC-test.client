package org.example;

import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class Client {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        com.example.grpc.GreetingServiceGrpc.GreetingServiceBlockingStub stub = com.example.grpc.GreetingServiceGrpc.newBlockingStub(channel);

        com.example.grpc.GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder()
                .setName("Vlad")
                .build();

        Iterator<GreetingServiceOuterClass.HelloResponse> iterator = stub.greeting(request);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        channel.shutdownNow();
    }
}
