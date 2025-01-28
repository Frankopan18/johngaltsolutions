# Use an official OpenJDK image as the base
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/app.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
