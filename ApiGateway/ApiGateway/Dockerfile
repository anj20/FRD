# Dockerfile for API Gateway
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/ApiGateway-0.0.1-SNAPSHOT.jar /app/ApiGateway.jar
ENTRYPOINT ["java", "-jar", "ApiGateway.jar"]
EXPOSE 8084
