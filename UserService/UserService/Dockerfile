# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim
# Set the working directory inside the container
WORKDIR /app
# Copy the current directory contents into the container at /app
COPY target/UserService-0.0.1-SNAPSHOT.jar /app/user-management.jar
# Expose the port the app runs on
EXPOSE 8081
# Run the jar file
ENTRYPOINT ["java", "-jar", "user-management.jar"]