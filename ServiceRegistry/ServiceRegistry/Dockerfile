# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim
# Set the working directory inside the container
WORKDIR /app
# Copy the jar file into the working directory
COPY target/ServiceRegistry-0.0.1-SNAPSHOT.jar /app/eureka-server.jar
# Expose the port the Eureka Server will run on
EXPOSE 8761
# Run the jar file
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]