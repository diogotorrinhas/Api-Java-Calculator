# Use the official OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar from the target directory
COPY target/calculator-1.0-SNAPSHOT.jar calculator.jar

# Run the application
ENTRYPOINT ["java", "-jar", "calculator.jar"]
