package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.catalog.FunctionTypeUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Function;

@SpringBootApplication
public class DemoApplication implements ApplicationContextInitializer<GenericApplicationContext> {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public Function<String, String> uppercase() {
        return value -> {
            if (value.equals("exception")) {
                throw new RuntimeException("Intentional exception");
            } else {
                return value.toUpperCase();
            }
        };
    }

    @Override
    public void initialize(GenericApplicationContext context) {
        context.registerBean("function", FunctionRegistration.class,
                () -> new FunctionRegistration<>(uppercase())
                        .type(FunctionType.from(String.class).to(String.class)));
    }
}
