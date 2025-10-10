# Use a base image with Java
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file
COPY target/DockerProj-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
