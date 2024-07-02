# Dockerfile for Spring Boot Admin Server
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/spring-boot-admin-0.0.1-SNAPSHOT.jar /app/spring-admin-server.jar
ENTRYPOINT ["java", "-jar", "spring-admin-server.jar"]
EXPOSE 8084
