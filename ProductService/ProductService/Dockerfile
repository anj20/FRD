# Dockerfile for Product Management Microservice
FROM openjdk:11-jre-slim
COPY ./ProductService/target/ProductService-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "product-management.jar"]
EXPOSE 8082
