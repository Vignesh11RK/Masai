# Use OpenJDK 17 as base image
FROM amazoncorretto:17

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/springboot-cicd-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application please
ENTRYPOINT ["java", "-jar", "app.jar"]
