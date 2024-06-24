# Dockerfile for Product Management Microservice
FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./ProductService/target/ProductService-0.0.1-SNAPSHOT.jar /app/ProductService
ENTRYPOINT ["java", "-jar", "ProductService.jar"]
EXPOSE 8082
