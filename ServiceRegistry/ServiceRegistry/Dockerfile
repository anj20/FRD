# Dockerfile for Eureka Server
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/ServiceRegistry-0.0.1-SNAPSHOT.jar /app/ServiceRegistry.jar
ENTRYPOINT ["java", "-jar", "ServiceRegistry.jar"]
EXPOSE 8761
