# Dockerfile for Product Management Microservice
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/ProductService-0.0.1-SNAPSHOT.jar /app/ProductService.jar
ENTRYPOINT ["java", "-jar", "ProductService.jar"]
EXPOSE 8082
