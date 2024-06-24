# Dockerfile for Order Processing Microservice
FROM openjdk:11-jre-slim
COPY ./OrderService/target/RatingService-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "order-processing.jar"]
EXPOSE 8083
