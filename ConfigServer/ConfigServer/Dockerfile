FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/ConfigServer-0.0.1-SNAPSHOT.jar /app/ConfigServer.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "ConfigServer.jar"]
