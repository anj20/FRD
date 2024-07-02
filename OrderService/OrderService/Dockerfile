# Dockerfile for Order Processing Microservice
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/RatingService-0.0.1-SNAPSHOT.jar /app/OrderService.jar
ENTRYPOINT ["java", "-jar", "OrderService.jar"]
EXPOSE 8083
