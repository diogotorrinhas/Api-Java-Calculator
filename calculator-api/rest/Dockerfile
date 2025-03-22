# Use the official OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar from the target directory
COPY target/rest-1.0-SNAPSHOT.jar rest.jar

# Run the application
ENTRYPOINT ["java", "-jar", "rest.jar"]
