# Dockerfile for API Gateway
FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./ApiGateway/target/ApiGateway-0.0.1-SNAPSHOT.jar /app/ApiGateway.jar
ENTRYPOINT ["java", "-jar", "ApiGateway.jar"]
EXPOSE 8084
