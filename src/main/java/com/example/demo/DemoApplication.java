package com.example.demo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import java.util.function.Function;

@SpringBootApplication
public class DemoApplication implements ApplicationContextInitializer<GenericApplicationContext> {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    public Function<Message<APIGatewayProxyRequestEvent>, APIGatewayV2HTTPResponse> uppercase() {
//    public Function<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> uppercase() {
//    public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> uppercase() {
    public Function<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> uppercase() {
//        return value -> new GenericMessage<>(APIGatewayV2HTTPResponse.builder().;
//        APIGatewayV2HTTPEvent
        return value -> {
            System.out.println(value);

//            return new APIGatewayProxyResponseEvent().withBody("test");
            return APIGatewayV2HTTPResponse.builder().withBody("test").withStatusCode(200).build();
        };
//        return value -> "woah";
    }

    @Override
    public void initialize(GenericApplicationContext context) {

        context.registerBean("function", FunctionRegistration.class,
                () -> new FunctionRegistration<>(uppercase())
//                        .type(FunctionType.from(Message.class).to(Message.class)));
//                        .type(FunctionType.from(APIGatewayProxyRequestEvent.class).to(APIGatewayProxyResponseEvent.class)));
                        .type(FunctionType.from(APIGatewayV2HTTPEvent.class).to(APIGatewayV2HTTPResponse.class)));
    }

//    public Function<String, String> uppercase() {
//        return value -> {
//            if (value.equals("exception")) {
//                throw new RuntimeException("Intentional exception");
//            } else {
//                return value.toUpperCase();
//            }
//        };
//    }
//
//    @Override
//    public void initialize(GenericApplicationContext context) {
//        context.registerBean("function", FunctionRegistration.class,
//                () -> new FunctionRegistration<>(uppercase())
//                        .type(FunctionType.from(String.class).to(String.class)));
//    }
}