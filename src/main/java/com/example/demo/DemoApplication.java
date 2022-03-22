package com.example.demo;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Function;

@SpringBootApplication
public class DemoApplication implements ApplicationContextInitializer<GenericApplicationContext> {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public Function<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> uppercase() {
        return value -> {
            System.out.println(value);
            return APIGatewayV2HTTPResponse.builder().withBody("test").withStatusCode(200).build();
        };
    }

    @Override
    public void initialize(GenericApplicationContext context) {

        context.registerBean("function", FunctionRegistration.class,
                () -> new FunctionRegistration<>(uppercase())
                        .type(FunctionType.from(APIGatewayV2HTTPEvent.class).to(APIGatewayV2HTTPResponse.class)));
    }
}