# Use official Maven + OpenJDK 17 image to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside container
WORKDIR /app

# Copy everything into the container
COPY inventory/ .

# Build the Spring Boot app (skip tests to speed up build)
RUN mvn clean package -DskipTests

# Use lightweight JDK image to run the built JAR
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]
