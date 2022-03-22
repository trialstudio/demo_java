FROM public.ecr.aws/lambda/java:11

# Copy function code and runtime dependencies from Maven layout
COPY target/classes ${LAMBDA_TASK_ROOT}
COPY target/dependency/* ${LAMBDA_TASK_ROOT}/lib/

ENV MAIN_CLASS com.example.demo.DemoApplication

CMD [ "org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest" ]
