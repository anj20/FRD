# Dockerfile for Order Processing Microservice
FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./OrderService/target/RatingService-0.0.1-SNAPSHOT.jar /app/OrderProcessing
ENTRYPOINT ["java", "-jar", "OrderProcessing.jar"]
EXPOSE 8083
