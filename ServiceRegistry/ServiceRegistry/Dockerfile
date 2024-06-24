# Dockerfile for Eureka Server
FROM openjdk:11-jre-slim
COPY ./ServiceRegistry/target/ServiceRegistry-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
EXPOSE 8761
