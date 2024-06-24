# Dockerfile for Eureka Server
FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./ServiceRegistry/target/ServiceRegistry-0.0.1-SNAPSHOT.jar /app/ServiceRegistry
ENTRYPOINT ["java", "-jar", "ServiceRegistry.jar"]
EXPOSE 8761
