# Dockerfile for API Gateway
FROM openjdk:11-jre-slim
COPY ./ApiGateway/target/ApiGateway-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
EXPOSE 8080
